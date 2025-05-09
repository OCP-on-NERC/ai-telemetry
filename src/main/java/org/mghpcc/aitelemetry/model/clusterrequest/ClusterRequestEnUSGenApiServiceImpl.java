package org.mghpcc.aitelemetry.model.clusterrequest;

import org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateEnUSApiServiceImpl;
import org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate;
import org.mghpcc.aitelemetry.user.SiteUserEnUSApiServiceImpl;
import org.mghpcc.aitelemetry.user.SiteUser;
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
import org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestPage;


/**
 * Translate: false
 * Generated: true
 **/
public class ClusterRequestEnUSGenApiServiceImpl extends BaseApiServiceImpl implements ClusterRequestEnUSGenApiService {

	protected static final Logger LOG = LoggerFactory.getLogger(ClusterRequestEnUSGenApiServiceImpl.class);

	// Search //

	@Override
	public void searchClusterRequest(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "GET"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						searchClusterRequestList(siteRequest, false, true, false).onSuccess(listClusterRequest -> {
							response200SearchClusterRequest(listClusterRequest).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchClusterRequest succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchClusterRequest failed. ", ex2));
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
				LOG.error(String.format("searchClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200SearchClusterRequest(SearchList<ClusterRequest> listClusterRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listClusterRequest.getSiteRequest_(SiteRequest.class);
			List<String> fls = listClusterRequest.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listClusterRequest.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = ClusterRequest.varIndexedClusterRequest(fieldName);
						if(v != null)
							fieldNames.add(ClusterRequest.varIndexedClusterRequest(fieldName));
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
			response200Search(listClusterRequest.getRequest(), listClusterRequest.getResponse(), json);
			if(json == null) {
				String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
						String m = String.format("%s %s not found", "OpenShift cluster request", name);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchClusterRequest(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchClusterRequest(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// GET //

	@Override
	public void getClusterRequest(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "GET"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						searchClusterRequestList(siteRequest, false, true, false).onSuccess(listClusterRequest -> {
							response200GETClusterRequest(listClusterRequest).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("getClusterRequest succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("getClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("getClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("getClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("getClusterRequest failed. ", ex2));
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
				LOG.error(String.format("getClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200GETClusterRequest(SearchList<ClusterRequest> listClusterRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listClusterRequest.getSiteRequest_(SiteRequest.class);
			JsonObject json = JsonObject.mapFrom(listClusterRequest.getList().stream().findFirst().orElse(null));
			if(json == null) {
				String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
						String m = String.format("%s %s not found", "OpenShift cluster request", name);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200GETClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PATCH //

	@Override
	public void patchClusterRequest(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("patchClusterRequest started. "));
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "PATCH"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						searchClusterRequestList(siteRequest, false, true, true).onSuccess(listClusterRequest -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listClusterRequest.getRequest().getRows());
								apiRequest.setNumFound(listClusterRequest.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listClusterRequest.first());
								apiRequest.setId(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getName().toString()).orElse(null));
								apiRequest.setPk(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());

								listPATCHClusterRequest(apiRequest, listClusterRequest).onSuccess(e -> {
									response200PATCHClusterRequest(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("patchClusterRequest succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("patchClusterRequest failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("patchClusterRequest failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("patchClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("patchClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("patchClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("patchClusterRequest failed. ", ex2));
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
				LOG.error(String.format("patchClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPATCHClusterRequest(ApiRequest apiRequest, SearchList<ClusterRequest> listClusterRequest) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listClusterRequest.getSiteRequest_(SiteRequest.class);
		listClusterRequest.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			ClusterRequest o2 = jsonObject.mapTo(ClusterRequest.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				patchClusterRequestFuture(o2, false).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listPATCHClusterRequest failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listClusterRequest.next().onSuccess(next -> {
				if(next) {
					listPATCHClusterRequest(apiRequest, listClusterRequest).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPATCHClusterRequest failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listPATCHClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listPATCHClusterRequest failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void patchClusterRequestFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
					scopes.stream().map(v -> v.toString()).forEach(scope -> {
						siteRequest.addScopes(scope);
					});
				});
				searchClusterRequestList(siteRequest, false, true, true).onSuccess(listClusterRequest -> {
					try {
						ClusterRequest o = listClusterRequest.first();
						if(o != null && listClusterRequest.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getName().toString()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getPk()).orElse(null));
							JsonObject jsonObject = JsonObject.mapFrom(o);
							ClusterRequest o2 = jsonObject.mapTo(ClusterRequest.class);
							o2.setSiteRequest_(siteRequest);
							patchClusterRequestFuture(o2, false).onSuccess(o3 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("patchClusterRequest failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("patchClusterRequest failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("patchClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("patchClusterRequest failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<ClusterRequest> patchClusterRequestFuture(ClusterRequest o, Boolean inheritPrimaryKey) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<ClusterRequest> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<ClusterRequest> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsClusterRequest(siteRequest).onSuccess(a -> {
					sqlPATCHClusterRequest(o, inheritPrimaryKey).onSuccess(clusterRequest -> {
						persistClusterRequest(clusterRequest, true).onSuccess(c -> {
							relateClusterRequest(clusterRequest).onSuccess(d -> {
								indexClusterRequest(clusterRequest).onSuccess(o2 -> {
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
											o2.apiRequestClusterRequest();
											if(apiRequest.getVars().size() > 0)
												eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());
										}
									}
									promise1.complete(clusterRequest);
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
			}).compose(clusterRequest -> {
				Promise<ClusterRequest> promise2 = Promise.promise();
				refreshClusterRequest(clusterRequest).onSuccess(a -> {
					promise2.complete(clusterRequest);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(clusterRequest -> {
				promise.complete(clusterRequest);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("patchClusterRequestFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ClusterRequest> sqlPATCHClusterRequest(ClusterRequest o, Boolean inheritPrimaryKey) {
		Promise<ClusterRequest> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE ClusterRequest SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			ClusterRequest o2 = new ClusterRequest();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			for(String entityVar : methodNames) {
				switch(entityVar) {
					case "setName":
							o2.setName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ClusterRequest.VAR_name + "=$" + num);
							num++;
							bParams.add(o2.sqlName());
						break;
					case "setClusterTemplateTitle":
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								search(siteRequest).query(ClusterTemplate.varIndexedClusterTemplate(ClusterTemplate.VAR_title), ClusterTemplate.varIndexedClusterTemplate(ClusterTemplate.VAR_pk), ClusterTemplate.class, val, inheritPrimaryKey).onSuccess(pk2 -> {
									if(!pks.contains(pk2)) {
										pks.add(pk2);
										classes.add("ClusterTemplate");
									}
									sql(siteRequest).update(ClusterRequest.class, pk).set(ClusterRequest.VAR_clusterTemplateTitle, ClusterTemplate.class, pk2, val).onSuccess(a -> {
										promise2.complete();
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
					case "removeClusterTemplateTitle":
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(pk2 -> {
							futures2.add(Future.future(promise2 -> {
								sql(siteRequest).update(ClusterRequest.class, pk).setToNull(ClusterRequest.VAR_clusterTemplateTitle, ClusterTemplate.class, null).onSuccess(a -> {
									promise2.complete();
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
					case "setCreated":
							o2.setCreated(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ClusterRequest.VAR_created + "=$" + num);
							num++;
							bParams.add(o2.sqlCreated());
						break;
					case "setUserId":
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								search(siteRequest).query(SiteUser.varIndexedSiteUser(SiteUser.VAR_userId), SiteUser.varIndexedSiteUser(SiteUser.VAR_pk), SiteUser.class, val, inheritPrimaryKey).onSuccess(pk2 -> {
									if(!pks.contains(pk2)) {
										pks.add(pk2);
										classes.add("SiteUser");
									}
									sql(siteRequest).update(ClusterRequest.class, pk).set(ClusterRequest.VAR_userId, SiteUser.class, pk2, val).onSuccess(a -> {
										promise2.complete();
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
					case "removeUserId":
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(pk2 -> {
							futures2.add(Future.future(promise2 -> {
								sql(siteRequest).update(ClusterRequest.class, pk).setToNull(ClusterRequest.VAR_userId, SiteUser.class, null).onSuccess(a -> {
									promise2.complete();
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
					case "setArchived":
							o2.setArchived(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ClusterRequest.VAR_archived + "=$" + num);
							num++;
							bParams.add(o2.sqlArchived());
						break;
					case "setSessionId":
							o2.setSessionId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ClusterRequest.VAR_sessionId + "=$" + num);
							num++;
							bParams.add(o2.sqlSessionId());
						break;
					case "setUserKey":
							o2.setUserKey(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ClusterRequest.VAR_userKey + "=$" + num);
							num++;
							bParams.add(o2.sqlUserKey());
						break;
					case "setObjectTitle":
							o2.setObjectTitle(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ClusterRequest.VAR_objectTitle + "=$" + num);
							num++;
							bParams.add(o2.sqlObjectTitle());
						break;
					case "setDisplayPage":
							o2.setDisplayPage(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ClusterRequest.VAR_displayPage + "=$" + num);
							num++;
							bParams.add(o2.sqlDisplayPage());
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
						RuntimeException ex2 = new RuntimeException("value ClusterRequest failed", ex);
						LOG.error(String.format("relateClusterRequest failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					ClusterRequest o3 = new ClusterRequest();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					promise.complete(o3);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPATCHClusterRequest failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPATCHClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPATCHClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200PATCHClusterRequest(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
						String m = String.format("%s %s not found", "OpenShift cluster request", name);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PATCHClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// POST //

	@Override
	public void postClusterRequest(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("postClusterRequest started. "));
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "POST"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1L);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());
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
						eventBus.request(ClusterRequest.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postClusterRequestFuture")).onSuccess(a -> {
							JsonObject responseMessage = (JsonObject)a.body();
							JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
							apiRequest.setPk(Long.parseLong(responseBody.getString("pk")));
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
							LOG.debug(String.format("postClusterRequest succeeded. "));
						}).onFailure(ex -> {
							LOG.error(String.format("postClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("postClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postClusterRequest failed. ", ex2));
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
				LOG.error(String.format("postClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	@Override
	public void postClusterRequestFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}
				postClusterRequestFuture(siteRequest, false).onSuccess(o -> {
					eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
				}).onFailure(ex -> {
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Throwable ex) {
				LOG.error(String.format("postClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postClusterRequest failed. ", ex2));
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
				LOG.error(String.format("postClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ClusterRequest> postClusterRequestFuture(SiteRequest siteRequest, Boolean name) {
		Promise<ClusterRequest> promise = Promise.promise();

		try {
			pgPool.withTransaction(sqlConnection -> {
				Promise<ClusterRequest> promise1 = Promise.promise();
				siteRequest.setSqlConnection(sqlConnection);
				varsClusterRequest(siteRequest).onSuccess(a -> {
					createClusterRequest(siteRequest).onSuccess(clusterRequest -> {
						sqlPOSTClusterRequest(clusterRequest, name).onSuccess(b -> {
							persistClusterRequest(clusterRequest, false).onSuccess(c -> {
								relateClusterRequest(clusterRequest).onSuccess(d -> {
									indexClusterRequest(clusterRequest).onSuccess(o2 -> {
										promise1.complete(clusterRequest);
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
			}).compose(clusterRequest -> {
				Promise<ClusterRequest> promise2 = Promise.promise();
				refreshClusterRequest(clusterRequest).onSuccess(a -> {
					try {
						ApiRequest apiRequest = siteRequest.getApiRequest_();
						if(apiRequest != null) {
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							clusterRequest.apiRequestClusterRequest();
							eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());
						}
						promise2.complete(clusterRequest);
					} catch(Exception ex) {
						LOG.error(String.format("postClusterRequestFuture failed. "), ex);
						promise.fail(ex);
					}
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(clusterRequest -> {
				try {
					ApiRequest apiRequest = siteRequest.getApiRequest_();
					if(apiRequest != null) {
						apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
						clusterRequest.apiRequestClusterRequest();
						eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());
					}
					promise.complete(clusterRequest);
				} catch(Exception ex) {
					LOG.error(String.format("postClusterRequestFuture failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("postClusterRequestFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ClusterRequest> sqlPOSTClusterRequest(ClusterRequest o, Boolean inheritPrimaryKey) {
		Promise<ClusterRequest> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE ClusterRequest SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			ClusterRequest o2 = new ClusterRequest();
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
					case ClusterRequest.VAR_name:
						o2.setName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ClusterRequest.VAR_name + "=$" + num);
						num++;
						bParams.add(o2.sqlName());
						break;
					case ClusterRequest.VAR_clusterTemplateTitle:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								search(siteRequest).query(ClusterTemplate.varIndexedClusterTemplate(ClusterTemplate.VAR_title), ClusterTemplate.varIndexedClusterTemplate(ClusterTemplate.VAR_pk), ClusterTemplate.class, val, inheritPrimaryKey).onSuccess(pk2 -> {
									if(!pks.contains(pk2)) {
										pks.add(pk2);
										classes.add("ClusterTemplate");
									}
									sql(siteRequest).update(ClusterRequest.class, pk).set(ClusterRequest.VAR_clusterTemplateTitle, ClusterTemplate.class, pk2, val).onSuccess(a -> {
										promise2.complete();
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
					case ClusterRequest.VAR_created:
						o2.setCreated(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ClusterRequest.VAR_created + "=$" + num);
						num++;
						bParams.add(o2.sqlCreated());
						break;
					case ClusterRequest.VAR_userId:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								search(siteRequest).query(SiteUser.varIndexedSiteUser(SiteUser.VAR_userId), SiteUser.varIndexedSiteUser(SiteUser.VAR_pk), SiteUser.class, val, inheritPrimaryKey).onSuccess(pk2 -> {
									if(!pks.contains(pk2)) {
										pks.add(pk2);
										classes.add("SiteUser");
									}
									sql(siteRequest).update(ClusterRequest.class, pk).set(ClusterRequest.VAR_userId, SiteUser.class, pk2, val).onSuccess(a -> {
										promise2.complete();
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
					case ClusterRequest.VAR_archived:
						o2.setArchived(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ClusterRequest.VAR_archived + "=$" + num);
						num++;
						bParams.add(o2.sqlArchived());
						break;
					case ClusterRequest.VAR_sessionId:
						o2.setSessionId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ClusterRequest.VAR_sessionId + "=$" + num);
						num++;
						bParams.add(o2.sqlSessionId());
						break;
					case ClusterRequest.VAR_userKey:
						o2.setUserKey(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ClusterRequest.VAR_userKey + "=$" + num);
						num++;
						bParams.add(o2.sqlUserKey());
						break;
					case ClusterRequest.VAR_objectTitle:
						o2.setObjectTitle(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ClusterRequest.VAR_objectTitle + "=$" + num);
						num++;
						bParams.add(o2.sqlObjectTitle());
						break;
					case ClusterRequest.VAR_displayPage:
						o2.setDisplayPage(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ClusterRequest.VAR_displayPage + "=$" + num);
						num++;
						bParams.add(o2.sqlDisplayPage());
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
						RuntimeException ex2 = new RuntimeException("value ClusterRequest failed", ex);
						LOG.error(String.format("relateClusterRequest failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete(o2);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPOSTClusterRequest failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPOSTClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPOSTClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200POSTClusterRequest(ClusterRequest o) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			if(json == null) {
				String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
						String m = String.format("%s %s not found", "OpenShift cluster request", name);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200POSTClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// DELETE //

	@Override
	public void deleteClusterRequest(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deleteClusterRequest started. "));
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "DELETE"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						searchClusterRequestList(siteRequest, false, true, true).onSuccess(listClusterRequest -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listClusterRequest.getRequest().getRows());
								apiRequest.setNumFound(listClusterRequest.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listClusterRequest.first());
								apiRequest.setPk(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());

								listDELETEClusterRequest(apiRequest, listClusterRequest).onSuccess(e -> {
									response200DELETEClusterRequest(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deleteClusterRequest succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deleteClusterRequest failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deleteClusterRequest failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deleteClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deleteClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deleteClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deleteClusterRequest failed. ", ex2));
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
				LOG.error(String.format("deleteClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEClusterRequest(ApiRequest apiRequest, SearchList<ClusterRequest> listClusterRequest) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listClusterRequest.getSiteRequest_(SiteRequest.class);
		listClusterRequest.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			ClusterRequest o2 = jsonObject.mapTo(ClusterRequest.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deleteClusterRequestFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEClusterRequest failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listClusterRequest.next().onSuccess(next -> {
				if(next) {
					listDELETEClusterRequest(apiRequest, listClusterRequest).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEClusterRequest failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEClusterRequest failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deleteClusterRequestFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
					scopes.stream().map(v -> v.toString()).forEach(scope -> {
						siteRequest.addScopes(scope);
					});
				});
				searchClusterRequestList(siteRequest, false, true, true).onSuccess(listClusterRequest -> {
					try {
						ClusterRequest o = listClusterRequest.first();
						if(o != null && listClusterRequest.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getName().toString()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getPk()).orElse(null));
							deleteClusterRequestFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deleteClusterRequest failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deleteClusterRequest failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deleteClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deleteClusterRequest failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<ClusterRequest> deleteClusterRequestFuture(ClusterRequest o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<ClusterRequest> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<ClusterRequest> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsClusterRequest(siteRequest).onSuccess(a -> {
					sqlDELETEClusterRequest(o).onSuccess(clusterRequest -> {
						relateClusterRequest(o).onSuccess(d -> {
							unindexClusterRequest(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestClusterRequest();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());
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
			}).compose(clusterRequest -> {
				Promise<ClusterRequest> promise2 = Promise.promise();
				refreshClusterRequest(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(clusterRequest -> {
				promise.complete(clusterRequest);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deleteClusterRequestFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEClusterRequest(ClusterRequest o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM ClusterRequest ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			ClusterRequest o2 = new ClusterRequest();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case ClusterRequest.VAR_clusterTemplateTitle:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								search(siteRequest).query(ClusterTemplate.varIndexedClusterTemplate(ClusterTemplate.VAR_title), ClusterTemplate.varIndexedClusterTemplate(ClusterTemplate.VAR_pk), ClusterTemplate.class, val, false).onSuccess(pk2 -> {
									if(!pks.contains(pk2)) {
										pks.add(pk2);
										classes.add("ClusterTemplate");
									}
									sql(siteRequest).update(ClusterRequest.class, pk).set(ClusterRequest.VAR_clusterTemplateTitle, ClusterTemplate.class, null, null).onSuccess(a -> {
										promise2.complete();
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
					case ClusterRequest.VAR_userId:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								search(siteRequest).query(SiteUser.varIndexedSiteUser(SiteUser.VAR_userId), SiteUser.varIndexedSiteUser(SiteUser.VAR_pk), SiteUser.class, val, false).onSuccess(pk2 -> {
									if(!pks.contains(pk2)) {
										pks.add(pk2);
										classes.add("SiteUser");
									}
									sql(siteRequest).update(ClusterRequest.class, pk).set(ClusterRequest.VAR_userId, SiteUser.class, null, null).onSuccess(a -> {
										promise2.complete();
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
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
					RuntimeException ex2 = new RuntimeException("value ClusterRequest failed", ex);
					LOG.error(String.format("unrelateClusterRequest failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEClusterRequest failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEClusterRequest(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
						String m = String.format("%s %s not found", "OpenShift cluster request", name);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PUTImport //

	@Override
	public void putimportClusterRequest(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("putimportClusterRequest started. "));
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "PUT"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						ApiRequest apiRequest = new ApiRequest();
						JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
						apiRequest.setRows(Long.valueOf(jsonArray.size()));
						apiRequest.setNumFound(Long.valueOf(jsonArray.size()));
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());
						varsClusterRequest(siteRequest).onSuccess(d -> {
							listPUTImportClusterRequest(apiRequest, siteRequest).onSuccess(e -> {
								response200PUTImportClusterRequest(siteRequest).onSuccess(response -> {
									LOG.debug(String.format("putimportClusterRequest succeeded. "));
									eventHandler.handle(Future.succeededFuture(response));
								}).onFailure(ex -> {
									LOG.error(String.format("putimportClusterRequest failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							}).onFailure(ex -> {
								LOG.error(String.format("putimportClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("putimportClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("putimportClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportClusterRequest failed. ", ex2));
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
				LOG.error(String.format("putimportClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPUTImportClusterRequest(ApiRequest apiRequest, SiteRequest siteRequest) {
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
					eventBus.request(ClusterRequest.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportClusterRequestFuture")).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPUTImportClusterRequest failed. "), ex);
						promise1.fail(ex);
					});
				}));
			});
			CompositeFuture.all(futures).onSuccess(a -> {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("listPUTImportClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("listPUTImportClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public void putimportClusterRequestFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				String name = Optional.ofNullable(body.getString(ClusterRequest.VAR_name)).orElse(body.getString(ClusterRequest.VAR_solrId));
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}
				pgPool.getConnection().onSuccess(sqlConnection -> {
					String sqlQuery = String.format("select * from %s WHERE name=$1", ClusterRequest.CLASS_SIMPLE_NAME);
					sqlConnection.preparedQuery(sqlQuery)
							.execute(Tuple.tuple(Arrays.asList(name))
							).onSuccess(result -> {
						sqlConnection.close().onSuccess(a -> {
							try {
								if(result.size() >= 1) {
									ClusterRequest o = new ClusterRequest();
									o.setSiteRequest_(siteRequest);
									for(Row definition : result.value()) {
										for(Integer i = 0; i < definition.size(); i++) {
											try {
												String columnName = definition.getColumnName(i);
												Object columnValue = definition.getValue(i);
												o.persistForClass(columnName, columnValue);
											} catch(Exception e) {
												LOG.error(String.format("persistClusterRequest failed. "), e);
											}
										}
									}
									ClusterRequest o2 = new ClusterRequest();
									o2.setSiteRequest_(siteRequest);
									JsonObject body2 = new JsonObject();
									for(String f : body.fieldNames()) {
										Object bodyVal = body.getValue(f);
										if(bodyVal instanceof JsonArray) {
											JsonArray bodyVals = (JsonArray)bodyVal;
											Object valsObj = o.obtainForClass(f);
											Collection<?> vals = valsObj instanceof JsonArray ? ((JsonArray)valsObj).getList() : (Collection<?>)valsObj;
											if(vals != null && bodyVals.size() == vals.size()) {
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
												if(vals != null)
													vals.clear();
												body2.put("set" + StringUtils.capitalize(f), bodyVal);
											}
										} else {
											o2.persistForClass(f, bodyVal);
											o2.relateForClass(f, bodyVal);
											if(!StringUtils.containsAny(f, "name", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
												body2.put("set" + StringUtils.capitalize(f), bodyVal);
										}
									}
									for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
										if(!body.fieldNames().contains(f)) {
											if(!StringUtils.containsAny(f, "name", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
												body2.putNull("set" + StringUtils.capitalize(f));
										}
									}
									if(result.size() >= 1) {
										apiRequest.setOriginal(o);
										apiRequest.setId(o.getName());
										apiRequest.setPk(o.getPk());
									}
									siteRequest.setJsonObject(body2);
									patchClusterRequestFuture(o, true).onSuccess(b -> {
										LOG.debug("Import ClusterRequest {} succeeded, modified ClusterRequest. ", body.getValue(ClusterRequest.VAR_name));
										eventHandler.handle(Future.succeededFuture());
									}).onFailure(ex -> {
										LOG.error(String.format("putimportClusterRequestFuture failed. "), ex);
										eventHandler.handle(Future.failedFuture(ex));
									});
								} else {
									postClusterRequestFuture(siteRequest, true).onSuccess(b -> {
										LOG.debug("Import ClusterRequest {} succeeded, created new ClusterRequest. ", body.getValue(ClusterRequest.VAR_name));
										eventHandler.handle(Future.succeededFuture());
									}).onFailure(ex -> {
										LOG.error(String.format("putimportClusterRequestFuture failed. "), ex);
										eventHandler.handle(Future.failedFuture(ex));
									});
								}
							} catch(Exception ex) {
								LOG.error(String.format("putimportClusterRequestFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							}
						}).onFailure(ex -> {
							LOG.error(String.format("putimportClusterRequestFuture failed. "), ex);
							eventHandler.handle(Future.failedFuture(ex));
						});
					}).onFailure(ex -> {
						LOG.error(String.format("putimportClusterRequestFuture failed. "), ex);
						eventHandler.handle(Future.failedFuture(ex));
					});
				}).onFailure(ex -> {
					LOG.error(String.format("putimportClusterRequestFuture failed. "), ex);
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Exception ex) {
				LOG.error(String.format("putimportClusterRequestFuture failed. "), ex);
				eventHandler.handle(Future.failedFuture(ex));
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportClusterRequest failed. ", ex2));
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
				LOG.error(String.format("putimportClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200PUTImportClusterRequest(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
						String m = String.format("%s %s not found", "OpenShift cluster request", name);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PUTImportClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// SearchPage //

	@Override
	public void searchpageClusterRequest(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "GET"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						searchClusterRequestList(siteRequest, false, true, false).onSuccess(listClusterRequest -> {
							response200SearchPageClusterRequest(listClusterRequest).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchpageClusterRequest succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchpageClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchpageClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchpageClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchpageClusterRequest failed. ", ex2));
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
				LOG.error(String.format("searchpageClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void searchpageClusterRequestPageInit(ClusterRequestPage page, SearchList<ClusterRequest> listClusterRequest) {
	}

	public String templateSearchPageClusterRequest(ServiceRequest serviceRequest) {
		return "en-us/search/cluster-request/ClusterRequestSearchPage.htm";
	}
	public Future<ServiceResponse> response200SearchPageClusterRequest(SearchList<ClusterRequest> listClusterRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listClusterRequest.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateSearchPageClusterRequest(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			ClusterRequestPage page = new ClusterRequestPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listClusterRequest.size() >= 1)
				siteRequest.setRequestPk(listClusterRequest.get(0).getPk());
			page.setSearchListClusterRequest_(listClusterRequest);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepClusterRequestPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200SearchPageClusterRequest failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchPageClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchPageClusterRequest(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchPageClusterRequest(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// EditPage //

	@Override
	public void editpageClusterRequest(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "GET"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						searchClusterRequestList(siteRequest, false, true, false).onSuccess(listClusterRequest -> {
							response200EditPageClusterRequest(listClusterRequest).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("editpageClusterRequest succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("editpageClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("editpageClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("editpageClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("editpageClusterRequest failed. ", ex2));
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
				LOG.error(String.format("editpageClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void editpageClusterRequestPageInit(ClusterRequestPage page, SearchList<ClusterRequest> listClusterRequest) {
	}

	public String templateEditPageClusterRequest(ServiceRequest serviceRequest) {
		return "en-us/edit/cluster-request/ClusterRequestEditPage.htm";
	}
	public Future<ServiceResponse> response200EditPageClusterRequest(SearchList<ClusterRequest> listClusterRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listClusterRequest.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateEditPageClusterRequest(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			ClusterRequestPage page = new ClusterRequestPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listClusterRequest.size() >= 1)
				siteRequest.setRequestPk(listClusterRequest.get(0).getPk());
			page.setSearchListClusterRequest_(listClusterRequest);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepClusterRequestPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200EditPageClusterRequest failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200EditPageClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotEditPageClusterRequest(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotEditPageClusterRequest(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// UserPage //

	@Override
	public void userpageClusterRequest(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "GET"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						searchClusterRequestList(siteRequest, false, true, false).onSuccess(listClusterRequest -> {
							response200UserPageClusterRequest(listClusterRequest).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("userpageClusterRequest succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("userpageClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("userpageClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("userpageClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("userpageClusterRequest failed. ", ex2));
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
				LOG.error(String.format("userpageClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void userpageClusterRequestPageInit(ClusterRequestPage page, SearchList<ClusterRequest> listClusterRequest) {
	}

	public String templateUserPageClusterRequest(ServiceRequest serviceRequest) {
		return String.format("%s.htm", serviceRequest.getExtra().getString("uri").substring(1));
	}
	public Future<ServiceResponse> response200UserPageClusterRequest(SearchList<ClusterRequest> listClusterRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listClusterRequest.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateUserPageClusterRequest(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			ClusterRequestPage page = new ClusterRequestPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listClusterRequest.size() >= 1)
				siteRequest.setRequestPk(listClusterRequest.get(0).getPk());
			page.setSearchListClusterRequest_(listClusterRequest);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepClusterRequestPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200UserPageClusterRequest failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200UserPageClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotUserPageClusterRequest(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotUserPageClusterRequest(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// DELETEFilter //

	@Override
	public void deletefilterClusterRequest(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deletefilterClusterRequest started. "));
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, "PUT"));
			if(name != null)
				form.add("permission", String.format("%s-%s#%s", ClusterRequest.CLASS_SIMPLE_NAME, name, "DELETE"));
			siteRequest.setPublicRead(classPublicRead);
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
						if(!scopes2.contains("POST"))
							scopes2.add("POST");
						if(!scopes2.contains("PATCH"))
							scopes2.add("PATCH");
						searchClusterRequestList(siteRequest, false, true, true).onSuccess(listClusterRequest -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listClusterRequest.getRequest().getRows());
								apiRequest.setNumFound(listClusterRequest.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listClusterRequest.first());
								apiRequest.setPk(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());

								listDELETEFilterClusterRequest(apiRequest, listClusterRequest).onSuccess(e -> {
									response200DELETEFilterClusterRequest(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deletefilterClusterRequest succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deletefilterClusterRequest failed. ", ex2));
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
				LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEFilterClusterRequest(ApiRequest apiRequest, SearchList<ClusterRequest> listClusterRequest) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listClusterRequest.getSiteRequest_(SiteRequest.class);
		listClusterRequest.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			ClusterRequest o2 = jsonObject.mapTo(ClusterRequest.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deletefilterClusterRequestFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEFilterClusterRequest failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listClusterRequest.next().onSuccess(next -> {
				if(next) {
					listDELETEFilterClusterRequest(apiRequest, listClusterRequest).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEFilterClusterRequest failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEFilterClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEFilterClusterRequest failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deletefilterClusterRequestFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = true;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
					scopes.stream().map(v -> v.toString()).forEach(scope -> {
						siteRequest.addScopes(scope);
					});
				});
				searchClusterRequestList(siteRequest, false, true, true).onSuccess(listClusterRequest -> {
					try {
						ClusterRequest o = listClusterRequest.first();
						if(o != null && listClusterRequest.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getName().toString()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listClusterRequest.first()).map(o2 -> o2.getPk()).orElse(null));
							deletefilterClusterRequestFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deletefilterClusterRequest failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<ClusterRequest> deletefilterClusterRequestFuture(ClusterRequest o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<ClusterRequest> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<ClusterRequest> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsClusterRequest(siteRequest).onSuccess(a -> {
					sqlDELETEFilterClusterRequest(o).onSuccess(clusterRequest -> {
						relateClusterRequest(o).onSuccess(d -> {
							unindexClusterRequest(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestClusterRequest();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketClusterRequest", JsonObject.mapFrom(apiRequest).toString());
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
			}).compose(clusterRequest -> {
				Promise<ClusterRequest> promise2 = Promise.promise();
				refreshClusterRequest(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(clusterRequest -> {
				promise.complete(clusterRequest);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deletefilterClusterRequestFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEFilterClusterRequest(ClusterRequest o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM ClusterRequest ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			ClusterRequest o2 = new ClusterRequest();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case ClusterRequest.VAR_clusterTemplateTitle:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								search(siteRequest).query(ClusterTemplate.varIndexedClusterTemplate(ClusterTemplate.VAR_title), ClusterTemplate.varIndexedClusterTemplate(ClusterTemplate.VAR_pk), ClusterTemplate.class, val, false).onSuccess(pk2 -> {
									if(!pks.contains(pk2)) {
										pks.add(pk2);
										classes.add("ClusterTemplate");
									}
									sql(siteRequest).update(ClusterRequest.class, pk).set(ClusterRequest.VAR_clusterTemplateTitle, ClusterTemplate.class, null, null).onSuccess(a -> {
										promise2.complete();
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
					case ClusterRequest.VAR_userId:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								search(siteRequest).query(SiteUser.varIndexedSiteUser(SiteUser.VAR_userId), SiteUser.varIndexedSiteUser(SiteUser.VAR_pk), SiteUser.class, val, false).onSuccess(pk2 -> {
									if(!pks.contains(pk2)) {
										pks.add(pk2);
										classes.add("SiteUser");
									}
									sql(siteRequest).update(ClusterRequest.class, pk).set(ClusterRequest.VAR_userId, SiteUser.class, null, null).onSuccess(a -> {
										promise2.complete();
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}).onFailure(ex -> {
									promise2.fail(ex);
								});
							}));
						});
						break;
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
					RuntimeException ex2 = new RuntimeException("value ClusterRequest failed", ex);
					LOG.error(String.format("unrelateClusterRequest failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEFilterClusterRequest failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEFilterClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEFilterClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEFilterClusterRequest(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String name = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("name");
						String m = String.format("%s %s not found", "OpenShift cluster request", name);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEFilterClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// General //

	public Future<ClusterRequest> createClusterRequest(SiteRequest siteRequest) {
		Promise<ClusterRequest> promise = Promise.promise();
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long userKey = siteRequest.getUserKey();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

			sqlConnection.preparedQuery("INSERT INTO ClusterRequest(created, userKey) VALUES($1, $2) RETURNING pk")
					.collecting(Collectors.toList())
					.execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
				Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				ClusterRequest o = new ClusterRequest();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				promise.complete(o);
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error("createClusterRequest failed. ", ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("createClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public void searchClusterRequestQ(SearchList<ClusterRequest> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
		}
	}

	public String searchClusterRequestFq(SearchList<ClusterRequest> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		if(StringUtils.startsWith(valueIndexed, "[")) {
			String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
			if(fqs.length != 2)
				throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
			String fq1 = fqs[0].equals("*") ? fqs[0] : ClusterRequest.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
			String fq2 = fqs[1].equals("*") ? fqs[1] : ClusterRequest.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
			 return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
		} else {
			return varIndexed + ":" + SearchTool.escapeQueryChars(ClusterRequest.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
		}
	}

	public void searchClusterRequestSort(SearchList<ClusterRequest> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.sort(varIndexed, valueIndexed);
	}

	public void searchClusterRequestRows(SearchList<ClusterRequest> searchList, Long valueRows) {
			searchList.rows(valueRows != null ? valueRows : 10L);
	}

	public void searchClusterRequestStart(SearchList<ClusterRequest> searchList, Long valueStart) {
		searchList.start(valueStart);
	}

	public void searchClusterRequestVar(SearchList<ClusterRequest> searchList, String var, String value) {
		searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
	}

	public void searchClusterRequestUri(SearchList<ClusterRequest> searchList) {
	}

	public Future<ServiceResponse> varsClusterRequest(SiteRequest siteRequest) {
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
					LOG.error(String.format("searchClusterRequest failed. "), ex);
					promise.fail(ex);
				}
			});
			promise.complete();
		} catch(Exception ex) {
			LOG.error(String.format("searchClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<SearchList<ClusterRequest>> searchClusterRequestList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify) {
		Promise<SearchList<ClusterRequest>> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();
			String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<ClusterRequest> searchList = new SearchList<ClusterRequest>();
			String facetRange = null;
			Date facetRangeStart = null;
			Date facetRangeEnd = null;
			String facetRangeGap = null;
			String statsField = null;
			String statsFieldIndexed = null;
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.q("*:*");
			searchList.setC(ClusterRequest.class);
			searchList.setSiteRequest_(siteRequest);
			searchList.facetMinCount(1);
			if(entityList != null) {
				for(String v : entityList) {
					searchList.fl(ClusterRequest.varIndexedClusterRequest(v));
				}
			}

			String name = serviceRequest.getParams().getJsonObject("path").getString("name");
			if(name != null) {
				searchList.fq("name_docvalues_string:" + SearchTool.escapeQueryChars(name));
			}

			if(!siteRequest.getScopes().contains("GET")) {
				searchList.fq("sessionId_docvalues_string:" + SearchTool.escapeQueryChars(Optional.ofNullable(siteRequest.getSessionId()).orElse("\"-----\"")) + " OR " + "sessionId_docvalues_string:" + SearchTool.escapeQueryChars(Optional.ofNullable(siteRequest.getSessionIdBefore()).orElse("\"-----\""))
						+ " OR userId_docvalues_string:" + Optional.ofNullable(siteRequest.getUserId()).orElse("\"-----\""));
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
								varsIndexed[i] = ClusterRequest.varIndexedClusterRequest(entityVar);
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
									varIndexed = ClusterRequest.varIndexedClusterRequest(entityVar);
									String entityQ = searchClusterRequestFq(searchList, entityVar, valueIndexed, varIndexed);
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
									varIndexed = ClusterRequest.varIndexedClusterRequest(entityVar);
									String entityFq = searchClusterRequestFq(searchList, entityVar, valueIndexed, varIndexed);
									mFq.appendReplacement(sb, entityFq);
								}
								if(!sb.isEmpty()) {
									mFq.appendTail(sb);
									searchList.fq(sb.toString());
								}
							} else if(paramName.equals("sort")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = ClusterRequest.varIndexedClusterRequest(entityVar);
								searchClusterRequestSort(searchList, entityVar, valueIndexed, varIndexed);
							} else if(paramName.equals("start")) {
								valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchClusterRequestStart(searchList, valueStart);
							} else if(paramName.equals("rows")) {
								valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchClusterRequestRows(searchList, valueRows);
							} else if(paramName.equals("stats")) {
								searchList.stats((Boolean)paramObject);
							} else if(paramName.equals("stats.field")) {
								Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								if(mStats.find()) {
									String solrLocalParams = mStats.group(1);
									entityVar = mStats.group(2).trim();
									varIndexed = ClusterRequest.varIndexedClusterRequest(entityVar);
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
									varIndexed = ClusterRequest.varIndexedClusterRequest(entityVar);
									searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									facetRange = entityVar;
								}
							} else if(paramName.equals("facet.field")) {
								entityVar = (String)paramObject;
								varIndexed = ClusterRequest.varIndexedClusterRequest(entityVar);
								if(varIndexed != null)
									searchList.facetField(varIndexed);
							} else if(paramName.equals("var")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								searchClusterRequestVar(searchList, entityVar, valueIndexed);
							} else if(paramName.equals("cursorMark")) {
								valueCursorMark = (String)paramObject;
								searchList.cursorMark((String)paramObject);
							}
						}
						searchClusterRequestUri(searchList);
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
			searchClusterRequest2(siteRequest, populate, store, modify, searchList);
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
						LOG.error(String.format("searchClusterRequest failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete(searchList);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("searchClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("searchClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void searchClusterRequest2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<ClusterRequest> searchList) {
	}

	public Future<Void> persistClusterRequest(ClusterRequest o, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.preparedQuery("SELECT * FROM ClusterRequest WHERE pk=$1")
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
									LOG.error(String.format("persistClusterRequest failed. "), e);
								}
							}
						}
					}
					o.promiseDeepForClass(siteRequest).onSuccess(a -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("persistClusterRequest failed. "), ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error(String.format("persistClusterRequest failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("persistClusterRequest failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("persistClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> relateClusterRequest(ClusterRequest o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			sqlConnection.preparedQuery("SELECT title as pk2, 'clusterTemplateTitle' from ClusterTemplate where title=$1 UNION SELECT userId as pk2, 'userId' from SiteUser where userId=$2")
					.collecting(Collectors.toList())
					.execute(Tuple.of(o.getClusterTemplateTitle(), o.getUserId())
					).onSuccess(result -> {
				try {
					if(result != null) {
						for(Row definition : result.value()) {
							o.relateForClass(definition.getString(1), definition.getValue(0));
						}
					}
					promise.complete();
				} catch(Exception ex) {
					LOG.error(String.format("relateClusterRequest failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("relateClusterRequest failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("relateClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public String searchVar(String varIndexed) {
		return ClusterRequest.searchVarClusterRequest(varIndexed);
	}

	@Override
	public String getClassApiAddress() {
		return ClusterRequest.CLASS_API_ADDRESS_ClusterRequest;
	}

	public Future<ClusterRequest> indexClusterRequest(ClusterRequest o) {
		Promise<ClusterRequest> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = new JsonObject();
			JsonObject add = new JsonObject();
			json.put("add", add);
			JsonObject doc = new JsonObject();
			add.put("doc", doc);
			o.indexClusterRequest(doc);
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
				LOG.error(String.format("indexClusterRequest failed. "), new RuntimeException(ex));
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("indexClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ClusterRequest> unindexClusterRequest(ClusterRequest o) {
		Promise<ClusterRequest> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			o.promiseDeepForClass(siteRequest).onSuccess(a -> {
				JsonObject json = new JsonObject();
				JsonObject delete = new JsonObject();
				json.put("delete", delete);
				String query = String.format("filter(%s:%s)", ClusterRequest.VAR_solrId, o.obtainForClass(ClusterRequest.VAR_solrId));
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
					LOG.error(String.format("unindexClusterRequest failed. "), new RuntimeException(ex));
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("unindexClusterRequest failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("unindexClusterRequest failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> refreshClusterRequest(ClusterRequest o) {
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

					if("ClusterTemplate".equals(classSimpleName2) && pk2 != null) {
						SearchList<ClusterTemplate> searchList2 = new SearchList<ClusterTemplate>();
						searchList2.setStore(true);
						searchList2.q("*:*");
						searchList2.setC(ClusterTemplate.class);
						searchList2.fq("pk_docvalues_long:" + pk2);
						searchList2.rows(1L);
						futures.add(Future.future(promise2 -> {
							searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
								ClusterTemplate o2 = searchList2.getList().stream().findFirst().orElse(null);
								if(o2 != null) {
									JsonObject params = new JsonObject();
									params.put("body", new JsonObject());
									params.put("cookie", new JsonObject());
									params.put("path", new JsonObject());
									params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("pk:" + pk2)).put("var", new JsonArray().add("refresh:false")));
									JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
									JsonObject json = new JsonObject().put("context", context);
									eventBus.request("ai-telemetry-enUS-ClusterTemplate", json, new DeliveryOptions().addHeader("action", "patchClusterTemplateFuture")).onSuccess(c -> {
										JsonObject responseMessage = (JsonObject)c.body();
										Integer statusCode = responseMessage.getInteger("statusCode");
										if(statusCode.equals(200))
											promise2.complete();
										else
											promise2.fail(new RuntimeException(responseMessage.getString("statusMessage")));
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}
							}).onFailure(ex -> {
								promise2.fail(ex);
							});
						}));
					}

					if("SiteUser".equals(classSimpleName2) && pk2 != null) {
						SearchList<SiteUser> searchList2 = new SearchList<SiteUser>();
						searchList2.setStore(true);
						searchList2.q("*:*");
						searchList2.setC(SiteUser.class);
						searchList2.fq("pk_docvalues_long:" + pk2);
						searchList2.rows(1L);
						futures.add(Future.future(promise2 -> {
							searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
								SiteUser o2 = searchList2.getList().stream().findFirst().orElse(null);
								if(o2 != null) {
									JsonObject params = new JsonObject();
									params.put("body", new JsonObject());
									params.put("cookie", new JsonObject());
									params.put("path", new JsonObject());
									params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("pk:" + pk2)).put("var", new JsonArray().add("refresh:false")));
									JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
									JsonObject json = new JsonObject().put("context", context);
									eventBus.request("ai-telemetry-enUS-SiteUser", json, new DeliveryOptions().addHeader("action", "patchSiteUserFuture")).onSuccess(c -> {
										JsonObject responseMessage = (JsonObject)c.body();
										Integer statusCode = responseMessage.getInteger("statusCode");
										if(statusCode.equals(200))
											promise2.complete();
										else
											promise2.fail(new RuntimeException(responseMessage.getString("statusMessage")));
									}).onFailure(ex -> {
										promise2.fail(ex);
									});
								}
							}).onFailure(ex -> {
								promise2.fail(ex);
							});
						}));
					}
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
					eventBus.request(ClusterRequest.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchClusterRequestFuture")).onSuccess(c -> {
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
			LOG.error(String.format("refreshClusterRequest failed. "), ex);
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
			ClusterRequest page = new ClusterRequest();
			page.setSiteRequest_((SiteRequest)siteRequest);

			page.persistForClass(ClusterRequest.VAR_name, ClusterRequest.staticSetName(siteRequest2, (String)result.get(ClusterRequest.VAR_name)));
			page.persistForClass(ClusterRequest.VAR_clusterTemplateTitle, ClusterRequest.staticSetClusterTemplateTitle(siteRequest2, (String)result.get(ClusterRequest.VAR_clusterTemplateTitle)));
			page.persistForClass(ClusterRequest.VAR_created, ClusterRequest.staticSetCreated(siteRequest2, (String)result.get(ClusterRequest.VAR_created)));
			page.persistForClass(ClusterRequest.VAR_userId, ClusterRequest.staticSetUserId(siteRequest2, (String)result.get(ClusterRequest.VAR_userId)));
			page.persistForClass(ClusterRequest.VAR_archived, ClusterRequest.staticSetArchived(siteRequest2, (String)result.get(ClusterRequest.VAR_archived)));
			page.persistForClass(ClusterRequest.VAR_sessionId, ClusterRequest.staticSetSessionId(siteRequest2, (String)result.get(ClusterRequest.VAR_sessionId)));
			page.persistForClass(ClusterRequest.VAR_userKey, ClusterRequest.staticSetUserKey(siteRequest2, (String)result.get(ClusterRequest.VAR_userKey)));
			page.persistForClass(ClusterRequest.VAR_objectTitle, ClusterRequest.staticSetObjectTitle(siteRequest2, (String)result.get(ClusterRequest.VAR_objectTitle)));
			page.persistForClass(ClusterRequest.VAR_displayPage, ClusterRequest.staticSetDisplayPage(siteRequest2, (String)result.get(ClusterRequest.VAR_displayPage)));

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
