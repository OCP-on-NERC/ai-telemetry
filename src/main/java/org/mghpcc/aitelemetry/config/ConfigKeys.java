
package org.mghpcc.aitelemetry.config;

import org.computate.vertx.config.ComputateConfigKeys;

import io.vertx.core.json.JsonObject;

/**
 * Keyword: classSimpleNameConfigKeys
 */
public class ConfigKeys extends ComputateConfigKeys {

	public static JsonObject getPageContext(JsonObject config) {
        JsonObject ctx = ComputateConfigKeys.getPageContext(config);
        ctx.put(ENABLE_FEATURE_OBSERVABILITY, config.getString(ENABLE_FEATURE_OBSERVABILITY));
        ctx.put(ENABLE_FEATURE_ORDER_BARE_METAL, config.getString(ENABLE_FEATURE_ORDER_BARE_METAL));
        ctx.put(ENABLE_FEATURE_ORDER_CLUSTER, config.getString(ENABLE_FEATURE_ORDER_CLUSTER));
        return ctx;
    }

    public static final String ENABLE_FEATURE_OBSERVABILITY = "ENABLE_FEATURE_OBSERVABILITY";
    public static final String ENABLE_FEATURE_ORDER_BARE_METAL = "ENABLE_FEATURE_ORDER_BARE_METAL";
    public static final String ENABLE_FEATURE_ORDER_CLUSTER = "ENABLE_FEATURE_ORDER_CLUSTER";

    public static final String PROM_KEYCLOAK_PROXY_PORT = "PROM_KEYCLOAK_PROXY_PORT";
    public static final String PROM_KEYCLOAK_PROXY_HOST_NAME = "PROM_KEYCLOAK_PROXY_HOST_NAME";
    public static final String PROM_KEYCLOAK_PROXY_SSL = "PROM_KEYCLOAK_PROXY_SSL";
    public static final String GRAFANA_BASE_URL = "GRAFANA_BASE_URL";
    public static final String AUTH_CLIENT_SA = "AUTH_CLIENT_SA";
    public static final String AUTH_SECRET_SA = "AUTH_SECRET_SA";
    public static final String FULFILLMENT_API_HOST_NAME = "FULFILLMENT_API_HOST_NAME";
    public static final String FULFILLMENT_API_SSL = "FULFILLMENT_API_SSL";
    public static final String FULFILLMENT_API_PORT = "FULFILLMENT_API_PORT";
    public static final String FULFILLMENT_API_OPENSHIFT_SECRET = "FULFILLMENT_API_OPENSHIFT_SECRET";
    public static final String FULFILLMENT_API_OPENSHIFT_TOKEN = "FULFILLMENT_API_OPENSHIFT_TOKEN";
    public static final String ESI_API_HOST_NAME = "ESI_API_HOST_NAME";
    public static final String ESI_API_SSL = "ESI_API_SSL";
    public static final String ESI_API_PORT = "ESI_API_PORT";
    public static final String ESI_API_OPENSHIFT_SECRET = "ESI_API_OPENSHIFT_SECRET";
    public static final String ESI_API_OPENSHIFT_TOKEN = "ESI_API_OPENSHIFT_TOKEN";
    public static final String KAFKA_TOPIC_FULFILL_OFFER = "KAFKA_TOPIC_FULFILL_OFFER";
    public static final String KAFKA_TOPIC_ORDER_LOOP = "KAFKA_TOPIC_ORDER_LOOP";
    public static final String KAFKA_TOPIC_ORDER_STATUS = "KAFKA_TOPIC_ORDER_STATUS";
    public static final String IMPORT_LIMIT = "IMPORT_LIMIT";
    public static final String IMPORT_OFFSET = "IMPORT_OFFSET";
    public static final String IMPORT_CLEANUP_DELAY_SECONDS = "IMPORT_CLEANUP_DELAY_SECONDS";
    public static final String ENABLE_THIN_UI = "ENABLE_THIN_UI";
}
