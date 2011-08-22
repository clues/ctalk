package com.db;

import java.util.ArrayList;
import java.util.List;

public class BasicDao {
	
	public List<Object> find(IBsonObject obj){
		List<Object> list = new ArrayList<Object>();
		list = MongoDB.getInstance().findAll(obj);
		return list;
	}
}
