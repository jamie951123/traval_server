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
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.APIModel;
import com.jamie.travel.model.UserProfile;

@RequestMapping(value = TokenObject.MAINAPI + "/authentication/photos")
@Controller
public class PhotoController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PhotoService photoService;

	@Autowired
	private ValidateTokenService validateTokenService;

	@Transactional(rollbackFor = Exception.class)
	@GetMapping(value = "/upload", produces = "text/plain;charset=UTF-8")
	public @ResponseBody APIModel upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			UserProfile u = validateTokenService.token_getUserProfile(request.getHeader(TokenObject.HEADER_USERTOKEN));
			if (u != null && ObjectUtils.isNotNullEmpty(u.getPartyId())) {
//				photoService.uploadPhoto(u.getPartyId(), name, request, response);

			} else {
				log.error(LogMsg.errLog("download", new String[] { "Wrong Token" }));
			}
		} catch (Exception e) {
			log.error(LogMsg.errLog("download", new String[] { e.getMessage() }));
		}
		return new APIModel(true,null,null);
	}

	@Transactional(rollbackFor = Exception.class)
	@PostMapping(value = "/download", produces = "image/*")
	public @ResponseBody void download(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String name) {
		try {
			UserProfile u = validateTokenService.token_getUserProfile(request.getHeader(TokenObject.HEADER_USERTOKEN));
			if (u != null && ObjectUtils.isNotNullEmpty(u.getPartyId())) {
				photoService.downloadPhoto(u.getPartyId(), name, request, response);

			} else {
				log.error(LogMsg.errLog("download", new String[] { "Wrong Token" }));
			}
		} catch (Exception e) {
			log.error(LogMsg.errLog("download", new String[] { e.getMessage() }));
		}
	}

}
