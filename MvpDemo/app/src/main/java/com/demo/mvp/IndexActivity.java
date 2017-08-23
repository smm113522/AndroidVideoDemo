package com.demo.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.demo.adapter.SimpleAdapter;
import com.demo.mvp.bean.Demo;
import com.demo.utils.InitRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/2/4.
 */

public class IndexActivity extends AppCompatActivity {

    @BindView(R.id.show_list)
    RecyclerView show_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* 基础使用 */
//        ActivityIndexBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_index);
//        Demo demo = new Demo("Test", "User");
//
//        binding.setDemo(demo);
//        binding.setTime(new Date());
//        binding.setIndex(this);
//
//        binding.setImageUrl("http://images.csdn.net/20150810/Blog-Image%E5%89%AF%E6%9C%AC.jpg");
//        binding.setImageUrl(R.mipmap.ic_launcher + "");
//        binding.setAge(12);

        /* listview 使用 */
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        List<Demo> Demos = new ArrayList<>();
        Demo demos = new Demo("Test1", R.mipmap.ic_launcher + "");
        Demos.add(demos);

        demos = new Demo("Test2", R.mipmap.ic_launcher + "");
        Demos.add(demos);

        demos = new Demo("Test3", R.mipmap.ic_launcher + "");
        Demos.add(demos);

        InitRecyclerView.initLinearLayoutVERTICAL(this,show_list);

        SimpleAdapter<Demo> adapter = new SimpleAdapter<Demo>(Demos, R.layout.demo_item, BR.demo);
        show_list.setAdapter(adapter);
    }


    public void DianWo(View view) {
        Toast.makeText(IndexActivity.this, "点我", Toast.LENGTH_SHORT).show();
    }
}
