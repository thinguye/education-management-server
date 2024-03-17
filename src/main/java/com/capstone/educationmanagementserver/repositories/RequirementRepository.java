package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Requirement;
import com.capstone.educationmanagementserver.repositories.interfaces.IRequirementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RequirementRepository implements IRequirementRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Requirement> findAll() {
		return mongoTemplate.findAll(Requirement.class);
	}

	@Override
	public Requirement findById(String Id) {
		return mongoTemplate.findById(Id, Requirement.class);
	}
	
	@Override
	public Requirement findByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Requirement.class);
	}

	@Override
	public void save(Requirement obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(Requirement obj) {
		mongoTemplate.remove(obj);

	}

}
