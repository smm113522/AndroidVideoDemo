package com.example.snm.recyclerviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by snm on 2016/4/1.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    Button gotab,gorecycle,textInputlayout;
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
        gotab.setOnClickListener(this);
        gorecycle.setOnClickListener(this);
        textInputlayout.setOnClickListener(this);
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

                break;
        }
    }
}
