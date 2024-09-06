
package org.mghpcc.aitelemetry.user;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.model.user.ComputateSiteUser;
import org.computate.vertx.request.ComputateSiteRequest;
import org.mghpcc.aitelemetry.model.BaseModel;
import org.mghpcc.aitelemetry.request.SiteRequest;

/**
 * Model: true
 * Api: true
 * Page: true
 * SuperPage: BaseModelPage
 * Indexed: true
 * SqlOrder: 1
 * Order: 3
 * 
 * ApiTag: User
 * ApiUri: /api/user
 * 
 * ApiMethod:
 *   Search:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: SiteUserPage
 *     PageSuper: BaseModelPage
 *     ApiUri: /user
 * 
 * Keyword: classSimpleNameSiteUser
 * Role: SiteAdmin
 * Filter: userId
 * 
 * AName: a site user
 * Icon: <i class="fa-duotone fa-solid fa-user-gear"></i>
 * 
 * RoleUser: true
 * Role: SiteAdmin
 * Description: A user record for each site user
 */
public class SiteUser extends SiteUserGen<BaseModel> implements ComputateSiteUser {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Description: User keys that relate to this user
	 */
	protected void _userKeys(List<Long> l) {
		l.add(pk);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Description: The unique user ID from the SSO server
	 */
	protected void _userId(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Description: The user's username
	 */
	protected void _userName(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Description: The user's email
	 */
	protected void _userEmail(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Description: The user's first name
	 */
	protected void _userFirstName(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Description: The user's last name
	 */
	protected void _userLastName(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Description: The user's full name
	 */
	protected void _userFullName(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 4
	 * HtmCell: 1
	 * HtmRowTitle: user options
	 * DisplayName: see archived
	 * Description: A user field allowing a user to see archived records
	 */
	protected void _seeArchived(Wrap<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 4
	 * HtmCell: 2
	 * DisplayName: see deleted
	 * Description: A user field allowing a user to see deleted records
	 */
	protected void _seeDeleted(Wrap<Boolean> c) {
		c.o(false);
	}

	/**
	 * Description: An implementation for the interface for the object title
	 */
	@Override
	protected void _objectTitle(Wrap<String> c) {
		c.o(String.format("%s (%s) <%s>", userFullName, userName, userEmail));
	}

	/**
	 * Description: An implementation for the interface to set the request object
	 */
	@Override
	public <T extends ComputateSiteRequest> void setSiteRequest_(T siteRequest) {
		siteRequest_ = (SiteRequest)siteRequest;
	}

	/**
	 * Description: An implementation for the interface to create a new API request object
	 */
	@Override
	public void apiRequestSiteUser() {
		super.apiRequestSiteUser();
	}
}
