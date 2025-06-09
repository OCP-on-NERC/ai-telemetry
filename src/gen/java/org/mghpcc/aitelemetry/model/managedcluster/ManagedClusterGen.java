package org.mghpcc.aitelemetry.model.managedcluster;

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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ManagedClusterGen into the class ManagedCluster. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the ManagedCluster API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the ManagedCluster class and it's generated class ManagedClusterGen&lt;BaseModel&gt;: </h3>extends ManagedClusterGen
 * <p>
 * This Java class extends a generated Java class ManagedClusterGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedCluster">Find the class ManagedCluster in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ManagedClusterGen<BaseModel>
 * <p>This <code>class ManagedCluster extends ManagedClusterGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ManagedClusterGen. 
 * The generated <code>class ManagedClusterGen extends BaseModel</code> which means that ManagedCluster extends ManagedClusterGen which extends BaseModel. 
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
 * <h2>ApiMethode: Download</h2>
 * <p>This class contains a comment <b>"ApiMethod: Download"</b>, which creates an API "Download". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiMethode: EditPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: EditPage"</b>, which creates an API "EditPage". 
 * </p>
 * <h2>ApiMethode: DELETEFilter</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETEFilter"</b>, which creates an API "DELETEFilter". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: tenant clusters"</b>, which groups all of the OpenAPIs for ManagedCluster objects under the tag "tenant clusters". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/managed-cluster</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/managed-cluster"</b>, which defines the base API URI for ManagedCluster objects as "/en-us/api/managed-cluster" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ManagedCluster class will inherit the helpful inherited class comments from the super class ManagedClusterGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 8</h2>
 * <p>This class contains a comment <b>"Order: 8"</b>, which means this class will be sorted by the given number 8 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 8</h2>
 * <p>This class contains a comment <b>"SqlOrder: 8"</b>, which means this class will be sorted by the given number 8 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ManagedCluster Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a tenant cluster</h2>
 * <p>This class contains a comment <b>"AName.enUS: a tenant cluster"</b>, which identifies the language context to describe a ManagedCluster as "a tenant cluster". 
 * </p>
 * <p>
 * Delete the class ManagedCluster in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedCluster&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.managedcluster in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ManagedClusterGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(ManagedCluster.class);

	public static final String Description_enUS = "An OpenShift tenant cluster";
	public static final String AName_enUS = "a tenant cluster";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this tenant cluster";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the tenant cluster";
	public static final String SingularName_enUS = "tenant cluster";
	public static final String PluralName_enUS = "tenant clusters";
	public static final String NameActual_enUS = "current tenant cluster";
	public static final String AllName_enUS = "all tenant clusters";
	public static final String SearchAllNameBy_enUS = "search tenant clusters by ";
	public static final String SearchAllName_enUS = "search tenant clusters";
	public static final String Title_enUS = "tenant clusters";
	public static final String ThePluralName_enUS = "the tenant clusters";
	public static final String NoNameFound_enUS = "no tenant cluster found";
	public static final String ApiUri_enUS = "/en-us/api/managed-cluster";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/managed-cluster";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/managed-cluster/{id}";
	public static final String OfName_enUS = "of tenant cluster";
	public static final String ANameAdjective_enUS = "a tenant cluster";
	public static final String NameAdjectiveSingular_enUS = "tenant cluster";
	public static final String NameAdjectivePlural_enUS = "tenant clusters";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/managed-cluster";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/managed-cluster";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/managed-cluster";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/managed-cluster/{id}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/managed-cluster/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/managed-cluster/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/managed-cluster";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/managed-cluster";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/managed-cluster";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/managed-cluster";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/managed-cluster";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/managed-cluster";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/managed-cluster/{id}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/managed-cluster/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/managed-cluster/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/managed-cluster-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/managed-cluster-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/managed-cluster-import";
	public static final String Download_enUS_OpenApiUri = "/en-us/download/kubeconfig/{id}";
	public static final String Download_enUS_StringFormatUri = "/en-us/download/kubeconfig/%s";
	public static final String Download_enUS_StringFormatUrl = "%s/en-us/download/kubeconfig/%s";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/managed-cluster";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/managed-cluster";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/managed-cluster";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/managed-cluster/{id}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/managed-cluster/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/managed-cluster/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/managed-cluster";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/managed-cluster";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/managed-cluster";

	public static final String Icon = "<i class=\"fa-regular fa-server\"></i>";

	////////
	// id //
	////////


	/**	 The entity id
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String id;

	/**	<br> The entity id
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedCluster&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ManagedCluster.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ManagedCluster idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (ManagedCluster)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return ManagedCluster.staticSearchId(siteRequest_, ManagedCluster.staticSetId(siteRequest_, o)).toString();
	}

	public String sqlId() {
		return id;
	}

	///////////
	// state //
	///////////


	/**	 The entity state
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String state;

	/**	<br> The entity state
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedCluster&fq=entiteVar_enUS_indexed_string:state">Find the entity state in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _state(Wrap<String> w);

	public String getState() {
		return state;
	}
	public void setState(String o) {
		this.state = ManagedCluster.staticSetState(siteRequest_, o);
	}
	public static String staticSetState(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ManagedCluster stateInit() {
		Wrap<String> stateWrap = new Wrap<String>().var("state");
		if(state == null) {
			_state(stateWrap);
			Optional.ofNullable(stateWrap.getO()).ifPresent(o -> {
				setState(o);
			});
		}
		return (ManagedCluster)this;
	}

	public static String staticSearchState(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrState(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqState(SiteRequest siteRequest_, String o) {
		return ManagedCluster.staticSearchState(siteRequest_, ManagedCluster.staticSetState(siteRequest_, o)).toString();
	}

	public String sqlState() {
		return state;
	}

	////////////
	// apiUrl //
	////////////


	/**	 The entity apiUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String apiUrl;

	/**	<br> The entity apiUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedCluster&fq=entiteVar_enUS_indexed_string:apiUrl">Find the entity apiUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _apiUrl(Wrap<String> w);

	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String o) {
		this.apiUrl = ManagedCluster.staticSetApiUrl(siteRequest_, o);
	}
	public static String staticSetApiUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ManagedCluster apiUrlInit() {
		Wrap<String> apiUrlWrap = new Wrap<String>().var("apiUrl");
		if(apiUrl == null) {
			_apiUrl(apiUrlWrap);
			Optional.ofNullable(apiUrlWrap.getO()).ifPresent(o -> {
				setApiUrl(o);
			});
		}
		return (ManagedCluster)this;
	}

	public static String staticSearchApiUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrApiUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqApiUrl(SiteRequest siteRequest_, String o) {
		return ManagedCluster.staticSearchApiUrl(siteRequest_, ManagedCluster.staticSetApiUrl(siteRequest_, o)).toString();
	}

	public String sqlApiUrl() {
		return apiUrl;
	}

	////////////////
	// consoleUrl //
	////////////////


	/**	 The entity consoleUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String consoleUrl;

	/**	<br> The entity consoleUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedCluster&fq=entiteVar_enUS_indexed_string:consoleUrl">Find the entity consoleUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _consoleUrl(Wrap<String> w);

	public String getConsoleUrl() {
		return consoleUrl;
	}
	public void setConsoleUrl(String o) {
		this.consoleUrl = ManagedCluster.staticSetConsoleUrl(siteRequest_, o);
	}
	public static String staticSetConsoleUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ManagedCluster consoleUrlInit() {
		Wrap<String> consoleUrlWrap = new Wrap<String>().var("consoleUrl");
		if(consoleUrl == null) {
			_consoleUrl(consoleUrlWrap);
			Optional.ofNullable(consoleUrlWrap.getO()).ifPresent(o -> {
				setConsoleUrl(o);
			});
		}
		return (ManagedCluster)this;
	}

	public static String staticSearchConsoleUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrConsoleUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqConsoleUrl(SiteRequest siteRequest_, String o) {
		return ManagedCluster.staticSearchConsoleUrl(siteRequest_, ManagedCluster.staticSetConsoleUrl(siteRequest_, o)).toString();
	}

	public String sqlConsoleUrl() {
		return consoleUrl;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ManagedClusterGen<DEV>> promiseDeepManagedCluster(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepManagedCluster();
	}

	public Future<ManagedClusterGen<DEV>> promiseDeepManagedCluster() {
		Promise<ManagedClusterGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseManagedCluster(promise2);
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

	public Future<Void> promiseManagedCluster(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				idInit();
				stateInit();
				apiUrlInit();
				consoleUrlInit();
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

	@Override public Future<? extends ManagedClusterGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepManagedCluster(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestManagedCluster(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestManagedCluster(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainManagedCluster(v);
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
	public Object obtainManagedCluster(String var) {
		ManagedCluster oManagedCluster = (ManagedCluster)this;
		switch(var) {
			case "id":
				return oManagedCluster.id;
			case "state":
				return oManagedCluster.state;
			case "apiUrl":
				return oManagedCluster.apiUrl;
			case "consoleUrl":
				return oManagedCluster.consoleUrl;
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
				o = relateManagedCluster(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateManagedCluster(String var, Object val) {
		ManagedCluster oManagedCluster = (ManagedCluster)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ManagedCluster o) {
		return staticSetManagedCluster(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetManagedCluster(String entityVar, SiteRequest siteRequest_, String v, ManagedCluster o) {
		switch(entityVar) {
		case "id":
			return ManagedCluster.staticSetId(siteRequest_, v);
		case "state":
			return ManagedCluster.staticSetState(siteRequest_, v);
		case "apiUrl":
			return ManagedCluster.staticSetApiUrl(siteRequest_, v);
		case "consoleUrl":
			return ManagedCluster.staticSetConsoleUrl(siteRequest_, v);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchManagedCluster(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchManagedCluster(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return ManagedCluster.staticSearchId(siteRequest_, (String)o);
		case "state":
			return ManagedCluster.staticSearchState(siteRequest_, (String)o);
		case "apiUrl":
			return ManagedCluster.staticSearchApiUrl(siteRequest_, (String)o);
		case "consoleUrl":
			return ManagedCluster.staticSearchConsoleUrl(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrManagedCluster(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrManagedCluster(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return ManagedCluster.staticSearchStrId(siteRequest_, (String)o);
		case "state":
			return ManagedCluster.staticSearchStrState(siteRequest_, (String)o);
		case "apiUrl":
			return ManagedCluster.staticSearchStrApiUrl(siteRequest_, (String)o);
		case "consoleUrl":
			return ManagedCluster.staticSearchStrConsoleUrl(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqManagedCluster(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqManagedCluster(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "id":
			return ManagedCluster.staticSearchFqId(siteRequest_, o);
		case "state":
			return ManagedCluster.staticSearchFqState(siteRequest_, o);
		case "apiUrl":
			return ManagedCluster.staticSearchFqApiUrl(siteRequest_, o);
		case "consoleUrl":
			return ManagedCluster.staticSearchFqConsoleUrl(siteRequest_, o);
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
					o = persistManagedCluster(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistManagedCluster(String var, Object val) {
		String varLower = var.toLowerCase();
			if("id".equals(varLower)) {
				if(val instanceof String) {
					setId((String)val);
				}
				saves.add("id");
				return val;
			} else if("state".equals(varLower)) {
				if(val instanceof String) {
					setState((String)val);
				}
				saves.add("state");
				return val;
			} else if("apiurl".equals(varLower)) {
				if(val instanceof String) {
					setApiUrl((String)val);
				}
				saves.add("apiUrl");
				return val;
			} else if("consoleurl".equals(varLower)) {
				if(val instanceof String) {
					setConsoleUrl((String)val);
				}
				saves.add("consoleUrl");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateManagedCluster(doc);
	}
	public void populateManagedCluster(SolrResponse.Doc doc) {
		ManagedCluster oManagedCluster = (ManagedCluster)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("id")) {
				String id = (String)doc.get("id_docvalues_string");
				if(id != null)
					oManagedCluster.setId(id);
			}

			if(saves.contains("state")) {
				String state = (String)doc.get("state_docvalues_string");
				if(state != null)
					oManagedCluster.setState(state);
			}

			if(saves.contains("apiUrl")) {
				String apiUrl = (String)doc.get("apiUrl_docvalues_string");
				if(apiUrl != null)
					oManagedCluster.setApiUrl(apiUrl);
			}

			if(saves.contains("consoleUrl")) {
				String consoleUrl = (String)doc.get("consoleUrl_docvalues_string");
				if(consoleUrl != null)
					oManagedCluster.setConsoleUrl(consoleUrl);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexManagedCluster(JsonObject doc) {
		if(id != null) {
			doc.put("id_docvalues_string", id);
		}
		if(state != null) {
			doc.put("state_docvalues_string", state);
		}
		if(apiUrl != null) {
			doc.put("apiUrl_docvalues_string", apiUrl);
		}
		if(consoleUrl != null) {
			doc.put("consoleUrl_docvalues_string", consoleUrl);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredManagedCluster(String entityVar) {
		switch(entityVar) {
			case "id":
				return "id_docvalues_string";
			case "state":
				return "state_docvalues_string";
			case "apiUrl":
				return "apiUrl_docvalues_string";
			case "consoleUrl":
				return "consoleUrl_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedManagedCluster(String entityVar) {
		switch(entityVar) {
			case "id":
				return "id_docvalues_string";
			case "state":
				return "state_docvalues_string";
			case "apiUrl":
				return "apiUrl_docvalues_string";
			case "consoleUrl":
				return "consoleUrl_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarManagedCluster(String searchVar) {
		switch(searchVar) {
			case "id_docvalues_string":
				return "id";
			case "state_docvalues_string":
				return "state";
			case "apiUrl_docvalues_string":
				return "apiUrl";
			case "consoleUrl_docvalues_string":
				return "consoleUrl";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchManagedCluster(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedManagedCluster(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeManagedCluster(doc);
	}
	public void storeManagedCluster(SolrResponse.Doc doc) {
		ManagedCluster oManagedCluster = (ManagedCluster)this;
		SiteRequest siteRequest = oManagedCluster.getSiteRequest_();

		oManagedCluster.setId(Optional.ofNullable(doc.get("id_docvalues_string")).map(v -> v.toString()).orElse(null));
		oManagedCluster.setState(Optional.ofNullable(doc.get("state_docvalues_string")).map(v -> v.toString()).orElse(null));
		oManagedCluster.setApiUrl(Optional.ofNullable(doc.get("apiUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
		oManagedCluster.setConsoleUrl(Optional.ofNullable(doc.get("consoleUrl_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestManagedCluster() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ManagedCluster) {
			ManagedCluster original = (ManagedCluster)o;
			if(!Objects.equals(id, original.getId()))
				apiRequest.addVars("id");
			if(!Objects.equals(state, original.getState()))
				apiRequest.addVars("state");
			if(!Objects.equals(apiUrl, original.getApiUrl()))
				apiRequest.addVars("apiUrl");
			if(!Objects.equals(consoleUrl, original.getConsoleUrl()))
				apiRequest.addVars("consoleUrl");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(id).map(v -> "id: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(state).map(v -> "state: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(apiUrl).map(v -> "apiUrl: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(consoleUrl).map(v -> "consoleUrl: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "ManagedCluster";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.managedcluster.ManagedCluster";
	public static final String CLASS_API_ADDRESS_ManagedCluster = "ai-telemetry-enUS-ManagedCluster";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_ManagedCluster;
	}
	public static final String VAR_id = "id";
	public static final String VAR_state = "state";
	public static final String VAR_apiUrl = "apiUrl";
	public static final String VAR_consoleUrl = "consoleUrl";

	public static List<String> varsQForClass() {
		return ManagedCluster.varsQManagedCluster(new ArrayList<String>());
	}
	public static List<String> varsQManagedCluster(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return ManagedCluster.varsFqManagedCluster(new ArrayList<String>());
	}
	public static List<String> varsFqManagedCluster(List<String> vars) {
		vars.add(VAR_id);
		vars.add(VAR_state);
		vars.add(VAR_apiUrl);
		vars.add(VAR_consoleUrl);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return ManagedCluster.varsRangeManagedCluster(new ArrayList<String>());
	}
	public static List<String> varsRangeManagedCluster(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_id = "cluster ID";
	public static final String DISPLAY_NAME_state = "cluster state";
	public static final String DISPLAY_NAME_apiUrl = "cluster API URL";
	public static final String DISPLAY_NAME_consoleUrl = "cluster console URL";

	@Override
	public String idForClass() {
		return id;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return id;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return ManagedCluster.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/managed-cluster/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return "%s/en-us/download/kubeconfig/%s";
	}

	public static String displayNameForClass(String var) {
		return ManagedCluster.displayNameManagedCluster(var);
	}
	public static String displayNameManagedCluster(String var) {
		switch(var) {
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_state:
			return DISPLAY_NAME_state;
		case VAR_apiUrl:
			return DISPLAY_NAME_apiUrl;
		case VAR_consoleUrl:
			return DISPLAY_NAME_consoleUrl;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionManagedCluster(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_id:
			return "The ID of this cluster";
		case VAR_state:
			return "The state of this cluster";
		case VAR_apiUrl:
			return "The cluster API URL";
		case VAR_consoleUrl:
			return "The cluster console URL";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameManagedCluster(String var) {
		switch(var) {
		case VAR_id:
			return "String";
		case VAR_state:
			return "String";
		case VAR_apiUrl:
			return "String";
		case VAR_consoleUrl:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnManagedCluster(String var) {
		switch(var) {
		case VAR_id:
			return 0;
		case VAR_state:
			return 1;
		case VAR_apiUrl:
			return 2;
		case VAR_consoleUrl:
			return 3;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowManagedCluster(String var) {
		switch(var) {
		case VAR_id:
			return 3;
		case VAR_state:
			return 3;
		case VAR_apiUrl:
			return 3;
		case VAR_consoleUrl:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellManagedCluster(String var) {
		switch(var) {
		case VAR_id:
			return 1;
		case VAR_state:
			return 3;
		case VAR_apiUrl:
			return 4;
		case VAR_consoleUrl:
			return 4;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinManagedCluster(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxManagedCluster(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxManagedCluster(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minManagedCluster(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
