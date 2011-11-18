package com.db.letdb.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.helper.ReflexHelper;


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
		fieldValues.put("class", clazz.toString().split(" ")[1]);
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
		return entity2jsonStr(key,fieldValues);
	}
	
	private String entity2jsonStr(String key,Map<String,Object> map){
		StringBuffer sb = new StringBuffer();
		if (key != null){
			sb.append(key).append(":");
		}
		sb.append("{");
		if (map != null && map.size() > 0){
			for (String name : map.keySet()) {
				sb.append("\"").append(name).append("\":\"").append(map.get(name)==null?"":map.get(name)).append("\",");
			}
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("}");
		return sb.toString();
	}
}
