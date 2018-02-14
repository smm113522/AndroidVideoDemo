package com.kesun.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/2/13 0013.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mFfmpegBt;
    private Button mIjkBt;
    private Button mMediaBt;
    private Button mPngBt;
    private Button mOtherBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        requestPermissions();
    }

    private void initView() {
        mFfmpegBt = (Button) findViewById(R.id.bt_ffmpeg);
        mFfmpegBt.setOnClickListener(this);
        mIjkBt = (Button) findViewById(R.id.bt_ijk);
        mIjkBt.setOnClickListener(this);
        mMediaBt = (Button) findViewById(R.id.bt_media);
        mMediaBt.setOnClickListener(this);
        mPngBt = (Button) findViewById(R.id.bt_png);
        mPngBt.setOnClickListener(this);
        mOtherBt = (Button) findViewById(R.id.bt_other);
        mOtherBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_ffmpeg) {// TODO 18/02/13
            ARouter.getInstance().build("/ffmpeg/main").navigation();
        } else if (i == R.id.bt_ijk) {// TODO 18/02/13
            ARouter.getInstance().build("/ijkplayer/main").navigation();
        } else if (i == R.id.bt_media) {// TODO 18/02/13
            ARouter.getInstance().build("/media/mian").navigation();
        } else if (i == R.id.bt_png) {// TODO 18/02/13
            ARouter.getInstance().build("/png/mian").navigation();
        }else if (i == R.id.bt_other) {// TODO 18/02/13
            startActivity(new Intent(MainActivity.this,DemoActivity.class));
        }
    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        //noinspection Since15
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d("1", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d("2", permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d("1", permission.name + " is denied.");
                        }
                    }
                });


    }
}
