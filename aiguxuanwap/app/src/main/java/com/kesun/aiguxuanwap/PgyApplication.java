package com.kesun.aiguxuanwap;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by frank on 15/11/19.
 */
public class PgyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        PgyCrashManager.register(getApplicationContext());
    }
}
