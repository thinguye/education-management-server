package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.repositories.interfaces.IBlockRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.requests.block.AddBlock;
import com.capstone.educationmanagementserver.requests.curriculum.UpdateCirriculumRequest;
import com.capstone.educationmanagementserver.services.interfaces.IBlockService;

import org.hibernate.validator.cfg.context.ReturnValueTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockService implements IBlockService {
	@Autowired
	private IBlockRepository iBlockRepository;
	@Autowired
	private ICurriculumRepository iCurriculumRepository;

	@Override
	public void addBlock(AddBlock request) {
		Block block = Block.builder().code(request.getCode()).name(request.getName()).curriculum(request.getCurriculum()).paraBlock(request.getParaBlock()).build();
		iBlockRepository.save(block);
	}

	@Override
	public List<Block> getAllBlocks() {
		return iBlockRepository.findAll();
	}

	@Override
	public Block getBlockById(String id) {
		return iBlockRepository.findById(id);
	}

	@Override
	public List<Block> getBlocksByCurriculum(String id) {
		Curriculum c = iCurriculumRepository.findById(id);
		return iBlockRepository.getBlocksByCurriculum(c);
	}

	@Override
	public void removeBlock(String id) {
		Block block = iBlockRepository.findById(id);
		iBlockRepository.remove(block);
	}

	@Override
	public void updatingBlock(UpdateCirriculumRequest request) {
		Block block = iBlockRepository.findById(request.getId());
		iBlockRepository.save(block);		
	}

}
