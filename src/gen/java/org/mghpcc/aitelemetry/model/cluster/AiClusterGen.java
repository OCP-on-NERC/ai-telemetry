package org.mghpcc.aitelemetry.model.cluster;

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
import io.vertx.pgclient.data.Point;
import org.computate.vertx.serialize.pgclient.PgClientPointSerializer;
import org.computate.vertx.serialize.pgclient.PgClientPointDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.BeanDescription;
import java.util.stream.Collectors;
import io.vertx.core.json.Json;
import org.computate.vertx.serialize.vertx.JsonObjectDeserializer;
import java.lang.Integer;
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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class AiClusterGen into the class AiCluster. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the AiCluster API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the AiCluster class and it's generated class AiClusterGen&lt;BaseModel&gt;: </h3>extends AiClusterGen
 * <p>
 * This Java class extends a generated Java class AiClusterGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster">Find the class AiCluster in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends AiClusterGen<BaseModel>
 * <p>This <code>class AiCluster extends AiClusterGen&lt;BaseModel&gt;</code>, which means it extends a newly generated AiClusterGen. 
 * The generated <code>class AiClusterGen extends BaseModel</code> which means that AiCluster extends AiClusterGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: AI clusters"</b>, which groups all of the OpenAPIs for AiCluster objects under the tag "AI clusters". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/ai-cluster</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/ai-cluster"</b>, which defines the base API URI for AiCluster objects as "/en-us/api/ai-cluster" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the AiCluster class will inherit the helpful inherited class comments from the super class AiClusterGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 3</h2>
 * <p>This class contains a comment <b>"Order: 3"</b>, which means this class will be sorted by the given number 3 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 3</h2>
 * <p>This class contains a comment <b>"SqlOrder: 3"</b>, which means this class will be sorted by the given number 3 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.cluster.AiClusterPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.cluster.AiClusterPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the AiCluster Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an AI cluster</h2>
 * <p>This class contains a comment <b>"AName.enUS: an AI cluster"</b>, which identifies the language context to describe a AiCluster as "an AI cluster". 
 * </p>
 * <p>
 * Delete the class AiCluster in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.cluster in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class AiClusterGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(AiCluster.class);

	public static final String Description_enUS = "A Red Hat OpenShift cluster containing GPUs";
	public static final String AName_enUS = "an AI cluster";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this AI cluster";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "theAI cluster";
	public static final String SingularName_enUS = "AI cluster";
	public static final String PluralName_enUS = "AI clusters";
	public static final String NameActual_enUS = "current AI cluster";
	public static final String AllName_enUS = "all AI clusters";
	public static final String SearchAllNameBy_enUS = "search AI clusters by ";
	public static final String Title_enUS = "AI clusters";
	public static final String ThePluralName_enUS = "the AI clusters";
	public static final String NoNameFound_enUS = "no AI cluster found";
	public static final String ApiUri_enUS = "/en-us/api/ai-cluster";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/ai-cluster";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/ai-cluster/{clusterName}";
	public static final String OfName_enUS = "of AI cluster";
	public static final String ANameAdjective_enUS = "an AI cluster";
	public static final String NameAdjectiveSingular_enUS = "AI cluster";
	public static final String NameAdjectivePlural_enUS = "AI clusters";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/ai-cluster";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/ai-cluster";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/ai-cluster";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/ai-cluster/{clusterName}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/ai-cluster/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/ai-cluster/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/ai-cluster";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/ai-cluster";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/ai-cluster";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/ai-cluster";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/ai-cluster";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/ai-cluster";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/ai-cluster/{clusterName}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/ai-cluster/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/ai-cluster/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/ai-cluster-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/ai-cluster-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/ai-cluster-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/ai-cluster";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/ai-cluster";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/ai-cluster";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/ai-cluster/{clusterName}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/ai-cluster/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/ai-cluster/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/ai-cluster/{clusterName}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/ai-cluster/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/ai-cluster/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/ai-cluster";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/ai-cluster";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/ai-cluster";

	public static final String Icon = "<i class=\"fa-regular fa-server\"></i>";

	/////////////////
	// clusterName //
	/////////////////


	/**	 The entity clusterName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String clusterName;

	/**	<br> The entity clusterName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:clusterName">Find the entity clusterName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterName(Wrap<String> w);

	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String o) {
		this.clusterName = AiCluster.staticSetClusterName(siteRequest_, o);
	}
	public static String staticSetClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiCluster clusterNameInit() {
		Wrap<String> clusterNameWrap = new Wrap<String>().var("clusterName");
		if(clusterName == null) {
			_clusterName(clusterNameWrap);
			Optional.ofNullable(clusterNameWrap.getO()).ifPresent(o -> {
				setClusterName(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterName(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchClusterName(siteRequest_, AiCluster.staticSetClusterName(siteRequest_, o)).toString();
	}

	public String sqlClusterName() {
		return clusterName;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = AiCluster.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiCluster descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchDescription(siteRequest_, AiCluster.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	////////////////////
	// locationColors //
	////////////////////


	/**	 The entity locationColors
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> locationColors = new ArrayList<String>();

	/**	<br> The entity locationColors
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:locationColors">Find the entity locationColors in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _locationColors(List<String> l);

	public List<String> getLocationColors() {
		return locationColors;
	}

	public void setLocationColors(List<String> locationColors) {
		this.locationColors = locationColors;
	}
	@JsonIgnore
	public void setLocationColors(String o) {
		String l = AiCluster.staticSetLocationColors(siteRequest_, o);
		if(l != null)
			addLocationColors(l);
	}
	public static String staticSetLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}
	public AiCluster addLocationColors(String...objects) {
		for(String o : objects) {
			addLocationColors(o);
		}
		return (AiCluster)this;
	}
	public AiCluster addLocationColors(String o) {
		if(o != null)
			this.locationColors.add(o);
		return (AiCluster)this;
	}
	@JsonIgnore
	public void setLocationColors(JsonArray objects) {
		locationColors.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addLocationColors(o);
		}
	}
	protected AiCluster locationColorsInit() {
		_locationColors(locationColors);
		return (AiCluster)this;
	}

	public static String staticSearchLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationColors(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationColors(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchLocationColors(siteRequest_, AiCluster.staticSetLocationColors(siteRequest_, o)).toString();
	}

	////////////////////
	// locationTitles //
	////////////////////


	/**	 The entity locationTitles
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> locationTitles = new ArrayList<String>();

	/**	<br> The entity locationTitles
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:locationTitles">Find the entity locationTitles in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _locationTitles(List<String> l);

	public List<String> getLocationTitles() {
		return locationTitles;
	}

	public void setLocationTitles(List<String> locationTitles) {
		this.locationTitles = locationTitles;
	}
	@JsonIgnore
	public void setLocationTitles(String o) {
		String l = AiCluster.staticSetLocationTitles(siteRequest_, o);
		if(l != null)
			addLocationTitles(l);
	}
	public static String staticSetLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}
	public AiCluster addLocationTitles(String...objects) {
		for(String o : objects) {
			addLocationTitles(o);
		}
		return (AiCluster)this;
	}
	public AiCluster addLocationTitles(String o) {
		if(o != null)
			this.locationTitles.add(o);
		return (AiCluster)this;
	}
	@JsonIgnore
	public void setLocationTitles(JsonArray objects) {
		locationTitles.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addLocationTitles(o);
		}
	}
	protected AiCluster locationTitlesInit() {
		_locationTitles(locationTitles);
		return (AiCluster)this;
	}

	public static String staticSearchLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationTitles(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationTitles(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchLocationTitles(siteRequest_, AiCluster.staticSetLocationTitles(siteRequest_, o)).toString();
	}

	///////////////////
	// locationLinks //
	///////////////////


	/**	 The entity locationLinks
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> locationLinks = new ArrayList<String>();

	/**	<br> The entity locationLinks
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:locationLinks">Find the entity locationLinks in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _locationLinks(List<String> l);

	public List<String> getLocationLinks() {
		return locationLinks;
	}

	public void setLocationLinks(List<String> locationLinks) {
		this.locationLinks = locationLinks;
	}
	@JsonIgnore
	public void setLocationLinks(String o) {
		String l = AiCluster.staticSetLocationLinks(siteRequest_, o);
		if(l != null)
			addLocationLinks(l);
	}
	public static String staticSetLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}
	public AiCluster addLocationLinks(String...objects) {
		for(String o : objects) {
			addLocationLinks(o);
		}
		return (AiCluster)this;
	}
	public AiCluster addLocationLinks(String o) {
		if(o != null)
			this.locationLinks.add(o);
		return (AiCluster)this;
	}
	@JsonIgnore
	public void setLocationLinks(JsonArray objects) {
		locationLinks.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addLocationLinks(o);
		}
	}
	protected AiCluster locationLinksInit() {
		_locationLinks(locationLinks);
		return (AiCluster)this;
	}

	public static String staticSearchLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationLinks(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationLinks(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchLocationLinks(siteRequest_, AiCluster.staticSetLocationLinks(siteRequest_, o)).toString();
	}

	//////////////
	// location //
	//////////////


	/**	 The entity location
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = PgClientPointDeserializer.class)
	@JsonSerialize(using = PgClientPointSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Point location;

	/**	<br> The entity location
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:location">Find the entity location in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _location(Wrap<Point> w);

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	@JsonIgnore
	public void setLocation(String o) {
		this.location = AiCluster.staticSetLocation(siteRequest_, o);
	}
	public static Point staticSetLocation(SiteRequest siteRequest_, String o) {
		if(o != null) {
			try {
				Point shape = null;
				if(StringUtils.isNotBlank(o)) {
					ObjectMapper objectMapper = new ObjectMapper();
					SimpleModule module = new SimpleModule();
					module.setDeserializerModifier(new BeanDeserializerModifier() {
						@Override
						public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
							if (beanDesc.getBeanClass() == Point.class) {
								return new PgClientPointDeserializer();
							}
							return deserializer;
						}
					});
					objectMapper.registerModule(module);
					shape = objectMapper.readValue(Json.encode(o), Point.class);
				}
				return shape;
			} catch(Exception ex) {
				ExceptionUtils.rethrow(ex);
			}
		}
		return null;
	}
	@JsonIgnore
	public void setLocation(JsonObject o) {
		this.location = AiCluster.staticSetLocation(siteRequest_, o);
	}
	public static Point staticSetLocation(SiteRequest siteRequest_, JsonObject o) {
		if(o != null) {
			try {
				Point shape = new Point();
				JsonArray coordinates = o.getJsonArray("coordinates");
				shape.setX(coordinates.getDouble(0));
				shape.setY(coordinates.getDouble(1));
				return shape;
			} catch(Exception ex) {
				ExceptionUtils.rethrow(ex);
			}
		}
		return null;
	}
	protected AiCluster locationInit() {
		Wrap<Point> locationWrap = new Wrap<Point>().var("location");
		if(location == null) {
			_location(locationWrap);
			Optional.ofNullable(locationWrap.getO()).ifPresent(o -> {
				setLocation(o);
			});
		}
		return (AiCluster)this;
	}

	public static Point staticSearchLocation(SiteRequest siteRequest_, Point o) {
		return o;
	}

	public static String staticSearchStrLocation(SiteRequest siteRequest_, Point o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocation(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchLocation(siteRequest_, AiCluster.staticSetLocation(siteRequest_, o)).toString();
	}

	public Point sqlLocation() {
		return location;
	}

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = AiCluster.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiCluster idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchId(siteRequest_, AiCluster.staticSetId(siteRequest_, o)).toString();
	}

	public String sqlId() {
		return id;
	}

	///////////////////
	// entityShortId //
	///////////////////


	/**	 The entity entityShortId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String entityShortId;

	/**	<br> The entity entityShortId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:entityShortId">Find the entity entityShortId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityShortId(Wrap<String> w);

	public String getEntityShortId() {
		return entityShortId;
	}
	public void setEntityShortId(String o) {
		this.entityShortId = AiCluster.staticSetEntityShortId(siteRequest_, o);
	}
	public static String staticSetEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiCluster entityShortIdInit() {
		Wrap<String> entityShortIdWrap = new Wrap<String>().var("entityShortId");
		if(entityShortId == null) {
			_entityShortId(entityShortIdWrap);
			Optional.ofNullable(entityShortIdWrap.getO()).ifPresent(o -> {
				setEntityShortId(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityShortId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityShortId(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchEntityShortId(siteRequest_, AiCluster.staticSetEntityShortId(siteRequest_, o)).toString();
	}

	//////////////////
	// ngsildTenant //
	//////////////////


	/**	 The entity ngsildTenant
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String ngsildTenant;

	/**	<br> The entity ngsildTenant
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:ngsildTenant">Find the entity ngsildTenant in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildTenant(Wrap<String> w);

	public String getNgsildTenant() {
		return ngsildTenant;
	}
	public void setNgsildTenant(String o) {
		this.ngsildTenant = AiCluster.staticSetNgsildTenant(siteRequest_, o);
	}
	public static String staticSetNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiCluster ngsildTenantInit() {
		Wrap<String> ngsildTenantWrap = new Wrap<String>().var("ngsildTenant");
		if(ngsildTenant == null) {
			_ngsildTenant(ngsildTenantWrap);
			Optional.ofNullable(ngsildTenantWrap.getO()).ifPresent(o -> {
				setNgsildTenant(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildTenant(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildTenant(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchNgsildTenant(siteRequest_, AiCluster.staticSetNgsildTenant(siteRequest_, o)).toString();
	}

	public String sqlNgsildTenant() {
		return ngsildTenant;
	}

	////////////////
	// ngsildPath //
	////////////////


	/**	 The entity ngsildPath
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String ngsildPath;

	/**	<br> The entity ngsildPath
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:ngsildPath">Find the entity ngsildPath in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildPath(Wrap<String> w);

	public String getNgsildPath() {
		return ngsildPath;
	}
	public void setNgsildPath(String o) {
		this.ngsildPath = AiCluster.staticSetNgsildPath(siteRequest_, o);
	}
	public static String staticSetNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiCluster ngsildPathInit() {
		Wrap<String> ngsildPathWrap = new Wrap<String>().var("ngsildPath");
		if(ngsildPath == null) {
			_ngsildPath(ngsildPathWrap);
			Optional.ofNullable(ngsildPathWrap.getO()).ifPresent(o -> {
				setNgsildPath(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildPath(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildPath(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchNgsildPath(siteRequest_, AiCluster.staticSetNgsildPath(siteRequest_, o)).toString();
	}

	public String sqlNgsildPath() {
		return ngsildPath;
	}

	///////////////////
	// ngsildContext //
	///////////////////


	/**	 The entity ngsildContext
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String ngsildContext;

	/**	<br> The entity ngsildContext
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:ngsildContext">Find the entity ngsildContext in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildContext(Wrap<String> w);

	public String getNgsildContext() {
		return ngsildContext;
	}
	public void setNgsildContext(String o) {
		this.ngsildContext = AiCluster.staticSetNgsildContext(siteRequest_, o);
	}
	public static String staticSetNgsildContext(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiCluster ngsildContextInit() {
		Wrap<String> ngsildContextWrap = new Wrap<String>().var("ngsildContext");
		if(ngsildContext == null) {
			_ngsildContext(ngsildContextWrap);
			Optional.ofNullable(ngsildContextWrap.getO()).ifPresent(o -> {
				setNgsildContext(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchNgsildContext(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildContext(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildContext(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchNgsildContext(siteRequest_, AiCluster.staticSetNgsildContext(siteRequest_, o)).toString();
	}

	public String sqlNgsildContext() {
		return ngsildContext;
	}

	////////////////
	// ngsildData //
	////////////////


	/**	 The entity ngsildData
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject ngsildData;

	/**	<br> The entity ngsildData
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:ngsildData">Find the entity ngsildData in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildData(Wrap<JsonObject> w);

	public JsonObject getNgsildData() {
		return ngsildData;
	}

	public void setNgsildData(JsonObject ngsildData) {
		this.ngsildData = ngsildData;
	}
	@JsonIgnore
	public void setNgsildData(String o) {
		this.ngsildData = AiCluster.staticSetNgsildData(siteRequest_, o);
	}
	public static JsonObject staticSetNgsildData(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected AiCluster ngsildDataInit() {
		Wrap<JsonObject> ngsildDataWrap = new Wrap<JsonObject>().var("ngsildData");
		if(ngsildData == null) {
			_ngsildData(ngsildDataWrap);
			Optional.ofNullable(ngsildDataWrap.getO()).ifPresent(o -> {
				setNgsildData(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchNgsildData(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrNgsildData(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildData(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchNgsildData(siteRequest_, AiCluster.staticSetNgsildData(siteRequest_, o)).toString();
	}

	public JsonObject sqlNgsildData() {
		return ngsildData;
	}

	//////////////////
	// aiNodesTotal //
	//////////////////


	/**	 The entity aiNodesTotal
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer aiNodesTotal;

	/**	<br> The entity aiNodesTotal
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:aiNodesTotal">Find the entity aiNodesTotal in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _aiNodesTotal(Wrap<Integer> w);

	public Integer getAiNodesTotal() {
		return aiNodesTotal;
	}

	public void setAiNodesTotal(Integer aiNodesTotal) {
		this.aiNodesTotal = aiNodesTotal;
	}
	@JsonIgnore
	public void setAiNodesTotal(String o) {
		this.aiNodesTotal = AiCluster.staticSetAiNodesTotal(siteRequest_, o);
	}
	public static Integer staticSetAiNodesTotal(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected AiCluster aiNodesTotalInit() {
		Wrap<Integer> aiNodesTotalWrap = new Wrap<Integer>().var("aiNodesTotal");
		if(aiNodesTotal == null) {
			_aiNodesTotal(aiNodesTotalWrap);
			Optional.ofNullable(aiNodesTotalWrap.getO()).ifPresent(o -> {
				setAiNodesTotal(o);
			});
		}
		return (AiCluster)this;
	}

	public static Integer staticSearchAiNodesTotal(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrAiNodesTotal(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAiNodesTotal(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchAiNodesTotal(siteRequest_, AiCluster.staticSetAiNodesTotal(siteRequest_, o)).toString();
	}

	public Integer sqlAiNodesTotal() {
		return aiNodesTotal;
	}

	/////////////////////
	// gpuDevicesTotal //
	/////////////////////


	/**	 The entity gpuDevicesTotal
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer gpuDevicesTotal;

	/**	<br> The entity gpuDevicesTotal
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:gpuDevicesTotal">Find the entity gpuDevicesTotal in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuDevicesTotal(Wrap<Integer> w);

	public Integer getGpuDevicesTotal() {
		return gpuDevicesTotal;
	}

	public void setGpuDevicesTotal(Integer gpuDevicesTotal) {
		this.gpuDevicesTotal = gpuDevicesTotal;
	}
	@JsonIgnore
	public void setGpuDevicesTotal(String o) {
		this.gpuDevicesTotal = AiCluster.staticSetGpuDevicesTotal(siteRequest_, o);
	}
	public static Integer staticSetGpuDevicesTotal(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected AiCluster gpuDevicesTotalInit() {
		Wrap<Integer> gpuDevicesTotalWrap = new Wrap<Integer>().var("gpuDevicesTotal");
		if(gpuDevicesTotal == null) {
			_gpuDevicesTotal(gpuDevicesTotalWrap);
			Optional.ofNullable(gpuDevicesTotalWrap.getO()).ifPresent(o -> {
				setGpuDevicesTotal(o);
			});
		}
		return (AiCluster)this;
	}

	public static Integer staticSearchGpuDevicesTotal(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrGpuDevicesTotal(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuDevicesTotal(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchGpuDevicesTotal(siteRequest_, AiCluster.staticSetGpuDevicesTotal(siteRequest_, o)).toString();
	}

	public Integer sqlGpuDevicesTotal() {
		return gpuDevicesTotal;
	}

	////////////////
	// grafanaUrl //
	////////////////


	/**	 The entity grafanaUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String grafanaUrl;

	/**	<br> The entity grafanaUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiCluster&fq=entiteVar_enUS_indexed_string:grafanaUrl">Find the entity grafanaUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _grafanaUrl(Wrap<String> w);

	public String getGrafanaUrl() {
		return grafanaUrl;
	}
	public void setGrafanaUrl(String o) {
		this.grafanaUrl = AiCluster.staticSetGrafanaUrl(siteRequest_, o);
	}
	public static String staticSetGrafanaUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiCluster grafanaUrlInit() {
		Wrap<String> grafanaUrlWrap = new Wrap<String>().var("grafanaUrl");
		if(grafanaUrl == null) {
			_grafanaUrl(grafanaUrlWrap);
			Optional.ofNullable(grafanaUrlWrap.getO()).ifPresent(o -> {
				setGrafanaUrl(o);
			});
		}
		return (AiCluster)this;
	}

	public static String staticSearchGrafanaUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrGrafanaUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGrafanaUrl(SiteRequest siteRequest_, String o) {
		return AiCluster.staticSearchGrafanaUrl(siteRequest_, AiCluster.staticSetGrafanaUrl(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<AiClusterGen<DEV>> promiseDeepAiCluster(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepAiCluster();
	}

	public Future<AiClusterGen<DEV>> promiseDeepAiCluster() {
		Promise<AiClusterGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseAiCluster(promise2);
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

	public Future<Void> promiseAiCluster(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				clusterNameInit();
				descriptionInit();
				locationColorsInit();
				locationTitlesInit();
				locationLinksInit();
				locationInit();
				idInit();
				entityShortIdInit();
				ngsildTenantInit();
				ngsildPathInit();
				ngsildContextInit();
				ngsildDataInit();
				aiNodesTotalInit();
				gpuDevicesTotalInit();
				grafanaUrlInit();
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

	@Override public Future<? extends AiClusterGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepAiCluster(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAiCluster(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestAiCluster(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAiCluster(v);
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
	public Object obtainAiCluster(String var) {
		AiCluster oAiCluster = (AiCluster)this;
		switch(var) {
			case "clusterName":
				return oAiCluster.clusterName;
			case "description":
				return oAiCluster.description;
			case "locationColors":
				return oAiCluster.locationColors;
			case "locationTitles":
				return oAiCluster.locationTitles;
			case "locationLinks":
				return oAiCluster.locationLinks;
			case "location":
				return oAiCluster.location;
			case "id":
				return oAiCluster.id;
			case "entityShortId":
				return oAiCluster.entityShortId;
			case "ngsildTenant":
				return oAiCluster.ngsildTenant;
			case "ngsildPath":
				return oAiCluster.ngsildPath;
			case "ngsildContext":
				return oAiCluster.ngsildContext;
			case "ngsildData":
				return oAiCluster.ngsildData;
			case "aiNodesTotal":
				return oAiCluster.aiNodesTotal;
			case "gpuDevicesTotal":
				return oAiCluster.gpuDevicesTotal;
			case "grafanaUrl":
				return oAiCluster.grafanaUrl;
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
				o = relateAiCluster(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateAiCluster(String var, Object val) {
		AiCluster oAiCluster = (AiCluster)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetAiCluster(entityVar,  siteRequest_, o);
	}
	public static Object staticSetAiCluster(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "clusterName":
			return AiCluster.staticSetClusterName(siteRequest_, o);
		case "description":
			return AiCluster.staticSetDescription(siteRequest_, o);
		case "locationColors":
			return AiCluster.staticSetLocationColors(siteRequest_, o);
		case "locationTitles":
			return AiCluster.staticSetLocationTitles(siteRequest_, o);
		case "locationLinks":
			return AiCluster.staticSetLocationLinks(siteRequest_, o);
		case "location":
			return AiCluster.staticSetLocation(siteRequest_, o);
		case "id":
			return AiCluster.staticSetId(siteRequest_, o);
		case "entityShortId":
			return AiCluster.staticSetEntityShortId(siteRequest_, o);
		case "ngsildTenant":
			return AiCluster.staticSetNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return AiCluster.staticSetNgsildPath(siteRequest_, o);
		case "ngsildContext":
			return AiCluster.staticSetNgsildContext(siteRequest_, o);
		case "ngsildData":
			return AiCluster.staticSetNgsildData(siteRequest_, o);
		case "aiNodesTotal":
			return AiCluster.staticSetAiNodesTotal(siteRequest_, o);
		case "gpuDevicesTotal":
			return AiCluster.staticSetGpuDevicesTotal(siteRequest_, o);
		case "grafanaUrl":
			return AiCluster.staticSetGrafanaUrl(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchAiCluster(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchAiCluster(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "clusterName":
			return AiCluster.staticSearchClusterName(siteRequest_, (String)o);
		case "description":
			return AiCluster.staticSearchDescription(siteRequest_, (String)o);
		case "locationColors":
			return AiCluster.staticSearchLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return AiCluster.staticSearchLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return AiCluster.staticSearchLocationLinks(siteRequest_, (String)o);
		case "location":
			return AiCluster.staticSearchLocation(siteRequest_, (Point)o);
		case "id":
			return AiCluster.staticSearchId(siteRequest_, (String)o);
		case "entityShortId":
			return AiCluster.staticSearchEntityShortId(siteRequest_, (String)o);
		case "ngsildTenant":
			return AiCluster.staticSearchNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return AiCluster.staticSearchNgsildPath(siteRequest_, (String)o);
		case "ngsildContext":
			return AiCluster.staticSearchNgsildContext(siteRequest_, (String)o);
		case "ngsildData":
			return AiCluster.staticSearchNgsildData(siteRequest_, (JsonObject)o);
		case "aiNodesTotal":
			return AiCluster.staticSearchAiNodesTotal(siteRequest_, (Integer)o);
		case "gpuDevicesTotal":
			return AiCluster.staticSearchGpuDevicesTotal(siteRequest_, (Integer)o);
		case "grafanaUrl":
			return AiCluster.staticSearchGrafanaUrl(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrAiCluster(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrAiCluster(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "clusterName":
			return AiCluster.staticSearchStrClusterName(siteRequest_, (String)o);
		case "description":
			return AiCluster.staticSearchStrDescription(siteRequest_, (String)o);
		case "locationColors":
			return AiCluster.staticSearchStrLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return AiCluster.staticSearchStrLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return AiCluster.staticSearchStrLocationLinks(siteRequest_, (String)o);
		case "location":
			return AiCluster.staticSearchStrLocation(siteRequest_, (Point)o);
		case "id":
			return AiCluster.staticSearchStrId(siteRequest_, (String)o);
		case "entityShortId":
			return AiCluster.staticSearchStrEntityShortId(siteRequest_, (String)o);
		case "ngsildTenant":
			return AiCluster.staticSearchStrNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return AiCluster.staticSearchStrNgsildPath(siteRequest_, (String)o);
		case "ngsildContext":
			return AiCluster.staticSearchStrNgsildContext(siteRequest_, (String)o);
		case "ngsildData":
			return AiCluster.staticSearchStrNgsildData(siteRequest_, (String)o);
		case "aiNodesTotal":
			return AiCluster.staticSearchStrAiNodesTotal(siteRequest_, (Integer)o);
		case "gpuDevicesTotal":
			return AiCluster.staticSearchStrGpuDevicesTotal(siteRequest_, (Integer)o);
		case "grafanaUrl":
			return AiCluster.staticSearchStrGrafanaUrl(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqAiCluster(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqAiCluster(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "clusterName":
			return AiCluster.staticSearchFqClusterName(siteRequest_, o);
		case "description":
			return AiCluster.staticSearchFqDescription(siteRequest_, o);
		case "locationColors":
			return AiCluster.staticSearchFqLocationColors(siteRequest_, o);
		case "locationTitles":
			return AiCluster.staticSearchFqLocationTitles(siteRequest_, o);
		case "locationLinks":
			return AiCluster.staticSearchFqLocationLinks(siteRequest_, o);
		case "location":
			return AiCluster.staticSearchFqLocation(siteRequest_, o);
		case "id":
			return AiCluster.staticSearchFqId(siteRequest_, o);
		case "entityShortId":
			return AiCluster.staticSearchFqEntityShortId(siteRequest_, o);
		case "ngsildTenant":
			return AiCluster.staticSearchFqNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return AiCluster.staticSearchFqNgsildPath(siteRequest_, o);
		case "ngsildContext":
			return AiCluster.staticSearchFqNgsildContext(siteRequest_, o);
		case "ngsildData":
			return AiCluster.staticSearchFqNgsildData(siteRequest_, o);
		case "aiNodesTotal":
			return AiCluster.staticSearchFqAiNodesTotal(siteRequest_, o);
		case "gpuDevicesTotal":
			return AiCluster.staticSearchFqGpuDevicesTotal(siteRequest_, o);
		case "grafanaUrl":
			return AiCluster.staticSearchFqGrafanaUrl(siteRequest_, o);
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
					o = persistAiCluster(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistAiCluster(String var, Object val) {
		String varLower = var.toLowerCase();
			if("clustername".equals(varLower)) {
				if(val instanceof String) {
					setClusterName((String)val);
				}
				saves.add("clusterName");
				return val;
			} else if("description".equals(varLower)) {
				if(val instanceof String) {
					setDescription((String)val);
				}
				saves.add("description");
				return val;
			} else if("location".equals(varLower)) {
				if(val instanceof String) {
					setLocation((String)val);
				} else if(val instanceof Point) {
					setLocation((Point)val);
				}
				saves.add("location");
				return val;
			} else if("id".equals(varLower)) {
				if(val instanceof String) {
					setId((String)val);
				}
				saves.add("id");
				return val;
			} else if("ngsildtenant".equals(varLower)) {
				if(val instanceof String) {
					setNgsildTenant((String)val);
				}
				saves.add("ngsildTenant");
				return val;
			} else if("ngsildpath".equals(varLower)) {
				if(val instanceof String) {
					setNgsildPath((String)val);
				}
				saves.add("ngsildPath");
				return val;
			} else if("ngsildcontext".equals(varLower)) {
				if(val instanceof String) {
					setNgsildContext((String)val);
				}
				saves.add("ngsildContext");
				return val;
			} else if("ngsilddata".equals(varLower)) {
				if(val instanceof String) {
					setNgsildData((String)val);
				} else if(val instanceof JsonObject) {
					setNgsildData((JsonObject)val);
				}
				saves.add("ngsildData");
				return val;
			} else if("ainodestotal".equals(varLower)) {
				if(val instanceof Integer) {
					setAiNodesTotal((Integer)val);
				} else {
					setAiNodesTotal(val == null ? null : val.toString());
				}
				saves.add("aiNodesTotal");
				return val;
			} else if("gpudevicestotal".equals(varLower)) {
				if(val instanceof Integer) {
					setGpuDevicesTotal((Integer)val);
				} else {
					setGpuDevicesTotal(val == null ? null : val.toString());
				}
				saves.add("gpuDevicesTotal");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateAiCluster(doc);
	}
	public void populateAiCluster(SolrResponse.Doc doc) {
		AiCluster oAiCluster = (AiCluster)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("clusterName")) {
				String clusterName = (String)doc.get("clusterName_docvalues_string");
				if(clusterName != null)
					oAiCluster.setClusterName(clusterName);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oAiCluster.setDescription(description);
			}

			if(saves.contains("locationColors")) {
				List<String> locationColors = (List<String>)doc.get("locationColors_indexedstored_strings");
				if(locationColors != null) {
					locationColors.stream().forEach( v -> {
						oAiCluster.locationColors.add(v);
					});
				}
			}

			if(saves.contains("locationTitles")) {
				List<String> locationTitles = (List<String>)doc.get("locationTitles_indexedstored_strings");
				if(locationTitles != null) {
					locationTitles.stream().forEach( v -> {
						oAiCluster.locationTitles.add(v);
					});
				}
			}

			if(saves.contains("locationLinks")) {
				List<String> locationLinks = (List<String>)doc.get("locationLinks_indexedstored_strings");
				if(locationLinks != null) {
					locationLinks.stream().forEach( v -> {
						oAiCluster.locationLinks.add(v);
					});
				}
			}

			if(saves.contains("location")) {
				Point location = (Point)doc.get("location_docvalues_location");
				if(location != null)
					oAiCluster.setLocation(location);
			}

			if(saves.contains("id")) {
				String id = (String)doc.get("id_docvalues_string");
				if(id != null)
					oAiCluster.setId(id);
			}

			if(saves.contains("entityShortId")) {
				String entityShortId = (String)doc.get("entityShortId_docvalues_string");
				if(entityShortId != null)
					oAiCluster.setEntityShortId(entityShortId);
			}

			if(saves.contains("ngsildTenant")) {
				String ngsildTenant = (String)doc.get("ngsildTenant_docvalues_string");
				if(ngsildTenant != null)
					oAiCluster.setNgsildTenant(ngsildTenant);
			}

			if(saves.contains("ngsildPath")) {
				String ngsildPath = (String)doc.get("ngsildPath_docvalues_string");
				if(ngsildPath != null)
					oAiCluster.setNgsildPath(ngsildPath);
			}

			if(saves.contains("ngsildContext")) {
				String ngsildContext = (String)doc.get("ngsildContext_docvalues_string");
				if(ngsildContext != null)
					oAiCluster.setNgsildContext(ngsildContext);
			}

			if(saves.contains("ngsildData")) {
				String ngsildData = (String)doc.get("ngsildData_docvalues_string");
				if(ngsildData != null)
					oAiCluster.setNgsildData(ngsildData);
			}

			if(saves.contains("aiNodesTotal")) {
				Integer aiNodesTotal = (Integer)doc.get("aiNodesTotal_docvalues_int");
				if(aiNodesTotal != null)
					oAiCluster.setAiNodesTotal(aiNodesTotal);
			}

			if(saves.contains("gpuDevicesTotal")) {
				Integer gpuDevicesTotal = (Integer)doc.get("gpuDevicesTotal_docvalues_int");
				if(gpuDevicesTotal != null)
					oAiCluster.setGpuDevicesTotal(gpuDevicesTotal);
			}

			if(saves.contains("grafanaUrl")) {
				String grafanaUrl = (String)doc.get("grafanaUrl_docvalues_string");
				if(grafanaUrl != null)
					oAiCluster.setGrafanaUrl(grafanaUrl);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexAiCluster(JsonObject doc) {
		if(clusterName != null) {
			doc.put("clusterName_docvalues_string", clusterName);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(locationColors != null) {
			JsonArray l = new JsonArray();
			doc.put("locationColors_indexedstored_strings", l);
			for(String o : locationColors) {
				l.add(AiCluster.staticSearchLocationColors(siteRequest_, o));
			}
		}
		if(locationTitles != null) {
			JsonArray l = new JsonArray();
			doc.put("locationTitles_indexedstored_strings", l);
			for(String o : locationTitles) {
				l.add(AiCluster.staticSearchLocationTitles(siteRequest_, o));
			}
		}
		if(locationLinks != null) {
			JsonArray l = new JsonArray();
			doc.put("locationLinks_indexedstored_strings", l);
			for(String o : locationLinks) {
				l.add(AiCluster.staticSearchLocationLinks(siteRequest_, o));
			}
		}
		if(location != null) {
			doc.put("location_docvalues_location", String.format("%s,%s", location.getY(), location.getX()));
		}
		if(id != null) {
			doc.put("id_docvalues_string", id);
		}
		if(entityShortId != null) {
			doc.put("entityShortId_docvalues_string", entityShortId);
		}
		if(ngsildTenant != null) {
			doc.put("ngsildTenant_docvalues_string", ngsildTenant);
		}
		if(ngsildPath != null) {
			doc.put("ngsildPath_docvalues_string", ngsildPath);
		}
		if(ngsildContext != null) {
			doc.put("ngsildContext_docvalues_string", ngsildContext);
		}
		if(ngsildData != null) {
			doc.put("ngsildData_docvalues_string", ngsildData.toString());
		}
		if(aiNodesTotal != null) {
			doc.put("aiNodesTotal_docvalues_int", aiNodesTotal);
		}
		if(gpuDevicesTotal != null) {
			doc.put("gpuDevicesTotal_docvalues_int", gpuDevicesTotal);
		}
		if(grafanaUrl != null) {
			doc.put("grafanaUrl_docvalues_string", grafanaUrl);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredAiCluster(String entityVar) {
		switch(entityVar) {
			case "clusterName":
				return "clusterName_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "locationColors":
				return "locationColors_indexedstored_strings";
			case "locationTitles":
				return "locationTitles_indexedstored_strings";
			case "locationLinks":
				return "locationLinks_indexedstored_strings";
			case "location":
				return "location_docvalues_location";
			case "id":
				return "id_docvalues_string";
			case "entityShortId":
				return "entityShortId_docvalues_string";
			case "ngsildTenant":
				return "ngsildTenant_docvalues_string";
			case "ngsildPath":
				return "ngsildPath_docvalues_string";
			case "ngsildContext":
				return "ngsildContext_docvalues_string";
			case "ngsildData":
				return "ngsildData_docvalues_string";
			case "aiNodesTotal":
				return "aiNodesTotal_docvalues_int";
			case "gpuDevicesTotal":
				return "gpuDevicesTotal_docvalues_int";
			case "grafanaUrl":
				return "grafanaUrl_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedAiCluster(String entityVar) {
		switch(entityVar) {
			case "clusterName":
				return "clusterName_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "locationColors":
				return "locationColors_indexedstored_strings";
			case "locationTitles":
				return "locationTitles_indexedstored_strings";
			case "locationLinks":
				return "locationLinks_indexedstored_strings";
			case "location":
				return "location_docvalues_location";
			case "id":
				return "id_docvalues_string";
			case "entityShortId":
				return "entityShortId_docvalues_string";
			case "ngsildTenant":
				return "ngsildTenant_docvalues_string";
			case "ngsildPath":
				return "ngsildPath_docvalues_string";
			case "ngsildContext":
				return "ngsildContext_docvalues_string";
			case "ngsildData":
				return "ngsildData_docvalues_string";
			case "aiNodesTotal":
				return "aiNodesTotal_docvalues_int";
			case "gpuDevicesTotal":
				return "gpuDevicesTotal_docvalues_int";
			case "grafanaUrl":
				return "grafanaUrl_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarAiCluster(String searchVar) {
		switch(searchVar) {
			case "clusterName_docvalues_string":
				return "clusterName";
			case "description_docvalues_string":
				return "description";
			case "locationColors_indexedstored_strings":
				return "locationColors";
			case "locationTitles_indexedstored_strings":
				return "locationTitles";
			case "locationLinks_indexedstored_strings":
				return "locationLinks";
			case "location_docvalues_location":
				return "location";
			case "id_docvalues_string":
				return "id";
			case "entityShortId_docvalues_string":
				return "entityShortId";
			case "ngsildTenant_docvalues_string":
				return "ngsildTenant";
			case "ngsildPath_docvalues_string":
				return "ngsildPath";
			case "ngsildContext_docvalues_string":
				return "ngsildContext";
			case "ngsildData_docvalues_string":
				return "ngsildData";
			case "aiNodesTotal_docvalues_int":
				return "aiNodesTotal";
			case "gpuDevicesTotal_docvalues_int":
				return "gpuDevicesTotal";
			case "grafanaUrl_docvalues_string":
				return "grafanaUrl";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchAiCluster(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedAiCluster(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeAiCluster(doc);
	}
	public void storeAiCluster(SolrResponse.Doc doc) {
		AiCluster oAiCluster = (AiCluster)this;
		SiteRequest siteRequest = oAiCluster.getSiteRequest_();

		oAiCluster.setClusterName(Optional.ofNullable(doc.get("clusterName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiCluster.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("locationColors_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oAiCluster.addLocationColors(AiCluster.staticSetLocationColors(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationTitles_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oAiCluster.addLocationTitles(AiCluster.staticSetLocationTitles(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationLinks_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oAiCluster.addLocationLinks(AiCluster.staticSetLocationLinks(siteRequest, v.toString()));
		});
		oAiCluster.setLocation(Optional.ofNullable(doc.get("location_docvalues_location")).map(v -> v.toString()).orElse(null));
		oAiCluster.setId(Optional.ofNullable(doc.get("id_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiCluster.setEntityShortId(Optional.ofNullable(doc.get("entityShortId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiCluster.setNgsildTenant(Optional.ofNullable(doc.get("ngsildTenant_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiCluster.setNgsildPath(Optional.ofNullable(doc.get("ngsildPath_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiCluster.setNgsildContext(Optional.ofNullable(doc.get("ngsildContext_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiCluster.setNgsildData(Optional.ofNullable(doc.get("ngsildData_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiCluster.setAiNodesTotal(Optional.ofNullable(doc.get("aiNodesTotal_docvalues_int")).map(v -> v.toString()).orElse(null));
		oAiCluster.setGpuDevicesTotal(Optional.ofNullable(doc.get("gpuDevicesTotal_docvalues_int")).map(v -> v.toString()).orElse(null));
		oAiCluster.setGrafanaUrl(Optional.ofNullable(doc.get("grafanaUrl_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestAiCluster() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof AiCluster) {
			AiCluster original = (AiCluster)o;
			if(!Objects.equals(clusterName, original.getClusterName()))
				apiRequest.addVars("clusterName");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(locationColors, original.getLocationColors()))
				apiRequest.addVars("locationColors");
			if(!Objects.equals(locationTitles, original.getLocationTitles()))
				apiRequest.addVars("locationTitles");
			if(!Objects.equals(locationLinks, original.getLocationLinks()))
				apiRequest.addVars("locationLinks");
			if(!Objects.equals(location, original.getLocation()))
				apiRequest.addVars("location");
			if(!Objects.equals(id, original.getId()))
				apiRequest.addVars("id");
			if(!Objects.equals(entityShortId, original.getEntityShortId()))
				apiRequest.addVars("entityShortId");
			if(!Objects.equals(ngsildTenant, original.getNgsildTenant()))
				apiRequest.addVars("ngsildTenant");
			if(!Objects.equals(ngsildPath, original.getNgsildPath()))
				apiRequest.addVars("ngsildPath");
			if(!Objects.equals(ngsildContext, original.getNgsildContext()))
				apiRequest.addVars("ngsildContext");
			if(!Objects.equals(ngsildData, original.getNgsildData()))
				apiRequest.addVars("ngsildData");
			if(!Objects.equals(aiNodesTotal, original.getAiNodesTotal()))
				apiRequest.addVars("aiNodesTotal");
			if(!Objects.equals(gpuDevicesTotal, original.getGpuDevicesTotal()))
				apiRequest.addVars("gpuDevicesTotal");
			if(!Objects.equals(grafanaUrl, original.getGrafanaUrl()))
				apiRequest.addVars("grafanaUrl");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(clusterName).map(v -> "clusterName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationColors).map(v -> "locationColors: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationTitles).map(v -> "locationTitles: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationLinks).map(v -> "locationLinks: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(location).map(v -> "location: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(id).map(v -> "id: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(entityShortId).map(v -> "entityShortId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildTenant).map(v -> "ngsildTenant: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildPath).map(v -> "ngsildPath: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildContext).map(v -> "ngsildContext: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildData).map(v -> "ngsildData: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(aiNodesTotal).map(v -> "aiNodesTotal: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(gpuDevicesTotal).map(v -> "gpuDevicesTotal: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(grafanaUrl).map(v -> "grafanaUrl: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "AiCluster";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.cluster.AiCluster";
	public static final String CLASS_API_ADDRESS_AiCluster = "ai-telemetry-enUS-AiCluster";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_AiCluster;
	}
	public static final String VAR_clusterName = "clusterName";
	public static final String VAR_description = "description";
	public static final String VAR_locationColors = "locationColors";
	public static final String VAR_locationTitles = "locationTitles";
	public static final String VAR_locationLinks = "locationLinks";
	public static final String VAR_location = "location";
	public static final String VAR_id = "id";
	public static final String VAR_entityShortId = "entityShortId";
	public static final String VAR_ngsildTenant = "ngsildTenant";
	public static final String VAR_ngsildPath = "ngsildPath";
	public static final String VAR_ngsildContext = "ngsildContext";
	public static final String VAR_ngsildData = "ngsildData";
	public static final String VAR_aiNodesTotal = "aiNodesTotal";
	public static final String VAR_gpuDevicesTotal = "gpuDevicesTotal";
	public static final String VAR_grafanaUrl = "grafanaUrl";

	public static List<String> varsQForClass() {
		return AiCluster.varsQAiCluster(new ArrayList<String>());
	}
	public static List<String> varsQAiCluster(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return AiCluster.varsFqAiCluster(new ArrayList<String>());
	}
	public static List<String> varsFqAiCluster(List<String> vars) {
		vars.add(VAR_clusterName);
		vars.add(VAR_location);
		vars.add(VAR_id);
		vars.add(VAR_entityShortId);
		vars.add(VAR_ngsildTenant);
		vars.add(VAR_ngsildPath);
		vars.add(VAR_ngsildContext);
		vars.add(VAR_ngsildData);
		vars.add(VAR_aiNodesTotal);
		vars.add(VAR_gpuDevicesTotal);
		vars.add(VAR_grafanaUrl);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return AiCluster.varsRangeAiCluster(new ArrayList<String>());
	}
	public static List<String> varsRangeAiCluster(List<String> vars) {
		vars.add(VAR_location);
		vars.add(VAR_ngsildData);
		vars.add(VAR_aiNodesTotal);
		vars.add(VAR_gpuDevicesTotal);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_clusterName = "cluster name";
	public static final String DISPLAY_NAME_description = "description";
	public static final String DISPLAY_NAME_locationColors = "area served colors";
	public static final String DISPLAY_NAME_locationTitles = "area served titles";
	public static final String DISPLAY_NAME_locationLinks = "area served links";
	public static final String DISPLAY_NAME_location = "location";
	public static final String DISPLAY_NAME_id = "entity ID";
	public static final String DISPLAY_NAME_entityShortId = "short entity ID";
	public static final String DISPLAY_NAME_ngsildTenant = "NGSILD-Tenant";
	public static final String DISPLAY_NAME_ngsildPath = "NGSILD-Path";
	public static final String DISPLAY_NAME_ngsildContext = "NGSILD context";
	public static final String DISPLAY_NAME_ngsildData = "NGSILD data";
	public static final String DISPLAY_NAME_aiNodesTotal = "AI nodes total";
	public static final String DISPLAY_NAME_gpuDevicesTotal = "GPU devices total";
	public static final String DISPLAY_NAME_grafanaUrl = "Grafana GPU utilization";

	@Override
	public String idForClass() {
		return clusterName;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return clusterName;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return AiCluster.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/ai-cluster/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/ai-cluster/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return AiCluster.displayNameAiCluster(var);
	}
	public static String displayNameAiCluster(String var) {
		switch(var) {
		case VAR_clusterName:
			return DISPLAY_NAME_clusterName;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_locationColors:
			return DISPLAY_NAME_locationColors;
		case VAR_locationTitles:
			return DISPLAY_NAME_locationTitles;
		case VAR_locationLinks:
			return DISPLAY_NAME_locationLinks;
		case VAR_location:
			return DISPLAY_NAME_location;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_entityShortId:
			return DISPLAY_NAME_entityShortId;
		case VAR_ngsildTenant:
			return DISPLAY_NAME_ngsildTenant;
		case VAR_ngsildPath:
			return DISPLAY_NAME_ngsildPath;
		case VAR_ngsildContext:
			return DISPLAY_NAME_ngsildContext;
		case VAR_ngsildData:
			return DISPLAY_NAME_ngsildData;
		case VAR_aiNodesTotal:
			return DISPLAY_NAME_aiNodesTotal;
		case VAR_gpuDevicesTotal:
			return DISPLAY_NAME_gpuDevicesTotal;
		case VAR_grafanaUrl:
			return DISPLAY_NAME_grafanaUrl;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionAiCluster(String var) {
		switch(var) {
		case VAR_clusterName:
			return "The name of this cluster";
		case VAR_description:
			return "A description of this cluster";
		case VAR_locationColors:
			return "The colors of each location Paths. ";
		case VAR_locationTitles:
			return "The titles of each location Paths. ";
		case VAR_locationLinks:
			return "The links of each location Paths. ";
		case VAR_location:
			return "Geojson reference to the item. It can be Point, LineString, Polygon, MultiPoint, MultiLineString or MultiPolygon";
		case VAR_id:
			return "A unique ID for this Smart Data Model";
		case VAR_entityShortId:
			return "A short ID for this Smart Data Model";
		case VAR_ngsildTenant:
			return "The NGSILD-Tenant or Fiware-Service";
		case VAR_ngsildPath:
			return "The NGSILD-Path or Fiware-ServicePath";
		case VAR_ngsildContext:
			return "The NGSILD context URL for @context data";
		case VAR_ngsildData:
			return "The NGSILD data with @context from the context broker";
		case VAR_aiNodesTotal:
			return "The total number of AI nodes on this cluster. ";
		case VAR_gpuDevicesTotal:
			return "The total number of GPU devices on this cluster. ";
		case VAR_grafanaUrl:
			return "Explore this cluster's GPU utilization in Grafana. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameAiCluster(String var) {
		switch(var) {
		case VAR_clusterName:
			return "String";
		case VAR_description:
			return "String";
		case VAR_locationColors:
			return "List";
		case VAR_locationTitles:
			return "List";
		case VAR_locationLinks:
			return "List";
		case VAR_location:
			return "Point";
		case VAR_id:
			return "String";
		case VAR_entityShortId:
			return "String";
		case VAR_ngsildTenant:
			return "String";
		case VAR_ngsildPath:
			return "String";
		case VAR_ngsildContext:
			return "String";
		case VAR_ngsildData:
			return "JsonObject";
		case VAR_aiNodesTotal:
			return "Integer";
		case VAR_gpuDevicesTotal:
			return "Integer";
		case VAR_grafanaUrl:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnAiCluster(String var) {
		switch(var) {
		case VAR_clusterName:
			return 1;
		case VAR_description:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowAiCluster(String var) {
		switch(var) {
		case VAR_clusterName:
			return 3;
		case VAR_description:
			return 3;
		case VAR_location:
			return 3;
		case VAR_id:
			return 3;
		case VAR_ngsildTenant:
			return 5;
		case VAR_ngsildPath:
			return 5;
		case VAR_ngsildContext:
			return 5;
		case VAR_ngsildData:
			return 5;
		case VAR_aiNodesTotal:
			return 4;
		case VAR_gpuDevicesTotal:
			return 4;
		case VAR_grafanaUrl:
			return 4;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellAiCluster(String var) {
		switch(var) {
		case VAR_clusterName:
			return 1;
		case VAR_description:
			return 2;
		case VAR_location:
			return 5;
		case VAR_id:
			return 4;
		case VAR_ngsildTenant:
			return 1;
		case VAR_ngsildPath:
			return 2;
		case VAR_ngsildContext:
			return 3;
		case VAR_ngsildData:
			return 4;
		case VAR_aiNodesTotal:
			return 1;
		case VAR_gpuDevicesTotal:
			return 2;
		case VAR_grafanaUrl:
			return 3;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinAiCluster(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxAiCluster(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxAiCluster(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minAiCluster(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
