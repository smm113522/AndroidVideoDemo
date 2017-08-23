package com.demo.mvp.main;


import com.demo.base.BaseModel;
import com.demo.base.BasePresenter;
import com.demo.base.BaseView;
import com.demo.model.ResponseCallBack;

/**
 * Created by z2wenfa on 2016/9/1.
 */
public interface LoginContract {

    interface View extends BaseView {
        void Sucess(String msg);
        void Fail(String msg);
    }

    interface Model extends BaseModel {
        void login(String acount,String password,ResponseCallBack responseCallBack);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {
        }
        public abstract void login(String acount,String password);
    }
}