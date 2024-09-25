package org.mghpcc.aitelemetry.result;

import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.page.PageLayout;
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
import org.mghpcc.aitelemetry.result.BaseResult;
import java.lang.String;
import org.computate.search.response.solr.SolrResponse.Stats;
import org.computate.search.response.solr.SolrResponse.FacetCounts;
import io.vertx.core.json.JsonObject;
import java.time.ZoneId;
import java.util.Locale;
import java.lang.Long;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.lang.Integer;
import java.math.BigDecimal;
import io.vertx.core.json.JsonArray;
import java.lang.Void;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BaseResultGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BaseResultGenPageGen into the class BaseResultGenPage. 
 * </li>
 * <h3>About the BaseResultGenPage class and it's generated class BaseResultGenPageGen&lt;PageLayout&gt;: </h3>extends BaseResultGenPageGen
 * <p>
 * This Java class extends a generated Java class BaseResultGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResultGenPage">Find the class BaseResultGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BaseResultGenPageGen<PageLayout>
 * <p>This <code>class BaseResultGenPage extends BaseResultGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated BaseResultGenPageGen. 
 * The generated <code>class BaseResultGenPageGen extends PageLayout</code> which means that BaseResultGenPage extends BaseResultGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BaseResultGenPage class will inherit the helpful inherited class comments from the super class BaseResultGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BaseResultGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class BaseResultGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResultGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.result in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.result&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BaseResultGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(BaseResultGenPage.class);

	///////////////////////////
	// searchListBaseResult_ //
	///////////////////////////


	/**	 The entity searchListBaseResult_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<BaseResult> searchListBaseResult_;

	/**	<br> The entity searchListBaseResult_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResultGenPage&fq=entiteVar_enUS_indexed_string:searchListBaseResult_">Find the entity searchListBaseResult_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListBaseResult_(Wrap<SearchList<BaseResult>> w);

	public SearchList<BaseResult> getSearchListBaseResult_() {
		return searchListBaseResult_;
	}

	public void setSearchListBaseResult_(SearchList<BaseResult> searchListBaseResult_) {
		this.searchListBaseResult_ = searchListBaseResult_;
	}
	public static SearchList<BaseResult> staticSetSearchListBaseResult_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BaseResultGenPage searchListBaseResult_Init() {
		Wrap<SearchList<BaseResult>> searchListBaseResult_Wrap = new Wrap<SearchList<BaseResult>>().var("searchListBaseResult_");
		if(searchListBaseResult_ == null) {
			_searchListBaseResult_(searchListBaseResult_Wrap);
			Optional.ofNullable(searchListBaseResult_Wrap.getO()).ifPresent(o -> {
				setSearchListBaseResult_(o);
			});
		}
		return (BaseResultGenPage)this;
	}

	////////////////////
	// listBaseResult //
	////////////////////


	/**	 The entity listBaseResult
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listBaseResult = new JsonArray();

	/**	<br> The entity listBaseResult
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResultGenPage&fq=entiteVar_enUS_indexed_string:listBaseResult">Find the entity listBaseResult in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listBaseResult(JsonArray l);

	public JsonArray getListBaseResult() {
		return listBaseResult;
	}

	public void setListBaseResult(JsonArray listBaseResult) {
		this.listBaseResult = listBaseResult;
	}
	@JsonIgnore
	public void setListBaseResult(String o) {
		this.listBaseResult = BaseResultGenPage.staticSetListBaseResult(siteRequest_, o);
	}
	public static JsonArray staticSetListBaseResult(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BaseResultGenPage listBaseResultInit() {
		_listBaseResult(listBaseResult);
		return (BaseResultGenPage)this;
	}

	public static String staticSearchListBaseResult(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListBaseResult(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListBaseResult(SiteRequest siteRequest_, String o) {
		return BaseResultGenPage.staticSearchListBaseResult(siteRequest_, BaseResultGenPage.staticSetListBaseResult(siteRequest_, o)).toString();
	}

	/////////////////////
	// baseResultCount //
	/////////////////////


	/**	 The entity baseResultCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer baseResultCount;

	/**	<br> The entity baseResultCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResultGenPage&fq=entiteVar_enUS_indexed_string:baseResultCount">Find the entity baseResultCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _baseResultCount(Wrap<Integer> w);

	public Integer getBaseResultCount() {
		return baseResultCount;
	}

	public void setBaseResultCount(Integer baseResultCount) {
		this.baseResultCount = baseResultCount;
	}
	@JsonIgnore
	public void setBaseResultCount(String o) {
		this.baseResultCount = BaseResultGenPage.staticSetBaseResultCount(siteRequest_, o);
	}
	public static Integer staticSetBaseResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BaseResultGenPage baseResultCountInit() {
		Wrap<Integer> baseResultCountWrap = new Wrap<Integer>().var("baseResultCount");
		if(baseResultCount == null) {
			_baseResultCount(baseResultCountWrap);
			Optional.ofNullable(baseResultCountWrap.getO()).ifPresent(o -> {
				setBaseResultCount(o);
			});
		}
		return (BaseResultGenPage)this;
	}

	public static Integer staticSearchBaseResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrBaseResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqBaseResultCount(SiteRequest siteRequest_, String o) {
		return BaseResultGenPage.staticSearchBaseResultCount(siteRequest_, BaseResultGenPage.staticSetBaseResultCount(siteRequest_, o)).toString();
	}

	/////////////////
	// baseResult_ //
	/////////////////


	/**	 The entity baseResult_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected BaseResult baseResult_;

	/**	<br> The entity baseResult_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResultGenPage&fq=entiteVar_enUS_indexed_string:baseResult_">Find the entity baseResult_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _baseResult_(Wrap<BaseResult> w);

	public BaseResult getBaseResult_() {
		return baseResult_;
	}

	public void setBaseResult_(BaseResult baseResult_) {
		this.baseResult_ = baseResult_;
	}
	public static BaseResult staticSetBaseResult_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BaseResultGenPage baseResult_Init() {
		Wrap<BaseResult> baseResult_Wrap = new Wrap<BaseResult>().var("baseResult_");
		if(baseResult_ == null) {
			_baseResult_(baseResult_Wrap);
			Optional.ofNullable(baseResult_Wrap.getO()).ifPresent(o -> {
				setBaseResult_(o);
			});
		}
		return (BaseResultGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResultGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = BaseResultGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResultGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (BaseResultGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return BaseResultGenPage.staticSearchId(siteRequest_, BaseResultGenPage.staticSetId(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepBaseResultGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBaseResultGenPage();
	}

	public Future<Void> promiseDeepBaseResultGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBaseResultGenPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepPageLayout(siteRequest_).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseBaseResultGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListBaseResult_Init();
				listBaseResultInit();
				baseResultCountInit();
				baseResult_Init();
				idInit();
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

	@Override public Future<Void> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBaseResultGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBaseResultGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBaseResultGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBaseResultGenPage(v);
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
	public Object obtainBaseResultGenPage(String var) {
		BaseResultGenPage oBaseResultGenPage = (BaseResultGenPage)this;
		switch(var) {
			case "searchListBaseResult_":
				return oBaseResultGenPage.searchListBaseResult_;
			case "listBaseResult":
				return oBaseResultGenPage.listBaseResult;
			case "baseResultCount":
				return oBaseResultGenPage.baseResultCount;
			case "baseResult_":
				return oBaseResultGenPage.baseResult_;
			case "id":
				return oBaseResultGenPage.id;
			default:
				return super.obtainPageLayout(var);
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
				o = relateBaseResultGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBaseResultGenPage(String var, Object val) {
		BaseResultGenPage oBaseResultGenPage = (BaseResultGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetBaseResultGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetBaseResultGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listBaseResult":
			return BaseResultGenPage.staticSetListBaseResult(siteRequest_, o);
		case "baseResultCount":
			return BaseResultGenPage.staticSetBaseResultCount(siteRequest_, o);
		case "id":
			return BaseResultGenPage.staticSetId(siteRequest_, o);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBaseResultGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBaseResultGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listBaseResult":
			return BaseResultGenPage.staticSearchListBaseResult(siteRequest_, (JsonArray)o);
		case "baseResultCount":
			return BaseResultGenPage.staticSearchBaseResultCount(siteRequest_, (Integer)o);
		case "id":
			return BaseResultGenPage.staticSearchId(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBaseResultGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBaseResultGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listBaseResult":
			return BaseResultGenPage.staticSearchStrListBaseResult(siteRequest_, (String)o);
		case "baseResultCount":
			return BaseResultGenPage.staticSearchStrBaseResultCount(siteRequest_, (Integer)o);
		case "id":
			return BaseResultGenPage.staticSearchStrId(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBaseResultGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBaseResultGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listBaseResult":
			return BaseResultGenPage.staticSearchFqListBaseResult(siteRequest_, o);
		case "baseResultCount":
			return BaseResultGenPage.staticSearchFqBaseResultCount(siteRequest_, o);
		case "id":
			return BaseResultGenPage.staticSearchFqId(siteRequest_, o);
			default:
				return PageLayout.staticSearchFqPageLayout(entityVar,  siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "BaseResultGenPage";
	public static final String VAR_searchListBaseResult_ = "searchListBaseResult_";
	public static final String VAR_listBaseResult = "listBaseResult";
	public static final String VAR_baseResultCount = "baseResultCount";
	public static final String VAR_baseResult_ = "baseResult_";
	public static final String VAR_id = "id";

	public static final String DISPLAY_NAME_searchListBaseResult_ = "";
	public static final String DISPLAY_NAME_listBaseResult = "";
	public static final String DISPLAY_NAME_baseResultCount = "";
	public static final String DISPLAY_NAME_baseResult_ = "";
	public static final String DISPLAY_NAME_id = "";

	public static String displayNameForClass(String var) {
		return BaseResultGenPage.displayNameBaseResultGenPage(var);
	}
	public static String displayNameBaseResultGenPage(String var) {
		switch(var) {
		case VAR_searchListBaseResult_:
			return DISPLAY_NAME_searchListBaseResult_;
		case VAR_listBaseResult:
			return DISPLAY_NAME_listBaseResult;
		case VAR_baseResultCount:
			return DISPLAY_NAME_baseResultCount;
		case VAR_baseResult_:
			return DISPLAY_NAME_baseResult_;
		case VAR_id:
			return DISPLAY_NAME_id;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}
}
