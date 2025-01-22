package org.mghpcc.aitelemetry.page;

import org.mghpcc.aitelemetry.request.SiteRequest;
import java.lang.Object;
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
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Vertx;
import java.lang.String;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.core.json.JsonObject;
import org.computate.vertx.serialize.vertx.JsonObjectDeserializer;
import java.lang.Long;
import java.lang.Void;
import io.vertx.core.json.JsonArray;
import org.computate.search.response.solr.SolrResponse.Stats;
import org.computate.search.response.solr.SolrResponse.FacetCounts;
import java.lang.Integer;
import java.time.ZoneId;
import java.util.Locale;
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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these PageLayout objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class PageLayoutGen into the class PageLayout. 
 * </li>
 * <h3>About the PageLayout class and it's generated class PageLayoutGen&lt;Object&gt;: </h3>extends PageLayoutGen
 * <p>
 * This Java class extends a generated Java class PageLayoutGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout">Find the class PageLayout in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends PageLayoutGen<Object>
 * <p>This <code>class PageLayout extends PageLayoutGen&lt;Object&gt;</code>, which means it extends a newly generated PageLayoutGen. 
 * The generated <code>class PageLayoutGen extends Object</code> which means that PageLayout extends PageLayoutGen which extends Object. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the PageLayout class will inherit the helpful inherited class comments from the super class PageLayoutGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the PageLayout Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class PageLayout in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.page in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.page&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class PageLayoutGen<DEV> extends Object {
	protected static final Logger LOG = LoggerFactory.getLogger(PageLayout.class);

	///////////////
	// webClient //
	///////////////


	/**	 The entity webClient
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected WebClient webClient;

	/**	<br> The entity webClient
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:webClient">Find the entity webClient in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _webClient(Wrap<WebClient> w);

	public WebClient getWebClient() {
		return webClient;
	}

	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}
	public static WebClient staticSetWebClient(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout webClientInit() {
		Wrap<WebClient> webClientWrap = new Wrap<WebClient>().var("webClient");
		if(webClient == null) {
			_webClient(webClientWrap);
			Optional.ofNullable(webClientWrap.getO()).ifPresent(o -> {
				setWebClient(o);
			});
		}
		return (PageLayout)this;
	}

	///////////
	// vertx //
	///////////


	/**	 The entity vertx
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected Vertx vertx;

	/**	<br> The entity vertx
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:vertx">Find the entity vertx in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _vertx(Wrap<Vertx> w);

	public Vertx getVertx() {
		return vertx;
	}

	public void setVertx(Vertx vertx) {
		this.vertx = vertx;
	}
	public static Vertx staticSetVertx(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout vertxInit() {
		Wrap<Vertx> vertxWrap = new Wrap<Vertx>().var("vertx");
		if(vertx == null) {
			_vertx(vertxWrap);
			Optional.ofNullable(vertxWrap.getO()).ifPresent(o -> {
				setVertx(o);
			});
		}
		return (PageLayout)this;
	}

	//////////////////
	// siteRequest_ //
	//////////////////


	/**	 The entity siteRequest_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SiteRequest siteRequest_;

	/**	<br> The entity siteRequest_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:siteRequest_">Find the entity siteRequest_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequest> w);

	public SiteRequest getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequest siteRequest_) {
		this.siteRequest_ = siteRequest_;
	}
	public static SiteRequest staticSetSiteRequest_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout siteRequest_Init() {
		Wrap<SiteRequest> siteRequest_Wrap = new Wrap<SiteRequest>().var("siteRequest_");
		if(siteRequest_ == null) {
			_siteRequest_(siteRequest_Wrap);
			Optional.ofNullable(siteRequest_Wrap.getO()).ifPresent(o -> {
				setSiteRequest_(o);
			});
		}
		return (PageLayout)this;
	}

	//////////
	// lang //
	//////////


	/**	 The entity lang
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String lang;

	/**	<br> The entity lang
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:lang">Find the entity lang in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _lang(Wrap<String> w);

	public String getLang() {
		return lang;
	}
	public void setLang(String o) {
		this.lang = PageLayout.staticSetLang(siteRequest_, o);
	}
	public static String staticSetLang(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout langInit() {
		Wrap<String> langWrap = new Wrap<String>().var("lang");
		if(lang == null) {
			_lang(langWrap);
			Optional.ofNullable(langWrap.getO()).ifPresent(o -> {
				setLang(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchLang(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLang(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLang(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchLang(siteRequest_, PageLayout.staticSetLang(siteRequest_, o)).toString();
	}

	/////////////////
	// requestVars //
	/////////////////


	/**	 The entity requestVars
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Map<String, String> requestVars;

	/**	<br> The entity requestVars
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:requestVars">Find the entity requestVars in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _requestVars(Wrap<Map<String, String>> w);

	public Map<String, String> getRequestVars() {
		return requestVars;
	}

	public void setRequestVars(Map<String, String> requestVars) {
		this.requestVars = requestVars;
	}
	public static Map<String, String> staticSetRequestVars(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout requestVarsInit() {
		Wrap<Map<String, String>> requestVarsWrap = new Wrap<Map<String, String>>().var("requestVars");
		if(requestVars == null) {
			_requestVars(requestVarsWrap);
			Optional.ofNullable(requestVarsWrap.getO()).ifPresent(o -> {
				setRequestVars(o);
			});
		}
		return (PageLayout)this;
	}

	////////////////////
	// serviceRequest //
	////////////////////


	/**	 The entity serviceRequest
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ServiceRequest serviceRequest;

	/**	<br> The entity serviceRequest
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:serviceRequest">Find the entity serviceRequest in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _serviceRequest(Wrap<ServiceRequest> w);

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public static ServiceRequest staticSetServiceRequest(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout serviceRequestInit() {
		Wrap<ServiceRequest> serviceRequestWrap = new Wrap<ServiceRequest>().var("serviceRequest");
		if(serviceRequest == null) {
			_serviceRequest(serviceRequestWrap);
			Optional.ofNullable(serviceRequestWrap.getO()).ifPresent(o -> {
				setServiceRequest(o);
			});
		}
		return (PageLayout)this;
	}

	///////////////////
	// staticBaseUrl //
	///////////////////


	/**	 The entity staticBaseUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String staticBaseUrl;

	/**	<br> The entity staticBaseUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:staticBaseUrl">Find the entity staticBaseUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _staticBaseUrl(Wrap<String> w);

	public String getStaticBaseUrl() {
		return staticBaseUrl;
	}
	public void setStaticBaseUrl(String o) {
		this.staticBaseUrl = PageLayout.staticSetStaticBaseUrl(siteRequest_, o);
	}
	public static String staticSetStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout staticBaseUrlInit() {
		Wrap<String> staticBaseUrlWrap = new Wrap<String>().var("staticBaseUrl");
		if(staticBaseUrl == null) {
			_staticBaseUrl(staticBaseUrlWrap);
			Optional.ofNullable(staticBaseUrlWrap.getO()).ifPresent(o -> {
				setStaticBaseUrl(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchStaticBaseUrl(siteRequest_, PageLayout.staticSetStaticBaseUrl(siteRequest_, o)).toString();
	}

	/////////////////
	// siteBaseUrl //
	/////////////////


	/**	 The entity siteBaseUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteBaseUrl;

	/**	<br> The entity siteBaseUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:siteBaseUrl">Find the entity siteBaseUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteBaseUrl(Wrap<String> w);

	public String getSiteBaseUrl() {
		return siteBaseUrl;
	}
	public void setSiteBaseUrl(String o) {
		this.siteBaseUrl = PageLayout.staticSetSiteBaseUrl(siteRequest_, o);
	}
	public static String staticSetSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout siteBaseUrlInit() {
		Wrap<String> siteBaseUrlWrap = new Wrap<String>().var("siteBaseUrl");
		if(siteBaseUrl == null) {
			_siteBaseUrl(siteBaseUrlWrap);
			Optional.ofNullable(siteBaseUrlWrap.getO()).ifPresent(o -> {
				setSiteBaseUrl(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchSiteBaseUrl(siteRequest_, PageLayout.staticSetSiteBaseUrl(siteRequest_, o)).toString();
	}

	/////////////////
	// siteAuthUrl //
	/////////////////


	/**	 The entity siteAuthUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteAuthUrl;

	/**	<br> The entity siteAuthUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:siteAuthUrl">Find the entity siteAuthUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteAuthUrl(Wrap<String> w);

	public String getSiteAuthUrl() {
		return siteAuthUrl;
	}
	public void setSiteAuthUrl(String o) {
		this.siteAuthUrl = PageLayout.staticSetSiteAuthUrl(siteRequest_, o);
	}
	public static String staticSetSiteAuthUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout siteAuthUrlInit() {
		Wrap<String> siteAuthUrlWrap = new Wrap<String>().var("siteAuthUrl");
		if(siteAuthUrl == null) {
			_siteAuthUrl(siteAuthUrlWrap);
			Optional.ofNullable(siteAuthUrlWrap.getO()).ifPresent(o -> {
				setSiteAuthUrl(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchSiteAuthUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteAuthUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteAuthUrl(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchSiteAuthUrl(siteRequest_, PageLayout.staticSetSiteAuthUrl(siteRequest_, o)).toString();
	}

	///////////////////
	// siteAuthRealm //
	///////////////////


	/**	 The entity siteAuthRealm
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteAuthRealm;

	/**	<br> The entity siteAuthRealm
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:siteAuthRealm">Find the entity siteAuthRealm in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteAuthRealm(Wrap<String> w);

	public String getSiteAuthRealm() {
		return siteAuthRealm;
	}
	public void setSiteAuthRealm(String o) {
		this.siteAuthRealm = PageLayout.staticSetSiteAuthRealm(siteRequest_, o);
	}
	public static String staticSetSiteAuthRealm(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout siteAuthRealmInit() {
		Wrap<String> siteAuthRealmWrap = new Wrap<String>().var("siteAuthRealm");
		if(siteAuthRealm == null) {
			_siteAuthRealm(siteAuthRealmWrap);
			Optional.ofNullable(siteAuthRealmWrap.getO()).ifPresent(o -> {
				setSiteAuthRealm(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchSiteAuthRealm(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteAuthRealm(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteAuthRealm(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchSiteAuthRealm(siteRequest_, PageLayout.staticSetSiteAuthRealm(siteRequest_, o)).toString();
	}

	////////////////////
	// fontAwesomeKit //
	////////////////////


	/**	 The entity fontAwesomeKit
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String fontAwesomeKit;

	/**	<br> The entity fontAwesomeKit
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:fontAwesomeKit">Find the entity fontAwesomeKit in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _fontAwesomeKit(Wrap<String> w);

	public String getFontAwesomeKit() {
		return fontAwesomeKit;
	}
	public void setFontAwesomeKit(String o) {
		this.fontAwesomeKit = PageLayout.staticSetFontAwesomeKit(siteRequest_, o);
	}
	public static String staticSetFontAwesomeKit(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout fontAwesomeKitInit() {
		Wrap<String> fontAwesomeKitWrap = new Wrap<String>().var("fontAwesomeKit");
		if(fontAwesomeKit == null) {
			_fontAwesomeKit(fontAwesomeKitWrap);
			Optional.ofNullable(fontAwesomeKitWrap.getO()).ifPresent(o -> {
				setFontAwesomeKit(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchFontAwesomeKit(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrFontAwesomeKit(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqFontAwesomeKit(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchFontAwesomeKit(siteRequest_, PageLayout.staticSetFontAwesomeKit(siteRequest_, o)).toString();
	}

	//////////////////////////
	// facebookGraphVersion //
	//////////////////////////


	/**	 The entity facebookGraphVersion
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String facebookGraphVersion;

	/**	<br> The entity facebookGraphVersion
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:facebookGraphVersion">Find the entity facebookGraphVersion in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _facebookGraphVersion(Wrap<String> w);

	public String getFacebookGraphVersion() {
		return facebookGraphVersion;
	}
	public void setFacebookGraphVersion(String o) {
		this.facebookGraphVersion = PageLayout.staticSetFacebookGraphVersion(siteRequest_, o);
	}
	public static String staticSetFacebookGraphVersion(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout facebookGraphVersionInit() {
		Wrap<String> facebookGraphVersionWrap = new Wrap<String>().var("facebookGraphVersion");
		if(facebookGraphVersion == null) {
			_facebookGraphVersion(facebookGraphVersionWrap);
			Optional.ofNullable(facebookGraphVersionWrap.getO()).ifPresent(o -> {
				setFacebookGraphVersion(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchFacebookGraphVersion(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrFacebookGraphVersion(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqFacebookGraphVersion(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchFacebookGraphVersion(siteRequest_, PageLayout.staticSetFacebookGraphVersion(siteRequest_, o)).toString();
	}

	///////////////////
	// facebookAppId //
	///////////////////


	/**	 The entity facebookAppId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String facebookAppId;

	/**	<br> The entity facebookAppId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:facebookAppId">Find the entity facebookAppId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _facebookAppId(Wrap<String> w);

	public String getFacebookAppId() {
		return facebookAppId;
	}
	public void setFacebookAppId(String o) {
		this.facebookAppId = PageLayout.staticSetFacebookAppId(siteRequest_, o);
	}
	public static String staticSetFacebookAppId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout facebookAppIdInit() {
		Wrap<String> facebookAppIdWrap = new Wrap<String>().var("facebookAppId");
		if(facebookAppId == null) {
			_facebookAppId(facebookAppIdWrap);
			Optional.ofNullable(facebookAppIdWrap.getO()).ifPresent(o -> {
				setFacebookAppId(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchFacebookAppId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrFacebookAppId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqFacebookAppId(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchFacebookAppId(siteRequest_, PageLayout.staticSetFacebookAppId(siteRequest_, o)).toString();
	}

	/////////////
	// pageUri //
	/////////////


	/**	 The entity pageUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUri;

	/**	<br> The entity pageUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageUri">Find the entity pageUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUri(Wrap<String> w);

	public String getPageUri() {
		return pageUri;
	}
	public void setPageUri(String o) {
		this.pageUri = PageLayout.staticSetPageUri(siteRequest_, o);
	}
	public static String staticSetPageUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout pageUriInit() {
		Wrap<String> pageUriWrap = new Wrap<String>().var("pageUri");
		if(pageUri == null) {
			_pageUri(pageUriWrap);
			Optional.ofNullable(pageUriWrap.getO()).ifPresent(o -> {
				setPageUri(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchPageUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUri(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageUri(siteRequest_, PageLayout.staticSetPageUri(siteRequest_, o)).toString();
	}

	////////////
	// pageId //
	////////////


	/**	 The entity pageId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageId;

	/**	<br> The entity pageId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = PageLayout.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageId(siteRequest_, PageLayout.staticSetPageId(siteRequest_, o)).toString();
	}

	////////////
	// apiUri //
	////////////


	/**	 The entity apiUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String apiUri;

	/**	<br> The entity apiUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:apiUri">Find the entity apiUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _apiUri(Wrap<String> w);

	public String getApiUri() {
		return apiUri;
	}
	public void setApiUri(String o) {
		this.apiUri = PageLayout.staticSetApiUri(siteRequest_, o);
	}
	public static String staticSetApiUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout apiUriInit() {
		Wrap<String> apiUriWrap = new Wrap<String>().var("apiUri");
		if(apiUri == null) {
			_apiUri(apiUriWrap);
			Optional.ofNullable(apiUriWrap.getO()).ifPresent(o -> {
				setApiUri(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchApiUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrApiUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqApiUri(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchApiUri(siteRequest_, PageLayout.staticSetApiUri(siteRequest_, o)).toString();
	}

	////////////////
	// pageMethod //
	////////////////


	/**	 The entity pageMethod
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageMethod;

	/**	<br> The entity pageMethod
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageMethod">Find the entity pageMethod in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageMethod(Wrap<String> w);

	public String getPageMethod() {
		return pageMethod;
	}
	public void setPageMethod(String o) {
		this.pageMethod = PageLayout.staticSetPageMethod(siteRequest_, o);
	}
	public static String staticSetPageMethod(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout pageMethodInit() {
		Wrap<String> pageMethodWrap = new Wrap<String>().var("pageMethod");
		if(pageMethod == null) {
			_pageMethod(pageMethodWrap);
			Optional.ofNullable(pageMethodWrap.getO()).ifPresent(o -> {
				setPageMethod(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchPageMethod(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageMethod(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageMethod(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageMethod(siteRequest_, PageLayout.staticSetPageMethod(siteRequest_, o)).toString();
	}

	////////////
	// params //
	////////////


	/**	 The entity params
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject params;

	/**	<br> The entity params
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:params">Find the entity params in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _params(Wrap<JsonObject> w);

	public JsonObject getParams() {
		return params;
	}

	public void setParams(JsonObject params) {
		this.params = params;
	}
	@JsonIgnore
	public void setParams(String o) {
		this.params = PageLayout.staticSetParams(siteRequest_, o);
	}
	public static JsonObject staticSetParams(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected PageLayout paramsInit() {
		Wrap<JsonObject> paramsWrap = new Wrap<JsonObject>().var("params");
		if(params == null) {
			_params(paramsWrap);
			Optional.ofNullable(paramsWrap.getO()).ifPresent(o -> {
				setParams(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchParams(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrParams(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqParams(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchParams(siteRequest_, PageLayout.staticSetParams(siteRequest_, o)).toString();
	}

	/////////////
	// userKey //
	/////////////


	/**	 The entity userKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long userKey;

	/**	<br> The entity userKey
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:userKey">Find the entity userKey in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userKey(Wrap<Long> w);

	public Long getUserKey() {
		return userKey;
	}

	public void setUserKey(Long userKey) {
		this.userKey = userKey;
	}
	@JsonIgnore
	public void setUserKey(String o) {
		this.userKey = PageLayout.staticSetUserKey(siteRequest_, o);
	}
	public static Long staticSetUserKey(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected PageLayout userKeyInit() {
		Wrap<Long> userKeyWrap = new Wrap<Long>().var("userKey");
		if(userKey == null) {
			_userKey(userKeyWrap);
			Optional.ofNullable(userKeyWrap.getO()).ifPresent(o -> {
				setUserKey(o);
			});
		}
		return (PageLayout)this;
	}

	public static Long staticSearchUserKey(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrUserKey(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserKey(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchUserKey(siteRequest_, PageLayout.staticSetUserKey(siteRequest_, o)).toString();
	}

	//////////////////
	// userFullName //
	//////////////////


	/**	 The entity userFullName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userFullName;

	/**	<br> The entity userFullName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:userFullName">Find the entity userFullName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userFullName(Wrap<String> w);

	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String o) {
		this.userFullName = PageLayout.staticSetUserFullName(siteRequest_, o);
	}
	public static String staticSetUserFullName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout userFullNameInit() {
		Wrap<String> userFullNameWrap = new Wrap<String>().var("userFullName");
		if(userFullName == null) {
			_userFullName(userFullNameWrap);
			Optional.ofNullable(userFullNameWrap.getO()).ifPresent(o -> {
				setUserFullName(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchUserFullName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserFullName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserFullName(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchUserFullName(siteRequest_, PageLayout.staticSetUserFullName(siteRequest_, o)).toString();
	}

	//////////////
	// userName //
	//////////////


	/**	 The entity userName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userName;

	/**	<br> The entity userName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:userName">Find the entity userName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userName(Wrap<String> w);

	public String getUserName() {
		return userName;
	}
	public void setUserName(String o) {
		this.userName = PageLayout.staticSetUserName(siteRequest_, o);
	}
	public static String staticSetUserName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout userNameInit() {
		Wrap<String> userNameWrap = new Wrap<String>().var("userName");
		if(userName == null) {
			_userName(userNameWrap);
			Optional.ofNullable(userNameWrap.getO()).ifPresent(o -> {
				setUserName(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchUserName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserName(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchUserName(siteRequest_, PageLayout.staticSetUserName(siteRequest_, o)).toString();
	}

	///////////////
	// userEmail //
	///////////////


	/**	 The entity userEmail
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userEmail;

	/**	<br> The entity userEmail
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:userEmail">Find the entity userEmail in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userEmail(Wrap<String> w);

	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String o) {
		this.userEmail = PageLayout.staticSetUserEmail(siteRequest_, o);
	}
	public static String staticSetUserEmail(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout userEmailInit() {
		Wrap<String> userEmailWrap = new Wrap<String>().var("userEmail");
		if(userEmail == null) {
			_userEmail(userEmailWrap);
			Optional.ofNullable(userEmailWrap.getO()).ifPresent(o -> {
				setUserEmail(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchUserEmail(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserEmail(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserEmail(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchUserEmail(siteRequest_, PageLayout.staticSetUserEmail(siteRequest_, o)).toString();
	}

	///////////////
	// logoutUrl //
	///////////////


	/**	 The entity logoutUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String logoutUrl;

	/**	<br> The entity logoutUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:logoutUrl">Find the entity logoutUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _logoutUrl(Wrap<String> w);

	public String getLogoutUrl() {
		return logoutUrl;
	}
	public void setLogoutUrl(String o) {
		this.logoutUrl = PageLayout.staticSetLogoutUrl(siteRequest_, o);
	}
	public static String staticSetLogoutUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout logoutUrlInit() {
		Wrap<String> logoutUrlWrap = new Wrap<String>().var("logoutUrl");
		if(logoutUrl == null) {
			_logoutUrl(logoutUrlWrap);
			Optional.ofNullable(logoutUrlWrap.getO()).ifPresent(o -> {
				setLogoutUrl(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchLogoutUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLogoutUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLogoutUrl(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchLogoutUrl(siteRequest_, PageLayout.staticSetLogoutUrl(siteRequest_, o)).toString();
	}

	///////////////////
	// promiseBefore //
	///////////////////


	/**	 The entity promiseBefore
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected Void promiseBefore;

	/**	<br> The entity promiseBefore
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:promiseBefore">Find the entity promiseBefore in Solr</a>
	 * <br>
	 * @param promise is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _promiseBefore(Promise<Void> promise);

	public Void getPromiseBefore() {
		return promiseBefore;
	}

	public void setPromiseBefore(Void promiseBefore) {
		this.promiseBefore = promiseBefore;
	}
	public static Void staticSetPromiseBefore(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected Future<Void> promiseBeforePromise() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		_promiseBefore(promise2);
		promise2.future().onSuccess(o -> {
			setPromiseBefore(o);
			promise.complete(o);
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	/////////////////////
	// classSimpleName //
	/////////////////////


	/**	 The entity classSimpleName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String classSimpleName;

	/**	<br> The entity classSimpleName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:classSimpleName">Find the entity classSimpleName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _classSimpleName(Wrap<String> w);

	public String getClassSimpleName() {
		return classSimpleName;
	}
	public void setClassSimpleName(String o) {
		this.classSimpleName = PageLayout.staticSetClassSimpleName(siteRequest_, o);
	}
	public static String staticSetClassSimpleName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout classSimpleNameInit() {
		Wrap<String> classSimpleNameWrap = new Wrap<String>().var("classSimpleName");
		if(classSimpleName == null) {
			_classSimpleName(classSimpleNameWrap);
			Optional.ofNullable(classSimpleNameWrap.getO()).ifPresent(o -> {
				setClassSimpleName(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchClassSimpleName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassSimpleName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassSimpleName(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchClassSimpleName(siteRequest_, PageLayout.staticSetClassSimpleName(siteRequest_, o)).toString();
	}

	///////////////
	// pageTitle //
	///////////////


	/**	 The entity pageTitle
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageTitle;

	/**	<br> The entity pageTitle
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageTitle">Find the entity pageTitle in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageTitle(Wrap<String> w);

	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String o) {
		this.pageTitle = PageLayout.staticSetPageTitle(siteRequest_, o);
	}
	public static String staticSetPageTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout pageTitleInit() {
		Wrap<String> pageTitleWrap = new Wrap<String>().var("pageTitle");
		if(pageTitle == null) {
			_pageTitle(pageTitleWrap);
			Optional.ofNullable(pageTitleWrap.getO()).ifPresent(o -> {
				setPageTitle(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchPageTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageTitle(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageTitle(siteRequest_, PageLayout.staticSetPageTitle(siteRequest_, o)).toString();
	}

	////////////
	// scopes //
	////////////


	/**	 The entity scopes
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> scopes = new ArrayList<String>();

	/**	<br> The entity scopes
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:scopes">Find the entity scopes in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _scopes(List<String> l);

	public List<String> getScopes() {
		return scopes;
	}

	public void setScopes(List<String> scopes) {
		this.scopes = scopes;
	}
	@JsonIgnore
	public void setScopes(String o) {
		String l = PageLayout.staticSetScopes(siteRequest_, o);
		if(l != null)
			addScopes(l);
	}
	public static String staticSetScopes(SiteRequest siteRequest_, String o) {
		return o;
	}
	public PageLayout addScopes(String...objects) {
		for(String o : objects) {
			addScopes(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addScopes(String o) {
		if(o != null)
			this.scopes.add(o);
		return (PageLayout)this;
	}
	@JsonIgnore
	public void setScopes(JsonArray objects) {
		scopes.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addScopes(o);
		}
	}
	protected PageLayout scopesInit() {
		_scopes(scopes);
		return (PageLayout)this;
	}

	public static String staticSearchScopes(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrScopes(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqScopes(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchScopes(siteRequest_, PageLayout.staticSetScopes(siteRequest_, o)).toString();
	}

	//////////////////
	// roleForWrite //
	//////////////////


	/**	 The entity roleForWrite
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> roleForWrite = new ArrayList<String>();

	/**	<br> The entity roleForWrite
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:roleForWrite">Find the entity roleForWrite in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _roleForWrite(List<String> l);

	public List<String> getRoleForWrite() {
		return roleForWrite;
	}

	public void setRoleForWrite(List<String> roleForWrite) {
		this.roleForWrite = roleForWrite;
	}
	@JsonIgnore
	public void setRoleForWrite(String o) {
		String l = PageLayout.staticSetRoleForWrite(siteRequest_, o);
		if(l != null)
			addRoleForWrite(l);
	}
	public static String staticSetRoleForWrite(SiteRequest siteRequest_, String o) {
		return o;
	}
	public PageLayout addRoleForWrite(String...objects) {
		for(String o : objects) {
			addRoleForWrite(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addRoleForWrite(String o) {
		if(o != null)
			this.roleForWrite.add(o);
		return (PageLayout)this;
	}
	@JsonIgnore
	public void setRoleForWrite(JsonArray objects) {
		roleForWrite.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addRoleForWrite(o);
		}
	}
	protected PageLayout roleForWriteInit() {
		_roleForWrite(roleForWrite);
		return (PageLayout)this;
	}

	public static String staticSearchRoleForWrite(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrRoleForWrite(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRoleForWrite(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchRoleForWrite(siteRequest_, PageLayout.staticSetRoleForWrite(siteRequest_, o)).toString();
	}

	/////////////////
	// roleForRead //
	/////////////////


	/**	 The entity roleForRead
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> roleForRead = new ArrayList<String>();

	/**	<br> The entity roleForRead
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:roleForRead">Find the entity roleForRead in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _roleForRead(List<String> l);

	public List<String> getRoleForRead() {
		return roleForRead;
	}

	public void setRoleForRead(List<String> roleForRead) {
		this.roleForRead = roleForRead;
	}
	@JsonIgnore
	public void setRoleForRead(String o) {
		String l = PageLayout.staticSetRoleForRead(siteRequest_, o);
		if(l != null)
			addRoleForRead(l);
	}
	public static String staticSetRoleForRead(SiteRequest siteRequest_, String o) {
		return o;
	}
	public PageLayout addRoleForRead(String...objects) {
		for(String o : objects) {
			addRoleForRead(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addRoleForRead(String o) {
		if(o != null)
			this.roleForRead.add(o);
		return (PageLayout)this;
	}
	@JsonIgnore
	public void setRoleForRead(JsonArray objects) {
		roleForRead.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addRoleForRead(o);
		}
	}
	protected PageLayout roleForReadInit() {
		_roleForRead(roleForRead);
		return (PageLayout)this;
	}

	public static String staticSearchRoleForRead(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrRoleForRead(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRoleForRead(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchRoleForRead(siteRequest_, PageLayout.staticSetRoleForRead(siteRequest_, o)).toString();
	}

	///////////
	// stats //
	///////////


	/**	 The entity stats
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Stats stats;

	/**	<br> The entity stats
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:stats">Find the entity stats in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _stats(Wrap<Stats> w);

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public static Stats staticSetStats(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout statsInit() {
		Wrap<Stats> statsWrap = new Wrap<Stats>().var("stats");
		if(stats == null) {
			_stats(statsWrap);
			Optional.ofNullable(statsWrap.getO()).ifPresent(o -> {
				setStats(o);
			});
		}
		return (PageLayout)this;
	}

	/////////////////
	// facetCounts //
	/////////////////


	/**	 The entity facetCounts
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected FacetCounts facetCounts;

	/**	<br> The entity facetCounts
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:facetCounts">Find the entity facetCounts in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _facetCounts(Wrap<FacetCounts> w);

	public FacetCounts getFacetCounts() {
		return facetCounts;
	}

	public void setFacetCounts(FacetCounts facetCounts) {
		this.facetCounts = facetCounts;
	}
	public static FacetCounts staticSetFacetCounts(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout facetCountsInit() {
		Wrap<FacetCounts> facetCountsWrap = new Wrap<FacetCounts>().var("facetCounts");
		if(facetCounts == null) {
			_facetCounts(facetCountsWrap);
			Optional.ofNullable(facetCountsWrap.getO()).ifPresent(o -> {
				setFacetCounts(o);
			});
		}
		return (PageLayout)this;
	}

	////////////////
	// pagination //
	////////////////


	/**	 The entity pagination
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject pagination = new JsonObject();

	/**	<br> The entity pagination
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pagination">Find the entity pagination in Solr</a>
	 * <br>
	 * @param pagination is the entity already constructed. 
	 **/
	protected abstract void _pagination(JsonObject pagination);

	public JsonObject getPagination() {
		return pagination;
	}

	public void setPagination(JsonObject pagination) {
		this.pagination = pagination;
	}
	@JsonIgnore
	public void setPagination(String o) {
		this.pagination = PageLayout.staticSetPagination(siteRequest_, o);
	}
	public static JsonObject staticSetPagination(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected PageLayout paginationInit() {
		_pagination(pagination);
		return (PageLayout)this;
	}

	public static String staticSearchPagination(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrPagination(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPagination(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPagination(siteRequest_, PageLayout.staticSetPagination(siteRequest_, o)).toString();
	}

	//////////////////////////
	// defaultFieldListVars //
	//////////////////////////


	/**	 The entity defaultFieldListVars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> defaultFieldListVars = new ArrayList<String>();

	/**	<br> The entity defaultFieldListVars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultFieldListVars">Find the entity defaultFieldListVars in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _defaultFieldListVars(List<String> l);

	public List<String> getDefaultFieldListVars() {
		return defaultFieldListVars;
	}

	public void setDefaultFieldListVars(List<String> defaultFieldListVars) {
		this.defaultFieldListVars = defaultFieldListVars;
	}
	@JsonIgnore
	public void setDefaultFieldListVars(String o) {
		String l = PageLayout.staticSetDefaultFieldListVars(siteRequest_, o);
		if(l != null)
			addDefaultFieldListVars(l);
	}
	public static String staticSetDefaultFieldListVars(SiteRequest siteRequest_, String o) {
		return o;
	}
	public PageLayout addDefaultFieldListVars(String...objects) {
		for(String o : objects) {
			addDefaultFieldListVars(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addDefaultFieldListVars(String o) {
		if(o != null)
			this.defaultFieldListVars.add(o);
		return (PageLayout)this;
	}
	@JsonIgnore
	public void setDefaultFieldListVars(JsonArray objects) {
		defaultFieldListVars.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addDefaultFieldListVars(o);
		}
	}
	protected PageLayout defaultFieldListVarsInit() {
		_defaultFieldListVars(defaultFieldListVars);
		return (PageLayout)this;
	}

	public static String staticSearchDefaultFieldListVars(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultFieldListVars(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultFieldListVars(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultFieldListVars(siteRequest_, PageLayout.staticSetDefaultFieldListVars(siteRequest_, o)).toString();
	}

	/////////////////////
	// defaultSortVars //
	/////////////////////


	/**	 The entity defaultSortVars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> defaultSortVars = new ArrayList<String>();

	/**	<br> The entity defaultSortVars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultSortVars">Find the entity defaultSortVars in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _defaultSortVars(List<String> l);

	public List<String> getDefaultSortVars() {
		return defaultSortVars;
	}

	public void setDefaultSortVars(List<String> defaultSortVars) {
		this.defaultSortVars = defaultSortVars;
	}
	@JsonIgnore
	public void setDefaultSortVars(String o) {
		String l = PageLayout.staticSetDefaultSortVars(siteRequest_, o);
		if(l != null)
			addDefaultSortVars(l);
	}
	public static String staticSetDefaultSortVars(SiteRequest siteRequest_, String o) {
		return o;
	}
	public PageLayout addDefaultSortVars(String...objects) {
		for(String o : objects) {
			addDefaultSortVars(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addDefaultSortVars(String o) {
		if(o != null)
			this.defaultSortVars.add(o);
		return (PageLayout)this;
	}
	@JsonIgnore
	public void setDefaultSortVars(JsonArray objects) {
		defaultSortVars.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addDefaultSortVars(o);
		}
	}
	protected PageLayout defaultSortVarsInit() {
		_defaultSortVars(defaultSortVars);
		return (PageLayout)this;
	}

	public static String staticSearchDefaultSortVars(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultSortVars(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultSortVars(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultSortVars(siteRequest_, PageLayout.staticSetDefaultSortVars(siteRequest_, o)).toString();
	}

	//////////////////////
	// defaultStatsVars //
	//////////////////////


	/**	 The entity defaultStatsVars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> defaultStatsVars = new ArrayList<String>();

	/**	<br> The entity defaultStatsVars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultStatsVars">Find the entity defaultStatsVars in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _defaultStatsVars(List<String> l);

	public List<String> getDefaultStatsVars() {
		return defaultStatsVars;
	}

	public void setDefaultStatsVars(List<String> defaultStatsVars) {
		this.defaultStatsVars = defaultStatsVars;
	}
	@JsonIgnore
	public void setDefaultStatsVars(String o) {
		String l = PageLayout.staticSetDefaultStatsVars(siteRequest_, o);
		if(l != null)
			addDefaultStatsVars(l);
	}
	public static String staticSetDefaultStatsVars(SiteRequest siteRequest_, String o) {
		return o;
	}
	public PageLayout addDefaultStatsVars(String...objects) {
		for(String o : objects) {
			addDefaultStatsVars(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addDefaultStatsVars(String o) {
		if(o != null)
			this.defaultStatsVars.add(o);
		return (PageLayout)this;
	}
	@JsonIgnore
	public void setDefaultStatsVars(JsonArray objects) {
		defaultStatsVars.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addDefaultStatsVars(o);
		}
	}
	protected PageLayout defaultStatsVarsInit() {
		_defaultStatsVars(defaultStatsVars);
		return (PageLayout)this;
	}

	public static String staticSearchDefaultStatsVars(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultStatsVars(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultStatsVars(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultStatsVars(siteRequest_, PageLayout.staticSetDefaultStatsVars(siteRequest_, o)).toString();
	}

	//////////////////////
	// defaultPivotVars //
	//////////////////////


	/**	 The entity defaultPivotVars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> defaultPivotVars = new ArrayList<String>();

	/**	<br> The entity defaultPivotVars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultPivotVars">Find the entity defaultPivotVars in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _defaultPivotVars(List<String> l);

	public List<String> getDefaultPivotVars() {
		return defaultPivotVars;
	}

	public void setDefaultPivotVars(List<String> defaultPivotVars) {
		this.defaultPivotVars = defaultPivotVars;
	}
	@JsonIgnore
	public void setDefaultPivotVars(String o) {
		String l = PageLayout.staticSetDefaultPivotVars(siteRequest_, o);
		if(l != null)
			addDefaultPivotVars(l);
	}
	public static String staticSetDefaultPivotVars(SiteRequest siteRequest_, String o) {
		return o;
	}
	public PageLayout addDefaultPivotVars(String...objects) {
		for(String o : objects) {
			addDefaultPivotVars(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addDefaultPivotVars(String o) {
		if(o != null)
			this.defaultPivotVars.add(o);
		return (PageLayout)this;
	}
	@JsonIgnore
	public void setDefaultPivotVars(JsonArray objects) {
		defaultPivotVars.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addDefaultPivotVars(o);
		}
	}
	protected PageLayout defaultPivotVarsInit() {
		_defaultPivotVars(defaultPivotVars);
		return (PageLayout)this;
	}

	public static String staticSearchDefaultPivotVars(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultPivotVars(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultPivotVars(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultPivotVars(siteRequest_, PageLayout.staticSetDefaultPivotVars(siteRequest_, o)).toString();
	}

	///////////
	// varsQ //
	///////////


	/**	 The entity varsQ
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject varsQ = new JsonObject();

	/**	<br> The entity varsQ
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:varsQ">Find the entity varsQ in Solr</a>
	 * <br>
	 * @param vars is the entity already constructed. 
	 **/
	protected abstract void _varsQ(JsonObject vars);

	public JsonObject getVarsQ() {
		return varsQ;
	}

	public void setVarsQ(JsonObject varsQ) {
		this.varsQ = varsQ;
	}
	@JsonIgnore
	public void setVarsQ(String o) {
		this.varsQ = PageLayout.staticSetVarsQ(siteRequest_, o);
	}
	public static JsonObject staticSetVarsQ(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected PageLayout varsQInit() {
		_varsQ(varsQ);
		return (PageLayout)this;
	}

	public static String staticSearchVarsQ(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrVarsQ(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVarsQ(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchVarsQ(siteRequest_, PageLayout.staticSetVarsQ(siteRequest_, o)).toString();
	}

	////////////
	// varsFq //
	////////////


	/**	 The entity varsFq
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject varsFq = new JsonObject();

	/**	<br> The entity varsFq
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:varsFq">Find the entity varsFq in Solr</a>
	 * <br>
	 * @param vars is the entity already constructed. 
	 **/
	protected abstract void _varsFq(JsonObject vars);

	public JsonObject getVarsFq() {
		return varsFq;
	}

	public void setVarsFq(JsonObject varsFq) {
		this.varsFq = varsFq;
	}
	@JsonIgnore
	public void setVarsFq(String o) {
		this.varsFq = PageLayout.staticSetVarsFq(siteRequest_, o);
	}
	public static JsonObject staticSetVarsFq(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected PageLayout varsFqInit() {
		_varsFq(varsFq);
		return (PageLayout)this;
	}

	public static String staticSearchVarsFq(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrVarsFq(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVarsFq(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchVarsFq(siteRequest_, PageLayout.staticSetVarsFq(siteRequest_, o)).toString();
	}

	/////////////////
	// varsFqCount //
	/////////////////


	/**	 The entity varsFqCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer varsFqCount;

	/**	<br> The entity varsFqCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:varsFqCount">Find the entity varsFqCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _varsFqCount(Wrap<Integer> w);

	public Integer getVarsFqCount() {
		return varsFqCount;
	}

	public void setVarsFqCount(Integer varsFqCount) {
		this.varsFqCount = varsFqCount;
	}
	@JsonIgnore
	public void setVarsFqCount(String o) {
		this.varsFqCount = PageLayout.staticSetVarsFqCount(siteRequest_, o);
	}
	public static Integer staticSetVarsFqCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PageLayout varsFqCountInit() {
		Wrap<Integer> varsFqCountWrap = new Wrap<Integer>().var("varsFqCount");
		if(varsFqCount == null) {
			_varsFqCount(varsFqCountWrap);
			Optional.ofNullable(varsFqCountWrap.getO()).ifPresent(o -> {
				setVarsFqCount(o);
			});
		}
		return (PageLayout)this;
	}

	public static Integer staticSearchVarsFqCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrVarsFqCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVarsFqCount(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchVarsFqCount(siteRequest_, PageLayout.staticSetVarsFqCount(siteRequest_, o)).toString();
	}

	///////////////
	// varsRange //
	///////////////


	/**	 The entity varsRange
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject varsRange = new JsonObject();

	/**	<br> The entity varsRange
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:varsRange">Find the entity varsRange in Solr</a>
	 * <br>
	 * @param vars is the entity already constructed. 
	 **/
	protected abstract void _varsRange(JsonObject vars);

	public JsonObject getVarsRange() {
		return varsRange;
	}

	public void setVarsRange(JsonObject varsRange) {
		this.varsRange = varsRange;
	}
	@JsonIgnore
	public void setVarsRange(String o) {
		this.varsRange = PageLayout.staticSetVarsRange(siteRequest_, o);
	}
	public static JsonObject staticSetVarsRange(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected PageLayout varsRangeInit() {
		_varsRange(varsRange);
		return (PageLayout)this;
	}

	public static String staticSearchVarsRange(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrVarsRange(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVarsRange(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchVarsRange(siteRequest_, PageLayout.staticSetVarsRange(siteRequest_, o)).toString();
	}

	///////////
	// query //
	///////////


	/**	 The entity query
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject query = new JsonObject();

	/**	<br> The entity query
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:query">Find the entity query in Solr</a>
	 * <br>
	 * @param query is the entity already constructed. 
	 **/
	protected abstract void _query(JsonObject query);

	public JsonObject getQuery() {
		return query;
	}

	public void setQuery(JsonObject query) {
		this.query = query;
	}
	@JsonIgnore
	public void setQuery(String o) {
		this.query = PageLayout.staticSetQuery(siteRequest_, o);
	}
	public static JsonObject staticSetQuery(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected PageLayout queryInit() {
		_query(query);
		return (PageLayout)this;
	}

	public static String staticSearchQuery(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrQuery(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqQuery(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchQuery(siteRequest_, PageLayout.staticSetQuery(siteRequest_, o)).toString();
	}

	//////////////////
	// pageResponse //
	//////////////////


	/**	 The entity pageResponse
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageResponse;

	/**	<br> The entity pageResponse
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageResponse">Find the entity pageResponse in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageResponse(Wrap<String> w);

	public String getPageResponse() {
		return pageResponse;
	}
	public void setPageResponse(String o) {
		this.pageResponse = PageLayout.staticSetPageResponse(siteRequest_, o);
	}
	public static String staticSetPageResponse(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout pageResponseInit() {
		Wrap<String> pageResponseWrap = new Wrap<String>().var("pageResponse");
		if(pageResponse == null) {
			_pageResponse(pageResponseWrap);
			Optional.ofNullable(pageResponseWrap.getO()).ifPresent(o -> {
				setPageResponse(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchPageResponse(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageResponse(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageResponse(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageResponse(siteRequest_, PageLayout.staticSetPageResponse(siteRequest_, o)).toString();
	}

	///////////////////
	// defaultZoneId //
	///////////////////


	/**	 The entity defaultZoneId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String defaultZoneId;

	/**	<br> The entity defaultZoneId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultZoneId">Find the entity defaultZoneId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultZoneId(Wrap<String> w);

	public String getDefaultZoneId() {
		return defaultZoneId;
	}
	public void setDefaultZoneId(String o) {
		this.defaultZoneId = PageLayout.staticSetDefaultZoneId(siteRequest_, o);
	}
	public static String staticSetDefaultZoneId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout defaultZoneIdInit() {
		Wrap<String> defaultZoneIdWrap = new Wrap<String>().var("defaultZoneId");
		if(defaultZoneId == null) {
			_defaultZoneId(defaultZoneIdWrap);
			Optional.ofNullable(defaultZoneIdWrap.getO()).ifPresent(o -> {
				setDefaultZoneId(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDefaultZoneId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultZoneId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultZoneId(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultZoneId(siteRequest_, PageLayout.staticSetDefaultZoneId(siteRequest_, o)).toString();
	}

	/////////////////////
	// defaultTimeZone //
	/////////////////////


	/**	 The entity defaultTimeZone
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ZoneId defaultTimeZone;

	/**	<br> The entity defaultTimeZone
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultTimeZone">Find the entity defaultTimeZone in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultTimeZone(Wrap<ZoneId> w);

	public ZoneId getDefaultTimeZone() {
		return defaultTimeZone;
	}

	public void setDefaultTimeZone(ZoneId defaultTimeZone) {
		this.defaultTimeZone = defaultTimeZone;
	}
	public static ZoneId staticSetDefaultTimeZone(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout defaultTimeZoneInit() {
		Wrap<ZoneId> defaultTimeZoneWrap = new Wrap<ZoneId>().var("defaultTimeZone");
		if(defaultTimeZone == null) {
			_defaultTimeZone(defaultTimeZoneWrap);
			Optional.ofNullable(defaultTimeZoneWrap.getO()).ifPresent(o -> {
				setDefaultTimeZone(o);
			});
		}
		return (PageLayout)this;
	}

	/////////////////////
	// defaultLocaleId //
	/////////////////////


	/**	 The entity defaultLocaleId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String defaultLocaleId;

	/**	<br> The entity defaultLocaleId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultLocaleId">Find the entity defaultLocaleId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultLocaleId(Wrap<String> w);

	public String getDefaultLocaleId() {
		return defaultLocaleId;
	}
	public void setDefaultLocaleId(String o) {
		this.defaultLocaleId = PageLayout.staticSetDefaultLocaleId(siteRequest_, o);
	}
	public static String staticSetDefaultLocaleId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout defaultLocaleIdInit() {
		Wrap<String> defaultLocaleIdWrap = new Wrap<String>().var("defaultLocaleId");
		if(defaultLocaleId == null) {
			_defaultLocaleId(defaultLocaleIdWrap);
			Optional.ofNullable(defaultLocaleIdWrap.getO()).ifPresent(o -> {
				setDefaultLocaleId(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDefaultLocaleId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultLocaleId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultLocaleId(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultLocaleId(siteRequest_, PageLayout.staticSetDefaultLocaleId(siteRequest_, o)).toString();
	}

	//////////
	// rows //
	//////////


	/**	 The entity rows
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long rows;

	/**	<br> The entity rows
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:rows">Find the entity rows in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _rows(Wrap<Long> w);

	public Long getRows() {
		return rows;
	}

	public void setRows(Long rows) {
		this.rows = rows;
	}
	@JsonIgnore
	public void setRows(String o) {
		this.rows = PageLayout.staticSetRows(siteRequest_, o);
	}
	public static Long staticSetRows(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected PageLayout rowsInit() {
		Wrap<Long> rowsWrap = new Wrap<Long>().var("rows");
		if(rows == null) {
			_rows(rowsWrap);
			Optional.ofNullable(rowsWrap.getO()).ifPresent(o -> {
				setRows(o);
			});
		}
		return (PageLayout)this;
	}

	public static Long staticSearchRows(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrRows(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRows(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchRows(siteRequest_, PageLayout.staticSetRows(siteRequest_, o)).toString();
	}

	///////////
	// start //
	///////////


	/**	 The entity start
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long start;

	/**	<br> The entity start
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:start">Find the entity start in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _start(Wrap<Long> w);

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}
	@JsonIgnore
	public void setStart(String o) {
		this.start = PageLayout.staticSetStart(siteRequest_, o);
	}
	public static Long staticSetStart(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected PageLayout startInit() {
		Wrap<Long> startWrap = new Wrap<Long>().var("start");
		if(start == null) {
			_start(startWrap);
			Optional.ofNullable(startWrap.getO()).ifPresent(o -> {
				setStart(o);
			});
		}
		return (PageLayout)this;
	}

	public static Long staticSearchStart(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrStart(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStart(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchStart(siteRequest_, PageLayout.staticSetStart(siteRequest_, o)).toString();
	}

	///////////////////
	// defaultLocale //
	///////////////////


	/**	 The entity defaultLocale
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected Locale defaultLocale;

	/**	<br> The entity defaultLocale
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultLocale">Find the entity defaultLocale in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultLocale(Wrap<Locale> w);

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
	public static Locale staticSetDefaultLocale(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected PageLayout defaultLocaleInit() {
		Wrap<Locale> defaultLocaleWrap = new Wrap<Locale>().var("defaultLocale");
		if(defaultLocale == null) {
			_defaultLocale(defaultLocaleWrap);
			Optional.ofNullable(defaultLocaleWrap.getO()).ifPresent(o -> {
				setDefaultLocale(o);
			});
		}
		return (PageLayout)this;
	}

	//////////////
	// rangeGap //
	//////////////


	/**	 The entity rangeGap
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String rangeGap;

	/**	<br> The entity rangeGap
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:rangeGap">Find the entity rangeGap in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _rangeGap(Wrap<String> w);

	public String getRangeGap() {
		return rangeGap;
	}
	public void setRangeGap(String o) {
		this.rangeGap = PageLayout.staticSetRangeGap(siteRequest_, o);
	}
	public static String staticSetRangeGap(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout rangeGapInit() {
		Wrap<String> rangeGapWrap = new Wrap<String>().var("rangeGap");
		if(rangeGap == null) {
			_rangeGap(rangeGapWrap);
			Optional.ofNullable(rangeGapWrap.getO()).ifPresent(o -> {
				setRangeGap(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchRangeGap(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrRangeGap(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRangeGap(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchRangeGap(siteRequest_, PageLayout.staticSetRangeGap(siteRequest_, o)).toString();
	}

	//////////////
	// rangeEnd //
	//////////////


	/**	 The entity rangeEnd
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = ComputateZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ComputateZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime rangeEnd;

	/**	<br> The entity rangeEnd
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:rangeEnd">Find the entity rangeEnd in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _rangeEnd(Wrap<ZonedDateTime> w);

	public ZonedDateTime getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(ZonedDateTime rangeEnd) {
		this.rangeEnd = Optional.ofNullable(rangeEnd).map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);
	}
	@JsonIgnore
	public void setRangeEnd(Instant o) {
		this.rangeEnd = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setRangeEnd(String o) {
		this.rangeEnd = PageLayout.staticSetRangeEnd(siteRequest_, o);
	}
	public static ZonedDateTime staticSetRangeEnd(SiteRequest siteRequest_, String o) {
		if(StringUtils.endsWith(o, "]"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER);
		else if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(Optional.ofNullable(siteRequest_).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))).truncatedTo(ChronoUnit.MILLIS);
		else if(StringUtils.contains(o, "T"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	@JsonIgnore
	public void setRangeEnd(Date o) {
		this.rangeEnd = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	protected PageLayout rangeEndInit() {
		Wrap<ZonedDateTime> rangeEndWrap = new Wrap<ZonedDateTime>().var("rangeEnd");
		if(rangeEnd == null) {
			_rangeEnd(rangeEndWrap);
			Optional.ofNullable(rangeEndWrap.getO()).ifPresent(o -> {
				setRangeEnd(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchRangeEnd(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrRangeEnd(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchRangeEnd(siteRequest_, PageLayout.staticSetRangeEnd(siteRequest_, o));
	}

	public static String staticSearchFqRangeEnd(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchRangeEnd(siteRequest_, PageLayout.staticSetRangeEnd(siteRequest_, o)).toString();
	}

	////////////////
	// rangeStart //
	////////////////


	/**	 The entity rangeStart
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = ComputateZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ComputateZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime rangeStart;

	/**	<br> The entity rangeStart
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:rangeStart">Find the entity rangeStart in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _rangeStart(Wrap<ZonedDateTime> w);

	public ZonedDateTime getRangeStart() {
		return rangeStart;
	}

	public void setRangeStart(ZonedDateTime rangeStart) {
		this.rangeStart = Optional.ofNullable(rangeStart).map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);
	}
	@JsonIgnore
	public void setRangeStart(Instant o) {
		this.rangeStart = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setRangeStart(String o) {
		this.rangeStart = PageLayout.staticSetRangeStart(siteRequest_, o);
	}
	public static ZonedDateTime staticSetRangeStart(SiteRequest siteRequest_, String o) {
		if(StringUtils.endsWith(o, "]"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER);
		else if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(Optional.ofNullable(siteRequest_).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))).truncatedTo(ChronoUnit.MILLIS);
		else if(StringUtils.contains(o, "T"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	@JsonIgnore
	public void setRangeStart(Date o) {
		this.rangeStart = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	protected PageLayout rangeStartInit() {
		Wrap<ZonedDateTime> rangeStartWrap = new Wrap<ZonedDateTime>().var("rangeStart");
		if(rangeStart == null) {
			_rangeStart(rangeStartWrap);
			Optional.ofNullable(rangeStartWrap.getO()).ifPresent(o -> {
				setRangeStart(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchRangeStart(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrRangeStart(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchRangeStart(siteRequest_, PageLayout.staticSetRangeStart(siteRequest_, o));
	}

	public static String staticSearchFqRangeStart(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchRangeStart(siteRequest_, PageLayout.staticSetRangeStart(siteRequest_, o)).toString();
	}

	///////////////////////
	// defaultRangeStats //
	///////////////////////


	/**	 The entity defaultRangeStats
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject defaultRangeStats;

	/**	<br> The entity defaultRangeStats
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultRangeStats">Find the entity defaultRangeStats in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultRangeStats(Wrap<JsonObject> w);

	public JsonObject getDefaultRangeStats() {
		return defaultRangeStats;
	}

	public void setDefaultRangeStats(JsonObject defaultRangeStats) {
		this.defaultRangeStats = defaultRangeStats;
	}
	@JsonIgnore
	public void setDefaultRangeStats(String o) {
		this.defaultRangeStats = PageLayout.staticSetDefaultRangeStats(siteRequest_, o);
	}
	public static JsonObject staticSetDefaultRangeStats(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected PageLayout defaultRangeStatsInit() {
		Wrap<JsonObject> defaultRangeStatsWrap = new Wrap<JsonObject>().var("defaultRangeStats");
		if(defaultRangeStats == null) {
			_defaultRangeStats(defaultRangeStatsWrap);
			Optional.ofNullable(defaultRangeStatsWrap.getO()).ifPresent(o -> {
				setDefaultRangeStats(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDefaultRangeStats(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrDefaultRangeStats(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultRangeStats(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultRangeStats(siteRequest_, PageLayout.staticSetDefaultRangeStats(siteRequest_, o)).toString();
	}

	/////////////////////
	// defaultRangeGap //
	/////////////////////


	/**	 The entity defaultRangeGap
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String defaultRangeGap;

	/**	<br> The entity defaultRangeGap
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultRangeGap">Find the entity defaultRangeGap in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultRangeGap(Wrap<String> w);

	public String getDefaultRangeGap() {
		return defaultRangeGap;
	}
	public void setDefaultRangeGap(String o) {
		this.defaultRangeGap = PageLayout.staticSetDefaultRangeGap(siteRequest_, o);
	}
	public static String staticSetDefaultRangeGap(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout defaultRangeGapInit() {
		Wrap<String> defaultRangeGapWrap = new Wrap<String>().var("defaultRangeGap");
		if(defaultRangeGap == null) {
			_defaultRangeGap(defaultRangeGapWrap);
			Optional.ofNullable(defaultRangeGapWrap.getO()).ifPresent(o -> {
				setDefaultRangeGap(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDefaultRangeGap(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultRangeGap(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultRangeGap(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultRangeGap(siteRequest_, PageLayout.staticSetDefaultRangeGap(siteRequest_, o)).toString();
	}

	/////////////////////
	// defaultRangeEnd //
	/////////////////////


	/**	 The entity defaultRangeEnd
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = ComputateZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ComputateZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime defaultRangeEnd;

	/**	<br> The entity defaultRangeEnd
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultRangeEnd">Find the entity defaultRangeEnd in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultRangeEnd(Wrap<ZonedDateTime> w);

	public ZonedDateTime getDefaultRangeEnd() {
		return defaultRangeEnd;
	}

	public void setDefaultRangeEnd(ZonedDateTime defaultRangeEnd) {
		this.defaultRangeEnd = Optional.ofNullable(defaultRangeEnd).map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);
	}
	@JsonIgnore
	public void setDefaultRangeEnd(Instant o) {
		this.defaultRangeEnd = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setDefaultRangeEnd(String o) {
		this.defaultRangeEnd = PageLayout.staticSetDefaultRangeEnd(siteRequest_, o);
	}
	public static ZonedDateTime staticSetDefaultRangeEnd(SiteRequest siteRequest_, String o) {
		if(StringUtils.endsWith(o, "]"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER);
		else if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(Optional.ofNullable(siteRequest_).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))).truncatedTo(ChronoUnit.MILLIS);
		else if(StringUtils.contains(o, "T"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	@JsonIgnore
	public void setDefaultRangeEnd(Date o) {
		this.defaultRangeEnd = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	protected PageLayout defaultRangeEndInit() {
		Wrap<ZonedDateTime> defaultRangeEndWrap = new Wrap<ZonedDateTime>().var("defaultRangeEnd");
		if(defaultRangeEnd == null) {
			_defaultRangeEnd(defaultRangeEndWrap);
			Optional.ofNullable(defaultRangeEndWrap.getO()).ifPresent(o -> {
				setDefaultRangeEnd(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDefaultRangeEnd(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrDefaultRangeEnd(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultRangeEnd(siteRequest_, PageLayout.staticSetDefaultRangeEnd(siteRequest_, o));
	}

	public static String staticSearchFqDefaultRangeEnd(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultRangeEnd(siteRequest_, PageLayout.staticSetDefaultRangeEnd(siteRequest_, o)).toString();
	}

	///////////////////////
	// defaultRangeStart //
	///////////////////////


	/**	 The entity defaultRangeStart
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = ComputateZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ComputateZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime defaultRangeStart;

	/**	<br> The entity defaultRangeStart
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultRangeStart">Find the entity defaultRangeStart in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultRangeStart(Wrap<ZonedDateTime> w);

	public ZonedDateTime getDefaultRangeStart() {
		return defaultRangeStart;
	}

	public void setDefaultRangeStart(ZonedDateTime defaultRangeStart) {
		this.defaultRangeStart = Optional.ofNullable(defaultRangeStart).map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);
	}
	@JsonIgnore
	public void setDefaultRangeStart(Instant o) {
		this.defaultRangeStart = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setDefaultRangeStart(String o) {
		this.defaultRangeStart = PageLayout.staticSetDefaultRangeStart(siteRequest_, o);
	}
	public static ZonedDateTime staticSetDefaultRangeStart(SiteRequest siteRequest_, String o) {
		if(StringUtils.endsWith(o, "]"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER);
		else if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(Optional.ofNullable(siteRequest_).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))).truncatedTo(ChronoUnit.MILLIS);
		else if(StringUtils.contains(o, "T"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	@JsonIgnore
	public void setDefaultRangeStart(Date o) {
		this.defaultRangeStart = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	protected PageLayout defaultRangeStartInit() {
		Wrap<ZonedDateTime> defaultRangeStartWrap = new Wrap<ZonedDateTime>().var("defaultRangeStart");
		if(defaultRangeStart == null) {
			_defaultRangeStart(defaultRangeStartWrap);
			Optional.ofNullable(defaultRangeStartWrap.getO()).ifPresent(o -> {
				setDefaultRangeStart(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDefaultRangeStart(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrDefaultRangeStart(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultRangeStart(siteRequest_, PageLayout.staticSetDefaultRangeStart(siteRequest_, o));
	}

	public static String staticSearchFqDefaultRangeStart(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultRangeStart(siteRequest_, PageLayout.staticSetDefaultRangeStart(siteRequest_, o)).toString();
	}

	/////////////////////
	// defaultRangeVar //
	/////////////////////


	/**	 The entity defaultRangeVar
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String defaultRangeVar;

	/**	<br> The entity defaultRangeVar
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultRangeVar">Find the entity defaultRangeVar in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultRangeVar(Wrap<String> w);

	public String getDefaultRangeVar() {
		return defaultRangeVar;
	}
	public void setDefaultRangeVar(String o) {
		this.defaultRangeVar = PageLayout.staticSetDefaultRangeVar(siteRequest_, o);
	}
	public static String staticSetDefaultRangeVar(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout defaultRangeVarInit() {
		Wrap<String> defaultRangeVarWrap = new Wrap<String>().var("defaultRangeVar");
		if(defaultRangeVar == null) {
			_defaultRangeVar(defaultRangeVarWrap);
			Optional.ofNullable(defaultRangeVarWrap.getO()).ifPresent(o -> {
				setDefaultRangeVar(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDefaultRangeVar(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultRangeVar(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultRangeVar(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultRangeVar(siteRequest_, PageLayout.staticSetDefaultRangeVar(siteRequest_, o)).toString();
	}

	//////////////////////
	// defaultFacetSort //
	//////////////////////


	/**	 The entity defaultFacetSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String defaultFacetSort;

	/**	<br> The entity defaultFacetSort
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultFacetSort">Find the entity defaultFacetSort in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultFacetSort(Wrap<String> w);

	public String getDefaultFacetSort() {
		return defaultFacetSort;
	}
	public void setDefaultFacetSort(String o) {
		this.defaultFacetSort = PageLayout.staticSetDefaultFacetSort(siteRequest_, o);
	}
	public static String staticSetDefaultFacetSort(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout defaultFacetSortInit() {
		Wrap<String> defaultFacetSortWrap = new Wrap<String>().var("defaultFacetSort");
		if(defaultFacetSort == null) {
			_defaultFacetSort(defaultFacetSortWrap);
			Optional.ofNullable(defaultFacetSortWrap.getO()).ifPresent(o -> {
				setDefaultFacetSort(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDefaultFacetSort(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultFacetSort(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultFacetSort(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultFacetSort(siteRequest_, PageLayout.staticSetDefaultFacetSort(siteRequest_, o)).toString();
	}

	///////////////////////
	// defaultFacetLimit //
	///////////////////////


	/**	 The entity defaultFacetLimit
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer defaultFacetLimit;

	/**	<br> The entity defaultFacetLimit
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultFacetLimit">Find the entity defaultFacetLimit in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultFacetLimit(Wrap<Integer> w);

	public Integer getDefaultFacetLimit() {
		return defaultFacetLimit;
	}

	public void setDefaultFacetLimit(Integer defaultFacetLimit) {
		this.defaultFacetLimit = defaultFacetLimit;
	}
	@JsonIgnore
	public void setDefaultFacetLimit(String o) {
		this.defaultFacetLimit = PageLayout.staticSetDefaultFacetLimit(siteRequest_, o);
	}
	public static Integer staticSetDefaultFacetLimit(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PageLayout defaultFacetLimitInit() {
		Wrap<Integer> defaultFacetLimitWrap = new Wrap<Integer>().var("defaultFacetLimit");
		if(defaultFacetLimit == null) {
			_defaultFacetLimit(defaultFacetLimitWrap);
			Optional.ofNullable(defaultFacetLimitWrap.getO()).ifPresent(o -> {
				setDefaultFacetLimit(o);
			});
		}
		return (PageLayout)this;
	}

	public static Integer staticSearchDefaultFacetLimit(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrDefaultFacetLimit(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultFacetLimit(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultFacetLimit(siteRequest_, PageLayout.staticSetDefaultFacetLimit(siteRequest_, o)).toString();
	}

	//////////////////////////
	// defaultFacetMinCount //
	//////////////////////////


	/**	 The entity defaultFacetMinCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer defaultFacetMinCount;

	/**	<br> The entity defaultFacetMinCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultFacetMinCount">Find the entity defaultFacetMinCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultFacetMinCount(Wrap<Integer> w);

	public Integer getDefaultFacetMinCount() {
		return defaultFacetMinCount;
	}

	public void setDefaultFacetMinCount(Integer defaultFacetMinCount) {
		this.defaultFacetMinCount = defaultFacetMinCount;
	}
	@JsonIgnore
	public void setDefaultFacetMinCount(String o) {
		this.defaultFacetMinCount = PageLayout.staticSetDefaultFacetMinCount(siteRequest_, o);
	}
	public static Integer staticSetDefaultFacetMinCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PageLayout defaultFacetMinCountInit() {
		Wrap<Integer> defaultFacetMinCountWrap = new Wrap<Integer>().var("defaultFacetMinCount");
		if(defaultFacetMinCount == null) {
			_defaultFacetMinCount(defaultFacetMinCountWrap);
			Optional.ofNullable(defaultFacetMinCountWrap.getO()).ifPresent(o -> {
				setDefaultFacetMinCount(o);
			});
		}
		return (PageLayout)this;
	}

	public static Integer staticSearchDefaultFacetMinCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrDefaultFacetMinCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultFacetMinCount(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultFacetMinCount(siteRequest_, PageLayout.staticSetDefaultFacetMinCount(siteRequest_, o)).toString();
	}

	//////////////////////////
	// defaultPivotMinCount //
	//////////////////////////


	/**	 The entity defaultPivotMinCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer defaultPivotMinCount;

	/**	<br> The entity defaultPivotMinCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:defaultPivotMinCount">Find the entity defaultPivotMinCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultPivotMinCount(Wrap<Integer> w);

	public Integer getDefaultPivotMinCount() {
		return defaultPivotMinCount;
	}

	public void setDefaultPivotMinCount(Integer defaultPivotMinCount) {
		this.defaultPivotMinCount = defaultPivotMinCount;
	}
	@JsonIgnore
	public void setDefaultPivotMinCount(String o) {
		this.defaultPivotMinCount = PageLayout.staticSetDefaultPivotMinCount(siteRequest_, o);
	}
	public static Integer staticSetDefaultPivotMinCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PageLayout defaultPivotMinCountInit() {
		Wrap<Integer> defaultPivotMinCountWrap = new Wrap<Integer>().var("defaultPivotMinCount");
		if(defaultPivotMinCount == null) {
			_defaultPivotMinCount(defaultPivotMinCountWrap);
			Optional.ofNullable(defaultPivotMinCountWrap.getO()).ifPresent(o -> {
				setDefaultPivotMinCount(o);
			});
		}
		return (PageLayout)this;
	}

	public static Integer staticSearchDefaultPivotMinCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrDefaultPivotMinCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultPivotMinCount(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDefaultPivotMinCount(siteRequest_, PageLayout.staticSetDefaultPivotMinCount(siteRequest_, o)).toString();
	}

	//////////////////////////
	// DEFAULT_MAP_LOCATION //
	//////////////////////////


	/**	 The entity DEFAULT_MAP_LOCATION
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonObjectDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonObject DEFAULT_MAP_LOCATION;

	/**	<br> The entity DEFAULT_MAP_LOCATION
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:DEFAULT_MAP_LOCATION">Find the entity DEFAULT_MAP_LOCATION in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _DEFAULT_MAP_LOCATION(Wrap<JsonObject> w);

	public JsonObject getDEFAULT_MAP_LOCATION() {
		return DEFAULT_MAP_LOCATION;
	}

	public void setDEFAULT_MAP_LOCATION(JsonObject DEFAULT_MAP_LOCATION) {
		this.DEFAULT_MAP_LOCATION = DEFAULT_MAP_LOCATION;
	}
	@JsonIgnore
	public void setDEFAULT_MAP_LOCATION(String o) {
		this.DEFAULT_MAP_LOCATION = PageLayout.staticSetDEFAULT_MAP_LOCATION(siteRequest_, o);
	}
	public static JsonObject staticSetDEFAULT_MAP_LOCATION(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected PageLayout DEFAULT_MAP_LOCATIONInit() {
		Wrap<JsonObject> DEFAULT_MAP_LOCATIONWrap = new Wrap<JsonObject>().var("DEFAULT_MAP_LOCATION");
		if(DEFAULT_MAP_LOCATION == null) {
			_DEFAULT_MAP_LOCATION(DEFAULT_MAP_LOCATIONWrap);
			Optional.ofNullable(DEFAULT_MAP_LOCATIONWrap.getO()).ifPresent(o -> {
				setDEFAULT_MAP_LOCATION(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchDEFAULT_MAP_LOCATION(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrDEFAULT_MAP_LOCATION(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDEFAULT_MAP_LOCATION(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDEFAULT_MAP_LOCATION(siteRequest_, PageLayout.staticSetDEFAULT_MAP_LOCATION(siteRequest_, o)).toString();
	}

	//////////////////////
	// DEFAULT_MAP_ZOOM //
	//////////////////////


	/**	 The entity DEFAULT_MAP_ZOOM
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal DEFAULT_MAP_ZOOM;

	/**	<br> The entity DEFAULT_MAP_ZOOM
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:DEFAULT_MAP_ZOOM">Find the entity DEFAULT_MAP_ZOOM in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _DEFAULT_MAP_ZOOM(Wrap<BigDecimal> w);

	public BigDecimal getDEFAULT_MAP_ZOOM() {
		return DEFAULT_MAP_ZOOM;
	}

	public void setDEFAULT_MAP_ZOOM(BigDecimal DEFAULT_MAP_ZOOM) {
		this.DEFAULT_MAP_ZOOM = DEFAULT_MAP_ZOOM;
	}
	@JsonIgnore
	public void setDEFAULT_MAP_ZOOM(String o) {
		this.DEFAULT_MAP_ZOOM = PageLayout.staticSetDEFAULT_MAP_ZOOM(siteRequest_, o);
	}
	public static BigDecimal staticSetDEFAULT_MAP_ZOOM(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setDEFAULT_MAP_ZOOM(Double o) {
		setDEFAULT_MAP_ZOOM(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setDEFAULT_MAP_ZOOM(Integer o) {
		setDEFAULT_MAP_ZOOM(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setDEFAULT_MAP_ZOOM(Number o) {
		setDEFAULT_MAP_ZOOM(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected PageLayout DEFAULT_MAP_ZOOMInit() {
		Wrap<BigDecimal> DEFAULT_MAP_ZOOMWrap = new Wrap<BigDecimal>().var("DEFAULT_MAP_ZOOM");
		if(DEFAULT_MAP_ZOOM == null) {
			_DEFAULT_MAP_ZOOM(DEFAULT_MAP_ZOOMWrap);
			Optional.ofNullable(DEFAULT_MAP_ZOOMWrap.getO()).ifPresent(o -> {
				setDEFAULT_MAP_ZOOM(o);
			});
		}
		return (PageLayout)this;
	}

	public static Double staticSearchDEFAULT_MAP_ZOOM(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrDEFAULT_MAP_ZOOM(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDEFAULT_MAP_ZOOM(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchDEFAULT_MAP_ZOOM(siteRequest_, PageLayout.staticSetDEFAULT_MAP_ZOOM(siteRequest_, o)).toString();
	}

	//////////////
	// queryStr //
	//////////////


	/**	 The entity queryStr
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String queryStr;

	/**	<br> The entity queryStr
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:queryStr">Find the entity queryStr in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _queryStr(Wrap<String> w);

	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String o) {
		this.queryStr = PageLayout.staticSetQueryStr(siteRequest_, o);
	}
	public static String staticSetQueryStr(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout queryStrInit() {
		Wrap<String> queryStrWrap = new Wrap<String>().var("queryStr");
		if(queryStr == null) {
			_queryStr(queryStrWrap);
			Optional.ofNullable(queryStrWrap.getO()).ifPresent(o -> {
				setQueryStr(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchQueryStr(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrQueryStr(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqQueryStr(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchQueryStr(siteRequest_, PageLayout.staticSetQueryStr(siteRequest_, o)).toString();
	}

	//////////////////
	// promiseAfter //
	//////////////////


	/**	 The entity promiseAfter
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected Void promiseAfter;

	/**	<br> The entity promiseAfter
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:promiseAfter">Find the entity promiseAfter in Solr</a>
	 * <br>
	 * @param promise is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _promiseAfter(Promise<Void> promise);

	public Void getPromiseAfter() {
		return promiseAfter;
	}

	public void setPromiseAfter(Void promiseAfter) {
		this.promiseAfter = promiseAfter;
	}
	public static Void staticSetPromiseAfter(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected Future<Void> promiseAfterPromise() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		_promiseAfter(promise2);
		promise2.future().onSuccess(o -> {
			setPromiseAfter(o);
			promise.complete(o);
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	//////////////////
	// pageImageUri //
	//////////////////


	/**	 The entity pageImageUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageImageUri;

	/**	<br> The entity pageImageUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageImageUri">Find the entity pageImageUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageUri(Wrap<String> w);

	public String getPageImageUri() {
		return pageImageUri;
	}
	public void setPageImageUri(String o) {
		this.pageImageUri = PageLayout.staticSetPageImageUri(siteRequest_, o);
	}
	public static String staticSetPageImageUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout pageImageUriInit() {
		Wrap<String> pageImageUriWrap = new Wrap<String>().var("pageImageUri");
		if(pageImageUri == null) {
			_pageImageUri(pageImageUriWrap);
			Optional.ofNullable(pageImageUriWrap.getO()).ifPresent(o -> {
				setPageImageUri(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchPageImageUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageImageUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageUri(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageImageUri(siteRequest_, PageLayout.staticSetPageImageUri(siteRequest_, o)).toString();
	}

	////////////////////
	// pageImageWidth //
	////////////////////


	/**	 The entity pageImageWidth
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer pageImageWidth;

	/**	<br> The entity pageImageWidth
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageImageWidth">Find the entity pageImageWidth in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageWidth(Wrap<Integer> c);

	public Integer getPageImageWidth() {
		return pageImageWidth;
	}

	public void setPageImageWidth(Integer pageImageWidth) {
		this.pageImageWidth = pageImageWidth;
	}
	@JsonIgnore
	public void setPageImageWidth(String o) {
		this.pageImageWidth = PageLayout.staticSetPageImageWidth(siteRequest_, o);
	}
	public static Integer staticSetPageImageWidth(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PageLayout pageImageWidthInit() {
		Wrap<Integer> pageImageWidthWrap = new Wrap<Integer>().var("pageImageWidth");
		if(pageImageWidth == null) {
			_pageImageWidth(pageImageWidthWrap);
			Optional.ofNullable(pageImageWidthWrap.getO()).ifPresent(o -> {
				setPageImageWidth(o);
			});
		}
		return (PageLayout)this;
	}

	public static Integer staticSearchPageImageWidth(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrPageImageWidth(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageWidth(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageImageWidth(siteRequest_, PageLayout.staticSetPageImageWidth(siteRequest_, o)).toString();
	}

	/////////////////////
	// pageImageHeight //
	/////////////////////


	/**	 The entity pageImageHeight
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer pageImageHeight;

	/**	<br> The entity pageImageHeight
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageImageHeight">Find the entity pageImageHeight in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageHeight(Wrap<Integer> c);

	public Integer getPageImageHeight() {
		return pageImageHeight;
	}

	public void setPageImageHeight(Integer pageImageHeight) {
		this.pageImageHeight = pageImageHeight;
	}
	@JsonIgnore
	public void setPageImageHeight(String o) {
		this.pageImageHeight = PageLayout.staticSetPageImageHeight(siteRequest_, o);
	}
	public static Integer staticSetPageImageHeight(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PageLayout pageImageHeightInit() {
		Wrap<Integer> pageImageHeightWrap = new Wrap<Integer>().var("pageImageHeight");
		if(pageImageHeight == null) {
			_pageImageHeight(pageImageHeightWrap);
			Optional.ofNullable(pageImageHeightWrap.getO()).ifPresent(o -> {
				setPageImageHeight(o);
			});
		}
		return (PageLayout)this;
	}

	public static Integer staticSearchPageImageHeight(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrPageImageHeight(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageHeight(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageImageHeight(siteRequest_, PageLayout.staticSetPageImageHeight(siteRequest_, o)).toString();
	}

	/////////////////
	// pageVideoId //
	/////////////////


	/**	 The entity pageVideoId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageVideoId;

	/**	<br> The entity pageVideoId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageVideoId">Find the entity pageVideoId in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageVideoId(Wrap<String> c);

	public String getPageVideoId() {
		return pageVideoId;
	}
	public void setPageVideoId(String o) {
		this.pageVideoId = PageLayout.staticSetPageVideoId(siteRequest_, o);
	}
	public static String staticSetPageVideoId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout pageVideoIdInit() {
		Wrap<String> pageVideoIdWrap = new Wrap<String>().var("pageVideoId");
		if(pageVideoId == null) {
			_pageVideoId(pageVideoIdWrap);
			Optional.ofNullable(pageVideoIdWrap.getO()).ifPresent(o -> {
				setPageVideoId(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchPageVideoId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageVideoId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageVideoId(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageVideoId(siteRequest_, PageLayout.staticSetPageVideoId(siteRequest_, o)).toString();
	}

	///////////////
	// classIcon //
	///////////////


	/**	 The entity classIcon
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String classIcon;

	/**	<br> The entity classIcon
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:classIcon">Find the entity classIcon in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _classIcon(Wrap<String> w);

	public String getClassIcon() {
		return classIcon;
	}
	public void setClassIcon(String o) {
		this.classIcon = PageLayout.staticSetClassIcon(siteRequest_, o);
	}
	public static String staticSetClassIcon(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout classIconInit() {
		Wrap<String> classIconWrap = new Wrap<String>().var("classIcon");
		if(classIcon == null) {
			_classIcon(classIconWrap);
			Optional.ofNullable(classIconWrap.getO()).ifPresent(o -> {
				setClassIcon(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchClassIcon(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassIcon(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassIcon(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchClassIcon(siteRequest_, PageLayout.staticSetClassIcon(siteRequest_, o)).toString();
	}

	/////////////////////
	// pageDescription //
	/////////////////////


	/**	 The entity pageDescription
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageDescription;

	/**	<br> The entity pageDescription
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.PageLayout&fq=entiteVar_enUS_indexed_string:pageDescription">Find the entity pageDescription in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageDescription(Wrap<String> w);

	public String getPageDescription() {
		return pageDescription;
	}
	public void setPageDescription(String o) {
		this.pageDescription = PageLayout.staticSetPageDescription(siteRequest_, o);
	}
	public static String staticSetPageDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected PageLayout pageDescriptionInit() {
		Wrap<String> pageDescriptionWrap = new Wrap<String>().var("pageDescription");
		if(pageDescription == null) {
			_pageDescription(pageDescriptionWrap);
			Optional.ofNullable(pageDescriptionWrap.getO()).ifPresent(o -> {
				setPageDescription(o);
			});
		}
		return (PageLayout)this;
	}

	public static String staticSearchPageDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageDescription(SiteRequest siteRequest_, String o) {
		return PageLayout.staticSearchPageDescription(siteRequest_, PageLayout.staticSetPageDescription(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<PageLayoutGen<DEV>> promiseDeepPageLayout(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepPageLayout();
	}

	public Future<PageLayoutGen<DEV>> promiseDeepPageLayout() {
		Promise<PageLayoutGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promisePageLayout(promise2);
		promise2.future().onSuccess(a -> {
			promise.complete(this);
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promisePageLayout(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				webClientInit();
				vertxInit();
				siteRequest_Init();
				langInit();
				requestVarsInit();
				serviceRequestInit();
				staticBaseUrlInit();
				siteBaseUrlInit();
				siteAuthUrlInit();
				siteAuthRealmInit();
				fontAwesomeKitInit();
				facebookGraphVersionInit();
				facebookAppIdInit();
				pageUriInit();
				pageIdInit();
				apiUriInit();
				pageMethodInit();
				paramsInit();
				userKeyInit();
				userFullNameInit();
				userNameInit();
				userEmailInit();
				logoutUrlInit();
				promise2.complete();
			} catch(Exception ex) {
				promise2.fail(ex);
			}
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			promiseBeforePromise().onSuccess(promiseBefore -> {
				promise2.complete();
			}).onFailure(ex -> {
				promise2.fail(ex);
			});
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				classSimpleNameInit();
				pageTitleInit();
				scopesInit();
				roleForWriteInit();
				roleForReadInit();
				statsInit();
				facetCountsInit();
				paginationInit();
				defaultFieldListVarsInit();
				defaultSortVarsInit();
				defaultStatsVarsInit();
				defaultPivotVarsInit();
				varsQInit();
				varsFqInit();
				varsFqCountInit();
				varsRangeInit();
				queryInit();
				pageResponseInit();
				defaultZoneIdInit();
				defaultTimeZoneInit();
				defaultLocaleIdInit();
				rowsInit();
				startInit();
				defaultLocaleInit();
				rangeGapInit();
				rangeEndInit();
				rangeStartInit();
				defaultRangeStatsInit();
				defaultRangeGapInit();
				defaultRangeEndInit();
				defaultRangeStartInit();
				defaultRangeVarInit();
				defaultFacetSortInit();
				defaultFacetLimitInit();
				defaultFacetMinCountInit();
				defaultPivotMinCountInit();
				DEFAULT_MAP_LOCATIONInit();
				DEFAULT_MAP_ZOOMInit();
				queryStrInit();
				promise2.complete();
			} catch(Exception ex) {
				promise2.fail(ex);
			}
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			promiseAfterPromise().onSuccess(promiseAfter -> {
				promise2.complete();
			}).onFailure(ex -> {
				promise2.fail(ex);
			});
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				pageImageUriInit();
				pageImageWidthInit();
				pageImageHeightInit();
				pageVideoIdInit();
				classIconInit();
				pageDescriptionInit();
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

	public Future<? extends PageLayoutGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepPageLayout(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPageLayout(SiteRequest siteRequest_) {
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestPageLayout(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPageLayout(v);
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
	public Object obtainPageLayout(String var) {
		PageLayout oPageLayout = (PageLayout)this;
		switch(var) {
			case "webClient":
				return oPageLayout.webClient;
			case "vertx":
				return oPageLayout.vertx;
			case "siteRequest_":
				return oPageLayout.siteRequest_;
			case "lang":
				return oPageLayout.lang;
			case "requestVars":
				return oPageLayout.requestVars;
			case "serviceRequest":
				return oPageLayout.serviceRequest;
			case "staticBaseUrl":
				return oPageLayout.staticBaseUrl;
			case "siteBaseUrl":
				return oPageLayout.siteBaseUrl;
			case "siteAuthUrl":
				return oPageLayout.siteAuthUrl;
			case "siteAuthRealm":
				return oPageLayout.siteAuthRealm;
			case "fontAwesomeKit":
				return oPageLayout.fontAwesomeKit;
			case "facebookGraphVersion":
				return oPageLayout.facebookGraphVersion;
			case "facebookAppId":
				return oPageLayout.facebookAppId;
			case "pageUri":
				return oPageLayout.pageUri;
			case "pageId":
				return oPageLayout.pageId;
			case "apiUri":
				return oPageLayout.apiUri;
			case "pageMethod":
				return oPageLayout.pageMethod;
			case "params":
				return oPageLayout.params;
			case "userKey":
				return oPageLayout.userKey;
			case "userFullName":
				return oPageLayout.userFullName;
			case "userName":
				return oPageLayout.userName;
			case "userEmail":
				return oPageLayout.userEmail;
			case "logoutUrl":
				return oPageLayout.logoutUrl;
			case "promiseBefore":
				return oPageLayout.promiseBefore;
			case "classSimpleName":
				return oPageLayout.classSimpleName;
			case "pageTitle":
				return oPageLayout.pageTitle;
			case "scopes":
				return oPageLayout.scopes;
			case "roleForWrite":
				return oPageLayout.roleForWrite;
			case "roleForRead":
				return oPageLayout.roleForRead;
			case "stats":
				return oPageLayout.stats;
			case "facetCounts":
				return oPageLayout.facetCounts;
			case "pagination":
				return oPageLayout.pagination;
			case "defaultFieldListVars":
				return oPageLayout.defaultFieldListVars;
			case "defaultSortVars":
				return oPageLayout.defaultSortVars;
			case "defaultStatsVars":
				return oPageLayout.defaultStatsVars;
			case "defaultPivotVars":
				return oPageLayout.defaultPivotVars;
			case "varsQ":
				return oPageLayout.varsQ;
			case "varsFq":
				return oPageLayout.varsFq;
			case "varsFqCount":
				return oPageLayout.varsFqCount;
			case "varsRange":
				return oPageLayout.varsRange;
			case "query":
				return oPageLayout.query;
			case "pageResponse":
				return oPageLayout.pageResponse;
			case "defaultZoneId":
				return oPageLayout.defaultZoneId;
			case "defaultTimeZone":
				return oPageLayout.defaultTimeZone;
			case "defaultLocaleId":
				return oPageLayout.defaultLocaleId;
			case "rows":
				return oPageLayout.rows;
			case "start":
				return oPageLayout.start;
			case "defaultLocale":
				return oPageLayout.defaultLocale;
			case "rangeGap":
				return oPageLayout.rangeGap;
			case "rangeEnd":
				return oPageLayout.rangeEnd;
			case "rangeStart":
				return oPageLayout.rangeStart;
			case "defaultRangeStats":
				return oPageLayout.defaultRangeStats;
			case "defaultRangeGap":
				return oPageLayout.defaultRangeGap;
			case "defaultRangeEnd":
				return oPageLayout.defaultRangeEnd;
			case "defaultRangeStart":
				return oPageLayout.defaultRangeStart;
			case "defaultRangeVar":
				return oPageLayout.defaultRangeVar;
			case "defaultFacetSort":
				return oPageLayout.defaultFacetSort;
			case "defaultFacetLimit":
				return oPageLayout.defaultFacetLimit;
			case "defaultFacetMinCount":
				return oPageLayout.defaultFacetMinCount;
			case "defaultPivotMinCount":
				return oPageLayout.defaultPivotMinCount;
			case "DEFAULT_MAP_LOCATION":
				return oPageLayout.DEFAULT_MAP_LOCATION;
			case "DEFAULT_MAP_ZOOM":
				return oPageLayout.DEFAULT_MAP_ZOOM;
			case "queryStr":
				return oPageLayout.queryStr;
			case "promiseAfter":
				return oPageLayout.promiseAfter;
			case "pageImageUri":
				return oPageLayout.pageImageUri;
			case "pageImageWidth":
				return oPageLayout.pageImageWidth;
			case "pageImageHeight":
				return oPageLayout.pageImageHeight;
			case "pageVideoId":
				return oPageLayout.pageVideoId;
			case "classIcon":
				return oPageLayout.classIcon;
			case "pageDescription":
				return oPageLayout.pageDescription;
			default:
				return null;
		}
	}

	///////////////
	// relate //
	///////////////

	public boolean relateForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = relatePageLayout(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relatePageLayout(String var, Object val) {
		PageLayout oPageLayout = (PageLayout)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetPageLayout(entityVar,  siteRequest_, o);
	}
	public static Object staticSetPageLayout(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "lang":
			return PageLayout.staticSetLang(siteRequest_, o);
		case "staticBaseUrl":
			return PageLayout.staticSetStaticBaseUrl(siteRequest_, o);
		case "siteBaseUrl":
			return PageLayout.staticSetSiteBaseUrl(siteRequest_, o);
		case "siteAuthUrl":
			return PageLayout.staticSetSiteAuthUrl(siteRequest_, o);
		case "siteAuthRealm":
			return PageLayout.staticSetSiteAuthRealm(siteRequest_, o);
		case "fontAwesomeKit":
			return PageLayout.staticSetFontAwesomeKit(siteRequest_, o);
		case "facebookGraphVersion":
			return PageLayout.staticSetFacebookGraphVersion(siteRequest_, o);
		case "facebookAppId":
			return PageLayout.staticSetFacebookAppId(siteRequest_, o);
		case "pageUri":
			return PageLayout.staticSetPageUri(siteRequest_, o);
		case "pageId":
			return PageLayout.staticSetPageId(siteRequest_, o);
		case "apiUri":
			return PageLayout.staticSetApiUri(siteRequest_, o);
		case "pageMethod":
			return PageLayout.staticSetPageMethod(siteRequest_, o);
		case "params":
			return PageLayout.staticSetParams(siteRequest_, o);
		case "userKey":
			return PageLayout.staticSetUserKey(siteRequest_, o);
		case "userFullName":
			return PageLayout.staticSetUserFullName(siteRequest_, o);
		case "userName":
			return PageLayout.staticSetUserName(siteRequest_, o);
		case "userEmail":
			return PageLayout.staticSetUserEmail(siteRequest_, o);
		case "logoutUrl":
			return PageLayout.staticSetLogoutUrl(siteRequest_, o);
		case "classSimpleName":
			return PageLayout.staticSetClassSimpleName(siteRequest_, o);
		case "pageTitle":
			return PageLayout.staticSetPageTitle(siteRequest_, o);
		case "scopes":
			return PageLayout.staticSetScopes(siteRequest_, o);
		case "roleForWrite":
			return PageLayout.staticSetRoleForWrite(siteRequest_, o);
		case "roleForRead":
			return PageLayout.staticSetRoleForRead(siteRequest_, o);
		case "pagination":
			return PageLayout.staticSetPagination(siteRequest_, o);
		case "defaultFieldListVars":
			return PageLayout.staticSetDefaultFieldListVars(siteRequest_, o);
		case "defaultSortVars":
			return PageLayout.staticSetDefaultSortVars(siteRequest_, o);
		case "defaultStatsVars":
			return PageLayout.staticSetDefaultStatsVars(siteRequest_, o);
		case "defaultPivotVars":
			return PageLayout.staticSetDefaultPivotVars(siteRequest_, o);
		case "varsQ":
			return PageLayout.staticSetVarsQ(siteRequest_, o);
		case "varsFq":
			return PageLayout.staticSetVarsFq(siteRequest_, o);
		case "varsFqCount":
			return PageLayout.staticSetVarsFqCount(siteRequest_, o);
		case "varsRange":
			return PageLayout.staticSetVarsRange(siteRequest_, o);
		case "query":
			return PageLayout.staticSetQuery(siteRequest_, o);
		case "pageResponse":
			return PageLayout.staticSetPageResponse(siteRequest_, o);
		case "defaultZoneId":
			return PageLayout.staticSetDefaultZoneId(siteRequest_, o);
		case "defaultLocaleId":
			return PageLayout.staticSetDefaultLocaleId(siteRequest_, o);
		case "rows":
			return PageLayout.staticSetRows(siteRequest_, o);
		case "start":
			return PageLayout.staticSetStart(siteRequest_, o);
		case "rangeGap":
			return PageLayout.staticSetRangeGap(siteRequest_, o);
		case "rangeEnd":
			return PageLayout.staticSetRangeEnd(siteRequest_, o);
		case "rangeStart":
			return PageLayout.staticSetRangeStart(siteRequest_, o);
		case "defaultRangeStats":
			return PageLayout.staticSetDefaultRangeStats(siteRequest_, o);
		case "defaultRangeGap":
			return PageLayout.staticSetDefaultRangeGap(siteRequest_, o);
		case "defaultRangeEnd":
			return PageLayout.staticSetDefaultRangeEnd(siteRequest_, o);
		case "defaultRangeStart":
			return PageLayout.staticSetDefaultRangeStart(siteRequest_, o);
		case "defaultRangeVar":
			return PageLayout.staticSetDefaultRangeVar(siteRequest_, o);
		case "defaultFacetSort":
			return PageLayout.staticSetDefaultFacetSort(siteRequest_, o);
		case "defaultFacetLimit":
			return PageLayout.staticSetDefaultFacetLimit(siteRequest_, o);
		case "defaultFacetMinCount":
			return PageLayout.staticSetDefaultFacetMinCount(siteRequest_, o);
		case "defaultPivotMinCount":
			return PageLayout.staticSetDefaultPivotMinCount(siteRequest_, o);
		case "DEFAULT_MAP_LOCATION":
			return PageLayout.staticSetDEFAULT_MAP_LOCATION(siteRequest_, o);
		case "DEFAULT_MAP_ZOOM":
			return PageLayout.staticSetDEFAULT_MAP_ZOOM(siteRequest_, o);
		case "queryStr":
			return PageLayout.staticSetQueryStr(siteRequest_, o);
		case "pageImageUri":
			return PageLayout.staticSetPageImageUri(siteRequest_, o);
		case "pageImageWidth":
			return PageLayout.staticSetPageImageWidth(siteRequest_, o);
		case "pageImageHeight":
			return PageLayout.staticSetPageImageHeight(siteRequest_, o);
		case "pageVideoId":
			return PageLayout.staticSetPageVideoId(siteRequest_, o);
		case "classIcon":
			return PageLayout.staticSetClassIcon(siteRequest_, o);
		case "pageDescription":
			return PageLayout.staticSetPageDescription(siteRequest_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchPageLayout(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchPageLayout(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "lang":
			return PageLayout.staticSearchLang(siteRequest_, (String)o);
		case "staticBaseUrl":
			return PageLayout.staticSearchStaticBaseUrl(siteRequest_, (String)o);
		case "siteBaseUrl":
			return PageLayout.staticSearchSiteBaseUrl(siteRequest_, (String)o);
		case "siteAuthUrl":
			return PageLayout.staticSearchSiteAuthUrl(siteRequest_, (String)o);
		case "siteAuthRealm":
			return PageLayout.staticSearchSiteAuthRealm(siteRequest_, (String)o);
		case "fontAwesomeKit":
			return PageLayout.staticSearchFontAwesomeKit(siteRequest_, (String)o);
		case "facebookGraphVersion":
			return PageLayout.staticSearchFacebookGraphVersion(siteRequest_, (String)o);
		case "facebookAppId":
			return PageLayout.staticSearchFacebookAppId(siteRequest_, (String)o);
		case "pageUri":
			return PageLayout.staticSearchPageUri(siteRequest_, (String)o);
		case "pageId":
			return PageLayout.staticSearchPageId(siteRequest_, (String)o);
		case "apiUri":
			return PageLayout.staticSearchApiUri(siteRequest_, (String)o);
		case "pageMethod":
			return PageLayout.staticSearchPageMethod(siteRequest_, (String)o);
		case "params":
			return PageLayout.staticSearchParams(siteRequest_, (JsonObject)o);
		case "userKey":
			return PageLayout.staticSearchUserKey(siteRequest_, (Long)o);
		case "userFullName":
			return PageLayout.staticSearchUserFullName(siteRequest_, (String)o);
		case "userName":
			return PageLayout.staticSearchUserName(siteRequest_, (String)o);
		case "userEmail":
			return PageLayout.staticSearchUserEmail(siteRequest_, (String)o);
		case "logoutUrl":
			return PageLayout.staticSearchLogoutUrl(siteRequest_, (String)o);
		case "classSimpleName":
			return PageLayout.staticSearchClassSimpleName(siteRequest_, (String)o);
		case "pageTitle":
			return PageLayout.staticSearchPageTitle(siteRequest_, (String)o);
		case "scopes":
			return PageLayout.staticSearchScopes(siteRequest_, (String)o);
		case "roleForWrite":
			return PageLayout.staticSearchRoleForWrite(siteRequest_, (String)o);
		case "roleForRead":
			return PageLayout.staticSearchRoleForRead(siteRequest_, (String)o);
		case "pagination":
			return PageLayout.staticSearchPagination(siteRequest_, (JsonObject)o);
		case "defaultFieldListVars":
			return PageLayout.staticSearchDefaultFieldListVars(siteRequest_, (String)o);
		case "defaultSortVars":
			return PageLayout.staticSearchDefaultSortVars(siteRequest_, (String)o);
		case "defaultStatsVars":
			return PageLayout.staticSearchDefaultStatsVars(siteRequest_, (String)o);
		case "defaultPivotVars":
			return PageLayout.staticSearchDefaultPivotVars(siteRequest_, (String)o);
		case "varsQ":
			return PageLayout.staticSearchVarsQ(siteRequest_, (JsonObject)o);
		case "varsFq":
			return PageLayout.staticSearchVarsFq(siteRequest_, (JsonObject)o);
		case "varsFqCount":
			return PageLayout.staticSearchVarsFqCount(siteRequest_, (Integer)o);
		case "varsRange":
			return PageLayout.staticSearchVarsRange(siteRequest_, (JsonObject)o);
		case "query":
			return PageLayout.staticSearchQuery(siteRequest_, (JsonObject)o);
		case "pageResponse":
			return PageLayout.staticSearchPageResponse(siteRequest_, (String)o);
		case "defaultZoneId":
			return PageLayout.staticSearchDefaultZoneId(siteRequest_, (String)o);
		case "defaultLocaleId":
			return PageLayout.staticSearchDefaultLocaleId(siteRequest_, (String)o);
		case "rows":
			return PageLayout.staticSearchRows(siteRequest_, (Long)o);
		case "start":
			return PageLayout.staticSearchStart(siteRequest_, (Long)o);
		case "rangeGap":
			return PageLayout.staticSearchRangeGap(siteRequest_, (String)o);
		case "rangeEnd":
			return PageLayout.staticSearchRangeEnd(siteRequest_, (ZonedDateTime)o);
		case "rangeStart":
			return PageLayout.staticSearchRangeStart(siteRequest_, (ZonedDateTime)o);
		case "defaultRangeStats":
			return PageLayout.staticSearchDefaultRangeStats(siteRequest_, (JsonObject)o);
		case "defaultRangeGap":
			return PageLayout.staticSearchDefaultRangeGap(siteRequest_, (String)o);
		case "defaultRangeEnd":
			return PageLayout.staticSearchDefaultRangeEnd(siteRequest_, (ZonedDateTime)o);
		case "defaultRangeStart":
			return PageLayout.staticSearchDefaultRangeStart(siteRequest_, (ZonedDateTime)o);
		case "defaultRangeVar":
			return PageLayout.staticSearchDefaultRangeVar(siteRequest_, (String)o);
		case "defaultFacetSort":
			return PageLayout.staticSearchDefaultFacetSort(siteRequest_, (String)o);
		case "defaultFacetLimit":
			return PageLayout.staticSearchDefaultFacetLimit(siteRequest_, (Integer)o);
		case "defaultFacetMinCount":
			return PageLayout.staticSearchDefaultFacetMinCount(siteRequest_, (Integer)o);
		case "defaultPivotMinCount":
			return PageLayout.staticSearchDefaultPivotMinCount(siteRequest_, (Integer)o);
		case "DEFAULT_MAP_LOCATION":
			return PageLayout.staticSearchDEFAULT_MAP_LOCATION(siteRequest_, (JsonObject)o);
		case "DEFAULT_MAP_ZOOM":
			return PageLayout.staticSearchDEFAULT_MAP_ZOOM(siteRequest_, (BigDecimal)o);
		case "queryStr":
			return PageLayout.staticSearchQueryStr(siteRequest_, (String)o);
		case "pageImageUri":
			return PageLayout.staticSearchPageImageUri(siteRequest_, (String)o);
		case "pageImageWidth":
			return PageLayout.staticSearchPageImageWidth(siteRequest_, (Integer)o);
		case "pageImageHeight":
			return PageLayout.staticSearchPageImageHeight(siteRequest_, (Integer)o);
		case "pageVideoId":
			return PageLayout.staticSearchPageVideoId(siteRequest_, (String)o);
		case "classIcon":
			return PageLayout.staticSearchClassIcon(siteRequest_, (String)o);
		case "pageDescription":
			return PageLayout.staticSearchPageDescription(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrPageLayout(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrPageLayout(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "lang":
			return PageLayout.staticSearchStrLang(siteRequest_, (String)o);
		case "staticBaseUrl":
			return PageLayout.staticSearchStrStaticBaseUrl(siteRequest_, (String)o);
		case "siteBaseUrl":
			return PageLayout.staticSearchStrSiteBaseUrl(siteRequest_, (String)o);
		case "siteAuthUrl":
			return PageLayout.staticSearchStrSiteAuthUrl(siteRequest_, (String)o);
		case "siteAuthRealm":
			return PageLayout.staticSearchStrSiteAuthRealm(siteRequest_, (String)o);
		case "fontAwesomeKit":
			return PageLayout.staticSearchStrFontAwesomeKit(siteRequest_, (String)o);
		case "facebookGraphVersion":
			return PageLayout.staticSearchStrFacebookGraphVersion(siteRequest_, (String)o);
		case "facebookAppId":
			return PageLayout.staticSearchStrFacebookAppId(siteRequest_, (String)o);
		case "pageUri":
			return PageLayout.staticSearchStrPageUri(siteRequest_, (String)o);
		case "pageId":
			return PageLayout.staticSearchStrPageId(siteRequest_, (String)o);
		case "apiUri":
			return PageLayout.staticSearchStrApiUri(siteRequest_, (String)o);
		case "pageMethod":
			return PageLayout.staticSearchStrPageMethod(siteRequest_, (String)o);
		case "params":
			return PageLayout.staticSearchStrParams(siteRequest_, (String)o);
		case "userKey":
			return PageLayout.staticSearchStrUserKey(siteRequest_, (Long)o);
		case "userFullName":
			return PageLayout.staticSearchStrUserFullName(siteRequest_, (String)o);
		case "userName":
			return PageLayout.staticSearchStrUserName(siteRequest_, (String)o);
		case "userEmail":
			return PageLayout.staticSearchStrUserEmail(siteRequest_, (String)o);
		case "logoutUrl":
			return PageLayout.staticSearchStrLogoutUrl(siteRequest_, (String)o);
		case "classSimpleName":
			return PageLayout.staticSearchStrClassSimpleName(siteRequest_, (String)o);
		case "pageTitle":
			return PageLayout.staticSearchStrPageTitle(siteRequest_, (String)o);
		case "scopes":
			return PageLayout.staticSearchStrScopes(siteRequest_, (String)o);
		case "roleForWrite":
			return PageLayout.staticSearchStrRoleForWrite(siteRequest_, (String)o);
		case "roleForRead":
			return PageLayout.staticSearchStrRoleForRead(siteRequest_, (String)o);
		case "pagination":
			return PageLayout.staticSearchStrPagination(siteRequest_, (String)o);
		case "defaultFieldListVars":
			return PageLayout.staticSearchStrDefaultFieldListVars(siteRequest_, (String)o);
		case "defaultSortVars":
			return PageLayout.staticSearchStrDefaultSortVars(siteRequest_, (String)o);
		case "defaultStatsVars":
			return PageLayout.staticSearchStrDefaultStatsVars(siteRequest_, (String)o);
		case "defaultPivotVars":
			return PageLayout.staticSearchStrDefaultPivotVars(siteRequest_, (String)o);
		case "varsQ":
			return PageLayout.staticSearchStrVarsQ(siteRequest_, (String)o);
		case "varsFq":
			return PageLayout.staticSearchStrVarsFq(siteRequest_, (String)o);
		case "varsFqCount":
			return PageLayout.staticSearchStrVarsFqCount(siteRequest_, (Integer)o);
		case "varsRange":
			return PageLayout.staticSearchStrVarsRange(siteRequest_, (String)o);
		case "query":
			return PageLayout.staticSearchStrQuery(siteRequest_, (String)o);
		case "pageResponse":
			return PageLayout.staticSearchStrPageResponse(siteRequest_, (String)o);
		case "defaultZoneId":
			return PageLayout.staticSearchStrDefaultZoneId(siteRequest_, (String)o);
		case "defaultLocaleId":
			return PageLayout.staticSearchStrDefaultLocaleId(siteRequest_, (String)o);
		case "rows":
			return PageLayout.staticSearchStrRows(siteRequest_, (Long)o);
		case "start":
			return PageLayout.staticSearchStrStart(siteRequest_, (Long)o);
		case "rangeGap":
			return PageLayout.staticSearchStrRangeGap(siteRequest_, (String)o);
		case "rangeEnd":
			return PageLayout.staticSearchStrRangeEnd(siteRequest_, (String)o);
		case "rangeStart":
			return PageLayout.staticSearchStrRangeStart(siteRequest_, (String)o);
		case "defaultRangeStats":
			return PageLayout.staticSearchStrDefaultRangeStats(siteRequest_, (String)o);
		case "defaultRangeGap":
			return PageLayout.staticSearchStrDefaultRangeGap(siteRequest_, (String)o);
		case "defaultRangeEnd":
			return PageLayout.staticSearchStrDefaultRangeEnd(siteRequest_, (String)o);
		case "defaultRangeStart":
			return PageLayout.staticSearchStrDefaultRangeStart(siteRequest_, (String)o);
		case "defaultRangeVar":
			return PageLayout.staticSearchStrDefaultRangeVar(siteRequest_, (String)o);
		case "defaultFacetSort":
			return PageLayout.staticSearchStrDefaultFacetSort(siteRequest_, (String)o);
		case "defaultFacetLimit":
			return PageLayout.staticSearchStrDefaultFacetLimit(siteRequest_, (Integer)o);
		case "defaultFacetMinCount":
			return PageLayout.staticSearchStrDefaultFacetMinCount(siteRequest_, (Integer)o);
		case "defaultPivotMinCount":
			return PageLayout.staticSearchStrDefaultPivotMinCount(siteRequest_, (Integer)o);
		case "DEFAULT_MAP_LOCATION":
			return PageLayout.staticSearchStrDEFAULT_MAP_LOCATION(siteRequest_, (String)o);
		case "DEFAULT_MAP_ZOOM":
			return PageLayout.staticSearchStrDEFAULT_MAP_ZOOM(siteRequest_, (Double)o);
		case "queryStr":
			return PageLayout.staticSearchStrQueryStr(siteRequest_, (String)o);
		case "pageImageUri":
			return PageLayout.staticSearchStrPageImageUri(siteRequest_, (String)o);
		case "pageImageWidth":
			return PageLayout.staticSearchStrPageImageWidth(siteRequest_, (Integer)o);
		case "pageImageHeight":
			return PageLayout.staticSearchStrPageImageHeight(siteRequest_, (Integer)o);
		case "pageVideoId":
			return PageLayout.staticSearchStrPageVideoId(siteRequest_, (String)o);
		case "classIcon":
			return PageLayout.staticSearchStrClassIcon(siteRequest_, (String)o);
		case "pageDescription":
			return PageLayout.staticSearchStrPageDescription(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqPageLayout(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqPageLayout(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "lang":
			return PageLayout.staticSearchFqLang(siteRequest_, o);
		case "staticBaseUrl":
			return PageLayout.staticSearchFqStaticBaseUrl(siteRequest_, o);
		case "siteBaseUrl":
			return PageLayout.staticSearchFqSiteBaseUrl(siteRequest_, o);
		case "siteAuthUrl":
			return PageLayout.staticSearchFqSiteAuthUrl(siteRequest_, o);
		case "siteAuthRealm":
			return PageLayout.staticSearchFqSiteAuthRealm(siteRequest_, o);
		case "fontAwesomeKit":
			return PageLayout.staticSearchFqFontAwesomeKit(siteRequest_, o);
		case "facebookGraphVersion":
			return PageLayout.staticSearchFqFacebookGraphVersion(siteRequest_, o);
		case "facebookAppId":
			return PageLayout.staticSearchFqFacebookAppId(siteRequest_, o);
		case "pageUri":
			return PageLayout.staticSearchFqPageUri(siteRequest_, o);
		case "pageId":
			return PageLayout.staticSearchFqPageId(siteRequest_, o);
		case "apiUri":
			return PageLayout.staticSearchFqApiUri(siteRequest_, o);
		case "pageMethod":
			return PageLayout.staticSearchFqPageMethod(siteRequest_, o);
		case "params":
			return PageLayout.staticSearchFqParams(siteRequest_, o);
		case "userKey":
			return PageLayout.staticSearchFqUserKey(siteRequest_, o);
		case "userFullName":
			return PageLayout.staticSearchFqUserFullName(siteRequest_, o);
		case "userName":
			return PageLayout.staticSearchFqUserName(siteRequest_, o);
		case "userEmail":
			return PageLayout.staticSearchFqUserEmail(siteRequest_, o);
		case "logoutUrl":
			return PageLayout.staticSearchFqLogoutUrl(siteRequest_, o);
		case "classSimpleName":
			return PageLayout.staticSearchFqClassSimpleName(siteRequest_, o);
		case "pageTitle":
			return PageLayout.staticSearchFqPageTitle(siteRequest_, o);
		case "scopes":
			return PageLayout.staticSearchFqScopes(siteRequest_, o);
		case "roleForWrite":
			return PageLayout.staticSearchFqRoleForWrite(siteRequest_, o);
		case "roleForRead":
			return PageLayout.staticSearchFqRoleForRead(siteRequest_, o);
		case "pagination":
			return PageLayout.staticSearchFqPagination(siteRequest_, o);
		case "defaultFieldListVars":
			return PageLayout.staticSearchFqDefaultFieldListVars(siteRequest_, o);
		case "defaultSortVars":
			return PageLayout.staticSearchFqDefaultSortVars(siteRequest_, o);
		case "defaultStatsVars":
			return PageLayout.staticSearchFqDefaultStatsVars(siteRequest_, o);
		case "defaultPivotVars":
			return PageLayout.staticSearchFqDefaultPivotVars(siteRequest_, o);
		case "varsQ":
			return PageLayout.staticSearchFqVarsQ(siteRequest_, o);
		case "varsFq":
			return PageLayout.staticSearchFqVarsFq(siteRequest_, o);
		case "varsFqCount":
			return PageLayout.staticSearchFqVarsFqCount(siteRequest_, o);
		case "varsRange":
			return PageLayout.staticSearchFqVarsRange(siteRequest_, o);
		case "query":
			return PageLayout.staticSearchFqQuery(siteRequest_, o);
		case "pageResponse":
			return PageLayout.staticSearchFqPageResponse(siteRequest_, o);
		case "defaultZoneId":
			return PageLayout.staticSearchFqDefaultZoneId(siteRequest_, o);
		case "defaultLocaleId":
			return PageLayout.staticSearchFqDefaultLocaleId(siteRequest_, o);
		case "rows":
			return PageLayout.staticSearchFqRows(siteRequest_, o);
		case "start":
			return PageLayout.staticSearchFqStart(siteRequest_, o);
		case "rangeGap":
			return PageLayout.staticSearchFqRangeGap(siteRequest_, o);
		case "rangeEnd":
			return PageLayout.staticSearchFqRangeEnd(siteRequest_, o);
		case "rangeStart":
			return PageLayout.staticSearchFqRangeStart(siteRequest_, o);
		case "defaultRangeStats":
			return PageLayout.staticSearchFqDefaultRangeStats(siteRequest_, o);
		case "defaultRangeGap":
			return PageLayout.staticSearchFqDefaultRangeGap(siteRequest_, o);
		case "defaultRangeEnd":
			return PageLayout.staticSearchFqDefaultRangeEnd(siteRequest_, o);
		case "defaultRangeStart":
			return PageLayout.staticSearchFqDefaultRangeStart(siteRequest_, o);
		case "defaultRangeVar":
			return PageLayout.staticSearchFqDefaultRangeVar(siteRequest_, o);
		case "defaultFacetSort":
			return PageLayout.staticSearchFqDefaultFacetSort(siteRequest_, o);
		case "defaultFacetLimit":
			return PageLayout.staticSearchFqDefaultFacetLimit(siteRequest_, o);
		case "defaultFacetMinCount":
			return PageLayout.staticSearchFqDefaultFacetMinCount(siteRequest_, o);
		case "defaultPivotMinCount":
			return PageLayout.staticSearchFqDefaultPivotMinCount(siteRequest_, o);
		case "DEFAULT_MAP_LOCATION":
			return PageLayout.staticSearchFqDEFAULT_MAP_LOCATION(siteRequest_, o);
		case "DEFAULT_MAP_ZOOM":
			return PageLayout.staticSearchFqDEFAULT_MAP_ZOOM(siteRequest_, o);
		case "queryStr":
			return PageLayout.staticSearchFqQueryStr(siteRequest_, o);
		case "pageImageUri":
			return PageLayout.staticSearchFqPageImageUri(siteRequest_, o);
		case "pageImageWidth":
			return PageLayout.staticSearchFqPageImageWidth(siteRequest_, o);
		case "pageImageHeight":
			return PageLayout.staticSearchFqPageImageHeight(siteRequest_, o);
		case "pageVideoId":
			return PageLayout.staticSearchFqPageVideoId(siteRequest_, o);
		case "classIcon":
			return PageLayout.staticSearchFqClassIcon(siteRequest_, o);
		case "pageDescription":
			return PageLayout.staticSearchFqPageDescription(siteRequest_, o);
			default:
				return null;
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "PageLayout";
	public static final String VAR_webClient = "webClient";
	public static final String VAR_vertx = "vertx";
	public static final String VAR_siteRequest_ = "siteRequest_";
	public static final String VAR_lang = "lang";
	public static final String VAR_requestVars = "requestVars";
	public static final String VAR_serviceRequest = "serviceRequest";
	public static final String VAR_staticBaseUrl = "staticBaseUrl";
	public static final String VAR_siteBaseUrl = "siteBaseUrl";
	public static final String VAR_siteAuthUrl = "siteAuthUrl";
	public static final String VAR_siteAuthRealm = "siteAuthRealm";
	public static final String VAR_fontAwesomeKit = "fontAwesomeKit";
	public static final String VAR_facebookGraphVersion = "facebookGraphVersion";
	public static final String VAR_facebookAppId = "facebookAppId";
	public static final String VAR_pageUri = "pageUri";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_apiUri = "apiUri";
	public static final String VAR_pageMethod = "pageMethod";
	public static final String VAR_params = "params";
	public static final String VAR_userKey = "userKey";
	public static final String VAR_userFullName = "userFullName";
	public static final String VAR_userName = "userName";
	public static final String VAR_userEmail = "userEmail";
	public static final String VAR_logoutUrl = "logoutUrl";
	public static final String VAR_promiseBefore = "promiseBefore";
	public static final String VAR_classSimpleName = "classSimpleName";
	public static final String VAR_pageTitle = "pageTitle";
	public static final String VAR_scopes = "scopes";
	public static final String VAR_roleForWrite = "roleForWrite";
	public static final String VAR_roleForRead = "roleForRead";
	public static final String VAR_stats = "stats";
	public static final String VAR_facetCounts = "facetCounts";
	public static final String VAR_pagination = "pagination";
	public static final String VAR_defaultFieldListVars = "defaultFieldListVars";
	public static final String VAR_defaultSortVars = "defaultSortVars";
	public static final String VAR_defaultStatsVars = "defaultStatsVars";
	public static final String VAR_defaultPivotVars = "defaultPivotVars";
	public static final String VAR_varsQ = "varsQ";
	public static final String VAR_varsFq = "varsFq";
	public static final String VAR_varsFqCount = "varsFqCount";
	public static final String VAR_varsRange = "varsRange";
	public static final String VAR_query = "query";
	public static final String VAR_pageResponse = "pageResponse";
	public static final String VAR_defaultZoneId = "defaultZoneId";
	public static final String VAR_defaultTimeZone = "defaultTimeZone";
	public static final String VAR_defaultLocaleId = "defaultLocaleId";
	public static final String VAR_rows = "rows";
	public static final String VAR_start = "start";
	public static final String VAR_defaultLocale = "defaultLocale";
	public static final String VAR_rangeGap = "rangeGap";
	public static final String VAR_rangeEnd = "rangeEnd";
	public static final String VAR_rangeStart = "rangeStart";
	public static final String VAR_defaultRangeStats = "defaultRangeStats";
	public static final String VAR_defaultRangeGap = "defaultRangeGap";
	public static final String VAR_defaultRangeEnd = "defaultRangeEnd";
	public static final String VAR_defaultRangeStart = "defaultRangeStart";
	public static final String VAR_defaultRangeVar = "defaultRangeVar";
	public static final String VAR_defaultFacetSort = "defaultFacetSort";
	public static final String VAR_defaultFacetLimit = "defaultFacetLimit";
	public static final String VAR_defaultFacetMinCount = "defaultFacetMinCount";
	public static final String VAR_defaultPivotMinCount = "defaultPivotMinCount";
	public static final String VAR_DEFAULT_MAP_LOCATION = "DEFAULT_MAP_LOCATION";
	public static final String VAR_DEFAULT_MAP_ZOOM = "DEFAULT_MAP_ZOOM";
	public static final String VAR_queryStr = "queryStr";
	public static final String VAR_promiseAfter = "promiseAfter";
	public static final String VAR_pageImageUri = "pageImageUri";
	public static final String VAR_pageImageWidth = "pageImageWidth";
	public static final String VAR_pageImageHeight = "pageImageHeight";
	public static final String VAR_pageVideoId = "pageVideoId";
	public static final String VAR_classIcon = "classIcon";
	public static final String VAR_pageDescription = "pageDescription";

	public static final String DISPLAY_NAME_webClient = "";
	public static final String DISPLAY_NAME_vertx = "";
	public static final String DISPLAY_NAME_siteRequest_ = "";
	public static final String DISPLAY_NAME_lang = "";
	public static final String DISPLAY_NAME_requestVars = "";
	public static final String DISPLAY_NAME_serviceRequest = "";
	public static final String DISPLAY_NAME_staticBaseUrl = "";
	public static final String DISPLAY_NAME_siteBaseUrl = "";
	public static final String DISPLAY_NAME_siteAuthUrl = "";
	public static final String DISPLAY_NAME_siteAuthRealm = "";
	public static final String DISPLAY_NAME_fontAwesomeKit = "";
	public static final String DISPLAY_NAME_facebookGraphVersion = "";
	public static final String DISPLAY_NAME_facebookAppId = "";
	public static final String DISPLAY_NAME_pageUri = "";
	public static final String DISPLAY_NAME_pageId = "";
	public static final String DISPLAY_NAME_apiUri = "";
	public static final String DISPLAY_NAME_pageMethod = "";
	public static final String DISPLAY_NAME_params = "";
	public static final String DISPLAY_NAME_userKey = "";
	public static final String DISPLAY_NAME_userFullName = "";
	public static final String DISPLAY_NAME_userName = "";
	public static final String DISPLAY_NAME_userEmail = "";
	public static final String DISPLAY_NAME_logoutUrl = "";
	public static final String DISPLAY_NAME_promiseBefore = "";
	public static final String DISPLAY_NAME_classSimpleName = "";
	public static final String DISPLAY_NAME_pageTitle = "";
	public static final String DISPLAY_NAME_scopes = "";
	public static final String DISPLAY_NAME_roleForWrite = "";
	public static final String DISPLAY_NAME_roleForRead = "";
	public static final String DISPLAY_NAME_stats = "";
	public static final String DISPLAY_NAME_facetCounts = "";
	public static final String DISPLAY_NAME_pagination = "";
	public static final String DISPLAY_NAME_defaultFieldListVars = "";
	public static final String DISPLAY_NAME_defaultSortVars = "";
	public static final String DISPLAY_NAME_defaultStatsVars = "";
	public static final String DISPLAY_NAME_defaultPivotVars = "";
	public static final String DISPLAY_NAME_varsQ = "";
	public static final String DISPLAY_NAME_varsFq = "";
	public static final String DISPLAY_NAME_varsFqCount = "";
	public static final String DISPLAY_NAME_varsRange = "";
	public static final String DISPLAY_NAME_query = "";
	public static final String DISPLAY_NAME_pageResponse = "";
	public static final String DISPLAY_NAME_defaultZoneId = "";
	public static final String DISPLAY_NAME_defaultTimeZone = "";
	public static final String DISPLAY_NAME_defaultLocaleId = "";
	public static final String DISPLAY_NAME_rows = "";
	public static final String DISPLAY_NAME_start = "";
	public static final String DISPLAY_NAME_defaultLocale = "";
	public static final String DISPLAY_NAME_rangeGap = "";
	public static final String DISPLAY_NAME_rangeEnd = "";
	public static final String DISPLAY_NAME_rangeStart = "";
	public static final String DISPLAY_NAME_defaultRangeStats = "";
	public static final String DISPLAY_NAME_defaultRangeGap = "";
	public static final String DISPLAY_NAME_defaultRangeEnd = "";
	public static final String DISPLAY_NAME_defaultRangeStart = "";
	public static final String DISPLAY_NAME_defaultRangeVar = "";
	public static final String DISPLAY_NAME_defaultFacetSort = "";
	public static final String DISPLAY_NAME_defaultFacetLimit = "";
	public static final String DISPLAY_NAME_defaultFacetMinCount = "";
	public static final String DISPLAY_NAME_defaultPivotMinCount = "";
	public static final String DISPLAY_NAME_DEFAULT_MAP_LOCATION = "";
	public static final String DISPLAY_NAME_DEFAULT_MAP_ZOOM = "";
	public static final String DISPLAY_NAME_queryStr = "";
	public static final String DISPLAY_NAME_promiseAfter = "";
	public static final String DISPLAY_NAME_pageImageUri = "";
	public static final String DISPLAY_NAME_pageImageWidth = "";
	public static final String DISPLAY_NAME_pageImageHeight = "";
	public static final String DISPLAY_NAME_pageVideoId = "";
	public static final String DISPLAY_NAME_classIcon = "";
	public static final String DISPLAY_NAME_pageDescription = "";

	public String idForClass() {
		return null;
	}

	public String titleForClass() {
		return null;
	}

	public String nameForClass() {
		return null;
	}

	public String classNameAdjectiveSingularForClass() {
		return null;
	}

	public String descriptionForClass() {
		return null;
	}

	public String classStringFormatUrlEditPageForClass() {
		return null;
	}

	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	public String classStringFormatUrlUserPageForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return PageLayout.displayNamePageLayout(var);
	}
	public static String displayNamePageLayout(String var) {
		switch(var) {
		case VAR_webClient:
			return DISPLAY_NAME_webClient;
		case VAR_vertx:
			return DISPLAY_NAME_vertx;
		case VAR_siteRequest_:
			return DISPLAY_NAME_siteRequest_;
		case VAR_lang:
			return DISPLAY_NAME_lang;
		case VAR_requestVars:
			return DISPLAY_NAME_requestVars;
		case VAR_serviceRequest:
			return DISPLAY_NAME_serviceRequest;
		case VAR_staticBaseUrl:
			return DISPLAY_NAME_staticBaseUrl;
		case VAR_siteBaseUrl:
			return DISPLAY_NAME_siteBaseUrl;
		case VAR_siteAuthUrl:
			return DISPLAY_NAME_siteAuthUrl;
		case VAR_siteAuthRealm:
			return DISPLAY_NAME_siteAuthRealm;
		case VAR_fontAwesomeKit:
			return DISPLAY_NAME_fontAwesomeKit;
		case VAR_facebookGraphVersion:
			return DISPLAY_NAME_facebookGraphVersion;
		case VAR_facebookAppId:
			return DISPLAY_NAME_facebookAppId;
		case VAR_pageUri:
			return DISPLAY_NAME_pageUri;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_apiUri:
			return DISPLAY_NAME_apiUri;
		case VAR_pageMethod:
			return DISPLAY_NAME_pageMethod;
		case VAR_params:
			return DISPLAY_NAME_params;
		case VAR_userKey:
			return DISPLAY_NAME_userKey;
		case VAR_userFullName:
			return DISPLAY_NAME_userFullName;
		case VAR_userName:
			return DISPLAY_NAME_userName;
		case VAR_userEmail:
			return DISPLAY_NAME_userEmail;
		case VAR_logoutUrl:
			return DISPLAY_NAME_logoutUrl;
		case VAR_promiseBefore:
			return DISPLAY_NAME_promiseBefore;
		case VAR_classSimpleName:
			return DISPLAY_NAME_classSimpleName;
		case VAR_pageTitle:
			return DISPLAY_NAME_pageTitle;
		case VAR_scopes:
			return DISPLAY_NAME_scopes;
		case VAR_roleForWrite:
			return DISPLAY_NAME_roleForWrite;
		case VAR_roleForRead:
			return DISPLAY_NAME_roleForRead;
		case VAR_stats:
			return DISPLAY_NAME_stats;
		case VAR_facetCounts:
			return DISPLAY_NAME_facetCounts;
		case VAR_pagination:
			return DISPLAY_NAME_pagination;
		case VAR_defaultFieldListVars:
			return DISPLAY_NAME_defaultFieldListVars;
		case VAR_defaultSortVars:
			return DISPLAY_NAME_defaultSortVars;
		case VAR_defaultStatsVars:
			return DISPLAY_NAME_defaultStatsVars;
		case VAR_defaultPivotVars:
			return DISPLAY_NAME_defaultPivotVars;
		case VAR_varsQ:
			return DISPLAY_NAME_varsQ;
		case VAR_varsFq:
			return DISPLAY_NAME_varsFq;
		case VAR_varsFqCount:
			return DISPLAY_NAME_varsFqCount;
		case VAR_varsRange:
			return DISPLAY_NAME_varsRange;
		case VAR_query:
			return DISPLAY_NAME_query;
		case VAR_pageResponse:
			return DISPLAY_NAME_pageResponse;
		case VAR_defaultZoneId:
			return DISPLAY_NAME_defaultZoneId;
		case VAR_defaultTimeZone:
			return DISPLAY_NAME_defaultTimeZone;
		case VAR_defaultLocaleId:
			return DISPLAY_NAME_defaultLocaleId;
		case VAR_rows:
			return DISPLAY_NAME_rows;
		case VAR_start:
			return DISPLAY_NAME_start;
		case VAR_defaultLocale:
			return DISPLAY_NAME_defaultLocale;
		case VAR_rangeGap:
			return DISPLAY_NAME_rangeGap;
		case VAR_rangeEnd:
			return DISPLAY_NAME_rangeEnd;
		case VAR_rangeStart:
			return DISPLAY_NAME_rangeStart;
		case VAR_defaultRangeStats:
			return DISPLAY_NAME_defaultRangeStats;
		case VAR_defaultRangeGap:
			return DISPLAY_NAME_defaultRangeGap;
		case VAR_defaultRangeEnd:
			return DISPLAY_NAME_defaultRangeEnd;
		case VAR_defaultRangeStart:
			return DISPLAY_NAME_defaultRangeStart;
		case VAR_defaultRangeVar:
			return DISPLAY_NAME_defaultRangeVar;
		case VAR_defaultFacetSort:
			return DISPLAY_NAME_defaultFacetSort;
		case VAR_defaultFacetLimit:
			return DISPLAY_NAME_defaultFacetLimit;
		case VAR_defaultFacetMinCount:
			return DISPLAY_NAME_defaultFacetMinCount;
		case VAR_defaultPivotMinCount:
			return DISPLAY_NAME_defaultPivotMinCount;
		case VAR_DEFAULT_MAP_LOCATION:
			return DISPLAY_NAME_DEFAULT_MAP_LOCATION;
		case VAR_DEFAULT_MAP_ZOOM:
			return DISPLAY_NAME_DEFAULT_MAP_ZOOM;
		case VAR_queryStr:
			return DISPLAY_NAME_queryStr;
		case VAR_promiseAfter:
			return DISPLAY_NAME_promiseAfter;
		case VAR_pageImageUri:
			return DISPLAY_NAME_pageImageUri;
		case VAR_pageImageWidth:
			return DISPLAY_NAME_pageImageWidth;
		case VAR_pageImageHeight:
			return DISPLAY_NAME_pageImageHeight;
		case VAR_pageVideoId:
			return DISPLAY_NAME_pageVideoId;
		case VAR_classIcon:
			return DISPLAY_NAME_classIcon;
		case VAR_pageDescription:
			return DISPLAY_NAME_pageDescription;
		default:
			return null;
		}
	}

	public static String descriptionPageLayout(String var) {
		switch(var) {
		case VAR_webClient:
			return "The current request object";
		case VAR_vertx:
			return "The current request object";
		case VAR_siteRequest_:
			return "The current request object";
		case VAR_lang:
			return "The current request language";
		case VAR_requestVars:
			return "The current request vars";
		case VAR_serviceRequest:
			return "The current Vert.x service request";
		case VAR_pageUri:
			return "The current request URI";
		case VAR_pageId:
			return "The current page name";
		case VAR_apiUri:
			return "The API request URI";
		case VAR_pageMethod:
			return "The current request HTTP method";
		case VAR_params:
			return "The current request parameters";
		case VAR_userKey:
			return "The current user's primary key";
		case VAR_userFullName:
			return "The current user's full name";
		case VAR_userName:
			return "The current user's username";
		case VAR_userEmail:
			return "The current user's email";
		case VAR_logoutUrl:
			return "The logout URL";
		case VAR_promiseBefore:
			return "A method that can be overridden at the start of the request that makes this main template be initialized with a Vert.x promise for reactive initialization";
		case VAR_classSimpleName:
			return "The simple name of this Java class";
		case VAR_pageTitle:
			return "The page title to override";
		case VAR_scopes:
			return "The user request scopes";
		case VAR_roleForWrite:
			return "The required roles to access this page";
		case VAR_roleForRead:
			return "The required roles to access this page";
		case VAR_pagination:
			return "The pagination data about this request";
		case VAR_query:
			return "The query data about this request";
		case VAR_queryStr:
			return "The query String for this request";
		case VAR_promiseAfter:
			return "A method that can be overridden at the end of the request that makes this main template be initialized with a Vert.x promise for reactive initialization";
		case VAR_pageImageUri:
			return "The image URI for this page";
		case VAR_pageImageWidth:
			return "The image width";
		case VAR_pageImageHeight:
			return "The image height";
		case VAR_pageVideoId:
			return "The video ID for this page";
		case VAR_classIcon:
			return "The icon for this page";
		case VAR_pageDescription:
			return "An optional description field for the page";
			default:
				return null;
		}
	}

	public static String classSimpleNamePageLayout(String var) {
		switch(var) {
		case VAR_webClient:
			return "WebClient";
		case VAR_vertx:
			return "Vertx";
		case VAR_siteRequest_:
			return "SiteRequest";
		case VAR_lang:
			return "String";
		case VAR_requestVars:
			return "Map";
		case VAR_serviceRequest:
			return "ServiceRequest";
		case VAR_staticBaseUrl:
			return "String";
		case VAR_siteBaseUrl:
			return "String";
		case VAR_siteAuthUrl:
			return "String";
		case VAR_siteAuthRealm:
			return "String";
		case VAR_fontAwesomeKit:
			return "String";
		case VAR_facebookGraphVersion:
			return "String";
		case VAR_facebookAppId:
			return "String";
		case VAR_pageUri:
			return "String";
		case VAR_pageId:
			return "String";
		case VAR_apiUri:
			return "String";
		case VAR_pageMethod:
			return "String";
		case VAR_params:
			return "JsonObject";
		case VAR_userKey:
			return "Long";
		case VAR_userFullName:
			return "String";
		case VAR_userName:
			return "String";
		case VAR_userEmail:
			return "String";
		case VAR_logoutUrl:
			return "String";
		case VAR_promiseBefore:
			return "Void";
		case VAR_classSimpleName:
			return "String";
		case VAR_pageTitle:
			return "String";
		case VAR_scopes:
			return "List";
		case VAR_roleForWrite:
			return "List";
		case VAR_roleForRead:
			return "List";
		case VAR_stats:
			return "Stats";
		case VAR_facetCounts:
			return "FacetCounts";
		case VAR_pagination:
			return "JsonObject";
		case VAR_defaultFieldListVars:
			return "List";
		case VAR_defaultSortVars:
			return "List";
		case VAR_defaultStatsVars:
			return "List";
		case VAR_defaultPivotVars:
			return "List";
		case VAR_varsQ:
			return "JsonObject";
		case VAR_varsFq:
			return "JsonObject";
		case VAR_varsFqCount:
			return "Integer";
		case VAR_varsRange:
			return "JsonObject";
		case VAR_query:
			return "JsonObject";
		case VAR_pageResponse:
			return "String";
		case VAR_defaultZoneId:
			return "String";
		case VAR_defaultTimeZone:
			return "ZoneId";
		case VAR_defaultLocaleId:
			return "String";
		case VAR_rows:
			return "Long";
		case VAR_start:
			return "Long";
		case VAR_defaultLocale:
			return "Locale";
		case VAR_rangeGap:
			return "String";
		case VAR_rangeEnd:
			return "ZonedDateTime";
		case VAR_rangeStart:
			return "ZonedDateTime";
		case VAR_defaultRangeStats:
			return "JsonObject";
		case VAR_defaultRangeGap:
			return "String";
		case VAR_defaultRangeEnd:
			return "ZonedDateTime";
		case VAR_defaultRangeStart:
			return "ZonedDateTime";
		case VAR_defaultRangeVar:
			return "String";
		case VAR_defaultFacetSort:
			return "String";
		case VAR_defaultFacetLimit:
			return "Integer";
		case VAR_defaultFacetMinCount:
			return "Integer";
		case VAR_defaultPivotMinCount:
			return "Integer";
		case VAR_DEFAULT_MAP_LOCATION:
			return "JsonObject";
		case VAR_DEFAULT_MAP_ZOOM:
			return "BigDecimal";
		case VAR_queryStr:
			return "String";
		case VAR_promiseAfter:
			return "Void";
		case VAR_pageImageUri:
			return "String";
		case VAR_pageImageWidth:
			return "Integer";
		case VAR_pageImageHeight:
			return "Integer";
		case VAR_pageVideoId:
			return "String";
		case VAR_classIcon:
			return "String";
		case VAR_pageDescription:
			return "String";
			default:
				return null;
		}
	}

	public static Integer htmColumnPageLayout(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer htmRowPageLayout(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer htmCellPageLayout(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer lengthMinPageLayout(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer lengthMaxPageLayout(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer maxPageLayout(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer minPageLayout(String var) {
		switch(var) {
			default:
				return null;
		}
	}
}
