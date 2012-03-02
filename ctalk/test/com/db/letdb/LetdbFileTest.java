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
		assertEquals(120,len);
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
		
		messageDigest.update("12589abc1".getBytes());
		DocIndex docIndex = DocIndex.getDocIndex(new ByteArray(messageDigest.digest()));
		
		assertEquals(12,docIndex.getLength());
		
	}
	
	
	private void indexFileInit(){
		LetdbFile.docindexFile = new File("letdb/"+LetdbFile.DocIndexName);
		DocIndex.indexTable.clear();
		messageDigest.reset();
		messageDigest.update("12589abc1".getBytes());
		DocIndex.indexTable.put(new ByteArray(messageDigest.digest()), new DocIndex(new ByteArray(messageDigest.digest()),12,589,"abc1"));
		
		messageDigest.reset();
		messageDigest.update("13955abc2".getBytes());
		DocIndex.indexTable.put(new ByteArray(messageDigest.digest()), new DocIndex(new ByteArray(messageDigest.digest()),13,955,"abc2"));
		
		messageDigest.reset();
		messageDigest.update("141000abc3".getBytes());
		DocIndex.indexTable.put(new ByteArray(messageDigest.digest()), new DocIndex(new ByteArray(messageDigest.digest()),14,1000,"abc3"));
	}

}
