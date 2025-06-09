package org.mghpcc.aitelemetry.model.baremetalorder;

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
import org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder;
import java.lang.String;
import org.computate.search.response.solr.SolrResponse.Stats;
import org.computate.search.response.solr.SolrResponse.FacetCounts;
import io.vertx.core.json.JsonObject;
import org.computate.vertx.serialize.vertx.JsonObjectDeserializer;
import java.lang.Integer;
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
import java.math.BigDecimal;
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import java.lang.Void;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BareMetalOrderGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalOrderGenPageGen into the class BareMetalOrderGenPage. 
 * </li>
 * <h3>About the BareMetalOrderGenPage class and it's generated class BareMetalOrderGenPageGen&lt;PageLayout&gt;: </h3>extends BareMetalOrderGenPageGen
 * <p>
 * This Java class extends a generated Java class BareMetalOrderGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage">Find the class BareMetalOrderGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalOrderGenPageGen<PageLayout>
 * <p>This <code>class BareMetalOrderGenPage extends BareMetalOrderGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated BareMetalOrderGenPageGen. 
 * The generated <code>class BareMetalOrderGenPageGen extends PageLayout</code> which means that BareMetalOrderGenPage extends BareMetalOrderGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalOrderGenPage class will inherit the helpful inherited class comments from the super class BareMetalOrderGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalOrderGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class BareMetalOrderGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class BareMetalOrderGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalOrderGenPage.class);

	///////////////////////////////
	// searchListBareMetalOrder_ //
	///////////////////////////////


	/**	 The entity searchListBareMetalOrder_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<BareMetalOrder> searchListBareMetalOrder_;

	/**	<br> The entity searchListBareMetalOrder_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage&fq=entiteVar_enUS_indexed_string:searchListBareMetalOrder_">Find the entity searchListBareMetalOrder_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListBareMetalOrder_(Wrap<SearchList<BareMetalOrder>> w);

	public SearchList<BareMetalOrder> getSearchListBareMetalOrder_() {
		return searchListBareMetalOrder_;
	}

	public void setSearchListBareMetalOrder_(SearchList<BareMetalOrder> searchListBareMetalOrder_) {
		this.searchListBareMetalOrder_ = searchListBareMetalOrder_;
	}
	public static SearchList<BareMetalOrder> staticSetSearchListBareMetalOrder_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BareMetalOrderGenPage searchListBareMetalOrder_Init() {
		Wrap<SearchList<BareMetalOrder>> searchListBareMetalOrder_Wrap = new Wrap<SearchList<BareMetalOrder>>().var("searchListBareMetalOrder_");
		if(searchListBareMetalOrder_ == null) {
			_searchListBareMetalOrder_(searchListBareMetalOrder_Wrap);
			Optional.ofNullable(searchListBareMetalOrder_Wrap.getO()).ifPresent(o -> {
				setSearchListBareMetalOrder_(o);
			});
		}
		return (BareMetalOrderGenPage)this;
	}

	////////////////////////
	// listBareMetalOrder //
	////////////////////////


	/**	 The entity listBareMetalOrder
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listBareMetalOrder = new JsonArray();

	/**	<br> The entity listBareMetalOrder
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage&fq=entiteVar_enUS_indexed_string:listBareMetalOrder">Find the entity listBareMetalOrder in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listBareMetalOrder(JsonArray l);

	public JsonArray getListBareMetalOrder() {
		return listBareMetalOrder;
	}

	public void setListBareMetalOrder(JsonArray listBareMetalOrder) {
		this.listBareMetalOrder = listBareMetalOrder;
	}
	@JsonIgnore
	public void setListBareMetalOrder(String o) {
		this.listBareMetalOrder = BareMetalOrderGenPage.staticSetListBareMetalOrder(siteRequest_, o);
	}
	public static JsonArray staticSetListBareMetalOrder(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BareMetalOrderGenPage listBareMetalOrderInit() {
		_listBareMetalOrder(listBareMetalOrder);
		return (BareMetalOrderGenPage)this;
	}

	public static String staticSearchListBareMetalOrder(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListBareMetalOrder(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListBareMetalOrder(SiteRequest siteRequest_, String o) {
		return BareMetalOrderGenPage.staticSearchListBareMetalOrder(siteRequest_, BareMetalOrderGenPage.staticSetListBareMetalOrder(siteRequest_, o)).toString();
	}

	/////////////////
	// resultCount //
	/////////////////


	/**	 The entity resultCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer resultCount;

	/**	<br> The entity resultCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _resultCount(Wrap<Integer> w);

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	@JsonIgnore
	public void setResultCount(String o) {
		this.resultCount = BareMetalOrderGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalOrderGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (BareMetalOrderGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return BareMetalOrderGenPage.staticSearchResultCount(siteRequest_, BareMetalOrderGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected BareMetalOrder result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<BareMetalOrder> w);

	public BareMetalOrder getResult() {
		return result;
	}

	public void setResult(BareMetalOrder result) {
		this.result = result;
	}
	public static BareMetalOrder staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BareMetalOrderGenPage resultInit() {
		Wrap<BareMetalOrder> resultWrap = new Wrap<BareMetalOrder>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (BareMetalOrderGenPage)this;
	}

	////////
	// pk //
	////////


	/**	 The entity pk
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long pk;

	/**	<br> The entity pk
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pk(Wrap<Long> w);

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	@JsonIgnore
	public void setPk(String o) {
		this.pk = BareMetalOrderGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected BareMetalOrderGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (BareMetalOrderGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return BareMetalOrderGenPage.staticSearchPk(siteRequest_, BareMetalOrderGenPage.staticSetPk(siteRequest_, o)).toString();
	}

	////////////
	// solrId //
	////////////


	/**	 The entity solrId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String solrId;

	/**	<br> The entity solrId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = BareMetalOrderGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrderGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (BareMetalOrderGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return BareMetalOrderGenPage.staticSearchSolrId(siteRequest_, BareMetalOrderGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriBareMetalOrder //
	///////////////////////////


	/**	 The entity pageUriBareMetalOrder
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriBareMetalOrder;

	/**	<br> The entity pageUriBareMetalOrder
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage&fq=entiteVar_enUS_indexed_string:pageUriBareMetalOrder">Find the entity pageUriBareMetalOrder in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriBareMetalOrder(Wrap<String> c);

	public String getPageUriBareMetalOrder() {
		return pageUriBareMetalOrder;
	}
	public void setPageUriBareMetalOrder(String o) {
		this.pageUriBareMetalOrder = BareMetalOrderGenPage.staticSetPageUriBareMetalOrder(siteRequest_, o);
	}
	public static String staticSetPageUriBareMetalOrder(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrderGenPage pageUriBareMetalOrderInit() {
		Wrap<String> pageUriBareMetalOrderWrap = new Wrap<String>().var("pageUriBareMetalOrder");
		if(pageUriBareMetalOrder == null) {
			_pageUriBareMetalOrder(pageUriBareMetalOrderWrap);
			Optional.ofNullable(pageUriBareMetalOrderWrap.getO()).ifPresent(o -> {
				setPageUriBareMetalOrder(o);
			});
		}
		return (BareMetalOrderGenPage)this;
	}

	public static String staticSearchPageUriBareMetalOrder(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriBareMetalOrder(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriBareMetalOrder(SiteRequest siteRequest_, String o) {
		return BareMetalOrderGenPage.staticSearchPageUriBareMetalOrder(siteRequest_, BareMetalOrderGenPage.staticSetPageUriBareMetalOrder(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalOrderGenPageGen<DEV>> promiseDeepBareMetalOrderGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalOrderGenPage();
	}

	public Future<BareMetalOrderGenPageGen<DEV>> promiseDeepBareMetalOrderGenPage() {
		Promise<BareMetalOrderGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalOrderGenPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepPageLayout(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseBareMetalOrderGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListBareMetalOrder_Init();
				listBareMetalOrderInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriBareMetalOrderInit();
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

	@Override public Future<? extends BareMetalOrderGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalOrderGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalOrderGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalOrderGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalOrderGenPage(v);
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
	public Object obtainBareMetalOrderGenPage(String var) {
		BareMetalOrderGenPage oBareMetalOrderGenPage = (BareMetalOrderGenPage)this;
		switch(var) {
			case "searchListBareMetalOrder_":
				return oBareMetalOrderGenPage.searchListBareMetalOrder_;
			case "listBareMetalOrder":
				return oBareMetalOrderGenPage.listBareMetalOrder;
			case "resultCount":
				return oBareMetalOrderGenPage.resultCount;
			case "result":
				return oBareMetalOrderGenPage.result;
			case "pk":
				return oBareMetalOrderGenPage.pk;
			case "solrId":
				return oBareMetalOrderGenPage.solrId;
			case "pageUriBareMetalOrder":
				return oBareMetalOrderGenPage.pageUriBareMetalOrder;
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
				o = relateBareMetalOrderGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalOrderGenPage(String var, Object val) {
		BareMetalOrderGenPage oBareMetalOrderGenPage = (BareMetalOrderGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, BareMetalOrderGenPage o) {
		return staticSetBareMetalOrderGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetBareMetalOrderGenPage(String entityVar, SiteRequest siteRequest_, String v, BareMetalOrderGenPage o) {
		switch(entityVar) {
		case "listBareMetalOrder":
			return BareMetalOrderGenPage.staticSetListBareMetalOrder(siteRequest_, v);
		case "resultCount":
			return BareMetalOrderGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return BareMetalOrderGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return BareMetalOrderGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriBareMetalOrder":
			return BareMetalOrderGenPage.staticSetPageUriBareMetalOrder(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalOrderGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalOrderGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listBareMetalOrder":
			return BareMetalOrderGenPage.staticSearchListBareMetalOrder(siteRequest_, (JsonArray)o);
		case "resultCount":
			return BareMetalOrderGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return BareMetalOrderGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return BareMetalOrderGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriBareMetalOrder":
			return BareMetalOrderGenPage.staticSearchPageUriBareMetalOrder(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalOrderGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalOrderGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listBareMetalOrder":
			return BareMetalOrderGenPage.staticSearchStrListBareMetalOrder(siteRequest_, (String)o);
		case "resultCount":
			return BareMetalOrderGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return BareMetalOrderGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return BareMetalOrderGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriBareMetalOrder":
			return BareMetalOrderGenPage.staticSearchStrPageUriBareMetalOrder(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalOrderGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalOrderGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listBareMetalOrder":
			return BareMetalOrderGenPage.staticSearchFqListBareMetalOrder(siteRequest_, o);
		case "resultCount":
			return BareMetalOrderGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return BareMetalOrderGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return BareMetalOrderGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriBareMetalOrder":
			return BareMetalOrderGenPage.staticSearchFqPageUriBareMetalOrder(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "BareMetalOrderGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderGenPage";
	public static final String VAR_searchListBareMetalOrder_ = "searchListBareMetalOrder_";
	public static final String VAR_listBareMetalOrder = "listBareMetalOrder";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriBareMetalOrder = "pageUriBareMetalOrder";

	public static final String DISPLAY_NAME_searchListBareMetalOrder_ = "";
	public static final String DISPLAY_NAME_listBareMetalOrder = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriBareMetalOrder = "";

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
		return BareMetalOrderGenPage.displayNameBareMetalOrderGenPage(var);
	}
	public static String displayNameBareMetalOrderGenPage(String var) {
		switch(var) {
		case VAR_searchListBareMetalOrder_:
			return DISPLAY_NAME_searchListBareMetalOrder_;
		case VAR_listBareMetalOrder:
			return DISPLAY_NAME_listBareMetalOrder;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriBareMetalOrder:
			return DISPLAY_NAME_pageUriBareMetalOrder;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionBareMetalOrderGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameBareMetalOrderGenPage(String var) {
		switch(var) {
		case VAR_searchListBareMetalOrder_:
			return "SearchList";
		case VAR_listBareMetalOrder:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "BareMetalOrder";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriBareMetalOrder:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnBareMetalOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowBareMetalOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellBareMetalOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinBareMetalOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxBareMetalOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxBareMetalOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minBareMetalOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
