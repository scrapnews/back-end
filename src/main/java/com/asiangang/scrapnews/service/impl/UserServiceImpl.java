package com.asiangang.scrapnews.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asiangang.scrapnews.config.SecurityConfig.Roles;
import com.asiangang.scrapnews.domain.dao.UserDao;
import com.asiangang.scrapnews.domain.model.RoleEntity;
import com.asiangang.scrapnews.domain.model.UserEntity;
import com.asiangang.scrapnews.service.UserService;
import com.asiangang.scrapnews.service.shared.RegisterUserInit;

@Service(value = UserServiceImpl.NAME)
public class UserServiceImpl implements UserService {

	public final static String NAME = "UserService";
	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userDao.findByUsername(username);
//		if (userDetails == null) {
//			UserEntity userEntity = new UserEntity();
//			userEntity.setUsername(username);
//			userEntity.setEmail("ngoc02@gmail.com");
//
//			userEntity.setAuthorities(getUserRoles());
//			userEntity.setPassword(UUID.randomUUID().toString());
//			userDao.save(userEntity);
//			logger.info("registerUser -> user created");
//			return userEntity;
//		}
		if (userDetails == null) {
			return null;
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (GrantedAuthority role : userDetails.getAuthorities()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	@Transactional
	@Secured(value = Roles.ROLE_ANONYMOUS)
	public UserEntity registerUser(RegisterUserInit init) {

		UserEntity userLoaded = userDao.findByUsername(init.getUserName());

		if (userLoaded == null) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUsername(init.getUserName());
			userEntity.setEmail(init.getEmail());

			userEntity.setAuthorities(getUserRoles());
			// TODO firebase users should not be able to login via username and
			// password so for now generation of password is OK
			userEntity.setPassword(UUID.randomUUID().toString());
			userDao.save(userEntity);
			logger.info("registerUser -> user created");
			return userEntity;
		} else {
			logger.info("registerUser -> user exists");
			return userLoaded;
		}
	}

	@PostConstruct
	public void init() {

		if (userDao.count() == 0) {
			UserEntity adminEntity = new UserEntity();
			adminEntity.setUsername("alexson");
			adminEntity.setPassword("sony");
			adminEntity.setEmail("wutangclan@scrapnews.com");

			adminEntity.setAuthorities(getAdminRoles());
			userDao.save(adminEntity);

			UserEntity userEntity = new UserEntity();
			userEntity.setUsername("ngoc");
			userEntity.setPassword("sennheiser");
			userEntity.setEmail("asiangang@scrapnews.com");
			userEntity.setAuthorities(getUserRoles());

			userDao.save(userEntity);
		}
	}
	
	private List<RoleEntity> getAdminRoles(){
		return Collections.singletonList(new RoleEntity(Roles.ROLE_ADMIN));
	}

	private List<RoleEntity> getUserRoles() {
		return Collections.singletonList(new RoleEntity(Roles.ROLE_USER));
	}

}
