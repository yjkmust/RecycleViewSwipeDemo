package com.yaojie.swiperecyclerviewdemo;

import android.app.Application;

/**
 * Created by GEOFLY on 2017/5/25.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }
    }

    public static App getInstance() {
        return instance;
    }
}