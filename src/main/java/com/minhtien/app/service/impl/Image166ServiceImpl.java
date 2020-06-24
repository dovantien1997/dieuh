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

import com.minhtien.app.model.Image166;
import com.minhtien.app.repository.Image166Repository;
import com.minhtien.app.service.Image166Service;
import com.minhtien.app.service.UploadPathService;

@Service
public class Image166ServiceImpl implements Image166Service{

	@Autowired
	private Image166Repository imageRepo;

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	private UploadPathService uploadpathService;

	@Autowired
	private ServletContext context;

	@Override
	public List<Image166> listAll() {
		return imageRepo.findAll();
	}

	@Override
	public Optional<Image166> getOne(Long id) {
		return imageRepo.findById(id);
	}

	@Override
	public Image166 save(Image166 modelObject) {
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

		

		Image166 imageDB = imageRepo.save(modelObject);
		return imageDB;
	}

	@Override
	public Image166 update(Image166 modelObject) {
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

		

		Image166 imageDB = imageRepo.save(modelObject);
		return imageDB;
	}

	@Override
	public void delete(Long id) {
		Optional<Image166> image = imageRepo.findById(id);
		if (image.isPresent()) {
			File dbStoreFile = new File(context.getRealPath("/images/" + File.separator + image.get().getFileImage()));
			if (dbStoreFile.exists()) {
				dbStoreFile.delete();
			}
			
		}
		imageRepo.deleteById(id);
	}

	@Override
	public Image166 getById(Long id) {
		Optional<Image166> image = imageRepo.findById(id);
		if (image.isPresent()) {
			return image.get();
		}
		return null;
	}
}
