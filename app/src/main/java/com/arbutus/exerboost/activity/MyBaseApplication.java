package com.arbutus.exerboost.activity;

import android.app.Application;

public class MyBaseApplication extends Application {

    private static Application applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
    }

    public static Application getContext(){
        return applicationContext;
    }
}
