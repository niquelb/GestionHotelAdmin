package utils;

import java.util.HashMap;

public class TempUserDB {
	private static final HashMap<String,String> LOGIN_INFO = new HashMap<String,String>();

	public static HashMap<String,String> getInfo(){
		LOGIN_INFO.put("admin","admin");
		LOGIN_INFO.put("test","password");
		
		return LOGIN_INFO;
	}


}
