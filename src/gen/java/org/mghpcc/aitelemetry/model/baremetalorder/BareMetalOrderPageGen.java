package org.mghpcc.aitelemetry.model.baremetalorder;

import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage;
import org.mghpcc.aitelemetry.model.BaseModel;
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
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClass;
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BareMetalOrderPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalOrderPageGen into the class BareMetalOrderPage. 
 * </li>
 * <h3>About the BareMetalOrderPage class and it's generated class BareMetalOrderPageGen&lt;BareMetalOrderGenPage&gt;: </h3>extends BareMetalOrderPageGen
 * <p>
 * This Java class extends a generated Java class BareMetalOrderPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage">Find the class BareMetalOrderPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalOrderPageGen<BareMetalOrderGenPage>
 * <p>This <code>class BareMetalOrderPage extends BareMetalOrderPageGen&lt;BareMetalOrderGenPage&gt;</code>, which means it extends a newly generated BareMetalOrderPageGen. 
 * The generated <code>class BareMetalOrderPageGen extends BareMetalOrderGenPage</code> which means that BareMetalOrderPage extends BareMetalOrderPageGen which extends BareMetalOrderGenPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalOrderPage class will inherit the helpful inherited class comments from the super class BareMetalOrderPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalOrderPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: null</h2>
 * <p>
 * Delete the class BareMetalOrderPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class BareMetalOrderPageGen<DEV> extends BareMetalOrderGenPage {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalOrderPage.class);

	/////////////////////////
	// resourceClassSearch //
	/////////////////////////


	/**	 The entity resourceClassSearch
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<BareMetalResourceClass> resourceClassSearch;

	/**	<br> The entity resourceClassSearch
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage&fq=entiteVar_enUS_indexed_string:resourceClassSearch">Find the entity resourceClassSearch in Solr</a>
	 * <br>
	 * @param promise is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _resourceClassSearch(Promise<SearchList<BareMetalResourceClass>> promise);

	public SearchList<BareMetalResourceClass> getResourceClassSearch() {
		return resourceClassSearch;
	}

	public void setResourceClassSearch(SearchList<BareMetalResourceClass> resourceClassSearch) {
		this.resourceClassSearch = resourceClassSearch;
	}
	public static SearchList<BareMetalResourceClass> staticSetResourceClassSearch(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected Future<SearchList<BareMetalResourceClass>> resourceClassSearchPromise() {
		Promise<SearchList<BareMetalResourceClass>> promise = Promise.promise();
		Promise<SearchList<BareMetalResourceClass>> promise2 = Promise.promise();
		_resourceClassSearch(promise2);
		promise2.future().onSuccess(o -> {
			if(o != null && resourceClassSearch == null) {
				o.promiseDeepForClass(siteRequest_).onSuccess(a -> {
					setResourceClassSearch(o);
					promise.complete(o);
				}).onFailure(ex -> {
					promise.fail(ex);
				});
			} else {
				promise.complete(o);
			}
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	/////////////////////
	// resourceClasses //
	/////////////////////


	/**	 The entity resourceClasses
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray resourceClasses;

	/**	<br> The entity resourceClasses
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage&fq=entiteVar_enUS_indexed_string:resourceClasses">Find the entity resourceClasses in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _resourceClasses(Wrap<JsonArray> w);

	public JsonArray getResourceClasses() {
		return resourceClasses;
	}

	public void setResourceClasses(JsonArray resourceClasses) {
		this.resourceClasses = resourceClasses;
	}
	@JsonIgnore
	public void setResourceClasses(String o) {
		this.resourceClasses = BareMetalOrderPage.staticSetResourceClasses(siteRequest_, o);
	}
	public static JsonArray staticSetResourceClasses(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BareMetalOrderPage resourceClassesInit() {
		Wrap<JsonArray> resourceClassesWrap = new Wrap<JsonArray>().var("resourceClasses");
		if(resourceClasses == null) {
			_resourceClasses(resourceClassesWrap);
			Optional.ofNullable(resourceClassesWrap.getO()).ifPresent(o -> {
				setResourceClasses(o);
			});
		}
		return (BareMetalOrderPage)this;
	}

	public static String staticSearchResourceClasses(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrResourceClasses(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResourceClasses(SiteRequest siteRequest_, String o) {
		return BareMetalOrderPage.staticSearchResourceClasses(siteRequest_, BareMetalOrderPage.staticSetResourceClasses(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalOrderPageGen<DEV>> promiseDeepBareMetalOrderPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalOrderPage();
	}

	public Future<BareMetalOrderPageGen<DEV>> promiseDeepBareMetalOrderPage() {
		Promise<BareMetalOrderPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalOrderPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBareMetalOrderGenPage(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseBareMetalOrderPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				promise2.complete();
			} catch(Exception ex) {
				promise2.fail(ex);
			}
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			resourceClassSearchPromise().onSuccess(resourceClassSearch -> {
				promise2.complete();
			}).onFailure(ex -> {
				promise2.fail(ex);
			});
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				resourceClassesInit();
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

	@Override public Future<? extends BareMetalOrderPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalOrderPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalOrderPage(SiteRequest siteRequest_) {
			super.siteRequestBareMetalOrderGenPage(siteRequest_);
		if(resourceClassSearch != null)
			resourceClassSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalOrderPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalOrderPage(v);
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
	public Object obtainBareMetalOrderPage(String var) {
		BareMetalOrderPage oBareMetalOrderPage = (BareMetalOrderPage)this;
		switch(var) {
			case "resourceClassSearch":
				return oBareMetalOrderPage.resourceClassSearch;
			case "resourceClasses":
				return oBareMetalOrderPage.resourceClasses;
			default:
				return super.obtainBareMetalOrderGenPage(var);
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
				o = relateBareMetalOrderPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalOrderPage(String var, Object val) {
		BareMetalOrderPage oBareMetalOrderPage = (BareMetalOrderPage)this;
		switch(var) {
			default:
				return super.relateBareMetalOrderGenPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, BareMetalOrderPage o) {
		return staticSetBareMetalOrderPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetBareMetalOrderPage(String entityVar, SiteRequest siteRequest_, String v, BareMetalOrderPage o) {
		switch(entityVar) {
		case "resourceClasses":
			return BareMetalOrderPage.staticSetResourceClasses(siteRequest_, v);
			default:
				return BareMetalOrderGenPage.staticSetBareMetalOrderGenPage(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalOrderPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalOrderPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "resourceClasses":
			return BareMetalOrderPage.staticSearchResourceClasses(siteRequest_, (JsonArray)o);
			default:
				return BareMetalOrderGenPage.staticSearchBareMetalOrderGenPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalOrderPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalOrderPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "resourceClasses":
			return BareMetalOrderPage.staticSearchStrResourceClasses(siteRequest_, (String)o);
			default:
				return BareMetalOrderGenPage.staticSearchStrBareMetalOrderGenPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalOrderPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalOrderPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "resourceClasses":
			return BareMetalOrderPage.staticSearchFqResourceClasses(siteRequest_, o);
			default:
				return BareMetalOrderGenPage.staticSearchFqBareMetalOrderGenPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "BareMetalOrderPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage";
	public static final String VAR_resourceClassSearch = "resourceClassSearch";
	public static final String VAR_resourceClasses = "resourceClasses";

	public static final String DISPLAY_NAME_resourceClassSearch = "";
	public static final String DISPLAY_NAME_resourceClasses = "";

	@Override
	public String idForClass() {
		return null;
	}

	@Override
	public String titleForClass() {
		return null;
	}

	@Override
	public String nameForClass() {
		return null;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return null;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return null;
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
		return BareMetalOrderPage.displayNameBareMetalOrderPage(var);
	}
	public static String displayNameBareMetalOrderPage(String var) {
		switch(var) {
		case VAR_resourceClassSearch:
			return DISPLAY_NAME_resourceClassSearch;
		case VAR_resourceClasses:
			return DISPLAY_NAME_resourceClasses;
		default:
			return BareMetalOrderGenPage.displayNameBareMetalOrderGenPage(var);
		}
	}

	public static String descriptionBareMetalOrderPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return BareMetalOrderGenPage.descriptionBareMetalOrderGenPage(var);
		}
	}

	public static String classSimpleNameBareMetalOrderPage(String var) {
		switch(var) {
		case VAR_resourceClassSearch:
			return "SearchList";
		case VAR_resourceClasses:
			return "JsonArray";
			default:
				return BareMetalOrderGenPage.classSimpleNameBareMetalOrderGenPage(var);
		}
	}

	public static Integer htmColumnBareMetalOrderPage(String var) {
		switch(var) {
			default:
				return BareMetalOrderGenPage.htmColumnBareMetalOrderGenPage(var);
		}
	}

	public static Integer htmRowBareMetalOrderPage(String var) {
		switch(var) {
			default:
				return BareMetalOrderGenPage.htmRowBareMetalOrderGenPage(var);
		}
	}

	public static Integer htmCellBareMetalOrderPage(String var) {
		switch(var) {
			default:
				return BareMetalOrderGenPage.htmCellBareMetalOrderGenPage(var);
		}
	}

	public static Integer lengthMinBareMetalOrderPage(String var) {
		switch(var) {
			default:
				return BareMetalOrderGenPage.lengthMinBareMetalOrderGenPage(var);
		}
	}

	public static Integer lengthMaxBareMetalOrderPage(String var) {
		switch(var) {
			default:
				return BareMetalOrderGenPage.lengthMaxBareMetalOrderGenPage(var);
		}
	}

	public static Integer maxBareMetalOrderPage(String var) {
		switch(var) {
			default:
				return BareMetalOrderGenPage.maxBareMetalOrderGenPage(var);
		}
	}

	public static Integer minBareMetalOrderPage(String var) {
		switch(var) {
			default:
				return BareMetalOrderGenPage.minBareMetalOrderGenPage(var);
		}
	}
}
