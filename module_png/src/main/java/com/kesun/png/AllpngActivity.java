package com.kesun.png;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by Administrator on 2018/2/13 0013.
 */
@Route(path = "/png/mian")
public class AllpngActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allpng);
    }
}
