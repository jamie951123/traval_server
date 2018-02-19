package com.jamie.travel.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.jwt.security.ValidateTokenService;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.service.UserProfileService;
import com.jamie.travel.table.model.UserProfile;
import com.jamie.travel.type.Role;

@RequestMapping(value = TokenObject.MAINAPI+"/authentication/registration")
@Controller
public class RegistrationController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserProfileService userProfileService;

	@Autowired 
	private ValidateTokenService validateTokenService;
	
	@Transactional(rollbackFor = Exception.class)
	@PostMapping(value = "/general",produces="application/json;charset=UTF-8")
	public @ResponseBody UserProfile general(HttpServletRequest request, HttpServletResponse response, @RequestBody UserProfile userProfile){
		LogMsg.infoLog("RegistrationController" , new String[] {"[UserProfile]-[save]-User Response(JSON) : ", userProfile.toString()});
		try{
			Map<String,Object> token = validateTokenService.split_registration_token(request.getHeader(TokenObject.HEADER_REGISTRATION));
			String token_registration_key = String.valueOf(token.get("registerKey"));
			
			if(token_registration_key !=null && userProfile!=null && ObjectUtils.isNotNullEmpty(userProfile.getUsername()) && ObjectUtils.isNotNullEmpty(userProfile.getPassword())){
				if(userProfileService.countByUserName(userProfile.getUsername()) >0){
					System.out.print("The Username is existing");
					log.warn(LogMsg.warnLog("RegistrationController", new String[] {"The Username is existing"}));
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The Username is existing");
					return null;
				}
				//	Role Checking			
				if(TokenObject.ADMIN_SECRET.equals(token_registration_key)) {
					userProfile.setRole(Role.ADMIN);
				}else if(TokenObject.ANDROID_SECRET.equals(token_registration_key) || TokenObject.IOS_SECRET.equals(token_registration_key) || TokenObject.WEB_SECRET.equals(token_registration_key)){
					userProfile.setRole(Role.GENERAL);
				}else {
					log.warn(LogMsg.warnLog("RegistrationController", new String[] {"Wrong Role"}));
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong Role");
					return null;
				}
				//Save User
				UserProfile response_userprofile = userProfileService.save(userProfile);
				log.info(LogMsg.infoLog("RegistrationController", new String[] {"[UserProfile]-[save]-User Response(JSON) : ", response_userprofile.toString()}));
				return response_userprofile;
			}else{
				log.warn(LogMsg.warnLog("RegistrationController", new String[] {"Apply Reject because without username or password"}));
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Apply Reject because without username or password");
			}
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		return null;	
	}
	
}
