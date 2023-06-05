package com.example.fullstacknewsapp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailActivity extends AppCompatActivity {

    String id;

    ImageView imgArticle;
    TextView txtArticleContent;
    TextView txtArticleTitle;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            ArticleModel art = (ArticleModel) msg.obj;
            txtArticleContent.setText(art.getContent());
            txtArticleTitle.setText(art.getTitle());

            NewsMainRepository repo = new NewsMainRepository();


            repo.downloadImage(((NewsApplication)getApplication()).srv, imgHandler, art.getImage_url());
            return true;
        }
    });


    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            imgArticle.setImageBitmap((Bitmap)msg.obj);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        imgArticle = findViewById(R.id.idArticleDetailImageView);
        txtArticleContent = findViewById(R.id.idArticleContent);
        txtArticleTitle = findViewById(R.id.idArticleTitle);
        id = getIntent().getStringExtra("id");
        Toolbar toolbar = findViewById(R.id.idToolbarNewsDetail);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Button button = (Button) findViewById(R.id.idShowCommentsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsDetailActivity.this, CommentsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        NewsMainRepository repo = new NewsMainRepository();
        repo.getArticleByArticleId(((NewsApplication)getApplication()).srv, handler, id);
    }



}