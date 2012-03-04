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
	public void testJson2Doc() throws ClassNotFoundException{
		
		String str = "{\"id\":\"abc-12\"}";
		Document doc = Document.getInstance(str,Document.class.getName());
		assertEquals(Document.class.getName(),doc.getClass().getName());
	}

}
