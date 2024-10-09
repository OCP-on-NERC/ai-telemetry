package org.mghpcc.aitelemetry.model.gpuslice;

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
import org.mghpcc.aitelemetry.model.gpuslice.GpuSlice;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these GpuSliceGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class GpuSliceGenPageGen into the class GpuSliceGenPage. 
 * </li>
 * <h3>About the GpuSliceGenPage class and it's generated class GpuSliceGenPageGen&lt;BaseModelPage&gt;: </h3>extends GpuSliceGenPageGen
 * <p>
 * This Java class extends a generated Java class GpuSliceGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage">Find the class GpuSliceGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends GpuSliceGenPageGen<BaseModelPage>
 * <p>This <code>class GpuSliceGenPage extends GpuSliceGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated GpuSliceGenPageGen. 
 * The generated <code>class GpuSliceGenPageGen extends BaseModelPage</code> which means that GpuSliceGenPage extends GpuSliceGenPageGen which extends BaseModelPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the GpuSliceGenPage class will inherit the helpful inherited class comments from the super class GpuSliceGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the GpuSliceGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class GpuSliceGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.gpuslice in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class GpuSliceGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(GpuSliceGenPage.class);

	/////////////////////////
	// searchListGpuSlice_ //
	/////////////////////////


	/**	 The entity searchListGpuSlice_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<GpuSlice> searchListGpuSlice_;

	/**	<br> The entity searchListGpuSlice_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage&fq=entiteVar_enUS_indexed_string:searchListGpuSlice_">Find the entity searchListGpuSlice_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListGpuSlice_(Wrap<SearchList<GpuSlice>> w);

	public SearchList<GpuSlice> getSearchListGpuSlice_() {
		return searchListGpuSlice_;
	}

	public void setSearchListGpuSlice_(SearchList<GpuSlice> searchListGpuSlice_) {
		this.searchListGpuSlice_ = searchListGpuSlice_;
	}
	public static SearchList<GpuSlice> staticSetSearchListGpuSlice_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected GpuSliceGenPage searchListGpuSlice_Init() {
		Wrap<SearchList<GpuSlice>> searchListGpuSlice_Wrap = new Wrap<SearchList<GpuSlice>>().var("searchListGpuSlice_");
		if(searchListGpuSlice_ == null) {
			_searchListGpuSlice_(searchListGpuSlice_Wrap);
			Optional.ofNullable(searchListGpuSlice_Wrap.getO()).ifPresent(o -> {
				setSearchListGpuSlice_(o);
			});
		}
		return (GpuSliceGenPage)this;
	}

	//////////////////
	// listGpuSlice //
	//////////////////


	/**	 The entity listGpuSlice
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listGpuSlice = new JsonArray();

	/**	<br> The entity listGpuSlice
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage&fq=entiteVar_enUS_indexed_string:listGpuSlice">Find the entity listGpuSlice in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listGpuSlice(JsonArray l);

	public JsonArray getListGpuSlice() {
		return listGpuSlice;
	}

	public void setListGpuSlice(JsonArray listGpuSlice) {
		this.listGpuSlice = listGpuSlice;
	}
	@JsonIgnore
	public void setListGpuSlice(String o) {
		this.listGpuSlice = GpuSliceGenPage.staticSetListGpuSlice(siteRequest_, o);
	}
	public static JsonArray staticSetListGpuSlice(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected GpuSliceGenPage listGpuSliceInit() {
		_listGpuSlice(listGpuSlice);
		return (GpuSliceGenPage)this;
	}

	public static String staticSearchListGpuSlice(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListGpuSlice(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListGpuSlice(SiteRequest siteRequest_, String o) {
		return GpuSliceGenPage.staticSearchListGpuSlice(siteRequest_, GpuSliceGenPage.staticSetListGpuSlice(siteRequest_, o)).toString();
	}

	///////////////////
	// gpuSliceCount //
	///////////////////


	/**	 The entity gpuSliceCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer gpuSliceCount;

	/**	<br> The entity gpuSliceCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage&fq=entiteVar_enUS_indexed_string:gpuSliceCount">Find the entity gpuSliceCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuSliceCount(Wrap<Integer> w);

	public Integer getGpuSliceCount() {
		return gpuSliceCount;
	}

	public void setGpuSliceCount(Integer gpuSliceCount) {
		this.gpuSliceCount = gpuSliceCount;
	}
	@JsonIgnore
	public void setGpuSliceCount(String o) {
		this.gpuSliceCount = GpuSliceGenPage.staticSetGpuSliceCount(siteRequest_, o);
	}
	public static Integer staticSetGpuSliceCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected GpuSliceGenPage gpuSliceCountInit() {
		Wrap<Integer> gpuSliceCountWrap = new Wrap<Integer>().var("gpuSliceCount");
		if(gpuSliceCount == null) {
			_gpuSliceCount(gpuSliceCountWrap);
			Optional.ofNullable(gpuSliceCountWrap.getO()).ifPresent(o -> {
				setGpuSliceCount(o);
			});
		}
		return (GpuSliceGenPage)this;
	}

	public static Integer staticSearchGpuSliceCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrGpuSliceCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGpuSliceCount(SiteRequest siteRequest_, String o) {
		return GpuSliceGenPage.staticSearchGpuSliceCount(siteRequest_, GpuSliceGenPage.staticSetGpuSliceCount(siteRequest_, o)).toString();
	}

	///////////////
	// gpuSlice_ //
	///////////////


	/**	 The entity gpuSlice_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected GpuSlice gpuSlice_;

	/**	<br> The entity gpuSlice_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage&fq=entiteVar_enUS_indexed_string:gpuSlice_">Find the entity gpuSlice_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gpuSlice_(Wrap<GpuSlice> w);

	public GpuSlice getGpuSlice_() {
		return gpuSlice_;
	}

	public void setGpuSlice_(GpuSlice gpuSlice_) {
		this.gpuSlice_ = gpuSlice_;
	}
	public static GpuSlice staticSetGpuSlice_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected GpuSliceGenPage gpuSlice_Init() {
		Wrap<GpuSlice> gpuSlice_Wrap = new Wrap<GpuSlice>().var("gpuSlice_");
		if(gpuSlice_ == null) {
			_gpuSlice_(gpuSlice_Wrap);
			Optional.ofNullable(gpuSlice_Wrap.getO()).ifPresent(o -> {
				setGpuSlice_(o);
			});
		}
		return (GpuSliceGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = GpuSliceGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected GpuSliceGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (GpuSliceGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return GpuSliceGenPage.staticSearchPk(siteRequest_, GpuSliceGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = GpuSliceGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuSliceGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (GpuSliceGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return GpuSliceGenPage.staticSearchId(siteRequest_, GpuSliceGenPage.staticSetId(siteRequest_, o)).toString();
	}

	/////////////////////
	// pageUriGpuSlice //
	/////////////////////


	/**	 The entity pageUriGpuSlice
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriGpuSlice;

	/**	<br> The entity pageUriGpuSlice
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.gpuslice.GpuSliceGenPage&fq=entiteVar_enUS_indexed_string:pageUriGpuSlice">Find the entity pageUriGpuSlice in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriGpuSlice(Wrap<String> c);

	public String getPageUriGpuSlice() {
		return pageUriGpuSlice;
	}
	public void setPageUriGpuSlice(String o) {
		this.pageUriGpuSlice = GpuSliceGenPage.staticSetPageUriGpuSlice(siteRequest_, o);
	}
	public static String staticSetPageUriGpuSlice(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected GpuSliceGenPage pageUriGpuSliceInit() {
		Wrap<String> pageUriGpuSliceWrap = new Wrap<String>().var("pageUriGpuSlice");
		if(pageUriGpuSlice == null) {
			_pageUriGpuSlice(pageUriGpuSliceWrap);
			Optional.ofNullable(pageUriGpuSliceWrap.getO()).ifPresent(o -> {
				setPageUriGpuSlice(o);
			});
		}
		return (GpuSliceGenPage)this;
	}

	public static String staticSearchPageUriGpuSlice(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriGpuSlice(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriGpuSlice(SiteRequest siteRequest_, String o) {
		return GpuSliceGenPage.staticSearchPageUriGpuSlice(siteRequest_, GpuSliceGenPage.staticSetPageUriGpuSlice(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepGpuSliceGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepGpuSliceGenPage();
	}

	public Future<Void> promiseDeepGpuSliceGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseGpuSliceGenPage(promise2);
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

	public Future<Void> promiseGpuSliceGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListGpuSlice_Init();
				listGpuSliceInit();
				gpuSliceCountInit();
				gpuSlice_Init();
				pkInit();
				idInit();
				pageUriGpuSliceInit();
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
		return promiseDeepGpuSliceGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestGpuSliceGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestGpuSliceGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainGpuSliceGenPage(v);
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
	public Object obtainGpuSliceGenPage(String var) {
		GpuSliceGenPage oGpuSliceGenPage = (GpuSliceGenPage)this;
		switch(var) {
			case "searchListGpuSlice_":
				return oGpuSliceGenPage.searchListGpuSlice_;
			case "listGpuSlice":
				return oGpuSliceGenPage.listGpuSlice;
			case "gpuSliceCount":
				return oGpuSliceGenPage.gpuSliceCount;
			case "gpuSlice_":
				return oGpuSliceGenPage.gpuSlice_;
			case "pk":
				return oGpuSliceGenPage.pk;
			case "id":
				return oGpuSliceGenPage.id;
			case "pageUriGpuSlice":
				return oGpuSliceGenPage.pageUriGpuSlice;
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
				o = relateGpuSliceGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateGpuSliceGenPage(String var, Object val) {
		GpuSliceGenPage oGpuSliceGenPage = (GpuSliceGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetGpuSliceGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetGpuSliceGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listGpuSlice":
			return GpuSliceGenPage.staticSetListGpuSlice(siteRequest_, o);
		case "gpuSliceCount":
			return GpuSliceGenPage.staticSetGpuSliceCount(siteRequest_, o);
		case "pk":
			return GpuSliceGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return GpuSliceGenPage.staticSetId(siteRequest_, o);
		case "pageUriGpuSlice":
			return GpuSliceGenPage.staticSetPageUriGpuSlice(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchGpuSliceGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchGpuSliceGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listGpuSlice":
			return GpuSliceGenPage.staticSearchListGpuSlice(siteRequest_, (JsonArray)o);
		case "gpuSliceCount":
			return GpuSliceGenPage.staticSearchGpuSliceCount(siteRequest_, (Integer)o);
		case "pk":
			return GpuSliceGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return GpuSliceGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriGpuSlice":
			return GpuSliceGenPage.staticSearchPageUriGpuSlice(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrGpuSliceGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrGpuSliceGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listGpuSlice":
			return GpuSliceGenPage.staticSearchStrListGpuSlice(siteRequest_, (String)o);
		case "gpuSliceCount":
			return GpuSliceGenPage.staticSearchStrGpuSliceCount(siteRequest_, (Integer)o);
		case "pk":
			return GpuSliceGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return GpuSliceGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriGpuSlice":
			return GpuSliceGenPage.staticSearchStrPageUriGpuSlice(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqGpuSliceGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqGpuSliceGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listGpuSlice":
			return GpuSliceGenPage.staticSearchFqListGpuSlice(siteRequest_, o);
		case "gpuSliceCount":
			return GpuSliceGenPage.staticSearchFqGpuSliceCount(siteRequest_, o);
		case "pk":
			return GpuSliceGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return GpuSliceGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriGpuSlice":
			return GpuSliceGenPage.staticSearchFqPageUriGpuSlice(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "GpuSliceGenPage";
	public static final String VAR_searchListGpuSlice_ = "searchListGpuSlice_";
	public static final String VAR_listGpuSlice = "listGpuSlice";
	public static final String VAR_gpuSliceCount = "gpuSliceCount";
	public static final String VAR_gpuSlice_ = "gpuSlice_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriGpuSlice = "pageUriGpuSlice";

	public static final String DISPLAY_NAME_searchListGpuSlice_ = "";
	public static final String DISPLAY_NAME_listGpuSlice = "";
	public static final String DISPLAY_NAME_gpuSliceCount = "";
	public static final String DISPLAY_NAME_gpuSlice_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriGpuSlice = "";

	public static String displayNameForClass(String var) {
		return GpuSliceGenPage.displayNameGpuSliceGenPage(var);
	}
	public static String displayNameGpuSliceGenPage(String var) {
		switch(var) {
		case VAR_searchListGpuSlice_:
			return DISPLAY_NAME_searchListGpuSlice_;
		case VAR_listGpuSlice:
			return DISPLAY_NAME_listGpuSlice;
		case VAR_gpuSliceCount:
			return DISPLAY_NAME_gpuSliceCount;
		case VAR_gpuSlice_:
			return DISPLAY_NAME_gpuSlice_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriGpuSlice:
			return DISPLAY_NAME_pageUriGpuSlice;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
