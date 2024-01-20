package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;

public interface IBlockRepository extends RepositoryManager<Block>{

	List<Block> getBlocksByCurriculum(Curriculum c);

}
