package com.example.fullstacknewsapp_android;

import java.util.ArrayList;

public class CategoryRVModel {
    private ArrayList<Categories> categories;

    public CategoryRVModel(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }
}
