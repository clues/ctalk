package com.db.letdb;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * @created: Mar 4, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class LetdbTest {

	File root;
	@Before
	public void setup() throws IOException{
		root = new File(LetdbFile.DbRoot);
		LetdbFile.loadDocIndex();
		StorageFileIndex.loadIndex();
		
	}
	
	@Test
	public void testSaveOrUpdate_save() throws IOException{	
		ExampleEntity doc = new ExampleEntity();
		Letdb letdb = new Letdb();
		letdb.saveOrUpdate(doc);
		
		assertEquals(ExampleEntity.class.getName(),DocIndex.getDocIndex(doc.getId()).getClazz());
	}
	
	@Test
	public void testSaveOrUpdate_update() throws IOException, ClassNotFoundException{	
		ExampleEntity doc = new ExampleEntity();
		doc.setName("hahha");
		doc.setCode(1);
		Letdb letdb = new Letdb();
		letdb.saveOrUpdate(doc);
		
		assertEquals(ExampleEntity.class.getName(),DocIndex.getDocIndex(doc.getId()).getClazz());
		assertEquals(1,((ExampleEntity)letdb.get(doc.getId())).getCode());
		
		doc.setCode(2);
		doc.setName("hahha2");
		letdb.saveOrUpdate(doc);
		
		assertEquals(2,((ExampleEntity)letdb.get(doc.getId())).getCode());
		assertEquals("hahha2",((ExampleEntity)letdb.get(doc.getId())).getName());
		
	}
	
	
	
	@Test
	public void testGet() throws IOException, ClassNotFoundException{
		ExampleEntity doc = new ExampleEntity();
		doc.setCode(1);
		doc.setName("jias");
		Letdb letdb = new Letdb();
		letdb.saveOrUpdate(doc);
		
		ExampleEntity doc1 = (ExampleEntity)letdb.get(doc.getId());
		
		
		assertEquals("jias",doc1.getName());
	}

}
