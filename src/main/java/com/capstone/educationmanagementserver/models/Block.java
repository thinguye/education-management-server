package com.capstone.educationmanagementserver.models;

import com.mongodb.lang.Nullable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("block")
public class Block {
	@Id
	String id;
	String code;
	String name;
	@DBRef
	@Nullable
	Block paraBlock;
	@DBRef
	Curriculum curriculum;
	Integer theoryCredit;
	Integer labCredit;

	@Builder
	public Block(String code, String name, Block paraBlock, Curriculum curriculum) {
		this.code = code;
		this.name = name;
		if (paraBlock != null) {
			this.paraBlock = paraBlock;
		}
		this.curriculum = curriculum;
		this.theoryCredit = 0;
		this.labCredit = 0;
	}
}
