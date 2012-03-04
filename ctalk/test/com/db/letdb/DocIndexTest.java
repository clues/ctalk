package com.db.letdb;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import com.db.letdb.ByteArray;
import com.db.letdb.DocIndex;

/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class DocIndexTest {

	MessageDigest messageDigest;
	
	@Before
	public void setup() throws NoSuchAlgorithmException {
	
	}
	
	
	@Test  
	public void testDocIndex2bytes() throws IOException{
		DocIndex index = new DocIndex();
		index.setLength(256);
		index.setOffset(32459);
		index.setFileName("sotre1.txt");
		index.setClazz(DocIndex.class.getName());
		
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
		
		
		DocIndex tmp = new DocIndex(index.getBytes());
		
		assertEquals(256,tmp.getLength());
		assertEquals(32459,tmp.getOffset());
		assertEquals("sotre1.txt",tmp.getFileName());
		
		
	}

}
