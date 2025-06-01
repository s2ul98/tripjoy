package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다: " + username));
		
		return org.springframework.security.core.userdetails.User.builder()
													            .username(user.getId())
													            .password(user.getPassword())
													            .roles("USER") 
													            .build();
		
	}

}
