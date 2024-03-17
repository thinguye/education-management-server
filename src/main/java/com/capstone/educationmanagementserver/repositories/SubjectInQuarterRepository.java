package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInQuarterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectInQuarterRepository implements ISubjectInQuarterRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<SubjectInQuarter> findAll() {
		return mongoTemplate.findAll(SubjectInQuarter.class);
	}

	@Override
	public SubjectInQuarter findById(String Id) {
		return mongoTemplate.findById(Id, SubjectInQuarter.class);
	}

	@Override
	public void save(SubjectInQuarter obj) {
		mongoTemplate.save(obj);

	}

	@Override
	public void remove(SubjectInQuarter obj) {
		mongoTemplate.remove(obj);

	}

	@Override
	public SubjectInQuarter findBySubjectQuarter(Subject s, Quarter q) {
		Query query = new Query(Criteria.where("subject").is(s).and("quarter").is(q));
		return mongoTemplate.findOne(query, SubjectInQuarter.class);
	}

	@Override
	public List<SubjectInQuarter> findByLecturer(Lecturer lecturer) {
		Query query = new Query(Criteria.where("lecturer").is(lecturer));
		query.with(Sort.by(Sort.Direction.DESC, "quarter"));
		return mongoTemplate.find(query, SubjectInQuarter.class);
	}

	@Override
	public List<SubjectInQuarter> findByLecturerLastQuarter(Lecturer l) {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "start"));
		Quarter q = mongoTemplate.findOne(query, Quarter.class);
		Query query2 = new Query(Criteria.where("lecturer").is(l).and("quarter").is(q));
		return mongoTemplate.find(query2, SubjectInQuarter.class);
	}

	@Override
	public List<SubjectInQuarter> findBySubjectLecturer(Lecturer l, Subject s) {
		Query query = new Query(Criteria.where("lecturer").is(l).and("subject").is(s));
		return mongoTemplate.find(query, SubjectInQuarter.class);
	}

	@Override
	public List<SubjectInQuarter> findByLastQuarter() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "start"));
		Quarter q = mongoTemplate.findOne(query, Quarter.class);
		Query query2 = new Query(Criteria.where("quarter").is(q));
		return mongoTemplate.find(query2, SubjectInQuarter.class);
	}

}
