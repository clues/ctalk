package com.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;

public class MongoDB {
	private static final Log log = LogFactory.getLog(MongoDB.class);
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
				log.info("try connect to db: "+this.ip+" ["+this.port+"]");
				mongo  = new Mongo( new ServerAddress( this.ip, this.port) );
				db = mongo.getDB("ctalk");
				if (!!!db.isAuthenticated()){
					log.info("connect db success...");
				}
			}
		}catch(Exception e){
			log.error("connect db faild!!!!!");
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
	
}
	

	



