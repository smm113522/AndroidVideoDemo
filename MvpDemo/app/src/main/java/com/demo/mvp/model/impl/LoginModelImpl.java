package com.demo.mvp.model.impl;

import com.demo.mvp.model.LoginModel;
import com.demo.mvp.model.OnLoginListener;

/**
 * Created by Administrator on 2017/2/4.
 */

public class LoginModelImpl implements LoginModel{

    @Override
    public void login(String name, String password, OnLoginListener onLoginListener) {

        if(name.equals("")){
            onLoginListener.onFailure("用户名不能为空");
        }
        if(password.equals("")){
            onLoginListener.onFailure("密码不能为空");
        }

        if("11".equals(name)){
            if("11".equals(password)){
                onLoginListener.onSuccess();
            }else {
                onLoginListener.onPasswordError();
            }
        }else {
            onLoginListener.onUsernameError();
        }
    }
}
