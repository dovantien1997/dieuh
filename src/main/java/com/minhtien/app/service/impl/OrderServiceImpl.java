package com.minhtien.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhtien.app.model.Order;
import com.minhtien.app.repository.OrderRepository;
import com.minhtien.app.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public List<Order> listAll() {
		return orderRepo.findAll();
	}

	@Override
	public Optional<Order> getOne(Long id) {
		return orderRepo.findById(id);
	}

	@Override
	public Order save(Order modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		return orderRepo.save(modelObject);
	}

	@Override
	public Order update(Order modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		return orderRepo.save(modelObject);
	}

	@Override
	public void delete(Long id) {

		orderRepo.deleteById(id);
	}

}
