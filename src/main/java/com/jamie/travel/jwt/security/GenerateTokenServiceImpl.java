package com.jamie.travel.jwt.security;

import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.jamie.travel.model.Role;
import com.jamie.travel.model.TokenModel;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.type.TokenType;

@Service
public class GenerateTokenServiceImpl implements GenerateTokenService {


	@Override
	public String login_general_token(UserProfile userProfile) {
		// TODO Auto-generated method stub
		long long_time = System.currentTimeMillis();
		Date token_iat = new Date(new Long(long_time));
		Date token_exp = new Date(long_time + JwtUtil.EXPIRATION_TIME);
		Role role = userProfile.getRole();
		LinkedHashMap<String,Object> map = new LinkedHashMap<>();
		map.put("iss", JwtUtil.iss);
		map.put("sub", userProfile.getUsername());
		map.put("iat", token_iat);
		map.put("expired", token_exp);
		map.put("role", role);
		return JwtUtil.generateToken(map);
	}

	@Override
	public String registration_token(TokenModel tokenModel) {
		// TODO Auto-generated method stub
		long long_time = System.currentTimeMillis();
		try{
			LinkedHashMap<String,Object> map = new LinkedHashMap<>();
			map.put("token_type", TokenType.APPLY);
			map.put("macAddress", tokenModel.getMacAddress());
			map.put("expired", new Date(long_time + JwtUtil.REGISTRATION_EXPIRATION_TIME));
			return JwtUtil.generateToken(map);
		}catch(Exception e){
			new RuntimeException("Token -- Server Cannot not create Registration_token");
		}
		return null;
	}

}
