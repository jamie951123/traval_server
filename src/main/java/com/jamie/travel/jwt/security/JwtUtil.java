package com.jamie.travel.jwt.security;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jamie.travel.exception.TokenValidationException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	static final String iss = "System";
	static final long REGISTRATION_EXPIRATION_TIME = 1800*1000;
    static final long EXPIRATION_TIME = 3600*1000; // 1 hour
    static final String SECRET = "JamieSever";
    public static final String TOKEN_PREFIX = "Token:";
    public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_REGISTRATION = "Registration";
    
    public static String generateToken(LinkedHashMap<String, Object> map) {
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration((Date)map.get("expired"))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    public static Map<String,Object> validateToken(String token) {
        if (token != null) {
            // parse the token.
            Map<String,Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return body;
            //[Token] -- Token is expired
        }else{
            throw new TokenValidationException("Missing token");
        }
    }

}