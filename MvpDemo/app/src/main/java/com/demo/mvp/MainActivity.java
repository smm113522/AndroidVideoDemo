package com.demo.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.mvp.presenter.LoginPresenter;
import com.demo.mvp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText)
    EditText editText;
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
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

    @Override
    public void move2Index() {
        startActivity(new Intent(MainActivity.this,IndexActivity.class));
    }

    @Override
    public void showToast(String txt) {
        Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return editText.getText().toString();
    }

    @Override
    public String getPassword() {
        return editText2.getText().toString();
    }
}
