package com.jamie.travel.jwt.security;

import java.util.Date;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.Role;
import com.jamie.travel.model.TokenModel;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.type.TokenType;

@Service
public class GenerateTokenServiceImpl implements GenerateTokenService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public String login_general_token(UserProfile userProfile) {
		// TODO Auto-generated method stub
		long long_time = System.currentTimeMillis();
		Date token_iat = new Date(new Long(long_time));
		Date token_exp = new Date(long_time + JwtUtils.EXPIRATION_TIME);
		Role role = userProfile.getRole();
		LinkedHashMap<String,Object> map = new LinkedHashMap<>();
		map.put("iss", JwtUtils.iss);
		map.put("sub", userProfile.getUsername());
		map.put("iat", token_iat);
		map.put("expired", token_exp);
		map.put("role", role);
		return JwtUtils.generateToken(map,TokenObject.LOGIN_SECRET);
	}

	@Override
	public String registration_token(TokenModel tokenModel) {
		// TODO Auto-generated method stub
		if(tokenModel == null || ObjectUtils.isNullEmpty(tokenModel.getResgisterKey())) {
			
			throw new RuntimeException("");
		}
		long long_time = System.currentTimeMillis();
		try{
			LinkedHashMap<String,Object> map = new LinkedHashMap<>();
			map.put("token_type", TokenType.APPLY);
			map.put("resgisterKey", tokenModel.getResgisterKey());
			map.put("expired", new Date(long_time + JwtUtils.REGISTRATION_EXPIRATION_TIME));
			return JwtUtils.generateToken(map,TokenObject.REGISTRATION_SECRET);
		}catch(Exception e){
			log.error(LogMsg.errLog(LogMsg.Resgister, new String[] { "Token -- Server Cannot not create Registration_token"}));
//			new RuntimeException("Token -- Server Cannot not create Registration_token");
		}
		return null;
	}

}
