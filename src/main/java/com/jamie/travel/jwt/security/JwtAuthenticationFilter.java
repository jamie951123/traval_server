package com.jamie.travel.jwt.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jamie.travel.core.util.ObjectUtils;
import com.jamie.travel.exception.TokenValidationException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	Logger log = LoggerFactory.getLogger(this.getClass());

	 private static final PathMatcher pathMatcher = new AntPathMatcher();

	 @Autowired
	 public ValidateTokenService validateTokenService;
	 
	    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
	           if(pathMatcher.match(FilterPattern.Pattern_UserProfile_Controller, request.getServletPath())) {
	               String token = request.getHeader(JwtUtil.HEADER_STRING);
	               validateTokenService.validate_login_general(token);
	           }else if(pathMatcher.match(FilterPattern.Pattern_Registration_Controller, request.getServletPath())) {
	        	   String token = request.getHeader(JwtUtil.HEADER_REGISTRATION);
	        	   validateTokenService.validate_registration_token(token);
	           }
	       } catch (Exception e) {
	           response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
	           throw e;
	       }
		try{
	       filterChain.doFilter(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}

	
}
