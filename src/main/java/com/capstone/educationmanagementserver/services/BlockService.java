package com.capstone.educationmanagementserver.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.repositories.interfaces.IBlockRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.requests.block.AddBlock;
import com.capstone.educationmanagementserver.services.interfaces.IBlockService;

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
		Block block = Block.builder().code(request.getCode()).name(request.getName())
				.curriculum(request.getCurriculum()).paraBlock(request.getParaBlock()).build();
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
	public void updatingBlock(String id, Map<Subject, Mandatory> subjects) {
		Block block = iBlockRepository.findById(id);
		Integer tempTheoryCredit = block.getTheoryCredit();
		Integer tempLabCredit = block.getLabCredit();
		Curriculum c = block.getCurriculum();
		Map<Subject, Mandatory> temp = block.getSubjects();
		Set<Subject> keys = subjects.keySet();
		Set<Subject> blockKeys = temp.keySet();
		Integer countTheory = 0;
		Integer countLab = 0;
		for (Subject key : keys) {
			if ((temp.containsKey(key) && !temp.get(key).equals(subjects.get(key))) || !temp.containsKey(key)) {
				temp.put(key, subjects.get(key));
				if (!temp.containsKey(key)) {
					countTheory += key.getTheoryCredit();
					countLab += key.getLabCredit();
				}
			}
		}
		for (Subject key : blockKeys) {
			if (!subjects.containsKey(key)) {
				temp.remove(key);
				countTheory -= key.getTheoryCredit();
				countLab -= key.getLabCredit();
			}
		}
		block.setTheoryCredit(tempTheoryCredit + countTheory);
		block.setLabCredit(tempLabCredit + countLab);
		Block paraBlock = block.getParaBlock();
		while (paraBlock != null) {
			paraBlock.setTheoryCredit(paraBlock.getTheoryCredit() + countTheory);
			paraBlock.setLabCredit(paraBlock.getLabCredit() + countLab);
			iBlockRepository.save(paraBlock);
			paraBlock = paraBlock.getParaBlock();
		}
		iBlockRepository.save(block);
		c.setTheoryCredit(c.getTheoryCredit() + countTheory);
		c.setLabCredit(c.getLabCredit() + countLab);
		iCurriculumRepository.save(c);
	}

}
