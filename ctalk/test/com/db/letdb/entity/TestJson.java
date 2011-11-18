package com.db.letdb.entity;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @created: 2011-11-17
 * @author : jias chao<lino.chao@gmail.com>
 */
public class TestJson {

	@Test
	public void testInstanceWithKey() {
		String str = "id:{\"id\":\"123\",\"name\":\"jias chao\",\"class\":\"com.db.letdb.Example\"}";
		Json json = Json.instance(str);
		Assert.assertEquals("jias chao", json.getProperty("name"));
		Assert.assertEquals("id", json.getKey());
		Assert.assertEquals("123", json.getKeyValue());
		Assert.assertEquals("com.db.letdb.Example", json.getClassName());
	}
	
	@Test
	public void testInstanceWithoutKey() {
		String str = "{\"id\":\"123\",\"name\":\"jias chao\",\"class\":\"com.db.letdb.Example\"}";
		Json json = Json.instance(str);
		Assert.assertEquals("jias chao", json.getProperty("name"));
		Assert.assertNull(json.getKey());
		Assert.assertNull(json.getKeyValue());
		Assert.assertEquals("com.db.letdb.Example", json.getClassName());
	}

}
