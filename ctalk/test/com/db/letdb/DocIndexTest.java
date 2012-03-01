package com.db.letdb;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * @created: Mar 1, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class DocIndexTest {

	@Before
	public void setup() {
	}
	
	
	@Test
	public void testDocIndex2bytes() throws IOException{
		DocIndex index = new DocIndex();
		index.setLength(256);
		index.setOffset(32459);
		index.setFileName("sotre1.txt");
		index.setClazz(DocIndex.class.getName());
		
		byte[] bytes = index.getBytes();
		
		assertEquals(DocIndex.LENGTH_DOCINDEX,bytes.length);
		
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
