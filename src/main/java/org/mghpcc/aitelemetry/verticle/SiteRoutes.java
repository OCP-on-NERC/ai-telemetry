package org.mghpcc.aitelemetry.verticle;

import java.util.Map.Entry;

import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.user.SiteUser;
import org.mghpcc.aitelemetry.user.SiteUserEnUSApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;

public class SiteRoutes {
	protected static final Logger LOG = LoggerFactory.getLogger(SiteRoutes.class);
  
  public static void routes(Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, SiteUserEnUSApiServiceImpl apiSiteUser) {
		router.getWithRegex("\\/prom-keycloak-proxy(?<uri>.*)").handler(oauth2AuthHandler).handler(handler -> {
			String originalUri = handler.pathParam("uri");
			ServiceRequest serviceRequest = apiSiteUser.generateServiceRequest(handler);
			apiSiteUser.user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_ComputateSiteUser, "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
				try {

					String uri = handler.pathParam("uri");

					Integer promKeycloakProxyPort = Integer.parseInt(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_PORT));
					String promKeycloakProxyHostName = config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_HOST_NAME);
					Boolean promKeycloakProxySsl = Boolean.parseBoolean(config.getString(ConfigKeys.PROM_KEYCLOAK_PROXY_SSL));

					HttpRequest<Buffer> get = webClient.get(promKeycloakProxyPort, promKeycloakProxyHostName, uri).ssl(promKeycloakProxySsl);
					for(Entry<String, String> entry : handler.queryParams()) {
						String paramName = entry.getKey();
						String paramObject = entry.getValue();
						get.addQueryParam(paramName, paramObject);
					}
					get
							.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUserPrincipal().getString("access_token")))
							.send()
							.expecting(HttpResponseExpectation.SC_OK)
							.onSuccess(metricsResponse -> {
						handler.response().putHeader("Content-Type", "application/json");
						handler.end(metricsResponse.bodyAsJsonObject().toBuffer());
					}).onFailure(ex -> {
						LOG.error("Failed to query the prom-keycloak-proxy. ", ex);
						handler.fail(403, ex);
					});
				} catch(Exception ex) {
					LOG.error("Failed to load page. ", ex);
					handler.fail(ex);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("Failed to render page %s", originalUri), ex);
				handler.fail(ex);
			});
		});
  }
}
