package com.jamie.travel.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.jamie.travel.type.TokenType;

public class TokenModel {
	
	private String platform;
	@Enumerated(EnumType.STRING)
	private TokenType tokenType;
	
	//Resgister
	private String resgisterKey;


	public String getResgisterKey() {
		return resgisterKey;
	}

	public void setResgisterKey(String resgisterKey) {
		this.resgisterKey = resgisterKey;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
	
	
	
}
