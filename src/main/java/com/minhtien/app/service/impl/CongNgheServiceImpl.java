package com.minhtien.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhtien.app.model.CongNghe;
import com.minhtien.app.repository.CongNgheRepository;
import com.minhtien.app.service.CongNgheService;

@Service
public class CongNgheServiceImpl implements CongNgheService{

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
	@Autowired
	private CongNgheRepository congNgheRepository;

	@Override
	public List<CongNghe> listAll() {
		return congNgheRepository.findAll();
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public CongNghe save(CongNghe modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		return congNgheRepository.save(modelObject);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public CongNghe update(CongNghe modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		return congNgheRepository.save(modelObject);
	}

	@Override
	public void delete(Long id) {
		congNgheRepository.deleteById(id);
		
	}

	@Override
	public Optional<CongNghe> getOne(Long id) {
		return congNgheRepository.findById(id);
	}
}
