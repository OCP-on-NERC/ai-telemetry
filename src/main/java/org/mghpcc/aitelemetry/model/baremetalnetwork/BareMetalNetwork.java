package org.mghpcc.aitelemetry.model.baremetalnetwork;

import java.time.ZonedDateTime;
import java.util.List;

import org.computate.search.wrap.Wrap;
import org.mghpcc.aitelemetry.model.BaseModel;

import io.vertx.core.json.JsonArray;

/**
 * Order: 10
 * Description: An OpenStack bare metal network
 * AName: a bare metal network
 * Icon: <i class="fa-duotone fa-regular fa-network-wired"></i>
 * 
 * SearchPageUri: /en-us/search/bare-metal-network
 * EditPageUri: /en-us/edit/bare-metal-network/{id}
 * ApiUri: /en-us/api/bare-metal-network
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   BareMetalNetworkReader:
 *     GET:
 *   Admin:
 *     GET:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     SuperAdmin:
 */
public class BareMetalNetwork extends BareMetalNetworkGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: network ID
	 * Description: The ID of this bare metal network
	 * HtmRowTitleOpen: bare metal network details
	 * HtmRow: 3
	 * HtmCell: 0
	 * Facet: true
	 * VarId: true
	 * Val.ESI: id
	 **/
	protected void _id(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: network name
	 * Description: The name of this bare metal network
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 0
	 * Facet: true
	 * VarName: true
	 * Val.ESI: name
	 **/
	protected void _name(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: network description
	 * Description: The description of this bare metal network
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * VarDescription: true
	 * Multiline: true
	 * Val.ESI: description
	 **/
	protected void _description(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: availability zone hints
	 * HtmRow: 3
	 * HtmCell: 3
	 * Facet: true
	 * Val.ESI: availability_zone_hints
	 **/
	protected void _availabilityZoneHints(Wrap<JsonArray> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: availability zones
	 * HtmRow: 3
	 * HtmCell: 4
	 * Facet: true
	 * Val.ESI: availability_zones
	 **/
	protected void _availabilityZones(List<String> l) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: created at
	 * HtmRow: 3
	 * HtmCell: 5
	 * Facet: true
	 * Val.ESI: created_at
	 **/
	protected void _createdAt(Wrap<ZonedDateTime> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: DNS domain
	 * HtmRow: 3
	 * HtmCell: 6
	 * Facet: true
	 * Val.ESI: dns_domain
	 **/
	protected void _dnsDomain(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: MTU
	 * HtmRow: 3
	 * HtmCell: 7
	 * Facet: true
	 * Val.ESI: mtu
	 **/
	protected void _mtu(Wrap<Integer> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: project ID
	 * HtmRow: 3
	 * HtmCell: 8
	 * Facet: true
	 * Val.ESI: project_id
	 **/
	protected void _projectId(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: provider network type
	 * HtmRow: 3
	 * HtmCell: 9
	 * Facet: true
	 * Val.ESI: provider_network_type
	 **/
	protected void _providerNetworkType(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: provider physical network
	 * HtmRow: 3
	 * HtmCell: 10
	 * Facet: true
	 * Val.ESI: provider_physical_network
	 **/
	protected void _providerPhysicalNetwork(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: provider segmentation ID
	 * HtmRow: 3
	 * HtmCell: 11
	 * HtmColumn: 1
	 * Facet: true
	 * Val.ESI: provider_segmentation_id
	 **/
	protected void _providerSegmentationId(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: QOS policy ID
	 * HtmRow: 3
	 * HtmCell: 12
	 * Facet: true
	 * Val.ESI: qos_policy_id
	 **/
	protected void _qosPolicyId(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: revision number
	 * HtmRow: 3
	 * HtmCell: 13
	 * Facet: true
	 * Val.ESI: revision_number
	 **/
	protected void _revisionNumber(Wrap<Integer> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: status
	 * HtmRow: 3
	 * HtmCell: 14
	 * Facet: true
	 * Val.ESI: status
	 **/
	protected void _status(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: subnetIds
	 * HtmRow: 3
	 * HtmCell: 15
	 * Facet: true
	 * Val.ESI: subnet_ids
	 **/
	protected void _subnetIds(List<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: tags
	 * HtmRow: 3
	 * HtmCell: 16
	 * Facet: true
	 * Val.ESI: tags
	 **/
	protected void _tags(List<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: tenantId
	 * HtmRow: 3
	 * HtmCell: 17
	 * Facet: true
	 * Val.ESI: tenant_id
	 **/
	protected void _tenantId(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: updatedAt
	 * HtmRow: 3
	 * HtmCell: 18
	 * Facet: true
	 * Val.ESI: updated_at
	 **/
	protected void _updatedAt(Wrap<ZonedDateTime> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRowTitleOpen: bare metal network state
	 * DisplayName: is admin state up
	 * HtmRow: 4
	 * HtmCell: 0
	 * Facet: true
	 * Val.ESI: is_admin_state_up
	 **/
	protected void _isAdminStateUp(Wrap<Boolean> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: is default
	 * HtmRow: 4
	 * HtmCell: 1
	 * Facet: true
	 * Val.ESI: is_default
	 **/
	protected void _isDefault(Wrap<Boolean> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: is port security enabled
	 * HtmRow: 4
	 * HtmCell: 2
	 * Facet: true
	 * Val.ESI: is_port_security_enabled
	 **/
	protected void _isPortSecurityEnabled(Wrap<Boolean> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: is router external
	 * HtmRow: 4
	 * HtmCell: 5
	 * Facet: true
	 * Val.ESI: is_router_external
	 **/
	protected void _isRouterExternal(Wrap<Boolean> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: is shared
	 * HtmRow: 4
	 * HtmCell: 6
	 * Facet: true
	 * Val.ESI: is_shared
	 **/
	protected void _isShared(Wrap<Boolean> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: is VLAN queing
	 * HtmRow: 4
	 * HtmCell: 7
	 * Facet: true
	 * Val.ESI: is_vlan_qing
	 **/
	protected void _isVlanQueing(Wrap<Boolean> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: is VLAN transparent
	 * HtmRow: 4
	 * HtmCell: 8
	 * Facet: true
	 * Val.ESI: is_vlan_transparent
	 **/
	protected void _isVlanTransparent(Wrap<Boolean> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: l2Adjecency
	 * HtmRow: 4
	 * HtmCell: 9
	 * Facet: true
	 * Val.ESI: l2_adjacency
	 **/
	protected void _l2Adjacency(Wrap<Boolean> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRowTitleOpen: location details
	 * DisplayName: location cloud
	 * HtmRow: 5
	 * HtmCell: 0
	 * Facet: true
	 * Val.ESIlocation: location
	 * Val.ESIcloud: cloud
	 **/
	protected void _locationCloud(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: location project domain ID
	 * HtmRow: 5
	 * HtmCell: 1
	 * Facet: true
	 * Val.ESIlocation: location
	 * Val.ESIproject: project
	 * Val.ESIdomain_id: domain_id
	 **/
	protected void _locationProjectDomainId(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: location project domain name
	 * HtmRow: 5
	 * HtmCell: 2
	 * Facet: true
	 * Val.ESIlocation: location
	 * Val.ESIproject: project
	 * Val.ESIdomain_name: domain_name
	 **/
	protected void _locationProjectDomainName(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: location project ID
	 * HtmRow: 5
	 * HtmCell: 3
	 * Facet: true
	 * Val.ESIlocation: location
	 * Val.ESIproject: project
	 * Val.ESIid: id
	 **/
	protected void _locationProjectId(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: location project name
	 * HtmRow: 5
	 * HtmCell: 4
	 * Facet: true
	 * Val.ESIlocation: location
	 * Val.ESIproject: project
	 * Val.ESIname: name
	 **/
	protected void _locationProjectName(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: location region name
	 * HtmRow: 5
	 * HtmCell: 5
	 * Facet: true
	 * Val.ESIlocation: location
	 * Val.ESIregion_name: region_name
	 **/
	protected void _locationRegionName(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: location zone
	 * HtmRow: 5
	 * HtmCell: 6
	 * Facet: true
	 * Val.ESIlocation: location
	 * Val.ESIzone: zone
	 **/
	protected void _locationZone(Wrap<String> w) {}
}
