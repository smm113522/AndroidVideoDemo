package com.demo.mvp;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.demo.adapter.BaseRecyclerAdapter;
import com.demo.adapter.SmartViewHolder;
import com.demo.base.BaseActivity;
import com.demo.base.BaseModel;
import com.demo.base.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Arrays;

import butterknife.BindView;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends BaseActivity<BasePresenter, BaseModel> implements AdapterView.OnItemClickListener {


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), Item.values()[position].clazz));
    }

    private enum Item {
        MVPDEMO("mvp 登录 中使用", LoginMvpActivity.class),
        DATABINDING("DataBinding 的使用", IndexActivity.class),
        ;
        public String name;
        public Class<?> clazz;
        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

    }
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("MvpDemo");
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), VERTICAL));
        recyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2,this) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                holder.text(android.R.id.text1, model.name());
                holder.text(android.R.id.text2, model.name);
                holder.textColorId(android.R.id.text2, R.color.colorAccent);
            }
        });
    }


//    @OnClick({R.id.bt_login, R.id.rxjava, R.id.Retrofit, R.id.RxCache,R.id.databinding})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_login:
//                startActivity(new Intent(MainActivity.this, LoginMvpActivity.class));
//                break;
//            case R.id.rxjava:
//                startActivity(new Intent(MainActivity.this, RxjavaActivity.class));
//                break;
//            case R.id.Retrofit:
//                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
//                break;
//            case R.id.RxCache:
//                startActivity(new Intent(MainActivity.this, RxCacheActivity.class));
//                break;
//            case R.id.databinding:
//                startActivity(new Intent(MainActivity.this, IndexActivity.class));
//                break;
//        }
//    }
}
