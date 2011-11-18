package com.db.letdb.entity;


import junit.framework.Assert;

import org.junit.Test;

import com.db.letdb.Example;
import com.helper.ReflexHelper;

/**
 * @created: 2011-11-16
 * @author : jias chao<lino.chao@gmail.com>
 */
public class TestEntity {

	@Test
	public void test2String(){
		Example ex = new Example();
		ex.setName("jias chao");
		ex.setId(new Long(123));
		
		System.out.println(ex.toString());
	}
	
	@Test
	public void testJson2entity(){
		String str = "id:{\"id\":\"123\",\"name\":\"jias chao\",\"class\":\"com.db.letdb.Example\"}";
		Json json = Json.instance(str);
		Example entity = (Example)ReflexHelper.instance(json);
		Assert.assertEquals("jias chao", entity.getName());
	}

}
