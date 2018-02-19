package com.jamie.travel.admin.service;

import java.util.List;

import com.jamie.travel.table.model.LoginHistory;
import com.jamie.travel.table.model.UserProfile;

public interface AdminService {
	//Find
		List<UserProfile> findAll();
		
		List<LoginHistory> findAllHistory();
}
