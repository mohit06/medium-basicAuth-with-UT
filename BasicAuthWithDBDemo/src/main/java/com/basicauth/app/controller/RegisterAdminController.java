package com.basicauth.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.basicauth.app.entity.UserProfile;
import com.basicauth.app.service.RegistrationService;

@RestController
public class RegisterAdminController {

	@Autowired
	RegistrationService registerService;

	@PostMapping("/register-admin")
	ResponseEntity<String> register(@RequestBody UserProfile admin) {
		if( registerService.registerAdmin(admin)) {
			return ResponseEntity.ok("Admin created successfully");
		}
		return ResponseEntity.badRequest().body("Admin already exists");
	}
}
