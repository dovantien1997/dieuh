package com.minhtien.app.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhtien.app.service.UploadPathService;

@Service
@Transactional
public class UploadPathServiceImpl implements UploadPathService{

	@Autowired
	ServletContext context;
	
	@Override
	public File getFilePath(String fileName, String path) {
		boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
		if(!exists) {
			new File(context.getRealPath("/" + path + "/")).mkdir();
		}
		String modifiedFilePath = context.getRealPath("/"+ path + File.separator + fileName);
		
		File file = new File(modifiedFilePath);
		return file;
	}

}
