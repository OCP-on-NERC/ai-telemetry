package org.mghpcc.aitelemetry.verticle;

import org.mghpcc.aitelemetry.request.SiteRequest;
import io.vertx.core.AbstractVerticle;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these WorkerVerticle objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class WorkerVerticleGen into the class WorkerVerticle. 
 * </li>
 * <h3>About the WorkerVerticle class and it's generated class WorkerVerticleGen&lt;AbstractVerticle&gt;: </h3>extends WorkerVerticleGen
 * <p>
 * This Java class extends a generated Java class WorkerVerticleGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.verticle.WorkerVerticle">Find the class WorkerVerticle in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends WorkerVerticleGen<AbstractVerticle>
 * <p>This <code>class WorkerVerticle extends WorkerVerticleGen&lt;AbstractVerticle&gt;</code>, which means it extends a newly generated WorkerVerticleGen. 
 * The generated <code>class WorkerVerticleGen extends AbstractVerticle</code> which means that WorkerVerticle extends WorkerVerticleGen which extends AbstractVerticle. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the WorkerVerticle class will inherit the helpful inherited class comments from the super class WorkerVerticleGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <h2>AName.enUS: null</h2>
 * <p>
 * Delete the class WorkerVerticle in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.mghpcc.aitelemetry.verticle.WorkerVerticle&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.mghpcc.aitelemetry.verticle in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.mghpcc.aitelemetry.verticle&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project ai-telemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:ai\-telemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class WorkerVerticleGen<DEV> extends AbstractVerticle {
	protected static final Logger LOG = LoggerFactory.getLogger(WorkerVerticle.class);
	public static final String configureI18nFileError1 = "Failed to load internationalization data from file: %s";
	public static final String configureI18nFileError = configureI18nFileError1;
	public static final String configureI18nError1 = "Failed to load internationalization data. ";
	public static final String configureI18nError = configureI18nError1;
	public static final String configureI18nComplete1 = "Loading internationalization data is complete. ";
	public static final String configureI18nComplete = configureI18nComplete1;
	public static final String configureI18nLoaded1 = "Loaded internationalization data: %s";
	public static final String configureI18nLoaded = configureI18nLoaded1;

	public static final String configureDataConnectionError1 = "Could not open the database client connection. ";
	public static final String configureDataConnectionError = configureDataConnectionError1;
	public static final String configureDataConnectionSuccess1 = "The database client connection was successful. \n";
	public static final String configureDataConnectionSuccess = configureDataConnectionSuccess1;
	public static final String configureDataInitError1 = "Could not initialize the database tables. ";
	public static final String configureDataInitError = configureDataInitError1;
	public static final String configureDataInitSuccess1 = "The database tables were created successfully. \n";
	public static final String configureDataInitSuccess = configureDataInitSuccess1;

	public static final String configureSharedWorkerExecutorFail1 = "Could not configure the shared worker executor. ";
	public static final String configureSharedWorkerExecutorFail = configureSharedWorkerExecutorFail1;
	public static final String configureSharedWorkerExecutorComplete1 = "The shared worker executor \"{}\" was configured successfully. \n";
	public static final String configureSharedWorkerExecutorComplete = configureSharedWorkerExecutorComplete1;

	public static final String configureKafkaSuccess1 = "The Kafka producer was initialized successfully. ";
	public static final String configureKafkaSuccess = configureKafkaSuccess1;

	public static final String importDataSkip1 = "The data import is disabled. ";
	public static final String importDataSkip = importDataSkip1;


	//////////////
	// initDeep //
	//////////////

	public WorkerVerticle initDeepWorkerVerticle(SiteRequest siteRequest_) {
		initDeepWorkerVerticle();
		return (WorkerVerticle)this;
	}

	public void initDeepWorkerVerticle() {
		initWorkerVerticle();
	}

	public void initWorkerVerticle() {
	}

	public void initDeepForClass(SiteRequest siteRequest_) {
		initDeepWorkerVerticle(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainWorkerVerticle(v);
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
	public Object obtainWorkerVerticle(String var) {
		WorkerVerticle oWorkerVerticle = (WorkerVerticle)this;
		switch(var) {
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
				o = relateWorkerVerticle(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateWorkerVerticle(String var, Object val) {
		WorkerVerticle oWorkerVerticle = (WorkerVerticle)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetWorkerVerticle(entityVar,  siteRequest_, o);
	}
	public static Object staticSetWorkerVerticle(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
			default:
				return null;
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchWorkerVerticle(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchWorkerVerticle(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return null;
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrWorkerVerticle(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrWorkerVerticle(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return null;
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqWorkerVerticle(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqWorkerVerticle(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
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

	public static final String[] WorkerVerticleVals = new String[] { configureI18nFileError1, configureI18nError1, configureI18nComplete1, configureI18nLoaded1, configureDataConnectionError1, configureDataConnectionSuccess1, configureDataInitError1, configureDataInitSuccess1, configureSharedWorkerExecutorFail1, configureSharedWorkerExecutorComplete1, configureKafkaSuccess1, importDataSkip1 };

	public static final String CLASS_SIMPLE_NAME = "WorkerVerticle";


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
		return WorkerVerticle.displayNameWorkerVerticle(var);
	}
	public static String displayNameWorkerVerticle(String var) {
		switch(var) {
		default:
			return null;
		}
	}

	public static String descriptionWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static String classSimpleNameWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer htmColumnWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer htmRowWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer htmCellWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer lengthMinWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer lengthMaxWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer maxWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}

	public static Integer minWorkerVerticle(String var) {
		switch(var) {
			default:
				return null;
		}
	}
}
