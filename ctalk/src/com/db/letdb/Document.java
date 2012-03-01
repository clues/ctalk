package com.db.letdb;

import com.alibaba.fastjson.JSON;

/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Document {
	
	private String id;

	protected String getJsonStr(){
		 String jstr = JSON.toJSONString(this);
		 return jstr;
	}
	
	protected Document getInstance(String jstr){
		Document doc = JSON.parseObject(jstr, Document.class);
		return doc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
