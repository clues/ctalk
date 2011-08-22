package com;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.db.IBsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class User implements IBsonObject{

	private String ip;
	private int port =0;
	private String name;
	private String password;
	private int state = -1; //-1#off  0#on
	private Socket sock;


	public static Map<String,User> onlinerMap = new HashMap<String,User>();
	public static Map<Socket,String>onlinerSocketMap = new HashMap<Socket,String>();
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public Socket getSock() {
		return sock;
	}
	public void setSock(Socket sock) {
		this.sock = sock;
	}
	
	public static void put(User user){
		onlinerMap.put(user.getName(), user);
		onlinerSocketMap.put(user.getSock(), user.getName());
	}
	
	public static User get(String name){
		return onlinerMap.get(name);
	}
	
	public static User removeUserBySocket(Socket sock){
		String name = onlinerSocketMap.get(sock);
		User user = onlinerMap.get(name);
		if (name != null){
			onlinerMap.remove(name);
			onlinerSocketMap.remove(sock);
			return user;
		}else{
			return null;
		}
	}
	
	public static User getUserBySocket(Socket sock){
		String name = onlinerSocketMap.get(sock);
		User user = onlinerMap.get(name);
		if (name != null && user != null){
			return user;
		}
		return null;
		
	}
	
	public static String getOnlinerStr(){
		StringBuffer sb = new StringBuffer("*******current onliners*********\r\n");
		for (Iterator it = onlinerMap.keySet().iterator();it.hasNext();){
			sb.append(it.next().toString()).append("\r\n");
		}
		sb.append("*******current onliners*********");
		return sb.toString();
	}
	
	public DBObject warrp(){
		BasicDBObject doc = new BasicDBObject();
		if (this.name != null){
			doc.put("name", name);
		}
		if (this.password != null){
			doc.put("password", password);
		}
		if (this.ip != null){
			doc.put("ip", ip);
		}
		if (this.port != 0){
			doc.put("port", port);
		}

		return doc;
	}
	
	public IBsonObject unWarrp(DBObject bson){
		User user = null;
		if (bson != null){
			user = new User();
			if (bson.get("ip") != null){
				user.setIp(bson.get("ip").toString());
			}
			if (bson.get("name") != null){
				user.setName(bson.get("name").toString());
			}
		}
		return user;
	}
	
}
