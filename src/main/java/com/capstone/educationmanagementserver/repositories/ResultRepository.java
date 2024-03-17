package com.capstone.educationmanagementserver.repositories;

import java.util.Date;
import java.util.List;

import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Result;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.repositories.interfaces.IResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ResultRepository implements IResultRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Result> findAll() {
		return mongoTemplate.findAll(Result.class);
	}

	@Override
	public Result findById(String Id) {
		return mongoTemplate.findById(Id, Result.class);
	}

	@Override
	public void save(Result obj) {
		mongoTemplate.save(obj);

	}

	@Override
	public void remove(Result obj) {
		mongoTemplate.remove(obj);

	}

	@Override
	public List<Result> getResultInQuarter(Quarter quarter) {
		Query query = new Query(Criteria.where("quarter").is(quarter));
		return mongoTemplate.find(query, Result.class);
	}

	@Override
	public List<Result> getResultByStudent(Student student) {
		Query query = new Query(Criteria.where("student").is(student));
		query.with(Sort.by(Sort.Direction.DESC, "year", "quarter"));
		return mongoTemplate.find(query, Result.class);

	}

	@Override
	public Result getResultByEnrollment(Enrollment enrollment) {
		Query query = new Query(Criteria.where("enrollments").all(enrollment));
		return mongoTemplate.findOne(query, Result.class);
	}

	@Override
	public Result getResultByStudentAndQuarter(Student student, Quarter quarter) {
		if (quarter == null) {
			Query q = new Query();
			q.with(Sort.by(Direction.DESC, "start"));
			quarter = mongoTemplate.findOne(q, Quarter.class);
		}
		Query query = new Query(Criteria.where("student").is(student).and("quarter").is(quarter));
		return mongoTemplate.findOne(query, Result.class);
	}

	@Override
	public Result getResultLastQuarterByStudent(Student st, Date date) {
		Query query = new Query(Criteria.where("start").lte(date).and("end").gte(date));
		Quarter q = mongoTemplate.findOne(query,Quarter.class);
		return getResultByStudentAndQuarter(st, q);
	}
	
	

}
