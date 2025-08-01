package org.mghpcc.aitelemetry.model.hub;

import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import java.net.URLEncoder;
import java.nio.file.Path;
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
import org.mghpcc.aitelemetry.model.cluster.Cluster;
import org.mghpcc.aitelemetry.request.SiteRequest;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.config.yaml.YamlProcessor;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class HubEnUSApiServiceImpl extends HubEnUSGenApiServiceImpl {

    protected Future<JsonArray> queryClusterCapacityMemoryBytes(Hub hub, String classSimpleName, String accessToken) {
        Promise<JsonArray> promise = Promise.promise();
        try {
            Integer promKeycloakProxyPort = hub.getPromKeycloakProxyPort();
            String promKeycloakProxyHostName = hub.getPromKeycloakProxyHostName();
            Boolean promKeycloakProxySsl = hub.getPromKeycloakProxySsl();
            String promKeycloakProxyUri = String.format("/api/v1/query?query=cluster:capacity_memory_bytes:sum{label_node_role_kubernetes_io!=\"master\"}");

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

    protected Future<JsonArray> queryClusterCapacityCpuCores(Hub hub, String classSimpleName, String accessToken) {
        Promise<JsonArray> promise = Promise.promise();
        try {
            Integer promKeycloakProxyPort = hub.getPromKeycloakProxyPort();
            String promKeycloakProxyHostName = hub.getPromKeycloakProxyHostName();
            Boolean promKeycloakProxySsl = hub.getPromKeycloakProxySsl();
            String promKeycloakProxyUri = String.format("/api/v1/query?query=cluster:capacity_cpu_cores:sum{label_node_role_kubernetes_io!=\"master\"}");

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

    protected Future<JsonArray> queryAiNodesTotal(Hub hub, String classSimpleName, String accessToken) {
        Promise<JsonArray> promise = Promise.promise();
        try {
            Integer promKeycloakProxyPort = hub.getPromKeycloakProxyPort();
            String promKeycloakProxyHostName = hub.getPromKeycloakProxyHostName();
            Boolean promKeycloakProxySsl = hub.getPromKeycloakProxySsl();
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

    protected Future<JsonArray> queryGpuDevicesTotal(Hub hub, String classSimpleName, String accessToken) {
        Promise<JsonArray> promise = Promise.promise();
        try {
            Integer promKeycloakProxyPort = hub.getPromKeycloakProxyPort();
            String promKeycloakProxyHostName = hub.getPromKeycloakProxyHostName();
            Boolean promKeycloakProxySsl = hub.getPromKeycloakProxySsl();
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

    public Future<Void> importCluster(Hub hub, String classSimpleName, String classApiAddress, JsonObject clusterMemoryBytesResult, JsonObject clusterCpuCoresResult, JsonObject aiNodeResult, JsonObject gpuDeviceResult) {
        Promise<Void> promise = Promise.promise();
        try {
            String hubId = hub.getHubId();
            String clusterName = clusterMemoryBytesResult.getString("clusterName");
            String hubResource = String.format("%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId);
            String clusterResource = String.format("%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
            JsonObject body = new JsonObject();
            body.put(Cluster.VAR_pk, clusterResource);
            body.put(Cluster.VAR_hubId, hubId);
            body.put(Cluster.VAR_hubResource, hubResource);
            body.put(Cluster.VAR_clusterResource, clusterResource);
            body.put(Cluster.VAR_clusterName, clusterName);
            body.put(Cluster.VAR_aiNodesTotal, Optional.ofNullable(aiNodeResult).map(result -> result.getJsonArray("value").getString(1)).orElse(null));
            body.put(Cluster.VAR_gpuDevicesTotal, Optional.ofNullable(gpuDeviceResult).map(result -> result.getJsonArray("value").getString(1)).orElse(null));
            body.put(Cluster.VAR_cpuCoresTotal, Optional.ofNullable(clusterCpuCoresResult).map(result -> result.getJsonArray("value").getString(1)).orElse(null));
            body.put(Cluster.VAR_memoryBytesTotal, Optional.ofNullable(clusterMemoryBytesResult).map(result -> result.getJsonArray("value").getString(1)).orElse(null));
            body.put(Cluster.VAR_promKeycloakProxyHostName, hub.getPromKeycloakProxyHostName());
            body.put(Cluster.VAR_promKeycloakProxyPort, hub.getPromKeycloakProxyPort());
            body.put(Cluster.VAR_promKeycloakProxySsl, hub.getPromKeycloakProxySsl());

            JsonObject pageParams = new JsonObject();
            pageParams.put("body", body);
            pageParams.put("path", new JsonObject());
            pageParams.put("cookie", new JsonObject());
            pageParams.put("query", new JsonObject().put("softCommit", true).put("q", "*:*").put("var", new JsonArray().add("refresh:false")));
            pageParams.put("scopes", new JsonArray().add("GET").add("POST").add("PATCH").add("PUT"));
            JsonObject pageContext = new JsonObject().put("params", pageParams);
            JsonObject pageRequest = new JsonObject().put("context", pageContext);

            vertx.eventBus().request(classApiAddress, pageRequest, new DeliveryOptions()
                    .setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
                    .addHeader("action", String.format("putimport%sFuture", classSimpleName))
                    ).onSuccess(message -> {
                importClusterAuth(hubId, classSimpleName, classApiAddress, body).onSuccess(a -> {
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

    public Future<Void> importClusterAuth(String hubId, String classSimpleName, String classApiAddress, JsonObject body) {
        Promise<Void> promise = Promise.promise();
        try {
            String clusterName = body.getString(Cluster.VAR_clusterName);
            String groupName = String.format("%s-%s-%s-%s-GET", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
            String policyId = String.format("%s-%s-%s-%s-GET", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
            String policyName = String.format("%s-%s-%s-%s-GET", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
            String resourceName = String.format("%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
            String permissionName = String.format("%s-%s-%s-%s-GET-permission", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
            String resourceDisplayName = String.format("%s %s %s %s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
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
                            webClient.get(authPort, authHostName, String.format("/admin/realms/%s/groups?exact=true&global=true&first=0&max=1&search=%s", authRealm, URLEncoder.encode(groupName, "UTF-8"))).ssl(authSsl)
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
                                                                .put("name", permissionName)
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

    protected Future<SearchList<Cluster>> cleanupNonAiNodesTotal(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName, String accessToken) {
        Promise<SearchList<Cluster>> promise = Promise.promise();
        try {
            SearchList<Cluster> searchList = new SearchList<Cluster>();
            searchList.setStore(true);
            searchList.q("*:*");
            searchList.setC(Cluster.class);
            searchList.fq(String.format("modified_docvalues_date:[* TO %s]", Cluster.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
            searchList.promiseDeepForClass(siteRequest).onSuccess(oldClusters -> {
                try {
                    List<Future<?>> futures = new ArrayList<>();
                    for(Integer i = 0; i < oldClusters.getList().size(); i++) {
                        Cluster oldCluster = oldClusters.getList().get(i);
                        futures.add(Future.future(promise1 -> {
                            try {
                                String clusterName = oldCluster.getClusterName();
                                JsonObject body = new JsonObject();
                                body.put(Cluster.VAR_clusterName, clusterName);
                                body.put(Cluster.VAR_aiNodesTotal, 0);
                                body.put(Cluster.VAR_gpuDevicesTotal, 0);

                                JsonObject pageParams = new JsonObject();
                                pageParams.put("body", body);
                                pageParams.put("path", new JsonObject());
                                pageParams.put("cookie", new JsonObject());
                                pageParams.put("query", new JsonObject().put("softCommit", true).put("q", "*:*").put("var", new JsonArray().add("refresh:false")));
                                JsonObject pageContext = new JsonObject().put("params", pageParams);
                                JsonObject pageRequest = new JsonObject().put("context", pageContext);

                                vertx.eventBus().request(Cluster.CLASS_API_ADDRESS_Cluster, pageRequest, new DeliveryOptions()
                                        .setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
                                        .addHeader("action", String.format("putimport%sFuture", classSimpleName))
                                        ).onSuccess(message -> {
                                    LOG.info(String.format("Imported %s AI cluster", clusterName));
                                    promise1.complete(oldClusters);
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
    public Future<Hub> patchHubFuture(Hub o, Boolean inheritPrimaryKey) {
        Promise<Hub> promise = Promise.promise();
        super.patchHubFuture(o, inheritPrimaryKey).onSuccess(o2 -> {
            postOrPatchHubFuture(o2).onSuccess(a -> {
                promise.complete();
            }).onFailure(ex -> {
                LOG.error(String.format("patchHubFuture failed. "), ex);
                promise.fail(ex);
            });
        }).onFailure(ex -> {
            LOG.error(String.format("patchHubFuture failed. "), ex);
            promise.fail(ex);
        });
        return promise.future();
    }

    @Override
    public Future<Hub> postHubFuture(SiteRequest siteRequest, Boolean hubResource) {
        Promise<Hub> promise = Promise.promise();
        super.postHubFuture(siteRequest, hubResource).onSuccess(o -> {
            postOrPatchHubFuture(o).onSuccess(a -> {
                promise.complete();
            }).onFailure(ex -> {
                LOG.error(String.format("postHubFuture failed. "), ex);
                promise.fail(ex);
            });
        }).onFailure(ex -> {
            LOG.error(String.format("postHubFuture failed. "), ex);
            promise.fail(ex);
        });
        return promise.future();
    }

    public Future<Hub> postOrPatchHubFuture(Hub hub) {
        Promise<Hub> promise = Promise.promise();
        try {
            String hubId = hub.getHubId();
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
                    queryClusterCapacityMemoryBytes(hub, Cluster.CLASS_SIMPLE_NAME, accessToken).onSuccess(clustersMemoryBytesTotal -> {
                        queryClusterCapacityCpuCores(hub, Cluster.CLASS_SIMPLE_NAME, accessToken).onSuccess(clustersCpuCoresTotal -> {
                            queryAiNodesTotal(hub, Cluster.CLASS_SIMPLE_NAME, accessToken).onSuccess(clustersAiNodesTotal -> {
                                queryGpuDevicesTotal(hub, Cluster.CLASS_SIMPLE_NAME, accessToken).onSuccess(clustersGpuDevicesTotal -> {
                                    List<JsonObject> clustersMemoryBytes = clustersMemoryBytesTotal.stream().filter(clusterResult -> !((JsonObject)clusterResult).getJsonArray("value").getString(1).equals("0")).map(aiNodeResult -> (JsonObject)aiNodeResult).map(aiNodeResult -> aiNodeResult.put("clusterName", "local-cluster".equals(aiNodeResult.getJsonObject("metric").getValue("cluster")) ? hub.getLocalClusterName() : aiNodeResult.getJsonObject("metric").getValue("cluster"))).collect(Collectors.toList());
                                    List<JsonObject> clustersCpuCores = clustersCpuCoresTotal.stream().filter(clusterResult -> !((JsonObject)clusterResult).getJsonArray("value").getString(1).equals("0")).map(aiNodeResult -> (JsonObject)aiNodeResult).collect(Collectors.toList());
                                    List<JsonObject> clustersAiNodes = clustersAiNodesTotal.stream().filter(clusterResult -> !((JsonObject)clusterResult).getJsonArray("value").getString(1).equals("0")).map(aiNodeResult -> (JsonObject)aiNodeResult).collect(Collectors.toList());
                                    List<JsonObject> clustersGpuDevices = clustersGpuDevicesTotal.stream().filter(clusterResult -> !((JsonObject)clusterResult).getJsonArray("value").getString(1).equals("0")).map(aiNodeResult -> (JsonObject)aiNodeResult).collect(Collectors.toList());
                                    List<Future<?>> futures = new ArrayList<>();
                                    for(Integer i = 0; i < clustersMemoryBytes.size(); i++) {
                                        JsonObject clusterMemoryBytesResult = clustersMemoryBytes.get(i);
                                        String clusterName = clusterMemoryBytesResult.getJsonObject("metric").getString("cluster");
                                        JsonObject clusterCpuCoresResult = clustersCpuCores.stream().filter(cluster -> clusterName.equals(cluster.getJsonObject("metric").getString("cluster"))).findFirst().orElse(null);
                                        JsonObject aiNodeResult = clustersAiNodes.stream().filter(cluster -> clusterName.equals(cluster.getJsonObject("metric").getString("cluster"))).findFirst().orElse(null);
                                        JsonObject gpuDeviceResult = clustersGpuDevices.stream().filter(cluster -> clusterName.equals(cluster.getJsonObject("metric").getString("cluster"))).findFirst().orElse(null);
                                        futures.add(Future.future(promise1 -> {
                                            importCluster(hub, Cluster.CLASS_SIMPLE_NAME, Cluster.CLASS_API_ADDRESS_Cluster, clusterMemoryBytesResult, clusterCpuCoresResult, aiNodeResult, gpuDeviceResult).onComplete(b -> {
                                                promise1.complete();
                                            }).onFailure(ex -> {
                                                LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
                                                promise1.fail(ex);
                                            });
                                        }));
                                    }
                                    Future.all(futures).onSuccess(b -> {
                                        promise.complete();
                                    }).onFailure(ex -> {
                                        LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
                                        promise.fail(ex);
                                    });
                                }).onFailure(ex -> {
                                    LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
                                    promise.fail(ex);
                                });
                            }).onFailure(ex -> {
                                LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
                                promise.fail(ex);
                            });
                        }).onFailure(ex -> {
                            LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
                            promise.fail(ex);
                        });
                    }).onFailure(ex -> {
                        LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
                        promise.fail(ex);
                    });
                } catch(Throwable ex) {
                    LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
                    promise.fail(ex);
                }
            }).onFailure(ex -> {
                LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
                promise.fail(ex);
            });
        } catch(Throwable ex) {
            LOG.error(String.format(importDataFail, Cluster.CLASS_SIMPLE_NAME), ex);
            promise.fail(ex);
        }
        return promise.future();
    }

    @Override
    protected Future<Void> importData(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest, String classCanonicalName,
            String classSimpleName, String classApiAddress, String classAuthResource, String varPageId, String varUserUrl, String varDownload) {
        Promise<Void> promise = Promise.promise();
        ZonedDateTime dateTimeStarted = ZonedDateTime.now();
        super.importData(pagePath, vertx, siteRequest, classCanonicalName, classSimpleName, classApiAddress, classAuthResource, varPageId, varUserUrl, varDownload).onSuccess(a -> {
            try {
                String authHostName = config.getString(ConfigKeys.AUTH_HOST_NAME);
                Integer authPort = config.getInteger(ConfigKeys.AUTH_PORT);
                String authTokenUri = config.getString(ConfigKeys.AUTH_TOKEN_URI);
                Boolean authSsl = config.getBoolean(ConfigKeys.AUTH_SSL);
                String authClient = config.getString(ConfigKeys.AUTH_CLIENT_SA);
                String authSecret = config.getString(ConfigKeys.AUTH_SECRET_SA);
                MultiMap form = MultiMap.caseInsensitiveMultiMap();
                String hubId = "moc";
                form.add("grant_type", "client_credentials");
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(authClient, authSecret);
                webClient.post(authPort, authHostName, authTokenUri).ssl(authSsl).authentication(credentials)
                        .putHeader("Content-Type", "application/json")
                        .sendForm(form)
                        .expecting(HttpResponseExpectation.SC_OK)
                        .onSuccess(requestAuthResponse -> {
                    try {
                        String accessToken = requestAuthResponse.bodyAsJsonObject().getString("access_token");
                        cleanupNonAiNodesTotal(siteRequest, dateTimeStarted, Cluster.CLASS_SIMPLE_NAME, accessToken).onSuccess(oldAiNodes -> {
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
}
