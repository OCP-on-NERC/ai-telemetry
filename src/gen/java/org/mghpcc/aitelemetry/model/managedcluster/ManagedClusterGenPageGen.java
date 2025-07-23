package org.mghpcc.aitelemetry.model.managedcluster;

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
import org.mghpcc.aitelemetry.model.managedcluster.ManagedCluster;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ManagedClusterGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ManagedClusterGenPageGen into the class ManagedClusterGenPage. 
 * </li>
 * <h3>About the ManagedClusterGenPage class and it's generated class ManagedClusterGenPageGen&lt;PageLayout&gt;: </h3>extends ManagedClusterGenPageGen
 * <p>
 * This Java class extends a generated Java class ManagedClusterGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage">Find the class ManagedClusterGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ManagedClusterGenPageGen<PageLayout>
 * <p>This <code>class ManagedClusterGenPage extends ManagedClusterGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated ManagedClusterGenPageGen. 
 * The generated <code>class ManagedClusterGenPageGen extends PageLayout</code> which means that ManagedClusterGenPage extends ManagedClusterGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ManagedClusterGenPage class will inherit the helpful inherited class comments from the super class ManagedClusterGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ManagedClusterGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ManagedClusterGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.managedcluster in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ManagedClusterGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(ManagedClusterGenPage.class);

	///////////////////////////////
	// searchListManagedCluster_ //
	///////////////////////////////


	/**	 The entity searchListManagedCluster_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<ManagedCluster> searchListManagedCluster_;

	/**	<br> The entity searchListManagedCluster_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage&fq=entiteVar_enUS_indexed_string:searchListManagedCluster_">Find the entity searchListManagedCluster_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListManagedCluster_(Wrap<SearchList<ManagedCluster>> w);

	public SearchList<ManagedCluster> getSearchListManagedCluster_() {
		return searchListManagedCluster_;
	}

	public void setSearchListManagedCluster_(SearchList<ManagedCluster> searchListManagedCluster_) {
		this.searchListManagedCluster_ = searchListManagedCluster_;
	}
	public static SearchList<ManagedCluster> staticSetSearchListManagedCluster_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ManagedClusterGenPage searchListManagedCluster_Init() {
		Wrap<SearchList<ManagedCluster>> searchListManagedCluster_Wrap = new Wrap<SearchList<ManagedCluster>>().var("searchListManagedCluster_");
		if(searchListManagedCluster_ == null) {
			_searchListManagedCluster_(searchListManagedCluster_Wrap);
			Optional.ofNullable(searchListManagedCluster_Wrap.getO()).ifPresent(o -> {
				setSearchListManagedCluster_(o);
			});
		}
		return (ManagedClusterGenPage)this;
	}

	////////////////////////
	// listManagedCluster //
	////////////////////////


	/**	 The entity listManagedCluster
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listManagedCluster = new JsonArray();

	/**	<br> The entity listManagedCluster
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage&fq=entiteVar_enUS_indexed_string:listManagedCluster">Find the entity listManagedCluster in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listManagedCluster(JsonArray l);

	public JsonArray getListManagedCluster() {
		return listManagedCluster;
	}

	public void setListManagedCluster(JsonArray listManagedCluster) {
		this.listManagedCluster = listManagedCluster;
	}
	@JsonIgnore
	public void setListManagedCluster(String o) {
		this.listManagedCluster = ManagedClusterGenPage.staticSetListManagedCluster(siteRequest_, o);
	}
	public static JsonArray staticSetListManagedCluster(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ManagedClusterGenPage listManagedClusterInit() {
		_listManagedCluster(listManagedCluster);
		return (ManagedClusterGenPage)this;
	}

	public static String staticSearchListManagedCluster(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListManagedCluster(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListManagedCluster(SiteRequest siteRequest_, String o) {
		return ManagedClusterGenPage.staticSearchListManagedCluster(siteRequest_, ManagedClusterGenPage.staticSetListManagedCluster(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = ManagedClusterGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ManagedClusterGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (ManagedClusterGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return ManagedClusterGenPage.staticSearchResultCount(siteRequest_, ManagedClusterGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ManagedCluster result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<ManagedCluster> w);

	public ManagedCluster getResult() {
		return result;
	}

	public void setResult(ManagedCluster result) {
		this.result = result;
	}
	public static ManagedCluster staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ManagedClusterGenPage resultInit() {
		Wrap<ManagedCluster> resultWrap = new Wrap<ManagedCluster>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (ManagedClusterGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = ManagedClusterGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ManagedClusterGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (ManagedClusterGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return ManagedClusterGenPage.staticSearchPk(siteRequest_, ManagedClusterGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = ManagedClusterGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ManagedClusterGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (ManagedClusterGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return ManagedClusterGenPage.staticSearchSolrId(siteRequest_, ManagedClusterGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriManagedCluster //
	///////////////////////////


	/**	 The entity pageUriManagedCluster
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriManagedCluster;

	/**	<br> The entity pageUriManagedCluster
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage&fq=entiteVar_enUS_indexed_string:pageUriManagedCluster">Find the entity pageUriManagedCluster in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriManagedCluster(Wrap<String> c);

	public String getPageUriManagedCluster() {
		return pageUriManagedCluster;
	}
	public void setPageUriManagedCluster(String o) {
		this.pageUriManagedCluster = ManagedClusterGenPage.staticSetPageUriManagedCluster(siteRequest_, o);
	}
	public static String staticSetPageUriManagedCluster(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ManagedClusterGenPage pageUriManagedClusterInit() {
		Wrap<String> pageUriManagedClusterWrap = new Wrap<String>().var("pageUriManagedCluster");
		if(pageUriManagedCluster == null) {
			_pageUriManagedCluster(pageUriManagedClusterWrap);
			Optional.ofNullable(pageUriManagedClusterWrap.getO()).ifPresent(o -> {
				setPageUriManagedCluster(o);
			});
		}
		return (ManagedClusterGenPage)this;
	}

	public static String staticSearchPageUriManagedCluster(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriManagedCluster(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriManagedCluster(SiteRequest siteRequest_, String o) {
		return ManagedClusterGenPage.staticSearchPageUriManagedCluster(siteRequest_, ManagedClusterGenPage.staticSetPageUriManagedCluster(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ManagedClusterGenPageGen<DEV>> promiseDeepManagedClusterGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepManagedClusterGenPage();
	}

	public Future<ManagedClusterGenPageGen<DEV>> promiseDeepManagedClusterGenPage() {
		Promise<ManagedClusterGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseManagedClusterGenPage(promise2);
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

	public Future<Void> promiseManagedClusterGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListManagedCluster_Init();
				listManagedClusterInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriManagedClusterInit();
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

	@Override public Future<? extends ManagedClusterGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepManagedClusterGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestManagedClusterGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestManagedClusterGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainManagedClusterGenPage(v);
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
	public Object obtainManagedClusterGenPage(String var) {
		ManagedClusterGenPage oManagedClusterGenPage = (ManagedClusterGenPage)this;
		switch(var) {
			case "searchListManagedCluster_":
				return oManagedClusterGenPage.searchListManagedCluster_;
			case "listManagedCluster":
				return oManagedClusterGenPage.listManagedCluster;
			case "resultCount":
				return oManagedClusterGenPage.resultCount;
			case "result":
				return oManagedClusterGenPage.result;
			case "pk":
				return oManagedClusterGenPage.pk;
			case "solrId":
				return oManagedClusterGenPage.solrId;
			case "pageUriManagedCluster":
				return oManagedClusterGenPage.pageUriManagedCluster;
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
				o = relateManagedClusterGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateManagedClusterGenPage(String var, Object val) {
		ManagedClusterGenPage oManagedClusterGenPage = (ManagedClusterGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ManagedClusterGenPage o) {
		return staticSetManagedClusterGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetManagedClusterGenPage(String entityVar, SiteRequest siteRequest_, String v, ManagedClusterGenPage o) {
		switch(entityVar) {
		case "listManagedCluster":
			return ManagedClusterGenPage.staticSetListManagedCluster(siteRequest_, v);
		case "resultCount":
			return ManagedClusterGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return ManagedClusterGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return ManagedClusterGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriManagedCluster":
			return ManagedClusterGenPage.staticSetPageUriManagedCluster(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchManagedClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchManagedClusterGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listManagedCluster":
			return ManagedClusterGenPage.staticSearchListManagedCluster(siteRequest_, (JsonArray)o);
		case "resultCount":
			return ManagedClusterGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return ManagedClusterGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return ManagedClusterGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriManagedCluster":
			return ManagedClusterGenPage.staticSearchPageUriManagedCluster(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrManagedClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrManagedClusterGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listManagedCluster":
			return ManagedClusterGenPage.staticSearchStrListManagedCluster(siteRequest_, (String)o);
		case "resultCount":
			return ManagedClusterGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return ManagedClusterGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return ManagedClusterGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriManagedCluster":
			return ManagedClusterGenPage.staticSearchStrPageUriManagedCluster(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqManagedClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqManagedClusterGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listManagedCluster":
			return ManagedClusterGenPage.staticSearchFqListManagedCluster(siteRequest_, o);
		case "resultCount":
			return ManagedClusterGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return ManagedClusterGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return ManagedClusterGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriManagedCluster":
			return ManagedClusterGenPage.staticSearchFqPageUriManagedCluster(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "ManagedClusterGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.managedcluster.ManagedClusterGenPage";
	public static final String CLASS_AUTH_RESOURCE = "";
	public static final String VAR_searchListManagedCluster_ = "searchListManagedCluster_";
	public static final String VAR_listManagedCluster = "listManagedCluster";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriManagedCluster = "pageUriManagedCluster";

	public static final String DISPLAY_NAME_searchListManagedCluster_ = "";
	public static final String DISPLAY_NAME_listManagedCluster = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriManagedCluster = "";

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
		return ManagedClusterGenPage.displayNameManagedClusterGenPage(var);
	}
	public static String displayNameManagedClusterGenPage(String var) {
		switch(var) {
		case VAR_searchListManagedCluster_:
			return DISPLAY_NAME_searchListManagedCluster_;
		case VAR_listManagedCluster:
			return DISPLAY_NAME_listManagedCluster;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriManagedCluster:
			return DISPLAY_NAME_pageUriManagedCluster;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionManagedClusterGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameManagedClusterGenPage(String var) {
		switch(var) {
		case VAR_searchListManagedCluster_:
			return "SearchList";
		case VAR_listManagedCluster:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "ManagedCluster";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriManagedCluster:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnManagedClusterGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowManagedClusterGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellManagedClusterGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinManagedClusterGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxManagedClusterGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxManagedClusterGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minManagedClusterGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
