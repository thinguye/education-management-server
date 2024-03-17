package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.repositories.interfaces.IMajorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MajorRepository implements IMajorRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Major> findAll() {
		return mongoTemplate.findAll(Major.class);
	}

	@Override
	public Major findById(String Id) {
		return mongoTemplate.findById(Id, Major.class);
	}

	@Override
	public void save(Major obj) {
		mongoTemplate.save(obj);

	}

	@Override
	public void remove(Major obj) {
		mongoTemplate.remove(obj);

	}
	
	@Override
	public List<Major> findByDepartment(Department department){
		Query query = new Query(Criteria.where("department").is(department));
		return mongoTemplate.find(query, Major.class);
	}

	@Override
	public Major findByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Major.class);
	}

}
