//package com.kesun.aiguxuanwap;
//
//import android.content.pm.ActivityInfo;
//import android.view.View;
//import android.view.WindowManager;
//import android.webkit.WebChromeClient;
//
///**
// * Created by Administrator on 2017/4/12.
// */
//
//public class MyWebChromeClient extends WebChromeClient {
//    private CustomViewCallback customViewCallback;
//    private int mOriginalOrientation = 1;
//
//    @Override
//    public void onShowCustomView(View view, CustomViewCallback callback) {
//        onShowCustomView(view, mOriginalOrientation, callback);
//        super.onShowCustomView(view, callback);
//
//    }
//
//    public void onShowCustomView(View view, int requestedOrientation,
//                                 WebChromeClient.CustomViewCallback callback) {
//        if (customView != null) {
//            callback.onCustomViewHidden();
//            return;
//        }
//        customView = view;
//        customViewCallback = callback;
//        mOriginalOrientation = getRequestedOrientation();
//        ll_content.setVisibility(View.GONE);
//        fl_video.addView(customView);
//        fl_video.setVisibility(View.VISIBLE);
//        fl_video.bringToFront();
//        //设置横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        //设置全屏
//        getParent().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    }
//
//    public void onHideCustomView() {
//        ll_content.setVisibility(View.VISIBLE);
//        if (customView == null) {
//            return;
//        }
//        fl_video.removeView(customView);
//        customView = null;
//        fl_video.setVisibility(View.GONE);
//        try {
//            customViewCallback.onCustomViewHidden();
//        } catch (Exception e) {
//        }
//        // 设置竖屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        // 取消全屏
//        final WindowManager.LayoutParams attrs = getParent().getWindow()
//                .getAttributes();
//        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getParent().getWindow().setAttributes(attrs);
//        getParent().getWindow().clearFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//    }
//}