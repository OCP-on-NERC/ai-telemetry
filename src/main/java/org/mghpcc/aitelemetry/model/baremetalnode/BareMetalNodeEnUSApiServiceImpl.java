package org.mghpcc.aitelemetry.model.baremetalnode;

import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.request.SiteRequest;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceResponse;

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
			body.put(BareMetalNode.VAR_networkInfo, result.getJsonArray(BareMetalNode.networkInfoESI_enUS));
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

	public static final String ESI_UTC_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";
	public static final DateTimeFormatter ESI_UTC_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(ESI_UTC_DATE_TIME_FORMAT, Locale.US);

	@Override
	public Future<SearchList<BareMetalNode>> searchBareMetalNodeList(SiteRequest siteRequest, Boolean populate,
			Boolean store, Boolean modify) {
		Promise<SearchList<BareMetalNode>> promise = Promise.promise();
		if(BooleanUtils.toBoolean(config.getString(ConfigKeys.ENABLE_THIN_UI))) {
			queryBareMetalNodes().onSuccess(array -> {
				try {
					SearchList<BareMetalNode> searchList = new SearchList<BareMetalNode>();
					List<Future> futures = new ArrayList<>();
					String requestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString(BareMetalNode.VAR_nodeId);
					array.stream().map(o -> (JsonObject)o).filter(result -> requestId == null || requestId.equals(result.getString(BareMetalNode.VAR_nodeId))).forEach(result -> {
						JsonObject body = new JsonObject();
						body.put(BareMetalNode.VAR_solrId, result.getString(BareMetalNode.nodeIdESI_enUS));
						body.put(BareMetalNode.VAR_leaseInfo, result.getJsonArray(BareMetalNode.leaseInfoESI_enUS));
						body.put(BareMetalNode.VAR_networkInfo, result.getJsonArray(BareMetalNode.networkInfoESI_enUS));
						body.put(BareMetalNode.VAR_nodeId, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeIdESI_enUS));
						body.put(BareMetalNode.VAR_nodeIsMaintenance, result.getJsonObject(BareMetalNode.nodeESI_enUS).getBoolean(BareMetalNode.nodeIsMaintenanceESI_enUS));
						body.put(BareMetalNode.VAR_nodeLinks, result.getJsonObject(BareMetalNode.nodeESI_enUS).getJsonArray(BareMetalNode.nodeLinksESI_enUS));
						body.put(BareMetalNode.VAR_nodeName, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeNameESI_enUS));
						body.put(BareMetalNode.VAR_nodePowerState, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodePowerStateESI_enUS));
						body.put(BareMetalNode.VAR_nodeProvisionState, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeProvisionStateESI_enUS));
						body.put(BareMetalNode.VAR_nodeResourceClass, result.getJsonObject(BareMetalNode.nodeESI_enUS).getString(BareMetalNode.nodeResourceClassESI_enUS));

						BareMetalNode node = body.mapTo(BareMetalNode.class);
						node.setSiteRequest_(siteRequest);
						searchList.addList(node);

						futures.add(Future.future(promise1 -> {
							node.promiseDeepBareMetalNode().onSuccess(b -> {
								promise1.complete();
							}).onFailure(ex -> {
								LOG.error(String.format("searchBareMetalNode failed. "), ex);
								promise1.fail(ex);
							});
						}));
					});
					CompositeFuture.all(futures).onSuccess(a -> {
						searchList.promiseDeepForClass(siteRequest).onSuccess(b -> {
							promise.complete(searchList);
						}).onFailure(ex -> {
							LOG.error(String.format("searchBareMetalNode failed. "), ex);
							promise.fail(ex);
						});
					});
				} catch(Exception ex) {
					LOG.error(String.format("searchBareMetalNode failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("searchBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		} else {
			super.searchBareMetalNodeList(siteRequest, populate, store, modify).onSuccess(listBareMetalNode -> {
				promise.complete(listBareMetalNode);
			}).onFailure(ex -> {
				LOG.error(String.format("searchBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		}
		return promise.future();
	}

	@Override
	public Future<ServiceResponse> response200SearchBareMetalNode(SearchList<BareMetalNode> listBareMetalNode) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			List<String> fls = listBareMetalNode.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listBareMetalNode.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = BareMetalNode.varIndexedBareMetalNode(fieldName);
						if(v != null)
							fieldNames.add(BareMetalNode.varIndexedBareMetalNode(fieldName));
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
			LOG.error(String.format("response200SearchBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
}
