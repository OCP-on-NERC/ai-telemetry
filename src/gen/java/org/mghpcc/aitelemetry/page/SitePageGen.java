package org.mghpcc.aitelemetry.page;

import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.result.BaseResult;
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
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;
import io.vertx.core.json.JsonObject;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SitePageGen into the class SitePage. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the SitePage API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these SitePage objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the SitePageGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the SitePage class and it's generated class SitePageGen&lt;BaseResult&gt;: </h3>extends SitePageGen
 * <p>
 * This Java class extends a generated Java class SitePageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage">Find the class SitePage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SitePageGen<BaseResult>
 * <p>This <code>class SitePage extends SitePageGen&lt;BaseResult&gt;</code>, which means it extends a newly generated SitePageGen. 
 * The generated <code>class SitePageGen extends BaseResult</code> which means that SitePage extends SitePageGen which extends BaseResult. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiMethode: Search</h2>
 * <p>This class contains a comment <b>"ApiMethod: Search"</b>, which creates an API "Search". 
 * </p>
 * <h2>ApiMethode: GET</h2>
 * <p>This class contains a comment <b>"ApiMethod: GET"</b>, which creates an API "GET". 
 * </p>
 * <h2>ApiMethode: PATCH</h2>
 * <p>This class contains a comment <b>"ApiMethod: PATCH"</b>, which creates an API "PATCH". 
 * </p>
 * <h2>ApiMethode: POST</h2>
 * <p>This class contains a comment <b>"ApiMethod: POST"</b>, which creates an API "POST". 
 * </p>
 * <h2>ApiMethode: PUTImport</h2>
 * <p>This class contains a comment <b>"ApiMethod: PUTImport"</b>, which creates an API "PUTImport". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiMethode: EditPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: EditPage"</b>, which creates an API "EditPage". 
 * </p>
 * <h2>ApiMethode: DisplayPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: DisplayPage"</b>, which creates an API "DisplayPage". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: articles"</b>, which groups all of the OpenAPIs for SitePage objects under the tag "articles". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/article</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/article"</b>, which defines the base API URI for SitePage objects as "/en-us/api/article" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SitePage class will inherit the helpful inherited class comments from the super class SitePageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 2</h2>
 * <p>This class contains a comment <b>"Order: 2"</b>, which means this class will be sorted by the given number 2 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 2</h2>
 * <p>This class contains a comment <b>"SqlOrder: 2"</b>, which means this class will be sorted by the given number 2 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.page.SitePagePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.page.SitePagePage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SitePage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an article</h2>
 * <p>This class contains a comment <b>"AName.enUS: an article"</b>, which identifies the language context to describe a SitePage as "an article". 
 * </p>
 * <p>
 * Delete the class SitePage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class SitePageGen<DEV> extends BaseResult {
	protected static final Logger LOG = LoggerFactory.getLogger(SitePage.class);

	public static final String Description_enUS = "Read the latest articles to learn more";
	public static final String AName_enUS = "an article";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this article";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "thearticle";
	public static final String SingularName_enUS = "article";
	public static final String PluralName_enUS = "articles";
	public static final String NameActual_enUS = "current article";
	public static final String AllName_enUS = "all articles";
	public static final String SearchAllNameBy_enUS = "search articles by ";
	public static final String SearchAllName_enUS = "search articles";
	public static final String Title_enUS = "articles";
	public static final String ThePluralName_enUS = "the articles";
	public static final String NoNameFound_enUS = "no article found";
	public static final String ApiUri_enUS = "/en-us/api/article";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/article";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/article/{pageId}";
	public static final String OfName_enUS = "of article";
	public static final String ANameAdjective_enUS = "an article";
	public static final String NameAdjectiveSingular_enUS = "article";
	public static final String NameAdjectivePlural_enUS = "articles";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/article";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/article";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/article";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/article/{pageId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/article/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/article/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/article";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/article";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/article";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/article";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/article";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/article";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/article-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/article-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/article-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/article";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/article";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/article";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/article/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/article/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/article/%s";
	public static final String DisplayPage_enUS_OpenApiUri = "/en-us/view/article/{pageId}";
	public static final String DisplayPage_enUS_StringFormatUri = "/en-us/view/article/%s";
	public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/view/article/%s";

	public static final String Icon = "<i class=\"fa-duotone fa-solid fa-newspaper\"></i>";

	/////////////
	// article //
	/////////////


	/**	 The entity article
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean article;

	/**	<br> The entity article
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:article">Find the entity article in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _article(Wrap<Boolean> w);

	public Boolean getArticle() {
		return article;
	}

	public void setArticle(Boolean article) {
		this.article = article;
	}
	@JsonIgnore
	public void setArticle(String o) {
		this.article = SitePage.staticSetArticle(siteRequest_, o);
	}
	public static Boolean staticSetArticle(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SitePage articleInit() {
		Wrap<Boolean> articleWrap = new Wrap<Boolean>().var("article");
		if(article == null) {
			_article(articleWrap);
			Optional.ofNullable(articleWrap.getO()).ifPresent(o -> {
				setArticle(o);
			});
		}
		return (SitePage)this;
	}

	public static Boolean staticSearchArticle(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrArticle(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqArticle(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchArticle(siteRequest_, SitePage.staticSetArticle(siteRequest_, o)).toString();
	}

	///////////////
	// githubOrg //
	///////////////


	/**	 The entity githubOrg
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String githubOrg;

	/**	<br> The entity githubOrg
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:githubOrg">Find the entity githubOrg in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _githubOrg(Wrap<String> w);

	public String getGithubOrg() {
		return githubOrg;
	}
	public void setGithubOrg(String o) {
		this.githubOrg = SitePage.staticSetGithubOrg(siteRequest_, o);
	}
	public static String staticSetGithubOrg(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage githubOrgInit() {
		Wrap<String> githubOrgWrap = new Wrap<String>().var("githubOrg");
		if(githubOrg == null) {
			_githubOrg(githubOrgWrap);
			Optional.ofNullable(githubOrgWrap.getO()).ifPresent(o -> {
				setGithubOrg(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchGithubOrg(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrGithubOrg(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGithubOrg(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchGithubOrg(siteRequest_, SitePage.staticSetGithubOrg(siteRequest_, o)).toString();
	}

	//////////////
	// siteName //
	//////////////


	/**	 The entity siteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteName;

	/**	<br> The entity siteName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:siteName">Find the entity siteName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteName(Wrap<String> w);

	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String o) {
		this.siteName = SitePage.staticSetSiteName(siteRequest_, o);
	}
	public static String staticSetSiteName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage siteNameInit() {
		Wrap<String> siteNameWrap = new Wrap<String>().var("siteName");
		if(siteName == null) {
			_siteName(siteNameWrap);
			Optional.ofNullable(siteNameWrap.getO()).ifPresent(o -> {
				setSiteName(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSiteName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteName(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSiteName(siteRequest_, SitePage.staticSetSiteName(siteRequest_, o)).toString();
	}

	/////////////////////
	// siteDisplayName //
	/////////////////////


	/**	 The entity siteDisplayName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteDisplayName;

	/**	<br> The entity siteDisplayName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:siteDisplayName">Find the entity siteDisplayName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteDisplayName(Wrap<String> w);

	public String getSiteDisplayName() {
		return siteDisplayName;
	}
	public void setSiteDisplayName(String o) {
		this.siteDisplayName = SitePage.staticSetSiteDisplayName(siteRequest_, o);
	}
	public static String staticSetSiteDisplayName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage siteDisplayNameInit() {
		Wrap<String> siteDisplayNameWrap = new Wrap<String>().var("siteDisplayName");
		if(siteDisplayName == null) {
			_siteDisplayName(siteDisplayNameWrap);
			Optional.ofNullable(siteDisplayNameWrap.getO()).ifPresent(o -> {
				setSiteDisplayName(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSiteDisplayName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteDisplayName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteDisplayName(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSiteDisplayName(siteRequest_, SitePage.staticSetSiteDisplayName(siteRequest_, o)).toString();
	}

	///////////////////
	// sitePublicUrl //
	///////////////////


	/**	 The entity sitePublicUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sitePublicUrl;

	/**	<br> The entity sitePublicUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:sitePublicUrl">Find the entity sitePublicUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sitePublicUrl(Wrap<String> w);

	public String getSitePublicUrl() {
		return sitePublicUrl;
	}
	public void setSitePublicUrl(String o) {
		this.sitePublicUrl = SitePage.staticSetSitePublicUrl(siteRequest_, o);
	}
	public static String staticSetSitePublicUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage sitePublicUrlInit() {
		Wrap<String> sitePublicUrlWrap = new Wrap<String>().var("sitePublicUrl");
		if(sitePublicUrl == null) {
			_sitePublicUrl(sitePublicUrlWrap);
			Optional.ofNullable(sitePublicUrlWrap.getO()).ifPresent(o -> {
				setSitePublicUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSitePublicUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSitePublicUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSitePublicUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSitePublicUrl(siteRequest_, SitePage.staticSetSitePublicUrl(siteRequest_, o)).toString();
	}

	////////////////////
	// mailingListUrl //
	////////////////////


	/**	 The entity mailingListUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String mailingListUrl;

	/**	<br> The entity mailingListUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:mailingListUrl">Find the entity mailingListUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _mailingListUrl(Wrap<String> w);

	public String getMailingListUrl() {
		return mailingListUrl;
	}
	public void setMailingListUrl(String o) {
		this.mailingListUrl = SitePage.staticSetMailingListUrl(siteRequest_, o);
	}
	public static String staticSetMailingListUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage mailingListUrlInit() {
		Wrap<String> mailingListUrlWrap = new Wrap<String>().var("mailingListUrl");
		if(mailingListUrl == null) {
			_mailingListUrl(mailingListUrlWrap);
			Optional.ofNullable(mailingListUrlWrap.getO()).ifPresent(o -> {
				setMailingListUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchMailingListUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrMailingListUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqMailingListUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchMailingListUrl(siteRequest_, SitePage.staticSetMailingListUrl(siteRequest_, o)).toString();
	}

	///////////////
	// quayioOrg //
	///////////////


	/**	 The entity quayioOrg
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String quayioOrg;

	/**	<br> The entity quayioOrg
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:quayioOrg">Find the entity quayioOrg in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _quayioOrg(Wrap<String> w);

	public String getQuayioOrg() {
		return quayioOrg;
	}
	public void setQuayioOrg(String o) {
		this.quayioOrg = SitePage.staticSetQuayioOrg(siteRequest_, o);
	}
	public static String staticSetQuayioOrg(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage quayioOrgInit() {
		Wrap<String> quayioOrgWrap = new Wrap<String>().var("quayioOrg");
		if(quayioOrg == null) {
			_quayioOrg(quayioOrgWrap);
			Optional.ofNullable(quayioOrgWrap.getO()).ifPresent(o -> {
				setQuayioOrg(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchQuayioOrg(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrQuayioOrg(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqQuayioOrg(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchQuayioOrg(siteRequest_, SitePage.staticSetQuayioOrg(siteRequest_, o)).toString();
	}

	////////////////////
	// sitePomGroupId //
	////////////////////


	/**	 The entity sitePomGroupId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sitePomGroupId;

	/**	<br> The entity sitePomGroupId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:sitePomGroupId">Find the entity sitePomGroupId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sitePomGroupId(Wrap<String> w);

	public String getSitePomGroupId() {
		return sitePomGroupId;
	}
	public void setSitePomGroupId(String o) {
		this.sitePomGroupId = SitePage.staticSetSitePomGroupId(siteRequest_, o);
	}
	public static String staticSetSitePomGroupId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage sitePomGroupIdInit() {
		Wrap<String> sitePomGroupIdWrap = new Wrap<String>().var("sitePomGroupId");
		if(sitePomGroupId == null) {
			_sitePomGroupId(sitePomGroupIdWrap);
			Optional.ofNullable(sitePomGroupIdWrap.getO()).ifPresent(o -> {
				setSitePomGroupId(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSitePomGroupId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSitePomGroupId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSitePomGroupId(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSitePomGroupId(siteRequest_, SitePage.staticSetSitePomGroupId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:staticBaseUrl">Find the entity staticBaseUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _staticBaseUrl(Wrap<String> w);

	public String getStaticBaseUrl() {
		return staticBaseUrl;
	}
	public void setStaticBaseUrl(String o) {
		this.staticBaseUrl = SitePage.staticSetStaticBaseUrl(siteRequest_, o);
	}
	public static String staticSetStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage staticBaseUrlInit() {
		Wrap<String> staticBaseUrlWrap = new Wrap<String>().var("staticBaseUrl");
		if(staticBaseUrl == null) {
			_staticBaseUrl(staticBaseUrlWrap);
			Optional.ofNullable(staticBaseUrlWrap.getO()).ifPresent(o -> {
				setStaticBaseUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchStaticBaseUrl(siteRequest_, SitePage.staticSetStaticBaseUrl(siteRequest_, o)).toString();
	}

	////////////////
	// staticPath //
	////////////////


	/**	 The entity staticPath
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String staticPath;

	/**	<br> The entity staticPath
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:staticPath">Find the entity staticPath in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _staticPath(Wrap<String> w);

	public String getStaticPath() {
		return staticPath;
	}
	public void setStaticPath(String o) {
		this.staticPath = SitePage.staticSetStaticPath(siteRequest_, o);
	}
	public static String staticSetStaticPath(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage staticPathInit() {
		Wrap<String> staticPathWrap = new Wrap<String>().var("staticPath");
		if(staticPath == null) {
			_staticPath(staticPathWrap);
			Optional.ofNullable(staticPathWrap.getO()).ifPresent(o -> {
				setStaticPath(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchStaticPath(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStaticPath(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStaticPath(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchStaticPath(siteRequest_, SitePage.staticSetStaticPath(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:siteBaseUrl">Find the entity siteBaseUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteBaseUrl(Wrap<String> w);

	public String getSiteBaseUrl() {
		return siteBaseUrl;
	}
	public void setSiteBaseUrl(String o) {
		this.siteBaseUrl = SitePage.staticSetSiteBaseUrl(siteRequest_, o);
	}
	public static String staticSetSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage siteBaseUrlInit() {
		Wrap<String> siteBaseUrlWrap = new Wrap<String>().var("siteBaseUrl");
		if(siteBaseUrl == null) {
			_siteBaseUrl(siteBaseUrlWrap);
			Optional.ofNullable(siteBaseUrlWrap.getO()).ifPresent(o -> {
				setSiteBaseUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSiteBaseUrl(siteRequest_, SitePage.staticSetSiteBaseUrl(siteRequest_, o)).toString();
	}

	///////////////
	// courseNum //
	///////////////


	/**	 The entity courseNum
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer courseNum;

	/**	<br> The entity courseNum
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:courseNum">Find the entity courseNum in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _courseNum(Wrap<Integer> w);

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}
	@JsonIgnore
	public void setCourseNum(String o) {
		this.courseNum = SitePage.staticSetCourseNum(siteRequest_, o);
	}
	public static Integer staticSetCourseNum(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePage courseNumInit() {
		Wrap<Integer> courseNumWrap = new Wrap<Integer>().var("courseNum");
		if(courseNum == null) {
			_courseNum(courseNumWrap);
			Optional.ofNullable(courseNumWrap.getO()).ifPresent(o -> {
				setCourseNum(o);
			});
		}
		return (SitePage)this;
	}

	public static Integer staticSearchCourseNum(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCourseNum(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCourseNum(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchCourseNum(siteRequest_, SitePage.staticSetCourseNum(siteRequest_, o)).toString();
	}

	public Integer sqlCourseNum() {
		return courseNum;
	}

	///////////////
	// lessonNum //
	///////////////


	/**	 The entity lessonNum
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer lessonNum;

	/**	<br> The entity lessonNum
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:lessonNum">Find the entity lessonNum in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _lessonNum(Wrap<Integer> w);

	public Integer getLessonNum() {
		return lessonNum;
	}

	public void setLessonNum(Integer lessonNum) {
		this.lessonNum = lessonNum;
	}
	@JsonIgnore
	public void setLessonNum(String o) {
		this.lessonNum = SitePage.staticSetLessonNum(siteRequest_, o);
	}
	public static Integer staticSetLessonNum(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePage lessonNumInit() {
		Wrap<Integer> lessonNumWrap = new Wrap<Integer>().var("lessonNum");
		if(lessonNum == null) {
			_lessonNum(lessonNumWrap);
			Optional.ofNullable(lessonNumWrap.getO()).ifPresent(o -> {
				setLessonNum(o);
			});
		}
		return (SitePage)this;
	}

	public static Integer staticSearchLessonNum(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrLessonNum(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLessonNum(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchLessonNum(siteRequest_, SitePage.staticSetLessonNum(siteRequest_, o)).toString();
	}

	public Integer sqlLessonNum() {
		return lessonNum;
	}

	//////////
	// name //
	//////////


	/**	 The entity name
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String name;

	/**	<br> The entity name
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = SitePage.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchName(siteRequest_, SitePage.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
	}

	////////////
	// author //
	////////////


	/**	 The entity author
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String author;

	/**	<br> The entity author
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:author">Find the entity author in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _author(Wrap<String> w);

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String o) {
		this.author = SitePage.staticSetAuthor(siteRequest_, o);
	}
	public static String staticSetAuthor(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage authorInit() {
		Wrap<String> authorWrap = new Wrap<String>().var("author");
		if(author == null) {
			_author(authorWrap);
			Optional.ofNullable(authorWrap.getO()).ifPresent(o -> {
				setAuthor(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchAuthor(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAuthor(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAuthor(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchAuthor(siteRequest_, SitePage.staticSetAuthor(siteRequest_, o)).toString();
	}

	public String sqlAuthor() {
		return author;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = SitePage.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageId(siteRequest_, SitePage.staticSetPageId(siteRequest_, o)).toString();
	}

	public String sqlPageId() {
		return pageId;
	}

	////////
	// h1 //
	////////


	/**	 The entity h1
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String h1;

	/**	<br> The entity h1
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:h1">Find the entity h1 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _h1(Wrap<String> w);

	public String getH1() {
		return h1;
	}
	public void setH1(String o) {
		this.h1 = SitePage.staticSetH1(siteRequest_, o);
	}
	public static String staticSetH1(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage h1Init() {
		Wrap<String> h1Wrap = new Wrap<String>().var("h1");
		if(h1 == null) {
			_h1(h1Wrap);
			Optional.ofNullable(h1Wrap.getO()).ifPresent(o -> {
				setH1(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchH1(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrH1(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqH1(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchH1(siteRequest_, SitePage.staticSetH1(siteRequest_, o)).toString();
	}

	public String sqlH1() {
		return h1;
	}

	////////
	// h2 //
	////////


	/**	 The entity h2
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String h2;

	/**	<br> The entity h2
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:h2">Find the entity h2 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _h2(Wrap<String> w);

	public String getH2() {
		return h2;
	}
	public void setH2(String o) {
		this.h2 = SitePage.staticSetH2(siteRequest_, o);
	}
	public static String staticSetH2(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage h2Init() {
		Wrap<String> h2Wrap = new Wrap<String>().var("h2");
		if(h2 == null) {
			_h2(h2Wrap);
			Optional.ofNullable(h2Wrap.getO()).ifPresent(o -> {
				setH2(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchH2(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrH2(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqH2(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchH2(siteRequest_, SitePage.staticSetH2(siteRequest_, o)).toString();
	}

	public String sqlH2() {
		return h2;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:pageImageUri">Find the entity pageImageUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageUri(Wrap<String> w);

	public String getPageImageUri() {
		return pageImageUri;
	}
	public void setPageImageUri(String o) {
		this.pageImageUri = SitePage.staticSetPageImageUri(siteRequest_, o);
	}
	public static String staticSetPageImageUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage pageImageUriInit() {
		Wrap<String> pageImageUriWrap = new Wrap<String>().var("pageImageUri");
		if(pageImageUri == null) {
			_pageImageUri(pageImageUriWrap);
			Optional.ofNullable(pageImageUriWrap.getO()).ifPresent(o -> {
				setPageImageUri(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchPageImageUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageImageUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageUri(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageImageUri(siteRequest_, SitePage.staticSetPageImageUri(siteRequest_, o)).toString();
	}

	public String sqlPageImageUri() {
		return pageImageUri;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:pageImageWidth">Find the entity pageImageWidth in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageWidth(Wrap<Integer> w);

	public Integer getPageImageWidth() {
		return pageImageWidth;
	}

	public void setPageImageWidth(Integer pageImageWidth) {
		this.pageImageWidth = pageImageWidth;
	}
	@JsonIgnore
	public void setPageImageWidth(String o) {
		this.pageImageWidth = SitePage.staticSetPageImageWidth(siteRequest_, o);
	}
	public static Integer staticSetPageImageWidth(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePage pageImageWidthInit() {
		Wrap<Integer> pageImageWidthWrap = new Wrap<Integer>().var("pageImageWidth");
		if(pageImageWidth == null) {
			_pageImageWidth(pageImageWidthWrap);
			Optional.ofNullable(pageImageWidthWrap.getO()).ifPresent(o -> {
				setPageImageWidth(o);
			});
		}
		return (SitePage)this;
	}

	public static Integer staticSearchPageImageWidth(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrPageImageWidth(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageWidth(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageImageWidth(siteRequest_, SitePage.staticSetPageImageWidth(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:pageImageHeight">Find the entity pageImageHeight in Solr</a>
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
		this.pageImageHeight = SitePage.staticSetPageImageHeight(siteRequest_, o);
	}
	public static Integer staticSetPageImageHeight(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePage pageImageHeightInit() {
		Wrap<Integer> pageImageHeightWrap = new Wrap<Integer>().var("pageImageHeight");
		if(pageImageHeight == null) {
			_pageImageHeight(pageImageHeightWrap);
			Optional.ofNullable(pageImageHeightWrap.getO()).ifPresent(o -> {
				setPageImageHeight(o);
			});
		}
		return (SitePage)this;
	}

	public static Integer staticSearchPageImageHeight(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrPageImageHeight(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageHeight(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageImageHeight(siteRequest_, SitePage.staticSetPageImageHeight(siteRequest_, o)).toString();
	}

	///////////////////
	// pageImageType //
	///////////////////


	/**	 The entity pageImageType
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageImageType;

	/**	<br> The entity pageImageType
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.page.SitePage&fq=entiteVar_enUS_indexed_string:pageImageType">Find the entity pageImageType in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageType(Wrap<String> c);

	public String getPageImageType() {
		return pageImageType;
	}
	public void setPageImageType(String o) {
		this.pageImageType = SitePage.staticSetPageImageType(siteRequest_, o);
	}
	public static String staticSetPageImageType(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage pageImageTypeInit() {
		Wrap<String> pageImageTypeWrap = new Wrap<String>().var("pageImageType");
		if(pageImageType == null) {
			_pageImageType(pageImageTypeWrap);
			Optional.ofNullable(pageImageTypeWrap.getO()).ifPresent(o -> {
				setPageImageType(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchPageImageType(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageImageType(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageType(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageImageType(siteRequest_, SitePage.staticSetPageImageType(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<SitePageGen<DEV>> promiseDeepSitePage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepSitePage();
	}

	public Future<SitePageGen<DEV>> promiseDeepSitePage() {
		Promise<SitePageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseSitePage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseResult(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseSitePage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				articleInit();
				githubOrgInit();
				siteNameInit();
				siteDisplayNameInit();
				sitePublicUrlInit();
				mailingListUrlInit();
				quayioOrgInit();
				sitePomGroupIdInit();
				staticBaseUrlInit();
				staticPathInit();
				siteBaseUrlInit();
				courseNumInit();
				lessonNumInit();
				nameInit();
				authorInit();
				pageIdInit();
				h1Init();
				h2Init();
				pageImageUriInit();
				pageImageWidthInit();
				pageImageHeightInit();
				pageImageTypeInit();
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

	@Override public Future<? extends SitePageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepSitePage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSitePage(SiteRequest siteRequest_) {
			super.siteRequestBaseResult(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestSitePage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSitePage(v);
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
	public Object obtainSitePage(String var) {
		SitePage oSitePage = (SitePage)this;
		switch(var) {
			case "article":
				return oSitePage.article;
			case "githubOrg":
				return oSitePage.githubOrg;
			case "siteName":
				return oSitePage.siteName;
			case "siteDisplayName":
				return oSitePage.siteDisplayName;
			case "sitePublicUrl":
				return oSitePage.sitePublicUrl;
			case "mailingListUrl":
				return oSitePage.mailingListUrl;
			case "quayioOrg":
				return oSitePage.quayioOrg;
			case "sitePomGroupId":
				return oSitePage.sitePomGroupId;
			case "staticBaseUrl":
				return oSitePage.staticBaseUrl;
			case "staticPath":
				return oSitePage.staticPath;
			case "siteBaseUrl":
				return oSitePage.siteBaseUrl;
			case "courseNum":
				return oSitePage.courseNum;
			case "lessonNum":
				return oSitePage.lessonNum;
			case "name":
				return oSitePage.name;
			case "author":
				return oSitePage.author;
			case "pageId":
				return oSitePage.pageId;
			case "h1":
				return oSitePage.h1;
			case "h2":
				return oSitePage.h2;
			case "pageImageUri":
				return oSitePage.pageImageUri;
			case "pageImageWidth":
				return oSitePage.pageImageWidth;
			case "pageImageHeight":
				return oSitePage.pageImageHeight;
			case "pageImageType":
				return oSitePage.pageImageType;
			default:
				return super.obtainBaseResult(var);
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
				o = relateSitePage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateSitePage(String var, Object val) {
		SitePage oSitePage = (SitePage)this;
		switch(var) {
			default:
				return super.relateBaseResult(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SitePage o) {
		return staticSetSitePage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetSitePage(String entityVar, SiteRequest siteRequest_, String v, SitePage o) {
		switch(entityVar) {
		case "article":
			return SitePage.staticSetArticle(siteRequest_, v);
		case "githubOrg":
			return SitePage.staticSetGithubOrg(siteRequest_, v);
		case "siteName":
			return SitePage.staticSetSiteName(siteRequest_, v);
		case "siteDisplayName":
			return SitePage.staticSetSiteDisplayName(siteRequest_, v);
		case "sitePublicUrl":
			return SitePage.staticSetSitePublicUrl(siteRequest_, v);
		case "mailingListUrl":
			return SitePage.staticSetMailingListUrl(siteRequest_, v);
		case "quayioOrg":
			return SitePage.staticSetQuayioOrg(siteRequest_, v);
		case "sitePomGroupId":
			return SitePage.staticSetSitePomGroupId(siteRequest_, v);
		case "staticBaseUrl":
			return SitePage.staticSetStaticBaseUrl(siteRequest_, v);
		case "staticPath":
			return SitePage.staticSetStaticPath(siteRequest_, v);
		case "siteBaseUrl":
			return SitePage.staticSetSiteBaseUrl(siteRequest_, v);
		case "courseNum":
			return SitePage.staticSetCourseNum(siteRequest_, v);
		case "lessonNum":
			return SitePage.staticSetLessonNum(siteRequest_, v);
		case "name":
			return SitePage.staticSetName(siteRequest_, v);
		case "author":
			return SitePage.staticSetAuthor(siteRequest_, v);
		case "pageId":
			return SitePage.staticSetPageId(siteRequest_, v);
		case "h1":
			return SitePage.staticSetH1(siteRequest_, v);
		case "h2":
			return SitePage.staticSetH2(siteRequest_, v);
		case "pageImageUri":
			return SitePage.staticSetPageImageUri(siteRequest_, v);
		case "pageImageWidth":
			return SitePage.staticSetPageImageWidth(siteRequest_, v);
		case "pageImageHeight":
			return SitePage.staticSetPageImageHeight(siteRequest_, v);
		case "pageImageType":
			return SitePage.staticSetPageImageType(siteRequest_, v);
			default:
				return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchSitePage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchSitePage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "article":
			return SitePage.staticSearchArticle(siteRequest_, (Boolean)o);
		case "githubOrg":
			return SitePage.staticSearchGithubOrg(siteRequest_, (String)o);
		case "siteName":
			return SitePage.staticSearchSiteName(siteRequest_, (String)o);
		case "siteDisplayName":
			return SitePage.staticSearchSiteDisplayName(siteRequest_, (String)o);
		case "sitePublicUrl":
			return SitePage.staticSearchSitePublicUrl(siteRequest_, (String)o);
		case "mailingListUrl":
			return SitePage.staticSearchMailingListUrl(siteRequest_, (String)o);
		case "quayioOrg":
			return SitePage.staticSearchQuayioOrg(siteRequest_, (String)o);
		case "sitePomGroupId":
			return SitePage.staticSearchSitePomGroupId(siteRequest_, (String)o);
		case "staticBaseUrl":
			return SitePage.staticSearchStaticBaseUrl(siteRequest_, (String)o);
		case "staticPath":
			return SitePage.staticSearchStaticPath(siteRequest_, (String)o);
		case "siteBaseUrl":
			return SitePage.staticSearchSiteBaseUrl(siteRequest_, (String)o);
		case "courseNum":
			return SitePage.staticSearchCourseNum(siteRequest_, (Integer)o);
		case "lessonNum":
			return SitePage.staticSearchLessonNum(siteRequest_, (Integer)o);
		case "name":
			return SitePage.staticSearchName(siteRequest_, (String)o);
		case "author":
			return SitePage.staticSearchAuthor(siteRequest_, (String)o);
		case "pageId":
			return SitePage.staticSearchPageId(siteRequest_, (String)o);
		case "h1":
			return SitePage.staticSearchH1(siteRequest_, (String)o);
		case "h2":
			return SitePage.staticSearchH2(siteRequest_, (String)o);
		case "pageImageUri":
			return SitePage.staticSearchPageImageUri(siteRequest_, (String)o);
		case "pageImageWidth":
			return SitePage.staticSearchPageImageWidth(siteRequest_, (Integer)o);
		case "pageImageHeight":
			return SitePage.staticSearchPageImageHeight(siteRequest_, (Integer)o);
		case "pageImageType":
			return SitePage.staticSearchPageImageType(siteRequest_, (String)o);
			default:
				return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrSitePage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrSitePage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "article":
			return SitePage.staticSearchStrArticle(siteRequest_, (Boolean)o);
		case "githubOrg":
			return SitePage.staticSearchStrGithubOrg(siteRequest_, (String)o);
		case "siteName":
			return SitePage.staticSearchStrSiteName(siteRequest_, (String)o);
		case "siteDisplayName":
			return SitePage.staticSearchStrSiteDisplayName(siteRequest_, (String)o);
		case "sitePublicUrl":
			return SitePage.staticSearchStrSitePublicUrl(siteRequest_, (String)o);
		case "mailingListUrl":
			return SitePage.staticSearchStrMailingListUrl(siteRequest_, (String)o);
		case "quayioOrg":
			return SitePage.staticSearchStrQuayioOrg(siteRequest_, (String)o);
		case "sitePomGroupId":
			return SitePage.staticSearchStrSitePomGroupId(siteRequest_, (String)o);
		case "staticBaseUrl":
			return SitePage.staticSearchStrStaticBaseUrl(siteRequest_, (String)o);
		case "staticPath":
			return SitePage.staticSearchStrStaticPath(siteRequest_, (String)o);
		case "siteBaseUrl":
			return SitePage.staticSearchStrSiteBaseUrl(siteRequest_, (String)o);
		case "courseNum":
			return SitePage.staticSearchStrCourseNum(siteRequest_, (Integer)o);
		case "lessonNum":
			return SitePage.staticSearchStrLessonNum(siteRequest_, (Integer)o);
		case "name":
			return SitePage.staticSearchStrName(siteRequest_, (String)o);
		case "author":
			return SitePage.staticSearchStrAuthor(siteRequest_, (String)o);
		case "pageId":
			return SitePage.staticSearchStrPageId(siteRequest_, (String)o);
		case "h1":
			return SitePage.staticSearchStrH1(siteRequest_, (String)o);
		case "h2":
			return SitePage.staticSearchStrH2(siteRequest_, (String)o);
		case "pageImageUri":
			return SitePage.staticSearchStrPageImageUri(siteRequest_, (String)o);
		case "pageImageWidth":
			return SitePage.staticSearchStrPageImageWidth(siteRequest_, (Integer)o);
		case "pageImageHeight":
			return SitePage.staticSearchStrPageImageHeight(siteRequest_, (Integer)o);
		case "pageImageType":
			return SitePage.staticSearchStrPageImageType(siteRequest_, (String)o);
			default:
				return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqSitePage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqSitePage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "article":
			return SitePage.staticSearchFqArticle(siteRequest_, o);
		case "githubOrg":
			return SitePage.staticSearchFqGithubOrg(siteRequest_, o);
		case "siteName":
			return SitePage.staticSearchFqSiteName(siteRequest_, o);
		case "siteDisplayName":
			return SitePage.staticSearchFqSiteDisplayName(siteRequest_, o);
		case "sitePublicUrl":
			return SitePage.staticSearchFqSitePublicUrl(siteRequest_, o);
		case "mailingListUrl":
			return SitePage.staticSearchFqMailingListUrl(siteRequest_, o);
		case "quayioOrg":
			return SitePage.staticSearchFqQuayioOrg(siteRequest_, o);
		case "sitePomGroupId":
			return SitePage.staticSearchFqSitePomGroupId(siteRequest_, o);
		case "staticBaseUrl":
			return SitePage.staticSearchFqStaticBaseUrl(siteRequest_, o);
		case "staticPath":
			return SitePage.staticSearchFqStaticPath(siteRequest_, o);
		case "siteBaseUrl":
			return SitePage.staticSearchFqSiteBaseUrl(siteRequest_, o);
		case "courseNum":
			return SitePage.staticSearchFqCourseNum(siteRequest_, o);
		case "lessonNum":
			return SitePage.staticSearchFqLessonNum(siteRequest_, o);
		case "name":
			return SitePage.staticSearchFqName(siteRequest_, o);
		case "author":
			return SitePage.staticSearchFqAuthor(siteRequest_, o);
		case "pageId":
			return SitePage.staticSearchFqPageId(siteRequest_, o);
		case "h1":
			return SitePage.staticSearchFqH1(siteRequest_, o);
		case "h2":
			return SitePage.staticSearchFqH2(siteRequest_, o);
		case "pageImageUri":
			return SitePage.staticSearchFqPageImageUri(siteRequest_, o);
		case "pageImageWidth":
			return SitePage.staticSearchFqPageImageWidth(siteRequest_, o);
		case "pageImageHeight":
			return SitePage.staticSearchFqPageImageHeight(siteRequest_, o);
		case "pageImageType":
			return SitePage.staticSearchFqPageImageType(siteRequest_, o);
			default:
				return BaseResult.staticSearchFqBaseResult(entityVar,  siteRequest_, o);
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
					o = persistSitePage(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistSitePage(String var, Object val) {
		String varLower = var.toLowerCase();
			if("coursenum".equals(varLower)) {
				if(val instanceof Integer) {
					setCourseNum((Integer)val);
				} else {
					setCourseNum(val == null ? null : val.toString());
				}
				saves.add("courseNum");
				return val;
			} else if("lessonnum".equals(varLower)) {
				if(val instanceof Integer) {
					setLessonNum((Integer)val);
				} else {
					setLessonNum(val == null ? null : val.toString());
				}
				saves.add("lessonNum");
				return val;
			} else if("name".equals(varLower)) {
				if(val instanceof String) {
					setName((String)val);
				}
				saves.add("name");
				return val;
			} else if("author".equals(varLower)) {
				if(val instanceof String) {
					setAuthor((String)val);
				}
				saves.add("author");
				return val;
			} else if("pageid".equals(varLower)) {
				if(val instanceof String) {
					setPageId((String)val);
				}
				saves.add("pageId");
				return val;
			} else if("h1".equals(varLower)) {
				if(val instanceof String) {
					setH1((String)val);
				}
				saves.add("h1");
				return val;
			} else if("h2".equals(varLower)) {
				if(val instanceof String) {
					setH2((String)val);
				}
				saves.add("h2");
				return val;
			} else if("pageimageuri".equals(varLower)) {
				if(val instanceof String) {
					setPageImageUri((String)val);
				}
				saves.add("pageImageUri");
				return val;
		} else {
			return super.persistBaseResult(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateSitePage(doc);
	}
	public void populateSitePage(SolrResponse.Doc doc) {
		SitePage oSitePage = (SitePage)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("courseNum")) {
				Integer courseNum = (Integer)doc.get("courseNum_docvalues_int");
				if(courseNum != null)
					oSitePage.setCourseNum(courseNum);
			}

			if(saves.contains("lessonNum")) {
				Integer lessonNum = (Integer)doc.get("lessonNum_docvalues_int");
				if(lessonNum != null)
					oSitePage.setLessonNum(lessonNum);
			}

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oSitePage.setName(name);
			}

			if(saves.contains("author")) {
				String author = (String)doc.get("author_docvalues_string");
				if(author != null)
					oSitePage.setAuthor(author);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oSitePage.setPageId(pageId);
			}

			if(saves.contains("h1")) {
				String h1 = (String)doc.get("h1_docvalues_string");
				if(h1 != null)
					oSitePage.setH1(h1);
			}

			if(saves.contains("h2")) {
				String h2 = (String)doc.get("h2_docvalues_string");
				if(h2 != null)
					oSitePage.setH2(h2);
			}

			if(saves.contains("pageImageUri")) {
				String pageImageUri = (String)doc.get("pageImageUri_docvalues_string");
				if(pageImageUri != null)
					oSitePage.setPageImageUri(pageImageUri);
			}
		}

		super.populateBaseResult(doc);
	}

	public void indexSitePage(JsonObject doc) {
		if(courseNum != null) {
			doc.put("courseNum_docvalues_int", courseNum);
		}
		if(lessonNum != null) {
			doc.put("lessonNum_docvalues_int", lessonNum);
		}
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(author != null) {
			doc.put("author_docvalues_string", author);
		}
		if(pageId != null) {
			doc.put("pageId_docvalues_string", pageId);
		}
		if(h1 != null) {
			doc.put("h1_docvalues_string", h1);
		}
		if(h2 != null) {
			doc.put("h2_docvalues_string", h2);
		}
		if(pageImageUri != null) {
			doc.put("pageImageUri_docvalues_string", pageImageUri);
		}
		super.indexBaseResult(doc);

	}

	public static String varStoredSitePage(String entityVar) {
		switch(entityVar) {
			case "courseNum":
				return "courseNum_docvalues_int";
			case "lessonNum":
				return "lessonNum_docvalues_int";
			case "name":
				return "name_docvalues_string";
			case "author":
				return "author_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "h1":
				return "h1_docvalues_string";
			case "h2":
				return "h2_docvalues_string";
			case "pageImageUri":
				return "pageImageUri_docvalues_string";
			default:
				return BaseResult.varStoredBaseResult(entityVar);
		}
	}

	public static String varIndexedSitePage(String entityVar) {
		switch(entityVar) {
			case "courseNum":
				return "courseNum_docvalues_int";
			case "lessonNum":
				return "lessonNum_docvalues_int";
			case "name":
				return "name_docvalues_string";
			case "author":
				return "author_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "h1":
				return "h1_docvalues_string";
			case "h2":
				return "h2_docvalues_string";
			case "pageImageUri":
				return "pageImageUri_docvalues_string";
			default:
				return BaseResult.varIndexedBaseResult(entityVar);
		}
	}

	public static String searchVarSitePage(String searchVar) {
		switch(searchVar) {
			case "courseNum_docvalues_int":
				return "courseNum";
			case "lessonNum_docvalues_int":
				return "lessonNum";
			case "name_docvalues_string":
				return "name";
			case "author_docvalues_string":
				return "author";
			case "pageId_docvalues_string":
				return "pageId";
			case "h1_docvalues_string":
				return "h1";
			case "h2_docvalues_string":
				return "h2";
			case "pageImageUri_docvalues_string":
				return "pageImageUri";
			default:
				return BaseResult.searchVarBaseResult(searchVar);
		}
	}

	public static String varSearchSitePage(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSearchBaseResult(entityVar);
		}
	}

	public static String varSuggestedSitePage(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSuggestedBaseResult(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeSitePage(doc);
	}
	public void storeSitePage(SolrResponse.Doc doc) {
		SitePage oSitePage = (SitePage)this;
		SiteRequest siteRequest = oSitePage.getSiteRequest_();

		oSitePage.setCourseNum(Optional.ofNullable(doc.get("courseNum_docvalues_int")).map(v -> v.toString()).orElse(null));
		oSitePage.setLessonNum(Optional.ofNullable(doc.get("lessonNum_docvalues_int")).map(v -> v.toString()).orElse(null));
		oSitePage.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setAuthor(Optional.ofNullable(doc.get("author_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setH1(Optional.ofNullable(doc.get("h1_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setH2(Optional.ofNullable(doc.get("h2_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setPageImageUri(Optional.ofNullable(doc.get("pageImageUri_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseResult(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSitePage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SitePage) {
			SitePage original = (SitePage)o;
			if(!Objects.equals(courseNum, original.getCourseNum()))
				apiRequest.addVars("courseNum");
			if(!Objects.equals(lessonNum, original.getLessonNum()))
				apiRequest.addVars("lessonNum");
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(author, original.getAuthor()))
				apiRequest.addVars("author");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			if(!Objects.equals(h1, original.getH1()))
				apiRequest.addVars("h1");
			if(!Objects.equals(h2, original.getH2()))
				apiRequest.addVars("h2");
			if(!Objects.equals(pageImageUri, original.getPageImageUri()))
				apiRequest.addVars("pageImageUri");
			super.apiRequestBaseResult();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(courseNum).map(v -> "courseNum: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(lessonNum).map(v -> "lessonNum: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(author).map(v -> "author: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(h1).map(v -> "h1: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(h2).map(v -> "h2: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(pageImageUri).map(v -> "pageImageUri: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "SitePage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.page.SitePage";
	public static final String CLASS_AUTH_RESOURCE = "SITEPAGE";
	public static final String CLASS_API_ADDRESS_SitePage = "ai-telemetry-enUS-SitePage";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_SitePage;
	}
	public static final String VAR_article = "article";
	public static final String VAR_githubOrg = "githubOrg";
	public static final String VAR_siteName = "siteName";
	public static final String VAR_siteDisplayName = "siteDisplayName";
	public static final String VAR_sitePublicUrl = "sitePublicUrl";
	public static final String VAR_mailingListUrl = "mailingListUrl";
	public static final String VAR_quayioOrg = "quayioOrg";
	public static final String VAR_sitePomGroupId = "sitePomGroupId";
	public static final String VAR_staticBaseUrl = "staticBaseUrl";
	public static final String VAR_staticPath = "staticPath";
	public static final String VAR_siteBaseUrl = "siteBaseUrl";
	public static final String VAR_courseNum = "courseNum";
	public static final String VAR_lessonNum = "lessonNum";
	public static final String VAR_name = "name";
	public static final String VAR_author = "author";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_h1 = "h1";
	public static final String VAR_h2 = "h2";
	public static final String VAR_pageImageUri = "pageImageUri";
	public static final String VAR_pageImageWidth = "pageImageWidth";
	public static final String VAR_pageImageHeight = "pageImageHeight";
	public static final String VAR_pageImageType = "pageImageType";

	public static List<String> varsQForClass() {
		return SitePage.varsQSitePage(new ArrayList<String>());
	}
	public static List<String> varsQSitePage(List<String> vars) {
		BaseResult.varsQBaseResult(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return SitePage.varsFqSitePage(new ArrayList<String>());
	}
	public static List<String> varsFqSitePage(List<String> vars) {
		vars.add(VAR_courseNum);
		vars.add(VAR_lessonNum);
		vars.add(VAR_author);
		vars.add(VAR_pageId);
		vars.add(VAR_pageImageUri);
		BaseResult.varsFqBaseResult(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return SitePage.varsRangeSitePage(new ArrayList<String>());
	}
	public static List<String> varsRangeSitePage(List<String> vars) {
		vars.add(VAR_courseNum);
		vars.add(VAR_lessonNum);
		BaseResult.varsRangeBaseResult(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_article = "";
	public static final String DISPLAY_NAME_githubOrg = "";
	public static final String DISPLAY_NAME_siteName = "";
	public static final String DISPLAY_NAME_siteDisplayName = "";
	public static final String DISPLAY_NAME_sitePublicUrl = "";
	public static final String DISPLAY_NAME_mailingListUrl = "";
	public static final String DISPLAY_NAME_quayioOrg = "";
	public static final String DISPLAY_NAME_sitePomGroupId = "";
	public static final String DISPLAY_NAME_staticBaseUrl = "";
	public static final String DISPLAY_NAME_staticPath = "";
	public static final String DISPLAY_NAME_siteBaseUrl = "";
	public static final String DISPLAY_NAME_courseNum = "Course Number";
	public static final String DISPLAY_NAME_lessonNum = "Lesson Number";
	public static final String DISPLAY_NAME_name = "title";
	public static final String DISPLAY_NAME_author = "author";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_h1 = "header 1";
	public static final String DISPLAY_NAME_h2 = "header 2";
	public static final String DISPLAY_NAME_pageImageUri = "imageUri";
	public static final String DISPLAY_NAME_pageImageWidth = "";
	public static final String DISPLAY_NAME_pageImageHeight = "";
	public static final String DISPLAY_NAME_pageImageType = "";

	@Override
	public String idForClass() {
		return pageId;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return name;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return SitePage.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/article/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return "%s/en-us/view/article/%s";
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
		return SitePage.displayNameSitePage(var);
	}
	public static String displayNameSitePage(String var) {
		switch(var) {
		case VAR_article:
			return DISPLAY_NAME_article;
		case VAR_githubOrg:
			return DISPLAY_NAME_githubOrg;
		case VAR_siteName:
			return DISPLAY_NAME_siteName;
		case VAR_siteDisplayName:
			return DISPLAY_NAME_siteDisplayName;
		case VAR_sitePublicUrl:
			return DISPLAY_NAME_sitePublicUrl;
		case VAR_mailingListUrl:
			return DISPLAY_NAME_mailingListUrl;
		case VAR_quayioOrg:
			return DISPLAY_NAME_quayioOrg;
		case VAR_sitePomGroupId:
			return DISPLAY_NAME_sitePomGroupId;
		case VAR_staticBaseUrl:
			return DISPLAY_NAME_staticBaseUrl;
		case VAR_staticPath:
			return DISPLAY_NAME_staticPath;
		case VAR_siteBaseUrl:
			return DISPLAY_NAME_siteBaseUrl;
		case VAR_courseNum:
			return DISPLAY_NAME_courseNum;
		case VAR_lessonNum:
			return DISPLAY_NAME_lessonNum;
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_author:
			return DISPLAY_NAME_author;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_h1:
			return DISPLAY_NAME_h1;
		case VAR_h2:
			return DISPLAY_NAME_h2;
		case VAR_pageImageUri:
			return DISPLAY_NAME_pageImageUri;
		case VAR_pageImageWidth:
			return DISPLAY_NAME_pageImageWidth;
		case VAR_pageImageHeight:
			return DISPLAY_NAME_pageImageHeight;
		case VAR_pageImageType:
			return DISPLAY_NAME_pageImageType;
		default:
			return BaseResult.displayNameBaseResult(var);
		}
	}

	public static String descriptionSitePage(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_courseNum:
			return "The course number for this page. ";
		case VAR_lessonNum:
			return "The lesson number for this page. ";
		case VAR_name:
			return "The name of this page. ";
		case VAR_author:
			return "The author";
		case VAR_pageId:
			return "The ID for this page. ";
		case VAR_h1:
			return "The 1st header of this page. ";
		case VAR_h2:
			return "The 2nd header of this page. ";
		case VAR_pageImageUri:
			return "The page image URI";
		case VAR_pageImageWidth:
			return "The image width";
		case VAR_pageImageHeight:
			return "The image height";
		case VAR_pageImageType:
			return "The image height";
			default:
				return BaseResult.descriptionBaseResult(var);
		}
	}

	public static String classSimpleNameSitePage(String var) {
		switch(var) {
		case VAR_article:
			return "Boolean";
		case VAR_githubOrg:
			return "String";
		case VAR_siteName:
			return "String";
		case VAR_siteDisplayName:
			return "String";
		case VAR_sitePublicUrl:
			return "String";
		case VAR_mailingListUrl:
			return "String";
		case VAR_quayioOrg:
			return "String";
		case VAR_sitePomGroupId:
			return "String";
		case VAR_staticBaseUrl:
			return "String";
		case VAR_staticPath:
			return "String";
		case VAR_siteBaseUrl:
			return "String";
		case VAR_courseNum:
			return "Integer";
		case VAR_lessonNum:
			return "Integer";
		case VAR_name:
			return "String";
		case VAR_author:
			return "String";
		case VAR_pageId:
			return "String";
		case VAR_h1:
			return "String";
		case VAR_h2:
			return "String";
		case VAR_pageImageUri:
			return "String";
		case VAR_pageImageWidth:
			return "Integer";
		case VAR_pageImageHeight:
			return "Integer";
		case VAR_pageImageType:
			return "String";
			default:
				return BaseResult.classSimpleNameBaseResult(var);
		}
	}

	public static Integer htmColumnSitePage(String var) {
		switch(var) {
		case VAR_name:
			return 1;
			default:
				return BaseResult.htmColumnBaseResult(var);
		}
	}

	public static Integer htmRowSitePage(String var) {
		switch(var) {
		case VAR_author:
			return 3;
		case VAR_pageId:
			return 99;
		case VAR_pageImageUri:
			return 4;
			default:
				return BaseResult.htmRowBaseResult(var);
		}
	}

	public static Integer htmCellSitePage(String var) {
		switch(var) {
		case VAR_author:
			return 3;
		case VAR_pageId:
			return 1;
		case VAR_pageImageUri:
			return 1;
			default:
				return BaseResult.htmCellBaseResult(var);
		}
	}

	public static Integer lengthMinSitePage(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMinBaseResult(var);
		}
	}

	public static Integer lengthMaxSitePage(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMaxBaseResult(var);
		}
	}

	public static Integer maxSitePage(String var) {
		switch(var) {
			default:
				return BaseResult.maxBaseResult(var);
		}
	}

	public static Integer minSitePage(String var) {
		switch(var) {
			default:
				return BaseResult.minBaseResult(var);
		}
	}
}
