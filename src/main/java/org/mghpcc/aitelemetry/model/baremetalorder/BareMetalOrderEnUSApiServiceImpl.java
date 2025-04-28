package org.mghpcc.aitelemetry.model.baremetalorder;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import java.util.Optional;

import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
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
public class BareMetalOrderEnUSApiServiceImpl extends BareMetalOrderEnUSGenApiServiceImpl {

    @Override
    public Future<BareMetalOrder> postBareMetalOrderFuture(SiteRequest siteRequest, Boolean pk) {
		Promise<BareMetalOrder> promise = Promise.promise();
        super.postBareMetalOrderFuture(siteRequest, pk).onSuccess(bareMetalOrder -> {
		    try {
		    	Integer esiApiPort = Integer.parseInt(config.getString(ConfigKeys.ESI_API_PORT));
		    	String esiApiHostName = config.getString(ConfigKeys.ESI_API_HOST_NAME);
		    	Boolean esiApiSsl = Boolean.parseBoolean(config.getString(ConfigKeys.ESI_API_SSL));
		    	String esiApiUri = String.format("/api/v1/baremetal-order/fulfill");

                // curl -X POST http://localhost:8081/api/v1/baremetal-order/fulfill \
                //   -H "Content-Type: application/json" \
                //   -d '{"order_id": "123_xyz",
                //        "network_id": "provisioning",
                //        "nodes": [{"resource_class": "fc430", "number": 1}]
                //   }'
                String solrId = bareMetalOrder.getSolrId();
                String networkId = bareMetalOrder.getNetworkId();
                JsonArray nodes = new JsonArray();

                Optional.ofNullable(bareMetalOrder.getNumberOfFc430()).filter(v -> v > 0).ifPresent(v -> {
                    nodes.add(new JsonObject().put("resource_class", "fc430").put("number", v));
                });
                Optional.ofNullable(bareMetalOrder.getNumberOfFc830()).filter(v -> v > 0).ifPresent(v -> {
                    nodes.add(new JsonObject().put("resource_class", "fc830").put("number", v));
                });
                Optional.ofNullable(bareMetalOrder.getNumberOfLenovoSd650nv2A100()).filter(v -> v > 0).ifPresent(v -> {
                    nodes.add(new JsonObject().put("resource_class", "lenovo-sd650nv2-a100").put("number", v));
                });
                Optional.ofNullable(bareMetalOrder.getNumberOfLenovoSd665nv3H100()).filter(v -> v > 0).ifPresent(v -> {
                    nodes.add(new JsonObject().put("resource_class", "lenovo-sd665nv3-h100").put("number", v));
                });
                Optional.ofNullable(bareMetalOrder.getNumberOfR730xd()).filter(v -> v > 0).ifPresent(v -> {
                    nodes.add(new JsonObject().put("resource_class", "r730xd").put("number", v));
                });
                Optional.ofNullable(bareMetalOrder.getNumberOfWhiteboxFlax1()).filter(v -> v > 0).ifPresent(v -> {
                    nodes.add(new JsonObject().put("resource_class", "whitebox-flax-1").put("number", v));
                });

                JsonObject body = new JsonObject();
                body.put("order_id", solrId);
                body.put("network_id", networkId);
                body.put("nodes", nodes);
		    	webClient.post(esiApiPort, esiApiHostName, esiApiUri).ssl(esiApiSsl)
		    			.sendJsonObject(body)
		    			.expecting(HttpResponseExpectation.SC_OK)
		    			.onSuccess(templatesResponse -> {
		    		promise.complete(bareMetalOrder);
		    	}).onFailure(ex -> {
			        LOG.error(String.format("postBareMetalOrderFuture to ESI failed. "), ex);
			        promise.fail(ex);
		    	});
		    } catch(Throwable ex) {
			    LOG.error(String.format("Preparing postBareMetalOrderFuture request to ESI failed. "), ex);
			    promise.fail(ex);
		    }
        }).onFailure(ex -> {
			LOG.error(String.format("postBareMetalOrderFuture failed. "), ex);
			promise.fail(ex);
        });
		return promise.future();
    }
}
