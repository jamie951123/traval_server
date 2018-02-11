package com.jamie.travel.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.controller.UserProfileController;
import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.dao.UserProfileDao;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.Role;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.type.Status;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	Logger log = LoggerFactory.getLogger(UserProfileController.class);
	@Autowired
	private UserProfileDao userProfileDao;
	
	//Find
	@Override
	public List<UserProfile> findAll() {
		// TODO Auto-generated method stub
		return userProfileDao.findAll();
	}

	@Override
	public UserProfile findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		try{
			return userProfileDao.findByUsernameAndPassword(username, password);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserProfile findByUsername(String username) {
		// TODO Auto-generated method stub
		try{
			return userProfileDao.findByUsername(username);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int countByUserName(String username) {
		// TODO Auto-generated method stub
		try{
			return userProfileDao.countByUserName(username);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public int countByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public UserProfile findByPartyId(String partyId) {
		// TODO Auto-generated method stub
		UserProfile u = userProfileDao.findByPartyId(partyId);
		if(u != null && u.getUserProfileId() != null) {
			return userProfileDao.findByPartyId(partyId);
		}else {
			log.error(LogMsg.errLog("UserProfile", new String[] {"findByPartyId","Account is not exist "}));
			return null;
		}
		
	}
	
	//Save
	@Override
	public UserProfile save(UserProfile userProfile) {
		// TODO Auto-generated method stub
		try{
			userProfile.setPartyId(userProfile.getUsername()+".TL");
			userProfile.setCreateBy(userProfile.getUsername()+".TL");
			userProfile.setCreateDate(new Date());
			userProfile.setRole(userProfile.getRole());
			userProfile.setStatus(Status.PROGRESS);
			return userProfileDao.save(userProfile);
		}catch(Exception e){
			throw new RuntimeException("User can not create");
		}
	}


}
