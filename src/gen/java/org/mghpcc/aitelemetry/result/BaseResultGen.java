package org.mghpcc.aitelemetry.result;

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
import java.lang.String;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.Locale;
import java.time.OffsetDateTime;
import java.lang.Boolean;
import io.vertx.core.json.JsonArray;
import java.lang.Long;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;
import io.vertx.core.json.JsonObject;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BaseResult objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BaseResultGen into the class BaseResult. 
 * </li>
 * <h3>About the BaseResult class and it's generated class BaseResultGen&lt;Object&gt;: </h3>extends BaseResultGen
 * <p>
 * This Java class extends a generated Java class BaseResultGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult">Find the class BaseResult in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BaseResultGen<Object>
 * <p>This <code>class BaseResult extends BaseResultGen&lt;Object&gt;</code>, which means it extends a newly generated BaseResultGen. 
 * The generated <code>class BaseResultGen extends Object</code> which means that BaseResult extends BaseResultGen which extends Object. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BaseResult class will inherit the helpful inherited class comments from the super class BaseResultGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 2</h2>
 * <p>This class contains a comment <b>"Order: 2"</b>, which means this class will be sorted by the given number 2 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.result.BaseResultPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.result.BaseResultPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BaseResult Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class BaseResult in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.result in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.result&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BaseResultGen<DEV> extends Object {
	protected static final Logger LOG = LoggerFactory.getLogger(BaseResult.class);

	public static final String BaseResult_Description_enUS = "A reusable base class for all non-model search classes";


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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:siteRequest_">Find the entity siteRequest_ in Solr</a>
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
	protected BaseResult siteRequest_Init() {
		Wrap<SiteRequest> siteRequest_Wrap = new Wrap<SiteRequest>().var("siteRequest_");
		if(siteRequest_ == null) {
			_siteRequest_(siteRequest_Wrap);
			Optional.ofNullable(siteRequest_Wrap.getO()).ifPresent(o -> {
				setSiteRequest_(o);
			});
		}
		return (BaseResult)this;
	}

	///////////////
	// inheritPk //
	///////////////


	/**	 The entity inheritPk
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String inheritPk;

	/**	<br> The entity inheritPk
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:inheritPk">Find the entity inheritPk in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _inheritPk(Wrap<String> w);

	public String getInheritPk() {
		return inheritPk;
	}
	public void setInheritPk(String o) {
		this.inheritPk = BaseResult.staticSetInheritPk(siteRequest_, o);
	}
	public static String staticSetInheritPk(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult inheritPkInit() {
		Wrap<String> inheritPkWrap = new Wrap<String>().var("inheritPk");
		if(inheritPk == null) {
			_inheritPk(inheritPkWrap);
			Optional.ofNullable(inheritPkWrap.getO()).ifPresent(o -> {
				setInheritPk(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchInheritPk(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrInheritPk(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqInheritPk(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchInheritPk(siteRequest_, BaseResult.staticSetInheritPk(siteRequest_, o)).toString();
	}

	public String sqlInheritPk() {
		return inheritPk;
	}

	/////////////
	// created //
	/////////////


	/**	 The entity created
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = ComputateZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ComputateZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime created;

	/**	<br> The entity created
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:created">Find the entity created in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _created(Wrap<ZonedDateTime> w);

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = Optional.ofNullable(created).map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);
	}
	@JsonIgnore
	public void setCreated(Instant o) {
		this.created = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setCreated(String o) {
		this.created = BaseResult.staticSetCreated(siteRequest_, o);
	}
	public static ZonedDateTime staticSetCreated(SiteRequest siteRequest_, String o) {
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
	public void setCreated(Date o) {
		this.created = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	protected BaseResult createdInit() {
		Wrap<ZonedDateTime> createdWrap = new Wrap<ZonedDateTime>().var("created");
		if(created == null) {
			_created(createdWrap);
			Optional.ofNullable(createdWrap.getO()).ifPresent(o -> {
				setCreated(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchCreated(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrCreated(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchCreated(siteRequest_, BaseResult.staticSetCreated(siteRequest_, o));
	}

	public static String staticSearchFqCreated(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchCreated(siteRequest_, BaseResult.staticSetCreated(siteRequest_, o)).toString();
	}

	public OffsetDateTime sqlCreated() {
		return created == null ? null : created.toOffsetDateTime();
	}

	//////////////
	// modified //
	//////////////


	/**	 The entity modified
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = ComputateZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ComputateZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime modified;

	/**	<br> The entity modified
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:modified">Find the entity modified in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _modified(Wrap<ZonedDateTime> w);

	public ZonedDateTime getModified() {
		return modified;
	}

	public void setModified(ZonedDateTime modified) {
		this.modified = Optional.ofNullable(modified).map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);
	}
	@JsonIgnore
	public void setModified(Instant o) {
		this.modified = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setModified(String o) {
		this.modified = BaseResult.staticSetModified(siteRequest_, o);
	}
	public static ZonedDateTime staticSetModified(SiteRequest siteRequest_, String o) {
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
	public void setModified(Date o) {
		this.modified = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	protected BaseResult modifiedInit() {
		Wrap<ZonedDateTime> modifiedWrap = new Wrap<ZonedDateTime>().var("modified");
		if(modified == null) {
			_modified(modifiedWrap);
			Optional.ofNullable(modifiedWrap.getO()).ifPresent(o -> {
				setModified(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchModified(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrModified(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchModified(siteRequest_, BaseResult.staticSetModified(siteRequest_, o));
	}

	public static String staticSearchFqModified(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchModified(siteRequest_, BaseResult.staticSetModified(siteRequest_, o)).toString();
	}

	//////////////
	// archived //
	//////////////


	/**	 The entity archived
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean archived;

	/**	<br> The entity archived
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:archived">Find the entity archived in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _archived(Wrap<Boolean> w);

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
	@JsonIgnore
	public void setArchived(String o) {
		this.archived = BaseResult.staticSetArchived(siteRequest_, o);
	}
	public static Boolean staticSetArchived(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BaseResult archivedInit() {
		Wrap<Boolean> archivedWrap = new Wrap<Boolean>().var("archived");
		if(archived == null) {
			_archived(archivedWrap);
			Optional.ofNullable(archivedWrap.getO()).ifPresent(o -> {
				setArchived(o);
			});
		}
		return (BaseResult)this;
	}

	public static Boolean staticSearchArchived(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrArchived(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqArchived(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchArchived(siteRequest_, BaseResult.staticSetArchived(siteRequest_, o)).toString();
	}

	public Boolean sqlArchived() {
		return archived;
	}

	////////////////////////
	// classCanonicalName //
	////////////////////////


	/**	 The entity classCanonicalName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String classCanonicalName;

	/**	<br> The entity classCanonicalName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:classCanonicalName">Find the entity classCanonicalName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _classCanonicalName(Wrap<String> w);

	public String getClassCanonicalName() {
		return classCanonicalName;
	}
	public void setClassCanonicalName(String o) {
		this.classCanonicalName = BaseResult.staticSetClassCanonicalName(siteRequest_, o);
	}
	public static String staticSetClassCanonicalName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult classCanonicalNameInit() {
		Wrap<String> classCanonicalNameWrap = new Wrap<String>().var("classCanonicalName");
		if(classCanonicalName == null) {
			_classCanonicalName(classCanonicalNameWrap);
			Optional.ofNullable(classCanonicalNameWrap.getO()).ifPresent(o -> {
				setClassCanonicalName(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchClassCanonicalName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassCanonicalName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassCanonicalName(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchClassCanonicalName(siteRequest_, BaseResult.staticSetClassCanonicalName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:classSimpleName">Find the entity classSimpleName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _classSimpleName(Wrap<String> w);

	public String getClassSimpleName() {
		return classSimpleName;
	}
	public void setClassSimpleName(String o) {
		this.classSimpleName = BaseResult.staticSetClassSimpleName(siteRequest_, o);
	}
	public static String staticSetClassSimpleName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult classSimpleNameInit() {
		Wrap<String> classSimpleNameWrap = new Wrap<String>().var("classSimpleName");
		if(classSimpleName == null) {
			_classSimpleName(classSimpleNameWrap);
			Optional.ofNullable(classSimpleNameWrap.getO()).ifPresent(o -> {
				setClassSimpleName(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchClassSimpleName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassSimpleName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassSimpleName(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchClassSimpleName(siteRequest_, BaseResult.staticSetClassSimpleName(siteRequest_, o)).toString();
	}

	/////////////////////////
	// classCanonicalNames //
	/////////////////////////


	/**	 The entity classCanonicalNames
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> classCanonicalNames = new ArrayList<String>();

	/**	<br> The entity classCanonicalNames
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:classCanonicalNames">Find the entity classCanonicalNames in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _classCanonicalNames(List<String> l);

	public List<String> getClassCanonicalNames() {
		return classCanonicalNames;
	}

	public void setClassCanonicalNames(List<String> classCanonicalNames) {
		this.classCanonicalNames = classCanonicalNames;
	}
	public void setClassCanonicalNames(String o) {
		String l = BaseResult.staticSetClassCanonicalNames(siteRequest_, o);
		if(l != null)
			addClassCanonicalNames(l);
	}
	public static String staticSetClassCanonicalNames(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseResult addClassCanonicalNames(String...objects) {
		for(String o : objects) {
			addClassCanonicalNames(o);
		}
		return (BaseResult)this;
	}
	public BaseResult addClassCanonicalNames(String o) {
		if(o != null)
			this.classCanonicalNames.add(o);
		return (BaseResult)this;
	}
	@JsonIgnore
	public void setClassCanonicalNames(JsonArray objects) {
		classCanonicalNames.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addClassCanonicalNames(o);
		}
	}
	protected BaseResult classCanonicalNamesInit() {
		_classCanonicalNames(classCanonicalNames);
		return (BaseResult)this;
	}

	public static String staticSearchClassCanonicalNames(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassCanonicalNames(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassCanonicalNames(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchClassCanonicalNames(siteRequest_, BaseResult.staticSetClassCanonicalNames(siteRequest_, o)).toString();
	}

	///////////////
	// sessionId //
	///////////////


	/**	 The entity sessionId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sessionId;

	/**	<br> The entity sessionId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:sessionId">Find the entity sessionId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionId(Wrap<String> w);

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String o) {
		this.sessionId = BaseResult.staticSetSessionId(siteRequest_, o);
	}
	public static String staticSetSessionId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult sessionIdInit() {
		Wrap<String> sessionIdWrap = new Wrap<String>().var("sessionId");
		if(sessionId == null) {
			_sessionId(sessionIdWrap);
			Optional.ofNullable(sessionIdWrap.getO()).ifPresent(o -> {
				setSessionId(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchSessionId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSessionId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSessionId(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchSessionId(siteRequest_, BaseResult.staticSetSessionId(siteRequest_, o)).toString();
	}

	public String sqlSessionId() {
		return sessionId;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:userKey">Find the entity userKey in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userKey(Wrap<Long> c);

	public Long getUserKey() {
		return userKey;
	}

	public void setUserKey(Long userKey) {
		this.userKey = userKey;
	}
	@JsonIgnore
	public void setUserKey(String o) {
		this.userKey = BaseResult.staticSetUserKey(siteRequest_, o);
	}
	public static Long staticSetUserKey(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected BaseResult userKeyInit() {
		Wrap<Long> userKeyWrap = new Wrap<Long>().var("userKey");
		if(userKey == null) {
			_userKey(userKeyWrap);
			Optional.ofNullable(userKeyWrap.getO()).ifPresent(o -> {
				setUserKey(o);
			});
		}
		return (BaseResult)this;
	}

	public static Long staticSearchUserKey(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrUserKey(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserKey(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchUserKey(siteRequest_, BaseResult.staticSetUserKey(siteRequest_, o)).toString();
	}

	public Long sqlUserKey() {
		return userKey;
	}

	///////////
	// saves //
	///////////


	/**	 The entity saves
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> saves = new ArrayList<String>();

	/**	<br> The entity saves
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:saves">Find the entity saves in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _saves(List<String> l);

	public List<String> getSaves() {
		return saves;
	}

	public void setSaves(List<String> saves) {
		this.saves = saves;
	}
	public void setSaves(String o) {
		String l = BaseResult.staticSetSaves(siteRequest_, o);
		if(l != null)
			addSaves(l);
	}
	public static String staticSetSaves(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseResult addSaves(String...objects) {
		for(String o : objects) {
			addSaves(o);
		}
		return (BaseResult)this;
	}
	public BaseResult addSaves(String o) {
		if(o != null)
			this.saves.add(o);
		return (BaseResult)this;
	}
	@JsonIgnore
	public void setSaves(JsonArray objects) {
		saves.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addSaves(o);
		}
	}
	protected BaseResult savesInit() {
		_saves(saves);
		return (BaseResult)this;
	}

	public static String staticSearchSaves(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSaves(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSaves(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchSaves(siteRequest_, BaseResult.staticSetSaves(siteRequest_, o)).toString();
	}

	////////////////
	// objectIcon //
	////////////////


	/**	 The entity objectIcon
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String objectIcon;

	/**	<br> The entity objectIcon
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:objectIcon">Find the entity objectIcon in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _objectIcon(Wrap<String> w);

	public String getObjectIcon() {
		return objectIcon;
	}
	public void setObjectIcon(String o) {
		this.objectIcon = BaseResult.staticSetObjectIcon(siteRequest_, o);
	}
	public static String staticSetObjectIcon(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult objectIconInit() {
		Wrap<String> objectIconWrap = new Wrap<String>().var("objectIcon");
		if(objectIcon == null) {
			_objectIcon(objectIconWrap);
			Optional.ofNullable(objectIconWrap.getO()).ifPresent(o -> {
				setObjectIcon(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchObjectIcon(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrObjectIcon(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqObjectIcon(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchObjectIcon(siteRequest_, BaseResult.staticSetObjectIcon(siteRequest_, o)).toString();
	}

	/////////////////
	// objectTitle //
	/////////////////


	/**	 The entity objectTitle
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String objectTitle;

	/**	<br> The entity objectTitle
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:objectTitle">Find the entity objectTitle in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _objectTitle(Wrap<String> w);

	public String getObjectTitle() {
		return objectTitle;
	}
	public void setObjectTitle(String o) {
		this.objectTitle = BaseResult.staticSetObjectTitle(siteRequest_, o);
	}
	public static String staticSetObjectTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult objectTitleInit() {
		Wrap<String> objectTitleWrap = new Wrap<String>().var("objectTitle");
		if(objectTitle == null) {
			_objectTitle(objectTitleWrap);
			Optional.ofNullable(objectTitleWrap.getO()).ifPresent(o -> {
				setObjectTitle(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchObjectTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrObjectTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqObjectTitle(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchObjectTitle(siteRequest_, BaseResult.staticSetObjectTitle(siteRequest_, o)).toString();
	}

	//////////////
	// objectId //
	//////////////


	/**	 The entity objectId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String objectId;

	/**	<br> The entity objectId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:objectId">Find the entity objectId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _objectId(Wrap<String> w);

	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String o) {
		this.objectId = BaseResult.staticSetObjectId(siteRequest_, o);
	}
	public static String staticSetObjectId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult objectIdInit() {
		Wrap<String> objectIdWrap = new Wrap<String>().var("objectId");
		if(objectId == null) {
			_objectId(objectIdWrap);
			Optional.ofNullable(objectIdWrap.getO()).ifPresent(o -> {
				setObjectId(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchObjectId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrObjectId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqObjectId(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchObjectId(siteRequest_, BaseResult.staticSetObjectId(siteRequest_, o)).toString();
	}

	public String sqlObjectId() {
		return objectId;
	}

	///////////////////
	// objectSuggest //
	///////////////////


	/**	 The entity objectSuggest
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String objectSuggest;

	/**	<br> The entity objectSuggest
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:objectSuggest">Find the entity objectSuggest in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _objectSuggest(Wrap<String> w);

	public String getObjectSuggest() {
		return objectSuggest;
	}
	public void setObjectSuggest(String o) {
		this.objectSuggest = BaseResult.staticSetObjectSuggest(siteRequest_, o);
	}
	public static String staticSetObjectSuggest(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult objectSuggestInit() {
		Wrap<String> objectSuggestWrap = new Wrap<String>().var("objectSuggest");
		if(objectSuggest == null) {
			_objectSuggest(objectSuggestWrap);
			Optional.ofNullable(objectSuggestWrap.getO()).ifPresent(o -> {
				setObjectSuggest(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchObjectSuggest(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrObjectSuggest(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqObjectSuggest(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchObjectSuggest(siteRequest_, BaseResult.staticSetObjectSuggest(siteRequest_, o)).toString();
	}

	////////////////
	// objectText //
	////////////////


	/**	 The entity objectText
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> objectText = new ArrayList<String>();

	/**	<br> The entity objectText
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:objectText">Find the entity objectText in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _objectText(List<String> l);

	public List<String> getObjectText() {
		return objectText;
	}

	public void setObjectText(List<String> objectText) {
		this.objectText = objectText;
	}
	public void setObjectText(String o) {
		String l = BaseResult.staticSetObjectText(siteRequest_, o);
		if(l != null)
			addObjectText(l);
	}
	public static String staticSetObjectText(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseResult addObjectText(String...objects) {
		for(String o : objects) {
			addObjectText(o);
		}
		return (BaseResult)this;
	}
	public BaseResult addObjectText(String o) {
		if(o != null)
			this.objectText.add(o);
		return (BaseResult)this;
	}
	@JsonIgnore
	public void setObjectText(JsonArray objects) {
		objectText.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addObjectText(o);
		}
	}
	protected BaseResult objectTextInit() {
		_objectText(objectText);
		return (BaseResult)this;
	}

	public static String staticSearchObjectText(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrObjectText(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqObjectText(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchObjectText(siteRequest_, BaseResult.staticSetObjectText(siteRequest_, o)).toString();
	}

	///////////////
	// pageUrlId //
	///////////////


	/**	 The entity pageUrlId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUrlId;

	/**	<br> The entity pageUrlId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:pageUrlId">Find the entity pageUrlId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUrlId(Wrap<String> w);

	public String getPageUrlId() {
		return pageUrlId;
	}
	public void setPageUrlId(String o) {
		this.pageUrlId = BaseResult.staticSetPageUrlId(siteRequest_, o);
	}
	public static String staticSetPageUrlId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult pageUrlIdInit() {
		Wrap<String> pageUrlIdWrap = new Wrap<String>().var("pageUrlId");
		if(pageUrlId == null) {
			_pageUrlId(pageUrlIdWrap);
			Optional.ofNullable(pageUrlIdWrap.getO()).ifPresent(o -> {
				setPageUrlId(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchPageUrlId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUrlId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUrlId(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchPageUrlId(siteRequest_, BaseResult.staticSetPageUrlId(siteRequest_, o)).toString();
	}

	///////////////
	// pageUrlPk //
	///////////////


	/**	 The entity pageUrlPk
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUrlPk;

	/**	<br> The entity pageUrlPk
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:pageUrlPk">Find the entity pageUrlPk in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUrlPk(Wrap<String> w);

	public String getPageUrlPk() {
		return pageUrlPk;
	}
	public void setPageUrlPk(String o) {
		this.pageUrlPk = BaseResult.staticSetPageUrlPk(siteRequest_, o);
	}
	public static String staticSetPageUrlPk(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult pageUrlPkInit() {
		Wrap<String> pageUrlPkWrap = new Wrap<String>().var("pageUrlPk");
		if(pageUrlPk == null) {
			_pageUrlPk(pageUrlPkWrap);
			Optional.ofNullable(pageUrlPkWrap.getO()).ifPresent(o -> {
				setPageUrlPk(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchPageUrlPk(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUrlPk(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUrlPk(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchPageUrlPk(siteRequest_, BaseResult.staticSetPageUrlPk(siteRequest_, o)).toString();
	}

	////////////////
	// pageUrlApi //
	////////////////


	/**	 The entity pageUrlApi
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUrlApi;

	/**	<br> The entity pageUrlApi
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:pageUrlApi">Find the entity pageUrlApi in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUrlApi(Wrap<String> w);

	public String getPageUrlApi() {
		return pageUrlApi;
	}
	public void setPageUrlApi(String o) {
		this.pageUrlApi = BaseResult.staticSetPageUrlApi(siteRequest_, o);
	}
	public static String staticSetPageUrlApi(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult pageUrlApiInit() {
		Wrap<String> pageUrlApiWrap = new Wrap<String>().var("pageUrlApi");
		if(pageUrlApi == null) {
			_pageUrlApi(pageUrlApiWrap);
			Optional.ofNullable(pageUrlApiWrap.getO()).ifPresent(o -> {
				setPageUrlApi(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchPageUrlApi(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUrlApi(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUrlApi(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchPageUrlApi(siteRequest_, BaseResult.staticSetPageUrlApi(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = BaseResult.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchId(siteRequest_, BaseResult.staticSetId(siteRequest_, o)).toString();
	}

	public String sqlId() {
		return id;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepBaseResult(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBaseResult();
	}

	public Future<Void> promiseDeepBaseResult() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBaseResult(promise2);
		promise2.future().onSuccess(a -> {
			promise.complete();
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseBaseResult(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				siteRequest_Init();
				inheritPkInit();
				createdInit();
				modifiedInit();
				archivedInit();
				classCanonicalNameInit();
				classSimpleNameInit();
				classCanonicalNamesInit();
				sessionIdInit();
				userKeyInit();
				savesInit();
				objectIconInit();
				objectTitleInit();
				objectIdInit();
				objectSuggestInit();
				objectTextInit();
				pageUrlIdInit();
				pageUrlPkInit();
				pageUrlApiInit();
				idInit();
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

	public Future<Void> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBaseResult(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBaseResult(SiteRequest siteRequest_) {
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBaseResult(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBaseResult(v);
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
	public Object obtainBaseResult(String var) {
		BaseResult oBaseResult = (BaseResult)this;
		switch(var) {
			case "siteRequest_":
				return oBaseResult.siteRequest_;
			case "inheritPk":
				return oBaseResult.inheritPk;
			case "created":
				return oBaseResult.created;
			case "modified":
				return oBaseResult.modified;
			case "archived":
				return oBaseResult.archived;
			case "classCanonicalName":
				return oBaseResult.classCanonicalName;
			case "classSimpleName":
				return oBaseResult.classSimpleName;
			case "classCanonicalNames":
				return oBaseResult.classCanonicalNames;
			case "sessionId":
				return oBaseResult.sessionId;
			case "userKey":
				return oBaseResult.userKey;
			case "saves":
				return oBaseResult.saves;
			case "objectIcon":
				return oBaseResult.objectIcon;
			case "objectTitle":
				return oBaseResult.objectTitle;
			case "objectId":
				return oBaseResult.objectId;
			case "objectSuggest":
				return oBaseResult.objectSuggest;
			case "objectText":
				return oBaseResult.objectText;
			case "pageUrlId":
				return oBaseResult.pageUrlId;
			case "pageUrlPk":
				return oBaseResult.pageUrlPk;
			case "pageUrlApi":
				return oBaseResult.pageUrlApi;
			case "id":
				return oBaseResult.id;
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
				o = relateBaseResult(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBaseResult(String var, Object val) {
		BaseResult oBaseResult = (BaseResult)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetBaseResult(entityVar,  siteRequest_, o);
	}
	public static Object staticSetBaseResult(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "inheritPk":
			return BaseResult.staticSetInheritPk(siteRequest_, o);
		case "created":
			return BaseResult.staticSetCreated(siteRequest_, o);
		case "modified":
			return BaseResult.staticSetModified(siteRequest_, o);
		case "archived":
			return BaseResult.staticSetArchived(siteRequest_, o);
		case "classCanonicalName":
			return BaseResult.staticSetClassCanonicalName(siteRequest_, o);
		case "classSimpleName":
			return BaseResult.staticSetClassSimpleName(siteRequest_, o);
		case "classCanonicalNames":
			return BaseResult.staticSetClassCanonicalNames(siteRequest_, o);
		case "sessionId":
			return BaseResult.staticSetSessionId(siteRequest_, o);
		case "userKey":
			return BaseResult.staticSetUserKey(siteRequest_, o);
		case "saves":
			return BaseResult.staticSetSaves(siteRequest_, o);
		case "objectIcon":
			return BaseResult.staticSetObjectIcon(siteRequest_, o);
		case "objectTitle":
			return BaseResult.staticSetObjectTitle(siteRequest_, o);
		case "objectId":
			return BaseResult.staticSetObjectId(siteRequest_, o);
		case "objectSuggest":
			return BaseResult.staticSetObjectSuggest(siteRequest_, o);
		case "objectText":
			return BaseResult.staticSetObjectText(siteRequest_, o);
		case "pageUrlId":
			return BaseResult.staticSetPageUrlId(siteRequest_, o);
		case "pageUrlPk":
			return BaseResult.staticSetPageUrlPk(siteRequest_, o);
		case "pageUrlApi":
			return BaseResult.staticSetPageUrlApi(siteRequest_, o);
		case "id":
			return BaseResult.staticSetId(siteRequest_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBaseResult(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBaseResult(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "inheritPk":
			return BaseResult.staticSearchInheritPk(siteRequest_, (String)o);
		case "created":
			return BaseResult.staticSearchCreated(siteRequest_, (ZonedDateTime)o);
		case "modified":
			return BaseResult.staticSearchModified(siteRequest_, (ZonedDateTime)o);
		case "archived":
			return BaseResult.staticSearchArchived(siteRequest_, (Boolean)o);
		case "classCanonicalName":
			return BaseResult.staticSearchClassCanonicalName(siteRequest_, (String)o);
		case "classSimpleName":
			return BaseResult.staticSearchClassSimpleName(siteRequest_, (String)o);
		case "classCanonicalNames":
			return BaseResult.staticSearchClassCanonicalNames(siteRequest_, (String)o);
		case "sessionId":
			return BaseResult.staticSearchSessionId(siteRequest_, (String)o);
		case "userKey":
			return BaseResult.staticSearchUserKey(siteRequest_, (Long)o);
		case "saves":
			return BaseResult.staticSearchSaves(siteRequest_, (String)o);
		case "objectIcon":
			return BaseResult.staticSearchObjectIcon(siteRequest_, (String)o);
		case "objectTitle":
			return BaseResult.staticSearchObjectTitle(siteRequest_, (String)o);
		case "objectId":
			return BaseResult.staticSearchObjectId(siteRequest_, (String)o);
		case "objectSuggest":
			return BaseResult.staticSearchObjectSuggest(siteRequest_, (String)o);
		case "objectText":
			return BaseResult.staticSearchObjectText(siteRequest_, (String)o);
		case "pageUrlId":
			return BaseResult.staticSearchPageUrlId(siteRequest_, (String)o);
		case "pageUrlPk":
			return BaseResult.staticSearchPageUrlPk(siteRequest_, (String)o);
		case "pageUrlApi":
			return BaseResult.staticSearchPageUrlApi(siteRequest_, (String)o);
		case "id":
			return BaseResult.staticSearchId(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBaseResult(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBaseResult(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "inheritPk":
			return BaseResult.staticSearchStrInheritPk(siteRequest_, (String)o);
		case "created":
			return BaseResult.staticSearchStrCreated(siteRequest_, (String)o);
		case "modified":
			return BaseResult.staticSearchStrModified(siteRequest_, (String)o);
		case "archived":
			return BaseResult.staticSearchStrArchived(siteRequest_, (Boolean)o);
		case "classCanonicalName":
			return BaseResult.staticSearchStrClassCanonicalName(siteRequest_, (String)o);
		case "classSimpleName":
			return BaseResult.staticSearchStrClassSimpleName(siteRequest_, (String)o);
		case "classCanonicalNames":
			return BaseResult.staticSearchStrClassCanonicalNames(siteRequest_, (String)o);
		case "sessionId":
			return BaseResult.staticSearchStrSessionId(siteRequest_, (String)o);
		case "userKey":
			return BaseResult.staticSearchStrUserKey(siteRequest_, (Long)o);
		case "saves":
			return BaseResult.staticSearchStrSaves(siteRequest_, (String)o);
		case "objectIcon":
			return BaseResult.staticSearchStrObjectIcon(siteRequest_, (String)o);
		case "objectTitle":
			return BaseResult.staticSearchStrObjectTitle(siteRequest_, (String)o);
		case "objectId":
			return BaseResult.staticSearchStrObjectId(siteRequest_, (String)o);
		case "objectSuggest":
			return BaseResult.staticSearchStrObjectSuggest(siteRequest_, (String)o);
		case "objectText":
			return BaseResult.staticSearchStrObjectText(siteRequest_, (String)o);
		case "pageUrlId":
			return BaseResult.staticSearchStrPageUrlId(siteRequest_, (String)o);
		case "pageUrlPk":
			return BaseResult.staticSearchStrPageUrlPk(siteRequest_, (String)o);
		case "pageUrlApi":
			return BaseResult.staticSearchStrPageUrlApi(siteRequest_, (String)o);
		case "id":
			return BaseResult.staticSearchStrId(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBaseResult(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBaseResult(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "inheritPk":
			return BaseResult.staticSearchFqInheritPk(siteRequest_, o);
		case "created":
			return BaseResult.staticSearchFqCreated(siteRequest_, o);
		case "modified":
			return BaseResult.staticSearchFqModified(siteRequest_, o);
		case "archived":
			return BaseResult.staticSearchFqArchived(siteRequest_, o);
		case "classCanonicalName":
			return BaseResult.staticSearchFqClassCanonicalName(siteRequest_, o);
		case "classSimpleName":
			return BaseResult.staticSearchFqClassSimpleName(siteRequest_, o);
		case "classCanonicalNames":
			return BaseResult.staticSearchFqClassCanonicalNames(siteRequest_, o);
		case "sessionId":
			return BaseResult.staticSearchFqSessionId(siteRequest_, o);
		case "userKey":
			return BaseResult.staticSearchFqUserKey(siteRequest_, o);
		case "saves":
			return BaseResult.staticSearchFqSaves(siteRequest_, o);
		case "objectIcon":
			return BaseResult.staticSearchFqObjectIcon(siteRequest_, o);
		case "objectTitle":
			return BaseResult.staticSearchFqObjectTitle(siteRequest_, o);
		case "objectId":
			return BaseResult.staticSearchFqObjectId(siteRequest_, o);
		case "objectSuggest":
			return BaseResult.staticSearchFqObjectSuggest(siteRequest_, o);
		case "objectText":
			return BaseResult.staticSearchFqObjectText(siteRequest_, o);
		case "pageUrlId":
			return BaseResult.staticSearchFqPageUrlId(siteRequest_, o);
		case "pageUrlPk":
			return BaseResult.staticSearchFqPageUrlPk(siteRequest_, o);
		case "pageUrlApi":
			return BaseResult.staticSearchFqPageUrlApi(siteRequest_, o);
		case "id":
			return BaseResult.staticSearchFqId(siteRequest_, o);
			default:
				return null;
		}
	}

	/////////////
	// persist //
	/////////////

	public boolean persistForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = persistBaseResult(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistBaseResult(String var, Object val) {
		String varLower = var.toLowerCase();
			if("inheritpk".equals(varLower)) {
				if(val instanceof String) {
					setInheritPk((String)val);
				}
				saves.add("inheritPk");
				return val;
			} else if("created".equals(varLower)) {
				if(val instanceof String) {
					setCreated((String)val);
				} else if(val instanceof OffsetDateTime) {
					setCreated(((OffsetDateTime)val).atZoneSameInstant(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))));
				}
				saves.add("created");
				return val;
			} else if("archived".equals(varLower)) {
				if(val instanceof Boolean) {
					setArchived((Boolean)val);
				} else {
					setArchived(val == null ? null : val.toString());
				}
				saves.add("archived");
				return val;
			} else if("sessionid".equals(varLower)) {
				if(val instanceof String) {
					setSessionId((String)val);
				}
				saves.add("sessionId");
				return val;
			} else if("userkey".equals(varLower)) {
				if(val instanceof Long) {
					setUserKey((Long)val);
				} else {
					setUserKey(val == null ? null : val.toString());
				}
				saves.add("userKey");
				return val;
			} else if("objectid".equals(varLower)) {
				if(val instanceof String) {
					setObjectId((String)val);
				}
				saves.add("objectId");
				return val;
			} else if("id".equals(varLower)) {
				if(val instanceof String) {
					setId((String)val);
				}
				saves.add("id");
				return val;
		} else {
			return null;
		}
	}

	/////////////
	// populate //
	/////////////

	public void populateForClass(SolrResponse.Doc doc) {
		populateBaseResult(doc);
	}
	public void populateBaseResult(SolrResponse.Doc doc) {
		BaseResult oBaseResult = (BaseResult)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("inheritPk")) {
				String inheritPk = (String)doc.get("inheritPk_docvalues_string");
				if(inheritPk != null)
					oBaseResult.setInheritPk(inheritPk);
			}

			if(saves.contains("created")) {
				String created = (String)doc.get("created_docvalues_date");
				if(created != null)
					oBaseResult.setCreated(created);
			}

			if(saves.contains("modified")) {
				String modified = (String)doc.get("modified_docvalues_date");
				if(modified != null)
					oBaseResult.setModified(modified);
			}

			if(saves.contains("archived")) {
				Boolean archived = (Boolean)doc.get("archived_docvalues_boolean");
				if(archived != null)
					oBaseResult.setArchived(archived);
			}

			if(saves.contains("classCanonicalName")) {
				String classCanonicalName = (String)doc.get("classCanonicalName_docvalues_string");
				if(classCanonicalName != null)
					oBaseResult.setClassCanonicalName(classCanonicalName);
			}

			if(saves.contains("classSimpleName")) {
				String classSimpleName = (String)doc.get("classSimpleName_docvalues_string");
				if(classSimpleName != null)
					oBaseResult.setClassSimpleName(classSimpleName);
			}

			if(saves.contains("classCanonicalNames")) {
				List<String> classCanonicalNames = (List<String>)doc.get("classCanonicalNames_docvalues_strings");
				if(classCanonicalNames != null)
					oBaseResult.classCanonicalNames.addAll(classCanonicalNames);
			}

			if(saves.contains("sessionId")) {
				String sessionId = (String)doc.get("sessionId_docvalues_string");
				if(sessionId != null)
					oBaseResult.setSessionId(sessionId);
			}

			if(saves.contains("userKey")) {
				Long userKey = (Long)doc.get("userKey_docvalues_long");
				if(userKey != null)
					oBaseResult.setUserKey(userKey);
			}

			if(saves.contains("saves")) {
				List<String> saves = (List<String>)doc.get("saves_docvalues_strings");
				if(saves != null)
					oBaseResult.saves.addAll(saves);
			}

			if(saves.contains("objectIcon")) {
				String objectIcon = (String)doc.get("objectIcon_docvalues_string");
				if(objectIcon != null)
					oBaseResult.setObjectIcon(objectIcon);
			}

			if(saves.contains("objectTitle")) {
				String objectTitle = (String)doc.get("objectTitle_docvalues_string");
				if(objectTitle != null)
					oBaseResult.setObjectTitle(objectTitle);
			}

			if(saves.contains("objectId")) {
				String objectId = (String)doc.get("objectId_docvalues_string");
				if(objectId != null)
					oBaseResult.setObjectId(objectId);
			}

			if(saves.contains("objectSuggest")) {
				String objectSuggest = (String)doc.get("objectSuggest_suggested");
				oBaseResult.setObjectSuggest(objectSuggest);
			}

			if(saves.contains("pageUrlId")) {
				String pageUrlId = (String)doc.get("pageUrlId_docvalues_string");
				if(pageUrlId != null)
					oBaseResult.setPageUrlId(pageUrlId);
			}

			if(saves.contains("pageUrlPk")) {
				String pageUrlPk = (String)doc.get("pageUrlPk_docvalues_string");
				if(pageUrlPk != null)
					oBaseResult.setPageUrlPk(pageUrlPk);
			}

			if(saves.contains("pageUrlApi")) {
				String pageUrlApi = (String)doc.get("pageUrlApi_docvalues_string");
				if(pageUrlApi != null)
					oBaseResult.setPageUrlApi(pageUrlApi);
			}

			String id = (String)doc.get("id");
			oBaseResult.setId(id);
		}
	}

	public void indexBaseResult(JsonObject doc) {
		if(inheritPk != null) {
			doc.put("inheritPk_docvalues_string", inheritPk);
		}
		if(created != null) {
			doc.put("created_docvalues_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(created.toInstant(), ZoneId.of("UTC"))));
		}
		if(modified != null) {
			doc.put("modified_docvalues_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(modified.toInstant(), ZoneId.of("UTC"))));
		}
		if(archived != null) {
			doc.put("archived_docvalues_boolean", archived);
		}
		if(classCanonicalName != null) {
			doc.put("classCanonicalName_docvalues_string", classCanonicalName);
		}
		if(classSimpleName != null) {
			doc.put("classSimpleName_docvalues_string", classSimpleName);
		}
		if(classCanonicalNames != null) {
			JsonArray l = new JsonArray();
			doc.put("classCanonicalNames_docvalues_strings", l);
			for(String o : classCanonicalNames) {
				l.add(o);
			}
		}
		if(sessionId != null) {
			doc.put("sessionId_docvalues_string", sessionId);
		}
		if(userKey != null) {
			doc.put("userKey_docvalues_long", userKey);
		}
		if(saves != null) {
			JsonArray l = new JsonArray();
			doc.put("saves_docvalues_strings", l);
			for(String o : saves) {
				l.add(o);
			}
		}
		if(objectIcon != null) {
			doc.put("objectIcon_docvalues_string", objectIcon);
		}
		if(objectTitle != null) {
			doc.put("objectTitle_docvalues_string", objectTitle);
		}
		if(objectId != null) {
			doc.put("objectId_docvalues_string", objectId);
		}
		if(objectSuggest != null) {
			doc.put("objectSuggest_suggested", objectSuggest);
		}
		if(objectText != null) {
			JsonArray l = new JsonArray();
			doc.put("objectText_text_enUS", l);
			for(String o : objectText) {
				l.add(o);
			}
		}
		if(pageUrlId != null) {
			doc.put("pageUrlId_docvalues_string", pageUrlId);
		}
		if(pageUrlPk != null) {
			doc.put("pageUrlPk_docvalues_string", pageUrlPk);
		}
		if(pageUrlApi != null) {
			doc.put("pageUrlApi_docvalues_string", pageUrlApi);
		}
		if(id != null) {
			doc.put("id", id);
		}
	}

	public static String varStoredBaseResult(String entityVar) {
		switch(entityVar) {
			case "inheritPk":
				return "inheritPk_docvalues_string";
			case "created":
				return "created_docvalues_date";
			case "modified":
				return "modified_docvalues_date";
			case "archived":
				return "archived_docvalues_boolean";
			case "classCanonicalName":
				return "classCanonicalName_docvalues_string";
			case "classSimpleName":
				return "classSimpleName_docvalues_string";
			case "classCanonicalNames":
				return "classCanonicalNames_docvalues_strings";
			case "sessionId":
				return "sessionId_docvalues_string";
			case "userKey":
				return "userKey_docvalues_long";
			case "saves":
				return "saves_docvalues_strings";
			case "objectIcon":
				return "objectIcon_docvalues_string";
			case "objectTitle":
				return "objectTitle_docvalues_string";
			case "objectId":
				return "objectId_docvalues_string";
			case "pageUrlId":
				return "pageUrlId_docvalues_string";
			case "pageUrlPk":
				return "pageUrlPk_docvalues_string";
			case "pageUrlApi":
				return "pageUrlApi_docvalues_string";
			default:
				return null;
		}
	}

	public static String varIndexedBaseResult(String entityVar) {
		switch(entityVar) {
			case "inheritPk":
				return "inheritPk_docvalues_string";
			case "created":
				return "created_docvalues_date";
			case "modified":
				return "modified_docvalues_date";
			case "archived":
				return "archived_docvalues_boolean";
			case "classCanonicalName":
				return "classCanonicalName_docvalues_string";
			case "classSimpleName":
				return "classSimpleName_docvalues_string";
			case "classCanonicalNames":
				return "classCanonicalNames_docvalues_strings";
			case "sessionId":
				return "sessionId_docvalues_string";
			case "userKey":
				return "userKey_docvalues_long";
			case "saves":
				return "saves_docvalues_strings";
			case "objectIcon":
				return "objectIcon_docvalues_string";
			case "objectTitle":
				return "objectTitle_docvalues_string";
			case "objectId":
				return "objectId_docvalues_string";
			case "objectSuggest":
				return "objectSuggest_suggested";
			case "objectText":
				return "objectText_text_enUS";
			case "pageUrlId":
				return "pageUrlId_docvalues_string";
			case "pageUrlPk":
				return "pageUrlPk_docvalues_string";
			case "pageUrlApi":
				return "pageUrlApi_docvalues_string";
			case "id":
				return "id";
			default:
				return null;
		}
	}

	public static String searchVarBaseResult(String searchVar) {
		switch(searchVar) {
			case "inheritPk_docvalues_string":
				return "inheritPk";
			case "created_docvalues_date":
				return "created";
			case "modified_docvalues_date":
				return "modified";
			case "archived_docvalues_boolean":
				return "archived";
			case "classCanonicalName_docvalues_string":
				return "classCanonicalName";
			case "classSimpleName_docvalues_string":
				return "classSimpleName";
			case "classCanonicalNames_docvalues_strings":
				return "classCanonicalNames";
			case "sessionId_docvalues_string":
				return "sessionId";
			case "userKey_docvalues_long":
				return "userKey";
			case "saves_docvalues_strings":
				return "saves";
			case "objectIcon_docvalues_string":
				return "objectIcon";
			case "objectTitle_docvalues_string":
				return "objectTitle";
			case "objectId_docvalues_string":
				return "objectId";
			case "objectSuggest_suggested":
				return "objectSuggest";
			case "objectText_text_enUS":
				return "objectText";
			case "pageUrlId_docvalues_string":
				return "pageUrlId";
			case "pageUrlPk_docvalues_string":
				return "pageUrlPk";
			case "pageUrlApi_docvalues_string":
				return "pageUrlApi";
			case "id":
				return "id";
			default:
				return null;
		}
	}

	public static String varSearchBaseResult(String entityVar) {
		switch(entityVar) {
			case "objectText":
				return "objectText_text_enUS";
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				return null;
		}
	}

	public static String varSuggestedBaseResult(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				return null;
		}
	}

	/////////////
	// store //
	/////////////

	public void storeForClass(SolrResponse.Doc doc) {
		storeBaseResult(doc);
	}
	public void storeBaseResult(SolrResponse.Doc doc) {
		BaseResult oBaseResult = (BaseResult)this;
		SiteRequest siteRequest = oBaseResult.getSiteRequest_();

		oBaseResult.setInheritPk(Optional.ofNullable(doc.get("inheritPk_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setCreated(Optional.ofNullable(doc.get("created_docvalues_date")).map(v -> v.toString()).orElse(null));
		oBaseResult.setModified(Optional.ofNullable(doc.get("modified_docvalues_date")).map(v -> v.toString()).orElse(null));
		oBaseResult.setArchived(Optional.ofNullable(doc.get("archived_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBaseResult.setClassCanonicalName(Optional.ofNullable(doc.get("classCanonicalName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setClassSimpleName(Optional.ofNullable(doc.get("classSimpleName_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("classCanonicalNames_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseResult.addClassCanonicalNames(BaseResult.staticSetClassCanonicalNames(siteRequest, v.toString()));
		});
		oBaseResult.setSessionId(Optional.ofNullable(doc.get("sessionId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setUserKey(Optional.ofNullable(doc.get("userKey_docvalues_long")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("saves_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseResult.addSaves(BaseResult.staticSetSaves(siteRequest, v.toString()));
		});
		oBaseResult.setObjectIcon(Optional.ofNullable(doc.get("objectIcon_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setObjectTitle(Optional.ofNullable(doc.get("objectTitle_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setObjectId(Optional.ofNullable(doc.get("objectId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setObjectSuggest(Optional.ofNullable(doc.get("objectSuggest_suggested")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("objectText_text_enUS")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseResult.addObjectText(BaseResult.staticSetObjectText(siteRequest, v.toString()));
		});
		oBaseResult.setPageUrlId(Optional.ofNullable(doc.get("pageUrlId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setPageUrlPk(Optional.ofNullable(doc.get("pageUrlPk_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setPageUrlApi(Optional.ofNullable(doc.get("pageUrlApi_docvalues_string")).map(v -> v.toString()).orElse(null));
		String id = (String)doc.get("id");
		oBaseResult.setId(id);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBaseResult() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BaseResult) {
			BaseResult original = (BaseResult)o;
			if(!Objects.equals(inheritPk, original.getInheritPk()))
				apiRequest.addVars("inheritPk");
			if(!Objects.equals(created, original.getCreated()))
				apiRequest.addVars("created");
			if(!Objects.equals(modified, original.getModified()))
				apiRequest.addVars("modified");
			if(!Objects.equals(archived, original.getArchived()))
				apiRequest.addVars("archived");
			if(!Objects.equals(classCanonicalName, original.getClassCanonicalName()))
				apiRequest.addVars("classCanonicalName");
			if(!Objects.equals(classSimpleName, original.getClassSimpleName()))
				apiRequest.addVars("classSimpleName");
			if(!Objects.equals(classCanonicalNames, original.getClassCanonicalNames()))
				apiRequest.addVars("classCanonicalNames");
			if(!Objects.equals(sessionId, original.getSessionId()))
				apiRequest.addVars("sessionId");
			if(!Objects.equals(userKey, original.getUserKey()))
				apiRequest.addVars("userKey");
			if(!Objects.equals(saves, original.getSaves()))
				apiRequest.addVars("saves");
			if(!Objects.equals(objectIcon, original.getObjectIcon()))
				apiRequest.addVars("objectIcon");
			if(!Objects.equals(objectTitle, original.getObjectTitle()))
				apiRequest.addVars("objectTitle");
			if(!Objects.equals(objectId, original.getObjectId()))
				apiRequest.addVars("objectId");
			if(!Objects.equals(objectSuggest, original.getObjectSuggest()))
				apiRequest.addVars("objectSuggest");
			if(!Objects.equals(objectText, original.getObjectText()))
				apiRequest.addVars("objectText");
			if(!Objects.equals(pageUrlId, original.getPageUrlId()))
				apiRequest.addVars("pageUrlId");
			if(!Objects.equals(pageUrlPk, original.getPageUrlPk()))
				apiRequest.addVars("pageUrlPk");
			if(!Objects.equals(pageUrlApi, original.getPageUrlApi()))
				apiRequest.addVars("pageUrlApi");
			if(!Objects.equals(id, original.getId()))
				apiRequest.addVars("id");
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Optional.ofNullable(inheritPk).map(v -> "inheritPk: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(created).map(v -> "created: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(modified).map(v -> "modified: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(archived).map(v -> "archived: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(classCanonicalName).map(v -> "classCanonicalName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(classSimpleName).map(v -> "classSimpleName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(classCanonicalNames).map(v -> "classCanonicalNames: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(sessionId).map(v -> "sessionId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userKey).map(v -> "userKey: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(saves).map(v -> "saves: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(objectIcon).map(v -> "objectIcon: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(objectTitle).map(v -> "objectTitle: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(objectId).map(v -> "objectId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(objectSuggest).map(v -> "objectSuggest: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(objectText).map(v -> "objectText: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(pageUrlId).map(v -> "pageUrlId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(pageUrlPk).map(v -> "pageUrlPk: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(pageUrlApi).map(v -> "pageUrlApi: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(id).map(v -> "id: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "BaseResult";
	public static final String VAR_siteRequest_ = "siteRequest_";
	public static final String VAR_inheritPk = "inheritPk";
	public static final String VAR_created = "created";
	public static final String VAR_modified = "modified";
	public static final String VAR_archived = "archived";
	public static final String VAR_classCanonicalName = "classCanonicalName";
	public static final String VAR_classSimpleName = "classSimpleName";
	public static final String VAR_classCanonicalNames = "classCanonicalNames";
	public static final String VAR_sessionId = "sessionId";
	public static final String VAR_userKey = "userKey";
	public static final String VAR_saves = "saves";
	public static final String VAR_objectIcon = "objectIcon";
	public static final String VAR_objectTitle = "objectTitle";
	public static final String VAR_objectId = "objectId";
	public static final String VAR_objectSuggest = "objectSuggest";
	public static final String VAR_objectText = "objectText";
	public static final String VAR_pageUrlId = "pageUrlId";
	public static final String VAR_pageUrlPk = "pageUrlPk";
	public static final String VAR_pageUrlApi = "pageUrlApi";
	public static final String VAR_id = "id";

	public static List<String> varsQForClass() {
		return BaseResult.varsQBaseResult(new ArrayList<String>());
	}
	public static List<String> varsQBaseResult(List<String> vars) {
		vars.add(VAR_objectSuggest);
		vars.add(VAR_objectText);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return BaseResult.varsFqBaseResult(new ArrayList<String>());
	}
	public static List<String> varsFqBaseResult(List<String> vars) {
		vars.add(VAR_created);
		vars.add(VAR_modified);
		vars.add(VAR_classSimpleName);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return BaseResult.varsRangeBaseResult(new ArrayList<String>());
	}
	public static List<String> varsRangeBaseResult(List<String> vars) {
		vars.add(VAR_created);
		vars.add(VAR_modified);
		return vars;
	}

	public static final String DISPLAY_NAME_siteRequest_ = "";
	public static final String DISPLAY_NAME_inheritPk = "";
	public static final String DISPLAY_NAME_created = "created";
	public static final String DISPLAY_NAME_modified = "modified";
	public static final String DISPLAY_NAME_archived = "archived";
	public static final String DISPLAY_NAME_classCanonicalName = "";
	public static final String DISPLAY_NAME_classSimpleName = "object type";
	public static final String DISPLAY_NAME_classCanonicalNames = "";
	public static final String DISPLAY_NAME_sessionId = "";
	public static final String DISPLAY_NAME_userKey = "";
	public static final String DISPLAY_NAME_saves = "";
	public static final String DISPLAY_NAME_objectIcon = "";
	public static final String DISPLAY_NAME_objectTitle = "";
	public static final String DISPLAY_NAME_objectId = "ID";
	public static final String DISPLAY_NAME_objectSuggest = "autosuggest";
	public static final String DISPLAY_NAME_objectText = "text";
	public static final String DISPLAY_NAME_pageUrlId = "";
	public static final String DISPLAY_NAME_pageUrlPk = "";
	public static final String DISPLAY_NAME_pageUrlApi = "";
	public static final String DISPLAY_NAME_id = "";

	public static String displayNameForClass(String var) {
		return BaseResult.displayNameBaseResult(var);
	}
	public static String displayNameBaseResult(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return DISPLAY_NAME_siteRequest_;
		case VAR_inheritPk:
			return DISPLAY_NAME_inheritPk;
		case VAR_created:
			return DISPLAY_NAME_created;
		case VAR_modified:
			return DISPLAY_NAME_modified;
		case VAR_archived:
			return DISPLAY_NAME_archived;
		case VAR_classCanonicalName:
			return DISPLAY_NAME_classCanonicalName;
		case VAR_classSimpleName:
			return DISPLAY_NAME_classSimpleName;
		case VAR_classCanonicalNames:
			return DISPLAY_NAME_classCanonicalNames;
		case VAR_sessionId:
			return DISPLAY_NAME_sessionId;
		case VAR_userKey:
			return DISPLAY_NAME_userKey;
		case VAR_saves:
			return DISPLAY_NAME_saves;
		case VAR_objectIcon:
			return DISPLAY_NAME_objectIcon;
		case VAR_objectTitle:
			return DISPLAY_NAME_objectTitle;
		case VAR_objectId:
			return DISPLAY_NAME_objectId;
		case VAR_objectSuggest:
			return DISPLAY_NAME_objectSuggest;
		case VAR_objectText:
			return DISPLAY_NAME_objectText;
		case VAR_pageUrlId:
			return DISPLAY_NAME_pageUrlId;
		case VAR_pageUrlPk:
			return DISPLAY_NAME_pageUrlPk;
		case VAR_pageUrlApi:
			return DISPLAY_NAME_pageUrlApi;
		case VAR_id:
			return DISPLAY_NAME_id;
		default:
			return null;
		}
	}

	public static String descriptionBaseResult(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return "The current request object";
		case VAR_inheritPk:
			return "An optional inherited primary key from a legacy system for this object in the database";
		case VAR_created:
			return "A created timestamp for this record in the database";
		case VAR_modified:
			return "A modified timestamp for this record in the database";
		case VAR_archived:
			return "For archiving this record";
		case VAR_classCanonicalName:
			return "the canonical name of this Java class";
		case VAR_classSimpleName:
			return "The simple name of this Java class";
		case VAR_classCanonicalNames:
			return "All the inherited canonical names of this Java class";
		case VAR_sessionId:
			return "The session ID of the user that created this object";
		case VAR_userKey:
			return "The primary key of the user that created this record";
		case VAR_saves:
			return "A list of fields that are saved for this record in the database";
		case VAR_objectIcon:
			return "The icon HTML";
		case VAR_objectTitle:
			return "The title of this object";
		case VAR_objectId:
			return "A URL friendly unique ID for this object";
		case VAR_objectSuggest:
			return "The indexed field in the search engine for this record while using autosuggest";
		case VAR_objectText:
			return "The full text search field in the search engine for this record while using autosuggest";
		case VAR_pageUrlId:
			return "The link by name for this object in the UI";
		case VAR_pageUrlPk:
			return "The link by primary key for this object in the UI";
		case VAR_pageUrlApi:
			return "The link to this object in the API";
		case VAR_id:
			return "The unique key for this record in the search engine";
			default:
				return null;
		}
	}

	public static String classSimpleNameBaseResult(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return "SiteRequest";
		case VAR_inheritPk:
			return "String";
		case VAR_created:
			return "ZonedDateTime";
		case VAR_modified:
			return "ZonedDateTime";
		case VAR_archived:
			return "Boolean";
		case VAR_classCanonicalName:
			return "String";
		case VAR_classSimpleName:
			return "String";
		case VAR_classCanonicalNames:
			return "List";
		case VAR_sessionId:
			return "String";
		case VAR_userKey:
			return "Long";
		case VAR_saves:
			return "List";
		case VAR_objectIcon:
			return "String";
		case VAR_objectTitle:
			return "String";
		case VAR_objectId:
			return "String";
		case VAR_objectSuggest:
			return "String";
		case VAR_objectText:
			return "List";
		case VAR_pageUrlId:
			return "String";
		case VAR_pageUrlPk:
			return "String";
		case VAR_pageUrlApi:
			return "String";
		case VAR_id:
			return "String";
			default:
				return null;
		}
	}

	public static Integer htmColumnBaseResult(String var) {
		switch(var) {
		case VAR_created:
			return 2;
		case VAR_objectTitle:
			return 3;
		case VAR_objectId:
			return 1;
			default:
				return null;
		}
	}

	public static Integer htmRowBaseResult(String var) {
		switch(var) {
		case VAR_created:
			return 1;
		case VAR_modified:
			return 1;
		case VAR_archived:
			return 2;
		case VAR_objectId:
			return 1;
			default:
				return null;
		}
	}

	public static Integer htmCellBaseResult(String var) {
		switch(var) {
		case VAR_created:
			return 2;
		case VAR_modified:
			return 3;
		case VAR_archived:
			return 1;
		case VAR_objectId:
			return 4;
			default:
				return null;
		}
	}

	public static Integer lengthMinBaseResult(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer lengthMaxBaseResult(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer maxBaseResult(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer minBaseResult(String var) {
		switch(var) {
			default:
				return null;
		}
	}
}
