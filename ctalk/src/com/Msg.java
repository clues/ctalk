package com;

import com.db.IBsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Msg implements IBsonObject{

	private String msg;
	
	private String username;
	
	private String time;
	
	
	public DBObject warrp(){
		BasicDBObject doc = new BasicDBObject();
		if (this.username != null){
			doc.put("username", username);
		}
		if (this.time != null){
			doc.put("time", time);
		}
		if (this.msg != null){
			doc.put("msg", msg);
		}
		return doc;
	}
	
	public Object unWarrp(DBObject bson){
		Msg msg = null;
		if (bson != null){
			msg = new Msg();
			if (bson.get("username") != null){
				msg.setUsername(bson.get("username").toString());
			}
			if (bson.get("time") != null){
				msg.setTime(bson.get("time").toString());
			}
			if (bson.get("msg") != null){
				msg.setMsg(bson.get("msg").toString());
			}
		}
		return msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
