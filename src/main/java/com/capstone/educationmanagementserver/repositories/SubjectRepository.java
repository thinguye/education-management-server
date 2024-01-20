package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
@Repository
public class SubjectRepository implements ISubjectRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Subject> findAll() {
		return mongoTemplate.findAll(Subject.class);
	}

	@Override
	public Subject findById(String Id) {
		return mongoTemplate.findById(Id, Subject.class);
	}

	@Override
	public void save(Subject obj) {
		mongoTemplate.save(obj);

	}

	@Override
	public void remove(Subject obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public Subject findByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Subject.class);
	}

	@Override
	public Subject findByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Subject.class);
	}

}
