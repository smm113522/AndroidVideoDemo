package com.demo.smm.bottomnavigationview.osc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/3.
 */

public class OsActivity extends BaseActivity implements NavFragment.OnNavigationReselectListener {


    FrameLayout mainContainer;

    private NavFragment mNavBar;
    @BindView(R.id.activity_main_ui)
    LinearLayout activityMainUi;

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
        setContentView(R.layout.osactivity_layout);
        ButterKnife.bind(this);
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this, manager, R.id.main_container, this);
    }

    @Override
    public void onReselect(NavigationButton navigationButton) {
        Fragment fragment = navigationButton.getFragment();
        if (fragment != null
                && fragment instanceof OnTabReselectListener) {
            OnTabReselectListener listener = (OnTabReselectListener) fragment;
            listener.onTabReselect();
        }
    }
}
