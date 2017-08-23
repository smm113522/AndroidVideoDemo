package com.demo.mvp.main;


import com.demo.model.AllModuel;
import com.demo.model.ResponseCallBack;

/**
 * Created by TMVPHelper on 2016/10/20
 */
public class LoginPresenter extends LoginContract.Presenter implements ResponseCallBack{


    @Override
    public void login(String acount, String password) {
        mModel.login(acount,password,this);
    }

    @Override
    public void OnResponseSucess(int states, String common) {
        mView.Sucess(common);
    }

    @Override
    public void OnResponseFaile(int states, String common) {
        mView.Fail(common);
    }
}