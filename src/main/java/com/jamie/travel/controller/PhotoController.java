package com.jamie.travel.controller;

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
import com.jamie.travel.image.service.PhotoService;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.jwt.security.ValidateTokenService;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.APIModel;
import com.jamie.travel.table.model.UserProfile;
import com.jamie.travel.type.PhotoAction;

@RequestMapping(value = TokenObject.MAINAPI + "/authentication/photos")
@Controller
public class PhotoController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PhotoService photoService;

	@Autowired
	private ValidateTokenService validateTokenService;

	@Transactional(rollbackFor = Exception.class)
	@PostMapping(value = "/upload", produces = "application/json;charset=UTF-8")
	public @ResponseBody APIModel upload(HttpServletRequest request, @RequestBody String fileUpload) throws Exception {
		try {
			UserProfile u = validateTokenService.token_getUserProfile(request.getHeader(TokenObject.HEADER_USERTOKEN));
			if (u != null && ObjectUtils.isNotNullEmpty(u.getPartyId())) {
				String base64Image = fileUpload.split(",")[1];

//				String filePath = photoService.uploadPhoto(base64Image, u.getPartyId());
				if (photoService.uploadPhoto(base64Image, u.getPartyId())) {
					return new APIModel(true, "successful");
				} else
					return new APIModel(false, "fail");

			} else {
				log.error(LogMsg.errLog("PhotoController", new String[] { "Wrong Token" }));
				return new APIModel(false, "Wrong Token");
			}
		} catch (Exception e) {
			log.error(LogMsg.errLog("PhotoController", new String[] { e.getMessage() }));
			return new APIModel(false, e.getMessage());
		}
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
