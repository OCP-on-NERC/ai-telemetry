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
import java.lang.String;
import java.lang.Boolean;
import io.vertx.core.json.JsonArray;
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
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult">Find the class BaseResult in Solr. </a></p>
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
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
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
	@JsonIgnore
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
	@JsonIgnore
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
	@JsonIgnore
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
	@JsonIgnore
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

	///////////
	// title //
	///////////


	/**	 The entity title
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String title;

	/**	<br> The entity title
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:title">Find the entity title in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _title(Wrap<String> w);

	public String getTitle() {
		return title;
	}
	@JsonIgnore
	public void setTitle(String o) {
		this.title = BaseResult.staticSetTitle(siteRequest_, o);
	}
	public static String staticSetTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult titleInit() {
		Wrap<String> titleWrap = new Wrap<String>().var("title");
		if(title == null) {
			_title(titleWrap);
			Optional.ofNullable(titleWrap.getO()).ifPresent(o -> {
				setTitle(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTitle(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchTitle(siteRequest_, BaseResult.staticSetTitle(siteRequest_, o)).toString();
	}

	public String sqlTitle() {
		return title;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:displayPage">Find the entity displayPage in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _displayPage(Wrap<String> w);

	public String getDisplayPage() {
		return displayPage;
	}
	@JsonIgnore
	public void setDisplayPage(String o) {
		this.displayPage = BaseResult.staticSetDisplayPage(siteRequest_, o);
	}
	public static String staticSetDisplayPage(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult displayPageInit() {
		Wrap<String> displayPageWrap = new Wrap<String>().var("displayPage");
		if(displayPage == null) {
			_displayPage(displayPageWrap);
			Optional.ofNullable(displayPageWrap.getO()).ifPresent(o -> {
				setDisplayPage(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchDisplayPage(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDisplayPage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDisplayPage(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchDisplayPage(siteRequest_, BaseResult.staticSetDisplayPage(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:editPage">Find the entity editPage in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _editPage(Wrap<String> w);

	public String getEditPage() {
		return editPage;
	}
	@JsonIgnore
	public void setEditPage(String o) {
		this.editPage = BaseResult.staticSetEditPage(siteRequest_, o);
	}
	public static String staticSetEditPage(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult editPageInit() {
		Wrap<String> editPageWrap = new Wrap<String>().var("editPage");
		if(editPage == null) {
			_editPage(editPageWrap);
			Optional.ofNullable(editPageWrap.getO()).ifPresent(o -> {
				setEditPage(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchEditPage(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEditPage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEditPage(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchEditPage(siteRequest_, BaseResult.staticSetEditPage(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:userPage">Find the entity userPage in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userPage(Wrap<String> w);

	public String getUserPage() {
		return userPage;
	}
	@JsonIgnore
	public void setUserPage(String o) {
		this.userPage = BaseResult.staticSetUserPage(siteRequest_, o);
	}
	public static String staticSetUserPage(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult userPageInit() {
		Wrap<String> userPageWrap = new Wrap<String>().var("userPage");
		if(userPage == null) {
			_userPage(userPageWrap);
			Optional.ofNullable(userPageWrap.getO()).ifPresent(o -> {
				setUserPage(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchUserPage(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserPage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserPage(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchUserPage(siteRequest_, BaseResult.staticSetUserPage(siteRequest_, o)).toString();
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
	@JsonIgnore
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
	@JsonIgnore
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.result.BaseResult&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	@JsonIgnore
	public void setSolrId(String o) {
		this.solrId = BaseResult.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseResult solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (BaseResult)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return BaseResult.staticSearchSolrId(siteRequest_, BaseResult.staticSetSolrId(siteRequest_, o)).toString();
	}

	public String sqlSolrId() {
		return solrId;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BaseResultGen<DEV>> promiseDeepBaseResult(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBaseResult();
	}

	public Future<BaseResultGen<DEV>> promiseDeepBaseResult() {
		Promise<BaseResultGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBaseResult(promise2);
		promise2.future().onSuccess(a -> {
			promise.complete(this);
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
				createdInit();
				modifiedInit();
				archivedInit();
				classCanonicalNameInit();
				classSimpleNameInit();
				classCanonicalNamesInit();
				savesInit();
				titleInit();
				displayPageInit();
				editPageInit();
				userPageInit();
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

	public Future<? extends BaseResultGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
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
			case "saves":
				return oBaseResult.saves;
			case "title":
				return oBaseResult.title;
			case "displayPage":
				return oBaseResult.displayPage;
			case "editPage":
				return oBaseResult.editPage;
			case "userPage":
				return oBaseResult.userPage;
			case "objectSuggest":
				return oBaseResult.objectSuggest;
			case "objectText":
				return oBaseResult.objectText;
			case "solrId":
				return oBaseResult.solrId;
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
		case "saves":
			return BaseResult.staticSetSaves(siteRequest_, o);
		case "title":
			return BaseResult.staticSetTitle(siteRequest_, o);
		case "displayPage":
			return BaseResult.staticSetDisplayPage(siteRequest_, o);
		case "editPage":
			return BaseResult.staticSetEditPage(siteRequest_, o);
		case "userPage":
			return BaseResult.staticSetUserPage(siteRequest_, o);
		case "objectSuggest":
			return BaseResult.staticSetObjectSuggest(siteRequest_, o);
		case "objectText":
			return BaseResult.staticSetObjectText(siteRequest_, o);
		case "solrId":
			return BaseResult.staticSetSolrId(siteRequest_, o);
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
		case "saves":
			return BaseResult.staticSearchSaves(siteRequest_, (String)o);
		case "title":
			return BaseResult.staticSearchTitle(siteRequest_, (String)o);
		case "displayPage":
			return BaseResult.staticSearchDisplayPage(siteRequest_, (String)o);
		case "editPage":
			return BaseResult.staticSearchEditPage(siteRequest_, (String)o);
		case "userPage":
			return BaseResult.staticSearchUserPage(siteRequest_, (String)o);
		case "objectSuggest":
			return BaseResult.staticSearchObjectSuggest(siteRequest_, (String)o);
		case "objectText":
			return BaseResult.staticSearchObjectText(siteRequest_, (String)o);
		case "solrId":
			return BaseResult.staticSearchSolrId(siteRequest_, (String)o);
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
		case "saves":
			return BaseResult.staticSearchStrSaves(siteRequest_, (String)o);
		case "title":
			return BaseResult.staticSearchStrTitle(siteRequest_, (String)o);
		case "displayPage":
			return BaseResult.staticSearchStrDisplayPage(siteRequest_, (String)o);
		case "editPage":
			return BaseResult.staticSearchStrEditPage(siteRequest_, (String)o);
		case "userPage":
			return BaseResult.staticSearchStrUserPage(siteRequest_, (String)o);
		case "objectSuggest":
			return BaseResult.staticSearchStrObjectSuggest(siteRequest_, (String)o);
		case "objectText":
			return BaseResult.staticSearchStrObjectText(siteRequest_, (String)o);
		case "solrId":
			return BaseResult.staticSearchStrSolrId(siteRequest_, (String)o);
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
		case "saves":
			return BaseResult.staticSearchFqSaves(siteRequest_, o);
		case "title":
			return BaseResult.staticSearchFqTitle(siteRequest_, o);
		case "displayPage":
			return BaseResult.staticSearchFqDisplayPage(siteRequest_, o);
		case "editPage":
			return BaseResult.staticSearchFqEditPage(siteRequest_, o);
		case "userPage":
			return BaseResult.staticSearchFqUserPage(siteRequest_, o);
		case "objectSuggest":
			return BaseResult.staticSearchFqObjectSuggest(siteRequest_, o);
		case "objectText":
			return BaseResult.staticSearchFqObjectText(siteRequest_, o);
		case "solrId":
			return BaseResult.staticSearchFqSolrId(siteRequest_, o);
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
			if("created".equals(varLower)) {
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
			} else if("title".equals(varLower)) {
				if(val instanceof String) {
					setTitle((String)val);
				}
				saves.add("title");
				return val;
			} else if("displaypage".equals(varLower)) {
				if(val instanceof String) {
					setDisplayPage((String)val);
				}
				saves.add("displayPage");
				return val;
			} else if("solrid".equals(varLower)) {
				if(val instanceof String) {
					setSolrId((String)val);
				}
				saves.add("solrId");
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

			if(saves.contains("saves")) {
				List<String> saves = (List<String>)doc.get("saves_docvalues_strings");
				if(saves != null)
					oBaseResult.saves.addAll(saves);
			}

			if(saves.contains("title")) {
				String title = (String)doc.get("title_docvalues_string");
				if(title != null)
					oBaseResult.setTitle(title);
			}

			if(saves.contains("displayPage")) {
				String displayPage = (String)doc.get("displayPage_docvalues_string");
				if(displayPage != null)
					oBaseResult.setDisplayPage(displayPage);
			}

			if(saves.contains("editPage")) {
				String editPage = (String)doc.get("editPage_docvalues_string");
				if(editPage != null)
					oBaseResult.setEditPage(editPage);
			}

			if(saves.contains("userPage")) {
				String userPage = (String)doc.get("userPage_docvalues_string");
				if(userPage != null)
					oBaseResult.setUserPage(userPage);
			}

			if(saves.contains("objectSuggest")) {
				String objectSuggest = (String)doc.get("objectSuggest_suggested");
				oBaseResult.setObjectSuggest(objectSuggest);
			}

			String solrId = (String)doc.get("solrId");
			oBaseResult.setSolrId(solrId);
		}
	}

	public void indexBaseResult(JsonObject doc) {
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
		if(saves != null) {
			JsonArray l = new JsonArray();
			doc.put("saves_docvalues_strings", l);
			for(String o : saves) {
				l.add(o);
			}
		}
		if(title != null) {
			doc.put("title_docvalues_string", title);
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

	public static String varStoredBaseResult(String entityVar) {
		switch(entityVar) {
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
			case "saves":
				return "saves_docvalues_strings";
			case "title":
				return "title_docvalues_string";
			case "displayPage":
				return "displayPage_docvalues_string";
			case "editPage":
				return "editPage_docvalues_string";
			case "userPage":
				return "userPage_docvalues_string";
			default:
				return null;
		}
	}

	public static String varIndexedBaseResult(String entityVar) {
		switch(entityVar) {
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
			case "saves":
				return "saves_docvalues_strings";
			case "title":
				return "title_docvalues_string";
			case "displayPage":
				return "displayPage_docvalues_string";
			case "editPage":
				return "editPage_docvalues_string";
			case "userPage":
				return "userPage_docvalues_string";
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

	public static String searchVarBaseResult(String searchVar) {
		switch(searchVar) {
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
			case "saves_docvalues_strings":
				return "saves";
			case "title_docvalues_string":
				return "title";
			case "displayPage_docvalues_string":
				return "displayPage";
			case "editPage_docvalues_string":
				return "editPage";
			case "userPage_docvalues_string":
				return "userPage";
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

		oBaseResult.setCreated(Optional.ofNullable(doc.get("created_docvalues_date")).map(v -> v.toString()).orElse(null));
		oBaseResult.setModified(Optional.ofNullable(doc.get("modified_docvalues_date")).map(v -> v.toString()).orElse(null));
		oBaseResult.setArchived(Optional.ofNullable(doc.get("archived_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBaseResult.setClassCanonicalName(Optional.ofNullable(doc.get("classCanonicalName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setClassSimpleName(Optional.ofNullable(doc.get("classSimpleName_docvalues_string")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("classCanonicalNames_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseResult.addClassCanonicalNames(BaseResult.staticSetClassCanonicalNames(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("saves_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseResult.addSaves(BaseResult.staticSetSaves(siteRequest, v.toString()));
		});
		oBaseResult.setTitle(Optional.ofNullable(doc.get("title_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setDisplayPage(Optional.ofNullable(doc.get("displayPage_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setEditPage(Optional.ofNullable(doc.get("editPage_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setUserPage(Optional.ofNullable(doc.get("userPage_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBaseResult.setObjectSuggest(Optional.ofNullable(doc.get("objectSuggest_suggested")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("objectText_text_enUS")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oBaseResult.addObjectText(BaseResult.staticSetObjectText(siteRequest, v.toString()));
		});
		String solrId = (String)doc.get("solrId");
		oBaseResult.setSolrId(solrId);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBaseResult() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BaseResult) {
			BaseResult original = (BaseResult)o;
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
			if(!Objects.equals(saves, original.getSaves()))
				apiRequest.addVars("saves");
			if(!Objects.equals(title, original.getTitle()))
				apiRequest.addVars("title");
			if(!Objects.equals(displayPage, original.getDisplayPage()))
				apiRequest.addVars("displayPage");
			if(!Objects.equals(editPage, original.getEditPage()))
				apiRequest.addVars("editPage");
			if(!Objects.equals(userPage, original.getUserPage()))
				apiRequest.addVars("userPage");
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
		sb.append(Optional.ofNullable(created).map(v -> "created: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(modified).map(v -> "modified: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(archived).map(v -> "archived: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(classCanonicalName).map(v -> "classCanonicalName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(classSimpleName).map(v -> "classSimpleName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(classCanonicalNames).map(v -> "classCanonicalNames: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(saves).map(v -> "saves: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(title).map(v -> "title: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(displayPage).map(v -> "displayPage: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(editPage).map(v -> "editPage: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userPage).map(v -> "userPage: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(objectSuggest).map(v -> "objectSuggest: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(objectText).map(v -> "objectText: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(solrId).map(v -> "solrId: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "BaseResult";
	public static final String VAR_siteRequest_ = "siteRequest_";
	public static final String VAR_created = "created";
	public static final String VAR_modified = "modified";
	public static final String VAR_archived = "archived";
	public static final String VAR_classCanonicalName = "classCanonicalName";
	public static final String VAR_classSimpleName = "classSimpleName";
	public static final String VAR_classCanonicalNames = "classCanonicalNames";
	public static final String VAR_saves = "saves";
	public static final String VAR_title = "title";
	public static final String VAR_displayPage = "displayPage";
	public static final String VAR_editPage = "editPage";
	public static final String VAR_userPage = "userPage";
	public static final String VAR_objectSuggest = "objectSuggest";
	public static final String VAR_objectText = "objectText";
	public static final String VAR_solrId = "solrId";

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
		vars.add(VAR_displayPage);
		vars.add(VAR_editPage);
		vars.add(VAR_userPage);
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
	public static final String DISPLAY_NAME_created = "created";
	public static final String DISPLAY_NAME_modified = "modified";
	public static final String DISPLAY_NAME_archived = "archived";
	public static final String DISPLAY_NAME_classCanonicalName = "";
	public static final String DISPLAY_NAME_classSimpleName = "object type";
	public static final String DISPLAY_NAME_classCanonicalNames = "";
	public static final String DISPLAY_NAME_saves = "";
	public static final String DISPLAY_NAME_title = "title";
	public static final String DISPLAY_NAME_displayPage = "product page";
	public static final String DISPLAY_NAME_editPage = "edit";
	public static final String DISPLAY_NAME_userPage = "user";
	public static final String DISPLAY_NAME_objectSuggest = "autosuggest";
	public static final String DISPLAY_NAME_objectText = "text";
	public static final String DISPLAY_NAME_solrId = "";

	public String idForClass() {
		return null;
	}

	public String titleForClass() {
		return title;
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
		return BaseResult.displayNameBaseResult(var);
	}
	public static String displayNameBaseResult(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return DISPLAY_NAME_siteRequest_;
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
		case VAR_saves:
			return DISPLAY_NAME_saves;
		case VAR_title:
			return DISPLAY_NAME_title;
		case VAR_displayPage:
			return DISPLAY_NAME_displayPage;
		case VAR_editPage:
			return DISPLAY_NAME_editPage;
		case VAR_userPage:
			return DISPLAY_NAME_userPage;
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

	public static String descriptionBaseResult(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return "The current request object";
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
		case VAR_saves:
			return "A list of fields that are saved for this record in the database";
		case VAR_title:
			return "The title of this page. ";
		case VAR_displayPage:
			return "Visit this product's landing page. ";
		case VAR_editPage:
			return "Edit this";
		case VAR_userPage:
			return "User page";
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

	public static String classSimpleNameBaseResult(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return "SiteRequest";
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
		case VAR_saves:
			return "List";
		case VAR_title:
			return "String";
		case VAR_displayPage:
			return "String";
		case VAR_editPage:
			return "String";
		case VAR_userPage:
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

	public static Integer htmColumnBaseResult(String var) {
		switch(var) {
		case VAR_editPage:
			return 99;
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
		case VAR_displayPage:
			return 99;
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
		case VAR_displayPage:
			return 2;
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
