package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/signup")
    public String showSignupForm(@ModelAttribute("user") User user) {
        return "signup";
    }
	
	@PostMapping("/signup")
	public String processSignup(@ModelAttribute("user") User user) {
	    
	    String encryptedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encryptedPassword);

	    userService.save(user);
	    
	    return "redirect:/login";
	}

	
	@GetMapping("/login")
	public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
	    if (error != null) {
	        model.addAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
	    }
	    return "login";
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	




}
