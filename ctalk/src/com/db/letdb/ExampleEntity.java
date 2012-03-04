package com.db.letdb;
/**
 * @created: Mar 4, 2012
 * @author : jias chao<lino.chao@gmail.com>
 */
public class ExampleEntity extends Document{

	private String name;
	
	private int code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
