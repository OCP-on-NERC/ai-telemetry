package org.mghpcc.aitelemetry.model.clusterrequest;

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
import org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequest;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ClusterRequestGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ClusterRequestGenPageGen into the class ClusterRequestGenPage. 
 * </li>
 * <h3>About the ClusterRequestGenPage class and it's generated class ClusterRequestGenPageGen&lt;PageLayout&gt;: </h3>extends ClusterRequestGenPageGen
 * <p>
 * This Java class extends a generated Java class ClusterRequestGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage">Find the class ClusterRequestGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ClusterRequestGenPageGen<PageLayout>
 * <p>This <code>class ClusterRequestGenPage extends ClusterRequestGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated ClusterRequestGenPageGen. 
 * The generated <code>class ClusterRequestGenPageGen extends PageLayout</code> which means that ClusterRequestGenPage extends ClusterRequestGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ClusterRequestGenPage class will inherit the helpful inherited class comments from the super class ClusterRequestGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ClusterRequestGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ClusterRequestGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.clusterrequest in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ClusterRequestGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(ClusterRequestGenPage.class);

	///////////////////////////////
	// searchListClusterRequest_ //
	///////////////////////////////


	/**	 The entity searchListClusterRequest_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<ClusterRequest> searchListClusterRequest_;

	/**	<br> The entity searchListClusterRequest_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage&fq=entiteVar_enUS_indexed_string:searchListClusterRequest_">Find the entity searchListClusterRequest_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListClusterRequest_(Wrap<SearchList<ClusterRequest>> w);

	public SearchList<ClusterRequest> getSearchListClusterRequest_() {
		return searchListClusterRequest_;
	}

	public void setSearchListClusterRequest_(SearchList<ClusterRequest> searchListClusterRequest_) {
		this.searchListClusterRequest_ = searchListClusterRequest_;
	}
	public static SearchList<ClusterRequest> staticSetSearchListClusterRequest_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ClusterRequestGenPage searchListClusterRequest_Init() {
		Wrap<SearchList<ClusterRequest>> searchListClusterRequest_Wrap = new Wrap<SearchList<ClusterRequest>>().var("searchListClusterRequest_");
		if(searchListClusterRequest_ == null) {
			_searchListClusterRequest_(searchListClusterRequest_Wrap);
			Optional.ofNullable(searchListClusterRequest_Wrap.getO()).ifPresent(o -> {
				setSearchListClusterRequest_(o);
			});
		}
		return (ClusterRequestGenPage)this;
	}

	////////////////////////
	// listClusterRequest //
	////////////////////////


	/**	 The entity listClusterRequest
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listClusterRequest = new JsonArray();

	/**	<br> The entity listClusterRequest
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage&fq=entiteVar_enUS_indexed_string:listClusterRequest">Find the entity listClusterRequest in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listClusterRequest(JsonArray l);

	public JsonArray getListClusterRequest() {
		return listClusterRequest;
	}

	public void setListClusterRequest(JsonArray listClusterRequest) {
		this.listClusterRequest = listClusterRequest;
	}
	@JsonIgnore
	public void setListClusterRequest(String o) {
		this.listClusterRequest = ClusterRequestGenPage.staticSetListClusterRequest(siteRequest_, o);
	}
	public static JsonArray staticSetListClusterRequest(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ClusterRequestGenPage listClusterRequestInit() {
		_listClusterRequest(listClusterRequest);
		return (ClusterRequestGenPage)this;
	}

	public static String staticSearchListClusterRequest(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListClusterRequest(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListClusterRequest(SiteRequest siteRequest_, String o) {
		return ClusterRequestGenPage.staticSearchListClusterRequest(siteRequest_, ClusterRequestGenPage.staticSetListClusterRequest(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = ClusterRequestGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ClusterRequestGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (ClusterRequestGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return ClusterRequestGenPage.staticSearchResultCount(siteRequest_, ClusterRequestGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ClusterRequest result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<ClusterRequest> w);

	public ClusterRequest getResult() {
		return result;
	}

	public void setResult(ClusterRequest result) {
		this.result = result;
	}
	public static ClusterRequest staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ClusterRequestGenPage resultInit() {
		Wrap<ClusterRequest> resultWrap = new Wrap<ClusterRequest>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (ClusterRequestGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = ClusterRequestGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ClusterRequestGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (ClusterRequestGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return ClusterRequestGenPage.staticSearchPk(siteRequest_, ClusterRequestGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = ClusterRequestGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterRequestGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (ClusterRequestGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return ClusterRequestGenPage.staticSearchSolrId(siteRequest_, ClusterRequestGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriClusterRequest //
	///////////////////////////


	/**	 The entity pageUriClusterRequest
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriClusterRequest;

	/**	<br> The entity pageUriClusterRequest
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage&fq=entiteVar_enUS_indexed_string:pageUriClusterRequest">Find the entity pageUriClusterRequest in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriClusterRequest(Wrap<String> c);

	public String getPageUriClusterRequest() {
		return pageUriClusterRequest;
	}
	public void setPageUriClusterRequest(String o) {
		this.pageUriClusterRequest = ClusterRequestGenPage.staticSetPageUriClusterRequest(siteRequest_, o);
	}
	public static String staticSetPageUriClusterRequest(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterRequestGenPage pageUriClusterRequestInit() {
		Wrap<String> pageUriClusterRequestWrap = new Wrap<String>().var("pageUriClusterRequest");
		if(pageUriClusterRequest == null) {
			_pageUriClusterRequest(pageUriClusterRequestWrap);
			Optional.ofNullable(pageUriClusterRequestWrap.getO()).ifPresent(o -> {
				setPageUriClusterRequest(o);
			});
		}
		return (ClusterRequestGenPage)this;
	}

	public static String staticSearchPageUriClusterRequest(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriClusterRequest(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriClusterRequest(SiteRequest siteRequest_, String o) {
		return ClusterRequestGenPage.staticSearchPageUriClusterRequest(siteRequest_, ClusterRequestGenPage.staticSetPageUriClusterRequest(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ClusterRequestGenPageGen<DEV>> promiseDeepClusterRequestGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepClusterRequestGenPage();
	}

	public Future<ClusterRequestGenPageGen<DEV>> promiseDeepClusterRequestGenPage() {
		Promise<ClusterRequestGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseClusterRequestGenPage(promise2);
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

	public Future<Void> promiseClusterRequestGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListClusterRequest_Init();
				listClusterRequestInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriClusterRequestInit();
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

	@Override public Future<? extends ClusterRequestGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepClusterRequestGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestClusterRequestGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestClusterRequestGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterRequestGenPage(v);
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
	public Object obtainClusterRequestGenPage(String var) {
		ClusterRequestGenPage oClusterRequestGenPage = (ClusterRequestGenPage)this;
		switch(var) {
			case "searchListClusterRequest_":
				return oClusterRequestGenPage.searchListClusterRequest_;
			case "listClusterRequest":
				return oClusterRequestGenPage.listClusterRequest;
			case "resultCount":
				return oClusterRequestGenPage.resultCount;
			case "result":
				return oClusterRequestGenPage.result;
			case "pk":
				return oClusterRequestGenPage.pk;
			case "solrId":
				return oClusterRequestGenPage.solrId;
			case "pageUriClusterRequest":
				return oClusterRequestGenPage.pageUriClusterRequest;
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
				o = relateClusterRequestGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateClusterRequestGenPage(String var, Object val) {
		ClusterRequestGenPage oClusterRequestGenPage = (ClusterRequestGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ClusterRequestGenPage o) {
		return staticSetClusterRequestGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetClusterRequestGenPage(String entityVar, SiteRequest siteRequest_, String v, ClusterRequestGenPage o) {
		switch(entityVar) {
		case "listClusterRequest":
			return ClusterRequestGenPage.staticSetListClusterRequest(siteRequest_, v);
		case "resultCount":
			return ClusterRequestGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return ClusterRequestGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return ClusterRequestGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriClusterRequest":
			return ClusterRequestGenPage.staticSetPageUriClusterRequest(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchClusterRequestGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchClusterRequestGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listClusterRequest":
			return ClusterRequestGenPage.staticSearchListClusterRequest(siteRequest_, (JsonArray)o);
		case "resultCount":
			return ClusterRequestGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return ClusterRequestGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return ClusterRequestGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriClusterRequest":
			return ClusterRequestGenPage.staticSearchPageUriClusterRequest(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrClusterRequestGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrClusterRequestGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listClusterRequest":
			return ClusterRequestGenPage.staticSearchStrListClusterRequest(siteRequest_, (String)o);
		case "resultCount":
			return ClusterRequestGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return ClusterRequestGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return ClusterRequestGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriClusterRequest":
			return ClusterRequestGenPage.staticSearchStrPageUriClusterRequest(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqClusterRequestGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqClusterRequestGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listClusterRequest":
			return ClusterRequestGenPage.staticSearchFqListClusterRequest(siteRequest_, o);
		case "resultCount":
			return ClusterRequestGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return ClusterRequestGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return ClusterRequestGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriClusterRequest":
			return ClusterRequestGenPage.staticSearchFqPageUriClusterRequest(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "ClusterRequestGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.clusterrequest.ClusterRequestGenPage";
	public static final String VAR_searchListClusterRequest_ = "searchListClusterRequest_";
	public static final String VAR_listClusterRequest = "listClusterRequest";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriClusterRequest = "pageUriClusterRequest";

	public static final String DISPLAY_NAME_searchListClusterRequest_ = "";
	public static final String DISPLAY_NAME_listClusterRequest = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriClusterRequest = "";

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
		return ClusterRequestGenPage.displayNameClusterRequestGenPage(var);
	}
	public static String displayNameClusterRequestGenPage(String var) {
		switch(var) {
		case VAR_searchListClusterRequest_:
			return DISPLAY_NAME_searchListClusterRequest_;
		case VAR_listClusterRequest:
			return DISPLAY_NAME_listClusterRequest;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriClusterRequest:
			return DISPLAY_NAME_pageUriClusterRequest;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionClusterRequestGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameClusterRequestGenPage(String var) {
		switch(var) {
		case VAR_searchListClusterRequest_:
			return "SearchList";
		case VAR_listClusterRequest:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "ClusterRequest";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriClusterRequest:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnClusterRequestGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowClusterRequestGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellClusterRequestGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinClusterRequestGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxClusterRequestGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxClusterRequestGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minClusterRequestGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
