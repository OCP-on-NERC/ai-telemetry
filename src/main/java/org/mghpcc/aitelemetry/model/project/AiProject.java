package org.mghpcc.aitelemetry.model.project;

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
 * Order: 8
 * Description: A research project using AI and GPUs
 * AName: an AI project
 * PluralName: AI projects
 * Icon: <i class="fa-regular fa-school"></i>
 * 
 * SearchPageUri: /en-us/search/ai-project
 * EditPageUri: /en-us/edit/ai-project/{pageId}
 * UserPageUri: /en-us/user/ai-project/{pageId}
 * ApiUri: /en-us/api/ai-project
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
public class AiProject extends AiProjectGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: name
   * Description: The name of this AI project
   * HtmRow: 3
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitle: AI project details
   * Facet: true
   * VarName: true
   **/
  protected void _projectName(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: project Id
   * Description: The unique ID of this AI project
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   * VarId: true
   **/
  protected void _projectId(Wrap<String> w) {
    w.o(toId(projectName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: description
   * Description: A description of this AI project
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   * HtmColumn: 2
   * VarDescription: true
   **/
  protected void _description(Wrap<String> w) {}
}
