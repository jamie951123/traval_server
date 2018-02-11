package com.jamie.travel.service;

import java.util.List;

import com.jamie.travel.model.UserProfile;

public interface UserProfileService {
	
	//Find
	List<UserProfile> findAll();
	UserProfile findByUsername(String username);
	UserProfile findByUsernameAndPassword(String username,String password);
	int countByUserName(String username);
	int countByUserNameAndPassword(String username, String password);
	//Save
	UserProfile save(UserProfile userProfile);
}
