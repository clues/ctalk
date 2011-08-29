package com;

import java.io.IOException;
import java.util.Iterator;

public class GroupHander {

	
	public static void flush2group(String str) throws IOException{
		for (Iterator<String> it = User.onlinerMap.keySet().iterator();it.hasNext();){
			User user = (User)User.onlinerMap.get(it.next());
			if (user.getState() != -1){
				user.getSock().getOutputStream().write((str+"\r\ncTalk>").getBytes());
			}
		}
	}
	
	public static boolean flush2One(String name,String str) throws IOException{
		User user = (User)User.onlinerMap.get(name);
		if (user == null){
			return false;
		}else{
			user.getSock().getOutputStream().write((str+"\r\ncTalk>").getBytes());
			return true;
		}
	}
}
