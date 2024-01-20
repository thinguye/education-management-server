package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.repositories.interfaces.IGenerationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GenerationRepository implements IGenerationRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Generation> findAll() {
		return mongoTemplate.findAll(Generation.class);
	}

	@Override
	public Generation findById(String Id) {
		return mongoTemplate.findById(Id, Generation.class);
	}

	@Override
	public void save(Generation obj) {
		mongoTemplate.save(obj);

	}

	@Override
	public void remove(Generation obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public Generation findByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Generation.class);
	}

	@Override
	public Generation findByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Generation.class);
	}

}
