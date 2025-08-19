package org.mghpcc.aitelemetry.model.node;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.model.BaseModel;
import org.mghpcc.aitelemetry.model.cluster.Cluster;
import org.mghpcc.aitelemetry.model.hub.Hub;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;

/**
 * Order: 6
 * Description: A Red Hat OpenShift node containing GPUs
 * AName: an AI node
 * Icon: <i class="fa-regular fa-computer"></i>
 * Sort.asc: hubId
 * Sort.asc: clusterName
 * Sort.asc: nodeName
 * Rows: 100
 *
 * SearchPageUri: /en-us/search/ai-node
 * EditPageUri: /en-us/edit/ai-node/{nodeResource}
 * UserPageUri: /en-us/user/ai-node/{nodeResource}
 * ApiUri: /en-us/api/ai-node
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
 *     GET:
 *   Admin:
 *     GET:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     SuperAdmin:
 **/
public class AiNode extends AiNodeGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: ACM Hub
	 * Description: The name of the ACM Hub for this cluster in Prometheus Keycloak Proxy. 
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 1
	 * HtmRowTitleOpen: AI node details
	 * Facet: true
	 **/
	protected void _hubId(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: hub auth resource
	 * Description: The unique authorization resource for the hub for multi-tenancy
	 * Facet: true
	 * Relate: Hub.hubResource
	 * AuthorizationResource: HUB
	 **/
	protected void _hubResource(Wrap<String> w) {
		w.o(String.format("%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster name
	 * Description: The name of this cluster
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 1
	 * Facet: true
	 **/
	protected void _clusterName(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster auth resource
	 * Description: The unique authorization resource for the cluster for multi-tenancy
	 * Facet: true
	 * AuthorizationResource: CLUSTER
	 * Relate: Cluster.clusterResource
	 **/
	protected void _clusterResource(Wrap<String> w) {
		w.o(String.format("%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: node name
	 * Description: The name of this node
	 * HtmRow: 3
	 * HtmCell: 2
	 * HtmColumn: 2
	 * Facet: true
	 **/
	protected void _nodeName(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: node auth resource
	 * Description: The unique authorization resource for the node for multi-tenancy
	 * Facet: true
	 * VarId: true
	 **/
	protected void _nodeResource(Wrap<String> w) {
		w.o(String.format("%s-%s-%s-%s-%s-%s", Hub.CLASS_AUTH_RESOURCE, hubId, Cluster.CLASS_AUTH_RESOURCE, clusterName, AiNode.CLASS_AUTH_RESOURCE, nodeName));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * DisplayName: node display name
	 * Description: The display name of this node
	 * Facet: true
	 * VarName: true
	 **/
	protected void _nodeDisplayName(Wrap<String> w) {
	  w.o(String.format("%s node in the %s cluster of %s hub", nodeName, clusterName, hubId));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: description
	 * Description: A description of this node
	 * HtmRow: 3
	 * HtmCell: 2
	 * VarDescription: true
	 * Multiline: true
	 **/
	protected void _description(Wrap<String> w) {
		w.o(String.format("Contains %s GPU devices", gpuDevicesTotal));
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
	 * HtmRow: 10
	 * HtmCell: 1
	 * Facet: true
	 **/
	protected void _location(Wrap<Point> w) {
		w.o(staticSetLocation(siteRequest_, siteRequest_.getConfig().getString(ComputateConfigKeys.DEFAULT_MAP_LOCATION)));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: GPU devices total
	 * Description: The total number of GPU devices on this cluster. 
	 * HtmRow: 3
	 * HtmCell: 6
	 * Facet: true
	 */
	protected void _gpuDevicesTotal(Wrap<Integer> w) {}

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
	protected void _id(Wrap<String> w) {
		w.o(String.format("urn:ngsi-ld:%s:%s", CLASS_SIMPLE_NAME, nodeResource));
	}

	/**
	 * {@inheritDoc}
	 * DisplayName: short entity ID
	 * Description: A short ID for this Smart Data Model
	 * DocValues: true
	 * Facet: true
	 */
	protected void _entityShortId(Wrap<String> w) {
		if(id != null) {
			w.o(StringUtils.substringAfter(id, String.format("urn:ngsi-ld:%s:", CLASS_SIMPLE_NAME)));
		}
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: NGSILD-Tenant
	 * Description: The NGSILD-Tenant or Fiware-Service
	 * HtmRow: 5
	 * HtmCell: 1
	 * Facet: true
	 */
	protected void _ngsildTenant(Wrap<String> w) {
		w.o(System.getenv("NGSILD_TENANT"));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: NGSILD-Path
	 * Description: The NGSILD-Path or Fiware-ServicePath
	 * HtmRow: 5
	 * HtmCell: 2
	 * Facet: true
	 */
	protected void _ngsildPath(Wrap<String> w) {
		w.o(System.getenv("NGSILD_PATH"));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: NGSILD context
	 * Description: The NGSILD context URL for @context data
	 * HtmRow: 5
	 * HtmCell: 3
	 * Facet: true
	 */
	protected void _ngsildContext(Wrap<String> w) {
		w.o(siteRequest_.getConfig().getString(ComputateConfigKeys.CONTEXT_BROKER_CONTEXT));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: NGSILD data
	 * Description: The NGSILD data with @context from the context broker
	 * HtmRow: 5
	 * HtmCell: 4
	 * Facet: true
	 * Multiline: true
	 */
	protected void _ngsildData(Wrap<JsonObject> w) {
	}
}
