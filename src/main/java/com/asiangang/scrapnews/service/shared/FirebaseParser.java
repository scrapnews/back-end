package com.asiangang.scrapnews.service.shared;

import com.asiangang.scrapnews.config.auth.firebase.FirebaseTokenHolder;
import com.asiangang.scrapnews.exception.FirebaseTokenInvalidException;
import com.asiangang.scrapnews.util.StringUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

public class FirebaseParser {
	public FirebaseTokenHolder parseToken(String idToken) {
		if (StringUtil.isBlank(idToken)) {
			throw new IllegalArgumentException("FirebaseTokenBlank");
		}
		try {
			Task<FirebaseToken> authTask = FirebaseAuth.getInstance().verifyIdToken(idToken);

			Tasks.await(authTask);

			return new FirebaseTokenHolder(authTask.getResult());
		} catch (Exception e) {
			throw new FirebaseTokenInvalidException(e.getMessage());
		}
	}
}
