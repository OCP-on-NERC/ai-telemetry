package org.mghpcc.aitelemetry.model.baremetalnode;

import org.mghpcc.aitelemetry.request.SiteRequest;
import org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodeGenPage;
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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BareMetalNodePage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BareMetalNodePageGen into the class BareMetalNodePage. 
 * </li>
 * <h3>About the BareMetalNodePage class and it's generated class BareMetalNodePageGen&lt;BareMetalNodeGenPage&gt;: </h3>extends BareMetalNodePageGen
 * <p>
 * This Java class extends a generated Java class BareMetalNodePageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodePage">Find the class BareMetalNodePage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BareMetalNodePageGen<BareMetalNodeGenPage>
 * <p>This <code>class BareMetalNodePage extends BareMetalNodePageGen&lt;BareMetalNodeGenPage&gt;</code>, which means it extends a newly generated BareMetalNodePageGen. 
 * The generated <code>class BareMetalNodePageGen extends BareMetalNodeGenPage</code> which means that BareMetalNodePage extends BareMetalNodePageGen which extends BareMetalNodeGenPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the BareMetalNodePage class will inherit the helpful inherited class comments from the super class BareMetalNodePageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BareMetalNodePage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class BareMetalNodePage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodePage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.model.baremetalnode in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.model.baremetalnode&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BareMetalNodePageGen<DEV> extends BareMetalNodeGenPage {
	protected static final Logger LOG = LoggerFactory.getLogger(BareMetalNodePage.class);

	//////////////
	// initDeep //
	//////////////

	public Future<BareMetalNodePageGen<DEV>> promiseDeepBareMetalNodePage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBareMetalNodePage();
	}

	public Future<BareMetalNodePageGen<DEV>> promiseDeepBareMetalNodePage() {
		Promise<BareMetalNodePageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBareMetalNodePage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBareMetalNodeGenPage(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseBareMetalNodePage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
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

	@Override public Future<? extends BareMetalNodePageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepBareMetalNodePage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBareMetalNodePage(SiteRequest siteRequest_) {
			super.siteRequestBareMetalNodeGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBareMetalNodePage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBareMetalNodePage(v);
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
	public Object obtainBareMetalNodePage(String var) {
		BareMetalNodePage oBareMetalNodePage = (BareMetalNodePage)this;
		switch(var) {
			default:
				return super.obtainBareMetalNodeGenPage(var);
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
				o = relateBareMetalNodePage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBareMetalNodePage(String var, Object val) {
		BareMetalNodePage oBareMetalNodePage = (BareMetalNodePage)this;
		switch(var) {
			default:
				return super.relateBareMetalNodeGenPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, BareMetalNodePage o) {
		return staticSetBareMetalNodePage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetBareMetalNodePage(String entityVar, SiteRequest siteRequest_, String v, BareMetalNodePage o) {
		switch(entityVar) {
			default:
				return BareMetalNodeGenPage.staticSetBareMetalNodeGenPage(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBareMetalNodePage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBareMetalNodePage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return BareMetalNodeGenPage.staticSearchBareMetalNodeGenPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBareMetalNodePage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBareMetalNodePage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return BareMetalNodeGenPage.staticSearchStrBareMetalNodeGenPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBareMetalNodePage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBareMetalNodePage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
			default:
				return BareMetalNodeGenPage.staticSearchFqBareMetalNodeGenPage(entityVar,  siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "BareMetalNodePage";
	public static final String CLASS_CANONICAL_NAME = "org.mghpcc.aitelemetry.model.baremetalnode.BareMetalNodePage";


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
		return BareMetalNodePage.displayNameBareMetalNodePage(var);
	}
	public static String displayNameBareMetalNodePage(String var) {
		switch(var) {
		default:
			return BareMetalNodeGenPage.displayNameBareMetalNodeGenPage(var);
		}
	}

	public static String descriptionBareMetalNodePage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return BareMetalNodeGenPage.descriptionBareMetalNodeGenPage(var);
		}
	}

	public static String classSimpleNameBareMetalNodePage(String var) {
		switch(var) {
			default:
				return BareMetalNodeGenPage.classSimpleNameBareMetalNodeGenPage(var);
		}
	}

	public static Integer htmColumnBareMetalNodePage(String var) {
		switch(var) {
			default:
				return BareMetalNodeGenPage.htmColumnBareMetalNodeGenPage(var);
		}
	}

	public static Integer htmRowBareMetalNodePage(String var) {
		switch(var) {
			default:
				return BareMetalNodeGenPage.htmRowBareMetalNodeGenPage(var);
		}
	}

	public static Integer htmCellBareMetalNodePage(String var) {
		switch(var) {
			default:
				return BareMetalNodeGenPage.htmCellBareMetalNodeGenPage(var);
		}
	}

	public static Integer lengthMinBareMetalNodePage(String var) {
		switch(var) {
			default:
				return BareMetalNodeGenPage.lengthMinBareMetalNodeGenPage(var);
		}
	}

	public static Integer lengthMaxBareMetalNodePage(String var) {
		switch(var) {
			default:
				return BareMetalNodeGenPage.lengthMaxBareMetalNodeGenPage(var);
		}
	}

	public static Integer maxBareMetalNodePage(String var) {
		switch(var) {
			default:
				return BareMetalNodeGenPage.maxBareMetalNodeGenPage(var);
		}
	}

	public static Integer minBareMetalNodePage(String var) {
		switch(var) {
			default:
				return BareMetalNodeGenPage.minBareMetalNodeGenPage(var);
		}
	}
}
