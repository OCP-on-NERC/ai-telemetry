package org.mghpcc.aitelemetry.model.gpuslice;

import io.vertx.ext.web.client.WebClient;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.serviceproxy.ServiceBinder;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.service.WebApiServiceGen;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import com.hubspot.jinjava.Jinjava;
import io.vertx.core.WorkerExecutor;
import io.vertx.pgclient.PgPool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.authorization.AuthorizationProvider;

/**
 * Translate: false
 * Gen: false
 * Generated: true
 **/
@WebApiServiceGen
@ProxyGen
public interface GpuSliceEnUSGenApiService {
	static GpuSliceEnUSApiServiceImpl registerService(Vertx vertx, JsonObject config, WorkerExecutor workerExecutor, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, PgPool pgPool, KafkaProducer<String, String> kafkaProducer, MqttClient mqttClient, AmqpSender amqpSender, RabbitMQClient rabbitmqClient, WebClient webClient, OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider, Jinjava jinjava) {
		GpuSliceEnUSApiServiceImpl api =  new GpuSliceEnUSApiServiceImpl(vertx, config, workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
		new ServiceBinder(vertx).setAddress(GpuSlice.getClassApiAddress()).register(GpuSliceEnUSGenApiService.class, api);
		return api;
	}

	public void searchGpuSlice(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void getGpuSlice(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void patchGpuSlice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void patchGpuSliceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void postGpuSlice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void postGpuSliceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void deleteGpuSlice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void deleteGpuSliceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void putimportGpuSlice(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void putimportGpuSliceFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void searchpageGpuSliceId(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void searchpageGpuSlice(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
}
