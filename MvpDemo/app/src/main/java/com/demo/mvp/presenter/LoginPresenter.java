package com.demo.mvp.presenter;

import com.demo.mvp.model.LoginModel;
import com.demo.mvp.model.OnLoginListener;
import com.demo.mvp.model.impl.LoginModelImpl;
import com.demo.mvp.view.LoginView;

/**
 * Created by Administrator on 2017/2/4.
 */

public class LoginPresenter implements OnLoginListener {

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    public void login() {
        String name = loginView.getName();
        String password = loginView.getPassword();
        loginModel.login(name, password, this);
    }

    @Override
    public void onUsernameError() {
        loginView.showToast("名字");
    }

    @Override
    public void onPasswordError() {
        loginView.showToast("密码");
    }

    @Override
    public void onSuccess() {
        loginView.move2Index();
    }

    @Override
    public void onFailure() {
        loginView.showToast("失败");
    }

    @Override
    public void onFailure(String txt) {
        loginView.showToast(txt);
    }
}
