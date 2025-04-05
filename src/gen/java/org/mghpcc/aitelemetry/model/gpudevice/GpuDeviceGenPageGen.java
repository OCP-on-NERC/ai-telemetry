package org.mghpcc.aitelemetry.model.gpudevice;

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
import org.mghpcc.aitelemetry.model.gpudevice.GpuDevice;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these GpuDeviceGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class GpuDeviceGenPageGen into the class GpuDeviceGenPage. 
 * </li>
 * <h3>About the GpuDeviceGenPage class and it's generated class GpuDeviceGenPageGen&lt;PageLayout&gt;: </h3>extends GpuDeviceGenPageGen
 * <p>
 * This Java class extends a generated Java class GpuDeviceGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage">Find the class GpuDeviceGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends GpuDeviceGenPageGen<PageLayout>
 * <p>This <code>class GpuDeviceGenPage extends GpuDeviceGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated GpuDeviceGenPageGen. 
 * The generated <code>class GpuDeviceGenPageGen extends PageLayout</code> which means that GpuDeviceGenPage extends GpuDeviceGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the GpuDeviceGenPage class will inherit the helpful inherited class comments from the super class GpuDeviceGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the GpuDeviceGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class GpuDeviceGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.gpudevice in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class GpuDeviceGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(GpuDeviceGenPage.class);

	//////////////////////////
	// searchListGpuDevice_ //
	//////////////////////////


	/**	 The entity searchListGpuDevice_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<GpuDevice> searchListGpuDevice_;

	/**	<br> The entity searchListGpuDevice_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:searchListGpuDevice_">Find the entity searchListGpuDevice_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListGpuDevice_(Wrap<SearchList<GpuDevice>> w);

	public SearchList<GpuDevice> getSearchListGpuDevice_() {
		return searchListGpuDevice_;
	}

	public void setSearchListGpuDevice_(SearchList<GpuDevice> searchListGpuDevice_) {
		this.searchListGpuDevice_ = searchListGpuDevice_;
	}
	public static SearchList<GpuDevice> staticSetSearchListGpuDevice_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected GpuDeviceGenPage searchListGpuDevice_Init() {
		Wrap<SearchList<GpuDevice>> searchListGpuDevice_Wrap = new Wrap<SearchList<GpuDevice>>().var("searchListGpuDevice_");
		if(searchListGpuDevice_ == null) {
			_searchListGpuDevice_(searchListGpuDevice_Wrap);
			Optional.ofNullable(searchListGpuDevice_Wrap.getO()).ifPresent(o -> {
				setSearchListGpuDevice_(o);
			});
		}
		return (GpuDeviceGenPage)this;
	}

	///////////////////
	// listGpuDevice //
	///////////////////


	/**	 The entity listGpuDevice
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listGpuDevice = new JsonArray();

	/**	<br> The entity listGpuDevice
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:listGpuDevice">Find the entity listGpuDevice in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listGpuDevice(JsonArray l);

	public JsonArray getListGpuDevice() {
		return listGpuDevice;
	}

	public void setListGpuDevice(JsonArray listGpuDevice) {
		this.listGpuDevice = listGpuDevice;
	}
	@JsonIgnore
	public void setListGpuDevice(String o) {
		this.listGpuDevice = GpuDeviceGenPage.staticSetListGpuDevice(siteRequest_, o);
	}
	public static JsonArray staticSetListGpuDevice(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected GpuDeviceGenPage listGpuDeviceInit() {
		_listGpuDevice(listGpuDevice);
		return (GpuDeviceGenPage)this;
	}

	public static String staticSearchListGpuDevice(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListGpuDevice(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListGpuDevice(SiteRequest siteRequest_, String o) {
		return GpuDeviceGenPage.staticSearchListGpuDevice(siteRequest_, GpuDeviceGenPage.staticSetListGpuDevice(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = GpuDeviceGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected GpuDeviceGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (GpuDeviceGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return GpuDeviceGenPage.staticSearchResultCount(siteRequest_, GpuDeviceGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected GpuDevice result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<GpuDevice> w);

	public GpuDevice getResult() {
		return result;
	}

	public void setResult(GpuDevice result) {
		this.result = result;
	}
	public static GpuDevice staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected GpuDeviceGenPage resultInit() {
		Wrap<GpuDevice> resultWrap = new Wrap<GpuDevice>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (GpuDeviceGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = GpuDeviceGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected GpuDeviceGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (GpuDeviceGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return GpuDeviceGenPage.staticSearchPk(siteRequest_, GpuDeviceGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = GpuDeviceGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDeviceGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (GpuDeviceGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return GpuDeviceGenPage.staticSearchSolrId(siteRequest_, GpuDeviceGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	//////////////////////
	// pageUriGpuDevice //
	//////////////////////


	/**	 The entity pageUriGpuDevice
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriGpuDevice;

	/**	<br> The entity pageUriGpuDevice
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:pageUriGpuDevice">Find the entity pageUriGpuDevice in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriGpuDevice(Wrap<String> c);

	public String getPageUriGpuDevice() {
		return pageUriGpuDevice;
	}
	public void setPageUriGpuDevice(String o) {
		this.pageUriGpuDevice = GpuDeviceGenPage.staticSetPageUriGpuDevice(siteRequest_, o);
	}
	public static String staticSetPageUriGpuDevice(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDeviceGenPage pageUriGpuDeviceInit() {
		Wrap<String> pageUriGpuDeviceWrap = new Wrap<String>().var("pageUriGpuDevice");
		if(pageUriGpuDevice == null) {
			_pageUriGpuDevice(pageUriGpuDeviceWrap);
			Optional.ofNullable(pageUriGpuDeviceWrap.getO()).ifPresent(o -> {
				setPageUriGpuDevice(o);
			});
		}
		return (GpuDeviceGenPage)this;
	}

	public static String staticSearchPageUriGpuDevice(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriGpuDevice(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriGpuDevice(SiteRequest siteRequest_, String o) {
		return GpuDeviceGenPage.staticSearchPageUriGpuDevice(siteRequest_, GpuDeviceGenPage.staticSetPageUriGpuDevice(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<GpuDeviceGenPageGen<DEV>> promiseDeepGpuDeviceGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepGpuDeviceGenPage();
	}

	public Future<GpuDeviceGenPageGen<DEV>> promiseDeepGpuDeviceGenPage() {
		Promise<GpuDeviceGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseGpuDeviceGenPage(promise2);
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

	public Future<Void> promiseGpuDeviceGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListGpuDevice_Init();
				listGpuDeviceInit();
				resultCountInit();
				resultInit();
				pkInit();
				solrIdInit();
				pageUriGpuDeviceInit();
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

	@Override public Future<? extends GpuDeviceGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepGpuDeviceGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestGpuDeviceGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestGpuDeviceGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainGpuDeviceGenPage(v);
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
	public Object obtainGpuDeviceGenPage(String var) {
		GpuDeviceGenPage oGpuDeviceGenPage = (GpuDeviceGenPage)this;
		switch(var) {
			case "searchListGpuDevice_":
				return oGpuDeviceGenPage.searchListGpuDevice_;
			case "listGpuDevice":
				return oGpuDeviceGenPage.listGpuDevice;
			case "resultCount":
				return oGpuDeviceGenPage.resultCount;
			case "result":
				return oGpuDeviceGenPage.result;
			case "pk":
				return oGpuDeviceGenPage.pk;
			case "solrId":
				return oGpuDeviceGenPage.solrId;
			case "pageUriGpuDevice":
				return oGpuDeviceGenPage.pageUriGpuDevice;
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
				o = relateGpuDeviceGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateGpuDeviceGenPage(String var, Object val) {
		GpuDeviceGenPage oGpuDeviceGenPage = (GpuDeviceGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetGpuDeviceGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetGpuDeviceGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listGpuDevice":
			return GpuDeviceGenPage.staticSetListGpuDevice(siteRequest_, o);
		case "resultCount":
			return GpuDeviceGenPage.staticSetResultCount(siteRequest_, o);
		case "pk":
			return GpuDeviceGenPage.staticSetPk(siteRequest_, o);
		case "solrId":
			return GpuDeviceGenPage.staticSetSolrId(siteRequest_, o);
		case "pageUriGpuDevice":
			return GpuDeviceGenPage.staticSetPageUriGpuDevice(siteRequest_, o);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchGpuDeviceGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchGpuDeviceGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listGpuDevice":
			return GpuDeviceGenPage.staticSearchListGpuDevice(siteRequest_, (JsonArray)o);
		case "resultCount":
			return GpuDeviceGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "pk":
			return GpuDeviceGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "solrId":
			return GpuDeviceGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriGpuDevice":
			return GpuDeviceGenPage.staticSearchPageUriGpuDevice(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrGpuDeviceGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrGpuDeviceGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listGpuDevice":
			return GpuDeviceGenPage.staticSearchStrListGpuDevice(siteRequest_, (String)o);
		case "resultCount":
			return GpuDeviceGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "pk":
			return GpuDeviceGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "solrId":
			return GpuDeviceGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriGpuDevice":
			return GpuDeviceGenPage.staticSearchStrPageUriGpuDevice(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqGpuDeviceGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqGpuDeviceGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listGpuDevice":
			return GpuDeviceGenPage.staticSearchFqListGpuDevice(siteRequest_, o);
		case "resultCount":
			return GpuDeviceGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "pk":
			return GpuDeviceGenPage.staticSearchFqPk(siteRequest_, o);
		case "solrId":
			return GpuDeviceGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriGpuDevice":
			return GpuDeviceGenPage.staticSearchFqPageUriGpuDevice(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "GpuDeviceGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage";
	public static final String VAR_searchListGpuDevice_ = "searchListGpuDevice_";
	public static final String VAR_listGpuDevice = "listGpuDevice";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_pk = "pk";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriGpuDevice = "pageUriGpuDevice";

	public static final String DISPLAY_NAME_searchListGpuDevice_ = "";
	public static final String DISPLAY_NAME_listGpuDevice = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriGpuDevice = "";

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
		return GpuDeviceGenPage.displayNameGpuDeviceGenPage(var);
	}
	public static String displayNameGpuDeviceGenPage(String var) {
		switch(var) {
		case VAR_searchListGpuDevice_:
			return DISPLAY_NAME_searchListGpuDevice_;
		case VAR_listGpuDevice:
			return DISPLAY_NAME_listGpuDevice;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriGpuDevice:
			return DISPLAY_NAME_pageUriGpuDevice;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionGpuDeviceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameGpuDeviceGenPage(String var) {
		switch(var) {
		case VAR_searchListGpuDevice_:
			return "SearchList";
		case VAR_listGpuDevice:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "GpuDevice";
		case VAR_pk:
			return "Long";
		case VAR_solrId:
			return "String";
		case VAR_pageUriGpuDevice:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnGpuDeviceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowGpuDeviceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellGpuDeviceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinGpuDeviceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxGpuDeviceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxGpuDeviceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minGpuDeviceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
