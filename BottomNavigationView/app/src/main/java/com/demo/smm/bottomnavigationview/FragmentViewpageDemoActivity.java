package com.demo.smm.bottomnavigationview;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/3.
 */

public class FragmentViewpageDemoActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationViewEx bottomNavigationView;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private NewLocationFragment mLocationFragment;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_fragment);
        ButterKnife.bind(this);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.add:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.delete:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.setting:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.me:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 如果想禁止滑动，可以把下面的代码取消注释
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(mLocationFragment.newInstance("拨号"));
        adapter.addFragment(mLocationFragment.newInstance("信息"));
        adapter.addFragment(mLocationFragment.newInstance("设置"));
        adapter.addFragment(mLocationFragment.newInstance("我的"));
        viewPager.setAdapter(adapter);
    }


}
