package com.jamie.travel.jwt.security;

import com.jamie.travel.model.TokenModel;
import com.jamie.travel.table.model.UserProfile;

public interface GenerateTokenService {
	//Login
	String login_general_token(UserProfile userProfile);
	
	//Registration
	String registration_token(TokenModel tokenModel);
}
