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
 * Order: 7
 * Description: A research project using AI and GPUs
 * AName: an AI project
 * PluralName: AI projects
 * Icon: <i class="fa-regular fa-school"></i>
 * Sort.asc: clusterName
 * Sort.asc: projectName
 * 
 * SearchPageUri: /en-us/search/ai-project
 * EditPageUri: /en-us/edit/ai-project/{projectId}
 * UserPageUri: /en-us/user/ai-project/{projectId}
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
 *   AiProjectAdmin:
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
 */
public class AiProject extends AiProjectGen<BaseModel> {

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
	 * AuthorizationResource: AiCluster
	 **/
	protected void _clusterName(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: project name
   * Description: The name of this AI project
   * HtmRow: 3
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitle: AI project details
   * Facet: true
   **/
  protected void _projectName(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: project display name
   * Description: The display name of this AI project
   * Facet: true
   * VarName: true
   **/
  protected void _projectDisplayName(Wrap<String> w) {
    w.o(String.format("%s %s", clusterName, projectName));
  }

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
    w.o(toId(String.format("%s-%s", clusterName, projectName)));
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
	 * Multiline: true
   **/
  protected void _description(Wrap<String> w) {}
}
