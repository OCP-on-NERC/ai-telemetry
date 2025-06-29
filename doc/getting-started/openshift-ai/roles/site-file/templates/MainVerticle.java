package {{ SITE_MAIN_VERTICLE_PACKAGE }};

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.serialize.ComputateLocalTimeSerializer;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.tool.SearchTool;
import org.computate.vertx.api.BaseApiServiceImpl;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.model.base.ComputateBaseModel;
import org.computate.vertx.model.user.ComputateSiteUser;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.openapi.OpenApi3Generator;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.result.base.ComputateBaseResult;
import org.computate.vertx.search.list.SearchList;
import org.computate.vertx.verticle.EmailVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.loader.FileLocator;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.LongCounterBuilder;
import io.opentelemetry.api.metrics.MeterBuilder;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.MetricReader;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.vertx.amqp.AmqpClient;
import io.vertx.amqp.AmqpClientOptions;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.rabbitmq.RabbitMQOptions;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
{% if SQUARE_ACCESS_TOKEN is defined %}
import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.api.CheckoutApi;
import com.squareup.square.api.CustomersApi;
import com.squareup.square.api.EventsApi;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.authentication.BearerAuthModel;
import com.squareup.square.models.Customer;
import com.squareup.square.models.GetPaymentResponse;
import com.squareup.square.models.Order;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderLineItemModifier;
import com.squareup.square.models.Payment;
import com.squareup.square.models.RetrieveCustomerResponse;
import com.squareup.square.models.RetrieveOrderResponse;
import com.squareup.square.models.Tender;
import com.squareup.square.models.UpdateOrderRequest;
import com.squareup.square.utilities.WebhooksHelper;
{% endif %}

import io.vertx.amqp.AmqpMessage;
import io.vertx.amqp.AmqpMessageBuilder;
import io.vertx.amqp.AmqpSenderOptions;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeInfo;
import io.vertx.core.streams.Pump;
import io.vertx.core.tracing.TracingPolicy;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2Options;
import io.vertx.ext.auth.oauth2.authorization.KeycloakAuthorization;
import io.vertx.ext.auth.oauth2.impl.OAuth2AuthProviderImpl;
import io.vertx.ext.auth.oauth2.providers.OpenIDConnectAuth;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.impl.RoutingContextImpl;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Tuple;
import io.vertx.tracing.opentelemetry.OpenTelemetryOptions;

import {{ SITE_CONFIG_KEYS_PACKAGE }}.ConfigKeys;
import {{ SITE_REQUEST_PACKAGE }}.SiteRequest;
import {{ SITE_PAGE_LAYOUT_PACKAGE }}.SitePage;
import {{ SITE_PAGE_LAYOUT_PACKAGE }}.SitePageEnUSGenApiService;
import {{ SITE_USER_PACKAGE }}.SiteUser;
import {{ SITE_USER_PACKAGE }}.SiteUserEnUSGenApiService;
import {{ SITE_USER_PACKAGE }}.SiteUserEnUSGenApiServiceImpl;
{% for JAVA_MODEL in JAVA_MODELS %}
import {{ JAVA_MODEL.classeNomCanoniqueGenApiService_enUS_stored_string }};
import {{ JAVA_MODEL.classeNomCanoniqueApiServiceImpl_enUS_stored_string }};
{% if JAVA_MODEL.classePageTemplates_enUS_stored_string is defined or JAVA_MODEL.classePageTemplatesUtilisateur_enUS_stored_string is defined %}
import {{ JAVA_MODEL.classeNomCanonique_enUS_stored_string }};
{% endif %}
{% endfor %}


/**
 * Description: A Java class to start the Vert.x application as a main method. 
 * Keyword: classSimpleNameVerticle
 **/
public class MainVerticle extends AbstractVerticle {
	private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

	private Integer siteInstances;
	private Integer workerPoolSize;
	private Integer jdbcMaxPoolSize; 
	private Integer jdbcMaxWaitQueueSize;

	LocalSessionStore sessionStore;
	SessionHandler sessionHandler;
	ComputateOAuth2AuthHandlerImpl oauth2AuthHandler;

	/**
	 * A io.vertx.ext.jdbc.JDBCClient for connecting to the relational database PostgreSQL. 
	 **/
	private PgPool pgPool;

	private WebClient webClient;

	private Router router;

	private WorkerExecutor workerExecutor;

	private OAuth2Auth oauth2AuthenticationProvider;

	private AuthorizationProvider authorizationProvider;

	private KafkaProducer<String, String> kafkaProducer;

	private MqttClient mqttClient;

	private AmqpClient amqpClient;

	private AmqpSender amqpSender;

	private RabbitMQClient rabbitmqClient;

	private Jinjava jinjava;

{% if SQUARE_ACCESS_TOKEN is defined %}
	private SquareClient squareClient;
{% endif %}

	/**	
	 *	The main method for the Vert.x application that runs the Vert.x Runner class
	 **/
	public static void  main(String[] args) {
		Vertx vertx = Vertx.vertx();
		String configVarsPath = System.getenv(ConfigKeys.VARS_PATH);
		configureConfig(vertx).onSuccess(config -> {
			try {
				Future<Void> originalFuture = Future.future(a -> a.complete());
				Future<Void> future = originalFuture;
				WebClient webClient = WebClient.create(vertx, new WebClientOptions().setVerifyHost(false).setTrustAll(true));
				Boolean runOpenApi3Generator = Optional.ofNullable(config.getBoolean(ConfigKeys.RUN_OPENAPI3_GENERATOR)).orElse(false);
				Boolean runSqlGenerator = Optional.ofNullable(config.getBoolean(ConfigKeys.RUN_SQL_GENERATOR)).orElse(false);
				Boolean runArticleGenerator = Optional.ofNullable(config.getBoolean(ConfigKeys.RUN_ARTICLE_GENERATOR)).orElse(false);
				Boolean runFiwareGenerator = Optional.ofNullable(config.getBoolean(ConfigKeys.RUN_FIWARE_GENERATOR)).orElse(false);
				Boolean runProjectGenerator = Optional.ofNullable(config.getBoolean(ConfigKeys.RUN_PROJECT_GENERATOR)).orElse(false);

				if(runOpenApi3Generator || runSqlGenerator || runArticleGenerator || runFiwareGenerator || runProjectGenerator) {
					SiteRequest siteRequest = new SiteRequest();
					siteRequest.setConfig(config);
					siteRequest.setWebClient(webClient);
					siteRequest.initDeepSiteRequest();
					OpenApi3Generator api = new OpenApi3Generator();
					api.setVertx_(vertx);
					api.setWebClient(webClient);
					api.setConfig(config);
					api.initDeepOpenApi3Generator(siteRequest);
					if(runOpenApi3Generator)
						future = future.compose(a -> api.writeOpenApi());
					if(runSqlGenerator)
						future = future.compose(a -> api.writeSql());
					if(runArticleGenerator)
						future = future.compose(a -> api.writeArticle());
					if(runFiwareGenerator)
						future = future.compose(a -> api.writeFiware());
					if(runProjectGenerator)
						future = future.compose(a -> api.writeProject());
					future.onComplete(a -> {
						vertx.close();
						System.exit(0);
					});
				} else {
					future = future.compose(a -> run(config).onSuccess(b -> {
						LOG.info("MainVerticle run completed");
					}).onFailure(ex -> {
						LOG.info("MainVerticle run failed");
						vertx.close();
						System.exit(0);
					}));
				}
			} catch(Exception ex) {
				LOG.error(String.format("Error loading config: %s", configVarsPath), ex);
				vertx.close();
			}
		}).onFailure(ex -> {
			LOG.error(String.format("Error loading config: %s", configVarsPath), ex);
			vertx.close();
		});
	}

	public static void  runOpenApi3Generator(String[] args, Vertx vertx, JsonObject config) {
		OpenApi3Generator api = new OpenApi3Generator();
		WebClient webClient = WebClient.create(vertx, new WebClientOptions().setVerifyHost(false).setTrustAll(true));
		SiteRequest siteRequest = new SiteRequest();
		siteRequest.setConfig(config);
		siteRequest.setWebClient(webClient);
		api.setWebClient(webClient);
		api.setConfig(config);
		siteRequest.initDeepSiteRequest();
		api.initDeepOpenApi3Generator(siteRequest);
		api.writeOpenApi().onSuccess(a -> {
			LOG.info("Write OpenAPI completed. ");
			vertx.close();
		}).onFailure(ex -> {
			LOG.error("Write OpenAPI failed. ", ex);
			vertx.close();
		});
	}

	public static Future<Void> runVerticles(Vertx vertx, JsonObject config) {
		Promise<Void> promise = Promise.promise();
		try {
			Long vertxWarningExceptionSeconds = config.getLong(ConfigKeys.VERTX_WARNING_EXCEPTION_SECONDS);
			Long vertxMaxEventLoopExecuteTime = config.getLong(ConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME);
			Long vertxMaxWorkerExecuteTime = config.getLong(ConfigKeys.VERTX_MAX_WORKER_EXECUTE_TIME);
			Integer siteInstances = config.getInteger(ConfigKeys.SITE_INSTANCES);

			DeploymentOptions deploymentOptions = new DeploymentOptions();
			deploymentOptions.setInstances(siteInstances);
			deploymentOptions.setConfig(config);
			deploymentOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
			deploymentOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);

			DeploymentOptions WorkerVerticleDeploymentOptions = new DeploymentOptions();
			WorkerVerticleDeploymentOptions.setConfig(config);
			WorkerVerticleDeploymentOptions.setInstances(1);
			WorkerVerticleDeploymentOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
			WorkerVerticleDeploymentOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);

			DeploymentOptions EmailVerticleDeploymentOptions = new DeploymentOptions();
			EmailVerticleDeploymentOptions.setConfig(config);
			EmailVerticleDeploymentOptions.setInstances(1);
			EmailVerticleDeploymentOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
			EmailVerticleDeploymentOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);

			vertx.deployVerticle(MainVerticle.class, deploymentOptions).onSuccess(a -> {
				LOG.info("Started main verticle. ");
				List<Future<String>> futures = new ArrayList<>();
				if(BooleanUtils.isNotFalse(config.getBoolean(ConfigKeys.ENABLE_WORKER_VERTICLE))) {
					futures.add(vertx.deployVerticle(WorkerVerticle.class, WorkerVerticleDeploymentOptions));
				}
				if(BooleanUtils.isTrue(config.getBoolean(ConfigKeys.ENABLE_EMAIL))) {
					futures.add(vertx.deployVerticle(EmailVerticle.class, EmailVerticleDeploymentOptions));
				}
				Future.all(futures).onSuccess(b -> {
					LOG.info("All verticles started successfully.");
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Failed to start verticles.", ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error("Failed to start main verticle. ", ex);
				vertx.close();
			});
		} catch (Throwable ex) {
			vertx.close();
			LOG.error("Creating clustered Vertx failed. ", ex);
			ExceptionUtils.rethrow(ex);
		}
		return promise.future();
	}

	public static Future<Void> run(JsonObject config) {
		Promise<Void> promise = Promise.promise();
		try {
			Boolean enableZookeeperCluster = Optional.ofNullable(config.getBoolean(ConfigKeys.ENABLE_ZOOKEEPER_CLUSTER)).orElse(false);
			VertxOptions vertxOptions = new VertxOptions();
			EventBusOptions eventBusOptions = new EventBusOptions();
	
			if(enableZookeeperCluster) {
				JsonObject zkConfig = new JsonObject();
				String hostname = config.getString(ConfigKeys.HOSTNAME);
				String openshiftService = config.getString(ConfigKeys.OPENSHIFT_SERVICE);
				String zookeeperHostName = config.getString(ConfigKeys.ZOOKEEPER_HOST_NAME);
				Integer zookeeperPort = config.getInteger(ConfigKeys.ZOOKEEPER_PORT);
				String zookeeperHosts = Optional.ofNullable(config.getString(ConfigKeys.ZOOKEEPER_HOSTS)).orElse(zookeeperHostName + ":" + zookeeperPort);
				String clusterHostName = config.getString(ConfigKeys.CLUSTER_HOST_NAME);
				Integer clusterPort = config.getInteger(ConfigKeys.CLUSTER_PORT);
				String clusterPublicHostName = config.getString(ConfigKeys.CLUSTER_PUBLIC_HOST_NAME);
				Integer clusterPublicPort = config.getInteger(ConfigKeys.CLUSTER_PUBLIC_PORT);
				String zookeeperRetryPolicy = config.getString(ConfigKeys.ZOOKEEPER_RETRY_POLICY);
				Integer zookeeperBaseSleepTimeMillis = config.getInteger(ConfigKeys.ZOOKEEPER_BASE_SLEEP_TIME_MILLIS);
				Integer zookeeperMaxSleepMillis = config.getInteger(ConfigKeys.ZOOKEEPER_MAX_SLEEP_MILLIS);
				Integer zookeeperMaxRetries = config.getInteger(ConfigKeys.ZOOKEEPER_MAX_RETRIES);
				Integer zookeeperConnectionTimeoutMillis = config.getInteger(ConfigKeys.ZOOKEEPER_CONNECTION_TIMEOUT_MILLIS);
				Integer zookeeperSessionTimeoutMillis = config.getInteger(ConfigKeys.ZOOKEEPER_SESSION_TIMEOUT_MILLIS);
				zkConfig.put("zookeeperHosts", zookeeperHosts);
				zkConfig.put("sessionTimeout", zookeeperSessionTimeoutMillis);
				zkConfig.put("connectTimeout", zookeeperConnectionTimeoutMillis);
				zkConfig.put("rootPath", config.getString(ConfigKeys.SITE_NAME));
				zkConfig.put("retry", new JsonObject()
						.put("policy", zookeeperRetryPolicy)
						.put("initialSleepTime", zookeeperBaseSleepTimeMillis)
						.put("intervalTimes", zookeeperMaxSleepMillis)
						.put("maxTimes", zookeeperMaxRetries)
				);
				ClusterManager clusterManager = new ZookeeperClusterManager(zkConfig);
	
				if(clusterHostName == null) {
					clusterHostName = hostname;
				}
				if(clusterPublicHostName == null) {
					if(hostname != null && openshiftService != null) {
						clusterPublicHostName = hostname + "." + openshiftService;
					}
				}
				if(clusterHostName != null) {
					LOG.info(String.format("%s — %s", ConfigKeys.CLUSTER_HOST_NAME, clusterHostName));
					eventBusOptions.setHost(clusterHostName);
				}
				if(clusterPort != null) {
					LOG.info(String.format("%s — %s", ConfigKeys.CLUSTER_PORT, clusterPort));
					eventBusOptions.setPort(clusterPort);
				}
				if(clusterPublicHostName != null) {
					LOG.info(String.format("%s — %s", ConfigKeys.CLUSTER_PUBLIC_HOST_NAME, clusterPublicHostName));
					eventBusOptions.setClusterPublicHost(clusterPublicHostName);
				}
				if(clusterPublicPort != null) {
					LOG.info(String.format("%s — %s", ConfigKeys.CLUSTER_PUBLIC_PORT, clusterPublicPort));
					eventBusOptions.setClusterPublicPort(clusterPublicPort);
				}
				vertxOptions.setClusterManager(clusterManager);
			}
			Long vertxWarningExceptionSeconds = config.getLong(ConfigKeys.VERTX_WARNING_EXCEPTION_SECONDS);
			Long vertxMaxEventLoopExecuteTime = config.getLong(ConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME);
			Long vertxMaxWorkerExecuteTime = config.getLong(ConfigKeys.VERTX_MAX_WORKER_EXECUTE_TIME);
			Integer siteInstances = config.getInteger(ConfigKeys.SITE_INSTANCES);
			vertxOptions.setEventBusOptions(eventBusOptions);
			vertxOptions.setWarningExceptionTime(vertxWarningExceptionSeconds);
			vertxOptions.setWarningExceptionTimeUnit(TimeUnit.SECONDS);
			vertxOptions.setMaxEventLoopExecuteTime(vertxMaxEventLoopExecuteTime);
			vertxOptions.setMaxEventLoopExecuteTimeUnit(TimeUnit.SECONDS);
			vertxOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
			vertxOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);
			vertxOptions.setWorkerPoolSize(config.getInteger(ConfigKeys.WORKER_POOL_SIZE));

			if(BooleanUtils.isTrue(config.getBoolean(ConfigKeys.ENABLE_OPEN_TELEMETRY))) {
				SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder().build();
				SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder().build();

				OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
						.setTracerProvider(sdkTracerProvider)
						.setMeterProvider(sdkMeterProvider)
						.setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
						.buildAndRegisterGlobal();
				vertxOptions.setTracingOptions(new OpenTelemetryOptions(openTelemetry));
			}
	
			if(enableZookeeperCluster) {
				Vertx.clusteredVertx(vertxOptions).onSuccess(vertx -> {
					runVerticles(vertx, config).onSuccess(a -> {
						promise.complete();
					});
				}).onFailure(ex -> {
					LOG.error("Creating clustered Vertx failed. ", ex);
					promise.fail(ex);
				});
			} else {
				Vertx vertx = Vertx.vertx(vertxOptions);
				runVerticles(vertx, config).onSuccess(a -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Creating clustered Vertx failed. ", ex);
					promise.fail(ex);
				});
			}
		} catch (Throwable ex) {
			LOG.error("Creating clustered Vertx failed. ", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**
	 * This is called by Vert.x when the verticle instance is deployed. 
	 * Initialize a new site context object for storing information about the entire site in English. 
	 * Setup the startPromise to handle the configuration steps and starting the server. 
	 **/
	@Override()
	public void  start(Promise<Void> startPromise) throws Exception, Exception {
		try {
			configureWebClient().onSuccess(a ->
				configureDataLoop().onSuccess(b -> 
					configureOpenApi().onSuccess(c -> 
						configureHealthChecks().onSuccess(d -> 
							configureSharedWorkerExecutor().onSuccess(e -> 
								configureWebsockets().onSuccess(f -> 
									configureKafka().onSuccess(g -> 
										configureMqtt().onSuccess(h -> 
											configureAmqp().onSuccess(i -> 
												configureRabbitmq().onSuccess(j -> 
													configureJinjava().onSuccess(k -> 
{% if SQUARE_ACCESS_TOKEN is defined %}
														configureSquare().onSuccess(l -> 
{% endif %}
															configureApi().onSuccess(m -> 
																configureUi().onSuccess(n -> 
																	startServer().onSuccess(o -> startPromise.complete())
																).onFailure(ex -> startPromise.fail(ex))
															).onFailure(ex -> startPromise.fail(ex))
{% if SQUARE_ACCESS_TOKEN is defined %}
														).onFailure(ex -> startPromise.fail(ex))
{% endif %}
													).onFailure(ex -> startPromise.fail(ex))
												).onFailure(ex -> startPromise.fail(ex))
											).onFailure(ex -> startPromise.fail(ex))
										).onFailure(ex -> startPromise.fail(ex))
									).onFailure(ex -> startPromise.fail(ex))
								).onFailure(ex -> startPromise.fail(ex))
							).onFailure(ex -> startPromise.fail(ex))
						).onFailure(ex -> startPromise.fail(ex))
					).onFailure(ex -> startPromise.fail(ex))
				).onFailure(ex -> startPromise.fail(ex))
			).onFailure(ex -> startPromise.fail(ex));
		} catch (Exception ex) {
			LOG.error("Couldn't start verticle. ", ex);
			startPromise.fail(ex);
		}
	}

	/**	
	 **/
	public Future<Void> configureWebClient() {
		Promise<Void> promise = Promise.promise();

		try {
			Boolean sslVerify = config().getBoolean(ConfigKeys.SSL_VERIFY);
			webClient = WebClient.create(vertx, new WebClientOptions().setVerifyHost(sslVerify).setTrustAll(!sslVerify));
			promise.complete();
		} catch(Exception ex) {
			LOG.error("Unable to configure site context. ", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 **/
	public Future<KafkaProducer<String, String>> configureKafka() {
		Promise<KafkaProducer<String, String>> promise = Promise.promise();

		try {
			if(BooleanUtils.isTrue(config().getBoolean(ConfigKeys.ENABLE_KAFKA, true))) {
				Map<String, String> kafkaConfig = new HashMap<>();
				kafkaConfig.put("bootstrap.servers", config().getString(ConfigKeys.KAFKA_BROKERS));
				kafkaConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				kafkaConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				kafkaConfig.put("acks", "1");
				kafkaConfig.put("security.protocol", "SSL");
				Optional.ofNullable(config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_TYPE)).ifPresent(keystoreType -> {
					kafkaConfig.put("ssl.keystore.type", keystoreType);
					kafkaConfig.put("ssl.keystore.location", config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_LOCATION));
					kafkaConfig.put("ssl.keystore.password", config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_PASSWORD));
				});
				Optional.ofNullable(config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_TYPE)).ifPresent(truststoreType -> {
					kafkaConfig.put("ssl.truststore.type", truststoreType);
					kafkaConfig.put("ssl.truststore.location", config().getString(ConfigKeys.KAFKA_SSL_TRUSTSTORE_LOCATION));
					kafkaConfig.put("ssl.truststore.password", config().getString(ConfigKeys.KAFKA_SSL_TRUSTSTORE_PASSWORD));
				});

				kafkaProducer = KafkaProducer.createShared(vertx, config().getString(ConfigKeys.SITE_NAME), kafkaConfig);
				LOG.info("The Kafka producer was initialized successfully. ");
				promise.complete(kafkaProducer);
			} else {
				LOG.info("The Kafka producer was initialized successfully. ");
				promise.complete(null);
			}
		} catch(Exception ex) {
			LOG.error("Unable to configure Kafka. ", ex);
			promise.fail(ex);
		}

		return promise.future();
	}
	/**
	 **/
	public Future<MqttClient> configureMqtt() {
		Promise<MqttClient> promise = Promise.promise();

		try {
			if(BooleanUtils.isTrue(config().getBoolean(ConfigKeys.ENABLE_MQTT))) {
				try {
					mqttClient = MqttClient.create(vertx);
					mqttClient.connect(config().getInteger(ConfigKeys.MQTT_PORT), config().getString(ConfigKeys.MQTT_HOST)).onSuccess(a -> {
						try {
							LOG.info("The MQTT client was initialized successfully.");
							promise.complete(mqttClient);
						} catch(Exception ex) {
							LOG.error("The MQTT client failed to initialize.", ex);
							promise.fail(ex);
						}
					}).onFailure(ex -> {
						LOG.error("The MQTT client failed to initialize.", ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error("The MQTT client failed to initialize.", ex);
					promise.fail(ex);
				}
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error("The MQTT client failed to initialize.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 **/
	public Future<AmqpClient> configureAmqp() {
		Promise<AmqpClient> promise = Promise.promise();

		try {
			if(BooleanUtils.isTrue(config().getBoolean(ConfigKeys.ENABLE_AMQP))) {
				try {
					AmqpClientOptions options = new AmqpClientOptions()
							.setHost(config().getString(ConfigKeys.AMQP_HOST))
							.setPort(config().getInteger(ConfigKeys.AMQP_PORT))
							.setUsername(config().getString(ConfigKeys.AMQP_USER))
							.setPassword(config().getString(ConfigKeys.AMQP_PASSWORD))
							.setVirtualHost(config().getString(ConfigKeys.AMQP_VIRTUAL_HOST))
							;
					amqpClient = AmqpClient.create(vertx, options);
					amqpClient.connect().onSuccess(amqpConnection -> {
						try {
							AmqpSenderOptions senderOptions = new AmqpSenderOptions();
							amqpConnection
									.createSender("my-queue", senderOptions)
									.onSuccess(sender -> {
								this.amqpSender = sender;
								LOG.info("The AMQP client was initialized successfully.");
								promise.complete(amqpClient);
							}).onFailure(ex -> {
								LOG.error("The AMQP client failed to initialize.", ex);
								promise.fail(ex);
							});
						} catch(Exception ex) {
							LOG.error("The AMQP client failed to initialize.", ex);
							promise.fail(ex);
						}
					}).onFailure(ex -> {
						LOG.error("The AMQP client failed to initialize.", ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error("The AMQP client failed to initialize.", ex);
					promise.fail(ex);
				}
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error("The AMQP client failed to initialize.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 **/
	public Future<RabbitMQClient> configureRabbitmq() {
		Promise<RabbitMQClient> promise = Promise.promise();

		try {
			if(BooleanUtils.isTrue(config().getBoolean(ConfigKeys.ENABLE_RABBITMQ))) {
				try {
					RabbitMQOptions options = new RabbitMQOptions()
							.setHost(config().getString(ConfigKeys.RABBITMQ_HOST_NAME))
							.setPort(config().getInteger(ConfigKeys.RABBITMQ_PORT))
							.setUser(config().getString(ConfigKeys.RABBITMQ_USER))
							.setPassword(config().getString(ConfigKeys.RABBITMQ_PASSWORD))
							.setVirtualHost(config().getString(ConfigKeys.RABBITMQ_VIRTUAL_HOST))
							.setAutomaticRecoveryEnabled(true)
							;
					this.rabbitmqClient = RabbitMQClient.create(vertx, options);
					rabbitmqClient.start().onSuccess(a -> {
						LOG.info("The AMQP client was initialized successfully.");
						promise.complete(rabbitmqClient);
					}).onFailure(ex -> {
						LOG.error("The AMQP client failed to initialize.", ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error("The AMQP client failed to initialize.", ex);
					promise.fail(ex);
				}
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error("The AMQP client failed to initialize.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 **/
	public Future<Jinjava> configureJinjava() {
		Promise<Jinjava> promise = Promise.promise();

		try {
			jinjava = ComputateConfigKeys.getJinjava();
			String templatePath = config().getString(ConfigKeys.TEMPLATE_PATH);
			if(!StringUtils.isBlank(templatePath))
				jinjava.setResourceLocator(new FileLocator(new File(templatePath)));
			promise.complete(jinjava);
		} catch(Exception ex) {
			LOG.error("Jinjava failed to initialize.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	public Future<Void> configureDataLoop() {
		Promise<Void> promise = Promise.promise();
		configureData().onSuccess(a -> {
			promise.complete();
		}).onFailure(ex -> {
			LOG.info("Call timer");
			vertx.setTimer(10000, a -> {
			LOG.info("Timer triggered");
				configureDataLoop();
			});
		});
		return promise.future();
	}

	/**	
	 *	Configure shared database connections across the cluster for massive scaling of the application. 
	 *	Return a promise that configures a shared database client connection. 
	 *	Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 **/
	public Future<Void> configureData() {
		Promise<Void> promise = Promise.promise();
		try {
			if(BooleanUtils.isTrue(config().getBoolean(ConfigKeys.ENABLE_DATABASE))) {
				PgConnectOptions pgOptions = new PgConnectOptions();
				pgOptions.setPort(config().getInteger(ConfigKeys.DATABASE_PORT));
				pgOptions.setHost(config().getString(ConfigKeys.DATABASE_HOST));
				pgOptions.setDatabase(config().getString(ConfigKeys.DATABASE_DATABASE));
				pgOptions.setUser(config().getString(ConfigKeys.DATABASE_USERNAME));
				pgOptions.setPassword(config().getString(ConfigKeys.DATABASE_PASSWORD));
				pgOptions.setIdleTimeout(config().getInteger(ConfigKeys.DATABASE_MAX_IDLE_TIME, 10));
				pgOptions.setIdleTimeoutUnit(TimeUnit.SECONDS);
				pgOptions.setConnectTimeout(config().getInteger(ConfigKeys.DATABASE_CONNECT_TIMEOUT, 1000));

				PoolOptions poolOptions = new PoolOptions();
				jdbcMaxPoolSize = config().getInteger(ConfigKeys.DATABASE_MAX_POOL_SIZE, 1);
				jdbcMaxWaitQueueSize = config().getInteger(ConfigKeys.DATABASE_MAX_WAIT_QUEUE_SIZE, 10);
				poolOptions.setMaxSize(jdbcMaxPoolSize);
				poolOptions.setMaxWaitQueueSize(jdbcMaxWaitQueueSize);

				pgPool = PgPool.pool(vertx, pgOptions, poolOptions);
				Promise<Void> promise1 = Promise.promise();
				pgPool.withConnection(sqlConnection -> {
					sqlConnection.preparedQuery("SELECT")
							.execute(Tuple.tuple()
							).onSuccess(result -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error("Could not initialize the database.", ex);
						promise1.fail(ex);
					});
					return promise1.future();
				}).onSuccess(a -> {
					LOG.info("The database was initialized successfully.");
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Could not initialize the database.", ex);
					promise.fail(ex);
				});
			} else {
				promise.complete();
			}
		} catch (Exception ex) {
			LOG.error("Could not initialize the database.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**	
	 * Configure the connection to the auth server and setup the routes based on the OpenAPI definition. 
	 * Setup a callback route when returning from the auth server after successful authentication. 
	 * Setup a logout route for logging out completely of the application. 
	 * Return a promise that configures the authentication server and OpenAPI. 
	 **/
	public Future<OAuth2AuthHandler> configureAuthClient(String authClientOpenApiId, JsonObject clientConfig, Map<String, OAuth2AuthHandler> authHandlers, Map<String, OAuth2Auth> authProviders) {
		Promise<OAuth2AuthHandler> promise = Promise.promise();
		try {
			String siteBaseUrl = config().getString(ConfigKeys.SITE_BASE_URL);

			OAuth2Options oauth2ClientOptions = new OAuth2Options();
			Boolean authSsl = clientConfig.getBoolean(ConfigKeys.AUTH_SSL);
			String authHostName = clientConfig.getString(ConfigKeys.AUTH_HOST_NAME);
			Integer authPort = clientConfig.getInteger(ConfigKeys.AUTH_PORT);
			String authUrl = String.format("%s", clientConfig.getString(ConfigKeys.AUTH_URL));
			oauth2ClientOptions.setSite(authUrl + "/realms/" + clientConfig.getString(ConfigKeys.AUTH_REALM));
			oauth2ClientOptions.setTenant(clientConfig.getString(ConfigKeys.AUTH_REALM));
			String authClient = clientConfig.getString(ConfigKeys.AUTH_CLIENT);
			oauth2ClientOptions.setClientId(authClient);
			oauth2ClientOptions.setClientSecret(clientConfig.getString(ConfigKeys.AUTH_SECRET));
			oauth2ClientOptions.setAuthorizationPath("/oauth/authorize");
			JsonObject extraParams = new JsonObject();
			extraParams.put("scope", "profile");
			oauth2ClientOptions.setExtraParameters(extraParams);
			oauth2ClientOptions.setHttpClientOptions(new HttpClientOptions().setTrustAll(true).setVerifyHost(false).setConnectTimeout(120000));
			String authCallbackUri = clientConfig.getString(ConfigKeys.AUTH_CALLBACK_URI);
			if(authCallbackUri == null)
				throw new RuntimeException(String.format("Please configure an AUTH_CALLBACK_URI for the AUTH_CLIENT %s.", authClient));

			OpenIDConnectAuth.discover(vertx, oauth2ClientOptions).onSuccess(oauth2AuthenticationProvider -> {
				authorizationProvider = KeycloakAuthorization.create();

				oauth2AuthHandler = new ComputateOAuth2AuthHandlerImpl(vertx, oauth2AuthenticationProvider, siteBaseUrl + authCallbackUri);
				Router tempRouter = Router.router(vertx);
				oauth2AuthHandler.setupCallback(tempRouter.get(authCallbackUri));
				authHandlers.put(authClientOpenApiId, oauth2AuthHandler);
				authProviders.put(authClientOpenApiId, oauth2AuthenticationProvider);
				LOG.info(String.format("Configured the auth server and API successfully.", authClient));
				promise.complete(oauth2AuthHandler);
			}).onFailure(ex -> {
				Exception ex2 = new RuntimeException("OpenID Connect Discovery failed", ex);
				LOG.error("Could not configure the auth server and API.", ex2);
				promise.fail(ex2);
			});
		} catch (Exception ex) {
			LOG.error("Could not configure the auth server and API.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Configure the connection to the auth server and setup the routes based on the OpenAPI definition. 
	 * Setup a callback route when returning from the auth server after successful authentication. 
	 * Setup a logout route for logging out completely of the application. 
	 * Return a promise that configures the authentication server and OpenAPI. 
	 **/
	public Future<Void> configureOpenApi() {
		Promise<Void> promise = Promise.promise();
		try {
			String siteBaseUrl = config().getString(ConfigKeys.SITE_BASE_URL);
			
			JsonObject authClients = Optional.ofNullable(config().getValue(ConfigKeys.AUTH_CLIENTS))
					.map(v -> v instanceof JsonObject ? (JsonObject)v : new JsonObject(v.toString()))
					.orElse(new JsonObject().put(Optional.ofNullable(config().getString(ConfigKeys.AUTH_OPEN_API_ID)).orElse("openIdConnect"), config()))
					;
			List<Future<OAuth2AuthHandler>> futures = new ArrayList<>();
			Map<String, OAuth2AuthHandler> authHandlers = new LinkedHashMap<>();
			Map<String, OAuth2Auth> authProviders = new LinkedHashMap<>();
			for(String authClientOpenApiId : authClients.fieldNames()) {
				JsonObject authClient = authClients.getJsonObject(authClientOpenApiId);
				futures.add(Future.future(promise1 -> {
					configureAuthClient(authClientOpenApiId, authClient, authHandlers, authProviders).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("configureAuthClient failed. "), ex);
						promise1.fail(ex);
					});
				}));
			}
			Future.all(futures).onSuccess(a -> {
				oauth2AuthenticationProvider = authProviders.get(authProviders.keySet().toArray()[0]);
				authorizationProvider = KeycloakAuthorization.create();
		
				//ClusteredSessionStore sessionStore = ClusteredSessionStore.create(vertx);
				sessionStore = LocalSessionStore.create(vertx, config().getString(ConfigKeys.SITE_NAME));
				sessionHandler = SessionHandler.create(sessionStore);
				if(StringUtils.startsWith(siteBaseUrl, "https://"))
					sessionHandler.setCookieSecureFlag(true);
		
				RouterBuilder.create(vertx, "webroot/openapi3-enUS.yaml").onSuccess(routerBuilder -> {
					routerBuilder.rootHandler(sessionHandler);
					routerBuilder.rootHandler(BodyHandler.create());

					routerBuilder.mountServicesFromExtensions();
	
					routerBuilder.serviceExtraPayloadMapper(routingContext -> new JsonObject()
							.put("uri", routingContext.request().uri())
							.put("method", routingContext.request().method().name())
							);
					for(String authClientOpenApiId : authHandlers.keySet()) {
						OAuth2AuthHandler authHandler = authHandlers.get(authClientOpenApiId);
						routerBuilder.securityHandler(authClientOpenApiId, authHandler);
					}
					routerBuilder.operation("callback").handler(ctx -> {
	
						// Handle the callback of the flow
						final String code = ctx.request().getParam("code");
	
						// code is a require value
						if (code == null) {
							ctx.fail(400);
							return;
						}
	
						final String state = ctx.request().getParam("state");
	
						final JsonObject config = new JsonObject().put("code", code);
	
						String authClientOpenApiId = config().getString(ConfigKeys.AUTH_OPEN_API_ID);
						JsonObject clientConfig = authClients.getJsonObject(authClientOpenApiId);
						String authCallbackUri = clientConfig.getString(ConfigKeys.AUTH_CALLBACK_URI);
						config.put("redirectUri", siteBaseUrl + authCallbackUri);
						OAuth2Auth authProvider = authProviders.get(authClientOpenApiId);

						authProvider.authenticate(config, res -> {
							if (res.failed()) {
								LOG.error("Failed to authenticate user. ", res.cause());
								ctx.fail(res.cause());
							} else {
								ctx.setUser(res.result());
								Session session = ctx.session();
								if (session != null) {
									// the user has upgraded from unauthenticated to authenticated
									// session should be upgraded as recommended by owasp
									Cookie cookie = Cookie.cookie("sessionIdBefore", session.id());
									if(StringUtils.startsWith(siteBaseUrl, "https://"))
										cookie.setSecure(true);
									ctx.addCookie(cookie);
									session.regenerateId();
									String redirectUri = session.get("redirect_uri");
									// we should redirect the UA so this link becomes invalid
									ctx.response()
											// disable all caching
											.putHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
											.putHeader("Pragma", "no-cache")
											.putHeader(HttpHeaders.EXPIRES, "0")
											// redirect (when there is no state, redirect to home
											.putHeader(HttpHeaders.LOCATION, redirectUri != null ? redirectUri : "/")
											.setStatusCode(302)
											.end("Redirecting to " + (redirectUri != null ? redirectUri : "/") + ".");
								} else {
									// there is no session object so we cannot keep state
									ctx.reroute(state != null ? state : "/");
								}
							}
						});
	
					});
					routerBuilder.operation("callback").failureHandler(ex -> {
						LOG.error("Failed callback. ", ex);
					});
	
					routerBuilder.operation("logout").handler(rc -> {
						String redirectUri = rc.request().params().get("redirect_uri");
						if(redirectUri == null)
							redirectUri = "/";
						rc.clearUser();
						rc.response()
								.putHeader(HttpHeaders.LOCATION, redirectUri)
								.setStatusCode(302)
								.end("Redirecting to " + redirectUri + ".");
					});
					routerBuilder.operation("logout").handler(c -> {});
	
					router = routerBuilder.createRouter();
	
					LOG.info("The auth server and API was configured successfully.");
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Could not configure the auth server and API.", ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error("Could not configure the auth server and API.", ex);
				promise.fail(ex);
			});
		} catch (Exception ex) {
			LOG.error("Could not configure the auth server and API.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 **/
	public static Future<JsonObject> configureConfig(Vertx vertx) {
		Promise<JsonObject> promise = Promise.promise();

		try {
			ConfigRetrieverOptions retrieverOptions = new ConfigRetrieverOptions();

			String configVarsPath = System.getenv(ComputateConfigKeys.VARS_PATH);
			if(StringUtils.isNotBlank(configVarsPath)) {
				Jinjava jinjava = ComputateConfigKeys.getJinjava();
				JsonObject config = ComputateConfigKeys.getConfig(jinjava);
				ConfigStoreOptions configOptions = new ConfigStoreOptions().setType("json").setConfig(config);
				retrieverOptions.addStore(configOptions);
			}

			ConfigStoreOptions storeEnv = new ConfigStoreOptions().setType("env");
			retrieverOptions.addStore(storeEnv);

			ConfigRetriever configRetriever = ConfigRetriever.create(vertx, retrieverOptions);
			configRetriever.getConfig().onSuccess(config -> {
				LOG.info("The config was configured successfully. ");
				promise.complete(config);
			}).onFailure(ex -> {
				LOG.error("Unable to configure site context. ", ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error("Unable to configure site context. ", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 * Configure a shared worker executor for running blocking tasks in the background. 
	 * Return a promise that configures the shared worker executor. 
	 **/
	public Future<Void> configureSharedWorkerExecutor() {
		Promise<Void> promise = Promise.promise();
		try {
			String name = "MainVerticle-WorkerExecutor";
			Integer workerPoolSize = System.getenv(ConfigKeys.WORKER_POOL_SIZE) == null ? 5 : Integer.parseInt(System.getenv(ConfigKeys.WORKER_POOL_SIZE));
			Long vertxMaxWorkerExecuteTime = config().getLong(ConfigKeys.VERTX_MAX_WORKER_EXECUTE_TIME);
			workerExecutor = vertx.createSharedWorkerExecutor(name, workerPoolSize, vertxMaxWorkerExecuteTime, TimeUnit.SECONDS);
			LOG.info("The shared worker executor \"{}\" was configured successfully.", name);
			promise.complete();
		} catch (Exception ex) {
			LOG.error("Could not configure the shared worker executor.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Configure health checks for the status of the website and it's dependent services. 
	 * Return a promise that configures the health checks. 
	 **/
	public Future<Void> configureHealthChecks() {
		Promise<Void> promise = Promise.promise();
		try {
			ClusterManager clusterManager = ((VertxImpl)vertx).getClusterManager();
			HealthCheckHandler healthCheckHandler = HealthCheckHandler.create(vertx);
			siteInstances = Optional.ofNullable(System.getenv(ConfigKeys.SITE_INSTANCES)).map(s -> Integer.parseInt(s)).orElse(1);
			workerPoolSize = System.getenv(ConfigKeys.WORKER_POOL_SIZE) == null ? null : Integer.parseInt(System.getenv(ConfigKeys.WORKER_POOL_SIZE));

			healthCheckHandler.register("vertx", 2000, a -> {
				a.complete(Status.OK(new JsonObject().put(ConfigKeys.SITE_INSTANCES, siteInstances).put("workerPoolSize", workerPoolSize)));
			});
			if(clusterManager != null) {
				healthCheckHandler.register("cluster", 2000, a -> {
					NodeInfo nodeInfo = clusterManager.getNodeInfo();
					JsonArray nodeArray = new JsonArray();
					clusterManager.getNodes().forEach(node -> nodeArray.add(node));
					a.complete(Status.OK(new JsonObject()
							.put("nodeId", clusterManager.getNodeId())
							.put("nodes", nodeArray)
							));
				});
			}
			router.get("/health").handler(healthCheckHandler);
			LOG.info("The health checks were configured successfully.");
			promise.complete();
		} catch (Exception ex) {
			LOG.error("Could not configure the health checks.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Configure websockets for realtime messages. 
	 **/
	public Future<Void> configureWebsockets() {
		Promise<Void> promise = Promise.promise();
		try {
			SockJSBridgeOptions options = new SockJSBridgeOptions()
					.addOutboundPermitted(new PermittedOptions().setAddressRegex("websocket.*"))
					;
			router.route("/eventbus*").subRouter(SockJSHandler.create(vertx).bridge(options));
			LOG.info("Configure websockets succeeded.");
			promise.complete();
		} catch (Exception ex) {
			LOG.error("Configure websockets failed.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

{% if SQUARE_ACCESS_TOKEN is defined %}
	/**	
	 * Configure square webhooks
	 **/
	public Future<Void> configureSquare() {
		Promise<Void> promise = Promise.promise();
		try {
			String squareAccessToken = config().getString(ConfigKeys.SQUARE_ACCESS_TOKEN);
			String squareSignatureKey = config().getString(ConfigKeys.SQUARE_SIGNATURE_KEY);
			String squareNotificationUrl = config().getString(ConfigKeys.SQUARE_NOTIFICATION_URL);
			if(squareAccessToken == null || squareSignatureKey == null || squareNotificationUrl == null) {
				promise.complete();
			} else {
				squareClient = new SquareClient.Builder()
						.bearerAuthCredentials(new BearerAuthModel.Builder(squareAccessToken).build())
						.environment(Environment.PRODUCTION)
						.build();
				LOG.info("Configure Square succeeded.");
				promise.complete();
			}
		} catch (Exception ex) {
			LOG.error("Configure Square failed.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}
{% endif %}

	/**
	 */
	public Future<Void> configureApi() {
		Promise<Void> promise = Promise.promise();
		try {
			List<Future<?>> futures = new ArrayList<>();
			List<String> authResources = Arrays.asList({% for JAVA_MODEL in JAVA_MODELS %}{% if loop.index > 1 %},{% endif %}"{{ JAVA_MODEL.classeNomSimple_enUS_stored_string }}"{% endfor %});
			List<String> publicResources = Arrays.asList({% for JAVA_MODEL in PUBLIC_MODELS %}{% if loop.index > 1 %},{% endif %}"{{ JAVA_MODEL.classeNomSimple_enUS_stored_string }}"{% endfor %});
			SiteUserEnUSGenApiServiceImpl apiSiteUser = SiteUserEnUSGenApiService.registerService(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
			apiSiteUser.configureUserSearchApi("/user-search", router, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_SiteUser, config(), webClient, authResources);
			apiSiteUser.configurePublicSearchApi("/search", router, SiteRequest.class, config(), webClient, publicResources);
{% for JAVA_MODEL in JAVA_MODELS %}
{% if JAVA_MODEL.classePageTemplates_enUS_stored_string is defined or JAVA_MODEL.classePageTemplatesUtilisateur_enUS_stored_string is defined %}

			{{ JAVA_MODEL.classeNomSimpleApiServiceImpl_enUS_stored_string }} api{{ JAVA_MODEL.classeNomSimple_enUS_stored_string }} = {{ JAVA_MODEL.classeNomSimpleGenApiService_enUS_stored_string }}.registerService(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
{% if JAVA_MODEL.classePageTemplates_enUS_stored_string is defined %}
			api{{ JAVA_MODEL.classeNomSimple_enUS_stored_string }}.configureUi{% if JAVA_MODEL.classeModele_stored_boolean %}Model{% else %}Result{% endif %}(router, {{ JAVA_MODEL.classeNomSimple_enUS_stored_string }}.class, SiteRequest.class, "{{ JAVA_MODEL.classePageTemplates_enUS_stored_string }}");
{% if JAVA_MODEL.classePageTemplatesUtilisateur_enUS_stored_string is defined %}
			api{{ JAVA_MODEL.classeNomSimple_enUS_stored_string }}.configureUserUi{% if JAVA_MODEL.classeModele_stored_boolean %}Model{% else %}Result{% endif %}(router, {{ JAVA_MODEL.classeNomSimple_enUS_stored_string }}.class, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_SiteUser, "{{ JAVA_MODEL.classePageTemplates_enUS_stored_string }}", "{{ JAVA_MODEL.classePageTemplatesUtilisateur_enUS_stored_string }}");
{% endif %}
{% else %}
{% if JAVA_MODEL.classePageTemplatesUtilisateur_enUS_stored_string is defined %}
			api{{ JAVA_MODEL.classeNomSimple_enUS_stored_string }}.configureUserUi{% if JAVA_MODEL.classeModele_stored_boolean %}Model{% else %}Result{% endif %}(router, {{ JAVA_MODEL.classeNomSimple_enUS_stored_string }}.class, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_SiteUser, null, "{{ JAVA_MODEL.classePageTemplatesUtilisateur_enUS_stored_string }}");
{% endif %}
{% endif %}
{% else %}

			{{ JAVA_MODEL.classeNomSimpleGenApiService_enUS_stored_string }}.registerService(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
{% endif %}
{% endfor %}

			Future.all(futures).onSuccess( a -> {
				LOG.info("The API was configured properly.");
				promise.complete();
			}).onFailure(ex -> {
				LOG.error("The API was not configured properly.", ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error("The API was not configured properly.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**
	 */
	public Future<Void> configureUi() {
		Promise<Void> promise = Promise.promise();
		try {
			String staticPath = config().getString(ConfigKeys.STATIC_PATH);

			StaticHandler staticHandler = StaticHandler.create().setCachingEnabled(false).setFilesReadOnly(false);
			if(staticPath != null) {
				staticHandler.setAllowRootFileSystemAccess(true);
				staticHandler.setWebRoot(staticPath);
				staticHandler.setFilesReadOnly(true);
			}
			router.route("/static/*").handler(staticHandler);

			router.get("/").handler(handler -> {
				try {
					String siteTemplatePath = config().getString(ConfigKeys.TEMPLATE_PATH);
					String pageTemplateUri = "/en-us/HomePage.htm";
					Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
					String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
					JsonObject ctx = ConfigKeys.getPageContext(config());
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					handler.response().putHeader("Content-Type", "text/html");
					handler.end(buffer);
				} catch(Exception ex) {
					LOG.error("Failed to load page. ", ex);
					handler.fail(ex);
				}
			});
{% if SITE_REDIRECTS is defined %}
{% for redirect in SITE_REDIRECTS %}

			router.get("{{ redirect.src }}").handler(ctx -> {
				ctx.response().putHeader("location", "{{ redirect.dest }}");
				ctx.response().setStatusCode(302);
				ctx.end();
			});
{% endfor %}
{% endif %}

			LOG.info("The UI was configured properly.");
			promise.complete();
		} catch(Exception ex) {
			LOG.error("The UI was not configured properly.");
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> putVarsInRoutingContext(RoutingContext ctx) {
		Promise<Void> promise = Promise.promise();
		try {
			for(Entry<String, String> entry : ctx.queryParams()) {
				String paramName = entry.getKey();
				String paramObject = entry.getValue();
				String entityVar = null;
				String valueIndexed = null;

				switch(paramName) {
					case "var":
						entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
						valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
						ctx.put(entityVar, valueIndexed);
						break;
				}
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error(String.format("putVarsInRoutingContext failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Start the Vert.x server. 
	 **/
	public Future<Void> startServer() {
		Promise<Void> promise = Promise.promise();

		try {
			Boolean sslPassthrough = config().getBoolean(ConfigKeys.SSL_PASSTHROUGH, false);
			String siteBaseUrl = config().getString(ConfigKeys.SITE_BASE_URL);
			Integer sitePort = config().getInteger(ConfigKeys.SITE_PORT);
			String sslJksPath = config().getString(ConfigKeys.SSL_JKS_PATH);
			String sslPrivateKeyPath = config().getString(ConfigKeys.SSL_KEY_PATH);
			String sslCertPath = config().getString(ConfigKeys.SSL_CERT_PATH);
			HttpServerOptions options = new HttpServerOptions();
			if(sslPassthrough) {
				if(sslPrivateKeyPath != null && sslCertPath != null) {
					options.setPemKeyCertOptions(new PemKeyCertOptions().setKeyPath(sslPrivateKeyPath).setCertPath(sslCertPath));
					LOG.info(String.format("Configuring SSL: %s", sslPrivateKeyPath));
					LOG.info(String.format("Configuring SSL: %s", sslCertPath));
				} else if(sslJksPath != null) {
					options.setKeyStoreOptions(new JksOptions().setPath(sslJksPath).setPassword(config().getString(ConfigKeys.SSL_JKS_PASSWORD)));
					LOG.info(String.format("Configuring SSL: %s", sslJksPath));
				}
				options.setSsl(true);
			}
			options.setPort(sitePort);
	
			LOG.info(String.format("HTTP server starting: %s", siteBaseUrl));
			vertx.createHttpServer(options).requestHandler(router).listen(ar -> {
				if (ar.succeeded()) {
					LOG.info(String.format("The HTTP server is running: %s", siteBaseUrl));
					promise.complete();
				} else {
					LOG.error("The server is not configured properly.", ar.cause());
					promise.fail(ar.cause());
				}
			});
		} catch (Exception ex) {
			LOG.error("The server is not configured properly.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**	
	 * This is called by Vert.x when the verticle instance is undeployed. 
	 * Setup the stopPromise to handle tearing down the server. 
	 **/
	@Override()
	public void stop(Promise<Void> promise) throws Exception, Exception {
		stopPgPool().onComplete(a -> {
			stopMqtt().onComplete(b -> {
				promise.complete();
			});
		});
	}

	/**
	 **/
	public Future<Void> stopPgPool() {
		Promise<Void> promise = Promise.promise();

		if(pgPool != null) {
			pgPool.close().onSuccess(a -> {
				LOG.info("The database client connection was closed.");
				promise.complete();
			}).onFailure(ex -> {
				LOG.error("The database client connection was closed.", ex);
				promise.fail(ex);
			});
		} else {
			promise.complete();
		}

		return promise.future();
	}

	/**
	 **/
	public Future<Void> stopMqtt() {
		Promise<Void> promise = Promise.promise();

		if(mqttClient != null) {
			mqttClient.disconnect().onSuccess(a -> {
				LOG.info("The MQTT client connection was closed.");
				promise.complete();
			}).onFailure(ex -> {
				LOG.error("Could not close the MQTT client connection.", ex);
				promise.fail(ex);
			});
		} else {
			promise.complete();
		}

		return promise.future();
	}

	public String toId(String s) {
		if(s != null) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
		}

		return s;
	}

	public Integer getSiteInstances() {
		return siteInstances;
	}

	public void setSiteInstances(Integer siteInstances) {
		this.siteInstances = siteInstances;
	}

	public Integer getWorkerPoolSize() {
		return workerPoolSize;
	}

	public void setWorkerPoolSize(Integer workerPoolSize) {
		this.workerPoolSize = workerPoolSize;
	}

	public Integer getJdbcMaxPoolSize() {
		return jdbcMaxPoolSize;
	}

	public void setJdbcMaxPoolSize(Integer jdbcMaxPoolSize) {
		this.jdbcMaxPoolSize = jdbcMaxPoolSize;
	}

	public Integer getJdbcMaxWaitQueueSize() {
		return jdbcMaxWaitQueueSize;
	}

	public void setJdbcMaxWaitQueueSize(Integer jdbcMaxWaitQueueSize) {
		this.jdbcMaxWaitQueueSize = jdbcMaxWaitQueueSize;
	}

	public PgPool getPgPool() {
		return pgPool;
	}

	public void setPgPool(PgPool pgPool) {
		this.pgPool = pgPool;
	}

	public WebClient getWebClient() {
		return webClient;
	}

	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Router getRouter() {
		return router;
	}

	public void setRouter(Router router) {
		this.router = router;
	}

	public WorkerExecutor getWorkerExecutor() {
		return workerExecutor;
	}

	public void setWorkerExecutor(WorkerExecutor workerExecutor) {
		this.workerExecutor = workerExecutor;
	}

	public OAuth2Auth getOauth2AuthenticationProvider() {
		return oauth2AuthenticationProvider;
	}

	public void setOauth2AuthenticationProvider(OAuth2Auth oauth2AuthenticationProvider) {
		this.oauth2AuthenticationProvider = oauth2AuthenticationProvider;
	}

	public AuthorizationProvider getAuthorizationProvider() {
		return authorizationProvider;
	}

	public void setAuthorizationProvider(AuthorizationProvider authorizationProvider) {
		this.authorizationProvider = authorizationProvider;
	}
}
