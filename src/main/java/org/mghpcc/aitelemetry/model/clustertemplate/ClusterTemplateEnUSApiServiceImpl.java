package org.mghpcc.aitelemetry.model.clustertemplate;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.request.SiteRequest;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class ClusterTemplateEnUSApiServiceImpl extends ClusterTemplateEnUSGenApiServiceImpl {

	protected Future<JsonObject> queryClusterTemplates(String accessToken) {
		Promise<JsonObject> promise = Promise.promise();
		try {
			Integer fulfillmentApiPort = Integer.parseInt(config.getString(ConfigKeys.FULFILLMENT_API_PORT));
			String fulfillmentApiHostName = config.getString(ConfigKeys.FULFILLMENT_API_HOST_NAME);
			Boolean fulfillmentApiSsl = Boolean.parseBoolean(config.getString(ConfigKeys.FULFILLMENT_API_SSL));
			String fulfillmentApiUri = String.format("/api/fulfillment/v1/cluster_templates");

			webClient.get(fulfillmentApiPort, fulfillmentApiHostName, fulfillmentApiUri).ssl(fulfillmentApiSsl)
					.putHeader("Authorization", String.format("Bearer %s", accessToken))
					.send()
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(templatesResponse -> {
				promise.complete(templatesResponse.bodyAsJsonObject());
			}).onFailure(ex -> {
				LOG.error(String.format("Querying fulfillment API cluster templates failed. ", ClusterTemplate.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error("Querying fulfillment API cluster templates failed. ", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> importResult(String classSimpleName, String classApiAddress, JsonObject result) {
		Promise<Void> promise = Promise.promise();
		try {
			JsonObject body = new JsonObject();
			String templateTitle = result.getString(ClusterTemplate.VAR_title);
			body.put(ClusterTemplate.VAR_id, result.getString(ClusterTemplate.VAR_id));
			body.put(ClusterTemplate.VAR_title, result.getString(ClusterTemplate.VAR_title));
			body.put(ClusterTemplate.VAR_description, result.getString(ClusterTemplate.VAR_description));
			body.put(ClusterTemplate.VAR_parameters, result.getJsonArray(ClusterTemplate.VAR_parameters));

			JsonObject pageParams = new JsonObject();
			pageParams.put("body", body);
			pageParams.put("path", new JsonObject());
			pageParams.put("cookie", new JsonObject());
			pageParams.put("query", new JsonObject().put("softCommit", true).put("q", "*:*").put("var", new JsonArray().add("refresh:false")));
			JsonObject pageContext = new JsonObject().put("params", pageParams);
			JsonObject pageRequest = new JsonObject().put("context", pageContext);

			vertx.eventBus().request(classApiAddress, pageRequest, new DeliveryOptions()
					.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
					.addHeader("action", String.format("putimport%sFuture", classSimpleName))
					).onSuccess(message -> {
				LOG.info("Imported cluster templates");
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format(importDataFail, classSimpleName), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format(importDataFail, classSimpleName), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	protected Future<SearchList<ClusterTemplate>> cleanupOldTemplates(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName, String accessToken) {
		Promise<SearchList<ClusterTemplate>> promise = Promise.promise();
		try {
			SearchList<ClusterTemplate> searchList = new SearchList<ClusterTemplate>();
			searchList.setStore(true);
			searchList.q("*:*");
			searchList.setC(ClusterTemplate.class);
			searchList.fq(String.format("modified_docvalues_date:[* TO %s]", ClusterTemplate.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
			searchList.promiseDeepForClass(siteRequest).onSuccess(oldClusterTemplates -> {
				try {
					List<Future<?>> futures = new ArrayList<>();
					for(Integer i = 0; i < oldClusterTemplates.getList().size(); i++) {
						ClusterTemplate oldClusterTemplate = oldClusterTemplates.getList().get(i);
						futures.add(Future.future(promise1 -> {
							try {
								String templateTitle = oldClusterTemplate.getTitle();
								JsonObject body = new JsonObject().put("setArchived", true);

								JsonObject pageParams = new JsonObject();
								pageParams.put("body", body);
								pageParams.put("path", new JsonObject());
								pageParams.put("cookie", new JsonObject());
								pageParams.put("query", new JsonObject()
									.put("softCommit", true)
									.put("q", "*:*")
									.put("var", new JsonArray().add("refresh:false"))
									.put("fq", String.format("%s:%s", ClusterTemplate.VAR_title, oldClusterTemplate.getTitle()))
									);
								JsonObject pageContext = new JsonObject().put("params", pageParams);
								JsonObject pageRequest = new JsonObject().put("context", pageContext);

								vertx.eventBus().request(ClusterTemplate.CLASS_API_ADDRESS_ClusterTemplate, pageRequest, new DeliveryOptions()
										.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
										.addHeader("action", String.format("patch%sFuture", classSimpleName))
										).onSuccess(message -> {
									LOG.info(String.format("Deleted %s AI cluster", templateTitle));
									promise1.complete(oldClusterTemplates);
								}).onFailure(ex -> {
									LOG.error(String.format(importDataFail, classSimpleName), ex);
									promise.fail(ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format(importDataFail, classSimpleName), ex);
								promise1.fail(ex);
							}
						}));
					}
					Future.all(futures).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format(importDataFail, classSimpleName), ex);
						promise.fail(ex);
					});
				} catch(Throwable ex) {
					LOG.error(String.format(importDataFail, classSimpleName), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				LOG.error(String.format(importDataFail, classSimpleName), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error(String.format(importDataFail, classSimpleName), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

    @Override
    protected Future<Void> importData(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest,
            String classCanonicalName, String classSimpleName, String classApiAddress, String varPageId,
            String varUserUrl, String varDownload) {
		Promise<Void> promise = Promise.promise();
		try {
			ZonedDateTime dateTimeStarted = ZonedDateTime.now();
			String accessToken = config.getString(ConfigKeys.FULFILLMENT_API_OPENSHIFT_TOKEN);
			queryClusterTemplates(accessToken).onSuccess(templateResponse -> {
				LOG.info(templateResponse.encodePrettily());
				List<JsonObject> templates = templateResponse.getJsonArray("items").stream().map(template -> (JsonObject)template).collect(Collectors.toList());
				List<Future<?>> futures = new ArrayList<>();
				for(Integer i = 0; i < templates.size(); i++) {
					JsonObject template = templates.get(i);
					futures.add(Future.future(promise1 -> {
						importResult(classSimpleName, classApiAddress, template).onComplete(b -> {
							promise1.complete();
						}).onFailure(ex -> {
							LOG.error(String.format(importDataFail, classSimpleName), ex);
							promise1.fail(ex);
						});
					}));
				}
				Future.all(futures).onSuccess(b -> {
					cleanupOldTemplates(siteRequest, dateTimeStarted, classSimpleName, accessToken).onSuccess(oldAiNodes -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format(importDataFail, classSimpleName), ex);
						promise.fail(ex);
					});
				}).onFailure(ex -> {
					LOG.error(String.format(importDataFail, classSimpleName), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format(importDataFail, classSimpleName), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error(String.format(importDataFail, classSimpleName), ex);
			promise.fail(ex);
		}
		return promise.future();
    }
}
