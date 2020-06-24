package com.minhtien.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhtien.app.model.ThuongHieu;
import com.minhtien.app.repository.ThuongHieuRepository;
import com.minhtien.app.service.ThuongHieuService;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	private ThuongHieuRepository thuongHieuRep;

	@Override
	public List<ThuongHieu> listAll() {
		return thuongHieuRep.findAll();
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public ThuongHieu save(ThuongHieu modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		return thuongHieuRep.save(modelObject);
	}

	@Override
	public void delete(Long id) {
		thuongHieuRep.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ThuongHieu update(ThuongHieu modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		return thuongHieuRep.save(modelObject);
	}

	@Override
	public Optional<ThuongHieu> getOne(Long id) {
		return thuongHieuRep.findById(id);
	}

	@Override
	public List<ThuongHieu> searchThuongHieu(String tenTH) {
		return thuongHieuRep.findByTenTHContaining(tenTH);
	}




}
