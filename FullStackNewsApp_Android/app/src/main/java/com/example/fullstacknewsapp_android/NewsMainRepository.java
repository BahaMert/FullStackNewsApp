package com.example.fullstacknewsapp_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

    public void get_all_articles(ExecutorService srv, Handler uiHandler) {
        srv.submit(() -> {
            try {
                Log.d("Dev", "Starting network request");

                List<ArticleModel> data_categories = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/newssystem/articles");
                Log.d("Dev", "URL created: " + url.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.d("Dev", "Connection opened");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                Log.d("Dev", "Reader created");

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                Log.d("Dev", "Response read");

                JSONArray arr = new JSONArray(buffer.toString());
                Log.d("Dev", "JSON array created");

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject curr = arr.getJSONObject(i);
                    ArticleModel curr_article = new ArticleModel(curr.getString("id")
                            , curr.getString("title")
                            , curr.getInt("year")
                            , (float) curr.getDouble("rating")
                            , curr.getInt("people_rated")
                            , curr.getString("content")
                            , curr.getString("image_url"));
                    data_categories.add(curr_article);
                    Log.d("Dev", "Added article: " + curr.getString("title"));
                }
                Log.d("Dev", "All articles processed");

                Message msg = new Message();

                msg.obj = data_categories;

                uiHandler.sendMessage(msg);

                Log.d("Dev", "Message sent to UI handler");

            } catch (MalformedURLException e) {
                Log.e("Dev", "Malformed URL exception", e);
            } catch (IOException e) {
                Log.e("Dev", "IO exception", e);
            } catch (JSONException e) {
                Log.e("Dev", "JSON exception", e);
            }
        });
    }

    public void getArticlesByCategoryId(ExecutorService srv, Handler uiHandler, String categoryId) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("categoryid",categoryId);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        srv.submit(() -> {
            try {
                Log.d("Dev", "Starting network request");

                List<ArticleModel> data_categories = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/newssystem/articles/search/category"); // replace with your actual URL
                Log.d("Dev", "URL created: " + url.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.d("Dev", "Connection opened");

                // Set the request method to POST
                conn.setRequestMethod("POST");

                // Enable input and output streams
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Set the content type of the request body
                conn.setRequestProperty("Content-Type", "application/json");

                // Create JSON object
                BufferedOutputStream writer = new BufferedOutputStream(conn.getOutputStream());
                writer.write(obj.toString().getBytes());
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                Log.d("Dev", "Reader created");

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                Log.d("Dev", "Response read");

                JSONArray arr = new JSONArray(buffer.toString());
                Log.d("Dev", "JSON array created");

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject curr = arr.getJSONObject(i);
                    ArticleModel curr_article = new ArticleModel(curr.getString("id")
                            , curr.getString("title")
                            , curr.getInt("year")
                            , (float) curr.getDouble("rating")
                            , curr.getInt("people_rated")
                            , curr.getString("content")
                            , curr.getString("image_url"));
                    data_categories.add(curr_article);
                    Log.d("Dev", "Added article: " + curr.getString("title"));
                }
                Log.d("Dev", "All articles processed");

                Message msg = new Message();

                msg.obj = data_categories;

                uiHandler.sendMessage(msg);

                Log.d("Dev", "Message sent to UI handler");

            } catch (MalformedURLException e) {
                Log.e("Dev", "Malformed URL exception", e);
            } catch (IOException e) {
                Log.e("Dev", "IO exception", e);
            } catch (JSONException e) {
                Log.e("Dev", "JSON exception", e);
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
