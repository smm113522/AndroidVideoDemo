package com.demo.smm.bottomnavigationview.ui.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.fragment.AboutFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static carbon.widget.Toolbar.OnMenuItemClickListener;

/**
 * Created by Administrator on 2017/8/17 0017.
 * <p>
 * http://www.jianshu.com/p/ce1d060573ba
 */

public class TabLayoutActivity extends BaseActivity {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout1)
    TabLayout tabLayout1;
    @BindView(R.id.tabLayout2)
    TabLayout tabLayout2;
    @BindView(R.id.tabLayout3)
    TabLayout tabLayout3;
    @BindView(R.id.toolbars)
    Toolbar toolbars;
    @BindView(R.id.tabLayout4)
    TabLayout tabLayout4;
    @BindView(R.id.toolbars1)
    Toolbar toolbars1;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tablayout;
    }

    @Override
    public void init() {

        toolbars1.setTitle("       ");
        setHeadText(true, "TabLayout 的使用");
        toolbars1.setNavigationIcon(R.mipmap.nav_btn_back);
//        toolbars.setNavigationIcon(R.mipmap.nav_btn_back);

        setSupportActionBar(toolbars1);

        toolbars1.setOnMenuItemClickListener(onMenuItemClick);

        if (toolbars1!=null) {


            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        tabLayout.setupWithViewPager(viewPager);
        tabLayout1.setupWithViewPager(viewPager);

        tabLayout3.addTab(tabLayout3.newTab().setText("商品"));

        tabLayout3.addTab(tabLayout3.newTab().setText("详情"));

        tabLayout3.addTab(tabLayout3.newTab().setText("评论"));

        tabLayout3.getTabAt(1).select();
        tabLayout4.addTab(tabLayout4.newTab().setText("商品"));

        tabLayout4.addTab(tabLayout4.newTab().setText("详情"));

        tabLayout4.addTab(tabLayout4.newTab().setText("评论"));

        //指定Tab的位置
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
        three = tabLayout.getTabAt(2);
        four = tabLayout.getTabAt(3);

        //设置Tab的图标，假如不需要则把下面的代码删去
//        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);
//        three.setIcon(R.mipmap.ic_launcher);
//        four.setIcon(R.mipmap.ic_launcher);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitles = new String[]{"首页", "发现", "进货单", "我的"};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 1) {
                return new AboutFragment();
            } else if (position == 2) {
                return new AboutFragment();
            } else if (position == 3) {
                return new AboutFragment();
            }
            return new AboutFragment();
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

    private OnMenuItemClickListener onMenuItemClick = new OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.add:
                    msg += "Click edit";
                    break;
                case R.id.delete:
                    msg += "Click share";
                    break;
                case R.id.setting:
                    msg += "Click setting";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(TabLayoutActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };


}
