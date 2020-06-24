package com.minhtien.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhtien.app.model.LoaiMay;
import com.minhtien.app.repository.LoaiMayRepository;
import com.minhtien.app.service.LoaiMayService;

@Service
public class LoaiMayServiceImpl implements LoaiMayService{

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
	@Autowired
	private LoaiMayRepository loaiMayRepository;

	@Override
	public List<LoaiMay> listAll() {
		return loaiMayRepository.findAll();
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public LoaiMay save(LoaiMay modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		return loaiMayRepository.save(modelObject);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public LoaiMay update(LoaiMay modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		return loaiMayRepository.save(modelObject);
	}

	@Override
	public void delete(Long id) {
		loaiMayRepository.deleteById(id);
		
	}

	@Override
	public Optional<LoaiMay> getOne(Long id) {
		return loaiMayRepository.findById(id);
	}
}
