package org.mghpcc.aitelemetry.model.baremetalnetwork;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.request.SiteRequest;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class BareMetalNetworkEnUSApiServiceImpl extends BareMetalNetworkEnUSGenApiServiceImpl {

	protected Future<JsonArray> queryBareMetalNetworks() {
		Promise<JsonArray> promise = Promise.promise();
		try {
			Integer esiApiPort = Integer.parseInt(config.getString(ConfigKeys.ESI_API_PORT));
			String esiApiHostName = config.getString(ConfigKeys.ESI_API_HOST_NAME);
			Boolean esiApiSsl = Boolean.parseBoolean(config.getString(ConfigKeys.ESI_API_SSL));
			String esiApiUri = String.format("/api/v1/list/networks");

			webClient.get(esiApiPort, esiApiHostName, esiApiUri).ssl(esiApiSsl)
					.send()
					.expecting(HttpResponseExpectation.SC_OK)
					.onSuccess(templatesResponse -> {
				promise.complete(templatesResponse.bodyAsJsonArray());
			}).onFailure(ex -> {
				LOG.error(String.format("Querying ESI API cluster templates failed. ", BareMetalNetwork.CLASS_SIMPLE_NAME), ex);
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
			String networkName = result.getString(BareMetalNetwork.VAR_name);
			body.put(BareMetalNetwork.VAR_id, result.getString(BareMetalNetwork.idESI_enUS));
			body.put(BareMetalNetwork.VAR_name, result.getString(BareMetalNetwork.nameESI_enUS));
			body.put(BareMetalNetwork.VAR_description, result.getString(BareMetalNetwork.descriptionESI_enUS));
			body.put(BareMetalNetwork.VAR_availabilityZoneHints, result.getJsonArray(BareMetalNetwork.availabilityZoneHintsESI_enUS));
			body.put(BareMetalNetwork.VAR_availabilityZones, result.getJsonArray(BareMetalNetwork.availabilityZonesESI_enUS));
			body.put(BareMetalNetwork.VAR_createdAt, result.getString(BareMetalNetwork.createdAtESI_enUS));
			body.put(BareMetalNetwork.VAR_dnsDomain, result.getString(BareMetalNetwork.dnsDomainESI_enUS));
			body.put(BareMetalNetwork.VAR_isAdminStateUp, result.getBoolean(BareMetalNetwork.isAdminStateUpESI_enUS));
			body.put(BareMetalNetwork.VAR_isDefault, result.getBoolean(BareMetalNetwork.isDefaultESI_enUS));
			body.put(BareMetalNetwork.VAR_isPortSecurityEnabled, result.getBoolean(BareMetalNetwork.isPortSecurityEnabledESI_enUS));
			body.put(BareMetalNetwork.VAR_isRouterExternal, result.getBoolean(BareMetalNetwork.isRouterExternalESI_enUS));
			body.put(BareMetalNetwork.VAR_isShared, result.getBoolean(BareMetalNetwork.isSharedESI_enUS));
			body.put(BareMetalNetwork.VAR_isVlanQueing, result.getBoolean(BareMetalNetwork.isVlanQueingESI_enUS));
			body.put(BareMetalNetwork.VAR_isVlanTransparent, result.getBoolean(BareMetalNetwork.isVlanTransparentESI_enUS));
			body.put(BareMetalNetwork.VAR_l2Adjacency, result.getBoolean(BareMetalNetwork.l2AdjacencyESI_enUS));
			body.put(BareMetalNetwork.VAR_locationCloud, result.getJsonObject(BareMetalNetwork.locationCloudESIlocation_enUS).getString(BareMetalNetwork.locationCloudESIcloud_enUS));
			body.put(BareMetalNetwork.VAR_locationProjectDomainId, result.getJsonObject(BareMetalNetwork.locationProjectDomainIdESIlocation_enUS).getJsonObject(BareMetalNetwork.locationProjectDomainIdESIproject_enUS).getString(BareMetalNetwork.locationProjectDomainIdESIdomain_id_enUS));
			body.put(BareMetalNetwork.VAR_locationProjectDomainName, result.getJsonObject(BareMetalNetwork.locationProjectDomainNameESIlocation_enUS).getJsonObject(BareMetalNetwork.locationProjectDomainNameESIproject_enUS).getString(BareMetalNetwork.locationProjectDomainNameESIdomain_name_enUS));
			body.put(BareMetalNetwork.VAR_locationProjectId, result.getJsonObject(BareMetalNetwork.locationProjectIdESIlocation_enUS).getJsonObject(BareMetalNetwork.locationProjectIdESIproject_enUS).getString(BareMetalNetwork.locationProjectIdESIid_enUS));
			body.put(BareMetalNetwork.VAR_locationProjectName, result.getJsonObject(BareMetalNetwork.locationProjectNameESIlocation_enUS).getJsonObject(BareMetalNetwork.locationProjectNameESIproject_enUS).getString(BareMetalNetwork.locationProjectNameESIname_enUS));
			body.put(BareMetalNetwork.VAR_locationRegionName, result.getJsonObject(BareMetalNetwork.locationRegionNameESIlocation_enUS).getString(BareMetalNetwork.locationRegionNameESIregion_name_enUS));
			body.put(BareMetalNetwork.VAR_locationZone, result.getJsonObject(BareMetalNetwork.locationZoneESIlocation_enUS).getString(BareMetalNetwork.locationZoneESIzone_enUS));
			body.put(BareMetalNetwork.VAR_mtu, result.getInteger(BareMetalNetwork.mtuESI_enUS));
			body.put(BareMetalNetwork.VAR_projectId, result.getString(BareMetalNetwork.projectIdESI_enUS));
			body.put(BareMetalNetwork.VAR_providerNetworkType, result.getString(BareMetalNetwork.providerNetworkTypeESI_enUS));
			body.put(BareMetalNetwork.VAR_providerPhysicalNetwork, result.getString(BareMetalNetwork.providerPhysicalNetworkESI_enUS));
			body.put(BareMetalNetwork.VAR_providerSegmentationId, result.getString(BareMetalNetwork.providerSegmentationIdESI_enUS));
			body.put(BareMetalNetwork.VAR_qosPolicyId, result.getString(BareMetalNetwork.qosPolicyIdESI_enUS));
			body.put(BareMetalNetwork.VAR_revisionNumber, result.getInteger(BareMetalNetwork.revisionNumberESI_enUS));
			body.put(BareMetalNetwork.VAR_status, result.getString(BareMetalNetwork.statusESI_enUS));
			body.put(BareMetalNetwork.VAR_subnetIds, result.getJsonArray(BareMetalNetwork.subnetIdsESI_enUS));
			body.put(BareMetalNetwork.VAR_tags, result.getJsonArray(BareMetalNetwork.tagsESI_enUS));
			body.put(BareMetalNetwork.VAR_tenantId, result.getString(BareMetalNetwork.tenantIdESI_enUS));
			body.put(BareMetalNetwork.VAR_updatedAt, result.getString(BareMetalNetwork.updatedAtESI_enUS));

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
				LOG.info("Imported cluster templates");
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

	protected Future<SearchList<BareMetalNetwork>> cleanupOldTemplates(ComputateSiteRequest siteRequest, ZonedDateTime dateTimeStarted, String classSimpleName) {
		Promise<SearchList<BareMetalNetwork>> promise = Promise.promise();
		try {
			SearchList<BareMetalNetwork> searchList = new SearchList<BareMetalNetwork>();
			searchList.setStore(true);
			searchList.q("*:*");
			searchList.setC(BareMetalNetwork.class);
			searchList.fq(String.format("modified_docvalues_date:[* TO %s]", BareMetalNetwork.staticSearchCreated((SiteRequest)siteRequest, dateTimeStarted)));
			searchList.promiseDeepForClass(siteRequest).onSuccess(oldBareMetalNetworks -> {
				try {
					List<Future<?>> futures = new ArrayList<>();
					for(Integer i = 0; i < oldBareMetalNetworks.getList().size(); i++) {
						BareMetalNetwork oldBareMetalNetwork = oldBareMetalNetworks.getList().get(i);
						futures.add(Future.future(promise1 -> {
							try {
								String templateTitle = oldBareMetalNetwork.getName();
								JsonObject body = new JsonObject().put("setArchived", true);

								JsonObject pageParams = new JsonObject();
								pageParams.put("body", body);
								pageParams.put("path", new JsonObject());
								pageParams.put("cookie", new JsonObject());
								pageParams.put("query", new JsonObject()
									.put("softCommit", true)
									.put("q", "*:*")
									.put("var", new JsonArray().add("refresh:false"))
									.put("fq", String.format("%s:%s", BareMetalNetwork.VAR_name, oldBareMetalNetwork.getName()))
									);
								JsonObject pageContext = new JsonObject().put("params", pageParams);
								JsonObject pageRequest = new JsonObject().put("context", pageContext);

								vertx.eventBus().request(BareMetalNetwork.CLASS_API_ADDRESS_BareMetalNetwork, pageRequest, new DeliveryOptions()
										.setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
										.addHeader("action", String.format("patch%sFuture", classSimpleName))
										).onSuccess(message -> {
									LOG.info(String.format("Archived %s AI cluster", templateTitle));
									promise1.complete(oldBareMetalNetworks);
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
			queryBareMetalNetworks().onSuccess(networks -> {
				List<Future<?>> futures = new ArrayList<>();
				for(Integer i = 0; i < networks.size(); i++) {
					JsonObject template = networks.getJsonObject(i);
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
					cleanupOldTemplates(siteRequest, dateTimeStarted, classSimpleName).onSuccess(oldAiNodes -> {
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
