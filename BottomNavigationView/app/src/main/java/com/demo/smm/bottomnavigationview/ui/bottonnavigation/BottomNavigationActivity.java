package com.demo.smm.bottomnavigationview.ui.bottonnavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.adapter.ViewPagerAdapter;
import com.demo.smm.bottomnavigationview.fragment.AboutFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class BottomNavigationActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bnv_menu)
    BottomNavigationView bnvMenu;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    ViewPagerAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_bottom_navigation;
    }

    @Override
    public void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*给底部导航栏菜单项添加点击事件*/
        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.delete:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.setting:
                        mViewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < 3; i++) {
            adapter.addFragment(AboutFragment.newInstance());
        }

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2); //预加载剩下两页

        // 如果想禁止滑动，可以把下面的代码取消注释
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

}
