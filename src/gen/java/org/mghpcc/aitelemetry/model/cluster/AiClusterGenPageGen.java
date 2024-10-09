package org.mghpcc.aitelemetry.model.cluster;

import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.model.BaseModelPage;
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
import org.mghpcc.aitelemetry.model.cluster.AiCluster;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these AiClusterGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class AiClusterGenPageGen into the class AiClusterGenPage. 
 * </li>
 * <h3>About the AiClusterGenPage class and it's generated class AiClusterGenPageGen&lt;BaseModelPage&gt;: </h3>extends AiClusterGenPageGen
 * <p>
 * This Java class extends a generated Java class AiClusterGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage">Find the class AiClusterGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends AiClusterGenPageGen<BaseModelPage>
 * <p>This <code>class AiClusterGenPage extends AiClusterGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated AiClusterGenPageGen. 
 * The generated <code>class AiClusterGenPageGen extends BaseModelPage</code> which means that AiClusterGenPage extends AiClusterGenPageGen which extends BaseModelPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the AiClusterGenPage class will inherit the helpful inherited class comments from the super class AiClusterGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the AiClusterGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class AiClusterGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.cluster in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class AiClusterGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(AiClusterGenPage.class);

	//////////////////////////
	// searchListAiCluster_ //
	//////////////////////////


	/**	 The entity searchListAiCluster_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<AiCluster> searchListAiCluster_;

	/**	<br> The entity searchListAiCluster_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage&fq=entiteVar_enUS_indexed_string:searchListAiCluster_">Find the entity searchListAiCluster_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListAiCluster_(Wrap<SearchList<AiCluster>> w);

	public SearchList<AiCluster> getSearchListAiCluster_() {
		return searchListAiCluster_;
	}

	public void setSearchListAiCluster_(SearchList<AiCluster> searchListAiCluster_) {
		this.searchListAiCluster_ = searchListAiCluster_;
	}
	public static SearchList<AiCluster> staticSetSearchListAiCluster_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected AiClusterGenPage searchListAiCluster_Init() {
		Wrap<SearchList<AiCluster>> searchListAiCluster_Wrap = new Wrap<SearchList<AiCluster>>().var("searchListAiCluster_");
		if(searchListAiCluster_ == null) {
			_searchListAiCluster_(searchListAiCluster_Wrap);
			Optional.ofNullable(searchListAiCluster_Wrap.getO()).ifPresent(o -> {
				setSearchListAiCluster_(o);
			});
		}
		return (AiClusterGenPage)this;
	}

	///////////////////
	// listAiCluster //
	///////////////////


	/**	 The entity listAiCluster
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listAiCluster = new JsonArray();

	/**	<br> The entity listAiCluster
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage&fq=entiteVar_enUS_indexed_string:listAiCluster">Find the entity listAiCluster in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listAiCluster(JsonArray l);

	public JsonArray getListAiCluster() {
		return listAiCluster;
	}

	public void setListAiCluster(JsonArray listAiCluster) {
		this.listAiCluster = listAiCluster;
	}
	@JsonIgnore
	public void setListAiCluster(String o) {
		this.listAiCluster = AiClusterGenPage.staticSetListAiCluster(siteRequest_, o);
	}
	public static JsonArray staticSetListAiCluster(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected AiClusterGenPage listAiClusterInit() {
		_listAiCluster(listAiCluster);
		return (AiClusterGenPage)this;
	}

	public static String staticSearchListAiCluster(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListAiCluster(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListAiCluster(SiteRequest siteRequest_, String o) {
		return AiClusterGenPage.staticSearchListAiCluster(siteRequest_, AiClusterGenPage.staticSetListAiCluster(siteRequest_, o)).toString();
	}

	////////////////////
	// aiClusterCount //
	////////////////////


	/**	 The entity aiClusterCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer aiClusterCount;

	/**	<br> The entity aiClusterCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage&fq=entiteVar_enUS_indexed_string:aiClusterCount">Find the entity aiClusterCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _aiClusterCount(Wrap<Integer> w);

	public Integer getAiClusterCount() {
		return aiClusterCount;
	}

	public void setAiClusterCount(Integer aiClusterCount) {
		this.aiClusterCount = aiClusterCount;
	}
	@JsonIgnore
	public void setAiClusterCount(String o) {
		this.aiClusterCount = AiClusterGenPage.staticSetAiClusterCount(siteRequest_, o);
	}
	public static Integer staticSetAiClusterCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected AiClusterGenPage aiClusterCountInit() {
		Wrap<Integer> aiClusterCountWrap = new Wrap<Integer>().var("aiClusterCount");
		if(aiClusterCount == null) {
			_aiClusterCount(aiClusterCountWrap);
			Optional.ofNullable(aiClusterCountWrap.getO()).ifPresent(o -> {
				setAiClusterCount(o);
			});
		}
		return (AiClusterGenPage)this;
	}

	public static Integer staticSearchAiClusterCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrAiClusterCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAiClusterCount(SiteRequest siteRequest_, String o) {
		return AiClusterGenPage.staticSearchAiClusterCount(siteRequest_, AiClusterGenPage.staticSetAiClusterCount(siteRequest_, o)).toString();
	}

	////////////////
	// aiCluster_ //
	////////////////


	/**	 The entity aiCluster_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected AiCluster aiCluster_;

	/**	<br> The entity aiCluster_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage&fq=entiteVar_enUS_indexed_string:aiCluster_">Find the entity aiCluster_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _aiCluster_(Wrap<AiCluster> w);

	public AiCluster getAiCluster_() {
		return aiCluster_;
	}

	public void setAiCluster_(AiCluster aiCluster_) {
		this.aiCluster_ = aiCluster_;
	}
	public static AiCluster staticSetAiCluster_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected AiClusterGenPage aiCluster_Init() {
		Wrap<AiCluster> aiCluster_Wrap = new Wrap<AiCluster>().var("aiCluster_");
		if(aiCluster_ == null) {
			_aiCluster_(aiCluster_Wrap);
			Optional.ofNullable(aiCluster_Wrap.getO()).ifPresent(o -> {
				setAiCluster_(o);
			});
		}
		return (AiClusterGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = AiClusterGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected AiClusterGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (AiClusterGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return AiClusterGenPage.staticSearchPk(siteRequest_, AiClusterGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = AiClusterGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiClusterGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (AiClusterGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return AiClusterGenPage.staticSearchId(siteRequest_, AiClusterGenPage.staticSetId(siteRequest_, o)).toString();
	}

	//////////////////////
	// pageUriAiCluster //
	//////////////////////


	/**	 The entity pageUriAiCluster
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriAiCluster;

	/**	<br> The entity pageUriAiCluster
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.cluster.AiClusterGenPage&fq=entiteVar_enUS_indexed_string:pageUriAiCluster">Find the entity pageUriAiCluster in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriAiCluster(Wrap<String> c);

	public String getPageUriAiCluster() {
		return pageUriAiCluster;
	}
	public void setPageUriAiCluster(String o) {
		this.pageUriAiCluster = AiClusterGenPage.staticSetPageUriAiCluster(siteRequest_, o);
	}
	public static String staticSetPageUriAiCluster(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiClusterGenPage pageUriAiClusterInit() {
		Wrap<String> pageUriAiClusterWrap = new Wrap<String>().var("pageUriAiCluster");
		if(pageUriAiCluster == null) {
			_pageUriAiCluster(pageUriAiClusterWrap);
			Optional.ofNullable(pageUriAiClusterWrap.getO()).ifPresent(o -> {
				setPageUriAiCluster(o);
			});
		}
		return (AiClusterGenPage)this;
	}

	public static String staticSearchPageUriAiCluster(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriAiCluster(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriAiCluster(SiteRequest siteRequest_, String o) {
		return AiClusterGenPage.staticSearchPageUriAiCluster(siteRequest_, AiClusterGenPage.staticSetPageUriAiCluster(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepAiClusterGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepAiClusterGenPage();
	}

	public Future<Void> promiseDeepAiClusterGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseAiClusterGenPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseModelPage(siteRequest_).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseAiClusterGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListAiCluster_Init();
				listAiClusterInit();
				aiClusterCountInit();
				aiCluster_Init();
				pkInit();
				idInit();
				pageUriAiClusterInit();
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
		return promiseDeepAiClusterGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAiClusterGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestAiClusterGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAiClusterGenPage(v);
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
	public Object obtainAiClusterGenPage(String var) {
		AiClusterGenPage oAiClusterGenPage = (AiClusterGenPage)this;
		switch(var) {
			case "searchListAiCluster_":
				return oAiClusterGenPage.searchListAiCluster_;
			case "listAiCluster":
				return oAiClusterGenPage.listAiCluster;
			case "aiClusterCount":
				return oAiClusterGenPage.aiClusterCount;
			case "aiCluster_":
				return oAiClusterGenPage.aiCluster_;
			case "pk":
				return oAiClusterGenPage.pk;
			case "id":
				return oAiClusterGenPage.id;
			case "pageUriAiCluster":
				return oAiClusterGenPage.pageUriAiCluster;
			default:
				return super.obtainBaseModelPage(var);
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
				o = relateAiClusterGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateAiClusterGenPage(String var, Object val) {
		AiClusterGenPage oAiClusterGenPage = (AiClusterGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetAiClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetAiClusterGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listAiCluster":
			return AiClusterGenPage.staticSetListAiCluster(siteRequest_, o);
		case "aiClusterCount":
			return AiClusterGenPage.staticSetAiClusterCount(siteRequest_, o);
		case "pk":
			return AiClusterGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return AiClusterGenPage.staticSetId(siteRequest_, o);
		case "pageUriAiCluster":
			return AiClusterGenPage.staticSetPageUriAiCluster(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchAiClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchAiClusterGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listAiCluster":
			return AiClusterGenPage.staticSearchListAiCluster(siteRequest_, (JsonArray)o);
		case "aiClusterCount":
			return AiClusterGenPage.staticSearchAiClusterCount(siteRequest_, (Integer)o);
		case "pk":
			return AiClusterGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return AiClusterGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriAiCluster":
			return AiClusterGenPage.staticSearchPageUriAiCluster(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrAiClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrAiClusterGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listAiCluster":
			return AiClusterGenPage.staticSearchStrListAiCluster(siteRequest_, (String)o);
		case "aiClusterCount":
			return AiClusterGenPage.staticSearchStrAiClusterCount(siteRequest_, (Integer)o);
		case "pk":
			return AiClusterGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return AiClusterGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriAiCluster":
			return AiClusterGenPage.staticSearchStrPageUriAiCluster(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqAiClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqAiClusterGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listAiCluster":
			return AiClusterGenPage.staticSearchFqListAiCluster(siteRequest_, o);
		case "aiClusterCount":
			return AiClusterGenPage.staticSearchFqAiClusterCount(siteRequest_, o);
		case "pk":
			return AiClusterGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return AiClusterGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriAiCluster":
			return AiClusterGenPage.staticSearchFqPageUriAiCluster(siteRequest_, o);
			default:
				return BaseModelPage.staticSearchFqBaseModelPage(entityVar,  siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "AiClusterGenPage";
	public static final String VAR_searchListAiCluster_ = "searchListAiCluster_";
	public static final String VAR_listAiCluster = "listAiCluster";
	public static final String VAR_aiClusterCount = "aiClusterCount";
	public static final String VAR_aiCluster_ = "aiCluster_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriAiCluster = "pageUriAiCluster";

	public static final String DISPLAY_NAME_searchListAiCluster_ = "";
	public static final String DISPLAY_NAME_listAiCluster = "";
	public static final String DISPLAY_NAME_aiClusterCount = "";
	public static final String DISPLAY_NAME_aiCluster_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriAiCluster = "";

	public static String displayNameForClass(String var) {
		return AiClusterGenPage.displayNameAiClusterGenPage(var);
	}
	public static String displayNameAiClusterGenPage(String var) {
		switch(var) {
		case VAR_searchListAiCluster_:
			return DISPLAY_NAME_searchListAiCluster_;
		case VAR_listAiCluster:
			return DISPLAY_NAME_listAiCluster;
		case VAR_aiClusterCount:
			return DISPLAY_NAME_aiClusterCount;
		case VAR_aiCluster_:
			return DISPLAY_NAME_aiCluster_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriAiCluster:
			return DISPLAY_NAME_pageUriAiCluster;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
