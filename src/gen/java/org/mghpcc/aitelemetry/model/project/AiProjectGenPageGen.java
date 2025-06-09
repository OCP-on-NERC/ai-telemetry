package org.mghpcc.aitelemetry.model.project;

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
import org.mghpcc.aitelemetry.model.project.AiProject;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these AiProjectGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class AiProjectGenPageGen into the class AiProjectGenPage. 
 * </li>
 * <h3>About the AiProjectGenPage class and it's generated class AiProjectGenPageGen&lt;PageLayout&gt;: </h3>extends AiProjectGenPageGen
 * <p>
 * This Java class extends a generated Java class AiProjectGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage">Find the class AiProjectGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends AiProjectGenPageGen<PageLayout>
 * <p>This <code>class AiProjectGenPage extends AiProjectGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated AiProjectGenPageGen. 
 * The generated <code>class AiProjectGenPageGen extends PageLayout</code> which means that AiProjectGenPage extends AiProjectGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the AiProjectGenPage class will inherit the helpful inherited class comments from the super class AiProjectGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the AiProjectGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class AiProjectGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.project in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.project&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class AiProjectGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(AiProjectGenPage.class);

	//////////////////////////
	// searchListAiProject_ //
	//////////////////////////


	/**	 The entity searchListAiProject_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<AiProject> searchListAiProject_;

	/**	<br> The entity searchListAiProject_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage&fq=entiteVar_enUS_indexed_string:searchListAiProject_">Find the entity searchListAiProject_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListAiProject_(Wrap<SearchList<AiProject>> w);

	public SearchList<AiProject> getSearchListAiProject_() {
		return searchListAiProject_;
	}

	public void setSearchListAiProject_(SearchList<AiProject> searchListAiProject_) {
		this.searchListAiProject_ = searchListAiProject_;
	}
	public static SearchList<AiProject> staticSetSearchListAiProject_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected AiProjectGenPage searchListAiProject_Init() {
		Wrap<SearchList<AiProject>> searchListAiProject_Wrap = new Wrap<SearchList<AiProject>>().var("searchListAiProject_");
		if(searchListAiProject_ == null) {
			_searchListAiProject_(searchListAiProject_Wrap);
			Optional.ofNullable(searchListAiProject_Wrap.getO()).ifPresent(o -> {
				setSearchListAiProject_(o);
			});
		}
		return (AiProjectGenPage)this;
	}

	///////////////////
	// listAiProject //
	///////////////////


	/**	 The entity listAiProject
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listAiProject = new JsonArray();

	/**	<br> The entity listAiProject
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage&fq=entiteVar_enUS_indexed_string:listAiProject">Find the entity listAiProject in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listAiProject(JsonArray l);

	public JsonArray getListAiProject() {
		return listAiProject;
	}

	public void setListAiProject(JsonArray listAiProject) {
		this.listAiProject = listAiProject;
	}
	@JsonIgnore
	public void setListAiProject(String o) {
		this.listAiProject = AiProjectGenPage.staticSetListAiProject(siteRequest_, o);
	}
	public static JsonArray staticSetListAiProject(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected AiProjectGenPage listAiProjectInit() {
		_listAiProject(listAiProject);
		return (AiProjectGenPage)this;
	}

	public static String staticSearchListAiProject(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListAiProject(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListAiProject(SiteRequest siteRequest_, String o) {
		return AiProjectGenPage.staticSearchListAiProject(siteRequest_, AiProjectGenPage.staticSetListAiProject(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = AiProjectGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected AiProjectGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (AiProjectGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return AiProjectGenPage.staticSearchResultCount(siteRequest_, AiProjectGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected AiProject result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<AiProject> w);

	public AiProject getResult() {
		return result;
	}

	public void setResult(AiProject result) {
		this.result = result;
	}
	public static AiProject staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected AiProjectGenPage resultInit() {
		Wrap<AiProject> resultWrap = new Wrap<AiProject>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (AiProjectGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = AiProjectGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected AiProjectGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (AiProjectGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return AiProjectGenPage.staticSearchPk(siteRequest_, AiProjectGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = AiProjectGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiProjectGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (AiProjectGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return AiProjectGenPage.staticSearchSolrId(siteRequest_, AiProjectGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	//////////////////////
	// pageUriAiProject //
	//////////////////////


	/**	 The entity pageUriAiProject
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriAiProject;

	/**	<br> The entity pageUriAiProject
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.project.AiProjectGenPage&fq=entiteVar_enUS_indexed_string:pageUriAiProject">Find the entity pageUriAiProject in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriAiProject(Wrap<String> c);

	public String getPageUriAiProject() {
		return pageUriAiProject;
	}
	public void setPageUriAiProject(String o) {
		this.pageUriAiProject = AiProjectGenPage.staticSetPageUriAiProject(siteRequest_, o);
	}
	public static String staticSetPageUriAiProject(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiProjectGenPage pageUriAiProjectInit() {
		Wrap<String> pageUriAiProjectWrap = new Wrap<String>().var("pageUriAiProject");
		if(pageUriAiProject == null) {
			_pageUriAiProject(pageUriAiProjectWrap);
			Optional.ofNullable(pageUriAiProjectWrap.getO()).ifPresent(o -> {
				setPageUriAiProject(o);
			});
		}
		return (AiProjectGenPage)this;
	}

	public static String staticSearchPageUriAiProject(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriAiProject(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriAiProject(SiteRequest siteRequest_, String o) {
		return AiProjectGenPage.staticSearchPageUriAiProject(siteRequest_, AiProjectGenPage.staticSetPageUriAiProject(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<AiProjectGenPageGen<DEV>> promiseDeepAiProjectGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepAiProjectGenPage();
	}

	public Future<AiProjectGenPageGen<DEV>> promiseDeepAiProjectGenPage() {
		Promise<AiProjectGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseAiProjectGenPage(promise2);
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

	public Future<Void> promiseAiProjectGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListAiProject_Init();
				listAiProjectInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriAiProjectInit();
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

	@Override public Future<? extends AiProjectGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepAiProjectGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAiProjectGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestAiProjectGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAiProjectGenPage(v);
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
	public Object obtainAiProjectGenPage(String var) {
		AiProjectGenPage oAiProjectGenPage = (AiProjectGenPage)this;
		switch(var) {
			case "searchListAiProject_":
				return oAiProjectGenPage.searchListAiProject_;
			case "listAiProject":
				return oAiProjectGenPage.listAiProject;
			case "resultCount":
				return oAiProjectGenPage.resultCount;
			case "result":
				return oAiProjectGenPage.result;
			case "pk":
				return oAiProjectGenPage.pk;
			case "solrId":
				return oAiProjectGenPage.solrId;
			case "pageUriAiProject":
				return oAiProjectGenPage.pageUriAiProject;
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
				o = relateAiProjectGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateAiProjectGenPage(String var, Object val) {
		AiProjectGenPage oAiProjectGenPage = (AiProjectGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, AiProjectGenPage o) {
		return staticSetAiProjectGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetAiProjectGenPage(String entityVar, SiteRequest siteRequest_, String v, AiProjectGenPage o) {
		switch(entityVar) {
		case "listAiProject":
			return AiProjectGenPage.staticSetListAiProject(siteRequest_, v);
		case "resultCount":
			return AiProjectGenPage.staticSetResultCount(siteRequest_, v);
		case "pk":
			return AiProjectGenPage.staticSetPk(siteRequest_, v);
		case "solrId":
			return AiProjectGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriAiProject":
			return AiProjectGenPage.staticSetPageUriAiProject(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchAiProjectGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchAiProjectGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listAiProject":
			return AiProjectGenPage.staticSearchListAiProject(siteRequest_, (JsonArray)o);
		case "resultCount":
			return AiProjectGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return AiProjectGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return AiProjectGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriAiProject":
			return AiProjectGenPage.staticSearchPageUriAiProject(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrAiProjectGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrAiProjectGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listAiProject":
			return AiProjectGenPage.staticSearchStrListAiProject(siteRequest_, (String)o);
		case "resultCount":
			return AiProjectGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return AiProjectGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return AiProjectGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriAiProject":
			return AiProjectGenPage.staticSearchStrPageUriAiProject(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqAiProjectGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqAiProjectGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listAiProject":
			return AiProjectGenPage.staticSearchFqListAiProject(siteRequest_, o);
		case "resultCount":
			return AiProjectGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return AiProjectGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return AiProjectGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriAiProject":
			return AiProjectGenPage.staticSearchFqPageUriAiProject(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "AiProjectGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.project.AiProjectGenPage";
	public static final String VAR_searchListAiProject_ = "searchListAiProject_";
	public static final String VAR_listAiProject = "listAiProject";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriAiProject = "pageUriAiProject";

	public static final String DISPLAY_NAME_searchListAiProject_ = "";
	public static final String DISPLAY_NAME_listAiProject = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriAiProject = "";

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
		return AiProjectGenPage.displayNameAiProjectGenPage(var);
	}
	public static String displayNameAiProjectGenPage(String var) {
		switch(var) {
		case VAR_searchListAiProject_:
			return DISPLAY_NAME_searchListAiProject_;
		case VAR_listAiProject:
			return DISPLAY_NAME_listAiProject;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriAiProject:
			return DISPLAY_NAME_pageUriAiProject;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionAiProjectGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameAiProjectGenPage(String var) {
		switch(var) {
		case VAR_searchListAiProject_:
			return "SearchList";
		case VAR_listAiProject:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "AiProject";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriAiProject:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnAiProjectGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowAiProjectGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellAiProjectGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinAiProjectGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxAiProjectGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxAiProjectGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minAiProjectGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
