package com.demo.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_login, R.id.rxjava, R.id.Retrofit, R.id.RxCache})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                startActivity(new Intent(MainActivity.this,LoginMvpActivity.class));
                break;
            case R.id.rxjava:
                startActivity(new Intent(MainActivity.this,RxjavaActivity.class));
                break;
            case R.id.Retrofit:
                startActivity(new Intent(MainActivity.this,RetrofitActivity.class));
                break;
            case R.id.RxCache:
                startActivity(new Intent(MainActivity.this,RxCacheActivity.class));
                break;
        }
    }
}
