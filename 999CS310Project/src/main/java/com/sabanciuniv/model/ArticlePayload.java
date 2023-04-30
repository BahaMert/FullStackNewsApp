package com.sabanciuniv.model;

public class ArticlePayload {
	

	private String authorid;
	private String categoryid;	
	public String articleid;
	private String title;
	private int year;
	private String content;
	private float rating;
	
	public ArticlePayload() {
		// TODO Auto-generated constructor stub
	}

	public ArticlePayload(String authorid, String categoryid, String articleid, String title, int year, String content,
			float rating) {
		super();
		this.authorid = authorid;
		this.categoryid = categoryid;
		this.articleid = articleid;
		this.title = title;
		this.year = year;
		this.content = content;
		this.rating = rating;
	}

	public String getAuthorid() {
		return authorid;
	}

	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getArticleid() {
		return articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}



	

	

	
	
	
}
