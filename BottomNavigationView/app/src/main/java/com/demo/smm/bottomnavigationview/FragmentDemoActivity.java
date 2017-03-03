package com.demo.smm.bottomnavigationview;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/3.
 */

public class FragmentDemoActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.tb)
    LinearLayout tb;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private LocationFragment mLocationFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        /*固定大小用的*/
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.common_btn_kehu, "位置")
                        .setActiveColor(android.R.color.holo_blue_light))
                .addItem(new BottomNavigationItem(R.drawable.common_btn_shouye_pr, "发现").setActiveColor(android.R.color.holo_blue_bright))
                .addItem(new BottomNavigationItem(R.drawable.common_btn_wode_pr, "爱好").setActiveColor(android.R.color.holo_green_dark))
                .addItem(new BottomNavigationItem(R.drawable.common_btn_xiangmu_pr, "图书").setActiveColor(android.R.color.holo_blue_light))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mLocationFragment = LocationFragment.newInstance("位置1");
//        mLocationFragment = new LocationFragment();
//        mLocationFragment.txt = param1;
        transaction.replace(R.id.tb, mLocationFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                mLocationFragment = LocationFragment.newInstance("位置");
                transaction.replace(R.id.tb, mLocationFragment);
                break;
            case 1:
                mLocationFragment = LocationFragment.newInstance("发现");
                transaction.replace(R.id.tb, mLocationFragment);
                break;
            case 2:
                mLocationFragment = LocationFragment.newInstance("爱好");
                transaction.replace(R.id.tb, mLocationFragment);
                break;
            case 3:
                mLocationFragment = LocationFragment.newInstance("图书");
                transaction.replace(R.id.tb, mLocationFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
