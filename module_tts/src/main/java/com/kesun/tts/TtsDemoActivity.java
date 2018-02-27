package com.kesun.tts;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.Locale;

/**
 * Created by Administrator on 2018/2/24 0024.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@Route(path = "/tts/mian")
public class TtsDemoActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private EditText mReadtxtEt;
    private Button mReadBt;
    TextToSpeech tts;
    int result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts_demo);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //初始化成功的话，设置语音，这里我将它设置为中文
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.CHINA);
                }
            }
        });

        initView();
    }

    private void initView() {
        mReadtxtEt = (EditText) findViewById(R.id.et_readtxt);
        mReadBt = (Button) findViewById(R.id.bt_read);
        mReadBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_read) {// TODO 18/02/24
            String txt = mReadtxtEt.getText().toString().trim();
            if (TextUtils.isEmpty(txt)) {
                Speak("请输入内容");
                return;
            }
            Speak(txt);

        } else {
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
            tts.setLanguage(Locale.CHINA);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (tts != null) {
            tts.shutdown();
        }
    }
}
