package com.jamie.travel.jwt.security;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.exception.TokenValidationException;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.Role;
import com.jamie.travel.service.UserProfileService;
import com.jamie.travel.type.TokenType;

@Service
public class ValidateTokenServiceImpl implements ValidateTokenService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	UserProfileService userProfileService;

	@Override
	public boolean validate_login_general(String token) {
		// TODO Auto-generated method stub
		Map<String,Object> body = JwtUtils.validateToken(token,TokenObject.LOGIN_SECRET);
		try{
			String iss = (String) (body.get("iss"));
			String sub = (String) (body.get("sub"));
			long iat = (long) (body.get("iat"));
			long expired = (long) (body.get("expired"));
			Role role = Role.valueOf((String)(body.get("role")));
			
			checkTokenThrowException(iss,sub,iat,expired,role);

//			19/9/2017 -- Future may be encode the role 
			if(Role.GENERAL == role){
				return true;
			}else{
				throw new TokenValidationException("[Token] -- Account do not have permission");
			}
			
		}catch (Exception e){
			throw new TokenValidationException(e.getMessage());
		}
	}

	//	Registration
	@Override
	public boolean validate_registration_token(String token) {
		// TODO Auto-generated method stub
		Map<String,Object> body = JwtUtils.validateToken(token,TokenObject.REGISTRATION_SECRET);
		try{
			TokenType tokenType = TokenType.valueOf((String)body.get("token_type"));
			
			if(tokenType != null && TokenType.APPLY == tokenType){
				return true;
			}else {
				throw new TokenValidationException("[Token] -- Registration Token is wrong");
			}
		}catch (Exception e){
			throw new TokenValidationException(e.getMessage());
		}
	}
	
	@Override
	public Map<String, Object> split_registration_token(String token) {
		// TODO Auto-generated method stub
		return JwtUtils.validateToken(token,TokenObject.REGISTRATION_SECRET);
	}
	
	
	private void checkTokenThrowException(String iss, String sub,long iat,long expired,Role role){
		if(ObjectUtils.isNotNullEmpty(iss)){
			log.info("iss: " + iss);
		}else{
			throw new TokenValidationException("[Token] -- without iss");
		}
		
		if(ObjectUtils.isNotNullEmpty(sub)){
			log.info(sub);
		}else{
			throw new TokenValidationException("[Token] -- without sub");
		}
		
		if(!Objects.isNull(iat)){
			log.info("iat: " + iat);
		}else{
			throw new TokenValidationException("[Token] -- without iat");
		}
		
		if(!Objects.isNull(expired)){
			log.info("expired: " + expired);
		}else{
			throw new TokenValidationException("[Token] -- without expired");
		}
		
		if(role != null){
			log.info("role: " + role);
		}else{
			throw new TokenValidationException("[Token] -- without role");
		}
	}

	@Override
	public String token_getPartyId(String token) {
		// TODO Auto-generated method stub
		Map<String,Object> body = JwtUtils.validateToken(token,TokenObject.LOGIN_SECRET);
		String sub = (String) (body.get("sub"));
		if(ObjectUtils.isNotNullEmpty(sub)) {
			return sub;
		}else {
			log.error(LogMsg.errLog("ValidateTokenService", new String[] {"token_getPartyId","Empty"}));
			return null;
		}
	}
}
