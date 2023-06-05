package com.sabanciuniv.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class Comment {

	@Id
	private String id;
	
	private String content;
	
	private String commenter;
	

	

	public Comment() {
		// TODO Auto-generated constructor stub
	}




	public Comment(String content, String commenter) {
		super();
		this.content = content;
		this.commenter = commenter;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCommenter() {
		return commenter;
	}
	
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}


	

	
	
	
	
	
}
