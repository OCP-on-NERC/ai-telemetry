package org.mghpcc.aitelemetry.page;

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
import org.mghpcc.aitelemetry.page.SitePage;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SitePageGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SitePageGenPageGen into the class SitePageGenPage. 
 * </li>
 * <h3>About the SitePageGenPage class and it's generated class SitePageGenPageGen&lt;PageLayout&gt;: </h3>extends SitePageGenPageGen
 * <p>
 * This Java class extends a generated Java class SitePageGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage">Find the class SitePageGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SitePageGenPageGen<PageLayout>
 * <p>This <code>class SitePageGenPage extends SitePageGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated SitePageGenPageGen. 
 * The generated <code>class SitePageGenPageGen extends PageLayout</code> which means that SitePageGenPage extends SitePageGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SitePageGenPage class will inherit the helpful inherited class comments from the super class SitePageGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SitePageGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class SitePageGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.page in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.page&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class SitePageGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(SitePageGenPage.class);

	/////////////////////////
	// searchListSitePage_ //
	/////////////////////////


	/**	 The entity searchListSitePage_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SitePage> searchListSitePage_;

	/**	<br> The entity searchListSitePage_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:searchListSitePage_">Find the entity searchListSitePage_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListSitePage_(Wrap<SearchList<SitePage>> w);

	public SearchList<SitePage> getSearchListSitePage_() {
		return searchListSitePage_;
	}

	public void setSearchListSitePage_(SearchList<SitePage> searchListSitePage_) {
		this.searchListSitePage_ = searchListSitePage_;
	}
	public static SearchList<SitePage> staticSetSearchListSitePage_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SitePageGenPage searchListSitePage_Init() {
		Wrap<SearchList<SitePage>> searchListSitePage_Wrap = new Wrap<SearchList<SitePage>>().var("searchListSitePage_");
		if(searchListSitePage_ == null) {
			_searchListSitePage_(searchListSitePage_Wrap);
			Optional.ofNullable(searchListSitePage_Wrap.getO()).ifPresent(o -> {
				setSearchListSitePage_(o);
			});
		}
		return (SitePageGenPage)this;
	}

	//////////////////
	// listSitePage //
	//////////////////


	/**	 The entity listSitePage
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listSitePage = new JsonArray();

	/**	<br> The entity listSitePage
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:listSitePage">Find the entity listSitePage in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listSitePage(JsonArray l);

	public JsonArray getListSitePage() {
		return listSitePage;
	}

	public void setListSitePage(JsonArray listSitePage) {
		this.listSitePage = listSitePage;
	}
	@JsonIgnore
	public void setListSitePage(String o) {
		this.listSitePage = SitePageGenPage.staticSetListSitePage(siteRequest_, o);
	}
	public static JsonArray staticSetListSitePage(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected SitePageGenPage listSitePageInit() {
		_listSitePage(listSitePage);
		return (SitePageGenPage)this;
	}

	public static String staticSearchListSitePage(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListSitePage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListSitePage(SiteRequest siteRequest_, String o) {
		return SitePageGenPage.staticSearchListSitePage(siteRequest_, SitePageGenPage.staticSetListSitePage(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = SitePageGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePageGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (SitePageGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return SitePageGenPage.staticSearchResultCount(siteRequest_, SitePageGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected SitePage result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<SitePage> w);

	public SitePage getResult() {
		return result;
	}

	public void setResult(SitePage result) {
		this.result = result;
	}
	public static SitePage staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SitePageGenPage resultInit() {
		Wrap<SitePage> resultWrap = new Wrap<SitePage>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (SitePageGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = SitePageGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePageGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (SitePageGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return SitePageGenPage.staticSearchSolrId(siteRequest_, SitePageGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	/////////////////////
	// pageUriSitePage //
	/////////////////////


	/**	 The entity pageUriSitePage
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriSitePage;

	/**	<br> The entity pageUriSitePage
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:pageUriSitePage">Find the entity pageUriSitePage in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriSitePage(Wrap<String> c);

	public String getPageUriSitePage() {
		return pageUriSitePage;
	}
	public void setPageUriSitePage(String o) {
		this.pageUriSitePage = SitePageGenPage.staticSetPageUriSitePage(siteRequest_, o);
	}
	public static String staticSetPageUriSitePage(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePageGenPage pageUriSitePageInit() {
		Wrap<String> pageUriSitePageWrap = new Wrap<String>().var("pageUriSitePage");
		if(pageUriSitePage == null) {
			_pageUriSitePage(pageUriSitePageWrap);
			Optional.ofNullable(pageUriSitePageWrap.getO()).ifPresent(o -> {
				setPageUriSitePage(o);
			});
		}
		return (SitePageGenPage)this;
	}

	public static String staticSearchPageUriSitePage(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriSitePage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriSitePage(SiteRequest siteRequest_, String o) {
		return SitePageGenPage.staticSearchPageUriSitePage(siteRequest_, SitePageGenPage.staticSetPageUriSitePage(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<SitePageGenPageGen<DEV>> promiseDeepSitePageGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepSitePageGenPage();
	}

	public Future<SitePageGenPageGen<DEV>> promiseDeepSitePageGenPage() {
		Promise<SitePageGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseSitePageGenPage(promise2);
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

	public Future<Void> promiseSitePageGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListSitePage_Init();
				listSitePageInit();
				resultCountInit();
				resultInit();
				solrIdInit();
				pageUriSitePageInit();
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

	@Override public Future<? extends SitePageGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepSitePageGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSitePageGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestSitePageGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSitePageGenPage(v);
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
	public Object obtainSitePageGenPage(String var) {
		SitePageGenPage oSitePageGenPage = (SitePageGenPage)this;
		switch(var) {
			case "searchListSitePage_":
				return oSitePageGenPage.searchListSitePage_;
			case "listSitePage":
				return oSitePageGenPage.listSitePage;
			case "resultCount":
				return oSitePageGenPage.resultCount;
			case "result":
				return oSitePageGenPage.result;
			case "solrId":
				return oSitePageGenPage.solrId;
			case "pageUriSitePage":
				return oSitePageGenPage.pageUriSitePage;
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
				o = relateSitePageGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateSitePageGenPage(String var, Object val) {
		SitePageGenPage oSitePageGenPage = (SitePageGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SitePageGenPage o) {
		return staticSetSitePageGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetSitePageGenPage(String entityVar, SiteRequest siteRequest_, String v, SitePageGenPage o) {
		switch(entityVar) {
		case "listSitePage":
			return SitePageGenPage.staticSetListSitePage(siteRequest_, v);
		case "resultCount":
			return SitePageGenPage.staticSetResultCount(siteRequest_, v);
		case "solrId":
			return SitePageGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriSitePage":
			return SitePageGenPage.staticSetPageUriSitePage(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchSitePageGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchSitePageGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listSitePage":
			return SitePageGenPage.staticSearchListSitePage(siteRequest_, (JsonArray)o);
		case "resultCount":
			return SitePageGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return SitePageGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriSitePage":
			return SitePageGenPage.staticSearchPageUriSitePage(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrSitePageGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrSitePageGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listSitePage":
			return SitePageGenPage.staticSearchStrListSitePage(siteRequest_, (String)o);
		case "resultCount":
			return SitePageGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return SitePageGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriSitePage":
			return SitePageGenPage.staticSearchStrPageUriSitePage(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqSitePageGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqSitePageGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listSitePage":
			return SitePageGenPage.staticSearchFqListSitePage(siteRequest_, o);
		case "resultCount":
			return SitePageGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "solrId":
			return SitePageGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriSitePage":
			return SitePageGenPage.staticSearchFqPageUriSitePage(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "SitePageGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.page.SitePageGenPage";
	public static final String VAR_searchListSitePage_ = "searchListSitePage_";
	public static final String VAR_listSitePage = "listSitePage";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriSitePage = "pageUriSitePage";

	public static final String DISPLAY_NAME_searchListSitePage_ = "";
	public static final String DISPLAY_NAME_listSitePage = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriSitePage = "";

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
		return SitePageGenPage.displayNameSitePageGenPage(var);
	}
	public static String displayNameSitePageGenPage(String var) {
		switch(var) {
		case VAR_searchListSitePage_:
			return DISPLAY_NAME_searchListSitePage_;
		case VAR_listSitePage:
			return DISPLAY_NAME_listSitePage;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriSitePage:
			return DISPLAY_NAME_pageUriSitePage;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionSitePageGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameSitePageGenPage(String var) {
		switch(var) {
		case VAR_searchListSitePage_:
			return "SearchList";
		case VAR_listSitePage:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "SitePage";
		case VAR_solrId:
			return "String";
		case VAR_pageUriSitePage:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnSitePageGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowSitePageGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellSitePageGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinSitePageGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxSitePageGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxSitePageGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minSitePageGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
