package com.jamie.travel.jwt.security;

public class TokenObject {

	//Api
	public static final String MAINAPI = "/travel";  
	
	//Registration Secret
	public static final String REGISTRATION_SECRET = "JAMIE_REGISTRATION_SECRET";  

	//Registration Key
	public static final String ANDROID_SECRET = "JAMIE_ANDROID_SECRET";  
	public static final String IOS_SECRET = "JAMIE_IOS_SECRET";  
	public static final String WEB_SECRET = "JAMIE_WEB_SECRET";  
	public static final String ADMIN_SECRET = "JAMIE_ADMIN_SECRET";  
	
	//	Login Secert
	public static final String LOGIN_SECRET = "JAMIE_LOGIN_SECRET"; 
	
	public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_REGISTRATION = "Registration";
    public static final String HEADER_USERTOKEN = "UserToken";
    
}
