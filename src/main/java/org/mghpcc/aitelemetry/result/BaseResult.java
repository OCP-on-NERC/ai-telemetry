
package org.mghpcc.aitelemetry.result;

import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.request.SiteRequest;
import org.computate.vertx.result.base.ComputateBaseResult;

import io.vertx.core.Promise;

/**
 * Indexed: true
 * Page: true
 * SuperPage: PageLayout
 * Keyword: classSimpleNameBaseResult
 * Description: A reusable base class for all non-model search classes
 * Order: 2
 * Promise: true
 */
public class BaseResult extends BaseResultGen<Object> implements ComputateBaseResult {

	/**
	 * {@inheritDoc}
	 * Ignore: true
	 * Description: The current request object
	 */
	protected void _siteRequest_(Wrap<SiteRequest> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * InheritPrimaryKey: true
	 * Persist: true
	 * Description: An optional inherited primary key from a legacy system for this object in the database
	 */
	protected void _inheritPk(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Modify: false
	 * VarCreated: true
	 * HtmRow: 1
	 * HtmCell: 2
	 * HtmRowTitle: primary key, ID, created, modified, archive details
	 * HtmColumn: 2
	 * DisplayName.enUS: created
	 * FormatHtm: MMM d, yyyy h:mm:ss a
	 * Description: A created timestamp for this record in the database
	 * Facet: true
	 */
	protected void _created(Wrap<ZonedDateTime> w) {
		w.o(ZonedDateTime.now(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Modify: false
	 * VarModified: true
	 * HtmRow: 1
	 * HtmCell: 3
	 * DisplayName.enUS: modified
	 * Description: A modified timestamp for this record in the database
	 * Facet: true
	 */
	protected void _modified(Wrap<ZonedDateTime> w) {
		w.o(ZonedDateTime.now(ZoneId.of(siteRequest_.getConfig().getString(ConfigKeys.SITE_ZONE))));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 2
	 * HtmCell: 1
	 * DisplayName.enUS: archived
	 * Description: For archiving this record
	 */
	protected void _archived(Wrap<Boolean> w) {
		w.o(false);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Description: the canonical name of this Java class
	 */
	protected void _classCanonicalName(Wrap<String> w) {
		w.o(getClass().getCanonicalName());
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Description: The simple name of this Java class
	 * DisplayName.enUS: object type
	 * Facet: true
	 */
	protected void _classSimpleName(Wrap<String> w) {
		w.o(getClass().getSimpleName());
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Description: All the inherited canonical names of this Java class
	 */
	protected void _classCanonicalNames(List<String> l) { 
		l.add(getClass().getCanonicalName());
		l.add(BaseResult.class.getCanonicalName());
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Modify: false
	 * Description: The session ID of the user that created this object
	 */
	protected void _sessionId(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: userKey
	 * DocValues: true
	 * Persist: true
	 * Modify: false
	 * Description: The primary key of the user that created this record
	 */
	protected void _userKey(Wrap<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Saves: true
	 * Description: A list of fields that are saved for this record in the database
	 */
	protected void _saves(List<String> l) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Description: The icon HTML
	 */
	protected void _objectIcon(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * VarTitle: true
	 * HtmColumn: 3
	 * Description: The title of this object
	 */
	protected void _objectTitle(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * Persist: true
	 * DocValues: true
	 * VarId: true
	 * UrlVar: pageUrlId
	 * HtmRow: 1
	 * HtmCell: 4
	 * HtmColumn: 1
	 * DisplayName.enUS: ID
	 * Description: A URL friendly unique ID for this object
	 */
	protected void _objectId(Wrap<String> w) {
	}

	/**
	 * Description: A helper method for generating a URL friendly unique ID for this object
	 */
	public String toId(String s) {
		if(s != null) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
		}

		return s;
	}

	/**
	 * {@inheritDoc}
	 * Suggested: true
	 * Description: The indexed field in the search engine for this record while using autosuggest
	 * DisplayName: autosuggest
	 */
	protected void _objectSuggest(Wrap<String> w) { 
		StringBuilder b = new StringBuilder();
		if(objectId != null)
			b.append(" ").append(objectId);
		if(objectTitle != null)
			b.append(" ").append(objectTitle);
		w.o(b.toString());
	}

	/**
	 * {@inheritDoc}
	 * Text: true
	 * Description: The full text search field in the search engine for this record while using autosuggest
	 * DisplayName: text
	 */
	protected void _objectText(List<String> l) { 
		if(objectId != null)
			l.add(objectId);
		if(objectTitle != null)
			l.add(objectTitle);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * VarUrlId: true
	 * Description: The link by name for this object in the UI
	 */
	protected void _pageUrlId(Wrap<String> w) {
		if(objectId != null) {
			try {
				Optional.ofNullable((String)FieldUtils.getField(getClass(), String.format("%s_ApiUriSearchPage_%s", getClass().getSimpleName(), siteRequest_.getLang())).get(this)).ifPresent(classApiUri -> {
					w.o(siteRequest_.getConfig().getString(ConfigKeys.SITE_BASE_URL) + classApiUri + "/" + objectId);
				});
			} catch (Exception e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * VarUrlPk: true
	 * Description: The link by primary key for this object in the UI
	 */
	protected void _pageUrlPk(Wrap<String> w) {
		w.o(pageUrlId);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * VarUrlApi: true
	 * Description: The link to this object in the API
	 */
	protected void _pageUrlApi(Wrap<String> w) {
		try {
			Optional.ofNullable((String)FieldUtils.getField(getClass(), String.format("%s_ApiUri_%s", getClass().getSimpleName(), siteRequest_.getLang())).get(this)).ifPresent(classApiUri -> {
				w.o(siteRequest_.getConfig().getString(ConfigKeys.SITE_BASE_URL) + classApiUri + "/" + objectId);
			});
		} catch (Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/**
	 * {@inheritDoc}
	 * UniqueKey: true
	 * Persist: true
	 * Description: The unique key for this record in the search engine
	 */
	protected void _id(Wrap<String> w) {
		w.o(objectId);
	}
}
