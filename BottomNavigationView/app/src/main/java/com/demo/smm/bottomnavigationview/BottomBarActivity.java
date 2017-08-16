package com.demo.smm.bottomnavigationview;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

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
        return 0;
    }

    @Override
    public void init() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom_bar);
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

    }

    public void onNavigationDrawerItemSelected(int position) {
//        mSelectPos = position;
        Fragment fragment = null;
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            actionBarCompShadow.setVisibility(View.VISIBLE);
//        } else {
//            ViewCompat.setElevation(appbar, GlobalUnit.ACTIONBAR_SHADOW);
//        }
//
//        taskOper(position);
//        updateNotifyFromService();
        switch (position) {
            case 0://防止重复加载数据
//                fragment = new ProjectFragment_();
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
//                bottomBar.getTabWithId(R.id.tabProject).setBadgeCount(20);
                fragment = new NewLocationFragment();
                break;
            case 2:
                // 进入冒泡页面，单独处理
                break;

            case 3:
//                bottomBar.getTabWithId(R.id.tabProject).setBadgeCount(300);
                fragment = new NewLocationFragment();
                break;

            case 4:
//                bottomBar.getTabWithId(R.id.tabProject).setBadgeCount(0);
                fragment = new NewLocationFragment();
                break;
        }

        if (position == 2) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            boolean containFragment = false;
            for (Fragment item : fragments) {
                if (item instanceof NewLocationFragment) {
                    containFragment = true;
                    break;
                }
            }

            if (!containFragment) {
//                int pos = toolbarMaopaoTitle.getSelectedItemPosition();
//                toolbarMaopaoTitle.getOnItemSelectedListener().onItemSelected(null, null, pos, pos);
            }

//            visibleTitle(toolbarMaopaoTitle);
        } else if (position == 0 || position == 1) {
            if (position == 0) {
//                toolbarProjectTitle.setText("我的项目");
            }
            if (position == 1) {
//                toolbarProjectTitle.setText("我的任务");
            }
//            toolbarProjectTitle.setTag(position);
//            visibleTitle(toolbarProjectTitle);
        } else {
//            toolbarTitle.setVisibility(View.VISIBLE);
//            visibleTitle(toolbarTitle);
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
    }

}
