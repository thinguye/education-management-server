package com.capstone.educationmanagementserver.models;

import java.util.ArrayList;
import java.util.List;

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
	@Nullable
	Block paraBlock;
	@DBRef
	Curriculum curriculum;
	Integer credit;

	@Builder
	public Block(String code, String name, Block paraBlock, Curriculum curriculum, Integer credit) {
		this.code = code;
		this.name = name;
		this.credit = credit;
		if (paraBlock != null) {
			this.paraBlock = paraBlock;
		}
		this.curriculum = curriculum;
	}
}
