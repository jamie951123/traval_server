package com.jamie.travel.service;

import com.jamie.travel.model.UserProfile;

public interface LoginService {
	UserProfile generalChecking(UserProfile userProfile);
	boolean hardcodeChecking(UserProfile userProfile);
}
