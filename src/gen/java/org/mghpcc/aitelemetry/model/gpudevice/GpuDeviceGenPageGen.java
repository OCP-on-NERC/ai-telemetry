package org.mghpcc.aitelemetry.model.gpudevice;

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
import org.mghpcc.aitelemetry.model.gpudevice.GpuDevice;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these GpuDeviceGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class GpuDeviceGenPageGen into the class GpuDeviceGenPage. 
 * </li>
 * <h3>About the GpuDeviceGenPage class and it's generated class GpuDeviceGenPageGen&lt;BaseModelPage&gt;: </h3>extends GpuDeviceGenPageGen
 * <p>
 * This Java class extends a generated Java class GpuDeviceGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage">Find the class GpuDeviceGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends GpuDeviceGenPageGen<BaseModelPage>
 * <p>This <code>class GpuDeviceGenPage extends GpuDeviceGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated GpuDeviceGenPageGen. 
 * The generated <code>class GpuDeviceGenPageGen extends BaseModelPage</code> which means that GpuDeviceGenPage extends GpuDeviceGenPageGen which extends BaseModelPage. 
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
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.gpudevice in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class GpuDeviceGenPageGen<DEV> extends BaseModelPage {
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:searchListGpuDevice_">Find the entity searchListGpuDevice_ in Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listGpuDevice = new JsonArray();

	/**	<br> The entity listGpuDevice
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:listGpuDevice">Find the entity listGpuDevice in Solr</a>
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

	////////////////////
	// gpuDeviceCount //
	////////////////////


	/**	 The entity gpuDeviceCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer gpuDeviceCount;

	/**	<br> The entity gpuDeviceCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:gpuDeviceCount">Find the entity gpuDeviceCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuDeviceCount(Wrap<Integer> w);

	public Integer getGpuDeviceCount() {
		return gpuDeviceCount;
	}

	public void setGpuDeviceCount(Integer gpuDeviceCount) {
		this.gpuDeviceCount = gpuDeviceCount;
	}
	@JsonIgnore
	public void setGpuDeviceCount(String o) {
		this.gpuDeviceCount = GpuDeviceGenPage.staticSetGpuDeviceCount(siteRequest_, o);
	}
	public static Integer staticSetGpuDeviceCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected GpuDeviceGenPage gpuDeviceCountInit() {
		Wrap<Integer> gpuDeviceCountWrap = new Wrap<Integer>().var("gpuDeviceCount");
		if(gpuDeviceCount == null) {
			_gpuDeviceCount(gpuDeviceCountWrap);
			Optional.ofNullable(gpuDeviceCountWrap.getO()).ifPresent(o -> {
				setGpuDeviceCount(o);
			});
		}
		return (GpuDeviceGenPage)this;
	}

	public static Integer staticSearchGpuDeviceCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrGpuDeviceCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuDeviceCount(SiteRequest siteRequest_, String o) {
		return GpuDeviceGenPage.staticSearchGpuDeviceCount(siteRequest_, GpuDeviceGenPage.staticSetGpuDeviceCount(siteRequest_, o)).toString();
	}

	////////////////
	// gpuDevice_ //
	////////////////


	/**	 The entity gpuDevice_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected GpuDevice gpuDevice_;

	/**	<br> The entity gpuDevice_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:gpuDevice_">Find the entity gpuDevice_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuDevice_(Wrap<GpuDevice> w);

	public GpuDevice getGpuDevice_() {
		return gpuDevice_;
	}

	public void setGpuDevice_(GpuDevice gpuDevice_) {
		this.gpuDevice_ = gpuDevice_;
	}
	public static GpuDevice staticSetGpuDevice_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected GpuDeviceGenPage gpuDevice_Init() {
		Wrap<GpuDevice> gpuDevice_Wrap = new Wrap<GpuDevice>().var("gpuDevice_");
		if(gpuDevice_ == null) {
			_gpuDevice_(gpuDevice_Wrap);
			Optional.ofNullable(gpuDevice_Wrap.getO()).ifPresent(o -> {
				setGpuDevice_(o);
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = GpuDeviceGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuDeviceGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (GpuDeviceGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return GpuDeviceGenPage.staticSearchId(siteRequest_, GpuDeviceGenPage.staticSetId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpudevice.GpuDeviceGenPage&fq=entiteVar_enUS_indexed_string:pageUriGpuDevice">Find the entity pageUriGpuDevice in Solr</a>
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

	public Future<Void> promiseDeepGpuDeviceGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepGpuDeviceGenPage();
	}

	public Future<Void> promiseDeepGpuDeviceGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseGpuDeviceGenPage(promise2);
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

	public Future<Void> promiseGpuDeviceGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListGpuDevice_Init();
				listGpuDeviceInit();
				gpuDeviceCountInit();
				gpuDevice_Init();
				pkInit();
				idInit();
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

	@Override public Future<Void> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepGpuDeviceGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestGpuDeviceGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
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
			case "gpuDeviceCount":
				return oGpuDeviceGenPage.gpuDeviceCount;
			case "gpuDevice_":
				return oGpuDeviceGenPage.gpuDevice_;
			case "pk":
				return oGpuDeviceGenPage.pk;
			case "id":
				return oGpuDeviceGenPage.id;
			case "pageUriGpuDevice":
				return oGpuDeviceGenPage.pageUriGpuDevice;
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
				return super.relateBaseModelPage(var, val);
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
		case "gpuDeviceCount":
			return GpuDeviceGenPage.staticSetGpuDeviceCount(siteRequest_, o);
		case "pk":
			return GpuDeviceGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return GpuDeviceGenPage.staticSetId(siteRequest_, o);
		case "pageUriGpuDevice":
			return GpuDeviceGenPage.staticSetPageUriGpuDevice(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
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
		case "gpuDeviceCount":
			return GpuDeviceGenPage.staticSearchGpuDeviceCount(siteRequest_, (Integer)o);
		case "pk":
			return GpuDeviceGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return GpuDeviceGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriGpuDevice":
			return GpuDeviceGenPage.staticSearchPageUriGpuDevice(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
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
		case "gpuDeviceCount":
			return GpuDeviceGenPage.staticSearchStrGpuDeviceCount(siteRequest_, (Integer)o);
		case "pk":
			return GpuDeviceGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return GpuDeviceGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriGpuDevice":
			return GpuDeviceGenPage.staticSearchStrPageUriGpuDevice(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
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
		case "gpuDeviceCount":
			return GpuDeviceGenPage.staticSearchFqGpuDeviceCount(siteRequest_, o);
		case "pk":
			return GpuDeviceGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return GpuDeviceGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriGpuDevice":
			return GpuDeviceGenPage.staticSearchFqPageUriGpuDevice(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "GpuDeviceGenPage";
	public static final String VAR_searchListGpuDevice_ = "searchListGpuDevice_";
	public static final String VAR_listGpuDevice = "listGpuDevice";
	public static final String VAR_gpuDeviceCount = "gpuDeviceCount";
	public static final String VAR_gpuDevice_ = "gpuDevice_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriGpuDevice = "pageUriGpuDevice";

	public static final String DISPLAY_NAME_searchListGpuDevice_ = "";
	public static final String DISPLAY_NAME_listGpuDevice = "";
	public static final String DISPLAY_NAME_gpuDeviceCount = "";
	public static final String DISPLAY_NAME_gpuDevice_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriGpuDevice = "";

	public static String displayNameForClass(String var) {
		return GpuDeviceGenPage.displayNameGpuDeviceGenPage(var);
	}
	public static String displayNameGpuDeviceGenPage(String var) {
		switch(var) {
		case VAR_searchListGpuDevice_:
			return DISPLAY_NAME_searchListGpuDevice_;
		case VAR_listGpuDevice:
			return DISPLAY_NAME_listGpuDevice;
		case VAR_gpuDeviceCount:
			return DISPLAY_NAME_gpuDeviceCount;
		case VAR_gpuDevice_:
			return DISPLAY_NAME_gpuDevice_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriGpuDevice:
			return DISPLAY_NAME_pageUriGpuDevice;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
