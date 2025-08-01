package org.mghpcc.aitelemetry.model.cluster;

import org.mghpcc.aitelemetry.model.hub.HubEnUSApiServiceImpl;
import org.mghpcc.aitelemetry.model.hub.Hub;
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
import org.mghpcc.aitelemetry.model.cluster.ClusterPage;


/**
 * Translate: false
 * Generated: true
 **/
public class ClusterEnUSGenApiServiceImpl extends BaseApiServiceImpl implements ClusterEnUSGenApiService {

	protected static final Logger LOG = LoggerFactory.getLogger(ClusterEnUSGenApiServiceImpl.class);

	// Search //

	@Override
	public void searchCluster(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "GET"));
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
					if(!scopes.contains("GET")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("GET");
						}
					}
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchClusterList(siteRequest, false, true, false).onSuccess(listCluster -> {
							response200SearchCluster(listCluster).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchCluster succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchCluster failed. ", ex2));
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
				LOG.error(String.format("searchCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200SearchCluster(SearchList<Cluster> listCluster) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listCluster.getSiteRequest_(SiteRequest.class);
			List<String> fls = listCluster.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listCluster.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = Cluster.varIndexedCluster(fieldName);
						if(v != null)
							fieldNames.add(Cluster.varIndexedCluster(fieldName));
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
			response200Search(listCluster.getRequest(), listCluster.getResponse(), json);
			if(json == null) {
				String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
				String m = String.format("%s %s not found", "OpenShift cluster", clusterResource);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchCluster(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchCluster(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// GET //

	@Override
	public void getCluster(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "GET"));
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
					if(!scopes.contains("GET")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("GET");
						}
					}
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchClusterList(siteRequest, false, true, false).onSuccess(listCluster -> {
							response200GETCluster(listCluster).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("getCluster succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("getCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("getCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("getCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("getCluster failed. ", ex2));
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
				LOG.error(String.format("getCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200GETCluster(SearchList<Cluster> listCluster) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listCluster.getSiteRequest_(SiteRequest.class);
			JsonObject json = JsonObject.mapFrom(listCluster.getList().stream().findFirst().orElse(null));
			if(json == null) {
				String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
				String m = String.format("%s %s not found", "OpenShift cluster", clusterResource);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200GETCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PATCH //

	@Override
	public void patchCluster(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("patchCluster started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "PATCH"));
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
					if(!scopes.contains("PATCH")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(PATCH)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(PATCH)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("PATCH");
						}
					}
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
						searchClusterList(siteRequest, false, true, true).onSuccess(listCluster -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listCluster.getRequest().getRows());
								apiRequest.setNumFound(listCluster.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listCluster.first());
								apiRequest.setId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getClusterResource().toString()).orElse(null));
								apiRequest.setSolrId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getSolrId()).orElse(null));
								eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());

								listPATCHCluster(apiRequest, listCluster).onSuccess(e -> {
									response200PATCHCluster(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("patchCluster succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("patchCluster failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("patchCluster failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("patchCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("patchCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("patchCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("patchCluster failed. ", ex2));
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
				LOG.error(String.format("patchCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPATCHCluster(ApiRequest apiRequest, SearchList<Cluster> listCluster) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listCluster.getSiteRequest_(SiteRequest.class);
		listCluster.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			Cluster o2 = jsonObject.mapTo(Cluster.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				patchClusterFuture(o2, false).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listPATCHCluster failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listCluster.next().onSuccess(next -> {
				if(next) {
					listPATCHCluster(apiRequest, listCluster).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPATCHCluster failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listPATCHCluster failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listPATCHCluster failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void patchClusterFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
				searchClusterList(siteRequest, false, true, true).onSuccess(listCluster -> {
					try {
						Cluster o = listCluster.first();
						if(o != null && listCluster.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getClusterResource().toString()).orElse(null));
							apiRequest.setSolrId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getSolrId()).orElse(null));
							JsonObject jsonObject = JsonObject.mapFrom(o);
							Cluster o2 = jsonObject.mapTo(Cluster.class);
							o2.setSiteRequest_(siteRequest);
							patchClusterFuture(o2, false).onSuccess(o3 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("patchCluster failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("patchCluster failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("patchCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("patchCluster failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<Cluster> patchClusterFuture(Cluster o, Boolean inheritPrimaryKey) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<Cluster> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<Cluster> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsCluster(siteRequest).onSuccess(a -> {
					sqlPATCHCluster(o, inheritPrimaryKey).onSuccess(cluster -> {
						persistCluster(cluster, true).onSuccess(c -> {
							relateCluster(cluster).onSuccess(d -> {
								indexCluster(cluster).onSuccess(o2 -> {
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
											o2.apiRequestCluster();
											if(apiRequest.getVars().size() > 0)
												eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());
										}
									}
									promise1.complete(cluster);
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
			}).compose(cluster -> {
				Promise<Cluster> promise2 = Promise.promise();
				refreshCluster(cluster).onSuccess(a -> {
					promise2.complete(cluster);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(cluster -> {
				promise.complete(cluster);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("patchClusterFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Cluster> sqlPATCHCluster(Cluster o, Boolean inheritPrimaryKey) {
		Promise<Cluster> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE Cluster SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			Cluster o2 = new Cluster();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			for(String entityVar : methodNames) {
				switch(entityVar) {
					case "setHubId":
							o2.setHubId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_hubId + "=$" + num);
							num++;
							bParams.add(o2.sqlHubId());
						break;
					case "setHubResource":
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								searchModel(siteRequest).query(Hub.varIndexedHub(Hub.VAR_hubResource), Hub.class, val).onSuccess(o3 -> {
									String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
									if(solrId2 != null) {
										solrIds.add(solrId2);
										classes.add("Hub");
									}
									sql(siteRequest).update(Cluster.class, pk).set(Cluster.VAR_hubResource, Hub.class, solrId2, val).onSuccess(a -> {
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
					case "removeHubResource":
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(solrId2 -> {
							futures2.add(Future.future(promise2 -> {
								sql(siteRequest).update(Cluster.class, pk).setToNull(Cluster.VAR_hubResource, Hub.class, null).onSuccess(a -> {
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
							bSql.append(Cluster.VAR_created + "=$" + num);
							num++;
							bParams.add(o2.sqlCreated());
						break;
					case "setClusterName":
							o2.setClusterName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_clusterName + "=$" + num);
							num++;
							bParams.add(o2.sqlClusterName());
						break;
					case "setClusterResource":
							o2.setClusterResource(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_clusterResource + "=$" + num);
							num++;
							bParams.add(o2.sqlClusterResource());
						break;
					case "setArchived":
							o2.setArchived(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_archived + "=$" + num);
							num++;
							bParams.add(o2.sqlArchived());
						break;
					case "setDescription":
							o2.setDescription(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_description + "=$" + num);
							num++;
							bParams.add(o2.sqlDescription());
						break;
					case "setSessionId":
							o2.setSessionId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_sessionId + "=$" + num);
							num++;
							bParams.add(o2.sqlSessionId());
						break;
					case "setUserKey":
							o2.setUserKey(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_userKey + "=$" + num);
							num++;
							bParams.add(o2.sqlUserKey());
						break;
					case "setLocation":
							o2.setLocation(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_location + "=$" + num);
							num++;
							bParams.add(o2.sqlLocation());
						break;
					case "setId":
							o2.setId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_id + "=$" + num);
							num++;
							bParams.add(o2.sqlId());
						break;
					case "setObjectTitle":
							o2.setObjectTitle(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_objectTitle + "=$" + num);
							num++;
							bParams.add(o2.sqlObjectTitle());
						break;
					case "setNgsildTenant":
							o2.setNgsildTenant(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_ngsildTenant + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildTenant());
						break;
					case "setDisplayPage":
							o2.setDisplayPage(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_displayPage + "=$" + num);
							num++;
							bParams.add(o2.sqlDisplayPage());
						break;
					case "setNgsildPath":
							o2.setNgsildPath(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_ngsildPath + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildPath());
						break;
					case "setNgsildContext":
							o2.setNgsildContext(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_ngsildContext + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildContext());
						break;
					case "setNgsildData":
							o2.setNgsildData(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_ngsildData + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildData());
						break;
					case "setAiNodesTotal":
							o2.setAiNodesTotal(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_aiNodesTotal + "=$" + num);
							num++;
							bParams.add(o2.sqlAiNodesTotal());
						break;
					case "setGpuDevicesTotal":
							o2.setGpuDevicesTotal(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_gpuDevicesTotal + "=$" + num);
							num++;
							bParams.add(o2.sqlGpuDevicesTotal());
						break;
					case "setCpuCoresTotal":
							o2.setCpuCoresTotal(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_cpuCoresTotal + "=$" + num);
							num++;
							bParams.add(o2.sqlCpuCoresTotal());
						break;
					case "setMemoryBytesTotal":
							o2.setMemoryBytesTotal(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_memoryBytesTotal + "=$" + num);
							num++;
							bParams.add(o2.sqlMemoryBytesTotal());
						break;
					case "setPromKeycloakProxySsl":
							o2.setPromKeycloakProxySsl(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_promKeycloakProxySsl + "=$" + num);
							num++;
							bParams.add(o2.sqlPromKeycloakProxySsl());
						break;
					case "setPromKeycloakProxyPort":
							o2.setPromKeycloakProxyPort(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_promKeycloakProxyPort + "=$" + num);
							num++;
							bParams.add(o2.sqlPromKeycloakProxyPort());
						break;
					case "setPromKeycloakProxyHostName":
							o2.setPromKeycloakProxyHostName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(Cluster.VAR_promKeycloakProxyHostName + "=$" + num);
							num++;
							bParams.add(o2.sqlPromKeycloakProxyHostName());
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
						RuntimeException ex2 = new RuntimeException("value Cluster failed", ex);
						LOG.error(String.format("relateCluster failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					Cluster o3 = new Cluster();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					promise.complete(o3);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPATCHCluster failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPATCHCluster failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPATCHCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200PATCHCluster(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
				String m = String.format("%s %s not found", "OpenShift cluster", clusterResource);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PATCHCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// POST //

	@Override
	public void postCluster(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("postCluster started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "POST"));
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
					if(!scopes.contains("POST")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(POST)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(POST)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("POST");
						}
					}
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
						eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());
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
						eventBus.request(Cluster.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postClusterFuture")).onSuccess(a -> {
							JsonObject responseMessage = (JsonObject)a.body();
							JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
							apiRequest.setSolrId(responseBody.getString(Cluster.VAR_solrId));
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
							LOG.debug(String.format("postCluster succeeded. "));
						}).onFailure(ex -> {
							LOG.error(String.format("postCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("postCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postCluster failed. ", ex2));
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
				LOG.error(String.format("postCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	@Override
	public void postClusterFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
				postClusterFuture(siteRequest, false).onSuccess(o -> {
					eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
				}).onFailure(ex -> {
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Throwable ex) {
				LOG.error(String.format("postCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postCluster failed. ", ex2));
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
				LOG.error(String.format("postCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Cluster> postClusterFuture(SiteRequest siteRequest, Boolean clusterResource) {
		Promise<Cluster> promise = Promise.promise();

		try {
			pgPool.withTransaction(sqlConnection -> {
				Promise<Cluster> promise1 = Promise.promise();
				siteRequest.setSqlConnection(sqlConnection);
				varsCluster(siteRequest).onSuccess(a -> {
					createCluster(siteRequest).onSuccess(cluster -> {
						sqlPOSTCluster(cluster, clusterResource).onSuccess(b -> {
							persistCluster(cluster, false).onSuccess(c -> {
								relateCluster(cluster).onSuccess(d -> {
									indexCluster(cluster).onSuccess(o2 -> {
										promise1.complete(cluster);
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
			}).compose(cluster -> {
				Promise<Cluster> promise2 = Promise.promise();
				refreshCluster(cluster).onSuccess(a -> {
					try {
						ApiRequest apiRequest = siteRequest.getApiRequest_();
						if(apiRequest != null) {
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							cluster.apiRequestCluster();
							eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());
						}
						promise2.complete(cluster);
					} catch(Exception ex) {
						LOG.error(String.format("postClusterFuture failed. "), ex);
						promise2.fail(ex);
					}
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(cluster -> {
				try {
					ApiRequest apiRequest = siteRequest.getApiRequest_();
					if(apiRequest != null) {
						apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
						cluster.apiRequestCluster();
						eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());
					}
					promise.complete(cluster);
				} catch(Exception ex) {
					LOG.error(String.format("postClusterFuture failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("postClusterFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Cluster> sqlPOSTCluster(Cluster o, Boolean inheritPrimaryKey) {
		Promise<Cluster> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE Cluster SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Cluster o2 = new Cluster();
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
					case Cluster.VAR_hubId:
						o2.setHubId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_hubId + "=$" + num);
						num++;
						bParams.add(o2.sqlHubId());
						break;
					case Cluster.VAR_hubResource:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								searchModel(siteRequest).query(Hub.varIndexedHub(Hub.VAR_hubResource), Hub.class, val).onSuccess(o3 -> {
									String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
									if(solrId2 != null) {
										solrIds.add(solrId2);
										classes.add("Hub");
									}
									sql(siteRequest).update(Cluster.class, pk).set(Cluster.VAR_hubResource, Hub.class, solrId2, val).onSuccess(a -> {
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
					case Cluster.VAR_created:
						o2.setCreated(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_created + "=$" + num);
						num++;
						bParams.add(o2.sqlCreated());
						break;
					case Cluster.VAR_clusterName:
						o2.setClusterName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_clusterName + "=$" + num);
						num++;
						bParams.add(o2.sqlClusterName());
						break;
					case Cluster.VAR_clusterResource:
						o2.setClusterResource(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_clusterResource + "=$" + num);
						num++;
						bParams.add(o2.sqlClusterResource());
						break;
					case Cluster.VAR_archived:
						o2.setArchived(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_archived + "=$" + num);
						num++;
						bParams.add(o2.sqlArchived());
						break;
					case Cluster.VAR_description:
						o2.setDescription(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_description + "=$" + num);
						num++;
						bParams.add(o2.sqlDescription());
						break;
					case Cluster.VAR_sessionId:
						o2.setSessionId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_sessionId + "=$" + num);
						num++;
						bParams.add(o2.sqlSessionId());
						break;
					case Cluster.VAR_userKey:
						o2.setUserKey(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_userKey + "=$" + num);
						num++;
						bParams.add(o2.sqlUserKey());
						break;
					case Cluster.VAR_location:
						o2.setLocation(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_location + "=$" + num);
						num++;
						bParams.add(o2.sqlLocation());
						break;
					case Cluster.VAR_id:
						o2.setId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_id + "=$" + num);
						num++;
						bParams.add(o2.sqlId());
						break;
					case Cluster.VAR_objectTitle:
						o2.setObjectTitle(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_objectTitle + "=$" + num);
						num++;
						bParams.add(o2.sqlObjectTitle());
						break;
					case Cluster.VAR_ngsildTenant:
						o2.setNgsildTenant(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_ngsildTenant + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildTenant());
						break;
					case Cluster.VAR_displayPage:
						o2.setDisplayPage(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_displayPage + "=$" + num);
						num++;
						bParams.add(o2.sqlDisplayPage());
						break;
					case Cluster.VAR_ngsildPath:
						o2.setNgsildPath(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_ngsildPath + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildPath());
						break;
					case Cluster.VAR_ngsildContext:
						o2.setNgsildContext(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_ngsildContext + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildContext());
						break;
					case Cluster.VAR_ngsildData:
						o2.setNgsildData(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_ngsildData + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildData());
						break;
					case Cluster.VAR_aiNodesTotal:
						o2.setAiNodesTotal(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_aiNodesTotal + "=$" + num);
						num++;
						bParams.add(o2.sqlAiNodesTotal());
						break;
					case Cluster.VAR_gpuDevicesTotal:
						o2.setGpuDevicesTotal(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_gpuDevicesTotal + "=$" + num);
						num++;
						bParams.add(o2.sqlGpuDevicesTotal());
						break;
					case Cluster.VAR_cpuCoresTotal:
						o2.setCpuCoresTotal(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_cpuCoresTotal + "=$" + num);
						num++;
						bParams.add(o2.sqlCpuCoresTotal());
						break;
					case Cluster.VAR_memoryBytesTotal:
						o2.setMemoryBytesTotal(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_memoryBytesTotal + "=$" + num);
						num++;
						bParams.add(o2.sqlMemoryBytesTotal());
						break;
					case Cluster.VAR_promKeycloakProxySsl:
						o2.setPromKeycloakProxySsl(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_promKeycloakProxySsl + "=$" + num);
						num++;
						bParams.add(o2.sqlPromKeycloakProxySsl());
						break;
					case Cluster.VAR_promKeycloakProxyPort:
						o2.setPromKeycloakProxyPort(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_promKeycloakProxyPort + "=$" + num);
						num++;
						bParams.add(o2.sqlPromKeycloakProxyPort());
						break;
					case Cluster.VAR_promKeycloakProxyHostName:
						o2.setPromKeycloakProxyHostName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(Cluster.VAR_promKeycloakProxyHostName + "=$" + num);
						num++;
						bParams.add(o2.sqlPromKeycloakProxyHostName());
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
						RuntimeException ex2 = new RuntimeException("value Cluster failed", ex);
						LOG.error(String.format("relateCluster failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete(o2);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPOSTCluster failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPOSTCluster failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPOSTCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200POSTCluster(Cluster o) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			if(json == null) {
				String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
				String m = String.format("%s %s not found", "OpenShift cluster", clusterResource);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200POSTCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// DELETE //

	@Override
	public void deleteCluster(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deleteCluster started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "DELETE"));
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
					if(!scopes.contains("DELETE")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(DELETE)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(DELETE)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("DELETE");
						}
					}
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
						searchClusterList(siteRequest, false, true, true).onSuccess(listCluster -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listCluster.getRequest().getRows());
								apiRequest.setNumFound(listCluster.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listCluster.first());
								apiRequest.setSolrId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getSolrId()).orElse(null));
								eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());

								listDELETECluster(apiRequest, listCluster).onSuccess(e -> {
									response200DELETECluster(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deleteCluster succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deleteCluster failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deleteCluster failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deleteCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deleteCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deleteCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deleteCluster failed. ", ex2));
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
				LOG.error(String.format("deleteCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETECluster(ApiRequest apiRequest, SearchList<Cluster> listCluster) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listCluster.getSiteRequest_(SiteRequest.class);
		listCluster.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			Cluster o2 = jsonObject.mapTo(Cluster.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deleteClusterFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETECluster failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listCluster.next().onSuccess(next -> {
				if(next) {
					listDELETECluster(apiRequest, listCluster).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETECluster failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETECluster failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETECluster failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deleteClusterFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
				searchClusterList(siteRequest, false, true, true).onSuccess(listCluster -> {
					try {
						Cluster o = listCluster.first();
						if(o != null && listCluster.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getClusterResource().toString()).orElse(null));
							apiRequest.setSolrId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getSolrId()).orElse(null));
							deleteClusterFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deleteCluster failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deleteCluster failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deleteCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deleteCluster failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<Cluster> deleteClusterFuture(Cluster o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<Cluster> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<Cluster> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsCluster(siteRequest).onSuccess(a -> {
					sqlDELETECluster(o).onSuccess(cluster -> {
						relateCluster(o).onSuccess(d -> {
							unindexCluster(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestCluster();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());
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
			}).compose(cluster -> {
				Promise<Cluster> promise2 = Promise.promise();
				refreshCluster(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(cluster -> {
				promise.complete(cluster);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deleteClusterFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETECluster(Cluster o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM Cluster ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Cluster o2 = new Cluster();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case Cluster.VAR_hubResource:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								searchModel(siteRequest).query(Hub.varIndexedHub(Hub.VAR_hubResource), Hub.class, val).onSuccess(o3 -> {
									String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
									if(solrId2 != null) {
										solrIds.add(solrId2);
										classes.add("Hub");
									}
									sql(siteRequest).update(Cluster.class, pk).set(Cluster.VAR_hubResource, Hub.class, null, null).onSuccess(a -> {
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
					RuntimeException ex2 = new RuntimeException("value Cluster failed", ex);
					LOG.error(String.format("unrelateCluster failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETECluster failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETECluster failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETECluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETECluster(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
				String m = String.format("%s %s not found", "OpenShift cluster", clusterResource);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETECluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PUTImport //

	@Override
	public void putimportCluster(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("putimportCluster started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "PUT"));
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
					if(!scopes.contains("PUT")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(PUT)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(PUT)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("PUT");
						}
					}
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
						eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());
						varsCluster(siteRequest).onSuccess(d -> {
							listPUTImportCluster(apiRequest, siteRequest).onSuccess(e -> {
								response200PUTImportCluster(siteRequest).onSuccess(response -> {
									LOG.debug(String.format("putimportCluster succeeded. "));
									eventHandler.handle(Future.succeededFuture(response));
								}).onFailure(ex -> {
									LOG.error(String.format("putimportCluster failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							}).onFailure(ex -> {
								LOG.error(String.format("putimportCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("putimportCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("putimportCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportCluster failed. ", ex2));
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
				LOG.error(String.format("putimportCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPUTImportCluster(ApiRequest apiRequest, SiteRequest siteRequest) {
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
					eventBus.request(Cluster.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportClusterFuture")).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPUTImportCluster failed. "), ex);
						promise1.fail(ex);
					});
				}));
			});
			CompositeFuture.all(futures).onSuccess(a -> {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("listPUTImportCluster failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("listPUTImportCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public void putimportClusterFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
				String clusterResource = Optional.ofNullable(body.getString(Cluster.VAR_clusterResource)).orElse(body.getString(Cluster.VAR_solrId));
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}
				pgPool.getConnection().onSuccess(sqlConnection -> {
					String sqlQuery = String.format("select * from %s WHERE clusterResource=$1", Cluster.CLASS_SIMPLE_NAME);
					sqlConnection.preparedQuery(sqlQuery)
							.execute(Tuple.tuple(Arrays.asList(clusterResource))
							).onSuccess(result -> {
						sqlConnection.close().onSuccess(a -> {
							try {
								if(result.size() >= 1) {
									Cluster o = new Cluster();
									o.setSiteRequest_(siteRequest);
									for(Row definition : result.value()) {
										for(Integer i = 0; i < definition.size(); i++) {
											try {
												String columnName = definition.getColumnName(i);
												Object columnValue = definition.getValue(i);
												o.persistForClass(columnName, columnValue);
											} catch(Exception e) {
												LOG.error(String.format("persistCluster failed. "), e);
											}
										}
									}
									Cluster o2 = new Cluster();
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
											if(!StringUtils.containsAny(f, "clusterResource", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
												body2.put("set" + StringUtils.capitalize(f), bodyVal);
										}
									}
									for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
										if(!body.fieldNames().contains(f)) {
											if(!StringUtils.containsAny(f, "clusterResource", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
												body2.putNull("set" + StringUtils.capitalize(f));
										}
									}
									if(result.size() >= 1) {
										apiRequest.setOriginal(o);
										apiRequest.setId(Optional.ofNullable(o.getClusterResource()).map(v -> v.toString()).orElse(null));
										apiRequest.setSolrId(o.getSolrId());
									}
									siteRequest.setJsonObject(body2);
									patchClusterFuture(o, true).onSuccess(b -> {
										LOG.debug("Import Cluster {} succeeded, modified Cluster. ", body.getValue(Cluster.VAR_clusterResource));
										eventHandler.handle(Future.succeededFuture());
									}).onFailure(ex -> {
										LOG.error(String.format("putimportClusterFuture failed. "), ex);
										eventHandler.handle(Future.failedFuture(ex));
									});
								} else {
									postClusterFuture(siteRequest, true).onSuccess(b -> {
										LOG.debug("Import Cluster {} succeeded, created new Cluster. ", body.getValue(Cluster.VAR_clusterResource));
										eventHandler.handle(Future.succeededFuture());
									}).onFailure(ex -> {
										LOG.error(String.format("putimportClusterFuture failed. "), ex);
										eventHandler.handle(Future.failedFuture(ex));
									});
								}
							} catch(Exception ex) {
								LOG.error(String.format("putimportClusterFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							}
						}).onFailure(ex -> {
							LOG.error(String.format("putimportClusterFuture failed. "), ex);
							eventHandler.handle(Future.failedFuture(ex));
						});
					}).onFailure(ex -> {
						LOG.error(String.format("putimportClusterFuture failed. "), ex);
						eventHandler.handle(Future.failedFuture(ex));
					});
				}).onFailure(ex -> {
					LOG.error(String.format("putimportClusterFuture failed. "), ex);
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Exception ex) {
				LOG.error(String.format("putimportClusterFuture failed. "), ex);
				eventHandler.handle(Future.failedFuture(ex));
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportCluster failed. ", ex2));
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
				LOG.error(String.format("putimportCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200PUTImportCluster(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
				String m = String.format("%s %s not found", "OpenShift cluster", clusterResource);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PUTImportCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// SearchPage //

	@Override
	public void searchpageCluster(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		oauth2AuthenticationProvider.refresh(User.create(serviceRequest.getUser())).onSuccess(user -> {
			serviceRequest.setUser(user.principal());
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "GET"));
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
					if(!scopes.contains("GET")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("GET");
						}
					}
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchClusterList(siteRequest, false, true, false).onSuccess(listCluster -> {
							response200SearchPageCluster(listCluster).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchpageCluster succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchpageCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchpageCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchpageCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchpageCluster failed. ", ex2));
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
				LOG.error(String.format("searchpageCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchpageCluster failed. ", ex2));
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
				LOG.error(String.format("searchpageCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void searchpageClusterPageInit(ClusterPage page, SearchList<Cluster> listCluster) {
	}

	public String templateSearchPageCluster(ServiceRequest serviceRequest) {
		return "en-us/search/cluster/ClusterSearchPage.htm";
	}
	public Future<ServiceResponse> response200SearchPageCluster(SearchList<Cluster> listCluster) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listCluster.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateSearchPageCluster(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			ClusterPage page = new ClusterPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listCluster.size() >= 1)
				siteRequest.setRequestPk(listCluster.get(0).getPk());
			page.setSearchListCluster_(listCluster);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepClusterPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200SearchPageCluster failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchPageCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchPageCluster(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchPageCluster(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// EditPage //

	@Override
	public void editpageCluster(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "GET"));
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
					if(!scopes.contains("GET")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("GET");
						}
					}
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchClusterList(siteRequest, false, true, false).onSuccess(listCluster -> {
							response200EditPageCluster(listCluster).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("editpageCluster succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("editpageCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("editpageCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("editpageCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("editpageCluster failed. ", ex2));
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
				LOG.error(String.format("editpageCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void editpageClusterPageInit(ClusterPage page, SearchList<Cluster> listCluster) {
	}

	public String templateEditPageCluster(ServiceRequest serviceRequest) {
		return "en-us/edit/cluster/ClusterEditPage.htm";
	}
	public Future<ServiceResponse> response200EditPageCluster(SearchList<Cluster> listCluster) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listCluster.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateEditPageCluster(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			ClusterPage page = new ClusterPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listCluster.size() >= 1)
				siteRequest.setRequestPk(listCluster.get(0).getPk());
			page.setSearchListCluster_(listCluster);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepClusterPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200EditPageCluster failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200EditPageCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotEditPageCluster(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotEditPageCluster(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// UserPage //

	@Override
	public void userpageCluster(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "GET"));
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
					if(!scopes.contains("GET")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(GET)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("GET");
						}
					}
					{
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						List<String> scopes2 = siteRequest.getScopes();
						searchClusterList(siteRequest, false, true, false).onSuccess(listCluster -> {
							response200UserPageCluster(listCluster).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("userpageCluster succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("userpageCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("userpageCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("userpageCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("userpageCluster failed. ", ex2));
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
				LOG.error(String.format("userpageCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void userpageClusterPageInit(ClusterPage page, SearchList<Cluster> listCluster) {
	}

	public String templateUserPageCluster(ServiceRequest serviceRequest) {
		return String.format("%s.htm", serviceRequest.getExtra().getString("uri").substring(1));
	}
	public Future<ServiceResponse> response200UserPageCluster(SearchList<Cluster> listCluster) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listCluster.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateUserPageCluster(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			ClusterPage page = new ClusterPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listCluster.size() >= 1)
				siteRequest.setRequestPk(listCluster.get(0).getPk());
			page.setSearchListCluster_(listCluster);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.setWebClient(webClient);
			page.setVertx(vertx);
			page.promiseDeepClusterPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200UserPageCluster failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200UserPageCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotUserPageCluster(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotUserPageCluster(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// DELETEFilter //

	@Override
	public void deletefilterCluster(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deletefilterCluster started. "));
		Boolean classPublicRead = false;
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
			String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
			String CLUSTER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("CLUSTER");
			MultiMap form = MultiMap.caseInsensitiveMultiMap();
			form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
			form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
			form.add("response_mode", "permissions");
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "GET"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "POST"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "DELETE"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PATCH"));
			form.add("permission", String.format("%s#%s", Cluster.CLASS_AUTH_RESOURCE, "PUT"));
			if(clusterResource != null)
				form.add("permission", String.format("%s#%s", clusterResource, "DELETE"));
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
					if(!scopes.contains("DELETE")) {
						//
						List<String> fqs = new ArrayList<>();
						List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?HUB-(.*))-(DELETE)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "hubResource", value));
								});
						groups.stream().map(group -> {
									Matcher mPermission = Pattern.compile("^/(.*-?CLUSTER-(.*))-(DELETE)$").matcher(group);
									return mPermission.find() ? mPermission.group(1) : null;
								}).filter(v -> v != null).forEach(value -> {
									fqs.add(String.format("%s:%s", "clusterResource", value));
								});
						JsonObject authParams = siteRequest.getServiceRequest().getParams();
						JsonObject authQuery = authParams.getJsonObject("query");
						if(authQuery == null) {
							authQuery = new JsonObject();
							authParams.put("query", authQuery);
						}
						JsonArray fq = authQuery.getJsonArray("fq");
						if(fq == null) {
							fq = new JsonArray();
							authQuery.put("fq", fq);
						}
						if(fqs.size() > 0) {
							fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
							scopes.add("DELETE");
						}
					}
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
						searchClusterList(siteRequest, false, true, true).onSuccess(listCluster -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listCluster.getRequest().getRows());
								apiRequest.setNumFound(listCluster.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listCluster.first());
								apiRequest.setSolrId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getSolrId()).orElse(null));
								eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());

								listDELETEFilterCluster(apiRequest, listCluster).onSuccess(e -> {
									response200DELETEFilterCluster(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deletefilterCluster succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deletefilterCluster failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deletefilterCluster failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deletefilterCluster failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deletefilterCluster failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deletefilterCluster failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deletefilterCluster failed. ", ex2));
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
				LOG.error(String.format("deletefilterCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEFilterCluster(ApiRequest apiRequest, SearchList<Cluster> listCluster) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listCluster.getSiteRequest_(SiteRequest.class);
		listCluster.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			siteRequest2.setScopes(siteRequest.getScopes());
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			Cluster o2 = jsonObject.mapTo(Cluster.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deletefilterClusterFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEFilterCluster failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listCluster.next().onSuccess(next -> {
				if(next) {
					listDELETEFilterCluster(apiRequest, listCluster).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEFilterCluster failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEFilterCluster failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEFilterCluster failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deletefilterClusterFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
				searchClusterList(siteRequest, false, true, true).onSuccess(listCluster -> {
					try {
						Cluster o = listCluster.first();
						if(o != null && listCluster.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getClusterResource().toString()).orElse(null));
							apiRequest.setSolrId(Optional.ofNullable(listCluster.first()).map(o2 -> o2.getSolrId()).orElse(null));
							deletefilterClusterFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deletefilterCluster failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deletefilterCluster failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deletefilterCluster failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deletefilterCluster failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<Cluster> deletefilterClusterFuture(Cluster o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<Cluster> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<Cluster> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsCluster(siteRequest).onSuccess(a -> {
					sqlDELETEFilterCluster(o).onSuccess(cluster -> {
						relateCluster(o).onSuccess(d -> {
							unindexCluster(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestCluster();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketCluster", JsonObject.mapFrom(apiRequest).toString());
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
			}).compose(cluster -> {
				Promise<Cluster> promise2 = Promise.promise();
				refreshCluster(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(cluster -> {
				promise.complete(cluster);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deletefilterClusterFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEFilterCluster(Cluster o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM Cluster ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Cluster o2 = new Cluster();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case Cluster.VAR_hubResource:
						Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
							futures1.add(Future.future(promise2 -> {
								searchModel(siteRequest).query(Hub.varIndexedHub(Hub.VAR_hubResource), Hub.class, val).onSuccess(o3 -> {
									String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
									if(solrId2 != null) {
										solrIds.add(solrId2);
										classes.add("Hub");
									}
									sql(siteRequest).update(Cluster.class, pk).set(Cluster.VAR_hubResource, Hub.class, null, null).onSuccess(a -> {
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
					RuntimeException ex2 = new RuntimeException("value Cluster failed", ex);
					LOG.error(String.format("unrelateCluster failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEFilterCluster failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEFilterCluster failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEFilterCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEFilterCluster(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String clusterResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("clusterResource");
				String m = String.format("%s %s not found", "OpenShift cluster", clusterResource);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEFilterCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// General //

	public Future<Cluster> createCluster(SiteRequest siteRequest) {
		Promise<Cluster> promise = Promise.promise();
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long userKey = siteRequest.getUserKey();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

			sqlConnection.preparedQuery("INSERT INTO Cluster(created, userKey) VALUES($1, $2) RETURNING pk")
					.collecting(Collectors.toList())
					.execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
				Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				Cluster o = new Cluster();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				promise.complete(o);
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error("createCluster failed. ", ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("createCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public void searchClusterQ(SearchList<Cluster> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
		}
	}

	public String searchClusterFq(SearchList<Cluster> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		if(StringUtils.startsWith(valueIndexed, "[")) {
			String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
			if(fqs.length != 2)
				throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
			String fq1 = fqs[0].equals("*") ? fqs[0] : Cluster.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
			String fq2 = fqs[1].equals("*") ? fqs[1] : Cluster.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
			 return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
		} else {
			return varIndexed + ":" + SearchTool.escapeQueryChars(Cluster.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
		}
	}

	public void searchClusterSort(SearchList<Cluster> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.sort(varIndexed, valueIndexed);
	}

	public void searchClusterRows(SearchList<Cluster> searchList, Long valueRows) {
			searchList.rows(valueRows != null ? valueRows : 10L);
	}

	public void searchClusterStart(SearchList<Cluster> searchList, Long valueStart) {
		searchList.start(valueStart);
	}

	public void searchClusterVar(SearchList<Cluster> searchList, String var, String value) {
		searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
	}

	public void searchClusterUri(SearchList<Cluster> searchList) {
	}

	public Future<ServiceResponse> varsCluster(SiteRequest siteRequest) {
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
					LOG.error(String.format("searchCluster failed. "), ex);
					promise.fail(ex);
				}
			});
			promise.complete();
		} catch(Exception ex) {
			LOG.error(String.format("searchCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<SearchList<Cluster>> searchClusterList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify) {
		Promise<SearchList<Cluster>> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();
			String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<Cluster> searchList = new SearchList<Cluster>();
			String facetRange = null;
			Date facetRangeStart = null;
			Date facetRangeEnd = null;
			String facetRangeGap = null;
			String statsField = null;
			String statsFieldIndexed = null;
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.q("*:*");
			searchList.setC(Cluster.class);
			searchList.setSiteRequest_(siteRequest);
			searchList.facetMinCount(1);
			if(entityList != null) {
				for(String v : entityList) {
					searchList.fl(Cluster.varIndexedCluster(v));
				}
			}

			String clusterResource = serviceRequest.getParams().getJsonObject("path").getString("clusterResource");
			if(clusterResource != null) {
				searchList.fq("clusterResource_docvalues_string:" + SearchTool.escapeQueryChars(clusterResource));
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
								varsIndexed[i] = Cluster.varIndexedCluster(entityVar);
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
									varIndexed = Cluster.varIndexedCluster(entityVar);
									String entityQ = searchClusterFq(searchList, entityVar, valueIndexed, varIndexed);
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
									varIndexed = Cluster.varIndexedCluster(entityVar);
									String entityFq = searchClusterFq(searchList, entityVar, valueIndexed, varIndexed);
									mFq.appendReplacement(sb, entityFq);
								}
								if(!sb.isEmpty()) {
									mFq.appendTail(sb);
									searchList.fq(sb.toString());
								}
							} else if(paramName.equals("sort")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = Cluster.varIndexedCluster(entityVar);
								searchClusterSort(searchList, entityVar, valueIndexed, varIndexed);
							} else if(paramName.equals("start")) {
								valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchClusterStart(searchList, valueStart);
							} else if(paramName.equals("rows")) {
								valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchClusterRows(searchList, valueRows);
							} else if(paramName.equals("stats")) {
								searchList.stats((Boolean)paramObject);
							} else if(paramName.equals("stats.field")) {
								Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								if(mStats.find()) {
									String solrLocalParams = mStats.group(1);
									entityVar = mStats.group(2).trim();
									varIndexed = Cluster.varIndexedCluster(entityVar);
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
									varIndexed = Cluster.varIndexedCluster(entityVar);
									searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									facetRange = entityVar;
								}
							} else if(paramName.equals("facet.field")) {
								entityVar = (String)paramObject;
								varIndexed = Cluster.varIndexedCluster(entityVar);
								if(varIndexed != null)
									searchList.facetField(varIndexed);
							} else if(paramName.equals("var")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								searchClusterVar(searchList, entityVar, valueIndexed);
							} else if(paramName.equals("cursorMark")) {
								valueCursorMark = (String)paramObject;
								searchList.cursorMark((String)paramObject);
							}
						}
						searchClusterUri(searchList);
					}
				} catch(Exception e) {
					ExceptionUtils.rethrow(e);
				}
			}
			if("*:*".equals(searchList.getQuery()) && searchList.getSorts().size() == 0) {
				searchList.sort("hubId_docvalues_string", "asc");
				searchList.sort("clusterName_docvalues_string", "asc");
				searchList.setDefaultSort(true);
			}
			String facetRange2 = facetRange;
			Date facetRangeStart2 = facetRangeStart;
			Date facetRangeEnd2 = facetRangeEnd;
			String facetRangeGap2 = facetRangeGap;
			String statsField2 = statsField;
			String statsFieldIndexed2 = statsFieldIndexed;
			searchCluster2(siteRequest, populate, store, modify, searchList);
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
						LOG.error(String.format("searchCluster failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete(searchList);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("searchCluster failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("searchCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void searchCluster2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<Cluster> searchList) {
	}

	public Future<Void> persistCluster(Cluster o, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.preparedQuery("SELECT hubId, hubResource, created, clusterName, clusterResource, archived, description, sessionId, userKey, location, id, objectTitle, ngsildTenant, displayPage, ngsildPath, ngsildContext, ngsildData, aiNodesTotal, gpuDevicesTotal, cpuCoresTotal, memoryBytesTotal, promKeycloakProxySsl, promKeycloakProxyPort, promKeycloakProxyHostName FROM Cluster WHERE pk=$1")
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
									LOG.error(String.format("persistCluster failed. "), e);
								}
							}
						}
					}
					o.promiseDeepForClass(siteRequest).onSuccess(a -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("persistCluster failed. "), ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error(String.format("persistCluster failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("persistCluster failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("persistCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> relateCluster(Cluster o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			sqlConnection.preparedQuery("SELECT hubResource as pk2, 'hubResource' from Hub where hubResource=$1")
					.collecting(Collectors.toList())
					.execute(Tuple.of(o.getHubResource())
					).onSuccess(result -> {
				try {
					if(result != null) {
						for(Row definition : result.value()) {
							o.relateForClass(definition.getString(1), definition.getValue(0));
						}
					}
					promise.complete();
				} catch(Exception ex) {
					LOG.error(String.format("relateCluster failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("relateCluster failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("relateCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public String searchVar(String varIndexed) {
		return Cluster.searchVarCluster(varIndexed);
	}

	@Override
	public String getClassApiAddress() {
		return Cluster.CLASS_API_ADDRESS_Cluster;
	}

	public Future<Cluster> indexCluster(Cluster o) {
		Promise<Cluster> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = new JsonObject();
			JsonObject add = new JsonObject();
			json.put("add", add);
			JsonObject doc = new JsonObject();
			add.put("doc", doc);
			o.indexCluster(doc);
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
				LOG.error(String.format("indexCluster failed. "), new RuntimeException(ex));
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("indexCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Cluster> unindexCluster(Cluster o) {
		Promise<Cluster> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			o.promiseDeepForClass(siteRequest).onSuccess(a -> {
				JsonObject json = new JsonObject();
				JsonObject delete = new JsonObject();
				json.put("delete", delete);
				String query = String.format("filter(%s:%s)", Cluster.VAR_solrId, o.obtainForClass(Cluster.VAR_solrId));
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
					LOG.error(String.format("unindexCluster failed. "), new RuntimeException(ex));
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("unindexCluster failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("unindexCluster failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> refreshCluster(Cluster o) {
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

					if("Hub".equals(classSimpleName2) && solrId2 != null) {
						SearchList<Hub> searchList2 = new SearchList<Hub>();
						searchList2.setStore(true);
						searchList2.q("*:*");
						searchList2.setC(Hub.class);
						searchList2.fq("solrId:" + solrId2);
						searchList2.rows(1L);
						futures.add(Future.future(promise2 -> {
							searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
								Hub o2 = searchList2.getList().stream().findFirst().orElse(null);
								if(o2 != null) {
									JsonObject params = new JsonObject();
									params.put("body", new JsonObject());
									params.put("cookie", new JsonObject());
									params.put("path", new JsonObject());
									params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("solrId:" + solrId2)).put("var", new JsonArray().add("refresh:false")));
									JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
									JsonObject json = new JsonObject().put("context", context);
									eventBus.request("ai-telemetry-enUS-Hub", json, new DeliveryOptions().addHeader("action", "patchHubFuture")).onSuccess(c -> {
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
					eventBus.request(Cluster.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchClusterFuture")).onSuccess(c -> {
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
			LOG.error(String.format("refreshCluster failed. "), ex);
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
			Cluster page = new Cluster();
			page.setSiteRequest_((SiteRequest)siteRequest);

			page.persistForClass(Cluster.VAR_hubId, Cluster.staticSetHubId(siteRequest2, (String)result.get(Cluster.VAR_hubId)));
			page.persistForClass(Cluster.VAR_hubResource, Cluster.staticSetHubResource(siteRequest2, (String)result.get(Cluster.VAR_hubResource)));
			page.persistForClass(Cluster.VAR_created, Cluster.staticSetCreated(siteRequest2, (String)result.get(Cluster.VAR_created), Optional.ofNullable(siteRequest).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))));
			page.persistForClass(Cluster.VAR_clusterName, Cluster.staticSetClusterName(siteRequest2, (String)result.get(Cluster.VAR_clusterName)));
			page.persistForClass(Cluster.VAR_clusterResource, Cluster.staticSetClusterResource(siteRequest2, (String)result.get(Cluster.VAR_clusterResource)));
			page.persistForClass(Cluster.VAR_archived, Cluster.staticSetArchived(siteRequest2, (String)result.get(Cluster.VAR_archived)));
			page.persistForClass(Cluster.VAR_description, Cluster.staticSetDescription(siteRequest2, (String)result.get(Cluster.VAR_description)));
			page.persistForClass(Cluster.VAR_sessionId, Cluster.staticSetSessionId(siteRequest2, (String)result.get(Cluster.VAR_sessionId)));
			page.persistForClass(Cluster.VAR_userKey, Cluster.staticSetUserKey(siteRequest2, (String)result.get(Cluster.VAR_userKey)));
			page.persistForClass(Cluster.VAR_location, Cluster.staticSetLocation(siteRequest2, (String)result.get(Cluster.VAR_location)));
			page.persistForClass(Cluster.VAR_id, Cluster.staticSetId(siteRequest2, (String)result.get(Cluster.VAR_id)));
			page.persistForClass(Cluster.VAR_objectTitle, Cluster.staticSetObjectTitle(siteRequest2, (String)result.get(Cluster.VAR_objectTitle)));
			page.persistForClass(Cluster.VAR_ngsildTenant, Cluster.staticSetNgsildTenant(siteRequest2, (String)result.get(Cluster.VAR_ngsildTenant)));
			page.persistForClass(Cluster.VAR_displayPage, Cluster.staticSetDisplayPage(siteRequest2, (String)result.get(Cluster.VAR_displayPage)));
			page.persistForClass(Cluster.VAR_ngsildPath, Cluster.staticSetNgsildPath(siteRequest2, (String)result.get(Cluster.VAR_ngsildPath)));
			page.persistForClass(Cluster.VAR_ngsildContext, Cluster.staticSetNgsildContext(siteRequest2, (String)result.get(Cluster.VAR_ngsildContext)));
			page.persistForClass(Cluster.VAR_ngsildData, Cluster.staticSetNgsildData(siteRequest2, (String)result.get(Cluster.VAR_ngsildData)));
			page.persistForClass(Cluster.VAR_aiNodesTotal, Cluster.staticSetAiNodesTotal(siteRequest2, (String)result.get(Cluster.VAR_aiNodesTotal)));
			page.persistForClass(Cluster.VAR_gpuDevicesTotal, Cluster.staticSetGpuDevicesTotal(siteRequest2, (String)result.get(Cluster.VAR_gpuDevicesTotal)));
			page.persistForClass(Cluster.VAR_cpuCoresTotal, Cluster.staticSetCpuCoresTotal(siteRequest2, (String)result.get(Cluster.VAR_cpuCoresTotal)));
			page.persistForClass(Cluster.VAR_memoryBytesTotal, Cluster.staticSetMemoryBytesTotal(siteRequest2, (String)result.get(Cluster.VAR_memoryBytesTotal)));
			page.persistForClass(Cluster.VAR_promKeycloakProxySsl, Cluster.staticSetPromKeycloakProxySsl(siteRequest2, (String)result.get(Cluster.VAR_promKeycloakProxySsl)));
			page.persistForClass(Cluster.VAR_promKeycloakProxyPort, Cluster.staticSetPromKeycloakProxyPort(siteRequest2, (String)result.get(Cluster.VAR_promKeycloakProxyPort)));
			page.persistForClass(Cluster.VAR_promKeycloakProxyHostName, Cluster.staticSetPromKeycloakProxyHostName(siteRequest2, (String)result.get(Cluster.VAR_promKeycloakProxyHostName)));

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
