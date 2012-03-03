package com.db.letdb;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class DocIndexTest {

	MessageDigest messageDigest;
	
	@Before
	public void setup() throws NoSuchAlgorithmException {
		messageDigest = MessageDigest.getInstance("MD5");  
        messageDigest.reset();  
	}
	
	
	@Test  
	public void testDocIndex2bytes() throws IOException{
		DocIndex index = new DocIndex();
		index.setLength(256);
		index.setOffset(32459);
		index.setFileName("sotre1.txt");
		index.setClazz(DocIndex.class.getName());
		
		messageDigest.reset();
		messageDigest.update("12589abc1".getBytes());
		index.setMd5key(new ByteArray(messageDigest.digest()));
		
		byte[] bytes = index.getBytes();
		
		
		
		assertEquals(DocIndex.LENGTH_INDEX,bytes.length);
		
	}
	
	@Test
	public void testDocIndex() throws IOException{
		DocIndex index = new DocIndex();
		index.setLength(256);
		index.setOffset(32459);
		index.setFileName("sotre1.txt");
		index.setClazz(DocIndex.class.getName());
		
		messageDigest.reset();
		messageDigest.update("12589abc1".getBytes());
		index.setMd5key(new ByteArray(messageDigest.digest()));
		
		DocIndex tmp = new DocIndex(index.getBytes());
		
		assertEquals(256,tmp.getLength());
		assertEquals(32459,tmp.getOffset());
		assertEquals("sotre1.txt",tmp.getFileName());
		
		
	}

}
