package com.demo.smm.bottomnavigationview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import carbon.widget.Button;
import carbon.widget.ImageView;
import carbon.widget.TextView;
import carbon.widget.Toolbar;

/**
 * Created by Administrator on 2017/2/3.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayoutId();

    public abstract void init();

    ImageView rightIcon;
    Button rightTxt;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);

        init();
    }

    public void setHeadText(boolean back, String title) {
        setHeadText(back, title, 0, null);
    }

    public void setHeadText(boolean back, String title, int rightIcons, String rightText) {
        if (toolbar == null) {
            rightIcon = (ImageView) findViewById(R.id.right_icon);
            rightTxt = (Button) findViewById(R.id.right_txt);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
        }
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setIconVisible(back);

        if (rightIcons != 0) {
            rightIcon.setVisibility(View.VISIBLE);
            rightIcon.setImageResource(rightIcons);
        } else {
            rightIcon.setVisibility(View.GONE);
        }
        if (rightText != null) {
            rightTxt.setVisibility(View.VISIBLE);
            rightTxt.setText(rightText);
        } else {
            rightTxt.setVisibility(View.GONE);
        }
    }

    public Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
