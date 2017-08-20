package com.demo.smm.bottomnavigationview.ui.other;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.adapter.ViewPagerAdapter;
import com.demo.smm.bottomnavigationview.fragment.AboutFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/20 0020.
 * 自定义的原理就是单独把
 * 下面的写出来。还有吧其他方式。
 *
 */

public class OtherActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.ivChat)
    ImageView ivChat;
    @BindView(R.id.tvChat)
    TextView tvChat;
    @BindView(R.id.llChat)
    LinearLayout llChat;

    @BindView(R.id.ivFriends)
    ImageView ivFriends;
    @BindView(R.id.tvFriends)
    TextView tvFriends;
    @BindView(R.id.llFriends)
    LinearLayout llFriends;

    @Override
    public int getLayoutId() {
        return R.layout.activity_other;
    }

    @Override
    public void init() {
        llChat.setOnClickListener(this);
        llFriends.setOnClickListener(this);

        ivChat.setSelected(true);
        tvChat.setSelected(true);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                changeTab(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < 2; i++) {

            adapter.addFragment(AboutFragment.newInstance());
        }

        //绑定Fragment适配器
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }

    private void changeTab(int id) {
        ivChat.setSelected(false);
        tvChat.setSelected(false);

        ivFriends.setSelected(false);
        tvFriends.setSelected(false);
        switch (id) {
            case R.id.llChat:
                viewPager.setCurrentItem(0);
            case 0:
                ivChat.setSelected(true);
                tvChat.setSelected(true);
                break;
            case R.id.llFriends:
                viewPager.setCurrentItem(1);
            case 1:
                ivFriends.setSelected(true);
                tvFriends.setSelected(true);
                break;


        }
    }
}
