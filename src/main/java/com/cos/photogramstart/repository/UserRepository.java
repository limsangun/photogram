package com.cos.photogramstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.photogramstart.domain.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	// JPA query method
	User findByUsername(String username);
}
