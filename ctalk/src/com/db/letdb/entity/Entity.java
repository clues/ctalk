package com.db.letdb.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @created: 2011-11-16
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Entity {

	@Override
	public String toString(){
		Class clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String key = null;
		String getMethodName = null;
		String setMethodName = null;
		Method getMethod = null;
		Method setMethod = null;
		Map<String,Object> fieldValues = new HashMap<String,Object>();
		for (Field field : fields) {
			if (field.isAnnotationPresent(EntityKey.class)){
				key = field.getName();
			}
			getMethodName = "get" + field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
			setMethodName = "set" + field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
			try{
				getMethod = clazz.getMethod(getMethodName, null);
				/*
				 * Field.class
				 * getType(): this filed modify class
				 * getClass(): java.lang.reflect.Field
				 * getDeclaringClass():class where field in
				 * 
				 */
				setMethod = clazz.getMethod(setMethodName, new Class[]{field.getType()});
				if (setMethod != null && getMethod != null){
					fieldValues.put(field.getName(), getMethod.invoke(this, null));
				}
			}catch(NoSuchMethodException e){
				e.printStackTrace();
			}catch(InvocationTargetException e){
				e.printStackTrace();
			}catch(IllegalAccessException e){
				e.printStackTrace();
			}
		}
		return entity2json(key,fieldValues);
	}
	
	private String entity2json(String key,Map<String,Object> map){
		StringBuffer sb = new StringBuffer();
		if (key != null){
			sb.append(key).append(":");
		}
		sb.append("{");
		if (map != null && map.size() > 0){
			for (String name : map.keySet()) {
				sb.append(name).append(":").append(map.get(name));
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Entity().toString());
	}
}
