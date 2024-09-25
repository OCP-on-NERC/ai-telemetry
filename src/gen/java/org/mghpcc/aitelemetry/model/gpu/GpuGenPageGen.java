package org.mghpcc.aitelemetry.model.gpu;

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
import org.mghpcc.aitelemetry.model.gpu.Gpu;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these GpuGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class GpuGenPageGen into the class GpuGenPage. 
 * </li>
 * <h3>About the GpuGenPage class and it's generated class GpuGenPageGen&lt;BaseModelPage&gt;: </h3>extends GpuGenPageGen
 * <p>
 * This Java class extends a generated Java class GpuGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage">Find the class GpuGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends GpuGenPageGen<BaseModelPage>
 * <p>This <code>class GpuGenPage extends GpuGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated GpuGenPageGen. 
 * The generated <code>class GpuGenPageGen extends BaseModelPage</code> which means that GpuGenPage extends GpuGenPageGen which extends BaseModelPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the GpuGenPage class will inherit the helpful inherited class comments from the super class GpuGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the GpuGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class GpuGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.gpu in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class GpuGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(GpuGenPage.class);

	////////////////////
	// searchListGpu_ //
	////////////////////


	/**	 The entity searchListGpu_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<Gpu> searchListGpu_;

	/**	<br> The entity searchListGpu_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage&fq=entiteVar_enUS_indexed_string:searchListGpu_">Find the entity searchListGpu_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListGpu_(Wrap<SearchList<Gpu>> w);

	public SearchList<Gpu> getSearchListGpu_() {
		return searchListGpu_;
	}

	public void setSearchListGpu_(SearchList<Gpu> searchListGpu_) {
		this.searchListGpu_ = searchListGpu_;
	}
	public static SearchList<Gpu> staticSetSearchListGpu_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected GpuGenPage searchListGpu_Init() {
		Wrap<SearchList<Gpu>> searchListGpu_Wrap = new Wrap<SearchList<Gpu>>().var("searchListGpu_");
		if(searchListGpu_ == null) {
			_searchListGpu_(searchListGpu_Wrap);
			Optional.ofNullable(searchListGpu_Wrap.getO()).ifPresent(o -> {
				setSearchListGpu_(o);
			});
		}
		return (GpuGenPage)this;
	}

	/////////////
	// listGpu //
	/////////////


	/**	 The entity listGpu
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listGpu = new JsonArray();

	/**	<br> The entity listGpu
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage&fq=entiteVar_enUS_indexed_string:listGpu">Find the entity listGpu in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listGpu(JsonArray l);

	public JsonArray getListGpu() {
		return listGpu;
	}

	public void setListGpu(JsonArray listGpu) {
		this.listGpu = listGpu;
	}
	@JsonIgnore
	public void setListGpu(String o) {
		this.listGpu = GpuGenPage.staticSetListGpu(siteRequest_, o);
	}
	public static JsonArray staticSetListGpu(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected GpuGenPage listGpuInit() {
		_listGpu(listGpu);
		return (GpuGenPage)this;
	}

	public static String staticSearchListGpu(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListGpu(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListGpu(SiteRequest siteRequest_, String o) {
		return GpuGenPage.staticSearchListGpu(siteRequest_, GpuGenPage.staticSetListGpu(siteRequest_, o)).toString();
	}

	//////////////
	// gpuCount //
	//////////////


	/**	 The entity gpuCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer gpuCount;

	/**	<br> The entity gpuCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage&fq=entiteVar_enUS_indexed_string:gpuCount">Find the entity gpuCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuCount(Wrap<Integer> w);

	public Integer getGpuCount() {
		return gpuCount;
	}

	public void setGpuCount(Integer gpuCount) {
		this.gpuCount = gpuCount;
	}
	@JsonIgnore
	public void setGpuCount(String o) {
		this.gpuCount = GpuGenPage.staticSetGpuCount(siteRequest_, o);
	}
	public static Integer staticSetGpuCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected GpuGenPage gpuCountInit() {
		Wrap<Integer> gpuCountWrap = new Wrap<Integer>().var("gpuCount");
		if(gpuCount == null) {
			_gpuCount(gpuCountWrap);
			Optional.ofNullable(gpuCountWrap.getO()).ifPresent(o -> {
				setGpuCount(o);
			});
		}
		return (GpuGenPage)this;
	}

	public static Integer staticSearchGpuCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrGpuCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuCount(SiteRequest siteRequest_, String o) {
		return GpuGenPage.staticSearchGpuCount(siteRequest_, GpuGenPage.staticSetGpuCount(siteRequest_, o)).toString();
	}

	//////////
	// gpu_ //
	//////////


	/**	 The entity gpu_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Gpu gpu_;

	/**	<br> The entity gpu_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage&fq=entiteVar_enUS_indexed_string:gpu_">Find the entity gpu_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpu_(Wrap<Gpu> w);

	public Gpu getGpu_() {
		return gpu_;
	}

	public void setGpu_(Gpu gpu_) {
		this.gpu_ = gpu_;
	}
	public static Gpu staticSetGpu_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected GpuGenPage gpu_Init() {
		Wrap<Gpu> gpu_Wrap = new Wrap<Gpu>().var("gpu_");
		if(gpu_ == null) {
			_gpu_(gpu_Wrap);
			Optional.ofNullable(gpu_Wrap.getO()).ifPresent(o -> {
				setGpu_(o);
			});
		}
		return (GpuGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = GpuGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected GpuGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (GpuGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return GpuGenPage.staticSearchPk(siteRequest_, GpuGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = GpuGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (GpuGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return GpuGenPage.staticSearchId(siteRequest_, GpuGenPage.staticSetId(siteRequest_, o)).toString();
	}

	////////////////
	// pageUriGpu //
	////////////////


	/**	 The entity pageUriGpu
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriGpu;

	/**	<br> The entity pageUriGpu
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpu.GpuGenPage&fq=entiteVar_enUS_indexed_string:pageUriGpu">Find the entity pageUriGpu in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriGpu(Wrap<String> c);

	public String getPageUriGpu() {
		return pageUriGpu;
	}
	public void setPageUriGpu(String o) {
		this.pageUriGpu = GpuGenPage.staticSetPageUriGpu(siteRequest_, o);
	}
	public static String staticSetPageUriGpu(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuGenPage pageUriGpuInit() {
		Wrap<String> pageUriGpuWrap = new Wrap<String>().var("pageUriGpu");
		if(pageUriGpu == null) {
			_pageUriGpu(pageUriGpuWrap);
			Optional.ofNullable(pageUriGpuWrap.getO()).ifPresent(o -> {
				setPageUriGpu(o);
			});
		}
		return (GpuGenPage)this;
	}

	public static String staticSearchPageUriGpu(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriGpu(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriGpu(SiteRequest siteRequest_, String o) {
		return GpuGenPage.staticSearchPageUriGpu(siteRequest_, GpuGenPage.staticSetPageUriGpu(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepGpuGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepGpuGenPage();
	}

	public Future<Void> promiseDeepGpuGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseGpuGenPage(promise2);
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

	public Future<Void> promiseGpuGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListGpu_Init();
				listGpuInit();
				gpuCountInit();
				gpu_Init();
				pkInit();
				idInit();
				pageUriGpuInit();
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
		return promiseDeepGpuGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestGpuGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestGpuGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainGpuGenPage(v);
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
	public Object obtainGpuGenPage(String var) {
		GpuGenPage oGpuGenPage = (GpuGenPage)this;
		switch(var) {
			case "searchListGpu_":
				return oGpuGenPage.searchListGpu_;
			case "listGpu":
				return oGpuGenPage.listGpu;
			case "gpuCount":
				return oGpuGenPage.gpuCount;
			case "gpu_":
				return oGpuGenPage.gpu_;
			case "pk":
				return oGpuGenPage.pk;
			case "id":
				return oGpuGenPage.id;
			case "pageUriGpu":
				return oGpuGenPage.pageUriGpu;
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
				o = relateGpuGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateGpuGenPage(String var, Object val) {
		GpuGenPage oGpuGenPage = (GpuGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetGpuGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetGpuGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listGpu":
			return GpuGenPage.staticSetListGpu(siteRequest_, o);
		case "gpuCount":
			return GpuGenPage.staticSetGpuCount(siteRequest_, o);
		case "pk":
			return GpuGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return GpuGenPage.staticSetId(siteRequest_, o);
		case "pageUriGpu":
			return GpuGenPage.staticSetPageUriGpu(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchGpuGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchGpuGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listGpu":
			return GpuGenPage.staticSearchListGpu(siteRequest_, (JsonArray)o);
		case "gpuCount":
			return GpuGenPage.staticSearchGpuCount(siteRequest_, (Integer)o);
		case "pk":
			return GpuGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return GpuGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriGpu":
			return GpuGenPage.staticSearchPageUriGpu(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrGpuGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrGpuGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listGpu":
			return GpuGenPage.staticSearchStrListGpu(siteRequest_, (String)o);
		case "gpuCount":
			return GpuGenPage.staticSearchStrGpuCount(siteRequest_, (Integer)o);
		case "pk":
			return GpuGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return GpuGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriGpu":
			return GpuGenPage.staticSearchStrPageUriGpu(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqGpuGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqGpuGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listGpu":
			return GpuGenPage.staticSearchFqListGpu(siteRequest_, o);
		case "gpuCount":
			return GpuGenPage.staticSearchFqGpuCount(siteRequest_, o);
		case "pk":
			return GpuGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return GpuGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriGpu":
			return GpuGenPage.staticSearchFqPageUriGpu(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "GpuGenPage";
	public static final String VAR_searchListGpu_ = "searchListGpu_";
	public static final String VAR_listGpu = "listGpu";
	public static final String VAR_gpuCount = "gpuCount";
	public static final String VAR_gpu_ = "gpu_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriGpu = "pageUriGpu";

	public static final String DISPLAY_NAME_searchListGpu_ = "";
	public static final String DISPLAY_NAME_listGpu = "";
	public static final String DISPLAY_NAME_gpuCount = "";
	public static final String DISPLAY_NAME_gpu_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriGpu = "";

	public static String displayNameForClass(String var) {
		return GpuGenPage.displayNameGpuGenPage(var);
	}
	public static String displayNameGpuGenPage(String var) {
		switch(var) {
		case VAR_searchListGpu_:
			return DISPLAY_NAME_searchListGpu_;
		case VAR_listGpu:
			return DISPLAY_NAME_listGpu;
		case VAR_gpuCount:
			return DISPLAY_NAME_gpuCount;
		case VAR_gpu_:
			return DISPLAY_NAME_gpu_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriGpu:
			return DISPLAY_NAME_pageUriGpu;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
