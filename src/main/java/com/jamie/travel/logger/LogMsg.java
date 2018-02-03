package com.jamie.travel.logger;

public class LogMsg {

//	Log Category
	public static String Resgister =  "Resgister";
	
	public static String infoLog(String category,String[] messages) {
		String log = "[ "+category+" ] ";
		if(messages != null ) {
			for(String m : messages) {
				log = log +  "-["+m+"] ";
			}
		}
		return log;
	}
	
	public static String warnLog(String category,String[] messages) {
		String log = "[ "+category+" ] ";
		if(messages != null ) {
			for(String m : messages) {
				log = log +  "-["+m+"] ";
			}
		}
		return log;
	}
	
	public static String errLog(String category,String[] messages) {
		String log = "[ "+category+" ] ";
		if(messages != null ) {
			for(String m : messages) {
				log = log +  "-["+m+"] ";
			}
		}
		return log;
	}
	
}
