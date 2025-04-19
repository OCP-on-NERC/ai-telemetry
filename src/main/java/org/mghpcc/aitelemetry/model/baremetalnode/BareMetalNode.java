package org.mghpcc.aitelemetry.model.baremetalnode;

import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.model.BaseModel;

import io.vertx.core.json.JsonArray;

/**
 * Order: 11
 * Description: An OpenStack bare metal node
 * AName: a bare metal node
 * Icon: <i class="fa-duotone fa-regular fa-hexagon-nodes"></i>
 * 
 * SearchPageUri: /en-us/search/bare-metal-node
 * EditPageUri: /en-us/edit/bare-metal-node/{nodeId}
 * ApiUri: /en-us/api/bare-metal-node
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   BareMetalNodeReader:
 *     GET:
 *   Admin:
 *     GET:
 *   SuperAdmin:
 *     GET:
 * Val.nodeESI: node
 */
public class BareMetalNode extends BareMetalNodeGen<BaseModel> {

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: lease info
     * HtmRowTitleOpen: details
     * HtmRow: 3
     * HtmCell: 0
     * Facet: true
     * Val.ESI: lease_info
     **/
    protected void _leaseInfo(Wrap<JsonArray> w) {
    }

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: network info
     * HtmRow: 3
     * HtmCell: 1
     * HtmColumn: 4
     * Facet: true
     * Val.ESI: network_info
     **/
    protected void _networkInfo(Wrap<String> w) {
    }

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: id
     * HtmRow: 3
     * HtmCell: 19
     * Facet: true
     * VarId: true
     * Val.ESI: id
     **/
    protected void _nodeId(Wrap<String> w) {
    }

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: is maintenance
     * HtmRow: 3
     * HtmCell: 25
     * Facet: true
     * Val.ESI: is_maintenance
     **/
    protected void _nodeIsMaintenance(Wrap<Integer> w) {
    }

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: links
     * HtmRow: 3
     * HtmCell: 31
     * Facet: true
     * Val.ESI: links
     **/
    protected void _nodeLinks(Wrap<JsonArray> w) {
    }

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: name
     * HtmRow: 3
     * HtmCell: 35
     * HtmColumn: 0
     * Facet: true
     * VarName: true
     * Val.ESI: name
     **/
    protected void _nodeName(Wrap<String> w) {
    }

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: power state
     * HtmRow: 3
     * HtmCell: 42
     * HtmColumn: 2
     * Facet: true
     * Val.ESI: power_state
     **/
    protected void _nodePowerState(Wrap<String> w) {
    }

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: provision state
     * HtmRow: 3
     * HtmCell: 45
     * HtmColumn: 3
     * Facet: true
     * Val.ESI: provision_state
     **/
    protected void _nodeProvisionState(Wrap<String> w) {
    }

    /**
     * {@inheritDoc}
     * DocValues: true
     * Persist: true
     * DisplayName: resource class
     * HtmRow: 3
     * HtmCell: 55
     * HtmColumn: 1
     * Facet: true
     * Val.ESI: resource_class
     **/
    protected void _nodeResourceClass(Wrap<String> w) {
    }
}
