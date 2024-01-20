package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.repositories.interfaces.ILecturerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LecturerRepository implements ILecturerRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Lecturer> findAll() {
		return mongoTemplate.findAll(Lecturer.class);
	}

	@Override
	public Lecturer findById(String Id) {
		return mongoTemplate.findById(Id, Lecturer.class);
	}

	@Override
	public void save(Lecturer obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(Lecturer obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public List<Lecturer> findByDepartment(String department) {
		Query query = new Query(Criteria.where("department").is(department));
		return mongoTemplate.find(query, Lecturer.class);
	}

	@Override
	public Lecturer findByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Lecturer.class);
	}

}
