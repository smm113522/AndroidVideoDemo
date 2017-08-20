package com.demo.smm.bottomnavigationview.ui.uithree;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;
import com.example.navigationbottom.MyNavigationBottom;
import com.example.navigationbottom.OnTabSelectListener;
import com.zhl.tabindicatorview.bean.TabItem;
import com.zhl.tabindicatorview.view.TabIndicatorView;
import com.zhl.tabindicatorview.view.TabItemView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class NavigationBottomDemoActivity extends BaseActivity {

    @BindView(R.id.navigationBtn1)
    MyNavigationBottom navigationBtn1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void init() {
        setHeadText(true,"NavigationBottomDemoActivity");
        navigationBtn1.show();

        navigationBtn1.setOnTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int index, View view) {
                Toast.makeText(NavigationBottomDemoActivity.this, "Selected "+index, Toast.LENGTH_SHORT).show();
            }
        });

        TabIndicatorView indicatorView = (TabIndicatorView) findViewById(R.id.tab_indicator_view);
        TabIndicatorView indicatorView2 = (TabIndicatorView) findViewById(R.id.tab_indicator_view2);
        ArrayList<TabItem> items = new ArrayList<TabItem>();
        for (int i = 0; i < 4; i++) {
            TabItem item = new TabItem();
            item.title = "近" + (i + 1) + "月";
            item.position = i;
            items.add(item);
        }
        indicatorView.initTabs(items, new TabIndicatorView.OnTabItemCheckListener() {
            @Override
            public void onTabItemCheck(TabItemView itemTabView, int position) {
                Toast.makeText(NavigationBottomDemoActivity.this, "当前选中==" + position, Toast.LENGTH_SHORT).show();
            }
        });
        indicatorView.setDefaultCheckedPos(1);

        ArrayList<TabItem> items2 = new ArrayList<TabItem>();
        for (int i = 0; i < 4; i++) {
            TabItem item = new TabItem();
            item.title = "第" + (i + 1) + "季度";
            item.position = i;
            items2.add(item);
        }
        indicatorView2.initTabs(items2, new TabIndicatorView.OnTabItemCheckListener() {
            @Override
            public void onTabItemCheck(TabItemView itemTabView, int position) {
                Toast.makeText(NavigationBottomDemoActivity.this, "当前选中==" + position, Toast.LENGTH_SHORT).show();
            }
        });
        indicatorView2.setDefaultCheckedPos(0);
    }


}
