package com.db.letdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class DocumentTest {
	
	
	@Test
	public void testDoc2json(){
		Document doc = new Document();
		assertEquals("{}",doc.getJsonStr());
		
		
		doc.setId(System.currentTimeMillis());
		assertEquals("{\"id\":\"abc-12\"}",doc.getJsonStr());
	}
	
	
	@Test
	public void testJson2Doc(){
		
		String str = "{\"id\":\"abc-12\"}";
		Document doc = new Document().getInstance(str);
		assertEquals("abc-12",doc.getId());
	}

}
