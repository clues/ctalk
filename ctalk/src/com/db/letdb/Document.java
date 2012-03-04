package com.db.letdb;

import com.alibaba.fastjson.JSON;


/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Document {
	
	private Long id;

	protected String getJsonStr(){
		 String jstr = JSON.toJSONString(this);
		 return jstr;
	}
	
	protected byte[] getBytes(){
		String jstr = this.getJsonStr();
		return jstr.getBytes();
	}
	
	public static Document getInstance(String jstr,String clazz) throws ClassNotFoundException{
		Document doc = null;
		if (clazz.equals(ExampleEntity.class.getName())){
			doc = JSON.parseObject(jstr,ExampleEntity.class);
		}else if(clazz.equals(Document.class.getName())){
			doc = JSON.parseObject(jstr,Document.class);
		}
		return doc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
