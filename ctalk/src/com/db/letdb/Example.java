package com.db.letdb;
/**
 * @created: 2011-11-16
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Example {
	
	private String name;
	
	private Long id;

	@EntityKey
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
