package org.mghpcc.aitelemetry.user;

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
import org.mghpcc.aitelemetry.user.SiteUser;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SiteUserGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SiteUserGenPageGen into the class SiteUserGenPage. 
 * </li>
 * <h3>About the SiteUserGenPage class and it's generated class SiteUserGenPageGen&lt;PageLayout&gt;: </h3>extends SiteUserGenPageGen
 * <p>
 * This Java class extends a generated Java class SiteUserGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage">Find the class SiteUserGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SiteUserGenPageGen<PageLayout>
 * <p>This <code>class SiteUserGenPage extends SiteUserGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated SiteUserGenPageGen. 
 * The generated <code>class SiteUserGenPageGen extends PageLayout</code> which means that SiteUserGenPage extends SiteUserGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SiteUserGenPage class will inherit the helpful inherited class comments from the super class SiteUserGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SiteUserGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class SiteUserGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.user in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.user&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class SiteUserGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(SiteUserGenPage.class);

	/////////////////////////
	// searchListSiteUser_ //
	/////////////////////////


	/**	 The entity searchListSiteUser_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SiteUser> searchListSiteUser_;

	/**	<br> The entity searchListSiteUser_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage&fq=entiteVar_enUS_indexed_string:searchListSiteUser_">Find the entity searchListSiteUser_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListSiteUser_(Wrap<SearchList<SiteUser>> w);

	public SearchList<SiteUser> getSearchListSiteUser_() {
		return searchListSiteUser_;
	}

	public void setSearchListSiteUser_(SearchList<SiteUser> searchListSiteUser_) {
		this.searchListSiteUser_ = searchListSiteUser_;
	}
	public static SearchList<SiteUser> staticSetSearchListSiteUser_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteUserGenPage searchListSiteUser_Init() {
		Wrap<SearchList<SiteUser>> searchListSiteUser_Wrap = new Wrap<SearchList<SiteUser>>().var("searchListSiteUser_");
		if(searchListSiteUser_ == null) {
			_searchListSiteUser_(searchListSiteUser_Wrap);
			Optional.ofNullable(searchListSiteUser_Wrap.getO()).ifPresent(o -> {
				setSearchListSiteUser_(o);
			});
		}
		return (SiteUserGenPage)this;
	}

	//////////////////
	// listSiteUser //
	//////////////////


	/**	 The entity listSiteUser
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listSiteUser = new JsonArray();

	/**	<br> The entity listSiteUser
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage&fq=entiteVar_enUS_indexed_string:listSiteUser">Find the entity listSiteUser in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listSiteUser(JsonArray l);

	public JsonArray getListSiteUser() {
		return listSiteUser;
	}

	public void setListSiteUser(JsonArray listSiteUser) {
		this.listSiteUser = listSiteUser;
	}
	@JsonIgnore
	public void setListSiteUser(String o) {
		this.listSiteUser = SiteUserGenPage.staticSetListSiteUser(siteRequest_, o);
	}
	public static JsonArray staticSetListSiteUser(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected SiteUserGenPage listSiteUserInit() {
		_listSiteUser(listSiteUser);
		return (SiteUserGenPage)this;
	}

	public static String staticSearchListSiteUser(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListSiteUser(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListSiteUser(SiteRequest siteRequest_, String o) {
		return SiteUserGenPage.staticSearchListSiteUser(siteRequest_, SiteUserGenPage.staticSetListSiteUser(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = SiteUserGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SiteUserGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (SiteUserGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return SiteUserGenPage.staticSearchResultCount(siteRequest_, SiteUserGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected SiteUser result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<SiteUser> w);

	public SiteUser getResult() {
		return result;
	}

	public void setResult(SiteUser result) {
		this.result = result;
	}
	public static SiteUser staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SiteUserGenPage resultInit() {
		Wrap<SiteUser> resultWrap = new Wrap<SiteUser>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (SiteUserGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = SiteUserGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SiteUserGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (SiteUserGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return SiteUserGenPage.staticSearchPk(siteRequest_, SiteUserGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = SiteUserGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUserGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (SiteUserGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return SiteUserGenPage.staticSearchSolrId(siteRequest_, SiteUserGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	/////////////////////
	// pageUriSiteUser //
	/////////////////////


	/**	 The entity pageUriSiteUser
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriSiteUser;

	/**	<br> The entity pageUriSiteUser
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUserGenPage&fq=entiteVar_enUS_indexed_string:pageUriSiteUser">Find the entity pageUriSiteUser in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriSiteUser(Wrap<String> c);

	public String getPageUriSiteUser() {
		return pageUriSiteUser;
	}
	public void setPageUriSiteUser(String o) {
		this.pageUriSiteUser = SiteUserGenPage.staticSetPageUriSiteUser(siteRequest_, o);
	}
	public static String staticSetPageUriSiteUser(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUserGenPage pageUriSiteUserInit() {
		Wrap<String> pageUriSiteUserWrap = new Wrap<String>().var("pageUriSiteUser");
		if(pageUriSiteUser == null) {
			_pageUriSiteUser(pageUriSiteUserWrap);
			Optional.ofNullable(pageUriSiteUserWrap.getO()).ifPresent(o -> {
				setPageUriSiteUser(o);
			});
		}
		return (SiteUserGenPage)this;
	}

	public static String staticSearchPageUriSiteUser(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriSiteUser(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriSiteUser(SiteRequest siteRequest_, String o) {
		return SiteUserGenPage.staticSearchPageUriSiteUser(siteRequest_, SiteUserGenPage.staticSetPageUriSiteUser(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<SiteUserGenPageGen<DEV>> promiseDeepSiteUserGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepSiteUserGenPage();
	}

	public Future<SiteUserGenPageGen<DEV>> promiseDeepSiteUserGenPage() {
		Promise<SiteUserGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseSiteUserGenPage(promise2);
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

	public Future<Void> promiseSiteUserGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListSiteUser_Init();
				listSiteUserInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriSiteUserInit();
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

	@Override public Future<? extends SiteUserGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepSiteUserGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteUserGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestSiteUserGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteUserGenPage(v);
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
	public Object obtainSiteUserGenPage(String var) {
		SiteUserGenPage oSiteUserGenPage = (SiteUserGenPage)this;
		switch(var) {
			case "searchListSiteUser_":
				return oSiteUserGenPage.searchListSiteUser_;
			case "listSiteUser":
				return oSiteUserGenPage.listSiteUser;
			case "resultCount":
				return oSiteUserGenPage.resultCount;
			case "result":
				return oSiteUserGenPage.result;
			case "pk":
				return oSiteUserGenPage.pk;
			case "solrId":
				return oSiteUserGenPage.solrId;
			case "pageUriSiteUser":
				return oSiteUserGenPage.pageUriSiteUser;
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
				o = relateSiteUserGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateSiteUserGenPage(String var, Object val) {
		SiteUserGenPage oSiteUserGenPage = (SiteUserGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SiteUserGenPage o) {
		return staticSetSiteUserGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetSiteUserGenPage(String entityVar, SiteRequest siteRequest_, String v, SiteUserGenPage o) {
		switch(entityVar) {
		case "listSiteUser":
			return SiteUserGenPage.staticSetListSiteUser(siteRequest_, v);
		case "resultCount":
			return SiteUserGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return SiteUserGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return SiteUserGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriSiteUser":
			return SiteUserGenPage.staticSetPageUriSiteUser(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchSiteUserGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchSiteUserGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listSiteUser":
			return SiteUserGenPage.staticSearchListSiteUser(siteRequest_, (JsonArray)o);
		case "resultCount":
			return SiteUserGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return SiteUserGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return SiteUserGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriSiteUser":
			return SiteUserGenPage.staticSearchPageUriSiteUser(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrSiteUserGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrSiteUserGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listSiteUser":
			return SiteUserGenPage.staticSearchStrListSiteUser(siteRequest_, (String)o);
		case "resultCount":
			return SiteUserGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return SiteUserGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return SiteUserGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriSiteUser":
			return SiteUserGenPage.staticSearchStrPageUriSiteUser(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqSiteUserGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqSiteUserGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listSiteUser":
			return SiteUserGenPage.staticSearchFqListSiteUser(siteRequest_, o);
		case "resultCount":
			return SiteUserGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return SiteUserGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return SiteUserGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriSiteUser":
			return SiteUserGenPage.staticSearchFqPageUriSiteUser(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "SiteUserGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.user.SiteUserGenPage";
	public static final String VAR_searchListSiteUser_ = "searchListSiteUser_";
	public static final String VAR_listSiteUser = "listSiteUser";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriSiteUser = "pageUriSiteUser";

	public static final String DISPLAY_NAME_searchListSiteUser_ = "";
	public static final String DISPLAY_NAME_listSiteUser = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriSiteUser = "";

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
		return SiteUserGenPage.displayNameSiteUserGenPage(var);
	}
	public static String displayNameSiteUserGenPage(String var) {
		switch(var) {
		case VAR_searchListSiteUser_:
			return DISPLAY_NAME_searchListSiteUser_;
		case VAR_listSiteUser:
			return DISPLAY_NAME_listSiteUser;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriSiteUser:
			return DISPLAY_NAME_pageUriSiteUser;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionSiteUserGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameSiteUserGenPage(String var) {
		switch(var) {
		case VAR_searchListSiteUser_:
			return "SearchList";
		case VAR_listSiteUser:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "SiteUser";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriSiteUser:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnSiteUserGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowSiteUserGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellSiteUserGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinSiteUserGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxSiteUserGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxSiteUserGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minSiteUserGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
