package org.mghpcc.aitelemetry.model.clustertemplate;

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
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ClusterTemplateGen into the class ClusterTemplate. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the ClusterTemplate API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the ClusterTemplate class and it's generated class ClusterTemplateGen&lt;BaseModel&gt;: </h3>extends ClusterTemplateGen
 * <p>
 * This Java class extends a generated Java class ClusterTemplateGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate">Find the class ClusterTemplate in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ClusterTemplateGen<BaseModel>
 * <p>This <code>class ClusterTemplate extends ClusterTemplateGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ClusterTemplateGen. 
 * The generated <code>class ClusterTemplateGen extends BaseModel</code> which means that ClusterTemplate extends ClusterTemplateGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: cluster templates"</b>, which groups all of the OpenAPIs for ClusterTemplate objects under the tag "cluster templates". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/cluster-template</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/cluster-template"</b>, which defines the base API URI for ClusterTemplate objects as "/en-us/api/cluster-template" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ClusterTemplate class will inherit the helpful inherited class comments from the super class ClusterTemplateGen. 
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
 * This creates a new Java class org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplatePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplatePage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ClusterTemplate Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a cluster template</h2>
 * <p>This class contains a comment <b>"AName.enUS: a cluster template"</b>, which identifies the language context to describe a ClusterTemplate as "a cluster template". 
 * </p>
 * <p>
 * Delete the class ClusterTemplate in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.clustertemplate in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ClusterTemplateGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(ClusterTemplate.class);

	public static final String Description_enUS = "An OpenShift cluster template";
	public static final String AName_enUS = "a cluster template";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this cluster template";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the cluster template";
	public static final String SingularName_enUS = "cluster template";
	public static final String PluralName_enUS = "cluster templates";
	public static final String NameActual_enUS = "current cluster template";
	public static final String AllName_enUS = "all cluster templates";
	public static final String SearchAllNameBy_enUS = "search cluster templates by ";
	public static final String Title_enUS = "cluster templates";
	public static final String ThePluralName_enUS = "the cluster templates";
	public static final String NoNameFound_enUS = "no cluster template found";
	public static final String ApiUri_enUS = "/en-us/api/cluster-template";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/cluster-template";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/cluster-template/{title}";
	public static final String OfName_enUS = "of cluster template";
	public static final String ANameAdjective_enUS = "a cluster template";
	public static final String NameAdjectiveSingular_enUS = "cluster template";
	public static final String NameAdjectivePlural_enUS = "cluster templates";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/cluster-template";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/cluster-template";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/cluster-template";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/cluster-template/{title}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/cluster-template/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/cluster-template/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/cluster-template";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/cluster-template";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/cluster-template";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/cluster-template";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/cluster-template";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/cluster-template";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/cluster-template/{title}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/cluster-template/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/cluster-template/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/cluster-template-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/cluster-template-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/cluster-template-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/cluster-template";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/cluster-template";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/cluster-template";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/cluster-template/{title}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/cluster-template/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/cluster-template/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/cluster-template";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/cluster-template";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/cluster-template";

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ClusterTemplate.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterTemplate idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (ClusterTemplate)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return ClusterTemplate.staticSearchId(siteRequest_, ClusterTemplate.staticSetId(siteRequest_, o)).toString();
	}

	public String sqlId() {
		return id;
	}

	///////////
	// title //
	///////////


	/**	 The entity title
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String title;

	/**	<br> The entity title
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate&fq=entiteVar_enUS_indexed_string:title">Find the entity title in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _title(Wrap<String> w);

	public String getTitle() {
		return title;
	}
	public void setTitle(String o) {
		this.title = ClusterTemplate.staticSetTitle(siteRequest_, o);
	}
	public static String staticSetTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterTemplate titleInit() {
		Wrap<String> titleWrap = new Wrap<String>().var("title");
		if(title == null) {
			_title(titleWrap);
			Optional.ofNullable(titleWrap.getO()).ifPresent(o -> {
				setTitle(o);
			});
		}
		return (ClusterTemplate)this;
	}

	public static String staticSearchTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTitle(SiteRequest siteRequest_, String o) {
		return ClusterTemplate.staticSearchTitle(siteRequest_, ClusterTemplate.staticSetTitle(siteRequest_, o)).toString();
	}

	public String sqlTitle() {
		return title;
	}

	/////////////////
	// description //
	/////////////////


	/**	 The entity description
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String description;

	/**	<br> The entity description
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = ClusterTemplate.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterTemplate descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (ClusterTemplate)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return ClusterTemplate.staticSearchDescription(siteRequest_, ClusterTemplate.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	////////////////
	// parameters //
	////////////////


	/**	 The entity parameters
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray parameters;

	/**	<br> The entity parameters
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate&fq=entiteVar_enUS_indexed_string:parameters">Find the entity parameters in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _parameters(Wrap<JsonArray> w);

	public JsonArray getParameters() {
		return parameters;
	}

	public void setParameters(JsonArray parameters) {
		this.parameters = parameters;
	}
	@JsonIgnore
	public void setParameters(String o) {
		this.parameters = ClusterTemplate.staticSetParameters(siteRequest_, o);
	}
	public static JsonArray staticSetParameters(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ClusterTemplate parametersInit() {
		Wrap<JsonArray> parametersWrap = new Wrap<JsonArray>().var("parameters");
		if(parameters == null) {
			_parameters(parametersWrap);
			Optional.ofNullable(parametersWrap.getO()).ifPresent(o -> {
				setParameters(o);
			});
		}
		return (ClusterTemplate)this;
	}

	public static String staticSearchParameters(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrParameters(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqParameters(SiteRequest siteRequest_, String o) {
		return ClusterTemplate.staticSearchParameters(siteRequest_, ClusterTemplate.staticSetParameters(siteRequest_, o)).toString();
	}

	public JsonArray sqlParameters() {
		return parameters;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ClusterTemplateGen<DEV>> promiseDeepClusterTemplate(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepClusterTemplate();
	}

	public Future<ClusterTemplateGen<DEV>> promiseDeepClusterTemplate() {
		Promise<ClusterTemplateGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseClusterTemplate(promise2);
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

	public Future<Void> promiseClusterTemplate(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				idInit();
				titleInit();
				descriptionInit();
				parametersInit();
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

	@Override public Future<? extends ClusterTemplateGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepClusterTemplate(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestClusterTemplate(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestClusterTemplate(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterTemplate(v);
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
	public Object obtainClusterTemplate(String var) {
		ClusterTemplate oClusterTemplate = (ClusterTemplate)this;
		switch(var) {
			case "id":
				return oClusterTemplate.id;
			case "title":
				return oClusterTemplate.title;
			case "description":
				return oClusterTemplate.description;
			case "parameters":
				return oClusterTemplate.parameters;
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
				o = relateClusterTemplate(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateClusterTemplate(String var, Object val) {
		ClusterTemplate oClusterTemplate = (ClusterTemplate)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetClusterTemplate(entityVar,  siteRequest_, o);
	}
	public static Object staticSetClusterTemplate(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "id":
			return ClusterTemplate.staticSetId(siteRequest_, o);
		case "title":
			return ClusterTemplate.staticSetTitle(siteRequest_, o);
		case "description":
			return ClusterTemplate.staticSetDescription(siteRequest_, o);
		case "parameters":
			return ClusterTemplate.staticSetParameters(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchClusterTemplate(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchClusterTemplate(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return ClusterTemplate.staticSearchId(siteRequest_, (String)o);
		case "title":
			return ClusterTemplate.staticSearchTitle(siteRequest_, (String)o);
		case "description":
			return ClusterTemplate.staticSearchDescription(siteRequest_, (String)o);
		case "parameters":
			return ClusterTemplate.staticSearchParameters(siteRequest_, (JsonArray)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrClusterTemplate(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrClusterTemplate(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return ClusterTemplate.staticSearchStrId(siteRequest_, (String)o);
		case "title":
			return ClusterTemplate.staticSearchStrTitle(siteRequest_, (String)o);
		case "description":
			return ClusterTemplate.staticSearchStrDescription(siteRequest_, (String)o);
		case "parameters":
			return ClusterTemplate.staticSearchStrParameters(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqClusterTemplate(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqClusterTemplate(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "id":
			return ClusterTemplate.staticSearchFqId(siteRequest_, o);
		case "title":
			return ClusterTemplate.staticSearchFqTitle(siteRequest_, o);
		case "description":
			return ClusterTemplate.staticSearchFqDescription(siteRequest_, o);
		case "parameters":
			return ClusterTemplate.staticSearchFqParameters(siteRequest_, o);
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
					o = persistClusterTemplate(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistClusterTemplate(String var, Object val) {
		String varLower = var.toLowerCase();
			if("id".equals(varLower)) {
				if(val instanceof String) {
					setId((String)val);
				}
				saves.add("id");
				return val;
			} else if("title".equals(varLower)) {
				if(val instanceof String) {
					setTitle((String)val);
				}
				saves.add("title");
				return val;
			} else if("description".equals(varLower)) {
				if(val instanceof String) {
					setDescription((String)val);
				}
				saves.add("description");
				return val;
			} else if("parameters".equals(varLower)) {
				if(val instanceof String) {
					setParameters((String)val);
				} else if(val instanceof JsonArray) {
					setParameters((JsonArray)val);
				}
				saves.add("parameters");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateClusterTemplate(doc);
	}
	public void populateClusterTemplate(SolrResponse.Doc doc) {
		ClusterTemplate oClusterTemplate = (ClusterTemplate)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("id")) {
				String id = (String)doc.get("id_docvalues_string");
				if(id != null)
					oClusterTemplate.setId(id);
			}

			if(saves.contains("title")) {
				String title = (String)doc.get("title_docvalues_string");
				if(title != null)
					oClusterTemplate.setTitle(title);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oClusterTemplate.setDescription(description);
			}

			if(saves.contains("parameters")) {
				String parameters = (String)doc.get("parameters_docvalues_string");
				if(parameters != null)
					oClusterTemplate.setParameters(parameters);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexClusterTemplate(JsonObject doc) {
		if(id != null) {
			doc.put("id_docvalues_string", id);
		}
		if(title != null) {
			doc.put("title_docvalues_string", title);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(parameters != null) {
			doc.put("parameters_docvalues_string", parameters.toString());
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredClusterTemplate(String entityVar) {
		switch(entityVar) {
			case "id":
				return "id_docvalues_string";
			case "title":
				return "title_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "parameters":
				return "parameters_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedClusterTemplate(String entityVar) {
		switch(entityVar) {
			case "id":
				return "id_docvalues_string";
			case "title":
				return "title_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "parameters":
				return "parameters_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarClusterTemplate(String searchVar) {
		switch(searchVar) {
			case "id_docvalues_string":
				return "id";
			case "title_docvalues_string":
				return "title";
			case "description_docvalues_string":
				return "description";
			case "parameters_docvalues_string":
				return "parameters";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchClusterTemplate(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedClusterTemplate(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeClusterTemplate(doc);
	}
	public void storeClusterTemplate(SolrResponse.Doc doc) {
		ClusterTemplate oClusterTemplate = (ClusterTemplate)this;
		SiteRequest siteRequest = oClusterTemplate.getSiteRequest_();

		oClusterTemplate.setId(Optional.ofNullable(doc.get("id_docvalues_string")).map(v -> v.toString()).orElse(null));
		oClusterTemplate.setTitle(Optional.ofNullable(doc.get("title_docvalues_string")).map(v -> v.toString()).orElse(null));
		oClusterTemplate.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oClusterTemplate.setParameters(Optional.ofNullable(doc.get("parameters_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestClusterTemplate() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ClusterTemplate) {
			ClusterTemplate original = (ClusterTemplate)o;
			if(!Objects.equals(id, original.getId()))
				apiRequest.addVars("id");
			if(!Objects.equals(title, original.getTitle()))
				apiRequest.addVars("title");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(parameters, original.getParameters()))
				apiRequest.addVars("parameters");
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
		sb.append(Optional.ofNullable(title).map(v -> "title: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(parameters).map(v -> "parameters: " + v + "\n").orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "ClusterTemplate";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate";
	public static final String CLASS_API_ADDRESS_ClusterTemplate = "ai-telemetry-enUS-ClusterTemplate";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_ClusterTemplate;
	}
	public static final String VAR_id = "id";
	public static final String VAR_title = "title";
	public static final String VAR_description = "description";
	public static final String VAR_parameters = "parameters";

	public static List<String> varsQForClass() {
		return ClusterTemplate.varsQClusterTemplate(new ArrayList<String>());
	}
	public static List<String> varsQClusterTemplate(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return ClusterTemplate.varsFqClusterTemplate(new ArrayList<String>());
	}
	public static List<String> varsFqClusterTemplate(List<String> vars) {
		vars.add(VAR_id);
		vars.add(VAR_title);
		vars.add(VAR_description);
		vars.add(VAR_parameters);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return ClusterTemplate.varsRangeClusterTemplate(new ArrayList<String>());
	}
	public static List<String> varsRangeClusterTemplate(List<String> vars) {
		vars.add(VAR_parameters);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_id = "template ID";
	public static final String DISPLAY_NAME_title = "template title";
	public static final String DISPLAY_NAME_description = "template description";
	public static final String DISPLAY_NAME_parameters = "template parameters";

	@Override
	public String idForClass() {
		return title;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return title;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return ClusterTemplate.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/cluster-template/%s";
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
		return ClusterTemplate.displayNameClusterTemplate(var);
	}
	public static String displayNameClusterTemplate(String var) {
		switch(var) {
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_title:
			return DISPLAY_NAME_title;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_parameters:
			return DISPLAY_NAME_parameters;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionClusterTemplate(String var) {
		switch(var) {
		case VAR_id:
			return "The ID of this template";
		case VAR_title:
			return "The title of this template";
		case VAR_description:
			return "The description of this template";
		case VAR_parameters:
			return "The parameters of this template";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameClusterTemplate(String var) {
		switch(var) {
		case VAR_id:
			return "String";
		case VAR_title:
			return "String";
		case VAR_description:
			return "String";
		case VAR_parameters:
			return "JsonArray";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnClusterTemplate(String var) {
		switch(var) {
		case VAR_title:
			return 1;
		case VAR_description:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowClusterTemplate(String var) {
		switch(var) {
		case VAR_id:
			return 3;
		case VAR_title:
			return 3;
		case VAR_description:
			return 3;
		case VAR_parameters:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellClusterTemplate(String var) {
		switch(var) {
		case VAR_id:
			return 1;
		case VAR_title:
			return 2;
		case VAR_description:
			return 3;
		case VAR_parameters:
			return 4;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinClusterTemplate(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxClusterTemplate(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxClusterTemplate(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minClusterTemplate(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
