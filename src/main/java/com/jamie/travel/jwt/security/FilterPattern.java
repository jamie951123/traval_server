package com.jamie.travel.jwt.security;

public class FilterPattern {
	
	//Authentication
	public static String Pattern_UserProfile_Controller = TokenObject.MAINAPI + "/authentication/user/**";
	public static String Pattern_Registration_Controller = TokenObject.MAINAPI + "/authentication/registration/**";
	public static String Pattern_Trip_Controller = TokenObject.MAINAPI + "/authentication/trip/**";
	public static String Pattern_Image_Controller = TokenObject.MAINAPI + "/authentication/images/**";

	//
}
