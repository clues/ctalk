package com.db;

import java.util.Iterator;
import java.util.List;

import com.Msg;
import com.User;

public class MsgService {

	/**
	 * 
	 * @param user
	 * @return  [chao]  2011-11-12 12:23:456:  hello boy
	 */
	public static String getAllMsgByUser(User user){
		Msg msg = new Msg();
		msg.setUsername(user.getName());
		List list = new BasicDao().find(msg);
		StringBuffer sb = new StringBuffer();
		for (Iterator it =list.iterator();it.hasNext();){
			Msg mg = (Msg)it.next();
			sb.append("\r\n[").append(mg.getUsername()).append("] ")
			.append(mg.getTime()).append(":  ").append(mg.getMsg());
		}
		sb.append("\r\n There find: ").append(list.size()).append(" records");
		return sb.toString();
	}
}
