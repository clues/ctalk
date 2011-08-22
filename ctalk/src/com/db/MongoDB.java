package com.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;

public class MongoDB {
	private String ip;
	private int port;
	private static DB db = null;
	private static MongoDB mogdb = null;;
	private MongoDB(){
		this.ip = "localhost";
		this.port = 27017;
	}
	public MongoDB(String ip,int port){
		this.ip = ip;
		this.port = port;
	}

	public void connect(){
		try{
			if (db == null){
				Mongo mongo = null;
				mongo  = new Mongo( new ServerAddress( this.ip, this.port) );
				db = mongo.getDB("ctalk");
			}
		}catch(Exception e){
			e.printStackTrace();
			db = null;
			return;
		}
	}
	
	public void save(IBsonObject obj){
		DBObject bson = obj.warrp();
		String key = obj.getClass().getName().replace(".", "_");
		DBCollection collection = db.getCollection(key);
		collection.insert(bson);
	}
	
	public List<Object> findAll(IBsonObject obj){
		String key = obj.getClass().getName().replace(".", "_");
		DBCollection collection = db.getCollection(key);
		DBCursor cur = collection.find(obj.warrp());
		List<Object> list = new ArrayList<Object>();
		while(cur.hasNext()){
			list.add(obj.unWarrp(cur.next()));
		}
		cur.close();
		return list;
	}
	
	public Object findOne(IBsonObject obj){
		String key = obj.getClass().getName().replace(".", "_");
		DBCollection collection = db.getCollection(key);
		DBObject bson = collection.findOne(obj.warrp()); 
		if (bson != null){
			return obj.unWarrp(bson);
		}else{
			return null;
		}
	}

	public BasicDBObject warrp2bson(Object obj){
		BasicDBObject bson = new BasicDBObject();
		return bson;
	}
	
	
	public static MongoDB getInstance(){
		if(mogdb == null){
			mogdb = new MongoDB();
		}
		if (db == null){
			mogdb.connect();
		}
		return mogdb;
	}
	
	public static void main(String args[]){
		User user = new User();
		user.setIp("192.168.1.1");
		user.setName("zhangsan");
//		
//		User user1 = new User();
//		user1.setIp("192.168.203.1");
//		user1.setName("lisi");		
//		db.save(user);
//		db.save(user);
//		List list = db.findAll(user);
//		for (int i=0;i<list.size();i++){
//			User useer = (User)list.get(i);
//			System.out.println(i+": "+ useer.getIp()+": "+useer.getName());
//		}
		
		Object bson = MongoDB.getInstance().findOne(user);
		if (bson != null){
			User userrr =(User)bson;
		}else{
			;
			;
		}
		
	}
}
	

	



