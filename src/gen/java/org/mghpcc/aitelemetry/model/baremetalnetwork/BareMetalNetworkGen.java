package org.mghpcc.aitelemetry.model.baremetalnetwork;

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
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.Locale;
import java.time.OffsetDateTime;
import java.lang.Integer;
import java.lang.Boolean;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalNetworkGen into the class BareMetalNetwork. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the BareMetalNetwork API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the BareMetalNetwork class and it's generated class BareMetalNetworkGen&lt;BaseModel&gt;: </h3>extends BareMetalNetworkGen
 * <p>
 * This Java class extends a generated Java class BareMetalNetworkGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork">Find the class BareMetalNetwork in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalNetworkGen<BaseModel>
 * <p>This <code>class BareMetalNetwork extends BareMetalNetworkGen&lt;BaseModel&gt;</code>, which means it extends a newly generated BareMetalNetworkGen. 
 * The generated <code>class BareMetalNetworkGen extends BaseModel</code> which means that BareMetalNetwork extends BareMetalNetworkGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: bare metal networks"</b>, which groups all of the OpenAPIs for BareMetalNetwork objects under the tag "bare metal networks". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/bare-metal-network</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/bare-metal-network"</b>, which defines the base API URI for BareMetalNetwork objects as "/en-us/api/bare-metal-network" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalNetwork class will inherit the helpful inherited class comments from the super class BareMetalNetworkGen. 
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
 * This creates a new Java class org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetworkPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetworkPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalNetwork Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a bare metal network</h2>
 * <p>This class contains a comment <b>"AName.enUS: a bare metal network"</b>, which identifies the language context to describe a BareMetalNetwork as "a bare metal network". 
 * </p>
 * <p>
 * Delete the class BareMetalNetwork in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.baremetalnetwork in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BareMetalNetworkGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalNetwork.class);

	public static final String Description_enUS = "An OpenStack bare metal network";
	public static final String AName_enUS = "a bare metal network";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this bare metal network";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the bare metal network";
	public static final String SingularName_enUS = "bare metal network";
	public static final String PluralName_enUS = "bare metal networks";
	public static final String NameActual_enUS = "current bare metal network";
	public static final String AllName_enUS = "all bare metal networks";
	public static final String SearchAllNameBy_enUS = "search bare metal networks by ";
	public static final String Title_enUS = "bare metal networks";
	public static final String ThePluralName_enUS = "the bare metal networks";
	public static final String NoNameFound_enUS = "no bare metal network found";
	public static final String ApiUri_enUS = "/en-us/api/bare-metal-network";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/bare-metal-network";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/bare-metal-network/{id}";
	public static final String OfName_enUS = "of bare metal network";
	public static final String ANameAdjective_enUS = "a bare metal network";
	public static final String NameAdjectiveSingular_enUS = "bare metal network";
	public static final String NameAdjectivePlural_enUS = "bare metal networks";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/bare-metal-network";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/bare-metal-network";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-network";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/bare-metal-network/{id}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/bare-metal-network/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-network/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/bare-metal-network";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/bare-metal-network";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-network";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/bare-metal-network";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/bare-metal-network";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-network";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/bare-metal-network/{id}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/bare-metal-network/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-network/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/bare-metal-network-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/bare-metal-network-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-network-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/bare-metal-network";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/bare-metal-network";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/bare-metal-network";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/bare-metal-network/{id}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/bare-metal-network/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/bare-metal-network/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/bare-metal-network";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/bare-metal-network";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-network";

	public static final String Icon = "<i class=\"fa-duotone fa-regular fa-network-wired\"></i>";

	////////
	// id //
	////////

	public static final String idESI1_enUS = "id";
	public static final String idESI_enUS = idESI1_enUS;

	/**	 The entity id
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String id;

	/**	<br> The entity id
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = BareMetalNetwork.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchId(siteRequest_, BareMetalNetwork.staticSetId(siteRequest_, o)).toString();
	}

	public String sqlId() {
		return id;
	}

	//////////
	// name //
	//////////

	public static final String nameESI1_enUS = "name";
	public static final String nameESI_enUS = nameESI1_enUS;

	/**	 The entity name
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String name;

	/**	<br> The entity name
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = BareMetalNetwork.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchName(siteRequest_, BareMetalNetwork.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
	}

	/////////////////
	// description //
	/////////////////

	public static final String descriptionESI1_enUS = "description";
	public static final String descriptionESI_enUS = descriptionESI1_enUS;

	/**	 The entity description
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String description;

	/**	<br> The entity description
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = BareMetalNetwork.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchDescription(siteRequest_, BareMetalNetwork.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	///////////////////////////
	// availabilityZoneHints //
	///////////////////////////

	public static final String availabilityZoneHintsESI1_enUS = "availability_zone_hints";
	public static final String availabilityZoneHintsESI_enUS = availabilityZoneHintsESI1_enUS;

	/**	 The entity availabilityZoneHints
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray availabilityZoneHints;

	/**	<br> The entity availabilityZoneHints
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:availabilityZoneHints">Find the entity availabilityZoneHints in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _availabilityZoneHints(Wrap<JsonArray> w);

	public JsonArray getAvailabilityZoneHints() {
		return availabilityZoneHints;
	}

	public void setAvailabilityZoneHints(JsonArray availabilityZoneHints) {
		this.availabilityZoneHints = availabilityZoneHints;
	}
	@JsonIgnore
	public void setAvailabilityZoneHints(String o) {
		this.availabilityZoneHints = BareMetalNetwork.staticSetAvailabilityZoneHints(siteRequest_, o);
	}
	public static JsonArray staticSetAvailabilityZoneHints(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BareMetalNetwork availabilityZoneHintsInit() {
		Wrap<JsonArray> availabilityZoneHintsWrap = new Wrap<JsonArray>().var("availabilityZoneHints");
		if(availabilityZoneHints == null) {
			_availabilityZoneHints(availabilityZoneHintsWrap);
			Optional.ofNullable(availabilityZoneHintsWrap.getO()).ifPresent(o -> {
				setAvailabilityZoneHints(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchAvailabilityZoneHints(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrAvailabilityZoneHints(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAvailabilityZoneHints(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchAvailabilityZoneHints(siteRequest_, BareMetalNetwork.staticSetAvailabilityZoneHints(siteRequest_, o)).toString();
	}

	public JsonArray sqlAvailabilityZoneHints() {
		return availabilityZoneHints;
	}

	///////////////////////
	// availabilityZones //
	///////////////////////

	public static final String availabilityZonesESI1_enUS = "availability_zones";
	public static final String availabilityZonesESI_enUS = availabilityZonesESI1_enUS;

	/**	 The entity availabilityZones
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected List<String> availabilityZones = new ArrayList<String>();

	/**	<br> The entity availabilityZones
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:availabilityZones">Find the entity availabilityZones in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _availabilityZones(List<String> l);

	public List<String> getAvailabilityZones() {
		return availabilityZones;
	}

	public void setAvailabilityZones(List<String> availabilityZones) {
		this.availabilityZones = availabilityZones;
	}
	@JsonIgnore
	public void setAvailabilityZones(String o) {
		String l = BareMetalNetwork.staticSetAvailabilityZones(siteRequest_, o);
		if(l != null)
			addAvailabilityZones(l);
	}
	public static String staticSetAvailabilityZones(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BareMetalNetwork addAvailabilityZones(String...objects) {
		for(String o : objects) {
			addAvailabilityZones(o);
		}
		return (BareMetalNetwork)this;
	}
	public BareMetalNetwork addAvailabilityZones(String o) {
		if(o != null)
			this.availabilityZones.add(o);
		return (BareMetalNetwork)this;
	}
	@JsonIgnore
	public void setAvailabilityZones(JsonArray objects) {
		availabilityZones.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addAvailabilityZones(o);
		}
	}
	protected BareMetalNetwork availabilityZonesInit() {
		_availabilityZones(availabilityZones);
		return (BareMetalNetwork)this;
	}

	public static String staticSearchAvailabilityZones(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAvailabilityZones(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAvailabilityZones(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchAvailabilityZones(siteRequest_, BareMetalNetwork.staticSetAvailabilityZones(siteRequest_, o)).toString();
	}

	public String[] sqlAvailabilityZones() {
		return availabilityZones.stream().map(v -> (String)v).toArray(String[]::new);
	}

	///////////////
	// createdAt //
	///////////////

	public static final String createdAtESI1_enUS = "created_at";
	public static final String createdAtESI_enUS = createdAtESI1_enUS;

	/**	 The entity createdAt
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = ComputateZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ComputateZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime createdAt;

	/**	<br> The entity createdAt
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:createdAt">Find the entity createdAt in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _createdAt(Wrap<ZonedDateTime> w);

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = Optional.ofNullable(createdAt).map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);
	}
	@JsonIgnore
	public void setCreatedAt(Instant o) {
		this.createdAt = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setCreatedAt(String o) {
		this.createdAt = BareMetalNetwork.staticSetCreatedAt(siteRequest_, o);
	}
	@JsonIgnore
	public void setCreatedAt(Date o) {
		this.createdAt = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	public static ZonedDateTime staticSetCreatedAt(SiteRequest siteRequest_, String o) {
		if(StringUtils.endsWith(o, "]"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER);
		else if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(Optional.ofNullable(siteRequest_).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))).truncatedTo(ChronoUnit.MILLIS);
		else if(StringUtils.contains(o, "T"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	protected BareMetalNetwork createdAtInit() {
		Wrap<ZonedDateTime> createdAtWrap = new Wrap<ZonedDateTime>().var("createdAt");
		if(createdAt == null) {
			_createdAt(createdAtWrap);
			Optional.ofNullable(createdAtWrap.getO()).ifPresent(o -> {
				setCreatedAt(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchCreatedAt(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrCreatedAt(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchCreatedAt(siteRequest_, BareMetalNetwork.staticSetCreatedAt(siteRequest_, o));
	}

	public static String staticSearchFqCreatedAt(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchCreatedAt(siteRequest_, BareMetalNetwork.staticSetCreatedAt(siteRequest_, o)).toString();
	}

	public OffsetDateTime sqlCreatedAt() {
		return createdAt == null ? null : createdAt.toOffsetDateTime();
	}

	///////////////
	// dnsDomain //
	///////////////

	public static final String dnsDomainESI1_enUS = "dns_domain";
	public static final String dnsDomainESI_enUS = dnsDomainESI1_enUS;

	/**	 The entity dnsDomain
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String dnsDomain;

	/**	<br> The entity dnsDomain
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:dnsDomain">Find the entity dnsDomain in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dnsDomain(Wrap<String> w);

	public String getDnsDomain() {
		return dnsDomain;
	}
	public void setDnsDomain(String o) {
		this.dnsDomain = BareMetalNetwork.staticSetDnsDomain(siteRequest_, o);
	}
	public static String staticSetDnsDomain(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork dnsDomainInit() {
		Wrap<String> dnsDomainWrap = new Wrap<String>().var("dnsDomain");
		if(dnsDomain == null) {
			_dnsDomain(dnsDomainWrap);
			Optional.ofNullable(dnsDomainWrap.getO()).ifPresent(o -> {
				setDnsDomain(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchDnsDomain(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDnsDomain(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDnsDomain(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchDnsDomain(siteRequest_, BareMetalNetwork.staticSetDnsDomain(siteRequest_, o)).toString();
	}

	public String sqlDnsDomain() {
		return dnsDomain;
	}

	/////////
	// mtu //
	/////////

	public static final String mtuESI1_enUS = "mtu";
	public static final String mtuESI_enUS = mtuESI1_enUS;

	/**	 The entity mtu
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer mtu;

	/**	<br> The entity mtu
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:mtu">Find the entity mtu in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _mtu(Wrap<Integer> w);

	public Integer getMtu() {
		return mtu;
	}

	public void setMtu(Integer mtu) {
		this.mtu = mtu;
	}
	@JsonIgnore
	public void setMtu(String o) {
		this.mtu = BareMetalNetwork.staticSetMtu(siteRequest_, o);
	}
	public static Integer staticSetMtu(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalNetwork mtuInit() {
		Wrap<Integer> mtuWrap = new Wrap<Integer>().var("mtu");
		if(mtu == null) {
			_mtu(mtuWrap);
			Optional.ofNullable(mtuWrap.getO()).ifPresent(o -> {
				setMtu(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Integer staticSearchMtu(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrMtu(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqMtu(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchMtu(siteRequest_, BareMetalNetwork.staticSetMtu(siteRequest_, o)).toString();
	}

	public Integer sqlMtu() {
		return mtu;
	}

	///////////////
	// projectId //
	///////////////

	public static final String projectIdESI1_enUS = "project_id";
	public static final String projectIdESI_enUS = projectIdESI1_enUS;

	/**	 The entity projectId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String projectId;

	/**	<br> The entity projectId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:projectId">Find the entity projectId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _projectId(Wrap<String> w);

	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String o) {
		this.projectId = BareMetalNetwork.staticSetProjectId(siteRequest_, o);
	}
	public static String staticSetProjectId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork projectIdInit() {
		Wrap<String> projectIdWrap = new Wrap<String>().var("projectId");
		if(projectId == null) {
			_projectId(projectIdWrap);
			Optional.ofNullable(projectIdWrap.getO()).ifPresent(o -> {
				setProjectId(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchProjectId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProjectId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProjectId(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchProjectId(siteRequest_, BareMetalNetwork.staticSetProjectId(siteRequest_, o)).toString();
	}

	public String sqlProjectId() {
		return projectId;
	}

	/////////////////////////
	// providerNetworkType //
	/////////////////////////

	public static final String providerNetworkTypeESI1_enUS = "provider_network_type";
	public static final String providerNetworkTypeESI_enUS = providerNetworkTypeESI1_enUS;

	/**	 The entity providerNetworkType
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String providerNetworkType;

	/**	<br> The entity providerNetworkType
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:providerNetworkType">Find the entity providerNetworkType in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _providerNetworkType(Wrap<String> w);

	public String getProviderNetworkType() {
		return providerNetworkType;
	}
	public void setProviderNetworkType(String o) {
		this.providerNetworkType = BareMetalNetwork.staticSetProviderNetworkType(siteRequest_, o);
	}
	public static String staticSetProviderNetworkType(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork providerNetworkTypeInit() {
		Wrap<String> providerNetworkTypeWrap = new Wrap<String>().var("providerNetworkType");
		if(providerNetworkType == null) {
			_providerNetworkType(providerNetworkTypeWrap);
			Optional.ofNullable(providerNetworkTypeWrap.getO()).ifPresent(o -> {
				setProviderNetworkType(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchProviderNetworkType(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProviderNetworkType(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProviderNetworkType(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchProviderNetworkType(siteRequest_, BareMetalNetwork.staticSetProviderNetworkType(siteRequest_, o)).toString();
	}

	public String sqlProviderNetworkType() {
		return providerNetworkType;
	}

	/////////////////////////////
	// providerPhysicalNetwork //
	/////////////////////////////

	public static final String providerPhysicalNetworkESI1_enUS = "provider_physical_network";
	public static final String providerPhysicalNetworkESI_enUS = providerPhysicalNetworkESI1_enUS;

	/**	 The entity providerPhysicalNetwork
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String providerPhysicalNetwork;

	/**	<br> The entity providerPhysicalNetwork
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:providerPhysicalNetwork">Find the entity providerPhysicalNetwork in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _providerPhysicalNetwork(Wrap<String> w);

	public String getProviderPhysicalNetwork() {
		return providerPhysicalNetwork;
	}
	public void setProviderPhysicalNetwork(String o) {
		this.providerPhysicalNetwork = BareMetalNetwork.staticSetProviderPhysicalNetwork(siteRequest_, o);
	}
	public static String staticSetProviderPhysicalNetwork(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork providerPhysicalNetworkInit() {
		Wrap<String> providerPhysicalNetworkWrap = new Wrap<String>().var("providerPhysicalNetwork");
		if(providerPhysicalNetwork == null) {
			_providerPhysicalNetwork(providerPhysicalNetworkWrap);
			Optional.ofNullable(providerPhysicalNetworkWrap.getO()).ifPresent(o -> {
				setProviderPhysicalNetwork(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchProviderPhysicalNetwork(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProviderPhysicalNetwork(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProviderPhysicalNetwork(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchProviderPhysicalNetwork(siteRequest_, BareMetalNetwork.staticSetProviderPhysicalNetwork(siteRequest_, o)).toString();
	}

	public String sqlProviderPhysicalNetwork() {
		return providerPhysicalNetwork;
	}

	////////////////////////////
	// providerSegmentationId //
	////////////////////////////

	public static final String providerSegmentationIdESI1_enUS = "provider_segmentation_id";
	public static final String providerSegmentationIdESI_enUS = providerSegmentationIdESI1_enUS;

	/**	 The entity providerSegmentationId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String providerSegmentationId;

	/**	<br> The entity providerSegmentationId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:providerSegmentationId">Find the entity providerSegmentationId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _providerSegmentationId(Wrap<String> w);

	public String getProviderSegmentationId() {
		return providerSegmentationId;
	}
	public void setProviderSegmentationId(String o) {
		this.providerSegmentationId = BareMetalNetwork.staticSetProviderSegmentationId(siteRequest_, o);
	}
	public static String staticSetProviderSegmentationId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork providerSegmentationIdInit() {
		Wrap<String> providerSegmentationIdWrap = new Wrap<String>().var("providerSegmentationId");
		if(providerSegmentationId == null) {
			_providerSegmentationId(providerSegmentationIdWrap);
			Optional.ofNullable(providerSegmentationIdWrap.getO()).ifPresent(o -> {
				setProviderSegmentationId(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchProviderSegmentationId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProviderSegmentationId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProviderSegmentationId(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchProviderSegmentationId(siteRequest_, BareMetalNetwork.staticSetProviderSegmentationId(siteRequest_, o)).toString();
	}

	public String sqlProviderSegmentationId() {
		return providerSegmentationId;
	}

	/////////////////
	// qosPolicyId //
	/////////////////

	public static final String qosPolicyIdESI1_enUS = "qos_policy_id";
	public static final String qosPolicyIdESI_enUS = qosPolicyIdESI1_enUS;

	/**	 The entity qosPolicyId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String qosPolicyId;

	/**	<br> The entity qosPolicyId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:qosPolicyId">Find the entity qosPolicyId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _qosPolicyId(Wrap<String> w);

	public String getQosPolicyId() {
		return qosPolicyId;
	}
	public void setQosPolicyId(String o) {
		this.qosPolicyId = BareMetalNetwork.staticSetQosPolicyId(siteRequest_, o);
	}
	public static String staticSetQosPolicyId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork qosPolicyIdInit() {
		Wrap<String> qosPolicyIdWrap = new Wrap<String>().var("qosPolicyId");
		if(qosPolicyId == null) {
			_qosPolicyId(qosPolicyIdWrap);
			Optional.ofNullable(qosPolicyIdWrap.getO()).ifPresent(o -> {
				setQosPolicyId(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchQosPolicyId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrQosPolicyId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqQosPolicyId(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchQosPolicyId(siteRequest_, BareMetalNetwork.staticSetQosPolicyId(siteRequest_, o)).toString();
	}

	public String sqlQosPolicyId() {
		return qosPolicyId;
	}

	////////////////////
	// revisionNumber //
	////////////////////

	public static final String revisionNumberESI1_enUS = "revision_number";
	public static final String revisionNumberESI_enUS = revisionNumberESI1_enUS;

	/**	 The entity revisionNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer revisionNumber;

	/**	<br> The entity revisionNumber
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:revisionNumber">Find the entity revisionNumber in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _revisionNumber(Wrap<Integer> w);

	public Integer getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(Integer revisionNumber) {
		this.revisionNumber = revisionNumber;
	}
	@JsonIgnore
	public void setRevisionNumber(String o) {
		this.revisionNumber = BareMetalNetwork.staticSetRevisionNumber(siteRequest_, o);
	}
	public static Integer staticSetRevisionNumber(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalNetwork revisionNumberInit() {
		Wrap<Integer> revisionNumberWrap = new Wrap<Integer>().var("revisionNumber");
		if(revisionNumber == null) {
			_revisionNumber(revisionNumberWrap);
			Optional.ofNullable(revisionNumberWrap.getO()).ifPresent(o -> {
				setRevisionNumber(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Integer staticSearchRevisionNumber(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrRevisionNumber(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRevisionNumber(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchRevisionNumber(siteRequest_, BareMetalNetwork.staticSetRevisionNumber(siteRequest_, o)).toString();
	}

	public Integer sqlRevisionNumber() {
		return revisionNumber;
	}

	////////////
	// status //
	////////////

	public static final String statusESI1_enUS = "status";
	public static final String statusESI_enUS = statusESI1_enUS;

	/**	 The entity status
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String status;

	/**	<br> The entity status
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:status">Find the entity status in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _status(Wrap<String> w);

	public String getStatus() {
		return status;
	}
	public void setStatus(String o) {
		this.status = BareMetalNetwork.staticSetStatus(siteRequest_, o);
	}
	public static String staticSetStatus(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork statusInit() {
		Wrap<String> statusWrap = new Wrap<String>().var("status");
		if(status == null) {
			_status(statusWrap);
			Optional.ofNullable(statusWrap.getO()).ifPresent(o -> {
				setStatus(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchStatus(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStatus(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStatus(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchStatus(siteRequest_, BareMetalNetwork.staticSetStatus(siteRequest_, o)).toString();
	}

	public String sqlStatus() {
		return status;
	}

	///////////////
	// subnetIds //
	///////////////

	public static final String subnetIdsESI1_enUS = "subnet_ids";
	public static final String subnetIdsESI_enUS = subnetIdsESI1_enUS;

	/**	 The entity subnetIds
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected List<String> subnetIds = new ArrayList<String>();

	/**	<br> The entity subnetIds
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:subnetIds">Find the entity subnetIds in Solr</a>
	 * <br>
	 * @param w is the entity already constructed. 
	 **/
	protected abstract void _subnetIds(List<String> w);

	public List<String> getSubnetIds() {
		return subnetIds;
	}

	public void setSubnetIds(List<String> subnetIds) {
		this.subnetIds = subnetIds;
	}
	@JsonIgnore
	public void setSubnetIds(String o) {
		String l = BareMetalNetwork.staticSetSubnetIds(siteRequest_, o);
		if(l != null)
			addSubnetIds(l);
	}
	public static String staticSetSubnetIds(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BareMetalNetwork addSubnetIds(String...objects) {
		for(String o : objects) {
			addSubnetIds(o);
		}
		return (BareMetalNetwork)this;
	}
	public BareMetalNetwork addSubnetIds(String o) {
		if(o != null)
			this.subnetIds.add(o);
		return (BareMetalNetwork)this;
	}
	@JsonIgnore
	public void setSubnetIds(JsonArray objects) {
		subnetIds.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addSubnetIds(o);
		}
	}
	protected BareMetalNetwork subnetIdsInit() {
		_subnetIds(subnetIds);
		return (BareMetalNetwork)this;
	}

	public static String staticSearchSubnetIds(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSubnetIds(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSubnetIds(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchSubnetIds(siteRequest_, BareMetalNetwork.staticSetSubnetIds(siteRequest_, o)).toString();
	}

	public String[] sqlSubnetIds() {
		return subnetIds.stream().map(v -> (String)v).toArray(String[]::new);
	}

	//////////
	// tags //
	//////////

	public static final String tagsESI1_enUS = "tags";
	public static final String tagsESI_enUS = tagsESI1_enUS;

	/**	 The entity tags
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected List<String> tags = new ArrayList<String>();

	/**	<br> The entity tags
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:tags">Find the entity tags in Solr</a>
	 * <br>
	 * @param w is the entity already constructed. 
	 **/
	protected abstract void _tags(List<String> w);

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	@JsonIgnore
	public void setTags(String o) {
		String l = BareMetalNetwork.staticSetTags(siteRequest_, o);
		if(l != null)
			addTags(l);
	}
	public static String staticSetTags(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BareMetalNetwork addTags(String...objects) {
		for(String o : objects) {
			addTags(o);
		}
		return (BareMetalNetwork)this;
	}
	public BareMetalNetwork addTags(String o) {
		if(o != null)
			this.tags.add(o);
		return (BareMetalNetwork)this;
	}
	@JsonIgnore
	public void setTags(JsonArray objects) {
		tags.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addTags(o);
		}
	}
	protected BareMetalNetwork tagsInit() {
		_tags(tags);
		return (BareMetalNetwork)this;
	}

	public static String staticSearchTags(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTags(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTags(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchTags(siteRequest_, BareMetalNetwork.staticSetTags(siteRequest_, o)).toString();
	}

	public String[] sqlTags() {
		return tags.stream().map(v -> (String)v).toArray(String[]::new);
	}

	//////////////
	// tenantId //
	//////////////

	public static final String tenantIdESI1_enUS = "tenant_id";
	public static final String tenantIdESI_enUS = tenantIdESI1_enUS;

	/**	 The entity tenantId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String tenantId;

	/**	<br> The entity tenantId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _tenantId(Wrap<String> w);

	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String o) {
		this.tenantId = BareMetalNetwork.staticSetTenantId(siteRequest_, o);
	}
	public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork tenantIdInit() {
		Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
		if(tenantId == null) {
			_tenantId(tenantIdWrap);
			Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
				setTenantId(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchTenantId(siteRequest_, BareMetalNetwork.staticSetTenantId(siteRequest_, o)).toString();
	}

	public String sqlTenantId() {
		return tenantId;
	}

	///////////////
	// updatedAt //
	///////////////

	public static final String updatedAtESI1_enUS = "updated_at";
	public static final String updatedAtESI_enUS = updatedAtESI1_enUS;

	/**	 The entity updatedAt
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = ComputateZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ComputateZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime updatedAt;

	/**	<br> The entity updatedAt
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:updatedAt">Find the entity updatedAt in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _updatedAt(Wrap<ZonedDateTime> w);

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = Optional.ofNullable(updatedAt).map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);
	}
	@JsonIgnore
	public void setUpdatedAt(Instant o) {
		this.updatedAt = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setUpdatedAt(String o) {
		this.updatedAt = BareMetalNetwork.staticSetUpdatedAt(siteRequest_, o);
	}
	@JsonIgnore
	public void setUpdatedAt(Date o) {
		this.updatedAt = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	public static ZonedDateTime staticSetUpdatedAt(SiteRequest siteRequest_, String o) {
		if(StringUtils.endsWith(o, "]"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER);
		else if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(Optional.ofNullable(siteRequest_).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))).truncatedTo(ChronoUnit.MILLIS);
		else if(StringUtils.contains(o, "T"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	protected BareMetalNetwork updatedAtInit() {
		Wrap<ZonedDateTime> updatedAtWrap = new Wrap<ZonedDateTime>().var("updatedAt");
		if(updatedAt == null) {
			_updatedAt(updatedAtWrap);
			Optional.ofNullable(updatedAtWrap.getO()).ifPresent(o -> {
				setUpdatedAt(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchUpdatedAt(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrUpdatedAt(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchUpdatedAt(siteRequest_, BareMetalNetwork.staticSetUpdatedAt(siteRequest_, o));
	}

	public static String staticSearchFqUpdatedAt(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchUpdatedAt(siteRequest_, BareMetalNetwork.staticSetUpdatedAt(siteRequest_, o)).toString();
	}

	public OffsetDateTime sqlUpdatedAt() {
		return updatedAt == null ? null : updatedAt.toOffsetDateTime();
	}

	////////////////////
	// isAdminStateUp //
	////////////////////

	public static final String isAdminStateUpESI1_enUS = "is_admin_state_up";
	public static final String isAdminStateUpESI_enUS = isAdminStateUpESI1_enUS;

	/**	 The entity isAdminStateUp
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean isAdminStateUp;

	/**	<br> The entity isAdminStateUp
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:isAdminStateUp">Find the entity isAdminStateUp in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _isAdminStateUp(Wrap<Boolean> w);

	public Boolean getIsAdminStateUp() {
		return isAdminStateUp;
	}

	public void setIsAdminStateUp(Boolean isAdminStateUp) {
		this.isAdminStateUp = isAdminStateUp;
	}
	@JsonIgnore
	public void setIsAdminStateUp(String o) {
		this.isAdminStateUp = BareMetalNetwork.staticSetIsAdminStateUp(siteRequest_, o);
	}
	public static Boolean staticSetIsAdminStateUp(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalNetwork isAdminStateUpInit() {
		Wrap<Boolean> isAdminStateUpWrap = new Wrap<Boolean>().var("isAdminStateUp");
		if(isAdminStateUp == null) {
			_isAdminStateUp(isAdminStateUpWrap);
			Optional.ofNullable(isAdminStateUpWrap.getO()).ifPresent(o -> {
				setIsAdminStateUp(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Boolean staticSearchIsAdminStateUp(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrIsAdminStateUp(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIsAdminStateUp(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchIsAdminStateUp(siteRequest_, BareMetalNetwork.staticSetIsAdminStateUp(siteRequest_, o)).toString();
	}

	public Boolean sqlIsAdminStateUp() {
		return isAdminStateUp;
	}

	///////////////
	// isDefault //
	///////////////

	public static final String isDefaultESI1_enUS = "is_default";
	public static final String isDefaultESI_enUS = isDefaultESI1_enUS;

	/**	 The entity isDefault
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean isDefault;

	/**	<br> The entity isDefault
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:isDefault">Find the entity isDefault in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _isDefault(Wrap<Boolean> w);

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	@JsonIgnore
	public void setIsDefault(String o) {
		this.isDefault = BareMetalNetwork.staticSetIsDefault(siteRequest_, o);
	}
	public static Boolean staticSetIsDefault(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalNetwork isDefaultInit() {
		Wrap<Boolean> isDefaultWrap = new Wrap<Boolean>().var("isDefault");
		if(isDefault == null) {
			_isDefault(isDefaultWrap);
			Optional.ofNullable(isDefaultWrap.getO()).ifPresent(o -> {
				setIsDefault(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Boolean staticSearchIsDefault(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrIsDefault(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIsDefault(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchIsDefault(siteRequest_, BareMetalNetwork.staticSetIsDefault(siteRequest_, o)).toString();
	}

	public Boolean sqlIsDefault() {
		return isDefault;
	}

	///////////////////////////
	// isPortSecurityEnabled //
	///////////////////////////

	public static final String isPortSecurityEnabledESI1_enUS = "is_port_security_enabled";
	public static final String isPortSecurityEnabledESI_enUS = isPortSecurityEnabledESI1_enUS;

	/**	 The entity isPortSecurityEnabled
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean isPortSecurityEnabled;

	/**	<br> The entity isPortSecurityEnabled
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:isPortSecurityEnabled">Find the entity isPortSecurityEnabled in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _isPortSecurityEnabled(Wrap<Boolean> w);

	public Boolean getIsPortSecurityEnabled() {
		return isPortSecurityEnabled;
	}

	public void setIsPortSecurityEnabled(Boolean isPortSecurityEnabled) {
		this.isPortSecurityEnabled = isPortSecurityEnabled;
	}
	@JsonIgnore
	public void setIsPortSecurityEnabled(String o) {
		this.isPortSecurityEnabled = BareMetalNetwork.staticSetIsPortSecurityEnabled(siteRequest_, o);
	}
	public static Boolean staticSetIsPortSecurityEnabled(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalNetwork isPortSecurityEnabledInit() {
		Wrap<Boolean> isPortSecurityEnabledWrap = new Wrap<Boolean>().var("isPortSecurityEnabled");
		if(isPortSecurityEnabled == null) {
			_isPortSecurityEnabled(isPortSecurityEnabledWrap);
			Optional.ofNullable(isPortSecurityEnabledWrap.getO()).ifPresent(o -> {
				setIsPortSecurityEnabled(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Boolean staticSearchIsPortSecurityEnabled(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrIsPortSecurityEnabled(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIsPortSecurityEnabled(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchIsPortSecurityEnabled(siteRequest_, BareMetalNetwork.staticSetIsPortSecurityEnabled(siteRequest_, o)).toString();
	}

	public Boolean sqlIsPortSecurityEnabled() {
		return isPortSecurityEnabled;
	}

	//////////////////////
	// isRouterExternal //
	//////////////////////

	public static final String isRouterExternalESI1_enUS = "is_router_external";
	public static final String isRouterExternalESI_enUS = isRouterExternalESI1_enUS;

	/**	 The entity isRouterExternal
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean isRouterExternal;

	/**	<br> The entity isRouterExternal
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:isRouterExternal">Find the entity isRouterExternal in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _isRouterExternal(Wrap<Boolean> w);

	public Boolean getIsRouterExternal() {
		return isRouterExternal;
	}

	public void setIsRouterExternal(Boolean isRouterExternal) {
		this.isRouterExternal = isRouterExternal;
	}
	@JsonIgnore
	public void setIsRouterExternal(String o) {
		this.isRouterExternal = BareMetalNetwork.staticSetIsRouterExternal(siteRequest_, o);
	}
	public static Boolean staticSetIsRouterExternal(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalNetwork isRouterExternalInit() {
		Wrap<Boolean> isRouterExternalWrap = new Wrap<Boolean>().var("isRouterExternal");
		if(isRouterExternal == null) {
			_isRouterExternal(isRouterExternalWrap);
			Optional.ofNullable(isRouterExternalWrap.getO()).ifPresent(o -> {
				setIsRouterExternal(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Boolean staticSearchIsRouterExternal(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrIsRouterExternal(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIsRouterExternal(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchIsRouterExternal(siteRequest_, BareMetalNetwork.staticSetIsRouterExternal(siteRequest_, o)).toString();
	}

	public Boolean sqlIsRouterExternal() {
		return isRouterExternal;
	}

	//////////////
	// isShared //
	//////////////

	public static final String isSharedESI1_enUS = "is_shared";
	public static final String isSharedESI_enUS = isSharedESI1_enUS;

	/**	 The entity isShared
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean isShared;

	/**	<br> The entity isShared
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:isShared">Find the entity isShared in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _isShared(Wrap<Boolean> w);

	public Boolean getIsShared() {
		return isShared;
	}

	public void setIsShared(Boolean isShared) {
		this.isShared = isShared;
	}
	@JsonIgnore
	public void setIsShared(String o) {
		this.isShared = BareMetalNetwork.staticSetIsShared(siteRequest_, o);
	}
	public static Boolean staticSetIsShared(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalNetwork isSharedInit() {
		Wrap<Boolean> isSharedWrap = new Wrap<Boolean>().var("isShared");
		if(isShared == null) {
			_isShared(isSharedWrap);
			Optional.ofNullable(isSharedWrap.getO()).ifPresent(o -> {
				setIsShared(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Boolean staticSearchIsShared(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrIsShared(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIsShared(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchIsShared(siteRequest_, BareMetalNetwork.staticSetIsShared(siteRequest_, o)).toString();
	}

	public Boolean sqlIsShared() {
		return isShared;
	}

	//////////////////
	// isVlanQueing //
	//////////////////

	public static final String isVlanQueingESI1_enUS = "is_vlan_qing";
	public static final String isVlanQueingESI_enUS = isVlanQueingESI1_enUS;

	/**	 The entity isVlanQueing
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean isVlanQueing;

	/**	<br> The entity isVlanQueing
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:isVlanQueing">Find the entity isVlanQueing in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _isVlanQueing(Wrap<Boolean> w);

	public Boolean getIsVlanQueing() {
		return isVlanQueing;
	}

	public void setIsVlanQueing(Boolean isVlanQueing) {
		this.isVlanQueing = isVlanQueing;
	}
	@JsonIgnore
	public void setIsVlanQueing(String o) {
		this.isVlanQueing = BareMetalNetwork.staticSetIsVlanQueing(siteRequest_, o);
	}
	public static Boolean staticSetIsVlanQueing(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalNetwork isVlanQueingInit() {
		Wrap<Boolean> isVlanQueingWrap = new Wrap<Boolean>().var("isVlanQueing");
		if(isVlanQueing == null) {
			_isVlanQueing(isVlanQueingWrap);
			Optional.ofNullable(isVlanQueingWrap.getO()).ifPresent(o -> {
				setIsVlanQueing(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Boolean staticSearchIsVlanQueing(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrIsVlanQueing(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIsVlanQueing(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchIsVlanQueing(siteRequest_, BareMetalNetwork.staticSetIsVlanQueing(siteRequest_, o)).toString();
	}

	public Boolean sqlIsVlanQueing() {
		return isVlanQueing;
	}

	///////////////////////
	// isVlanTransparent //
	///////////////////////

	public static final String isVlanTransparentESI1_enUS = "is_vlan_transparent";
	public static final String isVlanTransparentESI_enUS = isVlanTransparentESI1_enUS;

	/**	 The entity isVlanTransparent
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean isVlanTransparent;

	/**	<br> The entity isVlanTransparent
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:isVlanTransparent">Find the entity isVlanTransparent in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _isVlanTransparent(Wrap<Boolean> w);

	public Boolean getIsVlanTransparent() {
		return isVlanTransparent;
	}

	public void setIsVlanTransparent(Boolean isVlanTransparent) {
		this.isVlanTransparent = isVlanTransparent;
	}
	@JsonIgnore
	public void setIsVlanTransparent(String o) {
		this.isVlanTransparent = BareMetalNetwork.staticSetIsVlanTransparent(siteRequest_, o);
	}
	public static Boolean staticSetIsVlanTransparent(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalNetwork isVlanTransparentInit() {
		Wrap<Boolean> isVlanTransparentWrap = new Wrap<Boolean>().var("isVlanTransparent");
		if(isVlanTransparent == null) {
			_isVlanTransparent(isVlanTransparentWrap);
			Optional.ofNullable(isVlanTransparentWrap.getO()).ifPresent(o -> {
				setIsVlanTransparent(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Boolean staticSearchIsVlanTransparent(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrIsVlanTransparent(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIsVlanTransparent(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchIsVlanTransparent(siteRequest_, BareMetalNetwork.staticSetIsVlanTransparent(siteRequest_, o)).toString();
	}

	public Boolean sqlIsVlanTransparent() {
		return isVlanTransparent;
	}

	/////////////////
	// l2Adjacency //
	/////////////////

	public static final String l2AdjacencyESI1_enUS = "l2_adjacency";
	public static final String l2AdjacencyESI_enUS = l2AdjacencyESI1_enUS;

	/**	 The entity l2Adjacency
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean l2Adjacency;

	/**	<br> The entity l2Adjacency
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:l2Adjacency">Find the entity l2Adjacency in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _l2Adjacency(Wrap<Boolean> w);

	public Boolean getL2Adjacency() {
		return l2Adjacency;
	}

	public void setL2Adjacency(Boolean l2Adjacency) {
		this.l2Adjacency = l2Adjacency;
	}
	@JsonIgnore
	public void setL2Adjacency(String o) {
		this.l2Adjacency = BareMetalNetwork.staticSetL2Adjacency(siteRequest_, o);
	}
	public static Boolean staticSetL2Adjacency(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalNetwork l2AdjacencyInit() {
		Wrap<Boolean> l2AdjacencyWrap = new Wrap<Boolean>().var("l2Adjacency");
		if(l2Adjacency == null) {
			_l2Adjacency(l2AdjacencyWrap);
			Optional.ofNullable(l2AdjacencyWrap.getO()).ifPresent(o -> {
				setL2Adjacency(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static Boolean staticSearchL2Adjacency(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrL2Adjacency(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqL2Adjacency(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchL2Adjacency(siteRequest_, BareMetalNetwork.staticSetL2Adjacency(siteRequest_, o)).toString();
	}

	public Boolean sqlL2Adjacency() {
		return l2Adjacency;
	}

	///////////////////
	// locationCloud //
	///////////////////

	public static final String locationCloudESIlocation1_enUS = "location";
	public static final String locationCloudESIlocation_enUS = locationCloudESIlocation1_enUS;
	public static final String locationCloudESIcloud1_enUS = "cloud";
	public static final String locationCloudESIcloud_enUS = locationCloudESIcloud1_enUS;

	/**	 The entity locationCloud
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String locationCloud;

	/**	<br> The entity locationCloud
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:locationCloud">Find the entity locationCloud in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _locationCloud(Wrap<String> w);

	public String getLocationCloud() {
		return locationCloud;
	}
	public void setLocationCloud(String o) {
		this.locationCloud = BareMetalNetwork.staticSetLocationCloud(siteRequest_, o);
	}
	public static String staticSetLocationCloud(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork locationCloudInit() {
		Wrap<String> locationCloudWrap = new Wrap<String>().var("locationCloud");
		if(locationCloud == null) {
			_locationCloud(locationCloudWrap);
			Optional.ofNullable(locationCloudWrap.getO()).ifPresent(o -> {
				setLocationCloud(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchLocationCloud(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationCloud(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationCloud(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchLocationCloud(siteRequest_, BareMetalNetwork.staticSetLocationCloud(siteRequest_, o)).toString();
	}

	public String sqlLocationCloud() {
		return locationCloud;
	}

	/////////////////////////////
	// locationProjectDomainId //
	/////////////////////////////

	public static final String locationProjectDomainIdESIlocation1_enUS = "location";
	public static final String locationProjectDomainIdESIlocation_enUS = locationProjectDomainIdESIlocation1_enUS;
	public static final String locationProjectDomainIdESIproject1_enUS = "project";
	public static final String locationProjectDomainIdESIproject_enUS = locationProjectDomainIdESIproject1_enUS;
	public static final String locationProjectDomainIdESIdomain_id1_enUS = "domain_id";
	public static final String locationProjectDomainIdESIdomain_id_enUS = locationProjectDomainIdESIdomain_id1_enUS;

	/**	 The entity locationProjectDomainId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String locationProjectDomainId;

	/**	<br> The entity locationProjectDomainId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:locationProjectDomainId">Find the entity locationProjectDomainId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _locationProjectDomainId(Wrap<String> w);

	public String getLocationProjectDomainId() {
		return locationProjectDomainId;
	}
	public void setLocationProjectDomainId(String o) {
		this.locationProjectDomainId = BareMetalNetwork.staticSetLocationProjectDomainId(siteRequest_, o);
	}
	public static String staticSetLocationProjectDomainId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork locationProjectDomainIdInit() {
		Wrap<String> locationProjectDomainIdWrap = new Wrap<String>().var("locationProjectDomainId");
		if(locationProjectDomainId == null) {
			_locationProjectDomainId(locationProjectDomainIdWrap);
			Optional.ofNullable(locationProjectDomainIdWrap.getO()).ifPresent(o -> {
				setLocationProjectDomainId(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchLocationProjectDomainId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationProjectDomainId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationProjectDomainId(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchLocationProjectDomainId(siteRequest_, BareMetalNetwork.staticSetLocationProjectDomainId(siteRequest_, o)).toString();
	}

	public String sqlLocationProjectDomainId() {
		return locationProjectDomainId;
	}

	///////////////////////////////
	// locationProjectDomainName //
	///////////////////////////////

	public static final String locationProjectDomainNameESIlocation1_enUS = "location";
	public static final String locationProjectDomainNameESIlocation_enUS = locationProjectDomainNameESIlocation1_enUS;
	public static final String locationProjectDomainNameESIproject1_enUS = "project";
	public static final String locationProjectDomainNameESIproject_enUS = locationProjectDomainNameESIproject1_enUS;
	public static final String locationProjectDomainNameESIdomain_name1_enUS = "domain_name";
	public static final String locationProjectDomainNameESIdomain_name_enUS = locationProjectDomainNameESIdomain_name1_enUS;

	/**	 The entity locationProjectDomainName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String locationProjectDomainName;

	/**	<br> The entity locationProjectDomainName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:locationProjectDomainName">Find the entity locationProjectDomainName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _locationProjectDomainName(Wrap<String> w);

	public String getLocationProjectDomainName() {
		return locationProjectDomainName;
	}
	public void setLocationProjectDomainName(String o) {
		this.locationProjectDomainName = BareMetalNetwork.staticSetLocationProjectDomainName(siteRequest_, o);
	}
	public static String staticSetLocationProjectDomainName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork locationProjectDomainNameInit() {
		Wrap<String> locationProjectDomainNameWrap = new Wrap<String>().var("locationProjectDomainName");
		if(locationProjectDomainName == null) {
			_locationProjectDomainName(locationProjectDomainNameWrap);
			Optional.ofNullable(locationProjectDomainNameWrap.getO()).ifPresent(o -> {
				setLocationProjectDomainName(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchLocationProjectDomainName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationProjectDomainName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationProjectDomainName(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchLocationProjectDomainName(siteRequest_, BareMetalNetwork.staticSetLocationProjectDomainName(siteRequest_, o)).toString();
	}

	public String sqlLocationProjectDomainName() {
		return locationProjectDomainName;
	}

	///////////////////////
	// locationProjectId //
	///////////////////////

	public static final String locationProjectIdESIlocation1_enUS = "location";
	public static final String locationProjectIdESIlocation_enUS = locationProjectIdESIlocation1_enUS;
	public static final String locationProjectIdESIproject1_enUS = "project";
	public static final String locationProjectIdESIproject_enUS = locationProjectIdESIproject1_enUS;
	public static final String locationProjectIdESIid1_enUS = "id";
	public static final String locationProjectIdESIid_enUS = locationProjectIdESIid1_enUS;

	/**	 The entity locationProjectId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String locationProjectId;

	/**	<br> The entity locationProjectId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:locationProjectId">Find the entity locationProjectId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _locationProjectId(Wrap<String> w);

	public String getLocationProjectId() {
		return locationProjectId;
	}
	public void setLocationProjectId(String o) {
		this.locationProjectId = BareMetalNetwork.staticSetLocationProjectId(siteRequest_, o);
	}
	public static String staticSetLocationProjectId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork locationProjectIdInit() {
		Wrap<String> locationProjectIdWrap = new Wrap<String>().var("locationProjectId");
		if(locationProjectId == null) {
			_locationProjectId(locationProjectIdWrap);
			Optional.ofNullable(locationProjectIdWrap.getO()).ifPresent(o -> {
				setLocationProjectId(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchLocationProjectId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationProjectId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationProjectId(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchLocationProjectId(siteRequest_, BareMetalNetwork.staticSetLocationProjectId(siteRequest_, o)).toString();
	}

	public String sqlLocationProjectId() {
		return locationProjectId;
	}

	/////////////////////////
	// locationProjectName //
	/////////////////////////

	public static final String locationProjectNameESIlocation1_enUS = "location";
	public static final String locationProjectNameESIlocation_enUS = locationProjectNameESIlocation1_enUS;
	public static final String locationProjectNameESIproject1_enUS = "project";
	public static final String locationProjectNameESIproject_enUS = locationProjectNameESIproject1_enUS;
	public static final String locationProjectNameESIname1_enUS = "name";
	public static final String locationProjectNameESIname_enUS = locationProjectNameESIname1_enUS;

	/**	 The entity locationProjectName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String locationProjectName;

	/**	<br> The entity locationProjectName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:locationProjectName">Find the entity locationProjectName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _locationProjectName(Wrap<String> w);

	public String getLocationProjectName() {
		return locationProjectName;
	}
	public void setLocationProjectName(String o) {
		this.locationProjectName = BareMetalNetwork.staticSetLocationProjectName(siteRequest_, o);
	}
	public static String staticSetLocationProjectName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork locationProjectNameInit() {
		Wrap<String> locationProjectNameWrap = new Wrap<String>().var("locationProjectName");
		if(locationProjectName == null) {
			_locationProjectName(locationProjectNameWrap);
			Optional.ofNullable(locationProjectNameWrap.getO()).ifPresent(o -> {
				setLocationProjectName(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchLocationProjectName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationProjectName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationProjectName(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchLocationProjectName(siteRequest_, BareMetalNetwork.staticSetLocationProjectName(siteRequest_, o)).toString();
	}

	public String sqlLocationProjectName() {
		return locationProjectName;
	}

	////////////////////////
	// locationRegionName //
	////////////////////////

	public static final String locationRegionNameESIlocation1_enUS = "location";
	public static final String locationRegionNameESIlocation_enUS = locationRegionNameESIlocation1_enUS;
	public static final String locationRegionNameESIregion_name1_enUS = "region_name";
	public static final String locationRegionNameESIregion_name_enUS = locationRegionNameESIregion_name1_enUS;

	/**	 The entity locationRegionName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String locationRegionName;

	/**	<br> The entity locationRegionName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:locationRegionName">Find the entity locationRegionName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _locationRegionName(Wrap<String> w);

	public String getLocationRegionName() {
		return locationRegionName;
	}
	public void setLocationRegionName(String o) {
		this.locationRegionName = BareMetalNetwork.staticSetLocationRegionName(siteRequest_, o);
	}
	public static String staticSetLocationRegionName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork locationRegionNameInit() {
		Wrap<String> locationRegionNameWrap = new Wrap<String>().var("locationRegionName");
		if(locationRegionName == null) {
			_locationRegionName(locationRegionNameWrap);
			Optional.ofNullable(locationRegionNameWrap.getO()).ifPresent(o -> {
				setLocationRegionName(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchLocationRegionName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationRegionName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationRegionName(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchLocationRegionName(siteRequest_, BareMetalNetwork.staticSetLocationRegionName(siteRequest_, o)).toString();
	}

	public String sqlLocationRegionName() {
		return locationRegionName;
	}

	//////////////////
	// locationZone //
	//////////////////

	public static final String locationZoneESIlocation1_enUS = "location";
	public static final String locationZoneESIlocation_enUS = locationZoneESIlocation1_enUS;
	public static final String locationZoneESIzone1_enUS = "zone";
	public static final String locationZoneESIzone_enUS = locationZoneESIzone1_enUS;

	/**	 The entity locationZone
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String locationZone;

	/**	<br> The entity locationZone
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork&fq=entiteVar_enUS_indexed_string:locationZone">Find the entity locationZone in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _locationZone(Wrap<String> w);

	public String getLocationZone() {
		return locationZone;
	}
	public void setLocationZone(String o) {
		this.locationZone = BareMetalNetwork.staticSetLocationZone(siteRequest_, o);
	}
	public static String staticSetLocationZone(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNetwork locationZoneInit() {
		Wrap<String> locationZoneWrap = new Wrap<String>().var("locationZone");
		if(locationZone == null) {
			_locationZone(locationZoneWrap);
			Optional.ofNullable(locationZoneWrap.getO()).ifPresent(o -> {
				setLocationZone(o);
			});
		}
		return (BareMetalNetwork)this;
	}

	public static String staticSearchLocationZone(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationZone(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationZone(SiteRequest siteRequest_, String o) {
		return BareMetalNetwork.staticSearchLocationZone(siteRequest_, BareMetalNetwork.staticSetLocationZone(siteRequest_, o)).toString();
	}

	public String sqlLocationZone() {
		return locationZone;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalNetworkGen<DEV>> promiseDeepBareMetalNetwork(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalNetwork();
	}

	public Future<BareMetalNetworkGen<DEV>> promiseDeepBareMetalNetwork() {
		Promise<BareMetalNetworkGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalNetwork(promise2);
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

	public Future<Void> promiseBareMetalNetwork(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				idInit();
				nameInit();
				descriptionInit();
				availabilityZoneHintsInit();
				availabilityZonesInit();
				createdAtInit();
				dnsDomainInit();
				mtuInit();
				projectIdInit();
				providerNetworkTypeInit();
				providerPhysicalNetworkInit();
				providerSegmentationIdInit();
				qosPolicyIdInit();
				revisionNumberInit();
				statusInit();
				subnetIdsInit();
				tagsInit();
				tenantIdInit();
				updatedAtInit();
				isAdminStateUpInit();
				isDefaultInit();
				isPortSecurityEnabledInit();
				isRouterExternalInit();
				isSharedInit();
				isVlanQueingInit();
				isVlanTransparentInit();
				l2AdjacencyInit();
				locationCloudInit();
				locationProjectDomainIdInit();
				locationProjectDomainNameInit();
				locationProjectIdInit();
				locationProjectNameInit();
				locationRegionNameInit();
				locationZoneInit();
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

	@Override public Future<? extends BareMetalNetworkGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalNetwork(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalNetwork(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalNetwork(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalNetwork(v);
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
	public Object obtainBareMetalNetwork(String var) {
		BareMetalNetwork oBareMetalNetwork = (BareMetalNetwork)this;
		switch(var) {
			case "id":
				return oBareMetalNetwork.id;
			case "name":
				return oBareMetalNetwork.name;
			case "description":
				return oBareMetalNetwork.description;
			case "availabilityZoneHints":
				return oBareMetalNetwork.availabilityZoneHints;
			case "availabilityZones":
				return oBareMetalNetwork.availabilityZones;
			case "createdAt":
				return oBareMetalNetwork.createdAt;
			case "dnsDomain":
				return oBareMetalNetwork.dnsDomain;
			case "mtu":
				return oBareMetalNetwork.mtu;
			case "projectId":
				return oBareMetalNetwork.projectId;
			case "providerNetworkType":
				return oBareMetalNetwork.providerNetworkType;
			case "providerPhysicalNetwork":
				return oBareMetalNetwork.providerPhysicalNetwork;
			case "providerSegmentationId":
				return oBareMetalNetwork.providerSegmentationId;
			case "qosPolicyId":
				return oBareMetalNetwork.qosPolicyId;
			case "revisionNumber":
				return oBareMetalNetwork.revisionNumber;
			case "status":
				return oBareMetalNetwork.status;
			case "subnetIds":
				return oBareMetalNetwork.subnetIds;
			case "tags":
				return oBareMetalNetwork.tags;
			case "tenantId":
				return oBareMetalNetwork.tenantId;
			case "updatedAt":
				return oBareMetalNetwork.updatedAt;
			case "isAdminStateUp":
				return oBareMetalNetwork.isAdminStateUp;
			case "isDefault":
				return oBareMetalNetwork.isDefault;
			case "isPortSecurityEnabled":
				return oBareMetalNetwork.isPortSecurityEnabled;
			case "isRouterExternal":
				return oBareMetalNetwork.isRouterExternal;
			case "isShared":
				return oBareMetalNetwork.isShared;
			case "isVlanQueing":
				return oBareMetalNetwork.isVlanQueing;
			case "isVlanTransparent":
				return oBareMetalNetwork.isVlanTransparent;
			case "l2Adjacency":
				return oBareMetalNetwork.l2Adjacency;
			case "locationCloud":
				return oBareMetalNetwork.locationCloud;
			case "locationProjectDomainId":
				return oBareMetalNetwork.locationProjectDomainId;
			case "locationProjectDomainName":
				return oBareMetalNetwork.locationProjectDomainName;
			case "locationProjectId":
				return oBareMetalNetwork.locationProjectId;
			case "locationProjectName":
				return oBareMetalNetwork.locationProjectName;
			case "locationRegionName":
				return oBareMetalNetwork.locationRegionName;
			case "locationZone":
				return oBareMetalNetwork.locationZone;
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
				o = relateBareMetalNetwork(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalNetwork(String var, Object val) {
		BareMetalNetwork oBareMetalNetwork = (BareMetalNetwork)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetBareMetalNetwork(entityVar,  siteRequest_, o);
	}
	public static Object staticSetBareMetalNetwork(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "id":
			return BareMetalNetwork.staticSetId(siteRequest_, o);
		case "name":
			return BareMetalNetwork.staticSetName(siteRequest_, o);
		case "description":
			return BareMetalNetwork.staticSetDescription(siteRequest_, o);
		case "availabilityZoneHints":
			return BareMetalNetwork.staticSetAvailabilityZoneHints(siteRequest_, o);
		case "availabilityZones":
			return BareMetalNetwork.staticSetAvailabilityZones(siteRequest_, o);
		case "createdAt":
			return BareMetalNetwork.staticSetCreatedAt(siteRequest_, o);
		case "dnsDomain":
			return BareMetalNetwork.staticSetDnsDomain(siteRequest_, o);
		case "mtu":
			return BareMetalNetwork.staticSetMtu(siteRequest_, o);
		case "projectId":
			return BareMetalNetwork.staticSetProjectId(siteRequest_, o);
		case "providerNetworkType":
			return BareMetalNetwork.staticSetProviderNetworkType(siteRequest_, o);
		case "providerPhysicalNetwork":
			return BareMetalNetwork.staticSetProviderPhysicalNetwork(siteRequest_, o);
		case "providerSegmentationId":
			return BareMetalNetwork.staticSetProviderSegmentationId(siteRequest_, o);
		case "qosPolicyId":
			return BareMetalNetwork.staticSetQosPolicyId(siteRequest_, o);
		case "revisionNumber":
			return BareMetalNetwork.staticSetRevisionNumber(siteRequest_, o);
		case "status":
			return BareMetalNetwork.staticSetStatus(siteRequest_, o);
		case "subnetIds":
			return BareMetalNetwork.staticSetSubnetIds(siteRequest_, o);
		case "tags":
			return BareMetalNetwork.staticSetTags(siteRequest_, o);
		case "tenantId":
			return BareMetalNetwork.staticSetTenantId(siteRequest_, o);
		case "updatedAt":
			return BareMetalNetwork.staticSetUpdatedAt(siteRequest_, o);
		case "isAdminStateUp":
			return BareMetalNetwork.staticSetIsAdminStateUp(siteRequest_, o);
		case "isDefault":
			return BareMetalNetwork.staticSetIsDefault(siteRequest_, o);
		case "isPortSecurityEnabled":
			return BareMetalNetwork.staticSetIsPortSecurityEnabled(siteRequest_, o);
		case "isRouterExternal":
			return BareMetalNetwork.staticSetIsRouterExternal(siteRequest_, o);
		case "isShared":
			return BareMetalNetwork.staticSetIsShared(siteRequest_, o);
		case "isVlanQueing":
			return BareMetalNetwork.staticSetIsVlanQueing(siteRequest_, o);
		case "isVlanTransparent":
			return BareMetalNetwork.staticSetIsVlanTransparent(siteRequest_, o);
		case "l2Adjacency":
			return BareMetalNetwork.staticSetL2Adjacency(siteRequest_, o);
		case "locationCloud":
			return BareMetalNetwork.staticSetLocationCloud(siteRequest_, o);
		case "locationProjectDomainId":
			return BareMetalNetwork.staticSetLocationProjectDomainId(siteRequest_, o);
		case "locationProjectDomainName":
			return BareMetalNetwork.staticSetLocationProjectDomainName(siteRequest_, o);
		case "locationProjectId":
			return BareMetalNetwork.staticSetLocationProjectId(siteRequest_, o);
		case "locationProjectName":
			return BareMetalNetwork.staticSetLocationProjectName(siteRequest_, o);
		case "locationRegionName":
			return BareMetalNetwork.staticSetLocationRegionName(siteRequest_, o);
		case "locationZone":
			return BareMetalNetwork.staticSetLocationZone(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalNetwork(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalNetwork(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return BareMetalNetwork.staticSearchId(siteRequest_, (String)o);
		case "name":
			return BareMetalNetwork.staticSearchName(siteRequest_, (String)o);
		case "description":
			return BareMetalNetwork.staticSearchDescription(siteRequest_, (String)o);
		case "availabilityZoneHints":
			return BareMetalNetwork.staticSearchAvailabilityZoneHints(siteRequest_, (JsonArray)o);
		case "availabilityZones":
			return BareMetalNetwork.staticSearchAvailabilityZones(siteRequest_, (String)o);
		case "createdAt":
			return BareMetalNetwork.staticSearchCreatedAt(siteRequest_, (ZonedDateTime)o);
		case "dnsDomain":
			return BareMetalNetwork.staticSearchDnsDomain(siteRequest_, (String)o);
		case "mtu":
			return BareMetalNetwork.staticSearchMtu(siteRequest_, (Integer)o);
		case "projectId":
			return BareMetalNetwork.staticSearchProjectId(siteRequest_, (String)o);
		case "providerNetworkType":
			return BareMetalNetwork.staticSearchProviderNetworkType(siteRequest_, (String)o);
		case "providerPhysicalNetwork":
			return BareMetalNetwork.staticSearchProviderPhysicalNetwork(siteRequest_, (String)o);
		case "providerSegmentationId":
			return BareMetalNetwork.staticSearchProviderSegmentationId(siteRequest_, (String)o);
		case "qosPolicyId":
			return BareMetalNetwork.staticSearchQosPolicyId(siteRequest_, (String)o);
		case "revisionNumber":
			return BareMetalNetwork.staticSearchRevisionNumber(siteRequest_, (Integer)o);
		case "status":
			return BareMetalNetwork.staticSearchStatus(siteRequest_, (String)o);
		case "subnetIds":
			return BareMetalNetwork.staticSearchSubnetIds(siteRequest_, (String)o);
		case "tags":
			return BareMetalNetwork.staticSearchTags(siteRequest_, (String)o);
		case "tenantId":
			return BareMetalNetwork.staticSearchTenantId(siteRequest_, (String)o);
		case "updatedAt":
			return BareMetalNetwork.staticSearchUpdatedAt(siteRequest_, (ZonedDateTime)o);
		case "isAdminStateUp":
			return BareMetalNetwork.staticSearchIsAdminStateUp(siteRequest_, (Boolean)o);
		case "isDefault":
			return BareMetalNetwork.staticSearchIsDefault(siteRequest_, (Boolean)o);
		case "isPortSecurityEnabled":
			return BareMetalNetwork.staticSearchIsPortSecurityEnabled(siteRequest_, (Boolean)o);
		case "isRouterExternal":
			return BareMetalNetwork.staticSearchIsRouterExternal(siteRequest_, (Boolean)o);
		case "isShared":
			return BareMetalNetwork.staticSearchIsShared(siteRequest_, (Boolean)o);
		case "isVlanQueing":
			return BareMetalNetwork.staticSearchIsVlanQueing(siteRequest_, (Boolean)o);
		case "isVlanTransparent":
			return BareMetalNetwork.staticSearchIsVlanTransparent(siteRequest_, (Boolean)o);
		case "l2Adjacency":
			return BareMetalNetwork.staticSearchL2Adjacency(siteRequest_, (Boolean)o);
		case "locationCloud":
			return BareMetalNetwork.staticSearchLocationCloud(siteRequest_, (String)o);
		case "locationProjectDomainId":
			return BareMetalNetwork.staticSearchLocationProjectDomainId(siteRequest_, (String)o);
		case "locationProjectDomainName":
			return BareMetalNetwork.staticSearchLocationProjectDomainName(siteRequest_, (String)o);
		case "locationProjectId":
			return BareMetalNetwork.staticSearchLocationProjectId(siteRequest_, (String)o);
		case "locationProjectName":
			return BareMetalNetwork.staticSearchLocationProjectName(siteRequest_, (String)o);
		case "locationRegionName":
			return BareMetalNetwork.staticSearchLocationRegionName(siteRequest_, (String)o);
		case "locationZone":
			return BareMetalNetwork.staticSearchLocationZone(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalNetwork(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalNetwork(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return BareMetalNetwork.staticSearchStrId(siteRequest_, (String)o);
		case "name":
			return BareMetalNetwork.staticSearchStrName(siteRequest_, (String)o);
		case "description":
			return BareMetalNetwork.staticSearchStrDescription(siteRequest_, (String)o);
		case "availabilityZoneHints":
			return BareMetalNetwork.staticSearchStrAvailabilityZoneHints(siteRequest_, (String)o);
		case "availabilityZones":
			return BareMetalNetwork.staticSearchStrAvailabilityZones(siteRequest_, (String)o);
		case "createdAt":
			return BareMetalNetwork.staticSearchStrCreatedAt(siteRequest_, (String)o);
		case "dnsDomain":
			return BareMetalNetwork.staticSearchStrDnsDomain(siteRequest_, (String)o);
		case "mtu":
			return BareMetalNetwork.staticSearchStrMtu(siteRequest_, (Integer)o);
		case "projectId":
			return BareMetalNetwork.staticSearchStrProjectId(siteRequest_, (String)o);
		case "providerNetworkType":
			return BareMetalNetwork.staticSearchStrProviderNetworkType(siteRequest_, (String)o);
		case "providerPhysicalNetwork":
			return BareMetalNetwork.staticSearchStrProviderPhysicalNetwork(siteRequest_, (String)o);
		case "providerSegmentationId":
			return BareMetalNetwork.staticSearchStrProviderSegmentationId(siteRequest_, (String)o);
		case "qosPolicyId":
			return BareMetalNetwork.staticSearchStrQosPolicyId(siteRequest_, (String)o);
		case "revisionNumber":
			return BareMetalNetwork.staticSearchStrRevisionNumber(siteRequest_, (Integer)o);
		case "status":
			return BareMetalNetwork.staticSearchStrStatus(siteRequest_, (String)o);
		case "subnetIds":
			return BareMetalNetwork.staticSearchStrSubnetIds(siteRequest_, (String)o);
		case "tags":
			return BareMetalNetwork.staticSearchStrTags(siteRequest_, (String)o);
		case "tenantId":
			return BareMetalNetwork.staticSearchStrTenantId(siteRequest_, (String)o);
		case "updatedAt":
			return BareMetalNetwork.staticSearchStrUpdatedAt(siteRequest_, (String)o);
		case "isAdminStateUp":
			return BareMetalNetwork.staticSearchStrIsAdminStateUp(siteRequest_, (Boolean)o);
		case "isDefault":
			return BareMetalNetwork.staticSearchStrIsDefault(siteRequest_, (Boolean)o);
		case "isPortSecurityEnabled":
			return BareMetalNetwork.staticSearchStrIsPortSecurityEnabled(siteRequest_, (Boolean)o);
		case "isRouterExternal":
			return BareMetalNetwork.staticSearchStrIsRouterExternal(siteRequest_, (Boolean)o);
		case "isShared":
			return BareMetalNetwork.staticSearchStrIsShared(siteRequest_, (Boolean)o);
		case "isVlanQueing":
			return BareMetalNetwork.staticSearchStrIsVlanQueing(siteRequest_, (Boolean)o);
		case "isVlanTransparent":
			return BareMetalNetwork.staticSearchStrIsVlanTransparent(siteRequest_, (Boolean)o);
		case "l2Adjacency":
			return BareMetalNetwork.staticSearchStrL2Adjacency(siteRequest_, (Boolean)o);
		case "locationCloud":
			return BareMetalNetwork.staticSearchStrLocationCloud(siteRequest_, (String)o);
		case "locationProjectDomainId":
			return BareMetalNetwork.staticSearchStrLocationProjectDomainId(siteRequest_, (String)o);
		case "locationProjectDomainName":
			return BareMetalNetwork.staticSearchStrLocationProjectDomainName(siteRequest_, (String)o);
		case "locationProjectId":
			return BareMetalNetwork.staticSearchStrLocationProjectId(siteRequest_, (String)o);
		case "locationProjectName":
			return BareMetalNetwork.staticSearchStrLocationProjectName(siteRequest_, (String)o);
		case "locationRegionName":
			return BareMetalNetwork.staticSearchStrLocationRegionName(siteRequest_, (String)o);
		case "locationZone":
			return BareMetalNetwork.staticSearchStrLocationZone(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalNetwork(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalNetwork(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "id":
			return BareMetalNetwork.staticSearchFqId(siteRequest_, o);
		case "name":
			return BareMetalNetwork.staticSearchFqName(siteRequest_, o);
		case "description":
			return BareMetalNetwork.staticSearchFqDescription(siteRequest_, o);
		case "availabilityZoneHints":
			return BareMetalNetwork.staticSearchFqAvailabilityZoneHints(siteRequest_, o);
		case "availabilityZones":
			return BareMetalNetwork.staticSearchFqAvailabilityZones(siteRequest_, o);
		case "createdAt":
			return BareMetalNetwork.staticSearchFqCreatedAt(siteRequest_, o);
		case "dnsDomain":
			return BareMetalNetwork.staticSearchFqDnsDomain(siteRequest_, o);
		case "mtu":
			return BareMetalNetwork.staticSearchFqMtu(siteRequest_, o);
		case "projectId":
			return BareMetalNetwork.staticSearchFqProjectId(siteRequest_, o);
		case "providerNetworkType":
			return BareMetalNetwork.staticSearchFqProviderNetworkType(siteRequest_, o);
		case "providerPhysicalNetwork":
			return BareMetalNetwork.staticSearchFqProviderPhysicalNetwork(siteRequest_, o);
		case "providerSegmentationId":
			return BareMetalNetwork.staticSearchFqProviderSegmentationId(siteRequest_, o);
		case "qosPolicyId":
			return BareMetalNetwork.staticSearchFqQosPolicyId(siteRequest_, o);
		case "revisionNumber":
			return BareMetalNetwork.staticSearchFqRevisionNumber(siteRequest_, o);
		case "status":
			return BareMetalNetwork.staticSearchFqStatus(siteRequest_, o);
		case "subnetIds":
			return BareMetalNetwork.staticSearchFqSubnetIds(siteRequest_, o);
		case "tags":
			return BareMetalNetwork.staticSearchFqTags(siteRequest_, o);
		case "tenantId":
			return BareMetalNetwork.staticSearchFqTenantId(siteRequest_, o);
		case "updatedAt":
			return BareMetalNetwork.staticSearchFqUpdatedAt(siteRequest_, o);
		case "isAdminStateUp":
			return BareMetalNetwork.staticSearchFqIsAdminStateUp(siteRequest_, o);
		case "isDefault":
			return BareMetalNetwork.staticSearchFqIsDefault(siteRequest_, o);
		case "isPortSecurityEnabled":
			return BareMetalNetwork.staticSearchFqIsPortSecurityEnabled(siteRequest_, o);
		case "isRouterExternal":
			return BareMetalNetwork.staticSearchFqIsRouterExternal(siteRequest_, o);
		case "isShared":
			return BareMetalNetwork.staticSearchFqIsShared(siteRequest_, o);
		case "isVlanQueing":
			return BareMetalNetwork.staticSearchFqIsVlanQueing(siteRequest_, o);
		case "isVlanTransparent":
			return BareMetalNetwork.staticSearchFqIsVlanTransparent(siteRequest_, o);
		case "l2Adjacency":
			return BareMetalNetwork.staticSearchFqL2Adjacency(siteRequest_, o);
		case "locationCloud":
			return BareMetalNetwork.staticSearchFqLocationCloud(siteRequest_, o);
		case "locationProjectDomainId":
			return BareMetalNetwork.staticSearchFqLocationProjectDomainId(siteRequest_, o);
		case "locationProjectDomainName":
			return BareMetalNetwork.staticSearchFqLocationProjectDomainName(siteRequest_, o);
		case "locationProjectId":
			return BareMetalNetwork.staticSearchFqLocationProjectId(siteRequest_, o);
		case "locationProjectName":
			return BareMetalNetwork.staticSearchFqLocationProjectName(siteRequest_, o);
		case "locationRegionName":
			return BareMetalNetwork.staticSearchFqLocationRegionName(siteRequest_, o);
		case "locationZone":
			return BareMetalNetwork.staticSearchFqLocationZone(siteRequest_, o);
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
					o = persistBareMetalNetwork(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistBareMetalNetwork(String var, Object val) {
		String varLower = var.toLowerCase();
			if("id".equals(varLower)) {
				if(val instanceof String) {
					setId((String)val);
				}
				saves.add("id");
				return val;
			} else if("name".equals(varLower)) {
				if(val instanceof String) {
					setName((String)val);
				}
				saves.add("name");
				return val;
			} else if("description".equals(varLower)) {
				if(val instanceof String) {
					setDescription((String)val);
				}
				saves.add("description");
				return val;
			} else if("availabilityzonehints".equals(varLower)) {
				if(val instanceof String) {
					setAvailabilityZoneHints((String)val);
				} else if(val instanceof JsonArray) {
					setAvailabilityZoneHints((JsonArray)val);
				}
				saves.add("availabilityZoneHints");
				return val;
			} else if("availabilityzones".equals(varLower)) {
				if(val instanceof List<?>) {
					((List<String>)val).stream().forEach(v -> addAvailabilityZones(v));
				} else if(val instanceof JsonArray) {
					((JsonArray)val).stream().forEach(v -> addAvailabilityZones(staticSetAvailabilityZones(siteRequest_, v.toString())));
				} else if(val instanceof String[]) {
					Arrays.asList((String[])val).stream().forEach(v -> addAvailabilityZones((String)v));
				}
				if(!saves.contains("availabilityZones")) {
					saves.add("availabilityZones");
				}
				return val;
			} else if("createdat".equals(varLower)) {
				if(val instanceof String) {
					setCreatedAt((String)val);
				} else if(val instanceof OffsetDateTime) {
					setCreatedAt(((OffsetDateTime)val).atZoneSameInstant(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))));
				}
				saves.add("createdAt");
				return val;
			} else if("dnsdomain".equals(varLower)) {
				if(val instanceof String) {
					setDnsDomain((String)val);
				}
				saves.add("dnsDomain");
				return val;
			} else if("mtu".equals(varLower)) {
				if(val instanceof Integer) {
					setMtu((Integer)val);
				} else {
					setMtu(val == null ? null : val.toString());
				}
				saves.add("mtu");
				return val;
			} else if("projectid".equals(varLower)) {
				if(val instanceof String) {
					setProjectId((String)val);
				}
				saves.add("projectId");
				return val;
			} else if("providernetworktype".equals(varLower)) {
				if(val instanceof String) {
					setProviderNetworkType((String)val);
				}
				saves.add("providerNetworkType");
				return val;
			} else if("providerphysicalnetwork".equals(varLower)) {
				if(val instanceof String) {
					setProviderPhysicalNetwork((String)val);
				}
				saves.add("providerPhysicalNetwork");
				return val;
			} else if("providersegmentationid".equals(varLower)) {
				if(val instanceof String) {
					setProviderSegmentationId((String)val);
				}
				saves.add("providerSegmentationId");
				return val;
			} else if("qospolicyid".equals(varLower)) {
				if(val instanceof String) {
					setQosPolicyId((String)val);
				}
				saves.add("qosPolicyId");
				return val;
			} else if("revisionnumber".equals(varLower)) {
				if(val instanceof Integer) {
					setRevisionNumber((Integer)val);
				} else {
					setRevisionNumber(val == null ? null : val.toString());
				}
				saves.add("revisionNumber");
				return val;
			} else if("status".equals(varLower)) {
				if(val instanceof String) {
					setStatus((String)val);
				}
				saves.add("status");
				return val;
			} else if("subnetids".equals(varLower)) {
				if(val instanceof List<?>) {
					((List<String>)val).stream().forEach(v -> addSubnetIds(v));
				} else if(val instanceof JsonArray) {
					((JsonArray)val).stream().forEach(v -> addSubnetIds(staticSetSubnetIds(siteRequest_, v.toString())));
				} else if(val instanceof String[]) {
					Arrays.asList((String[])val).stream().forEach(v -> addSubnetIds((String)v));
				}
				if(!saves.contains("subnetIds")) {
					saves.add("subnetIds");
				}
				return val;
			} else if("tags".equals(varLower)) {
				if(val instanceof List<?>) {
					((List<String>)val).stream().forEach(v -> addTags(v));
				} else if(val instanceof JsonArray) {
					((JsonArray)val).stream().forEach(v -> addTags(staticSetTags(siteRequest_, v.toString())));
				} else if(val instanceof String[]) {
					Arrays.asList((String[])val).stream().forEach(v -> addTags((String)v));
				}
				if(!saves.contains("tags")) {
					saves.add("tags");
				}
				return val;
			} else if("tenantid".equals(varLower)) {
				if(val instanceof String) {
					setTenantId((String)val);
				}
				saves.add("tenantId");
				return val;
			} else if("updatedat".equals(varLower)) {
				if(val instanceof String) {
					setUpdatedAt((String)val);
				} else if(val instanceof OffsetDateTime) {
					setUpdatedAt(((OffsetDateTime)val).atZoneSameInstant(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))));
				}
				saves.add("updatedAt");
				return val;
			} else if("isadminstateup".equals(varLower)) {
				if(val instanceof Boolean) {
					setIsAdminStateUp((Boolean)val);
				} else {
					setIsAdminStateUp(val == null ? null : val.toString());
				}
				saves.add("isAdminStateUp");
				return val;
			} else if("isdefault".equals(varLower)) {
				if(val instanceof Boolean) {
					setIsDefault((Boolean)val);
				} else {
					setIsDefault(val == null ? null : val.toString());
				}
				saves.add("isDefault");
				return val;
			} else if("isportsecurityenabled".equals(varLower)) {
				if(val instanceof Boolean) {
					setIsPortSecurityEnabled((Boolean)val);
				} else {
					setIsPortSecurityEnabled(val == null ? null : val.toString());
				}
				saves.add("isPortSecurityEnabled");
				return val;
			} else if("isrouterexternal".equals(varLower)) {
				if(val instanceof Boolean) {
					setIsRouterExternal((Boolean)val);
				} else {
					setIsRouterExternal(val == null ? null : val.toString());
				}
				saves.add("isRouterExternal");
				return val;
			} else if("isshared".equals(varLower)) {
				if(val instanceof Boolean) {
					setIsShared((Boolean)val);
				} else {
					setIsShared(val == null ? null : val.toString());
				}
				saves.add("isShared");
				return val;
			} else if("isvlanqueing".equals(varLower)) {
				if(val instanceof Boolean) {
					setIsVlanQueing((Boolean)val);
				} else {
					setIsVlanQueing(val == null ? null : val.toString());
				}
				saves.add("isVlanQueing");
				return val;
			} else if("isvlantransparent".equals(varLower)) {
				if(val instanceof Boolean) {
					setIsVlanTransparent((Boolean)val);
				} else {
					setIsVlanTransparent(val == null ? null : val.toString());
				}
				saves.add("isVlanTransparent");
				return val;
			} else if("l2adjacency".equals(varLower)) {
				if(val instanceof Boolean) {
					setL2Adjacency((Boolean)val);
				} else {
					setL2Adjacency(val == null ? null : val.toString());
				}
				saves.add("l2Adjacency");
				return val;
			} else if("locationcloud".equals(varLower)) {
				if(val instanceof String) {
					setLocationCloud((String)val);
				}
				saves.add("locationCloud");
				return val;
			} else if("locationprojectdomainid".equals(varLower)) {
				if(val instanceof String) {
					setLocationProjectDomainId((String)val);
				}
				saves.add("locationProjectDomainId");
				return val;
			} else if("locationprojectdomainname".equals(varLower)) {
				if(val instanceof String) {
					setLocationProjectDomainName((String)val);
				}
				saves.add("locationProjectDomainName");
				return val;
			} else if("locationprojectid".equals(varLower)) {
				if(val instanceof String) {
					setLocationProjectId((String)val);
				}
				saves.add("locationProjectId");
				return val;
			} else if("locationprojectname".equals(varLower)) {
				if(val instanceof String) {
					setLocationProjectName((String)val);
				}
				saves.add("locationProjectName");
				return val;
			} else if("locationregionname".equals(varLower)) {
				if(val instanceof String) {
					setLocationRegionName((String)val);
				}
				saves.add("locationRegionName");
				return val;
			} else if("locationzone".equals(varLower)) {
				if(val instanceof String) {
					setLocationZone((String)val);
				}
				saves.add("locationZone");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateBareMetalNetwork(doc);
	}
	public void populateBareMetalNetwork(SolrResponse.Doc doc) {
		BareMetalNetwork oBareMetalNetwork = (BareMetalNetwork)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("id")) {
				String id = (String)doc.get("id_docvalues_string");
				if(id != null)
					oBareMetalNetwork.setId(id);
			}

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oBareMetalNetwork.setName(name);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oBareMetalNetwork.setDescription(description);
			}

			if(saves.contains("availabilityZoneHints")) {
				String availabilityZoneHints = (String)doc.get("availabilityZoneHints_docvalues_string");
				if(availabilityZoneHints != null)
					oBareMetalNetwork.setAvailabilityZoneHints(availabilityZoneHints);
			}

			if(saves.contains("availabilityZones")) {
				List<String> availabilityZones = (List<String>)doc.get("availabilityZones_docvalues_strings");
				if(availabilityZones != null) {
					availabilityZones.stream().forEach( v -> {
						oBareMetalNetwork.availabilityZones.add(BareMetalNetwork.staticSetAvailabilityZones(siteRequest_, v));
					});
				}
			}

			if(saves.contains("createdAt")) {
				String createdAt = (String)doc.get("createdAt_docvalues_date");
				if(createdAt != null)
					oBareMetalNetwork.setCreatedAt(createdAt);
			}

			if(saves.contains("dnsDomain")) {
				String dnsDomain = (String)doc.get("dnsDomain_docvalues_string");
				if(dnsDomain != null)
					oBareMetalNetwork.setDnsDomain(dnsDomain);
			}

			if(saves.contains("mtu")) {
				Integer mtu = (Integer)doc.get("mtu_docvalues_int");
				if(mtu != null)
					oBareMetalNetwork.setMtu(mtu);
			}

			if(saves.contains("projectId")) {
				String projectId = (String)doc.get("projectId_docvalues_string");
				if(projectId != null)
					oBareMetalNetwork.setProjectId(projectId);
			}

			if(saves.contains("providerNetworkType")) {
				String providerNetworkType = (String)doc.get("providerNetworkType_docvalues_string");
				if(providerNetworkType != null)
					oBareMetalNetwork.setProviderNetworkType(providerNetworkType);
			}

			if(saves.contains("providerPhysicalNetwork")) {
				String providerPhysicalNetwork = (String)doc.get("providerPhysicalNetwork_docvalues_string");
				if(providerPhysicalNetwork != null)
					oBareMetalNetwork.setProviderPhysicalNetwork(providerPhysicalNetwork);
			}

			if(saves.contains("providerSegmentationId")) {
				String providerSegmentationId = (String)doc.get("providerSegmentationId_docvalues_string");
				if(providerSegmentationId != null)
					oBareMetalNetwork.setProviderSegmentationId(providerSegmentationId);
			}

			if(saves.contains("qosPolicyId")) {
				String qosPolicyId = (String)doc.get("qosPolicyId_docvalues_string");
				if(qosPolicyId != null)
					oBareMetalNetwork.setQosPolicyId(qosPolicyId);
			}

			if(saves.contains("revisionNumber")) {
				Integer revisionNumber = (Integer)doc.get("revisionNumber_docvalues_int");
				if(revisionNumber != null)
					oBareMetalNetwork.setRevisionNumber(revisionNumber);
			}

			if(saves.contains("status")) {
				String status = (String)doc.get("status_docvalues_string");
				if(status != null)
					oBareMetalNetwork.setStatus(status);
			}

			if(saves.contains("subnetIds")) {
				List<String> subnetIds = (List<String>)doc.get("subnetIds_docvalues_strings");
				if(subnetIds != null) {
					subnetIds.stream().forEach( v -> {
						oBareMetalNetwork.subnetIds.add(BareMetalNetwork.staticSetSubnetIds(siteRequest_, v));
					});
				}
			}

			if(saves.contains("tags")) {
				List<String> tags = (List<String>)doc.get("tags_docvalues_strings");
				if(tags != null) {
					tags.stream().forEach( v -> {
						oBareMetalNetwork.tags.add(BareMetalNetwork.staticSetTags(siteRequest_, v));
					});
				}
			}

			if(saves.contains("tenantId")) {
				String tenantId = (String)doc.get("tenantId_docvalues_string");
				if(tenantId != null)
					oBareMetalNetwork.setTenantId(tenantId);
			}

			if(saves.contains("updatedAt")) {
				String updatedAt = (String)doc.get("updatedAt_docvalues_date");
				if(updatedAt != null)
					oBareMetalNetwork.setUpdatedAt(updatedAt);
			}

			if(saves.contains("isAdminStateUp")) {
				Boolean isAdminStateUp = (Boolean)doc.get("isAdminStateUp_docvalues_boolean");
				if(isAdminStateUp != null)
					oBareMetalNetwork.setIsAdminStateUp(isAdminStateUp);
			}

			if(saves.contains("isDefault")) {
				Boolean isDefault = (Boolean)doc.get("isDefault_docvalues_boolean");
				if(isDefault != null)
					oBareMetalNetwork.setIsDefault(isDefault);
			}

			if(saves.contains("isPortSecurityEnabled")) {
				Boolean isPortSecurityEnabled = (Boolean)doc.get("isPortSecurityEnabled_docvalues_boolean");
				if(isPortSecurityEnabled != null)
					oBareMetalNetwork.setIsPortSecurityEnabled(isPortSecurityEnabled);
			}

			if(saves.contains("isRouterExternal")) {
				Boolean isRouterExternal = (Boolean)doc.get("isRouterExternal_docvalues_boolean");
				if(isRouterExternal != null)
					oBareMetalNetwork.setIsRouterExternal(isRouterExternal);
			}

			if(saves.contains("isShared")) {
				Boolean isShared = (Boolean)doc.get("isShared_docvalues_boolean");
				if(isShared != null)
					oBareMetalNetwork.setIsShared(isShared);
			}

			if(saves.contains("isVlanQueing")) {
				Boolean isVlanQueing = (Boolean)doc.get("isVlanQueing_docvalues_boolean");
				if(isVlanQueing != null)
					oBareMetalNetwork.setIsVlanQueing(isVlanQueing);
			}

			if(saves.contains("isVlanTransparent")) {
				Boolean isVlanTransparent = (Boolean)doc.get("isVlanTransparent_docvalues_boolean");
				if(isVlanTransparent != null)
					oBareMetalNetwork.setIsVlanTransparent(isVlanTransparent);
			}

			if(saves.contains("l2Adjacency")) {
				Boolean l2Adjacency = (Boolean)doc.get("l2Adjacency_docvalues_boolean");
				if(l2Adjacency != null)
					oBareMetalNetwork.setL2Adjacency(l2Adjacency);
			}

			if(saves.contains("locationCloud")) {
				String locationCloud = (String)doc.get("locationCloud_docvalues_string");
				if(locationCloud != null)
					oBareMetalNetwork.setLocationCloud(locationCloud);
			}

			if(saves.contains("locationProjectDomainId")) {
				String locationProjectDomainId = (String)doc.get("locationProjectDomainId_docvalues_string");
				if(locationProjectDomainId != null)
					oBareMetalNetwork.setLocationProjectDomainId(locationProjectDomainId);
			}

			if(saves.contains("locationProjectDomainName")) {
				String locationProjectDomainName = (String)doc.get("locationProjectDomainName_docvalues_string");
				if(locationProjectDomainName != null)
					oBareMetalNetwork.setLocationProjectDomainName(locationProjectDomainName);
			}

			if(saves.contains("locationProjectId")) {
				String locationProjectId = (String)doc.get("locationProjectId_docvalues_string");
				if(locationProjectId != null)
					oBareMetalNetwork.setLocationProjectId(locationProjectId);
			}

			if(saves.contains("locationProjectName")) {
				String locationProjectName = (String)doc.get("locationProjectName_docvalues_string");
				if(locationProjectName != null)
					oBareMetalNetwork.setLocationProjectName(locationProjectName);
			}

			if(saves.contains("locationRegionName")) {
				String locationRegionName = (String)doc.get("locationRegionName_docvalues_string");
				if(locationRegionName != null)
					oBareMetalNetwork.setLocationRegionName(locationRegionName);
			}

			if(saves.contains("locationZone")) {
				String locationZone = (String)doc.get("locationZone_docvalues_string");
				if(locationZone != null)
					oBareMetalNetwork.setLocationZone(locationZone);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexBareMetalNetwork(JsonObject doc) {
		if(id != null) {
			doc.put("id_docvalues_string", id);
		}
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(availabilityZoneHints != null) {
			doc.put("availabilityZoneHints_docvalues_string", availabilityZoneHints.toString());
		}
		if(availabilityZones != null) {
			JsonArray l = new JsonArray();
			doc.put("availabilityZones_docvalues_strings", l);
			for(String o : availabilityZones) {
				l.add(BareMetalNetwork.staticSearchAvailabilityZones(siteRequest_, o));
			}
		}
		if(createdAt != null) {
			doc.put("createdAt_docvalues_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(createdAt.toInstant(), ZoneId.of("UTC"))));
		}
		if(dnsDomain != null) {
			doc.put("dnsDomain_docvalues_string", dnsDomain);
		}
		if(mtu != null) {
			doc.put("mtu_docvalues_int", mtu);
		}
		if(projectId != null) {
			doc.put("projectId_docvalues_string", projectId);
		}
		if(providerNetworkType != null) {
			doc.put("providerNetworkType_docvalues_string", providerNetworkType);
		}
		if(providerPhysicalNetwork != null) {
			doc.put("providerPhysicalNetwork_docvalues_string", providerPhysicalNetwork);
		}
		if(providerSegmentationId != null) {
			doc.put("providerSegmentationId_docvalues_string", providerSegmentationId);
		}
		if(qosPolicyId != null) {
			doc.put("qosPolicyId_docvalues_string", qosPolicyId);
		}
		if(revisionNumber != null) {
			doc.put("revisionNumber_docvalues_int", revisionNumber);
		}
		if(status != null) {
			doc.put("status_docvalues_string", status);
		}
		if(subnetIds != null) {
			JsonArray l = new JsonArray();
			doc.put("subnetIds_docvalues_strings", l);
			for(String o : subnetIds) {
				l.add(BareMetalNetwork.staticSearchSubnetIds(siteRequest_, o));
			}
		}
		if(tags != null) {
			JsonArray l = new JsonArray();
			doc.put("tags_docvalues_strings", l);
			for(String o : tags) {
				l.add(BareMetalNetwork.staticSearchTags(siteRequest_, o));
			}
		}
		if(tenantId != null) {
			doc.put("tenantId_docvalues_string", tenantId);
		}
		if(updatedAt != null) {
			doc.put("updatedAt_docvalues_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(updatedAt.toInstant(), ZoneId.of("UTC"))));
		}
		if(isAdminStateUp != null) {
			doc.put("isAdminStateUp_docvalues_boolean", isAdminStateUp);
		}
		if(isDefault != null) {
			doc.put("isDefault_docvalues_boolean", isDefault);
		}
		if(isPortSecurityEnabled != null) {
			doc.put("isPortSecurityEnabled_docvalues_boolean", isPortSecurityEnabled);
		}
		if(isRouterExternal != null) {
			doc.put("isRouterExternal_docvalues_boolean", isRouterExternal);
		}
		if(isShared != null) {
			doc.put("isShared_docvalues_boolean", isShared);
		}
		if(isVlanQueing != null) {
			doc.put("isVlanQueing_docvalues_boolean", isVlanQueing);
		}
		if(isVlanTransparent != null) {
			doc.put("isVlanTransparent_docvalues_boolean", isVlanTransparent);
		}
		if(l2Adjacency != null) {
			doc.put("l2Adjacency_docvalues_boolean", l2Adjacency);
		}
		if(locationCloud != null) {
			doc.put("locationCloud_docvalues_string", locationCloud);
		}
		if(locationProjectDomainId != null) {
			doc.put("locationProjectDomainId_docvalues_string", locationProjectDomainId);
		}
		if(locationProjectDomainName != null) {
			doc.put("locationProjectDomainName_docvalues_string", locationProjectDomainName);
		}
		if(locationProjectId != null) {
			doc.put("locationProjectId_docvalues_string", locationProjectId);
		}
		if(locationProjectName != null) {
			doc.put("locationProjectName_docvalues_string", locationProjectName);
		}
		if(locationRegionName != null) {
			doc.put("locationRegionName_docvalues_string", locationRegionName);
		}
		if(locationZone != null) {
			doc.put("locationZone_docvalues_string", locationZone);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredBareMetalNetwork(String entityVar) {
		switch(entityVar) {
			case "id":
				return "id_docvalues_string";
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "availabilityZoneHints":
				return "availabilityZoneHints_docvalues_string";
			case "availabilityZones":
				return "availabilityZones_docvalues_strings";
			case "createdAt":
				return "createdAt_docvalues_date";
			case "dnsDomain":
				return "dnsDomain_docvalues_string";
			case "mtu":
				return "mtu_docvalues_int";
			case "projectId":
				return "projectId_docvalues_string";
			case "providerNetworkType":
				return "providerNetworkType_docvalues_string";
			case "providerPhysicalNetwork":
				return "providerPhysicalNetwork_docvalues_string";
			case "providerSegmentationId":
				return "providerSegmentationId_docvalues_string";
			case "qosPolicyId":
				return "qosPolicyId_docvalues_string";
			case "revisionNumber":
				return "revisionNumber_docvalues_int";
			case "status":
				return "status_docvalues_string";
			case "subnetIds":
				return "subnetIds_docvalues_strings";
			case "tags":
				return "tags_docvalues_strings";
			case "tenantId":
				return "tenantId_docvalues_string";
			case "updatedAt":
				return "updatedAt_docvalues_date";
			case "isAdminStateUp":
				return "isAdminStateUp_docvalues_boolean";
			case "isDefault":
				return "isDefault_docvalues_boolean";
			case "isPortSecurityEnabled":
				return "isPortSecurityEnabled_docvalues_boolean";
			case "isRouterExternal":
				return "isRouterExternal_docvalues_boolean";
			case "isShared":
				return "isShared_docvalues_boolean";
			case "isVlanQueing":
				return "isVlanQueing_docvalues_boolean";
			case "isVlanTransparent":
				return "isVlanTransparent_docvalues_boolean";
			case "l2Adjacency":
				return "l2Adjacency_docvalues_boolean";
			case "locationCloud":
				return "locationCloud_docvalues_string";
			case "locationProjectDomainId":
				return "locationProjectDomainId_docvalues_string";
			case "locationProjectDomainName":
				return "locationProjectDomainName_docvalues_string";
			case "locationProjectId":
				return "locationProjectId_docvalues_string";
			case "locationProjectName":
				return "locationProjectName_docvalues_string";
			case "locationRegionName":
				return "locationRegionName_docvalues_string";
			case "locationZone":
				return "locationZone_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedBareMetalNetwork(String entityVar) {
		switch(entityVar) {
			case "id":
				return "id_docvalues_string";
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "availabilityZoneHints":
				return "availabilityZoneHints_docvalues_string";
			case "availabilityZones":
				return "availabilityZones_docvalues_strings";
			case "createdAt":
				return "createdAt_docvalues_date";
			case "dnsDomain":
				return "dnsDomain_docvalues_string";
			case "mtu":
				return "mtu_docvalues_int";
			case "projectId":
				return "projectId_docvalues_string";
			case "providerNetworkType":
				return "providerNetworkType_docvalues_string";
			case "providerPhysicalNetwork":
				return "providerPhysicalNetwork_docvalues_string";
			case "providerSegmentationId":
				return "providerSegmentationId_docvalues_string";
			case "qosPolicyId":
				return "qosPolicyId_docvalues_string";
			case "revisionNumber":
				return "revisionNumber_docvalues_int";
			case "status":
				return "status_docvalues_string";
			case "subnetIds":
				return "subnetIds_docvalues_strings";
			case "tags":
				return "tags_docvalues_strings";
			case "tenantId":
				return "tenantId_docvalues_string";
			case "updatedAt":
				return "updatedAt_docvalues_date";
			case "isAdminStateUp":
				return "isAdminStateUp_docvalues_boolean";
			case "isDefault":
				return "isDefault_docvalues_boolean";
			case "isPortSecurityEnabled":
				return "isPortSecurityEnabled_docvalues_boolean";
			case "isRouterExternal":
				return "isRouterExternal_docvalues_boolean";
			case "isShared":
				return "isShared_docvalues_boolean";
			case "isVlanQueing":
				return "isVlanQueing_docvalues_boolean";
			case "isVlanTransparent":
				return "isVlanTransparent_docvalues_boolean";
			case "l2Adjacency":
				return "l2Adjacency_docvalues_boolean";
			case "locationCloud":
				return "locationCloud_docvalues_string";
			case "locationProjectDomainId":
				return "locationProjectDomainId_docvalues_string";
			case "locationProjectDomainName":
				return "locationProjectDomainName_docvalues_string";
			case "locationProjectId":
				return "locationProjectId_docvalues_string";
			case "locationProjectName":
				return "locationProjectName_docvalues_string";
			case "locationRegionName":
				return "locationRegionName_docvalues_string";
			case "locationZone":
				return "locationZone_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarBareMetalNetwork(String searchVar) {
		switch(searchVar) {
			case "id_docvalues_string":
				return "id";
			case "name_docvalues_string":
				return "name";
			case "description_docvalues_string":
				return "description";
			case "availabilityZoneHints_docvalues_string":
				return "availabilityZoneHints";
			case "availabilityZones_docvalues_strings":
				return "availabilityZones";
			case "createdAt_docvalues_date":
				return "createdAt";
			case "dnsDomain_docvalues_string":
				return "dnsDomain";
			case "mtu_docvalues_int":
				return "mtu";
			case "projectId_docvalues_string":
				return "projectId";
			case "providerNetworkType_docvalues_string":
				return "providerNetworkType";
			case "providerPhysicalNetwork_docvalues_string":
				return "providerPhysicalNetwork";
			case "providerSegmentationId_docvalues_string":
				return "providerSegmentationId";
			case "qosPolicyId_docvalues_string":
				return "qosPolicyId";
			case "revisionNumber_docvalues_int":
				return "revisionNumber";
			case "status_docvalues_string":
				return "status";
			case "subnetIds_docvalues_strings":
				return "subnetIds";
			case "tags_docvalues_strings":
				return "tags";
			case "tenantId_docvalues_string":
				return "tenantId";
			case "updatedAt_docvalues_date":
				return "updatedAt";
			case "isAdminStateUp_docvalues_boolean":
				return "isAdminStateUp";
			case "isDefault_docvalues_boolean":
				return "isDefault";
			case "isPortSecurityEnabled_docvalues_boolean":
				return "isPortSecurityEnabled";
			case "isRouterExternal_docvalues_boolean":
				return "isRouterExternal";
			case "isShared_docvalues_boolean":
				return "isShared";
			case "isVlanQueing_docvalues_boolean":
				return "isVlanQueing";
			case "isVlanTransparent_docvalues_boolean":
				return "isVlanTransparent";
			case "l2Adjacency_docvalues_boolean":
				return "l2Adjacency";
			case "locationCloud_docvalues_string":
				return "locationCloud";
			case "locationProjectDomainId_docvalues_string":
				return "locationProjectDomainId";
			case "locationProjectDomainName_docvalues_string":
				return "locationProjectDomainName";
			case "locationProjectId_docvalues_string":
				return "locationProjectId";
			case "locationProjectName_docvalues_string":
				return "locationProjectName";
			case "locationRegionName_docvalues_string":
				return "locationRegionName";
			case "locationZone_docvalues_string":
				return "locationZone";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchBareMetalNetwork(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedBareMetalNetwork(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeBareMetalNetwork(doc);
	}
	public void storeBareMetalNetwork(SolrResponse.Doc doc) {
		BareMetalNetwork oBareMetalNetwork = (BareMetalNetwork)this;
		SiteRequest siteRequest = oBareMetalNetwork.getSiteRequest_();

		oBareMetalNetwork.setId(Optional.ofNullable(doc.get("id_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setAvailabilityZoneHints(Optional.ofNullable(doc.get("availabilityZoneHints_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("availabilityZones_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBareMetalNetwork.addAvailabilityZones(BareMetalNetwork.staticSetAvailabilityZones(siteRequest, v.toString()));
		});
		oBareMetalNetwork.setCreatedAt(Optional.ofNullable(doc.get("createdAt_docvalues_date")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setDnsDomain(Optional.ofNullable(doc.get("dnsDomain_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setMtu(Optional.ofNullable(doc.get("mtu_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setProjectId(Optional.ofNullable(doc.get("projectId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setProviderNetworkType(Optional.ofNullable(doc.get("providerNetworkType_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setProviderPhysicalNetwork(Optional.ofNullable(doc.get("providerPhysicalNetwork_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setProviderSegmentationId(Optional.ofNullable(doc.get("providerSegmentationId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setQosPolicyId(Optional.ofNullable(doc.get("qosPolicyId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setRevisionNumber(Optional.ofNullable(doc.get("revisionNumber_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setStatus(Optional.ofNullable(doc.get("status_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("subnetIds_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBareMetalNetwork.addSubnetIds(BareMetalNetwork.staticSetSubnetIds(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("tags_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBareMetalNetwork.addTags(BareMetalNetwork.staticSetTags(siteRequest, v.toString()));
		});
		oBareMetalNetwork.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setUpdatedAt(Optional.ofNullable(doc.get("updatedAt_docvalues_date")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setIsAdminStateUp(Optional.ofNullable(doc.get("isAdminStateUp_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setIsDefault(Optional.ofNullable(doc.get("isDefault_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setIsPortSecurityEnabled(Optional.ofNullable(doc.get("isPortSecurityEnabled_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setIsRouterExternal(Optional.ofNullable(doc.get("isRouterExternal_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setIsShared(Optional.ofNullable(doc.get("isShared_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setIsVlanQueing(Optional.ofNullable(doc.get("isVlanQueing_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setIsVlanTransparent(Optional.ofNullable(doc.get("isVlanTransparent_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setL2Adjacency(Optional.ofNullable(doc.get("l2Adjacency_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setLocationCloud(Optional.ofNullable(doc.get("locationCloud_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setLocationProjectDomainId(Optional.ofNullable(doc.get("locationProjectDomainId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setLocationProjectDomainName(Optional.ofNullable(doc.get("locationProjectDomainName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setLocationProjectId(Optional.ofNullable(doc.get("locationProjectId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setLocationProjectName(Optional.ofNullable(doc.get("locationProjectName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setLocationRegionName(Optional.ofNullable(doc.get("locationRegionName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNetwork.setLocationZone(Optional.ofNullable(doc.get("locationZone_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBareMetalNetwork() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BareMetalNetwork) {
			BareMetalNetwork original = (BareMetalNetwork)o;
			if(!Objects.equals(id, original.getId()))
				apiRequest.addVars("id");
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(availabilityZoneHints, original.getAvailabilityZoneHints()))
				apiRequest.addVars("availabilityZoneHints");
			if(!Objects.equals(availabilityZones, original.getAvailabilityZones()))
				apiRequest.addVars("availabilityZones");
			if(!Objects.equals(createdAt, original.getCreatedAt()))
				apiRequest.addVars("createdAt");
			if(!Objects.equals(dnsDomain, original.getDnsDomain()))
				apiRequest.addVars("dnsDomain");
			if(!Objects.equals(mtu, original.getMtu()))
				apiRequest.addVars("mtu");
			if(!Objects.equals(projectId, original.getProjectId()))
				apiRequest.addVars("projectId");
			if(!Objects.equals(providerNetworkType, original.getProviderNetworkType()))
				apiRequest.addVars("providerNetworkType");
			if(!Objects.equals(providerPhysicalNetwork, original.getProviderPhysicalNetwork()))
				apiRequest.addVars("providerPhysicalNetwork");
			if(!Objects.equals(providerSegmentationId, original.getProviderSegmentationId()))
				apiRequest.addVars("providerSegmentationId");
			if(!Objects.equals(qosPolicyId, original.getQosPolicyId()))
				apiRequest.addVars("qosPolicyId");
			if(!Objects.equals(revisionNumber, original.getRevisionNumber()))
				apiRequest.addVars("revisionNumber");
			if(!Objects.equals(status, original.getStatus()))
				apiRequest.addVars("status");
			if(!Objects.equals(subnetIds, original.getSubnetIds()))
				apiRequest.addVars("subnetIds");
			if(!Objects.equals(tags, original.getTags()))
				apiRequest.addVars("tags");
			if(!Objects.equals(tenantId, original.getTenantId()))
				apiRequest.addVars("tenantId");
			if(!Objects.equals(updatedAt, original.getUpdatedAt()))
				apiRequest.addVars("updatedAt");
			if(!Objects.equals(isAdminStateUp, original.getIsAdminStateUp()))
				apiRequest.addVars("isAdminStateUp");
			if(!Objects.equals(isDefault, original.getIsDefault()))
				apiRequest.addVars("isDefault");
			if(!Objects.equals(isPortSecurityEnabled, original.getIsPortSecurityEnabled()))
				apiRequest.addVars("isPortSecurityEnabled");
			if(!Objects.equals(isRouterExternal, original.getIsRouterExternal()))
				apiRequest.addVars("isRouterExternal");
			if(!Objects.equals(isShared, original.getIsShared()))
				apiRequest.addVars("isShared");
			if(!Objects.equals(isVlanQueing, original.getIsVlanQueing()))
				apiRequest.addVars("isVlanQueing");
			if(!Objects.equals(isVlanTransparent, original.getIsVlanTransparent()))
				apiRequest.addVars("isVlanTransparent");
			if(!Objects.equals(l2Adjacency, original.getL2Adjacency()))
				apiRequest.addVars("l2Adjacency");
			if(!Objects.equals(locationCloud, original.getLocationCloud()))
				apiRequest.addVars("locationCloud");
			if(!Objects.equals(locationProjectDomainId, original.getLocationProjectDomainId()))
				apiRequest.addVars("locationProjectDomainId");
			if(!Objects.equals(locationProjectDomainName, original.getLocationProjectDomainName()))
				apiRequest.addVars("locationProjectDomainName");
			if(!Objects.equals(locationProjectId, original.getLocationProjectId()))
				apiRequest.addVars("locationProjectId");
			if(!Objects.equals(locationProjectName, original.getLocationProjectName()))
				apiRequest.addVars("locationProjectName");
			if(!Objects.equals(locationRegionName, original.getLocationRegionName()))
				apiRequest.addVars("locationRegionName");
			if(!Objects.equals(locationZone, original.getLocationZone()))
				apiRequest.addVars("locationZone");
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
		sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(availabilityZoneHints).map(v -> "availabilityZoneHints: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(availabilityZones).map(v -> "availabilityZones: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(createdAt).map(v -> "createdAt: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(dnsDomain).map(v -> "dnsDomain: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(mtu).map(v -> "mtu: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(projectId).map(v -> "projectId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(providerNetworkType).map(v -> "providerNetworkType: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(providerPhysicalNetwork).map(v -> "providerPhysicalNetwork: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(providerSegmentationId).map(v -> "providerSegmentationId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(qosPolicyId).map(v -> "qosPolicyId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(revisionNumber).map(v -> "revisionNumber: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(status).map(v -> "status: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(subnetIds).map(v -> "subnetIds: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(tags).map(v -> "tags: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(tenantId).map(v -> "tenantId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(updatedAt).map(v -> "updatedAt: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(isAdminStateUp).map(v -> "isAdminStateUp: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(isDefault).map(v -> "isDefault: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(isPortSecurityEnabled).map(v -> "isPortSecurityEnabled: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(isRouterExternal).map(v -> "isRouterExternal: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(isShared).map(v -> "isShared: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(isVlanQueing).map(v -> "isVlanQueing: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(isVlanTransparent).map(v -> "isVlanTransparent: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(l2Adjacency).map(v -> "l2Adjacency: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationCloud).map(v -> "locationCloud: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationProjectDomainId).map(v -> "locationProjectDomainId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationProjectDomainName).map(v -> "locationProjectDomainName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationProjectId).map(v -> "locationProjectId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationProjectName).map(v -> "locationProjectName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationRegionName).map(v -> "locationRegionName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationZone).map(v -> "locationZone: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String[] BareMetalNetworkVals = new String[] { idESI1_enUS, nameESI1_enUS, descriptionESI1_enUS, availabilityZoneHintsESI1_enUS, availabilityZonesESI1_enUS, createdAtESI1_enUS, dnsDomainESI1_enUS, mtuESI1_enUS, projectIdESI1_enUS, providerNetworkTypeESI1_enUS, providerPhysicalNetworkESI1_enUS, providerSegmentationIdESI1_enUS, qosPolicyIdESI1_enUS, revisionNumberESI1_enUS, statusESI1_enUS, subnetIdsESI1_enUS, tagsESI1_enUS, tenantIdESI1_enUS, updatedAtESI1_enUS, isAdminStateUpESI1_enUS, isDefaultESI1_enUS, isPortSecurityEnabledESI1_enUS, isRouterExternalESI1_enUS, isSharedESI1_enUS, isVlanQueingESI1_enUS, isVlanTransparentESI1_enUS, l2AdjacencyESI1_enUS, locationCloudESIlocation1_enUS, locationCloudESIcloud1_enUS, locationProjectDomainIdESIlocation1_enUS, locationProjectDomainIdESIproject1_enUS, locationProjectDomainIdESIdomain_id1_enUS, locationProjectDomainNameESIlocation1_enUS, locationProjectDomainNameESIproject1_enUS, locationProjectDomainNameESIdomain_name1_enUS, locationProjectIdESIlocation1_enUS, locationProjectIdESIproject1_enUS, locationProjectIdESIid1_enUS, locationProjectNameESIlocation1_enUS, locationProjectNameESIproject1_enUS, locationProjectNameESIname1_enUS, locationRegionNameESIlocation1_enUS, locationRegionNameESIregion_name1_enUS, locationZoneESIlocation1_enUS, locationZoneESIzone1_enUS };

	public static final String CLASS_SIMPLE_NAME = "BareMetalNetwork";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork";
	public static final String CLASS_API_ADDRESS_BareMetalNetwork = "ai-telemetry-enUS-BareMetalNetwork";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_BareMetalNetwork;
	}
	public static final String VAR_id = "id";
	public static final String VAR_name = "name";
	public static final String VAR_description = "description";
	public static final String VAR_availabilityZoneHints = "availabilityZoneHints";
	public static final String VAR_availabilityZones = "availabilityZones";
	public static final String VAR_createdAt = "createdAt";
	public static final String VAR_dnsDomain = "dnsDomain";
	public static final String VAR_mtu = "mtu";
	public static final String VAR_projectId = "projectId";
	public static final String VAR_providerNetworkType = "providerNetworkType";
	public static final String VAR_providerPhysicalNetwork = "providerPhysicalNetwork";
	public static final String VAR_providerSegmentationId = "providerSegmentationId";
	public static final String VAR_qosPolicyId = "qosPolicyId";
	public static final String VAR_revisionNumber = "revisionNumber";
	public static final String VAR_status = "status";
	public static final String VAR_subnetIds = "subnetIds";
	public static final String VAR_tags = "tags";
	public static final String VAR_tenantId = "tenantId";
	public static final String VAR_updatedAt = "updatedAt";
	public static final String VAR_isAdminStateUp = "isAdminStateUp";
	public static final String VAR_isDefault = "isDefault";
	public static final String VAR_isPortSecurityEnabled = "isPortSecurityEnabled";
	public static final String VAR_isRouterExternal = "isRouterExternal";
	public static final String VAR_isShared = "isShared";
	public static final String VAR_isVlanQueing = "isVlanQueing";
	public static final String VAR_isVlanTransparent = "isVlanTransparent";
	public static final String VAR_l2Adjacency = "l2Adjacency";
	public static final String VAR_locationCloud = "locationCloud";
	public static final String VAR_locationProjectDomainId = "locationProjectDomainId";
	public static final String VAR_locationProjectDomainName = "locationProjectDomainName";
	public static final String VAR_locationProjectId = "locationProjectId";
	public static final String VAR_locationProjectName = "locationProjectName";
	public static final String VAR_locationRegionName = "locationRegionName";
	public static final String VAR_locationZone = "locationZone";

	public static List<String> varsQForClass() {
		return BareMetalNetwork.varsQBareMetalNetwork(new ArrayList<String>());
	}
	public static List<String> varsQBareMetalNetwork(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return BareMetalNetwork.varsFqBareMetalNetwork(new ArrayList<String>());
	}
	public static List<String> varsFqBareMetalNetwork(List<String> vars) {
		vars.add(VAR_id);
		vars.add(VAR_name);
		vars.add(VAR_description);
		vars.add(VAR_availabilityZoneHints);
		vars.add(VAR_availabilityZones);
		vars.add(VAR_createdAt);
		vars.add(VAR_dnsDomain);
		vars.add(VAR_mtu);
		vars.add(VAR_projectId);
		vars.add(VAR_providerNetworkType);
		vars.add(VAR_providerPhysicalNetwork);
		vars.add(VAR_providerSegmentationId);
		vars.add(VAR_qosPolicyId);
		vars.add(VAR_revisionNumber);
		vars.add(VAR_status);
		vars.add(VAR_subnetIds);
		vars.add(VAR_tags);
		vars.add(VAR_tenantId);
		vars.add(VAR_updatedAt);
		vars.add(VAR_isAdminStateUp);
		vars.add(VAR_isDefault);
		vars.add(VAR_isPortSecurityEnabled);
		vars.add(VAR_isRouterExternal);
		vars.add(VAR_isShared);
		vars.add(VAR_isVlanQueing);
		vars.add(VAR_isVlanTransparent);
		vars.add(VAR_l2Adjacency);
		vars.add(VAR_locationCloud);
		vars.add(VAR_locationProjectDomainId);
		vars.add(VAR_locationProjectDomainName);
		vars.add(VAR_locationProjectId);
		vars.add(VAR_locationProjectName);
		vars.add(VAR_locationRegionName);
		vars.add(VAR_locationZone);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return BareMetalNetwork.varsRangeBareMetalNetwork(new ArrayList<String>());
	}
	public static List<String> varsRangeBareMetalNetwork(List<String> vars) {
		vars.add(VAR_availabilityZoneHints);
		vars.add(VAR_createdAt);
		vars.add(VAR_mtu);
		vars.add(VAR_revisionNumber);
		vars.add(VAR_updatedAt);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_id = "network ID";
	public static final String DISPLAY_NAME_name = "network name";
	public static final String DISPLAY_NAME_description = "network description";
	public static final String DISPLAY_NAME_availabilityZoneHints = "availability zone hints";
	public static final String DISPLAY_NAME_availabilityZones = "availability zones";
	public static final String DISPLAY_NAME_createdAt = "created at";
	public static final String DISPLAY_NAME_dnsDomain = "DNS domain";
	public static final String DISPLAY_NAME_mtu = "MTU";
	public static final String DISPLAY_NAME_projectId = "project ID";
	public static final String DISPLAY_NAME_providerNetworkType = "provider network type";
	public static final String DISPLAY_NAME_providerPhysicalNetwork = "provider physical network";
	public static final String DISPLAY_NAME_providerSegmentationId = "provider segmentation ID";
	public static final String DISPLAY_NAME_qosPolicyId = "QOS policy ID";
	public static final String DISPLAY_NAME_revisionNumber = "revision number";
	public static final String DISPLAY_NAME_status = "status";
	public static final String DISPLAY_NAME_subnetIds = "subnetIds";
	public static final String DISPLAY_NAME_tags = "tags";
	public static final String DISPLAY_NAME_tenantId = "tenantId";
	public static final String DISPLAY_NAME_updatedAt = "updatedAt";
	public static final String DISPLAY_NAME_isAdminStateUp = "is admin state up";
	public static final String DISPLAY_NAME_isDefault = "is default";
	public static final String DISPLAY_NAME_isPortSecurityEnabled = "is port security enabled";
	public static final String DISPLAY_NAME_isRouterExternal = "is router external";
	public static final String DISPLAY_NAME_isShared = "is shared";
	public static final String DISPLAY_NAME_isVlanQueing = "is VLAN queing";
	public static final String DISPLAY_NAME_isVlanTransparent = "is VLAN transparent";
	public static final String DISPLAY_NAME_l2Adjacency = "l2Adjecency";
	public static final String DISPLAY_NAME_locationCloud = "location cloud";
	public static final String DISPLAY_NAME_locationProjectDomainId = "location project domain ID";
	public static final String DISPLAY_NAME_locationProjectDomainName = "location project domain name";
	public static final String DISPLAY_NAME_locationProjectId = "location project ID";
	public static final String DISPLAY_NAME_locationProjectName = "location project name";
	public static final String DISPLAY_NAME_locationRegionName = "location region name";
	public static final String DISPLAY_NAME_locationZone = "location zone";

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
		return name;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return BareMetalNetwork.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/bare-metal-network/%s";
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
		return BareMetalNetwork.displayNameBareMetalNetwork(var);
	}
	public static String displayNameBareMetalNetwork(String var) {
		switch(var) {
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_availabilityZoneHints:
			return DISPLAY_NAME_availabilityZoneHints;
		case VAR_availabilityZones:
			return DISPLAY_NAME_availabilityZones;
		case VAR_createdAt:
			return DISPLAY_NAME_createdAt;
		case VAR_dnsDomain:
			return DISPLAY_NAME_dnsDomain;
		case VAR_mtu:
			return DISPLAY_NAME_mtu;
		case VAR_projectId:
			return DISPLAY_NAME_projectId;
		case VAR_providerNetworkType:
			return DISPLAY_NAME_providerNetworkType;
		case VAR_providerPhysicalNetwork:
			return DISPLAY_NAME_providerPhysicalNetwork;
		case VAR_providerSegmentationId:
			return DISPLAY_NAME_providerSegmentationId;
		case VAR_qosPolicyId:
			return DISPLAY_NAME_qosPolicyId;
		case VAR_revisionNumber:
			return DISPLAY_NAME_revisionNumber;
		case VAR_status:
			return DISPLAY_NAME_status;
		case VAR_subnetIds:
			return DISPLAY_NAME_subnetIds;
		case VAR_tags:
			return DISPLAY_NAME_tags;
		case VAR_tenantId:
			return DISPLAY_NAME_tenantId;
		case VAR_updatedAt:
			return DISPLAY_NAME_updatedAt;
		case VAR_isAdminStateUp:
			return DISPLAY_NAME_isAdminStateUp;
		case VAR_isDefault:
			return DISPLAY_NAME_isDefault;
		case VAR_isPortSecurityEnabled:
			return DISPLAY_NAME_isPortSecurityEnabled;
		case VAR_isRouterExternal:
			return DISPLAY_NAME_isRouterExternal;
		case VAR_isShared:
			return DISPLAY_NAME_isShared;
		case VAR_isVlanQueing:
			return DISPLAY_NAME_isVlanQueing;
		case VAR_isVlanTransparent:
			return DISPLAY_NAME_isVlanTransparent;
		case VAR_l2Adjacency:
			return DISPLAY_NAME_l2Adjacency;
		case VAR_locationCloud:
			return DISPLAY_NAME_locationCloud;
		case VAR_locationProjectDomainId:
			return DISPLAY_NAME_locationProjectDomainId;
		case VAR_locationProjectDomainName:
			return DISPLAY_NAME_locationProjectDomainName;
		case VAR_locationProjectId:
			return DISPLAY_NAME_locationProjectId;
		case VAR_locationProjectName:
			return DISPLAY_NAME_locationProjectName;
		case VAR_locationRegionName:
			return DISPLAY_NAME_locationRegionName;
		case VAR_locationZone:
			return DISPLAY_NAME_locationZone;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionBareMetalNetwork(String var) {
		switch(var) {
		case VAR_id:
			return "The ID of this bare metal network";
		case VAR_name:
			return "The name of this bare metal network";
		case VAR_description:
			return "The description of this bare metal network";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameBareMetalNetwork(String var) {
		switch(var) {
		case VAR_id:
			return "String";
		case VAR_name:
			return "String";
		case VAR_description:
			return "String";
		case VAR_availabilityZoneHints:
			return "JsonArray";
		case VAR_availabilityZones:
			return "List";
		case VAR_createdAt:
			return "ZonedDateTime";
		case VAR_dnsDomain:
			return "String";
		case VAR_mtu:
			return "Integer";
		case VAR_projectId:
			return "String";
		case VAR_providerNetworkType:
			return "String";
		case VAR_providerPhysicalNetwork:
			return "String";
		case VAR_providerSegmentationId:
			return "String";
		case VAR_qosPolicyId:
			return "String";
		case VAR_revisionNumber:
			return "Integer";
		case VAR_status:
			return "String";
		case VAR_subnetIds:
			return "List";
		case VAR_tags:
			return "List";
		case VAR_tenantId:
			return "String";
		case VAR_updatedAt:
			return "ZonedDateTime";
		case VAR_isAdminStateUp:
			return "Boolean";
		case VAR_isDefault:
			return "Boolean";
		case VAR_isPortSecurityEnabled:
			return "Boolean";
		case VAR_isRouterExternal:
			return "Boolean";
		case VAR_isShared:
			return "Boolean";
		case VAR_isVlanQueing:
			return "Boolean";
		case VAR_isVlanTransparent:
			return "Boolean";
		case VAR_l2Adjacency:
			return "Boolean";
		case VAR_locationCloud:
			return "String";
		case VAR_locationProjectDomainId:
			return "String";
		case VAR_locationProjectDomainName:
			return "String";
		case VAR_locationProjectId:
			return "String";
		case VAR_locationProjectName:
			return "String";
		case VAR_locationRegionName:
			return "String";
		case VAR_locationZone:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnBareMetalNetwork(String var) {
		switch(var) {
		case VAR_name:
			return 0;
		case VAR_providerSegmentationId:
			return 1;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowBareMetalNetwork(String var) {
		switch(var) {
		case VAR_id:
			return 3;
		case VAR_name:
			return 3;
		case VAR_description:
			return 3;
		case VAR_availabilityZoneHints:
			return 3;
		case VAR_availabilityZones:
			return 3;
		case VAR_createdAt:
			return 3;
		case VAR_dnsDomain:
			return 3;
		case VAR_mtu:
			return 3;
		case VAR_projectId:
			return 3;
		case VAR_providerNetworkType:
			return 3;
		case VAR_providerPhysicalNetwork:
			return 3;
		case VAR_providerSegmentationId:
			return 3;
		case VAR_qosPolicyId:
			return 3;
		case VAR_revisionNumber:
			return 3;
		case VAR_status:
			return 3;
		case VAR_subnetIds:
			return 3;
		case VAR_tags:
			return 3;
		case VAR_tenantId:
			return 3;
		case VAR_updatedAt:
			return 3;
		case VAR_isAdminStateUp:
			return 4;
		case VAR_isDefault:
			return 4;
		case VAR_isPortSecurityEnabled:
			return 4;
		case VAR_isRouterExternal:
			return 4;
		case VAR_isShared:
			return 4;
		case VAR_isVlanQueing:
			return 4;
		case VAR_isVlanTransparent:
			return 4;
		case VAR_l2Adjacency:
			return 4;
		case VAR_locationCloud:
			return 5;
		case VAR_locationProjectDomainId:
			return 5;
		case VAR_locationProjectDomainName:
			return 5;
		case VAR_locationProjectId:
			return 5;
		case VAR_locationProjectName:
			return 5;
		case VAR_locationRegionName:
			return 5;
		case VAR_locationZone:
			return 5;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellBareMetalNetwork(String var) {
		switch(var) {
		case VAR_id:
			return 0;
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
		case VAR_availabilityZoneHints:
			return 3;
		case VAR_availabilityZones:
			return 4;
		case VAR_createdAt:
			return 5;
		case VAR_dnsDomain:
			return 6;
		case VAR_mtu:
			return 7;
		case VAR_projectId:
			return 8;
		case VAR_providerNetworkType:
			return 9;
		case VAR_providerPhysicalNetwork:
			return 10;
		case VAR_providerSegmentationId:
			return 11;
		case VAR_qosPolicyId:
			return 12;
		case VAR_revisionNumber:
			return 13;
		case VAR_status:
			return 14;
		case VAR_subnetIds:
			return 15;
		case VAR_tags:
			return 16;
		case VAR_tenantId:
			return 17;
		case VAR_updatedAt:
			return 18;
		case VAR_isAdminStateUp:
			return 0;
		case VAR_isDefault:
			return 1;
		case VAR_isPortSecurityEnabled:
			return 2;
		case VAR_isRouterExternal:
			return 5;
		case VAR_isShared:
			return 6;
		case VAR_isVlanQueing:
			return 7;
		case VAR_isVlanTransparent:
			return 8;
		case VAR_l2Adjacency:
			return 9;
		case VAR_locationCloud:
			return 0;
		case VAR_locationProjectDomainId:
			return 1;
		case VAR_locationProjectDomainName:
			return 2;
		case VAR_locationProjectId:
			return 3;
		case VAR_locationProjectName:
			return 4;
		case VAR_locationRegionName:
			return 5;
		case VAR_locationZone:
			return 6;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinBareMetalNetwork(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxBareMetalNetwork(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxBareMetalNetwork(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minBareMetalNetwork(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
