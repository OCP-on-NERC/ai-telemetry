package org.mghpcc.aitelemetry.model.gpudevice;

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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
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
public class GpuDeviceEnUSApiServiceImpl extends GpuDeviceEnUSGenApiServiceImpl {

	@Override
	protected Future<Void> importData(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest, String classCanonicalName,
			String classSimpleName, String classApiAddress, String varPageId, String varUserUrl, String varDownload) {
		Promise<Void> promise = Promise.promise();
		// importDataRest(pagePath, vertx, siteRequest, classSimpleName, classApiAddress).onComplete(a -> promise.complete());
		importDataVertx(pagePath, vertx, siteRequest, classCanonicalName, classSimpleName, classApiAddress, varPageId, varUserUrl, varDownload);
		promise.complete();
		return promise.future();
	}

	protected Future<Void> importDataRest(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest, String classCanonicalName,
			String classSimpleName, String classApiAddress, String varPageId, String varUserUrl, String varDownload) {
		Promise<Void> promise = Promise.promise();
		super.importData(pagePath, vertx, siteRequest, classCanonicalName, classSimpleName, classApiAddress, varPageId, varUserUrl, varDownload).onSuccess(a -> {
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

				ZonedDateTime now = ZonedDateTime.now(ZoneId.of(config.getString(ComputateConfigKeys.SITE_ZONE)));
				String siteHostName = config.getString(ComputateConfigKeys.SITE_HOST_NAME);
				Integer sitePort = Integer.parseInt(config.getString(ComputateConfigKeys.SITE_PORT));
				Boolean siteSsl = false;

				webClient.post(authPort, authHostName, authTokenUri).ssl(authSsl).authentication(credentials)
						.putHeader("Content-Type", "application/json")
						.sendForm(form)
						.expecting(HttpResponseExpectation.SC_OK)
						.onSuccess(requestAuthResponse -> {
					try {
						String accessToken = requestAuthResponse.bodyAsJsonObject().getString("access_token");
						Integer promKeycloakProxyPort = Integer.parseInt(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_PORT));
						String promKeycloakProxyHostName = config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME);
						Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_SSL));
						String promKeycloakProxyUri = String.format("/api/v1/query?query=DCGM_FI_DEV_GPU_UTIL");

						webClient.get(promKeycloakProxyPort, promKeycloakProxyHostName, promKeycloakProxyUri).ssl(promKeycloakProxySsl)
								.putHeader("Authorization", String.format("Bearer %s", accessToken))
								.send()
								.expecting(HttpResponseExpectation.SC_OK)
								.onSuccess(metricsResponse -> {
							JsonObject metricsBody = metricsResponse.bodyAsJsonObject();
							JsonArray dataResult = metricsBody.getJsonObject("data").getJsonArray("result");
							List<Future<?>> futures = new ArrayList<>();
							dataResult.stream().map(o -> (JsonObject)o).filter(o -> o.getString("Hostname") != null).forEach(clusterResult -> {
								futures.add(Future.future(promise1 -> {
									try {
										JsonObject clusterMetric = clusterResult.getJsonObject("metric");
										JsonArray clusterValue = clusterResult.getJsonArray("value");
										String clusterName = clusterMetric.getString("cluster");
										String nodeName = clusterMetric.getString("Hostname");
										Integer gpuDeviceNumber = Integer.parseInt(clusterMetric.getString("gpu"));
										String gpuDeviceUtilization = clusterValue.getString(1);
										JsonObject body = new JsonObject();
										String gpuDeviceId = String.format("%s-%s-%s", clusterName, nodeName, gpuDeviceNumber);
										body.put(GpuDevice.VAR_pk, gpuDeviceId);
										body.put(GpuDevice.VAR_gpuDeviceId, gpuDeviceId);
										body.put(GpuDevice.VAR_clusterName, clusterName);
										body.put(GpuDevice.VAR_nodeName, nodeName);
										body.put(GpuDevice.VAR_gpuDeviceNumber, gpuDeviceNumber.toString());
										body.put(GpuDevice.VAR_gpuDeviceUtilization, gpuDeviceUtilization);

										webClient.put(443, siteHostName, "/en-us/api/gpu-device-import?softCommit=true")
												.ssl(true)
												.putHeader("Content-Type", "application/json")
												.putHeader("Authorization", String.format("Bearer %s", accessToken))
												.sendJsonObject(new JsonObject().put("list", new JsonArray().add(body)))
												.expecting(HttpResponseExpectation.SC_OK)
												.onSuccess(importResponse -> {
											LOG.info(String.format("Imported %s GPU device", gpuDeviceId));
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
								promise.complete();
							}).onFailure(ex -> {
								vertx.setTimer(2000, timer -> {
									webClient.delete(443, siteHostName, "/en-us/api/gpu-device?rows=1000")
											.ssl(true)
											.putHeader("Content-Type", "application/json")
											.putHeader("Authorization", String.format("Bearer %s", accessToken))
											.send()
											.expecting(HttpResponseExpectation.SC_OK)
											.onSuccess(importResponse -> {
										LOG.warn("Deleted GPU devices created during failed import");
										promise.complete();
									}).onFailure(ex2 -> {
										LOG.error(String.format(importDataFail, classSimpleName), ex2);
										promise.fail(ex2);
									});
								});
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

	public Future<Void> importDataCamelCompensation() {
		Promise<Void> promise = Promise.promise();
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

			String siteHostName = config.getString(ComputateConfigKeys.SITE_HOST_NAME);
			Integer sitePort = Integer.parseInt(config.getString(ComputateConfigKeys.SITE_PORT));
			Boolean siteSsl = false;

			webClient.post(authPort, authHostName, authTokenUri).ssl(authSsl).authentication(credentials)
					.putHeader("Content-Type", "application/json")
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(requestAuthResponse -> {
				try {
					String accessToken = requestAuthResponse.bodyAsJsonObject().getString("access_token");

					webClient.delete(443, siteHostName, "/en-us/api/gpu-device?rows=1000")
							.ssl(true)
							.putHeader("Content-Type", "application/json")
							.putHeader("Authorization", String.format("Bearer %s", accessToken))
							.send()
							.expecting(HttpResponseExpectation.SC_OK)
							.onSuccess(importResponse -> {
						LOG.warn("Deleted GPU devices created during failed import");
						promise.complete();
					}).onFailure(ex2 -> {
						LOG.error(String.format(importDataFail, GpuDevice.CLASS_SIMPLE_NAME), ex2);
						promise.fail(ex2);
					});
				} catch(Throwable ex) {
					LOG.error(String.format(importDataFail, GpuDevice.CLASS_SIMPLE_NAME), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				LOG.error(String.format(importDataFail, GpuDevice.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error(String.format(importDataFail, GpuDevice.CLASS_SIMPLE_NAME), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	protected Future<Void> importDataVertx(Path pagePath, Vertx vertx, ComputateSiteRequest siteRequest, String classCanonicalName,
			String classSimpleName, String classApiAddress, String varPageId, String varUserUrl, String varDownload) {
		Promise<Void> promise = Promise.promise();
		ZonedDateTime dateTimeStarted = ZonedDateTime.now();
		super.importData(pagePath, vertx, siteRequest, classCanonicalName, classSimpleName, classApiAddress, varPageId, varUserUrl, varDownload).onSuccess(a -> {
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
						Integer promKeycloakProxyPort = Integer.parseInt(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_PORT));
						String promKeycloakProxyHostName = config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME);
						Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_SSL));
						String promKeycloakProxyUri = String.format("/api/v1/query?query=DCGM_FI_DEV_GPU_UTIL");

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
										String clusterName = clusterMetric.getString("cluster");
										String nodeName = clusterMetric.getString("Hostname");
										Integer gpuDeviceNumber = Integer.parseInt(clusterMetric.getString("gpu"));
										String gpuDeviceUtilization = clusterValue.getString(1);
										JsonObject body = new JsonObject();
										String gpuDeviceId = String.format("%s-%s-%s", clusterName, nodeName, gpuDeviceNumber);
										body.put(GpuDevice.VAR_pk, gpuDeviceId);
										body.put(GpuDevice.VAR_gpuDeviceId, gpuDeviceId);
										body.put(GpuDevice.VAR_clusterName, clusterName);
										body.put(GpuDevice.VAR_nodeName, nodeName);
										body.put(GpuDevice.VAR_gpuDeviceNumber, gpuDeviceNumber);
										body.put(GpuDevice.VAR_gpuDeviceUtilization, gpuDeviceUtilization);

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
											LOG.info(String.format("Imported %s GPU device", gpuDeviceId));
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
								cleanupGpuDevices(siteRequest, dateTimeStarted, classSimpleName, accessToken).onSuccess(oldAiNodes -> {
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
