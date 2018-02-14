package com.kesun.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.jia.jsplayer.listener.OnVideoControlListener;
import com.jia.jsplayer.utils.DisplayUtils;
import com.jia.jsplayer.video.JsPlayer;
import com.kesun.main.bean.VideoInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/2/14 0014.
 */

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {

    private JsPlayer mPlayer;
    private Button mOpenBt;
    private Button mCloseBt;
    private String fileName = "video.mp4";
//    private String path = "file:///android_asset/" + fileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();

        mPlayer.setOnVideoControlListener(new OnVideoControlListener() {
            @Override
            public void onStartPlay() {
                mPlayer.startPlay();
            }

            @Override
            public void onBack() {

            }

            @Override
            public void onFullScreen() {
                DisplayUtils.toggleScreenOrientation(DemoActivity.this);
            }

            @Override
            public void onRetry(int errorStatus) {

            }
        });
        mPlayer.setPath(new VideoInfo("艺术人生", getAssetsFils()));

        mPlayer.startPlay();
    }

    private void initView() {
        mPlayer = (JsPlayer) findViewById(R.id.player);
        mOpenBt = (Button) findViewById(R.id.bt_open);
        mOpenBt.setOnClickListener(this);
        mCloseBt = (Button) findViewById(R.id.bt_close);
        mCloseBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_open) {// TODO 18/02/14

        } else if (i == R.id.bt_close) {// TODO 18/02/14

        }
    }

    public String getAssetsFils() {
        File file = getFileFromAssetsFile(fileName);
        return file.getAbsolutePath();
    }

    /**
     * 获取assets目录下的单个文件
     *
     * @param fileName
     * @return
     */
    public File getFileFromAssetsFile(String fileName) {//这种方式不能用，只能用于webview加载，直接取路径是不行的
        String path = "file:///android_asset/" + fileName;
        String filepath = Environment.getExternalStorageDirectory().getPath() + "/"
//                + getResources().getString(R.string.app_name) + "/"
                + fileName;

        File dir = new File(filepath);
        if (!dir.exists()) {
            copyFilesFassets(getApplicationContext(), fileName, filepath);
        }
        File file = new File(filepath);
        return file;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public void onBackPressed() {
        if (!DisplayUtils.isPortrait(this)) {
            if (!mPlayer.isLock()) {
                DisplayUtils.toggleScreenOrientation(this);
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPlayer.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.onDestroy();
    }

    /**
     * 从assets目录中复制整个文件夹内容
     *
     * @param context Context 使用CopyFiles类的Activity
     * @param oldPath String  原文件路径  如：/aa
     * @param newPath String  复制后路径  如：xx:/bb/cc
     */
    public void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {//如果是文件

                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
