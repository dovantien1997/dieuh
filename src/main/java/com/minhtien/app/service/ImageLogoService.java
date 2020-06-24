package com.minhtien.app.service;

import com.minhtien.app.model.ImageLogo;

public interface ImageLogoService extends CRUDService<ImageLogo>{

	ImageLogo getById(Long id);
}
