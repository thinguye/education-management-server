package com.capstone.educationmanagementserver.models;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.mongodb.lang.Nullable;

import org.apache.commons.collections4.map.HashedMap;
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
	@DBRef
	Block paraBlock;
	@DBRef
	Curriculum curriculum;
	@DBRef
	Map<Subject, Mandatory> subjects = new HashMap<>();
	Integer theoryCredit;
	Integer labCredit;

	@Builder
	public Block(String code, String name, Block paraBlock, Curriculum curriculum) {
		this.code = code;
		this.name = name;
		this.theoryCredit = 0;
		this.labCredit = 0;
		if (paraBlock != null) {
			this.paraBlock = paraBlock;
		}
		this.curriculum = curriculum;
	}
}
