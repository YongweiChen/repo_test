package com.cyw.android.sqlitedemo;

import android.app.Application;

/**
 * Created by FH on 2017/2/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SqlManager sqlManager = SqlManager.getInstance(this);
    }
}
