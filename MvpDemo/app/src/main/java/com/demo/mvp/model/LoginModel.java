package com.demo.mvp.model;

/**
 * Created by Administrator on 2017/2/4.
 */

public interface LoginModel {
    void login(String name,String password,OnLoginListener onLoginListener);
}
