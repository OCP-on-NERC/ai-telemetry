package org.mghpcc.aitelemetry.model.gpuslice;

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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class GpuSliceGen into the class GpuSlice. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the GpuSlice API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the GpuSlice class and it's generated class GpuSliceGen&lt;BaseModel&gt;: </h3>extends GpuSliceGen
 * <p>
 * This Java class extends a generated Java class GpuSliceGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice">Find the class GpuSlice in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends GpuSliceGen<BaseModel>
 * <p>This <code>class GpuSlice extends GpuSliceGen&lt;BaseModel&gt;</code>, which means it extends a newly generated GpuSliceGen. 
 * The generated <code>class GpuSliceGen extends BaseModel</code> which means that GpuSlice extends GpuSliceGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: GPU slices"</b>, which groups all of the OpenAPIs for GpuSlice objects under the tag "GPU slices". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/gpu-slice</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/gpu-slice"</b>, which defines the base API URI for GpuSlice objects as "/en-us/api/gpu-slice" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the GpuSlice class will inherit the helpful inherited class comments from the super class GpuSliceGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 7</h2>
 * <p>This class contains a comment <b>"Order: 7"</b>, which means this class will be sorted by the given number 7 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.gpuslice.GpuSlicePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.gpuslice.GpuSlicePage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the GpuSlice Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a GPU slice</h2>
 * <p>This class contains a comment <b>"AName.enUS: a GPU slice"</b>, which identifies the language context to describe a GpuSlice as "a GPU slice". 
 * </p>
 * <p>
 * Delete the class GpuSlice in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.gpuslice in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class GpuSliceGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(GpuSlice.class);

	public static final String Description_enUS = "A GPU slice inside a GPU";
	public static final String AName_enUS = "a GPU slice";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this GPU slice";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the GPU slice";
	public static final String SingularName_enUS = "GPU slice";
	public static final String PluralName_enUS = "GPU slices";
	public static final String NameActual_enUS = "current GPU slice";
	public static final String AllName_enUS = "all GPU slices";
	public static final String SearchAllNameBy_enUS = "search GPU slices by ";
	public static final String Title_enUS = "GPU slices";
	public static final String ThePluralName_enUS = "the GPU slices";
	public static final String NoNameFound_enUS = "no GPU slice found";
	public static final String ApiUri_enUS = "/en-us/api/gpu-slice";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/gpu-slice";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/gpu-slice/{pageId}";
	public static final String OfName_enUS = "of GPU slice";
	public static final String ANameAdjective_enUS = "a GPU slice";
	public static final String NameAdjectiveSingular_enUS = "GPU slice";
	public static final String NameAdjectivePlural_enUS = "GPU slices";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/gpu-slice";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/gpu-slice";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/gpu-slice";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/gpu-slice/{entityId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/gpu-slice/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/gpu-slice/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/gpu-slice";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/gpu-slice";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/gpu-slice";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/gpu-slice";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/gpu-slice";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/gpu-slice";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/gpu-slice/{entityId}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/gpu-slice/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/gpu-slice/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/gpu-slice-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/gpu-slice-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/gpu-slice-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/gpu-slice";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/gpu-slice";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/gpu-slice";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/gpu-slice/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/gpu-slice/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/gpu-slice/%s";

	public static final String Icon = "<i class=\"fa-regular fa-cake-slice\"></i>";

	///////////////
	// sliceName //
	///////////////


	/**	 The entity sliceName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sliceName;

	/**	<br> The entity sliceName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&fq=entiteVar_enUS_indexed_string:sliceName">Find the entity sliceName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sliceName(Wrap<String> w);

	public String getSliceName() {
		return sliceName;
	}
	@JsonIgnore
	public void setSliceName(String o) {
		this.sliceName = GpuSlice.staticSetSliceName(siteRequest_, o);
	}
	public static String staticSetSliceName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuSlice sliceNameInit() {
		Wrap<String> sliceNameWrap = new Wrap<String>().var("sliceName");
		if(sliceName == null) {
			_sliceName(sliceNameWrap);
			Optional.ofNullable(sliceNameWrap.getO()).ifPresent(o -> {
				setSliceName(o);
			});
		}
		return (GpuSlice)this;
	}

	public static String staticSearchSliceName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSliceName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSliceName(SiteRequest siteRequest_, String o) {
		return GpuSlice.staticSearchSliceName(siteRequest_, GpuSlice.staticSetSliceName(siteRequest_, o)).toString();
	}

	public String sqlSliceName() {
		return sliceName;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	@JsonIgnore
	public void setDescription(String o) {
		this.description = GpuSlice.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuSlice descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (GpuSlice)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return GpuSlice.staticSearchDescription(siteRequest_, GpuSlice.staticSetDescription(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&fq=entiteVar_enUS_indexed_string:locationColors">Find the entity locationColors in Solr</a>
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
		String l = GpuSlice.staticSetLocationColors(siteRequest_, o);
		if(l != null)
			addLocationColors(l);
	}
	public static String staticSetLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}
	public GpuSlice addLocationColors(String...objects) {
		for(String o : objects) {
			addLocationColors(o);
		}
		return (GpuSlice)this;
	}
	public GpuSlice addLocationColors(String o) {
		if(o != null)
			this.locationColors.add(o);
		return (GpuSlice)this;
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
	protected GpuSlice locationColorsInit() {
		_locationColors(locationColors);
		return (GpuSlice)this;
	}

	public static String staticSearchLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationColors(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationColors(SiteRequest siteRequest_, String o) {
		return GpuSlice.staticSearchLocationColors(siteRequest_, GpuSlice.staticSetLocationColors(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&fq=entiteVar_enUS_indexed_string:locationTitles">Find the entity locationTitles in Solr</a>
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
		String l = GpuSlice.staticSetLocationTitles(siteRequest_, o);
		if(l != null)
			addLocationTitles(l);
	}
	public static String staticSetLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}
	public GpuSlice addLocationTitles(String...objects) {
		for(String o : objects) {
			addLocationTitles(o);
		}
		return (GpuSlice)this;
	}
	public GpuSlice addLocationTitles(String o) {
		if(o != null)
			this.locationTitles.add(o);
		return (GpuSlice)this;
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
	protected GpuSlice locationTitlesInit() {
		_locationTitles(locationTitles);
		return (GpuSlice)this;
	}

	public static String staticSearchLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationTitles(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationTitles(SiteRequest siteRequest_, String o) {
		return GpuSlice.staticSearchLocationTitles(siteRequest_, GpuSlice.staticSetLocationTitles(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&fq=entiteVar_enUS_indexed_string:locationLinks">Find the entity locationLinks in Solr</a>
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
		String l = GpuSlice.staticSetLocationLinks(siteRequest_, o);
		if(l != null)
			addLocationLinks(l);
	}
	public static String staticSetLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}
	public GpuSlice addLocationLinks(String...objects) {
		for(String o : objects) {
			addLocationLinks(o);
		}
		return (GpuSlice)this;
	}
	public GpuSlice addLocationLinks(String o) {
		if(o != null)
			this.locationLinks.add(o);
		return (GpuSlice)this;
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
	protected GpuSlice locationLinksInit() {
		_locationLinks(locationLinks);
		return (GpuSlice)this;
	}

	public static String staticSearchLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationLinks(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationLinks(SiteRequest siteRequest_, String o) {
		return GpuSlice.staticSearchLocationLinks(siteRequest_, GpuSlice.staticSetLocationLinks(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&fq=entiteVar_enUS_indexed_string:location">Find the entity location in Solr</a>
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
		this.location = GpuSlice.staticSetLocation(siteRequest_, o);
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
		this.location = GpuSlice.staticSetLocation(siteRequest_, o);
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
	protected GpuSlice locationInit() {
		Wrap<Point> locationWrap = new Wrap<Point>().var("location");
		if(location == null) {
			_location(locationWrap);
			Optional.ofNullable(locationWrap.getO()).ifPresent(o -> {
				setLocation(o);
			});
		}
		return (GpuSlice)this;
	}

	public static Point staticSearchLocation(SiteRequest siteRequest_, Point o) {
		return o;
	}

	public static String staticSearchStrLocation(SiteRequest siteRequest_, Point o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocation(SiteRequest siteRequest_, String o) {
		return GpuSlice.staticSearchLocation(siteRequest_, GpuSlice.staticSetLocation(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&fq=entiteVar_enUS_indexed_string:entityId">Find the entity entityId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityId(Wrap<String> w);

	public String getEntityId() {
		return entityId;
	}
	@JsonIgnore
	public void setEntityId(String o) {
		this.entityId = GpuSlice.staticSetEntityId(siteRequest_, o);
	}
	public static String staticSetEntityId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuSlice entityIdInit() {
		Wrap<String> entityIdWrap = new Wrap<String>().var("entityId");
		if(entityId == null) {
			_entityId(entityIdWrap);
			Optional.ofNullable(entityIdWrap.getO()).ifPresent(o -> {
				setEntityId(o);
			});
		}
		return (GpuSlice)this;
	}

	public static String staticSearchEntityId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityId(SiteRequest siteRequest_, String o) {
		return GpuSlice.staticSearchEntityId(siteRequest_, GpuSlice.staticSetEntityId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSlice&fq=entiteVar_enUS_indexed_string:entityShortId">Find the entity entityShortId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityShortId(Wrap<String> w);

	public String getEntityShortId() {
		return entityShortId;
	}
	@JsonIgnore
	public void setEntityShortId(String o) {
		this.entityShortId = GpuSlice.staticSetEntityShortId(siteRequest_, o);
	}
	public static String staticSetEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuSlice entityShortIdInit() {
		Wrap<String> entityShortIdWrap = new Wrap<String>().var("entityShortId");
		if(entityShortId == null) {
			_entityShortId(entityShortIdWrap);
			Optional.ofNullable(entityShortIdWrap.getO()).ifPresent(o -> {
				setEntityShortId(o);
			});
		}
		return (GpuSlice)this;
	}

	public static String staticSearchEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityShortId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityShortId(SiteRequest siteRequest_, String o) {
		return GpuSlice.staticSearchEntityShortId(siteRequest_, GpuSlice.staticSetEntityShortId(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<GpuSliceGen<DEV>> promiseDeepGpuSlice(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepGpuSlice();
	}

	public Future<GpuSliceGen<DEV>> promiseDeepGpuSlice() {
		Promise<GpuSliceGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseGpuSlice(promise2);
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

	public Future<Void> promiseGpuSlice(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				sliceNameInit();
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

	@Override public Future<? extends GpuSliceGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepGpuSlice(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestGpuSlice(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestGpuSlice(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainGpuSlice(v);
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
	public Object obtainGpuSlice(String var) {
		GpuSlice oGpuSlice = (GpuSlice)this;
		switch(var) {
			case "sliceName":
				return oGpuSlice.sliceName;
			case "description":
				return oGpuSlice.description;
			case "locationColors":
				return oGpuSlice.locationColors;
			case "locationTitles":
				return oGpuSlice.locationTitles;
			case "locationLinks":
				return oGpuSlice.locationLinks;
			case "location":
				return oGpuSlice.location;
			case "entityId":
				return oGpuSlice.entityId;
			case "entityShortId":
				return oGpuSlice.entityShortId;
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
				o = relateGpuSlice(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateGpuSlice(String var, Object val) {
		GpuSlice oGpuSlice = (GpuSlice)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetGpuSlice(entityVar,  siteRequest_, o);
	}
	public static Object staticSetGpuSlice(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "sliceName":
			return GpuSlice.staticSetSliceName(siteRequest_, o);
		case "description":
			return GpuSlice.staticSetDescription(siteRequest_, o);
		case "locationColors":
			return GpuSlice.staticSetLocationColors(siteRequest_, o);
		case "locationTitles":
			return GpuSlice.staticSetLocationTitles(siteRequest_, o);
		case "locationLinks":
			return GpuSlice.staticSetLocationLinks(siteRequest_, o);
		case "location":
			return GpuSlice.staticSetLocation(siteRequest_, o);
		case "entityId":
			return GpuSlice.staticSetEntityId(siteRequest_, o);
		case "entityShortId":
			return GpuSlice.staticSetEntityShortId(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchGpuSlice(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchGpuSlice(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "sliceName":
			return GpuSlice.staticSearchSliceName(siteRequest_, (String)o);
		case "description":
			return GpuSlice.staticSearchDescription(siteRequest_, (String)o);
		case "locationColors":
			return GpuSlice.staticSearchLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return GpuSlice.staticSearchLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return GpuSlice.staticSearchLocationLinks(siteRequest_, (String)o);
		case "location":
			return GpuSlice.staticSearchLocation(siteRequest_, (Point)o);
		case "entityId":
			return GpuSlice.staticSearchEntityId(siteRequest_, (String)o);
		case "entityShortId":
			return GpuSlice.staticSearchEntityShortId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrGpuSlice(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrGpuSlice(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "sliceName":
			return GpuSlice.staticSearchStrSliceName(siteRequest_, (String)o);
		case "description":
			return GpuSlice.staticSearchStrDescription(siteRequest_, (String)o);
		case "locationColors":
			return GpuSlice.staticSearchStrLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return GpuSlice.staticSearchStrLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return GpuSlice.staticSearchStrLocationLinks(siteRequest_, (String)o);
		case "location":
			return GpuSlice.staticSearchStrLocation(siteRequest_, (Point)o);
		case "entityId":
			return GpuSlice.staticSearchStrEntityId(siteRequest_, (String)o);
		case "entityShortId":
			return GpuSlice.staticSearchStrEntityShortId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqGpuSlice(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqGpuSlice(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "sliceName":
			return GpuSlice.staticSearchFqSliceName(siteRequest_, o);
		case "description":
			return GpuSlice.staticSearchFqDescription(siteRequest_, o);
		case "locationColors":
			return GpuSlice.staticSearchFqLocationColors(siteRequest_, o);
		case "locationTitles":
			return GpuSlice.staticSearchFqLocationTitles(siteRequest_, o);
		case "locationLinks":
			return GpuSlice.staticSearchFqLocationLinks(siteRequest_, o);
		case "location":
			return GpuSlice.staticSearchFqLocation(siteRequest_, o);
		case "entityId":
			return GpuSlice.staticSearchFqEntityId(siteRequest_, o);
		case "entityShortId":
			return GpuSlice.staticSearchFqEntityShortId(siteRequest_, o);
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
					o = persistGpuSlice(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistGpuSlice(String var, Object val) {
		String varLower = var.toLowerCase();
			if("slicename".equals(varLower)) {
				if(val instanceof String) {
					setSliceName((String)val);
				}
				saves.add("sliceName");
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
		populateGpuSlice(doc);
	}
	public void populateGpuSlice(SolrResponse.Doc doc) {
		GpuSlice oGpuSlice = (GpuSlice)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("sliceName")) {
				String sliceName = (String)doc.get("sliceName_docvalues_string");
				if(sliceName != null)
					oGpuSlice.setSliceName(sliceName);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oGpuSlice.setDescription(description);
			}

			if(saves.contains("locationColors")) {
				List<String> locationColors = (List<String>)doc.get("locationColors_indexedstored_strings");
				if(locationColors != null)
					oGpuSlice.locationColors.addAll(locationColors);
			}

			if(saves.contains("locationTitles")) {
				List<String> locationTitles = (List<String>)doc.get("locationTitles_indexedstored_strings");
				if(locationTitles != null)
					oGpuSlice.locationTitles.addAll(locationTitles);
			}

			if(saves.contains("locationLinks")) {
				List<String> locationLinks = (List<String>)doc.get("locationLinks_indexedstored_strings");
				if(locationLinks != null)
					oGpuSlice.locationLinks.addAll(locationLinks);
			}

			if(saves.contains("location")) {
				Point location = (Point)doc.get("location_docvalues_location");
				if(location != null)
					oGpuSlice.setLocation(location);
			}

			if(saves.contains("entityId")) {
				String entityId = (String)doc.get("entityId_docvalues_string");
				if(entityId != null)
					oGpuSlice.setEntityId(entityId);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexGpuSlice(JsonObject doc) {
		if(sliceName != null) {
			doc.put("sliceName_docvalues_string", sliceName);
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

	public static String varStoredGpuSlice(String entityVar) {
		switch(entityVar) {
			case "sliceName":
				return "sliceName_docvalues_string";
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

	public static String varIndexedGpuSlice(String entityVar) {
		switch(entityVar) {
			case "sliceName":
				return "sliceName_docvalues_string";
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

	public static String searchVarGpuSlice(String searchVar) {
		switch(searchVar) {
			case "sliceName_docvalues_string":
				return "sliceName";
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

	public static String varSearchGpuSlice(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedGpuSlice(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeGpuSlice(doc);
	}
	public void storeGpuSlice(SolrResponse.Doc doc) {
		GpuSlice oGpuSlice = (GpuSlice)this;
		SiteRequest siteRequest = oGpuSlice.getSiteRequest_();

		oGpuSlice.setSliceName(Optional.ofNullable(doc.get("sliceName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oGpuSlice.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("locationColors_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oGpuSlice.addLocationColors(GpuSlice.staticSetLocationColors(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationTitles_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oGpuSlice.addLocationTitles(GpuSlice.staticSetLocationTitles(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationLinks_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oGpuSlice.addLocationLinks(GpuSlice.staticSetLocationLinks(siteRequest, v.toString()));
		});
		oGpuSlice.setLocation(Optional.ofNullable(doc.get("location_docvalues_location")).map(v -> v.toString()).orElse(null));
		oGpuSlice.setEntityId(Optional.ofNullable(doc.get("entityId_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestGpuSlice() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof GpuSlice) {
			GpuSlice original = (GpuSlice)o;
			if(!Objects.equals(sliceName, original.getSliceName()))
				apiRequest.addVars("sliceName");
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
		sb.append(Optional.ofNullable(sliceName).map(v -> "sliceName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(locationColors).map(v -> "locationColors: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationTitles).map(v -> "locationTitles: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationLinks).map(v -> "locationLinks: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(location).map(v -> "location: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(entityId).map(v -> "entityId: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "GpuSlice";
	public static final String CLASS_API_ADDRESS_GpuSlice = "ai-telemetry-enUS-GpuSlice";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_GpuSlice;
	}
	public static final String VAR_sliceName = "sliceName";
	public static final String VAR_description = "description";
	public static final String VAR_locationColors = "locationColors";
	public static final String VAR_locationTitles = "locationTitles";
	public static final String VAR_locationLinks = "locationLinks";
	public static final String VAR_location = "location";
	public static final String VAR_entityId = "entityId";
	public static final String VAR_entityShortId = "entityShortId";

	public static List<String> varsQForClass() {
		return GpuSlice.varsQGpuSlice(new ArrayList<String>());
	}
	public static List<String> varsQGpuSlice(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return GpuSlice.varsFqGpuSlice(new ArrayList<String>());
	}
	public static List<String> varsFqGpuSlice(List<String> vars) {
		vars.add(VAR_sliceName);
		vars.add(VAR_description);
		vars.add(VAR_location);
		vars.add(VAR_entityId);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return GpuSlice.varsRangeGpuSlice(new ArrayList<String>());
	}
	public static List<String> varsRangeGpuSlice(List<String> vars) {
		vars.add(VAR_location);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_sliceName = "name";
	public static final String DISPLAY_NAME_description = "description";
	public static final String DISPLAY_NAME_locationColors = "location colors";
	public static final String DISPLAY_NAME_locationTitles = "location titles";
	public static final String DISPLAY_NAME_locationLinks = "location links";
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
		return sliceName;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return GpuSlice.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/gpu-slice/%s";
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
		return GpuSlice.displayNameGpuSlice(var);
	}
	public static String displayNameGpuSlice(String var) {
		switch(var) {
		case VAR_sliceName:
			return DISPLAY_NAME_sliceName;
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

	public static String descriptionGpuSlice(String var) {
		switch(var) {
		case VAR_sliceName:
			return "The name of this GPU slice";
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

	public static String classSimpleNameGpuSlice(String var) {
		switch(var) {
		case VAR_sliceName:
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
		case VAR_entityId:
			return "String";
		case VAR_entityShortId:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnGpuSlice(String var) {
		switch(var) {
		case VAR_sliceName:
			return 1;
		case VAR_description:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowGpuSlice(String var) {
		switch(var) {
		case VAR_sliceName:
			return 3;
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

	public static Integer htmCellGpuSlice(String var) {
		switch(var) {
		case VAR_sliceName:
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

	public static Integer lengthMinGpuSlice(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxGpuSlice(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxGpuSlice(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minGpuSlice(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
