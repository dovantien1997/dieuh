package com.minhtien.app.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class ImageLogo extends ModelClass {

	private String fileImage;

	private int status;

	@Transient
	private MultipartFile file;

	public String getFileImage() {
		return fileImage;
	}

	public void setFileImage(String fileImage) {
		this.fileImage = fileImage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
