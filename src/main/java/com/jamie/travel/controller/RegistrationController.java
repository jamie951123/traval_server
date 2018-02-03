package com.jamie.travel.controller;

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
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.service.UserProfileService;

@RequestMapping(value = TokenObject.MAINAPI+"/authentication/registration")
@Controller
public class RegistrationController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserProfileService userProfileService;

	@Transactional(rollbackFor = Exception.class)
	@PostMapping(value = "/general",produces="application/json;charset=UTF-8")
	public @ResponseBody UserProfile general(HttpServletResponse response, @RequestBody UserProfile userProfile){
		log.info("[UserProfile]-[save]-User Request(JSON) : "+ userProfile);
		try{
			if(userProfile!=null && ObjectUtils.isNotNullEmpty(userProfile.getUsername()) && ObjectUtils.isNotNullEmpty(userProfile.getPassword())){
				if(userProfileService.countByUserName(userProfile.getUsername()) >0){
					System.out.print("The Username is existing");
					log.warn(LogMsg.warnLog("RegistrationController", new String[] {"The Username is existing"}));
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The Username is existing");
					return null;
				}
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
