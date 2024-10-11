package org.mghpcc.aitelemetry.model.node;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.search.list.SearchList;
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
 * UserPageTemplates: /en-us/user/ai-node
 * SuperPage: BaseModelPage
 * Indexed: true
 * Order: 4
 * Description: A Red Hat OpenShift node containing GPUs
 * ApiTag: AI node
 * ApiUri: /api/ai-node
 *
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: AiNodePage
 *     ApiUri: /ai-node
 *
 * Role: SiteAdmin
 *
 * AName: an AI node
 * Icon: <i class="fa-regular fa-computer"></i>
 **/
public class AiNode extends AiNodeGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster name
	 * Description: The name of this cluster
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 2
	 * HtmRowTitleOpen: cluster details
	 * Facet: true
	 **/
	protected void _clusterName(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: node name
	 * Description: The name of this node
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmRowTitle: AI node details
	 * HtmColumn: 1
	 * Facet: true
	 **/
	protected void _nodeName(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: description
	 * Description: A description of this node
	 * HtmRow: 3
	 * HtmCell: 2
	 * HtmColumn: 3
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
	protected void _location(Wrap<Point> w) {}

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
	protected void _entityId(Wrap<String> w) {
		w.o(String.format("urn:ngsi-ld:%s:%s", CLASS_SIMPLE_NAME, toId(String.format("%s-%s", clusterName, nodeName))));
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

	@Override
	protected void _objectTitle(Wrap<String> w) {
		StringBuilder b = new StringBuilder();
		b.append(Optional.ofNullable(entityShortId).map(s -> String.format("%s — %s", AiNode_NameAdjectiveSingular_enUS, s)).orElse(pk.toString()));
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
