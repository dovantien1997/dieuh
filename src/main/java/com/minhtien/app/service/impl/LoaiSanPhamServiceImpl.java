package com.minhtien.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhtien.app.model.LoaiSanPham;
import com.minhtien.app.repository.LoaiSanPhamRepository;
import com.minhtien.app.service.LoaiSanPhamService;

@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService{

	@Autowired
	private LoaiSanPhamRepository loaiSanPhamRepository;
	
	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
	@Override
	public List<LoaiSanPham> listAll() {
		return loaiSanPhamRepository.findAll();
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public LoaiSanPham save(LoaiSanPham modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		return loaiSanPhamRepository.save(modelObject);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public LoaiSanPham update(LoaiSanPham modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		return loaiSanPhamRepository.save(modelObject);
	}

	@Override
	public void delete(Long id) {
		loaiSanPhamRepository.deleteById(id);		
	}

	@Override
	public Optional<LoaiSanPham> getOne(Long id) {
		return loaiSanPhamRepository.findById(id);
	}

}
