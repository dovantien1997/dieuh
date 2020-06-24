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

import com.minhtien.app.model.ImageLogo;
import com.minhtien.app.repository.ImageLogoRepository;
import com.minhtien.app.service.ImageLogoService;
import com.minhtien.app.service.UploadPathService;

@Service
public class ImageLogoServiceImpl implements ImageLogoService {

	@Autowired
	private ImageLogoRepository imageRepo;

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	private UploadPathService uploadpathService;

	@Autowired
	private ServletContext context;

	@Override
	public List<ImageLogo> listAll() {
		return imageRepo.findAll();
	}

	@Override
	public Optional<ImageLogo> getOne(Long id) {
		return imageRepo.findById(id);
	}

	@Override
	public ImageLogo save(ImageLogo modelObject) {
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

		ImageLogo imageDB = imageRepo.save(modelObject);
		return imageDB;
	}

	@Override
	public ImageLogo update(ImageLogo modelObject) {
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
		ImageLogo imageDB = imageRepo.save(modelObject);
		return imageDB;
	}

	@Override
	public void delete(Long id) {
		Optional<ImageLogo> image = imageRepo.findById(id);
		if (image.isPresent()) {
			File dbStoreFile = new File(context.getRealPath("/images/" + File.separator + image.get().getFileImage()));
			if (dbStoreFile.exists()) {
				dbStoreFile.delete();
			}
		}
		imageRepo.deleteById(id);
	}

	@Override
	public ImageLogo getById(Long id) {
		Optional<ImageLogo> image = imageRepo.findById(id);
		if (image.isPresent()) {
			return image.get();
		}
		return null;
	}

}
