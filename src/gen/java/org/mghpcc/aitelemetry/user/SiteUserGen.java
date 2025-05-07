package org.mghpcc.aitelemetry.user;

import org.mghpcc.aitelemetry.request.SiteRequest;
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
import io.vertx.core.json.JsonArray;
import java.lang.Boolean;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SiteUserGen into the class SiteUser. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the SiteUser API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the SiteUser class and it's generated class SiteUserGen&lt;BaseModel&gt;: </h3>extends SiteUserGen
 * <p>
 * This Java class extends a generated Java class SiteUserGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser">Find the class SiteUser in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SiteUserGen<BaseModel>
 * <p>This <code>class SiteUser extends SiteUserGen&lt;BaseModel&gt;</code>, which means it extends a newly generated SiteUserGen. 
 * The generated <code>class SiteUserGen extends BaseModel</code> which means that SiteUser extends SiteUserGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiMethode: Search</h2>
 * <p>This class contains a comment <b>"ApiMethod: Search"</b>, which creates an API "Search". 
 * </p>
 * <h2>ApiMethode: PATCH</h2>
 * <p>This class contains a comment <b>"ApiMethod: PATCH"</b>, which creates an API "PATCH". 
 * </p>
 * <h2>ApiMethode: POST</h2>
 * <p>This class contains a comment <b>"ApiMethod: POST"</b>, which creates an API "POST". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiMethode: EditPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: EditPage"</b>, which creates an API "EditPage". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: site users"</b>, which groups all of the OpenAPIs for SiteUser objects under the tag "site users". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/user</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/user"</b>, which defines the base API URI for SiteUser objects as "/en-us/api/user" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SiteUser class will inherit the helpful inherited class comments from the super class SiteUserGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 1</h2>
 * <p>This class contains a comment <b>"Order: 1"</b>, which means this class will be sorted by the given number 1 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 1</h2>
 * <p>This class contains a comment <b>"SqlOrder: 1"</b>, which means this class will be sorted by the given number 1 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.user.SiteUserPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.user.SiteUserPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SiteUser Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a site user</h2>
 * <p>This class contains a comment <b>"AName.enUS: a site user"</b>, which identifies the language context to describe a SiteUser as "a site user". 
 * </p>
 * <p>
 * Delete the class SiteUser in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.user in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.user&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class SiteUserGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(SiteUser.class);

	public static final String Description_enUS = "A user record for each site user";
	public static final String AName_enUS = "a site user";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this site user";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the site user";
	public static final String SingularName_enUS = "site user";
	public static final String PluralName_enUS = "site users";
	public static final String NameActual_enUS = "current site user";
	public static final String AllName_enUS = "all site users";
	public static final String SearchAllNameBy_enUS = "search site users by ";
	public static final String SearchAllName_enUS = "search site users";
	public static final String Title_enUS = "site users";
	public static final String ThePluralName_enUS = "the site users";
	public static final String NoNameFound_enUS = "no site user found";
	public static final String ApiUri_enUS = "/en-us/api/user";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/user";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/user/{userId}";
	public static final String OfName_enUS = "of site user";
	public static final String ANameAdjective_enUS = "a site user";
	public static final String NameAdjectiveSingular_enUS = "site user";
	public static final String NameAdjectivePlural_enUS = "site users";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/user";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/user";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/user";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/user";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/user";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/user";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/user";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/user";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/user";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/user";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/user";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/user";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/user/{userId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/user/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/user/%s";

	public static final String Icon = "<i class=\"fa-duotone fa-solid fa-user-gear\"></i>";

	//////////////
	// userKeys //
	//////////////


	/**	 The entity userKeys
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> userKeys = new ArrayList<Long>();

	/**	<br> The entity userKeys
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:userKeys">Find the entity userKeys in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _userKeys(List<Long> l);

	public List<Long> getUserKeys() {
		return userKeys;
	}

	public void setUserKeys(List<Long> userKeys) {
		this.userKeys = userKeys;
	}
	@JsonIgnore
	public void setUserKeys(String o) {
		Long l = SiteUser.staticSetUserKeys(siteRequest_, o);
		if(l != null)
			addUserKeys(l);
	}
	public static Long staticSetUserKeys(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SiteUser addUserKeys(Long...objects) {
		for(Long o : objects) {
			addUserKeys(o);
		}
		return (SiteUser)this;
	}
	public SiteUser addUserKeys(Long o) {
		if(o != null)
			this.userKeys.add(o);
		return (SiteUser)this;
	}
	@JsonIgnore
	public void setUserKeys(JsonArray objects) {
		userKeys.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			setUserKeys(o);
		}
	}
	public SiteUser addUserKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUserKeys(p);
		}
		return (SiteUser)this;
	}
	protected SiteUser userKeysInit() {
		_userKeys(userKeys);
		return (SiteUser)this;
	}

	public static Long staticSearchUserKeys(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrUserKeys(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserKeys(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchUserKeys(siteRequest_, SiteUser.staticSetUserKeys(siteRequest_, o)).toString();
	}

	////////////
	// userId //
	////////////


	/**	 The entity userId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userId;

	/**	<br> The entity userId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:userId">Find the entity userId in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userId(Wrap<String> c);

	public String getUserId() {
		return userId;
	}
	public void setUserId(String o) {
		this.userId = SiteUser.staticSetUserId(siteRequest_, o);
	}
	public static String staticSetUserId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser userIdInit() {
		Wrap<String> userIdWrap = new Wrap<String>().var("userId");
		if(userId == null) {
			_userId(userIdWrap);
			Optional.ofNullable(userIdWrap.getO()).ifPresent(o -> {
				setUserId(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchUserId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserId(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchUserId(siteRequest_, SiteUser.staticSetUserId(siteRequest_, o)).toString();
	}

	public String sqlUserId() {
		return userId;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:userName">Find the entity userName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userName(Wrap<String> c);

	public String getUserName() {
		return userName;
	}
	public void setUserName(String o) {
		this.userName = SiteUser.staticSetUserName(siteRequest_, o);
	}
	public static String staticSetUserName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser userNameInit() {
		Wrap<String> userNameWrap = new Wrap<String>().var("userName");
		if(userName == null) {
			_userName(userNameWrap);
			Optional.ofNullable(userNameWrap.getO()).ifPresent(o -> {
				setUserName(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchUserName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserName(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchUserName(siteRequest_, SiteUser.staticSetUserName(siteRequest_, o)).toString();
	}

	public String sqlUserName() {
		return userName;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:userEmail">Find the entity userEmail in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userEmail(Wrap<String> c);

	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String o) {
		this.userEmail = SiteUser.staticSetUserEmail(siteRequest_, o);
	}
	public static String staticSetUserEmail(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser userEmailInit() {
		Wrap<String> userEmailWrap = new Wrap<String>().var("userEmail");
		if(userEmail == null) {
			_userEmail(userEmailWrap);
			Optional.ofNullable(userEmailWrap.getO()).ifPresent(o -> {
				setUserEmail(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchUserEmail(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserEmail(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserEmail(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchUserEmail(siteRequest_, SiteUser.staticSetUserEmail(siteRequest_, o)).toString();
	}

	public String sqlUserEmail() {
		return userEmail;
	}

	///////////////////
	// userFirstName //
	///////////////////


	/**	 The entity userFirstName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userFirstName;

	/**	<br> The entity userFirstName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:userFirstName">Find the entity userFirstName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userFirstName(Wrap<String> c);

	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String o) {
		this.userFirstName = SiteUser.staticSetUserFirstName(siteRequest_, o);
	}
	public static String staticSetUserFirstName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser userFirstNameInit() {
		Wrap<String> userFirstNameWrap = new Wrap<String>().var("userFirstName");
		if(userFirstName == null) {
			_userFirstName(userFirstNameWrap);
			Optional.ofNullable(userFirstNameWrap.getO()).ifPresent(o -> {
				setUserFirstName(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchUserFirstName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserFirstName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserFirstName(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchUserFirstName(siteRequest_, SiteUser.staticSetUserFirstName(siteRequest_, o)).toString();
	}

	public String sqlUserFirstName() {
		return userFirstName;
	}

	//////////////////
	// userLastName //
	//////////////////


	/**	 The entity userLastName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userLastName;

	/**	<br> The entity userLastName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:userLastName">Find the entity userLastName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userLastName(Wrap<String> c);

	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String o) {
		this.userLastName = SiteUser.staticSetUserLastName(siteRequest_, o);
	}
	public static String staticSetUserLastName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser userLastNameInit() {
		Wrap<String> userLastNameWrap = new Wrap<String>().var("userLastName");
		if(userLastName == null) {
			_userLastName(userLastNameWrap);
			Optional.ofNullable(userLastNameWrap.getO()).ifPresent(o -> {
				setUserLastName(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchUserLastName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserLastName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserLastName(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchUserLastName(siteRequest_, SiteUser.staticSetUserLastName(siteRequest_, o)).toString();
	}

	public String sqlUserLastName() {
		return userLastName;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:userFullName">Find the entity userFullName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userFullName(Wrap<String> c);

	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String o) {
		this.userFullName = SiteUser.staticSetUserFullName(siteRequest_, o);
	}
	public static String staticSetUserFullName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser userFullNameInit() {
		Wrap<String> userFullNameWrap = new Wrap<String>().var("userFullName");
		if(userFullName == null) {
			_userFullName(userFullNameWrap);
			Optional.ofNullable(userFullNameWrap.getO()).ifPresent(o -> {
				setUserFullName(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchUserFullName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserFullName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserFullName(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchUserFullName(siteRequest_, SiteUser.staticSetUserFullName(siteRequest_, o)).toString();
	}

	public String sqlUserFullName() {
		return userFullName;
	}

	/////////////////
	// seeArchived //
	/////////////////


	/**	 The entity seeArchived
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean seeArchived;

	/**	<br> The entity seeArchived
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:seeArchived">Find the entity seeArchived in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seeArchived(Wrap<Boolean> c);

	public Boolean getSeeArchived() {
		return seeArchived;
	}

	public void setSeeArchived(Boolean seeArchived) {
		this.seeArchived = seeArchived;
	}
	@JsonIgnore
	public void setSeeArchived(String o) {
		this.seeArchived = SiteUser.staticSetSeeArchived(siteRequest_, o);
	}
	public static Boolean staticSetSeeArchived(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SiteUser seeArchivedInit() {
		Wrap<Boolean> seeArchivedWrap = new Wrap<Boolean>().var("seeArchived");
		if(seeArchived == null) {
			_seeArchived(seeArchivedWrap);
			Optional.ofNullable(seeArchivedWrap.getO()).ifPresent(o -> {
				setSeeArchived(o);
			});
		}
		return (SiteUser)this;
	}

	public static Boolean staticSearchSeeArchived(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrSeeArchived(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSeeArchived(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchSeeArchived(siteRequest_, SiteUser.staticSetSeeArchived(siteRequest_, o)).toString();
	}

	public Boolean sqlSeeArchived() {
		return seeArchived;
	}

	/////////////////
	// displayName //
	/////////////////


	/**	 The entity displayName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String displayName;

	/**	<br> The entity displayName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:displayName">Find the entity displayName in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _displayName(Wrap<String> c);

	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String o) {
		this.displayName = SiteUser.staticSetDisplayName(siteRequest_, o);
	}
	public static String staticSetDisplayName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser displayNameInit() {
		Wrap<String> displayNameWrap = new Wrap<String>().var("displayName");
		if(displayName == null) {
			_displayName(displayNameWrap);
			Optional.ofNullable(displayNameWrap.getO()).ifPresent(o -> {
				setDisplayName(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchDisplayName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDisplayName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDisplayName(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchDisplayName(siteRequest_, SiteUser.staticSetDisplayName(siteRequest_, o)).toString();
	}

	public String sqlDisplayName() {
		return displayName;
	}

	///////////////
	// siteTheme //
	///////////////


	/**	 The entity siteTheme
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteTheme;

	/**	<br> The entity siteTheme
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:siteTheme">Find the entity siteTheme in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteTheme(Wrap<String> w);

	public String getSiteTheme() {
		return siteTheme;
	}
	public void setSiteTheme(String o) {
		this.siteTheme = SiteUser.staticSetSiteTheme(siteRequest_, o);
	}
	public static String staticSetSiteTheme(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser siteThemeInit() {
		Wrap<String> siteThemeWrap = new Wrap<String>().var("siteTheme");
		if(siteTheme == null) {
			_siteTheme(siteThemeWrap);
			Optional.ofNullable(siteThemeWrap.getO()).ifPresent(o -> {
				setSiteTheme(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchSiteTheme(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteTheme(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteTheme(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchSiteTheme(siteRequest_, SiteUser.staticSetSiteTheme(siteRequest_, o)).toString();
	}

	public String sqlSiteTheme() {
		return siteTheme;
	}

	////////////////////////
	// webComponentsTheme //
	////////////////////////


	/**	 The entity webComponentsTheme
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String webComponentsTheme;

	/**	<br> The entity webComponentsTheme
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.user.SiteUser&fq=entiteVar_enUS_indexed_string:webComponentsTheme">Find the entity webComponentsTheme in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _webComponentsTheme(Wrap<String> w);

	public String getWebComponentsTheme() {
		return webComponentsTheme;
	}
	public void setWebComponentsTheme(String o) {
		this.webComponentsTheme = SiteUser.staticSetWebComponentsTheme(siteRequest_, o);
	}
	public static String staticSetWebComponentsTheme(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SiteUser webComponentsThemeInit() {
		Wrap<String> webComponentsThemeWrap = new Wrap<String>().var("webComponentsTheme");
		if(webComponentsTheme == null) {
			_webComponentsTheme(webComponentsThemeWrap);
			Optional.ofNullable(webComponentsThemeWrap.getO()).ifPresent(o -> {
				setWebComponentsTheme(o);
			});
		}
		return (SiteUser)this;
	}

	public static String staticSearchWebComponentsTheme(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrWebComponentsTheme(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWebComponentsTheme(SiteRequest siteRequest_, String o) {
		return SiteUser.staticSearchWebComponentsTheme(siteRequest_, SiteUser.staticSetWebComponentsTheme(siteRequest_, o)).toString();
	}

	public String sqlWebComponentsTheme() {
		return webComponentsTheme;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<SiteUserGen<DEV>> promiseDeepSiteUser(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepSiteUser();
	}

	public Future<SiteUserGen<DEV>> promiseDeepSiteUser() {
		Promise<SiteUserGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseSiteUser(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseModel(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseSiteUser(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				userKeysInit();
				userIdInit();
				userNameInit();
				userEmailInit();
				userFirstNameInit();
				userLastNameInit();
				userFullNameInit();
				seeArchivedInit();
				displayNameInit();
				siteThemeInit();
				webComponentsThemeInit();
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

	@Override public Future<? extends SiteUserGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepSiteUser(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteUser(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestSiteUser(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteUser(v);
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
	public Object obtainSiteUser(String var) {
		SiteUser oSiteUser = (SiteUser)this;
		switch(var) {
			case "userKeys":
				return oSiteUser.userKeys;
			case "userId":
				return oSiteUser.userId;
			case "userName":
				return oSiteUser.userName;
			case "userEmail":
				return oSiteUser.userEmail;
			case "userFirstName":
				return oSiteUser.userFirstName;
			case "userLastName":
				return oSiteUser.userLastName;
			case "userFullName":
				return oSiteUser.userFullName;
			case "seeArchived":
				return oSiteUser.seeArchived;
			case "displayName":
				return oSiteUser.displayName;
			case "siteTheme":
				return oSiteUser.siteTheme;
			case "webComponentsTheme":
				return oSiteUser.webComponentsTheme;
			default:
				return super.obtainBaseModel(var);
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
				o = relateSiteUser(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateSiteUser(String var, Object val) {
		SiteUser oSiteUser = (SiteUser)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetSiteUser(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSiteUser(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "userKeys":
			return SiteUser.staticSetUserKeys(siteRequest_, o);
		case "userId":
			return SiteUser.staticSetUserId(siteRequest_, o);
		case "userName":
			return SiteUser.staticSetUserName(siteRequest_, o);
		case "userEmail":
			return SiteUser.staticSetUserEmail(siteRequest_, o);
		case "userFirstName":
			return SiteUser.staticSetUserFirstName(siteRequest_, o);
		case "userLastName":
			return SiteUser.staticSetUserLastName(siteRequest_, o);
		case "userFullName":
			return SiteUser.staticSetUserFullName(siteRequest_, o);
		case "seeArchived":
			return SiteUser.staticSetSeeArchived(siteRequest_, o);
		case "displayName":
			return SiteUser.staticSetDisplayName(siteRequest_, o);
		case "siteTheme":
			return SiteUser.staticSetSiteTheme(siteRequest_, o);
		case "webComponentsTheme":
			return SiteUser.staticSetWebComponentsTheme(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchSiteUser(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchSiteUser(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "userKeys":
			return SiteUser.staticSearchUserKeys(siteRequest_, (Long)o);
		case "userId":
			return SiteUser.staticSearchUserId(siteRequest_, (String)o);
		case "userName":
			return SiteUser.staticSearchUserName(siteRequest_, (String)o);
		case "userEmail":
			return SiteUser.staticSearchUserEmail(siteRequest_, (String)o);
		case "userFirstName":
			return SiteUser.staticSearchUserFirstName(siteRequest_, (String)o);
		case "userLastName":
			return SiteUser.staticSearchUserLastName(siteRequest_, (String)o);
		case "userFullName":
			return SiteUser.staticSearchUserFullName(siteRequest_, (String)o);
		case "seeArchived":
			return SiteUser.staticSearchSeeArchived(siteRequest_, (Boolean)o);
		case "displayName":
			return SiteUser.staticSearchDisplayName(siteRequest_, (String)o);
		case "siteTheme":
			return SiteUser.staticSearchSiteTheme(siteRequest_, (String)o);
		case "webComponentsTheme":
			return SiteUser.staticSearchWebComponentsTheme(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrSiteUser(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrSiteUser(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "userKeys":
			return SiteUser.staticSearchStrUserKeys(siteRequest_, (Long)o);
		case "userId":
			return SiteUser.staticSearchStrUserId(siteRequest_, (String)o);
		case "userName":
			return SiteUser.staticSearchStrUserName(siteRequest_, (String)o);
		case "userEmail":
			return SiteUser.staticSearchStrUserEmail(siteRequest_, (String)o);
		case "userFirstName":
			return SiteUser.staticSearchStrUserFirstName(siteRequest_, (String)o);
		case "userLastName":
			return SiteUser.staticSearchStrUserLastName(siteRequest_, (String)o);
		case "userFullName":
			return SiteUser.staticSearchStrUserFullName(siteRequest_, (String)o);
		case "seeArchived":
			return SiteUser.staticSearchStrSeeArchived(siteRequest_, (Boolean)o);
		case "displayName":
			return SiteUser.staticSearchStrDisplayName(siteRequest_, (String)o);
		case "siteTheme":
			return SiteUser.staticSearchStrSiteTheme(siteRequest_, (String)o);
		case "webComponentsTheme":
			return SiteUser.staticSearchStrWebComponentsTheme(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqSiteUser(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqSiteUser(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "userKeys":
			return SiteUser.staticSearchFqUserKeys(siteRequest_, o);
		case "userId":
			return SiteUser.staticSearchFqUserId(siteRequest_, o);
		case "userName":
			return SiteUser.staticSearchFqUserName(siteRequest_, o);
		case "userEmail":
			return SiteUser.staticSearchFqUserEmail(siteRequest_, o);
		case "userFirstName":
			return SiteUser.staticSearchFqUserFirstName(siteRequest_, o);
		case "userLastName":
			return SiteUser.staticSearchFqUserLastName(siteRequest_, o);
		case "userFullName":
			return SiteUser.staticSearchFqUserFullName(siteRequest_, o);
		case "seeArchived":
			return SiteUser.staticSearchFqSeeArchived(siteRequest_, o);
		case "displayName":
			return SiteUser.staticSearchFqDisplayName(siteRequest_, o);
		case "siteTheme":
			return SiteUser.staticSearchFqSiteTheme(siteRequest_, o);
		case "webComponentsTheme":
			return SiteUser.staticSearchFqWebComponentsTheme(siteRequest_, o);
			default:
				return BaseModel.staticSearchFqBaseModel(entityVar,  siteRequest_, o);
		}
	}

	/////////////
	// persist //
	/////////////

	@Override public boolean persistForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = persistSiteUser(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistSiteUser(String var, Object val) {
		String varLower = var.toLowerCase();
			if("userid".equals(varLower)) {
				if(val instanceof String) {
					setUserId((String)val);
				}
				saves.add("userId");
				return val;
			} else if("username".equals(varLower)) {
				if(val instanceof String) {
					setUserName((String)val);
				}
				saves.add("userName");
				return val;
			} else if("useremail".equals(varLower)) {
				if(val instanceof String) {
					setUserEmail((String)val);
				}
				saves.add("userEmail");
				return val;
			} else if("userfirstname".equals(varLower)) {
				if(val instanceof String) {
					setUserFirstName((String)val);
				}
				saves.add("userFirstName");
				return val;
			} else if("userlastname".equals(varLower)) {
				if(val instanceof String) {
					setUserLastName((String)val);
				}
				saves.add("userLastName");
				return val;
			} else if("userfullname".equals(varLower)) {
				if(val instanceof String) {
					setUserFullName((String)val);
				}
				saves.add("userFullName");
				return val;
			} else if("seearchived".equals(varLower)) {
				if(val instanceof Boolean) {
					setSeeArchived((Boolean)val);
				} else {
					setSeeArchived(val == null ? null : val.toString());
				}
				saves.add("seeArchived");
				return val;
			} else if("displayname".equals(varLower)) {
				if(val instanceof String) {
					setDisplayName((String)val);
				}
				saves.add("displayName");
				return val;
			} else if("sitetheme".equals(varLower)) {
				if(val instanceof String) {
					setSiteTheme((String)val);
				}
				saves.add("siteTheme");
				return val;
			} else if("webcomponentstheme".equals(varLower)) {
				if(val instanceof String) {
					setWebComponentsTheme((String)val);
				}
				saves.add("webComponentsTheme");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateSiteUser(doc);
	}
	public void populateSiteUser(SolrResponse.Doc doc) {
		SiteUser oSiteUser = (SiteUser)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("userKeys")) {
				List<Long> userKeys = (List<Long>)doc.get("userKeys_docvalues_longs");
				if(userKeys != null) {
					userKeys.stream().forEach( v -> {
						oSiteUser.userKeys.add(v);
					});
				}
			}

			if(saves.contains("userId")) {
				String userId = (String)doc.get("userId_docvalues_string");
				if(userId != null)
					oSiteUser.setUserId(userId);
			}

			if(saves.contains("userName")) {
				String userName = (String)doc.get("userName_docvalues_string");
				if(userName != null)
					oSiteUser.setUserName(userName);
			}

			if(saves.contains("userEmail")) {
				String userEmail = (String)doc.get("userEmail_docvalues_string");
				if(userEmail != null)
					oSiteUser.setUserEmail(userEmail);
			}

			if(saves.contains("userFirstName")) {
				String userFirstName = (String)doc.get("userFirstName_docvalues_string");
				if(userFirstName != null)
					oSiteUser.setUserFirstName(userFirstName);
			}

			if(saves.contains("userLastName")) {
				String userLastName = (String)doc.get("userLastName_docvalues_string");
				if(userLastName != null)
					oSiteUser.setUserLastName(userLastName);
			}

			if(saves.contains("userFullName")) {
				String userFullName = (String)doc.get("userFullName_docvalues_string");
				if(userFullName != null)
					oSiteUser.setUserFullName(userFullName);
			}

			if(saves.contains("seeArchived")) {
				Boolean seeArchived = (Boolean)doc.get("seeArchived_docvalues_boolean");
				if(seeArchived != null)
					oSiteUser.setSeeArchived(seeArchived);
			}

			if(saves.contains("displayName")) {
				String displayName = (String)doc.get("displayName_docvalues_string");
				if(displayName != null)
					oSiteUser.setDisplayName(displayName);
			}

			if(saves.contains("siteTheme")) {
				String siteTheme = (String)doc.get("siteTheme_docvalues_string");
				if(siteTheme != null)
					oSiteUser.setSiteTheme(siteTheme);
			}

			if(saves.contains("webComponentsTheme")) {
				String webComponentsTheme = (String)doc.get("webComponentsTheme_docvalues_string");
				if(webComponentsTheme != null)
					oSiteUser.setWebComponentsTheme(webComponentsTheme);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexSiteUser(JsonObject doc) {
		if(userKeys != null) {
			JsonArray l = new JsonArray();
			doc.put("userKeys_docvalues_longs", l);
			for(Long o : userKeys) {
				l.add(SiteUser.staticSearchUserKeys(siteRequest_, o));
			}
		}
		if(userId != null) {
			doc.put("userId_docvalues_string", userId);
		}
		if(userName != null) {
			doc.put("userName_docvalues_string", userName);
		}
		if(userEmail != null) {
			doc.put("userEmail_docvalues_string", userEmail);
		}
		if(userFirstName != null) {
			doc.put("userFirstName_docvalues_string", userFirstName);
		}
		if(userLastName != null) {
			doc.put("userLastName_docvalues_string", userLastName);
		}
		if(userFullName != null) {
			doc.put("userFullName_docvalues_string", userFullName);
		}
		if(seeArchived != null) {
			doc.put("seeArchived_docvalues_boolean", seeArchived);
		}
		if(displayName != null) {
			doc.put("displayName_docvalues_string", displayName);
		}
		if(siteTheme != null) {
			doc.put("siteTheme_docvalues_string", siteTheme);
		}
		if(webComponentsTheme != null) {
			doc.put("webComponentsTheme_docvalues_string", webComponentsTheme);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredSiteUser(String entityVar) {
		switch(entityVar) {
			case "userKeys":
				return "userKeys_docvalues_longs";
			case "userId":
				return "userId_docvalues_string";
			case "userName":
				return "userName_docvalues_string";
			case "userEmail":
				return "userEmail_docvalues_string";
			case "userFirstName":
				return "userFirstName_docvalues_string";
			case "userLastName":
				return "userLastName_docvalues_string";
			case "userFullName":
				return "userFullName_docvalues_string";
			case "seeArchived":
				return "seeArchived_docvalues_boolean";
			case "displayName":
				return "displayName_docvalues_string";
			case "siteTheme":
				return "siteTheme_docvalues_string";
			case "webComponentsTheme":
				return "webComponentsTheme_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedSiteUser(String entityVar) {
		switch(entityVar) {
			case "userKeys":
				return "userKeys_docvalues_longs";
			case "userId":
				return "userId_docvalues_string";
			case "userName":
				return "userName_docvalues_string";
			case "userEmail":
				return "userEmail_docvalues_string";
			case "userFirstName":
				return "userFirstName_docvalues_string";
			case "userLastName":
				return "userLastName_docvalues_string";
			case "userFullName":
				return "userFullName_docvalues_string";
			case "seeArchived":
				return "seeArchived_docvalues_boolean";
			case "displayName":
				return "displayName_docvalues_string";
			case "siteTheme":
				return "siteTheme_docvalues_string";
			case "webComponentsTheme":
				return "webComponentsTheme_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarSiteUser(String searchVar) {
		switch(searchVar) {
			case "userKeys_docvalues_longs":
				return "userKeys";
			case "userId_docvalues_string":
				return "userId";
			case "userName_docvalues_string":
				return "userName";
			case "userEmail_docvalues_string":
				return "userEmail";
			case "userFirstName_docvalues_string":
				return "userFirstName";
			case "userLastName_docvalues_string":
				return "userLastName";
			case "userFullName_docvalues_string":
				return "userFullName";
			case "seeArchived_docvalues_boolean":
				return "seeArchived";
			case "displayName_docvalues_string":
				return "displayName";
			case "siteTheme_docvalues_string":
				return "siteTheme";
			case "webComponentsTheme_docvalues_string":
				return "webComponentsTheme";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchSiteUser(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedSiteUser(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeSiteUser(doc);
	}
	public void storeSiteUser(SolrResponse.Doc doc) {
		SiteUser oSiteUser = (SiteUser)this;
		SiteRequest siteRequest = oSiteUser.getSiteRequest_();

		Optional.ofNullable((List<?>)doc.get("userKeys_docvalues_longs")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oSiteUser.addUserKeys(SiteUser.staticSetUserKeys(siteRequest, v.toString()));
		});
		oSiteUser.setUserId(Optional.ofNullable(doc.get("userId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSiteUser.setUserName(Optional.ofNullable(doc.get("userName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSiteUser.setUserEmail(Optional.ofNullable(doc.get("userEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSiteUser.setUserFirstName(Optional.ofNullable(doc.get("userFirstName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSiteUser.setUserLastName(Optional.ofNullable(doc.get("userLastName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSiteUser.setUserFullName(Optional.ofNullable(doc.get("userFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSiteUser.setSeeArchived(Optional.ofNullable(doc.get("seeArchived_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oSiteUser.setDisplayName(Optional.ofNullable(doc.get("displayName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSiteUser.setSiteTheme(Optional.ofNullable(doc.get("siteTheme_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSiteUser.setWebComponentsTheme(Optional.ofNullable(doc.get("webComponentsTheme_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSiteUser() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SiteUser) {
			SiteUser original = (SiteUser)o;
			if(!Objects.equals(userKeys, original.getUserKeys()))
				apiRequest.addVars("userKeys");
			if(!Objects.equals(userId, original.getUserId()))
				apiRequest.addVars("userId");
			if(!Objects.equals(userName, original.getUserName()))
				apiRequest.addVars("userName");
			if(!Objects.equals(userEmail, original.getUserEmail()))
				apiRequest.addVars("userEmail");
			if(!Objects.equals(userFirstName, original.getUserFirstName()))
				apiRequest.addVars("userFirstName");
			if(!Objects.equals(userLastName, original.getUserLastName()))
				apiRequest.addVars("userLastName");
			if(!Objects.equals(userFullName, original.getUserFullName()))
				apiRequest.addVars("userFullName");
			if(!Objects.equals(seeArchived, original.getSeeArchived()))
				apiRequest.addVars("seeArchived");
			if(!Objects.equals(displayName, original.getDisplayName()))
				apiRequest.addVars("displayName");
			if(!Objects.equals(siteTheme, original.getSiteTheme()))
				apiRequest.addVars("siteTheme");
			if(!Objects.equals(webComponentsTheme, original.getWebComponentsTheme()))
				apiRequest.addVars("webComponentsTheme");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(userKeys).map(v -> "userKeys: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(userId).map(v -> "userId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userName).map(v -> "userName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userEmail).map(v -> "userEmail: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userFirstName).map(v -> "userFirstName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userLastName).map(v -> "userLastName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userFullName).map(v -> "userFullName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(seeArchived).map(v -> "seeArchived: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(displayName).map(v -> "displayName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(siteTheme).map(v -> "siteTheme: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(webComponentsTheme).map(v -> "webComponentsTheme: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "SiteUser";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.user.SiteUser";
	public static final String CLASS_API_ADDRESS_SiteUser = "ai-telemetry-enUS-SiteUser";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_SiteUser;
	}
	public static final String VAR_userKeys = "userKeys";
	public static final String VAR_userId = "userId";
	public static final String VAR_userName = "userName";
	public static final String VAR_userEmail = "userEmail";
	public static final String VAR_userFirstName = "userFirstName";
	public static final String VAR_userLastName = "userLastName";
	public static final String VAR_userFullName = "userFullName";
	public static final String VAR_seeArchived = "seeArchived";
	public static final String VAR_displayName = "displayName";
	public static final String VAR_siteTheme = "siteTheme";
	public static final String VAR_webComponentsTheme = "webComponentsTheme";

	public static List<String> varsQForClass() {
		return SiteUser.varsQSiteUser(new ArrayList<String>());
	}
	public static List<String> varsQSiteUser(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return SiteUser.varsFqSiteUser(new ArrayList<String>());
	}
	public static List<String> varsFqSiteUser(List<String> vars) {
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return SiteUser.varsRangeSiteUser(new ArrayList<String>());
	}
	public static List<String> varsRangeSiteUser(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_userKeys = "";
	public static final String DISPLAY_NAME_userId = "";
	public static final String DISPLAY_NAME_userName = "";
	public static final String DISPLAY_NAME_userEmail = "";
	public static final String DISPLAY_NAME_userFirstName = "";
	public static final String DISPLAY_NAME_userLastName = "";
	public static final String DISPLAY_NAME_userFullName = "";
	public static final String DISPLAY_NAME_seeArchived = "see archived";
	public static final String DISPLAY_NAME_displayName = "";
	public static final String DISPLAY_NAME_siteTheme = "site theme";
	public static final String DISPLAY_NAME_webComponentsTheme = "web components theme";

	@Override
	public String idForClass() {
		return userId;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return displayName;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return SiteUser.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/user/%s";
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
		return SiteUser.displayNameSiteUser(var);
	}
	public static String displayNameSiteUser(String var) {
		switch(var) {
		case VAR_userKeys:
			return DISPLAY_NAME_userKeys;
		case VAR_userId:
			return DISPLAY_NAME_userId;
		case VAR_userName:
			return DISPLAY_NAME_userName;
		case VAR_userEmail:
			return DISPLAY_NAME_userEmail;
		case VAR_userFirstName:
			return DISPLAY_NAME_userFirstName;
		case VAR_userLastName:
			return DISPLAY_NAME_userLastName;
		case VAR_userFullName:
			return DISPLAY_NAME_userFullName;
		case VAR_seeArchived:
			return DISPLAY_NAME_seeArchived;
		case VAR_displayName:
			return DISPLAY_NAME_displayName;
		case VAR_siteTheme:
			return DISPLAY_NAME_siteTheme;
		case VAR_webComponentsTheme:
			return DISPLAY_NAME_webComponentsTheme;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionSiteUser(String var) {
		switch(var) {
		case VAR_userKeys:
			return "User keys that relate to this user";
		case VAR_userId:
			return "The unique user ID from the SSO server";
		case VAR_userName:
			return "The user's username";
		case VAR_userEmail:
			return "The user's email";
		case VAR_userFirstName:
			return "The user's first name";
		case VAR_userLastName:
			return "The user's last name";
		case VAR_userFullName:
			return "The user's full name";
		case VAR_seeArchived:
			return "A user field allowing a user to see archived records";
		case VAR_displayName:
			return "The display name for this user";
		case VAR_siteTheme:
			return "The site theme, either light or dark. ";
		case VAR_webComponentsTheme:
			return "The web components theme for the site. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameSiteUser(String var) {
		switch(var) {
		case VAR_userKeys:
			return "List";
		case VAR_userId:
			return "String";
		case VAR_userName:
			return "String";
		case VAR_userEmail:
			return "String";
		case VAR_userFirstName:
			return "String";
		case VAR_userLastName:
			return "String";
		case VAR_userFullName:
			return "String";
		case VAR_seeArchived:
			return "Boolean";
		case VAR_displayName:
			return "String";
		case VAR_siteTheme:
			return "String";
		case VAR_webComponentsTheme:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnSiteUser(String var) {
		switch(var) {
		case VAR_userName:
			return 2;
		case VAR_userFullName:
			return 1;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowSiteUser(String var) {
		switch(var) {
		case VAR_seeArchived:
			return 4;
		case VAR_siteTheme:
			return 4;
		case VAR_webComponentsTheme:
			return 4;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellSiteUser(String var) {
		switch(var) {
		case VAR_seeArchived:
			return 1;
		case VAR_siteTheme:
			return 2;
		case VAR_webComponentsTheme:
			return 3;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinSiteUser(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxSiteUser(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxSiteUser(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minSiteUser(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
