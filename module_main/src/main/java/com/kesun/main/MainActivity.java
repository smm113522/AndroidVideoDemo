package com.kesun.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Administrator on 2018/2/13 0013.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mFfmpegBt;
    private Button mIjkBt;
    private Button mMediaBt;
    private Button mPngBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
        }
    }
}
