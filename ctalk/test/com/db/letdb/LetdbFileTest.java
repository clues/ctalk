package com.db.letdb;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @created: Feb 29, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class LetdbFileTest {

	LetdbFile lf;
	
	@Before
	public void setup() {
		lf = new LetdbFile();
	}
	
	@Test
	public void testCheckFileisExist() {
		assertEquals(lf.checkFileisExist(),true);
	}

}
