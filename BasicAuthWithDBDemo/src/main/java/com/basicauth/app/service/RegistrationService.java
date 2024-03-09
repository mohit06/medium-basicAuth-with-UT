package com.basicauth.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.basicauth.app.entity.UserProfile;
import com.basicauth.app.enums.Role;
import com.basicauth.app.repository.RegisterNewUserRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegisterNewUserRepository registerRepo;
	
	@Autowired
	private PasswordEncoder pwdEncoder;

	public boolean registerUser(UserProfile user) {
		Optional<UserProfile> userContainer = registerRepo.findByEmail(user.getEmail());
		if(userContainer.isEmpty()) {
			user.setRole(Role.ROLE_USER);
			user.setPassword(pwdEncoder.encode(user.getPassword()));
			registerRepo.save(user);
			return true;
		}
		return false;
	}
	
	public boolean registerAdmin(UserProfile admin) {
		Optional<UserProfile> userContainer = registerRepo.findByEmail(admin.getEmail());
		if(userContainer.isEmpty()) {
			admin.setRole(Role.ROLE_ADMIN);
			admin.setPassword(pwdEncoder.encode(admin.getPassword()));
			registerRepo.save(admin);
			return true;
		}
		return false;
	}
	
}
