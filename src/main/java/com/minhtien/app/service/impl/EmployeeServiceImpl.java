//package com.minhtien.app.service.impl;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.minhtien.app.model.Employee;
//import com.minhtien.app.repository.EmployeeRepository;
//import com.minhtien.app.service.EmployeeService;
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//	@Autowired
//	private EmployeeRepository employeeRepository;
//
//	@Override
//	public Iterable<Employee> findAll() {
//		return employeeRepository.findAll();
//	}
//
//	@Override
//	public List<Employee> search(String q) {
//		return employeeRepository.findByNameContaining(q);
//	}
//
//	@Override
//	public Employee findOne(long id) {
//		Optional<Employee> optional = employeeRepository.findById(id);
//		if (optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
//	}
//
//	@Override
//	public void save(Employee contact) {
//		employeeRepository.save(contact);
//	}
//
//	@Override
//	public void delete(Employee emp) {
//		employeeRepository.delete(emp);
//	}
//}
