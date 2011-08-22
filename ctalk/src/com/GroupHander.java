package com;

import java.io.IOException;
import java.util.Iterator;

public class GroupHander {

	
	public static void flush2group(String str) throws IOException{
		for (Iterator<String> it = User.onlinerMap.keySet().iterator();it.hasNext();){
			User user = (User)User.onlinerMap.get(it.next());
				user.getSock().getOutputStream().write((str+"\r\ncTalk>").getBytes());
		}
	}
	
	public static void flush2One(String str) throws IOException{
		for (Iterator it = User.onlinerMap.keySet().iterator();it.hasNext();){
			User user = (User)User.onlinerMap.get(it.next());
				user.getSock().getOutputStream().write((str+"\r\ncTalk>").getBytes());
		}
	}
}
