package com.jamie.travel.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.jwt.security.GenerateTokenService;
import com.jamie.travel.jwt.security.JwtUtils;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.LoginHistory;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.service.LoginService;

@RequestMapping(value = TokenObject.MAINAPI + "/login")
@Controller
public class LoginController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LoginService loginService;

	@Autowired
	GenerateTokenService generateTokenService;

	@Transactional(rollbackFor = Exception.class)
	@PostMapping("/general")
	public void login(HttpServletResponse response, @RequestBody final UserProfile userProfile) throws IOException {
		UserProfile u = loginService.generalChecking(userProfile);
		try {
			if (u != null && u.getUserProfileId() != null) {
				//

				String token = generateTokenService.login_general_token(u);
				if (ObjectUtils.isNotNullEmpty(token)) {
					loginService.saveHistory(u.getUserProfileId(), token);
					response.addHeader(TokenObject.HEADER_STRING, JwtUtils.TOKEN_PREFIX + " " + token);
					log.info(LogMsg.infoLog("LoginController",
							new String[] { "UserId : " + u.getUserProfileId(), "Using New token" }));
				} else {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Server Error");
					log.error(LogMsg.errLog("LoginController", new String[] { "Server Error" }));
				}

			} else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong Account");
				log.error(LogMsg.errLog("LoginController", new String[] { "Wrong Account" }));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
