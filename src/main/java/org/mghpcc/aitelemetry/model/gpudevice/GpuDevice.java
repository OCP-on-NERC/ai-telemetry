package org.mghpcc.aitelemetry.model.gpudevice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.model.BaseModel;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;

/**
 * Order: 5
 * Description: A Red Hat OpenShift GPU device containing GPUs
 * AName: a GPU device
 * Icon: <i class="fa-regular fa-memory"></i>
 *
 * SearchPageUri: /en-us/search/gpu-device
 * EditPageUri: /en-us/edit/gpu-device/{gpuDeviceId}
 * UserPageUri: /en-us/user/gpu-device/{gpuDeviceId}
 * ApiUri: /en-us/api/gpu-device
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   Admin:
 *     POST:
 *     PATCH:
 *     GET:
 *     PUT:
 *     DELETE:
 *     Admin:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     PUT:
 *     DELETE:
 *     SuperAdmin:
 **/
public class GpuDevice extends GpuDeviceGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster name
	 * Description: The cluster name of this GPU device
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 1
	 * HtmRowTitleOpen: GPU device details
	 * Facet: true
	 **/
	protected void _clusterName(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: node name
	 * Description: The node name of this GPU device
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
	 * DisplayName: GPU device number
	 * Description: The number of this GPU device
	 * HtmRow: 3
	 * HtmCell: 3
	 * HtmColumn: 3
	 * Facet: true
	 **/
	protected void _gpuDeviceNumber(Wrap<Integer> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: GPU device ID
	 * Description: A unique ID for a gpu device per cluster, and node. 
	 * Facet: true
	 * VarName: true
	 * VarId: true
	 **/
	protected void _gpuDeviceId(Wrap<String> w) {
		w.o(GpuDevice.toId(String.format("%s-%s-%s", clusterName, nodeName, gpuDeviceNumber)));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: GPU device utilization
	 * Description: Current utilization of the GPU device. 
	 * HtmRow: 4
	 * HtmCell: 1
	 * HtmRowTitleOpen: GPU device utilization stats
	 * Facet: true
	 **/
	protected void _gpuDeviceUtilization(Wrap<Integer> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: description
	 * Description: A description of this GPU device
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * HtmColumn: 2
	 * VarDescription: true
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
	 * DisplayName: entity ID
	 * Description: A unique ID for this Smart Data Model
	 * HtmRow: 3
	 * HtmCell: 4
	 * Facet: true
	 */
	protected void _id(Wrap<String> w) {
		w.o(String.format("urn:ngsi-ld:%s:%s", CLASS_SIMPLE_NAME, toId(gpuDeviceId)));
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
