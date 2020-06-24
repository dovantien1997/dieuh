package com.minhtien.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhtien.app.model.OrderDetail;
import com.minhtien.app.repository.OrderDetailRepository;
import com.minhtien.app.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Override
	public List<OrderDetail> listAll() {
		return orderDetailRepo.findAll();
	}

	@Override
	public Optional<OrderDetail> getOne(Long id) {
		return orderDetailRepo.findById(id);
	}

	@Override
	public OrderDetail save(OrderDetail modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		return orderDetailRepo.save(modelObject);
	}

	@Override
	public OrderDetail update(OrderDetail modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		return orderDetailRepo.save(modelObject);
	}

	@Override
	public void delete(Long id) {

		orderDetailRepo.deleteById(id);
	}

}
