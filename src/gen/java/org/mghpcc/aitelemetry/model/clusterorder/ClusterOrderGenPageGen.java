package org.mghpcc.aitelemetry.model.clusterorder;

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
import org.mghpcc.aitelemetry.model.clusterorder.ClusterOrder;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ClusterOrderGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ClusterOrderGenPageGen into the class ClusterOrderGenPage. 
 * </li>
 * <h3>About the ClusterOrderGenPage class and it's generated class ClusterOrderGenPageGen&lt;PageLayout&gt;: </h3>extends ClusterOrderGenPageGen
 * <p>
 * This Java class extends a generated Java class ClusterOrderGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage">Find the class ClusterOrderGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ClusterOrderGenPageGen<PageLayout>
 * <p>This <code>class ClusterOrderGenPage extends ClusterOrderGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated ClusterOrderGenPageGen. 
 * The generated <code>class ClusterOrderGenPageGen extends PageLayout</code> which means that ClusterOrderGenPage extends ClusterOrderGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ClusterOrderGenPage class will inherit the helpful inherited class comments from the super class ClusterOrderGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ClusterOrderGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ClusterOrderGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.clusterorder in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ClusterOrderGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(ClusterOrderGenPage.class);

	/////////////////////////////
	// searchListClusterOrder_ //
	/////////////////////////////


	/**	 The entity searchListClusterOrder_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<ClusterOrder> searchListClusterOrder_;

	/**	<br> The entity searchListClusterOrder_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage&fq=entiteVar_enUS_indexed_string:searchListClusterOrder_">Find the entity searchListClusterOrder_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListClusterOrder_(Wrap<SearchList<ClusterOrder>> w);

	public SearchList<ClusterOrder> getSearchListClusterOrder_() {
		return searchListClusterOrder_;
	}

	public void setSearchListClusterOrder_(SearchList<ClusterOrder> searchListClusterOrder_) {
		this.searchListClusterOrder_ = searchListClusterOrder_;
	}
	public static SearchList<ClusterOrder> staticSetSearchListClusterOrder_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ClusterOrderGenPage searchListClusterOrder_Init() {
		Wrap<SearchList<ClusterOrder>> searchListClusterOrder_Wrap = new Wrap<SearchList<ClusterOrder>>().var("searchListClusterOrder_");
		if(searchListClusterOrder_ == null) {
			_searchListClusterOrder_(searchListClusterOrder_Wrap);
			Optional.ofNullable(searchListClusterOrder_Wrap.getO()).ifPresent(o -> {
				setSearchListClusterOrder_(o);
			});
		}
		return (ClusterOrderGenPage)this;
	}

	//////////////////////
	// listClusterOrder //
	//////////////////////


	/**	 The entity listClusterOrder
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listClusterOrder = new JsonArray();

	/**	<br> The entity listClusterOrder
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage&fq=entiteVar_enUS_indexed_string:listClusterOrder">Find the entity listClusterOrder in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listClusterOrder(JsonArray l);

	public JsonArray getListClusterOrder() {
		return listClusterOrder;
	}

	public void setListClusterOrder(JsonArray listClusterOrder) {
		this.listClusterOrder = listClusterOrder;
	}
	@JsonIgnore
	public void setListClusterOrder(String o) {
		this.listClusterOrder = ClusterOrderGenPage.staticSetListClusterOrder(siteRequest_, o);
	}
	public static JsonArray staticSetListClusterOrder(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ClusterOrderGenPage listClusterOrderInit() {
		_listClusterOrder(listClusterOrder);
		return (ClusterOrderGenPage)this;
	}

	public static String staticSearchListClusterOrder(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListClusterOrder(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListClusterOrder(SiteRequest siteRequest_, String o) {
		return ClusterOrderGenPage.staticSearchListClusterOrder(siteRequest_, ClusterOrderGenPage.staticSetListClusterOrder(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = ClusterOrderGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ClusterOrderGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (ClusterOrderGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return ClusterOrderGenPage.staticSearchResultCount(siteRequest_, ClusterOrderGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ClusterOrder result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<ClusterOrder> w);

	public ClusterOrder getResult() {
		return result;
	}

	public void setResult(ClusterOrder result) {
		this.result = result;
	}
	public static ClusterOrder staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ClusterOrderGenPage resultInit() {
		Wrap<ClusterOrder> resultWrap = new Wrap<ClusterOrder>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (ClusterOrderGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = ClusterOrderGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ClusterOrderGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (ClusterOrderGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return ClusterOrderGenPage.staticSearchPk(siteRequest_, ClusterOrderGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = ClusterOrderGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterOrderGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (ClusterOrderGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return ClusterOrderGenPage.staticSearchSolrId(siteRequest_, ClusterOrderGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	/////////////////////////
	// pageUriClusterOrder //
	/////////////////////////


	/**	 The entity pageUriClusterOrder
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriClusterOrder;

	/**	<br> The entity pageUriClusterOrder
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage&fq=entiteVar_enUS_indexed_string:pageUriClusterOrder">Find the entity pageUriClusterOrder in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriClusterOrder(Wrap<String> c);

	public String getPageUriClusterOrder() {
		return pageUriClusterOrder;
	}
	public void setPageUriClusterOrder(String o) {
		this.pageUriClusterOrder = ClusterOrderGenPage.staticSetPageUriClusterOrder(siteRequest_, o);
	}
	public static String staticSetPageUriClusterOrder(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterOrderGenPage pageUriClusterOrderInit() {
		Wrap<String> pageUriClusterOrderWrap = new Wrap<String>().var("pageUriClusterOrder");
		if(pageUriClusterOrder == null) {
			_pageUriClusterOrder(pageUriClusterOrderWrap);
			Optional.ofNullable(pageUriClusterOrderWrap.getO()).ifPresent(o -> {
				setPageUriClusterOrder(o);
			});
		}
		return (ClusterOrderGenPage)this;
	}

	public static String staticSearchPageUriClusterOrder(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriClusterOrder(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriClusterOrder(SiteRequest siteRequest_, String o) {
		return ClusterOrderGenPage.staticSearchPageUriClusterOrder(siteRequest_, ClusterOrderGenPage.staticSetPageUriClusterOrder(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ClusterOrderGenPageGen<DEV>> promiseDeepClusterOrderGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepClusterOrderGenPage();
	}

	public Future<ClusterOrderGenPageGen<DEV>> promiseDeepClusterOrderGenPage() {
		Promise<ClusterOrderGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseClusterOrderGenPage(promise2);
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

	public Future<Void> promiseClusterOrderGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListClusterOrder_Init();
				listClusterOrderInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriClusterOrderInit();
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

	@Override public Future<? extends ClusterOrderGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepClusterOrderGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestClusterOrderGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestClusterOrderGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterOrderGenPage(v);
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
	public Object obtainClusterOrderGenPage(String var) {
		ClusterOrderGenPage oClusterOrderGenPage = (ClusterOrderGenPage)this;
		switch(var) {
			case "searchListClusterOrder_":
				return oClusterOrderGenPage.searchListClusterOrder_;
			case "listClusterOrder":
				return oClusterOrderGenPage.listClusterOrder;
			case "resultCount":
				return oClusterOrderGenPage.resultCount;
			case "result":
				return oClusterOrderGenPage.result;
			case "pk":
				return oClusterOrderGenPage.pk;
			case "solrId":
				return oClusterOrderGenPage.solrId;
			case "pageUriClusterOrder":
				return oClusterOrderGenPage.pageUriClusterOrder;
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
				o = relateClusterOrderGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateClusterOrderGenPage(String var, Object val) {
		ClusterOrderGenPage oClusterOrderGenPage = (ClusterOrderGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ClusterOrderGenPage o) {
		return staticSetClusterOrderGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetClusterOrderGenPage(String entityVar, SiteRequest siteRequest_, String v, ClusterOrderGenPage o) {
		switch(entityVar) {
		case "listClusterOrder":
			return ClusterOrderGenPage.staticSetListClusterOrder(siteRequest_, v);
		case "resultCount":
			return ClusterOrderGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return ClusterOrderGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return ClusterOrderGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriClusterOrder":
			return ClusterOrderGenPage.staticSetPageUriClusterOrder(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchClusterOrderGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchClusterOrderGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listClusterOrder":
			return ClusterOrderGenPage.staticSearchListClusterOrder(siteRequest_, (JsonArray)o);
		case "resultCount":
			return ClusterOrderGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return ClusterOrderGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return ClusterOrderGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriClusterOrder":
			return ClusterOrderGenPage.staticSearchPageUriClusterOrder(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrClusterOrderGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrClusterOrderGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listClusterOrder":
			return ClusterOrderGenPage.staticSearchStrListClusterOrder(siteRequest_, (String)o);
		case "resultCount":
			return ClusterOrderGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return ClusterOrderGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return ClusterOrderGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriClusterOrder":
			return ClusterOrderGenPage.staticSearchStrPageUriClusterOrder(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqClusterOrderGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqClusterOrderGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listClusterOrder":
			return ClusterOrderGenPage.staticSearchFqListClusterOrder(siteRequest_, o);
		case "resultCount":
			return ClusterOrderGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return ClusterOrderGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return ClusterOrderGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriClusterOrder":
			return ClusterOrderGenPage.staticSearchFqPageUriClusterOrder(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "ClusterOrderGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.clusterorder.ClusterOrderGenPage";
	public static final String VAR_searchListClusterOrder_ = "searchListClusterOrder_";
	public static final String VAR_listClusterOrder = "listClusterOrder";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriClusterOrder = "pageUriClusterOrder";

	public static final String DISPLAY_NAME_searchListClusterOrder_ = "";
	public static final String DISPLAY_NAME_listClusterOrder = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriClusterOrder = "";

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
		return ClusterOrderGenPage.displayNameClusterOrderGenPage(var);
	}
	public static String displayNameClusterOrderGenPage(String var) {
		switch(var) {
		case VAR_searchListClusterOrder_:
			return DISPLAY_NAME_searchListClusterOrder_;
		case VAR_listClusterOrder:
			return DISPLAY_NAME_listClusterOrder;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriClusterOrder:
			return DISPLAY_NAME_pageUriClusterOrder;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionClusterOrderGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameClusterOrderGenPage(String var) {
		switch(var) {
		case VAR_searchListClusterOrder_:
			return "SearchList";
		case VAR_listClusterOrder:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "ClusterOrder";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriClusterOrder:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnClusterOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowClusterOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellClusterOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinClusterOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxClusterOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxClusterOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minClusterOrderGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
