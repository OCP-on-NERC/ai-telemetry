package org.mghpcc.aitelemetry.model.baremetalnode;

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
import org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodePage;


/**
 * Translate: false
 * Generated: true
 **/
public class BareMetalNodeEnUSGenApiServiceImpl extends BaseApiServiceImpl implements BareMetalNodeEnUSGenApiService {

	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalNodeEnUSGenApiServiceImpl.class);

	// Search //

	@Override
	public void searchBareMetalNode(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "GET"));
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
						searchBareMetalNodeList(siteRequest, false, true, false).onSuccess(listBareMetalNode -> {
							response200SearchBareMetalNode(listBareMetalNode).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchBareMetalNode succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchBareMetalNode failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("searchBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200SearchBareMetalNode(SearchList<BareMetalNode> listBareMetalNode) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listBareMetalNode.getSiteRequest_(SiteRequest.class);
			List<String> fls = listBareMetalNode.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listBareMetalNode.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = BareMetalNode.varIndexedBareMetalNode(fieldName);
						if(v != null)
							fieldNames.add(BareMetalNode.varIndexedBareMetalNode(fieldName));
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
			response200Search(listBareMetalNode.getRequest(), listBareMetalNode.getResponse(), json);
			if(json == null) {
				String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
				String m = String.format("%s %s not found", "bare metal node", nodeId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchBareMetalNode(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchBareMetalNode(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// GET //

	@Override
	public void getBareMetalNode(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "GET"));
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
						searchBareMetalNodeList(siteRequest, false, true, false).onSuccess(listBareMetalNode -> {
							response200GETBareMetalNode(listBareMetalNode).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("getBareMetalNode succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("getBareMetalNode failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("getBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("getBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("getBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("getBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200GETBareMetalNode(SearchList<BareMetalNode> listBareMetalNode) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listBareMetalNode.getSiteRequest_(SiteRequest.class);
			JsonObject json = JsonObject.mapFrom(listBareMetalNode.getList().stream().findFirst().orElse(null));
			if(json == null) {
				String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
				String m = String.format("%s %s not found", "bare metal node", nodeId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200GETBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PATCH //

	@Override
	public void patchBareMetalNode(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("patchBareMetalNode started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "PATCH"));
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
					if(authorizationDecisionResponse.failed() && !scopes.contains("PATCH")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNodeList(siteRequest, false, true, true).onSuccess(listBareMetalNode -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listBareMetalNode.getRequest().getRows());
								apiRequest.setNumFound(listBareMetalNode.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listBareMetalNode.first());
								apiRequest.setId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getNodeId().toString()).orElse(null));
								apiRequest.setSolrId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getSolrId()).orElse(null));
								eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());

								listPATCHBareMetalNode(apiRequest, listBareMetalNode).onSuccess(e -> {
									response200PATCHBareMetalNode(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("patchBareMetalNode succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("patchBareMetalNode failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("patchBareMetalNode failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("patchBareMetalNode failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("patchBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("patchBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("patchBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("patchBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPATCHBareMetalNode(ApiRequest apiRequest, SearchList<BareMetalNode> listBareMetalNode) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listBareMetalNode.getSiteRequest_(SiteRequest.class);
		listBareMetalNode.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			BareMetalNode o2 = jsonObject.mapTo(BareMetalNode.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				patchBareMetalNodeFuture(o2, false).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listPATCHBareMetalNode failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listBareMetalNode.next().onSuccess(next -> {
				if(next) {
					listPATCHBareMetalNode(apiRequest, listBareMetalNode).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPATCHBareMetalNode failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listPATCHBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listPATCHBareMetalNode failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void patchBareMetalNodeFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
					scopes.stream().map(v -> v.toString()).forEach(scope -> {
						siteRequest.addScopes(scope);
					});
				});
				searchBareMetalNodeList(siteRequest, false, true, true).onSuccess(listBareMetalNode -> {
					try {
						BareMetalNode o = listBareMetalNode.first();
						if(o != null && listBareMetalNode.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getNodeId().toString()).orElse(null));
							apiRequest.setSolrId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getSolrId()).orElse(null));
							JsonObject jsonObject = JsonObject.mapFrom(o);
							BareMetalNode o2 = jsonObject.mapTo(BareMetalNode.class);
							o2.setSiteRequest_(siteRequest);
							patchBareMetalNodeFuture(o2, false).onSuccess(o3 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("patchBareMetalNode failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("patchBareMetalNode failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("patchBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("patchBareMetalNode failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<BareMetalNode> patchBareMetalNodeFuture(BareMetalNode o, Boolean inheritPrimaryKey) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<BareMetalNode> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<BareMetalNode> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsBareMetalNode(siteRequest).onSuccess(a -> {
					sqlPATCHBareMetalNode(o, inheritPrimaryKey).onSuccess(bareMetalNode -> {
						persistBareMetalNode(bareMetalNode, true).onSuccess(c -> {
							relateBareMetalNode(bareMetalNode).onSuccess(d -> {
								indexBareMetalNode(bareMetalNode).onSuccess(o2 -> {
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
											o2.apiRequestBareMetalNode();
											if(apiRequest.getVars().size() > 0)
												eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());
										}
									}
									promise1.complete(bareMetalNode);
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
			}).compose(bareMetalNode -> {
				Promise<BareMetalNode> promise2 = Promise.promise();
				refreshBareMetalNode(bareMetalNode).onSuccess(a -> {
					promise2.complete(bareMetalNode);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(bareMetalNode -> {
				promise.complete(bareMetalNode);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("patchBareMetalNodeFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<BareMetalNode> sqlPATCHBareMetalNode(BareMetalNode o, Boolean inheritPrimaryKey) {
		Promise<BareMetalNode> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE BareMetalNode SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			BareMetalNode o2 = new BareMetalNode();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			for(String entityVar : methodNames) {
				switch(entityVar) {
					case "setLeaseInfo":
							o2.setLeaseInfo(jsonObject.getJsonArray(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_leaseInfo + "=$" + num);
							num++;
							bParams.add(o2.sqlLeaseInfo());
						break;
					case "setNetworkInfo":
							o2.setNetworkInfo(jsonObject.getJsonArray(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_networkInfo + "=$" + num);
							num++;
							bParams.add(o2.sqlNetworkInfo());
						break;
					case "setCreated":
							o2.setCreated(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_created + "=$" + num);
							num++;
							bParams.add(o2.sqlCreated());
						break;
					case "setNodeId":
							o2.setNodeId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_nodeId + "=$" + num);
							num++;
							bParams.add(o2.sqlNodeId());
						break;
					case "setNodeIsMaintenance":
							o2.setNodeIsMaintenance(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_nodeIsMaintenance + "=$" + num);
							num++;
							bParams.add(o2.sqlNodeIsMaintenance());
						break;
					case "setArchived":
							o2.setArchived(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_archived + "=$" + num);
							num++;
							bParams.add(o2.sqlArchived());
						break;
					case "setNodeLinks":
							o2.setNodeLinks(jsonObject.getJsonArray(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_nodeLinks + "=$" + num);
							num++;
							bParams.add(o2.sqlNodeLinks());
						break;
					case "setNodeName":
							o2.setNodeName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_nodeName + "=$" + num);
							num++;
							bParams.add(o2.sqlNodeName());
						break;
					case "setNodePowerState":
							o2.setNodePowerState(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_nodePowerState + "=$" + num);
							num++;
							bParams.add(o2.sqlNodePowerState());
						break;
					case "setNodeProvisionState":
							o2.setNodeProvisionState(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_nodeProvisionState + "=$" + num);
							num++;
							bParams.add(o2.sqlNodeProvisionState());
						break;
					case "setSessionId":
							o2.setSessionId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_sessionId + "=$" + num);
							num++;
							bParams.add(o2.sqlSessionId());
						break;
					case "setNodeResourceClass":
							o2.setNodeResourceClass(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_nodeResourceClass + "=$" + num);
							num++;
							bParams.add(o2.sqlNodeResourceClass());
						break;
					case "setUserKey":
							o2.setUserKey(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_userKey + "=$" + num);
							num++;
							bParams.add(o2.sqlUserKey());
						break;
					case "setObjectTitle":
							o2.setObjectTitle(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_objectTitle + "=$" + num);
							num++;
							bParams.add(o2.sqlObjectTitle());
						break;
					case "setDisplayPage":
							o2.setDisplayPage(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(BareMetalNode.VAR_displayPage + "=$" + num);
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
						RuntimeException ex2 = new RuntimeException("value BareMetalNode failed", ex);
						LOG.error(String.format("relateBareMetalNode failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					BareMetalNode o3 = new BareMetalNode();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					promise.complete(o3);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPATCHBareMetalNode failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPATCHBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPATCHBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200PATCHBareMetalNode(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
				String m = String.format("%s %s not found", "bare metal node", nodeId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PATCHBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// POST //

	@Override
	public void postBareMetalNode(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("postBareMetalNode started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "POST"));
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
					if(authorizationDecisionResponse.failed() && !scopes.contains("POST")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1L);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());
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
						eventBus.request(BareMetalNode.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postBareMetalNodeFuture")).onSuccess(a -> {
							JsonObject responseMessage = (JsonObject)a.body();
							JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
							apiRequest.setSolrId(responseBody.getString(BareMetalNode.VAR_solrId));
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
							LOG.debug(String.format("postBareMetalNode succeeded. "));
						}).onFailure(ex -> {
							LOG.error(String.format("postBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("postBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("postBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	@Override
	public void postBareMetalNodeFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
					scopes.stream().map(v -> v.toString()).forEach(scope -> {
						siteRequest.addScopes(scope);
					});
				});
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}
				postBareMetalNodeFuture(siteRequest, false).onSuccess(o -> {
					eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
				}).onFailure(ex -> {
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Throwable ex) {
				LOG.error(String.format("postBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("postBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<BareMetalNode> postBareMetalNodeFuture(SiteRequest siteRequest, Boolean nodeId) {
		Promise<BareMetalNode> promise = Promise.promise();

		try {
			pgPool.withTransaction(sqlConnection -> {
				Promise<BareMetalNode> promise1 = Promise.promise();
				siteRequest.setSqlConnection(sqlConnection);
				varsBareMetalNode(siteRequest).onSuccess(a -> {
					createBareMetalNode(siteRequest).onSuccess(bareMetalNode -> {
						sqlPOSTBareMetalNode(bareMetalNode, nodeId).onSuccess(b -> {
							persistBareMetalNode(bareMetalNode, false).onSuccess(c -> {
								relateBareMetalNode(bareMetalNode).onSuccess(d -> {
									indexBareMetalNode(bareMetalNode).onSuccess(o2 -> {
										promise1.complete(bareMetalNode);
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
			}).compose(bareMetalNode -> {
				Promise<BareMetalNode> promise2 = Promise.promise();
				refreshBareMetalNode(bareMetalNode).onSuccess(a -> {
					try {
						ApiRequest apiRequest = siteRequest.getApiRequest_();
						if(apiRequest != null) {
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							bareMetalNode.apiRequestBareMetalNode();
							eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());
						}
						promise2.complete(bareMetalNode);
					} catch(Exception ex) {
						LOG.error(String.format("postBareMetalNodeFuture failed. "), ex);
						promise2.fail(ex);
					}
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(bareMetalNode -> {
				try {
					ApiRequest apiRequest = siteRequest.getApiRequest_();
					if(apiRequest != null) {
						apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
						bareMetalNode.apiRequestBareMetalNode();
						eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());
					}
					promise.complete(bareMetalNode);
				} catch(Exception ex) {
					LOG.error(String.format("postBareMetalNodeFuture failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("postBareMetalNodeFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<BareMetalNode> sqlPOSTBareMetalNode(BareMetalNode o, Boolean inheritPrimaryKey) {
		Promise<BareMetalNode> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE BareMetalNode SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			BareMetalNode o2 = new BareMetalNode();
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
					case BareMetalNode.VAR_leaseInfo:
						o2.setLeaseInfo(jsonObject.getJsonArray(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_leaseInfo + "=$" + num);
						num++;
						bParams.add(o2.sqlLeaseInfo());
						break;
					case BareMetalNode.VAR_networkInfo:
						o2.setNetworkInfo(jsonObject.getJsonArray(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_networkInfo + "=$" + num);
						num++;
						bParams.add(o2.sqlNetworkInfo());
						break;
					case BareMetalNode.VAR_created:
						o2.setCreated(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_created + "=$" + num);
						num++;
						bParams.add(o2.sqlCreated());
						break;
					case BareMetalNode.VAR_nodeId:
						o2.setNodeId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_nodeId + "=$" + num);
						num++;
						bParams.add(o2.sqlNodeId());
						break;
					case BareMetalNode.VAR_nodeIsMaintenance:
						o2.setNodeIsMaintenance(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_nodeIsMaintenance + "=$" + num);
						num++;
						bParams.add(o2.sqlNodeIsMaintenance());
						break;
					case BareMetalNode.VAR_archived:
						o2.setArchived(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_archived + "=$" + num);
						num++;
						bParams.add(o2.sqlArchived());
						break;
					case BareMetalNode.VAR_nodeLinks:
						o2.setNodeLinks(jsonObject.getJsonArray(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_nodeLinks + "=$" + num);
						num++;
						bParams.add(o2.sqlNodeLinks());
						break;
					case BareMetalNode.VAR_nodeName:
						o2.setNodeName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_nodeName + "=$" + num);
						num++;
						bParams.add(o2.sqlNodeName());
						break;
					case BareMetalNode.VAR_nodePowerState:
						o2.setNodePowerState(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_nodePowerState + "=$" + num);
						num++;
						bParams.add(o2.sqlNodePowerState());
						break;
					case BareMetalNode.VAR_nodeProvisionState:
						o2.setNodeProvisionState(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_nodeProvisionState + "=$" + num);
						num++;
						bParams.add(o2.sqlNodeProvisionState());
						break;
					case BareMetalNode.VAR_sessionId:
						o2.setSessionId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_sessionId + "=$" + num);
						num++;
						bParams.add(o2.sqlSessionId());
						break;
					case BareMetalNode.VAR_nodeResourceClass:
						o2.setNodeResourceClass(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_nodeResourceClass + "=$" + num);
						num++;
						bParams.add(o2.sqlNodeResourceClass());
						break;
					case BareMetalNode.VAR_userKey:
						o2.setUserKey(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_userKey + "=$" + num);
						num++;
						bParams.add(o2.sqlUserKey());
						break;
					case BareMetalNode.VAR_objectTitle:
						o2.setObjectTitle(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_objectTitle + "=$" + num);
						num++;
						bParams.add(o2.sqlObjectTitle());
						break;
					case BareMetalNode.VAR_displayPage:
						o2.setDisplayPage(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(BareMetalNode.VAR_displayPage + "=$" + num);
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
						RuntimeException ex2 = new RuntimeException("value BareMetalNode failed", ex);
						LOG.error(String.format("relateBareMetalNode failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete(o2);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPOSTBareMetalNode failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPOSTBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPOSTBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200POSTBareMetalNode(BareMetalNode o) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			if(json == null) {
				String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
				String m = String.format("%s %s not found", "bare metal node", nodeId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200POSTBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// DELETE //

	@Override
	public void deleteBareMetalNode(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deleteBareMetalNode started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "DELETE"));
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
					if(authorizationDecisionResponse.failed() && !scopes.contains("DELETE")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNodeList(siteRequest, false, true, true).onSuccess(listBareMetalNode -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listBareMetalNode.getRequest().getRows());
								apiRequest.setNumFound(listBareMetalNode.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listBareMetalNode.first());
								apiRequest.setSolrId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getSolrId()).orElse(null));
								eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());

								listDELETEBareMetalNode(apiRequest, listBareMetalNode).onSuccess(e -> {
									response200DELETEBareMetalNode(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deleteBareMetalNode succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deleteBareMetalNode failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deleteBareMetalNode failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deleteBareMetalNode failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deleteBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deleteBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deleteBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("deleteBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEBareMetalNode(ApiRequest apiRequest, SearchList<BareMetalNode> listBareMetalNode) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listBareMetalNode.getSiteRequest_(SiteRequest.class);
		listBareMetalNode.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			BareMetalNode o2 = jsonObject.mapTo(BareMetalNode.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deleteBareMetalNodeFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEBareMetalNode failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listBareMetalNode.next().onSuccess(next -> {
				if(next) {
					listDELETEBareMetalNode(apiRequest, listBareMetalNode).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEBareMetalNode failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEBareMetalNode failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deleteBareMetalNodeFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
					scopes.stream().map(v -> v.toString()).forEach(scope -> {
						siteRequest.addScopes(scope);
					});
				});
				searchBareMetalNodeList(siteRequest, false, true, true).onSuccess(listBareMetalNode -> {
					try {
						BareMetalNode o = listBareMetalNode.first();
						if(o != null && listBareMetalNode.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getNodeId().toString()).orElse(null));
							apiRequest.setSolrId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getSolrId()).orElse(null));
							deleteBareMetalNodeFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deleteBareMetalNode failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deleteBareMetalNode failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deleteBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deleteBareMetalNode failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<BareMetalNode> deleteBareMetalNodeFuture(BareMetalNode o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<BareMetalNode> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<BareMetalNode> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsBareMetalNode(siteRequest).onSuccess(a -> {
					sqlDELETEBareMetalNode(o).onSuccess(bareMetalNode -> {
						relateBareMetalNode(o).onSuccess(d -> {
							unindexBareMetalNode(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestBareMetalNode();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());
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
			}).compose(bareMetalNode -> {
				Promise<BareMetalNode> promise2 = Promise.promise();
				refreshBareMetalNode(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(bareMetalNode -> {
				promise.complete(bareMetalNode);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deleteBareMetalNodeFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEBareMetalNode(BareMetalNode o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM BareMetalNode ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			BareMetalNode o2 = new BareMetalNode();
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
					RuntimeException ex2 = new RuntimeException("value BareMetalNode failed", ex);
					LOG.error(String.format("unrelateBareMetalNode failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEBareMetalNode failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEBareMetalNode(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
				String m = String.format("%s %s not found", "bare metal node", nodeId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PUTImport //

	@Override
	public void putimportBareMetalNode(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("putimportBareMetalNode started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "PUT"));
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
					if(authorizationDecisionResponse.failed() && !scopes.contains("PUT")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						ApiRequest apiRequest = new ApiRequest();
						JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
						apiRequest.setRows(Long.valueOf(jsonArray.size()));
						apiRequest.setNumFound(Long.valueOf(jsonArray.size()));
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());
						varsBareMetalNode(siteRequest).onSuccess(d -> {
							listPUTImportBareMetalNode(apiRequest, siteRequest).onSuccess(e -> {
								response200PUTImportBareMetalNode(siteRequest).onSuccess(response -> {
									LOG.debug(String.format("putimportBareMetalNode succeeded. "));
									eventHandler.handle(Future.succeededFuture(response));
								}).onFailure(ex -> {
									LOG.error(String.format("putimportBareMetalNode failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							}).onFailure(ex -> {
								LOG.error(String.format("putimportBareMetalNode failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("putimportBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("putimportBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("putimportBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPUTImportBareMetalNode(ApiRequest apiRequest, SiteRequest siteRequest) {
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
					eventBus.request(BareMetalNode.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportBareMetalNodeFuture")).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPUTImportBareMetalNode failed. "), ex);
						promise1.fail(ex);
					});
				}));
			});
			CompositeFuture.all(futures).onSuccess(a -> {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("listPUTImportBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("listPUTImportBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public void putimportBareMetalNodeFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
					scopes.stream().map(v -> v.toString()).forEach(scope -> {
						siteRequest.addScopes(scope);
					});
				});
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				String nodeId = Optional.ofNullable(body.getString(BareMetalNode.VAR_nodeId)).orElse(body.getString(BareMetalNode.VAR_solrId));
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}
				pgPool.getConnection().onSuccess(sqlConnection -> {
					String sqlQuery = String.format("select * from %s WHERE nodeId=$1", BareMetalNode.CLASS_SIMPLE_NAME);
					sqlConnection.preparedQuery(sqlQuery)
							.execute(Tuple.tuple(Arrays.asList(nodeId))
							).onSuccess(result -> {
						sqlConnection.close().onSuccess(a -> {
							try {
								if(result.size() >= 1) {
									BareMetalNode o = new BareMetalNode();
									o.setSiteRequest_(siteRequest);
									for(Row definition : result.value()) {
										for(Integer i = 0; i < definition.size(); i++) {
											try {
												String columnName = definition.getColumnName(i);
												Object columnValue = definition.getValue(i);
												o.persistForClass(columnName, columnValue);
											} catch(Exception e) {
												LOG.error(String.format("persistBareMetalNode failed. "), e);
											}
										}
									}
									BareMetalNode o2 = new BareMetalNode();
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
											if(!StringUtils.containsAny(f, "nodeId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
												body2.put("set" + StringUtils.capitalize(f), bodyVal);
										}
									}
									for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
										if(!body.fieldNames().contains(f)) {
											if(!StringUtils.containsAny(f, "nodeId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
												body2.putNull("set" + StringUtils.capitalize(f));
										}
									}
									if(result.size() >= 1) {
										apiRequest.setOriginal(o);
										apiRequest.setId(Optional.ofNullable(o.getNodeId()).map(v -> v.toString()).orElse(null));
										apiRequest.setSolrId(o.getSolrId());
									}
									siteRequest.setJsonObject(body2);
									patchBareMetalNodeFuture(o, true).onSuccess(b -> {
										LOG.debug("Import BareMetalNode {} succeeded, modified BareMetalNode. ", body.getValue(BareMetalNode.VAR_nodeId));
										eventHandler.handle(Future.succeededFuture());
									}).onFailure(ex -> {
										LOG.error(String.format("putimportBareMetalNodeFuture failed. "), ex);
										eventHandler.handle(Future.failedFuture(ex));
									});
								} else {
									postBareMetalNodeFuture(siteRequest, true).onSuccess(b -> {
										LOG.debug("Import BareMetalNode {} succeeded, created new BareMetalNode. ", body.getValue(BareMetalNode.VAR_nodeId));
										eventHandler.handle(Future.succeededFuture());
									}).onFailure(ex -> {
										LOG.error(String.format("putimportBareMetalNodeFuture failed. "), ex);
										eventHandler.handle(Future.failedFuture(ex));
									});
								}
							} catch(Exception ex) {
								LOG.error(String.format("putimportBareMetalNodeFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							}
						}).onFailure(ex -> {
							LOG.error(String.format("putimportBareMetalNodeFuture failed. "), ex);
							eventHandler.handle(Future.failedFuture(ex));
						});
					}).onFailure(ex -> {
						LOG.error(String.format("putimportBareMetalNodeFuture failed. "), ex);
						eventHandler.handle(Future.failedFuture(ex));
					});
				}).onFailure(ex -> {
					LOG.error(String.format("putimportBareMetalNodeFuture failed. "), ex);
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Exception ex) {
				LOG.error(String.format("putimportBareMetalNodeFuture failed. "), ex);
				eventHandler.handle(Future.failedFuture(ex));
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("putimportBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200PUTImportBareMetalNode(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
				String m = String.format("%s %s not found", "bare metal node", nodeId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PUTImportBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// SearchPage //

	@Override
	public void searchpageBareMetalNode(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "GET"));
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
						searchBareMetalNodeList(siteRequest, false, true, false).onSuccess(listBareMetalNode -> {
							response200SearchPageBareMetalNode(listBareMetalNode).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchpageBareMetalNode succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchpageBareMetalNode failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchpageBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchpageBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchpageBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("searchpageBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void searchpageBareMetalNodePageInit(BareMetalNodePage page, SearchList<BareMetalNode> listBareMetalNode) {
	}

	public String templateSearchPageBareMetalNode(ServiceRequest serviceRequest) {
		return "en-us/search/bare-metal-node/BareMetalNodeSearchPage.htm";
	}
	public Future<ServiceResponse> response200SearchPageBareMetalNode(SearchList<BareMetalNode> listBareMetalNode) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listBareMetalNode.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateSearchPageBareMetalNode(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			BareMetalNodePage page = new BareMetalNodePage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listBareMetalNode.size() >= 1)
				siteRequest.setRequestPk(listBareMetalNode.get(0).getPk());
			page.setSearchListBareMetalNode_(listBareMetalNode);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepBareMetalNodePage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200SearchPageBareMetalNode failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchPageBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchPageBareMetalNode(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchPageBareMetalNode(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// EditPage //

	@Override
	public void editpageBareMetalNode(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "GET"));
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
						searchBareMetalNodeList(siteRequest, false, true, false).onSuccess(listBareMetalNode -> {
							response200EditPageBareMetalNode(listBareMetalNode).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("editpageBareMetalNode succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("editpageBareMetalNode failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("editpageBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("editpageBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("editpageBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("editpageBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void editpageBareMetalNodePageInit(BareMetalNodePage page, SearchList<BareMetalNode> listBareMetalNode) {
	}

	public String templateEditPageBareMetalNode(ServiceRequest serviceRequest) {
		return "en-us/edit/bare-metal-node/BareMetalNodeEditPage.htm";
	}
	public Future<ServiceResponse> response200EditPageBareMetalNode(SearchList<BareMetalNode> listBareMetalNode) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listBareMetalNode.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateEditPageBareMetalNode(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			BareMetalNodePage page = new BareMetalNodePage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listBareMetalNode.size() >= 1)
				siteRequest.setRequestPk(listBareMetalNode.get(0).getPk());
			page.setSearchListBareMetalNode_(listBareMetalNode);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepBareMetalNodePage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200EditPageBareMetalNode failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200EditPageBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotEditPageBareMetalNode(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotEditPageBareMetalNode(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// DELETEFilter //

	@Override
	public void deletefilterBareMetalNode(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deletefilterBareMetalNode started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "GET"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "POST"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "DELETE"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PATCH"));
			form.add("permission", String.format("%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, "PUT"));
			if(nodeId != null)
				form.add("permission", String.format("%s-%s#%s", BareMetalNode.CLASS_SIMPLE_NAME, nodeId, "DELETE"));
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
					if(authorizationDecisionResponse.failed() && !scopes.contains("DELETE")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchBareMetalNodeList(siteRequest, false, true, true).onSuccess(listBareMetalNode -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listBareMetalNode.getRequest().getRows());
								apiRequest.setNumFound(listBareMetalNode.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listBareMetalNode.first());
								apiRequest.setSolrId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getSolrId()).orElse(null));
								eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());

								listDELETEFilterBareMetalNode(apiRequest, listBareMetalNode).onSuccess(e -> {
									response200DELETEFilterBareMetalNode(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deletefilterBareMetalNode succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deletefilterBareMetalNode failed. ", ex2));
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
				LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEFilterBareMetalNode(ApiRequest apiRequest, SearchList<BareMetalNode> listBareMetalNode) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listBareMetalNode.getSiteRequest_(SiteRequest.class);
		listBareMetalNode.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			BareMetalNode o2 = jsonObject.mapTo(BareMetalNode.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deletefilterBareMetalNodeFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEFilterBareMetalNode failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listBareMetalNode.next().onSuccess(next -> {
				if(next) {
					listDELETEFilterBareMetalNode(apiRequest, listBareMetalNode).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEFilterBareMetalNode failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEFilterBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEFilterBareMetalNode failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deletefilterBareMetalNodeFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
					scopes.stream().map(v -> v.toString()).forEach(scope -> {
						siteRequest.addScopes(scope);
					});
				});
				searchBareMetalNodeList(siteRequest, false, true, true).onSuccess(listBareMetalNode -> {
					try {
						BareMetalNode o = listBareMetalNode.first();
						if(o != null && listBareMetalNode.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getNodeId().toString()).orElse(null));
							apiRequest.setSolrId(Optional.ofNullable(listBareMetalNode.first()).map(o2 -> o2.getSolrId()).orElse(null));
							deletefilterBareMetalNodeFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deletefilterBareMetalNode failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<BareMetalNode> deletefilterBareMetalNodeFuture(BareMetalNode o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<BareMetalNode> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<BareMetalNode> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsBareMetalNode(siteRequest).onSuccess(a -> {
					sqlDELETEFilterBareMetalNode(o).onSuccess(bareMetalNode -> {
						relateBareMetalNode(o).onSuccess(d -> {
							unindexBareMetalNode(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestBareMetalNode();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketBareMetalNode", JsonObject.mapFrom(apiRequest).toString());
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
			}).compose(bareMetalNode -> {
				Promise<BareMetalNode> promise2 = Promise.promise();
				refreshBareMetalNode(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(bareMetalNode -> {
				promise.complete(bareMetalNode);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deletefilterBareMetalNodeFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEFilterBareMetalNode(BareMetalNode o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM BareMetalNode ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			BareMetalNode o2 = new BareMetalNode();
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
					RuntimeException ex2 = new RuntimeException("value BareMetalNode failed", ex);
					LOG.error(String.format("unrelateBareMetalNode failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEFilterBareMetalNode failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEFilterBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEFilterBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEFilterBareMetalNode(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String nodeId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("nodeId");
				String m = String.format("%s %s not found", "bare metal node", nodeId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEFilterBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// General //

	public Future<BareMetalNode> createBareMetalNode(SiteRequest siteRequest) {
		Promise<BareMetalNode> promise = Promise.promise();
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long userKey = siteRequest.getUserKey();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

			sqlConnection.preparedQuery("INSERT INTO BareMetalNode(created, userKey) VALUES($1, $2) RETURNING pk")
					.collecting(Collectors.toList())
					.execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
				Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				BareMetalNode o = new BareMetalNode();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				promise.complete(o);
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error("createBareMetalNode failed. ", ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("createBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public void searchBareMetalNodeQ(SearchList<BareMetalNode> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
		}
	}

	public String searchBareMetalNodeFq(SearchList<BareMetalNode> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		if(StringUtils.startsWith(valueIndexed, "[")) {
			String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
			if(fqs.length != 2)
				throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
			String fq1 = fqs[0].equals("*") ? fqs[0] : BareMetalNode.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
			String fq2 = fqs[1].equals("*") ? fqs[1] : BareMetalNode.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
			 return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
		} else {
			return varIndexed + ":" + SearchTool.escapeQueryChars(BareMetalNode.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
		}
	}

	public void searchBareMetalNodeSort(SearchList<BareMetalNode> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.sort(varIndexed, valueIndexed);
	}

	public void searchBareMetalNodeRows(SearchList<BareMetalNode> searchList, Long valueRows) {
			searchList.rows(valueRows != null ? valueRows : 10L);
	}

	public void searchBareMetalNodeStart(SearchList<BareMetalNode> searchList, Long valueStart) {
		searchList.start(valueStart);
	}

	public void searchBareMetalNodeVar(SearchList<BareMetalNode> searchList, String var, String value) {
		searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
	}

	public void searchBareMetalNodeUri(SearchList<BareMetalNode> searchList) {
	}

	public Future<ServiceResponse> varsBareMetalNode(SiteRequest siteRequest) {
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
					LOG.error(String.format("searchBareMetalNode failed. "), ex);
					promise.fail(ex);
				}
			});
			promise.complete();
		} catch(Exception ex) {
			LOG.error(String.format("searchBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<SearchList<BareMetalNode>> searchBareMetalNodeList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify) {
		Promise<SearchList<BareMetalNode>> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();
			String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<BareMetalNode> searchList = new SearchList<BareMetalNode>();
			String facetRange = null;
			Date facetRangeStart = null;
			Date facetRangeEnd = null;
			String facetRangeGap = null;
			String statsField = null;
			String statsFieldIndexed = null;
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.q("*:*");
			searchList.setC(BareMetalNode.class);
			searchList.setSiteRequest_(siteRequest);
			searchList.facetMinCount(1);
			if(entityList != null) {
				for(String v : entityList) {
					searchList.fl(BareMetalNode.varIndexedBareMetalNode(v));
				}
			}

			String nodeId = serviceRequest.getParams().getJsonObject("path").getString("nodeId");
			if(nodeId != null) {
				searchList.fq("nodeId_docvalues_string:" + SearchTool.escapeQueryChars(nodeId));
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
								varsIndexed[i] = BareMetalNode.varIndexedBareMetalNode(entityVar);
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
									varIndexed = BareMetalNode.varIndexedBareMetalNode(entityVar);
									String entityQ = searchBareMetalNodeFq(searchList, entityVar, valueIndexed, varIndexed);
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
									varIndexed = BareMetalNode.varIndexedBareMetalNode(entityVar);
									String entityFq = searchBareMetalNodeFq(searchList, entityVar, valueIndexed, varIndexed);
									mFq.appendReplacement(sb, entityFq);
								}
								if(!sb.isEmpty()) {
									mFq.appendTail(sb);
									searchList.fq(sb.toString());
								}
							} else if(paramName.equals("sort")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = BareMetalNode.varIndexedBareMetalNode(entityVar);
								searchBareMetalNodeSort(searchList, entityVar, valueIndexed, varIndexed);
							} else if(paramName.equals("start")) {
								valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchBareMetalNodeStart(searchList, valueStart);
							} else if(paramName.equals("rows")) {
								valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchBareMetalNodeRows(searchList, valueRows);
							} else if(paramName.equals("stats")) {
								searchList.stats((Boolean)paramObject);
							} else if(paramName.equals("stats.field")) {
								Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								if(mStats.find()) {
									String solrLocalParams = mStats.group(1);
									entityVar = mStats.group(2).trim();
									varIndexed = BareMetalNode.varIndexedBareMetalNode(entityVar);
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
									varIndexed = BareMetalNode.varIndexedBareMetalNode(entityVar);
									searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									facetRange = entityVar;
								}
							} else if(paramName.equals("facet.field")) {
								entityVar = (String)paramObject;
								varIndexed = BareMetalNode.varIndexedBareMetalNode(entityVar);
								if(varIndexed != null)
									searchList.facetField(varIndexed);
							} else if(paramName.equals("var")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								searchBareMetalNodeVar(searchList, entityVar, valueIndexed);
							} else if(paramName.equals("cursorMark")) {
								valueCursorMark = (String)paramObject;
								searchList.cursorMark((String)paramObject);
							}
						}
						searchBareMetalNodeUri(searchList);
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
			searchBareMetalNode2(siteRequest, populate, store, modify, searchList);
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
						LOG.error(String.format("searchBareMetalNode failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete(searchList);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("searchBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("searchBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void searchBareMetalNode2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<BareMetalNode> searchList) {
	}

	public Future<Void> persistBareMetalNode(BareMetalNode o, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.preparedQuery("SELECT leaseInfo, networkInfo, created, nodeId, nodeIsMaintenance, archived, nodeLinks, nodeName, nodePowerState, nodeProvisionState, sessionId, nodeResourceClass, userKey, objectTitle, displayPage FROM BareMetalNode WHERE pk=$1")
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
									LOG.error(String.format("persistBareMetalNode failed. "), e);
								}
							}
						}
					}
					o.promiseDeepForClass(siteRequest).onSuccess(a -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("persistBareMetalNode failed. "), ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error(String.format("persistBareMetalNode failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("persistBareMetalNode failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("persistBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> relateBareMetalNode(BareMetalNode o) {
		Promise<Void> promise = Promise.promise();
		promise.complete();
		return promise.future();
	}

	public String searchVar(String varIndexed) {
		return BareMetalNode.searchVarBareMetalNode(varIndexed);
	}

	@Override
	public String getClassApiAddress() {
		return BareMetalNode.CLASS_API_ADDRESS_BareMetalNode;
	}

	public Future<BareMetalNode> indexBareMetalNode(BareMetalNode o) {
		Promise<BareMetalNode> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = new JsonObject();
			JsonObject add = new JsonObject();
			json.put("add", add);
			JsonObject doc = new JsonObject();
			add.put("doc", doc);
			o.indexBareMetalNode(doc);
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
				LOG.error(String.format("indexBareMetalNode failed. "), new RuntimeException(ex));
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("indexBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<BareMetalNode> unindexBareMetalNode(BareMetalNode o) {
		Promise<BareMetalNode> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			o.promiseDeepForClass(siteRequest).onSuccess(a -> {
				JsonObject json = new JsonObject();
				JsonObject delete = new JsonObject();
				json.put("delete", delete);
				String query = String.format("filter(%s:%s)", BareMetalNode.VAR_solrId, o.obtainForClass(BareMetalNode.VAR_solrId));
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
					LOG.error(String.format("unindexBareMetalNode failed. "), new RuntimeException(ex));
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("unindexBareMetalNode failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("unindexBareMetalNode failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> refreshBareMetalNode(BareMetalNode o) {
		Promise<Void> promise = Promise.promise();
		SiteRequest siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean refresh = !"false".equals(siteRequest.getRequestVars().get("refresh"));
			if(refresh && !Optional.ofNullable(siteRequest.getJsonObject()).map(JsonObject::isEmpty).orElse(true)) {
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < solrIds.size(); i++) {
					String solrId2 = solrIds.get(i);
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
					eventBus.request(BareMetalNode.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchBareMetalNodeFuture")).onSuccess(c -> {
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
			LOG.error(String.format("refreshBareMetalNode failed. "), ex);
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
			BareMetalNode page = new BareMetalNode();
			page.setSiteRequest_((SiteRequest)siteRequest);

			page.persistForClass(BareMetalNode.VAR_leaseInfo, BareMetalNode.staticSetLeaseInfo(siteRequest2, (String)result.get(BareMetalNode.VAR_leaseInfo)));
			page.persistForClass(BareMetalNode.VAR_networkInfo, BareMetalNode.staticSetNetworkInfo(siteRequest2, (String)result.get(BareMetalNode.VAR_networkInfo)));
			page.persistForClass(BareMetalNode.VAR_created, BareMetalNode.staticSetCreated(siteRequest2, (String)result.get(BareMetalNode.VAR_created), Optional.ofNullable(siteRequest).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))));
			page.persistForClass(BareMetalNode.VAR_nodeId, BareMetalNode.staticSetNodeId(siteRequest2, (String)result.get(BareMetalNode.VAR_nodeId)));
			page.persistForClass(BareMetalNode.VAR_nodeIsMaintenance, BareMetalNode.staticSetNodeIsMaintenance(siteRequest2, (String)result.get(BareMetalNode.VAR_nodeIsMaintenance)));
			page.persistForClass(BareMetalNode.VAR_archived, BareMetalNode.staticSetArchived(siteRequest2, (String)result.get(BareMetalNode.VAR_archived)));
			page.persistForClass(BareMetalNode.VAR_nodeLinks, BareMetalNode.staticSetNodeLinks(siteRequest2, (String)result.get(BareMetalNode.VAR_nodeLinks)));
			page.persistForClass(BareMetalNode.VAR_nodeName, BareMetalNode.staticSetNodeName(siteRequest2, (String)result.get(BareMetalNode.VAR_nodeName)));
			page.persistForClass(BareMetalNode.VAR_nodePowerState, BareMetalNode.staticSetNodePowerState(siteRequest2, (String)result.get(BareMetalNode.VAR_nodePowerState)));
			page.persistForClass(BareMetalNode.VAR_nodeProvisionState, BareMetalNode.staticSetNodeProvisionState(siteRequest2, (String)result.get(BareMetalNode.VAR_nodeProvisionState)));
			page.persistForClass(BareMetalNode.VAR_sessionId, BareMetalNode.staticSetSessionId(siteRequest2, (String)result.get(BareMetalNode.VAR_sessionId)));
			page.persistForClass(BareMetalNode.VAR_nodeResourceClass, BareMetalNode.staticSetNodeResourceClass(siteRequest2, (String)result.get(BareMetalNode.VAR_nodeResourceClass)));
			page.persistForClass(BareMetalNode.VAR_userKey, BareMetalNode.staticSetUserKey(siteRequest2, (String)result.get(BareMetalNode.VAR_userKey)));
			page.persistForClass(BareMetalNode.VAR_objectTitle, BareMetalNode.staticSetObjectTitle(siteRequest2, (String)result.get(BareMetalNode.VAR_objectTitle)));
			page.persistForClass(BareMetalNode.VAR_displayPage, BareMetalNode.staticSetDisplayPage(siteRequest2, (String)result.get(BareMetalNode.VAR_displayPage)));

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
