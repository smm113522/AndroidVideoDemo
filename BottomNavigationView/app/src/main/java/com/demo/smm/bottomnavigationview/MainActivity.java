package com.demo.smm.bottomnavigationview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;

import com.demo.smm.bottomnavigationview.osc.OsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.viewstub)
    ViewStub viewstub;
    @BindView(R.id.leading)
    LinearLayout leading;
    private LinearLayout masked1;
    private LinearLayout masked2;
    private LinearLayout masked3;
    private LinearLayout masked4;
    private LinearLayout masked5;
    private LinearLayout masked6;
    private LinearLayout masked7;
    private LinearLayout masked8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        initView();
    }

    public void initView() {
        viewstub.inflate();
        leading.setVisibility(View.GONE);
        this.masked6 = (LinearLayout) findViewById(R.id.masked6);
        this.masked7 = (LinearLayout) findViewById(R.id.masked7);
        this.masked5 = (LinearLayout) findViewById(R.id.masked5);
        this.masked4 = (LinearLayout) findViewById(R.id.masked4);
        this.masked3 = (LinearLayout) findViewById(R.id.masked3);
        this.masked2 = (LinearLayout) findViewById(R.id.masked2);
        this.masked1 = (LinearLayout) findViewById(R.id.masked1);
        this.masked8 = (LinearLayout) findViewById(R.id.masked8);
        this.masked6.setOnClickListener(this);
        this.masked5.setOnClickListener(this);
        this.masked4.setOnClickListener(this);
        this.masked3.setOnClickListener(this);
        this.masked2.setOnClickListener(this);
        this.masked1.setOnClickListener(this);
        this.masked7.setOnClickListener(this);
        this.masked8.setOnClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.masked2:
                startActivity(new Intent(MainActivity.this, JibenDemoActivity.class));
                break;
            case R.id.masked3:
                startActivity(new Intent(MainActivity.this, BottomNaviBarActivity.class));
                break;
            case R.id.masked4:
                startActivity(new Intent(MainActivity.this, AllBottonActivity.class));
                break;
            case R.id.masked5:
                startActivity(new Intent(MainActivity.this, FragmentDemoActivity.class));
                break;
            case R.id.masked6:
                startActivity(new Intent(MainActivity.this, FragmentViewpageDemoActivity.class));
                break;
            case R.id.masked1:
                startActivity(new Intent(MainActivity.this, FristButtonActivity.class));
                break;
            case R.id.masked7:
                startActivity(new Intent(MainActivity.this, OsActivity.class));
                break;
            case R.id.masked8:
                startActivity(new Intent(MainActivity.this, BottomBarActivity.class));
                break;
        }
    }

//    @OnClick({R.id.tv_jbsy, R.id.tv_jbsy_bar, R.id.tv_jbsy_all, R.id.tv_jbsy_fragment,
//            R.id.tv_jbsy_page_fragment, R.id.tv_group})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_jbsy:
//                startActivity(new Intent(MainActivity.this, JibenDemoActivity.class));
//                break;
//            case R.id.tv_jbsy_bar:
//                startActivity(new Intent(MainActivity.this, BottomNaviBarActivity.class));
//                break;
//            case R.id.tv_jbsy_all:
//                startActivity(new Intent(MainActivity.this, AllBottonActivity.class));
//                break;
//            case R.id.tv_jbsy_fragment:
//                startActivity(new Intent(MainActivity.this, FragmentDemoActivity.class));
//                break;
//            case R.id.tv_jbsy_page_fragment:
//                startActivity(new Intent(MainActivity.this, FragmentViewpageDemoActivity.class));
//                break;
//            case R.id.tv_group:
//                startActivity(new Intent(MainActivity.this, FristButtonActivity.class));
//                break;
//        }
//    }
}
