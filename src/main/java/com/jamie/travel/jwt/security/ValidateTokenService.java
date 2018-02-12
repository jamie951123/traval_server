package com.jamie.travel.jwt.security;

import java.util.Map;

import com.jamie.travel.model.UserProfile;

public interface ValidateTokenService {
	boolean validate_login_general(String token);	
	String token_getPartyId(String token);
	UserProfile token_getUserProfile(String token);
	//	
	boolean validate_registration_token(String token);
	Map<String,Object> split_registration_token(String token);

}