package org.mghpcc.aitelemetry.model.node;

import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.request.ComputateSiteRequest;
import org.mghpcc.aitelemetry.config.ConfigKeys;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class AiNodeEnUSApiServiceImpl extends AiNodeEnUSGenApiServiceImpl {

	@Override
	protected Future<Void> importData(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest,
			String classSimpleName, String classApiAddress) {
		Promise<Void> promise = Promise.promise();
		super.importData(pagePath, vertx, siteRequest, classSimpleName, classApiAddress).onSuccess(a -> {
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
						queryGpuDevicesTotal(classSimpleName, accessToken).onSuccess(gpuDevicesTotal -> {
							List<Future<?>> futures = new ArrayList<>();
							for(Integer i = 0; i < gpuDevicesTotal.size(); i++) {
								JsonObject gpuDeviceResult = gpuDevicesTotal.getJsonObject(i);
								futures.add(Future.future(promise1 -> {
									try {
										String clusterName = gpuDeviceResult.getJsonObject("metric").getString("cluster");
										String nodeName = gpuDeviceResult.getJsonObject("metric").getString("node");
										JsonObject body = new JsonObject();
										body.put(AiNode.VAR_pk, String.format("%s-%s", clusterName, nodeName));
										body.put(AiNode.VAR_clusterName, clusterName);
										body.put(AiNode.VAR_nodeName, nodeName);
										body.put(AiNode.VAR_nodeId, AiNode.toId(String.format("%s-%s", clusterName, nodeName)));
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
											promise1.complete();
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

	protected Future<JsonArray> queryGpuDevicesTotal(String classSimpleName, String accessToken) {
		Promise<JsonArray> promise = Promise.promise();
		try {
			Integer promKeycloakProxyPort = Integer.parseInt(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_PORT));
			String promKeycloakProxyHostName = config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME);
			Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_SSL));
			String promKeycloakProxyUri = String.format("/api/v1/query?query=%s", urlEncode("sum by (cluster, node) (gpu_operator_nvidia_pci_devices_total)"));

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
