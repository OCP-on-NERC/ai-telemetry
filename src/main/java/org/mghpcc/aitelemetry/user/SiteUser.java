
package org.mghpcc.aitelemetry.user;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.model.user.ComputateSiteUser;
import org.computate.vertx.request.ComputateSiteRequest;
import org.mghpcc.aitelemetry.model.BaseModel;
import org.mghpcc.aitelemetry.request.SiteRequest;

/**
 * Order: 1
 * Description: A user record for each site user
 * AName: a site user
 * Icon: <i class="fa-duotone fa-solid fa-user-gear"></i>
 * 
 * Keyword: classSimpleNameSiteUser
 * Filter: userId
 * AuthUser: true
 * AuthDefaultClient:
 *   GET:
 *   PATCH:
 * 
 * SearchPageUri: /en-us/search/user
 * EditPageUri: /en-us/edit/user/{userId}
 * ApiUri: /en-us/api/user
 * ApiMethod:
 *   Search:
 *   PATCH:
 *   POST:
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
	 * VarId: true
	 * Unique: true
	 */
	protected void _userId(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Description: The user's username
	 * HtmColumn: 2
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
	 * HtmColumn: 1
	 */
	protected void _userFullName(Wrap<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 4
	 * HtmCell: 1
	 * HtmRowTitleOpen: user options
	 * DisplayName: see archived
	 * Description: A user field allowing a user to see archived records
	 */
	protected void _seeArchived(Wrap<Boolean> c) {
		c.o(false);
	}

	/**
	 * DocValues: true
	 * Persist: true
	 * Description: The display name for this user
	 * VarName: true
	 */
	protected void _displayName(Wrap<String> c) {
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
