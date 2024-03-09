package com.basicauth.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.basicauth.app.entity.UserProfile;
import com.basicauth.app.service.RegistrationService;

@RestController
public class RegisterUserController {

	@Autowired
	RegistrationService registerService;

	@PostMapping("/register")
	ResponseEntity<String> register(@RequestBody UserProfile user) {
		System.out.println("INSIDE");
		if(registerService.registerUser(user)) {
			return ResponseEntity.ok("User created successfully");
		}
		return ResponseEntity.badRequest().body("User created successfully");
	}
}
