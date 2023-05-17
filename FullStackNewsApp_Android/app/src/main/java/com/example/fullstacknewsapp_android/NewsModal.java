package com.example.fullstacknewsapp_android;

import java.util.ArrayList;

public class NewsModal {
    private ArrayList<Articles> articles;

    public NewsModal(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
