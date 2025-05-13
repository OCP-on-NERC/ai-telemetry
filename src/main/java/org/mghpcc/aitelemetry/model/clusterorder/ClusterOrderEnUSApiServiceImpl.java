package org.mghpcc.aitelemetry.model.clusterorder;

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
import org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder;
import org.mghpcc.aitelemetry.request.SiteRequest;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class ClusterOrderEnUSApiServiceImpl extends ClusterOrderEnUSGenApiServiceImpl {

	protected Future<JsonObject> queryClusterOrders(String accessToken, Long offset, Long limit) {
		Promise<JsonObject> promise = Promise.promise();
		try {
			Integer fulfillmentApiPort = Integer.parseInt(config.getString(ConfigKeys.FULFILLMENT_API_PORT));
			String fulfillmentApiHostName = config.getString(ConfigKeys.FULFILLMENT_API_HOST_NAME);
			Boolean fulfillmentApiSsl = Boolean.parseBoolean(config.getString(ConfigKeys.FULFILLMENT_API_SSL));
			String fulfillmentApiUri = String.format("/api/fulfillment/v1/cluster_orders?offset=%s&limit=%s", offset, limit);

			webClient.get(fulfillmentApiPort, fulfillmentApiHostName, fulfillmentApiUri).ssl(fulfillmentApiSsl)
					.putHeader("Authorization", String.format("Bearer %s", accessToken))
					.send()
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(ordersResponse -> {
				promise.complete(ordersResponse.bodyAsJsonObject());
			}).onFailure(ex -> {
				LOG.error(String.format("Querying fulfillment API cluster orders failed. ", ClusterOrder.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error("Querying fulfillment API cluster orders failed. ", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> importResult(String classSimpleName, String classApiAddress, JsonObject result) {
		Promise<Void> promise = Promise.promise();
		try {
			JsonObject body = new JsonObject();
			body.put(ClusterOrder.VAR_id, result.getString(ClusterOrder.VAR_id));
			body.put(ClusterOrder.VAR_templateId, result.getJsonObject("spec").getString(ClusterOrder.VAR_templateId));
			body.put(ClusterOrder.VAR_state, Optional.ofNullable(result.getJsonObject("status")).map(status -> status.getString(ClusterOrder.VAR_state)).orElse(null));
			body.put(ClusterOrder.VAR_clusterId, Optional.ofNullable(result.getJsonObject("status")).map(status -> status.getString(ClusterOrder.VAR_clusterId)).orElse(null));

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
				LOG.info("Imported cluster orders");
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

	protected Future<SearchList<ClusterOrder>> cleanupOldOrders(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName, String accessToken) {
		Promise<SearchList<ClusterOrder>> promise = Promise.promise();
		vertx.timer(config.getLong(ConfigKeys.IMPORT_CLEANUP_DELAY_SECONDS, 2L) * 1000L).onSuccess(timer -> {
			try {
				SearchList<ClusterOrder> searchList = new SearchList<ClusterOrder>();
				searchList.setStore(true);
				searchList.q("*:*");
				searchList.setC(ClusterOrder.class);
				searchList.fq(String.format("modified_docvalues_date:[* TO %s]", ClusterOrder.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
				searchList.rows(100);
				searchList.promiseDeepForClass(siteRequest).onSuccess(oldClusterOrders -> {
					try {
						List<Future<?>> futures = new ArrayList<>();
						for(Integer i = 0; i < oldClusterOrders.getList().size(); i++) {
							ClusterOrder oldClusterOrder = oldClusterOrders.getList().get(i);
							futures.add(Future.future(promise1 -> {
								try {
									String orderTitle = oldClusterOrder.getId();
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
										.put("fq", String.format("%s:%s", ClusterOrder.VAR_id, oldClusterOrder.getId()))
										);
									JsonObject pageContext = new JsonObject().put("params", pageParams);
									JsonObject pageRequest = new JsonObject().put("context", pageContext);

									vertx.eventBus().request(ClusterOrder.CLASS_API_ADDRESS_ClusterOrder, pageRequest, new DeliveryOptions()
											.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
											.addHeader("action", String.format("patch%sFuture", classSimpleName))
											).onSuccess(message -> {
										LOG.info(String.format("Deleted %s cluster order", orderTitle));
										promise1.complete(oldClusterOrders);
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
		}).onFailure(ex -> {
			LOG.error(String.format(importDataFail, classSimpleName), ex);
			promise.fail(ex);
		});
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
			Long offset = config.getLong(String.format("%s_%s", ConfigKeys.IMPORT_OFFSET, ClusterOrder.CLASS_SIMPLE_NAME), 0L);
			Long limit = config.getLong(String.format("%s_%s", ConfigKeys.IMPORT_LIMIT, ClusterOrder.CLASS_SIMPLE_NAME), 100L);
			queryClusterOrders(accessToken, offset, limit).onSuccess(orderResponse -> {
				Long total = orderResponse.getLong("total");
				List<Future<?>> futures = new ArrayList<>();
				for(Long l = offset; l < total; l += limit) {
					futures.add(Future.future(promise1 -> {
						importDataList(pagePath, vertx, siteRequest, classCanonicalName, classSimpleName, classApiAddress, varPageId, varUserUrl, varDownload, dateTimeStarted, accessToken, offset, limit).onComplete(b -> {
							promise1.complete();
						}).onFailure(ex -> {
							LOG.error(String.format(importDataFail, classSimpleName), ex);
							promise1.fail(ex);
						});
					}));
				}
				Future.join(futures).onSuccess(b -> {
					cleanupOldOrders(siteRequest, dateTimeStarted, classSimpleName, accessToken).onSuccess(oldAiNodes -> {
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

    protected Future<Void> importDataList(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest,
            String classCanonicalName, String classSimpleName, String classApiAddress, String varPageId,
            String varUserUrl, String varDownload, ZonedDateTime dateTimeStarted, String accessToken, 
			Long offset, Long limit) {
		Promise<Void> promise = Promise.promise();
		try {
			queryClusterOrders(accessToken, offset, limit).onSuccess(orderResponse -> {
				List<Future<?>> futures = new ArrayList<>();
				List<JsonObject> orders = orderResponse.getJsonArray("items").stream().map(order -> (JsonObject)order).collect(Collectors.toList());
				for(Integer i = 0; i < orders.size(); i++) {
					JsonObject order = orders.get(i);
					futures.add(Future.future(promise1 -> {
						importResult(classSimpleName, classApiAddress, order).onComplete(b -> {
							promise1.complete();
						}).onFailure(ex -> {
							LOG.error(String.format(importDataFail, classSimpleName), ex);
							promise1.fail(ex);
						});
					}));
				}
				Future.join(futures).onSuccess(b -> {
					promise.complete();
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
	public Future<ClusterOrder> sqlPOSTClusterOrder(ClusterOrder o, Boolean inheritPrimaryKey) {
		Promise<ClusterOrder> promise = Promise.promise();
		try {		
			Integer fulfillmentApiPort = Integer.parseInt(config.getString(ConfigKeys.FULFILLMENT_API_PORT));
			String fulfillmentApiHostName = config.getString(ConfigKeys.FULFILLMENT_API_HOST_NAME);
			Boolean fulfillmentApiSsl = Boolean.parseBoolean(config.getString(ConfigKeys.FULFILLMENT_API_SSL));
			String fulfillmentApiUri = String.format("/api/fulfillment/v1/cluster_orders");
			String accessToken = config.getString(ConfigKeys.FULFILLMENT_API_OPENSHIFT_TOKEN);

			JsonObject orderJson = o.getSiteRequest_().getJsonObject();
			String templateId = orderJson.getString("templateId");
			String orderJsonId = orderJson.getString("id");

			JsonObject spec = new JsonObject();
			spec.put("templateId", templateId);
			JsonObject body = new JsonObject();
			body.put("spec", spec);

			if(orderJsonId == null) {
				webClient.post(fulfillmentApiPort, fulfillmentApiHostName, fulfillmentApiUri).ssl(fulfillmentApiSsl)
						.putHeader("Authorization", String.format("Bearer %s", accessToken))
						.sendJsonObject(body)
						.expecting(HttpResponseExpectation.SC_OK)
						.onSuccess(clusterOrderResponse -> {
					orderJson.put("id", clusterOrderResponse.bodyAsJsonObject().getString("id"));
					super.sqlPOSTClusterOrder(o, inheritPrimaryKey).onSuccess(o2 -> {
						promise.complete(o2);
					}).onFailure(ex -> {
						LOG.error(String.format("sqlPOSTClusterOrder fail"), ex);
						promise.fail(ex);
					});
				}).onFailure(ex -> {
					LOG.error(String.format("POST to fulfillment service cluster order endpoint failed. "), ex);
				});
			} else {
				super.sqlPOSTClusterOrder(o, inheritPrimaryKey).onSuccess(o2 -> {
					promise.complete(o2);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPOSTClusterOrder fail"), ex);
					promise.fail(ex);
				});
			}
		} catch(Exception ex) {
			LOG.error(String.format("sqlPOSTClusterOrder failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public Future<SearchList<ClusterOrder>> searchClusterOrderList(SiteRequest siteRequest, Boolean populate,
			Boolean store, Boolean modify) {
		Promise<SearchList<ClusterOrder>> promise = Promise.promise();
		if(BooleanUtils.toBoolean(config.getString(ConfigKeys.ENABLE_THIN_UI))) {
			try {
				String accessToken = config.getString(ConfigKeys.FULFILLMENT_API_OPENSHIFT_TOKEN);
				Long offset = config.getLong(String.format("%s_%s", ConfigKeys.IMPORT_OFFSET, ClusterOrder.CLASS_SIMPLE_NAME), 0L);
				Long limit = config.getLong(String.format("%s_%s", ConfigKeys.IMPORT_LIMIT, ClusterOrder.CLASS_SIMPLE_NAME), 100L);
				queryClusterOrders(accessToken, offset, limit).onSuccess(results -> {
					try {
						SearchList<ClusterOrder> searchList = new SearchList<ClusterOrder>();
						List<Future> futures = new ArrayList<>();
						String requestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString(ClusterOrder.VAR_id);
						results.getJsonArray("items").stream().map(o -> (JsonObject)o).filter(result -> requestId == null || requestId.equals(result.getString(ClusterOrder.VAR_id))).forEach(result -> {
							JsonObject body = new JsonObject();
							body.put(ClusterOrder.VAR_solrId, result.getString(ClusterOrder.VAR_id));
							body.put(ClusterOrder.VAR_id, result.getString(ClusterOrder.VAR_id));
							body.put(ClusterOrder.VAR_templateId, result.getJsonObject("spec").getString(ClusterOrder.VAR_templateId));
							body.put(ClusterOrder.VAR_state, Optional.ofNullable(result.getJsonObject("status")).map(status -> status.getString(ClusterOrder.VAR_state)).orElse(null));
							body.put(ClusterOrder.VAR_clusterId, Optional.ofNullable(result.getJsonObject("status")).map(status -> status.getString(ClusterOrder.VAR_clusterId)).orElse(null));

							ClusterOrder order = body.mapTo(ClusterOrder.class);
							order.setSiteRequest_(siteRequest);
							searchList.addList(order);

							futures.add(Future.future(promise1 -> {
								order.promiseDeepClusterOrder().onSuccess(b -> {
									promise1.complete();
								}).onFailure(ex -> {
									LOG.error(String.format("searchClusterOrder failed. "), ex);
									promise1.fail(ex);
								});
							}));
						});
						CompositeFuture.all(futures).onSuccess(a -> {
							searchList.promiseDeepForClass(siteRequest).onSuccess(b -> {
								promise.complete(searchList);
							}).onFailure(ex -> {
								LOG.error(String.format("searchClusterOrder failed. "), ex);
								promise.fail(ex);
							});
						});
					} catch(Exception ex) {
						LOG.error(String.format("searchClusterOrder failed. "), ex);
						promise.fail(ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("searchClusterOrder failed. "), ex);
					promise.fail(ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("searchClusterOrder failed. "), ex);
				promise.fail(ex);
			}
		} else {
			super.searchClusterOrderList(siteRequest, populate, store, modify).onSuccess(listClusterOrder -> {
				promise.complete(listClusterOrder);
			}).onFailure(ex -> {
				LOG.error(String.format("searchClusterOrder failed. "), ex);
				promise.fail(ex);
			});
		}
		return promise.future();
	}

	@Override
	public Future<ServiceResponse> response200SearchClusterOrder(SearchList<ClusterOrder> listClusterOrder) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			List<String> fls = listClusterOrder.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listClusterOrder.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = ClusterOrder.varIndexedClusterOrder(fieldName);
						if(v != null)
							fieldNames.add(ClusterOrder.varIndexedClusterOrder(fieldName));
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
			LOG.error(String.format("response200SearchClusterOrder failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
}
