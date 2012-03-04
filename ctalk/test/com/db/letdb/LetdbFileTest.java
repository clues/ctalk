package com.db.letdb;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * @created: Feb 29, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class LetdbFileTest {

	LetdbFile lf;
	 MessageDigest messageDigest = null;  
	
	@Before
	public void setup() throws NoSuchAlgorithmException {
		lf = new LetdbFile();
		messageDigest = MessageDigest.getInstance("MD5");  
        messageDigest.reset();  
	}
	
	@Test
	public void testCheckFileisExist() {
		assertEquals(lf.checkFileisExist(),true);
	}
	
	
	@Test
	public void testSync() throws IOException{
		indexFileInit();
		int len = DocIndex.sync();
		assertEquals(384,len);
	}
	
	@Test
	public void testOther(){
		Map<ByteArray,String> tab = new HashMap<ByteArray,String>();
		
		byte[] a = {0x01,0x02};
		ByteArray ba1 = new ByteArray(a);
		ByteArray ba2 = new ByteArray(a);
		
		tab.put(ba1, "test");
		assertEquals("test",tab.get(ba2));
		
	}
	
	@Test
	public void testLoadIndex() throws IOException{
		indexFileInit();
		DocIndex.sync();
		int count = lf.loadDocIndex();
		assertEquals(3,count);
		
		DocIndex docIndex = DocIndex.getDocIndex(new Long(1));
		
		assertEquals(12,docIndex.getLength());
		
	}
	
	
	private void indexFileInit(){
		LetdbFile.docindexFile = new File("letdb/"+LetdbFile.DocIndexName);
		DocIndex.indexTable.clear();

		Long id = new Long(1);
		DocIndex.indexTable.put(id, new DocIndex(id,589,12,"abc1",ExampleEntity.class.getName()));
		
		id = new Long(2);
		DocIndex.indexTable.put(id, new DocIndex(id,955,13,"abc2",ExampleEntity.class.getName()));

		
		id = new Long(3);
		DocIndex.indexTable.put(id, new DocIndex(id,1000,14,"abc3",ExampleEntity.class.getName()));

	}

}
