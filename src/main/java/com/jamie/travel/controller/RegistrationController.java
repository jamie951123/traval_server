package com.jamie.travel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamie.travel.core.util.ObjectUtils;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.service.UserProfileService;

@RequestMapping(value="/secret/authentication/registration")
@Controller
public class RegistrationController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserProfileService userProfileService;

	@Transactional(rollbackFor = Exception.class)
	@PostMapping(value = "/general",produces="application/json;charset=UTF-8")
	public @ResponseBody UserProfile general(@RequestBody UserProfile userProfile){
		log.info("[UserProfile]-[save]-User Request(JSON) : "+ userProfile);
		try{
			if(userProfile!=null && ObjectUtils.isNotNullEmpty(userProfile.getUsername()) && ObjectUtils.isNotNullEmpty(userProfile.getPassword())){
				if(userProfileService.countByUserName(userProfile.getUsername()) >0){
					System.out.print("The Username is existing");
					return null;
				}
				UserProfile response_userprofile = userProfileService.save(userProfile);
				log.info("[UserProfile]-[save]-User Response(JSON) : "+ response_userprofile);
				return response_userprofile;
			}else{
				throw new RuntimeException("Apply Reject because without username or password");
			}
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}	
	}
	
}
