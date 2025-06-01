package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service 
public class UserServiceImpl implements UserService {

	@Autowired
    UserRepository userRepository;
	
	@Override
	public void save(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User getUserById(String id) {
		return userRepository.findById(id).orElse(null);
	}

}
