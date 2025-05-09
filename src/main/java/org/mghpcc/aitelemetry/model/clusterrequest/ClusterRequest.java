package org.mghpcc.aitelemetry.model.clusterrequest;


import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.model.BaseModel;

/**
 * Order: 9
 * Description: An OpenShift cluster request
 * AName: an OpenShift cluster request
 * Icon: <i class="fa-regular fa-server"></i>
 *
 * SearchPageUri: /en-us/search/cluster-request
 * EditPageUri: /en-us/edit/cluster-request/{name}
 * UserPageUri: /en-us/user/cluster-request/{name}
 * ApiUri: /en-us/api/cluster-request
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthUser: true
 * AuthSession: true
 * AuthGroup:
 *   ClusterOwner:
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
public class ClusterRequest extends ClusterRequestGen<BaseModel> {

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
	 * VarId: true
	 **/
	protected void _name(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster template
	 * Description: The cluster template to use for this request. 
	 * HtmRow: 3
	 * HtmCell: 2
	 * HtmColumn: 2
	 * HtmRowTitleOpen: cluster request details
	 * Facet: true
	 * Relate: ClusterTemplate.title
	 **/
	protected void _clusterTemplateTitle(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: user
	 * Description: The user who requested the cluster
	 * HtmRow: 3
	 * HtmCell: 3
	 * HtmColumn: 3
	 * Facet: true
	 * Relate: SiteUser.userId
	 **/
	protected void _userId(Wrap<String> w) {}
}
