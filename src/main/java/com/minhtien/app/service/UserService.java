package com.minhtien.app.service;

import org.springframework.stereotype.Service;

import com.minhtien.app.model.User;

@Service
public interface UserService {
	User findByUserName(String username) throws Exception;

	User getUserDetailById(Long userId) throws Exception;

	User save(User user) throws Exception;

}
