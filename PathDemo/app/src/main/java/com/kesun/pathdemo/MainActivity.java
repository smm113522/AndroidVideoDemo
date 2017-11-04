package com.kesun.pathdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private android.widget.LinearLayout llcontent;
    MySurfaceView mySurface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mySurface = new MySurfaceView(this);
//        setContentView(mySurface);

        setContentView(R.layout.activity_main);
        this.llcontent = (LinearLayout) findViewById(R.id.ll_content);
        Bezier bezier = new Bezier(this);
        Bezier2 bezier2 = new Bezier2(this);
        Bezier3 bezier3 = new Bezier3(this);
        llcontent.addView(bezier);
    }
}
