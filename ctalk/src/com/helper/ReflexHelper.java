package com.helper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import com.db.letdb.entity.Json;

/**
 * @created: 2011-11-18
 * @author : jias chao<lino.chao@gmail.com>
 */
public class ReflexHelper {

	
	public static Object instance(Json json) {
		Class clazz = null;
		Object target = null;
		try{
			clazz = Class.forName(json.getClassName());
			target = clazz.newInstance();
			Set<String> fields = json.getFields().keySet();
			for (String field : fields) {
				if (!"class".equals(field)){
					String setMethodStr = "set"+field.substring(0, 1).toUpperCase()+field.substring(1);
					Field fd = clazz.getDeclaredField(field);
					Class[] parameterTypes = new Class[]{fd.getType()};
					Method method = clazz.getMethod(setMethodStr, parameterTypes);
					Object value = ReflexHelper.getAdptType(fd.getType().getName(), json.getFields().get(field));
					Object[] args = new Object[]{value};
					method.invoke(target, args);
				}
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}catch(InstantiationException e){
			e.printStackTrace();
			return null;
		}catch(IllegalAccessException e){
			e.printStackTrace();
			return null;
		}catch(NoSuchFieldException e){
			e.printStackTrace();
			return null;
		}catch(NoSuchMethodException e){
			e.printStackTrace();
			return null;
		}catch(SecurityException e){
			e.printStackTrace();
			return null;
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			return null;
		}catch(InvocationTargetException e){
			e.printStackTrace();
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return target;
	}
	
	public static Object getAdptType(String type,Object value){
		if (Long.class.getName().equals(type)){
			return  Long.valueOf(value.toString());
		}else if(String.class.getName().equals(type)){
			return (String)value;
		}else if(Integer.class.getName().equals(type)){
			return Integer.valueOf(value.toString());
		}else if(Byte.class.getName().equals(type)){
			return Byte.valueOf(value.toString());
		}else if(Short.class.getName().equals(type)){
			return Short.valueOf(value.toString());
		}else if(Float.class.getName().equals(type)){
			return Float.valueOf(value.toString());
		}else if(Double.class.getName().equals(type)){
			return Double.valueOf(value.toString());
		}
		return null;
	}
}
