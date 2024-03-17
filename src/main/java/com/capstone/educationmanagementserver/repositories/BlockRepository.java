package com.capstone.educationmanagementserver.repositories;

import java.util.List;

import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.SubjectInBlock;
import com.capstone.educationmanagementserver.repositories.interfaces.IBlockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BlockRepository implements IBlockRepository{
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Block> findAll() {
		return mongoTemplate.findAll(Block.class);
	}

	@Override
	public Block findById(String Id) {
		return mongoTemplate.findById(Id, Block.class);
	}

	@Override
	public void save(Block obj) {
		mongoTemplate.save(obj);
		
	}

	@Override
	public void remove(Block obj) {
		mongoTemplate.remove(obj);		
	}

	@Override
	public List<Block> getBlocksByCurriculum(Curriculum c) {
		Query query = new Query(Criteria.where("curriculum._id").is(c.getId()));
		return mongoTemplate.find(query, Block.class);
	}


	@Override
	public Block getBlockByNameCurriculum(String name, Curriculum c) {
		Query query = new Query(Criteria.where("curriculum").is(c).and("name").is(name));
		return mongoTemplate.findOne(query, Block.class);
	}

}
