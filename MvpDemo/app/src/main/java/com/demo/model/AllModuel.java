package com.demo.model;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class AllModuel extends BaseModel {

    private static AllModuel allModuel = null;

    public static AllModuel getInstance(Context context) {
        if (allModuel == null) {
            allModuel = new AllModuel(context);
        }

        return allModuel;
    }

    public AllModuel(Context context) {
        super(context);
    }
    public AllModuel() {
        super(null);
    }


    /**
     * 1用户登录
     *
     * @param account
     * @param password
     */
    public void Login(String account, String password, final ResponseCallBack responseCallBack) {
        OkHttpUtils.post().url("http://yueche.app.zhsg01.samyon.com/OsVIlaNb61zP796pr.php/Passport/login")
                .addParams("account", account)
                .addParams("password", password)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                responseCallBack.OnResponseFaile(201, "接口错误");
            }

            @Override
            public void onResponse(String response, int id) {
                responseCallBack.OnResponseSucess(200, response);
            }
        });
    }


}

