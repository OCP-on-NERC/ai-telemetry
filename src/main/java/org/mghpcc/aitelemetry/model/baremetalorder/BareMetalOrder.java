package org.mghpcc.aitelemetry.model.baremetalorder;

import java.util.Optional;

import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.model.BaseModel;

/**
 * Order: 12
 * Description: A bare metal order
 * AName: a bare metal order
 * Icon: <i class="fa-duotone fa-regular fa-share-nodes"></i>
 *
 * SearchPageUri: /en-us/search/bare-metal-order
 * EditPageUri: /en-us/edit/bare-metal-order/{pk}
 * UserPageUri: /en-us/user/bare-metal-order/{pk}
 * ApiUri: /en-us/api/bare-metal-order
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 * 
 * AuthUser: true
 * AuthGroup:
 *   BareMetalOrderOwner:
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
 *     SuperAdmin:
 **/
public class BareMetalOrder extends BareMetalOrderGen<BaseModel> {

    /**
     * VarId: true
     * VarName: true
	 * HtmColumn: 0
     */
    @Override
    protected void _pk(Wrap<Long> w) {
        super._pk(w);
    }
    
	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: bare metal order description
	 * Description: The description of this bare metal order
	 * HtmRow: 3
	 * HtmCell: 3
	 * HtmColumn: 1
	 * HtmRowTitleOpen: bare metal order details
	 * Facet: true
	 * VarDescription: true
	 **/
	protected void _description(Wrap<String> w) {}
}
