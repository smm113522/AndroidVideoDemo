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

import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.adapter.BaseRecyclerAdapter;
import com.demo.smm.bottomnavigationview.adapter.SmartViewHolder;
import com.demo.smm.bottomnavigationview.ui.bottonnavigation.BottomNavigationActivity;
import com.demo.smm.bottomnavigationview.ui.other.OtherActivity;
import com.demo.smm.bottomnavigationview.ui.radiogroup.FristButtonActivity;
import com.demo.smm.bottomnavigationview.ui.radiogroup.TwoButtonActivity;
import com.demo.smm.bottomnavigationview.ui.tabhost.TabHost1Activity;
import com.demo.smm.bottomnavigationview.ui.tabhost.TabHost2Activity;
import com.demo.smm.bottomnavigationview.ui.tabhost.TabHostActivity;
import com.demo.smm.bottomnavigationview.ui.tablayout.TabLayout1Activity;
import com.demo.smm.bottomnavigationview.ui.tablayout.TabLayoutActivity;
import com.demo.smm.bottomnavigationview.utils.StatusBarUtil;

import java.util.Arrays;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class FristFragment extends Fragment implements AdapterView.OnItemClickListener {

    private enum Item {
        RadioGroupRadioButton0("RadioGroup+RadioButton 组合产生底部", FristButtonActivity.class),
        RadioGroupRadioButton1("这个加上viewPager", TwoButtonActivity.class),
        TabHost0("TabHostActivity底部导航", TabHostActivity.class),
        TabHost1("TabHost+viewpager", TabHost1Activity.class),
        TabHost2("TabHost实现Tab切换", TabHost2Activity.class),
        TabLayout("TabLayout基本使用", TabLayoutActivity.class),
        TabLayout1("TabLayout 底部导航", TabLayout1Activity.class),
        BottomNavigation("BottomNavigation 底部导航", BottomNavigationActivity.class),
        BottomDaoHang("自定义 底部导航", OtherActivity.class),
        ;
        public String name;
        public Class<?> clazz;
        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

    }

    public static FristFragment newInstance() {
        FristFragment fragment = new FristFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_refresh_practive, container, false);
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
            recyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2,this) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
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
}
