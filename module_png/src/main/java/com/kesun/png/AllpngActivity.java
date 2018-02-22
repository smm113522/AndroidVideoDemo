package com.kesun.png;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by Administrator on 2018/2/13 0013.
 */
@Route(path = "/png/mian")
public class AllpngActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGridview;
    private Button mGallery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allpng);
        initView();
    }

    private void initView() {
        mGridview = (Button) findViewById(R.id.gridview);
        mGridview.setOnClickListener(this);
        mGallery = (Button) findViewById(R.id.Gallery);
        mGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.gridview) {
            // TODO 18/02/22
            startActivity(new Intent(this, ImageCacheDemo.class));
        }else if (i == R.id.Gallery) {
            // TODO 18/02/22
            startActivity(new Intent(this, ImageSDCardCacheDemo.class));
        } else {
        }
    }
}
