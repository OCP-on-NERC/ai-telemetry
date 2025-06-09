package org.mghpcc.aitelemetry.model.clustertemplate;

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
import org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplate;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ClusterTemplateGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ClusterTemplateGenPageGen into the class ClusterTemplateGenPage. 
 * </li>
 * <h3>About the ClusterTemplateGenPage class and it's generated class ClusterTemplateGenPageGen&lt;PageLayout&gt;: </h3>extends ClusterTemplateGenPageGen
 * <p>
 * This Java class extends a generated Java class ClusterTemplateGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage">Find the class ClusterTemplateGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ClusterTemplateGenPageGen<PageLayout>
 * <p>This <code>class ClusterTemplateGenPage extends ClusterTemplateGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated ClusterTemplateGenPageGen. 
 * The generated <code>class ClusterTemplateGenPageGen extends PageLayout</code> which means that ClusterTemplateGenPage extends ClusterTemplateGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ClusterTemplateGenPage class will inherit the helpful inherited class comments from the super class ClusterTemplateGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ClusterTemplateGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ClusterTemplateGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.clustertemplate in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ClusterTemplateGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(ClusterTemplateGenPage.class);

	////////////////////////////////
	// searchListClusterTemplate_ //
	////////////////////////////////


	/**	 The entity searchListClusterTemplate_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<ClusterTemplate> searchListClusterTemplate_;

	/**	<br> The entity searchListClusterTemplate_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage&fq=entiteVar_enUS_indexed_string:searchListClusterTemplate_">Find the entity searchListClusterTemplate_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListClusterTemplate_(Wrap<SearchList<ClusterTemplate>> w);

	public SearchList<ClusterTemplate> getSearchListClusterTemplate_() {
		return searchListClusterTemplate_;
	}

	public void setSearchListClusterTemplate_(SearchList<ClusterTemplate> searchListClusterTemplate_) {
		this.searchListClusterTemplate_ = searchListClusterTemplate_;
	}
	public static SearchList<ClusterTemplate> staticSetSearchListClusterTemplate_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ClusterTemplateGenPage searchListClusterTemplate_Init() {
		Wrap<SearchList<ClusterTemplate>> searchListClusterTemplate_Wrap = new Wrap<SearchList<ClusterTemplate>>().var("searchListClusterTemplate_");
		if(searchListClusterTemplate_ == null) {
			_searchListClusterTemplate_(searchListClusterTemplate_Wrap);
			Optional.ofNullable(searchListClusterTemplate_Wrap.getO()).ifPresent(o -> {
				setSearchListClusterTemplate_(o);
			});
		}
		return (ClusterTemplateGenPage)this;
	}

	/////////////////////////
	// listClusterTemplate //
	/////////////////////////


	/**	 The entity listClusterTemplate
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listClusterTemplate = new JsonArray();

	/**	<br> The entity listClusterTemplate
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage&fq=entiteVar_enUS_indexed_string:listClusterTemplate">Find the entity listClusterTemplate in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listClusterTemplate(JsonArray l);

	public JsonArray getListClusterTemplate() {
		return listClusterTemplate;
	}

	public void setListClusterTemplate(JsonArray listClusterTemplate) {
		this.listClusterTemplate = listClusterTemplate;
	}
	@JsonIgnore
	public void setListClusterTemplate(String o) {
		this.listClusterTemplate = ClusterTemplateGenPage.staticSetListClusterTemplate(siteRequest_, o);
	}
	public static JsonArray staticSetListClusterTemplate(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ClusterTemplateGenPage listClusterTemplateInit() {
		_listClusterTemplate(listClusterTemplate);
		return (ClusterTemplateGenPage)this;
	}

	public static String staticSearchListClusterTemplate(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListClusterTemplate(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListClusterTemplate(SiteRequest siteRequest_, String o) {
		return ClusterTemplateGenPage.staticSearchListClusterTemplate(siteRequest_, ClusterTemplateGenPage.staticSetListClusterTemplate(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = ClusterTemplateGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ClusterTemplateGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (ClusterTemplateGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return ClusterTemplateGenPage.staticSearchResultCount(siteRequest_, ClusterTemplateGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ClusterTemplate result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<ClusterTemplate> w);

	public ClusterTemplate getResult() {
		return result;
	}

	public void setResult(ClusterTemplate result) {
		this.result = result;
	}
	public static ClusterTemplate staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ClusterTemplateGenPage resultInit() {
		Wrap<ClusterTemplate> resultWrap = new Wrap<ClusterTemplate>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (ClusterTemplateGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = ClusterTemplateGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ClusterTemplateGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (ClusterTemplateGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return ClusterTemplateGenPage.staticSearchPk(siteRequest_, ClusterTemplateGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = ClusterTemplateGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterTemplateGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (ClusterTemplateGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return ClusterTemplateGenPage.staticSearchSolrId(siteRequest_, ClusterTemplateGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	////////////////////////////
	// pageUriClusterTemplate //
	////////////////////////////


	/**	 The entity pageUriClusterTemplate
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriClusterTemplate;

	/**	<br> The entity pageUriClusterTemplate
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage&fq=entiteVar_enUS_indexed_string:pageUriClusterTemplate">Find the entity pageUriClusterTemplate in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriClusterTemplate(Wrap<String> c);

	public String getPageUriClusterTemplate() {
		return pageUriClusterTemplate;
	}
	public void setPageUriClusterTemplate(String o) {
		this.pageUriClusterTemplate = ClusterTemplateGenPage.staticSetPageUriClusterTemplate(siteRequest_, o);
	}
	public static String staticSetPageUriClusterTemplate(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ClusterTemplateGenPage pageUriClusterTemplateInit() {
		Wrap<String> pageUriClusterTemplateWrap = new Wrap<String>().var("pageUriClusterTemplate");
		if(pageUriClusterTemplate == null) {
			_pageUriClusterTemplate(pageUriClusterTemplateWrap);
			Optional.ofNullable(pageUriClusterTemplateWrap.getO()).ifPresent(o -> {
				setPageUriClusterTemplate(o);
			});
		}
		return (ClusterTemplateGenPage)this;
	}

	public static String staticSearchPageUriClusterTemplate(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriClusterTemplate(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriClusterTemplate(SiteRequest siteRequest_, String o) {
		return ClusterTemplateGenPage.staticSearchPageUriClusterTemplate(siteRequest_, ClusterTemplateGenPage.staticSetPageUriClusterTemplate(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<ClusterTemplateGenPageGen<DEV>> promiseDeepClusterTemplateGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepClusterTemplateGenPage();
	}

	public Future<ClusterTemplateGenPageGen<DEV>> promiseDeepClusterTemplateGenPage() {
		Promise<ClusterTemplateGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseClusterTemplateGenPage(promise2);
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

	public Future<Void> promiseClusterTemplateGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListClusterTemplate_Init();
				listClusterTemplateInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriClusterTemplateInit();
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

	@Override public Future<? extends ClusterTemplateGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepClusterTemplateGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestClusterTemplateGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestClusterTemplateGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterTemplateGenPage(v);
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
	public Object obtainClusterTemplateGenPage(String var) {
		ClusterTemplateGenPage oClusterTemplateGenPage = (ClusterTemplateGenPage)this;
		switch(var) {
			case "searchListClusterTemplate_":
				return oClusterTemplateGenPage.searchListClusterTemplate_;
			case "listClusterTemplate":
				return oClusterTemplateGenPage.listClusterTemplate;
			case "resultCount":
				return oClusterTemplateGenPage.resultCount;
			case "result":
				return oClusterTemplateGenPage.result;
			case "pk":
				return oClusterTemplateGenPage.pk;
			case "solrId":
				return oClusterTemplateGenPage.solrId;
			case "pageUriClusterTemplate":
				return oClusterTemplateGenPage.pageUriClusterTemplate;
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
				o = relateClusterTemplateGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateClusterTemplateGenPage(String var, Object val) {
		ClusterTemplateGenPage oClusterTemplateGenPage = (ClusterTemplateGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ClusterTemplateGenPage o) {
		return staticSetClusterTemplateGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetClusterTemplateGenPage(String entityVar, SiteRequest siteRequest_, String v, ClusterTemplateGenPage o) {
		switch(entityVar) {
		case "listClusterTemplate":
			return ClusterTemplateGenPage.staticSetListClusterTemplate(siteRequest_, v);
		case "resultCount":
			return ClusterTemplateGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return ClusterTemplateGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return ClusterTemplateGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriClusterTemplate":
			return ClusterTemplateGenPage.staticSetPageUriClusterTemplate(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchClusterTemplateGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchClusterTemplateGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listClusterTemplate":
			return ClusterTemplateGenPage.staticSearchListClusterTemplate(siteRequest_, (JsonArray)o);
		case "resultCount":
			return ClusterTemplateGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return ClusterTemplateGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return ClusterTemplateGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriClusterTemplate":
			return ClusterTemplateGenPage.staticSearchPageUriClusterTemplate(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrClusterTemplateGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrClusterTemplateGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listClusterTemplate":
			return ClusterTemplateGenPage.staticSearchStrListClusterTemplate(siteRequest_, (String)o);
		case "resultCount":
			return ClusterTemplateGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return ClusterTemplateGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return ClusterTemplateGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriClusterTemplate":
			return ClusterTemplateGenPage.staticSearchStrPageUriClusterTemplate(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqClusterTemplateGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqClusterTemplateGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listClusterTemplate":
			return ClusterTemplateGenPage.staticSearchFqListClusterTemplate(siteRequest_, o);
		case "resultCount":
			return ClusterTemplateGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return ClusterTemplateGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return ClusterTemplateGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriClusterTemplate":
			return ClusterTemplateGenPage.staticSearchFqPageUriClusterTemplate(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "ClusterTemplateGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.clustertemplate.ClusterTemplateGenPage";
	public static final String VAR_searchListClusterTemplate_ = "searchListClusterTemplate_";
	public static final String VAR_listClusterTemplate = "listClusterTemplate";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriClusterTemplate = "pageUriClusterTemplate";

	public static final String DISPLAY_NAME_searchListClusterTemplate_ = "";
	public static final String DISPLAY_NAME_listClusterTemplate = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriClusterTemplate = "";

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
		return ClusterTemplateGenPage.displayNameClusterTemplateGenPage(var);
	}
	public static String displayNameClusterTemplateGenPage(String var) {
		switch(var) {
		case VAR_searchListClusterTemplate_:
			return DISPLAY_NAME_searchListClusterTemplate_;
		case VAR_listClusterTemplate:
			return DISPLAY_NAME_listClusterTemplate;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriClusterTemplate:
			return DISPLAY_NAME_pageUriClusterTemplate;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionClusterTemplateGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameClusterTemplateGenPage(String var) {
		switch(var) {
		case VAR_searchListClusterTemplate_:
			return "SearchList";
		case VAR_listClusterTemplate:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "ClusterTemplate";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriClusterTemplate:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnClusterTemplateGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowClusterTemplateGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellClusterTemplateGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinClusterTemplateGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxClusterTemplateGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxClusterTemplateGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minClusterTemplateGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
