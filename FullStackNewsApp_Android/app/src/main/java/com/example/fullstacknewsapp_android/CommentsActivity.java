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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import okhttp3.MediaType;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView recViewComments;
    EditText commentContentTextInput;
    EditText commenterTextInput;
    NewsMainRepository repo;

    Handler commentPostHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            //recreate(); buna gerek yok
            Intent intent = new Intent(CommentsActivity.this, CommentsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", id);
            startActivity(intent);
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
        commentContentTextInput = findViewById(R.id.idCommentBodyTextInput);
        commenterTextInput = findViewById(R.id.idCommenterTextInput);

        repo = new NewsMainRepository();
        repo.getCommentsByArticleId(((NewsApplication)getApplication()).srv,commentHandler, id);

        Button button = (Button) findViewById(R.id.idPostCommentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = commentContentTextInput.getText().toString();
                String commenter = commenterTextInput.getText().toString();

                /*if(commentContent.isEmpty()){
                    Toast.makeText(CommentsActivity.this, "Your Comment Can Not Be Empty!", Toast.LENGTH_LONG).show();
                    return;
                }*/
                if(commenter.isEmpty()){
                    commenter = "anonymous";
                }
                repo.postCommentsByArticleIdAndContent(((NewsApplication)getApplication()).srv, commentPostHandler, id, commenter, commentContent);
            }
        });
    }
}