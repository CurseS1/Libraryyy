package com.example.library2.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.library2.Entity.user;
import com.example.library2.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	
	public List<user> getAllUsers() {
		return userRepository.findAllByOrderByDisplayNameAsc();
	}
	
	
	public List<user> getAllActiveUsers() { return
		userRepository.findAllByActiveOrderByDisplayNameAsc(1);
	}
	
	public user getByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public user getById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public user addNew(user user) {
		user.setPassword(user.getPassword()) ;
		user.setCreatedDate( new Date() );
		user.setLastModifiedDate( user.getCreatedDate() );
		user.setActive(1);
		return userRepository.save(user);
	}
	
	public user update(user user) {
		user.setLastModifiedDate( new Date() );
		return userRepository.save( user );
	}
	
	public void delete(user user) {
		userRepository.delete(user);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
