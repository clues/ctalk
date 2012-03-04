package com.db.letdb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class DocumentTest {
	
	
	@Test
	public void testDoc2json(){
		ExampleEntity doc = new ExampleEntity();
		assertEquals("{\"code\":0}",doc.getJsonStr());
		
		
		doc.setId(new Long(654727));
		assertEquals("{\"code\":0,\"id\":654727}",doc.getJsonStr());
	}
	
	
	@Test
	public void testJson2Doc() throws ClassNotFoundException{
		String str = "{\"code\":0,\"id\":654727}";
		Document doc = Document.getInstance(str,ExampleEntity.class.getName());
		assertEquals(ExampleEntity.class.getName(),doc.getClass().getName());
	}

}
