package org.mghpcc.aitelemetry.model.clusterorder;

import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.model.BaseModel;

/**
 * Order: 8
 * Description: Manage your own Red Hat OpenShift cluster in the cloud. 
 * AName: an OpenShift cluster order
 * Icon: <i class="fa-regular fa-server"></i>
 * 
 * SearchPageUri: /en-us/search/cluster-order
 * EditPageUri: /en-us/edit/cluster-order/{id}
 * ApiUri: /en-us/api/cluster-order
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
 *     PATCH:
 *     DELETE:
 *     Admin:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     SuperAdmin:
 */
public class ClusterOrder extends ClusterOrderGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: order ID
	 * Description: The ID of this order
	 * HtmRowTitleOpen: cluster order details
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 0
	 * Facet: true
	 * VarId: true
	 * VarName: true
	 **/
	protected void _id(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: order template id
	 * Description: The template id used for this order
	 * HtmRow: 3
	 * HtmCell: 2
	 * HtmColumn: 1
	 * Facet: true
	 * Relate: ClusterTemplate.id
	 **/
	protected void _templateId(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: order state
	 * Description: The state of this order
	 * HtmRow: 3
	 * HtmCell: 3
	 * HtmColumn: 2
	 * Facet: true
	 **/
	protected void _state(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: order cluster id
	 * Description: The cluster id associated with the order
	 * HtmRow: 3
	 * HtmCell: 4
	 * HtmColumn: 3
	 * Facet: true
	 **/
	protected void _clusterId(Wrap<String> w) {}
}
