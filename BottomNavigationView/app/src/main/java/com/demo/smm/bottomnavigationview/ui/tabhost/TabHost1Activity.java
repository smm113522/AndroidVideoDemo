package com.demo.smm.bottomnavigationview.ui.tabhost;

import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.adapter.ViewPagerAdapter;
import com.demo.smm.bottomnavigationview.fragment.AboutFragment;
import com.demo.smm.bottomnavigationview.fragment.FristFragment;
import com.demo.smm.bottomnavigationview.fragment.NewLocationFragment;
import com.demo.smm.bottomnavigationview.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class TabHost1Activity extends BaseActivity implements TabHost.OnTabChangeListener{

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {NewLocationFragment.class, AboutFragment.class};
    private int imageViewArray[] = {R.drawable.tab_icon_me, R.drawable.tab_icon_kehu};
    private String textViewArray[] = {"首页", "关于"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_host1;
    }

    @Override
    public void init() {

        StatusBarUtil.immersive(this, 0xff000000, 0.1f);
        layoutInflater = LayoutInflater.from(this);//加载布局管理器

        /*实例化FragmentTabHost对象并进行绑定*/
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);//绑定viewpager

        mTabHost.setOnTabChangedListener(this);
        int count = textViewArray.length;

        /*新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment*/
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置标签、图标和文字
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中，并绑定Fragment
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
//            mTabHost.getTabWidget().getChildAt(i)
//                    .setBackgroundResource(R.drawable.selector_tab_background);//设置Tab被选中的时候颜色改变
        }
        initPage();
    }

    /*初始化Fragment*/
    private void initPage() {

        //绑定Fragment适配器
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(FristFragment.newInstance());
        adapter.addFragment(AboutFragment.newInstance());
        pager.setAdapter(adapter);
        mTabHost.getTabWidget().setDividerDrawable(null);

        /*实现setOnTabChangedListener接口,目的是为监听界面切换），然后实现TabHost里面图片文字的选中状态切换*/
        /*简单来说,是为了当点击下面菜单时,上面的ViewPager能滑动到对应的Fragment*/
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private View getTabItemView(int i) {
        //将xml布局转换为view对象
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }

    @Override
    public void onTabChanged(String s) {
        int position = mTabHost.getCurrentTab();
        pager.setCurrentItem(position);//把选中的Tab的位置赋给适配器，让它控制页面切换
    }
}
