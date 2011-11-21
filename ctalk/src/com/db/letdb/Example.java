package com.db.letdb;

import java.util.List;

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
	
	private int state;
	
	private List<Example> titles;

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<Example> getTitles() {
		return titles;
	}

	public void setTitles(List<Example> titles) {
		this.titles = titles;
	}

}
