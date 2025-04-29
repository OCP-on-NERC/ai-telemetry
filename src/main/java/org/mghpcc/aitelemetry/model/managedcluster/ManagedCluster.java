package org.mghpcc.aitelemetry.model.managedcluster;

import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.model.BaseModel;

/**
 * Order: 8
 * Description: An OpenShift managed cluster
 * Name: a managed cluster
 * Icon: <i class="fa-regular fa-server"></i>
 * 
 * SearchPageUri: /en-us/search/managed-cluster
 * EditPageUri: /en-us/edit/managed-cluster/{id}
 * ApiUri: /en-us/api/managed-cluster
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
public class ManagedCluster extends ManagedClusterGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster ID
	 * Description: The ID of this cluster
	 * HtmRowTitleOpen: cluster details
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
	 * DisplayName: cluster state
	 * Description: The state of this cluster
	 * HtmRow: 3
	 * HtmCell: 3
	 * HtmColumn: 1
	 * Facet: true
	 **/
	protected void _state(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster API URL
	 * Description: The cluster API URL
	 * HtmRow: 3
	 * HtmCell: 4
	 * HtmColumn: 2
	 * Facet: true
	 **/
	protected void _apiUrl(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: cluster console URL
	 * Description: The cluster console URL
	 * HtmRow: 3
	 * HtmCell: 4
	 * HtmColumn: 3
	 * Facet: true
	 **/
	protected void _consoleUrl(Wrap<String> w) {}
}
