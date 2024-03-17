package com.capstone.educationmanagementserver.repositories;

import java.util.ArrayList;
import java.util.List;

import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.repositories.interfaces.IEnrollmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepository implements IEnrollmentRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Enrollment> findAll() {
		return mongoTemplate.findAll(Enrollment.class);
	}

	@Override
	public Enrollment findById(String Id) {
		return mongoTemplate.findById(Id, Enrollment.class);
	}

	@Override
	public void save(Enrollment obj) {
		mongoTemplate.save(obj);
	}

	@Override
	public void remove(Enrollment obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public List<Enrollment> findBySubject(SubjectInQuarter s) {
		Query query = new Query(Criteria.where("subject").is(s));
		return mongoTemplate.find(query, Enrollment.class);
	}

	@Override
	public Enrollment findByStudentSubject(SubjectInQuarter s, Student st) {
		Query query = new Query(Criteria.where("subject").is(s).and("student").is(st));
		return mongoTemplate.findOne(query, Enrollment.class);
	}

	@Override
	public List<Enrollment> findSubjectByStudent(Student student) {
		Query query = new Query(Criteria.where("student").is(student));
		return mongoTemplate.find(query, Enrollment.class);
	}

	@Override
	public List<Enrollment> findByLecturer(Lecturer lecturer) {
		Query query = new Query(Criteria.where("subject.lecturer").is(lecturer));
		return mongoTemplate.find(query, Enrollment.class);
	}

	@Override
	public List<Enrollment> findBySubjectFail(SubjectInQuarter sub, Generation gen) {
		Query query = new Query(Criteria.where("subject").is(sub).and("grade").isNull().and("gradeLetter").is("F"));
		if (gen != null) {
			query.addCriteria(Criteria.where("student.generation").is(gen));
		}
		List<Enrollment> e = mongoTemplate.find(query, Enrollment.class);
		return e == null ? new ArrayList<>() : e;
	}

	@Override
	public List<Enrollment> findBySubjectPass(SubjectInQuarter sub, Generation gen) {
		Query query = new Query(Criteria.where("subject").is(sub).and("grade").gte(2.00));
		if (gen != null) {
			query.addCriteria(Criteria.where("student.generation").is(gen));
		}
		List<Enrollment> e = mongoTemplate.find(query, Enrollment.class);
		return e == null ? new ArrayList<>() : e;
	}

}
