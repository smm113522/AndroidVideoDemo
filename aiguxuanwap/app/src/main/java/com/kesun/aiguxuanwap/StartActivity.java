package com.kesun.aiguxuanwap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Created by Administrator on 2017/3/27.
 */

public class StartActivity extends AppCompatActivity {


    private Thread t1;
    private ImageView welcomeiv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        this.welcomeiv = (ImageView) findViewById(R.id.welcome_iv);

        t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    turnPage();// 跳转界面
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

    }

    /**
     * 跳转到主界面
     */
    private void turnPage() {
        Intent intent = new Intent(StartActivity.this, BrowserActivity.class);
        this.startActivity(intent);
        this.finish();
    }

}
