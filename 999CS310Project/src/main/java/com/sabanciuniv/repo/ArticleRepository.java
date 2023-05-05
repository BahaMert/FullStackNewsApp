package com.sabanciuniv.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sabanciuniv.model.Article;
import com.sabanciuniv.model.Author;
import com.sabanciuniv.model.Category;
import com.sabanciuniv.model.Comment;


public interface ArticleRepository extends MongoRepository<Article, String>{


	public List<Article> findAll();
	public List<Article> findByCategory(Category category);
	public List<Article> findByTitleContainsIgnoreCase(String title);
	public List<Article> findByAuthor(Author author);
	public List<Article> findByYear(int year);
	public List<Article> findAllByAuthorId(String authorId);
	public List<Article> findAllByCategoryId(String categoryId);
	//public Article findById(String id);
    // modified method to find all comments for an article by ID
    @Query(value = "{'_id': ?0}")
    public Article findArticleById(String articleId);

 
    	
}
