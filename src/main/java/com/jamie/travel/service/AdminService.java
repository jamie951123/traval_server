package com.jamie.travel.service;

import java.util.List;

import com.jamie.travel.model.UserProfile;

public interface AdminService {
	//Find
		List<UserProfile> findAll();
}