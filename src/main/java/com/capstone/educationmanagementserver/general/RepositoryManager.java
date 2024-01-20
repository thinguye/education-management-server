package com.capstone.educationmanagementserver.general;

import java.util.List;

public interface RepositoryManager<T> {

	List<T> findAll();

	T findById(String Id);

	void save(T obj);
	
	void remove(T obj); 
}