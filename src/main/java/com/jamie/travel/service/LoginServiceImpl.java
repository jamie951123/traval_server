package com.jamie.travel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.exception.TokenValidationException;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.UserProfile;

@Service
public class LoginServiceImpl implements LoginService{
	Logger log = LoggerFactory.getLogger(this.getClass());
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
					log.info(LogMsg.infoLog("LoginService", new String[] {"Correct Account"}));
					return u;
				}else{
					throw new TokenValidationException("Correct Account");
				}
			}catch(Exception e){
				log.error(LogMsg.errLog("LoginService", new String[] {e.getMessage()}));
				return null;
			}
		}
		return u;

	}

}
