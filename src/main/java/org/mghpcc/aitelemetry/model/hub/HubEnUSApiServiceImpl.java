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
import org.mghpcc.aitelemetry.model.gpudevice.GpuDevice;
import org.mghpcc.aitelemetry.model.node.AiNode;
import org.mghpcc.aitelemetry.model.project.Project;
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

    ////////////////
    // Hub import //
    ////////////////

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
                                queryGpuDevicesTotalForHub(hub, Cluster.CLASS_SIMPLE_NAME, accessToken).onSuccess(clustersGpuDevicesTotal -> {
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
                form.add("grant_type", "client_credentials");
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(authClient, authSecret);
                webClient.post(authPort, authHostName, authTokenUri).ssl(authSsl).authentication(credentials)
                        .putHeader("Content-Type", "application/json")
                        .sendForm(form)
                        .expecting(HttpResponseExpectation.SC_OK)
                        .onSuccess(requestAuthResponse -> {
                    try {
                        String accessToken = requestAuthResponse.bodyAsJsonObject().getString("access_token");
                        // cleanupNonAiNodesTotal(siteRequest, dateTimeStarted, Cluster.CLASS_SIMPLE_NAME, accessToken).onSuccess(oldAiNodes -> {
                            promise.complete();
                        // }).onFailure(ex -> {
                        //     LOG.error(String.format(importDataFail, classSimpleName), ex);
                        //     promise.fail(ex);
                        // });
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

    ////////////////////
    // Cluster import //
    ////////////////////

    protected Future<JsonArray> queryClusterCapacityMemoryBytes(Hub hub, String classSimpleName, String accessToken) {
        Promise<JsonArray> promise = Promise.promise();
        try {
            String hubIdEnv = hub.getHubId().toUpperCase().replace("-", "");
            Integer promKeycloakProxyPort = Integer.parseInt(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_PORT, hubIdEnv)));
            String promKeycloakProxyHostName = config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME, hubIdEnv));
            Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_SSL, hubIdEnv)));
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
            String hubIdEnv = hub.getHubId().toUpperCase().replace("-", "");
            Integer promKeycloakProxyPort = Integer.parseInt(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_PORT, hubIdEnv)));
            String promKeycloakProxyHostName = config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME, hubIdEnv));
            Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_SSL, hubIdEnv)));
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
            String hubIdEnv = hub.getHubId().toUpperCase().replace("-", "");
            Integer promKeycloakProxyPort = Integer.parseInt(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_PORT, hubIdEnv)));
            String promKeycloakProxyHostName = config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME, hubIdEnv));
            Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_SSL, hubIdEnv)));
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

    protected Future<JsonArray> queryGpuDevicesTotalForHub(Hub hub, String classSimpleName, String accessToken) {
        Promise<JsonArray> promise = Promise.promise();
        try {
            String hubIdEnv = hub.getHubId().toUpperCase().replace("-", "");
            Integer promKeycloakProxyPort = Integer.parseInt(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_PORT, hubIdEnv)));
            String promKeycloakProxyHostName = config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME, hubIdEnv));
            Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_SSL, hubIdEnv)));
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
                    importProjectData(body).onSuccess(b -> {
                        importAiNodeData(body).onSuccess(c -> {
                            importGpuDeviceData(body).onSuccess(d -> {
                                LOG.info(String.format("Imported %s AI cluster in %s", clusterName, hubId));
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

    ////////////////////
    // Project import //
    ////////////////////

    public Future<Void> importProjectAuth(String hubId, String classSimpleName, String classApiAddress, JsonObject body) {
        Promise<Void> promise = Promise.promise();
        try {
            String clusterName = body.getString(Project.VAR_clusterName);
            String projectName = body.getString(Project.VAR_projectName);
            String groupName = String.format("%s-%s-%s-%s-%s-%s-GET", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, Project.CLASS_AUTH_RESOURCE, projectName);
            String policyId = String.format("%s-%s-%s-%s-%s-%s-GET", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, Project.CLASS_AUTH_RESOURCE, projectName);
            String policyName = String.format("%s-%s-%s-%s-%s-%s-GET", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, Project.CLASS_AUTH_RESOURCE, projectName);
            String resourceName = String.format("%s-%s-%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, Project.CLASS_AUTH_RESOURCE, projectName);
            String permissionName = String.format("%s-%s-%s-%s-%s-%s-GET-permission", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, Project.CLASS_AUTH_RESOURCE, projectName);
            String resourceDisplayName = String.format("%s %s %s %s %s %s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, Project.CLASS_AUTH_RESOURCE, projectName);
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

    protected Future<Void> importProjectData(JsonObject clusterJson) {
        Promise<Void> promise = Promise.promise();
        String classSimpleName = Project.CLASS_SIMPLE_NAME;
        String classApiAddress = Project.CLASS_API_ADDRESS_Project;
        try {

            String authHostName = config.getString(ConfigKeys.AUTH_HOST_NAME);
            Integer authPort = Integer.parseInt(config.getString(ConfigKeys.AUTH_PORT));
            String authTokenUri = config.getString(ConfigKeys.AUTH_TOKEN_URI);
            Boolean authSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AUTH_SSL));
            String authClient = config.getString(ConfigKeys.AUTH_CLIENT_SA);
            String authSecret = config.getString(ConfigKeys.AUTH_SECRET_SA);
            String hubId = clusterJson.getString(Cluster.VAR_hubId);
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
                    queryGpuProjects(clusterJson, classSimpleName, accessToken).onSuccess(gpuDevicesTotal -> {
                        List<Future<?>> futures = new ArrayList<>();
                        for(Integer i = 0; i < gpuDevicesTotal.size(); i++) {
                            JsonObject gpuDeviceResult = gpuDevicesTotal.getJsonObject(i);
                            String clusterName = gpuDeviceResult.getJsonObject("metric").getString("cluster");
                            String projectName = gpuDeviceResult.getJsonObject("metric").getString("exported_namespace");
                            if(projectName != null) {
                                futures.add(Future.future(promise1 -> {
                                    try {
                                        String hubResource = String.format("%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId);
                                        String clusterResource = String.format("%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
                                        String projectResource = String.format("%s-%s-%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, Project.CLASS_AUTH_RESOURCE, projectName);
                                        JsonObject body = new JsonObject();
                                        body.put(Project.VAR_pk, projectResource);
                                        body.put(Project.VAR_hubId, hubId);
                                        body.put(Project.VAR_hubResource, hubResource);
                                        body.put(Project.VAR_clusterName, clusterName);
                                        body.put(Project.VAR_clusterResource, clusterResource);
                                        body.put(Project.VAR_projectResource, projectResource);
                                        body.put(Project.VAR_projectName, projectName);

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
                                            importProjectAuth(hubId, classSimpleName, classApiAddress, body).onSuccess(c -> {
                                                LOG.info(String.format("Imported %s project", projectResource));
                                                promise1.complete();
                                            }).onFailure(ex -> {
                                                LOG.error(String.format(importDataFail, classSimpleName), ex);
                                                promise1.fail(ex);
                                            });
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
                        }
                        Future.all(futures).onSuccess(b -> {
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

    protected Future<JsonArray> queryGpuProjects(JsonObject clusterJson, String classSimpleName, String accessToken) {
        Promise<JsonArray> promise = Promise.promise();
        try {
            String hubId = clusterJson.getString(Cluster.VAR_hubId);
            String hubIdEnv = hubId.toUpperCase().replace("-", "");
            String clusterName = clusterJson.getString(Cluster.VAR_clusterName);
            Integer promKeycloakProxyPort = Integer.parseInt(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_PORT, hubIdEnv)));
            String promKeycloakProxyHostName = config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME, hubIdEnv));
            Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_SSL, hubIdEnv)));
            String promKeycloakProxyUri = String.format("/api/v1/query?query=%s", urlEncode(String.format("sum by (cluster, exported_namespace) (DCGM_FI_DEV_GPU_UTIL{cluster=\"%s\"})", clusterName)));

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

    ///////////////////
    // AiNode import //
    ///////////////////

    public Future<Void> importAiNode(JsonObject clusterJson, String classSimpleName, String classApiAddress, JsonObject gpuDeviceResult) {
        Promise<Void> promise = Promise.promise();
        try {
            String clusterName = gpuDeviceResult.getJsonObject("metric").getString("cluster");
            String nodeName = gpuDeviceResult.getJsonObject("metric").getString("node");
            String hubId = clusterJson.getString(AiNode.VAR_hubId);
            String hubResource = String.format("%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId);
            String clusterResource = String.format("%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
            String nodeResource = String.format("%s-%s-%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, AiNode.CLASS_AUTH_RESOURCE, nodeName);
            JsonObject body = new JsonObject();
            body.put(AiNode.VAR_pk, nodeResource);
            body.put(AiNode.VAR_hubId, hubId);
            body.put(AiNode.VAR_hubResource, hubResource);
            body.put(AiNode.VAR_clusterName, clusterName);
            body.put(AiNode.VAR_clusterResource, clusterResource);
            body.put(AiNode.VAR_nodeName, nodeName);
            body.put(AiNode.VAR_nodeResource, nodeResource);
            body.put(AiNode.VAR_gpuDevicesTotal, gpuDeviceResult.getJsonArray("value").getString(1));

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
                LOG.info(String.format("Imported %s-%s AI node", clusterName, nodeName));
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

    protected Future<Void> importAiNodeData(JsonObject clusterJson) {
        Promise<Void> promise = Promise.promise();
        String classSimpleName = AiNode.CLASS_SIMPLE_NAME;
        String classApiAddress = AiNode.CLASS_API_ADDRESS_AiNode;
        try {
            String authHostName = config.getString(ConfigKeys.AUTH_HOST_NAME);
            Integer authPort = Integer.parseInt(config.getString(ConfigKeys.AUTH_PORT));
            String authTokenUri = config.getString(ConfigKeys.AUTH_TOKEN_URI);
            Boolean authSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AUTH_SSL));
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
                    queryGpuDevicesTotalForCluster(clusterJson, classSimpleName, accessToken).onSuccess(gpuDevicesTotal -> {
                        List<Future<?>> futures = new ArrayList<>();
                        for(Integer i = 0; i < gpuDevicesTotal.size(); i++) {
                            JsonObject gpuDeviceResult = gpuDevicesTotal.getJsonObject(i);
                            futures.add(Future.future(promise1 -> {
                                try {
                                    importAiNode(clusterJson, classSimpleName, classApiAddress, gpuDeviceResult).onSuccess(b -> {
                                        promise1.complete();
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

    protected Future<JsonArray> queryGpuDevicesTotalForCluster(JsonObject clusterJson, String classSimpleName, String accessToken) {
        Promise<JsonArray> promise = Promise.promise();
        try {
            String hubId = clusterJson.getString(Cluster.VAR_hubId);
            String clusterName = clusterJson.getString(Cluster.VAR_clusterName);
            String hubIdEnv = hubId.toUpperCase().replace("-", "");
            Integer promKeycloakProxyPort = Integer.parseInt(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_PORT, hubIdEnv)));
            String promKeycloakProxyHostName = config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME, hubIdEnv));
            Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_SSL, hubIdEnv)));
            String promKeycloakProxyUri = String.format("/api/v1/query?query=%s", urlEncode(String.format("sum by (cluster, node) (gpu_operator_nvidia_pci_devices_total{cluster=\"%s\"})", clusterName)));

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

    //////////////////////
    // GpuDevice import //
    //////////////////////

    protected Future<Void> importGpuDeviceData(JsonObject clusterJson) {
        Promise<Void> promise = Promise.promise();
        String classSimpleName = GpuDevice.CLASS_SIMPLE_NAME;
        String classApiAddress = GpuDevice.CLASS_API_ADDRESS_GpuDevice;
        String hubId = clusterJson.getString(Cluster.VAR_hubId);
        String clusterName = clusterJson.getString(Cluster.VAR_clusterName);
        try {
            String authHostName = config.getString(ConfigKeys.AUTH_HOST_NAME);
            Integer authPort = Integer.parseInt(config.getString(ConfigKeys.AUTH_PORT));
            String authTokenUri = config.getString(ConfigKeys.AUTH_TOKEN_URI);
            Boolean authSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AUTH_SSL));
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
                    String hubIdEnv = hubId.toUpperCase().replace("-", "");
                    Integer promKeycloakProxyPort = Integer.parseInt(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_PORT, hubIdEnv)));
                    String promKeycloakProxyHostName = config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME, hubIdEnv));
                    Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(String.format("%s_%s", ConfigKeys.PROM_KEYCLOAK_PROXY_SSL, hubIdEnv)));
                    String promKeycloakProxyUri = String.format("/api/v1/query?query=DCGM_FI_DEV_GPU_UTIL{cluster=\"%s\"}", clusterName);

                    webClient.get(promKeycloakProxyPort, promKeycloakProxyHostName, promKeycloakProxyUri).ssl(promKeycloakProxySsl)
                            .putHeader("Authorization", String.format("Bearer %s", accessToken))
                            .send()
                            .expecting(HttpResponseExpectation.SC_OK)
                            .onSuccess(metricsResponse -> {
                        JsonObject metricsBody = metricsResponse.bodyAsJsonObject();
                        JsonArray dataResult = metricsBody.getJsonObject("data").getJsonArray("result");
                        List<Future<?>> futures = new ArrayList<>();
                        dataResult.stream().map(o -> (JsonObject)o).forEach(clusterResult -> {
                            futures.add(Future.future(promise1 -> {
                                try {
                                    JsonObject clusterMetric = clusterResult.getJsonObject("metric");
                                    JsonArray clusterValue = clusterResult.getJsonArray("value");
                                    String nodeName = clusterMetric.getString("Hostname");
                                    String modelName = clusterMetric.getString("modelName");
                                    String hubResource = String.format("%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId);
                                    String clusterResource = String.format("%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName);
                                    String nodeResource = String.format("%s-%s-%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, AiNode.CLASS_AUTH_RESOURCE, nodeName);
                                    Integer gpuDeviceNumber = Integer.parseInt(clusterMetric.getString("gpu"));
                                    String gpuDeviceUtilization = clusterValue.getString(1);
                                    String gpuDeviceResource = String.format("%s-%s-%s-%s-%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, AiNode.CLASS_AUTH_RESOURCE, nodeName, GpuDevice.CLASS_AUTH_RESOURCE, gpuDeviceNumber);
                                    JsonObject body = new JsonObject();
                                    body.put(GpuDevice.VAR_pk, gpuDeviceResource);
                                    body.put(AiNode.VAR_hubId, hubId);
                                    body.put(AiNode.VAR_hubResource, hubResource);
                                    body.put(AiNode.VAR_clusterName, clusterName);
                                    body.put(AiNode.VAR_clusterResource, clusterResource);
                                    body.put(AiNode.VAR_nodeName, nodeName);
                                    body.put(AiNode.VAR_nodeResource, nodeResource);
                                    body.put(GpuDevice.VAR_gpuDeviceResource, gpuDeviceResource);
                                    body.put(GpuDevice.VAR_gpuDeviceNumber, gpuDeviceNumber.toString());
                                    body.put(GpuDevice.VAR_gpuDeviceUtilization, gpuDeviceUtilization);
                                    body.put(GpuDevice.VAR_modelName, modelName);

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
                                        LOG.info(String.format("Imported %s GPU device", gpuDeviceResource));
                                        promise1.complete();
                                    }).onFailure(ex -> {
                                        LOG.error(String.format(importDataFail, classSimpleName), ex);
                                        promise1.fail(ex);
                                    });
                                } catch(Exception ex) {
                                    LOG.error(String.format(importDataFail, classSimpleName), ex);
                                    promise1.fail(ex);
                                }
                            }));
                        });
                        Future.all(futures).onSuccess(b -> {
                            // cleanupGpuDevices(siteRequest, dateTimeStarted, classSimpleName, accessToken).onSuccess(oldAiNodes -> {
                                promise.complete();
                            // }).onFailure(ex -> {
                            //     LOG.error(String.format(importDataFail, classSimpleName), ex);
                            //     promise.fail(ex);
                            // });
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
        return promise.future();
    }

    protected Future<SearchList<GpuDevice>> cleanupGpuDevices(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName, String accessToken) {
        Promise<SearchList<GpuDevice>> promise = Promise.promise();
        try {
            SearchList<GpuDevice> searchList = new SearchList<GpuDevice>();
            searchList.setStore(true);
            searchList.q("*:*");
            searchList.setC(GpuDevice.class);
            searchList.fq(String.format("modified_docvalues_date:[* TO %s]", GpuDevice.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
            searchList.promiseDeepForClass(siteRequest).onSuccess(oldGpuDevices -> {
                try {
                    List<Future<?>> futures = new ArrayList<>();
                    for(Integer i = 0; i < oldGpuDevices.getList().size(); i++) {
                        GpuDevice oldGpuDevice = oldGpuDevices.getList().get(i);
                        futures.add(Future.future(promise1 -> {
                            try {
                                String clusterName = oldGpuDevice.getClusterName();
                                JsonObject body = new JsonObject();
                                body.put("setArchived", true);

                                JsonObject pageParams = new JsonObject();
                                pageParams.put("body", body);
                                pageParams.put("path", new JsonObject());
                                pageParams.put("cookie", new JsonObject());
                                pageParams.put("query", new JsonObject().put("softCommit", true).put("q", "*:*").put("var", new JsonArray().add("refresh:false")));
                                JsonObject pageContext = new JsonObject().put("params", pageParams);
                                JsonObject pageRequest = new JsonObject().put("context", pageContext);

                                vertx.eventBus().request(GpuDevice.CLASS_API_ADDRESS_GpuDevice, pageRequest, new DeliveryOptions()
                                        .setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
                                        .addHeader("action", String.format("patch%sFuture", classSimpleName))
                                        ).onSuccess(message -> {
                                    LOG.info(String.format("Archived %s GPU node", clusterName));
                                    promise1.complete(oldGpuDevices);
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
}
