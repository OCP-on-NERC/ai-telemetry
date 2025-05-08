package org.mghpcc.aitelemetry.model.baremetalnode;

import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.request.SiteRequest;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Translate: false
 **/
public class BareMetalNodeEnUSApiServiceImpl extends BareMetalNodeEnUSGenApiServiceImpl {

	protected Future<JsonArray> queryBareMetalNodes() {
		Promise<JsonArray> promise = Promise.promise();
		try {
			Integer esiApiPort = Integer.parseInt(config.getString(ConfigKeys.ESI_API_PORT));
			String esiApiHostName = config.getString(ConfigKeys.ESI_API_HOST_NAME);
			Boolean esiApiSsl = Boolean.parseBoolean(config.getString(ConfigKeys.ESI_API_SSL));
			String esiApiUri = String.format("/api/v1/nodes/list");

			webClient.get(esiApiPort, esiApiHostName, esiApiUri).ssl(esiApiSsl)
					.send()
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(templatesResponse -> {
				promise.complete(templatesResponse.bodyAsJsonArray());
			}).onFailure(ex -> {
				LOG.error(String.format("Querying ESI API cluster templates failed. ", BareMetalNode.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error("Querying ESI API cluster templates failed. ", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> importResult(String classSimpleName, String classApiAddress, JsonObject result) {
		Promise<Void> promise = Promise.promise();
		try {
			JsonObject body = new JsonObject();
			String nodeName = result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeNameESI_enUS);
			body.put(BareMetalNode.VAR_leaseInfo, result.getJsonArray(BareMetalNode.leaseInfoESI_enUS));
			body.put(BareMetalNode.VAR_networkInfo, result.getString(BareMetalNode.networkInfoESI_enUS));
			body.put(BareMetalNode.VAR_nodeId, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeIdESI_enUS));
			body.put(BareMetalNode.VAR_nodeIsMaintenance, result.getJsonObject(BareMetalNode.nodeESI_enUS).getBoolean(BareMetalNode.nodeIsMaintenanceESI_enUS));
			body.put(BareMetalNode.VAR_nodeLinks, result.getJsonObject(BareMetalNode.nodeESI_enUS).getJsonArray(BareMetalNode.nodeLinksESI_enUS));
			body.put(BareMetalNode.VAR_nodeName, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeNameESI_enUS));
			body.put(BareMetalNode.VAR_nodePowerState, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodePowerStateESI_enUS));
			body.put(BareMetalNode.VAR_nodeProvisionState, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeProvisionStateESI_enUS));
			body.put(BareMetalNode.VAR_nodeResourceClass, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeResourceClassESI_enUS));
		
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
				LOG.info(String.format("Imported %s %s", BareMetalNode.SingularName_enUS, nodeName));
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

	protected Future<SearchList<BareMetalNode>> cleanupOldBareMetalNodes(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName) {
		Promise<SearchList<BareMetalNode>> promise = Promise.promise();
		try {
			SearchList<BareMetalNode> searchList = new SearchList<BareMetalNode>();
			searchList.setStore(true);
			searchList.q("*:*");
			searchList.setC(BareMetalNode.class);
			searchList.fq(String.format("modified_docvalues_date:[* TO %s]", BareMetalNode.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
			searchList.rows(100);
			searchList.promiseDeepForClass(siteRequest).onSuccess(oldBareMetalNodes -> {
				try {
					List<Future<?>> futures = new ArrayList<>();
					for(Integer i = 0; i < oldBareMetalNodes.getList().size(); i++) {
						BareMetalNode oldBareMetalNode = oldBareMetalNodes.getList().get(i);
						futures.add(Future.future(promise1 -> {
							try {
								String nodeName = oldBareMetalNode.getNodeName();

								JsonObject pageParams = new JsonObject();
								pageParams.put("scopes", new JsonArray().add("GET").add("DELETE"));
								pageParams.put("path", new JsonObject());
								pageParams.put("cookie", new JsonObject());
								pageParams.put("query", new JsonObject()
									.put("softCommit", true)
									.put("q", "*:*")
									.put("var", new JsonArray().add("refresh:false"))
									.put("fq", String.format("%s:%s", BareMetalNode.VAR_nodeName, oldBareMetalNode.getNodeName()))
									);
								JsonObject pageContext = new JsonObject().put("params", pageParams);
								JsonObject pageRequest = new JsonObject().put("context", pageContext);

								vertx.eventBus().request(BareMetalNode.CLASS_API_ADDRESS_BareMetalNode, pageRequest, new DeliveryOptions()
										.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
										.addHeader("action", String.format("delete%sFuture", classSimpleName))
										).onSuccess(message -> {
									LOG.info(String.format("Deleted %s %s", BareMetalNode.SingularName_enUS, nodeName));
									promise1.complete(oldBareMetalNodes);
								}).onFailure(ex -> {
									LOG.error(String.format(importDataFail, classSimpleName), ex);
									promise1.fail(ex);
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
			queryBareMetalNodes().onSuccess(nodes -> {
				List<Future<?>> futures = new ArrayList<>();
				for(Integer i = 0; i < nodes.size(); i++) {
					JsonObject template = nodes.getJsonObject(i);
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
					cleanupOldBareMetalNodes(siteRequest, dateTimeStarted, classSimpleName).onSuccess(oldAiNodes -> {
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
