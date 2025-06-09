package org.mghpcc.aitelemetry.model.baremetalresourceclass;

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
import org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClass;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BareMetalResourceClassGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalResourceClassGenPageGen into the class BareMetalResourceClassGenPage. 
 * </li>
 * <h3>About the BareMetalResourceClassGenPage class and it's generated class BareMetalResourceClassGenPageGen&lt;PageLayout&gt;: </h3>extends BareMetalResourceClassGenPageGen
 * <p>
 * This Java class extends a generated Java class BareMetalResourceClassGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage">Find the class BareMetalResourceClassGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalResourceClassGenPageGen<PageLayout>
 * <p>This <code>class BareMetalResourceClassGenPage extends BareMetalResourceClassGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated BareMetalResourceClassGenPageGen. 
 * The generated <code>class BareMetalResourceClassGenPageGen extends PageLayout</code> which means that BareMetalResourceClassGenPage extends BareMetalResourceClassGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalResourceClassGenPage class will inherit the helpful inherited class comments from the super class BareMetalResourceClassGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalResourceClassGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class BareMetalResourceClassGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class BareMetalResourceClassGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalResourceClassGenPage.class);

	///////////////////////////////////////
	// searchListBareMetalResourceClass_ //
	///////////////////////////////////////


	/**	 The entity searchListBareMetalResourceClass_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<BareMetalResourceClass> searchListBareMetalResourceClass_;

	/**	<br> The entity searchListBareMetalResourceClass_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage&fq=entiteVar_enUS_indexed_string:searchListBareMetalResourceClass_">Find the entity searchListBareMetalResourceClass_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListBareMetalResourceClass_(Wrap<SearchList<BareMetalResourceClass>> w);

	public SearchList<BareMetalResourceClass> getSearchListBareMetalResourceClass_() {
		return searchListBareMetalResourceClass_;
	}

	public void setSearchListBareMetalResourceClass_(SearchList<BareMetalResourceClass> searchListBareMetalResourceClass_) {
		this.searchListBareMetalResourceClass_ = searchListBareMetalResourceClass_;
	}
	public static SearchList<BareMetalResourceClass> staticSetSearchListBareMetalResourceClass_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BareMetalResourceClassGenPage searchListBareMetalResourceClass_Init() {
		Wrap<SearchList<BareMetalResourceClass>> searchListBareMetalResourceClass_Wrap = new Wrap<SearchList<BareMetalResourceClass>>().var("searchListBareMetalResourceClass_");
		if(searchListBareMetalResourceClass_ == null) {
			_searchListBareMetalResourceClass_(searchListBareMetalResourceClass_Wrap);
			Optional.ofNullable(searchListBareMetalResourceClass_Wrap.getO()).ifPresent(o -> {
				setSearchListBareMetalResourceClass_(o);
			});
		}
		return (BareMetalResourceClassGenPage)this;
	}

	////////////////////////////////
	// listBareMetalResourceClass //
	////////////////////////////////


	/**	 The entity listBareMetalResourceClass
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listBareMetalResourceClass = new JsonArray();

	/**	<br> The entity listBareMetalResourceClass
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage&fq=entiteVar_enUS_indexed_string:listBareMetalResourceClass">Find the entity listBareMetalResourceClass in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listBareMetalResourceClass(JsonArray l);

	public JsonArray getListBareMetalResourceClass() {
		return listBareMetalResourceClass;
	}

	public void setListBareMetalResourceClass(JsonArray listBareMetalResourceClass) {
		this.listBareMetalResourceClass = listBareMetalResourceClass;
	}
	@JsonIgnore
	public void setListBareMetalResourceClass(String o) {
		this.listBareMetalResourceClass = BareMetalResourceClassGenPage.staticSetListBareMetalResourceClass(siteRequest_, o);
	}
	public static JsonArray staticSetListBareMetalResourceClass(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BareMetalResourceClassGenPage listBareMetalResourceClassInit() {
		_listBareMetalResourceClass(listBareMetalResourceClass);
		return (BareMetalResourceClassGenPage)this;
	}

	public static String staticSearchListBareMetalResourceClass(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListBareMetalResourceClass(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListBareMetalResourceClass(SiteRequest siteRequest_, String o) {
		return BareMetalResourceClassGenPage.staticSearchListBareMetalResourceClass(siteRequest_, BareMetalResourceClassGenPage.staticSetListBareMetalResourceClass(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = BareMetalResourceClassGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalResourceClassGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (BareMetalResourceClassGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return BareMetalResourceClassGenPage.staticSearchResultCount(siteRequest_, BareMetalResourceClassGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected BareMetalResourceClass result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<BareMetalResourceClass> w);

	public BareMetalResourceClass getResult() {
		return result;
	}

	public void setResult(BareMetalResourceClass result) {
		this.result = result;
	}
	public static BareMetalResourceClass staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BareMetalResourceClassGenPage resultInit() {
		Wrap<BareMetalResourceClass> resultWrap = new Wrap<BareMetalResourceClass>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (BareMetalResourceClassGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = BareMetalResourceClassGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected BareMetalResourceClassGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (BareMetalResourceClassGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return BareMetalResourceClassGenPage.staticSearchPk(siteRequest_, BareMetalResourceClassGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = BareMetalResourceClassGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalResourceClassGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (BareMetalResourceClassGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return BareMetalResourceClassGenPage.staticSearchSolrId(siteRequest_, BareMetalResourceClassGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	///////////////////////////////////
	// pageUriBareMetalResourceClass //
	///////////////////////////////////


	/**	 The entity pageUriBareMetalResourceClass
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriBareMetalResourceClass;

	/**	<br> The entity pageUriBareMetalResourceClass
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage&fq=entiteVar_enUS_indexed_string:pageUriBareMetalResourceClass">Find the entity pageUriBareMetalResourceClass in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriBareMetalResourceClass(Wrap<String> c);

	public String getPageUriBareMetalResourceClass() {
		return pageUriBareMetalResourceClass;
	}
	public void setPageUriBareMetalResourceClass(String o) {
		this.pageUriBareMetalResourceClass = BareMetalResourceClassGenPage.staticSetPageUriBareMetalResourceClass(siteRequest_, o);
	}
	public static String staticSetPageUriBareMetalResourceClass(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalResourceClassGenPage pageUriBareMetalResourceClassInit() {
		Wrap<String> pageUriBareMetalResourceClassWrap = new Wrap<String>().var("pageUriBareMetalResourceClass");
		if(pageUriBareMetalResourceClass == null) {
			_pageUriBareMetalResourceClass(pageUriBareMetalResourceClassWrap);
			Optional.ofNullable(pageUriBareMetalResourceClassWrap.getO()).ifPresent(o -> {
				setPageUriBareMetalResourceClass(o);
			});
		}
		return (BareMetalResourceClassGenPage)this;
	}

	public static String staticSearchPageUriBareMetalResourceClass(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriBareMetalResourceClass(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriBareMetalResourceClass(SiteRequest siteRequest_, String o) {
		return BareMetalResourceClassGenPage.staticSearchPageUriBareMetalResourceClass(siteRequest_, BareMetalResourceClassGenPage.staticSetPageUriBareMetalResourceClass(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalResourceClassGenPageGen<DEV>> promiseDeepBareMetalResourceClassGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalResourceClassGenPage();
	}

	public Future<BareMetalResourceClassGenPageGen<DEV>> promiseDeepBareMetalResourceClassGenPage() {
		Promise<BareMetalResourceClassGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalResourceClassGenPage(promise2);
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

	public Future<Void> promiseBareMetalResourceClassGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListBareMetalResourceClass_Init();
				listBareMetalResourceClassInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriBareMetalResourceClassInit();
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

	@Override public Future<? extends BareMetalResourceClassGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalResourceClassGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalResourceClassGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalResourceClassGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalResourceClassGenPage(v);
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
	public Object obtainBareMetalResourceClassGenPage(String var) {
		BareMetalResourceClassGenPage oBareMetalResourceClassGenPage = (BareMetalResourceClassGenPage)this;
		switch(var) {
			case "searchListBareMetalResourceClass_":
				return oBareMetalResourceClassGenPage.searchListBareMetalResourceClass_;
			case "listBareMetalResourceClass":
				return oBareMetalResourceClassGenPage.listBareMetalResourceClass;
			case "resultCount":
				return oBareMetalResourceClassGenPage.resultCount;
			case "result":
				return oBareMetalResourceClassGenPage.result;
			case "pk":
				return oBareMetalResourceClassGenPage.pk;
			case "solrId":
				return oBareMetalResourceClassGenPage.solrId;
			case "pageUriBareMetalResourceClass":
				return oBareMetalResourceClassGenPage.pageUriBareMetalResourceClass;
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
				o = relateBareMetalResourceClassGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalResourceClassGenPage(String var, Object val) {
		BareMetalResourceClassGenPage oBareMetalResourceClassGenPage = (BareMetalResourceClassGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, BareMetalResourceClassGenPage o) {
		return staticSetBareMetalResourceClassGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetBareMetalResourceClassGenPage(String entityVar, SiteRequest siteRequest_, String v, BareMetalResourceClassGenPage o) {
		switch(entityVar) {
		case "listBareMetalResourceClass":
			return BareMetalResourceClassGenPage.staticSetListBareMetalResourceClass(siteRequest_, v);
		case "resultCount":
			return BareMetalResourceClassGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return BareMetalResourceClassGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return BareMetalResourceClassGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriBareMetalResourceClass":
			return BareMetalResourceClassGenPage.staticSetPageUriBareMetalResourceClass(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalResourceClassGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalResourceClassGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listBareMetalResourceClass":
			return BareMetalResourceClassGenPage.staticSearchListBareMetalResourceClass(siteRequest_, (JsonArray)o);
		case "resultCount":
			return BareMetalResourceClassGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return BareMetalResourceClassGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return BareMetalResourceClassGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriBareMetalResourceClass":
			return BareMetalResourceClassGenPage.staticSearchPageUriBareMetalResourceClass(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalResourceClassGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalResourceClassGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listBareMetalResourceClass":
			return BareMetalResourceClassGenPage.staticSearchStrListBareMetalResourceClass(siteRequest_, (String)o);
		case "resultCount":
			return BareMetalResourceClassGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return BareMetalResourceClassGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return BareMetalResourceClassGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriBareMetalResourceClass":
			return BareMetalResourceClassGenPage.staticSearchStrPageUriBareMetalResourceClass(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalResourceClassGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalResourceClassGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listBareMetalResourceClass":
			return BareMetalResourceClassGenPage.staticSearchFqListBareMetalResourceClass(siteRequest_, o);
		case "resultCount":
			return BareMetalResourceClassGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return BareMetalResourceClassGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return BareMetalResourceClassGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriBareMetalResourceClass":
			return BareMetalResourceClassGenPage.staticSearchFqPageUriBareMetalResourceClass(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "BareMetalResourceClassGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalresourceclass.BareMetalResourceClassGenPage";
	public static final String VAR_searchListBareMetalResourceClass_ = "searchListBareMetalResourceClass_";
	public static final String VAR_listBareMetalResourceClass = "listBareMetalResourceClass";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriBareMetalResourceClass = "pageUriBareMetalResourceClass";

	public static final String DISPLAY_NAME_searchListBareMetalResourceClass_ = "";
	public static final String DISPLAY_NAME_listBareMetalResourceClass = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriBareMetalResourceClass = "";

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
		return BareMetalResourceClassGenPage.displayNameBareMetalResourceClassGenPage(var);
	}
	public static String displayNameBareMetalResourceClassGenPage(String var) {
		switch(var) {
		case VAR_searchListBareMetalResourceClass_:
			return DISPLAY_NAME_searchListBareMetalResourceClass_;
		case VAR_listBareMetalResourceClass:
			return DISPLAY_NAME_listBareMetalResourceClass;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriBareMetalResourceClass:
			return DISPLAY_NAME_pageUriBareMetalResourceClass;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionBareMetalResourceClassGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameBareMetalResourceClassGenPage(String var) {
		switch(var) {
		case VAR_searchListBareMetalResourceClass_:
			return "SearchList";
		case VAR_listBareMetalResourceClass:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "BareMetalResourceClass";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriBareMetalResourceClass:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnBareMetalResourceClassGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowBareMetalResourceClassGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellBareMetalResourceClassGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinBareMetalResourceClassGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxBareMetalResourceClassGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxBareMetalResourceClassGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minBareMetalResourceClassGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
