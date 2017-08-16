package com.demo.smm.bottomnavigationview;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/3.
 */

public class JibenDemoActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jibendemo);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        text.setText(item.getTitle().toString().toUpperCase());
                        return true;
                    }
                });
    }
}
