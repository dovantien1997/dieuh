package com.minhtien.app.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Image880 extends ModelClass {

	private String fileImage;

	private int status;

	@Transient
	private MultipartFile file;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFileImage() {
		return fileImage;
	}

	public void setFileImage(String fileImage) {
		this.fileImage = fileImage;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
