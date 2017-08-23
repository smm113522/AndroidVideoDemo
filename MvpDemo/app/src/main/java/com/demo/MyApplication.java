package com.demo;

import android.app.Application;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class MyApplication extends Application {


    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

    }

    public static MyApplication getInstance() {
        return myApplication;
    }

}
