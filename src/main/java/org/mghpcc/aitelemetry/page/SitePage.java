
package org.mghpcc.aitelemetry.page;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.model.BaseModel;
import org.mghpcc.aitelemetry.result.BaseResult;
import org.mghpcc.aitelemetry.request.SiteRequest;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


/**
 * Order: 4
 * Description: Read the latest articles to learn more
 * AName: an article
 * Icon: <i class="fa-duotone fa-regular fa-newspaper"></i>
 * Sort.desc: courseNum
 * Sort.desc: lessonNum
 * Rows: 100
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/article
 * EditPageUri: /en-us/edit/article/{pageId}
 * DisplayPageUri: /en-us/view/article/{pageId}
 * ApiUri: /en-us/api/article
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 * 
 * AuthGroup:
 *   Admin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     Admin:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     SuperAdmin:
 */
public class SitePage extends SitePageGen<BaseResult> {

	/**
	 * {@inheritDoc}
	 */
	protected void _article(Wrap<Boolean> w) {
		w.o(true);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _githubOrg(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.GITHUB_ORG));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _siteName(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.SITE_NAME));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _siteDisplayName(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.SITE_DISPLAY_NAME));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _sitePublicUrl(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.SITE_PUBLIC_URL));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _mailingListUrl(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.MAILING_LIST_URL));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _quayioOrg(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.QUAYIO_ORG));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _sitePomGroupId(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.SITE_POM_GROUP_ID));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _staticBaseUrl(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.STATIC_BASE_URL));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _staticPath(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.STATIC_PATH));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void _siteBaseUrl(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ConfigKeys.SITE_BASE_URL));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: Course Number
	 * Description: The course number for this page. 
	 */
	protected void _courseNum(Wrap<Integer> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: Lesson Number
	 * Description: The lesson number for this page. 
	 */
	protected void _lessonNum(Wrap<Integer> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: title
	 * Description: The name of this page. 
	 * HtmColumn: 1
	 * VarName: true
	 */
	protected void _name(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: description
	 * Description: The description of this page. 
	 * HtmColumn: 2
	 * VarDescription: true
	 */
	protected void _description(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 3
	 * Facet: true
	 * DisplayName: author name
	 * Description: The author name
	 */
	protected void _authorName(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 3
	 * Facet: true
	 * DisplayName: author URL
	 * Description: The author URL
	 */
	protected void _authorUrl(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRowTitleOpen: Useful URLs
	 * HtmRow: 99
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: Page ID
	 * Description: The ID for this page. 
	 * VarId: true
	 */
	protected void _pageId(Wrap<String> w) {
		toId(objectTitle);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: header 1
	 * Description: The 1st header of this page. 
	 */
	protected void _h1(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: header 2
	 * Description: The 2nd header of this page. 
	 */
	protected void _h2(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 4
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: imageUri
	 * Description: The page image URI
	 */
	protected void _pageImageUri(Wrap<String> w) {
	}
	
	/**
	 * DocValues: true
	 * Description: The image width
	 */
	protected void _pageImageWidth(Wrap<Integer> w) {
		if(pageImageUri != null) {
			Path path = Paths.get(siteRequest_.getConfig().getString(ConfigKeys.STATIC_PATH), pageImageUri);
			File file = path.toFile();
			if(file.exists()) {
				try {
					BufferedImage img = ImageIO.read(file);
					w.o(img.getWidth());
					setPageImageHeight(img.getHeight());
					setPageImageType(Files.probeContentType(path));
				} catch (Exception ex) {
					ExceptionUtils.rethrow(ex);
				}
			}
		}
	}

	/**
	 * DocValues: true
	 * Description: The image height
	 */
	protected void _pageImageHeight(Wrap<Integer> c) {
	}

	/**
	 * DocValues: true
	 * Description: The image height
	 */
	protected void _pageImageType(Wrap<String> c) {
	}

	/**
	 * Persist: true
	 * DocValues: true
	 * Description: The image accessibility text. 
	 */
	protected void _pageImageAlt(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: related article IDs
	 * Description: The related article IDs comma-separated. 
	 */
	protected void _relatedArticleIds(Wrap<String> w) {
	}

	/**
	 * Ignore: true
	 */
	protected void _relatedArticleSearch(Promise<SearchList<SitePage>> promise) {
		SearchList<SitePage> l = new SearchList<>();
		if(relatedArticleIds != null) {
			List<String> list = Arrays.asList(StringUtils.split(relatedArticleIds, ",")).stream().map(id -> id.trim()).collect(Collectors.toList());
			l.setC(SitePage.class);
			l.q("*:*");
			l.fq(String.format("pageId_docvalues_string:" + list.stream()
					.map(id -> SearchTool.escapeQueryChars(id))
					.collect(Collectors.joining(" OR ", "(", ")"))
					));
			l.setStore(true);
		}
		promise.complete(l);
	}

	/**
	 * {@inheritDoc}
	 * Stored: true
	 * DisplayName: related articles
	 * Description: A JSON array of related articles. 
	 */
  protected void _relatedArticles(Wrap<JsonArray> w) {
    JsonArray array = new JsonArray();
    relatedArticleSearch.getList().stream().forEach(relatedArticle -> {
        JsonObject obj = JsonObject.mapFrom(relatedArticle);
				obj.remove(SitePage.VAR_relatedArticles);
				obj.remove(SitePage.VAR_relatedArticleIds);
				JsonObject obj2 = new JsonObject();
				obj2.put(SitePage.VAR_pageId, obj.getString(SitePage.VAR_pageId));
				obj2.put(SitePage.VAR_name, obj.getString(SitePage.VAR_name));
				obj2.put(SitePage.VAR_pageImageUri, obj.getString(SitePage.VAR_pageImageUri));
				obj2.put(SitePage.VAR_pageImageWidth, obj.getString(SitePage.VAR_pageImageWidth));
				obj2.put(SitePage.VAR_pageImageHeight, obj.getString(SitePage.VAR_pageImageHeight));
				obj2.put(SitePage.VAR_pageImageAlt, obj.getString(SitePage.VAR_pageImageAlt));
				obj2.put(SitePage.VAR_displayPage, obj.getString(SitePage.VAR_displayPage));
        array.add(obj2);
    });
    w.o(array);
	}
}
