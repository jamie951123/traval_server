package com.jamie.travel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamie.travel.jwt.security.GenerateTokenService;
import com.jamie.travel.jwt.security.JwtUtil;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.service.LoginService;

@RequestMapping(value="/secret/login")
@Controller
public class LoginController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	GenerateTokenService generateTokenService;
	
	 @PostMapping("/general")
	    public void login(HttpServletResponse response,
	                      @RequestBody final UserProfile userProfile) throws IOException {
		 UserProfile u = loginService.generalChecking(userProfile);
	        if(u != null) {
	            String token = generateTokenService.login_general_token(u);
	            response.addHeader(JwtUtil.HEADER_STRING, JwtUtil.TOKEN_PREFIX + " " + token);
	        }else
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong credentials");
	    }
	  
}
