package org.mghpcc.aitelemetry.model.node;

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
import java.lang.Integer;
import org.computate.vertx.serialize.vertx.JsonObjectDeserializer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class AiNodeGen into the class AiNode. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the AiNode API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the AiNode class and it's generated class AiNodeGen&lt;BaseModel&gt;: </h3>extends AiNodeGen
 * <p>
 * This Java class extends a generated Java class AiNodeGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode">Find the class AiNode in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends AiNodeGen<BaseModel>
 * <p>This <code>class AiNode extends AiNodeGen&lt;BaseModel&gt;</code>, which means it extends a newly generated AiNodeGen. 
 * The generated <code>class AiNodeGen extends BaseModel</code> which means that AiNode extends AiNodeGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: AI nodes"</b>, which groups all of the OpenAPIs for AiNode objects under the tag "AI nodes". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/ai-node</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/ai-node"</b>, which defines the base API URI for AiNode objects as "/en-us/api/ai-node" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the AiNode class will inherit the helpful inherited class comments from the super class AiNodeGen. 
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
 * This creates a new Java class org.mghpcc.aitelemetry.model.node.AiNodePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.node.AiNodePage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the AiNode Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an AI node</h2>
 * <p>This class contains a comment <b>"AName.enUS: an AI node"</b>, which identifies the language context to describe a AiNode as "an AI node". 
 * </p>
 * <p>
 * Delete the class AiNode in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.node in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.node&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class AiNodeGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(AiNode.class);

	public static final String Description_enUS = "A Red Hat OpenShift node containing GPUs";
	public static final String AName_enUS = "an AI node";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this AI node";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "theAI node";
	public static final String SingularName_enUS = "AI node";
	public static final String PluralName_enUS = "AI nodes";
	public static final String NameActual_enUS = "current AI node";
	public static final String AllName_enUS = "all AI nodes";
	public static final String SearchAllNameBy_enUS = "search AI nodes by ";
	public static final String SearchAllName_enUS = "search AI nodes";
	public static final String Title_enUS = "AI nodes";
	public static final String ThePluralName_enUS = "the AI nodes";
	public static final String NoNameFound_enUS = "no AI node found";
	public static final String ApiUri_enUS = "/en-us/api/ai-node";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/ai-node";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/ai-node/{nodeId}";
	public static final String OfName_enUS = "of AI node";
	public static final String ANameAdjective_enUS = "an AI node";
	public static final String NameAdjectiveSingular_enUS = "AI node";
	public static final String NameAdjectivePlural_enUS = "AI nodes";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/ai-node";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/ai-node";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/ai-node";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/ai-node/{nodeId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/ai-node/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/ai-node/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/ai-node";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/ai-node";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/ai-node";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/ai-node";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/ai-node";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/ai-node";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/ai-node/{nodeId}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/ai-node/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/ai-node/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/ai-node-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/ai-node-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/ai-node-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/ai-node";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/ai-node";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/ai-node";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/ai-node/{nodeId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/ai-node/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/ai-node/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/ai-node/{nodeId}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/ai-node/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/ai-node/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/ai-node";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/ai-node";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/ai-node";

	public static final String Icon = "<i class=\"fa-regular fa-computer\"></i>";

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:clusterName">Find the entity clusterName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterName(Wrap<String> w);

	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String o) {
		this.clusterName = AiNode.staticSetClusterName(siteRequest_, o);
	}
	public static String staticSetClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode clusterNameInit() {
		Wrap<String> clusterNameWrap = new Wrap<String>().var("clusterName");
		if(clusterName == null) {
			_clusterName(clusterNameWrap);
			Optional.ofNullable(clusterNameWrap.getO()).ifPresent(o -> {
				setClusterName(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterName(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchClusterName(siteRequest_, AiNode.staticSetClusterName(siteRequest_, o)).toString();
	}

	public String sqlClusterName() {
		return clusterName;
	}

	//////////////
	// nodeName //
	//////////////


	/**	 The entity nodeName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String nodeName;

	/**	<br> The entity nodeName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:nodeName">Find the entity nodeName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeName(Wrap<String> w);

	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String o) {
		this.nodeName = AiNode.staticSetNodeName(siteRequest_, o);
	}
	public static String staticSetNodeName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode nodeNameInit() {
		Wrap<String> nodeNameWrap = new Wrap<String>().var("nodeName");
		if(nodeName == null) {
			_nodeName(nodeNameWrap);
			Optional.ofNullable(nodeNameWrap.getO()).ifPresent(o -> {
				setNodeName(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchNodeName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNodeName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeName(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchNodeName(siteRequest_, AiNode.staticSetNodeName(siteRequest_, o)).toString();
	}

	public String sqlNodeName() {
		return nodeName;
	}

	////////////
	// nodeId //
	////////////


	/**	 The entity nodeId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String nodeId;

	/**	<br> The entity nodeId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:nodeId">Find the entity nodeId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeId(Wrap<String> w);

	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String o) {
		this.nodeId = AiNode.staticSetNodeId(siteRequest_, o);
	}
	public static String staticSetNodeId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode nodeIdInit() {
		Wrap<String> nodeIdWrap = new Wrap<String>().var("nodeId");
		if(nodeId == null) {
			_nodeId(nodeIdWrap);
			Optional.ofNullable(nodeIdWrap.getO()).ifPresent(o -> {
				setNodeId(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchNodeId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNodeId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeId(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchNodeId(siteRequest_, AiNode.staticSetNodeId(siteRequest_, o)).toString();
	}

	public String sqlNodeId() {
		return nodeId;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = AiNode.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchDescription(siteRequest_, AiNode.staticSetDescription(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:locationColors">Find the entity locationColors in Solr</a>
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
		String l = AiNode.staticSetLocationColors(siteRequest_, o);
		if(l != null)
			addLocationColors(l);
	}
	public static String staticSetLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}
	public AiNode addLocationColors(String...objects) {
		for(String o : objects) {
			addLocationColors(o);
		}
		return (AiNode)this;
	}
	public AiNode addLocationColors(String o) {
		if(o != null)
			this.locationColors.add(o);
		return (AiNode)this;
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
	protected AiNode locationColorsInit() {
		_locationColors(locationColors);
		return (AiNode)this;
	}

	public static String staticSearchLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationColors(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationColors(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchLocationColors(siteRequest_, AiNode.staticSetLocationColors(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:locationTitles">Find the entity locationTitles in Solr</a>
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
		String l = AiNode.staticSetLocationTitles(siteRequest_, o);
		if(l != null)
			addLocationTitles(l);
	}
	public static String staticSetLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}
	public AiNode addLocationTitles(String...objects) {
		for(String o : objects) {
			addLocationTitles(o);
		}
		return (AiNode)this;
	}
	public AiNode addLocationTitles(String o) {
		if(o != null)
			this.locationTitles.add(o);
		return (AiNode)this;
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
	protected AiNode locationTitlesInit() {
		_locationTitles(locationTitles);
		return (AiNode)this;
	}

	public static String staticSearchLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationTitles(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationTitles(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchLocationTitles(siteRequest_, AiNode.staticSetLocationTitles(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:locationLinks">Find the entity locationLinks in Solr</a>
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
		String l = AiNode.staticSetLocationLinks(siteRequest_, o);
		if(l != null)
			addLocationLinks(l);
	}
	public static String staticSetLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}
	public AiNode addLocationLinks(String...objects) {
		for(String o : objects) {
			addLocationLinks(o);
		}
		return (AiNode)this;
	}
	public AiNode addLocationLinks(String o) {
		if(o != null)
			this.locationLinks.add(o);
		return (AiNode)this;
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
	protected AiNode locationLinksInit() {
		_locationLinks(locationLinks);
		return (AiNode)this;
	}

	public static String staticSearchLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationLinks(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationLinks(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchLocationLinks(siteRequest_, AiNode.staticSetLocationLinks(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:location">Find the entity location in Solr</a>
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
		this.location = AiNode.staticSetLocation(siteRequest_, o);
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
		this.location = AiNode.staticSetLocation(siteRequest_, o);
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
	protected AiNode locationInit() {
		Wrap<Point> locationWrap = new Wrap<Point>().var("location");
		if(location == null) {
			_location(locationWrap);
			Optional.ofNullable(locationWrap.getO()).ifPresent(o -> {
				setLocation(o);
			});
		}
		return (AiNode)this;
	}

	public static Point staticSearchLocation(SiteRequest siteRequest_, Point o) {
		return o;
	}

	public static String staticSearchStrLocation(SiteRequest siteRequest_, Point o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocation(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchLocation(siteRequest_, AiNode.staticSetLocation(siteRequest_, o)).toString();
	}

	public Point sqlLocation() {
		return location;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:gpuDevicesTotal">Find the entity gpuDevicesTotal in Solr</a>
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
		this.gpuDevicesTotal = AiNode.staticSetGpuDevicesTotal(siteRequest_, o);
	}
	public static Integer staticSetGpuDevicesTotal(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected AiNode gpuDevicesTotalInit() {
		Wrap<Integer> gpuDevicesTotalWrap = new Wrap<Integer>().var("gpuDevicesTotal");
		if(gpuDevicesTotal == null) {
			_gpuDevicesTotal(gpuDevicesTotalWrap);
			Optional.ofNullable(gpuDevicesTotalWrap.getO()).ifPresent(o -> {
				setGpuDevicesTotal(o);
			});
		}
		return (AiNode)this;
	}

	public static Integer staticSearchGpuDevicesTotal(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrGpuDevicesTotal(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuDevicesTotal(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchGpuDevicesTotal(siteRequest_, AiNode.staticSetGpuDevicesTotal(siteRequest_, o)).toString();
	}

	public Integer sqlGpuDevicesTotal() {
		return gpuDevicesTotal;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = AiNode.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchId(siteRequest_, AiNode.staticSetId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:entityShortId">Find the entity entityShortId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityShortId(Wrap<String> w);

	public String getEntityShortId() {
		return entityShortId;
	}
	public void setEntityShortId(String o) {
		this.entityShortId = AiNode.staticSetEntityShortId(siteRequest_, o);
	}
	public static String staticSetEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode entityShortIdInit() {
		Wrap<String> entityShortIdWrap = new Wrap<String>().var("entityShortId");
		if(entityShortId == null) {
			_entityShortId(entityShortIdWrap);
			Optional.ofNullable(entityShortIdWrap.getO()).ifPresent(o -> {
				setEntityShortId(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityShortId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityShortId(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchEntityShortId(siteRequest_, AiNode.staticSetEntityShortId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:ngsildTenant">Find the entity ngsildTenant in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildTenant(Wrap<String> w);

	public String getNgsildTenant() {
		return ngsildTenant;
	}
	public void setNgsildTenant(String o) {
		this.ngsildTenant = AiNode.staticSetNgsildTenant(siteRequest_, o);
	}
	public static String staticSetNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode ngsildTenantInit() {
		Wrap<String> ngsildTenantWrap = new Wrap<String>().var("ngsildTenant");
		if(ngsildTenant == null) {
			_ngsildTenant(ngsildTenantWrap);
			Optional.ofNullable(ngsildTenantWrap.getO()).ifPresent(o -> {
				setNgsildTenant(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildTenant(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildTenant(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchNgsildTenant(siteRequest_, AiNode.staticSetNgsildTenant(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:ngsildPath">Find the entity ngsildPath in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildPath(Wrap<String> w);

	public String getNgsildPath() {
		return ngsildPath;
	}
	public void setNgsildPath(String o) {
		this.ngsildPath = AiNode.staticSetNgsildPath(siteRequest_, o);
	}
	public static String staticSetNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode ngsildPathInit() {
		Wrap<String> ngsildPathWrap = new Wrap<String>().var("ngsildPath");
		if(ngsildPath == null) {
			_ngsildPath(ngsildPathWrap);
			Optional.ofNullable(ngsildPathWrap.getO()).ifPresent(o -> {
				setNgsildPath(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildPath(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildPath(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchNgsildPath(siteRequest_, AiNode.staticSetNgsildPath(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:ngsildContext">Find the entity ngsildContext in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildContext(Wrap<String> w);

	public String getNgsildContext() {
		return ngsildContext;
	}
	public void setNgsildContext(String o) {
		this.ngsildContext = AiNode.staticSetNgsildContext(siteRequest_, o);
	}
	public static String staticSetNgsildContext(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNode ngsildContextInit() {
		Wrap<String> ngsildContextWrap = new Wrap<String>().var("ngsildContext");
		if(ngsildContext == null) {
			_ngsildContext(ngsildContextWrap);
			Optional.ofNullable(ngsildContextWrap.getO()).ifPresent(o -> {
				setNgsildContext(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchNgsildContext(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildContext(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildContext(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchNgsildContext(siteRequest_, AiNode.staticSetNgsildContext(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNode&fq=entiteVar_enUS_indexed_string:ngsildData">Find the entity ngsildData in Solr</a>
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
		this.ngsildData = AiNode.staticSetNgsildData(siteRequest_, o);
	}
	public static JsonObject staticSetNgsildData(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected AiNode ngsildDataInit() {
		Wrap<JsonObject> ngsildDataWrap = new Wrap<JsonObject>().var("ngsildData");
		if(ngsildData == null) {
			_ngsildData(ngsildDataWrap);
			Optional.ofNullable(ngsildDataWrap.getO()).ifPresent(o -> {
				setNgsildData(o);
			});
		}
		return (AiNode)this;
	}

	public static String staticSearchNgsildData(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrNgsildData(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildData(SiteRequest siteRequest_, String o) {
		return AiNode.staticSearchNgsildData(siteRequest_, AiNode.staticSetNgsildData(siteRequest_, o)).toString();
	}

	public JsonObject sqlNgsildData() {
		return ngsildData;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<AiNodeGen<DEV>> promiseDeepAiNode(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepAiNode();
	}

	public Future<AiNodeGen<DEV>> promiseDeepAiNode() {
		Promise<AiNodeGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseAiNode(promise2);
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

	public Future<Void> promiseAiNode(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				clusterNameInit();
				nodeNameInit();
				nodeIdInit();
				descriptionInit();
				locationColorsInit();
				locationTitlesInit();
				locationLinksInit();
				locationInit();
				gpuDevicesTotalInit();
				idInit();
				entityShortIdInit();
				ngsildTenantInit();
				ngsildPathInit();
				ngsildContextInit();
				ngsildDataInit();
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

	@Override public Future<? extends AiNodeGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepAiNode(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAiNode(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestAiNode(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAiNode(v);
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
	public Object obtainAiNode(String var) {
		AiNode oAiNode = (AiNode)this;
		switch(var) {
			case "clusterName":
				return oAiNode.clusterName;
			case "nodeName":
				return oAiNode.nodeName;
			case "nodeId":
				return oAiNode.nodeId;
			case "description":
				return oAiNode.description;
			case "locationColors":
				return oAiNode.locationColors;
			case "locationTitles":
				return oAiNode.locationTitles;
			case "locationLinks":
				return oAiNode.locationLinks;
			case "location":
				return oAiNode.location;
			case "gpuDevicesTotal":
				return oAiNode.gpuDevicesTotal;
			case "id":
				return oAiNode.id;
			case "entityShortId":
				return oAiNode.entityShortId;
			case "ngsildTenant":
				return oAiNode.ngsildTenant;
			case "ngsildPath":
				return oAiNode.ngsildPath;
			case "ngsildContext":
				return oAiNode.ngsildContext;
			case "ngsildData":
				return oAiNode.ngsildData;
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
				o = relateAiNode(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateAiNode(String var, Object val) {
		AiNode oAiNode = (AiNode)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetAiNode(entityVar,  siteRequest_, o);
	}
	public static Object staticSetAiNode(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "clusterName":
			return AiNode.staticSetClusterName(siteRequest_, o);
		case "nodeName":
			return AiNode.staticSetNodeName(siteRequest_, o);
		case "nodeId":
			return AiNode.staticSetNodeId(siteRequest_, o);
		case "description":
			return AiNode.staticSetDescription(siteRequest_, o);
		case "locationColors":
			return AiNode.staticSetLocationColors(siteRequest_, o);
		case "locationTitles":
			return AiNode.staticSetLocationTitles(siteRequest_, o);
		case "locationLinks":
			return AiNode.staticSetLocationLinks(siteRequest_, o);
		case "location":
			return AiNode.staticSetLocation(siteRequest_, o);
		case "gpuDevicesTotal":
			return AiNode.staticSetGpuDevicesTotal(siteRequest_, o);
		case "id":
			return AiNode.staticSetId(siteRequest_, o);
		case "entityShortId":
			return AiNode.staticSetEntityShortId(siteRequest_, o);
		case "ngsildTenant":
			return AiNode.staticSetNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return AiNode.staticSetNgsildPath(siteRequest_, o);
		case "ngsildContext":
			return AiNode.staticSetNgsildContext(siteRequest_, o);
		case "ngsildData":
			return AiNode.staticSetNgsildData(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchAiNode(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchAiNode(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "clusterName":
			return AiNode.staticSearchClusterName(siteRequest_, (String)o);
		case "nodeName":
			return AiNode.staticSearchNodeName(siteRequest_, (String)o);
		case "nodeId":
			return AiNode.staticSearchNodeId(siteRequest_, (String)o);
		case "description":
			return AiNode.staticSearchDescription(siteRequest_, (String)o);
		case "locationColors":
			return AiNode.staticSearchLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return AiNode.staticSearchLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return AiNode.staticSearchLocationLinks(siteRequest_, (String)o);
		case "location":
			return AiNode.staticSearchLocation(siteRequest_, (Point)o);
		case "gpuDevicesTotal":
			return AiNode.staticSearchGpuDevicesTotal(siteRequest_, (Integer)o);
		case "id":
			return AiNode.staticSearchId(siteRequest_, (String)o);
		case "entityShortId":
			return AiNode.staticSearchEntityShortId(siteRequest_, (String)o);
		case "ngsildTenant":
			return AiNode.staticSearchNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return AiNode.staticSearchNgsildPath(siteRequest_, (String)o);
		case "ngsildContext":
			return AiNode.staticSearchNgsildContext(siteRequest_, (String)o);
		case "ngsildData":
			return AiNode.staticSearchNgsildData(siteRequest_, (JsonObject)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrAiNode(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrAiNode(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "clusterName":
			return AiNode.staticSearchStrClusterName(siteRequest_, (String)o);
		case "nodeName":
			return AiNode.staticSearchStrNodeName(siteRequest_, (String)o);
		case "nodeId":
			return AiNode.staticSearchStrNodeId(siteRequest_, (String)o);
		case "description":
			return AiNode.staticSearchStrDescription(siteRequest_, (String)o);
		case "locationColors":
			return AiNode.staticSearchStrLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return AiNode.staticSearchStrLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return AiNode.staticSearchStrLocationLinks(siteRequest_, (String)o);
		case "location":
			return AiNode.staticSearchStrLocation(siteRequest_, (Point)o);
		case "gpuDevicesTotal":
			return AiNode.staticSearchStrGpuDevicesTotal(siteRequest_, (Integer)o);
		case "id":
			return AiNode.staticSearchStrId(siteRequest_, (String)o);
		case "entityShortId":
			return AiNode.staticSearchStrEntityShortId(siteRequest_, (String)o);
		case "ngsildTenant":
			return AiNode.staticSearchStrNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return AiNode.staticSearchStrNgsildPath(siteRequest_, (String)o);
		case "ngsildContext":
			return AiNode.staticSearchStrNgsildContext(siteRequest_, (String)o);
		case "ngsildData":
			return AiNode.staticSearchStrNgsildData(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqAiNode(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqAiNode(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "clusterName":
			return AiNode.staticSearchFqClusterName(siteRequest_, o);
		case "nodeName":
			return AiNode.staticSearchFqNodeName(siteRequest_, o);
		case "nodeId":
			return AiNode.staticSearchFqNodeId(siteRequest_, o);
		case "description":
			return AiNode.staticSearchFqDescription(siteRequest_, o);
		case "locationColors":
			return AiNode.staticSearchFqLocationColors(siteRequest_, o);
		case "locationTitles":
			return AiNode.staticSearchFqLocationTitles(siteRequest_, o);
		case "locationLinks":
			return AiNode.staticSearchFqLocationLinks(siteRequest_, o);
		case "location":
			return AiNode.staticSearchFqLocation(siteRequest_, o);
		case "gpuDevicesTotal":
			return AiNode.staticSearchFqGpuDevicesTotal(siteRequest_, o);
		case "id":
			return AiNode.staticSearchFqId(siteRequest_, o);
		case "entityShortId":
			return AiNode.staticSearchFqEntityShortId(siteRequest_, o);
		case "ngsildTenant":
			return AiNode.staticSearchFqNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return AiNode.staticSearchFqNgsildPath(siteRequest_, o);
		case "ngsildContext":
			return AiNode.staticSearchFqNgsildContext(siteRequest_, o);
		case "ngsildData":
			return AiNode.staticSearchFqNgsildData(siteRequest_, o);
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
					o = persistAiNode(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistAiNode(String var, Object val) {
		String varLower = var.toLowerCase();
			if("clustername".equals(varLower)) {
				if(val instanceof String) {
					setClusterName((String)val);
				}
				saves.add("clusterName");
				return val;
			} else if("nodename".equals(varLower)) {
				if(val instanceof String) {
					setNodeName((String)val);
				}
				saves.add("nodeName");
				return val;
			} else if("nodeid".equals(varLower)) {
				if(val instanceof String) {
					setNodeId((String)val);
				}
				saves.add("nodeId");
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
			} else if("gpudevicestotal".equals(varLower)) {
				if(val instanceof Integer) {
					setGpuDevicesTotal((Integer)val);
				} else {
					setGpuDevicesTotal(val == null ? null : val.toString());
				}
				saves.add("gpuDevicesTotal");
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
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateAiNode(doc);
	}
	public void populateAiNode(SolrResponse.Doc doc) {
		AiNode oAiNode = (AiNode)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("clusterName")) {
				String clusterName = (String)doc.get("clusterName_docvalues_string");
				if(clusterName != null)
					oAiNode.setClusterName(clusterName);
			}

			if(saves.contains("nodeName")) {
				String nodeName = (String)doc.get("nodeName_docvalues_string");
				if(nodeName != null)
					oAiNode.setNodeName(nodeName);
			}

			if(saves.contains("nodeId")) {
				String nodeId = (String)doc.get("nodeId_docvalues_string");
				if(nodeId != null)
					oAiNode.setNodeId(nodeId);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oAiNode.setDescription(description);
			}

			if(saves.contains("locationColors")) {
				List<String> locationColors = (List<String>)doc.get("locationColors_indexedstored_strings");
				if(locationColors != null) {
					locationColors.stream().forEach( v -> {
						oAiNode.locationColors.add(AiNode.staticSetLocationColors(siteRequest_, v));
					});
				}
			}

			if(saves.contains("locationTitles")) {
				List<String> locationTitles = (List<String>)doc.get("locationTitles_indexedstored_strings");
				if(locationTitles != null) {
					locationTitles.stream().forEach( v -> {
						oAiNode.locationTitles.add(AiNode.staticSetLocationTitles(siteRequest_, v));
					});
				}
			}

			if(saves.contains("locationLinks")) {
				List<String> locationLinks = (List<String>)doc.get("locationLinks_indexedstored_strings");
				if(locationLinks != null) {
					locationLinks.stream().forEach( v -> {
						oAiNode.locationLinks.add(AiNode.staticSetLocationLinks(siteRequest_, v));
					});
				}
			}

			if(saves.contains("location")) {
				Point location = (Point)doc.get("location_docvalues_location");
				if(location != null)
					oAiNode.setLocation(location);
			}

			if(saves.contains("gpuDevicesTotal")) {
				Integer gpuDevicesTotal = (Integer)doc.get("gpuDevicesTotal_docvalues_int");
				if(gpuDevicesTotal != null)
					oAiNode.setGpuDevicesTotal(gpuDevicesTotal);
			}

			if(saves.contains("id")) {
				String id = (String)doc.get("id_docvalues_string");
				if(id != null)
					oAiNode.setId(id);
			}

			if(saves.contains("entityShortId")) {
				String entityShortId = (String)doc.get("entityShortId_docvalues_string");
				if(entityShortId != null)
					oAiNode.setEntityShortId(entityShortId);
			}

			if(saves.contains("ngsildTenant")) {
				String ngsildTenant = (String)doc.get("ngsildTenant_docvalues_string");
				if(ngsildTenant != null)
					oAiNode.setNgsildTenant(ngsildTenant);
			}

			if(saves.contains("ngsildPath")) {
				String ngsildPath = (String)doc.get("ngsildPath_docvalues_string");
				if(ngsildPath != null)
					oAiNode.setNgsildPath(ngsildPath);
			}

			if(saves.contains("ngsildContext")) {
				String ngsildContext = (String)doc.get("ngsildContext_docvalues_string");
				if(ngsildContext != null)
					oAiNode.setNgsildContext(ngsildContext);
			}

			if(saves.contains("ngsildData")) {
				String ngsildData = (String)doc.get("ngsildData_docvalues_string");
				if(ngsildData != null)
					oAiNode.setNgsildData(ngsildData);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexAiNode(JsonObject doc) {
		if(clusterName != null) {
			doc.put("clusterName_docvalues_string", clusterName);
		}
		if(nodeName != null) {
			doc.put("nodeName_docvalues_string", nodeName);
		}
		if(nodeId != null) {
			doc.put("nodeId_docvalues_string", nodeId);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(locationColors != null) {
			JsonArray l = new JsonArray();
			doc.put("locationColors_indexedstored_strings", l);
			for(String o : locationColors) {
				l.add(AiNode.staticSearchLocationColors(siteRequest_, o));
			}
		}
		if(locationTitles != null) {
			JsonArray l = new JsonArray();
			doc.put("locationTitles_indexedstored_strings", l);
			for(String o : locationTitles) {
				l.add(AiNode.staticSearchLocationTitles(siteRequest_, o));
			}
		}
		if(locationLinks != null) {
			JsonArray l = new JsonArray();
			doc.put("locationLinks_indexedstored_strings", l);
			for(String o : locationLinks) {
				l.add(AiNode.staticSearchLocationLinks(siteRequest_, o));
			}
		}
		if(location != null) {
			doc.put("location_docvalues_location", String.format("%s,%s", location.getY(), location.getX()));
		}
		if(gpuDevicesTotal != null) {
			doc.put("gpuDevicesTotal_docvalues_int", gpuDevicesTotal);
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
		super.indexBaseModel(doc);

	}

	public static String varStoredAiNode(String entityVar) {
		switch(entityVar) {
			case "clusterName":
				return "clusterName_docvalues_string";
			case "nodeName":
				return "nodeName_docvalues_string";
			case "nodeId":
				return "nodeId_docvalues_string";
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
			case "gpuDevicesTotal":
				return "gpuDevicesTotal_docvalues_int";
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
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedAiNode(String entityVar) {
		switch(entityVar) {
			case "clusterName":
				return "clusterName_docvalues_string";
			case "nodeName":
				return "nodeName_docvalues_string";
			case "nodeId":
				return "nodeId_docvalues_string";
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
			case "gpuDevicesTotal":
				return "gpuDevicesTotal_docvalues_int";
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
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarAiNode(String searchVar) {
		switch(searchVar) {
			case "clusterName_docvalues_string":
				return "clusterName";
			case "nodeName_docvalues_string":
				return "nodeName";
			case "nodeId_docvalues_string":
				return "nodeId";
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
			case "gpuDevicesTotal_docvalues_int":
				return "gpuDevicesTotal";
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
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchAiNode(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedAiNode(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeAiNode(doc);
	}
	public void storeAiNode(SolrResponse.Doc doc) {
		AiNode oAiNode = (AiNode)this;
		SiteRequest siteRequest = oAiNode.getSiteRequest_();

		oAiNode.setClusterName(Optional.ofNullable(doc.get("clusterName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiNode.setNodeName(Optional.ofNullable(doc.get("nodeName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiNode.setNodeId(Optional.ofNullable(doc.get("nodeId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiNode.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("locationColors_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oAiNode.addLocationColors(AiNode.staticSetLocationColors(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationTitles_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oAiNode.addLocationTitles(AiNode.staticSetLocationTitles(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationLinks_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oAiNode.addLocationLinks(AiNode.staticSetLocationLinks(siteRequest, v.toString()));
		});
		oAiNode.setLocation(Optional.ofNullable(doc.get("location_docvalues_location")).map(v -> v.toString()).orElse(null));
		oAiNode.setGpuDevicesTotal(Optional.ofNullable(doc.get("gpuDevicesTotal_docvalues_int")).map(v -> v.toString()).orElse(null));
		oAiNode.setId(Optional.ofNullable(doc.get("id_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiNode.setEntityShortId(Optional.ofNullable(doc.get("entityShortId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiNode.setNgsildTenant(Optional.ofNullable(doc.get("ngsildTenant_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiNode.setNgsildPath(Optional.ofNullable(doc.get("ngsildPath_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiNode.setNgsildContext(Optional.ofNullable(doc.get("ngsildContext_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiNode.setNgsildData(Optional.ofNullable(doc.get("ngsildData_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestAiNode() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof AiNode) {
			AiNode original = (AiNode)o;
			if(!Objects.equals(clusterName, original.getClusterName()))
				apiRequest.addVars("clusterName");
			if(!Objects.equals(nodeName, original.getNodeName()))
				apiRequest.addVars("nodeName");
			if(!Objects.equals(nodeId, original.getNodeId()))
				apiRequest.addVars("nodeId");
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
			if(!Objects.equals(gpuDevicesTotal, original.getGpuDevicesTotal()))
				apiRequest.addVars("gpuDevicesTotal");
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
		sb.append(Optional.ofNullable(nodeName).map(v -> "nodeName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(nodeId).map(v -> "nodeId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationColors).map(v -> "locationColors: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationTitles).map(v -> "locationTitles: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationLinks).map(v -> "locationLinks: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(location).map(v -> "location: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(gpuDevicesTotal).map(v -> "gpuDevicesTotal: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(id).map(v -> "id: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(entityShortId).map(v -> "entityShortId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildTenant).map(v -> "ngsildTenant: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildPath).map(v -> "ngsildPath: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildContext).map(v -> "ngsildContext: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildData).map(v -> "ngsildData: " + v + "\n").orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "AiNode";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.node.AiNode";
	public static final String CLASS_API_ADDRESS_AiNode = "ai-telemetry-enUS-AiNode";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_AiNode;
	}
	public static final String VAR_clusterName = "clusterName";
	public static final String VAR_nodeName = "nodeName";
	public static final String VAR_nodeId = "nodeId";
	public static final String VAR_description = "description";
	public static final String VAR_locationColors = "locationColors";
	public static final String VAR_locationTitles = "locationTitles";
	public static final String VAR_locationLinks = "locationLinks";
	public static final String VAR_location = "location";
	public static final String VAR_gpuDevicesTotal = "gpuDevicesTotal";
	public static final String VAR_id = "id";
	public static final String VAR_entityShortId = "entityShortId";
	public static final String VAR_ngsildTenant = "ngsildTenant";
	public static final String VAR_ngsildPath = "ngsildPath";
	public static final String VAR_ngsildContext = "ngsildContext";
	public static final String VAR_ngsildData = "ngsildData";

	public static List<String> varsQForClass() {
		return AiNode.varsQAiNode(new ArrayList<String>());
	}
	public static List<String> varsQAiNode(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return AiNode.varsFqAiNode(new ArrayList<String>());
	}
	public static List<String> varsFqAiNode(List<String> vars) {
		vars.add(VAR_clusterName);
		vars.add(VAR_nodeName);
		vars.add(VAR_nodeId);
		vars.add(VAR_location);
		vars.add(VAR_gpuDevicesTotal);
		vars.add(VAR_id);
		vars.add(VAR_entityShortId);
		vars.add(VAR_ngsildTenant);
		vars.add(VAR_ngsildPath);
		vars.add(VAR_ngsildContext);
		vars.add(VAR_ngsildData);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return AiNode.varsRangeAiNode(new ArrayList<String>());
	}
	public static List<String> varsRangeAiNode(List<String> vars) {
		vars.add(VAR_location);
		vars.add(VAR_gpuDevicesTotal);
		vars.add(VAR_ngsildData);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_clusterName = "cluster name";
	public static final String DISPLAY_NAME_nodeName = "node name";
	public static final String DISPLAY_NAME_nodeId = "node ID";
	public static final String DISPLAY_NAME_description = "description";
	public static final String DISPLAY_NAME_locationColors = "area served colors";
	public static final String DISPLAY_NAME_locationTitles = "area served titles";
	public static final String DISPLAY_NAME_locationLinks = "area served links";
	public static final String DISPLAY_NAME_location = "location";
	public static final String DISPLAY_NAME_gpuDevicesTotal = "GPU devices total";
	public static final String DISPLAY_NAME_id = "entity ID";
	public static final String DISPLAY_NAME_entityShortId = "short entity ID";
	public static final String DISPLAY_NAME_ngsildTenant = "NGSILD-Tenant";
	public static final String DISPLAY_NAME_ngsildPath = "NGSILD-Path";
	public static final String DISPLAY_NAME_ngsildContext = "NGSILD context";
	public static final String DISPLAY_NAME_ngsildData = "NGSILD data";

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
		return AiNode.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/ai-node/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/ai-node/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return AiNode.displayNameAiNode(var);
	}
	public static String displayNameAiNode(String var) {
		switch(var) {
		case VAR_clusterName:
			return DISPLAY_NAME_clusterName;
		case VAR_nodeName:
			return DISPLAY_NAME_nodeName;
		case VAR_nodeId:
			return DISPLAY_NAME_nodeId;
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
		case VAR_gpuDevicesTotal:
			return DISPLAY_NAME_gpuDevicesTotal;
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
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionAiNode(String var) {
		switch(var) {
		case VAR_clusterName:
			return "The name of this cluster";
		case VAR_nodeName:
			return "The name of this node";
		case VAR_nodeId:
			return "The unique ID of this node";
		case VAR_description:
			return "A description of this node";
		case VAR_locationColors:
			return "The colors of each location Paths. ";
		case VAR_locationTitles:
			return "The titles of each location Paths. ";
		case VAR_locationLinks:
			return "The links of each location Paths. ";
		case VAR_location:
			return "Geojson reference to the item. It can be Point, LineString, Polygon, MultiPoint, MultiLineString or MultiPolygon";
		case VAR_gpuDevicesTotal:
			return "The total number of GPU devices on this cluster. ";
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
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameAiNode(String var) {
		switch(var) {
		case VAR_clusterName:
			return "String";
		case VAR_nodeName:
			return "String";
		case VAR_nodeId:
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
		case VAR_gpuDevicesTotal:
			return "Integer";
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
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnAiNode(String var) {
		switch(var) {
		case VAR_clusterName:
			return 1;
		case VAR_nodeName:
			return 2;
		case VAR_description:
			return 3;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowAiNode(String var) {
		switch(var) {
		case VAR_clusterName:
			return 3;
		case VAR_nodeName:
			return 3;
		case VAR_nodeId:
			return 3;
		case VAR_description:
			return 3;
		case VAR_location:
			return 10;
		case VAR_gpuDevicesTotal:
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
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellAiNode(String var) {
		switch(var) {
		case VAR_clusterName:
			return 1;
		case VAR_nodeName:
			return 2;
		case VAR_nodeId:
			return 3;
		case VAR_description:
			return 2;
		case VAR_location:
			return 1;
		case VAR_gpuDevicesTotal:
			return 6;
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
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinAiNode(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxAiNode(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxAiNode(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minAiNode(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
