package com.asiangang.scrapnews.web.facade.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asiangang.scrapnews.config.auth.firebase.FirebaseTokenHolder;
import com.asiangang.scrapnews.service.FirebaseService;
import com.asiangang.scrapnews.service.UserService;
import com.asiangang.scrapnews.service.impl.UserServiceImpl;
import com.asiangang.scrapnews.service.shared.RegisterUserInit;
import com.asiangang.scrapnews.util.StringUtil;
import com.asiangang.scrapnews.web.facade.WebFacade;

@Service
public class WebFacadeImpl implements WebFacade {

	@Autowired(required = false)
	private FirebaseService firebaseService;

	@Autowired
	@Qualifier(value = UserServiceImpl.NAME)
	private UserService userService;

	@Transactional
	@Override
	public void registerUser(String firebaseToken) {
		if (StringUtil.isBlank(firebaseToken)) {
			throw new IllegalArgumentException("FirebaseTokenBlank");
		}
		FirebaseTokenHolder tokenHolder = firebaseService.parseToken(firebaseToken);
		userService.registerUser(new RegisterUserInit(tokenHolder.getUid(), tokenHolder.getEmail()));
	}
}
