package com.demo.smm.bottomnavigationview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_jbsy)
    TextView tvJbsy;
    @BindView(R.id.tv_jbsy_bar)
    TextView tvJbsyBar;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.tv_jbsy_all)
    TextView tvJbsyAll;
    @BindView(R.id.tv_jbsy_fragment)
    TextView tvJbsyFragment;
    @BindView(R.id.tv_jbsy_page_fragment)
    TextView tvJbsyPageFragment;
    @BindView(R.id.tv_group)
    TextView tvGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_jbsy, R.id.tv_jbsy_bar, R.id.tv_jbsy_all, R.id.tv_jbsy_fragment,
            R.id.tv_jbsy_page_fragment,R.id.tv_group})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jbsy:
                startActivity(new Intent(MainActivity.this, JibenDemoActivity.class));
                break;
            case R.id.tv_jbsy_bar:
                startActivity(new Intent(MainActivity.this, BottomNaviBarActivity.class));
                break;
            case R.id.tv_jbsy_all:
                startActivity(new Intent(MainActivity.this, AllBottonActivity.class));
                break;
            case R.id.tv_jbsy_fragment:
                startActivity(new Intent(MainActivity.this, FragmentDemoActivity.class));
                break;
            case R.id.tv_jbsy_page_fragment:
                startActivity(new Intent(MainActivity.this, FragmentViewpageDemoActivity.class));
                break;
            case R.id.tv_group:
                startActivity(new Intent(MainActivity.this, FristButtonActivity.class));
                break;
        }
    }
}
