package org.mghpcc.aitelemetry.model.baremetalorder;

import java.util.Optional;

import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.search.list.SearchList;
import org.mghpcc.aitelemetry.model.BaseModel;
import org.mghpcc.aitelemetry.model.baremetalnetwork.BareMetalNetwork;

import io.vertx.core.Promise;

/**
 * Order: 16
 * Description: A bare metal order
 * AName: a bare metal order
 * Icon: <i class="fa-regular fa-share-nodes"></i>
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
	 * HtmCell: 0
	 * HtmColumn: 1
	 * HtmRowTitleOpen: bare metal order details
	 * Facet: true
	 * VarDescription: true
	 * Multiline: true
	 **/
	protected void _description(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: bare metal network
	 * Description: The bare metal network to use for this order. 
	 * HtmRow: 3
	 * HtmCell: 1
	 * Facet: true
	 * Relate: BareMetalNetwork.id
	 **/
	protected void _networkId(Wrap<String> w) {}

	/**
	 * Ignore: true
	 */
	protected void _networkSearch(Promise<SearchList<BareMetalNetwork>> promise) {
		SearchList<BareMetalNetwork> l = new SearchList<>();
		if(networkId != null) {
			l.setC(BareMetalNetwork.class);
			l.q("*:*");
			l.fq(String.format("id_docvalues_string:%s", SearchTool.escapeQueryChars(networkId)));
			l.setStore(true);
		}
		promise.complete(l);
	}

	/**
	 * Ignore: true
	 * Initialized: false
	 */
	protected void _network(Wrap<BareMetalNetwork> w) {
		if(networkSearch.first() != null) {
			w.o(networkSearch.first());
		}
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * DisplayName: bare metal network name
	 * Description: The bare metal network name to use for this order. 
	 * HtmColumn: 2
	 * Facet: true
	 **/
	protected void _networkName(Wrap<String> w) {
		if(network != null) {
			w.o(network.getName());
		}
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: number of fc430 ({{ max }} available)
	 * Description: The number of fc430 nodes to request for this order. 
	 * HtmRow: 4
	 * HtmCell: 0
	 * HtmRowTitleOpen: node details
	 * Facet: true
	 * Min: 0
	 * Max: 100
	 * SearchMax:
	 *   VarJsonArray: resourceClasses
	 *   VarValue: name
	 *   Var: count
	 *   Value: fc430
	 **/
	protected void _numberOfFc430(Wrap<Integer> w) {
		w.o(0);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: number of fc830 ({{ max }} available)
	 * Description: The number of fc830 nodes to request for this order. 
	 * HtmRow: 4
	 * HtmCell: 1
	 * Facet: true
	 * Min: 0
	 * Max: 100
	 * SearchMax:
	 *   VarJsonArray: resourceClasses
	 *   VarValue: name
	 *   Var: count
	 *   Value: fc830
	 **/
	protected void _numberOfFc830(Wrap<Integer> w) {
		w.o(0);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: number of r730xd ({{ max }} available)
	 * Description: The number of r730xd nodes to request for this order. 
	 * HtmRow: 4
	 * HtmCell: 2
	 * Facet: true
	 * Min: 0
	 * Max: 100
	 * SearchMax:
	 *   VarJsonArray: resourceClasses
	 *   VarValue: name
	 *   Var: count
	 *   Value: r730xd
	 **/
	protected void _numberOfR730xd(Wrap<Integer> w) {
		w.o(0);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: number of whitebox-flax-1 ({{ max }} available)
	 * Description: The number of whitebox-flax-1 nodes to request for this order. 
	 * HtmRow: 4
	 * HtmCell: 3
	 * Facet: true
	 * Min: 0
	 * Max: 100
	 * SearchMax:
	 *   VarJsonArray: resourceClasses
	 *   VarValue: name
	 *   Var: count
	 *   Value: whitebox-flax-1
	 **/
	protected void _numberOfWhiteboxFlax1(Wrap<Integer> w) {
		w.o(0);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: number of lenovo-sd650nv2-a100 ({{ max }} available)
	 * Description: The number of lenovo-sd650nv2-a100 nodes to request for this order. 
	 * HtmRow: 4
	 * HtmCell: 4
	 * Facet: true
	 * Min: 0
	 * Max: 100
	 * SearchMax:
	 *   VarJsonArray: resourceClasses
	 *   VarValue: name
	 *   Var: count
	 *   Value: lenovo-sd650nv2-a100
	 **/
	protected void _numberOfLenovoSd650nv2A100(Wrap<Integer> w) {
		w.o(0);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: number of lenovo-sd665nv3-h100 ({{ max }} available)
	 * Description: The number of lenovo-sd665nv3-h100 nodes to request for this order. 
	 * HtmRow: 4
	 * HtmCell: 5
	 * Facet: true
	 * Min: 0
	 * Max: 100
	 * SearchMax:
	 *   VarJsonArray: resourceClasses
	 *   VarValue: name
	 *   Var: count
	 *   Value: lenovo-sd665nv3-h100
	 **/
	protected void _numberOfLenovoSd665nv3H100(Wrap<Integer> w) {
		w.o(0);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: image
	 * Description: The operating system image of a bare metal fulfillment
	 * HtmRowTitleOpen: operating system image
	 * HtmRow: 5
	 * HtmCell: 0
	 * Facet: true
	 * Option:
	 *   centos9-stream: CentOS 9 Stream Linux
	 *   fedora40: Fedora 40 Linux
	 *   ubuntu-22.04: Ubuntu 22.04 Linux
	 **/
	protected void _image(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: SSH public key
	 * Description: The SSH public key of the user, not the private SSH key! 
	 * HtmRowTitleOpen: credentials
	 * HtmRow: 6
	 * HtmCell: 0
	 * Facet: true
	 * Multiline: true
	 **/
	protected void _sshPublicKey(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: floating IP
	 * Description: The floating IP of a bare metal fulfillment
	 * HtmRowTitleOpen: networking
	 * HtmRow: 7
	 * HtmCell: 0
	 * Facet: true
	 * Default: false
	 **/
	protected void _floatingIp(Wrap<Boolean> w) {
		w.o(false);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: order status
	 * Description: The order status of a bare metal fulfillment
	 * HidePOST: true
	 * HidePATCH: true
	 * HtmRowTitleOpen: status
	 * HtmRow: 8
	 * HtmCell: 0
	 * HtmColumn: 1
	 * Facet: true
	 **/
	protected void _status(Wrap<String> w) {}
}
