package com.example.fullstacknewsapp_android;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsApplication extends Application {

    ExecutorService srv = Executors.newCachedThreadPool();

}
