package com.db.letdb.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @created: 2011-11-17
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Json {

	private String key;
	
	private Object keyValue;
	
	private String className;
	
	private Map<String, Object> fields = null;
	
	public static Json instance(String jsonStr){
		Json json = null;
		if (jsonStr != null && jsonStr.length() > 0){
			json = new Json();
			Map<String, Object> fields =  new HashMap<String, Object>();
			int bstart = jsonStr.indexOf('{');
			if (bstart > 0){
				json.setKey(jsonStr.substring(0, bstart-1));
			}else{
				bstart = 0;
			}
			String value = jsonStr.substring(bstart+1,jsonStr.length()-1);
			String[] tokens = value.split("\",\"");
			for(int i=0 ;i<tokens.length;i++){
				String[] keyValue = tokens[i].split("\":\"");
				if(i == 0){
					fields.put(keyValue[0].substring(1), keyValue[1]);
				}else if (i == tokens.length-1){
					fields.put(keyValue[0], keyValue[1].substring(0,keyValue[1].length()-1));
				}else{
					fields.put(keyValue[0], keyValue[1]);
				}
			}
			json.setFields(fields);
			json.setClassName(fields.get("class").toString());
			json.setKeyValue(fields.get(json.getKey()));
		
		}
		return json;
	}
	
	public String getProperty(String key){
		if (this.fields == null){
			return null;
		}
		return this.fields.get(key).toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	public Object getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Object keyValue) {
		this.keyValue = keyValue;
	}
	
	
}
