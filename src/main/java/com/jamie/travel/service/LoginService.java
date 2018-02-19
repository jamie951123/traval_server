package com.jamie.travel.service;

import com.jamie.travel.table.model.LoginHistory;
import com.jamie.travel.table.model.UserProfile;

public interface LoginService {
	UserProfile generalChecking(UserProfile userProfile);
	boolean hardcodeChecking(UserProfile userProfile);
	
	//save History
	LoginHistory saveHistory(Long userProfileId, String token);
	//find History
	LoginHistory findFirstByUserProfileIdOrderByCreateDateDesc(Long id);
	
}
