package com.demo.smm.bottomnavigationview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.demo.smm.bottomnavigationview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import carbon.widget.Button;
import carbon.widget.ImageView;
import carbon.widget.Toolbar;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class AboutFragment extends Fragment {

    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.right_txt)
    Button rightTxt;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String url = "http://shimengmeng.win";

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    public AboutFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle("关于我");
        rightIcon.setVisibility(View.GONE);
        rightTxt.setVisibility(View.GONE);
        toolbar.setIconVisible(false);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        //设置Web视图
        return view;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
