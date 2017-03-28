package com.asiangang.scrapnews.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.asiangang.scrapnews.domain.model.UserEntity;
import com.asiangang.scrapnews.service.shared.RegisterUserInit;

public interface UserService extends UserDetailsService {

	UserEntity registerUser(RegisterUserInit init);

}
