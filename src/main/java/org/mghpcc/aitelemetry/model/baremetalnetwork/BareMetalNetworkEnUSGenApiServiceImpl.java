package org.mghpcc.aitelemetry.model.baremetalnetwork;

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
import org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetworkPage;


/**
 * Translate: false
 * Generated: true
 **/
public class BareMetalNetworkEnUSGenApiServiceImpl extends BaseApiServiceImpl implements BareMetalNetworkEnUSGenApiService {

	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalNetworkEnUSGenApiServiceImpl.class);

	// Search //

	@Override
	public void searchBareMetalNetwork(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNetworkList(siteRequest, false, true, false).onSuccess(listBareMetalNetwork -> {
							response200SearchBareMetalNetwork(listBareMetalNetwork).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchBareMetalNetwork succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200SearchBareMetalNetwork(SearchList<BareMetalNetwork> listBareMetalNetwork) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listBareMetalNetwork.getSiteRequest_(SiteRequest.class);
			List<String> fls = listBareMetalNetwork.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listBareMetalNetwork.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = BareMetalNetwork.varIndexedBareMetalNetwork(fieldName);
						if(v != null)
							fieldNames.add(BareMetalNetwork.varIndexedBareMetalNetwork(fieldName));
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
			response200Search(listBareMetalNetwork.getRequest(), listBareMetalNetwork.getResponse(), json);
			if(json == null) {
				String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
						String m = String.format("%s %s not found", "bare metal network", id);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchBareMetalNetwork(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchBareMetalNetwork(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// GET //

	@Override
	public void getBareMetalNetwork(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNetworkList(siteRequest, false, true, false).onSuccess(listBareMetalNetwork -> {
							response200GETBareMetalNetwork(listBareMetalNetwork).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("getBareMetalNetwork succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("getBareMetalNetwork failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("getBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("getBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("getBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("getBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200GETBareMetalNetwork(SearchList<BareMetalNetwork> listBareMetalNetwork) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listBareMetalNetwork.getSiteRequest_(SiteRequest.class);
			JsonObject json = JsonObject.mapFrom(listBareMetalNetwork.getList().stream().findFirst().orElse(null));
			if(json == null) {
				String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
						String m = String.format("%s %s not found", "bare metal network", id);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200GETBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PATCH //

	@Override
	public void patchBareMetalNetwork(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("patchBareMetalNetwork started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "PATCH"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNetworkList(siteRequest, false, true, true).onSuccess(listBareMetalNetwork -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listBareMetalNetwork.getRequest().getRows());
								apiRequest.setNumFound(listBareMetalNetwork.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listBareMetalNetwork.first());
								apiRequest.setId(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getId()).orElse(null));
								apiRequest.setPk(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());

								listPATCHBareMetalNetwork(apiRequest, listBareMetalNetwork).onSuccess(e -> {
									response200PATCHBareMetalNetwork(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("patchBareMetalNetwork succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("patchBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPATCHBareMetalNetwork(ApiRequest apiRequest, SearchList<BareMetalNetwork> listBareMetalNetwork) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listBareMetalNetwork.getSiteRequest_(SiteRequest.class);
		listBareMetalNetwork.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			BareMetalNetwork o2 = jsonObject.mapTo(BareMetalNetwork.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				patchBareMetalNetworkFuture(o2, false).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listPATCHBareMetalNetwork failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listBareMetalNetwork.next().onSuccess(next -> {
				if(next) {
					listPATCHBareMetalNetwork(apiRequest, listBareMetalNetwork).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPATCHBareMetalNetwork failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listPATCHBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listPATCHBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void patchBareMetalNetworkFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchBareMetalNetworkList(siteRequest, false, true, true).onSuccess(listBareMetalNetwork -> {
					try {
						BareMetalNetwork o = listBareMetalNetwork.first();
						if(o != null && listBareMetalNetwork.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getId()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getPk()).orElse(null));
							JsonObject jsonObject = JsonObject.mapFrom(o);
							BareMetalNetwork o2 = jsonObject.mapTo(BareMetalNetwork.class);
							o2.setSiteRequest_(siteRequest);
							patchBareMetalNetworkFuture(o2, false).onSuccess(o3 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("patchBareMetalNetwork failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<BareMetalNetwork> patchBareMetalNetworkFuture(BareMetalNetwork o, Boolean id) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<BareMetalNetwork> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<BareMetalNetwork> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsBareMetalNetwork(siteRequest).onSuccess(a -> {
					sqlPATCHBareMetalNetwork(o, id).onSuccess(bareMetalNetwork -> {
						persistBareMetalNetwork(bareMetalNetwork, true).onSuccess(c -> {
							relateBareMetalNetwork(bareMetalNetwork).onSuccess(d -> {
								indexBareMetalNetwork(bareMetalNetwork).onSuccess(o2 -> {
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
											o2.apiRequestBareMetalNetwork();
											if(apiRequest.getVars().size() > 0)
												eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());
										}
									}
									promise1.complete(bareMetalNetwork);
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
			}).compose(bareMetalNetwork -> {
				Promise<BareMetalNetwork> promise2 = Promise.promise();
				refreshBareMetalNetwork(bareMetalNetwork).onSuccess(a -> {
					promise2.complete(bareMetalNetwork);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(bareMetalNetwork -> {
				promise.complete(bareMetalNetwork);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("patchBareMetalNetworkFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<BareMetalNetwork> sqlPATCHBareMetalNetwork(BareMetalNetwork o, Boolean id) {
		Promise<BareMetalNetwork> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE BareMetalNetwork SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			BareMetalNetwork o2 = new BareMetalNetwork();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			for(String entityVar : methodNames) {
				switch(entityVar) {
					case "setId":
							o2.setId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_id + "=$" + num);
							num++;
							bParams.add(o2.sqlId());
						break;
					case "setName":
							o2.setName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_name + "=$" + num);
							num++;
							bParams.add(o2.sqlName());
						break;
					case "setCreated":
							o2.setCreated(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_created + "=$" + num);
							num++;
							bParams.add(o2.sqlCreated());
						break;
					case "setDescription":
							o2.setDescription(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_description + "=$" + num);
							num++;
							bParams.add(o2.sqlDescription());
						break;
					case "setAvailabilityZoneHints":
							o2.setAvailabilityZoneHints(jsonObject.getJsonArray(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_availabilityZoneHints + "=$" + num);
							num++;
							bParams.add(o2.sqlAvailabilityZoneHints());
						break;
					case "setArchived":
							o2.setArchived(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_archived + "=$" + num);
							num++;
							bParams.add(o2.sqlArchived());
						break;
					case "setAvailabilityZones":
							o2.setAvailabilityZones(jsonObject.getJsonArray(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_availabilityZones + "=$" + num);
							num++;
							bParams.add(o2.sqlAvailabilityZones());
						break;
					case "setCreatedAt":
							o2.setCreatedAt(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_createdAt + "=$" + num);
							num++;
							bParams.add(o2.sqlCreatedAt());
						break;
					case "setDnsDomain":
							o2.setDnsDomain(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_dnsDomain + "=$" + num);
							num++;
							bParams.add(o2.sqlDnsDomain());
						break;
					case "setMtu":
							o2.setMtu(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_mtu + "=$" + num);
							num++;
							bParams.add(o2.sqlMtu());
						break;
					case "setSessionId":
							o2.setSessionId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_sessionId + "=$" + num);
							num++;
							bParams.add(o2.sqlSessionId());
						break;
					case "setProjectId":
							o2.setProjectId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_projectId + "=$" + num);
							num++;
							bParams.add(o2.sqlProjectId());
						break;
					case "setUserKey":
							o2.setUserKey(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_userKey + "=$" + num);
							num++;
							bParams.add(o2.sqlUserKey());
						break;
					case "setProviderNetworkType":
							o2.setProviderNetworkType(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_providerNetworkType + "=$" + num);
							num++;
							bParams.add(o2.sqlProviderNetworkType());
						break;
					case "setProviderPhysicalNetwork":
							o2.setProviderPhysicalNetwork(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_providerPhysicalNetwork + "=$" + num);
							num++;
							bParams.add(o2.sqlProviderPhysicalNetwork());
						break;
					case "setProviderSegmentationId":
							o2.setProviderSegmentationId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_providerSegmentationId + "=$" + num);
							num++;
							bParams.add(o2.sqlProviderSegmentationId());
						break;
					case "setObjectTitle":
							o2.setObjectTitle(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_objectTitle + "=$" + num);
							num++;
							bParams.add(o2.sqlObjectTitle());
						break;
					case "setQosPolicyId":
							o2.setQosPolicyId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_qosPolicyId + "=$" + num);
							num++;
							bParams.add(o2.sqlQosPolicyId());
						break;
					case "setDisplayPage":
							o2.setDisplayPage(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_displayPage + "=$" + num);
							num++;
							bParams.add(o2.sqlDisplayPage());
						break;
					case "setRevisionNumber":
							o2.setRevisionNumber(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_revisionNumber + "=$" + num);
							num++;
							bParams.add(o2.sqlRevisionNumber());
						break;
					case "setStatus":
							o2.setStatus(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_status + "=$" + num);
							num++;
							bParams.add(o2.sqlStatus());
						break;
					case "setSubnetIds":
							o2.setSubnetIds(jsonObject.getJsonArray(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_subnetIds + "=$" + num);
							num++;
							bParams.add(o2.sqlSubnetIds());
						break;
					case "setTags":
							o2.setTags(jsonObject.getJsonArray(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_tags + "=$" + num);
							num++;
							bParams.add(o2.sqlTags());
						break;
					case "setTenantId":
							o2.setTenantId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_tenantId + "=$" + num);
							num++;
							bParams.add(o2.sqlTenantId());
						break;
					case "setUpdatedAt":
							o2.setUpdatedAt(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_updatedAt + "=$" + num);
							num++;
							bParams.add(o2.sqlUpdatedAt());
						break;
					case "setIsAdminStateUp":
							o2.setIsAdminStateUp(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_isAdminStateUp + "=$" + num);
							num++;
							bParams.add(o2.sqlIsAdminStateUp());
						break;
					case "setIsDefault":
							o2.setIsDefault(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_isDefault + "=$" + num);
							num++;
							bParams.add(o2.sqlIsDefault());
						break;
					case "setIsPortSecurityEnabled":
							o2.setIsPortSecurityEnabled(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_isPortSecurityEnabled + "=$" + num);
							num++;
							bParams.add(o2.sqlIsPortSecurityEnabled());
						break;
					case "setIsRouterExternal":
							o2.setIsRouterExternal(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_isRouterExternal + "=$" + num);
							num++;
							bParams.add(o2.sqlIsRouterExternal());
						break;
					case "setIsShared":
							o2.setIsShared(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_isShared + "=$" + num);
							num++;
							bParams.add(o2.sqlIsShared());
						break;
					case "setIsVlanQueing":
							o2.setIsVlanQueing(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_isVlanQueing + "=$" + num);
							num++;
							bParams.add(o2.sqlIsVlanQueing());
						break;
					case "setIsVlanTransparent":
							o2.setIsVlanTransparent(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_isVlanTransparent + "=$" + num);
							num++;
							bParams.add(o2.sqlIsVlanTransparent());
						break;
					case "setL2Adjacency":
							o2.setL2Adjacency(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_l2Adjacency + "=$" + num);
							num++;
							bParams.add(o2.sqlL2Adjacency());
						break;
					case "setLocationCloud":
							o2.setLocationCloud(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_locationCloud + "=$" + num);
							num++;
							bParams.add(o2.sqlLocationCloud());
						break;
					case "setLocationProjectDomainId":
							o2.setLocationProjectDomainId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_locationProjectDomainId + "=$" + num);
							num++;
							bParams.add(o2.sqlLocationProjectDomainId());
						break;
					case "setLocationProjectDomainName":
							o2.setLocationProjectDomainName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_locationProjectDomainName + "=$" + num);
							num++;
							bParams.add(o2.sqlLocationProjectDomainName());
						break;
					case "setLocationProjectId":
							o2.setLocationProjectId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_locationProjectId + "=$" + num);
							num++;
							bParams.add(o2.sqlLocationProjectId());
						break;
					case "setLocationProjectName":
							o2.setLocationProjectName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_locationProjectName + "=$" + num);
							num++;
							bParams.add(o2.sqlLocationProjectName());
						break;
					case "setLocationRegionName":
							o2.setLocationRegionName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_locationRegionName + "=$" + num);
							num++;
							bParams.add(o2.sqlLocationRegionName());
						break;
					case "setLocationZone":
							o2.setLocationZone(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNetwork.VAR_locationZone + "=$" + num);
							num++;
							bParams.add(o2.sqlLocationZone());
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
						RuntimeException ex2 = new RuntimeException("value BareMetalNetwork failed", ex);
						LOG.error(String.format("relateBareMetalNetwork failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					BareMetalNetwork o3 = new BareMetalNetwork();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					promise.complete(o3);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPATCHBareMetalNetwork failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPATCHBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPATCHBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200PATCHBareMetalNetwork(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
						String m = String.format("%s %s not found", "bare metal network", id);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PATCHBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// POST //

	@Override
	public void postBareMetalNetwork(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("postBareMetalNetwork started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "POST"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1L);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());
						JsonObject params = new JsonObject();
						params.put("body", siteRequest.getJsonObject());
						params.put("path", new JsonObject());
						params.put("cookie", siteRequest.getServiceRequest().getParams().getJsonObject("cookie"));
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
						eventBus.request(BareMetalNetwork.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postBareMetalNetworkFuture")).onSuccess(a -> {
							JsonObject responseMessage = (JsonObject)a.body();
							JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
							apiRequest.setPk(Long.parseLong(responseBody.getString("pk")));
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
							LOG.debug(String.format("postBareMetalNetwork succeeded. "));
						}).onFailure(ex -> {
							LOG.error(String.format("postBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("postBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("postBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	@Override
	public void postBareMetalNetworkFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
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
				postBareMetalNetworkFuture(siteRequest, false).onSuccess(o -> {
					eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
				}).onFailure(ex -> {
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Throwable ex) {
				LOG.error(String.format("postBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("postBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<BareMetalNetwork> postBareMetalNetworkFuture(SiteRequest siteRequest, Boolean id) {
		Promise<BareMetalNetwork> promise = Promise.promise();

		try {
			pgPool.withTransaction(sqlConnection -> {
				Promise<BareMetalNetwork> promise1 = Promise.promise();
				siteRequest.setSqlConnection(sqlConnection);
				varsBareMetalNetwork(siteRequest).onSuccess(a -> {
					createBareMetalNetwork(siteRequest).onSuccess(bareMetalNetwork -> {
						sqlPOSTBareMetalNetwork(bareMetalNetwork, id).onSuccess(b -> {
							persistBareMetalNetwork(bareMetalNetwork, false).onSuccess(c -> {
								relateBareMetalNetwork(bareMetalNetwork).onSuccess(d -> {
									indexBareMetalNetwork(bareMetalNetwork).onSuccess(o2 -> {
										promise1.complete(bareMetalNetwork);
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
			}).compose(bareMetalNetwork -> {
				Promise<BareMetalNetwork> promise2 = Promise.promise();
				refreshBareMetalNetwork(bareMetalNetwork).onSuccess(a -> {
					try {
						ApiRequest apiRequest = siteRequest.getApiRequest_();
						if(apiRequest != null) {
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							bareMetalNetwork.apiRequestBareMetalNetwork();
							eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());
						}
						promise2.complete(bareMetalNetwork);
					} catch(Exception ex) {
						LOG.error(String.format("postBareMetalNetworkFuture failed. "), ex);
						promise.fail(ex);
					}
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(bareMetalNetwork -> {
				try {
					ApiRequest apiRequest = siteRequest.getApiRequest_();
					if(apiRequest != null) {
						apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
						bareMetalNetwork.apiRequestBareMetalNetwork();
						eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());
					}
					promise.complete(bareMetalNetwork);
				} catch(Exception ex) {
					LOG.error(String.format("postBareMetalNetworkFuture failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("postBareMetalNetworkFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<BareMetalNetwork> sqlPOSTBareMetalNetwork(BareMetalNetwork o, Boolean id) {
		Promise<BareMetalNetwork> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE BareMetalNetwork SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			BareMetalNetwork o2 = new BareMetalNetwork();
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
					case BareMetalNetwork.VAR_id:
						o2.setId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_id + "=$" + num);
						num++;
						bParams.add(o2.sqlId());
						break;
					case BareMetalNetwork.VAR_name:
						o2.setName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_name + "=$" + num);
						num++;
						bParams.add(o2.sqlName());
						break;
					case BareMetalNetwork.VAR_created:
						o2.setCreated(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_created + "=$" + num);
						num++;
						bParams.add(o2.sqlCreated());
						break;
					case BareMetalNetwork.VAR_description:
						o2.setDescription(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_description + "=$" + num);
						num++;
						bParams.add(o2.sqlDescription());
						break;
					case BareMetalNetwork.VAR_availabilityZoneHints:
						o2.setAvailabilityZoneHints(jsonObject.getJsonArray(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_availabilityZoneHints + "=$" + num);
						num++;
						bParams.add(o2.sqlAvailabilityZoneHints());
						break;
					case BareMetalNetwork.VAR_archived:
						o2.setArchived(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_archived + "=$" + num);
						num++;
						bParams.add(o2.sqlArchived());
						break;
					case BareMetalNetwork.VAR_availabilityZones:
						o2.setAvailabilityZones(jsonObject.getJsonArray(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_availabilityZones + "=$" + num);
						num++;
						bParams.add(o2.sqlAvailabilityZones());
						break;
					case BareMetalNetwork.VAR_createdAt:
						o2.setCreatedAt(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_createdAt + "=$" + num);
						num++;
						bParams.add(o2.sqlCreatedAt());
						break;
					case BareMetalNetwork.VAR_dnsDomain:
						o2.setDnsDomain(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_dnsDomain + "=$" + num);
						num++;
						bParams.add(o2.sqlDnsDomain());
						break;
					case BareMetalNetwork.VAR_mtu:
						o2.setMtu(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_mtu + "=$" + num);
						num++;
						bParams.add(o2.sqlMtu());
						break;
					case BareMetalNetwork.VAR_sessionId:
						o2.setSessionId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_sessionId + "=$" + num);
						num++;
						bParams.add(o2.sqlSessionId());
						break;
					case BareMetalNetwork.VAR_projectId:
						o2.setProjectId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_projectId + "=$" + num);
						num++;
						bParams.add(o2.sqlProjectId());
						break;
					case BareMetalNetwork.VAR_userKey:
						o2.setUserKey(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_userKey + "=$" + num);
						num++;
						bParams.add(o2.sqlUserKey());
						break;
					case BareMetalNetwork.VAR_providerNetworkType:
						o2.setProviderNetworkType(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_providerNetworkType + "=$" + num);
						num++;
						bParams.add(o2.sqlProviderNetworkType());
						break;
					case BareMetalNetwork.VAR_providerPhysicalNetwork:
						o2.setProviderPhysicalNetwork(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_providerPhysicalNetwork + "=$" + num);
						num++;
						bParams.add(o2.sqlProviderPhysicalNetwork());
						break;
					case BareMetalNetwork.VAR_providerSegmentationId:
						o2.setProviderSegmentationId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_providerSegmentationId + "=$" + num);
						num++;
						bParams.add(o2.sqlProviderSegmentationId());
						break;
					case BareMetalNetwork.VAR_objectTitle:
						o2.setObjectTitle(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_objectTitle + "=$" + num);
						num++;
						bParams.add(o2.sqlObjectTitle());
						break;
					case BareMetalNetwork.VAR_qosPolicyId:
						o2.setQosPolicyId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_qosPolicyId + "=$" + num);
						num++;
						bParams.add(o2.sqlQosPolicyId());
						break;
					case BareMetalNetwork.VAR_displayPage:
						o2.setDisplayPage(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_displayPage + "=$" + num);
						num++;
						bParams.add(o2.sqlDisplayPage());
						break;
					case BareMetalNetwork.VAR_revisionNumber:
						o2.setRevisionNumber(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_revisionNumber + "=$" + num);
						num++;
						bParams.add(o2.sqlRevisionNumber());
						break;
					case BareMetalNetwork.VAR_status:
						o2.setStatus(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_status + "=$" + num);
						num++;
						bParams.add(o2.sqlStatus());
						break;
					case BareMetalNetwork.VAR_subnetIds:
						o2.setSubnetIds(jsonObject.getJsonArray(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_subnetIds + "=$" + num);
						num++;
						bParams.add(o2.sqlSubnetIds());
						break;
					case BareMetalNetwork.VAR_tags:
						o2.setTags(jsonObject.getJsonArray(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_tags + "=$" + num);
						num++;
						bParams.add(o2.sqlTags());
						break;
					case BareMetalNetwork.VAR_tenantId:
						o2.setTenantId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_tenantId + "=$" + num);
						num++;
						bParams.add(o2.sqlTenantId());
						break;
					case BareMetalNetwork.VAR_updatedAt:
						o2.setUpdatedAt(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_updatedAt + "=$" + num);
						num++;
						bParams.add(o2.sqlUpdatedAt());
						break;
					case BareMetalNetwork.VAR_isAdminStateUp:
						o2.setIsAdminStateUp(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_isAdminStateUp + "=$" + num);
						num++;
						bParams.add(o2.sqlIsAdminStateUp());
						break;
					case BareMetalNetwork.VAR_isDefault:
						o2.setIsDefault(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_isDefault + "=$" + num);
						num++;
						bParams.add(o2.sqlIsDefault());
						break;
					case BareMetalNetwork.VAR_isPortSecurityEnabled:
						o2.setIsPortSecurityEnabled(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_isPortSecurityEnabled + "=$" + num);
						num++;
						bParams.add(o2.sqlIsPortSecurityEnabled());
						break;
					case BareMetalNetwork.VAR_isRouterExternal:
						o2.setIsRouterExternal(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_isRouterExternal + "=$" + num);
						num++;
						bParams.add(o2.sqlIsRouterExternal());
						break;
					case BareMetalNetwork.VAR_isShared:
						o2.setIsShared(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_isShared + "=$" + num);
						num++;
						bParams.add(o2.sqlIsShared());
						break;
					case BareMetalNetwork.VAR_isVlanQueing:
						o2.setIsVlanQueing(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_isVlanQueing + "=$" + num);
						num++;
						bParams.add(o2.sqlIsVlanQueing());
						break;
					case BareMetalNetwork.VAR_isVlanTransparent:
						o2.setIsVlanTransparent(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_isVlanTransparent + "=$" + num);
						num++;
						bParams.add(o2.sqlIsVlanTransparent());
						break;
					case BareMetalNetwork.VAR_l2Adjacency:
						o2.setL2Adjacency(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_l2Adjacency + "=$" + num);
						num++;
						bParams.add(o2.sqlL2Adjacency());
						break;
					case BareMetalNetwork.VAR_locationCloud:
						o2.setLocationCloud(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_locationCloud + "=$" + num);
						num++;
						bParams.add(o2.sqlLocationCloud());
						break;
					case BareMetalNetwork.VAR_locationProjectDomainId:
						o2.setLocationProjectDomainId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_locationProjectDomainId + "=$" + num);
						num++;
						bParams.add(o2.sqlLocationProjectDomainId());
						break;
					case BareMetalNetwork.VAR_locationProjectDomainName:
						o2.setLocationProjectDomainName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_locationProjectDomainName + "=$" + num);
						num++;
						bParams.add(o2.sqlLocationProjectDomainName());
						break;
					case BareMetalNetwork.VAR_locationProjectId:
						o2.setLocationProjectId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_locationProjectId + "=$" + num);
						num++;
						bParams.add(o2.sqlLocationProjectId());
						break;
					case BareMetalNetwork.VAR_locationProjectName:
						o2.setLocationProjectName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_locationProjectName + "=$" + num);
						num++;
						bParams.add(o2.sqlLocationProjectName());
						break;
					case BareMetalNetwork.VAR_locationRegionName:
						o2.setLocationRegionName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_locationRegionName + "=$" + num);
						num++;
						bParams.add(o2.sqlLocationRegionName());
						break;
					case BareMetalNetwork.VAR_locationZone:
						o2.setLocationZone(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNetwork.VAR_locationZone + "=$" + num);
						num++;
						bParams.add(o2.sqlLocationZone());
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
						RuntimeException ex2 = new RuntimeException("value BareMetalNetwork failed", ex);
						LOG.error(String.format("relateBareMetalNetwork failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete(o2);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPOSTBareMetalNetwork failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPOSTBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPOSTBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200POSTBareMetalNetwork(BareMetalNetwork o) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			if(json == null) {
				String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
						String m = String.format("%s %s not found", "bare metal network", id);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200POSTBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// DELETE //

	@Override
	public void deleteBareMetalNetwork(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deleteBareMetalNetwork started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "DELETE"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNetworkList(siteRequest, false, true, true).onSuccess(listBareMetalNetwork -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listBareMetalNetwork.getRequest().getRows());
								apiRequest.setNumFound(listBareMetalNetwork.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listBareMetalNetwork.first());
								apiRequest.setPk(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());

								listDELETEBareMetalNetwork(apiRequest, listBareMetalNetwork).onSuccess(e -> {
									response200DELETEBareMetalNetwork(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deleteBareMetalNetwork succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deleteBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEBareMetalNetwork(ApiRequest apiRequest, SearchList<BareMetalNetwork> listBareMetalNetwork) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listBareMetalNetwork.getSiteRequest_(SiteRequest.class);
		listBareMetalNetwork.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			BareMetalNetwork o2 = jsonObject.mapTo(BareMetalNetwork.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deleteBareMetalNetworkFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEBareMetalNetwork failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listBareMetalNetwork.next().onSuccess(next -> {
				if(next) {
					listDELETEBareMetalNetwork(apiRequest, listBareMetalNetwork).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEBareMetalNetwork failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deleteBareMetalNetworkFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchBareMetalNetworkList(siteRequest, false, true, true).onSuccess(listBareMetalNetwork -> {
					try {
						BareMetalNetwork o = listBareMetalNetwork.first();
						if(o != null && listBareMetalNetwork.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getId()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getPk()).orElse(null));
							deleteBareMetalNetworkFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deleteBareMetalNetwork failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<BareMetalNetwork> deleteBareMetalNetworkFuture(BareMetalNetwork o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<BareMetalNetwork> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<BareMetalNetwork> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsBareMetalNetwork(siteRequest).onSuccess(a -> {
					sqlDELETEBareMetalNetwork(o).onSuccess(bareMetalNetwork -> {
						relateBareMetalNetwork(o).onSuccess(d -> {
							unindexBareMetalNetwork(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestBareMetalNetwork();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());
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
			}).compose(bareMetalNetwork -> {
				Promise<BareMetalNetwork> promise2 = Promise.promise();
				refreshBareMetalNetwork(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(bareMetalNetwork -> {
				promise.complete(bareMetalNetwork);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deleteBareMetalNetworkFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEBareMetalNetwork(BareMetalNetwork o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM BareMetalNetwork ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			BareMetalNetwork o2 = new BareMetalNetwork();
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
					RuntimeException ex2 = new RuntimeException("value BareMetalNetwork failed", ex);
					LOG.error(String.format("unrelateBareMetalNetwork failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEBareMetalNetwork failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEBareMetalNetwork(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
						String m = String.format("%s %s not found", "bare metal network", id);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PUTImport //

	@Override
	public void putimportBareMetalNetwork(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("putimportBareMetalNetwork started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "PUT"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						ApiRequest apiRequest = new ApiRequest();
						JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
						apiRequest.setRows(Long.valueOf(jsonArray.size()));
						apiRequest.setNumFound(Long.valueOf(jsonArray.size()));
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());
						varsBareMetalNetwork(siteRequest).onSuccess(d -> {
							listPUTImportBareMetalNetwork(apiRequest, siteRequest).onSuccess(e -> {
								response200PUTImportBareMetalNetwork(siteRequest).onSuccess(response -> {
									LOG.debug(String.format("putimportBareMetalNetwork succeeded. "));
									eventHandler.handle(Future.succeededFuture(response));
								}).onFailure(ex -> {
									LOG.error(String.format("putimportBareMetalNetwork failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							}).onFailure(ex -> {
								LOG.error(String.format("putimportBareMetalNetwork failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("putimportBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("putimportBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("putimportBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPUTImportBareMetalNetwork(ApiRequest apiRequest, SiteRequest siteRequest) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				futures.add(Future.future(promise1 -> {
					JsonObject params = new JsonObject();
					params.put("body", obj);
					params.put("path", new JsonObject());
					params.put("cookie", siteRequest.getServiceRequest().getParams().getJsonObject("cookie"));
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
					eventBus.request(BareMetalNetwork.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportBareMetalNetworkFuture")).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPUTImportBareMetalNetwork failed. "), ex);
						promise1.fail(ex);
					});
				}));
			});
			CompositeFuture.all(futures).onSuccess(a -> {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("listPUTImportBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("listPUTImportBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public void putimportBareMetalNetworkFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				String id = Optional.ofNullable(body.getString(BareMetalNetwork.VAR_id)).orElse(body.getString(BareMetalNetwork.VAR_solrId));
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}

				SearchList<BareMetalNetwork> searchList = new SearchList<BareMetalNetwork>();
				searchList.setStore(true);
				searchList.q("*:*");
				searchList.setC(BareMetalNetwork.class);
				searchList.fq("archived_docvalues_boolean:false");
				searchList.fq("id_docvalues_string:" + SearchTool.escapeQueryChars(id));
				searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
					try {
						if(searchList.size() >= 1) {
							BareMetalNetwork o = searchList.getList().stream().findFirst().orElse(null);
							BareMetalNetwork o2 = new BareMetalNetwork();
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
									if(!StringUtils.containsAny(f, "id", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
										body2.put("set" + StringUtils.capitalize(f), bodyVal);
								}
							}
							for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
								if(!body.fieldNames().contains(f)) {
									if(!StringUtils.containsAny(f, "id", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
										body2.putNull("set" + StringUtils.capitalize(f));
								}
							}
							if(searchList.size() == 1) {
								apiRequest.setOriginal(o);
								apiRequest.setId(o.getId());
								apiRequest.setPk(o.getPk());
							}
							siteRequest.setJsonObject(body2);
							patchBareMetalNetworkFuture(o, true).onSuccess(b -> {
								LOG.debug("Import BareMetalNetwork {} succeeded, modified BareMetalNetwork. ", body.getValue(BareMetalNetwork.VAR_id));
								eventHandler.handle(Future.succeededFuture());
							}).onFailure(ex -> {
								LOG.error(String.format("putimportBareMetalNetworkFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							postBareMetalNetworkFuture(siteRequest, true).onSuccess(b -> {
								LOG.debug("Import BareMetalNetwork {} succeeded, created new BareMetalNetwork. ", body.getValue(BareMetalNetwork.VAR_id));
								eventHandler.handle(Future.succeededFuture());
							}).onFailure(ex -> {
								LOG.error(String.format("putimportBareMetalNetworkFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							});
						}
					} catch(Exception ex) {
						LOG.error(String.format("putimportBareMetalNetworkFuture failed. "), ex);
						eventHandler.handle(Future.failedFuture(ex));
					}
				}).onFailure(ex -> {
					LOG.error(String.format("putimportBareMetalNetworkFuture failed. "), ex);
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Exception ex) {
				LOG.error(String.format("putimportBareMetalNetworkFuture failed. "), ex);
				eventHandler.handle(Future.failedFuture(ex));
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("putimportBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200PUTImportBareMetalNetwork(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
						String m = String.format("%s %s not found", "bare metal network", id);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PUTImportBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// SearchPage //

	@Override
	public void searchpageBareMetalNetwork(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNetworkList(siteRequest, false, true, false).onSuccess(listBareMetalNetwork -> {
							response200SearchPageBareMetalNetwork(listBareMetalNetwork).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchpageBareMetalNetwork succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchpageBareMetalNetwork failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchpageBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchpageBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchpageBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("searchpageBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void searchpageBareMetalNetworkPageInit(BareMetalNetworkPage page, SearchList<BareMetalNetwork> listBareMetalNetwork) {
	}

	public String templateSearchPageBareMetalNetwork(ServiceRequest serviceRequest) {
		return "en-us/search/bare-metal-network/BareMetalNetworkSearchPage.htm";
	}
	public Future<ServiceResponse> response200SearchPageBareMetalNetwork(SearchList<BareMetalNetwork> listBareMetalNetwork) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listBareMetalNetwork.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateSearchPageBareMetalNetwork(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			BareMetalNetworkPage page = new BareMetalNetworkPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listBareMetalNetwork.size() >= 1)
				siteRequest.setRequestPk(listBareMetalNetwork.get(0).getPk());
			page.setSearchListBareMetalNetwork_(listBareMetalNetwork);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepBareMetalNetworkPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200SearchPageBareMetalNetwork failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchPageBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchPageBareMetalNetwork(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchPageBareMetalNetwork(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// EditPage //

	@Override
	public void editpageBareMetalNetwork(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "GET"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNetworkList(siteRequest, false, true, false).onSuccess(listBareMetalNetwork -> {
							response200EditPageBareMetalNetwork(listBareMetalNetwork).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("editpageBareMetalNetwork succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("editpageBareMetalNetwork failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("editpageBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("editpageBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("editpageBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("editpageBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void editpageBareMetalNetworkPageInit(BareMetalNetworkPage page, SearchList<BareMetalNetwork> listBareMetalNetwork) {
	}

	public String templateEditPageBareMetalNetwork(ServiceRequest serviceRequest) {
		return "en-us/edit/bare-metal-network/BareMetalNetworkEditPage.htm";
	}
	public Future<ServiceResponse> response200EditPageBareMetalNetwork(SearchList<BareMetalNetwork> listBareMetalNetwork) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listBareMetalNetwork.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateEditPageBareMetalNetwork(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			BareMetalNetworkPage page = new BareMetalNetworkPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listBareMetalNetwork.size() >= 1)
				siteRequest.setRequestPk(listBareMetalNetwork.get(0).getPk());
			page.setSearchListBareMetalNetwork_(listBareMetalNetwork);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepBareMetalNetworkPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200EditPageBareMetalNetwork failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200EditPageBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotEditPageBareMetalNetwork(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotEditPageBareMetalNetwork(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// DELETEFilter //

	@Override
	public void deletefilterBareMetalNetwork(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deletefilterBareMetalNetwork started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, "PUT"));
			if(id != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNetwork.CLASS_SIMPLE_NAME, id, "DELETE"));
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
					.sendForm(form)
					.expecting(HttpResponseExpectation.SC_OK)
			.onComplete(authorizationDecisionResponse -> {
				try {
					HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
					JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNetworkList(siteRequest, false, true, true).onSuccess(listBareMetalNetwork -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listBareMetalNetwork.getRequest().getRows());
								apiRequest.setNumFound(listBareMetalNetwork.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listBareMetalNetwork.first());
								apiRequest.setPk(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());

								listDELETEFilterBareMetalNetwork(apiRequest, listBareMetalNetwork).onSuccess(e -> {
									response200DELETEFilterBareMetalNetwork(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deletefilterBareMetalNetwork succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deletefilterBareMetalNetwork failed. ", ex2));
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
				LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEFilterBareMetalNetwork(ApiRequest apiRequest, SearchList<BareMetalNetwork> listBareMetalNetwork) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listBareMetalNetwork.getSiteRequest_(SiteRequest.class);
		listBareMetalNetwork.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			BareMetalNetwork o2 = jsonObject.mapTo(BareMetalNetwork.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deletefilterBareMetalNetworkFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEFilterBareMetalNetwork failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listBareMetalNetwork.next().onSuccess(next -> {
				if(next) {
					listDELETEFilterBareMetalNetwork(apiRequest, listBareMetalNetwork).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEFilterBareMetalNetwork failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEFilterBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEFilterBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deletefilterBareMetalNetworkFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.addScopes("GET");
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchBareMetalNetworkList(siteRequest, false, true, true).onSuccess(listBareMetalNetwork -> {
					try {
						BareMetalNetwork o = listBareMetalNetwork.first();
						if(o != null && listBareMetalNetwork.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getId()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listBareMetalNetwork.first()).map(o2 -> o2.getPk()).orElse(null));
							deletefilterBareMetalNetworkFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deletefilterBareMetalNetwork failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<BareMetalNetwork> deletefilterBareMetalNetworkFuture(BareMetalNetwork o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<BareMetalNetwork> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<BareMetalNetwork> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsBareMetalNetwork(siteRequest).onSuccess(a -> {
					sqlDELETEFilterBareMetalNetwork(o).onSuccess(bareMetalNetwork -> {
						relateBareMetalNetwork(o).onSuccess(d -> {
							unindexBareMetalNetwork(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestBareMetalNetwork();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketBareMetalNetwork", JsonObject.mapFrom(apiRequest).toString());
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
			}).compose(bareMetalNetwork -> {
				Promise<BareMetalNetwork> promise2 = Promise.promise();
				refreshBareMetalNetwork(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(bareMetalNetwork -> {
				promise.complete(bareMetalNetwork);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deletefilterBareMetalNetworkFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEFilterBareMetalNetwork(BareMetalNetwork o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM BareMetalNetwork ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			BareMetalNetwork o2 = new BareMetalNetwork();
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
					RuntimeException ex2 = new RuntimeException("value BareMetalNetwork failed", ex);
					LOG.error(String.format("unrelateBareMetalNetwork failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEFilterBareMetalNetwork failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEFilterBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEFilterBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEFilterBareMetalNetwork(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String id = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("id");
						String m = String.format("%s %s not found", "bare metal network", id);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEFilterBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// General //

	public Future<BareMetalNetwork> createBareMetalNetwork(SiteRequest siteRequest) {
		Promise<BareMetalNetwork> promise = Promise.promise();
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long userKey = siteRequest.getUserKey();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

			sqlConnection.preparedQuery("INSERT INTO BareMetalNetwork(created, userKey) VALUES($1, $2) RETURNING pk")
					.collecting(Collectors.toList())
					.execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
				Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				BareMetalNetwork o = new BareMetalNetwork();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				promise.complete(o);
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error("createBareMetalNetwork failed. ", ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("createBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public void searchBareMetalNetworkQ(SearchList<BareMetalNetwork> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
		}
	}

	public String searchBareMetalNetworkFq(SearchList<BareMetalNetwork> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		if(StringUtils.startsWith(valueIndexed, "[")) {
			String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
			if(fqs.length != 2)
				throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
			String fq1 = fqs[0].equals("*") ? fqs[0] : BareMetalNetwork.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
			String fq2 = fqs[1].equals("*") ? fqs[1] : BareMetalNetwork.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
			 return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
		} else {
			return varIndexed + ":" + SearchTool.escapeQueryChars(BareMetalNetwork.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
		}
	}

	public void searchBareMetalNetworkSort(SearchList<BareMetalNetwork> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.sort(varIndexed, valueIndexed);
	}

	public void searchBareMetalNetworkRows(SearchList<BareMetalNetwork> searchList, Long valueRows) {
			searchList.rows(valueRows != null ? valueRows : 10L);
	}

	public void searchBareMetalNetworkStart(SearchList<BareMetalNetwork> searchList, Long valueStart) {
		searchList.start(valueStart);
	}

	public void searchBareMetalNetworkVar(SearchList<BareMetalNetwork> searchList, String var, String value) {
		searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
	}

	public void searchBareMetalNetworkUri(SearchList<BareMetalNetwork> searchList) {
	}

	public Future<ServiceResponse> varsBareMetalNetwork(SiteRequest siteRequest) {
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
					LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
					promise.fail(ex);
				}
			});
			promise.complete();
		} catch(Exception ex) {
			LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<SearchList<BareMetalNetwork>> searchBareMetalNetworkList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify) {
		Promise<SearchList<BareMetalNetwork>> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();
			String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<BareMetalNetwork> searchList = new SearchList<BareMetalNetwork>();
			String facetRange = null;
			Date facetRangeStart = null;
			Date facetRangeEnd = null;
			String facetRangeGap = null;
			String statsField = null;
			String statsFieldIndexed = null;
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.q("*:*");
			searchList.setC(BareMetalNetwork.class);
			searchList.setSiteRequest_(siteRequest);
			searchList.facetMinCount(1);
			if(entityList != null) {
				for(String v : entityList) {
					searchList.fl(BareMetalNetwork.varIndexedBareMetalNetwork(v));
				}
			}

			String id = serviceRequest.getParams().getJsonObject("path").getString("id");
			if(id != null && NumberUtils.isCreatable(id)) {
				searchList.fq("(_docvalues_string:" + SearchTool.escapeQueryChars(id) + " OR id_docvalues_string:" + SearchTool.escapeQueryChars(id) + ")");
			} else if(id != null) {
				searchList.fq("id_docvalues_string:" + SearchTool.escapeQueryChars(id));
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
								varsIndexed[i] = BareMetalNetwork.varIndexedBareMetalNetwork(entityVar);
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
									varIndexed = BareMetalNetwork.varIndexedBareMetalNetwork(entityVar);
									String entityQ = searchBareMetalNetworkFq(searchList, entityVar, valueIndexed, varIndexed);
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
									varIndexed = BareMetalNetwork.varIndexedBareMetalNetwork(entityVar);
									String entityFq = searchBareMetalNetworkFq(searchList, entityVar, valueIndexed, varIndexed);
									mFq.appendReplacement(sb, entityFq);
								}
								if(!sb.isEmpty()) {
									mFq.appendTail(sb);
									searchList.fq(sb.toString());
								}
							} else if(paramName.equals("sort")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = BareMetalNetwork.varIndexedBareMetalNetwork(entityVar);
								searchBareMetalNetworkSort(searchList, entityVar, valueIndexed, varIndexed);
							} else if(paramName.equals("start")) {
								valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchBareMetalNetworkStart(searchList, valueStart);
							} else if(paramName.equals("rows")) {
								valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchBareMetalNetworkRows(searchList, valueRows);
							} else if(paramName.equals("stats")) {
								searchList.stats((Boolean)paramObject);
							} else if(paramName.equals("stats.field")) {
								Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								if(mStats.find()) {
									String solrLocalParams = mStats.group(1);
									entityVar = mStats.group(2).trim();
									varIndexed = BareMetalNetwork.varIndexedBareMetalNetwork(entityVar);
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
									varIndexed = BareMetalNetwork.varIndexedBareMetalNetwork(entityVar);
									searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									facetRange = entityVar;
								}
							} else if(paramName.equals("facet.field")) {
								entityVar = (String)paramObject;
								varIndexed = BareMetalNetwork.varIndexedBareMetalNetwork(entityVar);
								if(varIndexed != null)
									searchList.facetField(varIndexed);
							} else if(paramName.equals("var")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								searchBareMetalNetworkVar(searchList, entityVar, valueIndexed);
							} else if(paramName.equals("cursorMark")) {
								valueCursorMark = (String)paramObject;
								searchList.cursorMark((String)paramObject);
							}
						}
						searchBareMetalNetworkUri(searchList);
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
			searchBareMetalNetwork2(siteRequest, populate, store, modify, searchList);
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
						LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete(searchList);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("searchBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void searchBareMetalNetwork2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<BareMetalNetwork> searchList) {
	}

	public Future<Void> persistBareMetalNetwork(BareMetalNetwork o, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.preparedQuery("SELECT * FROM BareMetalNetwork WHERE pk=$1")
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
									LOG.error(String.format("persistBareMetalNetwork failed. "), e);
								}
							}
						}
					}
					o.promiseDeepForClass(siteRequest).onSuccess(a -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("persistBareMetalNetwork failed. "), ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error(String.format("persistBareMetalNetwork failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("persistBareMetalNetwork failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("persistBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> relateBareMetalNetwork(BareMetalNetwork o) {
		Promise<Void> promise = Promise.promise();
			promise.complete();
		return promise.future();
	}

	public String searchVar(String varIndexed) {
		return BareMetalNetwork.searchVarBareMetalNetwork(varIndexed);
	}

	@Override
	public String getClassApiAddress() {
		return BareMetalNetwork.CLASS_API_ADDRESS_BareMetalNetwork;
	}

	public Future<BareMetalNetwork> indexBareMetalNetwork(BareMetalNetwork o) {
		Promise<BareMetalNetwork> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = new JsonObject();
			JsonObject add = new JsonObject();
			json.put("add", add);
			JsonObject doc = new JsonObject();
			add.put("doc", doc);
			o.indexBareMetalNetwork(doc);
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
				LOG.error(String.format("indexBareMetalNetwork failed. "), new RuntimeException(ex));
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("indexBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<BareMetalNetwork> unindexBareMetalNetwork(BareMetalNetwork o) {
		Promise<BareMetalNetwork> promise = Promise.promise();
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
					LOG.error(String.format("unindexBareMetalNetwork failed. "), new RuntimeException(ex));
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("unindexBareMetalNetwork failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("unindexBareMetalNetwork failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> refreshBareMetalNetwork(BareMetalNetwork o) {
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
					params.put("cookie", siteRequest.getServiceRequest().getParams().getJsonObject("cookie"));
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
					eventBus.request(BareMetalNetwork.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchBareMetalNetworkFuture")).onSuccess(c -> {
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
			LOG.error(String.format("refreshBareMetalNetwork failed. "), ex);
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
			BareMetalNetwork page = new BareMetalNetwork();
			page.setSiteRequest_((SiteRequest)siteRequest);

			page.persistForClass(BareMetalNetwork.VAR_id, BareMetalNetwork.staticSetId(siteRequest2, (String)result.get(BareMetalNetwork.VAR_id)));
			page.persistForClass(BareMetalNetwork.VAR_name, BareMetalNetwork.staticSetName(siteRequest2, (String)result.get(BareMetalNetwork.VAR_name)));
			page.persistForClass(BareMetalNetwork.VAR_created, BareMetalNetwork.staticSetCreated(siteRequest2, (String)result.get(BareMetalNetwork.VAR_created)));
			page.persistForClass(BareMetalNetwork.VAR_description, BareMetalNetwork.staticSetDescription(siteRequest2, (String)result.get(BareMetalNetwork.VAR_description)));
			page.persistForClass(BareMetalNetwork.VAR_availabilityZoneHints, BareMetalNetwork.staticSetAvailabilityZoneHints(siteRequest2, (String)result.get(BareMetalNetwork.VAR_availabilityZoneHints)));
			page.persistForClass(BareMetalNetwork.VAR_archived, BareMetalNetwork.staticSetArchived(siteRequest2, (String)result.get(BareMetalNetwork.VAR_archived)));
			page.persistForClass(BareMetalNetwork.VAR_availabilityZones, BareMetalNetwork.staticSetAvailabilityZones(siteRequest2, (String)result.get(BareMetalNetwork.VAR_availabilityZones)));
			page.persistForClass(BareMetalNetwork.VAR_createdAt, BareMetalNetwork.staticSetCreatedAt(siteRequest2, (String)result.get(BareMetalNetwork.VAR_createdAt)));
			page.persistForClass(BareMetalNetwork.VAR_dnsDomain, BareMetalNetwork.staticSetDnsDomain(siteRequest2, (String)result.get(BareMetalNetwork.VAR_dnsDomain)));
			page.persistForClass(BareMetalNetwork.VAR_mtu, BareMetalNetwork.staticSetMtu(siteRequest2, (String)result.get(BareMetalNetwork.VAR_mtu)));
			page.persistForClass(BareMetalNetwork.VAR_sessionId, BareMetalNetwork.staticSetSessionId(siteRequest2, (String)result.get(BareMetalNetwork.VAR_sessionId)));
			page.persistForClass(BareMetalNetwork.VAR_projectId, BareMetalNetwork.staticSetProjectId(siteRequest2, (String)result.get(BareMetalNetwork.VAR_projectId)));
			page.persistForClass(BareMetalNetwork.VAR_userKey, BareMetalNetwork.staticSetUserKey(siteRequest2, (String)result.get(BareMetalNetwork.VAR_userKey)));
			page.persistForClass(BareMetalNetwork.VAR_providerNetworkType, BareMetalNetwork.staticSetProviderNetworkType(siteRequest2, (String)result.get(BareMetalNetwork.VAR_providerNetworkType)));
			page.persistForClass(BareMetalNetwork.VAR_providerPhysicalNetwork, BareMetalNetwork.staticSetProviderPhysicalNetwork(siteRequest2, (String)result.get(BareMetalNetwork.VAR_providerPhysicalNetwork)));
			page.persistForClass(BareMetalNetwork.VAR_providerSegmentationId, BareMetalNetwork.staticSetProviderSegmentationId(siteRequest2, (String)result.get(BareMetalNetwork.VAR_providerSegmentationId)));
			page.persistForClass(BareMetalNetwork.VAR_objectTitle, BareMetalNetwork.staticSetObjectTitle(siteRequest2, (String)result.get(BareMetalNetwork.VAR_objectTitle)));
			page.persistForClass(BareMetalNetwork.VAR_qosPolicyId, BareMetalNetwork.staticSetQosPolicyId(siteRequest2, (String)result.get(BareMetalNetwork.VAR_qosPolicyId)));
			page.persistForClass(BareMetalNetwork.VAR_displayPage, BareMetalNetwork.staticSetDisplayPage(siteRequest2, (String)result.get(BareMetalNetwork.VAR_displayPage)));
			page.persistForClass(BareMetalNetwork.VAR_revisionNumber, BareMetalNetwork.staticSetRevisionNumber(siteRequest2, (String)result.get(BareMetalNetwork.VAR_revisionNumber)));
			page.persistForClass(BareMetalNetwork.VAR_status, BareMetalNetwork.staticSetStatus(siteRequest2, (String)result.get(BareMetalNetwork.VAR_status)));
			page.persistForClass(BareMetalNetwork.VAR_subnetIds, BareMetalNetwork.staticSetSubnetIds(siteRequest2, (String)result.get(BareMetalNetwork.VAR_subnetIds)));
			page.persistForClass(BareMetalNetwork.VAR_tags, BareMetalNetwork.staticSetTags(siteRequest2, (String)result.get(BareMetalNetwork.VAR_tags)));
			page.persistForClass(BareMetalNetwork.VAR_tenantId, BareMetalNetwork.staticSetTenantId(siteRequest2, (String)result.get(BareMetalNetwork.VAR_tenantId)));
			page.persistForClass(BareMetalNetwork.VAR_updatedAt, BareMetalNetwork.staticSetUpdatedAt(siteRequest2, (String)result.get(BareMetalNetwork.VAR_updatedAt)));
			page.persistForClass(BareMetalNetwork.VAR_isAdminStateUp, BareMetalNetwork.staticSetIsAdminStateUp(siteRequest2, (String)result.get(BareMetalNetwork.VAR_isAdminStateUp)));
			page.persistForClass(BareMetalNetwork.VAR_isDefault, BareMetalNetwork.staticSetIsDefault(siteRequest2, (String)result.get(BareMetalNetwork.VAR_isDefault)));
			page.persistForClass(BareMetalNetwork.VAR_isPortSecurityEnabled, BareMetalNetwork.staticSetIsPortSecurityEnabled(siteRequest2, (String)result.get(BareMetalNetwork.VAR_isPortSecurityEnabled)));
			page.persistForClass(BareMetalNetwork.VAR_isRouterExternal, BareMetalNetwork.staticSetIsRouterExternal(siteRequest2, (String)result.get(BareMetalNetwork.VAR_isRouterExternal)));
			page.persistForClass(BareMetalNetwork.VAR_isShared, BareMetalNetwork.staticSetIsShared(siteRequest2, (String)result.get(BareMetalNetwork.VAR_isShared)));
			page.persistForClass(BareMetalNetwork.VAR_isVlanQueing, BareMetalNetwork.staticSetIsVlanQueing(siteRequest2, (String)result.get(BareMetalNetwork.VAR_isVlanQueing)));
			page.persistForClass(BareMetalNetwork.VAR_isVlanTransparent, BareMetalNetwork.staticSetIsVlanTransparent(siteRequest2, (String)result.get(BareMetalNetwork.VAR_isVlanTransparent)));
			page.persistForClass(BareMetalNetwork.VAR_l2Adjacency, BareMetalNetwork.staticSetL2Adjacency(siteRequest2, (String)result.get(BareMetalNetwork.VAR_l2Adjacency)));
			page.persistForClass(BareMetalNetwork.VAR_locationCloud, BareMetalNetwork.staticSetLocationCloud(siteRequest2, (String)result.get(BareMetalNetwork.VAR_locationCloud)));
			page.persistForClass(BareMetalNetwork.VAR_locationProjectDomainId, BareMetalNetwork.staticSetLocationProjectDomainId(siteRequest2, (String)result.get(BareMetalNetwork.VAR_locationProjectDomainId)));
			page.persistForClass(BareMetalNetwork.VAR_locationProjectDomainName, BareMetalNetwork.staticSetLocationProjectDomainName(siteRequest2, (String)result.get(BareMetalNetwork.VAR_locationProjectDomainName)));
			page.persistForClass(BareMetalNetwork.VAR_locationProjectId, BareMetalNetwork.staticSetLocationProjectId(siteRequest2, (String)result.get(BareMetalNetwork.VAR_locationProjectId)));
			page.persistForClass(BareMetalNetwork.VAR_locationProjectName, BareMetalNetwork.staticSetLocationProjectName(siteRequest2, (String)result.get(BareMetalNetwork.VAR_locationProjectName)));
			page.persistForClass(BareMetalNetwork.VAR_locationRegionName, BareMetalNetwork.staticSetLocationRegionName(siteRequest2, (String)result.get(BareMetalNetwork.VAR_locationRegionName)));
			page.persistForClass(BareMetalNetwork.VAR_locationZone, BareMetalNetwork.staticSetLocationZone(siteRequest2, (String)result.get(BareMetalNetwork.VAR_locationZone)));

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
