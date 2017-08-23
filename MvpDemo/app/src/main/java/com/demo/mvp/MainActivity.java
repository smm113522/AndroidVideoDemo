package com.demo.mvp;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.demo.base.BaseActivity;
import com.demo.base.BaseModel;
import com.demo.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<BasePresenter, BaseModel> {

    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.rxjava)
    Button rxjava;
    @BindView(R.id.Retrofit)
    Button Retrofit;
    @BindView(R.id.RxCache)
    Button RxCache;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("MvpDemo");
    }


    @OnClick({R.id.bt_login, R.id.rxjava, R.id.Retrofit, R.id.RxCache,R.id.databinding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                startActivity(new Intent(MainActivity.this, LoginMvpActivity.class));
                break;
            case R.id.rxjava:
                startActivity(new Intent(MainActivity.this, RxjavaActivity.class));
                break;
            case R.id.Retrofit:
                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
                break;
            case R.id.RxCache:
                startActivity(new Intent(MainActivity.this, RxCacheActivity.class));
                break;
            case R.id.databinding:
                startActivity(new Intent(MainActivity.this, IndexActivity.class));
                break;
        }
    }
}
