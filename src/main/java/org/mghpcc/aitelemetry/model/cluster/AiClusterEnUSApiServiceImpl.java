package org.mghpcc.aitelemetry.model.cluster;

import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;

import java.net.URLEncoder;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class AiClusterEnUSApiServiceImpl extends AiClusterEnUSGenApiServiceImpl {

	public Future<Void> importResult(String classSimpleName, String classApiAddress, JsonObject aiNodeResult, JsonObject gpuDeviceResult) {
		Promise<Void> promise = Promise.promise();
		try {
			String clusterName = aiNodeResult.getJsonObject("metric").getString("cluster");
			JsonObject body = new JsonObject();
			body.put(AiCluster.VAR_pk, clusterName);
			body.put(AiCluster.VAR_clusterName, clusterName);
			body.put(AiCluster.VAR_aiNodesTotal, aiNodeResult.getJsonArray("value").getString(1));
			body.put(AiCluster.VAR_gpuDevicesTotal, gpuDeviceResult.getJsonArray("value").getString(1));

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
				importResultAuth(classSimpleName, classApiAddress, body, clusterName).onSuccess(a -> {
					LOG.info(String.format("Imported %s AI cluster", clusterName));
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format(importDataFail, classSimpleName), ex);
					promise.fail(ex);
				});
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

	public Future<Void> importResultAuth(String classSimpleName, String classApiAddress, JsonObject body, String id) {
		Promise<Void> promise = Promise.promise();
		try {
			ZoneId zoneId = ZoneId.of(config.getString(ComputateConfigKeys.SITE_ZONE));
			String groupName = String.format("%s-%s-GET", classSimpleName, id);
			String policyId = String.format("%s-%s-GET", classSimpleName, id);
			String policyName = String.format("%s-%s-GET", classSimpleName, id);
			String resourceName = String.format("%s-%s", classSimpleName, id);
			String resourceDisplayName = String.format("%s %s", classSimpleName, id);
			String authAdminUsername = config.getString(ComputateConfigKeys.AUTH_ADMIN_USERNAME);
			String authAdminPassword = config.getString(ComputateConfigKeys.AUTH_ADMIN_PASSWORD);
			Integer authPort = Integer.parseInt(config.getString(ComputateConfigKeys.AUTH_PORT));
			String authHostName = config.getString(ComputateConfigKeys.AUTH_HOST_NAME);
			Boolean authSsl = Boolean.parseBoolean(config.getString(ComputateConfigKeys.AUTH_SSL));
			String authRealm = config.getString(ComputateConfigKeys.AUTH_REALM);
			String authClient = config.getString(ComputateConfigKeys.AUTH_CLIENT);
			webClient.post(authPort, authHostName, "/realms/master/protocol/openid-connect/token").ssl(authSsl)
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("username", authAdminUsername)
							.add("password", authAdminPassword)
							.add("grant_type", "password")
							.add("client_id", "admin-cli")
							).onSuccess(tokenResponse -> {
				try {
					String authToken = tokenResponse.bodyAsJsonObject().getString("access_token");
					webClient.post(authPort, authHostName, String.format("/admin/realms/%s/groups", authRealm)).ssl(authSsl)
							.putHeader("Authorization", String.format("Bearer %s", authToken))
							.sendJson(new JsonObject().put("name", groupName))
							.expecting(HttpResponseExpectation.SC_CREATED.or(HttpResponseExpectation.SC_CONFLICT))
							.onSuccess(createGroupResponse -> {
						try {
							webClient.get(authPort, authHostName, String.format("/admin/realms/%s/groups?exact=false&global=true&first=0&max=1&search=%s", authRealm, URLEncoder.encode(groupName, "UTF-8"))).ssl(authSsl)
									.putHeader("Authorization", String.format("Bearer %s", authToken))
									.send()
									.expecting(HttpResponseExpectation.SC_OK)
									.onSuccess(groupsResponse -> {
								try {
									JsonArray groups = Optional.ofNullable(groupsResponse.bodyAsJsonArray()).orElse(new JsonArray());
									JsonObject group = groups.stream().findFirst().map(o -> (JsonObject)o).orElse(null);
									if(group != null) {
										String groupId = group.getString("id");
										webClient.post(authPort, authHostName, String.format("/admin/realms/%s/clients/%s/authz/resource-server/policy/group", authRealm, authClient)).ssl(authSsl)
												.putHeader("Authorization", String.format("Bearer %s", authToken))
												.sendJson(new JsonObject().put("id", policyId).put("name", policyName).put("description", String.format("%s group", groupName)).put("groups", new JsonArray().add(groupId)))
												.expecting(HttpResponseExpectation.SC_CREATED.or(HttpResponseExpectation.SC_CONFLICT))
												.onSuccess(createPolicyResponse -> {
											webClient.post(authPort, authHostName, String.format("/admin/realms/%s/clients/%s/authz/resource-server/resource", authRealm, authClient)).ssl(authSsl)
													.putHeader("Authorization", String.format("Bearer %s", authToken))
													.sendJson(new JsonObject()
															.put("name", resourceName)
															.put("displayName", resourceDisplayName)
															.put("scopes", new JsonArray().add("GET").add("PATCH"))
															)
													.expecting(HttpResponseExpectation.SC_CREATED.or(HttpResponseExpectation.SC_CONFLICT))
													.onSuccess(createResourceResponse -> {
												webClient.post(authPort, authHostName, String.format("/admin/realms/%s/clients/%s/authz/resource-server/permission/scope", authRealm, authClient)).ssl(authSsl)
														.putHeader("Authorization", String.format("Bearer %s", authToken))
														.sendJson(new JsonObject()
																.put("name", String.format("%s-%s", authRealm, groupName))
																.put("description", String.format("GET %s", groupName))
																.put("decisionStrategy", "AFFIRMATIVE")
																.put("resources", new JsonArray().add(resourceName))
																.put("policies", new JsonArray().add(policyName))
																.put("scopes", new JsonArray().add(String.format("%s-GET", authRealm)))
																)
														.expecting(HttpResponseExpectation.SC_CREATED.or(HttpResponseExpectation.SC_CONFLICT))
														.onSuccess(createPermissionResponse -> {
													LOG.info(String.format("Successfully granted %s access to %s", "GET", resourceName));
													promise.complete();
												}).onFailure(ex -> {
													LOG.error(String.format("Failed to create an auth permission for resource %s. ", resourceName), ex);
													promise.fail(ex);
												});
											}).onFailure(ex -> {
												LOG.error(String.format("Failed to create an auth resource %s. ", resourceName), ex);
												promise.fail(ex);
											});
										}).onFailure(ex -> {
											LOG.error(String.format("Failed to create an auth policy for group %s. ", groupName), ex);
											promise.fail(ex);
										});
									} else {
										Throwable ex = new RuntimeException(String.format("Failed to find group %s", groupName));
										LOG.error(ex.getMessage(), ex);
										promise.fail(ex);
									}
								} catch(Throwable ex) {
									LOG.error("Failed to set up fine-grained resource permissions. ", ex);
									promise.fail(ex);
								}
							}).onFailure(ex -> {
								LOG.error(String.format("Failed to query the group %s. ", groupName), ex);
								promise.fail(ex);
							});
						} catch(Throwable ex) {
							LOG.error("Failed to set up fine-grained resource permissions. ", ex);
							promise.fail(ex);
						}
					}).onFailure(ex -> {
						LOG.error(String.format("Failed to create the group %s. ", groupName), ex);
						promise.fail(ex);
					});
				} catch(Throwable ex) {
					LOG.error(String.format("Failed to set up the auth token for fine-grained resource permissions for group %s", groupName), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("Failed to get an admin token while creating fine-grained resource permissions for group %s", groupName), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error(String.format("Failed to set up the auth token for fine-grained resource permissions for %s", classSimpleName), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	protected Future<Void> importData(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest, String classCanonicalName,
			String classSimpleName, String classApiAddress, String varPageId, String varUserUrl, String varDownload) {
		Promise<Void> promise = Promise.promise();
		ZonedDateTime dateTimeStarted = ZonedDateTime.now();
		super.importData(pagePath, vertx, siteRequest, classCanonicalName, classSimpleName, classApiAddress, varPageId, varUserUrl, varDownload).onSuccess(a -> {
			try {
				String authHostName = config.getString(ConfigKeys.AUTH_HOST_NAME);
				Integer authPort = config.getInteger(ConfigKeys.AUTH_PORT);
				String authTokenUri = config.getString(ConfigKeys.AUTH_TOKEN_URI);
				Boolean authSsl = config.getBoolean(ConfigKeys.AUTH_SSL);
				String authClient = config.getString(ConfigKeys.AUTH_CLIENT_SA);
				String authSecret = config.getString(ConfigKeys.AUTH_SECRET_SA);
				MultiMap form = MultiMap.caseInsensitiveMultiMap();
				form.add("grant_type", "client_credentials");
				UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(authClient, authSecret);
				webClient.post(authPort, authHostName, authTokenUri).ssl(authSsl).authentication(credentials)
						.putHeader("Content-Type", "application/json")
						.sendForm(form)
						.expecting(HttpResponseExpectation.SC_OK)
						.onSuccess(requestAuthResponse -> {
					try {
						String accessToken = requestAuthResponse.bodyAsJsonObject().getString("access_token");
						queryAiNodesTotal(classSimpleName, accessToken).onSuccess(aiNodesTotal -> {
							queryGpuDevicesTotal(classSimpleName, accessToken).onSuccess(gpuDevicesTotal -> {
								List<JsonObject> clustersWithAiNodesTotal = aiNodesTotal.stream().filter(aiNodeResult -> !((JsonObject)aiNodeResult).getJsonArray("value").getString(1).equals("0")).map(aiNodeResult -> (JsonObject)aiNodeResult).collect(Collectors.toList());
								List<Future<?>> futures = new ArrayList<>();
								for(Integer i = 0; i < clustersWithAiNodesTotal.size(); i++) {
									JsonObject aiNodeResult = clustersWithAiNodesTotal.get(i);
									JsonObject gpuDeviceResult = gpuDevicesTotal.getJsonObject(i);
									futures.add(Future.future(promise1 -> {
										importResult(classSimpleName, classApiAddress, aiNodeResult, gpuDeviceResult).onComplete(b -> {
											promise1.complete();
										}).onFailure(ex -> {
											LOG.error(String.format(importDataFail, classSimpleName), ex);
											promise1.fail(ex);
										});
									}));
								}
								Future.all(futures).onSuccess(b -> {
									cleanupNonAiNodesTotal(siteRequest, dateTimeStarted, classSimpleName, accessToken).onSuccess(oldAiNodes -> {
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

	protected Future<SearchList<AiCluster>> cleanupNonAiNodesTotal(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName, String accessToken) {
		Promise<SearchList<AiCluster>> promise = Promise.promise();
		try {
			SearchList<AiCluster> searchList = new SearchList<AiCluster>();
			searchList.setStore(true);
			searchList.q("*:*");
			searchList.setC(AiCluster.class);
			searchList.fq(String.format("modified_docvalues_date:[* TO %s]", AiCluster.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
			searchList.promiseDeepForClass(siteRequest).onSuccess(oldAiClusters -> {
				try {
					List<Future<?>> futures = new ArrayList<>();
					for(Integer i = 0; i < oldAiClusters.getList().size(); i++) {
						AiCluster oldAiCluster = oldAiClusters.getList().get(i);
						futures.add(Future.future(promise1 -> {
							try {
								String clusterName = oldAiCluster.getClusterName();
								JsonObject body = new JsonObject();
								body.put(AiCluster.VAR_clusterName, clusterName);
								body.put(AiCluster.VAR_aiNodesTotal, 0);
								body.put(AiCluster.VAR_gpuDevicesTotal, 0);

								JsonObject pageParams = new JsonObject();
								pageParams.put("body", body);
								pageParams.put("path", new JsonObject());
								pageParams.put("cookie", new JsonObject());
								pageParams.put("query", new JsonObject().put("softCommit", true).put("q", "*:*").put("var", new JsonArray().add("refresh:false")));
								JsonObject pageContext = new JsonObject().put("params", pageParams);
								JsonObject pageRequest = new JsonObject().put("context", pageContext);

								vertx.eventBus().request(AiCluster.CLASS_API_ADDRESS_AiCluster, pageRequest, new DeliveryOptions()
										.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
										.addHeader("action", String.format("putimport%sFuture", classSimpleName))
										).onSuccess(message -> {
									LOG.info(String.format("Imported %s AI cluster", clusterName));
									promise1.complete(oldAiClusters);
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

	protected Future<JsonArray> queryAiNodesTotal(String classSimpleName, String accessToken) {
		Promise<JsonArray> promise = Promise.promise();
		try {
			Integer promKeycloakProxyPort = Integer.parseInt(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_PORT));
			String promKeycloakProxyHostName = config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME);
			Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_SSL));
			String promKeycloakProxyUri = String.format("/api/v1/query?query=gpu_operator_gpu_nodes_total");

			webClient.get(promKeycloakProxyPort, promKeycloakProxyHostName, promKeycloakProxyUri).ssl(promKeycloakProxySsl)
					.putHeader("Authorization", String.format("Bearer %s", accessToken))
					.send()
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(metricsResponse -> {
				JsonObject metricsBody = metricsResponse.bodyAsJsonObject();
				promise.complete(metricsBody.getJsonObject("data").getJsonArray("result"));
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

	protected Future<JsonArray> queryGpuDevicesTotal(String classSimpleName, String accessToken) {
		Promise<JsonArray> promise = Promise.promise();
		try {
			Integer promKeycloakProxyPort = Integer.parseInt(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_PORT));
			String promKeycloakProxyHostName = config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME);
			Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_SSL));
			String promKeycloakProxyUri = String.format("/api/v1/query?query=%s", urlEncode("sum by (cluster) (gpu_operator_nvidia_pci_devices_total)"));

			webClient.get(promKeycloakProxyPort, promKeycloakProxyHostName, promKeycloakProxyUri).ssl(promKeycloakProxySsl)
					.putHeader("Authorization", String.format("Bearer %s", accessToken))
					.send()
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(metricsResponse -> {
				JsonObject metricsBody = metricsResponse.bodyAsJsonObject();
				promise.complete(metricsBody.getJsonObject("data").getJsonArray("result"));
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
