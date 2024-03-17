package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;
import java.util.Map;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.requests.block.AddBlock;

public interface IBlockService {

	void addBlock(AddBlock request);

	List<Block> getAllBlocks();

	Block getBlockById(String id);

	List<Block> getBlocksByCurriculum(String id);

	void removeBlock(String id);

	void updatingBlock(String id, Map<Subject, Mandatory> subjects);

	Block getBlockBySubjectAndCurriculum(Subject s, Curriculum c);


}
