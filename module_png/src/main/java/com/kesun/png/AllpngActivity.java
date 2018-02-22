package com.kesun.png;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.Date;

import cn.trinea.android.common.entity.HttpResponse;
import cn.trinea.android.common.service.HttpCache;
import cn.trinea.android.common.util.CacheManager;
import cn.trinea.android.common.util.StringUtils;

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
        } else {
        }
    }

    public void setmGetHtml() {
        String url = "https://www.baidu.com";

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
                } else {
                    Log.d("smm", "null");
                }

            }
        });
    }
}
