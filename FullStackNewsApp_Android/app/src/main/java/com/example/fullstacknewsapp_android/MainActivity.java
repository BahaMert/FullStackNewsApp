package com.example.fullstacknewsapp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface {

    RecyclerView recViewCat;
    RecyclerView recViewArt;
    ProgressBar prg;
    NewsMainRepository repo;

    Handler catHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<CategoryModel> data = (List<CategoryModel>) msg.obj;
            CategoryAdapter adp = new CategoryAdapter(MainActivity.this, data, MainActivity.this::onCategoryClick );
            recViewCat.setAdapter(adp);
            //prg.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    Handler artHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<ArticleModel> data = (List<ArticleModel>) msg.obj;
            NewsMainPageAdapter adp = new NewsMainPageAdapter(MainActivity.this, data);
            recViewArt.setAdapter(adp);
            prg.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prg = findViewById(R.id.idProgressBar);
        prg.setVisibility(View.VISIBLE);
        recViewCat = findViewById(R.id.idCategoriesRecyclerView);
        recViewArt = findViewById(R.id.idArticlesRecyclerView);
        //suanlik bunu boyle biraktim cunku layout kisminda ayarlamistim belki acabilirm geri
        recViewArt.setLayoutManager(new LinearLayoutManager(this));
        repo = new NewsMainRepository();
        repo.get_all_categories(((NewsApplication)getApplication()).srv,catHandler);
        repo.get_all_articles(((NewsApplication)getApplication()).srv,artHandler);

        Button button = (Button) findViewById(R.id.idViewAuthors);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AuthorsActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onCategoryClick(int position) {
        prg.setVisibility(View.VISIBLE);
        String categoryId = ((CategoryAdapter) recViewCat.getAdapter()).getCategoryAtPosition(position).getCategory_id();
        Log.d("Dev", "Checked category id: " + categoryId);
        repo.getArticlesByCategoryId(((NewsApplication)getApplication()).srv,artHandler, categoryId);
    }
}