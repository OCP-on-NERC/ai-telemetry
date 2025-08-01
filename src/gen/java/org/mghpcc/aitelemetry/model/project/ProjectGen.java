package org.mghpcc.aitelemetry.model.project;

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
import org.mghpcc.aitelemetry.model.cluster.Cluster;
import java.lang.Boolean;
import java.lang.Integer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ProjectGen into the class Project. 
 * </li>
 * <h3>About the Project class and it's generated class ProjectGen&lt;BaseModel&gt;: </h3>extends ProjectGen
 * <p>
 * This Java class extends a generated Java class ProjectGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project">Find the class Project in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ProjectGen<BaseModel>
 * <p>This <code>class Project extends ProjectGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ProjectGen. 
 * The generated <code>class ProjectGen extends BaseModel</code> which means that Project extends ProjectGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: projects"</b>, which groups all of the OpenAPIs for Project objects under the tag "projects". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/project</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/project"</b>, which defines the base API URI for Project objects as "/en-us/api/project" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the Project class will inherit the helpful inherited class comments from the super class ProjectGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the Project API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
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
 * This creates a new Java class org.mghpcc.aitelemetry.model.project.ProjectPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.project.ProjectPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the Project Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an project</h2>
 * <p>This class contains a comment <b>"AName.enUS: an project"</b>, which identifies the language context to describe a Project as "an project". 
 * </p>
 * <p>
 * Delete the class Project in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.project in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.project&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ProjectGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(Project.class);

	public static final String Description_enUS = "A research project using AI and GPUs";
	public static final String AName_enUS = "an project";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this project";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the project";
	public static final String SingularName_enUS = "project";
	public static final String PluralName_enUS = "projects";
	public static final String NameActual_enUS = "current project";
	public static final String AllName_enUS = "all projects";
	public static final String SearchAllNameBy_enUS = "search projects by ";
	public static final String SearchAllName_enUS = "search projects";
	public static final String Title_enUS = "projects";
	public static final String ThePluralName_enUS = "the projects";
	public static final String NoNameFound_enUS = "no project found";
	public static final String ApiUri_enUS = "/en-us/api/project";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/project";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/project/{projectResource}";
	public static final String OfName_enUS = "of project";
	public static final String ANameAdjective_enUS = "a project";
	public static final String NameAdjectiveSingular_enUS = "project";
	public static final String NameAdjectivePlural_enUS = "projects";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/project";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/project";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/project";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/project/{projectResource}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/project/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/project/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/project";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/project";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/project";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/project";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/project";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/project";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/project/{projectResource}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/project/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/project/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/project-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/project-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/project-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/project";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/project";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/project";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/project/{projectResource}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/project/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/project/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/project/{projectResource}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/project/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/project/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/project";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/project";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/project";

	public static final String Icon = "<i class=\"fa-regular fa-school\"></i>";
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:hubId">Find the entity hubId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _hubId(Wrap<String> w);

	public String getHubId() {
		return hubId;
	}
	public void setHubId(String o) {
		this.hubId = Project.staticSetHubId(siteRequest_, o);
	}
	public static String staticSetHubId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project hubIdInit() {
		Wrap<String> hubIdWrap = new Wrap<String>().var("hubId");
		if(hubId == null) {
			_hubId(hubIdWrap);
			Optional.ofNullable(hubIdWrap.getO()).ifPresent(o -> {
				setHubId(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchHubId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrHubId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqHubId(SiteRequest siteRequest_, String o) {
		return Project.staticSearchHubId(siteRequest_, Project.staticSetHubId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:hubResource">Find the entity hubResource in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _hubResource(Wrap<String> w);

	public String getHubResource() {
		return hubResource;
	}
	public void setHubResource(String o) {
		this.hubResource = Project.staticSetHubResource(siteRequest_, o);
	}
	public static String staticSetHubResource(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project hubResourceInit() {
		Wrap<String> hubResourceWrap = new Wrap<String>().var("hubResource");
		if(hubResource == null) {
			_hubResource(hubResourceWrap);
			Optional.ofNullable(hubResourceWrap.getO()).ifPresent(o -> {
				setHubResource(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchHubResource(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrHubResource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqHubResource(SiteRequest siteRequest_, String o) {
		return Project.staticSearchHubResource(siteRequest_, Project.staticSetHubResource(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:clusterName">Find the entity clusterName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterName(Wrap<String> w);

	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String o) {
		this.clusterName = Project.staticSetClusterName(siteRequest_, o);
	}
	public static String staticSetClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project clusterNameInit() {
		Wrap<String> clusterNameWrap = new Wrap<String>().var("clusterName");
		if(clusterName == null) {
			_clusterName(clusterNameWrap);
			Optional.ofNullable(clusterNameWrap.getO()).ifPresent(o -> {
				setClusterName(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchClusterName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterName(SiteRequest siteRequest_, String o) {
		return Project.staticSearchClusterName(siteRequest_, Project.staticSetClusterName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:clusterResource">Find the entity clusterResource in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _clusterResource(Wrap<String> w);

	public String getClusterResource() {
		return clusterResource;
	}
	public void setClusterResource(String o) {
		this.clusterResource = Project.staticSetClusterResource(siteRequest_, o);
	}
	public static String staticSetClusterResource(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project clusterResourceInit() {
		Wrap<String> clusterResourceWrap = new Wrap<String>().var("clusterResource");
		if(clusterResource == null) {
			_clusterResource(clusterResourceWrap);
			Optional.ofNullable(clusterResourceWrap.getO()).ifPresent(o -> {
				setClusterResource(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchClusterResource(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClusterResource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClusterResource(SiteRequest siteRequest_, String o) {
		return Project.staticSearchClusterResource(siteRequest_, Project.staticSetClusterResource(siteRequest_, o)).toString();
	}

	public String sqlClusterResource() {
		return clusterResource;
	}

	/////////////////
	// projectName //
	/////////////////


	/**	 The entity projectName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String projectName;

	/**	<br> The entity projectName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:projectName">Find the entity projectName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _projectName(Wrap<String> w);

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String o) {
		this.projectName = Project.staticSetProjectName(siteRequest_, o);
	}
	public static String staticSetProjectName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project projectNameInit() {
		Wrap<String> projectNameWrap = new Wrap<String>().var("projectName");
		if(projectName == null) {
			_projectName(projectNameWrap);
			Optional.ofNullable(projectNameWrap.getO()).ifPresent(o -> {
				setProjectName(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchProjectName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProjectName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProjectName(SiteRequest siteRequest_, String o) {
		return Project.staticSearchProjectName(siteRequest_, Project.staticSetProjectName(siteRequest_, o)).toString();
	}

	public String sqlProjectName() {
		return projectName;
	}

	/////////////////////
	// projectResource //
	/////////////////////


	/**	 The entity projectResource
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String projectResource;

	/**	<br> The entity projectResource
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:projectResource">Find the entity projectResource in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _projectResource(Wrap<String> w);

	public String getProjectResource() {
		return projectResource;
	}
	public void setProjectResource(String o) {
		this.projectResource = Project.staticSetProjectResource(siteRequest_, o);
	}
	public static String staticSetProjectResource(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project projectResourceInit() {
		Wrap<String> projectResourceWrap = new Wrap<String>().var("projectResource");
		if(projectResource == null) {
			_projectResource(projectResourceWrap);
			Optional.ofNullable(projectResourceWrap.getO()).ifPresent(o -> {
				setProjectResource(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchProjectResource(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProjectResource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProjectResource(SiteRequest siteRequest_, String o) {
		return Project.staticSearchProjectResource(siteRequest_, Project.staticSetProjectResource(siteRequest_, o)).toString();
	}

	public String sqlProjectResource() {
		return projectResource;
	}

	////////////////////////
	// projectDisplayName //
	////////////////////////


	/**	 The entity projectDisplayName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String projectDisplayName;

	/**	<br> The entity projectDisplayName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:projectDisplayName">Find the entity projectDisplayName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _projectDisplayName(Wrap<String> w);

	public String getProjectDisplayName() {
		return projectDisplayName;
	}
	public void setProjectDisplayName(String o) {
		this.projectDisplayName = Project.staticSetProjectDisplayName(siteRequest_, o);
	}
	public static String staticSetProjectDisplayName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project projectDisplayNameInit() {
		Wrap<String> projectDisplayNameWrap = new Wrap<String>().var("projectDisplayName");
		if(projectDisplayName == null) {
			_projectDisplayName(projectDisplayNameWrap);
			Optional.ofNullable(projectDisplayNameWrap.getO()).ifPresent(o -> {
				setProjectDisplayName(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchProjectDisplayName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProjectDisplayName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProjectDisplayName(SiteRequest siteRequest_, String o) {
		return Project.staticSearchProjectDisplayName(siteRequest_, Project.staticSetProjectDisplayName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = Project.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return Project.staticSearchDescription(siteRequest_, Project.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:promKeycloakProxySsl">Find the entity promKeycloakProxySsl in Solr</a>
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
		this.promKeycloakProxySsl = Project.staticSetPromKeycloakProxySsl(siteRequest_, o);
	}
	public static Boolean staticSetPromKeycloakProxySsl(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected Project promKeycloakProxySslInit() {
		Wrap<Boolean> promKeycloakProxySslWrap = new Wrap<Boolean>().var("promKeycloakProxySsl");
		if(promKeycloakProxySsl == null) {
			_promKeycloakProxySsl(promKeycloakProxySslWrap);
			Optional.ofNullable(promKeycloakProxySslWrap.getO()).ifPresent(o -> {
				setPromKeycloakProxySsl(o);
			});
		}
		return (Project)this;
	}

	public static Boolean staticSearchPromKeycloakProxySsl(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrPromKeycloakProxySsl(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPromKeycloakProxySsl(SiteRequest siteRequest_, String o) {
		return Project.staticSearchPromKeycloakProxySsl(siteRequest_, Project.staticSetPromKeycloakProxySsl(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:promKeycloakProxyPort">Find the entity promKeycloakProxyPort in Solr</a>
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
		this.promKeycloakProxyPort = Project.staticSetPromKeycloakProxyPort(siteRequest_, o);
	}
	public static Integer staticSetPromKeycloakProxyPort(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected Project promKeycloakProxyPortInit() {
		Wrap<Integer> promKeycloakProxyPortWrap = new Wrap<Integer>().var("promKeycloakProxyPort");
		if(promKeycloakProxyPort == null) {
			_promKeycloakProxyPort(promKeycloakProxyPortWrap);
			Optional.ofNullable(promKeycloakProxyPortWrap.getO()).ifPresent(o -> {
				setPromKeycloakProxyPort(o);
			});
		}
		return (Project)this;
	}

	public static Integer staticSearchPromKeycloakProxyPort(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrPromKeycloakProxyPort(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPromKeycloakProxyPort(SiteRequest siteRequest_, String o) {
		return Project.staticSearchPromKeycloakProxyPort(siteRequest_, Project.staticSetPromKeycloakProxyPort(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.Project&fq=entiteVar_enUS_indexed_string:promKeycloakProxyHostName">Find the entity promKeycloakProxyHostName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _promKeycloakProxyHostName(Wrap<String> w);

	public String getPromKeycloakProxyHostName() {
		return promKeycloakProxyHostName;
	}
	public void setPromKeycloakProxyHostName(String o) {
		this.promKeycloakProxyHostName = Project.staticSetPromKeycloakProxyHostName(siteRequest_, o);
	}
	public static String staticSetPromKeycloakProxyHostName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected Project promKeycloakProxyHostNameInit() {
		Wrap<String> promKeycloakProxyHostNameWrap = new Wrap<String>().var("promKeycloakProxyHostName");
		if(promKeycloakProxyHostName == null) {
			_promKeycloakProxyHostName(promKeycloakProxyHostNameWrap);
			Optional.ofNullable(promKeycloakProxyHostNameWrap.getO()).ifPresent(o -> {
				setPromKeycloakProxyHostName(o);
			});
		}
		return (Project)this;
	}

	public static String staticSearchPromKeycloakProxyHostName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPromKeycloakProxyHostName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPromKeycloakProxyHostName(SiteRequest siteRequest_, String o) {
		return Project.staticSearchPromKeycloakProxyHostName(siteRequest_, Project.staticSetPromKeycloakProxyHostName(siteRequest_, o)).toString();
	}

	public String sqlPromKeycloakProxyHostName() {
		return promKeycloakProxyHostName;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ProjectGen<DEV>> promiseDeepProject(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepProject();
	}

	public Future<ProjectGen<DEV>> promiseDeepProject() {
		Promise<ProjectGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseProject(promise2);
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

	public Future<Void> promiseProject(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				hubIdInit();
				hubResourceInit();
				clusterNameInit();
				clusterResourceInit();
				projectNameInit();
				projectResourceInit();
				projectDisplayNameInit();
				descriptionInit();
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

	@Override public Future<? extends ProjectGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepProject(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestProject(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestProject(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainProject(v);
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
	public Object obtainProject(String var) {
		Project oProject = (Project)this;
		switch(var) {
			case "hubId":
				return oProject.hubId;
			case "hubResource":
				return oProject.hubResource;
			case "clusterName":
				return oProject.clusterName;
			case "clusterResource":
				return oProject.clusterResource;
			case "projectName":
				return oProject.projectName;
			case "projectResource":
				return oProject.projectResource;
			case "projectDisplayName":
				return oProject.projectDisplayName;
			case "description":
				return oProject.description;
			case "promKeycloakProxySsl":
				return oProject.promKeycloakProxySsl;
			case "promKeycloakProxyPort":
				return oProject.promKeycloakProxyPort;
			case "promKeycloakProxyHostName":
				return oProject.promKeycloakProxyHostName;
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
				o = relateProject(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateProject(String var, Object val) {
		Project oProject = (Project)this;
		switch(var) {
			case "hubResource":
				if(oProject.getHubResource() == null)
					oProject.setHubResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
				if(!saves.contains("hubResource"))
					saves.add("hubResource");
				return val;
			case "clusterResource":
				if(oProject.getClusterResource() == null)
					oProject.setClusterResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
				if(!saves.contains("clusterResource"))
					saves.add("clusterResource");
				return val;
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, Project o) {
		return staticSetProject(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetProject(String entityVar, SiteRequest siteRequest_, String v, Project o) {
		switch(entityVar) {
		case "hubId":
			return Project.staticSetHubId(siteRequest_, v);
		case "hubResource":
			return Project.staticSetHubResource(siteRequest_, v);
		case "clusterName":
			return Project.staticSetClusterName(siteRequest_, v);
		case "clusterResource":
			return Project.staticSetClusterResource(siteRequest_, v);
		case "projectName":
			return Project.staticSetProjectName(siteRequest_, v);
		case "projectResource":
			return Project.staticSetProjectResource(siteRequest_, v);
		case "projectDisplayName":
			return Project.staticSetProjectDisplayName(siteRequest_, v);
		case "description":
			return Project.staticSetDescription(siteRequest_, v);
		case "promKeycloakProxySsl":
			return Project.staticSetPromKeycloakProxySsl(siteRequest_, v);
		case "promKeycloakProxyPort":
			return Project.staticSetPromKeycloakProxyPort(siteRequest_, v);
		case "promKeycloakProxyHostName":
			return Project.staticSetPromKeycloakProxyHostName(siteRequest_, v);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchProject(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchProject(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "hubId":
			return Project.staticSearchHubId(siteRequest_, (String)o);
		case "hubResource":
			return Project.staticSearchHubResource(siteRequest_, (String)o);
		case "clusterName":
			return Project.staticSearchClusterName(siteRequest_, (String)o);
		case "clusterResource":
			return Project.staticSearchClusterResource(siteRequest_, (String)o);
		case "projectName":
			return Project.staticSearchProjectName(siteRequest_, (String)o);
		case "projectResource":
			return Project.staticSearchProjectResource(siteRequest_, (String)o);
		case "projectDisplayName":
			return Project.staticSearchProjectDisplayName(siteRequest_, (String)o);
		case "description":
			return Project.staticSearchDescription(siteRequest_, (String)o);
		case "promKeycloakProxySsl":
			return Project.staticSearchPromKeycloakProxySsl(siteRequest_, (Boolean)o);
		case "promKeycloakProxyPort":
			return Project.staticSearchPromKeycloakProxyPort(siteRequest_, (Integer)o);
		case "promKeycloakProxyHostName":
			return Project.staticSearchPromKeycloakProxyHostName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrProject(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrProject(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "hubId":
			return Project.staticSearchStrHubId(siteRequest_, (String)o);
		case "hubResource":
			return Project.staticSearchStrHubResource(siteRequest_, (String)o);
		case "clusterName":
			return Project.staticSearchStrClusterName(siteRequest_, (String)o);
		case "clusterResource":
			return Project.staticSearchStrClusterResource(siteRequest_, (String)o);
		case "projectName":
			return Project.staticSearchStrProjectName(siteRequest_, (String)o);
		case "projectResource":
			return Project.staticSearchStrProjectResource(siteRequest_, (String)o);
		case "projectDisplayName":
			return Project.staticSearchStrProjectDisplayName(siteRequest_, (String)o);
		case "description":
			return Project.staticSearchStrDescription(siteRequest_, (String)o);
		case "promKeycloakProxySsl":
			return Project.staticSearchStrPromKeycloakProxySsl(siteRequest_, (Boolean)o);
		case "promKeycloakProxyPort":
			return Project.staticSearchStrPromKeycloakProxyPort(siteRequest_, (Integer)o);
		case "promKeycloakProxyHostName":
			return Project.staticSearchStrPromKeycloakProxyHostName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqProject(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqProject(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "hubId":
			return Project.staticSearchFqHubId(siteRequest_, o);
		case "hubResource":
			return Project.staticSearchFqHubResource(siteRequest_, o);
		case "clusterName":
			return Project.staticSearchFqClusterName(siteRequest_, o);
		case "clusterResource":
			return Project.staticSearchFqClusterResource(siteRequest_, o);
		case "projectName":
			return Project.staticSearchFqProjectName(siteRequest_, o);
		case "projectResource":
			return Project.staticSearchFqProjectResource(siteRequest_, o);
		case "projectDisplayName":
			return Project.staticSearchFqProjectDisplayName(siteRequest_, o);
		case "description":
			return Project.staticSearchFqDescription(siteRequest_, o);
		case "promKeycloakProxySsl":
			return Project.staticSearchFqPromKeycloakProxySsl(siteRequest_, o);
		case "promKeycloakProxyPort":
			return Project.staticSearchFqPromKeycloakProxyPort(siteRequest_, o);
		case "promKeycloakProxyHostName":
			return Project.staticSearchFqPromKeycloakProxyHostName(siteRequest_, o);
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
					o = persistProject(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistProject(String var, Object val) {
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
			} else if("projectname".equals(varLower)) {
				if(val instanceof String) {
					setProjectName((String)val);
				}
				saves.add("projectName");
				return val;
			} else if("projectresource".equals(varLower)) {
				if(val instanceof String) {
					setProjectResource((String)val);
				}
				saves.add("projectResource");
				return val;
			} else if("description".equals(varLower)) {
				if(val instanceof String) {
					setDescription((String)val);
				}
				saves.add("description");
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
		populateProject(doc);
	}
	public void populateProject(SolrResponse.Doc doc) {
		Project oProject = (Project)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("hubId")) {
				String hubId = (String)doc.get("hubId_docvalues_string");
				if(hubId != null)
					oProject.setHubId(hubId);
			}

			String hubResource = (String)doc.get("hubResource_docvalues_string");
			if(hubResource != null)
				oProject.setHubResource(hubResource);

			if(saves.contains("clusterName")) {
				String clusterName = (String)doc.get("clusterName_docvalues_string");
				if(clusterName != null)
					oProject.setClusterName(clusterName);
			}

			String clusterResource = (String)doc.get("clusterResource_docvalues_string");
			if(clusterResource != null)
				oProject.setClusterResource(clusterResource);

			if(saves.contains("projectName")) {
				String projectName = (String)doc.get("projectName_docvalues_string");
				if(projectName != null)
					oProject.setProjectName(projectName);
			}

			if(saves.contains("projectResource")) {
				String projectResource = (String)doc.get("projectResource_docvalues_string");
				if(projectResource != null)
					oProject.setProjectResource(projectResource);
			}

			if(saves.contains("projectDisplayName")) {
				String projectDisplayName = (String)doc.get("projectDisplayName_docvalues_string");
				if(projectDisplayName != null)
					oProject.setProjectDisplayName(projectDisplayName);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oProject.setDescription(description);
			}

			if(saves.contains("promKeycloakProxySsl")) {
				Boolean promKeycloakProxySsl = (Boolean)doc.get("promKeycloakProxySsl_docvalues_boolean");
				if(promKeycloakProxySsl != null)
					oProject.setPromKeycloakProxySsl(promKeycloakProxySsl);
			}

			if(saves.contains("promKeycloakProxyPort")) {
				Integer promKeycloakProxyPort = (Integer)doc.get("promKeycloakProxyPort_docvalues_int");
				if(promKeycloakProxyPort != null)
					oProject.setPromKeycloakProxyPort(promKeycloakProxyPort);
			}

			if(saves.contains("promKeycloakProxyHostName")) {
				String promKeycloakProxyHostName = (String)doc.get("promKeycloakProxyHostName_docvalues_string");
				if(promKeycloakProxyHostName != null)
					oProject.setPromKeycloakProxyHostName(promKeycloakProxyHostName);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexProject(JsonObject doc) {
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
		if(projectName != null) {
			doc.put("projectName_docvalues_string", projectName);
		}
		if(projectResource != null) {
			doc.put("projectResource_docvalues_string", projectResource);
		}
		if(projectDisplayName != null) {
			doc.put("projectDisplayName_docvalues_string", projectDisplayName);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
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

	public static String varStoredProject(String entityVar) {
		switch(entityVar) {
			case "hubId":
				return "hubId_docvalues_string";
			case "hubResource":
				return "hubResource_docvalues_string";
			case "clusterName":
				return "clusterName_docvalues_string";
			case "clusterResource":
				return "clusterResource_docvalues_string";
			case "projectName":
				return "projectName_docvalues_string";
			case "projectResource":
				return "projectResource_docvalues_string";
			case "projectDisplayName":
				return "projectDisplayName_docvalues_string";
			case "description":
				return "description_docvalues_string";
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

	public static String varIndexedProject(String entityVar) {
		switch(entityVar) {
			case "hubId":
				return "hubId_docvalues_string";
			case "hubResource":
				return "hubResource_docvalues_string";
			case "clusterName":
				return "clusterName_docvalues_string";
			case "clusterResource":
				return "clusterResource_docvalues_string";
			case "projectName":
				return "projectName_docvalues_string";
			case "projectResource":
				return "projectResource_docvalues_string";
			case "projectDisplayName":
				return "projectDisplayName_docvalues_string";
			case "description":
				return "description_docvalues_string";
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

	public static String searchVarProject(String searchVar) {
		switch(searchVar) {
			case "hubId_docvalues_string":
				return "hubId";
			case "hubResource_docvalues_string":
				return "hubResource";
			case "clusterName_docvalues_string":
				return "clusterName";
			case "clusterResource_docvalues_string":
				return "clusterResource";
			case "projectName_docvalues_string":
				return "projectName";
			case "projectResource_docvalues_string":
				return "projectResource";
			case "projectDisplayName_docvalues_string":
				return "projectDisplayName";
			case "description_docvalues_string":
				return "description";
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

	public static String varSearchProject(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedProject(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeProject(doc);
	}
	public void storeProject(SolrResponse.Doc doc) {
		Project oProject = (Project)this;
		SiteRequest siteRequest = oProject.getSiteRequest_();

		oProject.setHubId(Optional.ofNullable(doc.get("hubId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oProject.setHubResource(Optional.ofNullable(doc.get("hubResource_docvalues_string")).map(v -> v.toString()).orElse(null));
		oProject.setClusterName(Optional.ofNullable(doc.get("clusterName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oProject.setClusterResource(Optional.ofNullable(doc.get("clusterResource_docvalues_string")).map(v -> v.toString()).orElse(null));
		oProject.setProjectName(Optional.ofNullable(doc.get("projectName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oProject.setProjectResource(Optional.ofNullable(doc.get("projectResource_docvalues_string")).map(v -> v.toString()).orElse(null));
		oProject.setProjectDisplayName(Optional.ofNullable(doc.get("projectDisplayName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oProject.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oProject.setPromKeycloakProxySsl(Optional.ofNullable(doc.get("promKeycloakProxySsl_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oProject.setPromKeycloakProxyPort(Optional.ofNullable(doc.get("promKeycloakProxyPort_docvalues_int")).map(v -> v.toString()).orElse(null));
		oProject.setPromKeycloakProxyHostName(Optional.ofNullable(doc.get("promKeycloakProxyHostName_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestProject() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof Project) {
			Project original = (Project)o;
			if(!Objects.equals(hubId, original.getHubId()))
				apiRequest.addVars("hubId");
			if(!Objects.equals(hubResource, original.getHubResource()))
				apiRequest.addVars("hubResource");
			if(!Objects.equals(clusterName, original.getClusterName()))
				apiRequest.addVars("clusterName");
			if(!Objects.equals(clusterResource, original.getClusterResource()))
				apiRequest.addVars("clusterResource");
			if(!Objects.equals(projectName, original.getProjectName()))
				apiRequest.addVars("projectName");
			if(!Objects.equals(projectResource, original.getProjectResource()))
				apiRequest.addVars("projectResource");
			if(!Objects.equals(projectDisplayName, original.getProjectDisplayName()))
				apiRequest.addVars("projectDisplayName");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
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
		sb.append(Optional.ofNullable(projectName).map(v -> "projectName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(projectResource).map(v -> "projectResource: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(projectDisplayName).map(v -> "projectDisplayName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(promKeycloakProxySsl).map(v -> "promKeycloakProxySsl: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(promKeycloakProxyPort).map(v -> "promKeycloakProxyPort: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(promKeycloakProxyHostName).map(v -> "promKeycloakProxyHostName: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "Project";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.project.Project";
	public static final String CLASS_AUTH_RESOURCE = "PROJECT";
	public static final String CLASS_API_ADDRESS_Project = "ai-telemetry-enUS-Project";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_Project;
	}
	public static final String VAR_hubId = "hubId";
	public static final String VAR_hubResource = "hubResource";
	public static final String VAR_clusterName = "clusterName";
	public static final String VAR_clusterResource = "clusterResource";
	public static final String VAR_projectName = "projectName";
	public static final String VAR_projectResource = "projectResource";
	public static final String VAR_projectDisplayName = "projectDisplayName";
	public static final String VAR_description = "description";
	public static final String VAR_promKeycloakProxySsl = "promKeycloakProxySsl";
	public static final String VAR_promKeycloakProxyPort = "promKeycloakProxyPort";
	public static final String VAR_promKeycloakProxyHostName = "promKeycloakProxyHostName";

	public static List<String> varsQForClass() {
		return Project.varsQProject(new ArrayList<String>());
	}
	public static List<String> varsQProject(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return Project.varsFqProject(new ArrayList<String>());
	}
	public static List<String> varsFqProject(List<String> vars) {
		vars.add(VAR_hubId);
		vars.add(VAR_hubResource);
		vars.add(VAR_clusterName);
		vars.add(VAR_clusterResource);
		vars.add(VAR_projectName);
		vars.add(VAR_projectResource);
		vars.add(VAR_projectDisplayName);
		vars.add(VAR_description);
		vars.add(VAR_promKeycloakProxySsl);
		vars.add(VAR_promKeycloakProxyPort);
		vars.add(VAR_promKeycloakProxyHostName);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return Project.varsRangeProject(new ArrayList<String>());
	}
	public static List<String> varsRangeProject(List<String> vars) {
		vars.add(VAR_promKeycloakProxyPort);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_hubId = "ACM Hub";
	public static final String DISPLAY_NAME_hubResource = "hub auth resource";
	public static final String DISPLAY_NAME_clusterName = "cluster name";
	public static final String DISPLAY_NAME_clusterResource = "cluster auth resource";
	public static final String DISPLAY_NAME_projectName = "project name";
	public static final String DISPLAY_NAME_projectResource = "project auth resource";
	public static final String DISPLAY_NAME_projectDisplayName = "project display name";
	public static final String DISPLAY_NAME_description = "description";
	public static final String DISPLAY_NAME_promKeycloakProxySsl = "proxy SSL";
	public static final String DISPLAY_NAME_promKeycloakProxyPort = "proxy Port";
	public static final String DISPLAY_NAME_promKeycloakProxyHostName = "proxy host name";

	@Override
	public String idForClass() {
		return projectResource;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return projectDisplayName;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return Project.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/project/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/project/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return Project.displayNameProject(var);
	}
	public static String displayNameProject(String var) {
		switch(var) {
		case VAR_hubId:
			return DISPLAY_NAME_hubId;
		case VAR_hubResource:
			return DISPLAY_NAME_hubResource;
		case VAR_clusterName:
			return DISPLAY_NAME_clusterName;
		case VAR_clusterResource:
			return DISPLAY_NAME_clusterResource;
		case VAR_projectName:
			return DISPLAY_NAME_projectName;
		case VAR_projectResource:
			return DISPLAY_NAME_projectResource;
		case VAR_projectDisplayName:
			return DISPLAY_NAME_projectDisplayName;
		case VAR_description:
			return DISPLAY_NAME_description;
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

	public static String descriptionProject(String var) {
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
		case VAR_projectName:
			return "The name of this project";
		case VAR_projectResource:
			return "The unique authorization resource for the project for multi-tenancy";
		case VAR_projectDisplayName:
			return "The display name of this project";
		case VAR_description:
			return "A description of this project";
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

	public static String classSimpleNameProject(String var) {
		switch(var) {
		case VAR_hubId:
			return "String";
		case VAR_hubResource:
			return "String";
		case VAR_clusterName:
			return "String";
		case VAR_clusterResource:
			return "String";
		case VAR_projectName:
			return "String";
		case VAR_projectResource:
			return "String";
		case VAR_projectDisplayName:
			return "String";
		case VAR_description:
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

	public static Integer htmColumnProject(String var) {
		switch(var) {
		case VAR_hubId:
			return 1;
		case VAR_clusterName:
			return 2;
		case VAR_projectName:
			return 3;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowProject(String var) {
		switch(var) {
		case VAR_hubId:
			return 3;
		case VAR_clusterName:
			return 3;
		case VAR_projectName:
			return 3;
		case VAR_description:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellProject(String var) {
		switch(var) {
		case VAR_hubId:
			return 1;
		case VAR_clusterName:
			return 3;
		case VAR_projectName:
			return 5;
		case VAR_description:
			return 7;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinProject(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxProject(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxProject(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minProject(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
