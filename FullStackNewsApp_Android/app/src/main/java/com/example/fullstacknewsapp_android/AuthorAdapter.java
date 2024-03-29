package com.example.fullstacknewsapp_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>{

    private List<AuthorsModel> authorsList;
    private Context context;

    public AuthorsModel getAuthorAtPosition(int position) {
        return authorsList.get(position);
    }

    AuthorAdapter.AuthorClickInterface authorClickInterface;
    public AuthorAdapter(List<AuthorsModel> authorsList, Context context, AuthorClickInterface authorClickInterface) {
        this.authorsList = authorsList;
        this.context = context;
        this.authorClickInterface = authorClickInterface;
    }

    @NonNull
    @Override
    public AuthorAdapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.authors, parent, false);
        AuthorAdapter.AuthorViewHolder holder = new AuthorAdapter.AuthorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorAdapter.AuthorViewHolder holder, int position) {
        AuthorsModel author = authorsList.get(position);
        holder.authorFullNameTextView.setText(String.format("%s %s", author.getFirstName(), author.getLastName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorClickInterface.onAuthorClick(position);
            }
        });
    }

    public interface AuthorClickInterface{
        void onAuthorClick(int position);
    }
    @Override
    public int getItemCount() {
        return authorsList.size();
    }

    public class AuthorViewHolder extends RecyclerView.ViewHolder {
        private TextView authorFullNameTextView;
        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            authorFullNameTextView = itemView.findViewById(R.id.idAuthorFullName);
        }
    }
}

