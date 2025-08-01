package org.mghpcc.aitelemetry.model.baremetalresourceclass;

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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalResourceClassGen into the class BareMetalResourceClass. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the BareMetalResourceClass API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the BareMetalResourceClass class and it's generated class BareMetalResourceClassGen&lt;BaseModel&gt;: </h3>extends BareMetalResourceClassGen
 * <p>
 * This Java class extends a generated Java class BareMetalResourceClassGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClass">Find the class BareMetalResourceClass in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalResourceClassGen<BaseModel>
 * <p>This <code>class BareMetalResourceClass extends BareMetalResourceClassGen&lt;BaseModel&gt;</code>, which means it extends a newly generated BareMetalResourceClassGen. 
 * The generated <code>class BareMetalResourceClassGen extends BaseModel</code> which means that BareMetalResourceClass extends BareMetalResourceClassGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: bare metal resource classes"</b>, which groups all of the OpenAPIs for BareMetalResourceClass objects under the tag "bare metal resource classes". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/bare-metal-resource-class</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/bare-metal-resource-class"</b>, which defines the base API URI for BareMetalResourceClass objects as "/en-us/api/bare-metal-resource-class" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalResourceClass class will inherit the helpful inherited class comments from the super class BareMetalResourceClassGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 14</h2>
 * <p>This class contains a comment <b>"Order: 14"</b>, which means this class will be sorted by the given number 14 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 14</h2>
 * <p>This class contains a comment <b>"SqlOrder: 14"</b>, which means this class will be sorted by the given number 14 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalResourceClass Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a bare metal resource class</h2>
 * <p>This class contains a comment <b>"AName.enUS: a bare metal resource class"</b>, which identifies the language context to describe a BareMetalResourceClass as "a bare metal resource class". 
 * </p>
 * <p>
 * Delete the class BareMetalResourceClass in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClass&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.baremetalresourceclass in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BareMetalResourceClassGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalResourceClass.class);

	public static final String Description_enUS = "A bare metal resource class";
	public static final String AName_enUS = "a bare metal resource class";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this bare metal resource class";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the bare metal resource class";
	public static final String SingularName_enUS = "bare metal resource class";
	public static final String PluralName_enUS = "bare metal resource classes";
	public static final String NameActual_enUS = "current bare metal resource class";
	public static final String AllName_enUS = "all bare metal resource classes";
	public static final String SearchAllNameBy_enUS = "search bare metal resource classes by ";
	public static final String SearchAllName_enUS = "search bare metal resource classes";
	public static final String Title_enUS = "bare metal resource classes";
	public static final String ThePluralName_enUS = "the bare metal resource classes";
	public static final String NoNameFound_enUS = "no bare metal resource class found";
	public static final String ApiUri_enUS = "/en-us/api/bare-metal-resource-class";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/bare-metal-resource-class";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/bare-metal-resource-class/{name}";
	public static final String OfName_enUS = "of bare metal resource class";
	public static final String ANameAdjective_enUS = "a bare metal resource class";
	public static final String NameAdjectiveSingular_enUS = "bare metal resource class";
	public static final String NameAdjectivePlural_enUS = "bare metal resource classes";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/bare-metal-resource-class";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/bare-metal-resource-class";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-resource-class";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/bare-metal-resource-class/{name}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/bare-metal-resource-class/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-resource-class/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/bare-metal-resource-class";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/bare-metal-resource-class";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-resource-class";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/bare-metal-resource-class";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/bare-metal-resource-class";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-resource-class";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/bare-metal-resource-class/{name}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/bare-metal-resource-class/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-resource-class/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/bare-metal-resource-class-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/bare-metal-resource-class-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-resource-class-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/bare-metal-resource-class";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/bare-metal-resource-class";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/bare-metal-resource-class";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/bare-metal-resource-class/{name}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/bare-metal-resource-class/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/bare-metal-resource-class/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/bare-metal-resource-class";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/bare-metal-resource-class";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-resource-class";

	public static final String Icon = "<i class=\"fa-regular fa-server\"></i>";

	//////////
	// name //
	//////////


	/**	 The entity name
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String name;

	/**	<br> The entity name
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClass&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = BareMetalResourceClass.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalResourceClass nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (BareMetalResourceClass)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return BareMetalResourceClass.staticSearchName(siteRequest_, BareMetalResourceClass.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
	}

	///////////
	// count //
	///////////


	/**	 The entity count
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String count;

	/**	<br> The entity count
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClass&fq=entiteVar_enUS_indexed_string:count">Find the entity count in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _count(Wrap<String> w);

	public String getCount() {
		return count;
	}
	public void setCount(String o) {
		this.count = BareMetalResourceClass.staticSetCount(siteRequest_, o);
	}
	public static String staticSetCount(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalResourceClass countInit() {
		Wrap<String> countWrap = new Wrap<String>().var("count");
		if(count == null) {
			_count(countWrap);
			Optional.ofNullable(countWrap.getO()).ifPresent(o -> {
				setCount(o);
			});
		}
		return (BareMetalResourceClass)this;
	}

	public static String staticSearchCount(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrCount(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCount(SiteRequest siteRequest_, String o) {
		return BareMetalResourceClass.staticSearchCount(siteRequest_, BareMetalResourceClass.staticSetCount(siteRequest_, o)).toString();
	}

	public String sqlCount() {
		return count;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalResourceClassGen<DEV>> promiseDeepBareMetalResourceClass(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalResourceClass();
	}

	public Future<BareMetalResourceClassGen<DEV>> promiseDeepBareMetalResourceClass() {
		Promise<BareMetalResourceClassGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalResourceClass(promise2);
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

	public Future<Void> promiseBareMetalResourceClass(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				countInit();
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

	@Override public Future<? extends BareMetalResourceClassGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalResourceClass(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalResourceClass(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalResourceClass(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalResourceClass(v);
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
	public Object obtainBareMetalResourceClass(String var) {
		BareMetalResourceClass oBareMetalResourceClass = (BareMetalResourceClass)this;
		switch(var) {
			case "name":
				return oBareMetalResourceClass.name;
			case "count":
				return oBareMetalResourceClass.count;
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
				o = relateBareMetalResourceClass(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalResourceClass(String var, Object val) {
		BareMetalResourceClass oBareMetalResourceClass = (BareMetalResourceClass)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, BareMetalResourceClass o) {
		return staticSetBareMetalResourceClass(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetBareMetalResourceClass(String entityVar, SiteRequest siteRequest_, String v, BareMetalResourceClass o) {
		switch(entityVar) {
		case "name":
			return BareMetalResourceClass.staticSetName(siteRequest_, v);
		case "count":
			return BareMetalResourceClass.staticSetCount(siteRequest_, v);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalResourceClass(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalResourceClass(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return BareMetalResourceClass.staticSearchName(siteRequest_, (String)o);
		case "count":
			return BareMetalResourceClass.staticSearchCount(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalResourceClass(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalResourceClass(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return BareMetalResourceClass.staticSearchStrName(siteRequest_, (String)o);
		case "count":
			return BareMetalResourceClass.staticSearchStrCount(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalResourceClass(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalResourceClass(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return BareMetalResourceClass.staticSearchFqName(siteRequest_, o);
		case "count":
			return BareMetalResourceClass.staticSearchFqCount(siteRequest_, o);
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
					o = persistBareMetalResourceClass(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistBareMetalResourceClass(String var, Object val) {
		String varLower = var.toLowerCase();
			if("name".equals(varLower)) {
				if(val instanceof String) {
					setName((String)val);
				}
				saves.add("name");
				return val;
			} else if("count".equals(varLower)) {
				if(val instanceof String) {
					setCount((String)val);
				}
				saves.add("count");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateBareMetalResourceClass(doc);
	}
	public void populateBareMetalResourceClass(SolrResponse.Doc doc) {
		BareMetalResourceClass oBareMetalResourceClass = (BareMetalResourceClass)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oBareMetalResourceClass.setName(name);
			}

			if(saves.contains("count")) {
				String count = (String)doc.get("count_docvalues_string");
				if(count != null)
					oBareMetalResourceClass.setCount(count);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexBareMetalResourceClass(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(count != null) {
			doc.put("count_docvalues_string", count);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredBareMetalResourceClass(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "count":
				return "count_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedBareMetalResourceClass(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "count":
				return "count_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarBareMetalResourceClass(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "count_docvalues_string":
				return "count";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchBareMetalResourceClass(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedBareMetalResourceClass(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeBareMetalResourceClass(doc);
	}
	public void storeBareMetalResourceClass(SolrResponse.Doc doc) {
		BareMetalResourceClass oBareMetalResourceClass = (BareMetalResourceClass)this;
		SiteRequest siteRequest = oBareMetalResourceClass.getSiteRequest_();

		oBareMetalResourceClass.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalResourceClass.setCount(Optional.ofNullable(doc.get("count_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBareMetalResourceClass() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BareMetalResourceClass) {
			BareMetalResourceClass original = (BareMetalResourceClass)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(count, original.getCount()))
				apiRequest.addVars("count");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(count).map(v -> "count: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "BareMetalResourceClass";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClass";
	public static final String CLASS_AUTH_RESOURCE = "BAREMETALRESOURCECLASS";
	public static final String CLASS_API_ADDRESS_BareMetalResourceClass = "ai-telemetry-enUS-BareMetalResourceClass";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_BareMetalResourceClass;
	}
	public static final String VAR_name = "name";
	public static final String VAR_count = "count";

	public static List<String> varsQForClass() {
		return BareMetalResourceClass.varsQBareMetalResourceClass(new ArrayList<String>());
	}
	public static List<String> varsQBareMetalResourceClass(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return BareMetalResourceClass.varsFqBareMetalResourceClass(new ArrayList<String>());
	}
	public static List<String> varsFqBareMetalResourceClass(List<String> vars) {
		vars.add(VAR_name);
		vars.add(VAR_count);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return BareMetalResourceClass.varsRangeBareMetalResourceClass(new ArrayList<String>());
	}
	public static List<String> varsRangeBareMetalResourceClass(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "resource class name";
	public static final String DISPLAY_NAME_count = "number of available nodes";

	@Override
	public String idForClass() {
		return name;
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
		return BareMetalResourceClass.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/bare-metal-resource-class/%s";
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
		return BareMetalResourceClass.displayNameBareMetalResourceClass(var);
	}
	public static String displayNameBareMetalResourceClass(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_count:
			return DISPLAY_NAME_count;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionBareMetalResourceClass(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_name:
			return "The name of the resource class";
		case VAR_count:
			return "The number of available nodes";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameBareMetalResourceClass(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_count:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnBareMetalResourceClass(String var) {
		switch(var) {
		case VAR_name:
			return 0;
		case VAR_count:
			return 1;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowBareMetalResourceClass(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_count:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellBareMetalResourceClass(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_count:
			return 3;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinBareMetalResourceClass(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxBareMetalResourceClass(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxBareMetalResourceClass(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minBareMetalResourceClass(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
