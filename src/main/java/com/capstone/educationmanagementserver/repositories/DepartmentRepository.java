package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.repositories.interfaces.IDepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository implements IDepartmentRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Department> findAll() {
		return mongoTemplate.findAll(Department.class);
	}

	@Override
	public Department findById(String Id) {
		return mongoTemplate.findById(Id, Department.class);
	}

	@Override
	public void save(Department obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(Department obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public List<Department> findBySchool(String school) {
		Query query = new Query(Criteria.where("school").is(school));
		return mongoTemplate.find(query, Department.class);
	}

	@Override
	public Department findByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Department.class);
	}

}
