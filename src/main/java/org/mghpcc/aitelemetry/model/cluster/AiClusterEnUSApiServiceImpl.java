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
public class AiClusterEnUSApiServiceImpl extends AiClusterEnUSGenApiServiceImpl {

	public AiClusterEnUSApiServiceImpl(Vertx vertx, JsonObject config, WorkerExecutor workerExecutor, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, PgPool pgPool, KafkaProducer<String, String> kafkaProducer, MqttClient mqttClient, AmqpSender amqpSender, RabbitMQClient rabbitmqClient, WebClient webClient, OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider, Jinjava jinjava) {
		super(vertx, config, workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
	}

	@Override
	protected Future<Void> importData(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest,
			String classSimpleName, String classApiAddress) {
		Promise<Void> promise = Promise.promise();
		super.importData(pagePath, vertx, siteRequest, classSimpleName, classApiAddress).onSuccess(a -> {
			try {
				String authHostName = config.getString(ConfigKeys.AUTH_HOST_NAME);
				Integer authPort = config.getInteger(ConfigKeys.AUTH_PORT);
				String authTokenUri = config.getString(ConfigKeys.AUTH_TOKEN_URI);
				Boolean authSsl = config.getBoolean(ConfigKeys.AUTH_SSL);
				String authClient = config.getString(ConfigKeys.AUTH_CLIENT);
				String authSecret = config.getString(ConfigKeys.AUTH_SECRET);
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
						Integer promKeycloakProxyPort = config.getInteger(ConfigKeys.PROM_KEYCLOAK_PROXY_PORT);
						String promKeycloakProxyHostName = config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME);
						Boolean promKeycloakProxySsl = config.getBoolean(ConfigKeys.PROM_KEYCLOAK_PROXY_SSL);
						String promKeycloakProxyUri = String.format("/api/v1/query?query=gpu_operator_gpu_nodes_total");

						webClient.get(promKeycloakProxyPort, promKeycloakProxyHostName, promKeycloakProxyUri).ssl(promKeycloakProxySsl)
								.putHeader("Authorization", String.format("Bearer %s", accessToken))
								.send()
								.expecting(HttpResponseExpectation.SC_OK)
								.onSuccess(metricsResponse -> {
							LOG.info(String.format("metrics: %s", metricsResponse.bodyAsJsonObject().encodePrettily()));

							JsonObject metricsBody = metricsResponse.bodyAsJsonObject();
							JsonArray dataResult = metricsBody.getJsonObject("data").getJsonArray("result");
							List<Future<?>> futures = new ArrayList<>();
							dataResult.stream().map(o -> (JsonObject)o).forEach(clusterResult -> {
								futures.add(Future.future(promise1 -> {
									try {
										JsonObject clusterMetric = clusterResult.getJsonObject("metric");
										JsonArray clusterValue = clusterResult.getJsonArray("value");
										String name = clusterMetric.getString("cluster");
										String gpuNodesTotal = clusterValue.getString(1);
										JsonObject body = new JsonObject();
										body.put(AiCluster.VAR_pk, name);
										body.put(AiCluster.VAR_name, name);
										body.put(AiCluster.VAR_gpuNodesTotal, gpuNodesTotal);

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
											LOG.info(String.format("Imported %s AI cluster", name));
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
							});
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
}
