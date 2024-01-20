package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Year;
import com.capstone.educationmanagementserver.repositories.interfaces.IQuarterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class QuarterRepository implements IQuarterRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Quarter> findAll() {
		return mongoTemplate.findAll(Quarter.class);
	}

	@Override
	public Quarter findById(String Id) {
		return mongoTemplate.findById(Id, Quarter.class);
	}

	@Override
	public void save(Quarter obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(Quarter obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public List<Quarter> findQuartersByYear(Year year) {
		Query query = new Query(Criteria.where("year").is(year));
		return mongoTemplate.find(query, Quarter.class);
	}

	@Override
	public Quarter findQuarterByQuarterYear(String quarter, Year yearObject) {
		Query query = new Query(Criteria.where("name").is(quarter).and("year").is(yearObject));
		return mongoTemplate.findOne(query, Quarter.class);
	}

}
