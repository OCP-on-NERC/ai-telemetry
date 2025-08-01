package org.mghpcc.aitelemetry.model.hub;

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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HubGen into the class Hub. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the Hub API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the Hub class and it's generated class HubGen&lt;BaseModel&gt;: </h3>extends HubGen
 * <p>
 * This Java class extends a generated Java class HubGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.hub.Hub">Find the class Hub in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HubGen<BaseModel>
 * <p>This <code>class Hub extends HubGen&lt;BaseModel&gt;</code>, which means it extends a newly generated HubGen. 
 * The generated <code>class HubGen extends BaseModel</code> which means that Hub extends HubGen which extends BaseModel. 
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
 * <h2>ApiMethode: DisplayPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: DisplayPage"</b>, which creates an API "DisplayPage". 
 * </p>
 * <h2>ApiMethode: UserPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: UserPage"</b>, which creates an API "UserPage". 
 * </p>
 * <h2>ApiMethode: DELETEFilter</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETEFilter"</b>, which creates an API "DELETEFilter". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: hubs"</b>, which groups all of the OpenAPIs for Hub objects under the tag "hubs". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/hub</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/hub"</b>, which defines the base API URI for Hub objects as "/en-us/api/hub" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the Hub class will inherit the helpful inherited class comments from the super class HubGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 4</h2>
 * <p>This class contains a comment <b>"Order: 4"</b>, which means this class will be sorted by the given number 4 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 4</h2>
 * <p>This class contains a comment <b>"SqlOrder: 4"</b>, which means this class will be sorted by the given number 4 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.hub.HubPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.hub.HubPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the Hub Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a hub</h2>
 * <p>This class contains a comment <b>"AName.enUS: a hub"</b>, which identifies the language context to describe a Hub as "a hub". 
 * </p>
 * <p>
 * Delete the class Hub in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.hub.Hub&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.hub in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.hub&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class HubGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(Hub.class);

	public static final String Description_enUS = "An Red Hat Advanced Cluster Management Hub cluster. ";
	public static final String AName_enUS = "a hub";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this hub";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "thehub";
	public static final String SingularName_enUS = "hub";
	public static final String PluralName_enUS = "hubs";
	public static final String NameActual_enUS = "current hub";
	public static final String AllName_enUS = "all hubs";
	public static final String SearchAllNameBy_enUS = "search hubs by ";
	public static final String SearchAllName_enUS = "search hubs";
	public static final String Title_enUS = "hubs";
	public static final String ThePluralName_enUS = "the hubs";
	public static final String NoNameFound_enUS = "no hub found";
	public static final String ApiUri_enUS = "/en-us/api/hub";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/hub";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/hub/{authResource}";
	public static final String OfName_enUS = "of hub";
	public static final String ANameAdjective_enUS = "an hub";
	public static final String NameAdjectiveSingular_enUS = "hub";
	public static final String NameAdjectivePlural_enUS = "hubs";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/hub";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/hub";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/hub";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/hub/{hubResource}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/hub/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/hub/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/hub";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/hub";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/hub";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/hub";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/hub";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/hub";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/hub/{hubResource}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/hub/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/hub/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/hub-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/hub-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/hub-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/hub";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/hub";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/hub";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/hub/{authResource}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/hub/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/hub/%s";
	public static final String DisplayPage_enUS_OpenApiUri = "/en-us/view/hub/{authResource}";
	public static final String DisplayPage_enUS_StringFormatUri = "/en-us/view/hub/%s";
	public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/view/hub/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/hub/{authResource}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/hub/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/hub/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/hub";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/hub";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/hub";

	public static final String Icon = "<i class=\"fa-regular fa-sitemap\"></i>";

	/////////////
	// hubName //
	/////////////


	/**	 The entity hubName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String hubName;

	/**	<br> The entity hubName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.hub.Hub&fq=entiteVar_enUS_indexed_string:hubName">Find the entity hubName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _hubName(Wrap<String> w);

	public String getHubName() {
		return hubName;
	}
	public void setHubName(String o) {
		this.hubName = Hub.staticSetHubName(siteRequest_, o);
	}
	public static String staticSetHubName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Hub hubNameInit() {
		Wrap<String> hubNameWrap = new Wrap<String>().var("hubName");
		if(hubName == null) {
			_hubName(hubNameWrap);
			Optional.ofNullable(hubNameWrap.getO()).ifPresent(o -> {
				setHubName(o);
			});
		}
		return (Hub)this;
	}

	public static String staticSearchHubName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrHubName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqHubName(SiteRequest siteRequest_, String o) {
		return Hub.staticSearchHubName(siteRequest_, Hub.staticSetHubName(siteRequest_, o)).toString();
	}

	public String sqlHubName() {
		return hubName;
	}

	///////////
	// hubId //
	///////////


	/**	 The entity hubId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String hubId;

	/**	<br> The entity hubId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.hub.Hub&fq=entiteVar_enUS_indexed_string:hubId">Find the entity hubId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _hubId(Wrap<String> w);

	public String getHubId() {
		return hubId;
	}
	public void setHubId(String o) {
		this.hubId = Hub.staticSetHubId(siteRequest_, o);
	}
	public static String staticSetHubId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Hub hubIdInit() {
		Wrap<String> hubIdWrap = new Wrap<String>().var("hubId");
		if(hubId == null) {
			_hubId(hubIdWrap);
			Optional.ofNullable(hubIdWrap.getO()).ifPresent(o -> {
				setHubId(o);
			});
		}
		return (Hub)this;
	}

	public static String staticSearchHubId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrHubId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqHubId(SiteRequest siteRequest_, String o) {
		return Hub.staticSearchHubId(siteRequest_, Hub.staticSetHubId(siteRequest_, o)).toString();
	}

	public String sqlHubId() {
		return hubId;
	}

	/////////////////
	// hubResource //
	/////////////////


	/**	 The entity hubResource
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String hubResource;

	/**	<br> The entity hubResource
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.hub.Hub&fq=entiteVar_enUS_indexed_string:hubResource">Find the entity hubResource in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _hubResource(Wrap<String> w);

	public String getHubResource() {
		return hubResource;
	}
	public void setHubResource(String o) {
		this.hubResource = Hub.staticSetHubResource(siteRequest_, o);
	}
	public static String staticSetHubResource(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Hub hubResourceInit() {
		Wrap<String> hubResourceWrap = new Wrap<String>().var("hubResource");
		if(hubResource == null) {
			_hubResource(hubResourceWrap);
			Optional.ofNullable(hubResourceWrap.getO()).ifPresent(o -> {
				setHubResource(o);
			});
		}
		return (Hub)this;
	}

	public static String staticSearchHubResource(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrHubResource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqHubResource(SiteRequest siteRequest_, String o) {
		return Hub.staticSearchHubResource(siteRequest_, Hub.staticSetHubResource(siteRequest_, o)).toString();
	}

	public String sqlHubResource() {
		return hubResource;
	}

	////////////
	// pageId //
	////////////


	/**	 The entity pageId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageId;

	/**	<br> The entity pageId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.hub.Hub&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = Hub.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Hub pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (Hub)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return Hub.staticSearchPageId(siteRequest_, Hub.staticSetPageId(siteRequest_, o)).toString();
	}

	public String sqlPageId() {
		return pageId;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.hub.Hub&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = Hub.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Hub descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (Hub)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return Hub.staticSearchDescription(siteRequest_, Hub.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<HubGen<DEV>> promiseDeepHub(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepHub();
	}

	public Future<HubGen<DEV>> promiseDeepHub() {
		Promise<HubGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseHub(promise2);
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

	public Future<Void> promiseHub(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				hubNameInit();
				hubIdInit();
				hubResourceInit();
				pageIdInit();
				descriptionInit();
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

	@Override public Future<? extends HubGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepHub(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestHub(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestHub(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainHub(v);
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
	public Object obtainHub(String var) {
		Hub oHub = (Hub)this;
		switch(var) {
			case "hubName":
				return oHub.hubName;
			case "hubId":
				return oHub.hubId;
			case "hubResource":
				return oHub.hubResource;
			case "pageId":
				return oHub.pageId;
			case "description":
				return oHub.description;
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
				o = relateHub(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateHub(String var, Object val) {
		Hub oHub = (Hub)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, Hub o) {
		return staticSetHub(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetHub(String entityVar, SiteRequest siteRequest_, String v, Hub o) {
		switch(entityVar) {
		case "hubName":
			return Hub.staticSetHubName(siteRequest_, v);
		case "hubId":
			return Hub.staticSetHubId(siteRequest_, v);
		case "hubResource":
			return Hub.staticSetHubResource(siteRequest_, v);
		case "pageId":
			return Hub.staticSetPageId(siteRequest_, v);
		case "description":
			return Hub.staticSetDescription(siteRequest_, v);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchHub(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchHub(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "hubName":
			return Hub.staticSearchHubName(siteRequest_, (String)o);
		case "hubId":
			return Hub.staticSearchHubId(siteRequest_, (String)o);
		case "hubResource":
			return Hub.staticSearchHubResource(siteRequest_, (String)o);
		case "pageId":
			return Hub.staticSearchPageId(siteRequest_, (String)o);
		case "description":
			return Hub.staticSearchDescription(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrHub(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrHub(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "hubName":
			return Hub.staticSearchStrHubName(siteRequest_, (String)o);
		case "hubId":
			return Hub.staticSearchStrHubId(siteRequest_, (String)o);
		case "hubResource":
			return Hub.staticSearchStrHubResource(siteRequest_, (String)o);
		case "pageId":
			return Hub.staticSearchStrPageId(siteRequest_, (String)o);
		case "description":
			return Hub.staticSearchStrDescription(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqHub(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqHub(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "hubName":
			return Hub.staticSearchFqHubName(siteRequest_, o);
		case "hubId":
			return Hub.staticSearchFqHubId(siteRequest_, o);
		case "hubResource":
			return Hub.staticSearchFqHubResource(siteRequest_, o);
		case "pageId":
			return Hub.staticSearchFqPageId(siteRequest_, o);
		case "description":
			return Hub.staticSearchFqDescription(siteRequest_, o);
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
					o = persistHub(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistHub(String var, Object val) {
		String varLower = var.toLowerCase();
			if("hubname".equals(varLower)) {
				if(val instanceof String) {
					setHubName((String)val);
				}
				saves.add("hubName");
				return val;
			} else if("hubid".equals(varLower)) {
				if(val instanceof String) {
					setHubId((String)val);
				}
				saves.add("hubId");
				return val;
			} else if("hubresource".equals(varLower)) {
				if(val instanceof String) {
					setHubResource((String)val);
				}
				saves.add("hubResource");
				return val;
			} else if("pageid".equals(varLower)) {
				if(val instanceof String) {
					setPageId((String)val);
				}
				saves.add("pageId");
				return val;
			} else if("description".equals(varLower)) {
				if(val instanceof String) {
					setDescription((String)val);
				}
				saves.add("description");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateHub(doc);
	}
	public void populateHub(SolrResponse.Doc doc) {
		Hub oHub = (Hub)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("hubName")) {
				String hubName = (String)doc.get("hubName_docvalues_string");
				if(hubName != null)
					oHub.setHubName(hubName);
			}

			if(saves.contains("hubId")) {
				String hubId = (String)doc.get("hubId_docvalues_string");
				if(hubId != null)
					oHub.setHubId(hubId);
			}

			if(saves.contains("hubResource")) {
				String hubResource = (String)doc.get("hubResource_docvalues_string");
				if(hubResource != null)
					oHub.setHubResource(hubResource);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oHub.setPageId(pageId);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oHub.setDescription(description);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexHub(JsonObject doc) {
		if(hubName != null) {
			doc.put("hubName_docvalues_string", hubName);
		}
		if(hubId != null) {
			doc.put("hubId_docvalues_string", hubId);
		}
		if(hubResource != null) {
			doc.put("hubResource_docvalues_string", hubResource);
		}
		if(pageId != null) {
			doc.put("pageId_docvalues_string", pageId);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredHub(String entityVar) {
		switch(entityVar) {
			case "hubName":
				return "hubName_docvalues_string";
			case "hubId":
				return "hubId_docvalues_string";
			case "hubResource":
				return "hubResource_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "description":
				return "description_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedHub(String entityVar) {
		switch(entityVar) {
			case "hubName":
				return "hubName_docvalues_string";
			case "hubId":
				return "hubId_docvalues_string";
			case "hubResource":
				return "hubResource_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "description":
				return "description_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarHub(String searchVar) {
		switch(searchVar) {
			case "hubName_docvalues_string":
				return "hubName";
			case "hubId_docvalues_string":
				return "hubId";
			case "hubResource_docvalues_string":
				return "hubResource";
			case "pageId_docvalues_string":
				return "pageId";
			case "description_docvalues_string":
				return "description";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchHub(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedHub(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeHub(doc);
	}
	public void storeHub(SolrResponse.Doc doc) {
		Hub oHub = (Hub)this;
		SiteRequest siteRequest = oHub.getSiteRequest_();

		oHub.setHubName(Optional.ofNullable(doc.get("hubName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oHub.setHubId(Optional.ofNullable(doc.get("hubId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oHub.setHubResource(Optional.ofNullable(doc.get("hubResource_docvalues_string")).map(v -> v.toString()).orElse(null));
		oHub.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oHub.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestHub() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof Hub) {
			Hub original = (Hub)o;
			if(!Objects.equals(hubName, original.getHubName()))
				apiRequest.addVars("hubName");
			if(!Objects.equals(hubId, original.getHubId()))
				apiRequest.addVars("hubId");
			if(!Objects.equals(hubResource, original.getHubResource()))
				apiRequest.addVars("hubResource");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(hubName).map(v -> "hubName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(hubId).map(v -> "hubId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(hubResource).map(v -> "hubResource: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "Hub";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.hub.Hub";
	public static final String CLASS_AUTH_RESOURCE = "HUB";
	public static final String CLASS_API_ADDRESS_Hub = "ai-telemetry-enUS-Hub";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_Hub;
	}
	public static final String VAR_hubName = "hubName";
	public static final String VAR_hubId = "hubId";
	public static final String VAR_hubResource = "hubResource";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_description = "description";

	public static List<String> varsQForClass() {
		return Hub.varsQHub(new ArrayList<String>());
	}
	public static List<String> varsQHub(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return Hub.varsFqHub(new ArrayList<String>());
	}
	public static List<String> varsFqHub(List<String> vars) {
		vars.add(VAR_hubName);
		vars.add(VAR_hubId);
		vars.add(VAR_hubResource);
		vars.add(VAR_pageId);
		vars.add(VAR_description);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return Hub.varsRangeHub(new ArrayList<String>());
	}
	public static List<String> varsRangeHub(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_hubName = "hub name";
	public static final String DISPLAY_NAME_hubId = "hub ID";
	public static final String DISPLAY_NAME_hubResource = "hub auth resource";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_description = "description";

	@Override
	public String idForClass() {
		return hubResource;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return hubName;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return Hub.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/hub/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return "%s/en-us/view/hub/%s";
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/hub/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return Hub.displayNameHub(var);
	}
	public static String displayNameHub(String var) {
		switch(var) {
		case VAR_hubName:
			return DISPLAY_NAME_hubName;
		case VAR_hubId:
			return DISPLAY_NAME_hubId;
		case VAR_hubResource:
			return DISPLAY_NAME_hubResource;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_description:
			return DISPLAY_NAME_description;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionHub(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_hubName:
			return "The name of this hub";
		case VAR_hubId:
			return "The ID of this hub";
		case VAR_hubResource:
			return "The unique authorization resource for the hub for multi-tenancy";
		case VAR_pageId:
			return "The ID for this page. ";
		case VAR_description:
			return "A description of this hub";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameHub(String var) {
		switch(var) {
		case VAR_hubName:
			return "String";
		case VAR_hubId:
			return "String";
		case VAR_hubResource:
			return "String";
		case VAR_pageId:
			return "String";
		case VAR_description:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnHub(String var) {
		switch(var) {
		case VAR_hubName:
			return 1;
		case VAR_hubId:
			return 2;
		case VAR_description:
			return 3;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowHub(String var) {
		switch(var) {
		case VAR_hubName:
			return 3;
		case VAR_hubId:
			return 3;
		case VAR_pageId:
			return 99;
		case VAR_description:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellHub(String var) {
		switch(var) {
		case VAR_hubName:
			return 1;
		case VAR_hubId:
			return 2;
		case VAR_pageId:
			return 1;
		case VAR_description:
			return 4;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinHub(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxHub(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxHub(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minHub(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
