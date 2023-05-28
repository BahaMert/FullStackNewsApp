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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{


    Context context;
    List<CategoryModel> data;

    public CategoryModel getCategoryAtPosition(int position) {
        return data.get(position);
    }
    CategoryClickInterface categoryClickInterface;

    public CategoryAdapter(Context context, List<CategoryModel> data, CategoryClickInterface categoryClickInterface) {
        this.context = context;
        this.data = data;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root =
                LayoutInflater.from(context).inflate(R.layout.categories, parent, false);
        //rootview will become itemview of holder
        CategoryViewHolder holder = new CategoryViewHolder(root);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.txtName.setText(data.get(position).getName());
        //eger sorun olursa categoryviewholderdan da id yi cikar yada buradan almak gerekbilir id yi bakalim
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }
    class CategoryViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout row;
        TextView txtName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.idcategories_row_list);
            txtName = itemView.findViewById(R.id.idCategoryTextView);

        }
    }


}
