package com.demo.mvp;

import android.view.View;
import android.widget.EditText;

import com.demo.base.BaseActivity;
import com.demo.mvp.main.LoginContract;
import com.demo.mvp.main.LoginModel;
import com.demo.mvp.main.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/10.
 */

public class LoginMvpActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mPresenter.login(editText1.getText().toString().trim(), editText2.getText().toString().trim());
                break;
        }
    }


    @Override
    public void Sucess(String msg) {
        msgToast(msg);
    }

    @Override
    public void Fail(String msg) {
        msgToast(msg);
    }
}
