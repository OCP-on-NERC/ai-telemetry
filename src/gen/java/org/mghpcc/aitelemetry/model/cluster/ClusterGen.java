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
import org.mghpcc.aitelemetry.model.hub.Hub;
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
import java.lang.Long;
import java.lang.Boolean;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ClusterGen into the class Cluster. 
 * </li>
 * <h3>About the Cluster class and it's generated class ClusterGen&lt;BaseModel&gt;: </h3>extends ClusterGen
 * <p>
 * This Java class extends a generated Java class ClusterGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster">Find the class Cluster in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ClusterGen<BaseModel>
 * <p>This <code>class Cluster extends ClusterGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ClusterGen. 
 * The generated <code>class ClusterGen extends BaseModel</code> which means that Cluster extends ClusterGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: OpenShift clusters"</b>, which groups all of the OpenAPIs for Cluster objects under the tag "OpenShift clusters". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/cluster</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/cluster"</b>, which defines the base API URI for Cluster objects as "/en-us/api/cluster" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the Cluster class will inherit the helpful inherited class comments from the super class ClusterGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the Cluster API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 5</h2>
 * <p>This class contains a comment <b>"Order: 5"</b>, which means this class will be sorted by the given number 5 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 5</h2>
 * <p>This class contains a comment <b>"SqlOrder: 5"</b>, which means this class will be sorted by the given number 5 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.cluster.ClusterPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.cluster.ClusterPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the Cluster Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an OpenShift cluster</h2>
 * <p>This class contains a comment <b>"AName.enUS: an OpenShift cluster"</b>, which identifies the language context to describe a Cluster as "an OpenShift cluster". 
 * </p>
 * <p>
 * Delete the class Cluster in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class ClusterGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(Cluster.class);

	public static final String Description_enUS = "A Red Hat OpenShift cluster containing GPUs";
	public static final String AName_enUS = "an OpenShift cluster";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this OpenShift cluster";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "theOpenShift cluster";
	public static final String SingularName_enUS = "OpenShift cluster";
	public static final String PluralName_enUS = "OpenShift clusters";
	public static final String NameActual_enUS = "current OpenShift cluster";
	public static final String AllName_enUS = "all OpenShift clusters";
	public static final String SearchAllNameBy_enUS = "search OpenShift clusters by ";
	public static final String SearchAllName_enUS = "search OpenShift clusters";
	public static final String Title_enUS = "OpenShift clusters";
	public static final String ThePluralName_enUS = "the OpenShift clusters";
	public static final String NoNameFound_enUS = "no OpenShift cluster found";
	public static final String ApiUri_enUS = "/en-us/api/cluster";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/cluster";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/cluster/{clusterResource}";
	public static final String OfName_enUS = "of OpenShift cluster";
	public static final String ANameAdjective_enUS = "an OpenShift cluster";
	public static final String NameAdjectiveSingular_enUS = "OpenShift cluster";
	public static final String NameAdjectivePlural_enUS = "OpenShift clusters";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/cluster";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/cluster";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/cluster";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/cluster/{clusterResource}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/cluster/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/cluster/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/cluster";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/cluster";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/cluster";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/cluster";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/cluster";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/cluster";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/cluster/{clusterResource}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/cluster/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/cluster/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/cluster-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/cluster-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/cluster-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/cluster";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/cluster";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/cluster";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/cluster/{clusterResource}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/cluster/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/cluster/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/cluster/{clusterResource}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/cluster/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/cluster/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/cluster";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/cluster";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/cluster";

	public static final String Icon = "<i class=\"fa-regular fa-server\"></i>";
	public static final Integer Rows = 100;

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:hubId">Find the entity hubId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _hubId(Wrap<String> w);

	public String getHubId() {
		return hubId;
	}
	public void setHubId(String o) {
		this.hubId = Cluster.staticSetHubId(siteRequest_, o);
	}
	public static String staticSetHubId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster hubIdInit() {
		Wrap<String> hubIdWrap = new Wrap<String>().var("hubId");
		if(hubId == null) {
			_hubId(hubIdWrap);
			Optional.ofNullable(hubIdWrap.getO()).ifPresent(o -> {
				setHubId(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchHubId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrHubId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqHubId(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchHubId(siteRequest_, Cluster.staticSetHubId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:hubResource">Find the entity hubResource in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _hubResource(Wrap<String> w);

	public String getHubResource() {
		return hubResource;
	}
	public void setHubResource(String o) {
		this.hubResource = Cluster.staticSetHubResource(siteRequest_, o);
	}
	public static String staticSetHubResource(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster hubResourceInit() {
		Wrap<String> hubResourceWrap = new Wrap<String>().var("hubResource");
		if(hubResource == null) {
			_hubResource(hubResourceWrap);
			Optional.ofNullable(hubResourceWrap.getO()).ifPresent(o -> {
				setHubResource(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchHubResource(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrHubResource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqHubResource(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchHubResource(siteRequest_, Cluster.staticSetHubResource(siteRequest_, o)).toString();
	}

	public String sqlHubResource() {
		return hubResource;
	}

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:clusterName">Find the entity clusterName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterName(Wrap<String> w);

	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String o) {
		this.clusterName = Cluster.staticSetClusterName(siteRequest_, o);
	}
	public static String staticSetClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster clusterNameInit() {
		Wrap<String> clusterNameWrap = new Wrap<String>().var("clusterName");
		if(clusterName == null) {
			_clusterName(clusterNameWrap);
			Optional.ofNullable(clusterNameWrap.getO()).ifPresent(o -> {
				setClusterName(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterName(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchClusterName(siteRequest_, Cluster.staticSetClusterName(siteRequest_, o)).toString();
	}

	public String sqlClusterName() {
		return clusterName;
	}

	/////////////////////
	// clusterResource //
	/////////////////////


	/**	 The entity clusterResource
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String clusterResource;

	/**	<br> The entity clusterResource
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:clusterResource">Find the entity clusterResource in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterResource(Wrap<String> w);

	public String getClusterResource() {
		return clusterResource;
	}
	public void setClusterResource(String o) {
		this.clusterResource = Cluster.staticSetClusterResource(siteRequest_, o);
	}
	public static String staticSetClusterResource(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster clusterResourceInit() {
		Wrap<String> clusterResourceWrap = new Wrap<String>().var("clusterResource");
		if(clusterResource == null) {
			_clusterResource(clusterResourceWrap);
			Optional.ofNullable(clusterResourceWrap.getO()).ifPresent(o -> {
				setClusterResource(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchClusterResource(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterResource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterResource(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchClusterResource(siteRequest_, Cluster.staticSetClusterResource(siteRequest_, o)).toString();
	}

	public String sqlClusterResource() {
		return clusterResource;
	}

	////////////////
	// uniqueName //
	////////////////


	/**	 The entity uniqueName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String uniqueName;

	/**	<br> The entity uniqueName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:uniqueName">Find the entity uniqueName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _uniqueName(Wrap<String> w);

	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String o) {
		this.uniqueName = Cluster.staticSetUniqueName(siteRequest_, o);
	}
	public static String staticSetUniqueName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster uniqueNameInit() {
		Wrap<String> uniqueNameWrap = new Wrap<String>().var("uniqueName");
		if(uniqueName == null) {
			_uniqueName(uniqueNameWrap);
			Optional.ofNullable(uniqueNameWrap.getO()).ifPresent(o -> {
				setUniqueName(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchUniqueName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUniqueName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUniqueName(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchUniqueName(siteRequest_, Cluster.staticSetUniqueName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = Cluster.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchDescription(siteRequest_, Cluster.staticSetDescription(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:locationColors">Find the entity locationColors in Solr</a>
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
		String l = Cluster.staticSetLocationColors(siteRequest_, o);
		if(l != null)
			addLocationColors(l);
	}
	public static String staticSetLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}
	public Cluster addLocationColors(String...objects) {
		for(String o : objects) {
			addLocationColors(o);
		}
		return (Cluster)this;
	}
	public Cluster addLocationColors(String o) {
		if(o != null)
			this.locationColors.add(o);
		return (Cluster)this;
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
	protected Cluster locationColorsInit() {
		_locationColors(locationColors);
		return (Cluster)this;
	}

	public static String staticSearchLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationColors(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationColors(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchLocationColors(siteRequest_, Cluster.staticSetLocationColors(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:locationTitles">Find the entity locationTitles in Solr</a>
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
		String l = Cluster.staticSetLocationTitles(siteRequest_, o);
		if(l != null)
			addLocationTitles(l);
	}
	public static String staticSetLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}
	public Cluster addLocationTitles(String...objects) {
		for(String o : objects) {
			addLocationTitles(o);
		}
		return (Cluster)this;
	}
	public Cluster addLocationTitles(String o) {
		if(o != null)
			this.locationTitles.add(o);
		return (Cluster)this;
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
	protected Cluster locationTitlesInit() {
		_locationTitles(locationTitles);
		return (Cluster)this;
	}

	public static String staticSearchLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationTitles(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationTitles(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchLocationTitles(siteRequest_, Cluster.staticSetLocationTitles(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:locationLinks">Find the entity locationLinks in Solr</a>
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
		String l = Cluster.staticSetLocationLinks(siteRequest_, o);
		if(l != null)
			addLocationLinks(l);
	}
	public static String staticSetLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}
	public Cluster addLocationLinks(String...objects) {
		for(String o : objects) {
			addLocationLinks(o);
		}
		return (Cluster)this;
	}
	public Cluster addLocationLinks(String o) {
		if(o != null)
			this.locationLinks.add(o);
		return (Cluster)this;
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
	protected Cluster locationLinksInit() {
		_locationLinks(locationLinks);
		return (Cluster)this;
	}

	public static String staticSearchLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationLinks(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationLinks(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchLocationLinks(siteRequest_, Cluster.staticSetLocationLinks(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:location">Find the entity location in Solr</a>
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
		this.location = Cluster.staticSetLocation(siteRequest_, o);
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
		this.location = Cluster.staticSetLocation(siteRequest_, o);
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
	protected Cluster locationInit() {
		Wrap<Point> locationWrap = new Wrap<Point>().var("location");
		if(location == null) {
			_location(locationWrap);
			Optional.ofNullable(locationWrap.getO()).ifPresent(o -> {
				setLocation(o);
			});
		}
		return (Cluster)this;
	}

	public static Point staticSearchLocation(SiteRequest siteRequest_, Point o) {
		return o;
	}

	public static String staticSearchStrLocation(SiteRequest siteRequest_, Point o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocation(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchLocation(siteRequest_, Cluster.staticSetLocation(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = Cluster.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchId(siteRequest_, Cluster.staticSetId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:entityShortId">Find the entity entityShortId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityShortId(Wrap<String> w);

	public String getEntityShortId() {
		return entityShortId;
	}
	public void setEntityShortId(String o) {
		this.entityShortId = Cluster.staticSetEntityShortId(siteRequest_, o);
	}
	public static String staticSetEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster entityShortIdInit() {
		Wrap<String> entityShortIdWrap = new Wrap<String>().var("entityShortId");
		if(entityShortId == null) {
			_entityShortId(entityShortIdWrap);
			Optional.ofNullable(entityShortIdWrap.getO()).ifPresent(o -> {
				setEntityShortId(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityShortId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityShortId(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchEntityShortId(siteRequest_, Cluster.staticSetEntityShortId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:ngsildTenant">Find the entity ngsildTenant in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildTenant(Wrap<String> w);

	public String getNgsildTenant() {
		return ngsildTenant;
	}
	public void setNgsildTenant(String o) {
		this.ngsildTenant = Cluster.staticSetNgsildTenant(siteRequest_, o);
	}
	public static String staticSetNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster ngsildTenantInit() {
		Wrap<String> ngsildTenantWrap = new Wrap<String>().var("ngsildTenant");
		if(ngsildTenant == null) {
			_ngsildTenant(ngsildTenantWrap);
			Optional.ofNullable(ngsildTenantWrap.getO()).ifPresent(o -> {
				setNgsildTenant(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildTenant(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildTenant(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchNgsildTenant(siteRequest_, Cluster.staticSetNgsildTenant(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:ngsildPath">Find the entity ngsildPath in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildPath(Wrap<String> w);

	public String getNgsildPath() {
		return ngsildPath;
	}
	public void setNgsildPath(String o) {
		this.ngsildPath = Cluster.staticSetNgsildPath(siteRequest_, o);
	}
	public static String staticSetNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster ngsildPathInit() {
		Wrap<String> ngsildPathWrap = new Wrap<String>().var("ngsildPath");
		if(ngsildPath == null) {
			_ngsildPath(ngsildPathWrap);
			Optional.ofNullable(ngsildPathWrap.getO()).ifPresent(o -> {
				setNgsildPath(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildPath(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildPath(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchNgsildPath(siteRequest_, Cluster.staticSetNgsildPath(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:ngsildContext">Find the entity ngsildContext in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildContext(Wrap<String> w);

	public String getNgsildContext() {
		return ngsildContext;
	}
	public void setNgsildContext(String o) {
		this.ngsildContext = Cluster.staticSetNgsildContext(siteRequest_, o);
	}
	public static String staticSetNgsildContext(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster ngsildContextInit() {
		Wrap<String> ngsildContextWrap = new Wrap<String>().var("ngsildContext");
		if(ngsildContext == null) {
			_ngsildContext(ngsildContextWrap);
			Optional.ofNullable(ngsildContextWrap.getO()).ifPresent(o -> {
				setNgsildContext(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchNgsildContext(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildContext(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildContext(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchNgsildContext(siteRequest_, Cluster.staticSetNgsildContext(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:ngsildData">Find the entity ngsildData in Solr</a>
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
		this.ngsildData = Cluster.staticSetNgsildData(siteRequest_, o);
	}
	public static JsonObject staticSetNgsildData(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected Cluster ngsildDataInit() {
		Wrap<JsonObject> ngsildDataWrap = new Wrap<JsonObject>().var("ngsildData");
		if(ngsildData == null) {
			_ngsildData(ngsildDataWrap);
			Optional.ofNullable(ngsildDataWrap.getO()).ifPresent(o -> {
				setNgsildData(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchNgsildData(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrNgsildData(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildData(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchNgsildData(siteRequest_, Cluster.staticSetNgsildData(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:aiNodesTotal">Find the entity aiNodesTotal in Solr</a>
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
		this.aiNodesTotal = Cluster.staticSetAiNodesTotal(siteRequest_, o);
	}
	public static Integer staticSetAiNodesTotal(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected Cluster aiNodesTotalInit() {
		Wrap<Integer> aiNodesTotalWrap = new Wrap<Integer>().var("aiNodesTotal");
		if(aiNodesTotal == null) {
			_aiNodesTotal(aiNodesTotalWrap);
			Optional.ofNullable(aiNodesTotalWrap.getO()).ifPresent(o -> {
				setAiNodesTotal(o);
			});
		}
		return (Cluster)this;
	}

	public static Integer staticSearchAiNodesTotal(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrAiNodesTotal(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAiNodesTotal(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchAiNodesTotal(siteRequest_, Cluster.staticSetAiNodesTotal(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:gpuDevicesTotal">Find the entity gpuDevicesTotal in Solr</a>
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
		this.gpuDevicesTotal = Cluster.staticSetGpuDevicesTotal(siteRequest_, o);
	}
	public static Integer staticSetGpuDevicesTotal(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected Cluster gpuDevicesTotalInit() {
		Wrap<Integer> gpuDevicesTotalWrap = new Wrap<Integer>().var("gpuDevicesTotal");
		if(gpuDevicesTotal == null) {
			_gpuDevicesTotal(gpuDevicesTotalWrap);
			Optional.ofNullable(gpuDevicesTotalWrap.getO()).ifPresent(o -> {
				setGpuDevicesTotal(o);
			});
		}
		return (Cluster)this;
	}

	public static Integer staticSearchGpuDevicesTotal(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrGpuDevicesTotal(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuDevicesTotal(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchGpuDevicesTotal(siteRequest_, Cluster.staticSetGpuDevicesTotal(siteRequest_, o)).toString();
	}

	public Integer sqlGpuDevicesTotal() {
		return gpuDevicesTotal;
	}

	///////////////////
	// cpuCoresTotal //
	///////////////////


	/**	 The entity cpuCoresTotal
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer cpuCoresTotal;

	/**	<br> The entity cpuCoresTotal
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:cpuCoresTotal">Find the entity cpuCoresTotal in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _cpuCoresTotal(Wrap<Integer> w);

	public Integer getCpuCoresTotal() {
		return cpuCoresTotal;
	}

	public void setCpuCoresTotal(Integer cpuCoresTotal) {
		this.cpuCoresTotal = cpuCoresTotal;
	}
	@JsonIgnore
	public void setCpuCoresTotal(String o) {
		this.cpuCoresTotal = Cluster.staticSetCpuCoresTotal(siteRequest_, o);
	}
	public static Integer staticSetCpuCoresTotal(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected Cluster cpuCoresTotalInit() {
		Wrap<Integer> cpuCoresTotalWrap = new Wrap<Integer>().var("cpuCoresTotal");
		if(cpuCoresTotal == null) {
			_cpuCoresTotal(cpuCoresTotalWrap);
			Optional.ofNullable(cpuCoresTotalWrap.getO()).ifPresent(o -> {
				setCpuCoresTotal(o);
			});
		}
		return (Cluster)this;
	}

	public static Integer staticSearchCpuCoresTotal(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCpuCoresTotal(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCpuCoresTotal(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchCpuCoresTotal(siteRequest_, Cluster.staticSetCpuCoresTotal(siteRequest_, o)).toString();
	}

	public Integer sqlCpuCoresTotal() {
		return cpuCoresTotal;
	}

	//////////////////////
	// memoryBytesTotal //
	//////////////////////


	/**	 The entity memoryBytesTotal
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long memoryBytesTotal;

	/**	<br> The entity memoryBytesTotal
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:memoryBytesTotal">Find the entity memoryBytesTotal in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _memoryBytesTotal(Wrap<Long> w);

	public Long getMemoryBytesTotal() {
		return memoryBytesTotal;
	}

	public void setMemoryBytesTotal(Long memoryBytesTotal) {
		this.memoryBytesTotal = memoryBytesTotal;
	}
	@JsonIgnore
	public void setMemoryBytesTotal(String o) {
		this.memoryBytesTotal = Cluster.staticSetMemoryBytesTotal(siteRequest_, o);
	}
	public static Long staticSetMemoryBytesTotal(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected Cluster memoryBytesTotalInit() {
		Wrap<Long> memoryBytesTotalWrap = new Wrap<Long>().var("memoryBytesTotal");
		if(memoryBytesTotal == null) {
			_memoryBytesTotal(memoryBytesTotalWrap);
			Optional.ofNullable(memoryBytesTotalWrap.getO()).ifPresent(o -> {
				setMemoryBytesTotal(o);
			});
		}
		return (Cluster)this;
	}

	public static Long staticSearchMemoryBytesTotal(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrMemoryBytesTotal(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqMemoryBytesTotal(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchMemoryBytesTotal(siteRequest_, Cluster.staticSetMemoryBytesTotal(siteRequest_, o)).toString();
	}

	public Long sqlMemoryBytesTotal() {
		return memoryBytesTotal;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:grafanaUrl">Find the entity grafanaUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _grafanaUrl(Wrap<String> w);

	public String getGrafanaUrl() {
		return grafanaUrl;
	}
	public void setGrafanaUrl(String o) {
		this.grafanaUrl = Cluster.staticSetGrafanaUrl(siteRequest_, o);
	}
	public static String staticSetGrafanaUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster grafanaUrlInit() {
		Wrap<String> grafanaUrlWrap = new Wrap<String>().var("grafanaUrl");
		if(grafanaUrl == null) {
			_grafanaUrl(grafanaUrlWrap);
			Optional.ofNullable(grafanaUrlWrap.getO()).ifPresent(o -> {
				setGrafanaUrl(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchGrafanaUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrGrafanaUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGrafanaUrl(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchGrafanaUrl(siteRequest_, Cluster.staticSetGrafanaUrl(siteRequest_, o)).toString();
	}

	//////////////////////////
	// promKeycloakProxySsl //
	//////////////////////////


	/**	 The entity promKeycloakProxySsl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean promKeycloakProxySsl;

	/**	<br> The entity promKeycloakProxySsl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:promKeycloakProxySsl">Find the entity promKeycloakProxySsl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _promKeycloakProxySsl(Wrap<Boolean> w);

	public Boolean getPromKeycloakProxySsl() {
		return promKeycloakProxySsl;
	}

	public void setPromKeycloakProxySsl(Boolean promKeycloakProxySsl) {
		this.promKeycloakProxySsl = promKeycloakProxySsl;
	}
	@JsonIgnore
	public void setPromKeycloakProxySsl(String o) {
		this.promKeycloakProxySsl = Cluster.staticSetPromKeycloakProxySsl(siteRequest_, o);
	}
	public static Boolean staticSetPromKeycloakProxySsl(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected Cluster promKeycloakProxySslInit() {
		Wrap<Boolean> promKeycloakProxySslWrap = new Wrap<Boolean>().var("promKeycloakProxySsl");
		if(promKeycloakProxySsl == null) {
			_promKeycloakProxySsl(promKeycloakProxySslWrap);
			Optional.ofNullable(promKeycloakProxySslWrap.getO()).ifPresent(o -> {
				setPromKeycloakProxySsl(o);
			});
		}
		return (Cluster)this;
	}

	public static Boolean staticSearchPromKeycloakProxySsl(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrPromKeycloakProxySsl(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPromKeycloakProxySsl(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchPromKeycloakProxySsl(siteRequest_, Cluster.staticSetPromKeycloakProxySsl(siteRequest_, o)).toString();
	}

	public Boolean sqlPromKeycloakProxySsl() {
		return promKeycloakProxySsl;
	}

	///////////////////////////
	// promKeycloakProxyPort //
	///////////////////////////


	/**	 The entity promKeycloakProxyPort
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer promKeycloakProxyPort;

	/**	<br> The entity promKeycloakProxyPort
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:promKeycloakProxyPort">Find the entity promKeycloakProxyPort in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _promKeycloakProxyPort(Wrap<Integer> w);

	public Integer getPromKeycloakProxyPort() {
		return promKeycloakProxyPort;
	}

	public void setPromKeycloakProxyPort(Integer promKeycloakProxyPort) {
		this.promKeycloakProxyPort = promKeycloakProxyPort;
	}
	@JsonIgnore
	public void setPromKeycloakProxyPort(String o) {
		this.promKeycloakProxyPort = Cluster.staticSetPromKeycloakProxyPort(siteRequest_, o);
	}
	public static Integer staticSetPromKeycloakProxyPort(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected Cluster promKeycloakProxyPortInit() {
		Wrap<Integer> promKeycloakProxyPortWrap = new Wrap<Integer>().var("promKeycloakProxyPort");
		if(promKeycloakProxyPort == null) {
			_promKeycloakProxyPort(promKeycloakProxyPortWrap);
			Optional.ofNullable(promKeycloakProxyPortWrap.getO()).ifPresent(o -> {
				setPromKeycloakProxyPort(o);
			});
		}
		return (Cluster)this;
	}

	public static Integer staticSearchPromKeycloakProxyPort(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrPromKeycloakProxyPort(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPromKeycloakProxyPort(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchPromKeycloakProxyPort(siteRequest_, Cluster.staticSetPromKeycloakProxyPort(siteRequest_, o)).toString();
	}

	public Integer sqlPromKeycloakProxyPort() {
		return promKeycloakProxyPort;
	}

	///////////////////////////////
	// promKeycloakProxyHostName //
	///////////////////////////////


	/**	 The entity promKeycloakProxyHostName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String promKeycloakProxyHostName;

	/**	<br> The entity promKeycloakProxyHostName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.Cluster&fq=entiteVar_enUS_indexed_string:promKeycloakProxyHostName">Find the entity promKeycloakProxyHostName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _promKeycloakProxyHostName(Wrap<String> w);

	public String getPromKeycloakProxyHostName() {
		return promKeycloakProxyHostName;
	}
	public void setPromKeycloakProxyHostName(String o) {
		this.promKeycloakProxyHostName = Cluster.staticSetPromKeycloakProxyHostName(siteRequest_, o);
	}
	public static String staticSetPromKeycloakProxyHostName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Cluster promKeycloakProxyHostNameInit() {
		Wrap<String> promKeycloakProxyHostNameWrap = new Wrap<String>().var("promKeycloakProxyHostName");
		if(promKeycloakProxyHostName == null) {
			_promKeycloakProxyHostName(promKeycloakProxyHostNameWrap);
			Optional.ofNullable(promKeycloakProxyHostNameWrap.getO()).ifPresent(o -> {
				setPromKeycloakProxyHostName(o);
			});
		}
		return (Cluster)this;
	}

	public static String staticSearchPromKeycloakProxyHostName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPromKeycloakProxyHostName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPromKeycloakProxyHostName(SiteRequest siteRequest_, String o) {
		return Cluster.staticSearchPromKeycloakProxyHostName(siteRequest_, Cluster.staticSetPromKeycloakProxyHostName(siteRequest_, o)).toString();
	}

	public String sqlPromKeycloakProxyHostName() {
		return promKeycloakProxyHostName;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ClusterGen<DEV>> promiseDeepCluster(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCluster();
	}

	public Future<ClusterGen<DEV>> promiseDeepCluster() {
		Promise<ClusterGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCluster(promise2);
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

	public Future<Void> promiseCluster(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				hubIdInit();
				hubResourceInit();
				clusterNameInit();
				clusterResourceInit();
				uniqueNameInit();
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
				cpuCoresTotalInit();
				memoryBytesTotalInit();
				grafanaUrlInit();
				promKeycloakProxySslInit();
				promKeycloakProxyPortInit();
				promKeycloakProxyHostNameInit();
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

	@Override public Future<? extends ClusterGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCluster(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCluster(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCluster(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCluster(v);
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
	public Object obtainCluster(String var) {
		Cluster oCluster = (Cluster)this;
		switch(var) {
			case "hubId":
				return oCluster.hubId;
			case "hubResource":
				return oCluster.hubResource;
			case "clusterName":
				return oCluster.clusterName;
			case "clusterResource":
				return oCluster.clusterResource;
			case "uniqueName":
				return oCluster.uniqueName;
			case "description":
				return oCluster.description;
			case "locationColors":
				return oCluster.locationColors;
			case "locationTitles":
				return oCluster.locationTitles;
			case "locationLinks":
				return oCluster.locationLinks;
			case "location":
				return oCluster.location;
			case "id":
				return oCluster.id;
			case "entityShortId":
				return oCluster.entityShortId;
			case "ngsildTenant":
				return oCluster.ngsildTenant;
			case "ngsildPath":
				return oCluster.ngsildPath;
			case "ngsildContext":
				return oCluster.ngsildContext;
			case "ngsildData":
				return oCluster.ngsildData;
			case "aiNodesTotal":
				return oCluster.aiNodesTotal;
			case "gpuDevicesTotal":
				return oCluster.gpuDevicesTotal;
			case "cpuCoresTotal":
				return oCluster.cpuCoresTotal;
			case "memoryBytesTotal":
				return oCluster.memoryBytesTotal;
			case "grafanaUrl":
				return oCluster.grafanaUrl;
			case "promKeycloakProxySsl":
				return oCluster.promKeycloakProxySsl;
			case "promKeycloakProxyPort":
				return oCluster.promKeycloakProxyPort;
			case "promKeycloakProxyHostName":
				return oCluster.promKeycloakProxyHostName;
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
				o = relateCluster(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCluster(String var, Object val) {
		Cluster oCluster = (Cluster)this;
		switch(var) {
			case "hubResource":
				if(oCluster.getHubResource() == null)
					oCluster.setHubResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
				if(!saves.contains("hubResource"))
					saves.add("hubResource");
				return val;
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, Cluster o) {
		return staticSetCluster(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetCluster(String entityVar, SiteRequest siteRequest_, String v, Cluster o) {
		switch(entityVar) {
		case "hubId":
			return Cluster.staticSetHubId(siteRequest_, v);
		case "hubResource":
			return Cluster.staticSetHubResource(siteRequest_, v);
		case "clusterName":
			return Cluster.staticSetClusterName(siteRequest_, v);
		case "clusterResource":
			return Cluster.staticSetClusterResource(siteRequest_, v);
		case "uniqueName":
			return Cluster.staticSetUniqueName(siteRequest_, v);
		case "description":
			return Cluster.staticSetDescription(siteRequest_, v);
		case "locationColors":
			return Cluster.staticSetLocationColors(siteRequest_, v);
		case "locationTitles":
			return Cluster.staticSetLocationTitles(siteRequest_, v);
		case "locationLinks":
			return Cluster.staticSetLocationLinks(siteRequest_, v);
		case "location":
			return Cluster.staticSetLocation(siteRequest_, v);
		case "id":
			return Cluster.staticSetId(siteRequest_, v);
		case "entityShortId":
			return Cluster.staticSetEntityShortId(siteRequest_, v);
		case "ngsildTenant":
			return Cluster.staticSetNgsildTenant(siteRequest_, v);
		case "ngsildPath":
			return Cluster.staticSetNgsildPath(siteRequest_, v);
		case "ngsildContext":
			return Cluster.staticSetNgsildContext(siteRequest_, v);
		case "ngsildData":
			return Cluster.staticSetNgsildData(siteRequest_, v);
		case "aiNodesTotal":
			return Cluster.staticSetAiNodesTotal(siteRequest_, v);
		case "gpuDevicesTotal":
			return Cluster.staticSetGpuDevicesTotal(siteRequest_, v);
		case "cpuCoresTotal":
			return Cluster.staticSetCpuCoresTotal(siteRequest_, v);
		case "memoryBytesTotal":
			return Cluster.staticSetMemoryBytesTotal(siteRequest_, v);
		case "grafanaUrl":
			return Cluster.staticSetGrafanaUrl(siteRequest_, v);
		case "promKeycloakProxySsl":
			return Cluster.staticSetPromKeycloakProxySsl(siteRequest_, v);
		case "promKeycloakProxyPort":
			return Cluster.staticSetPromKeycloakProxyPort(siteRequest_, v);
		case "promKeycloakProxyHostName":
			return Cluster.staticSetPromKeycloakProxyHostName(siteRequest_, v);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCluster(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCluster(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "hubId":
			return Cluster.staticSearchHubId(siteRequest_, (String)o);
		case "hubResource":
			return Cluster.staticSearchHubResource(siteRequest_, (String)o);
		case "clusterName":
			return Cluster.staticSearchClusterName(siteRequest_, (String)o);
		case "clusterResource":
			return Cluster.staticSearchClusterResource(siteRequest_, (String)o);
		case "uniqueName":
			return Cluster.staticSearchUniqueName(siteRequest_, (String)o);
		case "description":
			return Cluster.staticSearchDescription(siteRequest_, (String)o);
		case "locationColors":
			return Cluster.staticSearchLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return Cluster.staticSearchLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return Cluster.staticSearchLocationLinks(siteRequest_, (String)o);
		case "location":
			return Cluster.staticSearchLocation(siteRequest_, (Point)o);
		case "id":
			return Cluster.staticSearchId(siteRequest_, (String)o);
		case "entityShortId":
			return Cluster.staticSearchEntityShortId(siteRequest_, (String)o);
		case "ngsildTenant":
			return Cluster.staticSearchNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return Cluster.staticSearchNgsildPath(siteRequest_, (String)o);
		case "ngsildContext":
			return Cluster.staticSearchNgsildContext(siteRequest_, (String)o);
		case "ngsildData":
			return Cluster.staticSearchNgsildData(siteRequest_, (JsonObject)o);
		case "aiNodesTotal":
			return Cluster.staticSearchAiNodesTotal(siteRequest_, (Integer)o);
		case "gpuDevicesTotal":
			return Cluster.staticSearchGpuDevicesTotal(siteRequest_, (Integer)o);
		case "cpuCoresTotal":
			return Cluster.staticSearchCpuCoresTotal(siteRequest_, (Integer)o);
		case "memoryBytesTotal":
			return Cluster.staticSearchMemoryBytesTotal(siteRequest_, (Long)o);
		case "grafanaUrl":
			return Cluster.staticSearchGrafanaUrl(siteRequest_, (String)o);
		case "promKeycloakProxySsl":
			return Cluster.staticSearchPromKeycloakProxySsl(siteRequest_, (Boolean)o);
		case "promKeycloakProxyPort":
			return Cluster.staticSearchPromKeycloakProxyPort(siteRequest_, (Integer)o);
		case "promKeycloakProxyHostName":
			return Cluster.staticSearchPromKeycloakProxyHostName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCluster(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCluster(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "hubId":
			return Cluster.staticSearchStrHubId(siteRequest_, (String)o);
		case "hubResource":
			return Cluster.staticSearchStrHubResource(siteRequest_, (String)o);
		case "clusterName":
			return Cluster.staticSearchStrClusterName(siteRequest_, (String)o);
		case "clusterResource":
			return Cluster.staticSearchStrClusterResource(siteRequest_, (String)o);
		case "uniqueName":
			return Cluster.staticSearchStrUniqueName(siteRequest_, (String)o);
		case "description":
			return Cluster.staticSearchStrDescription(siteRequest_, (String)o);
		case "locationColors":
			return Cluster.staticSearchStrLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return Cluster.staticSearchStrLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return Cluster.staticSearchStrLocationLinks(siteRequest_, (String)o);
		case "location":
			return Cluster.staticSearchStrLocation(siteRequest_, (Point)o);
		case "id":
			return Cluster.staticSearchStrId(siteRequest_, (String)o);
		case "entityShortId":
			return Cluster.staticSearchStrEntityShortId(siteRequest_, (String)o);
		case "ngsildTenant":
			return Cluster.staticSearchStrNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return Cluster.staticSearchStrNgsildPath(siteRequest_, (String)o);
		case "ngsildContext":
			return Cluster.staticSearchStrNgsildContext(siteRequest_, (String)o);
		case "ngsildData":
			return Cluster.staticSearchStrNgsildData(siteRequest_, (String)o);
		case "aiNodesTotal":
			return Cluster.staticSearchStrAiNodesTotal(siteRequest_, (Integer)o);
		case "gpuDevicesTotal":
			return Cluster.staticSearchStrGpuDevicesTotal(siteRequest_, (Integer)o);
		case "cpuCoresTotal":
			return Cluster.staticSearchStrCpuCoresTotal(siteRequest_, (Integer)o);
		case "memoryBytesTotal":
			return Cluster.staticSearchStrMemoryBytesTotal(siteRequest_, (Long)o);
		case "grafanaUrl":
			return Cluster.staticSearchStrGrafanaUrl(siteRequest_, (String)o);
		case "promKeycloakProxySsl":
			return Cluster.staticSearchStrPromKeycloakProxySsl(siteRequest_, (Boolean)o);
		case "promKeycloakProxyPort":
			return Cluster.staticSearchStrPromKeycloakProxyPort(siteRequest_, (Integer)o);
		case "promKeycloakProxyHostName":
			return Cluster.staticSearchStrPromKeycloakProxyHostName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCluster(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCluster(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "hubId":
			return Cluster.staticSearchFqHubId(siteRequest_, o);
		case "hubResource":
			return Cluster.staticSearchFqHubResource(siteRequest_, o);
		case "clusterName":
			return Cluster.staticSearchFqClusterName(siteRequest_, o);
		case "clusterResource":
			return Cluster.staticSearchFqClusterResource(siteRequest_, o);
		case "uniqueName":
			return Cluster.staticSearchFqUniqueName(siteRequest_, o);
		case "description":
			return Cluster.staticSearchFqDescription(siteRequest_, o);
		case "locationColors":
			return Cluster.staticSearchFqLocationColors(siteRequest_, o);
		case "locationTitles":
			return Cluster.staticSearchFqLocationTitles(siteRequest_, o);
		case "locationLinks":
			return Cluster.staticSearchFqLocationLinks(siteRequest_, o);
		case "location":
			return Cluster.staticSearchFqLocation(siteRequest_, o);
		case "id":
			return Cluster.staticSearchFqId(siteRequest_, o);
		case "entityShortId":
			return Cluster.staticSearchFqEntityShortId(siteRequest_, o);
		case "ngsildTenant":
			return Cluster.staticSearchFqNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return Cluster.staticSearchFqNgsildPath(siteRequest_, o);
		case "ngsildContext":
			return Cluster.staticSearchFqNgsildContext(siteRequest_, o);
		case "ngsildData":
			return Cluster.staticSearchFqNgsildData(siteRequest_, o);
		case "aiNodesTotal":
			return Cluster.staticSearchFqAiNodesTotal(siteRequest_, o);
		case "gpuDevicesTotal":
			return Cluster.staticSearchFqGpuDevicesTotal(siteRequest_, o);
		case "cpuCoresTotal":
			return Cluster.staticSearchFqCpuCoresTotal(siteRequest_, o);
		case "memoryBytesTotal":
			return Cluster.staticSearchFqMemoryBytesTotal(siteRequest_, o);
		case "grafanaUrl":
			return Cluster.staticSearchFqGrafanaUrl(siteRequest_, o);
		case "promKeycloakProxySsl":
			return Cluster.staticSearchFqPromKeycloakProxySsl(siteRequest_, o);
		case "promKeycloakProxyPort":
			return Cluster.staticSearchFqPromKeycloakProxyPort(siteRequest_, o);
		case "promKeycloakProxyHostName":
			return Cluster.staticSearchFqPromKeycloakProxyHostName(siteRequest_, o);
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
					o = persistCluster(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistCluster(String var, Object val) {
		String varLower = var.toLowerCase();
			if("hubid".equals(varLower)) {
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
			} else if("clustername".equals(varLower)) {
				if(val instanceof String) {
					setClusterName((String)val);
				}
				saves.add("clusterName");
				return val;
			} else if("clusterresource".equals(varLower)) {
				if(val instanceof String) {
					setClusterResource((String)val);
				}
				saves.add("clusterResource");
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
			} else if("cpucorestotal".equals(varLower)) {
				if(val instanceof Integer) {
					setCpuCoresTotal((Integer)val);
				} else {
					setCpuCoresTotal(val == null ? null : val.toString());
				}
				saves.add("cpuCoresTotal");
				return val;
			} else if("memorybytestotal".equals(varLower)) {
				if(val instanceof Long) {
					setMemoryBytesTotal((Long)val);
				} else {
					setMemoryBytesTotal(val == null ? null : val.toString());
				}
				saves.add("memoryBytesTotal");
				return val;
			} else if("promkeycloakproxyssl".equals(varLower)) {
				if(val instanceof Boolean) {
					setPromKeycloakProxySsl((Boolean)val);
				} else {
					setPromKeycloakProxySsl(val == null ? null : val.toString());
				}
				saves.add("promKeycloakProxySsl");
				return val;
			} else if("promkeycloakproxyport".equals(varLower)) {
				if(val instanceof Integer) {
					setPromKeycloakProxyPort((Integer)val);
				} else {
					setPromKeycloakProxyPort(val == null ? null : val.toString());
				}
				saves.add("promKeycloakProxyPort");
				return val;
			} else if("promkeycloakproxyhostname".equals(varLower)) {
				if(val instanceof String) {
					setPromKeycloakProxyHostName((String)val);
				}
				saves.add("promKeycloakProxyHostName");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateCluster(doc);
	}
	public void populateCluster(SolrResponse.Doc doc) {
		Cluster oCluster = (Cluster)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("hubId")) {
				String hubId = (String)doc.get("hubId_docvalues_string");
				if(hubId != null)
					oCluster.setHubId(hubId);
			}

			String hubResource = (String)doc.get("hubResource_docvalues_string");
			if(hubResource != null)
				oCluster.setHubResource(hubResource);

			if(saves.contains("clusterName")) {
				String clusterName = (String)doc.get("clusterName_docvalues_string");
				if(clusterName != null)
					oCluster.setClusterName(clusterName);
			}

			if(saves.contains("clusterResource")) {
				String clusterResource = (String)doc.get("clusterResource_docvalues_string");
				if(clusterResource != null)
					oCluster.setClusterResource(clusterResource);
			}

			if(saves.contains("uniqueName")) {
				String uniqueName = (String)doc.get("uniqueName_docvalues_string");
				if(uniqueName != null)
					oCluster.setUniqueName(uniqueName);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oCluster.setDescription(description);
			}

			if(saves.contains("locationColors")) {
				List<String> locationColors = (List<String>)doc.get("locationColors_indexedstored_strings");
				if(locationColors != null) {
					locationColors.stream().forEach( v -> {
						oCluster.locationColors.add(Cluster.staticSetLocationColors(siteRequest_, v));
					});
				}
			}

			if(saves.contains("locationTitles")) {
				List<String> locationTitles = (List<String>)doc.get("locationTitles_indexedstored_strings");
				if(locationTitles != null) {
					locationTitles.stream().forEach( v -> {
						oCluster.locationTitles.add(Cluster.staticSetLocationTitles(siteRequest_, v));
					});
				}
			}

			if(saves.contains("locationLinks")) {
				List<String> locationLinks = (List<String>)doc.get("locationLinks_indexedstored_strings");
				if(locationLinks != null) {
					locationLinks.stream().forEach( v -> {
						oCluster.locationLinks.add(Cluster.staticSetLocationLinks(siteRequest_, v));
					});
				}
			}

			if(saves.contains("location")) {
				Point location = (Point)doc.get("location_docvalues_location");
				if(location != null)
					oCluster.setLocation(location);
			}

			if(saves.contains("id")) {
				String id = (String)doc.get("id_docvalues_string");
				if(id != null)
					oCluster.setId(id);
			}

			if(saves.contains("entityShortId")) {
				String entityShortId = (String)doc.get("entityShortId_docvalues_string");
				if(entityShortId != null)
					oCluster.setEntityShortId(entityShortId);
			}

			if(saves.contains("ngsildTenant")) {
				String ngsildTenant = (String)doc.get("ngsildTenant_docvalues_string");
				if(ngsildTenant != null)
					oCluster.setNgsildTenant(ngsildTenant);
			}

			if(saves.contains("ngsildPath")) {
				String ngsildPath = (String)doc.get("ngsildPath_docvalues_string");
				if(ngsildPath != null)
					oCluster.setNgsildPath(ngsildPath);
			}

			if(saves.contains("ngsildContext")) {
				String ngsildContext = (String)doc.get("ngsildContext_docvalues_string");
				if(ngsildContext != null)
					oCluster.setNgsildContext(ngsildContext);
			}

			if(saves.contains("ngsildData")) {
				String ngsildData = (String)doc.get("ngsildData_docvalues_string");
				if(ngsildData != null)
					oCluster.setNgsildData(ngsildData);
			}

			if(saves.contains("aiNodesTotal")) {
				Integer aiNodesTotal = (Integer)doc.get("aiNodesTotal_docvalues_int");
				if(aiNodesTotal != null)
					oCluster.setAiNodesTotal(aiNodesTotal);
			}

			if(saves.contains("gpuDevicesTotal")) {
				Integer gpuDevicesTotal = (Integer)doc.get("gpuDevicesTotal_docvalues_int");
				if(gpuDevicesTotal != null)
					oCluster.setGpuDevicesTotal(gpuDevicesTotal);
			}

			if(saves.contains("cpuCoresTotal")) {
				Integer cpuCoresTotal = (Integer)doc.get("cpuCoresTotal_docvalues_int");
				if(cpuCoresTotal != null)
					oCluster.setCpuCoresTotal(cpuCoresTotal);
			}

			if(saves.contains("memoryBytesTotal")) {
				Long memoryBytesTotal = (Long)doc.get("memoryBytesTotal_docvalues_long");
				if(memoryBytesTotal != null)
					oCluster.setMemoryBytesTotal(memoryBytesTotal);
			}

			if(saves.contains("grafanaUrl")) {
				String grafanaUrl = (String)doc.get("grafanaUrl_docvalues_string");
				if(grafanaUrl != null)
					oCluster.setGrafanaUrl(grafanaUrl);
			}

			if(saves.contains("promKeycloakProxySsl")) {
				Boolean promKeycloakProxySsl = (Boolean)doc.get("promKeycloakProxySsl_docvalues_boolean");
				if(promKeycloakProxySsl != null)
					oCluster.setPromKeycloakProxySsl(promKeycloakProxySsl);
			}

			if(saves.contains("promKeycloakProxyPort")) {
				Integer promKeycloakProxyPort = (Integer)doc.get("promKeycloakProxyPort_docvalues_int");
				if(promKeycloakProxyPort != null)
					oCluster.setPromKeycloakProxyPort(promKeycloakProxyPort);
			}

			if(saves.contains("promKeycloakProxyHostName")) {
				String promKeycloakProxyHostName = (String)doc.get("promKeycloakProxyHostName_docvalues_string");
				if(promKeycloakProxyHostName != null)
					oCluster.setPromKeycloakProxyHostName(promKeycloakProxyHostName);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexCluster(JsonObject doc) {
		if(hubId != null) {
			doc.put("hubId_docvalues_string", hubId);
		}
		if(hubResource != null) {
			doc.put("hubResource_docvalues_string", hubResource);
		}
		if(clusterName != null) {
			doc.put("clusterName_docvalues_string", clusterName);
		}
		if(clusterResource != null) {
			doc.put("clusterResource_docvalues_string", clusterResource);
		}
		if(uniqueName != null) {
			doc.put("uniqueName_docvalues_string", uniqueName);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(locationColors != null) {
			JsonArray l = new JsonArray();
			doc.put("locationColors_indexedstored_strings", l);
			for(String o : locationColors) {
				l.add(Cluster.staticSearchLocationColors(siteRequest_, o));
			}
		}
		if(locationTitles != null) {
			JsonArray l = new JsonArray();
			doc.put("locationTitles_indexedstored_strings", l);
			for(String o : locationTitles) {
				l.add(Cluster.staticSearchLocationTitles(siteRequest_, o));
			}
		}
		if(locationLinks != null) {
			JsonArray l = new JsonArray();
			doc.put("locationLinks_indexedstored_strings", l);
			for(String o : locationLinks) {
				l.add(Cluster.staticSearchLocationLinks(siteRequest_, o));
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
			doc.put("ngsildData_docvalues_string", ngsildData.encode());
		}
		if(aiNodesTotal != null) {
			doc.put("aiNodesTotal_docvalues_int", aiNodesTotal);
		}
		if(gpuDevicesTotal != null) {
			doc.put("gpuDevicesTotal_docvalues_int", gpuDevicesTotal);
		}
		if(cpuCoresTotal != null) {
			doc.put("cpuCoresTotal_docvalues_int", cpuCoresTotal);
		}
		if(memoryBytesTotal != null) {
			doc.put("memoryBytesTotal_docvalues_long", memoryBytesTotal);
		}
		if(grafanaUrl != null) {
			doc.put("grafanaUrl_docvalues_string", grafanaUrl);
		}
		if(promKeycloakProxySsl != null) {
			doc.put("promKeycloakProxySsl_docvalues_boolean", promKeycloakProxySsl);
		}
		if(promKeycloakProxyPort != null) {
			doc.put("promKeycloakProxyPort_docvalues_int", promKeycloakProxyPort);
		}
		if(promKeycloakProxyHostName != null) {
			doc.put("promKeycloakProxyHostName_docvalues_string", promKeycloakProxyHostName);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredCluster(String entityVar) {
		switch(entityVar) {
			case "hubId":
				return "hubId_docvalues_string";
			case "hubResource":
				return "hubResource_docvalues_string";
			case "clusterName":
				return "clusterName_docvalues_string";
			case "clusterResource":
				return "clusterResource_docvalues_string";
			case "uniqueName":
				return "uniqueName_docvalues_string";
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
			case "cpuCoresTotal":
				return "cpuCoresTotal_docvalues_int";
			case "memoryBytesTotal":
				return "memoryBytesTotal_docvalues_long";
			case "grafanaUrl":
				return "grafanaUrl_docvalues_string";
			case "promKeycloakProxySsl":
				return "promKeycloakProxySsl_docvalues_boolean";
			case "promKeycloakProxyPort":
				return "promKeycloakProxyPort_docvalues_int";
			case "promKeycloakProxyHostName":
				return "promKeycloakProxyHostName_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedCluster(String entityVar) {
		switch(entityVar) {
			case "hubId":
				return "hubId_docvalues_string";
			case "hubResource":
				return "hubResource_docvalues_string";
			case "clusterName":
				return "clusterName_docvalues_string";
			case "clusterResource":
				return "clusterResource_docvalues_string";
			case "uniqueName":
				return "uniqueName_docvalues_string";
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
			case "cpuCoresTotal":
				return "cpuCoresTotal_docvalues_int";
			case "memoryBytesTotal":
				return "memoryBytesTotal_docvalues_long";
			case "grafanaUrl":
				return "grafanaUrl_docvalues_string";
			case "promKeycloakProxySsl":
				return "promKeycloakProxySsl_docvalues_boolean";
			case "promKeycloakProxyPort":
				return "promKeycloakProxyPort_docvalues_int";
			case "promKeycloakProxyHostName":
				return "promKeycloakProxyHostName_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarCluster(String searchVar) {
		switch(searchVar) {
			case "hubId_docvalues_string":
				return "hubId";
			case "hubResource_docvalues_string":
				return "hubResource";
			case "clusterName_docvalues_string":
				return "clusterName";
			case "clusterResource_docvalues_string":
				return "clusterResource";
			case "uniqueName_docvalues_string":
				return "uniqueName";
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
			case "cpuCoresTotal_docvalues_int":
				return "cpuCoresTotal";
			case "memoryBytesTotal_docvalues_long":
				return "memoryBytesTotal";
			case "grafanaUrl_docvalues_string":
				return "grafanaUrl";
			case "promKeycloakProxySsl_docvalues_boolean":
				return "promKeycloakProxySsl";
			case "promKeycloakProxyPort_docvalues_int":
				return "promKeycloakProxyPort";
			case "promKeycloakProxyHostName_docvalues_string":
				return "promKeycloakProxyHostName";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchCluster(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedCluster(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeCluster(doc);
	}
	public void storeCluster(SolrResponse.Doc doc) {
		Cluster oCluster = (Cluster)this;
		SiteRequest siteRequest = oCluster.getSiteRequest_();

		oCluster.setHubId(Optional.ofNullable(doc.get("hubId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setHubResource(Optional.ofNullable(doc.get("hubResource_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setClusterName(Optional.ofNullable(doc.get("clusterName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setClusterResource(Optional.ofNullable(doc.get("clusterResource_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setUniqueName(Optional.ofNullable(doc.get("uniqueName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("locationColors_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oCluster.addLocationColors(Cluster.staticSetLocationColors(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationTitles_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oCluster.addLocationTitles(Cluster.staticSetLocationTitles(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationLinks_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oCluster.addLocationLinks(Cluster.staticSetLocationLinks(siteRequest, v.toString()));
		});
		oCluster.setLocation(Optional.ofNullable(doc.get("location_docvalues_location")).map(v -> v.toString()).orElse(null));
		oCluster.setId(Optional.ofNullable(doc.get("id_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setEntityShortId(Optional.ofNullable(doc.get("entityShortId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setNgsildTenant(Optional.ofNullable(doc.get("ngsildTenant_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setNgsildPath(Optional.ofNullable(doc.get("ngsildPath_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setNgsildContext(Optional.ofNullable(doc.get("ngsildContext_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setNgsildData(Optional.ofNullable(doc.get("ngsildData_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setAiNodesTotal(Optional.ofNullable(doc.get("aiNodesTotal_docvalues_int")).map(v -> v.toString()).orElse(null));
		oCluster.setGpuDevicesTotal(Optional.ofNullable(doc.get("gpuDevicesTotal_docvalues_int")).map(v -> v.toString()).orElse(null));
		oCluster.setCpuCoresTotal(Optional.ofNullable(doc.get("cpuCoresTotal_docvalues_int")).map(v -> v.toString()).orElse(null));
		oCluster.setMemoryBytesTotal(Optional.ofNullable(doc.get("memoryBytesTotal_docvalues_long")).map(v -> v.toString()).orElse(null));
		oCluster.setGrafanaUrl(Optional.ofNullable(doc.get("grafanaUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCluster.setPromKeycloakProxySsl(Optional.ofNullable(doc.get("promKeycloakProxySsl_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oCluster.setPromKeycloakProxyPort(Optional.ofNullable(doc.get("promKeycloakProxyPort_docvalues_int")).map(v -> v.toString()).orElse(null));
		oCluster.setPromKeycloakProxyHostName(Optional.ofNullable(doc.get("promKeycloakProxyHostName_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestCluster() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof Cluster) {
			Cluster original = (Cluster)o;
			if(!Objects.equals(hubId, original.getHubId()))
				apiRequest.addVars("hubId");
			if(!Objects.equals(hubResource, original.getHubResource()))
				apiRequest.addVars("hubResource");
			if(!Objects.equals(clusterName, original.getClusterName()))
				apiRequest.addVars("clusterName");
			if(!Objects.equals(clusterResource, original.getClusterResource()))
				apiRequest.addVars("clusterResource");
			if(!Objects.equals(uniqueName, original.getUniqueName()))
				apiRequest.addVars("uniqueName");
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
			if(!Objects.equals(cpuCoresTotal, original.getCpuCoresTotal()))
				apiRequest.addVars("cpuCoresTotal");
			if(!Objects.equals(memoryBytesTotal, original.getMemoryBytesTotal()))
				apiRequest.addVars("memoryBytesTotal");
			if(!Objects.equals(grafanaUrl, original.getGrafanaUrl()))
				apiRequest.addVars("grafanaUrl");
			if(!Objects.equals(promKeycloakProxySsl, original.getPromKeycloakProxySsl()))
				apiRequest.addVars("promKeycloakProxySsl");
			if(!Objects.equals(promKeycloakProxyPort, original.getPromKeycloakProxyPort()))
				apiRequest.addVars("promKeycloakProxyPort");
			if(!Objects.equals(promKeycloakProxyHostName, original.getPromKeycloakProxyHostName()))
				apiRequest.addVars("promKeycloakProxyHostName");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(hubId).map(v -> "hubId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(hubResource).map(v -> "hubResource: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(clusterName).map(v -> "clusterName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(clusterResource).map(v -> "clusterResource: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(uniqueName).map(v -> "uniqueName: \"" + v + "\"\n" ).orElse(""));
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
		sb.append(Optional.ofNullable(cpuCoresTotal).map(v -> "cpuCoresTotal: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(memoryBytesTotal).map(v -> "memoryBytesTotal: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(grafanaUrl).map(v -> "grafanaUrl: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(promKeycloakProxySsl).map(v -> "promKeycloakProxySsl: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(promKeycloakProxyPort).map(v -> "promKeycloakProxyPort: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(promKeycloakProxyHostName).map(v -> "promKeycloakProxyHostName: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "Cluster";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.cluster.Cluster";
	public static final String CLASS_AUTH_RESOURCE = "CLUSTER";
	public static final String CLASS_API_ADDRESS_Cluster = "ai-telemetry-enUS-Cluster";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_Cluster;
	}
	public static final String VAR_hubId = "hubId";
	public static final String VAR_hubResource = "hubResource";
	public static final String VAR_clusterName = "clusterName";
	public static final String VAR_clusterResource = "clusterResource";
	public static final String VAR_uniqueName = "uniqueName";
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
	public static final String VAR_cpuCoresTotal = "cpuCoresTotal";
	public static final String VAR_memoryBytesTotal = "memoryBytesTotal";
	public static final String VAR_grafanaUrl = "grafanaUrl";
	public static final String VAR_promKeycloakProxySsl = "promKeycloakProxySsl";
	public static final String VAR_promKeycloakProxyPort = "promKeycloakProxyPort";
	public static final String VAR_promKeycloakProxyHostName = "promKeycloakProxyHostName";

	public static List<String> varsQForClass() {
		return Cluster.varsQCluster(new ArrayList<String>());
	}
	public static List<String> varsQCluster(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return Cluster.varsFqCluster(new ArrayList<String>());
	}
	public static List<String> varsFqCluster(List<String> vars) {
		vars.add(VAR_hubId);
		vars.add(VAR_hubResource);
		vars.add(VAR_clusterName);
		vars.add(VAR_clusterResource);
		vars.add(VAR_uniqueName);
		vars.add(VAR_location);
		vars.add(VAR_id);
		vars.add(VAR_entityShortId);
		vars.add(VAR_ngsildTenant);
		vars.add(VAR_ngsildPath);
		vars.add(VAR_ngsildContext);
		vars.add(VAR_ngsildData);
		vars.add(VAR_aiNodesTotal);
		vars.add(VAR_gpuDevicesTotal);
		vars.add(VAR_cpuCoresTotal);
		vars.add(VAR_memoryBytesTotal);
		vars.add(VAR_grafanaUrl);
		vars.add(VAR_promKeycloakProxySsl);
		vars.add(VAR_promKeycloakProxyPort);
		vars.add(VAR_promKeycloakProxyHostName);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return Cluster.varsRangeCluster(new ArrayList<String>());
	}
	public static List<String> varsRangeCluster(List<String> vars) {
		vars.add(VAR_location);
		vars.add(VAR_ngsildData);
		vars.add(VAR_aiNodesTotal);
		vars.add(VAR_gpuDevicesTotal);
		vars.add(VAR_cpuCoresTotal);
		vars.add(VAR_memoryBytesTotal);
		vars.add(VAR_promKeycloakProxyPort);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_hubId = "ACM Hub";
	public static final String DISPLAY_NAME_hubResource = "hub auth resource";
	public static final String DISPLAY_NAME_clusterName = "cluster name";
	public static final String DISPLAY_NAME_clusterResource = "cluster auth resource";
	public static final String DISPLAY_NAME_uniqueName = "unique name";
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
	public static final String DISPLAY_NAME_cpuCoresTotal = "CPU cores total";
	public static final String DISPLAY_NAME_memoryBytesTotal = "memory bytes total";
	public static final String DISPLAY_NAME_grafanaUrl = "Grafana GPU utilization";
	public static final String DISPLAY_NAME_promKeycloakProxySsl = "proxy SSL";
	public static final String DISPLAY_NAME_promKeycloakProxyPort = "proxy Port";
	public static final String DISPLAY_NAME_promKeycloakProxyHostName = "proxy host name";

	@Override
	public String idForClass() {
		return clusterResource;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return uniqueName;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return Cluster.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/cluster/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/cluster/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return Cluster.displayNameCluster(var);
	}
	public static String displayNameCluster(String var) {
		switch(var) {
		case VAR_hubId:
			return DISPLAY_NAME_hubId;
		case VAR_hubResource:
			return DISPLAY_NAME_hubResource;
		case VAR_clusterName:
			return DISPLAY_NAME_clusterName;
		case VAR_clusterResource:
			return DISPLAY_NAME_clusterResource;
		case VAR_uniqueName:
			return DISPLAY_NAME_uniqueName;
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
		case VAR_cpuCoresTotal:
			return DISPLAY_NAME_cpuCoresTotal;
		case VAR_memoryBytesTotal:
			return DISPLAY_NAME_memoryBytesTotal;
		case VAR_grafanaUrl:
			return DISPLAY_NAME_grafanaUrl;
		case VAR_promKeycloakProxySsl:
			return DISPLAY_NAME_promKeycloakProxySsl;
		case VAR_promKeycloakProxyPort:
			return DISPLAY_NAME_promKeycloakProxyPort;
		case VAR_promKeycloakProxyHostName:
			return DISPLAY_NAME_promKeycloakProxyHostName;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionCluster(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_hubId:
			return "The name of the ACM Hub for this cluster in Prometheus Keycloak Proxy. ";
		case VAR_hubResource:
			return "The unique authorization resource for the hub for multi-tenancy";
		case VAR_clusterName:
			return "The name of this cluster";
		case VAR_clusterResource:
			return "The unique authorization resource for the cluster for multi-tenancy";
		case VAR_uniqueName:
			return "The unique name of this cluster";
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
		case VAR_cpuCoresTotal:
			return "The total number of CPU cores on this cluster. ";
		case VAR_memoryBytesTotal:
			return "The total number of memory bytes on this cluster. ";
		case VAR_grafanaUrl:
			return "Explore this cluster's GPU utilization in Grafana. ";
		case VAR_promKeycloakProxySsl:
			return "Whether to enable SSL for accessing the Prometheus Keycloak Proxy for this ACM Hub. ";
		case VAR_promKeycloakProxyPort:
			return "The port for accessing the Prometheus Keycloak Proxy for this ACM Hub. ";
		case VAR_promKeycloakProxyHostName:
			return "The host name for accessing the Prometheus Keycloak Proxy for this ACM Hub. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameCluster(String var) {
		switch(var) {
		case VAR_hubId:
			return "String";
		case VAR_hubResource:
			return "String";
		case VAR_clusterName:
			return "String";
		case VAR_clusterResource:
			return "String";
		case VAR_uniqueName:
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
		case VAR_cpuCoresTotal:
			return "Integer";
		case VAR_memoryBytesTotal:
			return "Long";
		case VAR_grafanaUrl:
			return "String";
		case VAR_promKeycloakProxySsl:
			return "Boolean";
		case VAR_promKeycloakProxyPort:
			return "Integer";
		case VAR_promKeycloakProxyHostName:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnCluster(String var) {
		switch(var) {
		case VAR_hubId:
			return 1;
		case VAR_clusterName:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowCluster(String var) {
		switch(var) {
		case VAR_hubId:
			return 3;
		case VAR_clusterName:
			return 3;
		case VAR_uniqueName:
			return 3;
		case VAR_description:
			return 3;
		case VAR_location:
			return 3;
		case VAR_id:
			return 5;
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
		case VAR_cpuCoresTotal:
			return 4;
		case VAR_memoryBytesTotal:
			return 4;
		case VAR_grafanaUrl:
			return 4;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellCluster(String var) {
		switch(var) {
		case VAR_hubId:
			return 1;
		case VAR_clusterName:
			return 3;
		case VAR_uniqueName:
			return 5;
		case VAR_description:
			return 6;
		case VAR_location:
			return 5;
		case VAR_id:
			return 1;
		case VAR_ngsildTenant:
			return 2;
		case VAR_ngsildPath:
			return 3;
		case VAR_ngsildContext:
			return 4;
		case VAR_ngsildData:
			return 5;
		case VAR_aiNodesTotal:
			return 1;
		case VAR_gpuDevicesTotal:
			return 2;
		case VAR_cpuCoresTotal:
			return 4;
		case VAR_memoryBytesTotal:
			return 4;
		case VAR_grafanaUrl:
			return 3;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinCluster(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxCluster(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxCluster(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minCluster(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
