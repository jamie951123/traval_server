package com.jamie.travel.jwt.security;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jamie.travel.exception.TokenValidationException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
	static final String iss = "System";
	static final long REGISTRATION_EXPIRATION_TIME = 1800*1000; //expire date
    static final long EXPIRATION_TIME = 3600*1000; // 1 hour
    public static final String TOKEN_PREFIX = "Token:";
    
    
    public static String generateToken(LinkedHashMap<String, Object> map,String secret) {
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration((Date)map.get("expired"))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return jwt;
    }

    public static Map<String,Object> validateToken(String token,String secret)  {
        if (token != null) {
            // parse the token.
            Map<String,Object> body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return body;
            //[Token] -- Token is expired
        }else{
            throw new TokenValidationException("Missing token");
        }
    }

}