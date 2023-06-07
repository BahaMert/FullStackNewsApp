package com.example.fullstacknewsapp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AuthorsActivity extends AppCompatActivity {

    RecyclerView recViewAuthors;
    NewsMainRepository repo;

    Handler authorHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<AuthorsModel> data = (List<AuthorsModel>) msg.obj;
            AuthorAdapter adp = new AuthorAdapter(data, AuthorsActivity.this);
            recViewAuthors.setAdapter(adp);
            //prg.setVisibility(View.INVISIBLE);
            TextView commentCount = findViewById(R.id.idAuthorCountText);
            commentCount.setText(String.format("%d Authors", data.size()));
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        recViewAuthors = findViewById(R.id.idAuthorsRecyclerView);
        Toolbar toolbar = findViewById(R.id.idToolbarAuthors);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        repo = new NewsMainRepository();
        repo.get_all_authors(((NewsApplication)getApplication()).srv,authorHandler);
    }
}