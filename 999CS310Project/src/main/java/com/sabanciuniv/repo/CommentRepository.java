package com.sabanciuniv.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sabanciuniv.model.Comment;


public interface CommentRepository extends MongoRepository<Comment, String>{


	public List<Comment> findAll();
	public List<Comment> findByContent(String content);
	
}
