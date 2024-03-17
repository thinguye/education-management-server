package com.capstone.educationmanagementserver.models;

import lombok.Data;

@Data
public class BlockCredit{
	Block block;
	Integer credit;
	
	public BlockCredit(Block block){
		if (block.paraBlock!=null) {
			this.block = block;
			this.credit = 0;
		}
	}
}
