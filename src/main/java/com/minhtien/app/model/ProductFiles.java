package com.minhtien.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductFiles extends ModelClass implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fileName;

	@ManyToOne
	@JoinColumn(name = "dieuhoa_id")
	private DieuHoa dieuHoa;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public DieuHoa getDieuHoa() {
		return dieuHoa;
	}

	public void setDieuHoa(DieuHoa dieuHoa) {
		this.dieuHoa = dieuHoa;
	}
	
	

}
