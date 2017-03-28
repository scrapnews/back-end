package com.asiangang.scrapnews.config.auth.firebase;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.asiangang.scrapnews.config.SecurityConfig.Roles;
import com.asiangang.scrapnews.domain.dao.UserDao;
import com.asiangang.scrapnews.domain.model.RoleEntity;
import com.asiangang.scrapnews.domain.model.UserEntity;
import com.asiangang.scrapnews.exception.FirebaseTokenInvalidException;
import com.asiangang.scrapnews.service.FirebaseService;
import com.asiangang.scrapnews.service.UserService;
import com.asiangang.scrapnews.service.impl.UserServiceImpl;
import com.asiangang.scrapnews.service.shared.FirebaseParser;
import com.asiangang.scrapnews.util.StringUtil;

public class FirebaseFilter extends OncePerRequestFilter {

	private static String HEADER_NAME = "X-Authorization-Firebase-ScrapNews";
	
	private FirebaseService firebaseService;

	public FirebaseFilter(FirebaseService firebaseService) {
		this.firebaseService = firebaseService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String xAuth = request.getHeader(HEADER_NAME);
		if (StringUtil.isBlank(xAuth)) {
			filterChain.doFilter(request, response);
			return;
		} else {
			try {
				FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);

				String userName = holder.getUid();

				Authentication auth = new FirebaseAuthenticationToken(userName, holder);
				SecurityContextHolder.getContext().setAuthentication(auth);

				filterChain.doFilter(request, response);
			} catch (FirebaseTokenInvalidException e) {
				throw new SecurityException(e);
			}
		}
	}

}
