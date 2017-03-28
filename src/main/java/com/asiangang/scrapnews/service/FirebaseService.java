package com.asiangang.scrapnews.service;

import com.asiangang.scrapnews.config.auth.firebase.FirebaseTokenHolder;

public interface FirebaseService {

	FirebaseTokenHolder parseToken(String idToken);

}
