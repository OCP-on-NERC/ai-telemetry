package org.mghpcc.aitelemetry.model.clusterrequest;

import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.model.BaseModel;
import io.vertx.core.json.JsonObject;
import java.util.Date;
import java.util.Set;
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
import java.lang.String;
import org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate;
import org.mghpcc.aitelemetry.user.SiteUser;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ClusterRequestGen into the class ClusterRequest. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the ClusterRequest API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the ClusterRequest class and it's generated class ClusterRequestGen&lt;BaseModel&gt;: </h3>extends ClusterRequestGen
 * <p>
 * This Java class extends a generated Java class ClusterRequestGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequest">Find the class ClusterRequest in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ClusterRequestGen<BaseModel>
 * <p>This <code>class ClusterRequest extends ClusterRequestGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ClusterRequestGen. 
 * The generated <code>class ClusterRequestGen extends BaseModel</code> which means that ClusterRequest extends ClusterRequestGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiMethode: Search</h2>
 * <p>This class contains a comment <b>"ApiMethod: Search"</b>, which creates an API "Search". 
 * </p>
 * <h2>ApiMethode: GET</h2>
 * <p>This class contains a comment <b>"ApiMethod: GET"</b>, which creates an API "GET". 
 * </p>
 * <h2>ApiMethode: PATCH</h2>
 * <p>This class contains a comment <b>"ApiMethod: PATCH"</b>, which creates an API "PATCH". 
 * </p>
 * <h2>ApiMethode: POST</h2>
 * <p>This class contains a comment <b>"ApiMethod: POST"</b>, which creates an API "POST". 
 * </p>
 * <h2>ApiMethode: DELETE</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETE"</b>, which creates an API "DELETE". 
 * </p>
 * <h2>ApiMethode: PUTImport</h2>
 * <p>This class contains a comment <b>"ApiMethod: PUTImport"</b>, which creates an API "PUTImport". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiMethode: EditPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: EditPage"</b>, which creates an API "EditPage". 
 * </p>
 * <h2>ApiMethode: UserPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: UserPage"</b>, which creates an API "UserPage". 
 * </p>
 * <h2>ApiMethode: DELETEFilter</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETEFilter"</b>, which creates an API "DELETEFilter". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: OpenShift cluster requests"</b>, which groups all of the OpenAPIs for ClusterRequest objects under the tag "OpenShift cluster requests". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/cluster-request</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/cluster-request"</b>, which defines the base API URI for ClusterRequest objects as "/en-us/api/cluster-request" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ClusterRequest class will inherit the helpful inherited class comments from the super class ClusterRequestGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 12</h2>
 * <p>This class contains a comment <b>"Order: 12"</b>, which means this class will be sorted by the given number 12 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 12</h2>
 * <p>This class contains a comment <b>"SqlOrder: 12"</b>, which means this class will be sorted by the given number 12 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ClusterRequest Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
 * </p>
 * <p>
 *   Adding protected void methods beginning with an underscore with a Promise as the only parameter will automatically set `Promise: true`. 
 * </p>
 * <p>
 *   <pre>
 *   
 *   	protected void _promiseBefore(Promise&lt;Void&gt; promise) {
 *   		promise.complete();
 *   	}
 *   </pre>
 * </p>
 * <p>
 *   Java classes with the `Model: true` will automatically set `Promise: true`. 
 * </p>
 * <p>
 *   If a super class of this Java class with `Model: true`, then the child class will also inherit `Promise: true`. 
 * </p>
 * <h2>AName.enUS: an OpenShift cluster request</h2>
 * <p>This class contains a comment <b>"AName.enUS: an OpenShift cluster request"</b>, which identifies the language context to describe a ClusterRequest as "an OpenShift cluster request". 
 * </p>
 * <p>
 * Delete the class ClusterRequest in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequest&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.clusterrequest in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ClusterRequestGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(ClusterRequest.class);

	public static final String Description_enUS = "An OpenShift cluster request";
	public static final String AName_enUS = "an OpenShift cluster request";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this OpenShift cluster request";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "theOpenShift cluster request";
	public static final String SingularName_enUS = "OpenShift cluster request";
	public static final String PluralName_enUS = "OpenShift cluster requests";
	public static final String NameActual_enUS = "current OpenShift cluster request";
	public static final String AllName_enUS = "all OpenShift cluster requests";
	public static final String SearchAllNameBy_enUS = "search OpenShift cluster requests by ";
	public static final String SearchAllName_enUS = "search OpenShift cluster requests";
	public static final String Title_enUS = "OpenShift cluster requests";
	public static final String ThePluralName_enUS = "the OpenShift cluster requests";
	public static final String NoNameFound_enUS = "no OpenShift cluster request found";
	public static final String ApiUri_enUS = "/en-us/api/cluster-request";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/cluster-request";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/cluster-request/{name}";
	public static final String OfName_enUS = "of OpenShift cluster request";
	public static final String ANameAdjective_enUS = "an OpenShift cluster request";
	public static final String NameAdjectiveSingular_enUS = "OpenShift cluster request";
	public static final String NameAdjectivePlural_enUS = "OpenShift cluster requests";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/cluster-request";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/cluster-request";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/cluster-request";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/cluster-request/{name}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/cluster-request/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/cluster-request/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/cluster-request";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/cluster-request";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/cluster-request";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/cluster-request";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/cluster-request";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/cluster-request";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/cluster-request/{name}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/cluster-request/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/cluster-request/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/cluster-request-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/cluster-request-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/cluster-request-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/cluster-request";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/cluster-request";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/cluster-request";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/cluster-request/{name}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/cluster-request/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/cluster-request/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/cluster-request/{name}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/cluster-request/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/cluster-request/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/cluster-request";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/cluster-request";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/cluster-request";

	public static final String Icon = "<i class=\"fa-regular fa-server\"></i>";

	//////////
	// name //
	//////////


	/**	 The entity name
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String name;

	/**	<br> The entity name
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequest&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = ClusterRequest.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterRequest nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (ClusterRequest)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return ClusterRequest.staticSearchName(siteRequest_, ClusterRequest.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
	}

	//////////////////////////
	// clusterTemplateTitle //
	//////////////////////////


	/**	 The entity clusterTemplateTitle
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String clusterTemplateTitle;

	/**	<br> The entity clusterTemplateTitle
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequest&fq=entiteVar_enUS_indexed_string:clusterTemplateTitle">Find the entity clusterTemplateTitle in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterTemplateTitle(Wrap<String> w);

	public String getClusterTemplateTitle() {
		return clusterTemplateTitle;
	}
	public void setClusterTemplateTitle(String o) {
		this.clusterTemplateTitle = ClusterRequest.staticSetClusterTemplateTitle(siteRequest_, o);
	}
	public static String staticSetClusterTemplateTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterRequest clusterTemplateTitleInit() {
		Wrap<String> clusterTemplateTitleWrap = new Wrap<String>().var("clusterTemplateTitle");
		if(clusterTemplateTitle == null) {
			_clusterTemplateTitle(clusterTemplateTitleWrap);
			Optional.ofNullable(clusterTemplateTitleWrap.getO()).ifPresent(o -> {
				setClusterTemplateTitle(o);
			});
		}
		return (ClusterRequest)this;
	}

	public static String staticSearchClusterTemplateTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterTemplateTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterTemplateTitle(SiteRequest siteRequest_, String o) {
		return ClusterRequest.staticSearchClusterTemplateTitle(siteRequest_, ClusterRequest.staticSetClusterTemplateTitle(siteRequest_, o)).toString();
	}

	public String sqlClusterTemplateTitle() {
		return clusterTemplateTitle;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequest&fq=entiteVar_enUS_indexed_string:userId">Find the entity userId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userId(Wrap<String> w);

	public String getUserId() {
		return userId;
	}
	public void setUserId(String o) {
		this.userId = ClusterRequest.staticSetUserId(siteRequest_, o);
	}
	public static String staticSetUserId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterRequest userIdInit() {
		Wrap<String> userIdWrap = new Wrap<String>().var("userId");
		if(userId == null) {
			_userId(userIdWrap);
			Optional.ofNullable(userIdWrap.getO()).ifPresent(o -> {
				setUserId(o);
			});
		}
		return (ClusterRequest)this;
	}

	public static String staticSearchUserId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserId(SiteRequest siteRequest_, String o) {
		return ClusterRequest.staticSearchUserId(siteRequest_, ClusterRequest.staticSetUserId(siteRequest_, o)).toString();
	}

	public String sqlUserId() {
		return userId;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ClusterRequestGen<DEV>> promiseDeepClusterRequest(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepClusterRequest();
	}

	public Future<ClusterRequestGen<DEV>> promiseDeepClusterRequest() {
		Promise<ClusterRequestGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseClusterRequest(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseModel(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseClusterRequest(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				clusterTemplateTitleInit();
				userIdInit();
				promise2.complete();
			} catch(Exception ex) {
				promise2.fail(ex);
			}
			return promise2.future();
		}).onSuccess(a -> {
			promise.complete();
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override public Future<? extends ClusterRequestGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepClusterRequest(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestClusterRequest(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestClusterRequest(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterRequest(v);
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
	public Object obtainClusterRequest(String var) {
		ClusterRequest oClusterRequest = (ClusterRequest)this;
		switch(var) {
			case "name":
				return oClusterRequest.name;
			case "clusterTemplateTitle":
				return oClusterRequest.clusterTemplateTitle;
			case "userId":
				return oClusterRequest.userId;
			default:
				return super.obtainBaseModel(var);
		}
	}

	///////////////
	// relate //
	///////////////

	@Override public boolean relateForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = relateClusterRequest(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateClusterRequest(String var, Object val) {
		ClusterRequest oClusterRequest = (ClusterRequest)this;
		switch(var) {
			case "clusterTemplateTitle":
				if(oClusterRequest.getClusterTemplateTitle() == null)
					oClusterRequest.setClusterTemplateTitle(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
				if(!saves.contains("clusterTemplateTitle"))
					saves.add("clusterTemplateTitle");
				return val;
			case "userId":
				if(oClusterRequest.getUserId() == null)
					oClusterRequest.setUserId(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
				if(!saves.contains("userId"))
					saves.add("userId");
				return val;
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ClusterRequest o) {
		return staticSetClusterRequest(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetClusterRequest(String entityVar, SiteRequest siteRequest_, String v, ClusterRequest o) {
		switch(entityVar) {
		case "name":
			return ClusterRequest.staticSetName(siteRequest_, v);
		case "clusterTemplateTitle":
			return ClusterRequest.staticSetClusterTemplateTitle(siteRequest_, v);
		case "userId":
			return ClusterRequest.staticSetUserId(siteRequest_, v);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchClusterRequest(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchClusterRequest(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return ClusterRequest.staticSearchName(siteRequest_, (String)o);
		case "clusterTemplateTitle":
			return ClusterRequest.staticSearchClusterTemplateTitle(siteRequest_, (String)o);
		case "userId":
			return ClusterRequest.staticSearchUserId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrClusterRequest(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrClusterRequest(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return ClusterRequest.staticSearchStrName(siteRequest_, (String)o);
		case "clusterTemplateTitle":
			return ClusterRequest.staticSearchStrClusterTemplateTitle(siteRequest_, (String)o);
		case "userId":
			return ClusterRequest.staticSearchStrUserId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqClusterRequest(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqClusterRequest(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return ClusterRequest.staticSearchFqName(siteRequest_, o);
		case "clusterTemplateTitle":
			return ClusterRequest.staticSearchFqClusterTemplateTitle(siteRequest_, o);
		case "userId":
			return ClusterRequest.staticSearchFqUserId(siteRequest_, o);
			default:
				return BaseModel.staticSearchFqBaseModel(entityVar,  siteRequest_, o);
		}
	}

	/////////////
	// persist //
	/////////////

	@Override public boolean persistForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = persistClusterRequest(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistClusterRequest(String var, Object val) {
		String varLower = var.toLowerCase();
			if("name".equals(varLower)) {
				if(val instanceof String) {
					setName((String)val);
				}
				saves.add("name");
				return val;
			} else if("clustertemplatetitle".equals(varLower)) {
				if(val instanceof String) {
					setClusterTemplateTitle((String)val);
				}
				saves.add("clusterTemplateTitle");
				return val;
			} else if("userid".equals(varLower)) {
				if(val instanceof String) {
					setUserId((String)val);
				}
				saves.add("userId");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateClusterRequest(doc);
	}
	public void populateClusterRequest(SolrResponse.Doc doc) {
		ClusterRequest oClusterRequest = (ClusterRequest)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oClusterRequest.setName(name);
			}

			String clusterTemplateTitle = (String)doc.get("clusterTemplateTitle_docvalues_string");
			if(clusterTemplateTitle != null)
				oClusterRequest.setClusterTemplateTitle(clusterTemplateTitle);

			String userId = (String)doc.get("userId_docvalues_string");
			if(userId != null)
				oClusterRequest.setUserId(userId);
		}

		super.populateBaseModel(doc);
	}

	public void indexClusterRequest(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(clusterTemplateTitle != null) {
			doc.put("clusterTemplateTitle_docvalues_string", clusterTemplateTitle);
		}
		if(userId != null) {
			doc.put("userId_docvalues_string", userId);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredClusterRequest(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "clusterTemplateTitle":
				return "clusterTemplateTitle_docvalues_string";
			case "userId":
				return "userId_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedClusterRequest(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "clusterTemplateTitle":
				return "clusterTemplateTitle_docvalues_string";
			case "userId":
				return "userId_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarClusterRequest(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "clusterTemplateTitle_docvalues_string":
				return "clusterTemplateTitle";
			case "userId_docvalues_string":
				return "userId";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchClusterRequest(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedClusterRequest(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeClusterRequest(doc);
	}
	public void storeClusterRequest(SolrResponse.Doc doc) {
		ClusterRequest oClusterRequest = (ClusterRequest)this;
		SiteRequest siteRequest = oClusterRequest.getSiteRequest_();

		oClusterRequest.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oClusterRequest.setClusterTemplateTitle(Optional.ofNullable(doc.get("clusterTemplateTitle_docvalues_string")).map(v -> v.toString()).orElse(null));
		oClusterRequest.setUserId(Optional.ofNullable(doc.get("userId_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestClusterRequest() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ClusterRequest) {
			ClusterRequest original = (ClusterRequest)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(clusterTemplateTitle, original.getClusterTemplateTitle()))
				apiRequest.addVars("clusterTemplateTitle");
			if(!Objects.equals(userId, original.getUserId()))
				apiRequest.addVars("userId");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(clusterTemplateTitle).map(v -> "clusterTemplateTitle: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userId).map(v -> "userId: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "ClusterRequest";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequest";
	public static final String CLASS_AUTH_RESOURCE = "CLUSTERREQUEST";
	public static final String CLASS_API_ADDRESS_ClusterRequest = "ai-telemetry-enUS-ClusterRequest";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_ClusterRequest;
	}
	public static final String VAR_name = "name";
	public static final String VAR_clusterTemplateTitle = "clusterTemplateTitle";
	public static final String VAR_userId = "userId";

	public static List<String> varsQForClass() {
		return ClusterRequest.varsQClusterRequest(new ArrayList<String>());
	}
	public static List<String> varsQClusterRequest(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return ClusterRequest.varsFqClusterRequest(new ArrayList<String>());
	}
	public static List<String> varsFqClusterRequest(List<String> vars) {
		vars.add(VAR_name);
		vars.add(VAR_clusterTemplateTitle);
		vars.add(VAR_userId);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return ClusterRequest.varsRangeClusterRequest(new ArrayList<String>());
	}
	public static List<String> varsRangeClusterRequest(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "cluster name";
	public static final String DISPLAY_NAME_clusterTemplateTitle = "cluster template";
	public static final String DISPLAY_NAME_userId = "user";

	@Override
	public String idForClass() {
		return name;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return name;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return ClusterRequest.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/cluster-request/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/cluster-request/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return ClusterRequest.displayNameClusterRequest(var);
	}
	public static String displayNameClusterRequest(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_clusterTemplateTitle:
			return DISPLAY_NAME_clusterTemplateTitle;
		case VAR_userId:
			return DISPLAY_NAME_userId;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionClusterRequest(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_name:
			return "The name of this cluster";
		case VAR_clusterTemplateTitle:
			return "The cluster template to use for this request. ";
		case VAR_userId:
			return "The user who requested the cluster";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameClusterRequest(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_clusterTemplateTitle:
			return "String";
		case VAR_userId:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnClusterRequest(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_clusterTemplateTitle:
			return 2;
		case VAR_userId:
			return 3;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowClusterRequest(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_clusterTemplateTitle:
			return 3;
		case VAR_userId:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellClusterRequest(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_clusterTemplateTitle:
			return 2;
		case VAR_userId:
			return 3;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinClusterRequest(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxClusterRequest(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxClusterRequest(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minClusterRequest(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
