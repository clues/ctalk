package com.db.letdb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @created: 2011-11-16
 * @author : jias chao<lino.chao@gmail.com>
 */
public class TestEntityKey {

	@Test
	public void testKeyAndValue() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Example ex = new Example();
		ex.setId(new Long(11));
		ex.setName("jias chao");
		
		Method[] methods = ex.getClass().getDeclaredMethods(); 
		String mName="";
		String mValue="";
		for (Method method : methods) {
			boolean hasAnnotation = method.isAnnotationPresent(EntityKey.class);
			if (hasAnnotation){
				//EntityKey ek = method.getAnnotation(EntityKey.class);
				mName = method.getName();
				mValue = method.invoke(ex, new Object[]{}).toString();
				break;
			}
		}
		Assert.assertEquals("getName", mName);
		Assert.assertEquals("jias chao", mValue);
	}

}
