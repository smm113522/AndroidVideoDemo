package com.example.snm.recyclerviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by snm on 2016/4/1.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    Button gotab,gorecycle,textInputlayout,floatingaction,navigationview,coordinatorlayout,AppBarLayout,collapsingtoolbarlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView(){
        gotab =(Button) findViewById(R.id.tablayout);
        gorecycle = (Button) findViewById(R.id.recyclerview);
        textInputlayout = (Button)findViewById(R.id.textInputlayout);
        floatingaction = (Button)findViewById(R.id.floatingaction);
        navigationview = (Button)findViewById(R.id.navigationview);

        coordinatorlayout = (Button)findViewById(R.id.coordinatorlayout);
        AppBarLayout = (Button)findViewById(R.id.AppBarLayout);
        collapsingtoolbarlayout = (Button)findViewById(R.id.collapsingtoolbarlayout);

        gotab.setOnClickListener(this);
        gorecycle.setOnClickListener(this);
        textInputlayout.setOnClickListener(this);
        floatingaction.setOnClickListener(this);
        navigationview.setOnClickListener(this);
        coordinatorlayout.setOnClickListener(this);
        AppBarLayout.setOnClickListener(this);
        collapsingtoolbarlayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tablayout:
                startActivity(new Intent(MainActivity.this,TabLayoutActivity.class));
                break;
            case R.id.recyclerview:
                startActivity(new Intent(MainActivity.this,RecyclerViewActivity.class));
                break;
            case R.id.textInputlayout:
                startActivity(new Intent(MainActivity.this,TextInputLayoutActivity.class));
                break;
            case R.id.floatingaction:
                startActivity(new Intent(MainActivity.this,FloatingActionButtonActivity.class));
                break;
            case R.id.navigationview:
                startActivity(new Intent(MainActivity.this,NavigationViewActivity.class));
                break;
            case R.id.coordinatorlayout:
                break;
            case R.id.AppBarLayout:
                break;
            case R.id.collapsingtoolbarlayout:
                break;
        }
    }
}
