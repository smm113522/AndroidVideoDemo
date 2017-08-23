package com.demo.mvp.main;


import com.demo.model.AllModuel;
import com.demo.model.ResponseCallBack;

/**
 * Created by TMVPHelper on 2016/10/20
 */
public class LoginModel implements LoginContract.Model {


    @Override
    public void login(String acount, String password, ResponseCallBack responseCallBack) {
        if("".equals(acount)){
            responseCallBack.OnResponseFaile(201,"用户名不能为空");
            return;
        }
        if("".equals(password)){
            responseCallBack.OnResponseFaile(201,"用户名不能为空");
            return;
        }
        new AllModuel().Login(acount,password,responseCallBack);
    }
}