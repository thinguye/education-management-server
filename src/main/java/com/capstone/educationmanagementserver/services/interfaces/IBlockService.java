package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.requests.block.AddBlock;
import com.capstone.educationmanagementserver.requests.curriculum.UpdateCirriculumRequest;

public interface IBlockService {

	void addBlock(AddBlock request);

	List<Block> getAllBlocks();

	Block getBlockById(String id);

	List<Block> getBlocksByCurriculum(String id);

	void removeBlock(String id);

	void updatingBlock(UpdateCirriculumRequest request);


}
