package com.minhtien.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee extends ModelClass {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
