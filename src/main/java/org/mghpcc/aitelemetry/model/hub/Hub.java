package org.mghpcc.aitelemetry.model.hub;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.api.BaseApiServiceImpl;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.config.ConfigKeys;
import org.mghpcc.aitelemetry.model.BaseModel;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;

/**
 * Order: 4
 * Description: An Red Hat Advanced Cluster Management Hub cluster. 
 * AName: a hub
 * Icon: <i class="fa-regular fa-sitemap"></i>
 *
 * SearchPageUri: /en-us/search/hub
 * EditPageUri: /en-us/edit/hub/{authResource}
 * DisplayPageUri: /en-us/view/hub/{authResource}
 * UserPageUri: /en-us/user/hub/{authResource}
 * ApiUri: /en-us/api/hub
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   HubAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     Admin:
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
 *     Admin:
 *     SuperAdmin:
 **/
public class Hub extends HubGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: hub name
	 * Description: The name of this hub
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 1
	 * HtmRowTitleOpen: hub details
	 * Facet: true
	 * VarName: true
	 **/
	protected void _hubName(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: hub ID
	 * Description: The ID of this hub
	 * HtmRow: 3
	 * HtmCell: 2
	 * HtmColumn: 2
	 * Facet: true
	 **/
	protected void _hubId(Wrap<String> w) {
		w.o(toId(hubName));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: hub auth resource
	 * Description: The unique authorization resource for the hub for multi-tenancy
	 * Facet: true
	 * VarId: true
	 * AuthorizationResource: HUB
	 **/
	protected void _hubResource(Wrap<String> w) {
		w.o(String.format("%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId));
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
	 */
	protected void _pageId(Wrap<String> w) {
		w.o(hubId);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: description
	 * Description: A description of this hub
	 * HtmRow: 3
	 * HtmCell: 4
	 * Facet: true
	 * HtmColumn: 3
	 * VarDescription: true
	 **/
	protected void _description(Wrap<String> w) {
	}
}
