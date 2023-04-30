package com.sabanciuniv.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class Article {

	@Id
	private String id;
	
	private String title;
	private int year;
	private float rating;
	private int people_rated;
	private String content;
	
	@DBRef
	private Category category;
	
	@DBRef
	private List<Comment> comments;
	
	//Also @DocumentReference
	@DBRef
	private Author author;
	

	public Article() {
		// TODO Auto-generated constructor stub
	}
	
	



	public Article(String title, int year, float rating, int people_rated, String content, Category category,
			List<Comment> comments, Author author) {
		super();
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.people_rated = people_rated;
		this.content = content;
		this.category = category;
		this.comments = comments;
		this.author = author;
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public int getYear() {
		return year;
	}





	public void setYear(int year) {
		this.year = year;
	}





	public float getRating() {
		return rating;
	}





	public void setRating(float rating) {
		this.rating = rating;
	}





	public int getPeople_rated() {
		return people_rated;
	}





	public void setPeople_rated(int people_rated) {
		this.people_rated = people_rated;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	public Category getCategory() {
		return category;
	}





	public void setCategory(Category category) {
		this.category = category;
	}





	public List<Comment> getComments() {
		return comments;
	}





	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}





	public Author getAuthor() {
		return author;
	}





	public void setAuthor(Author author) {
		this.author = author;
	}








	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", year=" + year + ", rating=" + rating + ", people_rated="
				+ people_rated + ", content=" + content + ", category=" + category + ", author=" + author
				+  "]";
	}



	
	
	
	
	
}
