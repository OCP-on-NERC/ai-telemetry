package org.mghpcc.aitelemetry.model.node;

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
import org.mghpcc.aitelemetry.model.node.AiNode;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these AiNodeGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class AiNodeGenPageGen into the class AiNodeGenPage. 
 * </li>
 * <h3>About the AiNodeGenPage class and it's generated class AiNodeGenPageGen&lt;BaseModelPage&gt;: </h3>extends AiNodeGenPageGen
 * <p>
 * This Java class extends a generated Java class AiNodeGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage">Find the class AiNodeGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends AiNodeGenPageGen<BaseModelPage>
 * <p>This <code>class AiNodeGenPage extends AiNodeGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated AiNodeGenPageGen. 
 * The generated <code>class AiNodeGenPageGen extends BaseModelPage</code> which means that AiNodeGenPage extends AiNodeGenPageGen which extends BaseModelPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the AiNodeGenPage class will inherit the helpful inherited class comments from the super class AiNodeGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the AiNodeGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class AiNodeGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.node in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.node&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class AiNodeGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(AiNodeGenPage.class);

	///////////////////////
	// searchListAiNode_ //
	///////////////////////


	/**	 The entity searchListAiNode_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<AiNode> searchListAiNode_;

	/**	<br> The entity searchListAiNode_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage&fq=entiteVar_enUS_indexed_string:searchListAiNode_">Find the entity searchListAiNode_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListAiNode_(Wrap<SearchList<AiNode>> w);

	public SearchList<AiNode> getSearchListAiNode_() {
		return searchListAiNode_;
	}

	public void setSearchListAiNode_(SearchList<AiNode> searchListAiNode_) {
		this.searchListAiNode_ = searchListAiNode_;
	}
	public static SearchList<AiNode> staticSetSearchListAiNode_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected AiNodeGenPage searchListAiNode_Init() {
		Wrap<SearchList<AiNode>> searchListAiNode_Wrap = new Wrap<SearchList<AiNode>>().var("searchListAiNode_");
		if(searchListAiNode_ == null) {
			_searchListAiNode_(searchListAiNode_Wrap);
			Optional.ofNullable(searchListAiNode_Wrap.getO()).ifPresent(o -> {
				setSearchListAiNode_(o);
			});
		}
		return (AiNodeGenPage)this;
	}

	////////////////
	// listAiNode //
	////////////////


	/**	 The entity listAiNode
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listAiNode = new JsonArray();

	/**	<br> The entity listAiNode
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage&fq=entiteVar_enUS_indexed_string:listAiNode">Find the entity listAiNode in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listAiNode(JsonArray l);

	public JsonArray getListAiNode() {
		return listAiNode;
	}

	public void setListAiNode(JsonArray listAiNode) {
		this.listAiNode = listAiNode;
	}
	@JsonIgnore
	public void setListAiNode(String o) {
		this.listAiNode = AiNodeGenPage.staticSetListAiNode(siteRequest_, o);
	}
	public static JsonArray staticSetListAiNode(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected AiNodeGenPage listAiNodeInit() {
		_listAiNode(listAiNode);
		return (AiNodeGenPage)this;
	}

	public static String staticSearchListAiNode(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListAiNode(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListAiNode(SiteRequest siteRequest_, String o) {
		return AiNodeGenPage.staticSearchListAiNode(siteRequest_, AiNodeGenPage.staticSetListAiNode(siteRequest_, o)).toString();
	}

	/////////////////
	// aiNodeCount //
	/////////////////


	/**	 The entity aiNodeCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer aiNodeCount;

	/**	<br> The entity aiNodeCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage&fq=entiteVar_enUS_indexed_string:aiNodeCount">Find the entity aiNodeCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _aiNodeCount(Wrap<Integer> w);

	public Integer getAiNodeCount() {
		return aiNodeCount;
	}

	public void setAiNodeCount(Integer aiNodeCount) {
		this.aiNodeCount = aiNodeCount;
	}
	@JsonIgnore
	public void setAiNodeCount(String o) {
		this.aiNodeCount = AiNodeGenPage.staticSetAiNodeCount(siteRequest_, o);
	}
	public static Integer staticSetAiNodeCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected AiNodeGenPage aiNodeCountInit() {
		Wrap<Integer> aiNodeCountWrap = new Wrap<Integer>().var("aiNodeCount");
		if(aiNodeCount == null) {
			_aiNodeCount(aiNodeCountWrap);
			Optional.ofNullable(aiNodeCountWrap.getO()).ifPresent(o -> {
				setAiNodeCount(o);
			});
		}
		return (AiNodeGenPage)this;
	}

	public static Integer staticSearchAiNodeCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrAiNodeCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAiNodeCount(SiteRequest siteRequest_, String o) {
		return AiNodeGenPage.staticSearchAiNodeCount(siteRequest_, AiNodeGenPage.staticSetAiNodeCount(siteRequest_, o)).toString();
	}

	/////////////
	// aiNode_ //
	/////////////


	/**	 The entity aiNode_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected AiNode aiNode_;

	/**	<br> The entity aiNode_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage&fq=entiteVar_enUS_indexed_string:aiNode_">Find the entity aiNode_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _aiNode_(Wrap<AiNode> w);

	public AiNode getAiNode_() {
		return aiNode_;
	}

	public void setAiNode_(AiNode aiNode_) {
		this.aiNode_ = aiNode_;
	}
	public static AiNode staticSetAiNode_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected AiNodeGenPage aiNode_Init() {
		Wrap<AiNode> aiNode_Wrap = new Wrap<AiNode>().var("aiNode_");
		if(aiNode_ == null) {
			_aiNode_(aiNode_Wrap);
			Optional.ofNullable(aiNode_Wrap.getO()).ifPresent(o -> {
				setAiNode_(o);
			});
		}
		return (AiNodeGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = AiNodeGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected AiNodeGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (AiNodeGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return AiNodeGenPage.staticSearchPk(siteRequest_, AiNodeGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = AiNodeGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNodeGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (AiNodeGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return AiNodeGenPage.staticSearchId(siteRequest_, AiNodeGenPage.staticSetId(siteRequest_, o)).toString();
	}

	///////////////////
	// pageUriAiNode //
	///////////////////


	/**	 The entity pageUriAiNode
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriAiNode;

	/**	<br> The entity pageUriAiNode
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.node.AiNodeGenPage&fq=entiteVar_enUS_indexed_string:pageUriAiNode">Find the entity pageUriAiNode in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriAiNode(Wrap<String> c);

	public String getPageUriAiNode() {
		return pageUriAiNode;
	}
	public void setPageUriAiNode(String o) {
		this.pageUriAiNode = AiNodeGenPage.staticSetPageUriAiNode(siteRequest_, o);
	}
	public static String staticSetPageUriAiNode(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected AiNodeGenPage pageUriAiNodeInit() {
		Wrap<String> pageUriAiNodeWrap = new Wrap<String>().var("pageUriAiNode");
		if(pageUriAiNode == null) {
			_pageUriAiNode(pageUriAiNodeWrap);
			Optional.ofNullable(pageUriAiNodeWrap.getO()).ifPresent(o -> {
				setPageUriAiNode(o);
			});
		}
		return (AiNodeGenPage)this;
	}

	public static String staticSearchPageUriAiNode(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriAiNode(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriAiNode(SiteRequest siteRequest_, String o) {
		return AiNodeGenPage.staticSearchPageUriAiNode(siteRequest_, AiNodeGenPage.staticSetPageUriAiNode(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepAiNodeGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepAiNodeGenPage();
	}

	public Future<Void> promiseDeepAiNodeGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseAiNodeGenPage(promise2);
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

	public Future<Void> promiseAiNodeGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListAiNode_Init();
				listAiNodeInit();
				aiNodeCountInit();
				aiNode_Init();
				pkInit();
				idInit();
				pageUriAiNodeInit();
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
		return promiseDeepAiNodeGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAiNodeGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestAiNodeGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAiNodeGenPage(v);
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
	public Object obtainAiNodeGenPage(String var) {
		AiNodeGenPage oAiNodeGenPage = (AiNodeGenPage)this;
		switch(var) {
			case "searchListAiNode_":
				return oAiNodeGenPage.searchListAiNode_;
			case "listAiNode":
				return oAiNodeGenPage.listAiNode;
			case "aiNodeCount":
				return oAiNodeGenPage.aiNodeCount;
			case "aiNode_":
				return oAiNodeGenPage.aiNode_;
			case "pk":
				return oAiNodeGenPage.pk;
			case "id":
				return oAiNodeGenPage.id;
			case "pageUriAiNode":
				return oAiNodeGenPage.pageUriAiNode;
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
				o = relateAiNodeGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateAiNodeGenPage(String var, Object val) {
		AiNodeGenPage oAiNodeGenPage = (AiNodeGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetAiNodeGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetAiNodeGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listAiNode":
			return AiNodeGenPage.staticSetListAiNode(siteRequest_, o);
		case "aiNodeCount":
			return AiNodeGenPage.staticSetAiNodeCount(siteRequest_, o);
		case "pk":
			return AiNodeGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return AiNodeGenPage.staticSetId(siteRequest_, o);
		case "pageUriAiNode":
			return AiNodeGenPage.staticSetPageUriAiNode(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchAiNodeGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchAiNodeGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listAiNode":
			return AiNodeGenPage.staticSearchListAiNode(siteRequest_, (JsonArray)o);
		case "aiNodeCount":
			return AiNodeGenPage.staticSearchAiNodeCount(siteRequest_, (Integer)o);
		case "pk":
			return AiNodeGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return AiNodeGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriAiNode":
			return AiNodeGenPage.staticSearchPageUriAiNode(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrAiNodeGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrAiNodeGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listAiNode":
			return AiNodeGenPage.staticSearchStrListAiNode(siteRequest_, (String)o);
		case "aiNodeCount":
			return AiNodeGenPage.staticSearchStrAiNodeCount(siteRequest_, (Integer)o);
		case "pk":
			return AiNodeGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return AiNodeGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriAiNode":
			return AiNodeGenPage.staticSearchStrPageUriAiNode(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqAiNodeGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqAiNodeGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listAiNode":
			return AiNodeGenPage.staticSearchFqListAiNode(siteRequest_, o);
		case "aiNodeCount":
			return AiNodeGenPage.staticSearchFqAiNodeCount(siteRequest_, o);
		case "pk":
			return AiNodeGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return AiNodeGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriAiNode":
			return AiNodeGenPage.staticSearchFqPageUriAiNode(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "AiNodeGenPage";
	public static final String VAR_searchListAiNode_ = "searchListAiNode_";
	public static final String VAR_listAiNode = "listAiNode";
	public static final String VAR_aiNodeCount = "aiNodeCount";
	public static final String VAR_aiNode_ = "aiNode_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriAiNode = "pageUriAiNode";

	public static final String DISPLAY_NAME_searchListAiNode_ = "";
	public static final String DISPLAY_NAME_listAiNode = "";
	public static final String DISPLAY_NAME_aiNodeCount = "";
	public static final String DISPLAY_NAME_aiNode_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriAiNode = "";

	public static String displayNameForClass(String var) {
		return AiNodeGenPage.displayNameAiNodeGenPage(var);
	}
	public static String displayNameAiNodeGenPage(String var) {
		switch(var) {
		case VAR_searchListAiNode_:
			return DISPLAY_NAME_searchListAiNode_;
		case VAR_listAiNode:
			return DISPLAY_NAME_listAiNode;
		case VAR_aiNodeCount:
			return DISPLAY_NAME_aiNodeCount;
		case VAR_aiNode_:
			return DISPLAY_NAME_aiNode_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriAiNode:
			return DISPLAY_NAME_pageUriAiNode;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
