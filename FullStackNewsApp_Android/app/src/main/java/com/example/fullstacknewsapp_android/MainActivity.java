package com.example.fullstacknewsapp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    RecyclerView recViewCat;
    RecyclerView recViewArt;
    ProgressBar prg;

    Handler catHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<CategoryModel> data = (List<CategoryModel>) msg.obj;
            CategoryAdapter adp = new CategoryAdapter(MainActivity.this, data);
            recViewCat.setAdapter(adp);
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
        //suanlik bunu boyle biraktim cunku layout kisminda ayarlamistim belki acabilirm geri
        //recViewArt.setLayoutManager(new LinearLayoutManager(this));

        NewsMainRepository repo = new NewsMainRepository();
        repo.get_all_categories(((NewsApplication)getApplication()).srv,catHandler);


    }


}