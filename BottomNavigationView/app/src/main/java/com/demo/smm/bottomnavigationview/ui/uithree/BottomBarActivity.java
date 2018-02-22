package com.demo.smm.bottomnavigationview.ui.uithree;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.fragment.AboutFragment;
import com.demo.smm.bottomnavigationview.fragment.NewLocationFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public class BottomBarActivity extends BaseActivity {


    private android.widget.FrameLayout frameLayout;
    private com.roughike.bottombar.BottomBar bottomBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_buttom_bar;
    }

    @Override
    public void init() {

        this.bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        this.frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int[] tabs = new int[]{
                        R.id.tabProject,
                        R.id.tabTask,
                        R.id.tabMaopao,
                        R.id.tabMessage,
                        R.id.tabMy
                };

                for (int i = 0; i < tabs.length; ++i) {
                    if (tabs[i] == tabId) {
                        onNavigationDrawerItemSelected(i);
                    }
                }
            }
        });

        bottomBar.getTabWithId(R.id.tabProject).setBadgeCount(20);

    }

    public void onNavigationDrawerItemSelected(int position) {
//        mSelectPos = position;
        Fragment fragment = null;

        switch (position) {
            case 0://防止重复加载数据
//                fragment = new ProjectFragment_();
                bottomBar.getTabWithId(R.id.tabProject).setBadgeCount(20);
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                boolean containFragment = false;
                if (fragments != null) {
                    for (Fragment item : fragments) {
                        if (item instanceof NewLocationFragment) {
                            containFragment = true;
                            break;
                        }
                    }
                }

                if (!containFragment) {
                    fragment = new NewLocationFragment();
                }
                break;
            case 1:
                fragment = AboutFragment.newInstance();
                break;
            case 2:
                // 进入冒泡页面，单独处理
                break;

            case 3:
                bottomBar.getTabWithId(R.id.tabProject).setBadgeCount(-1);
                fragment = new NewLocationFragment();
                break;

            case 4:
                bottomBar.getTabWithId(R.id.tabProject).setBadgeCount(0);
                fragment = new NewLocationFragment();
                break;
        }


        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
    }

}
