//package com.kesun.aiguxuanwap;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.res.Configuration;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import java.util.List;
//
//
///**
// * Created by Administrator on 2017/4/12.
// */
//
//public class VideoActivity extends BaseActivity {
//
//    private VideoEnabledWebView webView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video);
////动态请求权限
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSIONS);
//                requestPermissions(
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
//            }
//        }
//        this.webView = (VideoEnabledWebView) findViewById(R.id.webView);
//        initWebViewSettings();
//    }
//
//    private void initWebViewSettings() {
//        WebSettings webSetting = this.webView.getSettings();
//        webSetting.setJavaScriptEnabled(true);
//        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSetting.setAllowFileAccess(true);
//        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webSetting.setSupportZoom(true);
//        webSetting.setBuiltInZoomControls(true);
//        webSetting.setUseWideViewPort(true);
//        webSetting.setSupportMultipleWindows(true);
//        // webSetting.setLoadWithOverviewMode(true);
//        webSetting.setAppCacheEnabled(true);
//        // webSetting.setDatabaseEnabled(true);
//        webSetting.setDomStorageEnabled(true);
//        webSetting.setGeolocationEnabled(true);
//        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
//        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
//        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
//        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
//
//        webSetting.setJavaScriptEnabled(true);
//        webSetting.setPluginState(WebSettings.PluginState.ON);
//        /**
//         * 控件
//         */
//
//        ll_content = (LinearLayout)findViewById(R.id.ll_content);
//        fl_video = (FrameLayout)findViewById(R.id.fl_video);
//        /**
//         * 设置webview
//         */
//
//        webSetting.setJavaScriptEnabled(true);
//        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSetting.setPluginState(WebSettings.PluginState.ON);
//        webSetting.setAllowFileAccess(true);
//        webSetting.setLoadWithOverviewMode(true);
//        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
//        webSetting.setUserAgentString(null);
//        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        webView.setWebChromeClient(new MyWebChromeClient());
//        webView.setWebViewClient(new MyWebViewClient());
//
//
//        webView.setWebViewClient(new WebViewClient(){
//            public boolean shouldOverrideUrlLoading(WebView view, String url){
//                view.loadUrl(url);
//                return true;
//            }
//        });
//
//        webView.setWebChromeClient(new WebChromeClient() {
//
//            /*** 视频播放相关的方法 **/
//
//            @Override
//            public View getVideoLoadingProgressView() {
//                FrameLayout frameLayout = new FrameLayout(VideoActivity.this);
//                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//                return frameLayout;
//            }
//
//            @Override
//            public void onShowCustomView(View view, CustomViewCallback callback) {
//                showCustomView(view, callback);
//            }
//
//            @Override
//            public void onHideCustomView() {
//                hideCustomView();
//            }
//        });
//
//        this.webView.loadUrl(MainActivity.url);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (webView.canGoBack()) {
//                webView.goBack();//返回上一浏览页面
//                return true;
//            } else {
////                finish();//关闭Activity
//                return exit();
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//
//
//    }
//
//    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSIONS = 1;
//    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 2;
//
//    @SuppressLint("NewApi")
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_CODE_WRITE_EXTERNAL_STORAGE: {
//                for (int i = 0; i < permissions.length; i++) {
//                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//
//
//                    } else {
//                    }
//
//                }
//            }
//            case REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSIONS: {
//                for (int i = 0; i < permissions.length; i++) {
//                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(this, "允许读写存储！", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(this, "未允许读写存储！", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//            break;
//            default: {
//                super.onRequestPermissionsResult(requestCode, permissions,
//                        grantResults);
//            }
//        }
//    }
//
//    boolean isExit = false;
//
//    Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            super.handleMessage(msg);
//            isExit = false;
//        }
//
//    };
//
//    public boolean exit() {
//        if (!isExit) {
//            isExit = true;
//            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            mHandler.sendEmptyMessageDelayed(0, 2000);
//            return true;
//        } else {
//            mHandler.removeCallbacksAndMessages(0);
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
//            System.exit(0);
//            return true;
//        }
//    }
//
//    /** 视频全屏参数 */
//    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//    private View customView;
//    private FrameLayout fullscreenContainer;
//    private WebChromeClient.CustomViewCallback customViewCallback;
//
//    /** 视频播放全屏 **/
//    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
//        // if a view already exists then immediately terminate the new one
//        if (webView != null) {
//            callback.onCustomViewHidden();
//            return;
//        }
//
//        this.getWindow().getDecorView();
//
//        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
//        fullscreenContainer = new FullscreenHolder(getApplicationContext());
//        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
//        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
//        customView = view;
//        setStatusBarVisibility(false);
//        customViewCallback = callback;
//    }
//
//    /** 隐藏视频全屏 */
//    private void hideCustomView() {
//        if (customView == null) {
//            return;
//        }
//
//        setStatusBarVisibility(true);
//        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
//        decor.removeView(fullscreenContainer);
//        fullscreenContainer = null;
//        customView = null;
//        customViewCallback.onCustomViewHidden();
//        webView.setVisibility(View.VISIBLE);
//    }
//
//    /** 全屏容器界面 */
//    static class FullscreenHolder extends FrameLayout {
//
//        public FullscreenHolder(Context ctx) {
//            super(ctx);
//            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
//        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent evt) {
//            return true;
//        }
//    }
//
//    private void setStatusBarVisibility(boolean visible) {
//        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    }
//
//    private LinearLayout ll_content;
//    private FrameLayout fl_video;
//    private String url;
//
//
//    class MyWebChromeClient extends WebChromeClient {
//        private CustomViewCallback customViewCallback;
//
//        // 全屏调用此函数
//        @SuppressWarnings("deprecation")
//        @Override
//        public void onShowCustomView(View view, int requestedOrientation,
//                                     CustomViewCallback callback) {
////            fullScreen();
//
//            super.onShowCustomView(view, requestedOrientation, callback);
//            if (customView != null) {
//                callback.onCustomViewHidden();
//                return;
//            }
//            customView = view;
//            customView.setVisibility(View.VISIBLE);
//            customViewCallback = callback;
//            ll_content.setVisibility(View.GONE);
//            fl_video.addView(view);
//            fl_video.setVisibility(View.VISIBLE);
//            fl_video.bringToFront();
////            //设置横屏
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            //设置全屏
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
//
//        // 退出全屏调用此函数
//        @Override
//        public void onHideCustomView() {
//            ll_content.setVisibility(View.VISIBLE);
//            ll_content.bringToFront();
//            if (customView == null) {
//                return;
//            }
//            try {
//                customViewCallback.onCustomViewHidden();
//            } catch (Exception e) {
//            }
//            customView.setVisibility(View.GONE);
//            fl_video.removeView(customView);
//            customView = null;
//            fl_video.setVisibility(View.GONE);
//            // 设置竖屏
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            // 取消全屏
//            final WindowManager.LayoutParams attrs = getWindow()
//                    .getAttributes();
//            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getWindow().setAttributes(attrs);
//            getWindow().clearFlags(
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
//    }
//
//    private void fullScreen() {
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
//    }
//
//    class MyWebViewClient extends WebViewClient {
//
//        // 重写超链接处理方法
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            return true;// 禁用超链接
//        }
//
//    }
//
//
//
//    /**
//     * 检查是否安装flashplayer
//     * @return
//     */
//    private boolean checkAdobeInstall(){
//        PackageManager manager = getPackageManager();
//        List<PackageInfo> infos = manager.getInstalledPackages(PackageManager.GET_SERVICES);
//
//        for(PackageInfo info : infos){
//            if("com.adobe.flashplayer".equals(info.packageName)){
//                Toast.makeText(getBaseContext(), "已安装flashplayer", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        }
//        Toast.makeText(getBaseContext(), "未检测到flashplayer，不能播放网络视频", Toast.LENGTH_SHORT).show();
//        return false;
//    }
//
//    @SuppressWarnings("deprecation")
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        webView.onPause();
//        webView.freeMemory();
//        ll_content.removeAllViews();
//        webView.removeAllViews();
//        webView.destroy();
//        webView = null;
//    }
//}
