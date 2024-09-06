package org.mghpcc.aitelemetry.page;

import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.result.BaseResultPage;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SitePageGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SitePageGenPageGen into the class SitePageGenPage. 
 * </li>
 * <h3>About the SitePageGenPage class and it's generated class SitePageGenPageGen&lt;BaseResultPage&gt;: </h3>extends SitePageGenPageGen
 * <p>
 * This Java class extends a generated Java class SitePageGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage">Find the class SitePageGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SitePageGenPageGen<BaseResultPage>
 * <p>This <code>class SitePageGenPage extends SitePageGenPageGen&lt;BaseResultPage&gt;</code>, which means it extends a newly generated SitePageGenPageGen. 
 * The generated <code>class SitePageGenPageGen extends BaseResultPage</code> which means that SitePageGenPage extends SitePageGenPageGen which extends BaseResultPage. 
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
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.page in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.page&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class SitePageGenPageGen<DEV> extends BaseResultPage {
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:searchListSitePage_">Find the entity searchListSitePage_ in Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listSitePage = new JsonArray();

	/**	<br> The entity listSitePage
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:listSitePage">Find the entity listSitePage in Solr</a>
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

	///////////////////
	// sitePageCount //
	///////////////////


	/**	 The entity sitePageCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sitePageCount;

	/**	<br> The entity sitePageCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:sitePageCount">Find the entity sitePageCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sitePageCount(Wrap<Integer> w);

	public Integer getSitePageCount() {
		return sitePageCount;
	}

	public void setSitePageCount(Integer sitePageCount) {
		this.sitePageCount = sitePageCount;
	}
	@JsonIgnore
	public void setSitePageCount(String o) {
		this.sitePageCount = SitePageGenPage.staticSetSitePageCount(siteRequest_, o);
	}
	public static Integer staticSetSitePageCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePageGenPage sitePageCountInit() {
		Wrap<Integer> sitePageCountWrap = new Wrap<Integer>().var("sitePageCount");
		if(sitePageCount == null) {
			_sitePageCount(sitePageCountWrap);
			Optional.ofNullable(sitePageCountWrap.getO()).ifPresent(o -> {
				setSitePageCount(o);
			});
		}
		return (SitePageGenPage)this;
	}

	public static Integer staticSearchSitePageCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrSitePageCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSitePageCount(SiteRequest siteRequest_, String o) {
		return SitePageGenPage.staticSearchSitePageCount(siteRequest_, SitePageGenPage.staticSetSitePageCount(siteRequest_, o)).toString();
	}

	///////////////
	// sitePage_ //
	///////////////


	/**	 The entity sitePage_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected SitePage sitePage_;

	/**	<br> The entity sitePage_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:sitePage_">Find the entity sitePage_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sitePage_(Wrap<SitePage> w);

	public SitePage getSitePage_() {
		return sitePage_;
	}

	public void setSitePage_(SitePage sitePage_) {
		this.sitePage_ = sitePage_;
	}
	public static SitePage staticSetSitePage_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected SitePageGenPage sitePage_Init() {
		Wrap<SitePage> sitePage_Wrap = new Wrap<SitePage>().var("sitePage_");
		if(sitePage_ == null) {
			_sitePage_(sitePage_Wrap);
			Optional.ofNullable(sitePage_Wrap.getO()).ifPresent(o -> {
				setSitePage_(o);
			});
		}
		return (SitePageGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = SitePageGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePageGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (SitePageGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return SitePageGenPage.staticSearchId(siteRequest_, SitePageGenPage.staticSetId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePageGenPage&fq=entiteVar_enUS_indexed_string:pageUriSitePage">Find the entity pageUriSitePage in Solr</a>
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

	public Future<Void> promiseDeepSitePageGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepSitePageGenPage();
	}

	public Future<Void> promiseDeepSitePageGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseSitePageGenPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseResultPage(siteRequest_).onSuccess(b -> {
				promise.complete();
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
				sitePageCountInit();
				sitePage_Init();
				idInit();
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

	@Override public Future<Void> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepSitePageGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSitePageGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseResultPage(siteRequest_);
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
			case "sitePageCount":
				return oSitePageGenPage.sitePageCount;
			case "sitePage_":
				return oSitePageGenPage.sitePage_;
			case "id":
				return oSitePageGenPage.id;
			case "pageUriSitePage":
				return oSitePageGenPage.pageUriSitePage;
			default:
				return super.obtainBaseResultPage(var);
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
				return super.relateBaseResultPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetSitePageGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSitePageGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listSitePage":
			return SitePageGenPage.staticSetListSitePage(siteRequest_, o);
		case "sitePageCount":
			return SitePageGenPage.staticSetSitePageCount(siteRequest_, o);
		case "id":
			return SitePageGenPage.staticSetId(siteRequest_, o);
		case "pageUriSitePage":
			return SitePageGenPage.staticSetPageUriSitePage(siteRequest_, o);
			default:
				return BaseResultPage.staticSetBaseResultPage(entityVar,  siteRequest_, o);
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
		case "sitePageCount":
			return SitePageGenPage.staticSearchSitePageCount(siteRequest_, (Integer)o);
		case "id":
			return SitePageGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriSitePage":
			return SitePageGenPage.staticSearchPageUriSitePage(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchBaseResultPage(entityVar,  siteRequest_, o);
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
		case "sitePageCount":
			return SitePageGenPage.staticSearchStrSitePageCount(siteRequest_, (Integer)o);
		case "id":
			return SitePageGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriSitePage":
			return SitePageGenPage.staticSearchStrPageUriSitePage(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchStrBaseResultPage(entityVar,  siteRequest_, o);
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
		case "sitePageCount":
			return SitePageGenPage.staticSearchFqSitePageCount(siteRequest_, o);
		case "id":
			return SitePageGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriSitePage":
			return SitePageGenPage.staticSearchFqPageUriSitePage(siteRequest_, o);
			default:
				return BaseResultPage.staticSearchFqBaseResultPage(entityVar,  siteRequest_, o);
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
	public static final String VAR_searchListSitePage_ = "searchListSitePage_";
	public static final String VAR_listSitePage = "listSitePage";
	public static final String VAR_sitePageCount = "sitePageCount";
	public static final String VAR_sitePage_ = "sitePage_";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriSitePage = "pageUriSitePage";

	public static final String DISPLAY_NAME_searchListSitePage_ = "";
	public static final String DISPLAY_NAME_listSitePage = "";
	public static final String DISPLAY_NAME_sitePageCount = "";
	public static final String DISPLAY_NAME_sitePage_ = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriSitePage = "";

	public static String displayNameForClass(String var) {
		return SitePageGenPage.displayNameSitePageGenPage(var);
	}
	public static String displayNameSitePageGenPage(String var) {
		switch(var) {
		case VAR_searchListSitePage_:
			return DISPLAY_NAME_searchListSitePage_;
		case VAR_listSitePage:
			return DISPLAY_NAME_listSitePage;
		case VAR_sitePageCount:
			return DISPLAY_NAME_sitePageCount;
		case VAR_sitePage_:
			return DISPLAY_NAME_sitePage_;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriSitePage:
			return DISPLAY_NAME_pageUriSitePage;
		default:
			return BaseResultPage.displayNameBaseResultPage(var);
		}
	}
}
