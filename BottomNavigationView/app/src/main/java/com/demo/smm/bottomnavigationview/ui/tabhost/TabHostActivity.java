package com.demo.smm.bottomnavigationview.ui.tabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.demo.smm.bottomnavigationview.BaseActivity;
import com.demo.smm.bottomnavigationview.R;
import com.demo.smm.bottomnavigationview.fragment.AboutFragment;
import com.demo.smm.bottomnavigationview.fragment.FristFragment;
import com.demo.smm.bottomnavigationview.fragment.NewLocationFragment;
import com.demo.smm.bottomnavigationview.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class TabHostActivity extends BaseActivity {


    @BindView(R.id.maincontent)
    FrameLayout maincontent;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;


    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {FristFragment.class,NewLocationFragment.class,AboutFragment.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_icon_me,R.drawable.tab_icon_kehu,R.drawable.tab_icon_news};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "消息", "好友"};


    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_host;
    }

    @Override
    public void init() {
        //实例化布局对象

        StatusBarUtil.immersive(this, 0xff000000, 0.1f);
        layoutInflater = LayoutInflater.from(this);

        tabhost.setup(this, getSupportFragmentManager(), R.id.maincontent);

        //得到fragment的个数
        int count = fragmentArray.length;

        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            tabhost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
//            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.common_btn_kehu_pr);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }

}
