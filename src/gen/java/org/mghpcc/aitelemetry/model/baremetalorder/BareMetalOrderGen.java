package org.mghpcc.aitelemetry.model.baremetalorder;

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
import java.lang.Long;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalOrderGen into the class BareMetalOrder. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the BareMetalOrder API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the BareMetalOrder class and it's generated class BareMetalOrderGen&lt;BaseModel&gt;: </h3>extends BareMetalOrderGen
 * <p>
 * This Java class extends a generated Java class BareMetalOrderGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder">Find the class BareMetalOrder in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalOrderGen<BaseModel>
 * <p>This <code>class BareMetalOrder extends BareMetalOrderGen&lt;BaseModel&gt;</code>, which means it extends a newly generated BareMetalOrderGen. 
 * The generated <code>class BareMetalOrderGen extends BaseModel</code> which means that BareMetalOrder extends BareMetalOrderGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: bare metal orders"</b>, which groups all of the OpenAPIs for BareMetalOrder objects under the tag "bare metal orders". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/bare-metal-order</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/bare-metal-order"</b>, which defines the base API URI for BareMetalOrder objects as "/en-us/api/bare-metal-order" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalOrder class will inherit the helpful inherited class comments from the super class BareMetalOrderGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 12</h2>
 * <p>This class contains a comment <b>"Order: 12"</b>, which means this class will be sorted by the given number 12 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalOrder Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a bare metal order</h2>
 * <p>This class contains a comment <b>"AName.enUS: a bare metal order"</b>, which identifies the language context to describe a BareMetalOrder as "a bare metal order". 
 * </p>
 * <p>
 * Delete the class BareMetalOrder in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.baremetalorder in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BareMetalOrderGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalOrder.class);

	public static final String Description_enUS = "A bare metal order";
	public static final String AName_enUS = "a bare metal order";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this bare metal order";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the bare metal order";
	public static final String SingularName_enUS = "bare metal order";
	public static final String PluralName_enUS = "bare metal orders";
	public static final String NameActual_enUS = "current bare metal order";
	public static final String AllName_enUS = "all bare metal orders";
	public static final String SearchAllNameBy_enUS = "search bare metal orders by ";
	public static final String Title_enUS = "bare metal orders";
	public static final String ThePluralName_enUS = "the bare metal orders";
	public static final String NoNameFound_enUS = "no bare metal order found";
	public static final String ApiUri_enUS = "/en-us/api/bare-metal-order";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/bare-metal-order";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/bare-metal-order/{pk}";
	public static final String OfName_enUS = "of bare metal order";
	public static final String ANameAdjective_enUS = "a bare metal order";
	public static final String NameAdjectiveSingular_enUS = "bare metal order";
	public static final String NameAdjectivePlural_enUS = "bare metal orders";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/bare-metal-order";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/bare-metal-order";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/bare-metal-order/{pk}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/bare-metal-order/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/bare-metal-order";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/bare-metal-order";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/bare-metal-order";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/bare-metal-order";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/bare-metal-order/{pk}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/bare-metal-order/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order/%s";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/bare-metal-order";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/bare-metal-order";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/bare-metal-order";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/bare-metal-order/{pk}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/bare-metal-order/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/bare-metal-order/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/bare-metal-order/{pk}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/bare-metal-order/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/bare-metal-order/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/bare-metal-order";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/bare-metal-order";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order";

	public static final String Icon = "<i class=\"fa-duotone fa-regular fa-share-nodes\"></i>";

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = BareMetalOrder.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrder descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchDescription(siteRequest_, BareMetalOrder.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalOrderGen<DEV>> promiseDeepBareMetalOrder(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalOrder();
	}

	public Future<BareMetalOrderGen<DEV>> promiseDeepBareMetalOrder() {
		Promise<BareMetalOrderGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalOrder(promise2);
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

	public Future<Void> promiseBareMetalOrder(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
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

	@Override public Future<? extends BareMetalOrderGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalOrder(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalOrder(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalOrder(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalOrder(v);
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
	public Object obtainBareMetalOrder(String var) {
		BareMetalOrder oBareMetalOrder = (BareMetalOrder)this;
		switch(var) {
			case "description":
				return oBareMetalOrder.description;
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
				o = relateBareMetalOrder(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalOrder(String var, Object val) {
		BareMetalOrder oBareMetalOrder = (BareMetalOrder)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetBareMetalOrder(entityVar,  siteRequest_, o);
	}
	public static Object staticSetBareMetalOrder(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "description":
			return BareMetalOrder.staticSetDescription(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalOrder(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalOrder(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "description":
			return BareMetalOrder.staticSearchDescription(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalOrder(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalOrder(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "description":
			return BareMetalOrder.staticSearchStrDescription(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalOrder(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalOrder(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "description":
			return BareMetalOrder.staticSearchFqDescription(siteRequest_, o);
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
					o = persistBareMetalOrder(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistBareMetalOrder(String var, Object val) {
		String varLower = var.toLowerCase();
			if("description".equals(varLower)) {
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
		populateBareMetalOrder(doc);
	}
	public void populateBareMetalOrder(SolrResponse.Doc doc) {
		BareMetalOrder oBareMetalOrder = (BareMetalOrder)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oBareMetalOrder.setDescription(description);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexBareMetalOrder(JsonObject doc) {
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredBareMetalOrder(String entityVar) {
		switch(entityVar) {
			case "description":
				return "description_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedBareMetalOrder(String entityVar) {
		switch(entityVar) {
			case "description":
				return "description_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarBareMetalOrder(String searchVar) {
		switch(searchVar) {
			case "description_docvalues_string":
				return "description";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchBareMetalOrder(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedBareMetalOrder(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeBareMetalOrder(doc);
	}
	public void storeBareMetalOrder(SolrResponse.Doc doc) {
		BareMetalOrder oBareMetalOrder = (BareMetalOrder)this;
		SiteRequest siteRequest = oBareMetalOrder.getSiteRequest_();

		oBareMetalOrder.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBareMetalOrder() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BareMetalOrder) {
			BareMetalOrder original = (BareMetalOrder)o;
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
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "BareMetalOrder";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder";
	public static final String CLASS_API_ADDRESS_BareMetalOrder = "ai-telemetry-enUS-BareMetalOrder";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_BareMetalOrder;
	}
	public static final String VAR_description = "description";

	public static List<String> varsQForClass() {
		return BareMetalOrder.varsQBareMetalOrder(new ArrayList<String>());
	}
	public static List<String> varsQBareMetalOrder(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return BareMetalOrder.varsFqBareMetalOrder(new ArrayList<String>());
	}
	public static List<String> varsFqBareMetalOrder(List<String> vars) {
		vars.add(VAR_description);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return BareMetalOrder.varsRangeBareMetalOrder(new ArrayList<String>());
	}
	public static List<String> varsRangeBareMetalOrder(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_description = "bare metal order description";

	@Override
	public String idForClass() {
		return Optional.ofNullable(pk).map(o -> o.toString()).orElse(null);
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return Optional.ofNullable(pk).map(o -> o.toString()).orElse(null);
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return BareMetalOrder.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/bare-metal-order/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/bare-metal-order/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return BareMetalOrder.displayNameBareMetalOrder(var);
	}
	public static String displayNameBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return DISPLAY_NAME_description;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return "The description of this bare metal order";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return 1;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return 3;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinBareMetalOrder(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxBareMetalOrder(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxBareMetalOrder(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minBareMetalOrder(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
