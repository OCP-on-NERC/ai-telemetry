package org.mghpcc.aitelemetry.model.baremetalorder;

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
import org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork;
import org.computate.vertx.search.list.SearchList;
import java.lang.Integer;
import java.lang.Boolean;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalOrderGen into the class BareMetalOrder. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the BareMetalOrder API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the BareMetalOrder class and it's generated class BareMetalOrderGen&lt;BaseModel&gt;: </h3>extends BareMetalOrderGen
 * <p>
 * This Java class extends a generated Java class BareMetalOrderGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder">Find the class BareMetalOrder in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalOrderGen<BaseModel>
 * <p>This <code>class BareMetalOrder extends BareMetalOrderGen&lt;BaseModel&gt;</code>, which means it extends a newly generated BareMetalOrderGen. 
 * The generated <code>class BareMetalOrderGen extends BaseModel</code> which means that BareMetalOrder extends BareMetalOrderGen which extends BaseModel. 
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
 * <h2>ApiMethode: DELETE</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETE"</b>, which creates an API "DELETE". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiMethode: EditPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: EditPage"</b>, which creates an API "EditPage". 
 * </p>
 * <h2>ApiMethode: UserPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: UserPage"</b>, which creates an API "UserPage". 
 * </p>
 * <h2>ApiMethode: DELETEFilter</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETEFilter"</b>, which creates an API "DELETEFilter". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: bare metal orders"</b>, which groups all of the OpenAPIs for BareMetalOrder objects under the tag "bare metal orders". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/bare-metal-order</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/bare-metal-order"</b>, which defines the base API URI for BareMetalOrder objects as "/en-us/api/bare-metal-order" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalOrder class will inherit the helpful inherited class comments from the super class BareMetalOrderGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 16</h2>
 * <p>This class contains a comment <b>"Order: 16"</b>, which means this class will be sorted by the given number 16 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 16</h2>
 * <p>This class contains a comment <b>"SqlOrder: 16"</b>, which means this class will be sorted by the given number 16 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrderPage extends org.mghpcc.aitelemetry.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalOrder Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a bare metal order</h2>
 * <p>This class contains a comment <b>"AName.enUS: a bare metal order"</b>, which identifies the language context to describe a BareMetalOrder as "a bare metal order". 
 * </p>
 * <p>
 * Delete the class BareMetalOrder in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.baremetalorder in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BareMetalOrderGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalOrder.class);

	public static final String Description_enUS = "A bare metal order";
	public static final String AName_enUS = "a bare metal order";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this bare metal order";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the bare metal order";
	public static final String SingularName_enUS = "bare metal order";
	public static final String PluralName_enUS = "bare metal orders";
	public static final String NameActual_enUS = "current bare metal order";
	public static final String AllName_enUS = "all bare metal orders";
	public static final String SearchAllNameBy_enUS = "search bare metal orders by ";
	public static final String SearchAllName_enUS = "search bare metal orders";
	public static final String Title_enUS = "bare metal orders";
	public static final String ThePluralName_enUS = "the bare metal orders";
	public static final String NoNameFound_enUS = "no bare metal order found";
	public static final String ApiUri_enUS = "/en-us/api/bare-metal-order";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/bare-metal-order";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/bare-metal-order/{pk}";
	public static final String OfName_enUS = "of bare metal order";
	public static final String ANameAdjective_enUS = "a bare metal order";
	public static final String NameAdjectiveSingular_enUS = "bare metal order";
	public static final String NameAdjectivePlural_enUS = "bare metal orders";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/bare-metal-order";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/bare-metal-order";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/bare-metal-order/{pk}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/bare-metal-order/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/bare-metal-order";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/bare-metal-order";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/bare-metal-order";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/bare-metal-order";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/bare-metal-order/{pk}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/bare-metal-order/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order/%s";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/bare-metal-order";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/bare-metal-order";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/bare-metal-order";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/bare-metal-order/{pk}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/bare-metal-order/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/bare-metal-order/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/user/bare-metal-order/{pk}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/user/bare-metal-order/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/bare-metal-order/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/bare-metal-order";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/bare-metal-order";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/bare-metal-order";

	public static final String Icon = "<i class=\"fa-regular fa-share-nodes\"></i>";

	/////////////////
	// description //
	/////////////////


	/**	 The entity description
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String description;

	/**	<br> The entity description
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = BareMetalOrder.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrder descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchDescription(siteRequest_, BareMetalOrder.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	///////////////
	// networkId //
	///////////////


	/**	 The entity networkId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String networkId;

	/**	<br> The entity networkId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:networkId">Find the entity networkId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _networkId(Wrap<String> w);

	public String getNetworkId() {
		return networkId;
	}
	public void setNetworkId(String o) {
		this.networkId = BareMetalOrder.staticSetNetworkId(siteRequest_, o);
	}
	public static String staticSetNetworkId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrder networkIdInit() {
		Wrap<String> networkIdWrap = new Wrap<String>().var("networkId");
		if(networkId == null) {
			_networkId(networkIdWrap);
			Optional.ofNullable(networkIdWrap.getO()).ifPresent(o -> {
				setNetworkId(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static String staticSearchNetworkId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNetworkId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNetworkId(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchNetworkId(siteRequest_, BareMetalOrder.staticSetNetworkId(siteRequest_, o)).toString();
	}

	public String sqlNetworkId() {
		return networkId;
	}

	///////////////////
	// networkSearch //
	///////////////////


	/**	 The entity networkSearch
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<BareMetalNetwork> networkSearch;

	/**	<br> The entity networkSearch
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:networkSearch">Find the entity networkSearch in Solr</a>
	 * <br>
	 * @param promise is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _networkSearch(Promise<SearchList<BareMetalNetwork>> promise);

	public SearchList<BareMetalNetwork> getNetworkSearch() {
		return networkSearch;
	}

	public void setNetworkSearch(SearchList<BareMetalNetwork> networkSearch) {
		this.networkSearch = networkSearch;
	}
	public static SearchList<BareMetalNetwork> staticSetNetworkSearch(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected Future<SearchList<BareMetalNetwork>> networkSearchPromise() {
		Promise<SearchList<BareMetalNetwork>> promise = Promise.promise();
		Promise<SearchList<BareMetalNetwork>> promise2 = Promise.promise();
		_networkSearch(promise2);
		promise2.future().onSuccess(o -> {
			if(o != null && networkSearch == null) {
				o.promiseDeepForClass(siteRequest_).onSuccess(a -> {
					setNetworkSearch(o);
					promise.complete(o);
				}).onFailure(ex -> {
					promise.fail(ex);
				});
			} else {
				promise.complete(o);
			}
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	/////////////
	// network //
	/////////////


	/**	 The entity network
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected BareMetalNetwork network;

	/**	<br> The entity network
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:network">Find the entity network in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _network(Wrap<BareMetalNetwork> w);

	public BareMetalNetwork getNetwork() {
		return network;
	}

	public void setNetwork(BareMetalNetwork network) {
		this.network = network;
	}
	public static BareMetalNetwork staticSetNetwork(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BareMetalOrder networkInit() {
		Wrap<BareMetalNetwork> networkWrap = new Wrap<BareMetalNetwork>().var("network");
		if(network == null) {
			_network(networkWrap);
			Optional.ofNullable(networkWrap.getO()).ifPresent(o -> {
				setNetwork(o);
			});
		}
		return (BareMetalOrder)this;
	}

	/////////////////
	// networkName //
	/////////////////


	/**	 The entity networkName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String networkName;

	/**	<br> The entity networkName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:networkName">Find the entity networkName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _networkName(Wrap<String> w);

	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String o) {
		this.networkName = BareMetalOrder.staticSetNetworkName(siteRequest_, o);
	}
	public static String staticSetNetworkName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrder networkNameInit() {
		Wrap<String> networkNameWrap = new Wrap<String>().var("networkName");
		if(networkName == null) {
			_networkName(networkNameWrap);
			Optional.ofNullable(networkNameWrap.getO()).ifPresent(o -> {
				setNetworkName(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static String staticSearchNetworkName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNetworkName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNetworkName(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchNetworkName(siteRequest_, BareMetalOrder.staticSetNetworkName(siteRequest_, o)).toString();
	}

	///////////////////
	// numberOfFc430 //
	///////////////////


	/**	 The entity numberOfFc430
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer numberOfFc430;

	/**	<br> The entity numberOfFc430
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:numberOfFc430">Find the entity numberOfFc430 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _numberOfFc430(Wrap<Integer> w);

	public Integer getNumberOfFc430() {
		return numberOfFc430;
	}

	public void setNumberOfFc430(Integer numberOfFc430) {
		this.numberOfFc430 = numberOfFc430;
	}
	@JsonIgnore
	public void setNumberOfFc430(String o) {
		this.numberOfFc430 = BareMetalOrder.staticSetNumberOfFc430(siteRequest_, o);
	}
	public static Integer staticSetNumberOfFc430(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalOrder numberOfFc430Init() {
		Wrap<Integer> numberOfFc430Wrap = new Wrap<Integer>().var("numberOfFc430");
		if(numberOfFc430 == null) {
			_numberOfFc430(numberOfFc430Wrap);
			Optional.ofNullable(numberOfFc430Wrap.getO()).ifPresent(o -> {
				setNumberOfFc430(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static Integer staticSearchNumberOfFc430(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrNumberOfFc430(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNumberOfFc430(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchNumberOfFc430(siteRequest_, BareMetalOrder.staticSetNumberOfFc430(siteRequest_, o)).toString();
	}

	public Integer sqlNumberOfFc430() {
		return numberOfFc430;
	}

	///////////////////
	// numberOfFc830 //
	///////////////////


	/**	 The entity numberOfFc830
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer numberOfFc830;

	/**	<br> The entity numberOfFc830
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:numberOfFc830">Find the entity numberOfFc830 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _numberOfFc830(Wrap<Integer> w);

	public Integer getNumberOfFc830() {
		return numberOfFc830;
	}

	public void setNumberOfFc830(Integer numberOfFc830) {
		this.numberOfFc830 = numberOfFc830;
	}
	@JsonIgnore
	public void setNumberOfFc830(String o) {
		this.numberOfFc830 = BareMetalOrder.staticSetNumberOfFc830(siteRequest_, o);
	}
	public static Integer staticSetNumberOfFc830(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalOrder numberOfFc830Init() {
		Wrap<Integer> numberOfFc830Wrap = new Wrap<Integer>().var("numberOfFc830");
		if(numberOfFc830 == null) {
			_numberOfFc830(numberOfFc830Wrap);
			Optional.ofNullable(numberOfFc830Wrap.getO()).ifPresent(o -> {
				setNumberOfFc830(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static Integer staticSearchNumberOfFc830(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrNumberOfFc830(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNumberOfFc830(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchNumberOfFc830(siteRequest_, BareMetalOrder.staticSetNumberOfFc830(siteRequest_, o)).toString();
	}

	public Integer sqlNumberOfFc830() {
		return numberOfFc830;
	}

	////////////////////
	// numberOfR730xd //
	////////////////////


	/**	 The entity numberOfR730xd
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer numberOfR730xd;

	/**	<br> The entity numberOfR730xd
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:numberOfR730xd">Find the entity numberOfR730xd in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _numberOfR730xd(Wrap<Integer> w);

	public Integer getNumberOfR730xd() {
		return numberOfR730xd;
	}

	public void setNumberOfR730xd(Integer numberOfR730xd) {
		this.numberOfR730xd = numberOfR730xd;
	}
	@JsonIgnore
	public void setNumberOfR730xd(String o) {
		this.numberOfR730xd = BareMetalOrder.staticSetNumberOfR730xd(siteRequest_, o);
	}
	public static Integer staticSetNumberOfR730xd(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalOrder numberOfR730xdInit() {
		Wrap<Integer> numberOfR730xdWrap = new Wrap<Integer>().var("numberOfR730xd");
		if(numberOfR730xd == null) {
			_numberOfR730xd(numberOfR730xdWrap);
			Optional.ofNullable(numberOfR730xdWrap.getO()).ifPresent(o -> {
				setNumberOfR730xd(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static Integer staticSearchNumberOfR730xd(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrNumberOfR730xd(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNumberOfR730xd(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchNumberOfR730xd(siteRequest_, BareMetalOrder.staticSetNumberOfR730xd(siteRequest_, o)).toString();
	}

	public Integer sqlNumberOfR730xd() {
		return numberOfR730xd;
	}

	///////////////////////////
	// numberOfWhiteboxFlax1 //
	///////////////////////////


	/**	 The entity numberOfWhiteboxFlax1
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer numberOfWhiteboxFlax1;

	/**	<br> The entity numberOfWhiteboxFlax1
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:numberOfWhiteboxFlax1">Find the entity numberOfWhiteboxFlax1 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _numberOfWhiteboxFlax1(Wrap<Integer> w);

	public Integer getNumberOfWhiteboxFlax1() {
		return numberOfWhiteboxFlax1;
	}

	public void setNumberOfWhiteboxFlax1(Integer numberOfWhiteboxFlax1) {
		this.numberOfWhiteboxFlax1 = numberOfWhiteboxFlax1;
	}
	@JsonIgnore
	public void setNumberOfWhiteboxFlax1(String o) {
		this.numberOfWhiteboxFlax1 = BareMetalOrder.staticSetNumberOfWhiteboxFlax1(siteRequest_, o);
	}
	public static Integer staticSetNumberOfWhiteboxFlax1(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalOrder numberOfWhiteboxFlax1Init() {
		Wrap<Integer> numberOfWhiteboxFlax1Wrap = new Wrap<Integer>().var("numberOfWhiteboxFlax1");
		if(numberOfWhiteboxFlax1 == null) {
			_numberOfWhiteboxFlax1(numberOfWhiteboxFlax1Wrap);
			Optional.ofNullable(numberOfWhiteboxFlax1Wrap.getO()).ifPresent(o -> {
				setNumberOfWhiteboxFlax1(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static Integer staticSearchNumberOfWhiteboxFlax1(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrNumberOfWhiteboxFlax1(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNumberOfWhiteboxFlax1(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchNumberOfWhiteboxFlax1(siteRequest_, BareMetalOrder.staticSetNumberOfWhiteboxFlax1(siteRequest_, o)).toString();
	}

	public Integer sqlNumberOfWhiteboxFlax1() {
		return numberOfWhiteboxFlax1;
	}

	////////////////////////////////
	// numberOfLenovoSd650nv2A100 //
	////////////////////////////////


	/**	 The entity numberOfLenovoSd650nv2A100
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer numberOfLenovoSd650nv2A100;

	/**	<br> The entity numberOfLenovoSd650nv2A100
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:numberOfLenovoSd650nv2A100">Find the entity numberOfLenovoSd650nv2A100 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _numberOfLenovoSd650nv2A100(Wrap<Integer> w);

	public Integer getNumberOfLenovoSd650nv2A100() {
		return numberOfLenovoSd650nv2A100;
	}

	public void setNumberOfLenovoSd650nv2A100(Integer numberOfLenovoSd650nv2A100) {
		this.numberOfLenovoSd650nv2A100 = numberOfLenovoSd650nv2A100;
	}
	@JsonIgnore
	public void setNumberOfLenovoSd650nv2A100(String o) {
		this.numberOfLenovoSd650nv2A100 = BareMetalOrder.staticSetNumberOfLenovoSd650nv2A100(siteRequest_, o);
	}
	public static Integer staticSetNumberOfLenovoSd650nv2A100(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalOrder numberOfLenovoSd650nv2A100Init() {
		Wrap<Integer> numberOfLenovoSd650nv2A100Wrap = new Wrap<Integer>().var("numberOfLenovoSd650nv2A100");
		if(numberOfLenovoSd650nv2A100 == null) {
			_numberOfLenovoSd650nv2A100(numberOfLenovoSd650nv2A100Wrap);
			Optional.ofNullable(numberOfLenovoSd650nv2A100Wrap.getO()).ifPresent(o -> {
				setNumberOfLenovoSd650nv2A100(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static Integer staticSearchNumberOfLenovoSd650nv2A100(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrNumberOfLenovoSd650nv2A100(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNumberOfLenovoSd650nv2A100(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchNumberOfLenovoSd650nv2A100(siteRequest_, BareMetalOrder.staticSetNumberOfLenovoSd650nv2A100(siteRequest_, o)).toString();
	}

	public Integer sqlNumberOfLenovoSd650nv2A100() {
		return numberOfLenovoSd650nv2A100;
	}

	////////////////////////////////
	// numberOfLenovoSd665nv3H100 //
	////////////////////////////////


	/**	 The entity numberOfLenovoSd665nv3H100
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer numberOfLenovoSd665nv3H100;

	/**	<br> The entity numberOfLenovoSd665nv3H100
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:numberOfLenovoSd665nv3H100">Find the entity numberOfLenovoSd665nv3H100 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _numberOfLenovoSd665nv3H100(Wrap<Integer> w);

	public Integer getNumberOfLenovoSd665nv3H100() {
		return numberOfLenovoSd665nv3H100;
	}

	public void setNumberOfLenovoSd665nv3H100(Integer numberOfLenovoSd665nv3H100) {
		this.numberOfLenovoSd665nv3H100 = numberOfLenovoSd665nv3H100;
	}
	@JsonIgnore
	public void setNumberOfLenovoSd665nv3H100(String o) {
		this.numberOfLenovoSd665nv3H100 = BareMetalOrder.staticSetNumberOfLenovoSd665nv3H100(siteRequest_, o);
	}
	public static Integer staticSetNumberOfLenovoSd665nv3H100(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BareMetalOrder numberOfLenovoSd665nv3H100Init() {
		Wrap<Integer> numberOfLenovoSd665nv3H100Wrap = new Wrap<Integer>().var("numberOfLenovoSd665nv3H100");
		if(numberOfLenovoSd665nv3H100 == null) {
			_numberOfLenovoSd665nv3H100(numberOfLenovoSd665nv3H100Wrap);
			Optional.ofNullable(numberOfLenovoSd665nv3H100Wrap.getO()).ifPresent(o -> {
				setNumberOfLenovoSd665nv3H100(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static Integer staticSearchNumberOfLenovoSd665nv3H100(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrNumberOfLenovoSd665nv3H100(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNumberOfLenovoSd665nv3H100(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchNumberOfLenovoSd665nv3H100(siteRequest_, BareMetalOrder.staticSetNumberOfLenovoSd665nv3H100(siteRequest_, o)).toString();
	}

	public Integer sqlNumberOfLenovoSd665nv3H100() {
		return numberOfLenovoSd665nv3H100;
	}

	///////////
	// image //
	///////////


	/**	 The entity image
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String image;

	/**	<br> The entity image
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:image">Find the entity image in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _image(Wrap<String> w);

	public String getImage() {
		return image;
	}
	public void setImage(String o) {
		this.image = BareMetalOrder.staticSetImage(siteRequest_, o);
	}
	public static String staticSetImage(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrder imageInit() {
		Wrap<String> imageWrap = new Wrap<String>().var("image");
		if(image == null) {
			_image(imageWrap);
			Optional.ofNullable(imageWrap.getO()).ifPresent(o -> {
				setImage(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static String staticSearchImage(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrImage(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqImage(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchImage(siteRequest_, BareMetalOrder.staticSetImage(siteRequest_, o)).toString();
	}

	public String sqlImage() {
		return image;
	}

	//////////////////
	// sshPublicKey //
	//////////////////


	/**	 The entity sshPublicKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sshPublicKey;

	/**	<br> The entity sshPublicKey
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:sshPublicKey">Find the entity sshPublicKey in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sshPublicKey(Wrap<String> w);

	public String getSshPublicKey() {
		return sshPublicKey;
	}
	public void setSshPublicKey(String o) {
		this.sshPublicKey = BareMetalOrder.staticSetSshPublicKey(siteRequest_, o);
	}
	public static String staticSetSshPublicKey(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrder sshPublicKeyInit() {
		Wrap<String> sshPublicKeyWrap = new Wrap<String>().var("sshPublicKey");
		if(sshPublicKey == null) {
			_sshPublicKey(sshPublicKeyWrap);
			Optional.ofNullable(sshPublicKeyWrap.getO()).ifPresent(o -> {
				setSshPublicKey(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static String staticSearchSshPublicKey(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSshPublicKey(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSshPublicKey(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchSshPublicKey(siteRequest_, BareMetalOrder.staticSetSshPublicKey(siteRequest_, o)).toString();
	}

	public String sqlSshPublicKey() {
		return sshPublicKey;
	}

	////////////////
	// floatingIp //
	////////////////


	/**	 The entity floatingIp
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean floatingIp;

	/**	<br> The entity floatingIp
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:floatingIp">Find the entity floatingIp in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _floatingIp(Wrap<Boolean> w);

	public Boolean getFloatingIp() {
		return floatingIp;
	}

	public void setFloatingIp(Boolean floatingIp) {
		this.floatingIp = floatingIp;
	}
	@JsonIgnore
	public void setFloatingIp(String o) {
		this.floatingIp = BareMetalOrder.staticSetFloatingIp(siteRequest_, o);
	}
	public static Boolean staticSetFloatingIp(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected BareMetalOrder floatingIpInit() {
		Wrap<Boolean> floatingIpWrap = new Wrap<Boolean>().var("floatingIp");
		if(floatingIp == null) {
			setFloatingIp("false");
		}
		return (BareMetalOrder)this;
	}

	public static Boolean staticSearchFloatingIp(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrFloatingIp(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqFloatingIp(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchFloatingIp(siteRequest_, BareMetalOrder.staticSetFloatingIp(siteRequest_, o)).toString();
	}

	public Boolean sqlFloatingIp() {
		return floatingIp;
	}

	////////////
	// status //
	////////////


	/**	 The entity status
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String status;

	/**	<br> The entity status
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder&fq=entiteVar_enUS_indexed_string:status">Find the entity status in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _status(Wrap<String> w);

	public String getStatus() {
		return status;
	}
	public void setStatus(String o) {
		this.status = BareMetalOrder.staticSetStatus(siteRequest_, o);
	}
	public static String staticSetStatus(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BareMetalOrder statusInit() {
		Wrap<String> statusWrap = new Wrap<String>().var("status");
		if(status == null) {
			_status(statusWrap);
			Optional.ofNullable(statusWrap.getO()).ifPresent(o -> {
				setStatus(o);
			});
		}
		return (BareMetalOrder)this;
	}

	public static String staticSearchStatus(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStatus(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStatus(SiteRequest siteRequest_, String o) {
		return BareMetalOrder.staticSearchStatus(siteRequest_, BareMetalOrder.staticSetStatus(siteRequest_, o)).toString();
	}

	public String sqlStatus() {
		return status;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalOrderGen<DEV>> promiseDeepBareMetalOrder(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalOrder();
	}

	public Future<BareMetalOrderGen<DEV>> promiseDeepBareMetalOrder() {
		Promise<BareMetalOrderGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalOrder(promise2);
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

	public Future<Void> promiseBareMetalOrder(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				descriptionInit();
				networkIdInit();
				promise2.complete();
			} catch(Exception ex) {
				promise2.fail(ex);
			}
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			networkSearchPromise().onSuccess(networkSearch -> {
				promise2.complete();
			}).onFailure(ex -> {
				promise2.fail(ex);
			});
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				networkInit();
				networkNameInit();
				numberOfFc430Init();
				numberOfFc830Init();
				numberOfR730xdInit();
				numberOfWhiteboxFlax1Init();
				numberOfLenovoSd650nv2A100Init();
				numberOfLenovoSd665nv3H100Init();
				imageInit();
				sshPublicKeyInit();
				floatingIpInit();
				statusInit();
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

	@Override public Future<? extends BareMetalOrderGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalOrder(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalOrder(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
		if(networkSearch != null)
			networkSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalOrder(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalOrder(v);
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
	public Object obtainBareMetalOrder(String var) {
		BareMetalOrder oBareMetalOrder = (BareMetalOrder)this;
		switch(var) {
			case "description":
				return oBareMetalOrder.description;
			case "networkId":
				return oBareMetalOrder.networkId;
			case "networkSearch":
				return oBareMetalOrder.networkSearch;
			case "network":
				return oBareMetalOrder.network;
			case "networkName":
				return oBareMetalOrder.networkName;
			case "numberOfFc430":
				return oBareMetalOrder.numberOfFc430;
			case "numberOfFc830":
				return oBareMetalOrder.numberOfFc830;
			case "numberOfR730xd":
				return oBareMetalOrder.numberOfR730xd;
			case "numberOfWhiteboxFlax1":
				return oBareMetalOrder.numberOfWhiteboxFlax1;
			case "numberOfLenovoSd650nv2A100":
				return oBareMetalOrder.numberOfLenovoSd650nv2A100;
			case "numberOfLenovoSd665nv3H100":
				return oBareMetalOrder.numberOfLenovoSd665nv3H100;
			case "image":
				return oBareMetalOrder.image;
			case "sshPublicKey":
				return oBareMetalOrder.sshPublicKey;
			case "floatingIp":
				return oBareMetalOrder.floatingIp;
			case "status":
				return oBareMetalOrder.status;
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
				o = relateBareMetalOrder(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalOrder(String var, Object val) {
		BareMetalOrder oBareMetalOrder = (BareMetalOrder)this;
		switch(var) {
			case "networkId":
				if(oBareMetalOrder.getNetworkId() == null)
					oBareMetalOrder.setNetworkId(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
				if(!saves.contains("networkId"))
					saves.add("networkId");
				return val;
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, BareMetalOrder o) {
		return staticSetBareMetalOrder(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetBareMetalOrder(String entityVar, SiteRequest siteRequest_, String v, BareMetalOrder o) {
		switch(entityVar) {
		case "description":
			return BareMetalOrder.staticSetDescription(siteRequest_, v);
		case "networkId":
			return BareMetalOrder.staticSetNetworkId(siteRequest_, v);
		case "networkName":
			return BareMetalOrder.staticSetNetworkName(siteRequest_, v);
		case "numberOfFc430":
			return BareMetalOrder.staticSetNumberOfFc430(siteRequest_, v);
		case "numberOfFc830":
			return BareMetalOrder.staticSetNumberOfFc830(siteRequest_, v);
		case "numberOfR730xd":
			return BareMetalOrder.staticSetNumberOfR730xd(siteRequest_, v);
		case "numberOfWhiteboxFlax1":
			return BareMetalOrder.staticSetNumberOfWhiteboxFlax1(siteRequest_, v);
		case "numberOfLenovoSd650nv2A100":
			return BareMetalOrder.staticSetNumberOfLenovoSd650nv2A100(siteRequest_, v);
		case "numberOfLenovoSd665nv3H100":
			return BareMetalOrder.staticSetNumberOfLenovoSd665nv3H100(siteRequest_, v);
		case "image":
			return BareMetalOrder.staticSetImage(siteRequest_, v);
		case "sshPublicKey":
			return BareMetalOrder.staticSetSshPublicKey(siteRequest_, v);
		case "floatingIp":
			return BareMetalOrder.staticSetFloatingIp(siteRequest_, v);
		case "status":
			return BareMetalOrder.staticSetStatus(siteRequest_, v);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalOrder(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalOrder(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "description":
			return BareMetalOrder.staticSearchDescription(siteRequest_, (String)o);
		case "networkId":
			return BareMetalOrder.staticSearchNetworkId(siteRequest_, (String)o);
		case "networkName":
			return BareMetalOrder.staticSearchNetworkName(siteRequest_, (String)o);
		case "numberOfFc430":
			return BareMetalOrder.staticSearchNumberOfFc430(siteRequest_, (Integer)o);
		case "numberOfFc830":
			return BareMetalOrder.staticSearchNumberOfFc830(siteRequest_, (Integer)o);
		case "numberOfR730xd":
			return BareMetalOrder.staticSearchNumberOfR730xd(siteRequest_, (Integer)o);
		case "numberOfWhiteboxFlax1":
			return BareMetalOrder.staticSearchNumberOfWhiteboxFlax1(siteRequest_, (Integer)o);
		case "numberOfLenovoSd650nv2A100":
			return BareMetalOrder.staticSearchNumberOfLenovoSd650nv2A100(siteRequest_, (Integer)o);
		case "numberOfLenovoSd665nv3H100":
			return BareMetalOrder.staticSearchNumberOfLenovoSd665nv3H100(siteRequest_, (Integer)o);
		case "image":
			return BareMetalOrder.staticSearchImage(siteRequest_, (String)o);
		case "sshPublicKey":
			return BareMetalOrder.staticSearchSshPublicKey(siteRequest_, (String)o);
		case "floatingIp":
			return BareMetalOrder.staticSearchFloatingIp(siteRequest_, (Boolean)o);
		case "status":
			return BareMetalOrder.staticSearchStatus(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalOrder(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalOrder(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "description":
			return BareMetalOrder.staticSearchStrDescription(siteRequest_, (String)o);
		case "networkId":
			return BareMetalOrder.staticSearchStrNetworkId(siteRequest_, (String)o);
		case "networkName":
			return BareMetalOrder.staticSearchStrNetworkName(siteRequest_, (String)o);
		case "numberOfFc430":
			return BareMetalOrder.staticSearchStrNumberOfFc430(siteRequest_, (Integer)o);
		case "numberOfFc830":
			return BareMetalOrder.staticSearchStrNumberOfFc830(siteRequest_, (Integer)o);
		case "numberOfR730xd":
			return BareMetalOrder.staticSearchStrNumberOfR730xd(siteRequest_, (Integer)o);
		case "numberOfWhiteboxFlax1":
			return BareMetalOrder.staticSearchStrNumberOfWhiteboxFlax1(siteRequest_, (Integer)o);
		case "numberOfLenovoSd650nv2A100":
			return BareMetalOrder.staticSearchStrNumberOfLenovoSd650nv2A100(siteRequest_, (Integer)o);
		case "numberOfLenovoSd665nv3H100":
			return BareMetalOrder.staticSearchStrNumberOfLenovoSd665nv3H100(siteRequest_, (Integer)o);
		case "image":
			return BareMetalOrder.staticSearchStrImage(siteRequest_, (String)o);
		case "sshPublicKey":
			return BareMetalOrder.staticSearchStrSshPublicKey(siteRequest_, (String)o);
		case "floatingIp":
			return BareMetalOrder.staticSearchStrFloatingIp(siteRequest_, (Boolean)o);
		case "status":
			return BareMetalOrder.staticSearchStrStatus(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalOrder(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalOrder(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "description":
			return BareMetalOrder.staticSearchFqDescription(siteRequest_, o);
		case "networkId":
			return BareMetalOrder.staticSearchFqNetworkId(siteRequest_, o);
		case "networkName":
			return BareMetalOrder.staticSearchFqNetworkName(siteRequest_, o);
		case "numberOfFc430":
			return BareMetalOrder.staticSearchFqNumberOfFc430(siteRequest_, o);
		case "numberOfFc830":
			return BareMetalOrder.staticSearchFqNumberOfFc830(siteRequest_, o);
		case "numberOfR730xd":
			return BareMetalOrder.staticSearchFqNumberOfR730xd(siteRequest_, o);
		case "numberOfWhiteboxFlax1":
			return BareMetalOrder.staticSearchFqNumberOfWhiteboxFlax1(siteRequest_, o);
		case "numberOfLenovoSd650nv2A100":
			return BareMetalOrder.staticSearchFqNumberOfLenovoSd650nv2A100(siteRequest_, o);
		case "numberOfLenovoSd665nv3H100":
			return BareMetalOrder.staticSearchFqNumberOfLenovoSd665nv3H100(siteRequest_, o);
		case "image":
			return BareMetalOrder.staticSearchFqImage(siteRequest_, o);
		case "sshPublicKey":
			return BareMetalOrder.staticSearchFqSshPublicKey(siteRequest_, o);
		case "floatingIp":
			return BareMetalOrder.staticSearchFqFloatingIp(siteRequest_, o);
		case "status":
			return BareMetalOrder.staticSearchFqStatus(siteRequest_, o);
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
					o = persistBareMetalOrder(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistBareMetalOrder(String var, Object val) {
		String varLower = var.toLowerCase();
			if("description".equals(varLower)) {
				if(val instanceof String) {
					setDescription((String)val);
				}
				saves.add("description");
				return val;
			} else if("networkid".equals(varLower)) {
				if(val instanceof String) {
					setNetworkId((String)val);
				}
				saves.add("networkId");
				return val;
			} else if("numberoffc430".equals(varLower)) {
				if(val instanceof Integer) {
					setNumberOfFc430((Integer)val);
				} else {
					setNumberOfFc430(val == null ? null : val.toString());
				}
				saves.add("numberOfFc430");
				return val;
			} else if("numberoffc830".equals(varLower)) {
				if(val instanceof Integer) {
					setNumberOfFc830((Integer)val);
				} else {
					setNumberOfFc830(val == null ? null : val.toString());
				}
				saves.add("numberOfFc830");
				return val;
			} else if("numberofr730xd".equals(varLower)) {
				if(val instanceof Integer) {
					setNumberOfR730xd((Integer)val);
				} else {
					setNumberOfR730xd(val == null ? null : val.toString());
				}
				saves.add("numberOfR730xd");
				return val;
			} else if("numberofwhiteboxflax1".equals(varLower)) {
				if(val instanceof Integer) {
					setNumberOfWhiteboxFlax1((Integer)val);
				} else {
					setNumberOfWhiteboxFlax1(val == null ? null : val.toString());
				}
				saves.add("numberOfWhiteboxFlax1");
				return val;
			} else if("numberoflenovosd650nv2a100".equals(varLower)) {
				if(val instanceof Integer) {
					setNumberOfLenovoSd650nv2A100((Integer)val);
				} else {
					setNumberOfLenovoSd650nv2A100(val == null ? null : val.toString());
				}
				saves.add("numberOfLenovoSd650nv2A100");
				return val;
			} else if("numberoflenovosd665nv3h100".equals(varLower)) {
				if(val instanceof Integer) {
					setNumberOfLenovoSd665nv3H100((Integer)val);
				} else {
					setNumberOfLenovoSd665nv3H100(val == null ? null : val.toString());
				}
				saves.add("numberOfLenovoSd665nv3H100");
				return val;
			} else if("image".equals(varLower)) {
				if(val instanceof String) {
					setImage((String)val);
				}
				saves.add("image");
				return val;
			} else if("sshpublickey".equals(varLower)) {
				if(val instanceof String) {
					setSshPublicKey((String)val);
				}
				saves.add("sshPublicKey");
				return val;
			} else if("floatingip".equals(varLower)) {
				if(val instanceof Boolean) {
					setFloatingIp((Boolean)val);
				} else {
					setFloatingIp(val == null ? null : val.toString());
				}
				saves.add("floatingIp");
				return val;
			} else if("status".equals(varLower)) {
				if(val instanceof String) {
					setStatus((String)val);
				}
				saves.add("status");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateBareMetalOrder(doc);
	}
	public void populateBareMetalOrder(SolrResponse.Doc doc) {
		BareMetalOrder oBareMetalOrder = (BareMetalOrder)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oBareMetalOrder.setDescription(description);
			}

			String networkId = (String)doc.get("networkId_docvalues_string");
			if(networkId != null)
				oBareMetalOrder.setNetworkId(networkId);

			if(saves.contains("networkName")) {
				String networkName = (String)doc.get("networkName_docvalues_string");
				if(networkName != null)
					oBareMetalOrder.setNetworkName(networkName);
			}

			if(saves.contains("numberOfFc430")) {
				Integer numberOfFc430 = (Integer)doc.get("numberOfFc430_docvalues_int");
				if(numberOfFc430 != null)
					oBareMetalOrder.setNumberOfFc430(numberOfFc430);
			}

			if(saves.contains("numberOfFc830")) {
				Integer numberOfFc830 = (Integer)doc.get("numberOfFc830_docvalues_int");
				if(numberOfFc830 != null)
					oBareMetalOrder.setNumberOfFc830(numberOfFc830);
			}

			if(saves.contains("numberOfR730xd")) {
				Integer numberOfR730xd = (Integer)doc.get("numberOfR730xd_docvalues_int");
				if(numberOfR730xd != null)
					oBareMetalOrder.setNumberOfR730xd(numberOfR730xd);
			}

			if(saves.contains("numberOfWhiteboxFlax1")) {
				Integer numberOfWhiteboxFlax1 = (Integer)doc.get("numberOfWhiteboxFlax1_docvalues_int");
				if(numberOfWhiteboxFlax1 != null)
					oBareMetalOrder.setNumberOfWhiteboxFlax1(numberOfWhiteboxFlax1);
			}

			if(saves.contains("numberOfLenovoSd650nv2A100")) {
				Integer numberOfLenovoSd650nv2A100 = (Integer)doc.get("numberOfLenovoSd650nv2A100_docvalues_int");
				if(numberOfLenovoSd650nv2A100 != null)
					oBareMetalOrder.setNumberOfLenovoSd650nv2A100(numberOfLenovoSd650nv2A100);
			}

			if(saves.contains("numberOfLenovoSd665nv3H100")) {
				Integer numberOfLenovoSd665nv3H100 = (Integer)doc.get("numberOfLenovoSd665nv3H100_docvalues_int");
				if(numberOfLenovoSd665nv3H100 != null)
					oBareMetalOrder.setNumberOfLenovoSd665nv3H100(numberOfLenovoSd665nv3H100);
			}

			if(saves.contains("image")) {
				String image = (String)doc.get("image_docvalues_string");
				if(image != null)
					oBareMetalOrder.setImage(image);
			}

			if(saves.contains("sshPublicKey")) {
				String sshPublicKey = (String)doc.get("sshPublicKey_docvalues_string");
				if(sshPublicKey != null)
					oBareMetalOrder.setSshPublicKey(sshPublicKey);
			}

			if(saves.contains("floatingIp")) {
				Boolean floatingIp = (Boolean)doc.get("floatingIp_docvalues_boolean");
				if(floatingIp != null)
					oBareMetalOrder.setFloatingIp(floatingIp);
			}

			if(saves.contains("status")) {
				String status = (String)doc.get("status_docvalues_string");
				if(status != null)
					oBareMetalOrder.setStatus(status);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexBareMetalOrder(JsonObject doc) {
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(networkId != null) {
			doc.put("networkId_docvalues_string", networkId);
		}
		if(networkName != null) {
			doc.put("networkName_docvalues_string", networkName);
		}
		if(numberOfFc430 != null) {
			doc.put("numberOfFc430_docvalues_int", numberOfFc430);
		}
		if(numberOfFc830 != null) {
			doc.put("numberOfFc830_docvalues_int", numberOfFc830);
		}
		if(numberOfR730xd != null) {
			doc.put("numberOfR730xd_docvalues_int", numberOfR730xd);
		}
		if(numberOfWhiteboxFlax1 != null) {
			doc.put("numberOfWhiteboxFlax1_docvalues_int", numberOfWhiteboxFlax1);
		}
		if(numberOfLenovoSd650nv2A100 != null) {
			doc.put("numberOfLenovoSd650nv2A100_docvalues_int", numberOfLenovoSd650nv2A100);
		}
		if(numberOfLenovoSd665nv3H100 != null) {
			doc.put("numberOfLenovoSd665nv3H100_docvalues_int", numberOfLenovoSd665nv3H100);
		}
		if(image != null) {
			doc.put("image_docvalues_string", image);
		}
		if(sshPublicKey != null) {
			doc.put("sshPublicKey_docvalues_string", sshPublicKey);
		}
		if(floatingIp != null) {
			doc.put("floatingIp_docvalues_boolean", floatingIp);
		}
		if(status != null) {
			doc.put("status_docvalues_string", status);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredBareMetalOrder(String entityVar) {
		switch(entityVar) {
			case "description":
				return "description_docvalues_string";
			case "networkId":
				return "networkId_docvalues_string";
			case "networkName":
				return "networkName_docvalues_string";
			case "numberOfFc430":
				return "numberOfFc430_docvalues_int";
			case "numberOfFc830":
				return "numberOfFc830_docvalues_int";
			case "numberOfR730xd":
				return "numberOfR730xd_docvalues_int";
			case "numberOfWhiteboxFlax1":
				return "numberOfWhiteboxFlax1_docvalues_int";
			case "numberOfLenovoSd650nv2A100":
				return "numberOfLenovoSd650nv2A100_docvalues_int";
			case "numberOfLenovoSd665nv3H100":
				return "numberOfLenovoSd665nv3H100_docvalues_int";
			case "image":
				return "image_docvalues_string";
			case "sshPublicKey":
				return "sshPublicKey_docvalues_string";
			case "floatingIp":
				return "floatingIp_docvalues_boolean";
			case "status":
				return "status_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedBareMetalOrder(String entityVar) {
		switch(entityVar) {
			case "description":
				return "description_docvalues_string";
			case "networkId":
				return "networkId_docvalues_string";
			case "networkName":
				return "networkName_docvalues_string";
			case "numberOfFc430":
				return "numberOfFc430_docvalues_int";
			case "numberOfFc830":
				return "numberOfFc830_docvalues_int";
			case "numberOfR730xd":
				return "numberOfR730xd_docvalues_int";
			case "numberOfWhiteboxFlax1":
				return "numberOfWhiteboxFlax1_docvalues_int";
			case "numberOfLenovoSd650nv2A100":
				return "numberOfLenovoSd650nv2A100_docvalues_int";
			case "numberOfLenovoSd665nv3H100":
				return "numberOfLenovoSd665nv3H100_docvalues_int";
			case "image":
				return "image_docvalues_string";
			case "sshPublicKey":
				return "sshPublicKey_docvalues_string";
			case "floatingIp":
				return "floatingIp_docvalues_boolean";
			case "status":
				return "status_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarBareMetalOrder(String searchVar) {
		switch(searchVar) {
			case "description_docvalues_string":
				return "description";
			case "networkId_docvalues_string":
				return "networkId";
			case "networkName_docvalues_string":
				return "networkName";
			case "numberOfFc430_docvalues_int":
				return "numberOfFc430";
			case "numberOfFc830_docvalues_int":
				return "numberOfFc830";
			case "numberOfR730xd_docvalues_int":
				return "numberOfR730xd";
			case "numberOfWhiteboxFlax1_docvalues_int":
				return "numberOfWhiteboxFlax1";
			case "numberOfLenovoSd650nv2A100_docvalues_int":
				return "numberOfLenovoSd650nv2A100";
			case "numberOfLenovoSd665nv3H100_docvalues_int":
				return "numberOfLenovoSd665nv3H100";
			case "image_docvalues_string":
				return "image";
			case "sshPublicKey_docvalues_string":
				return "sshPublicKey";
			case "floatingIp_docvalues_boolean":
				return "floatingIp";
			case "status_docvalues_string":
				return "status";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchBareMetalOrder(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedBareMetalOrder(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeBareMetalOrder(doc);
	}
	public void storeBareMetalOrder(SolrResponse.Doc doc) {
		BareMetalOrder oBareMetalOrder = (BareMetalOrder)this;
		SiteRequest siteRequest = oBareMetalOrder.getSiteRequest_();

		oBareMetalOrder.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setNetworkId(Optional.ofNullable(doc.get("networkId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setNetworkName(Optional.ofNullable(doc.get("networkName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setNumberOfFc430(Optional.ofNullable(doc.get("numberOfFc430_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setNumberOfFc830(Optional.ofNullable(doc.get("numberOfFc830_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setNumberOfR730xd(Optional.ofNullable(doc.get("numberOfR730xd_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setNumberOfWhiteboxFlax1(Optional.ofNullable(doc.get("numberOfWhiteboxFlax1_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setNumberOfLenovoSd650nv2A100(Optional.ofNullable(doc.get("numberOfLenovoSd650nv2A100_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setNumberOfLenovoSd665nv3H100(Optional.ofNullable(doc.get("numberOfLenovoSd665nv3H100_docvalues_int")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setImage(Optional.ofNullable(doc.get("image_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setSshPublicKey(Optional.ofNullable(doc.get("sshPublicKey_docvalues_string")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setFloatingIp(Optional.ofNullable(doc.get("floatingIp_docvalues_boolean")).map(v -> v.toString()).orElse(null));
		oBareMetalOrder.setStatus(Optional.ofNullable(doc.get("status_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBareMetalOrder() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BareMetalOrder) {
			BareMetalOrder original = (BareMetalOrder)o;
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(networkId, original.getNetworkId()))
				apiRequest.addVars("networkId");
			if(!Objects.equals(networkName, original.getNetworkName()))
				apiRequest.addVars("networkName");
			if(!Objects.equals(numberOfFc430, original.getNumberOfFc430()))
				apiRequest.addVars("numberOfFc430");
			if(!Objects.equals(numberOfFc830, original.getNumberOfFc830()))
				apiRequest.addVars("numberOfFc830");
			if(!Objects.equals(numberOfR730xd, original.getNumberOfR730xd()))
				apiRequest.addVars("numberOfR730xd");
			if(!Objects.equals(numberOfWhiteboxFlax1, original.getNumberOfWhiteboxFlax1()))
				apiRequest.addVars("numberOfWhiteboxFlax1");
			if(!Objects.equals(numberOfLenovoSd650nv2A100, original.getNumberOfLenovoSd650nv2A100()))
				apiRequest.addVars("numberOfLenovoSd650nv2A100");
			if(!Objects.equals(numberOfLenovoSd665nv3H100, original.getNumberOfLenovoSd665nv3H100()))
				apiRequest.addVars("numberOfLenovoSd665nv3H100");
			if(!Objects.equals(image, original.getImage()))
				apiRequest.addVars("image");
			if(!Objects.equals(sshPublicKey, original.getSshPublicKey()))
				apiRequest.addVars("sshPublicKey");
			if(!Objects.equals(floatingIp, original.getFloatingIp()))
				apiRequest.addVars("floatingIp");
			if(!Objects.equals(status, original.getStatus()))
				apiRequest.addVars("status");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(networkId).map(v -> "networkId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(networkName).map(v -> "networkName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(numberOfFc430).map(v -> "numberOfFc430: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(numberOfFc830).map(v -> "numberOfFc830: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(numberOfR730xd).map(v -> "numberOfR730xd: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(numberOfWhiteboxFlax1).map(v -> "numberOfWhiteboxFlax1: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(numberOfLenovoSd650nv2A100).map(v -> "numberOfLenovoSd650nv2A100: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(numberOfLenovoSd665nv3H100).map(v -> "numberOfLenovoSd665nv3H100: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(image).map(v -> "image: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(sshPublicKey).map(v -> "sshPublicKey: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(floatingIp).map(v -> "floatingIp: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(status).map(v -> "status: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "BareMetalOrder";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalorder.BareMetalOrder";
	public static final String CLASS_AUTH_RESOURCE = "BAREMETALORDER";
	public static final String CLASS_API_ADDRESS_BareMetalOrder = "ai-telemetry-enUS-BareMetalOrder";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_BareMetalOrder;
	}
	public static final String VAR_description = "description";
	public static final String VAR_networkId = "networkId";
	public static final String VAR_networkSearch = "networkSearch";
	public static final String VAR_network = "network";
	public static final String VAR_networkName = "networkName";
	public static final String VAR_numberOfFc430 = "numberOfFc430";
	public static final String VAR_numberOfFc830 = "numberOfFc830";
	public static final String VAR_numberOfR730xd = "numberOfR730xd";
	public static final String VAR_numberOfWhiteboxFlax1 = "numberOfWhiteboxFlax1";
	public static final String VAR_numberOfLenovoSd650nv2A100 = "numberOfLenovoSd650nv2A100";
	public static final String VAR_numberOfLenovoSd665nv3H100 = "numberOfLenovoSd665nv3H100";
	public static final String VAR_image = "image";
	public static final String VAR_sshPublicKey = "sshPublicKey";
	public static final String VAR_floatingIp = "floatingIp";
	public static final String VAR_status = "status";

	public static List<String> varsQForClass() {
		return BareMetalOrder.varsQBareMetalOrder(new ArrayList<String>());
	}
	public static List<String> varsQBareMetalOrder(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return BareMetalOrder.varsFqBareMetalOrder(new ArrayList<String>());
	}
	public static List<String> varsFqBareMetalOrder(List<String> vars) {
		vars.add(VAR_description);
		vars.add(VAR_networkId);
		vars.add(VAR_networkName);
		vars.add(VAR_numberOfFc430);
		vars.add(VAR_numberOfFc830);
		vars.add(VAR_numberOfR730xd);
		vars.add(VAR_numberOfWhiteboxFlax1);
		vars.add(VAR_numberOfLenovoSd650nv2A100);
		vars.add(VAR_numberOfLenovoSd665nv3H100);
		vars.add(VAR_image);
		vars.add(VAR_sshPublicKey);
		vars.add(VAR_floatingIp);
		vars.add(VAR_status);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return BareMetalOrder.varsRangeBareMetalOrder(new ArrayList<String>());
	}
	public static List<String> varsRangeBareMetalOrder(List<String> vars) {
		vars.add(VAR_numberOfFc430);
		vars.add(VAR_numberOfFc830);
		vars.add(VAR_numberOfR730xd);
		vars.add(VAR_numberOfWhiteboxFlax1);
		vars.add(VAR_numberOfLenovoSd650nv2A100);
		vars.add(VAR_numberOfLenovoSd665nv3H100);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_description = "bare metal order description";
	public static final String DISPLAY_NAME_networkId = "bare metal network";
	public static final String DISPLAY_NAME_networkSearch = "";
	public static final String DISPLAY_NAME_network = "";
	public static final String DISPLAY_NAME_networkName = "bare metal network name";
	public static final String DISPLAY_NAME_numberOfFc430 = "number of fc430 ({{ max }} available)";
	public static final String DISPLAY_NAME_numberOfFc830 = "number of fc830 ({{ max }} available)";
	public static final String DISPLAY_NAME_numberOfR730xd = "number of r730xd ({{ max }} available)";
	public static final String DISPLAY_NAME_numberOfWhiteboxFlax1 = "number of whitebox-flax-1 ({{ max }} available)";
	public static final String DISPLAY_NAME_numberOfLenovoSd650nv2A100 = "number of lenovo-sd650nv2-a100 ({{ max }} available)";
	public static final String DISPLAY_NAME_numberOfLenovoSd665nv3H100 = "number of lenovo-sd665nv3-h100 ({{ max }} available)";
	public static final String DISPLAY_NAME_image = "image";
	public static final String DISPLAY_NAME_sshPublicKey = "SSH public key";
	public static final String DISPLAY_NAME_floatingIp = "floating IP";
	public static final String DISPLAY_NAME_status = "order status";

	@Override
	public String idForClass() {
		return Optional.ofNullable(pk).map(o -> o.toString()).orElse(null);
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return Optional.ofNullable(pk).map(o -> o.toString()).orElse(null);
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return BareMetalOrder.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/bare-metal-order/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/user/bare-metal-order/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return BareMetalOrder.displayNameBareMetalOrder(var);
	}
	public static String displayNameBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_networkId:
			return DISPLAY_NAME_networkId;
		case VAR_networkSearch:
			return DISPLAY_NAME_networkSearch;
		case VAR_network:
			return DISPLAY_NAME_network;
		case VAR_networkName:
			return DISPLAY_NAME_networkName;
		case VAR_numberOfFc430:
			return DISPLAY_NAME_numberOfFc430;
		case VAR_numberOfFc830:
			return DISPLAY_NAME_numberOfFc830;
		case VAR_numberOfR730xd:
			return DISPLAY_NAME_numberOfR730xd;
		case VAR_numberOfWhiteboxFlax1:
			return DISPLAY_NAME_numberOfWhiteboxFlax1;
		case VAR_numberOfLenovoSd650nv2A100:
			return DISPLAY_NAME_numberOfLenovoSd650nv2A100;
		case VAR_numberOfLenovoSd665nv3H100:
			return DISPLAY_NAME_numberOfLenovoSd665nv3H100;
		case VAR_image:
			return DISPLAY_NAME_image;
		case VAR_sshPublicKey:
			return DISPLAY_NAME_sshPublicKey;
		case VAR_floatingIp:
			return DISPLAY_NAME_floatingIp;
		case VAR_status:
			return DISPLAY_NAME_status;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionBareMetalOrder(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_description:
			return "The description of this bare metal order";
		case VAR_networkId:
			return "The bare metal network to use for this order. ";
		case VAR_networkName:
			return "The bare metal network name to use for this order. ";
		case VAR_numberOfFc430:
			return "The number of fc430 nodes to request for this order. ";
		case VAR_numberOfFc830:
			return "The number of fc830 nodes to request for this order. ";
		case VAR_numberOfR730xd:
			return "The number of r730xd nodes to request for this order. ";
		case VAR_numberOfWhiteboxFlax1:
			return "The number of whitebox-flax-1 nodes to request for this order. ";
		case VAR_numberOfLenovoSd650nv2A100:
			return "The number of lenovo-sd650nv2-a100 nodes to request for this order. ";
		case VAR_numberOfLenovoSd665nv3H100:
			return "The number of lenovo-sd665nv3-h100 nodes to request for this order. ";
		case VAR_image:
			return "The operating system image of a bare metal fulfillment";
		case VAR_sshPublicKey:
			return "The SSH public key of the user, not the private SSH key! ";
		case VAR_floatingIp:
			return "The floating IP of a bare metal fulfillment";
		case VAR_status:
			return "The order status of a bare metal fulfillment";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return "String";
		case VAR_networkId:
			return "String";
		case VAR_networkSearch:
			return "SearchList";
		case VAR_network:
			return "BareMetalNetwork";
		case VAR_networkName:
			return "String";
		case VAR_numberOfFc430:
			return "Integer";
		case VAR_numberOfFc830:
			return "Integer";
		case VAR_numberOfR730xd:
			return "Integer";
		case VAR_numberOfWhiteboxFlax1:
			return "Integer";
		case VAR_numberOfLenovoSd650nv2A100:
			return "Integer";
		case VAR_numberOfLenovoSd665nv3H100:
			return "Integer";
		case VAR_image:
			return "String";
		case VAR_sshPublicKey:
			return "String";
		case VAR_floatingIp:
			return "Boolean";
		case VAR_status:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return 1;
		case VAR_networkName:
			return 2;
		case VAR_status:
			return 1;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return 3;
		case VAR_networkId:
			return 3;
		case VAR_numberOfFc430:
			return 4;
		case VAR_numberOfFc830:
			return 4;
		case VAR_numberOfR730xd:
			return 4;
		case VAR_numberOfWhiteboxFlax1:
			return 4;
		case VAR_numberOfLenovoSd650nv2A100:
			return 4;
		case VAR_numberOfLenovoSd665nv3H100:
			return 4;
		case VAR_image:
			return 5;
		case VAR_sshPublicKey:
			return 6;
		case VAR_floatingIp:
			return 7;
		case VAR_status:
			return 8;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellBareMetalOrder(String var) {
		switch(var) {
		case VAR_description:
			return 0;
		case VAR_networkId:
			return 1;
		case VAR_numberOfFc430:
			return 0;
		case VAR_numberOfFc830:
			return 1;
		case VAR_numberOfR730xd:
			return 2;
		case VAR_numberOfWhiteboxFlax1:
			return 3;
		case VAR_numberOfLenovoSd650nv2A100:
			return 4;
		case VAR_numberOfLenovoSd665nv3H100:
			return 5;
		case VAR_image:
			return 0;
		case VAR_sshPublicKey:
			return 0;
		case VAR_floatingIp:
			return 0;
		case VAR_status:
			return 0;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinBareMetalOrder(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxBareMetalOrder(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxBareMetalOrder(String var) {
		switch(var) {
		case VAR_numberOfFc430:
			return 100;
		case VAR_numberOfFc830:
			return 100;
		case VAR_numberOfR730xd:
			return 100;
		case VAR_numberOfWhiteboxFlax1:
			return 100;
		case VAR_numberOfLenovoSd650nv2A100:
			return 100;
		case VAR_numberOfLenovoSd665nv3H100:
			return 100;
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minBareMetalOrder(String var) {
		switch(var) {
		case VAR_numberOfFc430:
			return 0;
		case VAR_numberOfFc830:
			return 0;
		case VAR_numberOfR730xd:
			return 0;
		case VAR_numberOfWhiteboxFlax1:
			return 0;
		case VAR_numberOfLenovoSd650nv2A100:
			return 0;
		case VAR_numberOfLenovoSd665nv3H100:
			return 0;
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
