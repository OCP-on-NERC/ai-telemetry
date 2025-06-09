package org.mghpcc.aitelemetry.model.baremetalnode;

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
import org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNode;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BareMetalNodeGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalNodeGenPageGen into the class BareMetalNodeGenPage. 
 * </li>
 * <h3>About the BareMetalNodeGenPage class and it's generated class BareMetalNodeGenPageGen&lt;PageLayout&gt;: </h3>extends BareMetalNodeGenPageGen
 * <p>
 * This Java class extends a generated Java class BareMetalNodeGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage">Find the class BareMetalNodeGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalNodeGenPageGen<PageLayout>
 * <p>This <code>class BareMetalNodeGenPage extends BareMetalNodeGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated BareMetalNodeGenPageGen. 
 * The generated <code>class BareMetalNodeGenPageGen extends PageLayout</code> which means that BareMetalNodeGenPage extends BareMetalNodeGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalNodeGenPage class will inherit the helpful inherited class comments from the super class BareMetalNodeGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalNodeGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class BareMetalNodeGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.baremetalnode in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BareMetalNodeGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalNodeGenPage.class);

	//////////////////////////////
	// searchListBareMetalNode_ //
	//////////////////////////////


	/**	 The entity searchListBareMetalNode_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<BareMetalNode> searchListBareMetalNode_;

	/**	<br> The entity searchListBareMetalNode_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage&fq=entiteVar_enUS_indexed_string:searchListBareMetalNode_">Find the entity searchListBareMetalNode_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListBareMetalNode_(Wrap<SearchList<BareMetalNode>> w);

	public SearchList<BareMetalNode> getSearchListBareMetalNode_() {
		return searchListBareMetalNode_;
	}

	public void setSearchListBareMetalNode_(SearchList<BareMetalNode> searchListBareMetalNode_) {
		this.searchListBareMetalNode_ = searchListBareMetalNode_;
	}
	public static SearchList<BareMetalNode> staticSetSearchListBareMetalNode_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BareMetalNodeGenPage searchListBareMetalNode_Init() {
		Wrap<SearchList<BareMetalNode>> searchListBareMetalNode_Wrap = new Wrap<SearchList<BareMetalNode>>().var("searchListBareMetalNode_");
		if(searchListBareMetalNode_ == null) {
			_searchListBareMetalNode_(searchListBareMetalNode_Wrap);
			Optional.ofNullable(searchListBareMetalNode_Wrap.getO()).ifPresent(o -> {
				setSearchListBareMetalNode_(o);
			});
		}
		return (BareMetalNodeGenPage)this;
	}

	///////////////////////
	// listBareMetalNode //
	///////////////////////


	/**	 The entity listBareMetalNode
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listBareMetalNode = new JsonArray();

	/**	<br> The entity listBareMetalNode
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage&fq=entiteVar_enUS_indexed_string:listBareMetalNode">Find the entity listBareMetalNode in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listBareMetalNode(JsonArray l);

	public JsonArray getListBareMetalNode() {
		return listBareMetalNode;
	}

	public void setListBareMetalNode(JsonArray listBareMetalNode) {
		this.listBareMetalNode = listBareMetalNode;
	}
	@JsonIgnore
	public void setListBareMetalNode(String o) {
		this.listBareMetalNode = BareMetalNodeGenPage.staticSetListBareMetalNode(siteRequest_, o);
	}
	public static JsonArray staticSetListBareMetalNode(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BareMetalNodeGenPage listBareMetalNodeInit() {
		_listBareMetalNode(listBareMetalNode);
		return (BareMetalNodeGenPage)this;
	}

	public static String staticSearchListBareMetalNode(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListBareMetalNode(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListBareMetalNode(SiteRequest siteRequest_, String o) {
		return BareMetalNodeGenPage.staticSearchListBareMetalNode(siteRequest_, BareMetalNodeGenPage.staticSetListBareMetalNode(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = BareMetalNodeGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalNodeGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (BareMetalNodeGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return BareMetalNodeGenPage.staticSearchResultCount(siteRequest_, BareMetalNodeGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected BareMetalNode result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<BareMetalNode> w);

	public BareMetalNode getResult() {
		return result;
	}

	public void setResult(BareMetalNode result) {
		this.result = result;
	}
	public static BareMetalNode staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BareMetalNodeGenPage resultInit() {
		Wrap<BareMetalNode> resultWrap = new Wrap<BareMetalNode>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (BareMetalNodeGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = BareMetalNodeGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected BareMetalNodeGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (BareMetalNodeGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return BareMetalNodeGenPage.staticSearchPk(siteRequest_, BareMetalNodeGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = BareMetalNodeGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNodeGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (BareMetalNodeGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return BareMetalNodeGenPage.staticSearchSolrId(siteRequest_, BareMetalNodeGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	//////////////////////////
	// pageUriBareMetalNode //
	//////////////////////////


	/**	 The entity pageUriBareMetalNode
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriBareMetalNode;

	/**	<br> The entity pageUriBareMetalNode
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage&fq=entiteVar_enUS_indexed_string:pageUriBareMetalNode">Find the entity pageUriBareMetalNode in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriBareMetalNode(Wrap<String> c);

	public String getPageUriBareMetalNode() {
		return pageUriBareMetalNode;
	}
	public void setPageUriBareMetalNode(String o) {
		this.pageUriBareMetalNode = BareMetalNodeGenPage.staticSetPageUriBareMetalNode(siteRequest_, o);
	}
	public static String staticSetPageUriBareMetalNode(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalNodeGenPage pageUriBareMetalNodeInit() {
		Wrap<String> pageUriBareMetalNodeWrap = new Wrap<String>().var("pageUriBareMetalNode");
		if(pageUriBareMetalNode == null) {
			_pageUriBareMetalNode(pageUriBareMetalNodeWrap);
			Optional.ofNullable(pageUriBareMetalNodeWrap.getO()).ifPresent(o -> {
				setPageUriBareMetalNode(o);
			});
		}
		return (BareMetalNodeGenPage)this;
	}

	public static String staticSearchPageUriBareMetalNode(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriBareMetalNode(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriBareMetalNode(SiteRequest siteRequest_, String o) {
		return BareMetalNodeGenPage.staticSearchPageUriBareMetalNode(siteRequest_, BareMetalNodeGenPage.staticSetPageUriBareMetalNode(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalNodeGenPageGen<DEV>> promiseDeepBareMetalNodeGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalNodeGenPage();
	}

	public Future<BareMetalNodeGenPageGen<DEV>> promiseDeepBareMetalNodeGenPage() {
		Promise<BareMetalNodeGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalNodeGenPage(promise2);
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

	public Future<Void> promiseBareMetalNodeGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListBareMetalNode_Init();
				listBareMetalNodeInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriBareMetalNodeInit();
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

	@Override public Future<? extends BareMetalNodeGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalNodeGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalNodeGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalNodeGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalNodeGenPage(v);
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
	public Object obtainBareMetalNodeGenPage(String var) {
		BareMetalNodeGenPage oBareMetalNodeGenPage = (BareMetalNodeGenPage)this;
		switch(var) {
			case "searchListBareMetalNode_":
				return oBareMetalNodeGenPage.searchListBareMetalNode_;
			case "listBareMetalNode":
				return oBareMetalNodeGenPage.listBareMetalNode;
			case "resultCount":
				return oBareMetalNodeGenPage.resultCount;
			case "result":
				return oBareMetalNodeGenPage.result;
			case "pk":
				return oBareMetalNodeGenPage.pk;
			case "solrId":
				return oBareMetalNodeGenPage.solrId;
			case "pageUriBareMetalNode":
				return oBareMetalNodeGenPage.pageUriBareMetalNode;
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
				o = relateBareMetalNodeGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalNodeGenPage(String var, Object val) {
		BareMetalNodeGenPage oBareMetalNodeGenPage = (BareMetalNodeGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, BareMetalNodeGenPage o) {
		return staticSetBareMetalNodeGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetBareMetalNodeGenPage(String entityVar, SiteRequest siteRequest_, String v, BareMetalNodeGenPage o) {
		switch(entityVar) {
		case "listBareMetalNode":
			return BareMetalNodeGenPage.staticSetListBareMetalNode(siteRequest_, v);
		case "resultCount":
			return BareMetalNodeGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return BareMetalNodeGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return BareMetalNodeGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriBareMetalNode":
			return BareMetalNodeGenPage.staticSetPageUriBareMetalNode(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalNodeGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalNodeGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listBareMetalNode":
			return BareMetalNodeGenPage.staticSearchListBareMetalNode(siteRequest_, (JsonArray)o);
		case "resultCount":
			return BareMetalNodeGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return BareMetalNodeGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return BareMetalNodeGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriBareMetalNode":
			return BareMetalNodeGenPage.staticSearchPageUriBareMetalNode(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalNodeGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalNodeGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listBareMetalNode":
			return BareMetalNodeGenPage.staticSearchStrListBareMetalNode(siteRequest_, (String)o);
		case "resultCount":
			return BareMetalNodeGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return BareMetalNodeGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return BareMetalNodeGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriBareMetalNode":
			return BareMetalNodeGenPage.staticSearchStrPageUriBareMetalNode(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalNodeGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalNodeGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listBareMetalNode":
			return BareMetalNodeGenPage.staticSearchFqListBareMetalNode(siteRequest_, o);
		case "resultCount":
			return BareMetalNodeGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return BareMetalNodeGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return BareMetalNodeGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriBareMetalNode":
			return BareMetalNodeGenPage.staticSearchFqPageUriBareMetalNode(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "BareMetalNodeGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage";
	public static final String VAR_searchListBareMetalNode_ = "searchListBareMetalNode_";
	public static final String VAR_listBareMetalNode = "listBareMetalNode";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriBareMetalNode = "pageUriBareMetalNode";

	public static final String DISPLAY_NAME_searchListBareMetalNode_ = "";
	public static final String DISPLAY_NAME_listBareMetalNode = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriBareMetalNode = "";

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
		return BareMetalNodeGenPage.displayNameBareMetalNodeGenPage(var);
	}
	public static String displayNameBareMetalNodeGenPage(String var) {
		switch(var) {
		case VAR_searchListBareMetalNode_:
			return DISPLAY_NAME_searchListBareMetalNode_;
		case VAR_listBareMetalNode:
			return DISPLAY_NAME_listBareMetalNode;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriBareMetalNode:
			return DISPLAY_NAME_pageUriBareMetalNode;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionBareMetalNodeGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameBareMetalNodeGenPage(String var) {
		switch(var) {
		case VAR_searchListBareMetalNode_:
			return "SearchList";
		case VAR_listBareMetalNode:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "BareMetalNode";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriBareMetalNode:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnBareMetalNodeGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowBareMetalNodeGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellBareMetalNodeGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinBareMetalNodeGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxBareMetalNodeGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxBareMetalNodeGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minBareMetalNodeGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
