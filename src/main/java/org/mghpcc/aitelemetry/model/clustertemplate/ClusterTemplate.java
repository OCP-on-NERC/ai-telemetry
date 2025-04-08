package org.mghpcc.aitelemetry.model.clustertemplate;

import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.model.BaseModel;

import io.vertx.core.json.JsonArray;

/**
 * Order: 8
 * Description: An OpenShift cluster template
 * AName: a cluster template
 * Icon: <i class="fa-regular fa-server"></i>
 * 
 * SearchPageUri: /en-us/search/cluster-template
 * EditPageUri: /en-us/edit/cluster-template/{title}
 * ApiUri: /en-us/api/cluster-template
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
public class ClusterTemplate extends ClusterTemplateGen<BaseModel> {

	/**
	 * 
	 * DisplayName: manage
	 */
	@Override
	protected void _editPage(Wrap<String> w) {
		super._editPage(w);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: template ID
	 * Description: The ID of this template
	 * HtmRowTitleOpen: cluster template details
	 * HtmRow: 3
	 * HtmCell: 1
	 * Facet: true
	 **/
	protected void _id(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: template title
	 * Description: The title of this template
	 * HtmRow: 3
	 * HtmCell: 2
	 * HtmColumn: 1
	 * Facet: true
	 * VarName: true
	 * VarId: true
	 **/
	protected void _title(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: template description
	 * Description: The description of this template
	 * HtmRow: 3
	 * HtmCell: 3
	 * HtmColumn: 2
	 * Facet: true
	 **/
	protected void _description(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: template parameters
	 * Description: The parameters of this template
	 * HtmRow: 3
	 * HtmCell: 4
	 * Facet: true
	 **/
	protected void _parameters(Wrap<JsonArray> w) {
		w.o(new JsonArray());
	}

	@Override
	protected void _objectSuggest(Wrap<String> w) {
		StringBuilder b = new StringBuilder();
		String objectId = idForClass();
		String objectTitle = titleForClass();
		if(pk != null)
			b.append(" ").append(pk);
		if(id != null)
			b.append(" ").append(id);
		if(objectId != null)
			b.append(" ").append(objectId);
		if(objectTitle != null)
			b.append(" ").append(objectTitle);
		w.o(b.toString());
	}
}
