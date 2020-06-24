package com.minhtien.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ThuongHieu extends ModelClass {

	@Column(nullable = false)
	private String tenTH;

	public String getTenTH() {
		return tenTH;
	}

	public void setTenTH(String tenTH) {
		this.tenTH = tenTH;
	}

}
