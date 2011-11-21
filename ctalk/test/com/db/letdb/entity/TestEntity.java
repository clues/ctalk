package com.db.letdb.entity;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.db.letdb.Example;
import com.helper.ReflexHelper;

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
		List<Example> exs = new ArrayList<Example>();
		for (int i=0;i<3;i++){
			Example inner = new Example();
			inner.setName("example"+i);
			inner.setId(new Long(i));
			inner.setName("chuntian"+i);
			exs.add(inner);
		}
		ex.setTitles(exs);
		
		System.out.println(ex.toString());
	}
	
	@Test
	public void testJson2entity(){
		String str = "{id:{'id':'123','name':'jias chao','state':'0','titles':'[{id:{'id':'0','name':'chuntian0','state':'0','titles':'','class':'com.db.letdb.Example'}}, {id:{'id':'1','name':'chuntian1','state':'0','titles':'','class':'com.db.letdb.Example'}}, {id:{'id':'2','name':'chuntian2','state':'0','titles':'','class':'com.db.letdb.Example'}}]','class':'com.db.letdb.Example'}}";
		Json json = Json.instance(str);
		Example entity = (Example)ReflexHelper.instance(json);
		Assert.assertEquals("jias chao", entity.getName());
	}

}
