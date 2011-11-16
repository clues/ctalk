package com.db.letdb.entity;


import org.junit.Test;

import com.db.letdb.Example;

/**
 * @created: 2011-11-16
 * @author : jias chao<lino.chao@gmail.com>
 */
public class TestEntity {

	@Test
	public void test2String(){
		Example ex = new Example();
		ex.setName("jias chao");
		ex.setId(new Long(123));
		
		System.out.println(ex.toString());
	}

}
