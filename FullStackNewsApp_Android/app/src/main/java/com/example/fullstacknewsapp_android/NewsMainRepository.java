package com.example.fullstacknewsapp_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsMainRepository {

    public void get_all_categories(ExecutorService srv, Handler uiHandler){
        srv.submit(()->{
            try {

                List<CategoryModel> data_categories = new ArrayList<>();

                URL url =
                        new URL("http://10.0.2.2:8080/newssystem/categories");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                //now i need to construct my operating system objects

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject curr = arr.getJSONObject(i);

                    CategoryModel curr_article = new CategoryModel(curr.getString("name"), curr.getString("id"));
                    data_categories.add(curr_article);
                }
                //now i ve got data in my memory i ll just inform my handler.

                Message msg = new Message();

                msg.obj = data_categories;

                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("Dev", e.getMessage());
            } catch (IOException e) {
                Log.e("Dev", e.getMessage());
            } catch (JSONException e) {
                Log.e("Dev", e.getMessage());
            }

        });




    }

    public void get_all_articles(ExecutorService srv, Handler uiHandler){
        srv.submit(()->{
            try {

                List<ArticleModel> data_categories = new ArrayList<>();

                URL url =
                        new URL("http://10.0.2.2:8080/newssystem/articles");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                //now i need to construct my operating system objects

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject curr = arr.getJSONObject(i);
                    //String id, String title, int year, float rating, int people_rated, String content
                    //                   , String image_url
                    ArticleModel curr_article = new ArticleModel(curr.getString("id")
                            , curr.getString("title")
                            , curr.getInt("year")
                            , (float) curr.getDouble("rating")
                            , curr.getInt("people_rate")
                            , curr.getString("content")
                            , curr.getString("image_url"));
                    data_categories.add(curr_article);
                }
                //now i ve got data in my memory i ll just inform my handler.

                Message msg = new Message();

                msg.obj = data_categories;

                uiHandler.sendMessage(msg);

                //now i have to download the images from url

            } catch (MalformedURLException e) {
                Log.e("Dev", e.getMessage());
            } catch (IOException e) {
                Log.e("Dev", e.getMessage());
            } catch (JSONException e) {
                Log.e("Dev", e.getMessage());
            }

        });




    }

    public void downloadImage(ExecutorService srv, Handler uiHandler, String path){

        srv.submit(()-> {

                    try {
                        URL url =
                                new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        Bitmap bmp = BitmapFactory.decodeStream(conn.getInputStream());

                        Message msg = new Message();
                        msg.obj = bmp;

                        uiHandler.sendMessage(msg);

                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
                );


    }


}
