package com.kesun.tts;

import android.Manifest;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mogujie.tt.VoiceRecorderHelper;
import com.wulee.text2speachlib.Text2Speech;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Administrator on 2018/2/24 0024.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@Route(path = "/tts/mian")
public class TtsDemoActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private EditText mReadtxtEt;
    private Button mReadBt;
    private Button bt_tts_read;
    TextToSpeech tts;
    int result;
    private Button mReadLBt;
    private Button mReadBBt;
    private VoiceRecorderHelper voiceRecorderHelper;
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts_demo);

        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO
                },
                1
        );

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //初始化成功的话，设置语音，这里我将它设置为中文
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.CHINA);
                }
            }
        });

        voiceRecorderHelper =
                new VoiceRecorderHelper(
                        this,
                        new VoiceRecorderHelper.CallBack() {
                            @Override
                            public String setOutPutPath() {
                                /** 设置录音结果路径，你的格式也在这里设置 */
                                return getAudioSavePath("LinGuanHong");
                            }

                            @Override
                            public void onDown(View v) {
                                /** 纯粹的 down 事件回调 */
                            }

                            @Override
                            public void onMove_in_limit(View v) {
                                /** 手指移动的范围在限制内 */
                            }

                            @Override
                            public void onMove_out_limit(View v) {
                                /** 手指移动超过范围，内部做了显示取消的提示 */
                            }

                            @Override
                            public void onUp_start(View v) {
                                /** 纯粹的 Up 事件回调 */
                            }

                            @Override
                            public void onUp_cancel(View v) {
                                /** 这个时候已经因为手指移动超过范围取消了录音 */
                            }

                            @Override
                            public void onFinishRecord() {
                                /** 录音结束 */
                            }

                            @Override
                            public void onRecordSuccess(float len, String savePath) {
                                /** 录音解码并且保存成功 */
                                path = savePath;
                                try {
                                    File file = new File(path);
                                    mediaPlayer.setDataSource(file.getPath());//指定音频文件路径
                                    mediaPlayer.setLooping(true);//设置为循环播放
                                    mediaPlayer.prepare();//初始化播放器MediaPlayer

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(getApplicationContext(), "添加完成", Toast.LENGTH_SHORT).show();
                                Log.e("aaaaa", "录音的路径 " + savePath + " 长度 " + len);
                            }

                            @Override
                            public void onRecordVolumeChange(int voiceValue) {
                                /** 录音声音强度的变化，单位分贝 */
                            }
                        }
                );

        initView();
    }


    private void initView() {
        mReadtxtEt = (EditText) findViewById(R.id.et_readtxt);
        mReadBt = (Button) findViewById(R.id.bt_read);
        mReadBt.setOnClickListener(this);
        bt_tts_read = (Button) findViewById(R.id.bt_tts_read);
        bt_tts_read.setOnClickListener(this);
        mReadLBt = (Button) findViewById(R.id.bt_read_l);
//        mReadLBt.setOnClickListener(this);

        mReadLBt.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                voiceRecorderHelper.Action_Down(v, motionEvent);
                                return true;
                            case MotionEvent.ACTION_MOVE:
                                voiceRecorderHelper.Action_Move(v, motionEvent);
                                return true;
                            case MotionEvent.ACTION_UP:
                                voiceRecorderHelper.Action_Up(v, motionEvent);
                                return true;
                        }
                        return false;
                    }
                }
        );


        mReadBBt = (Button) findViewById(R.id.bt_read_b);
        mReadBBt.setOnClickListener(this);
    }

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void onClick(View v) {
        int i1 = v.getId();
        if (i1 == R.id.bt_read) {// TODO 18/03/07
            String txt = mReadtxtEt.getText().toString().trim();
            if (TextUtils.isEmpty(txt)) {
                if (Text2Speech.isSpeeching()) {
                    Text2Speech.pause(this);
                } else {
                    Text2Speech.speech(this, "请输入内容", true);
                }
                return;
            }
            Text2Speech.speech(this, txt, true);
            return;
        }
        if (i1 == R.id.bt_tts_read) {// TODO 18/02/24

            String txt = mReadtxtEt.getText().toString().trim();
            if (TextUtils.isEmpty(txt)) {
                Speak("请输入内容");
                return;
            }
            Speak(txt);

        }

        if (i1 == R.id.bt_read_b) {
            if (TextUtils.isEmpty(path)) {
                Speak("请先录音");
            } else {
                //获取mp3文件的路径

                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Speak(String txt) {
//        tts.speak(txt, TextToSpeech.QUEUE_ADD, null);
        tts.speak(txt, TextToSpeech.QUEUE_ADD, null, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            result = tts.setLanguage(Locale.CHINA);

            if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE && result != TextToSpeech.LANG_AVAILABLE) {
                Toast.makeText(TtsDemoActivity.this, "暂不支持该语言", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (tts != null) {
            tts.shutdown();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Text2Speech.isSpeeching())
            Text2Speech.pause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Text2Speech.isSpeeching())
            Text2Speech.shutUp(this);
    }

    private static String getAudioSavePath(String userId) {
        String path = getAudioPathWithoutFile(userId);
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        return path;
    }

    private static String getAudioPathWithoutFile(String userId) {
        return getSavePath() + userId
                + "_" + String.valueOf(System.currentTimeMillis())
                + ".mp3";
    }

    private static String getSavePath() {
        String path;
        String floder = "audio";
        if (checkSDCard()) {
            path = Environment.getExternalStorageDirectory().toString()
                    + File.separator + "MGJ-IM" + File.separator + floder
                    + File.separator;

        } else {
            path = Environment.getDataDirectory().toString() + File.separator
                    + "MGJ-IM" + File.separator + floder + File.separator;
        }
        return path;
    }

    private static boolean checkSDCard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }


}
