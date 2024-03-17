package com.capstone.educationmanagementserver.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInBlock;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInBlockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectInBlockRepository implements ISubjectInBlockRepository {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<SubjectInBlock> findAll() {
		return mongoTemplate.findAll(SubjectInBlock.class);
	}

	@Override
	public SubjectInBlock findById(String Id) {
		return mongoTemplate.findById(Id, SubjectInBlock.class);
	}

	@Override
	public void save(SubjectInBlock obj) {
		mongoTemplate.save(obj);

	}

	@Override
	public void remove(SubjectInBlock obj) {
		mongoTemplate.remove(obj);

	}

	@Override
	public List<SubjectInBlock> getAllSubjectsInBlock(Block block) {
		Query query = new Query(Criteria.where("block").is(block));
		return mongoTemplate.find(query, SubjectInBlock.class);
	}

	@Override
	public List<SubjectInBlock> getAllSubjectsInCurriculum(Curriculum c) {
		List<SubjectInBlock> subjects = new ArrayList<>();
		Query query2 = new Query(Criteria.where("block.curriculum").is(c));
		subjects.addAll(mongoTemplate.find(query2, SubjectInBlock.class));
		return subjects;
	}
	
	@Override
	public List<SubjectInBlock> getSubjectsInCurriculumByMandatory(Curriculum c, Mandatory m) {
		List<SubjectInBlock> subjects = new ArrayList<>();
		Query query2 = new Query(Criteria.where("block.curriculum").is(c).and("mandatory").is(m));
		subjects.addAll(mongoTemplate.find(query2, SubjectInBlock.class));
		return subjects;
	}

	@Override
	public boolean isExistInCurriculum(Curriculum c, Subject subject) {
		Query query = new Query(Criteria.where("block.curriculum").is(c).and("subject").is(subject));
		SubjectInBlock subjectInBlock = mongoTemplate.findOne(query, SubjectInBlock.class);
		return subjectInBlock != null;

	}

	@Override
	public Block findBySubject(Subject subject, Curriculum curriculum) {
		Query query = new Query(Criteria.where("subject").is(subject).and("block.curriculum").is(curriculum));
		Block b = mongoTemplate.findOne(query, SubjectInBlock.class).getBlock();
		return b;
	}
}
