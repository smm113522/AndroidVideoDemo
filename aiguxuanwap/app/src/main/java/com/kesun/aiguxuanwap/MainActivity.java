//package com.kesun.aiguxuanwap;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
//import android.view.KeyEvent;
//import android.view.View;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//public class MainActivity extends AppCompatActivity {
//
//    public static String url = BuildConfig.HTTPURL;
//    private WebView webView;
//    private android.widget.RelativeLayout activitymain;
//    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSIONS = 1;
//    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 2;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //动态请求权限
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
//
//        this.activitymain = (RelativeLayout) findViewById(R.id.activity_main);
//        this.webView = (WebView) findViewById(R.id.webView);
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);//设置支持javascript
////        webView.addJavascriptInterface(new JavaScriptInterface(), "xueleapp");
//        webView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress == 100) {
//                    //网页加载完成
//                } else {
//                    //网页加载中
//                }
//            }
//        });
//
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//
//        webSettings.setSupportZoom(true);  //支持缩放
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
//        webSettings.supportMultipleWindows();  //多窗口
//        webSettings.setAllowFileAccess(true);  //设置可以访问文件
//        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
//        webSettings.setBuiltInZoomControls(true); //设置支持缩放
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
////        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//
//        //settings.setUseWideViewPort(true);造成文字太小
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setDatabaseEnabled(true);
//        webSettings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
//        webSettings.setAppCacheEnabled(true);
//
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//
//
//        webView.loadUrl(url);
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
//    public boolean exit(){
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
//}
