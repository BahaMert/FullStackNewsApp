package com.example.fullstacknewsapp_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface{

    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModel> categoryRVModelArrayList;

    private ArrayList<Categories> categoriesArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idArticles);
        categoryRV = findViewById(R.id.idCategory);
        loadingPB = findViewById(R.id.idProgressBar);
        articlesArrayList = new ArrayList<>();
        categoriesArrayList = new ArrayList<>();
        categoryRVModelArrayList= new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModelArrayList,this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getCategories();
        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getCategories() {
        loadingPB.setVisibility(View.VISIBLE);
        categoriesArrayList.clear();
        String base_url = "http://10.0.2.2:8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<CategoryRVModel> call = retrofitAPI.getAllCategories();
        call.enqueue(new Callback<CategoryRVModel>() {
            @Override
            public void onResponse(Call<CategoryRVModel> call, Response<CategoryRVModel> response) {
                if (response.isSuccessful()) {
                    CategoryRVModel categories = response.body();
                    loadingPB.setVisibility(View.GONE);
                    ArrayList<Categories> categoriesArrayList1 = categories.getCategories();
                    for (int i = 0; i < categoriesArrayList1.size(); i++) {
                        categoriesArrayList.add(new Categories(categoriesArrayList1.get(i).getName(),categoriesArrayList1.get(i).getId() ));
                    }
                    categoryRVAdapter.notifyDataSetChanged();
                } else {

                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CategoryRVModel> call, Throwable t) {
                loadingPB.setVisibility(View.GONE);
                Log.d("Response Body", "Response: ");

                Toast.makeText(MainActivity.this, "Failure getting categories.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onCategoryClick(int position) {

    }
}