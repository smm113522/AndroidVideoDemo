package com.kesun.webview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.iguxuan.BaseActivity;
import com.iguxuan.BrowserActivity;
import com.kesun.webview.R;
import com.kesun.webview.adapter.BaseRecyclerAdapter;
import com.kesun.webview.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Arrays;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private android.support.v7.widget.RecyclerView recyclerView;
    private com.scwang.smartrefresh.layout.SmartRefreshLayout refreshLayout;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), Item.values()[position].clazz));
    }

    private enum Item {
        BAN1("爱股轩官网", BrowserActivity.class),
        BAN2("爱股轩官网", BrowserActivity.class),
        ;
        public String name;
        public Class<?> clazz;

        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), VERTICAL));
        recyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2, this) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                holder.text(android.R.id.text1, model.name());
                holder.text(android.R.id.text2, model.name);
                holder.textColorId(android.R.id.text2, R.color.colorAccent);
            }
        });

    }
}
