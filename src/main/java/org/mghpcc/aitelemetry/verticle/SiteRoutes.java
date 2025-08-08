package org.mghpcc.aitelemetry.verticle;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.computate.search.response.solr.SolrResponse;
import org.computate.search.response.solr.SolrResponse.FacetField;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder;
import org.mghpcc.aitelemetry.model.cluster.Cluster;
import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.user.SiteUser;
import org.mghpcc.aitelemetry.user.SiteUserEnUSApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kafka.client.consumer.KafkaConsumer;

public class SiteRoutes {
	protected static final Logger LOG = LoggerFactory.getLogger(SiteRoutes.class);
  
  public static void routes(Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser) {

		router.get("/").handler(eventHandler -> {
			ServiceRequest serviceRequest = apiSiteUser.generateServiceRequest(eventHandler);
			apiSiteUser.user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_ComputateSiteUser, "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
				try {
					SearchList<Cluster> searchList = new SearchList<Cluster>();
					String facetRange = null;

					String solrUsername = config.getString(ComputateConfigKeys.SOLR_USERNAME);
					String solrPassword = config.getString(ComputateConfigKeys.SOLR_PASSWORD);
					String solrHostName = config.getString(ComputateConfigKeys.SOLR_HOST_NAME);
					Integer solrPort = Integer.parseInt(config.getString(ComputateConfigKeys.SOLR_PORT));
					String solrCollection = config.getString(ComputateConfigKeys.SOLR_COLLECTION);
					Boolean solrSsl = Boolean.parseBoolean(config.getString(ComputateConfigKeys.SOLR_SSL));
					String facetUri = String.format(
							"/solr/%s/select%s%s%s"
							, solrCollection
							, "?rows=0&q="
							, URLEncoder.encode("*:*", "UTF-8")
							, "&facet=true&facet.mincount=1&facet.field=classSimpleName_docvalues_string"
							);
					webClient.get(solrPort, solrHostName, facetUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).send().onSuccess(a -> {
						try {
							String gpuDeviceUri = String.format(
									"/solr/%s/select%s%s%s"
									, solrCollection
									, "?rows=0&q="
									, URLEncoder.encode("*:*", "UTF-8")
									, "&facet=true&facet.mincount=1&fq=classSimpleName_docvalues_string:GpuDevice&facet.field=modelName_docvalues_string"
									);
							webClient.get(solrPort, solrHostName, gpuDeviceUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).send().onSuccess(b -> {
								try {

									SolrResponse response = a.bodyAsJson(SolrResponse.class);
									FacetField facetClass = response.getFacetField("classSimpleName_docvalues_string");

									SolrResponse gpuDeviceResponse = b.bodyAsJson(SolrResponse.class);
									FacetField facetGpuDevice = gpuDeviceResponse.getFacetField("modelName_docvalues_string");

									String siteTemplatePath = config.getString(ConfigKeys.TEMPLATE_PATH);
									String pageTemplateUri = "/en-us/HomePage.htm";
									Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
									String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
									JsonObject ctx = ConfigKeys.getPageContext(config);

									SiteUser user = siteRequest.getSiteUser_();
									if(user != null) {
										ctx.put("userName", user.getUserName());
										ctx.put("userEmail", user.getUserEmail());
										ctx.put("userFullName", user.getUserFullName());
										ctx.put("userGroups", siteRequest.getGroups().stream().filter(group -> group.startsWith("/")).collect(Collectors.toList()));
										ctx.put("scopes", siteRequest.getScopes());
									}

									ctx.put("facetClass", facetClass);
									ctx.put("facetGpuDevice", facetGpuDevice);

									String renderedTemplate = jinjava.render(template, ctx.getMap());
									Buffer buffer = Buffer.buffer(renderedTemplate);
									eventHandler.response().putHeader("Content-Type", "text/html");
									eventHandler.end(buffer);
								} catch(Exception ex) {
									LOG.error(String.format("Could not read response from Solr: %s", facetUri), ex);
								}
							}).onFailure(ex -> {
								LOG.error(String.format("Search failed. "), new RuntimeException(ex));
							});
						} catch(Exception ex) {
							LOG.error("Failed to load page. ", ex);
							eventHandler.fail(ex);
						}
					}).onFailure(ex -> {
						LOG.error(String.format("Search failed. "), new RuntimeException(ex));
					});
				} catch(Exception ex) {
					LOG.error("Failed to load page. ", ex);
					eventHandler.fail(ex);
				}
			}).onFailure(ex -> {
				if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
					try {
						eventHandler.redirect("/logout?redirect_uri=" + URLEncoder.encode("/", "UTF-8"));
					} catch(Exception ex2) {
						LOG.error(String.format("searchSiteUser failed. ", ex2));
						eventHandler.fail(ex2);
					}
				} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
					eventHandler.response().setStatusCode(401).setStatusMessage("UNAUTHORIZED")
							.send(Buffer.buffer().appendString(
								new JsonObject()
									.put("errorCode", "401")
									.put("errorMessage", "SSO Resource Permission check returned DENY")
									.encodePrettily()
								)
							);
				} else {
					LOG.error(String.format("searchSiteUser failed. "), ex);
					eventHandler.fail(ex);
				}
			});
		});

		router.getWithRegex("\\/prom-keycloak-proxy/(?<hub>[^/]+)(?<uri>/.*)").handler(oauth2AuthHandler).handler(handler -> {
			String originalUri = handler.pathParam("uri");
			ServiceRequest serviceRequest = apiSiteUser.generateServiceRequest(handler);
			apiSiteUser.user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_ComputateSiteUser, "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
				try {

					String uri = handler.pathParam("uri");
					String hubId = handler.pathParam("hub");

					Integer promKeycloakProxyPort = Integer.parseInt(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_PORT, hubId.toUpperCase().replace("-", ""))));
					String promKeycloakProxyHostName = config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME, hubId.toUpperCase().replace("-", "")));
					Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_SSL, hubId.toUpperCase().replace("-", ""))));

					HttpRequest<Buffer> get = webClient.get(promKeycloakProxyPort, promKeycloakProxyHostName, uri).ssl(promKeycloakProxySsl);
					for(Entry<String, String> entry : handler.queryParams()) {
						String paramName = entry.getKey();
						String paramObject = entry.getValue();
						get.addQueryParam(paramName, paramObject);
					}
					get
							.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUserPrincipal().getString("access_token")))
							.send()
							.expecting(HttpResponseExpectation.SC_OK)
							.onSuccess(metricsResponse -> {
						handler.response().putHeader("Content-Type", "application/json");
						handler.end(Optional.ofNullable(metricsResponse.bodyAsJsonObject()).map(body -> body.toBuffer()).orElse(new JsonObject().toBuffer()));
					}).onFailure(ex -> {
						LOG.error("Failed to query the prom-keycloak-proxy. ", ex);
						handler.fail(403, ex);
					});
				} catch(Exception ex) {
					LOG.error("Failed to load page. ", ex);
					handler.fail(ex);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("Failed to render page %s", originalUri), ex);
				handler.fail(ex);
			});
		});
  }

  public static Future<Void> kafkaConsumer(Vertx vertx, KafkaConsumer<String, String> consumer, JsonObject config) {
	Promise<Void> promise = Promise.promise();
	try {
		String kafkaTopicOrderStatus = config.getString(ConfigKeys.KAFKA_TOPIC_ORDER_STATUS);
		consumer.handler(message -> {
			try {
				String topic = message.topic();
				LOG.info(String.format("Kafka message received on topic %s: %s", topic, message.value()));

				JsonObject result = new JsonObject(message.value().toString());
				String orderId = result.getString("order_id");
				String status = result.getString("status");

				if(orderId != null) {
					JsonObject body = new JsonObject();
					body.put("setStatus", status);
		
					JsonObject pageParams = new JsonObject();
					pageParams.put("scopes", new JsonArray().add("GET").add("PATCH"));
					pageParams.put("body", body);
					pageParams.put("path", new JsonObject());
					pageParams.put("cookie", new JsonObject());
					pageParams.put("query", new JsonObject()
						.put("softCommit", true)
						.put("q", "*:*")
						.put("var", new JsonArray().add("refresh:false"))
						.put("fq", String.format("%s:%s", BareMetalOrder.VAR_solrId, orderId))
						);
					JsonObject pageContext = new JsonObject().put("params", pageParams);
					JsonObject pageRequest = new JsonObject().put("context", pageContext);

					vertx.eventBus().request(BareMetalOrder.CLASS_API_ADDRESS_BareMetalOrder, pageRequest, new DeliveryOptions()
							.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
							.addHeader("action", String.format("patch%sFuture", BareMetalOrder.CLASS_SIMPLE_NAME))
							).onSuccess(message2 -> {
						LOG.info(String.format("Updated %s %s", BareMetalOrder.SingularName_enUS, orderId));
					}).onFailure(ex -> {
						LOG.error(String.format("Failed to update %s %s", BareMetalOrder.SingularName_enUS, orderId), ex);
					});
				}
			} catch(Exception ex) {
				LOG.error(String.format("Failed to import %s", BareMetalOrder.SingularName_enUS), ex);
			}
		});
		consumer.subscribe(kafkaTopicOrderStatus).onSuccess(a -> {
			LOG.info(String.format("Successfully subscribed to topic", kafkaTopicOrderStatus));
			promise.complete();
		}).onFailure(ex -> {
			LOG.error(String.format("Failed to subscribe to topic", kafkaTopicOrderStatus), ex);
			promise.fail(ex);
		});
	} catch(Exception ex) {
		LOG.error("Unable to configure Kafka consumers. ", ex);
		promise.fail(ex);
	}

	return promise.future();
  }
}
