package com.jamie.travel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.service.UserProfileService;

@RequestMapping(value = TokenObject.MAINAPI+"/authentication/user")
@Controller
public class UserProfileController {
	Logger log = LoggerFactory.getLogger(UserProfileController.class);
	
	@Autowired
	private UserProfileService userProfileService; 
	
	@RequestMapping("/hello")
	public ModelAndView firstPage() {
		return new ModelAndView("hello");
	}
	
	@GetMapping(value ="/findAll")
	public @ResponseBody List<UserProfile> findAll(){
		List<UserProfile> userProfiles = userProfileService.findAll();
		log.info("[UserProfile]-[findAll]-User Response() : "+ userProfiles);
		return userProfiles;
	} 
	
////	Save  
//	@Transactional(rollbackFor = Exception.class)
//	@PostMapping(value = "/save",produces="application/json;charset=UTF-8")
//	public @ResponseBody UserProfile save(@RequestBody String userProfile_json){
//		log.info("[UserProfile]-[save]-User Request(JSON) : "+ userProfile_json);
//		System.out.println("Token Successful");
//		return null;
//	}
}
