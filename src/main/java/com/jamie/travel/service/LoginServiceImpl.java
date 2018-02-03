package com.jamie.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.exception.TokenValidationException;
import com.jamie.travel.model.UserProfile;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	UserProfileService userProfileService;
	
	@Override
	public boolean hardcodeChecking(UserProfile userProfile) {
		// TODO Auto-generated method stub
		 return "admin".equals(userProfile.getUsername())
	                && "admin".equals(userProfile.getPassword());
	}

	@Override
	public UserProfile generalChecking(UserProfile userProfile) {
		// TODO Auto-generated method stub
		UserProfile u = null;
		if(userProfile != null && ObjectUtils.isNotNullEmpty(userProfile.getUsername()) && ObjectUtils.isNotNullEmpty(userProfile.getPassword())){
			try{
				u = userProfileService.findByUsernameAndpPassword(userProfile.getUsername(), userProfile.getPassword());
				if(u != null){
					return u;
				}else{
					throw new TokenValidationException("Incorrect Account");
				}
			}catch(Exception e){
				throw new TokenValidationException(e.getMessage());
			}
		}
		return u;

	}

}
