package com.basicauth.app.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConditionalOnProperty(value = "security.enabled", havingValue = "false")
public class DisableSecurityConfig {
	@Bean
	public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {

		http.csrf(htpSecurity -> htpSecurity.disable());

		http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
		return http.build();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
