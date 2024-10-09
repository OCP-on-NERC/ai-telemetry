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
 * Fiware: true
 *
 * Model: true
 * Api: true
 * Page: true
 * UserPageTemplates: /en-us/user/ai-cluster
 * SuperPage: BaseModelPage
 * Indexed: true
 * Order: 3
 * Description: A Red Hat OpenShift cluster containing GPUs
 * ApiTag: AI cluster
 * ApiUri: /api/ai-cluster
 *
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: AiClusterPage
 *     ApiUri: /ai-cluster
 *
 * Role: SiteAdmin
 *
 * AName: an AI cluster
 * Icon: <i class="fa-regular fa-server"></i>
 **/
public class AiCluster extends AiClusterGen<BaseModel> {


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: name
	 * Description: The name of this cluster
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 1
	 * HtmRowTitleOpen: cluster details
	 * Facet: true
	 **/
	protected void _name(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: description
	 * Description: A description of this cluster
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * HtmColumn: 2
	 **/
	protected void _description(Wrap<String> w) {}


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
	 * HtmRow: 4
	 * HtmCell: 1
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
	 */
	protected void _entityId(Wrap<String> w) {
		w.o(String.format("urn:ngsi-ld:%s:%s", CLASS_SIMPLE_NAME, toId(name)));
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
	 * DisplayName: GPU nodes total
	 * Description: The total number of GPU nodes on this cluster. 
	 * HtmRow: 5
	 * HtmCell: 1
	 * Facet: true
	 */
	protected void _gpuNodesTotal(Wrap<Integer> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * DisplayName: Grafana GPU utilization URL
	 * Description: The Grafana URL to explore this cluster's GPU utilization. 
	 * HtmRow: 6
	 * HtmRowTitle: Useful URLs
	 * HtmCell: 1
	 * Facet: true
	 */
	protected void _grafanaUrl(Wrap<String> w) {
		w.o(String.format("%s/explore?orgId=1&left=%s"
				, siteRequest_.getConfig().getString(ConfigKeys.GRAFANA_BASE_URL, ConfigKeys.GRAFANA_BASE_URL)
				, BaseApiServiceImpl.urlEncode(
						String.format("[\"now-1h\",\"now\",\"observability-metrics\",{\"exemplar\":true,\"expr\":\"DCGM_FI_DEV_GPU_UTIL{cluster=\\\"%s\\\"}\"}]"
								, BaseApiServiceImpl.urlEncode(name)
						)
				)
		));
	}

	@Override
	protected void _objectTitle(Wrap<String> w) {
		StringBuilder b = new StringBuilder();
		b.append(Optional.ofNullable(entityShortId).map(s -> String.format("%s — %s", AiCluster_NameAdjectiveSingular_enUS, s)).orElse(pk.toString()));
		w.o(b.toString().trim());
	}

	@Override
	protected void _objectId(Wrap<String> w) {
	if(objectTitle != null) {
			w.o(toId(objectTitle));
		} else if(id != null){
			w.o(id.toString());
		}
	}
}


