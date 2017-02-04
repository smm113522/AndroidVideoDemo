package com.demo.mvp.model;

/**
 * Created by Administrator on 2017/2/4.
 */

public interface OnLoginListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess();

    public void onFailure();
    public void onFailure(String txt);
}
