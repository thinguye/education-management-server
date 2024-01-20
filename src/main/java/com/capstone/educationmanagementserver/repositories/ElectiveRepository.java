package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.ElectiveGroup;
import com.capstone.educationmanagementserver.models.ElectiveSubject;
import com.capstone.educationmanagementserver.repositories.interfaces.IElectiveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
@Repository
public class ElectiveRepository implements IElectiveRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<ElectiveSubject> findAll() {
		return mongoTemplate.findAll(ElectiveSubject.class);
	}

	@Override
	public ElectiveSubject findById(String Id) {
		return mongoTemplate.findById(Id, ElectiveSubject.class);
	}

	@Override
	public void save(ElectiveSubject obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(ElectiveSubject obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public ElectiveSubject findByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, ElectiveSubject.class);
	}

	@Override
	public List<ElectiveSubject> findByGroup(ElectiveGroup e) {
		Query query = new Query(Criteria.where("electiveGroup").is(e));
		return mongoTemplate.find(query, ElectiveSubject.class);
	}

}
