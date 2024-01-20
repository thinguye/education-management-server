package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.ElectiveGroup;
import com.capstone.educationmanagementserver.repositories.interfaces.IElectiveGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ElectiveGroupRepository implements IElectiveGroupRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<ElectiveGroup> findAll() {
		return mongoTemplate.findAll(ElectiveGroup.class);
	}

	@Override
	public ElectiveGroup findById(String Id) {
		return mongoTemplate.findById(Id, ElectiveGroup.class);
	}

	@Override
	public void save(ElectiveGroup obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(ElectiveGroup obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public ElectiveGroup findByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, ElectiveGroup.class);
	}

}
