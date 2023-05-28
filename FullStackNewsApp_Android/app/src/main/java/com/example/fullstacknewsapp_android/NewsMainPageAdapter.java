package com.example.fullstacknewsapp_android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;

//for articles
public class NewsMainPageAdapter extends RecyclerView.Adapter<NewsMainPageAdapter.NewsMainPageHolder>{

    Context context;
    List<ArticleModel> data;

    public NewsMainPageAdapter(Context context, List<ArticleModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NewsMainPageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root =
                LayoutInflater.from(context).inflate(R.layout.news, parent, false);
        //rootview will become itemview of holder
        NewsMainPageHolder holder = new NewsMainPageHolder(root);

        //because there are images since images are recycled by framework
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsMainPageHolder holder, int position) {
        holder.txtName.setText(data.get(position).getTitle());

        NewsApplication app = (NewsApplication) ((Activity) context).getApplication();
        holder.downloadImage(app.srv, data.get(position).getImage_url());

        //then manage the list selection or click events.

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NewsMainPageHolder extends RecyclerView.ViewHolder{
        ConstraintLayout row;
        TextView txtName;
        ImageView imgView;

        boolean image_downloaded;

        Handler imageHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                imgView.setImageBitmap((Bitmap) msg.obj);
                image_downloaded = true;

                return true;
            }
        });
        public NewsMainPageHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.idNews_Column_list);
            txtName = itemView.findViewById(R.id.idNewsTextViewHeading);
            imgView = itemView.findViewById(R.id.idNewsImageView);


        }

        public void downloadImage(ExecutorService srv, String path){

            if(image_downloaded == false){
                NewsMainRepository repo = new NewsMainRepository();
                repo.downloadImage(srv,imageHandler,path);
            }

        }
    }
}
