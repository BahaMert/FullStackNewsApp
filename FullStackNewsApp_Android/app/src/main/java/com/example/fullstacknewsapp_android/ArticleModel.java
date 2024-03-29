package com.example.fullstacknewsapp_android;

public class ArticleModel {

    private String id;

    private String title;
    private int year;
    private float rating;
    private int people_rated;
    private String content;

    private String authorFullName;
    private String image_url;

    public ArticleModel() {
    }







    public ArticleModel(String id, String title, int year, float rating, int people_rated, String content
                   , String image_url, String authorFullName) {
        super();
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.people_rated = people_rated;
        this.content = content;
        this.image_url = image_url;
        this.authorFullName = authorFullName;
    }


    public String getAuthorFullName() {
        return authorFullName;
    }


    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
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







    public String getImage_url() {
        return image_url;
    }







    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }



}
