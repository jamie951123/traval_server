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
import com.jamie.travel.image.service.PhotoService;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.jwt.security.ValidateTokenService;
import com.jamie.travel.model.Trip;
import com.jamie.travel.model.UserProfile;

@RequestMapping(value = TokenObject.MAINAPI + "/authentication/photos")
@Controller
public class UploadController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PhotoService imageService;
	
	@Autowired
	private ValidateTokenService validateTokenService;
	
	
	@Transactional(rollbackFor = Exception.class)
	@GetMapping(value = "/upload", produces="text/plain;charset=UTF-8")
	public @ResponseBody String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info(System.getProperty("catalina.home"));
		log.info(System.getProperty("catalina.base"));
		return "ImageController";
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@PostMapping(value = "/download", produces = "application/json;charset=UTF-8")
	public @ResponseBody String download(HttpServletRequest request, HttpServletResponse response, @RequestBody String r)
			throws IOException {
		UserProfile u = validateTokenService.token_getUserProfile(request.getHeader(TokenObject.HEADER_USERTOKEN));

		if (u != null && ObjectUtils.isNotNullEmpty(u.getPartyId())) {
			return imageService.downloadPhoto(u.getPartyId(),"test.jpg");

		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong Token");
			return null;
		}
	}
	
	
}
