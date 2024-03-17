package com.capstone.educationmanagementserver.services;

import java.util.List;
import java.util.stream.Collectors;

import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.SubjectInBlock;
import com.capstone.educationmanagementserver.repositories.interfaces.IBlockRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInBlockRepository;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectInBlockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.IOException;

@Service
public class SubjectInBlockService implements ISubjectInBlockService {
	@Autowired
	private IBlockRepository iBlockRepository;
	@Autowired
	private ICurriculumRepository iCurriculumRepository;
	@Autowired
	private ISubjectInBlockRepository iSubjectInBlockRepository;

	@Override
	public List<SubjectInBlock> getBlocksByCurriculum(String id) {
		Curriculum c = iCurriculumRepository.findById(id);
		return iSubjectInBlockRepository.findAll().stream().filter(s -> {
			try {
				if (s.getBlock().getCurriculum().equals(c)) {
						return true;
				}
			} catch (IOException e) {
				// handle exception
			}
			return false;
		}).collect(Collectors.toList());
	}

	@Override
	public List<SubjectInBlock> getSubjectsByCurriculum(String id) {
		Curriculum c = iCurriculumRepository.findById(id);
		return iSubjectInBlockRepository.getAllSubjectsInCurriculum(c);
//		return iSubjectInBlockRepository.findAll().stream().filter(s -> {
//			try {
//				if (s.getBlock().getCurriculum().equals(c)) {
//						return true;
//				}
//			} catch (IOException e) {
//				// handle exception
//			}
//			return false;
//		}).collect(Collectors.toList());
	}

	@Override
	public List<SubjectInBlock> getAll() {
		return iSubjectInBlockRepository.findAll();
	}

}
