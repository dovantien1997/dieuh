//package com.minhtien.app.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.minhtien.app.model.User;
//import com.minhtien.app.repository.UserRepository;
//import com.minhtien.app.service.UserService;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//	@Autowired
//	UserRepository userRepo;
//	
//	@Override
//	public User findByUserName(String username) throws Exception {
//		return userRepo.findByUserName(username).orElseThrow(()->new Exception("User Not found.."));
//	}
//
//	@Override
//	public User getUserDetailById(Long userId) throws Exception {
//		return userRepo.findById(userId).orElseThrow(()->new Exception("User Not found.."));
//	}
//
//	@Override
//	public User save(User user) throws Exception {
//		return userRepo.save(user);
//	}
//
//}
