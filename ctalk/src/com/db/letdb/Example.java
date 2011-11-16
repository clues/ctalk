package com.db.letdb;

import com.db.letdb.entity.Entity;
import com.db.letdb.entity.EntityKey;

/**
 * @created: 2011-11-16
 * @author : jias chao<lino.chao@gmail.com>
 */
public class Example extends Entity{
	
	private String name;
	
	@EntityKey
	private Long id;

	
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
