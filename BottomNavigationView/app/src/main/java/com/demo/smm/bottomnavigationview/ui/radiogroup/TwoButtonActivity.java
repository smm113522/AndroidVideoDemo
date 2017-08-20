package com.demo.smm.bottomnavigationview.ui.radiogroup;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.adapter.ViewPagerAdapter;
import com.demo.smm.bottomnavigationview.fragment.AboutFragment;
import com.demo.smm.bottomnavigationview.fragment.FristFragment;
import com.demo.smm.bottomnavigationview.fragment.NewLocationFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/8.
 * <p>
 * https://github.com/Kaopiz/android-segmented-control
 * 使用第三方控件
 */

public class TwoButtonActivity extends BaseActivity {


    @BindView(R.id.rb_kh_all)
    RadioButton rbKhAll;
    @BindView(R.id.rb_kh_bb)
    RadioButton rbKhBb;
    @BindView(R.id.rb_kh_rg)
    RadioButton rbKhRg;
    @BindView(R.id.rb_kh_qy)
    RadioButton rbKhQy;


    @BindView(R.id.button31)
    RadioButton button31;
    @BindView(R.id.button32)
    RadioButton button32;
    @BindView(R.id.button33)
    RadioButton button33;
    @BindView(R.id.button34)
    RadioButton button34;


    @BindView(R.id.mRb_home)
    RadioButton mRbHome;
    @BindView(R.id.mRb_message)
    RadioButton mRbMessage;
    @BindView(R.id.mRb_find)
    RadioButton mRbFind;
    @BindView(R.id.mRb_my)
    RadioButton mRbMy;


    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_twobtitton;
    }

    @Override
    public void init() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rbKhAll.setChecked(true);
                        button31.setChecked(true);
                        mRbHome.setChecked(true);
                        break;
                    case 1:
                        rbKhBb.setChecked(true);
                        button32.setChecked(true);
                        mRbMessage.setChecked(true);
                        break;
                    case 2:
                        rbKhRg.setChecked(true);
                        button33.setChecked(true);
                        mRbFind.setChecked(true);
                        break;
                    case 3:
                        rbKhQy.setChecked(true);
                        button34.setChecked(true);
                        mRbMy.setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 如果想禁止滑动，可以把下面的代码取消注释
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewpager);
    }


    ViewPagerAdapter adapter;
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(FristFragment.newInstance());
        adapter.addFragment(NewLocationFragment.newInstance());
        adapter.addFragment(AboutFragment.newInstance());
        adapter.addFragment(AboutFragment.newInstance());
        viewPager.setAdapter(adapter);
    }


    @OnClick({R.id.rb_kh_all, R.id.rb_kh_bb, R.id.rb_kh_rg, R.id.rb_kh_qy, R.id.button31,
            R.id.button32, R.id.button33, R.id.button34, R.id.mRb_home, R.id.mRb_message,
            R.id.mRb_find, R.id.mRb_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_kh_all:
            case R.id.button31:
            case R.id.mRb_home:
                viewpager.setCurrentItem(0);
                break;
            case R.id.button32:
            case R.id.rb_kh_bb:
            case R.id.mRb_message:
                viewpager.setCurrentItem(1);
                break;
            case R.id.button33:
            case R.id.rb_kh_rg:
            case R.id.mRb_find:
                viewpager.setCurrentItem(2);
                break;
            case R.id.button34:
            case R.id.rb_kh_qy:
            case R.id.mRb_my:
                viewpager.setCurrentItem(3);
                break;
        }
    }
}
