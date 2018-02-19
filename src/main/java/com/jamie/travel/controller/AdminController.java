package com.jamie.travel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jamie.travel.admin.service.AdminService;
import com.jamie.travel.core.utils.GsonUtils;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.table.model.LoginHistory;
import com.jamie.travel.table.model.UserProfile;

import java.lang.reflect.Type;

@RequestMapping(value = TokenObject.MAINAPI+"/admin")
@Controller
public class AdminController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AdminService adminService;
	
	@GetMapping(value ="/findAll")
	public @ResponseBody List<UserProfile> findAll(){
		List<UserProfile> userProfiles = adminService.findAll();
		log.info(LogMsg.infoLog("AdminController", new String[] {"[UserProfile]-[findAll]-User Response() : ",userProfiles.toString()}));
//		Type listType = new TypeToken<List<UserProfile>>() {}.getType();
//		String gson = GsonUtils.getGson().toJson(userProfiles, listType);
		return userProfiles;
	} 
	
	@GetMapping(value ="/findAllHistory")
	public @ResponseBody List<LoginHistory> findAllHistory(){
		List<LoginHistory> loginHistorys = adminService.findAllHistory();
		return loginHistorys;
	} 
	
}
