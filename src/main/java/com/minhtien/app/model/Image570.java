package com.minhtien.app.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Image570 extends ModelClass {

	private String fileImage;

	private String fileImage1;

	@Transient
	private MultipartFile file;

	@Transient
	private MultipartFile file1;

	private int status;

	public String getFileImage() {
		return fileImage;
	}

	public void setFileImage(String fileImage) {
		this.fileImage = fileImage;
	}

	public String getFileImage1() {
		return fileImage1;
	}

	public void setFileImage1(String fileImage1) {
		this.fileImage1 = fileImage1;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
