package com.basicauth.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basicauth.app.entity.UserProfile;
import com.basicauth.app.enums.Role;

public interface RegisterNewUserRepository extends JpaRepository<UserProfile,Long>{

	Optional<UserProfile> findByEmail(String email);

}
