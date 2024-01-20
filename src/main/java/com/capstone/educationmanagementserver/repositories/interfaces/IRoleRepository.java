package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.Optional;

import com.capstone.educationmanagementserver.enums.ERole;
import com.capstone.educationmanagementserver.models.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}