package com.app.booksexchange.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booksexchange.entity.UsersRights;
import com.app.booksexchange.security.dao.UserDao;
import com.app.booksexchange.services.ProfilesService;
import com.app.booksexchange.services.RightsService;
import com.app.booksexchange.services.UsersService;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Resource(name = "rightsService")
	RightsService rightService;

	@Resource(name = "profileService")
	ProfilesService profileService;

	@Resource(name = "usersService")
	UsersService userService;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		User userDetails;
		List<UsersRights> rights;
		com.app.booksexchange.entity.Users user = userDao
				.findByUserName(username);

		if (user != null) {
			rights = rightService.getUserRights(user.getUserId());
			List<GrantedAuthority> authorities = buildUserAuthority(rights);
			userDetails = buildUserForAuthentication(user, authorities);
		} else {
			if (username != null && !username.isEmpty())
				userDetails = new User(username, "", false, false, false,
						false, new HashSet<GrantedAuthority>());
			else
				throw new UsernameNotFoundException("Empty name");
		}
		return userDetails;
	}

	// Convertir ModisUsers to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(
			com.app.booksexchange.entity.Users user,
			List<GrantedAuthority> authorities) {
		boolean accountNotExpired = true; // TODO review

		return new User(user.getMail(), user.getPassword(), true,
				accountNotExpired, true, !true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<UsersRights> rights) {
		Set<GrantedAuthority> setAuths = new HashSet();
		if (rights != null && !rights.isEmpty()) {
			// Build user's authorities
			for (UsersRights right : rights) {
				setAuths.add(new SimpleGrantedAuthority(right.getProfile()
						.getProfileName()));
			}
		}
		List<GrantedAuthority> Result = new ArrayList(setAuths);
		return Result;
	}
}
