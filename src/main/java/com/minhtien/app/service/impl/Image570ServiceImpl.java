package com.minhtien.app.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhtien.app.model.Image570;
import com.minhtien.app.repository.Image570Repository;
import com.minhtien.app.service.Image570Service;
import com.minhtien.app.service.UploadPathService;

@Service
public class Image570ServiceImpl implements Image570Service {

	@Autowired
	private Image570Repository imageRepo;

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	private UploadPathService uploadpathService;

	@Autowired
	private ServletContext context;

	@Override
	public List<Image570> listAll() {
		return imageRepo.findAll();
	}

	@Override
	public Optional<Image570> getOne(Long id) {
		return imageRepo.findById(id);
	}

	@Override
	public Image570 save(Image570 modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		if (modelObject.getFile() != null && modelObject.getFile().getSize() > 0) {
			modelObject.setFileImage(modelObject.getFile().getOriginalFilename());
			File storeFile = uploadpathService.getFilePath(modelObject.getFile().getOriginalFilename(), "images");
			if (storeFile != null) {
				try {
					FileUtils.writeByteArrayToFile(storeFile, modelObject.getFile().getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (modelObject.getFile1() != null && modelObject.getFile1().getSize() > 0) {
			modelObject.setFileImage1(modelObject.getFile1().getOriginalFilename());
			File storeFile = uploadpathService.getFilePath(modelObject.getFile1().getOriginalFilename(), "images");
			if (storeFile != null) {
				try {
					FileUtils.writeByteArrayToFile(storeFile, modelObject.getFile1().getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		Image570 imageDB = imageRepo.save(modelObject);
		return imageDB;
	}

	@Override
	public Image570 update(Image570 modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		if (modelObject.getFile() != null && modelObject.getFile().getSize() > 0) {
			modelObject.setFileImage(modelObject.getFile().getOriginalFilename());
			File storeFile = uploadpathService.getFilePath(modelObject.getFile().getOriginalFilename(), "images");
			if (storeFile != null) {
				try {
					FileUtils.writeByteArrayToFile(storeFile, modelObject.getFile().getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (modelObject.getFile1() != null && modelObject.getFile1().getSize() > 0) {
			modelObject.setFileImage1(modelObject.getFile1().getOriginalFilename());
			File storeFile = uploadpathService.getFilePath(modelObject.getFile1().getOriginalFilename(), "images");
			if (storeFile != null) {
				try {
					FileUtils.writeByteArrayToFile(storeFile, modelObject.getFile1().getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		Image570 imageDB = imageRepo.save(modelObject);
		return imageDB;
	}

	@Override
	public void delete(Long id) {
		Optional<Image570> image = imageRepo.findById(id);
		if (image.isPresent()) {
			File dbStoreFile = new File(context.getRealPath("/images/" + File.separator + image.get().getFileImage()));
			if (dbStoreFile.exists()) {
				dbStoreFile.delete();
			}
			File dbStoreFile1 = new File(
					context.getRealPath("/images/" + File.separator + image.get().getFileImage1()));
			if (dbStoreFile1.exists()) {
				dbStoreFile1.delete();
			}
		}
		imageRepo.deleteById(id);
	}

	@Override
	public Image570 getById(Long id) {
		Optional<Image570> image = imageRepo.findById(id);
		if (image.isPresent()) {
			return image.get();
		}
		return null;
	}

}
