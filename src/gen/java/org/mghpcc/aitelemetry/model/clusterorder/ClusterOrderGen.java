package org.mghpcc.aitelemetry.model.clusterorder;

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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ClusterOrderGen into the class ClusterOrder. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the ClusterOrder API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the ClusterOrder class and it's generated class ClusterOrderGen&lt;BaseModel&gt;: </h3>extends ClusterOrderGen
 * <p>
 * This Java class extends a generated Java class ClusterOrderGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder">Find the class ClusterOrder in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ClusterOrderGen<BaseModel>
 * <p>This <code>class ClusterOrder extends ClusterOrderGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ClusterOrderGen. 
 * The generated <code>class ClusterOrderGen extends BaseModel</code> which means that ClusterOrder extends ClusterOrderGen which extends BaseModel. 
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
 * <h2>ApiMethode: DELETEFilter</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETEFilter"</b>, which creates an API "DELETEFilter". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: OpenShift cluster orders"</b>, which groups all of the OpenAPIs for ClusterOrder objects under the tag "OpenShift cluster orders". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/cluster-order</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/cluster-order"</b>, which defines the base API URI for ClusterOrder objects as "/en-us/api/cluster-order" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ClusterOrder class will inherit the helpful inherited class comments from the super class ClusterOrderGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 10</h2>
 * <p>This class contains a comment <b>"Order: 10"</b>, which means this class will be sorted by the given number 10 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 10</h2>
 * <p>This class contains a comment <b>"SqlOrder: 10"</b>, which means this class will be sorted by the given number 10 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ClusterOrder Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an OpenShift cluster order</h2>
 * <p>This class contains a comment <b>"AName.enUS: an OpenShift cluster order"</b>, which identifies the language context to describe a ClusterOrder as "an OpenShift cluster order". 
 * </p>
 * <p>
 * Delete the class ClusterOrder in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.clusterorder in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ClusterOrderGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(ClusterOrder.class);

	public static final String Description_enUS = "Manage your own Red Hat OpenShift cluster in the cloud. ";
	public static final String AName_enUS = "an OpenShift cluster order";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this OpenShift cluster order";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "theOpenShift cluster order";
	public static final String SingularName_enUS = "OpenShift cluster order";
	public static final String PluralName_enUS = "OpenShift cluster orders";
	public static final String NameActual_enUS = "current OpenShift cluster order";
	public static final String AllName_enUS = "all OpenShift cluster orders";
	public static final String SearchAllNameBy_enUS = "search OpenShift cluster orders by ";
	public static final String SearchAllName_enUS = "search OpenShift cluster orders";
	public static final String Title_enUS = "OpenShift cluster orders";
	public static final String ThePluralName_enUS = "the OpenShift cluster orders";
	public static final String NoNameFound_enUS = "no OpenShift cluster order found";
	public static final String ApiUri_enUS = "/en-us/api/cluster-order";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/cluster-order";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/cluster-order/{id}";
	public static final String OfName_enUS = "of OpenShift cluster order";
	public static final String ANameAdjective_enUS = "an OpenShift cluster order";
	public static final String NameAdjectiveSingular_enUS = "OpenShift cluster order";
	public static final String NameAdjectivePlural_enUS = "OpenShift cluster orders";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/cluster-order";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/cluster-order";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/cluster-order";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/cluster-order/{id}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/cluster-order/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/cluster-order/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/cluster-order";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/cluster-order";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/cluster-order";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/cluster-order";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/cluster-order";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/cluster-order";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/cluster-order/{id}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/cluster-order/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/cluster-order/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/cluster-order-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/cluster-order-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/cluster-order-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/cluster-order";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/cluster-order";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/cluster-order";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/cluster-order/{id}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/cluster-order/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/cluster-order/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/cluster-order";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/cluster-order";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/cluster-order";

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ClusterOrder.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterOrder idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (ClusterOrder)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return ClusterOrder.staticSearchId(siteRequest_, ClusterOrder.staticSetId(siteRequest_, o)).toString();
	}

	public String sqlId() {
		return id;
	}

	////////////////
	// templateId //
	////////////////


	/**	 The entity templateId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String templateId;

	/**	<br> The entity templateId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder&fq=entiteVar_enUS_indexed_string:templateId">Find the entity templateId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _templateId(Wrap<String> w);

	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String o) {
		this.templateId = ClusterOrder.staticSetTemplateId(siteRequest_, o);
	}
	public static String staticSetTemplateId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterOrder templateIdInit() {
		Wrap<String> templateIdWrap = new Wrap<String>().var("templateId");
		if(templateId == null) {
			_templateId(templateIdWrap);
			Optional.ofNullable(templateIdWrap.getO()).ifPresent(o -> {
				setTemplateId(o);
			});
		}
		return (ClusterOrder)this;
	}

	public static String staticSearchTemplateId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTemplateId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTemplateId(SiteRequest siteRequest_, String o) {
		return ClusterOrder.staticSearchTemplateId(siteRequest_, ClusterOrder.staticSetTemplateId(siteRequest_, o)).toString();
	}

	public String sqlTemplateId() {
		return templateId;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder&fq=entiteVar_enUS_indexed_string:state">Find the entity state in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _state(Wrap<String> w);

	public String getState() {
		return state;
	}
	public void setState(String o) {
		this.state = ClusterOrder.staticSetState(siteRequest_, o);
	}
	public static String staticSetState(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterOrder stateInit() {
		Wrap<String> stateWrap = new Wrap<String>().var("state");
		if(state == null) {
			_state(stateWrap);
			Optional.ofNullable(stateWrap.getO()).ifPresent(o -> {
				setState(o);
			});
		}
		return (ClusterOrder)this;
	}

	public static String staticSearchState(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrState(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqState(SiteRequest siteRequest_, String o) {
		return ClusterOrder.staticSearchState(siteRequest_, ClusterOrder.staticSetState(siteRequest_, o)).toString();
	}

	public String sqlState() {
		return state;
	}

	///////////////
	// clusterId //
	///////////////


	/**	 The entity clusterId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String clusterId;

	/**	<br> The entity clusterId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder&fq=entiteVar_enUS_indexed_string:clusterId">Find the entity clusterId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterId(Wrap<String> w);

	public String getClusterId() {
		return clusterId;
	}
	public void setClusterId(String o) {
		this.clusterId = ClusterOrder.staticSetClusterId(siteRequest_, o);
	}
	public static String staticSetClusterId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterOrder clusterIdInit() {
		Wrap<String> clusterIdWrap = new Wrap<String>().var("clusterId");
		if(clusterId == null) {
			_clusterId(clusterIdWrap);
			Optional.ofNullable(clusterIdWrap.getO()).ifPresent(o -> {
				setClusterId(o);
			});
		}
		return (ClusterOrder)this;
	}

	public static String staticSearchClusterId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterId(SiteRequest siteRequest_, String o) {
		return ClusterOrder.staticSearchClusterId(siteRequest_, ClusterOrder.staticSetClusterId(siteRequest_, o)).toString();
	}

	public String sqlClusterId() {
		return clusterId;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ClusterOrderGen<DEV>> promiseDeepClusterOrder(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepClusterOrder();
	}

	public Future<ClusterOrderGen<DEV>> promiseDeepClusterOrder() {
		Promise<ClusterOrderGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseClusterOrder(promise2);
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

	public Future<Void> promiseClusterOrder(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				idInit();
				templateIdInit();
				stateInit();
				clusterIdInit();
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

	@Override public Future<? extends ClusterOrderGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepClusterOrder(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestClusterOrder(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestClusterOrder(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterOrder(v);
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
	public Object obtainClusterOrder(String var) {
		ClusterOrder oClusterOrder = (ClusterOrder)this;
		switch(var) {
			case "id":
				return oClusterOrder.id;
			case "templateId":
				return oClusterOrder.templateId;
			case "state":
				return oClusterOrder.state;
			case "clusterId":
				return oClusterOrder.clusterId;
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
				o = relateClusterOrder(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateClusterOrder(String var, Object val) {
		ClusterOrder oClusterOrder = (ClusterOrder)this;
		switch(var) {
			case "templateId":
				if(oClusterOrder.getTemplateId() == null)
					oClusterOrder.setTemplateId(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
				if(!saves.contains("templateId"))
					saves.add("templateId");
				return val;
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ClusterOrder o) {
		return staticSetClusterOrder(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetClusterOrder(String entityVar, SiteRequest siteRequest_, String v, ClusterOrder o) {
		switch(entityVar) {
		case "id":
			return ClusterOrder.staticSetId(siteRequest_, v);
		case "templateId":
			return ClusterOrder.staticSetTemplateId(siteRequest_, v);
		case "state":
			return ClusterOrder.staticSetState(siteRequest_, v);
		case "clusterId":
			return ClusterOrder.staticSetClusterId(siteRequest_, v);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchClusterOrder(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchClusterOrder(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return ClusterOrder.staticSearchId(siteRequest_, (String)o);
		case "templateId":
			return ClusterOrder.staticSearchTemplateId(siteRequest_, (String)o);
		case "state":
			return ClusterOrder.staticSearchState(siteRequest_, (String)o);
		case "clusterId":
			return ClusterOrder.staticSearchClusterId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrClusterOrder(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrClusterOrder(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return ClusterOrder.staticSearchStrId(siteRequest_, (String)o);
		case "templateId":
			return ClusterOrder.staticSearchStrTemplateId(siteRequest_, (String)o);
		case "state":
			return ClusterOrder.staticSearchStrState(siteRequest_, (String)o);
		case "clusterId":
			return ClusterOrder.staticSearchStrClusterId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqClusterOrder(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqClusterOrder(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "id":
			return ClusterOrder.staticSearchFqId(siteRequest_, o);
		case "templateId":
			return ClusterOrder.staticSearchFqTemplateId(siteRequest_, o);
		case "state":
			return ClusterOrder.staticSearchFqState(siteRequest_, o);
		case "clusterId":
			return ClusterOrder.staticSearchFqClusterId(siteRequest_, o);
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
					o = persistClusterOrder(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistClusterOrder(String var, Object val) {
		String varLower = var.toLowerCase();
			if("id".equals(varLower)) {
				if(val instanceof String) {
					setId((String)val);
				}
				saves.add("id");
				return val;
			} else if("templateid".equals(varLower)) {
				if(val instanceof String) {
					setTemplateId((String)val);
				}
				saves.add("templateId");
				return val;
			} else if("state".equals(varLower)) {
				if(val instanceof String) {
					setState((String)val);
				}
				saves.add("state");
				return val;
			} else if("clusterid".equals(varLower)) {
				if(val instanceof String) {
					setClusterId((String)val);
				}
				saves.add("clusterId");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateClusterOrder(doc);
	}
	public void populateClusterOrder(SolrResponse.Doc doc) {
		ClusterOrder oClusterOrder = (ClusterOrder)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("id")) {
				String id = (String)doc.get("id_docvalues_string");
				if(id != null)
					oClusterOrder.setId(id);
			}

			String templateId = (String)doc.get("templateId_docvalues_string");
			if(templateId != null)
				oClusterOrder.setTemplateId(templateId);

			if(saves.contains("state")) {
				String state = (String)doc.get("state_docvalues_string");
				if(state != null)
					oClusterOrder.setState(state);
			}

			if(saves.contains("clusterId")) {
				String clusterId = (String)doc.get("clusterId_docvalues_string");
				if(clusterId != null)
					oClusterOrder.setClusterId(clusterId);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexClusterOrder(JsonObject doc) {
		if(id != null) {
			doc.put("id_docvalues_string", id);
		}
		if(templateId != null) {
			doc.put("templateId_docvalues_string", templateId);
		}
		if(state != null) {
			doc.put("state_docvalues_string", state);
		}
		if(clusterId != null) {
			doc.put("clusterId_docvalues_string", clusterId);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredClusterOrder(String entityVar) {
		switch(entityVar) {
			case "id":
				return "id_docvalues_string";
			case "templateId":
				return "templateId_docvalues_string";
			case "state":
				return "state_docvalues_string";
			case "clusterId":
				return "clusterId_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedClusterOrder(String entityVar) {
		switch(entityVar) {
			case "id":
				return "id_docvalues_string";
			case "templateId":
				return "templateId_docvalues_string";
			case "state":
				return "state_docvalues_string";
			case "clusterId":
				return "clusterId_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarClusterOrder(String searchVar) {
		switch(searchVar) {
			case "id_docvalues_string":
				return "id";
			case "templateId_docvalues_string":
				return "templateId";
			case "state_docvalues_string":
				return "state";
			case "clusterId_docvalues_string":
				return "clusterId";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchClusterOrder(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedClusterOrder(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeClusterOrder(doc);
	}
	public void storeClusterOrder(SolrResponse.Doc doc) {
		ClusterOrder oClusterOrder = (ClusterOrder)this;
		SiteRequest siteRequest = oClusterOrder.getSiteRequest_();

		oClusterOrder.setId(Optional.ofNullable(doc.get("id_docvalues_string")).map(v -> v.toString()).orElse(null));
		oClusterOrder.setTemplateId(Optional.ofNullable(doc.get("templateId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oClusterOrder.setState(Optional.ofNullable(doc.get("state_docvalues_string")).map(v -> v.toString()).orElse(null));
		oClusterOrder.setClusterId(Optional.ofNullable(doc.get("clusterId_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestClusterOrder() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ClusterOrder) {
			ClusterOrder original = (ClusterOrder)o;
			if(!Objects.equals(id, original.getId()))
				apiRequest.addVars("id");
			if(!Objects.equals(templateId, original.getTemplateId()))
				apiRequest.addVars("templateId");
			if(!Objects.equals(state, original.getState()))
				apiRequest.addVars("state");
			if(!Objects.equals(clusterId, original.getClusterId()))
				apiRequest.addVars("clusterId");
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
		sb.append(Optional.ofNullable(templateId).map(v -> "templateId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(state).map(v -> "state: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(clusterId).map(v -> "clusterId: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "ClusterOrder";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder";
	public static final String CLASS_AUTH_RESOURCE = "CLUSTERORDER";
	public static final String CLASS_API_ADDRESS_ClusterOrder = "ai-telemetry-enUS-ClusterOrder";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_ClusterOrder;
	}
	public static final String VAR_id = "id";
	public static final String VAR_templateId = "templateId";
	public static final String VAR_state = "state";
	public static final String VAR_clusterId = "clusterId";

	public static List<String> varsQForClass() {
		return ClusterOrder.varsQClusterOrder(new ArrayList<String>());
	}
	public static List<String> varsQClusterOrder(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return ClusterOrder.varsFqClusterOrder(new ArrayList<String>());
	}
	public static List<String> varsFqClusterOrder(List<String> vars) {
		vars.add(VAR_id);
		vars.add(VAR_templateId);
		vars.add(VAR_state);
		vars.add(VAR_clusterId);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return ClusterOrder.varsRangeClusterOrder(new ArrayList<String>());
	}
	public static List<String> varsRangeClusterOrder(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_id = "order ID";
	public static final String DISPLAY_NAME_templateId = "order template id";
	public static final String DISPLAY_NAME_state = "order state";
	public static final String DISPLAY_NAME_clusterId = "order cluster id";

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
		return ClusterOrder.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/cluster-order/%s";
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
		return null;
	}

	public static String displayNameForClass(String var) {
		return ClusterOrder.displayNameClusterOrder(var);
	}
	public static String displayNameClusterOrder(String var) {
		switch(var) {
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_templateId:
			return DISPLAY_NAME_templateId;
		case VAR_state:
			return DISPLAY_NAME_state;
		case VAR_clusterId:
			return DISPLAY_NAME_clusterId;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionClusterOrder(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_id:
			return "The ID of this order";
		case VAR_templateId:
			return "The template id used for this order";
		case VAR_state:
			return "The state of this order";
		case VAR_clusterId:
			return "The cluster id associated with the order";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameClusterOrder(String var) {
		switch(var) {
		case VAR_id:
			return "String";
		case VAR_templateId:
			return "String";
		case VAR_state:
			return "String";
		case VAR_clusterId:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnClusterOrder(String var) {
		switch(var) {
		case VAR_id:
			return 0;
		case VAR_templateId:
			return 1;
		case VAR_state:
			return 2;
		case VAR_clusterId:
			return 3;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowClusterOrder(String var) {
		switch(var) {
		case VAR_id:
			return 3;
		case VAR_templateId:
			return 3;
		case VAR_state:
			return 3;
		case VAR_clusterId:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellClusterOrder(String var) {
		switch(var) {
		case VAR_id:
			return 1;
		case VAR_templateId:
			return 2;
		case VAR_state:
			return 3;
		case VAR_clusterId:
			return 4;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinClusterOrder(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxClusterOrder(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxClusterOrder(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minClusterOrder(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
