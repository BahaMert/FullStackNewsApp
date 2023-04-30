package com.sabanciuniv.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sabanciuniv.model.Category;


public interface CategoryRepository extends MongoRepository<Category, String>{

	public Category findByName(String name);
	public List<Category> findByNameIgnoreCase(String name);
	public List<Category> findByNameContainsIgnoreCase(String name);
	public List<Category> findAll();
	
}
