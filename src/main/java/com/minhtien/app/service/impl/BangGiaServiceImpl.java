package com.minhtien.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhtien.app.model.BangGia;
import com.minhtien.app.repository.BangGiaRepository;
import com.minhtien.app.service.BangGiaService;

@Service
public class BangGiaServiceImpl implements BangGiaService {

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	private BangGiaRepository bangGiaRepo;

	@Override
	public List<BangGia> listAll() {
		return bangGiaRepo.findAll();
	}

	@Override
	public Optional<BangGia> getOne(Long id) {
		return bangGiaRepo.findById(id);
	}

	@Override
	public BangGia save(BangGia modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		return bangGiaRepo.save(modelObject);
	}

	@Override
	public BangGia update(BangGia modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		return bangGiaRepo.save(modelObject);
	}

	@Override
	public void delete(Long id) {

		bangGiaRepo.deleteById(id);
	}

}
