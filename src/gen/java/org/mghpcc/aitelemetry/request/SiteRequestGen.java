package org.mghpcc.aitelemetry.request;

import org.mghpcc.aitelemetry.request.SiteRequest;
import java.lang.Object;
import org.mghpcc.aitelemetry.model.BaseModel;
import org.computate.vertx.api.ApiRequest;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import java.util.Optional;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.computate.search.serialize.ComputateLocalDateSerializer;
import org.computate.search.serialize.ComputateLocalDateDeserializer;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.serialize.ComputateZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.apache.commons.lang3.math.NumberUtils;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.RoundingMode;
import java.util.Map;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.auth.User;
import java.lang.String;
import java.lang.Long;
import io.vertx.core.json.JsonArray;
import org.mghpcc.aitelemetry.user.SiteUser;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.core.MultiMap;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SiteRequest objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SiteRequestGen into the class SiteRequest. 
 * </li>
 * <h3>About the SiteRequest class and it's generated class SiteRequestGen&lt;Object&gt;: </h3>extends SiteRequestGen
 * <p>
 * This Java class extends a generated Java class SiteRequestGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest">Find the class SiteRequest in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SiteRequestGen<Object>
 * <p>This <code>class SiteRequest extends SiteRequestGen&lt;Object&gt;</code>, which means it extends a newly generated SiteRequestGen. 
 * The generated <code>class SiteRequestGen extends Object</code> which means that SiteRequest extends SiteRequestGen which extends Object. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SiteRequest class will inherit the helpful inherited class comments from the super class SiteRequestGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <h2>AName.enUS: null</h2>
 * <p>
 * Delete the class SiteRequest in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.request in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.request&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class SiteRequestGen<DEV> extends Object {
	protected static final Logger LOG = LoggerFactory.getLogger(SiteRequest.class);

	////////////
	// config //
	////////////


	/**	 The entity config
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject config;

	/**	<br> The entity config
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:config">Find the entity config in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _config(Wrap<JsonObject> c);

	public JsonObject getConfig() {
		return config;
	}

	public void setConfig(JsonObject config) {
		this.config = config;
	}
	@JsonIgnore
	public void setConfig(String o) {
		this.config = SiteRequest.staticSetConfig(siteRequest_, o);
	}
	public static JsonObject staticSetConfig(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected SiteRequest configInit() {
		Wrap<JsonObject> configWrap = new Wrap<JsonObject>().var("config");
		if(config == null) {
			_config(configWrap);
			Optional.ofNullable(configWrap.getO()).ifPresent(o -> {
				setConfig(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchConfig(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrConfig(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqConfig(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchConfig(siteRequest_, SiteRequest.staticSetConfig(siteRequest_, o)).toString();
	}

	//////////////////
	// siteRequest_ //
	//////////////////


	/**	 The entity siteRequest_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected SiteRequest siteRequest_;

	/**	<br> The entity siteRequest_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:siteRequest_">Find the entity siteRequest_ in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequest> c);

	public SiteRequest getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequest siteRequest_) {
		this.siteRequest_ = siteRequest_;
	}
	public static SiteRequest staticSetSiteRequest_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest siteRequest_Init() {
		Wrap<SiteRequest> siteRequest_Wrap = new Wrap<SiteRequest>().var("siteRequest_");
		if(siteRequest_ == null) {
			_siteRequest_(siteRequest_Wrap);
			Optional.ofNullable(siteRequest_Wrap.getO()).ifPresent(o -> {
				setSiteRequest_(o);
			});
		}
		return (SiteRequest)this;
	}

	///////////////
	// webClient //
	///////////////


	/**	 The entity webClient
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected WebClient webClient;

	/**	<br> The entity webClient
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:webClient">Find the entity webClient in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _webClient(Wrap<WebClient> c);

	public WebClient getWebClient() {
		return webClient;
	}

	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}
	public static WebClient staticSetWebClient(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest webClientInit() {
		Wrap<WebClient> webClientWrap = new Wrap<WebClient>().var("webClient");
		if(webClient == null) {
			_webClient(webClientWrap);
			Optional.ofNullable(webClientWrap.getO()).ifPresent(o -> {
				setWebClient(o);
			});
		}
		return (SiteRequest)this;
	}

	/////////////////
	// apiRequest_ //
	/////////////////


	/**	 The entity apiRequest_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ApiRequest apiRequest_;

	/**	<br> The entity apiRequest_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:apiRequest_">Find the entity apiRequest_ in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _apiRequest_(Wrap<ApiRequest> c);

	public ApiRequest getApiRequest_() {
		return apiRequest_;
	}

	public void setApiRequest_(ApiRequest apiRequest_) {
		this.apiRequest_ = apiRequest_;
	}
	public static ApiRequest staticSetApiRequest_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest apiRequest_Init() {
		Wrap<ApiRequest> apiRequest_Wrap = new Wrap<ApiRequest>().var("apiRequest_");
		if(apiRequest_ == null) {
			_apiRequest_(apiRequest_Wrap);
			Optional.ofNullable(apiRequest_Wrap.getO()).ifPresent(o -> {
				setApiRequest_(o);
			});
		}
		return (SiteRequest)this;
	}

	////////////////
	// jsonObject //
	////////////////


	/**	 The entity jsonObject
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject jsonObject;

	/**	<br> The entity jsonObject
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:jsonObject">Find the entity jsonObject in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _jsonObject(Wrap<JsonObject> c);

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	@JsonIgnore
	public void setJsonObject(String o) {
		this.jsonObject = SiteRequest.staticSetJsonObject(siteRequest_, o);
	}
	public static JsonObject staticSetJsonObject(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected SiteRequest jsonObjectInit() {
		Wrap<JsonObject> jsonObjectWrap = new Wrap<JsonObject>().var("jsonObject");
		if(jsonObject == null) {
			_jsonObject(jsonObjectWrap);
			Optional.ofNullable(jsonObjectWrap.getO()).ifPresent(o -> {
				setJsonObject(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchJsonObject(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrJsonObject(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqJsonObject(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchJsonObject(siteRequest_, SiteRequest.staticSetJsonObject(siteRequest_, o)).toString();
	}

	////////////////////
	// serviceRequest //
	////////////////////


	/**	 The entity serviceRequest
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ServiceRequest serviceRequest;

	/**	<br> The entity serviceRequest
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:serviceRequest">Find the entity serviceRequest in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _serviceRequest(Wrap<ServiceRequest> c);

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public static ServiceRequest staticSetServiceRequest(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest serviceRequestInit() {
		Wrap<ServiceRequest> serviceRequestWrap = new Wrap<ServiceRequest>().var("serviceRequest");
		if(serviceRequest == null) {
			_serviceRequest(serviceRequestWrap);
			Optional.ofNullable(serviceRequestWrap.getO()).ifPresent(o -> {
				setServiceRequest(o);
			});
		}
		return (SiteRequest)this;
	}

	//////////
	// user //
	//////////


	/**	 The entity user
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected User user;

	/**	<br> The entity user
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:user">Find the entity user in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _user(Wrap<User> c);

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public static User staticSetUser(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest userInit() {
		Wrap<User> userWrap = new Wrap<User>().var("user");
		if(user == null) {
			_user(userWrap);
			Optional.ofNullable(userWrap.getO()).ifPresent(o -> {
				setUser(o);
			});
		}
		return (SiteRequest)this;
	}

	///////////////////
	// userPrincipal //
	///////////////////


	/**	 The entity userPrincipal
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject userPrincipal;

	/**	<br> The entity userPrincipal
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userPrincipal">Find the entity userPrincipal in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userPrincipal(Wrap<JsonObject> w);

	public JsonObject getUserPrincipal() {
		return userPrincipal;
	}

	public void setUserPrincipal(JsonObject userPrincipal) {
		this.userPrincipal = userPrincipal;
	}
	@JsonIgnore
	public void setUserPrincipal(String o) {
		this.userPrincipal = SiteRequest.staticSetUserPrincipal(siteRequest_, o);
	}
	public static JsonObject staticSetUserPrincipal(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected SiteRequest userPrincipalInit() {
		Wrap<JsonObject> userPrincipalWrap = new Wrap<JsonObject>().var("userPrincipal");
		if(userPrincipal == null) {
			_userPrincipal(userPrincipalWrap);
			Optional.ofNullable(userPrincipalWrap.getO()).ifPresent(o -> {
				setUserPrincipal(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchUserPrincipal(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrUserPrincipal(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserPrincipal(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserPrincipal(siteRequest_, SiteRequest.staticSetUserPrincipal(siteRequest_, o)).toString();
	}

	////////////
	// userId //
	////////////


	/**	 The entity userId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userId;

	/**	<br> The entity userId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userId">Find the entity userId in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userId(Wrap<String> c);

	public String getUserId() {
		return userId;
	}
	public void setUserId(String o) {
		this.userId = SiteRequest.staticSetUserId(siteRequest_, o);
	}
	public static String staticSetUserId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest userIdInit() {
		Wrap<String> userIdWrap = new Wrap<String>().var("userId");
		if(userId == null) {
			_userId(userIdWrap);
			Optional.ofNullable(userIdWrap.getO()).ifPresent(o -> {
				setUserId(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchUserId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserId(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserId(siteRequest_, SiteRequest.staticSetUserId(siteRequest_, o)).toString();
	}

	/////////////
	// userKey //
	/////////////


	/**	 The entity userKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long userKey;

	/**	<br> The entity userKey
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userKey">Find the entity userKey in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userKey(Wrap<Long> c);

	public Long getUserKey() {
		return userKey;
	}

	public void setUserKey(Long userKey) {
		this.userKey = userKey;
	}
	@JsonIgnore
	public void setUserKey(String o) {
		this.userKey = SiteRequest.staticSetUserKey(siteRequest_, o);
	}
	public static Long staticSetUserKey(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SiteRequest userKeyInit() {
		Wrap<Long> userKeyWrap = new Wrap<Long>().var("userKey");
		if(userKey == null) {
			_userKey(userKeyWrap);
			Optional.ofNullable(userKeyWrap.getO()).ifPresent(o -> {
				setUserKey(o);
			});
		}
		return (SiteRequest)this;
	}

	public static Long staticSearchUserKey(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrUserKey(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserKey(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserKey(siteRequest_, SiteRequest.staticSetUserKey(siteRequest_, o)).toString();
	}

	///////////////
	// sessionId //
	///////////////


	/**	 The entity sessionId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sessionId;

	/**	<br> The entity sessionId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:sessionId">Find the entity sessionId in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionId(Wrap<String> c);

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String o) {
		this.sessionId = SiteRequest.staticSetSessionId(siteRequest_, o);
	}
	public static String staticSetSessionId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest sessionIdInit() {
		Wrap<String> sessionIdWrap = new Wrap<String>().var("sessionId");
		if(sessionId == null) {
			_sessionId(sessionIdWrap);
			Optional.ofNullable(sessionIdWrap.getO()).ifPresent(o -> {
				setSessionId(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchSessionId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSessionId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSessionId(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchSessionId(siteRequest_, SiteRequest.staticSetSessionId(siteRequest_, o)).toString();
	}

	/////////////////////
	// sessionIdBefore //
	/////////////////////


	/**	 The entity sessionIdBefore
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sessionIdBefore;

	/**	<br> The entity sessionIdBefore
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:sessionIdBefore">Find the entity sessionIdBefore in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionIdBefore(Wrap<String> c);

	public String getSessionIdBefore() {
		return sessionIdBefore;
	}
	public void setSessionIdBefore(String o) {
		this.sessionIdBefore = SiteRequest.staticSetSessionIdBefore(siteRequest_, o);
	}
	public static String staticSetSessionIdBefore(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest sessionIdBeforeInit() {
		Wrap<String> sessionIdBeforeWrap = new Wrap<String>().var("sessionIdBefore");
		if(sessionIdBefore == null) {
			_sessionIdBefore(sessionIdBeforeWrap);
			Optional.ofNullable(sessionIdBeforeWrap.getO()).ifPresent(o -> {
				setSessionIdBefore(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchSessionIdBefore(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSessionIdBefore(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSessionIdBefore(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchSessionIdBefore(siteRequest_, SiteRequest.staticSetSessionIdBefore(siteRequest_, o)).toString();
	}

	//////////////
	// userName //
	//////////////


	/**	 The entity userName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userName;

	/**	<br> The entity userName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userName">Find the entity userName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userName(Wrap<String> c);

	public String getUserName() {
		return userName;
	}
	public void setUserName(String o) {
		this.userName = SiteRequest.staticSetUserName(siteRequest_, o);
	}
	public static String staticSetUserName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest userNameInit() {
		Wrap<String> userNameWrap = new Wrap<String>().var("userName");
		if(userName == null) {
			_userName(userNameWrap);
			Optional.ofNullable(userNameWrap.getO()).ifPresent(o -> {
				setUserName(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchUserName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserName(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserName(siteRequest_, SiteRequest.staticSetUserName(siteRequest_, o)).toString();
	}

	//////////////////
	// userLastName //
	//////////////////


	/**	 The entity userLastName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userLastName;

	/**	<br> The entity userLastName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userLastName">Find the entity userLastName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userLastName(Wrap<String> c);

	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String o) {
		this.userLastName = SiteRequest.staticSetUserLastName(siteRequest_, o);
	}
	public static String staticSetUserLastName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest userLastNameInit() {
		Wrap<String> userLastNameWrap = new Wrap<String>().var("userLastName");
		if(userLastName == null) {
			_userLastName(userLastNameWrap);
			Optional.ofNullable(userLastNameWrap.getO()).ifPresent(o -> {
				setUserLastName(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchUserLastName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserLastName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserLastName(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserLastName(siteRequest_, SiteRequest.staticSetUserLastName(siteRequest_, o)).toString();
	}

	///////////////////
	// userFirstName //
	///////////////////


	/**	 The entity userFirstName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userFirstName;

	/**	<br> The entity userFirstName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userFirstName">Find the entity userFirstName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userFirstName(Wrap<String> c);

	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String o) {
		this.userFirstName = SiteRequest.staticSetUserFirstName(siteRequest_, o);
	}
	public static String staticSetUserFirstName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest userFirstNameInit() {
		Wrap<String> userFirstNameWrap = new Wrap<String>().var("userFirstName");
		if(userFirstName == null) {
			_userFirstName(userFirstNameWrap);
			Optional.ofNullable(userFirstNameWrap.getO()).ifPresent(o -> {
				setUserFirstName(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchUserFirstName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserFirstName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserFirstName(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserFirstName(siteRequest_, SiteRequest.staticSetUserFirstName(siteRequest_, o)).toString();
	}

	//////////////////
	// userFullName //
	//////////////////


	/**	 The entity userFullName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userFullName;

	/**	<br> The entity userFullName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userFullName">Find the entity userFullName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userFullName(Wrap<String> c);

	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String o) {
		this.userFullName = SiteRequest.staticSetUserFullName(siteRequest_, o);
	}
	public static String staticSetUserFullName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest userFullNameInit() {
		Wrap<String> userFullNameWrap = new Wrap<String>().var("userFullName");
		if(userFullName == null) {
			_userFullName(userFullNameWrap);
			Optional.ofNullable(userFullNameWrap.getO()).ifPresent(o -> {
				setUserFullName(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchUserFullName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserFullName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserFullName(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserFullName(siteRequest_, SiteRequest.staticSetUserFullName(siteRequest_, o)).toString();
	}

	///////////////
	// userEmail //
	///////////////


	/**	 The entity userEmail
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userEmail;

	/**	<br> The entity userEmail
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userEmail">Find the entity userEmail in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userEmail(Wrap<String> c);

	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String o) {
		this.userEmail = SiteRequest.staticSetUserEmail(siteRequest_, o);
	}
	public static String staticSetUserEmail(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest userEmailInit() {
		Wrap<String> userEmailWrap = new Wrap<String>().var("userEmail");
		if(userEmail == null) {
			_userEmail(userEmailWrap);
			Optional.ofNullable(userEmailWrap.getO()).ifPresent(o -> {
				setUserEmail(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchUserEmail(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserEmail(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserEmail(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserEmail(siteRequest_, SiteRequest.staticSetUserEmail(siteRequest_, o)).toString();
	}

	////////////
	// scopes //
	////////////


	/**	 The entity scopes
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> scopes = new ArrayList<String>();

	/**	<br> The entity scopes
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:scopes">Find the entity scopes in Solr</a>
	 * <br>
	 * @param o is the entity already constructed. 
	 **/
	protected abstract void _scopes(List<String> o);

	public List<String> getScopes() {
		return scopes;
	}

	public void setScopes(List<String> scopes) {
		this.scopes = scopes;
	}
	public void setScopes(String o) {
		String l = SiteRequest.staticSetScopes(siteRequest_, o);
		if(l != null)
			addScopes(l);
	}
	public static String staticSetScopes(SiteRequest siteRequest_, String o) {
		return o;
	}
	public SiteRequest addScopes(String...objects) {
		for(String o : objects) {
			addScopes(o);
		}
		return (SiteRequest)this;
	}
	public SiteRequest addScopes(String o) {
		if(o != null)
			this.scopes.add(o);
		return (SiteRequest)this;
	}
	@JsonIgnore
	public void setScopes(JsonArray objects) {
		scopes.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addScopes(o);
		}
	}
	protected SiteRequest scopesInit() {
		_scopes(scopes);
		return (SiteRequest)this;
	}

	public static String staticSearchScopes(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrScopes(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqScopes(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchScopes(siteRequest_, SiteRequest.staticSetScopes(siteRequest_, o)).toString();
	}

	//////////////////
	// userResource //
	//////////////////


	/**	 The entity userResource
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject userResource;

	/**	<br> The entity userResource
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:userResource">Find the entity userResource in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userResource(Wrap<JsonObject> c);

	public JsonObject getUserResource() {
		return userResource;
	}

	public void setUserResource(JsonObject userResource) {
		this.userResource = userResource;
	}
	@JsonIgnore
	public void setUserResource(String o) {
		this.userResource = SiteRequest.staticSetUserResource(siteRequest_, o);
	}
	public static JsonObject staticSetUserResource(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected SiteRequest userResourceInit() {
		Wrap<JsonObject> userResourceWrap = new Wrap<JsonObject>().var("userResource");
		if(userResource == null) {
			_userResource(userResourceWrap);
			Optional.ofNullable(userResourceWrap.getO()).ifPresent(o -> {
				setUserResource(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchUserResource(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrUserResource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserResource(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchUserResource(siteRequest_, SiteRequest.staticSetUserResource(siteRequest_, o)).toString();
	}

	///////////////
	// siteUser_ //
	///////////////


	/**	 The entity siteUser_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected SiteUser siteUser_;

	/**	<br> The entity siteUser_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:siteUser_">Find the entity siteUser_ in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteUser_(Wrap<SiteUser> c);

	public SiteUser getSiteUser_() {
		return siteUser_;
	}

	public void setSiteUser_(SiteUser siteUser_) {
		this.siteUser_ = siteUser_;
	}
	public static SiteUser staticSetSiteUser_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest siteUser_Init() {
		Wrap<SiteUser> siteUser_Wrap = new Wrap<SiteUser>().var("siteUser_");
		if(siteUser_ == null) {
			_siteUser_(siteUser_Wrap);
			Optional.ofNullable(siteUser_Wrap.getO()).ifPresent(o -> {
				setSiteUser_(o);
			});
		}
		return (SiteRequest)this;
	}

	//////////
	// lang //
	//////////


	/**	 The entity lang
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String lang;

	/**	<br> The entity lang
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:lang">Find the entity lang in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _lang(Wrap<String> w);

	public String getLang() {
		return lang;
	}
	public void setLang(String o) {
		this.lang = SiteRequest.staticSetLang(siteRequest_, o);
	}
	public static String staticSetLang(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest langInit() {
		Wrap<String> langWrap = new Wrap<String>().var("lang");
		if(lang == null) {
			_lang(langWrap);
			Optional.ofNullable(langWrap.getO()).ifPresent(o -> {
				setLang(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchLang(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLang(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLang(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchLang(siteRequest_, SiteRequest.staticSetLang(siteRequest_, o)).toString();
	}

	///////////////
	// requestPk //
	///////////////


	/**	 The entity requestPk
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long requestPk;

	/**	<br> The entity requestPk
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:requestPk">Find the entity requestPk in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _requestPk(Wrap<Long> c);

	public Long getRequestPk() {
		return requestPk;
	}

	public void setRequestPk(Long requestPk) {
		this.requestPk = requestPk;
	}
	@JsonIgnore
	public void setRequestPk(String o) {
		this.requestPk = SiteRequest.staticSetRequestPk(siteRequest_, o);
	}
	public static Long staticSetRequestPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SiteRequest requestPkInit() {
		Wrap<Long> requestPkWrap = new Wrap<Long>().var("requestPk");
		if(requestPk == null) {
			_requestPk(requestPkWrap);
			Optional.ofNullable(requestPkWrap.getO()).ifPresent(o -> {
				setRequestPk(o);
			});
		}
		return (SiteRequest)this;
	}

	public static Long staticSearchRequestPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrRequestPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRequestPk(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchRequestPk(siteRequest_, SiteRequest.staticSetRequestPk(siteRequest_, o)).toString();
	}

	////////////////
	// requestUri //
	////////////////


	/**	 The entity requestUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String requestUri;

	/**	<br> The entity requestUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:requestUri">Find the entity requestUri in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _requestUri(Wrap<String> c);

	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String o) {
		this.requestUri = SiteRequest.staticSetRequestUri(siteRequest_, o);
	}
	public static String staticSetRequestUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest requestUriInit() {
		Wrap<String> requestUriWrap = new Wrap<String>().var("requestUri");
		if(requestUri == null) {
			_requestUri(requestUriWrap);
			Optional.ofNullable(requestUriWrap.getO()).ifPresent(o -> {
				setRequestUri(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchRequestUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrRequestUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRequestUri(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchRequestUri(siteRequest_, SiteRequest.staticSetRequestUri(siteRequest_, o)).toString();
	}

	///////////////////
	// requestMethod //
	///////////////////


	/**	 The entity requestMethod
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String requestMethod;

	/**	<br> The entity requestMethod
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:requestMethod">Find the entity requestMethod in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _requestMethod(Wrap<String> c);

	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String o) {
		this.requestMethod = SiteRequest.staticSetRequestMethod(siteRequest_, o);
	}
	public static String staticSetRequestMethod(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteRequest requestMethodInit() {
		Wrap<String> requestMethodWrap = new Wrap<String>().var("requestMethod");
		if(requestMethod == null) {
			_requestMethod(requestMethodWrap);
			Optional.ofNullable(requestMethodWrap.getO()).ifPresent(o -> {
				setRequestMethod(o);
			});
		}
		return (SiteRequest)this;
	}

	public static String staticSearchRequestMethod(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrRequestMethod(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRequestMethod(SiteRequest siteRequest_, String o) {
		return SiteRequest.staticSearchRequestMethod(siteRequest_, SiteRequest.staticSetRequestMethod(siteRequest_, o)).toString();
	}

	///////////////////
	// sqlConnection //
	///////////////////


	/**	 The entity sqlConnection
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected SqlConnection sqlConnection;

	/**	<br> The entity sqlConnection
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:sqlConnection">Find the entity sqlConnection in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sqlConnection(Wrap<SqlConnection> c);

	public SqlConnection getSqlConnection() {
		return sqlConnection;
	}

	public void setSqlConnection(SqlConnection sqlConnection) {
		this.sqlConnection = sqlConnection;
	}
	public static SqlConnection staticSetSqlConnection(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest sqlConnectionInit() {
		Wrap<SqlConnection> sqlConnectionWrap = new Wrap<SqlConnection>().var("sqlConnection");
		if(sqlConnection == null) {
			_sqlConnection(sqlConnectionWrap);
			Optional.ofNullable(sqlConnectionWrap.getO()).ifPresent(o -> {
				setSqlConnection(o);
			});
		}
		return (SiteRequest)this;
	}

	////////////////////
	// requestHeaders //
	////////////////////


	/**	 The entity requestHeaders
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected MultiMap requestHeaders;

	/**	<br> The entity requestHeaders
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:requestHeaders">Find the entity requestHeaders in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _requestHeaders(Wrap<MultiMap> c);

	public MultiMap getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(MultiMap requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	public static MultiMap staticSetRequestHeaders(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest requestHeadersInit() {
		Wrap<MultiMap> requestHeadersWrap = new Wrap<MultiMap>().var("requestHeaders");
		if(requestHeaders == null) {
			_requestHeaders(requestHeadersWrap);
			Optional.ofNullable(requestHeadersWrap.getO()).ifPresent(o -> {
				setRequestHeaders(o);
			});
		}
		return (SiteRequest)this;
	}

	/////////////////
	// requestVars //
	/////////////////


	/**	 The entity requestVars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Map<String, String> requestVars = new HashMap<String, String>();

	/**	<br> The entity requestVars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.request.SiteRequest&fq=entiteVar_enUS_indexed_string:requestVars">Find the entity requestVars in Solr</a>
	 * <br>
	 * @param m is the entity already constructed. 
	 **/
	protected abstract void _requestVars(Map<String, String> m);

	public Map<String, String> getRequestVars() {
		return requestVars;
	}

	public void setRequestVars(Map<String, String> requestVars) {
		this.requestVars = requestVars;
	}
	public static Map<String, String> staticSetRequestVars(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteRequest requestVarsInit() {
		_requestVars(requestVars);
		return (SiteRequest)this;
	}

	//////////////
	// initDeep //
	//////////////

	public SiteRequest initDeepSiteRequest(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		initDeepSiteRequest();
		return (SiteRequest)this;
	}

	public void initDeepSiteRequest() {
		initSiteRequest();
	}

	public void initSiteRequest() {
				configInit();
				siteRequest_Init();
				webClientInit();
				apiRequest_Init();
				jsonObjectInit();
				serviceRequestInit();
				userInit();
				userPrincipalInit();
				userIdInit();
				userKeyInit();
				sessionIdInit();
				sessionIdBeforeInit();
				userNameInit();
				userLastNameInit();
				userFirstNameInit();
				userFullNameInit();
				userEmailInit();
				scopesInit();
				userResourceInit();
				siteUser_Init();
				langInit();
				requestPkInit();
				requestUriInit();
				requestMethodInit();
				sqlConnectionInit();
				requestHeadersInit();
				requestVarsInit();
	}

	public void initDeepForClass(SiteRequest siteRequest_) {
		initDeepSiteRequest(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteRequest(SiteRequest siteRequest_) {
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestSiteRequest(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteRequest(v);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.obtainForClass(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtainSiteRequest(String var) {
		SiteRequest oSiteRequest = (SiteRequest)this;
		switch(var) {
			case "config":
				return oSiteRequest.config;
			case "siteRequest_":
				return oSiteRequest.siteRequest_;
			case "webClient":
				return oSiteRequest.webClient;
			case "apiRequest_":
				return oSiteRequest.apiRequest_;
			case "jsonObject":
				return oSiteRequest.jsonObject;
			case "serviceRequest":
				return oSiteRequest.serviceRequest;
			case "user":
				return oSiteRequest.user;
			case "userPrincipal":
				return oSiteRequest.userPrincipal;
			case "userId":
				return oSiteRequest.userId;
			case "userKey":
				return oSiteRequest.userKey;
			case "sessionId":
				return oSiteRequest.sessionId;
			case "sessionIdBefore":
				return oSiteRequest.sessionIdBefore;
			case "userName":
				return oSiteRequest.userName;
			case "userLastName":
				return oSiteRequest.userLastName;
			case "userFirstName":
				return oSiteRequest.userFirstName;
			case "userFullName":
				return oSiteRequest.userFullName;
			case "userEmail":
				return oSiteRequest.userEmail;
			case "scopes":
				return oSiteRequest.scopes;
			case "userResource":
				return oSiteRequest.userResource;
			case "siteUser_":
				return oSiteRequest.siteUser_;
			case "lang":
				return oSiteRequest.lang;
			case "requestPk":
				return oSiteRequest.requestPk;
			case "requestUri":
				return oSiteRequest.requestUri;
			case "requestMethod":
				return oSiteRequest.requestMethod;
			case "sqlConnection":
				return oSiteRequest.sqlConnection;
			case "requestHeaders":
				return oSiteRequest.requestHeaders;
			case "requestVars":
				return oSiteRequest.requestVars;
			default:
				return null;
		}
	}

	///////////////
	// relate //
	///////////////

	public boolean relateForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = relateSiteRequest(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateSiteRequest(String var, Object val) {
		SiteRequest oSiteRequest = (SiteRequest)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetSiteRequest(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSiteRequest(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "config":
			return SiteRequest.staticSetConfig(siteRequest_, o);
		case "jsonObject":
			return SiteRequest.staticSetJsonObject(siteRequest_, o);
		case "userPrincipal":
			return SiteRequest.staticSetUserPrincipal(siteRequest_, o);
		case "userId":
			return SiteRequest.staticSetUserId(siteRequest_, o);
		case "userKey":
			return SiteRequest.staticSetUserKey(siteRequest_, o);
		case "sessionId":
			return SiteRequest.staticSetSessionId(siteRequest_, o);
		case "sessionIdBefore":
			return SiteRequest.staticSetSessionIdBefore(siteRequest_, o);
		case "userName":
			return SiteRequest.staticSetUserName(siteRequest_, o);
		case "userLastName":
			return SiteRequest.staticSetUserLastName(siteRequest_, o);
		case "userFirstName":
			return SiteRequest.staticSetUserFirstName(siteRequest_, o);
		case "userFullName":
			return SiteRequest.staticSetUserFullName(siteRequest_, o);
		case "userEmail":
			return SiteRequest.staticSetUserEmail(siteRequest_, o);
		case "scopes":
			return SiteRequest.staticSetScopes(siteRequest_, o);
		case "userResource":
			return SiteRequest.staticSetUserResource(siteRequest_, o);
		case "lang":
			return SiteRequest.staticSetLang(siteRequest_, o);
		case "requestPk":
			return SiteRequest.staticSetRequestPk(siteRequest_, o);
		case "requestUri":
			return SiteRequest.staticSetRequestUri(siteRequest_, o);
		case "requestMethod":
			return SiteRequest.staticSetRequestMethod(siteRequest_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchSiteRequest(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchSiteRequest(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "config":
			return SiteRequest.staticSearchConfig(siteRequest_, (JsonObject)o);
		case "jsonObject":
			return SiteRequest.staticSearchJsonObject(siteRequest_, (JsonObject)o);
		case "userPrincipal":
			return SiteRequest.staticSearchUserPrincipal(siteRequest_, (JsonObject)o);
		case "userId":
			return SiteRequest.staticSearchUserId(siteRequest_, (String)o);
		case "userKey":
			return SiteRequest.staticSearchUserKey(siteRequest_, (Long)o);
		case "sessionId":
			return SiteRequest.staticSearchSessionId(siteRequest_, (String)o);
		case "sessionIdBefore":
			return SiteRequest.staticSearchSessionIdBefore(siteRequest_, (String)o);
		case "userName":
			return SiteRequest.staticSearchUserName(siteRequest_, (String)o);
		case "userLastName":
			return SiteRequest.staticSearchUserLastName(siteRequest_, (String)o);
		case "userFirstName":
			return SiteRequest.staticSearchUserFirstName(siteRequest_, (String)o);
		case "userFullName":
			return SiteRequest.staticSearchUserFullName(siteRequest_, (String)o);
		case "userEmail":
			return SiteRequest.staticSearchUserEmail(siteRequest_, (String)o);
		case "scopes":
			return SiteRequest.staticSearchScopes(siteRequest_, (String)o);
		case "userResource":
			return SiteRequest.staticSearchUserResource(siteRequest_, (JsonObject)o);
		case "lang":
			return SiteRequest.staticSearchLang(siteRequest_, (String)o);
		case "requestPk":
			return SiteRequest.staticSearchRequestPk(siteRequest_, (Long)o);
		case "requestUri":
			return SiteRequest.staticSearchRequestUri(siteRequest_, (String)o);
		case "requestMethod":
			return SiteRequest.staticSearchRequestMethod(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrSiteRequest(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrSiteRequest(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "config":
			return SiteRequest.staticSearchStrConfig(siteRequest_, (String)o);
		case "jsonObject":
			return SiteRequest.staticSearchStrJsonObject(siteRequest_, (String)o);
		case "userPrincipal":
			return SiteRequest.staticSearchStrUserPrincipal(siteRequest_, (String)o);
		case "userId":
			return SiteRequest.staticSearchStrUserId(siteRequest_, (String)o);
		case "userKey":
			return SiteRequest.staticSearchStrUserKey(siteRequest_, (Long)o);
		case "sessionId":
			return SiteRequest.staticSearchStrSessionId(siteRequest_, (String)o);
		case "sessionIdBefore":
			return SiteRequest.staticSearchStrSessionIdBefore(siteRequest_, (String)o);
		case "userName":
			return SiteRequest.staticSearchStrUserName(siteRequest_, (String)o);
		case "userLastName":
			return SiteRequest.staticSearchStrUserLastName(siteRequest_, (String)o);
		case "userFirstName":
			return SiteRequest.staticSearchStrUserFirstName(siteRequest_, (String)o);
		case "userFullName":
			return SiteRequest.staticSearchStrUserFullName(siteRequest_, (String)o);
		case "userEmail":
			return SiteRequest.staticSearchStrUserEmail(siteRequest_, (String)o);
		case "scopes":
			return SiteRequest.staticSearchStrScopes(siteRequest_, (String)o);
		case "userResource":
			return SiteRequest.staticSearchStrUserResource(siteRequest_, (String)o);
		case "lang":
			return SiteRequest.staticSearchStrLang(siteRequest_, (String)o);
		case "requestPk":
			return SiteRequest.staticSearchStrRequestPk(siteRequest_, (Long)o);
		case "requestUri":
			return SiteRequest.staticSearchStrRequestUri(siteRequest_, (String)o);
		case "requestMethod":
			return SiteRequest.staticSearchStrRequestMethod(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqSiteRequest(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqSiteRequest(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "config":
			return SiteRequest.staticSearchFqConfig(siteRequest_, o);
		case "jsonObject":
			return SiteRequest.staticSearchFqJsonObject(siteRequest_, o);
		case "userPrincipal":
			return SiteRequest.staticSearchFqUserPrincipal(siteRequest_, o);
		case "userId":
			return SiteRequest.staticSearchFqUserId(siteRequest_, o);
		case "userKey":
			return SiteRequest.staticSearchFqUserKey(siteRequest_, o);
		case "sessionId":
			return SiteRequest.staticSearchFqSessionId(siteRequest_, o);
		case "sessionIdBefore":
			return SiteRequest.staticSearchFqSessionIdBefore(siteRequest_, o);
		case "userName":
			return SiteRequest.staticSearchFqUserName(siteRequest_, o);
		case "userLastName":
			return SiteRequest.staticSearchFqUserLastName(siteRequest_, o);
		case "userFirstName":
			return SiteRequest.staticSearchFqUserFirstName(siteRequest_, o);
		case "userFullName":
			return SiteRequest.staticSearchFqUserFullName(siteRequest_, o);
		case "userEmail":
			return SiteRequest.staticSearchFqUserEmail(siteRequest_, o);
		case "scopes":
			return SiteRequest.staticSearchFqScopes(siteRequest_, o);
		case "userResource":
			return SiteRequest.staticSearchFqUserResource(siteRequest_, o);
		case "lang":
			return SiteRequest.staticSearchFqLang(siteRequest_, o);
		case "requestPk":
			return SiteRequest.staticSearchFqRequestPk(siteRequest_, o);
		case "requestUri":
			return SiteRequest.staticSearchFqRequestUri(siteRequest_, o);
		case "requestMethod":
			return SiteRequest.staticSearchFqRequestMethod(siteRequest_, o);
			default:
				return null;
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "SiteRequest";
	public static final String VAR_config = "config";
	public static final String VAR_siteRequest_ = "siteRequest_";
	public static final String VAR_webClient = "webClient";
	public static final String VAR_apiRequest_ = "apiRequest_";
	public static final String VAR_jsonObject = "jsonObject";
	public static final String VAR_serviceRequest = "serviceRequest";
	public static final String VAR_user = "user";
	public static final String VAR_userPrincipal = "userPrincipal";
	public static final String VAR_userId = "userId";
	public static final String VAR_userKey = "userKey";
	public static final String VAR_sessionId = "sessionId";
	public static final String VAR_sessionIdBefore = "sessionIdBefore";
	public static final String VAR_userName = "userName";
	public static final String VAR_userLastName = "userLastName";
	public static final String VAR_userFirstName = "userFirstName";
	public static final String VAR_userFullName = "userFullName";
	public static final String VAR_userEmail = "userEmail";
	public static final String VAR_scopes = "scopes";
	public static final String VAR_userResource = "userResource";
	public static final String VAR_siteUser_ = "siteUser_";
	public static final String VAR_lang = "lang";
	public static final String VAR_requestPk = "requestPk";
	public static final String VAR_requestUri = "requestUri";
	public static final String VAR_requestMethod = "requestMethod";
	public static final String VAR_sqlConnection = "sqlConnection";
	public static final String VAR_requestHeaders = "requestHeaders";
	public static final String VAR_requestVars = "requestVars";

	public static final String DISPLAY_NAME_config = "";
	public static final String DISPLAY_NAME_siteRequest_ = "";
	public static final String DISPLAY_NAME_webClient = "";
	public static final String DISPLAY_NAME_apiRequest_ = "";
	public static final String DISPLAY_NAME_jsonObject = "";
	public static final String DISPLAY_NAME_serviceRequest = "";
	public static final String DISPLAY_NAME_user = "";
	public static final String DISPLAY_NAME_userPrincipal = "";
	public static final String DISPLAY_NAME_userId = "";
	public static final String DISPLAY_NAME_userKey = "";
	public static final String DISPLAY_NAME_sessionId = "";
	public static final String DISPLAY_NAME_sessionIdBefore = "";
	public static final String DISPLAY_NAME_userName = "";
	public static final String DISPLAY_NAME_userLastName = "";
	public static final String DISPLAY_NAME_userFirstName = "";
	public static final String DISPLAY_NAME_userFullName = "";
	public static final String DISPLAY_NAME_userEmail = "";
	public static final String DISPLAY_NAME_scopes = "";
	public static final String DISPLAY_NAME_userResource = "";
	public static final String DISPLAY_NAME_siteUser_ = "";
	public static final String DISPLAY_NAME_lang = "";
	public static final String DISPLAY_NAME_requestPk = "";
	public static final String DISPLAY_NAME_requestUri = "";
	public static final String DISPLAY_NAME_requestMethod = "";
	public static final String DISPLAY_NAME_sqlConnection = "";
	public static final String DISPLAY_NAME_requestHeaders = "";
	public static final String DISPLAY_NAME_requestVars = "";

	public static String displayNameForClass(String var) {
		return SiteRequest.displayNameSiteRequest(var);
	}
	public static String displayNameSiteRequest(String var) {
		switch(var) {
		case VAR_config:
			return DISPLAY_NAME_config;
		case VAR_siteRequest_:
			return DISPLAY_NAME_siteRequest_;
		case VAR_webClient:
			return DISPLAY_NAME_webClient;
		case VAR_apiRequest_:
			return DISPLAY_NAME_apiRequest_;
		case VAR_jsonObject:
			return DISPLAY_NAME_jsonObject;
		case VAR_serviceRequest:
			return DISPLAY_NAME_serviceRequest;
		case VAR_user:
			return DISPLAY_NAME_user;
		case VAR_userPrincipal:
			return DISPLAY_NAME_userPrincipal;
		case VAR_userId:
			return DISPLAY_NAME_userId;
		case VAR_userKey:
			return DISPLAY_NAME_userKey;
		case VAR_sessionId:
			return DISPLAY_NAME_sessionId;
		case VAR_sessionIdBefore:
			return DISPLAY_NAME_sessionIdBefore;
		case VAR_userName:
			return DISPLAY_NAME_userName;
		case VAR_userLastName:
			return DISPLAY_NAME_userLastName;
		case VAR_userFirstName:
			return DISPLAY_NAME_userFirstName;
		case VAR_userFullName:
			return DISPLAY_NAME_userFullName;
		case VAR_userEmail:
			return DISPLAY_NAME_userEmail;
		case VAR_scopes:
			return DISPLAY_NAME_scopes;
		case VAR_userResource:
			return DISPLAY_NAME_userResource;
		case VAR_siteUser_:
			return DISPLAY_NAME_siteUser_;
		case VAR_lang:
			return DISPLAY_NAME_lang;
		case VAR_requestPk:
			return DISPLAY_NAME_requestPk;
		case VAR_requestUri:
			return DISPLAY_NAME_requestUri;
		case VAR_requestMethod:
			return DISPLAY_NAME_requestMethod;
		case VAR_sqlConnection:
			return DISPLAY_NAME_sqlConnection;
		case VAR_requestHeaders:
			return DISPLAY_NAME_requestHeaders;
		case VAR_requestVars:
			return DISPLAY_NAME_requestVars;
		default:
			return null;
		}
	}
}
