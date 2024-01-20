package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Organization;
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
	public List<Organization> findAll() {
		return mongoTemplate.findAll(Organization.class);
	}

	@Override
	public Organization findById(String Id) {
		return mongoTemplate.findById(Id, Organization.class);
	}

	@Override
	public void save(Organization obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(Organization obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public List<Organization> findBySchool(String school) {
		Query query = new Query(Criteria.where("school").is(school));
		return mongoTemplate.find(query, Organization.class);
	}

}
