package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Organization;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CurriculumRepository implements ICurriculumRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Curriculum> findAll() {
		return mongoTemplate.findAll(Curriculum.class);
	}

	@Override
	public Curriculum findById(String Id) {
		return mongoTemplate.findById(Id, Curriculum.class);
	}

	@Override
	public void save(Curriculum obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(Curriculum obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public List<Curriculum> getCurriculumsByGeneration(Generation generation) {
		Query query = new Query(Criteria.where("generation").is(generation));
		return mongoTemplate.find(query, Curriculum.class);
	}

	@Override
	public Curriculum findCurriculumByOrganization(Organization organization, Generation generation) {
		Query query = new Query(Criteria.where("generation").is(generation).and("organization").is(organization));
		return mongoTemplate.findOne(query, Curriculum.class);
	}

	@Override
	public List<Curriculum> getCurriculumsByOrganization(Organization organization) {
		Query query = new Query(Criteria.where("organization").is(organization));
		return mongoTemplate.find(query, Curriculum.class);
	}

}
