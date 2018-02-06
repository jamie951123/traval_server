package com.jamie.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.dao.UserProfileDao;
import com.jamie.travel.model.UserProfile;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserProfileDao userProfileDao;
	
	@Override
	public List<UserProfile> findAll() {
		// TODO Auto-generated method stub
		return userProfileDao.findAll();
	}

	
}
