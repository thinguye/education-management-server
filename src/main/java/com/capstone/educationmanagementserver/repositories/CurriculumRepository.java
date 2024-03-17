package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.models.Department;
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
	public Curriculum findCurriculumByOrganization(Major organization, Generation generation) {
		Query query = new Query(Criteria.where("organization").is(organization).and("generation")
				.elemMatch(Criteria.where("_id").is(generation.getId())));
		return mongoTemplate.findOne(query, Curriculum.class);

	}

	@Override
	public List<Curriculum> getCurriculumsByOrganization(Department organization) {
		Query query = new Query(Criteria.where("organization.department").is(organization));
		return mongoTemplate.find(query, Curriculum.class);
	}

	@Override
	public Curriculum getCurriculumByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Curriculum.class);
	}

}
