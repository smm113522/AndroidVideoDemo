package com.demo.mvp.view;

/**
 * Created by Administrator on 2017/2/4.
 */

public interface LoginView {

    void move2Index();

    void showToast(String txt);

    //获取界面的姓名
    String getName();

    //获取界面的密码
    String getPassword();
}
