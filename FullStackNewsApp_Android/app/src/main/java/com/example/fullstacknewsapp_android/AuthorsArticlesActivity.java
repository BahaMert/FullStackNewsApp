package com.example.fullstacknewsapp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class AuthorsArticlesActivity extends AppCompatActivity {

    RecyclerView recViewArticles;
    TextView authorArticleCount;
    TextView authorArticleHeading;
    NewsMainRepository repo;
    String authorid;
    String authorFullName;

    Handler articleHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<ArticleModel> data = (List<ArticleModel>) msg.obj;
            NewsMainPageAdapter adp = new NewsMainPageAdapter(AuthorsArticlesActivity.this, data);
            authorArticleCount.setText(String.format("%d %s", data.size(), "Articles"));
            recViewArticles.setAdapter(adp);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_articles);

        recViewArticles = findViewById(R.id.idAuthorsArticlesRecyclerView);
        authorArticleCount = findViewById(R.id.idAuthorArticlesCount);
        authorArticleHeading = findViewById(R.id.idAuthorArticlesHeading);

        recViewArticles.setLayoutManager(new LinearLayoutManager(this));
        authorid = getIntent().getStringExtra("id");
        authorFullName = getIntent().getStringExtra("name");

        Toolbar toolbar = findViewById(R.id.idToolbarAuthorsArticles);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        authorArticleHeading.setText(authorFullName);

        repo = new NewsMainRepository();
        repo.getArticlesByAuthorId(((NewsApplication)getApplication()).srv,articleHandler, authorid);
    }
}