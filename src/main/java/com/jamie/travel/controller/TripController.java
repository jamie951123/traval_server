package com.jamie.travel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.jwt.security.ValidateTokenService;
import com.jamie.travel.model.Trip;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.service.TripService;
import com.jamie.travel.service.UserProfileService;

@RequestMapping(value = TokenObject.MAINAPI+"/authentication/trip")
@Controller
public class TripController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private ValidateTokenService validateTokenService;
	
	@Transactional(rollbackFor = Exception.class)
	@GetMapping("/test")
	public @ResponseBody String test() throws IOException {
		return "Pass Token";
	}
	
	@Transactional(rollbackFor = Exception.class)
	@PostMapping(value = "/save",produces="application/json;charset=UTF-8")
	public @ResponseBody UserProfile login(HttpServletRequest request, HttpServletResponse response, @RequestBody Trip trip) throws IOException {
		String partyId = validateTokenService.token_getPartyId(request.getHeader(TokenObject.HEADER_USERTOKEN));
		if(ObjectUtils.isNotNullEmpty(partyId)) {
			UserProfile u = userProfileService.findByPartyId(partyId);
			if(u != null && u.getUserProfileId() != null) {
				tripService.save(u,trip);
			}
			
		}else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong Token");
		}
		return null;
		
	}
}
