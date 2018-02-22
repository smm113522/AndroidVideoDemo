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
import com.demo.smm.bottomnavigationview.ui.radiogroup.TwoButtonActivity;
import com.demo.smm.bottomnavigationview.ui.sos.SaoDemoActivity;
import com.demo.smm.bottomnavigationview.ui.tab.PagerSlidingTabStripActivity;
import com.demo.smm.bottomnavigationview.utils.StatusBarUtil;

import java.util.Arrays;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class SeacodFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getContext(), Item.values()[position].clazz));
    }

    private enum Item {
        PagerSlidingTabStrip("Android PagerSlidingTabStrip", PagerSlidingTabStripActivity.class),
        RadioGroupRadioButton1("这个加上viewPager", TwoButtonActivity.class),
        Flashlight("手电筒", SaoDemoActivity.class),
//        Flashlight1("手电筒", FlashActivity.class),
        ;
        public String name;
        public Class<?> clazz;
        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

    }

    public static SeacodFragment newInstance() {
        SeacodFragment fragment = new SeacodFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
}
