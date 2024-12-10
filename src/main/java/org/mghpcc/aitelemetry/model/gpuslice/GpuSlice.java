package org.mghpcc.aitelemetry.model.gpuslice;

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
 * Order: 7
 * Description: A GPU slice inside a GPU
 * AName: a GPU slice
 * PluralName: GPU slices
 * Icon: <i class="fa-regular fa-cake-slice"></i>
 * 
 * SearchPageUri: /en-us/search/gpu-slice
 * EditPageUri: /en-us/edit/gpu-slice/{pageId}
 * ApiUri: /en-us/api/gpu-slice
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
 */
public class GpuSlice extends GpuSliceGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: name
   * Description: The name of this GPU slice
   * HtmRow: 3
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitle: GPU device details
   * Facet: true
   * VarName: true
   **/
  protected void _sliceName(Wrap<String> w) {}

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
   * DisplayName: location colors
   * Description: The colors of each location Paths. 
   */
  protected void _locationColors(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * LocationTitle: true
   * Indexed: true
   * Stored: true
   * DisplayName: location titles
   * Description: The titles of each location Paths. 
   */
  protected void _locationTitles(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * LocationUrl: true
   * Indexed: true
   * Stored: true
   * DisplayName: location links
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
   * DisplayName: entity ID
   * Description: A unique ID for this Smart Data Model
   * HtmRow: 3
   * HtmCell: 4
   * Facet: true
   * VarId: true
   */
  protected void _entityId(Wrap<String> w) {
    w.o(String.format("urn:ngsi-ld:%s:%s", CLASS_SIMPLE_NAME, toId(sliceName)));
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
}
