package com.kesun.aiguxuanwap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

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
        upApp();
        super.onResume();
    }

    private void upApp() {
        PgyUpdateManager.register(this,"aiguxuan",
                new UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {

                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
//                        Logger.d(appBean.getDownloadURL() + appBean.getVersionName());
                        new AlertDialog.Builder(BaseActivity.this)
                                .setTitle("更新")
                                .setMessage("版本：" + appBean.getVersionName() + "\n " + "更新内容：" + appBean.getReleaseNote())
                                .setNegativeButton(
                                        "确定",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                startDownloadTask(
                                                        BaseActivity.this,
                                                        appBean.getDownloadURL());
                                            }
                                        }).show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });
    }

}
