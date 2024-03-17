package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.enums.Status;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.repositories.interfaces.IStudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository implements IStudentRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Student> findAll() {
		return mongoTemplate.findAll(Student.class);
	}

	@Override
	public Student findById(String Id) {
		return mongoTemplate.findById(Id, Student.class);
	}

	@Override
	public void save(Student obj) {
		mongoTemplate.save(obj);

	}

	@Override
	public void remove(Student obj) {
		mongoTemplate.remove(obj);
	}

	@Override
	public Student findByCode(String code) {
		Query query = new Query(Criteria.where("code").is(code));
		return mongoTemplate.findOne(query, Student.class);
	}

	@Override
	public Student findByEmail(String email) {
		Query query = new Query(Criteria.where("email").is(email));
		return mongoTemplate.findOne(query, Student.class);
	}

	@Override
	public List<Student> findStudentGraduated() {
		Query query = new Query(Criteria.where("this.credit >= this.curriculum.credit"));
		return mongoTemplate.find(query, Student.class);
	}

	@Override
	public List<Student> findStudentUndergraduated() {
		return mongoTemplate.findAll(Student.class);
	}

	@Override
	public List<Student> findByStatus(Status status) {
		Query query = new Query(Criteria.where("status").is(status));
		return mongoTemplate.find(query, Student.class);
	}

	@Override
	public List<Student> findByGeneration(Generation generation) {
		Query query = new Query(Criteria.where("generation").is(generation));
		return mongoTemplate.find(query, Student.class);
	}


}
