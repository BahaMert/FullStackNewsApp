package com.sabanciuniv.controller;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Author;
import com.sabanciuniv.model.Category;
import com.sabanciuniv.model.Comment;
import com.sabanciuniv.model.Article;
import com.sabanciuniv.model.ArticlePayload;

import com.sabanciuniv.repo.AuthorRepository;
import com.sabanciuniv.repo.CategoryRepository;
import com.sabanciuniv.repo.CommentRepository;
import com.sabanciuniv.repo.ArticleRepository;

@RestController
@RequestMapping("/newssystem")
public class NewsSystemRestController {
	
	
	@Autowired private AuthorRepository authorRepository;
	@Autowired private ArticleRepository articleRepository;
	@Autowired private CommentRepository commentRepository;
	@Autowired private CategoryRepository categoryRepository;

	
	private static final Logger logger = LoggerFactory.getLogger(NewsSystemRestController.class);
	
	@PostConstruct
	public void init() {
		
		if(authorRepository.count() ==0) {
			logger.info("Database is empty, initializing..");

			
			Author a1 = new Author("author1","one");
			Author a2 = new Author("author2","two");
			Author a3 = new Author("author3","three");
			
			authorRepository.save(a1);
			authorRepository.save(a2);
			authorRepository.save(a3);
			
			
			List<Author> authors = authorRepository.findAll();
			
			Category cat1 = new Category("category1");
			Category cat2 = new Category("category2");
			Category cat3 = new Category("category3");
			
			categoryRepository.save(cat1);
			categoryRepository.save(cat2);
			categoryRepository.save(cat3);
			
			
			List<Category> categories = categoryRepository.findAll();
			
			
			Comment com1 = new Comment("comment body1");
			Comment com2 = new Comment("comment body2");
			Comment com3 = new Comment("comment body3");
			
			commentRepository.save(com1);
			commentRepository.save(com2);
			commentRepository.save(com3);
			
			
			List<Comment> comments = commentRepository.findAll();
			
			
			
			Article art1 = new Article("Article 1", 2022, 5.0f, 6, "Article 1 content" , categories.get(0), comments.subList(0, 1), authors.get(0), "url 1" );
			Article art2 = new Article("Article 2", 1999, 2.5f, 8, "Article 2 content" , categories.get(1), comments.subList(0, 2), authors.get(1), "url 2");
			Article art3 = new Article("Article 3", 2000, 3.4f, 10,"Article 3 content" , categories.get(2), comments.subList(1, 2), authors.get(2), "url 3");
			Article art4 = new Article("Article 4", 2020, 4.5f, 12,  "Article 4 content" , categories.get(0) , comments.subList(0, 1), authors.get(0), "url 4");
			Article art5 = new Article("Article 5", 2022, 3.9f, 3, "Article 5 content" , categories.get(2), comments.subList(1, 1), authors.get(1), "url 5");
			
			articleRepository.save(art1);
			articleRepository.save(art2);
			articleRepository.save(art3);
			articleRepository.save(art4);
			articleRepository.save(art5);
			
			logger.info("All sample data saved!");
			
			

		}
		
		
		
	}
	
	
	@GetMapping("/authors")
	public List<Author> authors(){
		
		return authorRepository.findAll();
	
	}
	
	@PostMapping("/authors/save")
	public Author saveAuthor(@RequestBody Author author) {
		
		Author authorSaved = authorRepository.save(author);
		return authorSaved;
	}
	
	
	
	@GetMapping("/articles")
	public List<Article> books(){
		return articleRepository.findAll();
	}
	
	@PostMapping("/articles/search/author")
	public List<Article> searchArticlesAuthor(@RequestBody ArticlePayload payload){
		
		List<Article> articles = articleRepository.findAllByAuthorId(payload.getAuthorid());	
		return articles;
	}
	
	
	@GetMapping("/articles/search/category")
	public List<Article> searchArticlesCategory(@RequestBody ArticlePayload payload){
		
		List<Article> articles = articleRepository.findAllByCategoryId(payload.getCategoryid());	
		return articles;
	}
	
	@GetMapping("/articles/search/name")
	public List<Article> searchArticlesName(@RequestBody ArticlePayload payload){
		
		List<Article> articles = articleRepository.findByTitleContainsIgnoreCase(payload.getContent());	
		return articles;
	}
	
	@GetMapping("/categories")
	public List<Category> categories(){
		return categoryRepository.findAll();
	}
	
	@PostMapping("/categories/save")
	public Category savePublisher(@RequestBody Category publisher) {
		
		Category catSaved = categoryRepository.save(publisher);
		return catSaved;
	}

	@GetMapping("/articles/comments/get")
	public List<Comment> searchArticlesComments(@RequestBody ArticlePayload payload){
		
	    Article article = articleRepository.findArticleById(payload.getArticleid());
	    
	    List<Comment> comments = article.getComments();
	    return comments;
	    //List<String> commentIds = articleRepository.findCommentIdsByArticleId(article.getId());
	    //logger.info(commentIds.get(0) );
	    //List<Comment> comments = commentRepository.findAllById(commentIds);
	    //return commentIds;
	}


	@PostMapping("/articles/comments/post")
	public Article addCommentByArticle(@RequestBody ArticlePayload payload){
		
		Article article = articleRepository.findArticleById(payload.getArticleid());
	    Comment comment = new Comment(payload.getContent());
	    commentRepository.save(comment);
	    article.getComments().add(comment);
	    articleRepository.save(article);
		article = articleRepository.findArticleById(payload.getArticleid());
	    return article;	    
	}
	
	@PostMapping("/articles/rating/post")
	public Article addRating(@RequestBody ArticlePayload payload){
		
		float my_rating = payload.getRating();
		Article article = articleRepository.findArticleById(payload.getArticleid());
		article.setPeople_rated(article.getPeople_rated()+1);
		article.setRating((my_rating+article.getRating()*(article.getPeople_rated()-1))/article.getPeople_rated());
		articleRepository.save(article);
		article = articleRepository.findArticleById(payload.getArticleid());
		return article;	    
	}

}
