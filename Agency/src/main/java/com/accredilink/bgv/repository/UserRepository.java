package com.accredilink.bgv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accredilink.bgv.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmailIdOrUserName(String emailId,String userName);
	
	Optional<User> findByEmailId(String emailId);
	
	Optional<User> findByEmailIdAndTokenNumer(String emailId,String tokenNumer);
}