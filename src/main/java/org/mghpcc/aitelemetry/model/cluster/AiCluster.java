package org.mghpcc.aitelemetry.model.cluster;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.api.BaseApiServiceImpl;
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
 * Order: 3
 * Description: A Red Hat OpenShift cluster containing GPUs
 * AName: an AI cluster
 * Icon: <i class="fa-regular fa-server"></i>
 *
 * SearchPageUri: /en-us/search/ai-cluster
 * EditPageUri: /en-us/edit/ai-cluster/{pageId}
 * ApiUri: /en-us/api/ai-cluster
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
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
 **/
public class AiCluster extends AiClusterGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster name
	 * Description: The name of this cluster
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 1
	 * HtmRowTitleOpen: cluster details
	 * Facet: true
	 * VarName: true
	 **/
	protected void _clusterName(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: description
	 * Description: A description of this cluster
	 * HtmRow: 3
	 * HtmCell: 2
	 * HtmColumn: 2
	 * VarDescription: true
	 **/
	protected void _description(Wrap<String> w) {
		w.o(String.format("Contains %s AI nodes and %s GPU devices", aiNodesTotal, gpuDevicesTotal));
	}


	/**
	 * {@inheritDoc}
	
	 * LocationColor: true
	 * Indexed: true
	 * Stored: true
	 * DisplayName: area served colors
	 * Description: The colors of each location Paths. 
	 */
	protected void _locationColors(List<String> l) {
	}

	/**
	 * {@inheritDoc}
	 * LocationTitle: true
	 * Indexed: true
	 * Stored: true
	 * DisplayName: area served titles
	 * Description: The titles of each location Paths. 
	 */
	protected void _locationTitles(List<String> l) {
	}

	/**
	 * {@inheritDoc}
	 * LocationUrl: true
	 * Indexed: true
	 * Stored: true
	 * DisplayName: area served links
	 * Description: The links of each location Paths. 
	 */
	protected void _locationLinks(List<String> l) {
	}


	/**
	 * {@inheritDoc}
	 * FiwareType: geo:point
	 * Location: true
	 * DocValues: true
	 * Persist: true
	 * DisplayName: location
	 * Description: Geojson reference to the item. It can be Point, LineString, Polygon, MultiPoint, MultiLineString or MultiPolygon
	 * HtmRow: 3
	 * HtmCell: 5
	 * Facet: true
	 **/
	protected void _location(Wrap<Point> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: entity ID
	 * Description: A unique ID for this Smart Data Model
	 * HtmRow: 3
	 * HtmCell: 4
	 * Facet: true
	 * VarId: true
	 */
	protected void _entityId(Wrap<String> w) {
		w.o(String.format("urn:ngsi-ld:%s:%s", CLASS_SIMPLE_NAME, toId(clusterName)));
	}

	/**
	 * {@inheritDoc}
	 * DisplayName: short entity ID
	 * Description: A short ID for this Smart Data Model
	 * Facet: true
	 */
	protected void _entityShortId(Wrap<String> w) {
		if(entityId != null) {
			w.o(StringUtils.substringAfter(entityId, String.format("urn:ngsi-ld:%s:", CLASS_SIMPLE_NAME)));
		}
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: AI nodes total
	 * Description: The total number of AI nodes on this cluster. 
	 * HtmRowTitleOpen: cluster totals
	 * HtmRow: 4
	 * HtmCell: 1
	 * Facet: true
	 */
	protected void _aiNodesTotal(Wrap<Integer> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: GPU devices total
	 * Description: The total number of GPU devices on this cluster. 
	 * HtmRow: 4
	 * HtmCell: 2
	 * Facet: true
	 */
	protected void _gpuDevicesTotal(Wrap<Integer> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * DisplayName: Grafana GPU utilization
	 * Description: Explore this cluster's GPU utilization in Grafana. 
	 * HtmRow: 5
	 * HtmRowTitleOpen: Useful URLs
	 * HtmCell: 1
	 * Facet: true
	 * Link: true
	 */
	protected void _grafanaUrl(Wrap<String> w) {
		w.o(String.format("%s/explore?orgId=1&left=%s"
				, siteRequest_.getConfig().getString(ConfigKeys.GRAFANA_BASE_URL, ConfigKeys.GRAFANA_BASE_URL)
				, BaseApiServiceImpl.urlEncode(
						String.format("[\"now-1h\",\"now\",\"observability-metrics\",{\"exemplar\":true,\"expr\":\"DCGM_FI_DEV_GPU_UTIL{cluster=\\\"%s\\\"}\"}]"
								, BaseApiServiceImpl.urlEncode(clusterName)
						)
				)
		));
	}
}


