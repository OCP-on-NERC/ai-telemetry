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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class AiProjectGen into the class AiProject. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the AiProject API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the AiProject class and it's generated class AiProjectGen&lt;BaseModel&gt;: </h3>extends AiProjectGen
 * <p>
 * This Java class extends a generated Java class AiProjectGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProject">Find the class AiProject in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends AiProjectGen<BaseModel>
 * <p>This <code>class AiProject extends AiProjectGen&lt;BaseModel&gt;</code>, which means it extends a newly generated AiProjectGen. 
 * The generated <code>class AiProjectGen extends BaseModel</code> which means that AiProject extends AiProjectGen which extends BaseModel. 
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
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: AI projects"</b>, which groups all of the OpenAPIs for AiProject objects under the tag "AI projects". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/ai-project</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/ai-project"</b>, which defines the base API URI for AiProject objects as "/en-us/api/ai-project" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the AiProject class will inherit the helpful inherited class comments from the super class AiProjectGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 8</h2>
 * <p>This class contains a comment <b>"Order: 8"</b>, which means this class will be sorted by the given number 8 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.project.AiProjectPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.project.AiProjectPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the AiProject Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an AI project</h2>
 * <p>This class contains a comment <b>"AName.enUS: an AI project"</b>, which identifies the language context to describe a AiProject as "an AI project". 
 * </p>
 * <p>
 * Delete the class AiProject in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProject&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class AiProjectGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(AiProject.class);

	public static final String Description_enUS = "A research project using AI and GPUs";
	public static final String AName_enUS = "an AI project";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this AI project";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "theAI project";
	public static final String SingularName_enUS = "AI project";
	public static final String PluralName_enUS = "AI projects";
	public static final String NameActual_enUS = "current AI project";
	public static final String AllName_enUS = "all AI projects";
	public static final String SearchAllNameBy_enUS = "search AI projects by ";
	public static final String Title_enUS = "AI projects";
	public static final String ThePluralName_enUS = "the AI projects";
	public static final String NoNameFound_enUS = "no AI project found";
	public static final String ApiUri_enUS = "/en-us/api/ai-project";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/ai-project";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/ai-project/{pageId}";
	public static final String OfName_enUS = "of AI project";
	public static final String ANameAdjective_enUS = "an AI project";
	public static final String NameAdjectiveSingular_enUS = "AI project";
	public static final String NameAdjectivePlural_enUS = "AI projects";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/ai-project";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/ai-project";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/ai-project";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/ai-project/{projectId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/ai-project/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/ai-project/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/ai-project";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/ai-project";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/ai-project";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/ai-project";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/ai-project";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/ai-project";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/ai-project/{projectId}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/ai-project/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/ai-project/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/ai-project-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/ai-project-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/ai-project-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/ai-project";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/ai-project";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/ai-project";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/ai-project/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/ai-project/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/ai-project/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/ai-project/{pageId}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/ai-project/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/ai-project/%s";

	public static final String Icon = "<i class=\"fa-regular fa-school\"></i>";

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProject&fq=entiteVar_enUS_indexed_string:projectName">Find the entity projectName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _projectName(Wrap<String> w);

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String o) {
		this.projectName = AiProject.staticSetProjectName(siteRequest_, o);
	}
	public static String staticSetProjectName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiProject projectNameInit() {
		Wrap<String> projectNameWrap = new Wrap<String>().var("projectName");
		if(projectName == null) {
			_projectName(projectNameWrap);
			Optional.ofNullable(projectNameWrap.getO()).ifPresent(o -> {
				setProjectName(o);
			});
		}
		return (AiProject)this;
	}

	public static String staticSearchProjectName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProjectName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProjectName(SiteRequest siteRequest_, String o) {
		return AiProject.staticSearchProjectName(siteRequest_, AiProject.staticSetProjectName(siteRequest_, o)).toString();
	}

	public String sqlProjectName() {
		return projectName;
	}

	///////////////
	// projectId //
	///////////////


	/**	 The entity projectId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String projectId;

	/**	<br> The entity projectId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProject&fq=entiteVar_enUS_indexed_string:projectId">Find the entity projectId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _projectId(Wrap<String> w);

	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String o) {
		this.projectId = AiProject.staticSetProjectId(siteRequest_, o);
	}
	public static String staticSetProjectId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiProject projectIdInit() {
		Wrap<String> projectIdWrap = new Wrap<String>().var("projectId");
		if(projectId == null) {
			_projectId(projectIdWrap);
			Optional.ofNullable(projectIdWrap.getO()).ifPresent(o -> {
				setProjectId(o);
			});
		}
		return (AiProject)this;
	}

	public static String staticSearchProjectId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrProjectId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProjectId(SiteRequest siteRequest_, String o) {
		return AiProject.staticSearchProjectId(siteRequest_, AiProject.staticSetProjectId(siteRequest_, o)).toString();
	}

	public String sqlProjectId() {
		return projectId;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProject&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = AiProject.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiProject descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (AiProject)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return AiProject.staticSearchDescription(siteRequest_, AiProject.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<AiProjectGen<DEV>> promiseDeepAiProject(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepAiProject();
	}

	public Future<AiProjectGen<DEV>> promiseDeepAiProject() {
		Promise<AiProjectGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseAiProject(promise2);
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

	public Future<Void> promiseAiProject(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				projectNameInit();
				projectIdInit();
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

	@Override public Future<? extends AiProjectGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepAiProject(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAiProject(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestAiProject(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAiProject(v);
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
	public Object obtainAiProject(String var) {
		AiProject oAiProject = (AiProject)this;
		switch(var) {
			case "projectName":
				return oAiProject.projectName;
			case "projectId":
				return oAiProject.projectId;
			case "description":
				return oAiProject.description;
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
				o = relateAiProject(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateAiProject(String var, Object val) {
		AiProject oAiProject = (AiProject)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetAiProject(entityVar,  siteRequest_, o);
	}
	public static Object staticSetAiProject(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "projectName":
			return AiProject.staticSetProjectName(siteRequest_, o);
		case "projectId":
			return AiProject.staticSetProjectId(siteRequest_, o);
		case "description":
			return AiProject.staticSetDescription(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchAiProject(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchAiProject(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "projectName":
			return AiProject.staticSearchProjectName(siteRequest_, (String)o);
		case "projectId":
			return AiProject.staticSearchProjectId(siteRequest_, (String)o);
		case "description":
			return AiProject.staticSearchDescription(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrAiProject(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrAiProject(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "projectName":
			return AiProject.staticSearchStrProjectName(siteRequest_, (String)o);
		case "projectId":
			return AiProject.staticSearchStrProjectId(siteRequest_, (String)o);
		case "description":
			return AiProject.staticSearchStrDescription(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqAiProject(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqAiProject(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "projectName":
			return AiProject.staticSearchFqProjectName(siteRequest_, o);
		case "projectId":
			return AiProject.staticSearchFqProjectId(siteRequest_, o);
		case "description":
			return AiProject.staticSearchFqDescription(siteRequest_, o);
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
					o = persistAiProject(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistAiProject(String var, Object val) {
		String varLower = var.toLowerCase();
			if("projectname".equals(varLower)) {
				if(val instanceof String) {
					setProjectName((String)val);
				}
				saves.add("projectName");
				return val;
			} else if("projectid".equals(varLower)) {
				if(val instanceof String) {
					setProjectId((String)val);
				}
				saves.add("projectId");
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
		populateAiProject(doc);
	}
	public void populateAiProject(SolrResponse.Doc doc) {
		AiProject oAiProject = (AiProject)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("projectName")) {
				String projectName = (String)doc.get("projectName_docvalues_string");
				if(projectName != null)
					oAiProject.setProjectName(projectName);
			}

			if(saves.contains("projectId")) {
				String projectId = (String)doc.get("projectId_docvalues_string");
				if(projectId != null)
					oAiProject.setProjectId(projectId);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oAiProject.setDescription(description);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexAiProject(JsonObject doc) {
		if(projectName != null) {
			doc.put("projectName_docvalues_string", projectName);
		}
		if(projectId != null) {
			doc.put("projectId_docvalues_string", projectId);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredAiProject(String entityVar) {
		switch(entityVar) {
			case "projectName":
				return "projectName_docvalues_string";
			case "projectId":
				return "projectId_docvalues_string";
			case "description":
				return "description_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedAiProject(String entityVar) {
		switch(entityVar) {
			case "projectName":
				return "projectName_docvalues_string";
			case "projectId":
				return "projectId_docvalues_string";
			case "description":
				return "description_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarAiProject(String searchVar) {
		switch(searchVar) {
			case "projectName_docvalues_string":
				return "projectName";
			case "projectId_docvalues_string":
				return "projectId";
			case "description_docvalues_string":
				return "description";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchAiProject(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedAiProject(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeAiProject(doc);
	}
	public void storeAiProject(SolrResponse.Doc doc) {
		AiProject oAiProject = (AiProject)this;
		SiteRequest siteRequest = oAiProject.getSiteRequest_();

		oAiProject.setProjectName(Optional.ofNullable(doc.get("projectName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiProject.setProjectId(Optional.ofNullable(doc.get("projectId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oAiProject.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestAiProject() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof AiProject) {
			AiProject original = (AiProject)o;
			if(!Objects.equals(projectName, original.getProjectName()))
				apiRequest.addVars("projectName");
			if(!Objects.equals(projectId, original.getProjectId()))
				apiRequest.addVars("projectId");
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
		sb.append(Optional.ofNullable(projectName).map(v -> "projectName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(projectId).map(v -> "projectId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "AiProject";
	public static final String CLASS_API_ADDRESS_AiProject = "ai-telemetry-enUS-AiProject";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_AiProject;
	}
	public static final String VAR_projectName = "projectName";
	public static final String VAR_projectId = "projectId";
	public static final String VAR_description = "description";

	public static List<String> varsQForClass() {
		return AiProject.varsQAiProject(new ArrayList<String>());
	}
	public static List<String> varsQAiProject(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return AiProject.varsFqAiProject(new ArrayList<String>());
	}
	public static List<String> varsFqAiProject(List<String> vars) {
		vars.add(VAR_projectName);
		vars.add(VAR_projectId);
		vars.add(VAR_description);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return AiProject.varsRangeAiProject(new ArrayList<String>());
	}
	public static List<String> varsRangeAiProject(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_projectName = "name";
	public static final String DISPLAY_NAME_projectId = "project Id";
	public static final String DISPLAY_NAME_description = "description";

	@Override
	public String idForClass() {
		return projectId;
	}

	@Override
	public String titleForClass() {
		return title;
	}

	@Override
	public String nameForClass() {
		return projectName;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return AiProject.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/ai-project/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/ai-project/%s";
	}

	public static String displayNameForClass(String var) {
		return AiProject.displayNameAiProject(var);
	}
	public static String displayNameAiProject(String var) {
		switch(var) {
		case VAR_projectName:
			return DISPLAY_NAME_projectName;
		case VAR_projectId:
			return DISPLAY_NAME_projectId;
		case VAR_description:
			return DISPLAY_NAME_description;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionAiProject(String var) {
		switch(var) {
		case VAR_projectName:
			return "The name of this AI project";
		case VAR_projectId:
			return "The unique ID of this AI project";
		case VAR_description:
			return "A description of this AI project";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameAiProject(String var) {
		switch(var) {
		case VAR_projectName:
			return "String";
		case VAR_projectId:
			return "String";
		case VAR_description:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnAiProject(String var) {
		switch(var) {
		case VAR_projectName:
			return 1;
		case VAR_description:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowAiProject(String var) {
		switch(var) {
		case VAR_projectName:
			return 3;
		case VAR_projectId:
			return 3;
		case VAR_description:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellAiProject(String var) {
		switch(var) {
		case VAR_projectName:
			return 1;
		case VAR_projectId:
			return 2;
		case VAR_description:
			return 2;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinAiProject(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxAiProject(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxAiProject(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minAiProject(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
