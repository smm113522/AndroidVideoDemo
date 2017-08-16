package com.demo.smm.bottomnavigationview.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.NewLocationFragment;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.ViewPagerAdapter;
import com.demo.smm.bottomnavigationview.fragment.AboutFragment;
import com.demo.smm.bottomnavigationview.utils.StatusBarUtil;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class DemoActivity extends BaseActivity {


    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationViewEx bottomNavigationBar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private NewLocationFragment mLocationFragment;
    MenuItem prevMenuItem;
    @Override
    public int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void init() {
//        setHeadText(false, "底部导航的使用");

        StatusBarUtil.immersive(this, 0xff000000, 0.1f);
        /* 点击变大 */
        bottomNavigationBar.enableAnimation(false);
        /* 突出 */
        bottomNavigationBar.enableShiftingMode(false);
        /* 文字隐藏 */
        bottomNavigationBar.enableItemShiftingMode(false);

        bottomNavigationBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.add:
                                viewpager.setCurrentItem(0);
                                break;
                            case R.id.delete:
                                viewpager.setCurrentItem(1);
                                break;
                            case R.id.setting:
                                viewpager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationBar.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationBar.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationBar.getMenu().getItem(position);

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

        setupViewPager(viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(mLocationFragment.newInstance("拨号"));
        adapter.addFragment(mLocationFragment.newInstance("信息"));
        adapter.addFragment(AboutFragment.newInstance());
        viewPager.setAdapter(adapter);
    }
}
