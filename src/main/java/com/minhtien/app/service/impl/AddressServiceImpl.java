package com.minhtien.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhtien.app.model.Address;
import com.minhtien.app.repository.AddressRepository;
import com.minhtien.app.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public List<Address> listAll() {
		return addressRepo.findAll();
	}

	@Override
	public Optional<Address> getOne(Long id) {
		return addressRepo.findById(id);
	}

	@Override
	public Address save(Address modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		return addressRepo.save(modelObject);
	}

	@Override
	public Address update(Address modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		return null;
	}

	@Override
	public void delete(Long id) {

		addressRepo.deleteById(id);
	}

}
