package com.asiangang.scrapnews.service.impl;

import org.springframework.stereotype.Service;

import com.asiangang.scrapnews.config.auth.firebase.FirebaseTokenHolder;
import com.asiangang.scrapnews.service.FirebaseService;
import com.asiangang.scrapnews.service.shared.FirebaseParser;

@Service
public class FirebaseServiceImpl implements FirebaseService {
	@Override
	public FirebaseTokenHolder parseToken(String firebaseToken) {
		return new FirebaseParser().parseToken(firebaseToken);
	}
}
