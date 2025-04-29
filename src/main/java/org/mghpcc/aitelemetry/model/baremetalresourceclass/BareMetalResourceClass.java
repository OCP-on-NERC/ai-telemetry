package org.mghpcc.aitelemetry.model.baremetalresourceclass;

import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.model.BaseModel;

/**
 * Order: 8
 * Description: A bare metal resource class
 * Name: a bare metal resource class
 * Icon: <i class="fa-regular fa-server"></i>
 * 
 * SearchPageUri: /en-us/search/bare-metal-resource-class
 * EditPageUri: /en-us/edit/bare-metal-resource-class/{id}
 * ApiUri: /en-us/api/bare-metal-resource-class
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * PublicRead: true
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
public class BareMetalResourceClass extends BareMetalResourceClassGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: resource class name
	 * Description: The name of the resource class
	 * HtmRowTitleOpen: name
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 0
	 * Facet: true
	 * VarId: true
	 * VarName: true
	 **/
	protected void _name(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: number of available nodes
	 * Description: The number of available nodes
	 * HtmRow: 3
	 * HtmCell: 3
	 * HtmColumn: 1
	 * Facet: true
	 **/
	protected void _count(Wrap<String> w) {}
}