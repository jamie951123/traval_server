package com.jamie.travel.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.dao.LoginHistoryDao;
import com.jamie.travel.exception.TokenValidationException;
import com.jamie.travel.jwt.security.JwtUtils;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.jwt.security.ValidateTokenService;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.table.model.LoginHistory;
import com.jamie.travel.table.model.UserProfile;

@Service
public class LoginServiceImpl implements LoginService{
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	LoginHistoryDao loginHistoryDao; 
	
	@Autowired
	ValidateTokenService validateTokenService;
	
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
				u = userProfileService.findByUsernameAndPassword(userProfile.getUsername(), userProfile.getPassword());
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

	@Override
	public LoginHistory findFirstByUserProfileIdOrderByCreateDateDesc(Long id) {
		// TODO Auto-generated method stub
		 LoginHistory loginHistory = loginHistoryDao.findFirstByUserProfileIdOrderByCreateDateDesc(id);
		 if(loginHistory != null) {
			 log.info(LogMsg.infoLog("LoginHistory", new String[] {loginHistory.toString()}));
		 }
		 return loginHistory;
	}

	@Override
	public LoginHistory saveHistory(Long userProfileId, String token) {
		// TODO Auto-generated method stub
		LoginHistory loginHistory = new LoginHistory();
		loginHistory.setCreateDate(new Date());
		loginHistory.setExpirationTime(JwtUtils.EXPIRATION_TIME);
		loginHistory.setToken(token);
		loginHistory.setUserProfileId(userProfileId);
		return loginHistoryDao.save(loginHistory);
	}


}
