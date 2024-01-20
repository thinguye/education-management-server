package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.requests.generation.AddGenerationRequest;
import com.capstone.educationmanagementserver.requests.generation.UpdateGenerationRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IGenerationService {
	void addGenerationFromFile(MultipartFile file);

	void addGeneration(AddGenerationRequest generation);

	void updateGeneration(UpdateGenerationRequest generation);

	void removeGeneration(String id);

	List<Generation> getAllGeneration();

	Generation getGenerationByCode(String code);

	Generation getGenerationById(String id);

	Generation getGenerationByName(String name);
}
