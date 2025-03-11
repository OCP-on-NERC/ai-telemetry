package org.mghpcc.aitelemetry.model.gpudevice;

import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.user.SiteUser;
import org.computate.vertx.api.ApiRequest;
import org.computate.vertx.search.list.SearchResult;
import org.computate.vertx.verticle.EmailVerticle;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.computate.vertx.api.BaseApiServiceImpl;
import io.vertx.ext.web.client.WebClient;
import java.util.Objects;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.pgclient.PgPool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import com.hubspot.jinjava.Jinjava;
import io.vertx.core.eventbus.DeliveryOptions;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.computate.search.response.solr.SolrResponse.StatsField;
import java.util.stream.Collectors;
import io.vertx.core.json.Json;
import org.apache.commons.lang3.StringUtils;
import java.security.Principal;
import org.apache.commons.lang3.exception.ExceptionUtils;
import java.io.PrintWriter;
import java.util.Collection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.web.Router;
import java.nio.file.Path;
import java.nio.file.Files;
import com.google.common.io.Resources;
import java.nio.charset.StandardCharsets;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.config.ComputateConfigKeys;
import io.vertx.ext.reactivestreams.ReactiveReadStream;
import io.vertx.ext.reactivestreams.ReactiveWriteStream;
import io.vertx.core.MultiMap;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.Row;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.Timestamp;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.AsyncResult;
import java.net.URLEncoder;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.CompositeFuture;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpResponseExpectation;
import java.nio.charset.Charset;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import java.util.HashMap;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import java.util.Optional;
import java.util.stream.Stream;
import java.net.URLDecoder;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map.Entry;
import java.util.Iterator;
import org.computate.search.tool.SearchTool;
import org.computate.search.response.solr.SolrResponse;
import java.util.Base64;
import java.time.ZonedDateTime;
import org.apache.commons.lang3.BooleanUtils;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.model.gpudevice.GpuDevicePage;


/**
 * Translate: false
 * Generated: true
 **/
public class GpuDeviceEnUSGenApiServiceImpl extends BaseApiServiceImpl implements GpuDeviceEnUSGenApiService {

	protected static final Logger LOG = LoggerFactory.getLogger(GpuDeviceEnUSGenApiServiceImpl.class);

	// Search //

	@Override
	public void searchGpuDevice(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchGpuDeviceList(siteRequest, false, true, false).onSuccess(listGpuDevice -> {
							response200SearchGpuDevice(listGpuDevice).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchGpuDevice succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("searchGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200SearchGpuDevice(SearchList<GpuDevice> listGpuDevice) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listGpuDevice.getSiteRequest_(SiteRequest.class);
			List<String> fls = listGpuDevice.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listGpuDevice.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = GpuDevice.varIndexedGpuDevice(fieldName);
						if(v != null)
							fieldNames.add(GpuDevice.varIndexedGpuDevice(fieldName));
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
			response200Search(listGpuDevice.getRequest(), listGpuDevice.getResponse(), json);
			if(json == null) {
				String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
						String m = String.format("%s %s not found", "GPU device", gpuDeviceId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchGpuDevice(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
		if(pivots != null) {
			for(SolrResponse.Pivot pivotField : pivots) {
				String entityIndexed = pivotField.getField();
				String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
				JsonObject pivotJson = new JsonObject();
				pivotArray.add(pivotJson);
				pivotJson.put("field", entityVar);
				pivotJson.put("value", pivotField.getValue());
				pivotJson.put("count", pivotField.getCount());
				Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
				List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
				if(pivotRanges != null) {
					JsonObject rangeJson = new JsonObject();
					pivotJson.put("ranges", rangeJson);
					for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
						JsonObject rangeFacetJson = new JsonObject();
						String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
						rangeJson.put(rangeFacetVar, rangeFacetJson);
						JsonObject rangeFacetCountsObject = new JsonObject();
						rangeFacetJson.put("counts", rangeFacetCountsObject);
						rangeFacet.getCounts().forEach((value, count) -> {
							rangeFacetCountsObject.put(value, count);
						});
					}
				}
				if(pivotFields2 != null) {
					JsonArray pivotArray2 = new JsonArray();
					pivotJson.put("pivot", pivotArray2);
					responsePivotSearchGpuDevice(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// GET //

	@Override
	public void getGpuDevice(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchGpuDeviceList(siteRequest, false, true, false).onSuccess(listGpuDevice -> {
							response200GETGpuDevice(listGpuDevice).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("getGpuDevice succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("getGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("getGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("getGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("getGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("getGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200GETGpuDevice(SearchList<GpuDevice> listGpuDevice) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listGpuDevice.getSiteRequest_(SiteRequest.class);
			JsonObject json = JsonObject.mapFrom(listGpuDevice.getList().stream().findFirst().orElse(null));
			if(json == null) {
				String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
						String m = String.format("%s %s not found", "GPU device", gpuDeviceId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200GETGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PATCH //

	@Override
	public void patchGpuDevice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("patchGpuDevice started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "PATCH"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchGpuDeviceList(siteRequest, false, true, true).onSuccess(listGpuDevice -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listGpuDevice.getRequest().getRows());
								apiRequest.setNumFound(listGpuDevice.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listGpuDevice.first());
								apiRequest.setId(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getGpuDeviceId()).orElse(null));
								apiRequest.setPk(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());

								listPATCHGpuDevice(apiRequest, listGpuDevice).onSuccess(e -> {
									response200PATCHGpuDevice(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("patchGpuDevice succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("patchGpuDevice failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("patchGpuDevice failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("patchGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("patchGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("patchGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("patchGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("patchGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPATCHGpuDevice(ApiRequest apiRequest, SearchList<GpuDevice> listGpuDevice) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listGpuDevice.getSiteRequest_(SiteRequest.class);
		listGpuDevice.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			GpuDevice o2 = jsonObject.mapTo(GpuDevice.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				patchGpuDeviceFuture(o2, false).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listPATCHGpuDevice failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listGpuDevice.next().onSuccess(next -> {
				if(next) {
					listPATCHGpuDevice(apiRequest, listGpuDevice).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPATCHGpuDevice failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listPATCHGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listPATCHGpuDevice failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void patchGpuDeviceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchGpuDeviceList(siteRequest, false, true, true).onSuccess(listGpuDevice -> {
					try {
						GpuDevice o = listGpuDevice.first();
						if(o != null && listGpuDevice.getResponse().getResponse().getNumFound() == 1) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1L);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
								siteRequest.getRequestVars().put( "refresh", "false" );
							}
							if(apiRequest.getNumFound() == 1L)
								apiRequest.setOriginal(o);
							apiRequest.setId(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getGpuDeviceId()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getPk()).orElse(null));
							JsonObject jsonObject = JsonObject.mapFrom(o);
							GpuDevice o2 = jsonObject.mapTo(GpuDevice.class);
							o2.setSiteRequest_(siteRequest);
							patchGpuDeviceFuture(o2, false).onSuccess(o3 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("patchGpuDevice failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("patchGpuDevice failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("patchGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("patchGpuDevice failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<GpuDevice> patchGpuDeviceFuture(GpuDevice o, Boolean gpuDeviceId) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<GpuDevice> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<GpuDevice> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsGpuDevice(siteRequest).onSuccess(a -> {
					sqlPATCHGpuDevice(o, gpuDeviceId).onSuccess(gpuDevice -> {
						persistGpuDevice(gpuDevice, true).onSuccess(c -> {
							relateGpuDevice(gpuDevice).onSuccess(d -> {
								indexGpuDevice(gpuDevice).onSuccess(o2 -> {
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
											o2.apiRequestGpuDevice();
											if(apiRequest.getVars().size() > 0)
												eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());
										}
									}
									promise1.complete(gpuDevice);
								}).onFailure(ex -> {
									promise1.fail(ex);
								});
							}).onFailure(ex -> {
								promise1.fail(ex);
							});
						}).onFailure(ex -> {
							promise1.fail(ex);
						});
					}).onFailure(ex -> {
						promise1.fail(ex);
					});
				}).onFailure(ex -> {
					promise1.fail(ex);
				});
				return promise1.future();
			}).onSuccess(a -> {
				siteRequest.setSqlConnection(null);
			}).onFailure(ex -> {
				siteRequest.setSqlConnection(null);
				promise.fail(ex);
			}).compose(gpuDevice -> {
				Promise<GpuDevice> promise2 = Promise.promise();
				refreshGpuDevice(gpuDevice).onSuccess(a -> {
					promise2.complete(gpuDevice);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(gpuDevice -> {
				promise.complete(gpuDevice);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("patchGpuDeviceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<GpuDevice> sqlPATCHGpuDevice(GpuDevice o, Boolean gpuDeviceId) {
		Promise<GpuDevice> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE GpuDevice SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			GpuDevice o2 = new GpuDevice();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			for(String entityVar : methodNames) {
				switch(entityVar) {
					case "setClusterName":
							o2.setClusterName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_clusterName + "=$" + num);
							num++;
							bParams.add(o2.sqlClusterName());
						break;
					case "setNodeName":
							o2.setNodeName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_nodeName + "=$" + num);
							num++;
							bParams.add(o2.sqlNodeName());
						break;
					case "setCreated":
							o2.setCreated(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_created + "=$" + num);
							num++;
							bParams.add(o2.sqlCreated());
						break;
					case "setGpuDeviceNumber":
							o2.setGpuDeviceNumber(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_gpuDeviceNumber + "=$" + num);
							num++;
							bParams.add(o2.sqlGpuDeviceNumber());
						break;
					case "setGpuDeviceId":
							o2.setGpuDeviceId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_gpuDeviceId + "=$" + num);
							num++;
							bParams.add(o2.sqlGpuDeviceId());
						break;
					case "setArchived":
							o2.setArchived(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_archived + "=$" + num);
							num++;
							bParams.add(o2.sqlArchived());
						break;
					case "setGpuDeviceUtilization":
							o2.setGpuDeviceUtilization(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_gpuDeviceUtilization + "=$" + num);
							num++;
							bParams.add(o2.sqlGpuDeviceUtilization());
						break;
					case "setDescription":
							o2.setDescription(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_description + "=$" + num);
							num++;
							bParams.add(o2.sqlDescription());
						break;
					case "setSessionId":
							o2.setSessionId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_sessionId + "=$" + num);
							num++;
							bParams.add(o2.sqlSessionId());
						break;
					case "setUserKey":
							o2.setUserKey(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_userKey + "=$" + num);
							num++;
							bParams.add(o2.sqlUserKey());
						break;
					case "setLocation":
							o2.setLocation(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_location + "=$" + num);
							num++;
							bParams.add(o2.sqlLocation());
						break;
					case "setId":
							o2.setId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_id + "=$" + num);
							num++;
							bParams.add(o2.sqlId());
						break;
					case "setTitle":
							o2.setTitle(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_title + "=$" + num);
							num++;
							bParams.add(o2.sqlTitle());
						break;
					case "setNgsildTenant":
							o2.setNgsildTenant(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_ngsildTenant + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildTenant());
						break;
					case "setDisplayPage":
							o2.setDisplayPage(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_displayPage + "=$" + num);
							num++;
							bParams.add(o2.sqlDisplayPage());
						break;
					case "setNgsildPath":
							o2.setNgsildPath(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_ngsildPath + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildPath());
						break;
					case "setNgsildContext":
							o2.setNgsildContext(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_ngsildContext + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildContext());
						break;
					case "setNgsildData":
							o2.setNgsildData(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(GpuDevice.VAR_ngsildData + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildData());
						break;
				}
			}
			bSql.append(" WHERE pk=$" + num);
			if(bParams.size() > 0) {
				bParams.add(pk);
				num++;
				futures2.add(0, Future.future(a -> {
					sqlConnection.preparedQuery(bSql.toString())
							.execute(Tuple.tuple(bParams)
							).onSuccess(b -> {
						a.handle(Future.succeededFuture());
					}).onFailure(ex -> {
						RuntimeException ex2 = new RuntimeException("value GpuDevice failed", ex);
						LOG.error(String.format("relateGpuDevice failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					GpuDevice o3 = new GpuDevice();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					promise.complete(o3);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPATCHGpuDevice failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPATCHGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPATCHGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200PATCHGpuDevice(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
						String m = String.format("%s %s not found", "GPU device", gpuDeviceId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PATCHGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// POST //

	@Override
	public void postGpuDevice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("postGpuDevice started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "POST"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1L);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());
						JsonObject params = new JsonObject();
						params.put("body", siteRequest.getJsonObject());
						params.put("path", new JsonObject());
						params.put("cookie", new JsonObject());
						params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
						params.put("form", new JsonObject());
						JsonObject query = new JsonObject();
						Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
						Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
						if(softCommit == null && commitWithin == null)
							softCommit = true;
						if(softCommit != null)
							query.put("softCommit", softCommit);
						if(commitWithin != null)
							query.put("commitWithin", commitWithin);
						params.put("query", query);
						JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
						JsonObject json = new JsonObject().put("context", context);
						eventBus.request(GpuDevice.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postGpuDeviceFuture")).onSuccess(a -> {
							JsonObject responseMessage = (JsonObject)a.body();
							JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
							apiRequest.setPk(Long.parseLong(responseBody.getString("pk")));
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
							LOG.debug(String.format("postGpuDevice succeeded. "));
						}).onFailure(ex -> {
							LOG.error(String.format("postGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("postGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("postGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	@Override
	public void postGpuDeviceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}
				postGpuDeviceFuture(siteRequest, false).onSuccess(o -> {
					eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
				}).onFailure(ex -> {
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Throwable ex) {
				LOG.error(String.format("postGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("postGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<GpuDevice> postGpuDeviceFuture(SiteRequest siteRequest, Boolean gpuDeviceId) {
		Promise<GpuDevice> promise = Promise.promise();

		try {
			pgPool.withTransaction(sqlConnection -> {
				Promise<GpuDevice> promise1 = Promise.promise();
				siteRequest.setSqlConnection(sqlConnection);
				varsGpuDevice(siteRequest).onSuccess(a -> {
					createGpuDevice(siteRequest).onSuccess(gpuDevice -> {
						sqlPOSTGpuDevice(gpuDevice, gpuDeviceId).onSuccess(b -> {
							persistGpuDevice(gpuDevice, false).onSuccess(c -> {
								relateGpuDevice(gpuDevice).onSuccess(d -> {
									indexGpuDevice(gpuDevice).onSuccess(o2 -> {
										promise1.complete(gpuDevice);
									}).onFailure(ex -> {
										promise1.fail(ex);
									});
								}).onFailure(ex -> {
									promise1.fail(ex);
								});
							}).onFailure(ex -> {
								promise1.fail(ex);
							});
						}).onFailure(ex -> {
							promise1.fail(ex);
						});
					}).onFailure(ex -> {
						promise1.fail(ex);
					});
				}).onFailure(ex -> {
					promise1.fail(ex);
				});
				return promise1.future();
			}).onSuccess(a -> {
				siteRequest.setSqlConnection(null);
			}).onFailure(ex -> {
				siteRequest.setSqlConnection(null);
				promise.fail(ex);
			}).compose(gpuDevice -> {
				Promise<GpuDevice> promise2 = Promise.promise();
				refreshGpuDevice(gpuDevice).onSuccess(a -> {
					try {
						ApiRequest apiRequest = siteRequest.getApiRequest_();
						if(apiRequest != null) {
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							gpuDevice.apiRequestGpuDevice();
							eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());
						}
						promise2.complete(gpuDevice);
					} catch(Exception ex) {
						LOG.error(String.format("postGpuDeviceFuture failed. "), ex);
						promise.fail(ex);
					}
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(gpuDevice -> {
				try {
					ApiRequest apiRequest = siteRequest.getApiRequest_();
					if(apiRequest != null) {
						apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
						gpuDevice.apiRequestGpuDevice();
						eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());
					}
					promise.complete(gpuDevice);
				} catch(Exception ex) {
					LOG.error(String.format("postGpuDeviceFuture failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("postGpuDeviceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<GpuDevice> sqlPOSTGpuDevice(GpuDevice o, Boolean gpuDeviceId) {
		Promise<GpuDevice> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE GpuDevice SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			GpuDevice o2 = new GpuDevice();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(siteRequest.getSessionId() != null) {
				if(bParams.size() > 0) {
					bSql.append(", ");
				}
				bSql.append("sessionId=$" + num);
				num++;
				bParams.add(siteRequest.getSessionId());
			}
			if(siteRequest.getUserKey() != null) {
				if(bParams.size() > 0) {
					bSql.append(", ");
				}
				bSql.append("userKey=$" + num);
				num++;
				bParams.add(siteRequest.getUserKey());
			}

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case GpuDevice.VAR_clusterName:
						o2.setClusterName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_clusterName + "=$" + num);
						num++;
						bParams.add(o2.sqlClusterName());
						break;
					case GpuDevice.VAR_nodeName:
						o2.setNodeName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_nodeName + "=$" + num);
						num++;
						bParams.add(o2.sqlNodeName());
						break;
					case GpuDevice.VAR_created:
						o2.setCreated(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_created + "=$" + num);
						num++;
						bParams.add(o2.sqlCreated());
						break;
					case GpuDevice.VAR_gpuDeviceNumber:
						o2.setGpuDeviceNumber(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_gpuDeviceNumber + "=$" + num);
						num++;
						bParams.add(o2.sqlGpuDeviceNumber());
						break;
					case GpuDevice.VAR_gpuDeviceId:
						o2.setGpuDeviceId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_gpuDeviceId + "=$" + num);
						num++;
						bParams.add(o2.sqlGpuDeviceId());
						break;
					case GpuDevice.VAR_archived:
						o2.setArchived(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_archived + "=$" + num);
						num++;
						bParams.add(o2.sqlArchived());
						break;
					case GpuDevice.VAR_gpuDeviceUtilization:
						o2.setGpuDeviceUtilization(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_gpuDeviceUtilization + "=$" + num);
						num++;
						bParams.add(o2.sqlGpuDeviceUtilization());
						break;
					case GpuDevice.VAR_description:
						o2.setDescription(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_description + "=$" + num);
						num++;
						bParams.add(o2.sqlDescription());
						break;
					case GpuDevice.VAR_sessionId:
						o2.setSessionId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_sessionId + "=$" + num);
						num++;
						bParams.add(o2.sqlSessionId());
						break;
					case GpuDevice.VAR_userKey:
						o2.setUserKey(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_userKey + "=$" + num);
						num++;
						bParams.add(o2.sqlUserKey());
						break;
					case GpuDevice.VAR_location:
						o2.setLocation(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_location + "=$" + num);
						num++;
						bParams.add(o2.sqlLocation());
						break;
					case GpuDevice.VAR_id:
						o2.setId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_id + "=$" + num);
						num++;
						bParams.add(o2.sqlId());
						break;
					case GpuDevice.VAR_title:
						o2.setTitle(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_title + "=$" + num);
						num++;
						bParams.add(o2.sqlTitle());
						break;
					case GpuDevice.VAR_ngsildTenant:
						o2.setNgsildTenant(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_ngsildTenant + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildTenant());
						break;
					case GpuDevice.VAR_displayPage:
						o2.setDisplayPage(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_displayPage + "=$" + num);
						num++;
						bParams.add(o2.sqlDisplayPage());
						break;
					case GpuDevice.VAR_ngsildPath:
						o2.setNgsildPath(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_ngsildPath + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildPath());
						break;
					case GpuDevice.VAR_ngsildContext:
						o2.setNgsildContext(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_ngsildContext + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildContext());
						break;
					case GpuDevice.VAR_ngsildData:
						o2.setNgsildData(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(GpuDevice.VAR_ngsildData + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildData());
						break;
					}
				}
			}
			bSql.append(" WHERE pk=$" + num);
			if(bParams.size() > 0) {
			bParams.add(pk);
			num++;
				futures2.add(0, Future.future(a -> {
					sqlConnection.preparedQuery(bSql.toString())
							.execute(Tuple.tuple(bParams)
							).onSuccess(b -> {
						a.handle(Future.succeededFuture());
					}).onFailure(ex -> {
						RuntimeException ex2 = new RuntimeException("value GpuDevice failed", ex);
						LOG.error(String.format("relateGpuDevice failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete(o2);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPOSTGpuDevice failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPOSTGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPOSTGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200POSTGpuDevice(GpuDevice o) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			if(json == null) {
				String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
						String m = String.format("%s %s not found", "GPU device", gpuDeviceId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200POSTGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// DELETE //

	@Override
	public void deleteGpuDevice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deleteGpuDevice started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "DELETE"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchGpuDeviceList(siteRequest, false, true, true).onSuccess(listGpuDevice -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listGpuDevice.getRequest().getRows());
								apiRequest.setNumFound(listGpuDevice.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listGpuDevice.first());
								apiRequest.setPk(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());

								listDELETEGpuDevice(apiRequest, listGpuDevice).onSuccess(e -> {
									response200DELETEGpuDevice(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deleteGpuDevice succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deleteGpuDevice failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deleteGpuDevice failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deleteGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deleteGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deleteGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deleteGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("deleteGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEGpuDevice(ApiRequest apiRequest, SearchList<GpuDevice> listGpuDevice) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listGpuDevice.getSiteRequest_(SiteRequest.class);
		listGpuDevice.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			GpuDevice o2 = jsonObject.mapTo(GpuDevice.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deleteGpuDeviceFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEGpuDevice failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listGpuDevice.next().onSuccess(next -> {
				if(next) {
					listDELETEGpuDevice(apiRequest, listGpuDevice).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEGpuDevice failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEGpuDevice failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deleteGpuDeviceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchGpuDeviceList(siteRequest, false, true, true).onSuccess(listGpuDevice -> {
					try {
						GpuDevice o = listGpuDevice.first();
						if(o != null && listGpuDevice.getResponse().getResponse().getNumFound() == 1) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1L);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
								siteRequest.getRequestVars().put( "refresh", "false" );
							}
							if(apiRequest.getNumFound() == 1L)
								apiRequest.setOriginal(o);
							apiRequest.setId(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getGpuDeviceId()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getPk()).orElse(null));
							deleteGpuDeviceFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deleteGpuDevice failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deleteGpuDevice failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deleteGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deleteGpuDevice failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<GpuDevice> deleteGpuDeviceFuture(GpuDevice o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<GpuDevice> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<GpuDevice> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsGpuDevice(siteRequest).onSuccess(a -> {
					sqlDELETEGpuDevice(o).onSuccess(gpuDevice -> {
						relateGpuDevice(o).onSuccess(d -> {
							unindexGpuDevice(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestGpuDevice();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());
									}
								}
								promise1.complete();
							}).onFailure(ex -> {
								promise1.fail(ex);
							});
						}).onFailure(ex -> {
							promise1.fail(ex);
						});
					}).onFailure(ex -> {
						promise1.fail(ex);
					});
				}).onFailure(ex -> {
					promise1.fail(ex);
				});
				return promise1.future();
			}).onSuccess(a -> {
				siteRequest.setSqlConnection(null);
			}).onFailure(ex -> {
				siteRequest.setSqlConnection(null);
				promise.fail(ex);
			}).compose(gpuDevice -> {
				Promise<GpuDevice> promise2 = Promise.promise();
				refreshGpuDevice(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(gpuDevice -> {
				promise.complete(gpuDevice);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deleteGpuDeviceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEGpuDevice(GpuDevice o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM GpuDevice ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			GpuDevice o2 = new GpuDevice();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					}
				}
			}
			bSql.append(" WHERE pk=$" + num);
			bParams.add(pk);
			num++;
			futures2.add(0, Future.future(a -> {
				sqlConnection.preparedQuery(bSql.toString())
						.execute(Tuple.tuple(bParams)
						).onSuccess(b -> {
					a.handle(Future.succeededFuture());
				}).onFailure(ex -> {
					RuntimeException ex2 = new RuntimeException("value GpuDevice failed", ex);
					LOG.error(String.format("unrelateGpuDevice failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEGpuDevice failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEGpuDevice(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
						String m = String.format("%s %s not found", "GPU device", gpuDeviceId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PUTImport //

	@Override
	public void putimportGpuDevice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("putimportGpuDevice started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "PUT"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						ApiRequest apiRequest = new ApiRequest();
						JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
						apiRequest.setRows(Long.valueOf(jsonArray.size()));
						apiRequest.setNumFound(Long.valueOf(jsonArray.size()));
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());
						varsGpuDevice(siteRequest).onSuccess(d -> {
							listPUTImportGpuDevice(apiRequest, siteRequest).onSuccess(e -> {
								response200PUTImportGpuDevice(siteRequest).onSuccess(response -> {
									LOG.debug(String.format("putimportGpuDevice succeeded. "));
									eventHandler.handle(Future.succeededFuture(response));
								}).onFailure(ex -> {
									LOG.error(String.format("putimportGpuDevice failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							}).onFailure(ex -> {
								LOG.error(String.format("putimportGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("putimportGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("putimportGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("putimportGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPUTImportGpuDevice(ApiRequest apiRequest, SiteRequest siteRequest) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				futures.add(Future.future(promise1 -> {
					JsonObject params = new JsonObject();
					params.put("body", obj);
					params.put("path", new JsonObject());
					params.put("cookie", new JsonObject());
					params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
					params.put("form", new JsonObject());
					JsonObject query = new JsonObject();
					Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
					Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
					if(softCommit == null && commitWithin == null)
						softCommit = true;
					if(softCommit != null)
						query.put("softCommit", softCommit);
					if(commitWithin != null)
						query.put("commitWithin", commitWithin);
					params.put("query", query);
					JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
					JsonObject json = new JsonObject().put("context", context);
					eventBus.request(GpuDevice.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportGpuDeviceFuture")).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPUTImportGpuDevice failed. "), ex);
						promise1.fail(ex);
					});
				}));
			});
			CompositeFuture.all(futures).onSuccess(a -> {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("listPUTImportGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("listPUTImportGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public void putimportGpuDeviceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				String gpuDeviceId = Optional.ofNullable(body.getString(GpuDevice.VAR_gpuDeviceId)).orElse(body.getString(GpuDevice.VAR_solrId));
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}

				SearchList<GpuDevice> searchList = new SearchList<GpuDevice>();
				searchList.setStore(true);
				searchList.q("*:*");
				searchList.setC(GpuDevice.class);
				searchList.fq("archived_docvalues_boolean:false");
				searchList.fq("gpuDeviceId_docvalues_string:" + SearchTool.escapeQueryChars(gpuDeviceId));
				searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
					try {
						if(searchList.size() >= 1) {
							GpuDevice o = searchList.getList().stream().findFirst().orElse(null);
							GpuDevice o2 = new GpuDevice();
							o2.setSiteRequest_(siteRequest);
							JsonObject body2 = new JsonObject();
							for(String f : body.fieldNames()) {
								Object bodyVal = body.getValue(f);
								if(bodyVal instanceof JsonArray) {
									JsonArray bodyVals = (JsonArray)bodyVal;
									Object valsObj = o.obtainForClass(f);
									Collection<?> vals = valsObj instanceof JsonArray ? ((JsonArray)valsObj).getList() : (Collection<?>)valsObj;
									if(bodyVals.size() == vals.size()) {
										Boolean match = true;
										for(Object val : vals) {
											if(val != null) {
												if(!bodyVals.contains(val.toString())) {
													match = false;
													break;
												}
											} else {
												match = false;
												break;
											}
										}
										vals.clear();
										body2.put("set" + StringUtils.capitalize(f), bodyVal);
									} else {
										vals.clear();
										body2.put("set" + StringUtils.capitalize(f), bodyVal);
									}
								} else {
									o2.persistForClass(f, bodyVal);
									o2.relateForClass(f, bodyVal);
									if(!StringUtils.containsAny(f, "gpuDeviceId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
										body2.put("set" + StringUtils.capitalize(f), bodyVal);
								}
							}
							for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
								if(!body.fieldNames().contains(f)) {
									if(!StringUtils.containsAny(f, "gpuDeviceId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
										body2.putNull("set" + StringUtils.capitalize(f));
								}
							}
							if(searchList.size() == 1) {
								apiRequest.setOriginal(o);
								apiRequest.setId(o.getGpuDeviceId());
								apiRequest.setPk(o.getPk());
							}
							siteRequest.setJsonObject(body2);
							patchGpuDeviceFuture(o, true).onSuccess(b -> {
								LOG.debug("Import GpuDevice {} succeeded, modified GpuDevice. ", body.getValue(GpuDevice.VAR_gpuDeviceId));
								eventHandler.handle(Future.succeededFuture());
							}).onFailure(ex -> {
								LOG.error(String.format("putimportGpuDeviceFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							postGpuDeviceFuture(siteRequest, true).onSuccess(b -> {
								LOG.debug("Import GpuDevice {} succeeded, created new GpuDevice. ", body.getValue(GpuDevice.VAR_gpuDeviceId));
								eventHandler.handle(Future.succeededFuture());
							}).onFailure(ex -> {
								LOG.error(String.format("putimportGpuDeviceFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							});
						}
					} catch(Exception ex) {
						LOG.error(String.format("putimportGpuDeviceFuture failed. "), ex);
						eventHandler.handle(Future.failedFuture(ex));
					}
				}).onFailure(ex -> {
					LOG.error(String.format("putimportGpuDeviceFuture failed. "), ex);
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Exception ex) {
				LOG.error(String.format("putimportGpuDeviceFuture failed. "), ex);
				eventHandler.handle(Future.failedFuture(ex));
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("putimportGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200PUTImportGpuDevice(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
						String m = String.format("%s %s not found", "GPU device", gpuDeviceId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PUTImportGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// SearchPage //

	@Override
	public void searchpageGpuDevice(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchGpuDeviceList(siteRequest, false, true, false).onSuccess(listGpuDevice -> {
							response200SearchPageGpuDevice(listGpuDevice).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchpageGpuDevice succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchpageGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchpageGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchpageGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchpageGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("searchpageGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void searchpageGpuDevicePageInit(GpuDevicePage page, SearchList<GpuDevice> listGpuDevice) {
	}

	public String templateSearchPageGpuDevice(ServiceRequest serviceRequest) {
		return "en-us/search/gpu-device/GpuDeviceSearchPage.htm";
	}
	public Future<ServiceResponse> response200SearchPageGpuDevice(SearchList<GpuDevice> listGpuDevice) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listGpuDevice.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateSearchPageGpuDevice(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			GpuDevicePage page = new GpuDevicePage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listGpuDevice.size() >= 1)
				siteRequest.setRequestPk(listGpuDevice.get(0).getPk());
			page.setSearchListGpuDevice_(listGpuDevice);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepGpuDevicePage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200SearchPageGpuDevice failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchPageGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchPageGpuDevice(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
		if(pivots != null) {
			for(SolrResponse.Pivot pivotField : pivots) {
				String entityIndexed = pivotField.getField();
				String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
				JsonObject pivotJson = new JsonObject();
				pivotArray.add(pivotJson);
				pivotJson.put("field", entityVar);
				pivotJson.put("value", pivotField.getValue());
				pivotJson.put("count", pivotField.getCount());
				Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
				List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
				if(pivotRanges != null) {
					JsonObject rangeJson = new JsonObject();
					pivotJson.put("ranges", rangeJson);
					for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
						JsonObject rangeFacetJson = new JsonObject();
						String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
						rangeJson.put(rangeFacetVar, rangeFacetJson);
						JsonObject rangeFacetCountsObject = new JsonObject();
						rangeFacetJson.put("counts", rangeFacetCountsObject);
						rangeFacet.getCounts().forEach((value, count) -> {
							rangeFacetCountsObject.put(value, count);
						});
					}
				}
				if(pivotFields2 != null) {
					JsonArray pivotArray2 = new JsonArray();
					pivotJson.put("pivot", pivotArray2);
					responsePivotSearchPageGpuDevice(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// EditPage //

	@Override
	public void editpageGpuDevice(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchGpuDeviceList(siteRequest, false, true, false).onSuccess(listGpuDevice -> {
							response200EditPageGpuDevice(listGpuDevice).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("editpageGpuDevice succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("editpageGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("editpageGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("editpageGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("editpageGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("editpageGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void editpageGpuDevicePageInit(GpuDevicePage page, SearchList<GpuDevice> listGpuDevice) {
	}

	public String templateEditPageGpuDevice(ServiceRequest serviceRequest) {
		return "en-us/edit/gpu-device/GpuDeviceEditPage.htm";
	}
	public Future<ServiceResponse> response200EditPageGpuDevice(SearchList<GpuDevice> listGpuDevice) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listGpuDevice.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateEditPageGpuDevice(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			GpuDevicePage page = new GpuDevicePage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listGpuDevice.size() >= 1)
				siteRequest.setRequestPk(listGpuDevice.get(0).getPk());
			page.setSearchListGpuDevice_(listGpuDevice);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepGpuDevicePage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200EditPageGpuDevice failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200EditPageGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotEditPageGpuDevice(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
		if(pivots != null) {
			for(SolrResponse.Pivot pivotField : pivots) {
				String entityIndexed = pivotField.getField();
				String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
				JsonObject pivotJson = new JsonObject();
				pivotArray.add(pivotJson);
				pivotJson.put("field", entityVar);
				pivotJson.put("value", pivotField.getValue());
				pivotJson.put("count", pivotField.getCount());
				Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
				List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
				if(pivotRanges != null) {
					JsonObject rangeJson = new JsonObject();
					pivotJson.put("ranges", rangeJson);
					for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
						JsonObject rangeFacetJson = new JsonObject();
						String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
						rangeJson.put(rangeFacetVar, rangeFacetJson);
						JsonObject rangeFacetCountsObject = new JsonObject();
						rangeFacetJson.put("counts", rangeFacetCountsObject);
						rangeFacet.getCounts().forEach((value, count) -> {
							rangeFacetCountsObject.put(value, count);
						});
					}
				}
				if(pivotFields2 != null) {
					JsonArray pivotArray2 = new JsonArray();
					pivotJson.put("pivot", pivotArray2);
					responsePivotEditPageGpuDevice(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// UserPage //

	@Override
	public void userpageGpuDevice(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchGpuDeviceList(siteRequest, false, true, false).onSuccess(listGpuDevice -> {
							response200UserPageGpuDevice(listGpuDevice).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("userpageGpuDevice succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("userpageGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("userpageGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("userpageGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("userpageGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("userpageGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void userpageGpuDevicePageInit(GpuDevicePage page, SearchList<GpuDevice> listGpuDevice) {
	}

	public String templateUserPageGpuDevice(ServiceRequest serviceRequest) {
		return String.format("%s.htm", serviceRequest.getExtra().getString("uri").substring(1));
	}
	public Future<ServiceResponse> response200UserPageGpuDevice(SearchList<GpuDevice> listGpuDevice) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listGpuDevice.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateUserPageGpuDevice(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			GpuDevicePage page = new GpuDevicePage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listGpuDevice.size() >= 1)
				siteRequest.setRequestPk(listGpuDevice.get(0).getPk());
			page.setSearchListGpuDevice_(listGpuDevice);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepGpuDevicePage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200UserPageGpuDevice failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200UserPageGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotUserPageGpuDevice(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
		if(pivots != null) {
			for(SolrResponse.Pivot pivotField : pivots) {
				String entityIndexed = pivotField.getField();
				String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
				JsonObject pivotJson = new JsonObject();
				pivotArray.add(pivotJson);
				pivotJson.put("field", entityVar);
				pivotJson.put("value", pivotField.getValue());
				pivotJson.put("count", pivotField.getCount());
				Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
				List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
				if(pivotRanges != null) {
					JsonObject rangeJson = new JsonObject();
					pivotJson.put("ranges", rangeJson);
					for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
						JsonObject rangeFacetJson = new JsonObject();
						String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
						rangeJson.put(rangeFacetVar, rangeFacetJson);
						JsonObject rangeFacetCountsObject = new JsonObject();
						rangeFacetJson.put("counts", rangeFacetCountsObject);
						rangeFacet.getCounts().forEach((value, count) -> {
							rangeFacetCountsObject.put(value, count);
						});
					}
				}
				if(pivotFields2 != null) {
					JsonArray pivotArray2 = new JsonArray();
					pivotJson.put("pivot", pivotArray2);
					responsePivotUserPageGpuDevice(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// DELETEFilter //

	@Override
	public void deletefilterGpuDevice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deletefilterGpuDevice started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", GpuDevice.CLASS_SIMPLE_NAME, "PUT"));
			if(gpuDeviceId != null)
				form.add("permission", String.format("%s-%s#%s", GpuDevice.CLASS_SIMPLE_NAME, gpuDeviceId, "DELETE"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchGpuDeviceList(siteRequest, false, true, true).onSuccess(listGpuDevice -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listGpuDevice.getRequest().getRows());
								apiRequest.setNumFound(listGpuDevice.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listGpuDevice.first());
								apiRequest.setPk(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());

								listDELETEFilterGpuDevice(apiRequest, listGpuDevice).onSuccess(e -> {
									response200DELETEFilterGpuDevice(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deletefilterGpuDevice succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deletefilterGpuDevice failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEFilterGpuDevice(ApiRequest apiRequest, SearchList<GpuDevice> listGpuDevice) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listGpuDevice.getSiteRequest_(SiteRequest.class);
		listGpuDevice.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			GpuDevice o2 = jsonObject.mapTo(GpuDevice.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deletefilterGpuDeviceFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEFilterGpuDevice failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listGpuDevice.next().onSuccess(next -> {
				if(next) {
					listDELETEFilterGpuDevice(apiRequest, listGpuDevice).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEFilterGpuDevice failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEFilterGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEFilterGpuDevice failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deletefilterGpuDeviceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchGpuDeviceList(siteRequest, false, true, true).onSuccess(listGpuDevice -> {
					try {
						GpuDevice o = listGpuDevice.first();
						if(o != null && listGpuDevice.getResponse().getResponse().getNumFound() == 1) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1L);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
								siteRequest.getRequestVars().put( "refresh", "false" );
							}
							if(apiRequest.getNumFound() == 1L)
								apiRequest.setOriginal(o);
							apiRequest.setId(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getGpuDeviceId()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listGpuDevice.first()).map(o2 -> o2.getPk()).orElse(null));
							deletefilterGpuDeviceFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deletefilterGpuDevice failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<GpuDevice> deletefilterGpuDeviceFuture(GpuDevice o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<GpuDevice> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<GpuDevice> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsGpuDevice(siteRequest).onSuccess(a -> {
					sqlDELETEFilterGpuDevice(o).onSuccess(gpuDevice -> {
						relateGpuDevice(o).onSuccess(d -> {
							unindexGpuDevice(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestGpuDevice();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketGpuDevice", JsonObject.mapFrom(apiRequest).toString());
									}
								}
								promise1.complete();
							}).onFailure(ex -> {
								promise1.fail(ex);
							});
						}).onFailure(ex -> {
							promise1.fail(ex);
						});
					}).onFailure(ex -> {
						promise1.fail(ex);
					});
				}).onFailure(ex -> {
					promise1.fail(ex);
				});
				return promise1.future();
			}).onSuccess(a -> {
				siteRequest.setSqlConnection(null);
			}).onFailure(ex -> {
				siteRequest.setSqlConnection(null);
				promise.fail(ex);
			}).compose(gpuDevice -> {
				Promise<GpuDevice> promise2 = Promise.promise();
				refreshGpuDevice(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(gpuDevice -> {
				promise.complete(gpuDevice);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deletefilterGpuDeviceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEFilterGpuDevice(GpuDevice o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM GpuDevice ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			GpuDevice o2 = new GpuDevice();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					}
				}
			}
			bSql.append(" WHERE pk=$" + num);
			bParams.add(pk);
			num++;
			futures2.add(0, Future.future(a -> {
				sqlConnection.preparedQuery(bSql.toString())
						.execute(Tuple.tuple(bParams)
						).onSuccess(b -> {
					a.handle(Future.succeededFuture());
				}).onFailure(ex -> {
					RuntimeException ex2 = new RuntimeException("value GpuDevice failed", ex);
					LOG.error(String.format("unrelateGpuDevice failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEFilterGpuDevice failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEFilterGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEFilterGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEFilterGpuDevice(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String gpuDeviceId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("gpuDeviceId");
						String m = String.format("%s %s not found", "GPU device", gpuDeviceId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEFilterGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// General //

	public Future<GpuDevice> createGpuDevice(SiteRequest siteRequest) {
		Promise<GpuDevice> promise = Promise.promise();
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long userKey = siteRequest.getUserKey();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

			sqlConnection.preparedQuery("INSERT INTO GpuDevice(created, userKey) VALUES($1, $2) RETURNING pk")
					.collecting(Collectors.toList())
					.execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
				Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				GpuDevice o = new GpuDevice();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				promise.complete(o);
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error("createGpuDevice failed. ", ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("createGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public void searchGpuDeviceQ(SearchList<GpuDevice> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
		}
	}

	public String searchGpuDeviceFq(SearchList<GpuDevice> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		if(StringUtils.startsWith(valueIndexed, "[")) {
			String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
			if(fqs.length != 2)
				throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
			String fq1 = fqs[0].equals("*") ? fqs[0] : GpuDevice.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
			String fq2 = fqs[1].equals("*") ? fqs[1] : GpuDevice.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
			 return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
		} else {
			return varIndexed + ":" + SearchTool.escapeQueryChars(GpuDevice.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
		}
	}

	public void searchGpuDeviceSort(SearchList<GpuDevice> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.sort(varIndexed, valueIndexed);
	}

	public void searchGpuDeviceRows(SearchList<GpuDevice> searchList, Long valueRows) {
			searchList.rows(valueRows != null ? valueRows : 10L);
	}

	public void searchGpuDeviceStart(SearchList<GpuDevice> searchList, Long valueStart) {
		searchList.start(valueStart);
	}

	public void searchGpuDeviceVar(SearchList<GpuDevice> searchList, String var, String value) {
		searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
	}

	public void searchGpuDeviceUri(SearchList<GpuDevice> searchList) {
	}

	public Future<ServiceResponse> varsGpuDevice(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();

			serviceRequest.getParams().getJsonObject("query").stream().filter(paramRequest -> "var".equals(paramRequest.getKey()) && paramRequest.getValue() != null).findFirst().ifPresent(paramRequest -> {
				String entityVar = null;
				String valueIndexed = null;
				Object paramValuesObject = paramRequest.getValue();
				JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

				try {
					for(Object paramObject : paramObjects) {
						entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
						valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
						siteRequest.getRequestVars().put(entityVar, valueIndexed);
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchGpuDevice failed. "), ex);
					promise.fail(ex);
				}
			});
			promise.complete();
		} catch(Exception ex) {
			LOG.error(String.format("searchGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<SearchList<GpuDevice>> searchGpuDeviceList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify) {
		Promise<SearchList<GpuDevice>> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();
			String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<GpuDevice> searchList = new SearchList<GpuDevice>();
			String facetRange = null;
			Date facetRangeStart = null;
			Date facetRangeEnd = null;
			String facetRangeGap = null;
			String statsField = null;
			String statsFieldIndexed = null;
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.q("*:*");
			searchList.setC(GpuDevice.class);
			searchList.setSiteRequest_(siteRequest);
			searchList.facetMinCount(1);
			if(entityList != null) {
				for(String v : entityList) {
					searchList.fl(GpuDevice.varIndexedGpuDevice(v));
				}
			}

			String gpuDeviceId = serviceRequest.getParams().getJsonObject("path").getString("gpuDeviceId");
			if(gpuDeviceId != null && NumberUtils.isCreatable(gpuDeviceId)) {
				searchList.fq("(_docvalues_string:" + SearchTool.escapeQueryChars(gpuDeviceId) + " OR gpuDeviceId_docvalues_string:" + SearchTool.escapeQueryChars(gpuDeviceId) + ")");
			} else if(gpuDeviceId != null) {
				searchList.fq("gpuDeviceId_docvalues_string:" + SearchTool.escapeQueryChars(gpuDeviceId));
			}

			for(String paramName : serviceRequest.getParams().getJsonObject("query").fieldNames()) {
				Object paramValuesObject = serviceRequest.getParams().getJsonObject("query").getValue(paramName);
				String entityVar = null;
				String valueIndexed = null;
				String varIndexed = null;
				String valueSort = null;
				Long valueStart = null;
				Long valueRows = null;
				String valueCursorMark = null;
				JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

				try {
					if(paramValuesObject != null && "facet.pivot".equals(paramName)) {
						Matcher mFacetPivot = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher(StringUtils.join(paramObjects.getList().toArray(), ","));
						if(mFacetPivot.find()) {
							String solrLocalParams = mFacetPivot.group(1);
							String[] entityVars = mFacetPivot.group(2).trim().split(",");
							String[] varsIndexed = new String[entityVars.length];
							for(Integer i = 0; i < entityVars.length; i++) {
								entityVar = entityVars[i];
								varsIndexed[i] = GpuDevice.varIndexedGpuDevice(entityVar);
							}
							searchList.facetPivot((solrLocalParams == null ? "" : solrLocalParams) + StringUtils.join(varsIndexed, ","));
						}
					} else if(paramValuesObject != null) {
						for(Object paramObject : paramObjects) {
							if(paramName.equals("q")) {
								Matcher mQ = Pattern.compile("(\\w+):(.+?(?=(\\)|\\s+OR\\s+|\\s+AND\\s+|\\^|$)))").matcher((String)paramObject);
								StringBuffer sb = new StringBuffer();
								while(mQ.find()) {
									entityVar = mQ.group(1).trim();
									valueIndexed = mQ.group(2).trim();
									varIndexed = GpuDevice.varIndexedGpuDevice(entityVar);
									String entityQ = searchGpuDeviceFq(searchList, entityVar, valueIndexed, varIndexed);
									mQ.appendReplacement(sb, entityQ);
								}
								if(!sb.isEmpty()) {
									mQ.appendTail(sb);
									searchList.q(sb.toString());
								}
							} else if(paramName.equals("fq")) {
								Matcher mFq = Pattern.compile("(\\w+):(.+?(?=(\\)|\\s+OR\\s+|\\s+AND\\s+|$)))").matcher((String)paramObject);
									StringBuffer sb = new StringBuffer();
								while(mFq.find()) {
									entityVar = mFq.group(1).trim();
									valueIndexed = mFq.group(2).trim();
									varIndexed = GpuDevice.varIndexedGpuDevice(entityVar);
									String entityFq = searchGpuDeviceFq(searchList, entityVar, valueIndexed, varIndexed);
									mFq.appendReplacement(sb, entityFq);
								}
								if(!sb.isEmpty()) {
									mFq.appendTail(sb);
									searchList.fq(sb.toString());
								}
							} else if(paramName.equals("sort")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = GpuDevice.varIndexedGpuDevice(entityVar);
								searchGpuDeviceSort(searchList, entityVar, valueIndexed, varIndexed);
							} else if(paramName.equals("start")) {
								valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchGpuDeviceStart(searchList, valueStart);
							} else if(paramName.equals("rows")) {
								valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchGpuDeviceRows(searchList, valueRows);
							} else if(paramName.equals("stats")) {
								searchList.stats((Boolean)paramObject);
							} else if(paramName.equals("stats.field")) {
								Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								if(mStats.find()) {
									String solrLocalParams = mStats.group(1);
									entityVar = mStats.group(2).trim();
									varIndexed = GpuDevice.varIndexedGpuDevice(entityVar);
									searchList.statsField((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									statsField = entityVar;
									statsFieldIndexed = varIndexed;
								}
							} else if(paramName.equals("facet")) {
								searchList.facet((Boolean)paramObject);
							} else if(paramName.equals("facet.range.start")) {
								String startMathStr = (String)paramObject;
								Date start = SearchTool.parseMath(startMathStr);
								searchList.facetRangeStart(start.toInstant().toString());
								facetRangeStart = start;
							} else if(paramName.equals("facet.range.end")) {
								String endMathStr = (String)paramObject;
								Date end = SearchTool.parseMath(endMathStr);
								searchList.facetRangeEnd(end.toInstant().toString());
								facetRangeEnd = end;
							} else if(paramName.equals("facet.range.gap")) {
								String gap = (String)paramObject;
								searchList.facetRangeGap(gap);
								facetRangeGap = gap;
							} else if(paramName.equals("facet.range")) {
								Matcher mFacetRange = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								if(mFacetRange.find()) {
									String solrLocalParams = mFacetRange.group(1);
									entityVar = mFacetRange.group(2).trim();
									varIndexed = GpuDevice.varIndexedGpuDevice(entityVar);
									searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									facetRange = entityVar;
								}
							} else if(paramName.equals("facet.field")) {
								entityVar = (String)paramObject;
								varIndexed = GpuDevice.varIndexedGpuDevice(entityVar);
								if(varIndexed != null)
									searchList.facetField(varIndexed);
							} else if(paramName.equals("var")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								searchGpuDeviceVar(searchList, entityVar, valueIndexed);
							} else if(paramName.equals("cursorMark")) {
								valueCursorMark = (String)paramObject;
								searchList.cursorMark((String)paramObject);
							}
						}
						searchGpuDeviceUri(searchList);
					}
				} catch(Exception e) {
					ExceptionUtils.rethrow(e);
				}
			}
			if("*:*".equals(searchList.getQuery()) && searchList.getSorts().size() == 0) {
				searchList.sort("created_docvalues_date", "desc");
			}
			String facetRange2 = facetRange;
			Date facetRangeStart2 = facetRangeStart;
			Date facetRangeEnd2 = facetRangeEnd;
			String facetRangeGap2 = facetRangeGap;
			String statsField2 = statsField;
			String statsFieldIndexed2 = statsFieldIndexed;
			searchGpuDevice2(siteRequest, populate, store, modify, searchList);
			searchList.promiseDeepForClass(siteRequest).onSuccess(searchList2 -> {
				if(facetRange2 != null && statsField2 != null && facetRange2.equals(statsField2)) {
					StatsField stats = searchList.getResponse().getStats().getStatsFields().get(statsFieldIndexed2);
					Instant min = Optional.ofNullable(stats.getMin()).map(val -> Instant.parse(val.toString())).orElse(Instant.now());
					Instant max = Optional.ofNullable(stats.getMax()).map(val -> Instant.parse(val.toString())).orElse(Instant.now());
					if(min.equals(max)) {
						min = min.minus(1, ChronoUnit.DAYS);
						max = max.plus(2, ChronoUnit.DAYS);
					}
					Duration duration = Duration.between(min, max);
					String gap = "DAY";
					if(duration.toDays() >= 365)
						gap = "YEAR";
					else if(duration.toDays() >= 28)
						gap = "MONTH";
					else if(duration.toDays() >= 1)
						gap = "DAY";
					else if(duration.toHours() >= 1)
						gap = "HOUR";
					else if(duration.toMinutes() >= 1)
						gap = "MINUTE";
					else if(duration.toMillis() >= 1000)
						gap = "SECOND";
					else if(duration.toMillis() >= 1)
						gap = "MILLI";

					if(facetRangeStart2 == null)
						searchList.facetRangeStart(min.toString());
					if(facetRangeEnd2 == null)
						searchList.facetRangeEnd(max.toString());
					if(facetRangeGap2 == null)
						searchList.facetRangeGap(String.format("+1%s", gap));
					searchList.query().onSuccess(b -> {
						promise.complete(searchList);
					}).onFailure(ex -> {
						LOG.error(String.format("searchGpuDevice failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete(searchList);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("searchGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("searchGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void searchGpuDevice2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<GpuDevice> searchList) {
	}

	public Future<Void> persistGpuDevice(GpuDevice o, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.preparedQuery("SELECT * FROM GpuDevice WHERE pk=$1")
					.collecting(Collectors.toList())
					.execute(Tuple.of(pk)
					).onSuccess(result -> {
				try {
					for(Row definition : result.value()) {
						for(Integer i = 0; i < definition.size(); i++) {
							String columnName = definition.getColumnName(i);
							Object columnValue = definition.getValue(i);
							if(!"pk".equals(columnName)) {
								try {
									o.persistForClass(columnName, columnValue);
								} catch(Exception e) {
									LOG.error(String.format("persistGpuDevice failed. "), e);
								}
							}
						}
					}
					o.promiseDeepForClass(siteRequest).onSuccess(a -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("persistGpuDevice failed. "), ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error(String.format("persistGpuDevice failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("persistGpuDevice failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("persistGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> relateGpuDevice(GpuDevice o) {
		Promise<Void> promise = Promise.promise();
			promise.complete();
		return promise.future();
	}

	public String searchVar(String varIndexed) {
		return GpuDevice.searchVarGpuDevice(varIndexed);
	}

	@Override
	public String getClassApiAddress() {
		return GpuDevice.CLASS_API_ADDRESS_GpuDevice;
	}

	public Future<GpuDevice> indexGpuDevice(GpuDevice o) {
		Promise<GpuDevice> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = new JsonObject();
			JsonObject add = new JsonObject();
			json.put("add", add);
			JsonObject doc = new JsonObject();
			add.put("doc", doc);
			o.indexGpuDevice(doc);
			String solrUsername = siteRequest.getConfig().getString(ConfigKeys.SOLR_USERNAME);
			String solrPassword = siteRequest.getConfig().getString(ConfigKeys.SOLR_PASSWORD);
			String solrHostName = siteRequest.getConfig().getString(ConfigKeys.SOLR_HOST_NAME);
			Integer solrPort = Integer.parseInt(siteRequest.getConfig().getString(ConfigKeys.SOLR_PORT));
			String solrCollection = siteRequest.getConfig().getString(ConfigKeys.SOLR_COLLECTION);
			Boolean solrSsl = Boolean.parseBoolean(siteRequest.getConfig().getString(ConfigKeys.SOLR_SSL));
			Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
			Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
				if(softCommit == null && commitWithin == null)
					softCommit = true;
				else if(softCommit == null)
					softCommit = false;
			String solrRequestUri = String.format("/solr/%s/update%s%s%s", solrCollection, "?overwrite=true&wt=json", softCommit ? "&softCommit=true" : "", commitWithin != null ? ("&commitWithin=" + commitWithin) : "");
			webClient.post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader("Content-Type", "application/json").sendBuffer(json.toBuffer()).expecting(HttpResponseExpectation.SC_OK).onSuccess(b -> {
				promise.complete(o);
			}).onFailure(ex -> {
				LOG.error(String.format("indexGpuDevice failed. "), new RuntimeException(ex));
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("indexGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<GpuDevice> unindexGpuDevice(GpuDevice o) {
		Promise<GpuDevice> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			o.promiseDeepForClass(siteRequest).onSuccess(a -> {
				JsonObject json = new JsonObject();
				JsonObject delete = new JsonObject();
				json.put("delete", delete);
				String query = String.format("filter(pk_docvalues_long:%s)", o.obtainForClass("pk"));
				delete.put("query", query);
				String solrUsername = siteRequest.getConfig().getString(ConfigKeys.SOLR_USERNAME);
				String solrPassword = siteRequest.getConfig().getString(ConfigKeys.SOLR_PASSWORD);
				String solrHostName = siteRequest.getConfig().getString(ConfigKeys.SOLR_HOST_NAME);
				Integer solrPort = Integer.parseInt(siteRequest.getConfig().getString(ConfigKeys.SOLR_PORT));
				String solrCollection = siteRequest.getConfig().getString(ConfigKeys.SOLR_COLLECTION);
				Boolean solrSsl = Boolean.parseBoolean(siteRequest.getConfig().getString(ConfigKeys.SOLR_SSL));
				Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
				Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
					if(softCommit == null && commitWithin == null)
						softCommit = true;
					else if(softCommit == null)
						softCommit = false;
				String solrRequestUri = String.format("/solr/%s/update%s%s%s", solrCollection, "?overwrite=true&wt=json", softCommit ? "&softCommit=true" : "", commitWithin != null ? ("&commitWithin=" + commitWithin) : "");
				webClient.post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader("Content-Type", "application/json").sendBuffer(json.toBuffer()).expecting(HttpResponseExpectation.SC_OK).onSuccess(b -> {
					promise.complete(o);
				}).onFailure(ex -> {
					LOG.error(String.format("unindexGpuDevice failed. "), new RuntimeException(ex));
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("unindexGpuDevice failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("unindexGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> refreshGpuDevice(GpuDevice o) {
		Promise<Void> promise = Promise.promise();
		SiteRequest siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean refresh = !"false".equals(siteRequest.getRequestVars().get("refresh"));
			if(refresh && !Optional.ofNullable(siteRequest.getJsonObject()).map(JsonObject::isEmpty).orElse(true)) {
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classSimpleName2 = classes.get(i);
				}

				CompositeFuture.all(futures).onSuccess(b -> {
					JsonObject params = new JsonObject();
					params.put("body", new JsonObject());
					params.put("cookie", new JsonObject());
					params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
					params.put("form", new JsonObject());
					params.put("path", new JsonObject());
					JsonObject query = new JsonObject();
					Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
					Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
					if(softCommit == null && commitWithin == null)
						softCommit = true;
					if(softCommit != null)
						query.put("softCommit", softCommit);
					if(commitWithin != null)
						query.put("commitWithin", commitWithin);
					query.put("q", "*:*").put("fq", new JsonArray().add("pk:" + o.getPk())).put("var", new JsonArray().add("refresh:false"));
					params.put("query", query);
					JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
					JsonObject json = new JsonObject().put("context", context);
					eventBus.request(GpuDevice.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchGpuDeviceFuture")).onSuccess(c -> {
						JsonObject responseMessage = (JsonObject)c.body();
						Integer statusCode = responseMessage.getInteger("statusCode");
						if(statusCode.equals(200))
							promise.complete();
						else
							promise.fail(new RuntimeException(responseMessage.getString("statusMessage")));
					}).onFailure(ex -> {
						LOG.error("Refresh relations failed. ", ex);
						promise.fail(ex);
					});
				}).onFailure(ex -> {
					LOG.error("Refresh relations failed. ", ex);
					promise.fail(ex);
				});
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error(String.format("refreshGpuDevice failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public Future<JsonObject> generatePageBody(ComputateSiteRequest siteRequest, Map<String, Object> ctx, String templatePath, String classSimpleName) {
		Promise<JsonObject> promise = Promise.promise();
		try {
			Map<String, Object> result = (Map<String, Object>)ctx.get("result");
			SiteRequest siteRequest2 = (SiteRequest)siteRequest;
			String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);
			GpuDevice page = new GpuDevice();
			page.setSiteRequest_((SiteRequest)siteRequest);

			page.persistForClass(GpuDevice.VAR_clusterName, GpuDevice.staticSetClusterName(siteRequest2, (String)result.get(GpuDevice.VAR_clusterName)));
			page.persistForClass(GpuDevice.VAR_nodeName, GpuDevice.staticSetNodeName(siteRequest2, (String)result.get(GpuDevice.VAR_nodeName)));
			page.persistForClass(GpuDevice.VAR_created, GpuDevice.staticSetCreated(siteRequest2, (String)result.get(GpuDevice.VAR_created)));
			page.persistForClass(GpuDevice.VAR_gpuDeviceNumber, GpuDevice.staticSetGpuDeviceNumber(siteRequest2, (String)result.get(GpuDevice.VAR_gpuDeviceNumber)));
			page.persistForClass(GpuDevice.VAR_gpuDeviceId, GpuDevice.staticSetGpuDeviceId(siteRequest2, (String)result.get(GpuDevice.VAR_gpuDeviceId)));
			page.persistForClass(GpuDevice.VAR_archived, GpuDevice.staticSetArchived(siteRequest2, (String)result.get(GpuDevice.VAR_archived)));
			page.persistForClass(GpuDevice.VAR_gpuDeviceUtilization, GpuDevice.staticSetGpuDeviceUtilization(siteRequest2, (String)result.get(GpuDevice.VAR_gpuDeviceUtilization)));
			page.persistForClass(GpuDevice.VAR_description, GpuDevice.staticSetDescription(siteRequest2, (String)result.get(GpuDevice.VAR_description)));
			page.persistForClass(GpuDevice.VAR_sessionId, GpuDevice.staticSetSessionId(siteRequest2, (String)result.get(GpuDevice.VAR_sessionId)));
			page.persistForClass(GpuDevice.VAR_userKey, GpuDevice.staticSetUserKey(siteRequest2, (String)result.get(GpuDevice.VAR_userKey)));
			page.persistForClass(GpuDevice.VAR_location, GpuDevice.staticSetLocation(siteRequest2, (String)result.get(GpuDevice.VAR_location)));
			page.persistForClass(GpuDevice.VAR_id, GpuDevice.staticSetId(siteRequest2, (String)result.get(GpuDevice.VAR_id)));
			page.persistForClass(GpuDevice.VAR_title, GpuDevice.staticSetTitle(siteRequest2, (String)result.get(GpuDevice.VAR_title)));
			page.persistForClass(GpuDevice.VAR_ngsildTenant, GpuDevice.staticSetNgsildTenant(siteRequest2, (String)result.get(GpuDevice.VAR_ngsildTenant)));
			page.persistForClass(GpuDevice.VAR_displayPage, GpuDevice.staticSetDisplayPage(siteRequest2, (String)result.get(GpuDevice.VAR_displayPage)));
			page.persistForClass(GpuDevice.VAR_ngsildPath, GpuDevice.staticSetNgsildPath(siteRequest2, (String)result.get(GpuDevice.VAR_ngsildPath)));
			page.persistForClass(GpuDevice.VAR_ngsildContext, GpuDevice.staticSetNgsildContext(siteRequest2, (String)result.get(GpuDevice.VAR_ngsildContext)));
			page.persistForClass(GpuDevice.VAR_ngsildData, GpuDevice.staticSetNgsildData(siteRequest2, (String)result.get(GpuDevice.VAR_ngsildData)));

			page.promiseDeepForClass((SiteRequest)siteRequest).onSuccess(a -> {
				try {
					JsonObject data = JsonObject.mapFrom(result);
					promise.complete(data);
				} catch(Exception ex) {
					LOG.error(String.format(importModelFail, classSimpleName), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("generatePageBody failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("generatePageBody failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
}
