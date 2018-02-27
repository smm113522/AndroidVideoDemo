package com.kesun.png;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kesun.png.utils.GetImagePathUtil;

import java.util.ArrayList;
import java.util.Date;

import cn.trinea.android.common.entity.HttpResponse;
import cn.trinea.android.common.service.HttpCache;
import cn.trinea.android.common.util.CacheManager;

/**
 * Created by Administrator on 2018/2/13 0013.
 */
@Route(path = "/png/mian")
public class AllpngActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGridview;
    private Button mGallery;
    private Button mRecyclerview;
    private Button mGetHtml;
    private HttpCache httpCache;
    private TextView mContent;
    private Button mGetdemo;
    private Button mGettabdemo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allpng);
        // get the singleton instance of HttpCache
        httpCache = CacheManager.getHttpCache(getApplicationContext());
        initView();
    }

    private void initView() {
        mGridview = (Button) findViewById(R.id.gridview);
        mGridview.setOnClickListener(this);
        mGallery = (Button) findViewById(R.id.Gallery);
        mGallery.setOnClickListener(this);
        mRecyclerview = (Button) findViewById(R.id.recyclerview);
        mRecyclerview.setOnClickListener(this);
        mGetHtml = (Button) findViewById(R.id.getHtml);
        mGetHtml.setOnClickListener(this);
        mContent = (TextView) findViewById(R.id.content);
        mContent.setOnClickListener(this);
        mGetdemo = (Button) findViewById(R.id.getdemo);
        mGetdemo.setOnClickListener(this);
        mGettabdemo = (Button) findViewById(R.id.gettabdemo);
        mGettabdemo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.gridview) {
            // TODO 18/02/22
            startActivity(new Intent(this, ImageCacheDemo.class));
        } else if (i == R.id.Gallery) {
            // TODO 18/02/22
            startActivity(new Intent(this, ImageSDCardCacheDemo.class));
        } else if (i == R.id.recyclerview) {
            // TODO 18/02/22
            startActivity(new Intent(this, DemoActivity.class));
        } else if (i == R.id.getHtml) {
            // TODO 18/02/22
            setmGetHtml();
        } else if (i == R.id.getdemo) {
            startActivity(new Intent(this, AllDemoActivity.class));
        } else if (i == R.id.gettabdemo) {
            startActivity(new Intent(this, TabDemoActivity.class));
        } else if (i == R.id.content) {
            // TODO 18/02/22
            DemoActivity.StartActivity(this, list1);
        } else {

        }
    }

    private String content;
    ArrayList<String> list1;

    public void setmGetHtml() {
        String url = "http://p.codekk.com/detail/Android/zeleven/mua";

        httpCache.httpGet(url, new HttpCache.HttpCacheListener() {

            protected void onPreGet() {

            }

            protected void onPostGet(HttpResponse httpResponse, boolean isInCache) {
                if (httpResponse != null) {
                    StringBuilder sb = new StringBuilder(256);
                    sb.append("is in cache: ").append(isInCache).append("\r\n");
                    if (isInCache) {
                        sb.append("expires: ").append(new Date(httpResponse.getExpiredTime()).toGMTString())
                                .append("\r\n");
                    }

                    Log.d("smm", sb.toString());
                    Log.d("smm", httpResponse.getResponseBody());
                    content = httpResponse.getResponseBody();

//                    ArrayList<String> list = (ArrayList<String>) GetImagePathUtil.getImgStr(content);
                    list1 = GetImagePathUtil.getImgList(content);
//                    Log.d("smm", list.toString());
                    Log.d("smm", list1.toString());
                    mContent.setText(list1.toString());
                } else {
                    Log.d("smm", "null");
                }

            }
        });
    }
}
