package com.demo.smm.bottomnavigationview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chaychan.bottombarlayout.TouMainActivity;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.adapter.BaseRecyclerAdapter;
import com.demo.smm.bottomnavigationview.adapter.SmartViewHolder;
import com.demo.smm.bottomnavigationview.osc.OsActivity;
import com.demo.smm.bottomnavigationview.ui.uithree.AllBottonActivity;
import com.demo.smm.bottomnavigationview.ui.uithree.BottomBarActivity;
import com.demo.smm.bottomnavigationview.ui.uithree.BottomNaviBarActivity;
import com.demo.smm.bottomnavigationview.ui.uithree.LuseenBottomNavigationActity;
import com.demo.smm.bottomnavigationview.ui.uithree.NavigationBottomDemoActivity;
import com.demo.smm.bottomnavigationview.ui.uithree.QiHuTabLayoutActivity;
import com.demo.smm.bottomnavigationview.ui.weixin.WeixinActivity;
import com.demo.smm.bottomnavigationview.utils.StatusBarUtil;
import com.shizhefei.indicator.MainActivity;

import java.util.Arrays;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by Administrator on 2017/2/3.
 */

public class NewLocationFragment extends Fragment implements AdapterView.OnItemClickListener {


    private enum Item {
        BottomBar("A custom view component that mimics the new Material Design Bottom Navigation pattern.", BottomBarActivity.class),
        BottomNavigation("This Library helps users to use Bottom Navigation Bar (A new pattern from google) with ease and allows ton of customizations", AllBottonActivity.class),
        BottomNaviBar("网页引用-WebView", BottomNaviBarActivity.class),
        osc("开源中国底部导航", OsActivity.class),
        LuseenBottomNavigation("BottomNavigationView Designed according Google guideLine", LuseenBottomNavigationActity.class),
        QiHuTabLayout("仿照360底部菜单", QiHuTabLayoutActivity.class),
        NavigationBottomDemo1("模仿知乎的底部导航栏", NavigationBottomDemoActivity.class),
        TAB("一个简单的可自定义的 tab 导航控件", NavigationBottomDemoActivity.class),
        WEIXIN("仿照微信导航控件", WeixinActivity.class),
        XINLAN("仿照新浪地址", MainActivity.class),
        KAIXI("仿照今日头条底部", TouMainActivity.class),
        ;
        public String name;
        public Class<?> clazz;

        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

    }

    public static NewLocationFragment newInstance() {
        NewLocationFragment fragment = new NewLocationFragment();
        return fragment;
    }

    public static NewLocationFragment newInstance(String txt) {
        NewLocationFragment fragment = new NewLocationFragment();
        return fragment;
    }

    public NewLocationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        StatusBarUtil.setPaddingSmart(getContext(), root.findViewById(R.id.toolbar));

        View view = root.findViewById(R.id.recyclerView);
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
            recyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2, this) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, NewLocationFragment.Item model, int position) {
                    holder.text(android.R.id.text1, model.name());
                    holder.text(android.R.id.text2, model.name);
                    holder.textColorId(android.R.id.text2, R.color.colorAccent);
                }
            });
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getContext(), Item.values()[position].clazz));
    }

//    http://www.jianshu.com/p/ade8485a16be
//    https://github.com/Ashok-Varma/BottomNavigation
//    http://p.codekk.com/detail/Android/Arisono/MarksToAndroid 这里面搜索
}

