package org.mghpcc.aitelemetry.model.baremetalnode;

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
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import java.lang.String;
import java.lang.Integer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalNodeGen into the class BareMetalNode. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the BareMetalNode API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the BareMetalNode class and it's generated class BareMetalNodeGen&lt;BaseModel&gt;: </h3>extends BareMetalNodeGen
 * <p>
 * This Java class extends a generated Java class BareMetalNodeGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode">Find the class BareMetalNode in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalNodeGen<BaseModel>
 * <p>This <code>class BareMetalNode extends BareMetalNodeGen&lt;BaseModel&gt;</code>, which means it extends a newly generated BareMetalNodeGen. 
 * The generated <code>class BareMetalNodeGen extends BaseModel</code> which means that BareMetalNode extends BareMetalNodeGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: bare metal nodes"</b>, which groups all of the OpenAPIs for BareMetalNode objects under the tag "bare metal nodes". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/bare-metal-node</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/bare-metal-node"</b>, which defines the base API URI for BareMetalNode objects as "/en-us/api/bare-metal-node" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalNode class will inherit the helpful inherited class comments from the super class BareMetalNodeGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 11</h2>
 * <p>This class contains a comment <b>"Order: 11"</b>, which means this class will be sorted by the given number 11 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodePage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalNode Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a bare metal node</h2>
 * <p>This class contains a comment <b>"AName.enUS: a bare metal node"</b>, which identifies the language context to describe a BareMetalNode as "a bare metal node". 
 * </p>
 * <p>
 * Delete the class BareMetalNode in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.baremetalnode in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BareMetalNodeGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalNode.class);
	public static final String nodeESI1_enUS = "node";
	public static final String nodeESI_enUS = nodeESI1_enUS;

	public static final String Description_enUS = "An OpenStack bare metal node";
	public static final String AName_enUS = "a bare metal node";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this bare metal node";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the bare metal node";
	public static final String SingularName_enUS = "bare metal node";
	public static final String PluralName_enUS = "bare metal nodes";
	public static final String NameActual_enUS = "current bare metal node";
	public static final String AllName_enUS = "all bare metal nodes";
	public static final String SearchAllNameBy_enUS = "search bare metal nodes by ";
	public static final String Title_enUS = "bare metal nodes";
	public static final String ThePluralName_enUS = "the bare metal nodes";
	public static final String NoNameFound_enUS = "no bare metal node found";
	public static final String ApiUri_enUS = "/en-us/api/bare-metal-node";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/bare-metal-node";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/bare-metal-node/{nodeId}";
	public static final String OfName_enUS = "of bare metal node";
	public static final String ANameAdjective_enUS = "a bare metal node";
	public static final String NameAdjectiveSingular_enUS = "bare metal node";
	public static final String NameAdjectivePlural_enUS = "bare metal nodes";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/bare-metal-node";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/bare-metal-node";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-node";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/bare-metal-node/{nodeId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/bare-metal-node/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-node/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/bare-metal-node";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/bare-metal-node";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-node";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/bare-metal-node";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/bare-metal-node";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-node";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/bare-metal-node/{nodeId}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/bare-metal-node/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-node/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/bare-metal-node-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/bare-metal-node-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-node-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/bare-metal-node";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/bare-metal-node";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/bare-metal-node";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/bare-metal-node/{nodeId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/bare-metal-node/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/bare-metal-node/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/bare-metal-node";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/bare-metal-node";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-node";

	public static final String Icon = "<i class=\"fa-duotone fa-regular fa-hexagon-nodes\"></i>";

	///////////////
	// leaseInfo //
	///////////////

	public static final String leaseInfoESI1_enUS = "lease_info";
	public static final String leaseInfoESI_enUS = leaseInfoESI1_enUS;

	/**	 The entity leaseInfo
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray leaseInfo;

	/**	<br> The entity leaseInfo
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:leaseInfo">Find the entity leaseInfo in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _leaseInfo(Wrap<JsonArray> w);

	public JsonArray getLeaseInfo() {
		return leaseInfo;
	}

	public void setLeaseInfo(JsonArray leaseInfo) {
		this.leaseInfo = leaseInfo;
	}
	@JsonIgnore
	public void setLeaseInfo(String o) {
		this.leaseInfo = BareMetalNode.staticSetLeaseInfo(siteRequest_, o);
	}
	public static JsonArray staticSetLeaseInfo(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BareMetalNode leaseInfoInit() {
		Wrap<JsonArray> leaseInfoWrap = new Wrap<JsonArray>().var("leaseInfo");
		if(leaseInfo == null) {
			_leaseInfo(leaseInfoWrap);
			Optional.ofNullable(leaseInfoWrap.getO()).ifPresent(o -> {
				setLeaseInfo(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static String staticSearchLeaseInfo(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrLeaseInfo(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLeaseInfo(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchLeaseInfo(siteRequest_, BareMetalNode.staticSetLeaseInfo(siteRequest_, o)).toString();
	}

	public JsonArray sqlLeaseInfo() {
		return leaseInfo;
	}

	/////////////////
	// networkInfo //
	/////////////////

	public static final String networkInfoESI1_enUS = "network_info";
	public static final String networkInfoESI_enUS = networkInfoESI1_enUS;

	/**	 The entity networkInfo
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray networkInfo;

	/**	<br> The entity networkInfo
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:networkInfo">Find the entity networkInfo in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _networkInfo(Wrap<JsonArray> w);

	public JsonArray getNetworkInfo() {
		return networkInfo;
	}

	public void setNetworkInfo(JsonArray networkInfo) {
		this.networkInfo = networkInfo;
	}
	@JsonIgnore
	public void setNetworkInfo(String o) {
		this.networkInfo = BareMetalNode.staticSetNetworkInfo(siteRequest_, o);
	}
	public static JsonArray staticSetNetworkInfo(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BareMetalNode networkInfoInit() {
		Wrap<JsonArray> networkInfoWrap = new Wrap<JsonArray>().var("networkInfo");
		if(networkInfo == null) {
			_networkInfo(networkInfoWrap);
			Optional.ofNullable(networkInfoWrap.getO()).ifPresent(o -> {
				setNetworkInfo(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static String staticSearchNetworkInfo(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrNetworkInfo(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNetworkInfo(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchNetworkInfo(siteRequest_, BareMetalNode.staticSetNetworkInfo(siteRequest_, o)).toString();
	}

	public JsonArray sqlNetworkInfo() {
		return networkInfo;
	}

	////////////
	// nodeId //
	////////////

	public static final String nodeIdESI1_enUS = "id";
	public static final String nodeIdESI_enUS = nodeIdESI1_enUS;

	/**	 The entity nodeId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String nodeId;

	/**	<br> The entity nodeId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:nodeId">Find the entity nodeId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeId(Wrap<String> w);

	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String o) {
		this.nodeId = BareMetalNode.staticSetNodeId(siteRequest_, o);
	}
	public static String staticSetNodeId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNode nodeIdInit() {
		Wrap<String> nodeIdWrap = new Wrap<String>().var("nodeId");
		if(nodeId == null) {
			_nodeId(nodeIdWrap);
			Optional.ofNullable(nodeIdWrap.getO()).ifPresent(o -> {
				setNodeId(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static String staticSearchNodeId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNodeId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeId(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchNodeId(siteRequest_, BareMetalNode.staticSetNodeId(siteRequest_, o)).toString();
	}

	public String sqlNodeId() {
		return nodeId;
	}

	///////////////////////
	// nodeIsMaintenance //
	///////////////////////

	public static final String nodeIsMaintenanceESI1_enUS = "is_maintenance";
	public static final String nodeIsMaintenanceESI_enUS = nodeIsMaintenanceESI1_enUS;

	/**	 The entity nodeIsMaintenance
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer nodeIsMaintenance;

	/**	<br> The entity nodeIsMaintenance
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:nodeIsMaintenance">Find the entity nodeIsMaintenance in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeIsMaintenance(Wrap<Integer> w);

	public Integer getNodeIsMaintenance() {
		return nodeIsMaintenance;
	}

	public void setNodeIsMaintenance(Integer nodeIsMaintenance) {
		this.nodeIsMaintenance = nodeIsMaintenance;
	}
	@JsonIgnore
	public void setNodeIsMaintenance(String o) {
		this.nodeIsMaintenance = BareMetalNode.staticSetNodeIsMaintenance(siteRequest_, o);
	}
	public static Integer staticSetNodeIsMaintenance(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalNode nodeIsMaintenanceInit() {
		Wrap<Integer> nodeIsMaintenanceWrap = new Wrap<Integer>().var("nodeIsMaintenance");
		if(nodeIsMaintenance == null) {
			_nodeIsMaintenance(nodeIsMaintenanceWrap);
			Optional.ofNullable(nodeIsMaintenanceWrap.getO()).ifPresent(o -> {
				setNodeIsMaintenance(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static Integer staticSearchNodeIsMaintenance(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrNodeIsMaintenance(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeIsMaintenance(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchNodeIsMaintenance(siteRequest_, BareMetalNode.staticSetNodeIsMaintenance(siteRequest_, o)).toString();
	}

	public Integer sqlNodeIsMaintenance() {
		return nodeIsMaintenance;
	}

	///////////////
	// nodeLinks //
	///////////////

	public static final String nodeLinksESI1_enUS = "links";
	public static final String nodeLinksESI_enUS = nodeLinksESI1_enUS;

	/**	 The entity nodeLinks
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray nodeLinks;

	/**	<br> The entity nodeLinks
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:nodeLinks">Find the entity nodeLinks in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeLinks(Wrap<JsonArray> w);

	public JsonArray getNodeLinks() {
		return nodeLinks;
	}

	public void setNodeLinks(JsonArray nodeLinks) {
		this.nodeLinks = nodeLinks;
	}
	@JsonIgnore
	public void setNodeLinks(String o) {
		this.nodeLinks = BareMetalNode.staticSetNodeLinks(siteRequest_, o);
	}
	public static JsonArray staticSetNodeLinks(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BareMetalNode nodeLinksInit() {
		Wrap<JsonArray> nodeLinksWrap = new Wrap<JsonArray>().var("nodeLinks");
		if(nodeLinks == null) {
			_nodeLinks(nodeLinksWrap);
			Optional.ofNullable(nodeLinksWrap.getO()).ifPresent(o -> {
				setNodeLinks(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static String staticSearchNodeLinks(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrNodeLinks(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeLinks(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchNodeLinks(siteRequest_, BareMetalNode.staticSetNodeLinks(siteRequest_, o)).toString();
	}

	public JsonArray sqlNodeLinks() {
		return nodeLinks;
	}

	//////////////
	// nodeName //
	//////////////

	public static final String nodeNameESI1_enUS = "name";
	public static final String nodeNameESI_enUS = nodeNameESI1_enUS;

	/**	 The entity nodeName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String nodeName;

	/**	<br> The entity nodeName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:nodeName">Find the entity nodeName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeName(Wrap<String> w);

	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String o) {
		this.nodeName = BareMetalNode.staticSetNodeName(siteRequest_, o);
	}
	public static String staticSetNodeName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNode nodeNameInit() {
		Wrap<String> nodeNameWrap = new Wrap<String>().var("nodeName");
		if(nodeName == null) {
			_nodeName(nodeNameWrap);
			Optional.ofNullable(nodeNameWrap.getO()).ifPresent(o -> {
				setNodeName(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static String staticSearchNodeName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNodeName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeName(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchNodeName(siteRequest_, BareMetalNode.staticSetNodeName(siteRequest_, o)).toString();
	}

	public String sqlNodeName() {
		return nodeName;
	}

	////////////////////
	// nodePowerState //
	////////////////////

	public static final String nodePowerStateESI1_enUS = "power_state";
	public static final String nodePowerStateESI_enUS = nodePowerStateESI1_enUS;

	/**	 The entity nodePowerState
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String nodePowerState;

	/**	<br> The entity nodePowerState
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:nodePowerState">Find the entity nodePowerState in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodePowerState(Wrap<String> w);

	public String getNodePowerState() {
		return nodePowerState;
	}
	public void setNodePowerState(String o) {
		this.nodePowerState = BareMetalNode.staticSetNodePowerState(siteRequest_, o);
	}
	public static String staticSetNodePowerState(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNode nodePowerStateInit() {
		Wrap<String> nodePowerStateWrap = new Wrap<String>().var("nodePowerState");
		if(nodePowerState == null) {
			_nodePowerState(nodePowerStateWrap);
			Optional.ofNullable(nodePowerStateWrap.getO()).ifPresent(o -> {
				setNodePowerState(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static String staticSearchNodePowerState(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNodePowerState(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodePowerState(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchNodePowerState(siteRequest_, BareMetalNode.staticSetNodePowerState(siteRequest_, o)).toString();
	}

	public String sqlNodePowerState() {
		return nodePowerState;
	}

	////////////////////////
	// nodeProvisionState //
	////////////////////////

	public static final String nodeProvisionStateESI1_enUS = "provision_state";
	public static final String nodeProvisionStateESI_enUS = nodeProvisionStateESI1_enUS;

	/**	 The entity nodeProvisionState
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String nodeProvisionState;

	/**	<br> The entity nodeProvisionState
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:nodeProvisionState">Find the entity nodeProvisionState in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeProvisionState(Wrap<String> w);

	public String getNodeProvisionState() {
		return nodeProvisionState;
	}
	public void setNodeProvisionState(String o) {
		this.nodeProvisionState = BareMetalNode.staticSetNodeProvisionState(siteRequest_, o);
	}
	public static String staticSetNodeProvisionState(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNode nodeProvisionStateInit() {
		Wrap<String> nodeProvisionStateWrap = new Wrap<String>().var("nodeProvisionState");
		if(nodeProvisionState == null) {
			_nodeProvisionState(nodeProvisionStateWrap);
			Optional.ofNullable(nodeProvisionStateWrap.getO()).ifPresent(o -> {
				setNodeProvisionState(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static String staticSearchNodeProvisionState(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNodeProvisionState(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeProvisionState(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchNodeProvisionState(siteRequest_, BareMetalNode.staticSetNodeProvisionState(siteRequest_, o)).toString();
	}

	public String sqlNodeProvisionState() {
		return nodeProvisionState;
	}

	///////////////////////
	// nodeResourceClass //
	///////////////////////

	public static final String nodeResourceClassESI1_enUS = "resource_class";
	public static final String nodeResourceClassESI_enUS = nodeResourceClassESI1_enUS;

	/**	 The entity nodeResourceClass
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String nodeResourceClass;

	/**	<br> The entity nodeResourceClass
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode&fq=entiteVar_enUS_indexed_string:nodeResourceClass">Find the entity nodeResourceClass in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeResourceClass(Wrap<String> w);

	public String getNodeResourceClass() {
		return nodeResourceClass;
	}
	public void setNodeResourceClass(String o) {
		this.nodeResourceClass = BareMetalNode.staticSetNodeResourceClass(siteRequest_, o);
	}
	public static String staticSetNodeResourceClass(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNode nodeResourceClassInit() {
		Wrap<String> nodeResourceClassWrap = new Wrap<String>().var("nodeResourceClass");
		if(nodeResourceClass == null) {
			_nodeResourceClass(nodeResourceClassWrap);
			Optional.ofNullable(nodeResourceClassWrap.getO()).ifPresent(o -> {
				setNodeResourceClass(o);
			});
		}
		return (BareMetalNode)this;
	}

	public static String staticSearchNodeResourceClass(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNodeResourceClass(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeResourceClass(SiteRequest siteRequest_, String o) {
		return BareMetalNode.staticSearchNodeResourceClass(siteRequest_, BareMetalNode.staticSetNodeResourceClass(siteRequest_, o)).toString();
	}

	public String sqlNodeResourceClass() {
		return nodeResourceClass;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalNodeGen<DEV>> promiseDeepBareMetalNode(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalNode();
	}

	public Future<BareMetalNodeGen<DEV>> promiseDeepBareMetalNode() {
		Promise<BareMetalNodeGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalNode(promise2);
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

	public Future<Void> promiseBareMetalNode(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				leaseInfoInit();
				networkInfoInit();
				nodeIdInit();
				nodeIsMaintenanceInit();
				nodeLinksInit();
				nodeNameInit();
				nodePowerStateInit();
				nodeProvisionStateInit();
				nodeResourceClassInit();
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

	@Override public Future<? extends BareMetalNodeGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalNode(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalNode(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalNode(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalNode(v);
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
	public Object obtainBareMetalNode(String var) {
		BareMetalNode oBareMetalNode = (BareMetalNode)this;
		switch(var) {
			case "leaseInfo":
				return oBareMetalNode.leaseInfo;
			case "networkInfo":
				return oBareMetalNode.networkInfo;
			case "nodeId":
				return oBareMetalNode.nodeId;
			case "nodeIsMaintenance":
				return oBareMetalNode.nodeIsMaintenance;
			case "nodeLinks":
				return oBareMetalNode.nodeLinks;
			case "nodeName":
				return oBareMetalNode.nodeName;
			case "nodePowerState":
				return oBareMetalNode.nodePowerState;
			case "nodeProvisionState":
				return oBareMetalNode.nodeProvisionState;
			case "nodeResourceClass":
				return oBareMetalNode.nodeResourceClass;
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
				o = relateBareMetalNode(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalNode(String var, Object val) {
		BareMetalNode oBareMetalNode = (BareMetalNode)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetBareMetalNode(entityVar,  siteRequest_, o);
	}
	public static Object staticSetBareMetalNode(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "leaseInfo":
			return BareMetalNode.staticSetLeaseInfo(siteRequest_, o);
		case "networkInfo":
			return BareMetalNode.staticSetNetworkInfo(siteRequest_, o);
		case "nodeId":
			return BareMetalNode.staticSetNodeId(siteRequest_, o);
		case "nodeIsMaintenance":
			return BareMetalNode.staticSetNodeIsMaintenance(siteRequest_, o);
		case "nodeLinks":
			return BareMetalNode.staticSetNodeLinks(siteRequest_, o);
		case "nodeName":
			return BareMetalNode.staticSetNodeName(siteRequest_, o);
		case "nodePowerState":
			return BareMetalNode.staticSetNodePowerState(siteRequest_, o);
		case "nodeProvisionState":
			return BareMetalNode.staticSetNodeProvisionState(siteRequest_, o);
		case "nodeResourceClass":
			return BareMetalNode.staticSetNodeResourceClass(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalNode(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalNode(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "leaseInfo":
			return BareMetalNode.staticSearchLeaseInfo(siteRequest_, (JsonArray)o);
		case "networkInfo":
			return BareMetalNode.staticSearchNetworkInfo(siteRequest_, (JsonArray)o);
		case "nodeId":
			return BareMetalNode.staticSearchNodeId(siteRequest_, (String)o);
		case "nodeIsMaintenance":
			return BareMetalNode.staticSearchNodeIsMaintenance(siteRequest_, (Integer)o);
		case "nodeLinks":
			return BareMetalNode.staticSearchNodeLinks(siteRequest_, (JsonArray)o);
		case "nodeName":
			return BareMetalNode.staticSearchNodeName(siteRequest_, (String)o);
		case "nodePowerState":
			return BareMetalNode.staticSearchNodePowerState(siteRequest_, (String)o);
		case "nodeProvisionState":
			return BareMetalNode.staticSearchNodeProvisionState(siteRequest_, (String)o);
		case "nodeResourceClass":
			return BareMetalNode.staticSearchNodeResourceClass(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalNode(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalNode(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "leaseInfo":
			return BareMetalNode.staticSearchStrLeaseInfo(siteRequest_, (String)o);
		case "networkInfo":
			return BareMetalNode.staticSearchStrNetworkInfo(siteRequest_, (String)o);
		case "nodeId":
			return BareMetalNode.staticSearchStrNodeId(siteRequest_, (String)o);
		case "nodeIsMaintenance":
			return BareMetalNode.staticSearchStrNodeIsMaintenance(siteRequest_, (Integer)o);
		case "nodeLinks":
			return BareMetalNode.staticSearchStrNodeLinks(siteRequest_, (String)o);
		case "nodeName":
			return BareMetalNode.staticSearchStrNodeName(siteRequest_, (String)o);
		case "nodePowerState":
			return BareMetalNode.staticSearchStrNodePowerState(siteRequest_, (String)o);
		case "nodeProvisionState":
			return BareMetalNode.staticSearchStrNodeProvisionState(siteRequest_, (String)o);
		case "nodeResourceClass":
			return BareMetalNode.staticSearchStrNodeResourceClass(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalNode(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalNode(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "leaseInfo":
			return BareMetalNode.staticSearchFqLeaseInfo(siteRequest_, o);
		case "networkInfo":
			return BareMetalNode.staticSearchFqNetworkInfo(siteRequest_, o);
		case "nodeId":
			return BareMetalNode.staticSearchFqNodeId(siteRequest_, o);
		case "nodeIsMaintenance":
			return BareMetalNode.staticSearchFqNodeIsMaintenance(siteRequest_, o);
		case "nodeLinks":
			return BareMetalNode.staticSearchFqNodeLinks(siteRequest_, o);
		case "nodeName":
			return BareMetalNode.staticSearchFqNodeName(siteRequest_, o);
		case "nodePowerState":
			return BareMetalNode.staticSearchFqNodePowerState(siteRequest_, o);
		case "nodeProvisionState":
			return BareMetalNode.staticSearchFqNodeProvisionState(siteRequest_, o);
		case "nodeResourceClass":
			return BareMetalNode.staticSearchFqNodeResourceClass(siteRequest_, o);
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
					o = persistBareMetalNode(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistBareMetalNode(String var, Object val) {
		String varLower = var.toLowerCase();
			if("leaseinfo".equals(varLower)) {
				if(val instanceof String) {
					setLeaseInfo((String)val);
				} else if(val instanceof JsonArray) {
					setLeaseInfo((JsonArray)val);
				}
				saves.add("leaseInfo");
				return val;
			} else if("networkinfo".equals(varLower)) {
				if(val instanceof String) {
					setNetworkInfo((String)val);
				} else if(val instanceof JsonArray) {
					setNetworkInfo((JsonArray)val);
				}
				saves.add("networkInfo");
				return val;
			} else if("nodeid".equals(varLower)) {
				if(val instanceof String) {
					setNodeId((String)val);
				}
				saves.add("nodeId");
				return val;
			} else if("nodeismaintenance".equals(varLower)) {
				if(val instanceof Integer) {
					setNodeIsMaintenance((Integer)val);
				} else {
					setNodeIsMaintenance(val == null ? null : val.toString());
				}
				saves.add("nodeIsMaintenance");
				return val;
			} else if("nodelinks".equals(varLower)) {
				if(val instanceof String) {
					setNodeLinks((String)val);
				} else if(val instanceof JsonArray) {
					setNodeLinks((JsonArray)val);
				}
				saves.add("nodeLinks");
				return val;
			} else if("nodename".equals(varLower)) {
				if(val instanceof String) {
					setNodeName((String)val);
				}
				saves.add("nodeName");
				return val;
			} else if("nodepowerstate".equals(varLower)) {
				if(val instanceof String) {
					setNodePowerState((String)val);
				}
				saves.add("nodePowerState");
				return val;
			} else if("nodeprovisionstate".equals(varLower)) {
				if(val instanceof String) {
					setNodeProvisionState((String)val);
				}
				saves.add("nodeProvisionState");
				return val;
			} else if("noderesourceclass".equals(varLower)) {
				if(val instanceof String) {
					setNodeResourceClass((String)val);
				}
				saves.add("nodeResourceClass");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateBareMetalNode(doc);
	}
	public void populateBareMetalNode(SolrResponse.Doc doc) {
		BareMetalNode oBareMetalNode = (BareMetalNode)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("leaseInfo")) {
				String leaseInfo = (String)doc.get("leaseInfo_docvalues_string");
				if(leaseInfo != null)
					oBareMetalNode.setLeaseInfo(leaseInfo);
			}

			if(saves.contains("networkInfo")) {
				String networkInfo = (String)doc.get("networkInfo_docvalues_string");
				if(networkInfo != null)
					oBareMetalNode.setNetworkInfo(networkInfo);
			}

			if(saves.contains("nodeId")) {
				String nodeId = (String)doc.get("nodeId_docvalues_string");
				if(nodeId != null)
					oBareMetalNode.setNodeId(nodeId);
			}

			if(saves.contains("nodeIsMaintenance")) {
				Integer nodeIsMaintenance = (Integer)doc.get("nodeIsMaintenance_docvalues_int");
				if(nodeIsMaintenance != null)
					oBareMetalNode.setNodeIsMaintenance(nodeIsMaintenance);
			}

			if(saves.contains("nodeLinks")) {
				String nodeLinks = (String)doc.get("nodeLinks_docvalues_string");
				if(nodeLinks != null)
					oBareMetalNode.setNodeLinks(nodeLinks);
			}

			if(saves.contains("nodeName")) {
				String nodeName = (String)doc.get("nodeName_docvalues_string");
				if(nodeName != null)
					oBareMetalNode.setNodeName(nodeName);
			}

			if(saves.contains("nodePowerState")) {
				String nodePowerState = (String)doc.get("nodePowerState_docvalues_string");
				if(nodePowerState != null)
					oBareMetalNode.setNodePowerState(nodePowerState);
			}

			if(saves.contains("nodeProvisionState")) {
				String nodeProvisionState = (String)doc.get("nodeProvisionState_docvalues_string");
				if(nodeProvisionState != null)
					oBareMetalNode.setNodeProvisionState(nodeProvisionState);
			}

			if(saves.contains("nodeResourceClass")) {
				String nodeResourceClass = (String)doc.get("nodeResourceClass_docvalues_string");
				if(nodeResourceClass != null)
					oBareMetalNode.setNodeResourceClass(nodeResourceClass);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexBareMetalNode(JsonObject doc) {
		if(leaseInfo != null) {
			doc.put("leaseInfo_docvalues_string", leaseInfo.toString());
		}
		if(networkInfo != null) {
			doc.put("networkInfo_docvalues_string", networkInfo.toString());
		}
		if(nodeId != null) {
			doc.put("nodeId_docvalues_string", nodeId);
		}
		if(nodeIsMaintenance != null) {
			doc.put("nodeIsMaintenance_docvalues_int", nodeIsMaintenance);
		}
		if(nodeLinks != null) {
			doc.put("nodeLinks_docvalues_string", nodeLinks.toString());
		}
		if(nodeName != null) {
			doc.put("nodeName_docvalues_string", nodeName);
		}
		if(nodePowerState != null) {
			doc.put("nodePowerState_docvalues_string", nodePowerState);
		}
		if(nodeProvisionState != null) {
			doc.put("nodeProvisionState_docvalues_string", nodeProvisionState);
		}
		if(nodeResourceClass != null) {
			doc.put("nodeResourceClass_docvalues_string", nodeResourceClass);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredBareMetalNode(String entityVar) {
		switch(entityVar) {
			case "leaseInfo":
				return "leaseInfo_docvalues_string";
			case "networkInfo":
				return "networkInfo_docvalues_string";
			case "nodeId":
				return "nodeId_docvalues_string";
			case "nodeIsMaintenance":
				return "nodeIsMaintenance_docvalues_int";
			case "nodeLinks":
				return "nodeLinks_docvalues_string";
			case "nodeName":
				return "nodeName_docvalues_string";
			case "nodePowerState":
				return "nodePowerState_docvalues_string";
			case "nodeProvisionState":
				return "nodeProvisionState_docvalues_string";
			case "nodeResourceClass":
				return "nodeResourceClass_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedBareMetalNode(String entityVar) {
		switch(entityVar) {
			case "leaseInfo":
				return "leaseInfo_docvalues_string";
			case "networkInfo":
				return "networkInfo_docvalues_string";
			case "nodeId":
				return "nodeId_docvalues_string";
			case "nodeIsMaintenance":
				return "nodeIsMaintenance_docvalues_int";
			case "nodeLinks":
				return "nodeLinks_docvalues_string";
			case "nodeName":
				return "nodeName_docvalues_string";
			case "nodePowerState":
				return "nodePowerState_docvalues_string";
			case "nodeProvisionState":
				return "nodeProvisionState_docvalues_string";
			case "nodeResourceClass":
				return "nodeResourceClass_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarBareMetalNode(String searchVar) {
		switch(searchVar) {
			case "leaseInfo_docvalues_string":
				return "leaseInfo";
			case "networkInfo_docvalues_string":
				return "networkInfo";
			case "nodeId_docvalues_string":
				return "nodeId";
			case "nodeIsMaintenance_docvalues_int":
				return "nodeIsMaintenance";
			case "nodeLinks_docvalues_string":
				return "nodeLinks";
			case "nodeName_docvalues_string":
				return "nodeName";
			case "nodePowerState_docvalues_string":
				return "nodePowerState";
			case "nodeProvisionState_docvalues_string":
				return "nodeProvisionState";
			case "nodeResourceClass_docvalues_string":
				return "nodeResourceClass";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchBareMetalNode(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedBareMetalNode(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeBareMetalNode(doc);
	}
	public void storeBareMetalNode(SolrResponse.Doc doc) {
		BareMetalNode oBareMetalNode = (BareMetalNode)this;
		SiteRequest siteRequest = oBareMetalNode.getSiteRequest_();

		oBareMetalNode.setLeaseInfo(Optional.ofNullable(doc.get("leaseInfo_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNode.setNetworkInfo(Optional.ofNullable(doc.get("networkInfo_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNode.setNodeId(Optional.ofNullable(doc.get("nodeId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNode.setNodeIsMaintenance(Optional.ofNullable(doc.get("nodeIsMaintenance_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalNode.setNodeLinks(Optional.ofNullable(doc.get("nodeLinks_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNode.setNodeName(Optional.ofNullable(doc.get("nodeName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNode.setNodePowerState(Optional.ofNullable(doc.get("nodePowerState_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNode.setNodeProvisionState(Optional.ofNullable(doc.get("nodeProvisionState_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalNode.setNodeResourceClass(Optional.ofNullable(doc.get("nodeResourceClass_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBareMetalNode() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BareMetalNode) {
			BareMetalNode original = (BareMetalNode)o;
			if(!Objects.equals(leaseInfo, original.getLeaseInfo()))
				apiRequest.addVars("leaseInfo");
			if(!Objects.equals(networkInfo, original.getNetworkInfo()))
				apiRequest.addVars("networkInfo");
			if(!Objects.equals(nodeId, original.getNodeId()))
				apiRequest.addVars("nodeId");
			if(!Objects.equals(nodeIsMaintenance, original.getNodeIsMaintenance()))
				apiRequest.addVars("nodeIsMaintenance");
			if(!Objects.equals(nodeLinks, original.getNodeLinks()))
				apiRequest.addVars("nodeLinks");
			if(!Objects.equals(nodeName, original.getNodeName()))
				apiRequest.addVars("nodeName");
			if(!Objects.equals(nodePowerState, original.getNodePowerState()))
				apiRequest.addVars("nodePowerState");
			if(!Objects.equals(nodeProvisionState, original.getNodeProvisionState()))
				apiRequest.addVars("nodeProvisionState");
			if(!Objects.equals(nodeResourceClass, original.getNodeResourceClass()))
				apiRequest.addVars("nodeResourceClass");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(leaseInfo).map(v -> "leaseInfo: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(networkInfo).map(v -> "networkInfo: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(nodeId).map(v -> "nodeId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(nodeIsMaintenance).map(v -> "nodeIsMaintenance: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(nodeLinks).map(v -> "nodeLinks: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(nodeName).map(v -> "nodeName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(nodePowerState).map(v -> "nodePowerState: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(nodeProvisionState).map(v -> "nodeProvisionState: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(nodeResourceClass).map(v -> "nodeResourceClass: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String[] BareMetalNodeVals = new String[] { nodeESI1_enUS, leaseInfoESI1_enUS, networkInfoESI1_enUS, nodeIdESI1_enUS, nodeIsMaintenanceESI1_enUS, nodeLinksESI1_enUS, nodeNameESI1_enUS, nodePowerStateESI1_enUS, nodeProvisionStateESI1_enUS, nodeResourceClassESI1_enUS };

	public static final String CLASS_SIMPLE_NAME = "BareMetalNode";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode";
	public static final String CLASS_API_ADDRESS_BareMetalNode = "ai-telemetry-enUS-BareMetalNode";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_BareMetalNode;
	}
	public static final String VAR_leaseInfo = "leaseInfo";
	public static final String VAR_networkInfo = "networkInfo";
	public static final String VAR_nodeId = "nodeId";
	public static final String VAR_nodeIsMaintenance = "nodeIsMaintenance";
	public static final String VAR_nodeLinks = "nodeLinks";
	public static final String VAR_nodeName = "nodeName";
	public static final String VAR_nodePowerState = "nodePowerState";
	public static final String VAR_nodeProvisionState = "nodeProvisionState";
	public static final String VAR_nodeResourceClass = "nodeResourceClass";

	public static List<String> varsQForClass() {
		return BareMetalNode.varsQBareMetalNode(new ArrayList<String>());
	}
	public static List<String> varsQBareMetalNode(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return BareMetalNode.varsFqBareMetalNode(new ArrayList<String>());
	}
	public static List<String> varsFqBareMetalNode(List<String> vars) {
		vars.add(VAR_leaseInfo);
		vars.add(VAR_networkInfo);
		vars.add(VAR_nodeId);
		vars.add(VAR_nodeIsMaintenance);
		vars.add(VAR_nodeLinks);
		vars.add(VAR_nodeName);
		vars.add(VAR_nodePowerState);
		vars.add(VAR_nodeProvisionState);
		vars.add(VAR_nodeResourceClass);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return BareMetalNode.varsRangeBareMetalNode(new ArrayList<String>());
	}
	public static List<String> varsRangeBareMetalNode(List<String> vars) {
		vars.add(VAR_leaseInfo);
		vars.add(VAR_networkInfo);
		vars.add(VAR_nodeIsMaintenance);
		vars.add(VAR_nodeLinks);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_leaseInfo = "lease info";
	public static final String DISPLAY_NAME_networkInfo = "network info";
	public static final String DISPLAY_NAME_nodeId = "id";
	public static final String DISPLAY_NAME_nodeIsMaintenance = "is maintenance";
	public static final String DISPLAY_NAME_nodeLinks = "links";
	public static final String DISPLAY_NAME_nodeName = "name";
	public static final String DISPLAY_NAME_nodePowerState = "power state";
	public static final String DISPLAY_NAME_nodeProvisionState = "provision state";
	public static final String DISPLAY_NAME_nodeResourceClass = "resource class";

	@Override
	public String idForClass() {
		return nodeId;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return nodeName;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return BareMetalNode.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/bare-metal-node/%s";
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
		return BareMetalNode.displayNameBareMetalNode(var);
	}
	public static String displayNameBareMetalNode(String var) {
		switch(var) {
		case VAR_leaseInfo:
			return DISPLAY_NAME_leaseInfo;
		case VAR_networkInfo:
			return DISPLAY_NAME_networkInfo;
		case VAR_nodeId:
			return DISPLAY_NAME_nodeId;
		case VAR_nodeIsMaintenance:
			return DISPLAY_NAME_nodeIsMaintenance;
		case VAR_nodeLinks:
			return DISPLAY_NAME_nodeLinks;
		case VAR_nodeName:
			return DISPLAY_NAME_nodeName;
		case VAR_nodePowerState:
			return DISPLAY_NAME_nodePowerState;
		case VAR_nodeProvisionState:
			return DISPLAY_NAME_nodeProvisionState;
		case VAR_nodeResourceClass:
			return DISPLAY_NAME_nodeResourceClass;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionBareMetalNode(String var) {
		switch(var) {
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameBareMetalNode(String var) {
		switch(var) {
		case VAR_leaseInfo:
			return "JsonArray";
		case VAR_networkInfo:
			return "JsonArray";
		case VAR_nodeId:
			return "String";
		case VAR_nodeIsMaintenance:
			return "Integer";
		case VAR_nodeLinks:
			return "JsonArray";
		case VAR_nodeName:
			return "String";
		case VAR_nodePowerState:
			return "String";
		case VAR_nodeProvisionState:
			return "String";
		case VAR_nodeResourceClass:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnBareMetalNode(String var) {
		switch(var) {
		case VAR_nodeName:
			return 0;
		case VAR_nodePowerState:
			return 2;
		case VAR_nodeProvisionState:
			return 3;
		case VAR_nodeResourceClass:
			return 1;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowBareMetalNode(String var) {
		switch(var) {
		case VAR_leaseInfo:
			return 3;
		case VAR_networkInfo:
			return 3;
		case VAR_nodeId:
			return 3;
		case VAR_nodeIsMaintenance:
			return 3;
		case VAR_nodeLinks:
			return 3;
		case VAR_nodeName:
			return 3;
		case VAR_nodePowerState:
			return 3;
		case VAR_nodeProvisionState:
			return 3;
		case VAR_nodeResourceClass:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellBareMetalNode(String var) {
		switch(var) {
		case VAR_leaseInfo:
			return 0;
		case VAR_networkInfo:
			return 1;
		case VAR_nodeId:
			return 19;
		case VAR_nodeIsMaintenance:
			return 25;
		case VAR_nodeLinks:
			return 31;
		case VAR_nodeName:
			return 35;
		case VAR_nodePowerState:
			return 42;
		case VAR_nodeProvisionState:
			return 45;
		case VAR_nodeResourceClass:
			return 55;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinBareMetalNode(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxBareMetalNode(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxBareMetalNode(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minBareMetalNode(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
