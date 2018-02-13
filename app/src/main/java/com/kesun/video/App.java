package com.kesun.video;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Administrator on 2018/2/13 0013.
 */

public class App extends Application {

    private static App app;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

//            开启InstantRun之后，一定要在ARouter.init之前调用openDebug
        ARouter.openDebug();
        ARouter.openLog();

        ARouter.init(app); // 尽可能早，推荐在Application中初始化
    }

    public static App getInstance() {
        return app;
    }

    /*1，OnLowMemory被回调时，已经没有后台进程；而onTrimMemory被回调时，还有后台进程。
      2，OnLowMemory是在最后一个后台进程被杀时调用，一般情况是low memory killer 杀进程后触发；而OnTrimMemory的触发更频繁，每次计算进程优先级时，只要满足条件，都会触发。
      3，通过一键清理后，OnLowMemory不会被触发，而OnTrimMemory会被触发一次。*/
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /*   在onTrimMemory释放资源，释放图片、数组、缓存等资源。*/
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        /*避免了64K问题。*/
        MultiDex.install(this);
    }

}
