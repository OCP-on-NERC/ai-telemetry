package org.mghpcc.aitelemetry.model.managedcluster;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
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
public class ManagedClusterEnUSApiServiceImpl extends ManagedClusterEnUSGenApiServiceImpl {

	protected Future<JsonObject> queryManagedClusters(String accessToken) {
		Promise<JsonObject> promise = Promise.promise();
		try {
			Integer fulfillmentApiPort = Integer.parseInt(config.getString(ConfigKeys.FULFILLMENT_API_PORT));
			String fulfillmentApiHostName = config.getString(ConfigKeys.FULFILLMENT_API_HOST_NAME);
			Boolean fulfillmentApiSsl = Boolean.parseBoolean(config.getString(ConfigKeys.FULFILLMENT_API_SSL));
			String fulfillmentApiUri = String.format("/api/fulfillment/v1/clusters");

			webClient.get(fulfillmentApiPort, fulfillmentApiHostName, fulfillmentApiUri).ssl(fulfillmentApiSsl)
					.putHeader("Authorization", String.format("Bearer %s", accessToken))
					.send()
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(clustersResponse -> {
				promise.complete(clustersResponse.bodyAsJsonObject());
			}).onFailure(ex -> {
				LOG.error(String.format("Querying fulfillment API clusters failed. ", ManagedCluster.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error("Querying fulfillment API clusters failed. ", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> importResult(String classSimpleName, String classApiAddress, JsonObject result) {
		Promise<Void> promise = Promise.promise();
		try {
			JsonObject body = new JsonObject();
			body.put(ManagedCluster.VAR_id, result.getString(ManagedCluster.VAR_id));
			body.put(ManagedCluster.VAR_state, result.getJsonObject("status").getString(ManagedCluster.VAR_state));
			body.put(ManagedCluster.VAR_apiUrl, result.getJsonObject("status").getString(ManagedCluster.VAR_apiUrl));
			body.put(ManagedCluster.VAR_consoleUrl, result.getJsonObject("status").getString(ManagedCluster.VAR_consoleUrl));

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
				LOG.info("Imported tenant clusters");
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

	protected Future<SearchList<ManagedCluster>> cleanupOldClusters(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName, String accessToken) {
		Promise<SearchList<ManagedCluster>> promise = Promise.promise();
		try {
			SearchList<ManagedCluster> searchList = new SearchList<ManagedCluster>();
			searchList.setStore(true);
			searchList.q("*:*");
			searchList.setC(ManagedCluster.class);
			searchList.fq(String.format("modified_docvalues_date:[* TO %s]", ManagedCluster.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
			searchList.rows(100);
			searchList.promiseDeepForClass(siteRequest).onSuccess(oldManagedClusters -> {
				try {
					List<Future<?>> futures = new ArrayList<>();
					for(Integer i = 0; i < oldManagedClusters.getList().size(); i++) {
						ManagedCluster oldManagedCluster = oldManagedClusters.getList().get(i);
						futures.add(Future.future(promise1 -> {
							try {
								String clusterTitle = oldManagedCluster.getId();
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
									.put("fq", String.format("%s:%s", ManagedCluster.VAR_id, oldManagedCluster.getId()))
									);
								JsonObject pageContext = new JsonObject().put("params", pageParams);
								JsonObject pageRequest = new JsonObject().put("context", pageContext);

								vertx.eventBus().request(ManagedCluster.CLASS_API_ADDRESS_ManagedCluster, pageRequest, new DeliveryOptions()
										.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
										.addHeader("action", String.format("patch%sFuture", classSimpleName))
										).onSuccess(message -> {
									LOG.info(String.format("Deleted %s tenant cluster", clusterTitle));
									promise1.complete(oldManagedClusters);
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
			queryManagedClusters(accessToken).onSuccess(clusterResponse -> {
				List<JsonObject> clusters = clusterResponse.getJsonArray("items").stream().map(cluster -> (JsonObject)cluster).collect(Collectors.toList());
				List<Future<?>> futures = new ArrayList<>();
				for(Integer i = 0; i < clusters.size(); i++) {
					JsonObject cluster = clusters.get(i);
					futures.add(Future.future(promise1 -> {
						importResult(classSimpleName, classApiAddress, cluster).onComplete(b -> {
							promise1.complete();
						}).onFailure(ex -> {
							LOG.error(String.format(importDataFail, classSimpleName), ex);
							promise1.fail(ex);
						});
					}));
				}
				Future.all(futures).onSuccess(b -> {
					cleanupOldClusters(siteRequest, dateTimeStarted, classSimpleName, accessToken).onSuccess(oldAiNodes -> {
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

	@Override
	public Future<SearchList<ManagedCluster>> searchManagedClusterList(SiteRequest siteRequest, Boolean populate,
			Boolean store, Boolean modify) {
		Promise<SearchList<ManagedCluster>> promise = Promise.promise();
		if(BooleanUtils.toBoolean(config.getString(ConfigKeys.ENABLE_THIN_UI))) {
			try {
				String accessToken = config.getString(ConfigKeys.FULFILLMENT_API_OPENSHIFT_TOKEN);
				queryManagedClusters(accessToken).onSuccess(results -> {
					try {
						SearchList<ManagedCluster> searchList = new SearchList<ManagedCluster>();
						List<Future> futures = new ArrayList<>();
						String requestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString(ManagedCluster.VAR_id);
						results.getJsonArray("items").stream().map(o -> (JsonObject)o).filter(result -> requestId == null || requestId.equals(result.getString(ManagedCluster.VAR_id))).forEach(result -> {
							JsonObject body = new JsonObject();
							body.put(ManagedCluster.VAR_solrId, result.getString(ManagedCluster.VAR_id));
							body.put(ManagedCluster.VAR_id, result.getString(ManagedCluster.VAR_id));
							body.put(ManagedCluster.VAR_state, result.getJsonObject("status").getString(ManagedCluster.VAR_state));
							body.put(ManagedCluster.VAR_apiUrl, result.getJsonObject("status").getString(ManagedCluster.VAR_apiUrl));
							body.put(ManagedCluster.VAR_consoleUrl, result.getJsonObject("status").getString(ManagedCluster.VAR_consoleUrl));

							ManagedCluster cluster = body.mapTo(ManagedCluster.class);
							cluster.setSiteRequest_(siteRequest);
							searchList.addList(cluster);

							futures.add(Future.future(promise1 -> {
								cluster.promiseDeepManagedCluster().onSuccess(b -> {
									promise1.complete();
								}).onFailure(ex -> {
									LOG.error(String.format("searchManagedCluster failed. "), ex);
									promise1.fail(ex);
								});
							}));
						});
						CompositeFuture.all(futures).onSuccess(a -> {
							searchList.promiseDeepForClass(siteRequest).onSuccess(b -> {
								promise.complete(searchList);
							}).onFailure(ex -> {
								LOG.error(String.format("searchManagedCluster failed. "), ex);
								promise.fail(ex);
							});
						});
					} catch(Exception ex) {
						LOG.error(String.format("searchManagedCluster failed. "), ex);
						promise.fail(ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("searchManagedCluster failed. "), ex);
					promise.fail(ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("searchManagedCluster failed. "), ex);
				promise.fail(ex);
			}
		} else {
			super.searchManagedClusterList(siteRequest, populate, store, modify).onSuccess(listManagedCluster -> {
				promise.complete(listManagedCluster);
			}).onFailure(ex -> {
				LOG.error(String.format("searchManagedCluster failed. "), ex);
				promise.fail(ex);
			});
		}
		return promise.future();
	}

	@Override
	public Future<ServiceResponse> response200SearchManagedCluster(SearchList<ManagedCluster> listManagedCluster) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			List<String> fls = listManagedCluster.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listManagedCluster.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = ManagedCluster.varIndexedManagedCluster(fieldName);
						if(v != null)
							fieldNames.add(ManagedCluster.varIndexedManagedCluster(fieldName));
					}
					if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals("saves_docvalues_strings")) {
						fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray("saves_docvalues_strings")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));
						fieldNames.remove("pk_docvalues_long");
						fieldNames.remove("created_docvalues_date");
					}
					else if(fls.size() >= 1) {
						fieldNames.removeAll(fls);
					}
					for(String fieldName : fieldNames) {
						if(!fls.contains(fieldName))
							json2.remove(fieldName);
					}
				}
				l.add(json2);
			});
			json.put("list", l);
			promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchManagedCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
}
