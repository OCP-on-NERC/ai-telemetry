package org.mghpcc.aitelemetry.model;

import org.mghpcc.aitelemetry.request.SiteRequest;
import java.lang.Object;
import org.mghpcc.aitelemetry.model.BaseModel;
import io.vertx.core.json.JsonObject;
import java.util.Date;
import java.util.Set;
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
import java.lang.Long;
import java.lang.String;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.Locale;
import java.time.OffsetDateTime;
import java.lang.Boolean;
import io.vertx.core.json.JsonArray;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BaseModel objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BaseModelGen into the class BaseModel. 
 * </li>
 * <h3>About the BaseModel class and it's generated class BaseModelGen&lt;Object&gt;: </h3>extends BaseModelGen
 * <p>
 * This Java class extends a generated Java class BaseModelGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel">Find the class BaseModel in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BaseModelGen<Object>
 * <p>This <code>class BaseModel extends BaseModelGen&lt;Object&gt;</code>, which means it extends a newly generated BaseModelGen. 
 * The generated <code>class BaseModelGen extends Object</code> which means that BaseModel extends BaseModelGen which extends Object. 
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
 * <p>By adding a class comment "{@inheritDoc}", the BaseModel class will inherit the helpful inherited class comments from the super class BaseModelGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 0</h2>
 * <p>This class contains a comment <b>"Order: 0"</b>, which means this class will be sorted by the given number 0 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 0</h2>
 * <p>This class contains a comment <b>"SqlOrder: 0"</b>, which means this class will be sorted by the given number 0 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BaseModel Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class BaseModel in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BaseModelGen<DEV> extends Object {
	protected static final Logger LOG = LoggerFactory.getLogger(BaseModel.class);

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:siteRequest_">Find the entity siteRequest_ in Solr</a>
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
	protected BaseModel siteRequest_Init() {
		Wrap<SiteRequest> siteRequest_Wrap = new Wrap<SiteRequest>().var("siteRequest_");
		if(siteRequest_ == null) {
			_siteRequest_(siteRequest_Wrap);
			Optional.ofNullable(siteRequest_Wrap.getO()).ifPresent(o -> {
				setSiteRequest_(o);
			});
		}
		return (BaseModel)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = BaseModel.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected BaseModel pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (BaseModel)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchPk(siteRequest_, BaseModel.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:created">Find the entity created in Solr</a>
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
		ZoneId zoneId = Optional.ofNullable(siteRequest_).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"));
		this.created = BaseModel.staticSetCreated(siteRequest_, o, zoneId);
	}
	@JsonIgnore
	public void setCreated(Date o) {
		this.created = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	public static ZonedDateTime staticSetCreated(SiteRequest siteRequest_, String o, ZoneId zoneId) {
		if(StringUtils.endsWith(o, "]"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER);
		else if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(zoneId).truncatedTo(ChronoUnit.MILLIS);
		else if(StringUtils.contains(o, "T"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(zoneId).truncatedTo(ChronoUnit.MILLIS);
	}
	protected BaseModel createdInit() {
		Wrap<ZonedDateTime> createdWrap = new Wrap<ZonedDateTime>().var("created");
		if(created == null) {
			_created(createdWrap);
			Optional.ofNullable(createdWrap.getO()).ifPresent(o -> {
				setCreated(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchCreated(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrCreated(SiteRequest siteRequest_, String o) {
		ZoneId zoneId = ZoneId.of("UTC");
		return BaseModel.staticSearchCreated(siteRequest_, BaseModel.staticSetCreated(siteRequest_, o, zoneId));
	}

	public static String staticSearchFqCreated(SiteRequest siteRequest_, String o) {
		ZoneId zoneId = ZoneId.of("UTC");
		return BaseModel.staticSearchCreated(siteRequest_, BaseModel.staticSetCreated(siteRequest_, o, zoneId)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:modified">Find the entity modified in Solr</a>
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
		ZoneId zoneId = Optional.ofNullable(siteRequest_).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"));
		this.modified = BaseModel.staticSetModified(siteRequest_, o, zoneId);
	}
	@JsonIgnore
	public void setModified(Date o) {
		this.modified = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))).truncatedTo(ChronoUnit.MILLIS);
	}
	public static ZonedDateTime staticSetModified(SiteRequest siteRequest_, String o, ZoneId zoneId) {
		if(StringUtils.endsWith(o, "]"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER);
		else if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(zoneId).truncatedTo(ChronoUnit.MILLIS);
		else if(StringUtils.contains(o, "T"))
			return o == null ? null : ZonedDateTime.parse(o, ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(zoneId).truncatedTo(ChronoUnit.MILLIS);
	}
	protected BaseModel modifiedInit() {
		Wrap<ZonedDateTime> modifiedWrap = new Wrap<ZonedDateTime>().var("modified");
		if(modified == null) {
			_modified(modifiedWrap);
			Optional.ofNullable(modifiedWrap.getO()).ifPresent(o -> {
				setModified(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchModified(SiteRequest siteRequest_, ZonedDateTime o) {
		return o == null ? null : ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));
	}

	public static String staticSearchStrModified(SiteRequest siteRequest_, String o) {
		ZoneId zoneId = ZoneId.of("UTC");
		return BaseModel.staticSearchModified(siteRequest_, BaseModel.staticSetModified(siteRequest_, o, zoneId));
	}

	public static String staticSearchFqModified(SiteRequest siteRequest_, String o) {
		ZoneId zoneId = ZoneId.of("UTC");
		return BaseModel.staticSearchModified(siteRequest_, BaseModel.staticSetModified(siteRequest_, o, zoneId)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:archived">Find the entity archived in Solr</a>
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
		this.archived = BaseModel.staticSetArchived(siteRequest_, o);
	}
	public static Boolean staticSetArchived(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BaseModel archivedInit() {
		Wrap<Boolean> archivedWrap = new Wrap<Boolean>().var("archived");
		if(archived == null) {
			_archived(archivedWrap);
			Optional.ofNullable(archivedWrap.getO()).ifPresent(o -> {
				setArchived(o);
			});
		}
		return (BaseModel)this;
	}

	public static Boolean staticSearchArchived(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrArchived(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqArchived(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchArchived(siteRequest_, BaseModel.staticSetArchived(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:classCanonicalName">Find the entity classCanonicalName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _classCanonicalName(Wrap<String> w);

	public String getClassCanonicalName() {
		return classCanonicalName;
	}
	public void setClassCanonicalName(String o) {
		this.classCanonicalName = BaseModel.staticSetClassCanonicalName(siteRequest_, o);
	}
	public static String staticSetClassCanonicalName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel classCanonicalNameInit() {
		Wrap<String> classCanonicalNameWrap = new Wrap<String>().var("classCanonicalName");
		if(classCanonicalName == null) {
			_classCanonicalName(classCanonicalNameWrap);
			Optional.ofNullable(classCanonicalNameWrap.getO()).ifPresent(o -> {
				setClassCanonicalName(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchClassCanonicalName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassCanonicalName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassCanonicalName(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchClassCanonicalName(siteRequest_, BaseModel.staticSetClassCanonicalName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:classSimpleName">Find the entity classSimpleName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _classSimpleName(Wrap<String> w);

	public String getClassSimpleName() {
		return classSimpleName;
	}
	public void setClassSimpleName(String o) {
		this.classSimpleName = BaseModel.staticSetClassSimpleName(siteRequest_, o);
	}
	public static String staticSetClassSimpleName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel classSimpleNameInit() {
		Wrap<String> classSimpleNameWrap = new Wrap<String>().var("classSimpleName");
		if(classSimpleName == null) {
			_classSimpleName(classSimpleNameWrap);
			Optional.ofNullable(classSimpleNameWrap.getO()).ifPresent(o -> {
				setClassSimpleName(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchClassSimpleName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassSimpleName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassSimpleName(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchClassSimpleName(siteRequest_, BaseModel.staticSetClassSimpleName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:classCanonicalNames">Find the entity classCanonicalNames in Solr</a>
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
	@JsonIgnore
	public void setClassCanonicalNames(String o) {
		String l = BaseModel.staticSetClassCanonicalNames(siteRequest_, o);
		if(l != null)
			addClassCanonicalNames(l);
	}
	public static String staticSetClassCanonicalNames(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseModel addClassCanonicalNames(String...objects) {
		for(String o : objects) {
			addClassCanonicalNames(o);
		}
		return (BaseModel)this;
	}
	public BaseModel addClassCanonicalNames(String o) {
		if(o != null)
			this.classCanonicalNames.add(o);
		return (BaseModel)this;
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
	protected BaseModel classCanonicalNamesInit() {
		_classCanonicalNames(classCanonicalNames);
		return (BaseModel)this;
	}

	public static String staticSearchClassCanonicalNames(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassCanonicalNames(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassCanonicalNames(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchClassCanonicalNames(siteRequest_, BaseModel.staticSetClassCanonicalNames(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:sessionId">Find the entity sessionId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionId(Wrap<String> w);

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String o) {
		this.sessionId = BaseModel.staticSetSessionId(siteRequest_, o);
	}
	public static String staticSetSessionId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel sessionIdInit() {
		Wrap<String> sessionIdWrap = new Wrap<String>().var("sessionId");
		if(sessionId == null) {
			_sessionId(sessionIdWrap);
			Optional.ofNullable(sessionIdWrap.getO()).ifPresent(o -> {
				setSessionId(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchSessionId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSessionId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSessionId(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchSessionId(siteRequest_, BaseModel.staticSetSessionId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:userKey">Find the entity userKey in Solr</a>
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
		this.userKey = BaseModel.staticSetUserKey(siteRequest_, o);
	}
	public static Long staticSetUserKey(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected BaseModel userKeyInit() {
		Wrap<Long> userKeyWrap = new Wrap<Long>().var("userKey");
		if(userKey == null) {
			_userKey(userKeyWrap);
			Optional.ofNullable(userKeyWrap.getO()).ifPresent(o -> {
				setUserKey(o);
			});
		}
		return (BaseModel)this;
	}

	public static Long staticSearchUserKey(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrUserKey(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserKey(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchUserKey(siteRequest_, BaseModel.staticSetUserKey(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:saves">Find the entity saves in Solr</a>
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
	@JsonIgnore
	public void setSaves(String o) {
		String l = BaseModel.staticSetSaves(siteRequest_, o);
		if(l != null)
			addSaves(l);
	}
	public static String staticSetSaves(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseModel addSaves(String...objects) {
		for(String o : objects) {
			addSaves(o);
		}
		return (BaseModel)this;
	}
	public BaseModel addSaves(String o) {
		if(o != null)
			this.saves.add(o);
		return (BaseModel)this;
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
	protected BaseModel savesInit() {
		_saves(saves);
		return (BaseModel)this;
	}

	public static String staticSearchSaves(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSaves(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSaves(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchSaves(siteRequest_, BaseModel.staticSetSaves(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:objectTitle">Find the entity objectTitle in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _objectTitle(Wrap<String> w);

	public String getObjectTitle() {
		return objectTitle;
	}
	public void setObjectTitle(String o) {
		this.objectTitle = BaseModel.staticSetObjectTitle(siteRequest_, o);
	}
	public static String staticSetObjectTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel objectTitleInit() {
		Wrap<String> objectTitleWrap = new Wrap<String>().var("objectTitle");
		if(objectTitle == null) {
			_objectTitle(objectTitleWrap);
			Optional.ofNullable(objectTitleWrap.getO()).ifPresent(o -> {
				setObjectTitle(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchObjectTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrObjectTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqObjectTitle(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchObjectTitle(siteRequest_, BaseModel.staticSetObjectTitle(siteRequest_, o)).toString();
	}

	public String sqlObjectTitle() {
		return objectTitle;
	}

	/////////////////
	// displayPage //
	/////////////////


	/**	 The entity displayPage
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String displayPage;

	/**	<br> The entity displayPage
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:displayPage">Find the entity displayPage in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _displayPage(Wrap<String> w);

	public String getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(String o) {
		this.displayPage = BaseModel.staticSetDisplayPage(siteRequest_, o);
	}
	public static String staticSetDisplayPage(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel displayPageInit() {
		Wrap<String> displayPageWrap = new Wrap<String>().var("displayPage");
		if(displayPage == null) {
			_displayPage(displayPageWrap);
			Optional.ofNullable(displayPageWrap.getO()).ifPresent(o -> {
				setDisplayPage(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchDisplayPage(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDisplayPage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDisplayPage(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchDisplayPage(siteRequest_, BaseModel.staticSetDisplayPage(siteRequest_, o)).toString();
	}

	public String sqlDisplayPage() {
		return displayPage;
	}

	//////////////
	// editPage //
	//////////////


	/**	 The entity editPage
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String editPage;

	/**	<br> The entity editPage
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:editPage">Find the entity editPage in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _editPage(Wrap<String> w);

	public String getEditPage() {
		return editPage;
	}
	public void setEditPage(String o) {
		this.editPage = BaseModel.staticSetEditPage(siteRequest_, o);
	}
	public static String staticSetEditPage(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel editPageInit() {
		Wrap<String> editPageWrap = new Wrap<String>().var("editPage");
		if(editPage == null) {
			_editPage(editPageWrap);
			Optional.ofNullable(editPageWrap.getO()).ifPresent(o -> {
				setEditPage(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchEditPage(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEditPage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEditPage(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchEditPage(siteRequest_, BaseModel.staticSetEditPage(siteRequest_, o)).toString();
	}

	//////////////
	// userPage //
	//////////////


	/**	 The entity userPage
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userPage;

	/**	<br> The entity userPage
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:userPage">Find the entity userPage in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userPage(Wrap<String> w);

	public String getUserPage() {
		return userPage;
	}
	public void setUserPage(String o) {
		this.userPage = BaseModel.staticSetUserPage(siteRequest_, o);
	}
	public static String staticSetUserPage(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel userPageInit() {
		Wrap<String> userPageWrap = new Wrap<String>().var("userPage");
		if(userPage == null) {
			_userPage(userPageWrap);
			Optional.ofNullable(userPageWrap.getO()).ifPresent(o -> {
				setUserPage(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchUserPage(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserPage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserPage(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchUserPage(siteRequest_, BaseModel.staticSetUserPage(siteRequest_, o)).toString();
	}

	//////////////
	// download //
	//////////////


	/**	 The entity download
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String download;

	/**	<br> The entity download
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:download">Find the entity download in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _download(Wrap<String> w);

	public String getDownload() {
		return download;
	}
	public void setDownload(String o) {
		this.download = BaseModel.staticSetDownload(siteRequest_, o);
	}
	public static String staticSetDownload(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel downloadInit() {
		Wrap<String> downloadWrap = new Wrap<String>().var("download");
		if(download == null) {
			_download(downloadWrap);
			Optional.ofNullable(downloadWrap.getO()).ifPresent(o -> {
				setDownload(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchDownload(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDownload(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDownload(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchDownload(siteRequest_, BaseModel.staticSetDownload(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:objectSuggest">Find the entity objectSuggest in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _objectSuggest(Wrap<String> w);

	public String getObjectSuggest() {
		return objectSuggest;
	}
	public void setObjectSuggest(String o) {
		this.objectSuggest = BaseModel.staticSetObjectSuggest(siteRequest_, o);
	}
	public static String staticSetObjectSuggest(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel objectSuggestInit() {
		Wrap<String> objectSuggestWrap = new Wrap<String>().var("objectSuggest");
		if(objectSuggest == null) {
			_objectSuggest(objectSuggestWrap);
			Optional.ofNullable(objectSuggestWrap.getO()).ifPresent(o -> {
				setObjectSuggest(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchObjectSuggest(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrObjectSuggest(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqObjectSuggest(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchObjectSuggest(siteRequest_, BaseModel.staticSetObjectSuggest(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:objectText">Find the entity objectText in Solr</a>
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
	@JsonIgnore
	public void setObjectText(String o) {
		String l = BaseModel.staticSetObjectText(siteRequest_, o);
		if(l != null)
			addObjectText(l);
	}
	public static String staticSetObjectText(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseModel addObjectText(String...objects) {
		for(String o : objects) {
			addObjectText(o);
		}
		return (BaseModel)this;
	}
	public BaseModel addObjectText(String o) {
		if(o != null)
			this.objectText.add(o);
		return (BaseModel)this;
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
	protected BaseModel objectTextInit() {
		_objectText(objectText);
		return (BaseModel)this;
	}

	public static String staticSearchObjectText(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrObjectText(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqObjectText(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchObjectText(siteRequest_, BaseModel.staticSetObjectText(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.BaseModel&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = BaseModel.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModel solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (BaseModel)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return BaseModel.staticSearchSolrId(siteRequest_, BaseModel.staticSetSolrId(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BaseModelGen<DEV>> promiseDeepBaseModel(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBaseModel();
	}

	public Future<BaseModelGen<DEV>> promiseDeepBaseModel() {
		Promise<BaseModelGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBaseModel(promise2);
		promise2.future().onSuccess(a -> {
			promise.complete(this);
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseBaseModel(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				siteRequest_Init();
				pkInit();
				createdInit();
				modifiedInit();
				archivedInit();
				classCanonicalNameInit();
				classSimpleNameInit();
				classCanonicalNamesInit();
				sessionIdInit();
				userKeyInit();
				savesInit();
				objectTitleInit();
				displayPageInit();
				editPageInit();
				userPageInit();
				downloadInit();
				objectSuggestInit();
				objectTextInit();
				solrIdInit();
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

	public Future<? extends BaseModelGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBaseModel(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBaseModel(SiteRequest siteRequest_) {
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBaseModel(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBaseModel(v);
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
	public Object obtainBaseModel(String var) {
		BaseModel oBaseModel = (BaseModel)this;
		switch(var) {
			case "siteRequest_":
				return oBaseModel.siteRequest_;
			case "pk":
				return oBaseModel.pk;
			case "created":
				return oBaseModel.created;
			case "modified":
				return oBaseModel.modified;
			case "archived":
				return oBaseModel.archived;
			case "classCanonicalName":
				return oBaseModel.classCanonicalName;
			case "classSimpleName":
				return oBaseModel.classSimpleName;
			case "classCanonicalNames":
				return oBaseModel.classCanonicalNames;
			case "sessionId":
				return oBaseModel.sessionId;
			case "userKey":
				return oBaseModel.userKey;
			case "saves":
				return oBaseModel.saves;
			case "objectTitle":
				return oBaseModel.objectTitle;
			case "displayPage":
				return oBaseModel.displayPage;
			case "editPage":
				return oBaseModel.editPage;
			case "userPage":
				return oBaseModel.userPage;
			case "download":
				return oBaseModel.download;
			case "objectSuggest":
				return oBaseModel.objectSuggest;
			case "objectText":
				return oBaseModel.objectText;
			case "solrId":
				return oBaseModel.solrId;
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
				o = relateBaseModel(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBaseModel(String var, Object val) {
		BaseModel oBaseModel = (BaseModel)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, BaseModel o) {
		return staticSetBaseModel(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetBaseModel(String entityVar, SiteRequest siteRequest_, String v, BaseModel o) {
		switch(entityVar) {
		case "pk":
			return BaseModel.staticSetPk(siteRequest_, v);
		case "created":
		case "modified":
		case "archived":
			return BaseModel.staticSetArchived(siteRequest_, v);
		case "classCanonicalName":
			return BaseModel.staticSetClassCanonicalName(siteRequest_, v);
		case "classSimpleName":
			return BaseModel.staticSetClassSimpleName(siteRequest_, v);
		case "classCanonicalNames":
			return BaseModel.staticSetClassCanonicalNames(siteRequest_, v);
		case "sessionId":
			return BaseModel.staticSetSessionId(siteRequest_, v);
		case "userKey":
			return BaseModel.staticSetUserKey(siteRequest_, v);
		case "saves":
			return BaseModel.staticSetSaves(siteRequest_, v);
		case "objectTitle":
			return BaseModel.staticSetObjectTitle(siteRequest_, v);
		case "displayPage":
			return BaseModel.staticSetDisplayPage(siteRequest_, v);
		case "editPage":
			return BaseModel.staticSetEditPage(siteRequest_, v);
		case "userPage":
			return BaseModel.staticSetUserPage(siteRequest_, v);
		case "download":
			return BaseModel.staticSetDownload(siteRequest_, v);
		case "objectSuggest":
			return BaseModel.staticSetObjectSuggest(siteRequest_, v);
		case "objectText":
			return BaseModel.staticSetObjectText(siteRequest_, v);
		case "solrId":
			return BaseModel.staticSetSolrId(siteRequest_, v);
			default:
				return null;
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBaseModel(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBaseModel(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "pk":
			return BaseModel.staticSearchPk(siteRequest_, (Long)o);
		case "created":
			return BaseModel.staticSearchCreated(siteRequest_, (ZonedDateTime)o);
		case "modified":
			return BaseModel.staticSearchModified(siteRequest_, (ZonedDateTime)o);
		case "archived":
			return BaseModel.staticSearchArchived(siteRequest_, (Boolean)o);
		case "classCanonicalName":
			return BaseModel.staticSearchClassCanonicalName(siteRequest_, (String)o);
		case "classSimpleName":
			return BaseModel.staticSearchClassSimpleName(siteRequest_, (String)o);
		case "classCanonicalNames":
			return BaseModel.staticSearchClassCanonicalNames(siteRequest_, (String)o);
		case "sessionId":
			return BaseModel.staticSearchSessionId(siteRequest_, (String)o);
		case "userKey":
			return BaseModel.staticSearchUserKey(siteRequest_, (Long)o);
		case "saves":
			return BaseModel.staticSearchSaves(siteRequest_, (String)o);
		case "objectTitle":
			return BaseModel.staticSearchObjectTitle(siteRequest_, (String)o);
		case "displayPage":
			return BaseModel.staticSearchDisplayPage(siteRequest_, (String)o);
		case "editPage":
			return BaseModel.staticSearchEditPage(siteRequest_, (String)o);
		case "userPage":
			return BaseModel.staticSearchUserPage(siteRequest_, (String)o);
		case "download":
			return BaseModel.staticSearchDownload(siteRequest_, (String)o);
		case "objectSuggest":
			return BaseModel.staticSearchObjectSuggest(siteRequest_, (String)o);
		case "objectText":
			return BaseModel.staticSearchObjectText(siteRequest_, (String)o);
		case "solrId":
			return BaseModel.staticSearchSolrId(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBaseModel(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBaseModel(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "pk":
			return BaseModel.staticSearchStrPk(siteRequest_, (Long)o);
		case "created":
			return BaseModel.staticSearchStrCreated(siteRequest_, (String)o);
		case "modified":
			return BaseModel.staticSearchStrModified(siteRequest_, (String)o);
		case "archived":
			return BaseModel.staticSearchStrArchived(siteRequest_, (Boolean)o);
		case "classCanonicalName":
			return BaseModel.staticSearchStrClassCanonicalName(siteRequest_, (String)o);
		case "classSimpleName":
			return BaseModel.staticSearchStrClassSimpleName(siteRequest_, (String)o);
		case "classCanonicalNames":
			return BaseModel.staticSearchStrClassCanonicalNames(siteRequest_, (String)o);
		case "sessionId":
			return BaseModel.staticSearchStrSessionId(siteRequest_, (String)o);
		case "userKey":
			return BaseModel.staticSearchStrUserKey(siteRequest_, (Long)o);
		case "saves":
			return BaseModel.staticSearchStrSaves(siteRequest_, (String)o);
		case "objectTitle":
			return BaseModel.staticSearchStrObjectTitle(siteRequest_, (String)o);
		case "displayPage":
			return BaseModel.staticSearchStrDisplayPage(siteRequest_, (String)o);
		case "editPage":
			return BaseModel.staticSearchStrEditPage(siteRequest_, (String)o);
		case "userPage":
			return BaseModel.staticSearchStrUserPage(siteRequest_, (String)o);
		case "download":
			return BaseModel.staticSearchStrDownload(siteRequest_, (String)o);
		case "objectSuggest":
			return BaseModel.staticSearchStrObjectSuggest(siteRequest_, (String)o);
		case "objectText":
			return BaseModel.staticSearchStrObjectText(siteRequest_, (String)o);
		case "solrId":
			return BaseModel.staticSearchStrSolrId(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBaseModel(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBaseModel(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "pk":
			return BaseModel.staticSearchFqPk(siteRequest_, o);
		case "created":
			return BaseModel.staticSearchFqCreated(siteRequest_, o);
		case "modified":
			return BaseModel.staticSearchFqModified(siteRequest_, o);
		case "archived":
			return BaseModel.staticSearchFqArchived(siteRequest_, o);
		case "classCanonicalName":
			return BaseModel.staticSearchFqClassCanonicalName(siteRequest_, o);
		case "classSimpleName":
			return BaseModel.staticSearchFqClassSimpleName(siteRequest_, o);
		case "classCanonicalNames":
			return BaseModel.staticSearchFqClassCanonicalNames(siteRequest_, o);
		case "sessionId":
			return BaseModel.staticSearchFqSessionId(siteRequest_, o);
		case "userKey":
			return BaseModel.staticSearchFqUserKey(siteRequest_, o);
		case "saves":
			return BaseModel.staticSearchFqSaves(siteRequest_, o);
		case "objectTitle":
			return BaseModel.staticSearchFqObjectTitle(siteRequest_, o);
		case "displayPage":
			return BaseModel.staticSearchFqDisplayPage(siteRequest_, o);
		case "editPage":
			return BaseModel.staticSearchFqEditPage(siteRequest_, o);
		case "userPage":
			return BaseModel.staticSearchFqUserPage(siteRequest_, o);
		case "download":
			return BaseModel.staticSearchFqDownload(siteRequest_, o);
		case "objectSuggest":
			return BaseModel.staticSearchFqObjectSuggest(siteRequest_, o);
		case "objectText":
			return BaseModel.staticSearchFqObjectText(siteRequest_, o);
		case "solrId":
			return BaseModel.staticSearchFqSolrId(siteRequest_, o);
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
					o = persistBaseModel(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistBaseModel(String var, Object val) {
		String varLower = var.toLowerCase();
			if("pk".equals(varLower)) {
				if(val instanceof Long) {
					setPk((Long)val);
				} else {
					setPk(val == null ? null : val.toString());
				}
				saves.add("pk");
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
			} else if("objecttitle".equals(varLower)) {
				if(val instanceof String) {
					setObjectTitle((String)val);
				}
				saves.add("objectTitle");
				return val;
			} else if("displaypage".equals(varLower)) {
				if(val instanceof String) {
					setDisplayPage((String)val);
				}
				saves.add("displayPage");
				return val;
		} else {
			return null;
		}
	}

	/////////////
	// populate //
	/////////////

	public void populateForClass(SolrResponse.Doc doc) {
		populateBaseModel(doc);
	}
	public void populateBaseModel(SolrResponse.Doc doc) {
		BaseModel oBaseModel = (BaseModel)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			Long pk = (Long)doc.get("pk_docvalues_long");
			oBaseModel.setPk(pk);

			if(saves.contains("created")) {
				String created = (String)doc.get("created_docvalues_date");
				if(created != null)
					oBaseModel.setCreated(created);
			}

			if(saves.contains("modified")) {
				String modified = (String)doc.get("modified_docvalues_date");
				if(modified != null)
					oBaseModel.setModified(modified);
			}

			if(saves.contains("archived")) {
				Boolean archived = (Boolean)doc.get("archived_docvalues_boolean");
				if(archived != null)
					oBaseModel.setArchived(archived);
			}

			if(saves.contains("classCanonicalName")) {
				String classCanonicalName = (String)doc.get("classCanonicalName_docvalues_string");
				if(classCanonicalName != null)
					oBaseModel.setClassCanonicalName(classCanonicalName);
			}

			if(saves.contains("classSimpleName")) {
				String classSimpleName = (String)doc.get("classSimpleName_docvalues_string");
				if(classSimpleName != null)
					oBaseModel.setClassSimpleName(classSimpleName);
			}

			if(saves.contains("classCanonicalNames")) {
				List<String> classCanonicalNames = (List<String>)doc.get("classCanonicalNames_docvalues_strings");
				if(classCanonicalNames != null) {
					classCanonicalNames.stream().forEach( v -> {
						oBaseModel.classCanonicalNames.add(BaseModel.staticSetClassCanonicalNames(siteRequest_, v));
					});
				}
			}

			if(saves.contains("sessionId")) {
				String sessionId = (String)doc.get("sessionId_docvalues_string");
				if(sessionId != null)
					oBaseModel.setSessionId(sessionId);
			}

			if(saves.contains("userKey")) {
				Long userKey = (Long)doc.get("userKey_docvalues_long");
				if(userKey != null)
					oBaseModel.setUserKey(userKey);
			}

			if(saves.contains("saves")) {
				List<String> saves = (List<String>)doc.get("saves_docvalues_strings");
				if(saves != null) {
					saves.stream().forEach( v -> {
						oBaseModel.saves.add(BaseModel.staticSetSaves(siteRequest_, v));
					});
				}
			}

			if(saves.contains("objectTitle")) {
				String objectTitle = (String)doc.get("objectTitle_docvalues_string");
				if(objectTitle != null)
					oBaseModel.setObjectTitle(objectTitle);
			}

			if(saves.contains("displayPage")) {
				String displayPage = (String)doc.get("displayPage_docvalues_string");
				if(displayPage != null)
					oBaseModel.setDisplayPage(displayPage);
			}

			if(saves.contains("editPage")) {
				String editPage = (String)doc.get("editPage_docvalues_string");
				if(editPage != null)
					oBaseModel.setEditPage(editPage);
			}

			if(saves.contains("userPage")) {
				String userPage = (String)doc.get("userPage_docvalues_string");
				if(userPage != null)
					oBaseModel.setUserPage(userPage);
			}

			if(saves.contains("download")) {
				String download = (String)doc.get("download_docvalues_string");
				if(download != null)
					oBaseModel.setDownload(download);
			}

			if(saves.contains("objectSuggest")) {
				String objectSuggest = (String)doc.get("objectSuggest_suggested");
				oBaseModel.setObjectSuggest(objectSuggest);
			}

			String solrId = (String)doc.get("solrId");
			oBaseModel.setSolrId(solrId);
		}
	}

	public void indexBaseModel(JsonObject doc) {
		if(pk != null) {
			doc.put("pk_docvalues_long", pk);
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
				l.add(BaseModel.staticSearchClassCanonicalNames(siteRequest_, o));
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
				l.add(BaseModel.staticSearchSaves(siteRequest_, o));
			}
		}
		if(objectTitle != null) {
			doc.put("objectTitle_docvalues_string", objectTitle);
		}
		if(displayPage != null) {
			doc.put("displayPage_docvalues_string", displayPage);
		}
		if(editPage != null) {
			doc.put("editPage_docvalues_string", editPage);
		}
		if(userPage != null) {
			doc.put("userPage_docvalues_string", userPage);
		}
		if(download != null) {
			doc.put("download_docvalues_string", download);
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
		if(solrId != null) {
			doc.put("solrId", solrId);
		}
	}

	public static String varStoredBaseModel(String entityVar) {
		switch(entityVar) {
			case "pk":
				return "pk_docvalues_long";
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
			case "objectTitle":
				return "objectTitle_docvalues_string";
			case "displayPage":
				return "displayPage_docvalues_string";
			case "editPage":
				return "editPage_docvalues_string";
			case "userPage":
				return "userPage_docvalues_string";
			case "download":
				return "download_docvalues_string";
			default:
				return null;
		}
	}

	public static String varIndexedBaseModel(String entityVar) {
		switch(entityVar) {
			case "pk":
				return "pk_docvalues_long";
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
			case "objectTitle":
				return "objectTitle_docvalues_string";
			case "displayPage":
				return "displayPage_docvalues_string";
			case "editPage":
				return "editPage_docvalues_string";
			case "userPage":
				return "userPage_docvalues_string";
			case "download":
				return "download_docvalues_string";
			case "objectSuggest":
				return "objectSuggest_suggested";
			case "objectText":
				return "objectText_text_enUS";
			case "solrId":
				return "solrId";
			default:
				return null;
		}
	}

	public static String searchVarBaseModel(String searchVar) {
		switch(searchVar) {
			case "pk_docvalues_long":
				return "pk";
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
			case "objectTitle_docvalues_string":
				return "objectTitle";
			case "displayPage_docvalues_string":
				return "displayPage";
			case "editPage_docvalues_string":
				return "editPage";
			case "userPage_docvalues_string":
				return "userPage";
			case "download_docvalues_string":
				return "download";
			case "objectSuggest_suggested":
				return "objectSuggest";
			case "objectText_text_enUS":
				return "objectText";
			case "solrId":
				return "solrId";
			default:
				return null;
		}
	}

	public static String varSearchBaseModel(String entityVar) {
		switch(entityVar) {
			case "objectText":
				return "objectText_text_enUS";
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				return null;
		}
	}

	public static String varSuggestedBaseModel(String entityVar) {
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
		storeBaseModel(doc);
	}
	public void storeBaseModel(SolrResponse.Doc doc) {
		BaseModel oBaseModel = (BaseModel)this;
		SiteRequest siteRequest = oBaseModel.getSiteRequest_();

		oBaseModel.setPk(Optional.ofNullable(doc.get("pk_docvalues_long")).map(v -> v.toString()).orElse(null));
		oBaseModel.setCreated(Optional.ofNullable(doc.get("created_docvalues_date")).map(v -> v.toString()).orElse(null));
		oBaseModel.setModified(Optional.ofNullable(doc.get("modified_docvalues_date")).map(v -> v.toString()).orElse(null));
		oBaseModel.setArchived(Optional.ofNullable(doc.get("archived_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBaseModel.setClassCanonicalName(Optional.ofNullable(doc.get("classCanonicalName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseModel.setClassSimpleName(Optional.ofNullable(doc.get("classSimpleName_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("classCanonicalNames_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseModel.addClassCanonicalNames(BaseModel.staticSetClassCanonicalNames(siteRequest, v.toString()));
		});
		oBaseModel.setSessionId(Optional.ofNullable(doc.get("sessionId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseModel.setUserKey(Optional.ofNullable(doc.get("userKey_docvalues_long")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("saves_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseModel.addSaves(BaseModel.staticSetSaves(siteRequest, v.toString()));
		});
		oBaseModel.setObjectTitle(Optional.ofNullable(doc.get("objectTitle_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseModel.setDisplayPage(Optional.ofNullable(doc.get("displayPage_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseModel.setEditPage(Optional.ofNullable(doc.get("editPage_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseModel.setUserPage(Optional.ofNullable(doc.get("userPage_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseModel.setDownload(Optional.ofNullable(doc.get("download_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseModel.setObjectSuggest(Optional.ofNullable(doc.get("objectSuggest_suggested")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("objectText_text_enUS")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseModel.addObjectText(BaseModel.staticSetObjectText(siteRequest, v.toString()));
		});
		String solrId = (String)doc.get("solrId");
		oBaseModel.setSolrId(solrId);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBaseModel() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BaseModel) {
			BaseModel original = (BaseModel)o;
			if(!Objects.equals(pk, original.getPk()))
				apiRequest.addVars("pk");
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
			if(!Objects.equals(objectTitle, original.getObjectTitle()))
				apiRequest.addVars("objectTitle");
			if(!Objects.equals(displayPage, original.getDisplayPage()))
				apiRequest.addVars("displayPage");
			if(!Objects.equals(editPage, original.getEditPage()))
				apiRequest.addVars("editPage");
			if(!Objects.equals(userPage, original.getUserPage()))
				apiRequest.addVars("userPage");
			if(!Objects.equals(download, original.getDownload()))
				apiRequest.addVars("download");
			if(!Objects.equals(objectSuggest, original.getObjectSuggest()))
				apiRequest.addVars("objectSuggest");
			if(!Objects.equals(objectText, original.getObjectText()))
				apiRequest.addVars("objectText");
			if(!Objects.equals(solrId, original.getSolrId()))
				apiRequest.addVars("solrId");
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Optional.ofNullable(pk).map(v -> "pk: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(created).map(v -> "created: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(modified).map(v -> "modified: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(archived).map(v -> "archived: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(classCanonicalName).map(v -> "classCanonicalName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(classSimpleName).map(v -> "classSimpleName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(classCanonicalNames).map(v -> "classCanonicalNames: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(sessionId).map(v -> "sessionId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userKey).map(v -> "userKey: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(saves).map(v -> "saves: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(objectTitle).map(v -> "objectTitle: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(displayPage).map(v -> "displayPage: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(editPage).map(v -> "editPage: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userPage).map(v -> "userPage: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(download).map(v -> "download: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(objectSuggest).map(v -> "objectSuggest: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(objectText).map(v -> "objectText: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(solrId).map(v -> "solrId: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "BaseModel";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.BaseModel";
	public static final String CLASS_AUTH_RESOURCE = "";
	public static final String VAR_siteRequest_ = "siteRequest_";
	public static final String VAR_pk = "pk";
	public static final String VAR_created = "created";
	public static final String VAR_modified = "modified";
	public static final String VAR_archived = "archived";
	public static final String VAR_classCanonicalName = "classCanonicalName";
	public static final String VAR_classSimpleName = "classSimpleName";
	public static final String VAR_classCanonicalNames = "classCanonicalNames";
	public static final String VAR_sessionId = "sessionId";
	public static final String VAR_userKey = "userKey";
	public static final String VAR_saves = "saves";
	public static final String VAR_objectTitle = "objectTitle";
	public static final String VAR_displayPage = "displayPage";
	public static final String VAR_editPage = "editPage";
	public static final String VAR_userPage = "userPage";
	public static final String VAR_download = "download";
	public static final String VAR_objectSuggest = "objectSuggest";
	public static final String VAR_objectText = "objectText";
	public static final String VAR_solrId = "solrId";

	public static List<String> varsQForClass() {
		return BaseModel.varsQBaseModel(new ArrayList<String>());
	}
	public static List<String> varsQBaseModel(List<String> vars) {
		vars.add(VAR_objectSuggest);
		vars.add(VAR_objectText);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return BaseModel.varsFqBaseModel(new ArrayList<String>());
	}
	public static List<String> varsFqBaseModel(List<String> vars) {
		vars.add(VAR_pk);
		vars.add(VAR_created);
		vars.add(VAR_modified);
		vars.add(VAR_archived);
		vars.add(VAR_classSimpleName);
		vars.add(VAR_displayPage);
		vars.add(VAR_editPage);
		vars.add(VAR_userPage);
		vars.add(VAR_download);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return BaseModel.varsRangeBaseModel(new ArrayList<String>());
	}
	public static List<String> varsRangeBaseModel(List<String> vars) {
		vars.add(VAR_pk);
		vars.add(VAR_created);
		vars.add(VAR_modified);
		return vars;
	}

	public static final String DISPLAY_NAME_siteRequest_ = "";
	public static final String DISPLAY_NAME_pk = "primary key";
	public static final String DISPLAY_NAME_created = "created";
	public static final String DISPLAY_NAME_modified = "modified";
	public static final String DISPLAY_NAME_archived = "archive";
	public static final String DISPLAY_NAME_classCanonicalName = "";
	public static final String DISPLAY_NAME_classSimpleName = "object type";
	public static final String DISPLAY_NAME_classCanonicalNames = "";
	public static final String DISPLAY_NAME_sessionId = "";
	public static final String DISPLAY_NAME_userKey = "";
	public static final String DISPLAY_NAME_saves = "";
	public static final String DISPLAY_NAME_objectTitle = "title";
	public static final String DISPLAY_NAME_displayPage = "display page";
	public static final String DISPLAY_NAME_editPage = "manage";
	public static final String DISPLAY_NAME_userPage = "user";
	public static final String DISPLAY_NAME_download = "download";
	public static final String DISPLAY_NAME_objectSuggest = "autosuggest";
	public static final String DISPLAY_NAME_objectText = "text";
	public static final String DISPLAY_NAME_solrId = "";

	public String idForClass() {
		return null;
	}

	public String titleForClass() {
		return objectTitle;
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

	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return BaseModel.displayNameBaseModel(var);
	}
	public static String displayNameBaseModel(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return DISPLAY_NAME_siteRequest_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
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
		case VAR_objectTitle:
			return DISPLAY_NAME_objectTitle;
		case VAR_displayPage:
			return DISPLAY_NAME_displayPage;
		case VAR_editPage:
			return DISPLAY_NAME_editPage;
		case VAR_userPage:
			return DISPLAY_NAME_userPage;
		case VAR_download:
			return DISPLAY_NAME_download;
		case VAR_objectSuggest:
			return DISPLAY_NAME_objectSuggest;
		case VAR_objectText:
			return DISPLAY_NAME_objectText;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		default:
			return null;
		}
	}

	public static String descriptionBaseModel(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_siteRequest_:
			return "The current request object";
		case VAR_pk:
			return "The primary key of this object in the database";
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
		case VAR_objectTitle:
			return "The title of this page. ";
		case VAR_displayPage:
			return "Visit this landing page. ";
		case VAR_editPage:
			return "Manage this";
		case VAR_userPage:
			return "User page";
		case VAR_download:
			return "the download URL";
		case VAR_objectSuggest:
			return "The indexed field in the search engine for this record while using autosuggest";
		case VAR_objectText:
			return "The full text search field in the search engine for this record while using autosuggest";
		case VAR_solrId:
			return "The unique key for this record in the search engine";
			default:
				return null;
		}
	}

	public static String classSimpleNameBaseModel(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return "SiteRequest";
		case VAR_pk:
			return "Long";
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
		case VAR_objectTitle:
			return "String";
		case VAR_displayPage:
			return "String";
		case VAR_editPage:
			return "String";
		case VAR_userPage:
			return "String";
		case VAR_download:
			return "String";
		case VAR_objectSuggest:
			return "String";
		case VAR_objectText:
			return "List";
		case VAR_solrId:
			return "String";
			default:
				return null;
		}
	}

	public static Integer htmColumnBaseModel(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer htmRowBaseModel(String var) {
		switch(var) {
		case VAR_pk:
			return 1;
		case VAR_created:
			return 1;
		case VAR_modified:
			return 1;
		case VAR_archived:
			return 2;
			default:
				return null;
		}
	}

	public static Integer htmCellBaseModel(String var) {
		switch(var) {
		case VAR_pk:
			return 1;
		case VAR_created:
			return 3;
		case VAR_modified:
			return 4;
		case VAR_archived:
			return 1;
			default:
				return null;
		}
	}

	public static Integer lengthMinBaseModel(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer lengthMaxBaseModel(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer maxBaseModel(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer minBaseModel(String var) {
		switch(var) {
			default:
				return null;
		}
	}
}
