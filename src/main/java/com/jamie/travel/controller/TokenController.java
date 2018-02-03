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
import com.jamie.travel.jwt.security.JwtUtils;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.TokenModel;

@RequestMapping(value = TokenObject.MAINAPI+"/token/request")
@Controller
public class TokenController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GenerateTokenService generateTokenService;

	@PostMapping("/registration")
	public void login(HttpServletResponse response, @RequestBody final TokenModel tokenModel) throws IOException {
		try {
			if (tokenModel != null) {
				if (TokenObject.ANDROID_SECRET.equals(tokenModel.getResgisterKey())) {
					log.info(LogMsg.infoLog(LogMsg.Resgister, new String[] { "Android Application" }));
				} else if (TokenObject.IOS_SECRET.equals(tokenModel.getResgisterKey())) {
					log.info(LogMsg.infoLog(LogMsg.Resgister, new String[] { "Ios Application" }));
				} else if (TokenObject.ADMIN_SECRET.equals(tokenModel.getResgisterKey())) {
					log.info(LogMsg.infoLog(LogMsg.Resgister, new String[] { "Admin Application" }));
				} else {
					log.warn(LogMsg.warnLog(LogMsg.Resgister, new String[] { "Wrong ResgisterKey" }));
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong ResgisterKey");
					return;
				}
				 String token = generateTokenService.registration_token(tokenModel);
				 response.addHeader(TokenObject.HEADER_REGISTRATION, JwtUtils.TOKEN_PREFIX + " " + token);
			} else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong credentials");
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			log.error(LogMsg.errLog(LogMsg.Resgister, new String[] { e.getMessage() }));
		}
	}
}
