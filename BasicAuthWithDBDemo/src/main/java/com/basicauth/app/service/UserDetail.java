package com.basicauth.app.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.basicauth.app.entity.UserProfile;
import com.basicauth.app.repository.RegisterNewUserRepository;
@Service
public class UserDetail implements UserDetailsService {

	@Autowired
	private RegisterNewUserRepository registerNewUserRepository;

	private Set<GrantedAuthority> set = new HashSet<>();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserProfile> user = registerNewUserRepository.findByEmail(email);
		if (user.isEmpty()) {
			new UsernameNotFoundException("User not exists by Username");
		}
		GrantedAuthority authorities = new SimpleGrantedAuthority(user.get().getRole().toString());
		set.add(authorities);

		return new org.springframework.security.core.userdetails.User(email, user.get().getPassword(), set);
	}

}
