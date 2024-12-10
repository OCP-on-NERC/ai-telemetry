package org.mghpcc.aitelemetry.model.gpudevice;

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
import java.lang.Integer;
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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class GpuDeviceGen into the class GpuDevice. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the GpuDevice API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the GpuDevice class and it's generated class GpuDeviceGen&lt;BaseModel&gt;: </h3>extends GpuDeviceGen
 * <p>
 * This Java class extends a generated Java class GpuDeviceGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice">Find the class GpuDevice in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends GpuDeviceGen<BaseModel>
 * <p>This <code>class GpuDevice extends GpuDeviceGen&lt;BaseModel&gt;</code>, which means it extends a newly generated GpuDeviceGen. 
 * The generated <code>class GpuDeviceGen extends BaseModel</code> which means that GpuDevice extends GpuDeviceGen which extends BaseModel. 
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
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: GPU devices"</b>, which groups all of the OpenAPIs for GpuDevice objects under the tag "GPU devices". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/gpu-device</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/gpu-device"</b>, which defines the base API URI for GpuDevice objects as "/en-us/api/gpu-device" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the GpuDevice class will inherit the helpful inherited class comments from the super class GpuDeviceGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 5</h2>
 * <p>This class contains a comment <b>"Order: 5"</b>, which means this class will be sorted by the given number 5 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.gpudevice.GpuDevicePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.gpudevice.GpuDevicePage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the GpuDevice Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a GPU device</h2>
 * <p>This class contains a comment <b>"AName.enUS: a GPU device"</b>, which identifies the language context to describe a GpuDevice as "a GPU device". 
 * </p>
 * <p>
 * Delete the class GpuDevice in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.gpudevice in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class GpuDeviceGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(GpuDevice.class);

	public static final String Description_enUS = "A Red Hat OpenShift GPU device containing GPUs";
	public static final String AName_enUS = "a GPU device";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this GPU device";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the GPU device";
	public static final String SingularName_enUS = "GPU device";
	public static final String PluralName_enUS = "GPU devices";
	public static final String NameActual_enUS = "current GPU device";
	public static final String AllName_enUS = "all GPU devices";
	public static final String SearchAllNameBy_enUS = "search GPU devices by ";
	public static final String Title_enUS = "GPU devices";
	public static final String ThePluralName_enUS = "the GPU devices";
	public static final String NoNameFound_enUS = "no GPU device found";
	public static final String ApiUri_enUS = "/en-us/api/gpu-device";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/gpu-device";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/gpu-device/{pageId}";
	public static final String OfName_enUS = "of GPU device";
	public static final String ANameAdjective_enUS = "a GPU device";
	public static final String NameAdjectiveSingular_enUS = "GPU device";
	public static final String NameAdjectivePlural_enUS = "GPU devices";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/gpu-device";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/gpu-device";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/gpu-device";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/gpu-device/{entityId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/gpu-device/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/gpu-device/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/gpu-device";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/gpu-device";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/gpu-device";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/gpu-device";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/gpu-device";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/gpu-device";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/gpu-device/{entityId}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/gpu-device/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/gpu-device/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/gpu-device-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/gpu-device-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/gpu-device-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/gpu-device";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/gpu-device";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/gpu-device";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/gpu-device/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/gpu-device/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/gpu-device/%s";

	public static final String Icon = "<i class=\"fa-regular fa-memory\"></i>";

	/////////////////
	// gpuDeviceId //
	/////////////////


	/**	 The entity gpuDeviceId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String gpuDeviceId;

	/**	<br> The entity gpuDeviceId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:gpuDeviceId">Find the entity gpuDeviceId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuDeviceId(Wrap<String> w);

	public String getGpuDeviceId() {
		return gpuDeviceId;
	}
	@JsonIgnore
	public void setGpuDeviceId(String o) {
		this.gpuDeviceId = GpuDevice.staticSetGpuDeviceId(siteRequest_, o);
	}
	public static String staticSetGpuDeviceId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDevice gpuDeviceIdInit() {
		Wrap<String> gpuDeviceIdWrap = new Wrap<String>().var("gpuDeviceId");
		if(gpuDeviceId == null) {
			_gpuDeviceId(gpuDeviceIdWrap);
			Optional.ofNullable(gpuDeviceIdWrap.getO()).ifPresent(o -> {
				setGpuDeviceId(o);
			});
		}
		return (GpuDevice)this;
	}

	public static String staticSearchGpuDeviceId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrGpuDeviceId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuDeviceId(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchGpuDeviceId(siteRequest_, GpuDevice.staticSetGpuDeviceId(siteRequest_, o)).toString();
	}

	public String sqlGpuDeviceId() {
		return gpuDeviceId;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:clusterName">Find the entity clusterName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterName(Wrap<String> w);

	public String getClusterName() {
		return clusterName;
	}
	@JsonIgnore
	public void setClusterName(String o) {
		this.clusterName = GpuDevice.staticSetClusterName(siteRequest_, o);
	}
	public static String staticSetClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDevice clusterNameInit() {
		Wrap<String> clusterNameWrap = new Wrap<String>().var("clusterName");
		if(clusterName == null) {
			_clusterName(clusterNameWrap);
			Optional.ofNullable(clusterNameWrap.getO()).ifPresent(o -> {
				setClusterName(o);
			});
		}
		return (GpuDevice)this;
	}

	public static String staticSearchClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterName(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchClusterName(siteRequest_, GpuDevice.staticSetClusterName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:nodeName">Find the entity nodeName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _nodeName(Wrap<String> w);

	public String getNodeName() {
		return nodeName;
	}
	@JsonIgnore
	public void setNodeName(String o) {
		this.nodeName = GpuDevice.staticSetNodeName(siteRequest_, o);
	}
	public static String staticSetNodeName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDevice nodeNameInit() {
		Wrap<String> nodeNameWrap = new Wrap<String>().var("nodeName");
		if(nodeName == null) {
			_nodeName(nodeNameWrap);
			Optional.ofNullable(nodeNameWrap.getO()).ifPresent(o -> {
				setNodeName(o);
			});
		}
		return (GpuDevice)this;
	}

	public static String staticSearchNodeName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNodeName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNodeName(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchNodeName(siteRequest_, GpuDevice.staticSetNodeName(siteRequest_, o)).toString();
	}

	public String sqlNodeName() {
		return nodeName;
	}

	/////////////////////
	// gpuDeviceNumber //
	/////////////////////


	/**	 The entity gpuDeviceNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer gpuDeviceNumber;

	/**	<br> The entity gpuDeviceNumber
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:gpuDeviceNumber">Find the entity gpuDeviceNumber in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuDeviceNumber(Wrap<Integer> w);

	public Integer getGpuDeviceNumber() {
		return gpuDeviceNumber;
	}

	public void setGpuDeviceNumber(Integer gpuDeviceNumber) {
		this.gpuDeviceNumber = gpuDeviceNumber;
	}
	@JsonIgnore
	public void setGpuDeviceNumber(String o) {
		this.gpuDeviceNumber = GpuDevice.staticSetGpuDeviceNumber(siteRequest_, o);
	}
	public static Integer staticSetGpuDeviceNumber(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected GpuDevice gpuDeviceNumberInit() {
		Wrap<Integer> gpuDeviceNumberWrap = new Wrap<Integer>().var("gpuDeviceNumber");
		if(gpuDeviceNumber == null) {
			_gpuDeviceNumber(gpuDeviceNumberWrap);
			Optional.ofNullable(gpuDeviceNumberWrap.getO()).ifPresent(o -> {
				setGpuDeviceNumber(o);
			});
		}
		return (GpuDevice)this;
	}

	public static Integer staticSearchGpuDeviceNumber(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrGpuDeviceNumber(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuDeviceNumber(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchGpuDeviceNumber(siteRequest_, GpuDevice.staticSetGpuDeviceNumber(siteRequest_, o)).toString();
	}

	public Integer sqlGpuDeviceNumber() {
		return gpuDeviceNumber;
	}

	//////////////////////////
	// gpuDeviceUtilization //
	//////////////////////////


	/**	 The entity gpuDeviceUtilization
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer gpuDeviceUtilization;

	/**	<br> The entity gpuDeviceUtilization
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:gpuDeviceUtilization">Find the entity gpuDeviceUtilization in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuDeviceUtilization(Wrap<Integer> w);

	public Integer getGpuDeviceUtilization() {
		return gpuDeviceUtilization;
	}

	public void setGpuDeviceUtilization(Integer gpuDeviceUtilization) {
		this.gpuDeviceUtilization = gpuDeviceUtilization;
	}
	@JsonIgnore
	public void setGpuDeviceUtilization(String o) {
		this.gpuDeviceUtilization = GpuDevice.staticSetGpuDeviceUtilization(siteRequest_, o);
	}
	public static Integer staticSetGpuDeviceUtilization(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected GpuDevice gpuDeviceUtilizationInit() {
		Wrap<Integer> gpuDeviceUtilizationWrap = new Wrap<Integer>().var("gpuDeviceUtilization");
		if(gpuDeviceUtilization == null) {
			_gpuDeviceUtilization(gpuDeviceUtilizationWrap);
			Optional.ofNullable(gpuDeviceUtilizationWrap.getO()).ifPresent(o -> {
				setGpuDeviceUtilization(o);
			});
		}
		return (GpuDevice)this;
	}

	public static Integer staticSearchGpuDeviceUtilization(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrGpuDeviceUtilization(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuDeviceUtilization(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchGpuDeviceUtilization(siteRequest_, GpuDevice.staticSetGpuDeviceUtilization(siteRequest_, o)).toString();
	}

	public Integer sqlGpuDeviceUtilization() {
		return gpuDeviceUtilization;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	@JsonIgnore
	public void setDescription(String o) {
		this.description = GpuDevice.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDevice descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (GpuDevice)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchDescription(siteRequest_, GpuDevice.staticSetDescription(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:locationColors">Find the entity locationColors in Solr</a>
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
		String l = GpuDevice.staticSetLocationColors(siteRequest_, o);
		if(l != null)
			addLocationColors(l);
	}
	public static String staticSetLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}
	public GpuDevice addLocationColors(String...objects) {
		for(String o : objects) {
			addLocationColors(o);
		}
		return (GpuDevice)this;
	}
	public GpuDevice addLocationColors(String o) {
		if(o != null)
			this.locationColors.add(o);
		return (GpuDevice)this;
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
	protected GpuDevice locationColorsInit() {
		_locationColors(locationColors);
		return (GpuDevice)this;
	}

	public static String staticSearchLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationColors(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationColors(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchLocationColors(siteRequest_, GpuDevice.staticSetLocationColors(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:locationTitles">Find the entity locationTitles in Solr</a>
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
		String l = GpuDevice.staticSetLocationTitles(siteRequest_, o);
		if(l != null)
			addLocationTitles(l);
	}
	public static String staticSetLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}
	public GpuDevice addLocationTitles(String...objects) {
		for(String o : objects) {
			addLocationTitles(o);
		}
		return (GpuDevice)this;
	}
	public GpuDevice addLocationTitles(String o) {
		if(o != null)
			this.locationTitles.add(o);
		return (GpuDevice)this;
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
	protected GpuDevice locationTitlesInit() {
		_locationTitles(locationTitles);
		return (GpuDevice)this;
	}

	public static String staticSearchLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationTitles(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationTitles(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchLocationTitles(siteRequest_, GpuDevice.staticSetLocationTitles(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:locationLinks">Find the entity locationLinks in Solr</a>
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
		String l = GpuDevice.staticSetLocationLinks(siteRequest_, o);
		if(l != null)
			addLocationLinks(l);
	}
	public static String staticSetLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}
	public GpuDevice addLocationLinks(String...objects) {
		for(String o : objects) {
			addLocationLinks(o);
		}
		return (GpuDevice)this;
	}
	public GpuDevice addLocationLinks(String o) {
		if(o != null)
			this.locationLinks.add(o);
		return (GpuDevice)this;
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
	protected GpuDevice locationLinksInit() {
		_locationLinks(locationLinks);
		return (GpuDevice)this;
	}

	public static String staticSearchLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationLinks(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationLinks(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchLocationLinks(siteRequest_, GpuDevice.staticSetLocationLinks(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:location">Find the entity location in Solr</a>
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
		this.location = GpuDevice.staticSetLocation(siteRequest_, o);
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
	public void setLocation(JsonObject o) {
		this.location = GpuDevice.staticSetLocation(siteRequest_, o);
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
	protected GpuDevice locationInit() {
		Wrap<Point> locationWrap = new Wrap<Point>().var("location");
		if(location == null) {
			_location(locationWrap);
			Optional.ofNullable(locationWrap.getO()).ifPresent(o -> {
				setLocation(o);
			});
		}
		return (GpuDevice)this;
	}

	public static Point staticSearchLocation(SiteRequest siteRequest_, Point o) {
		return o;
	}

	public static String staticSearchStrLocation(SiteRequest siteRequest_, Point o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocation(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchLocation(siteRequest_, GpuDevice.staticSetLocation(siteRequest_, o)).toString();
	}

	public Point sqlLocation() {
		return location;
	}

	//////////////
	// entityId //
	//////////////


	/**	 The entity entityId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String entityId;

	/**	<br> The entity entityId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:entityId">Find the entity entityId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityId(Wrap<String> w);

	public String getEntityId() {
		return entityId;
	}
	@JsonIgnore
	public void setEntityId(String o) {
		this.entityId = GpuDevice.staticSetEntityId(siteRequest_, o);
	}
	public static String staticSetEntityId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDevice entityIdInit() {
		Wrap<String> entityIdWrap = new Wrap<String>().var("entityId");
		if(entityId == null) {
			_entityId(entityIdWrap);
			Optional.ofNullable(entityIdWrap.getO()).ifPresent(o -> {
				setEntityId(o);
			});
		}
		return (GpuDevice)this;
	}

	public static String staticSearchEntityId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityId(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchEntityId(siteRequest_, GpuDevice.staticSetEntityId(siteRequest_, o)).toString();
	}

	public String sqlEntityId() {
		return entityId;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDevice&fq=entiteVar_enUS_indexed_string:entityShortId">Find the entity entityShortId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityShortId(Wrap<String> w);

	public String getEntityShortId() {
		return entityShortId;
	}
	@JsonIgnore
	public void setEntityShortId(String o) {
		this.entityShortId = GpuDevice.staticSetEntityShortId(siteRequest_, o);
	}
	public static String staticSetEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDevice entityShortIdInit() {
		Wrap<String> entityShortIdWrap = new Wrap<String>().var("entityShortId");
		if(entityShortId == null) {
			_entityShortId(entityShortIdWrap);
			Optional.ofNullable(entityShortIdWrap.getO()).ifPresent(o -> {
				setEntityShortId(o);
			});
		}
		return (GpuDevice)this;
	}

	public static String staticSearchEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityShortId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityShortId(SiteRequest siteRequest_, String o) {
		return GpuDevice.staticSearchEntityShortId(siteRequest_, GpuDevice.staticSetEntityShortId(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<GpuDeviceGen<DEV>> promiseDeepGpuDevice(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepGpuDevice();
	}

	public Future<GpuDeviceGen<DEV>> promiseDeepGpuDevice() {
		Promise<GpuDeviceGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseGpuDevice(promise2);
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

	public Future<Void> promiseGpuDevice(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				gpuDeviceIdInit();
				clusterNameInit();
				nodeNameInit();
				gpuDeviceNumberInit();
				gpuDeviceUtilizationInit();
				descriptionInit();
				locationColorsInit();
				locationTitlesInit();
				locationLinksInit();
				locationInit();
				entityIdInit();
				entityShortIdInit();
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

	@Override public Future<? extends GpuDeviceGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepGpuDevice(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestGpuDevice(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestGpuDevice(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainGpuDevice(v);
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
	public Object obtainGpuDevice(String var) {
		GpuDevice oGpuDevice = (GpuDevice)this;
		switch(var) {
			case "gpuDeviceId":
				return oGpuDevice.gpuDeviceId;
			case "clusterName":
				return oGpuDevice.clusterName;
			case "nodeName":
				return oGpuDevice.nodeName;
			case "gpuDeviceNumber":
				return oGpuDevice.gpuDeviceNumber;
			case "gpuDeviceUtilization":
				return oGpuDevice.gpuDeviceUtilization;
			case "description":
				return oGpuDevice.description;
			case "locationColors":
				return oGpuDevice.locationColors;
			case "locationTitles":
				return oGpuDevice.locationTitles;
			case "locationLinks":
				return oGpuDevice.locationLinks;
			case "location":
				return oGpuDevice.location;
			case "entityId":
				return oGpuDevice.entityId;
			case "entityShortId":
				return oGpuDevice.entityShortId;
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
				o = relateGpuDevice(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateGpuDevice(String var, Object val) {
		GpuDevice oGpuDevice = (GpuDevice)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetGpuDevice(entityVar,  siteRequest_, o);
	}
	public static Object staticSetGpuDevice(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "gpuDeviceId":
			return GpuDevice.staticSetGpuDeviceId(siteRequest_, o);
		case "clusterName":
			return GpuDevice.staticSetClusterName(siteRequest_, o);
		case "nodeName":
			return GpuDevice.staticSetNodeName(siteRequest_, o);
		case "gpuDeviceNumber":
			return GpuDevice.staticSetGpuDeviceNumber(siteRequest_, o);
		case "gpuDeviceUtilization":
			return GpuDevice.staticSetGpuDeviceUtilization(siteRequest_, o);
		case "description":
			return GpuDevice.staticSetDescription(siteRequest_, o);
		case "locationColors":
			return GpuDevice.staticSetLocationColors(siteRequest_, o);
		case "locationTitles":
			return GpuDevice.staticSetLocationTitles(siteRequest_, o);
		case "locationLinks":
			return GpuDevice.staticSetLocationLinks(siteRequest_, o);
		case "location":
			return GpuDevice.staticSetLocation(siteRequest_, o);
		case "entityId":
			return GpuDevice.staticSetEntityId(siteRequest_, o);
		case "entityShortId":
			return GpuDevice.staticSetEntityShortId(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchGpuDevice(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchGpuDevice(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "gpuDeviceId":
			return GpuDevice.staticSearchGpuDeviceId(siteRequest_, (String)o);
		case "clusterName":
			return GpuDevice.staticSearchClusterName(siteRequest_, (String)o);
		case "nodeName":
			return GpuDevice.staticSearchNodeName(siteRequest_, (String)o);
		case "gpuDeviceNumber":
			return GpuDevice.staticSearchGpuDeviceNumber(siteRequest_, (Integer)o);
		case "gpuDeviceUtilization":
			return GpuDevice.staticSearchGpuDeviceUtilization(siteRequest_, (Integer)o);
		case "description":
			return GpuDevice.staticSearchDescription(siteRequest_, (String)o);
		case "locationColors":
			return GpuDevice.staticSearchLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return GpuDevice.staticSearchLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return GpuDevice.staticSearchLocationLinks(siteRequest_, (String)o);
		case "location":
			return GpuDevice.staticSearchLocation(siteRequest_, (Point)o);
		case "entityId":
			return GpuDevice.staticSearchEntityId(siteRequest_, (String)o);
		case "entityShortId":
			return GpuDevice.staticSearchEntityShortId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrGpuDevice(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrGpuDevice(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "gpuDeviceId":
			return GpuDevice.staticSearchStrGpuDeviceId(siteRequest_, (String)o);
		case "clusterName":
			return GpuDevice.staticSearchStrClusterName(siteRequest_, (String)o);
		case "nodeName":
			return GpuDevice.staticSearchStrNodeName(siteRequest_, (String)o);
		case "gpuDeviceNumber":
			return GpuDevice.staticSearchStrGpuDeviceNumber(siteRequest_, (Integer)o);
		case "gpuDeviceUtilization":
			return GpuDevice.staticSearchStrGpuDeviceUtilization(siteRequest_, (Integer)o);
		case "description":
			return GpuDevice.staticSearchStrDescription(siteRequest_, (String)o);
		case "locationColors":
			return GpuDevice.staticSearchStrLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return GpuDevice.staticSearchStrLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return GpuDevice.staticSearchStrLocationLinks(siteRequest_, (String)o);
		case "location":
			return GpuDevice.staticSearchStrLocation(siteRequest_, (Point)o);
		case "entityId":
			return GpuDevice.staticSearchStrEntityId(siteRequest_, (String)o);
		case "entityShortId":
			return GpuDevice.staticSearchStrEntityShortId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqGpuDevice(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqGpuDevice(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "gpuDeviceId":
			return GpuDevice.staticSearchFqGpuDeviceId(siteRequest_, o);
		case "clusterName":
			return GpuDevice.staticSearchFqClusterName(siteRequest_, o);
		case "nodeName":
			return GpuDevice.staticSearchFqNodeName(siteRequest_, o);
		case "gpuDeviceNumber":
			return GpuDevice.staticSearchFqGpuDeviceNumber(siteRequest_, o);
		case "gpuDeviceUtilization":
			return GpuDevice.staticSearchFqGpuDeviceUtilization(siteRequest_, o);
		case "description":
			return GpuDevice.staticSearchFqDescription(siteRequest_, o);
		case "locationColors":
			return GpuDevice.staticSearchFqLocationColors(siteRequest_, o);
		case "locationTitles":
			return GpuDevice.staticSearchFqLocationTitles(siteRequest_, o);
		case "locationLinks":
			return GpuDevice.staticSearchFqLocationLinks(siteRequest_, o);
		case "location":
			return GpuDevice.staticSearchFqLocation(siteRequest_, o);
		case "entityId":
			return GpuDevice.staticSearchFqEntityId(siteRequest_, o);
		case "entityShortId":
			return GpuDevice.staticSearchFqEntityShortId(siteRequest_, o);
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
					o = persistGpuDevice(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistGpuDevice(String var, Object val) {
		String varLower = var.toLowerCase();
			if("gpudeviceid".equals(varLower)) {
				if(val instanceof String) {
					setGpuDeviceId((String)val);
				}
				saves.add("gpuDeviceId");
				return val;
			} else if("clustername".equals(varLower)) {
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
			} else if("gpudevicenumber".equals(varLower)) {
				if(val instanceof Integer) {
					setGpuDeviceNumber((Integer)val);
				} else {
					setGpuDeviceNumber(val == null ? null : val.toString());
				}
				saves.add("gpuDeviceNumber");
				return val;
			} else if("gpudeviceutilization".equals(varLower)) {
				if(val instanceof Integer) {
					setGpuDeviceUtilization((Integer)val);
				} else {
					setGpuDeviceUtilization(val == null ? null : val.toString());
				}
				saves.add("gpuDeviceUtilization");
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
			} else if("entityid".equals(varLower)) {
				if(val instanceof String) {
					setEntityId((String)val);
				}
				saves.add("entityId");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateGpuDevice(doc);
	}
	public void populateGpuDevice(SolrResponse.Doc doc) {
		GpuDevice oGpuDevice = (GpuDevice)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("gpuDeviceId")) {
				String gpuDeviceId = (String)doc.get("gpuDeviceId_docvalues_string");
				if(gpuDeviceId != null)
					oGpuDevice.setGpuDeviceId(gpuDeviceId);
			}

			if(saves.contains("clusterName")) {
				String clusterName = (String)doc.get("clusterName_docvalues_string");
				if(clusterName != null)
					oGpuDevice.setClusterName(clusterName);
			}

			if(saves.contains("nodeName")) {
				String nodeName = (String)doc.get("nodeName_docvalues_string");
				if(nodeName != null)
					oGpuDevice.setNodeName(nodeName);
			}

			if(saves.contains("gpuDeviceNumber")) {
				Integer gpuDeviceNumber = (Integer)doc.get("gpuDeviceNumber_docvalues_int");
				if(gpuDeviceNumber != null)
					oGpuDevice.setGpuDeviceNumber(gpuDeviceNumber);
			}

			if(saves.contains("gpuDeviceUtilization")) {
				Integer gpuDeviceUtilization = (Integer)doc.get("gpuDeviceUtilization_docvalues_int");
				if(gpuDeviceUtilization != null)
					oGpuDevice.setGpuDeviceUtilization(gpuDeviceUtilization);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oGpuDevice.setDescription(description);
			}

			if(saves.contains("locationColors")) {
				List<String> locationColors = (List<String>)doc.get("locationColors_indexedstored_strings");
				if(locationColors != null)
					oGpuDevice.locationColors.addAll(locationColors);
			}

			if(saves.contains("locationTitles")) {
				List<String> locationTitles = (List<String>)doc.get("locationTitles_indexedstored_strings");
				if(locationTitles != null)
					oGpuDevice.locationTitles.addAll(locationTitles);
			}

			if(saves.contains("locationLinks")) {
				List<String> locationLinks = (List<String>)doc.get("locationLinks_indexedstored_strings");
				if(locationLinks != null)
					oGpuDevice.locationLinks.addAll(locationLinks);
			}

			if(saves.contains("location")) {
				Point location = (Point)doc.get("location_docvalues_location");
				if(location != null)
					oGpuDevice.setLocation(location);
			}

			if(saves.contains("entityId")) {
				String entityId = (String)doc.get("entityId_docvalues_string");
				if(entityId != null)
					oGpuDevice.setEntityId(entityId);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexGpuDevice(JsonObject doc) {
		if(gpuDeviceId != null) {
			doc.put("gpuDeviceId_docvalues_string", gpuDeviceId);
		}
		if(clusterName != null) {
			doc.put("clusterName_docvalues_string", clusterName);
		}
		if(nodeName != null) {
			doc.put("nodeName_docvalues_string", nodeName);
		}
		if(gpuDeviceNumber != null) {
			doc.put("gpuDeviceNumber_docvalues_int", gpuDeviceNumber);
		}
		if(gpuDeviceUtilization != null) {
			doc.put("gpuDeviceUtilization_docvalues_int", gpuDeviceUtilization);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(locationColors != null) {
			JsonArray l = new JsonArray();
			doc.put("locationColors_indexedstored_strings", l);
			for(String o : locationColors) {
				l.add(o);
			}
		}
		if(locationTitles != null) {
			JsonArray l = new JsonArray();
			doc.put("locationTitles_indexedstored_strings", l);
			for(String o : locationTitles) {
				l.add(o);
			}
		}
		if(locationLinks != null) {
			JsonArray l = new JsonArray();
			doc.put("locationLinks_indexedstored_strings", l);
			for(String o : locationLinks) {
				l.add(o);
			}
		}
		if(location != null) {
			doc.put("location_docvalues_location", String.format("%s,%s", location.getX(), location.getY()));
		}
		if(entityId != null) {
			doc.put("entityId_docvalues_string", entityId);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredGpuDevice(String entityVar) {
		switch(entityVar) {
			case "gpuDeviceId":
				return "gpuDeviceId_docvalues_string";
			case "clusterName":
				return "clusterName_docvalues_string";
			case "nodeName":
				return "nodeName_docvalues_string";
			case "gpuDeviceNumber":
				return "gpuDeviceNumber_docvalues_int";
			case "gpuDeviceUtilization":
				return "gpuDeviceUtilization_docvalues_int";
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
			case "entityId":
				return "entityId_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedGpuDevice(String entityVar) {
		switch(entityVar) {
			case "gpuDeviceId":
				return "gpuDeviceId_docvalues_string";
			case "clusterName":
				return "clusterName_docvalues_string";
			case "nodeName":
				return "nodeName_docvalues_string";
			case "gpuDeviceNumber":
				return "gpuDeviceNumber_docvalues_int";
			case "gpuDeviceUtilization":
				return "gpuDeviceUtilization_docvalues_int";
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
			case "entityId":
				return "entityId_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarGpuDevice(String searchVar) {
		switch(searchVar) {
			case "gpuDeviceId_docvalues_string":
				return "gpuDeviceId";
			case "clusterName_docvalues_string":
				return "clusterName";
			case "nodeName_docvalues_string":
				return "nodeName";
			case "gpuDeviceNumber_docvalues_int":
				return "gpuDeviceNumber";
			case "gpuDeviceUtilization_docvalues_int":
				return "gpuDeviceUtilization";
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
			case "entityId_docvalues_string":
				return "entityId";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchGpuDevice(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedGpuDevice(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeGpuDevice(doc);
	}
	public void storeGpuDevice(SolrResponse.Doc doc) {
		GpuDevice oGpuDevice = (GpuDevice)this;
		SiteRequest siteRequest = oGpuDevice.getSiteRequest_();

		oGpuDevice.setGpuDeviceId(Optional.ofNullable(doc.get("gpuDeviceId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oGpuDevice.setClusterName(Optional.ofNullable(doc.get("clusterName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oGpuDevice.setNodeName(Optional.ofNullable(doc.get("nodeName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oGpuDevice.setGpuDeviceNumber(Optional.ofNullable(doc.get("gpuDeviceNumber_docvalues_int")).map(v -> v.toString()).orElse(null));
		oGpuDevice.setGpuDeviceUtilization(Optional.ofNullable(doc.get("gpuDeviceUtilization_docvalues_int")).map(v -> v.toString()).orElse(null));
		oGpuDevice.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("locationColors_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oGpuDevice.addLocationColors(GpuDevice.staticSetLocationColors(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationTitles_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oGpuDevice.addLocationTitles(GpuDevice.staticSetLocationTitles(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationLinks_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oGpuDevice.addLocationLinks(GpuDevice.staticSetLocationLinks(siteRequest, v.toString()));
		});
		oGpuDevice.setLocation(Optional.ofNullable(doc.get("location_docvalues_location")).map(v -> v.toString()).orElse(null));
		oGpuDevice.setEntityId(Optional.ofNullable(doc.get("entityId_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestGpuDevice() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof GpuDevice) {
			GpuDevice original = (GpuDevice)o;
			if(!Objects.equals(gpuDeviceId, original.getGpuDeviceId()))
				apiRequest.addVars("gpuDeviceId");
			if(!Objects.equals(clusterName, original.getClusterName()))
				apiRequest.addVars("clusterName");
			if(!Objects.equals(nodeName, original.getNodeName()))
				apiRequest.addVars("nodeName");
			if(!Objects.equals(gpuDeviceNumber, original.getGpuDeviceNumber()))
				apiRequest.addVars("gpuDeviceNumber");
			if(!Objects.equals(gpuDeviceUtilization, original.getGpuDeviceUtilization()))
				apiRequest.addVars("gpuDeviceUtilization");
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
			if(!Objects.equals(entityId, original.getEntityId()))
				apiRequest.addVars("entityId");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(gpuDeviceId).map(v -> "gpuDeviceId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(clusterName).map(v -> "clusterName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(nodeName).map(v -> "nodeName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(gpuDeviceNumber).map(v -> "gpuDeviceNumber: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(gpuDeviceUtilization).map(v -> "gpuDeviceUtilization: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationColors).map(v -> "locationColors: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationTitles).map(v -> "locationTitles: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationLinks).map(v -> "locationLinks: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(location).map(v -> "location: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(entityId).map(v -> "entityId: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "GpuDevice";
	public static final String CLASS_API_ADDRESS_GpuDevice = "ai-telemetry-enUS-GpuDevice";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_GpuDevice;
	}
	public static final String VAR_gpuDeviceId = "gpuDeviceId";
	public static final String VAR_clusterName = "clusterName";
	public static final String VAR_nodeName = "nodeName";
	public static final String VAR_gpuDeviceNumber = "gpuDeviceNumber";
	public static final String VAR_gpuDeviceUtilization = "gpuDeviceUtilization";
	public static final String VAR_description = "description";
	public static final String VAR_locationColors = "locationColors";
	public static final String VAR_locationTitles = "locationTitles";
	public static final String VAR_locationLinks = "locationLinks";
	public static final String VAR_location = "location";
	public static final String VAR_entityId = "entityId";
	public static final String VAR_entityShortId = "entityShortId";

	public static List<String> varsQForClass() {
		return GpuDevice.varsQGpuDevice(new ArrayList<String>());
	}
	public static List<String> varsQGpuDevice(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return GpuDevice.varsFqGpuDevice(new ArrayList<String>());
	}
	public static List<String> varsFqGpuDevice(List<String> vars) {
		vars.add(VAR_gpuDeviceId);
		vars.add(VAR_clusterName);
		vars.add(VAR_nodeName);
		vars.add(VAR_gpuDeviceNumber);
		vars.add(VAR_gpuDeviceUtilization);
		vars.add(VAR_description);
		vars.add(VAR_location);
		vars.add(VAR_entityId);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return GpuDevice.varsRangeGpuDevice(new ArrayList<String>());
	}
	public static List<String> varsRangeGpuDevice(List<String> vars) {
		vars.add(VAR_gpuDeviceNumber);
		vars.add(VAR_gpuDeviceUtilization);
		vars.add(VAR_location);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_gpuDeviceId = "GPU device ID";
	public static final String DISPLAY_NAME_clusterName = "cluster name";
	public static final String DISPLAY_NAME_nodeName = "node name";
	public static final String DISPLAY_NAME_gpuDeviceNumber = "GPU device number";
	public static final String DISPLAY_NAME_gpuDeviceUtilization = "GPU device utilization";
	public static final String DISPLAY_NAME_description = "description";
	public static final String DISPLAY_NAME_locationColors = "area served colors";
	public static final String DISPLAY_NAME_locationTitles = "area served titles";
	public static final String DISPLAY_NAME_locationLinks = "area served links";
	public static final String DISPLAY_NAME_location = "location";
	public static final String DISPLAY_NAME_entityId = "entity ID";
	public static final String DISPLAY_NAME_entityShortId = "short entity ID";

	@Override
	public String idForClass() {
		return entityId;
	}

	@Override
	public String titleForClass() {
		return title;
	}

	@Override
	public String nameForClass() {
		return gpuDeviceId;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return GpuDevice.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/gpu-device/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return GpuDevice.displayNameGpuDevice(var);
	}
	public static String displayNameGpuDevice(String var) {
		switch(var) {
		case VAR_gpuDeviceId:
			return DISPLAY_NAME_gpuDeviceId;
		case VAR_clusterName:
			return DISPLAY_NAME_clusterName;
		case VAR_nodeName:
			return DISPLAY_NAME_nodeName;
		case VAR_gpuDeviceNumber:
			return DISPLAY_NAME_gpuDeviceNumber;
		case VAR_gpuDeviceUtilization:
			return DISPLAY_NAME_gpuDeviceUtilization;
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
		case VAR_entityId:
			return DISPLAY_NAME_entityId;
		case VAR_entityShortId:
			return DISPLAY_NAME_entityShortId;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionGpuDevice(String var) {
		switch(var) {
		case VAR_gpuDeviceId:
			return "A unique ID for a gpu device per cluster, and node. ";
		case VAR_clusterName:
			return "The cluster name of this GPU device";
		case VAR_nodeName:
			return "The node name of this GPU device";
		case VAR_gpuDeviceNumber:
			return "The number of this GPU device";
		case VAR_gpuDeviceUtilization:
			return "Current utilization of the GPU device. ";
		case VAR_description:
			return "A description of this GPU device";
		case VAR_locationColors:
			return "The colors of each location Paths. ";
		case VAR_locationTitles:
			return "The titles of each location Paths. ";
		case VAR_locationLinks:
			return "The links of each location Paths. ";
		case VAR_location:
			return "Geojson reference to the item. It can be Point, LineString, Polygon, MultiPoint, MultiLineString or MultiPolygon";
		case VAR_entityId:
			return "A unique ID for this Smart Data Model";
		case VAR_entityShortId:
			return "A short ID for this Smart Data Model";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameGpuDevice(String var) {
		switch(var) {
		case VAR_gpuDeviceId:
			return "String";
		case VAR_clusterName:
			return "String";
		case VAR_nodeName:
			return "String";
		case VAR_gpuDeviceNumber:
			return "Integer";
		case VAR_gpuDeviceUtilization:
			return "Integer";
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
		case VAR_entityId:
			return "String";
		case VAR_entityShortId:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnGpuDevice(String var) {
		switch(var) {
		case VAR_clusterName:
			return 1;
		case VAR_nodeName:
			return 2;
		case VAR_gpuDeviceNumber:
			return 3;
		case VAR_description:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowGpuDevice(String var) {
		switch(var) {
		case VAR_clusterName:
			return 3;
		case VAR_nodeName:
			return 3;
		case VAR_gpuDeviceNumber:
			return 3;
		case VAR_gpuDeviceUtilization:
			return 4;
		case VAR_description:
			return 3;
		case VAR_location:
			return 10;
		case VAR_entityId:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellGpuDevice(String var) {
		switch(var) {
		case VAR_clusterName:
			return 1;
		case VAR_nodeName:
			return 2;
		case VAR_gpuDeviceNumber:
			return 3;
		case VAR_gpuDeviceUtilization:
			return 1;
		case VAR_description:
			return 2;
		case VAR_location:
			return 1;
		case VAR_entityId:
			return 4;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinGpuDevice(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxGpuDevice(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxGpuDevice(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minGpuDevice(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
