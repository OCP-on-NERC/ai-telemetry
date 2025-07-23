package org.mghpcc.aitelemetry.model.baremetalresourceclass;

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

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class BareMetalResourceClassEnUSApiServiceImpl extends BareMetalResourceClassEnUSGenApiServiceImpl {

	protected Future<JsonArray> queryBareMetalResourceClasses() {
		Promise<JsonArray> promise = Promise.promise();
		try {
			Integer esiApiPort = Integer.parseInt(config.getString(ConfigKeys.ESI_API_PORT));
			String esiApiHostName = config.getString(ConfigKeys.ESI_API_HOST_NAME);
			Boolean esiApiSsl = Boolean.parseBoolean(config.getString(ConfigKeys.ESI_API_SSL));
			String esiApiUri = String.format("/api/v1/offers/list");

			webClient.get(esiApiPort, esiApiHostName, esiApiUri).ssl(esiApiSsl)
					.send()
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(resourceClassesResponse -> {
				promise.complete(resourceClassesResponse.bodyAsJsonArray());
			}).onFailure(ex -> {
				LOG.error(String.format("Querying ESI API resource classes failed. ", BareMetalResourceClass.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error("Querying ESI API resource classes failed. ", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> importResult(String classSimpleName, String classApiAddress, JsonObject result) {
		Promise<Void> promise = Promise.promise();
		try {
			JsonObject body = new JsonObject();
			String resourceClassName = result.getString("resource_class");
			body.put(BareMetalResourceClass.VAR_name, resourceClassName);
			body.put(BareMetalResourceClass.VAR_count, result.getString(BareMetalResourceClass.VAR_count));

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
				LOG.info(String.format("Imported %s %s", BareMetalResourceClass.VAR_name, resourceClassName));
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

	protected Future<SearchList<BareMetalResourceClass>> cleanupOldBareMetalResourceClasses(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName) {
		Promise<SearchList<BareMetalResourceClass>> promise = Promise.promise();
		try {
			SearchList<BareMetalResourceClass> searchList = new SearchList<BareMetalResourceClass>();
			searchList.setStore(true);
			searchList.q("*:*");
			searchList.setC(BareMetalResourceClass.class);
			searchList.fq(String.format("modified_docvalues_date:[* TO %s]", BareMetalResourceClass.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
			searchList.rows(100);
			searchList.promiseDeepForClass(siteRequest).onSuccess(oldBareMetalResourceClasses -> {
				try {
					List<Future<?>> futures = new ArrayList<>();
					for(Integer i = 0; i < oldBareMetalResourceClasses.getList().size(); i++) {
						BareMetalResourceClass oldBareMetalResourceClass = oldBareMetalResourceClasses.getList().get(i);
						futures.add(Future.future(promise1 -> {
							try {
								String templateTitle = oldBareMetalResourceClass.getName();
								JsonObject body = new JsonObject().put("setArchived", true);

								JsonObject pageParams = new JsonObject();
								pageParams.put("scopes", new JsonArray().add("GET").add("DELETE"));
								pageParams.put("body", body);
								pageParams.put("path", new JsonObject());
								pageParams.put("cookie", new JsonObject());
								pageParams.put("query", new JsonObject()
									.put("softCommit", true)
									.put("q", "*:*")
									.put("var", new JsonArray().add("refresh:false"))
									.put("fq", String.format("%s:%s", BareMetalResourceClass.VAR_name, oldBareMetalResourceClass.getName()))
									);
								JsonObject pageContext = new JsonObject().put("params", pageParams);
								JsonObject pageRequest = new JsonObject().put("context", pageContext);

								vertx.eventBus().request(BareMetalResourceClass.CLASS_API_ADDRESS_BareMetalResourceClass, pageRequest, new DeliveryOptions()
										.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
										.addHeader("action", String.format("patch%sFuture", classSimpleName))
										).onSuccess(message -> {
									LOG.info(String.format("Archived %s %s", BareMetalResourceClass.VAR_name, templateTitle));
									promise1.complete(oldBareMetalResourceClass);
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
            String classCanonicalName, String classSimpleName, String classApiAddress, String classAuthResource, String varPageId,
            String varUserUrl, String varDownload) {
		Promise<Void> promise = Promise.promise();
		try {
			ZonedDateTime dateTimeStarted = ZonedDateTime.now();
			queryBareMetalResourceClasses().onSuccess(resourceClasses -> {
				List<Future<?>> futures = new ArrayList<>();
				for(Integer i = 0; i < resourceClasses.size(); i++) {
					JsonObject resourceClass = resourceClasses.getJsonObject(i);
					futures.add(Future.future(promise1 -> {
						importResult(classSimpleName, classApiAddress, resourceClass).onComplete(b -> {
							promise1.complete();
						}).onFailure(ex -> {
							LOG.error(String.format(importDataFail, classSimpleName), ex);
							promise1.fail(ex);
						});
					}));
				}
				Future.all(futures).onSuccess(b -> {
					cleanupOldBareMetalResourceClasses(siteRequest, dateTimeStarted, classSimpleName).onSuccess(oldResourceClasses -> {
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