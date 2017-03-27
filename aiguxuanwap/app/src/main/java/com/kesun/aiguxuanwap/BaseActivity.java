package com.kesun.aiguxuanwap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.pgyersdk.update.PgyUpdateManager;

/**
 * Created by Administrator on 2017/3/27.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PgyCrashManager.register(this);
    }

    @Override
    protected void onPause() {
//        PgyFeedbackShakeManager.unregister();
        super.onPause();
    }

    @Override
    protected void onResume() {
//        PgyFeedbackShakeManager.register(this, false);
        PgyUpdateManager.register(BaseActivity.this,getString(R.string.file_provider));
        super.onResume();
    }

}
