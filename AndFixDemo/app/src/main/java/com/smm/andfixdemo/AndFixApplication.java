package com.smm.andfixdemo;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class AndFixApplication extends Application {

    public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化patch管理类
        mPatchManager = new PatchManager(this);

        // 初始化patch版本
        mPatchManager.init(BuildConfig.VERSION_NAME);
//        String appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//        mPatchManager.init(appVersion);

        // 加载已经添加到PatchManager中的patch
        mPatchManager.loadPatch();

        OkGo.getInstance().init(this);                           //必须调用初始化
//                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
//                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
//                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
//                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params);                       //全局公共参数


    }

}
