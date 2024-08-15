
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
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.model.BaseModel;
import org.mghpcc.aitelemetry.result.BaseResult;
import org.mghpcc.aitelemetry.request.SiteRequest;
import org.computate.vertx.config.ComputateConfigKeys;

import io.vertx.core.Promise;


/**
 * Api: true
 * Page: true
 * PageTemplates: /en-us/article
 * SuperPage: BaseResultPage
 * Indexed: true
 * Order: 2
 * 
 * ApiTag: Page
 * ApiUri: /api/page
 * 
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: SitePagePage
 *     PageSuper: BaseResultPage
 *     ApiUri: /page
 * 
 * AName: an article
 * Icon: <i class="fa-duotone fa-solid fa-newspaper"></i>
 * 
 * Sort.desc: courseNum
 * Sort.desc: lessonNum
 * 
 * PublicRead: true
 * Role: SiteAdmin
 * Description: Read the latest articles to learn more
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
	 * Description: The title of this page. 
	 * UrlVar: pageUrlId
	 * HtmColumn: 1
	 */
	protected void _title(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 3
	 * Facet: true
	 * DisplayName: author
	 * Description: The author
	 */
	protected void _author(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: Page ID
	 * Description: The ID for this page. 
	 */
	protected void _pageId(Wrap<String> w) {
		toId(title);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: resource URI
	 * Description: The resource relative URI for this page. 
	 */
	protected void _resourceUri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: template URI
	 * Description: The template relative URI for this page. 
	 */
	protected void _templateUri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * DisplayName: URI
	 * Description: The relative URI for this page. 
	 */
	protected void _uri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * DisplayName: URL
	 * Description: The URL for this page. 
	 */
	protected void _url(Wrap<String> w) {
		w.o(String.format("%s%s", siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), uri));
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
	 * Description: The image height
	 */
	protected void _pageImageHeight(Wrap<Integer> c) {
	}

	/**
	 * Description: The image height
	 */
	protected void _pageImageType(Wrap<String> c) {
	}

	@Override
	protected void _objectTitle(Wrap<String> w) {
		w.o(String.format("%s - %s", SitePage_NameAdjectiveSingular_enUS, title));
	}

	@Override
	protected void _objectId(Wrap<String> w) {
		w.o(String.format("%s_%s", SitePage.CLASS_SIMPLE_NAME, pageId));
	}

	@Override
	protected void _id(Wrap<String> w) {
		w.o(String.format("%s_%s", SitePage.CLASS_SIMPLE_NAME, pageId));
	}

	/**
	 * HtmColumn: 2
	 */
	@Override
	protected void _pageUrlId(Wrap<String> w) {
		w.o(url);
	}
}
