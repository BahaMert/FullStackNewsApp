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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import okhttp3.MediaType;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView recViewComments;
    TextInputEditText commentContentTextInput;
    NewsMainRepository repo;

    Handler commentPostHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            //sonra doldurabiliriz, gerekirse
            return true;
        }
    });
    Handler commentHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<CommentModel> data = (List<CommentModel>) msg.obj;
            CommentsAdapter adp = new CommentsAdapter(CommentsActivity.this, data );
            recViewComments.setAdapter(adp);
            //prg.setVisibility(View.INVISIBLE);
            TextView commentCount = findViewById(R.id.idCommentCountText);
            commentCount.setText(data.size() + " Comments");
            return true;
        }
    });

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        id = getIntent().getStringExtra("id");
        recViewComments = findViewById(R.id.idCommentRecyclerView);
        Toolbar toolbar = findViewById(R.id.idToolbarComments);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        repo = new NewsMainRepository();
        repo.getCommentsByArticleId(((NewsApplication)getApplication()).srv,commentHandler, id);

        Button button = (Button) findViewById(R.id.idPostCommentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = commentContentTextInput.getText().toString();
                repo.postCommentsByArticleIdAndContent(((NewsApplication)getApplication()).srv, commentHandler, id, commentContent);
            }
        });
    }
}