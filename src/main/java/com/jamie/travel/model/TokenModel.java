package com.jamie.travel.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.jamie.travel.type.TokenType;

public class TokenModel {
	private String platform;
	private String macAddress;

	@Enumerated(EnumType.STRING)
	private TokenType tokenType;
	
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public TokenType getTokenType() {
		return tokenType;
	}
	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}
	
	
}
