package org.mghpcc.aitelemetry.verticle;

import java.util.Map.Entry;

import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder;
import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.user.SiteUser;
import org.mghpcc.aitelemetry.user.SiteUserEnUSApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kafka.client.consumer.KafkaConsumer;

public class SiteRoutes {
	protected static final Logger LOG = LoggerFactory.getLogger(SiteRoutes.class);
  
  public static void routes(Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, SiteUserEnUSApiServiceImpl apiSiteUser) {
		router.getWithRegex("\\/prom-keycloak-proxy(?<uri>.*)").handler(oauth2AuthHandler).handler(handler -> {
			String originalUri = handler.pathParam("uri");
			ServiceRequest serviceRequest = apiSiteUser.generateServiceRequest(handler);
			apiSiteUser.user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_ComputateSiteUser, "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
				try {

					String uri = handler.pathParam("uri");

					Integer promKeycloakProxyPort = Integer.parseInt(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_PORT));
					String promKeycloakProxyHostName = config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME);
					Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_SSL));

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
						handler.end(metricsResponse.bodyAsJsonObject().toBuffer());
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
