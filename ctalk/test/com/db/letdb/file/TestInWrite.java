package com.db.letdb.file;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * @created: 2011-11-16
 * @author : jias chao<lino.chao@gmail.com>
 */
public class TestInWrite {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testWrite() throws IOException{
		InWrite iw = new InWrite("context.txt");
		iw.write("abc");
		iw.write("123");
		
	}
	
	@Test
	public void testRead() throws IOException{
		InWrite iw = new InWrite("context.txt");
		String str = iw.readNextLine();
			System.out.print(str);
		
	}

}
