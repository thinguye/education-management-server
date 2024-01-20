package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.Optional;

import com.capstone.educationmanagementserver.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}