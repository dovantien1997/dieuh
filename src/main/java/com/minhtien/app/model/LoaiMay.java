package com.minhtien.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LoaiMay extends ModelClass{

	@Column(nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
