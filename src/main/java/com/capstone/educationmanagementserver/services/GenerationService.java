package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.repositories.interfaces.IGenerationRepository;
import com.capstone.educationmanagementserver.requests.generation.AddGenerationRequest;
import com.capstone.educationmanagementserver.requests.generation.UpdateGenerationRequest;
import com.capstone.educationmanagementserver.services.interfaces.IGenerationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GenerationService implements IGenerationService {
	@Autowired
	private IGenerationRepository iGenerationRepository;

	@Override
	public void addGenerationFromFile(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addGeneration(AddGenerationRequest generation) {
		Generation newGeneration = Generation.builder().code(generation.getCode()).name(generation.getName())
				.year(generation.getYear()).build();
		iGenerationRepository.save(newGeneration);

	}

	@Override
	public void updateGeneration(UpdateGenerationRequest generation) {
		try {
			Generation updateGeneration = getGenerationById(generation.getId());
			updateGeneration.setCode(generation.getCode());
			updateGeneration.setName(generation.getName());
			updateGeneration.setYear(generation.getYear());
			iGenerationRepository.save(updateGeneration);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeGeneration(String id) {
		Generation generation = getGenerationById(id);
		iGenerationRepository.remove(generation);
	}

	@Override
	public List<Generation> getAllGeneration() {
		return iGenerationRepository.findAll();
	}

	@Override
	public Generation getGenerationByCode(String code) {
		return iGenerationRepository.findByCode(code);
	}

	@Override
	public Generation getGenerationById(String id) {
		return iGenerationRepository.findById(id);
	}

	@Override
	public Generation getGenerationByName(String name) {
		return iGenerationRepository.findByName(name);
	}

}
