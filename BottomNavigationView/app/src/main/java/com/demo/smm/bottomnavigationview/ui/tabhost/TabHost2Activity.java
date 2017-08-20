package com.demo.smm.bottomnavigationview.ui.tabhost;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TabHost;

import com.demo.smm.bottomnavigationview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.Button;
import carbon.widget.ImageView;
import carbon.widget.Toolbar;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class TabHost2Activity extends TabActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host_2);
        ButterKnife.bind(this);

        toolbar.setTitle("tabhost");

        //从TabActivity上面获取放置Tab的TabHost
        tabhost = getTabHost();

        tabhost.addTab(tabhost
                //创建新标签one
                .newTabSpec("one")
                //设置标签标题
                .setIndicator("红色")
                //设置该标签的布局内容
                .setContent(R.id.widget_layout_red));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("黄色").setContent(R.id.widget_layout_yellow));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
