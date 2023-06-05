package com.example.fullstacknewsapp_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>{
    private List<CommentModel> commentModelArrayList;
    private Context context;

    public CommentsAdapter(Context context, List<CommentModel> commentModelArrayList) {
        this.commentModelArrayList = commentModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments, parent, false);
        return new CommentsAdapter.CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        CommentModel comment = commentModelArrayList.get(position);
        holder.commentBodyTV.setText(comment.getContent());
    }


    @Override
    public int getItemCount() {
        return commentModelArrayList.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {
        private TextView commentBodyTV;
        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            commentBodyTV = itemView.findViewById(R.id.idCommentBody);
        }
    }
}
