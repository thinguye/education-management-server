package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Year;
import com.capstone.educationmanagementserver.repositories.interfaces.IYearRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class YearRepository implements IYearRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Year> findAll() {
		return mongoTemplate.findAll(Year.class);
	}

	@Override
	public Year findById(String Id) {
		return mongoTemplate.findById(Id, Year.class);
	}

	@Override
	public void save(Year obj) {
		mongoTemplate.save(obj);

	}

	@Override
	public void remove(Year obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public Year findByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Year.class);
	}

	@Override
	public Year findByName(String year) {
		Query query = new Query(Criteria.where("name").is(year));
		return mongoTemplate.findOne(query, Year.class);
	}

}
