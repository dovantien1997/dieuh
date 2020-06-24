//package com.minhtien.app.security;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.minhtien.app.model.User;
//import com.minhtien.app.model.repository.UserRepository;
//
//@Service
//public class UserPrincipalDetailsService implements UserDetailsService{
//
//	private UserRepository repository;
//	
//	public  UserPrincipalDetailsService(UserRepository repository) {
//		this.repository = repository;
//	}
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = this.repository.findUserByUserName(username);
//		UserPrincpal princpal = new UserPrincpal(user);
//		return princpal;
//	}
//
//}
