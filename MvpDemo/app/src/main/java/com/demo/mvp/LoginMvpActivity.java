package com.demo.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.mvp.presenter.LoginPresenter;
import com.demo.mvp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/10.
 */

public class LoginMvpActivity extends AppCompatActivity implements LoginView {


    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.rxjava1)
    Button rxjava1;
    @BindView(R.id.rxjava2)
    Button rxjava2;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void move2Index() {

    }

    @Override
    public void showToast(String txt) {

    }

    @Override
    public String getName() {
        return editText.getText().toString();
    }

    @Override
    public String getPassword() {
        return editText2.getText().toString();
    }

    @OnClick({R.id.button2, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                break;
            case R.id.button:
                loginPresenter.login();
                break;
        }
    }
}
