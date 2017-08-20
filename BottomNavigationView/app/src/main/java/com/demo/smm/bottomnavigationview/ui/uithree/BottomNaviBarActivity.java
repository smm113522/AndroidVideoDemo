package com.demo.smm.bottomnavigationview.ui.uithree;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/3.
 */

public class BottomNaviBarActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.tb)
    TextView mTextView;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bottonnavibar;
    }

    @Override
    public void init() {


        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.common_btn_kehu_pr, "位置").setActiveColor(android.R.color.holo_orange_dark))
                .addItem(new BottomNavigationItem(R.drawable.common_btn_shouye_pr, "发现").setActiveColor(android.R.color.holo_blue_bright))
                .addItem(new BottomNavigationItem(R.drawable.common_btn_wode_pr, "爱好").setActiveColor(android.R.color.holo_green_dark))
                .addItem(new BottomNavigationItem(R.drawable.common_btn_xiangmu_pr, "图书").setActiveColor(android.R.color.holo_blue_light))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                mTextView.setText("位置");
                break;
            case 1:
                mTextView.setText("位置");
                break;
            case 2:
                mTextView.setText("爱好");
                break;
            case 3:
                mTextView.setText("图书");
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
