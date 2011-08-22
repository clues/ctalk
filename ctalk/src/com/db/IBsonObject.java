package com.db;

import com.mongodb.DBObject;

public interface IBsonObject {
	
	public DBObject warrp();
	
	public Object unWarrp(DBObject bson);
	
}
