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
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.tokenizer.BasicTokenizer;

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
        int i1 = v.getId();
        if (i1 == R.id.bt_read) {// TODO 18/02/24

            System.out.println("首次编译运行时，HanLP会自动构建词典缓存，请稍候……");
//            HanLP.Config.enableDebug();         // 为了避免你等得无聊，开启调试模式说点什么:-)
//            System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！接下来请从其他Demo中体验HanLP丰富的功能~"));
            HanLP.segment("你好，欢迎使用HanLP汉语处理包！接下来请从其他Demo中体验HanLP丰富的功能~");
            String txt = mReadtxtEt.getText().toString().trim();
            if (TextUtils.isEmpty(txt)) {
                Speak("请输入内容");
                return;
            }
            Speak(txt);

            String text = "举办纪念活动铭记二战历史，不忘战争带给人类的深重灾难，是为了防止悲剧重演，确保和平永驻；" +
                    "铭记二战历史，更是为了提醒国际社会，需要共同捍卫二战胜利成果和国际公平正义，" +
                    "必须警惕和抵制在历史认知和维护战后国际秩序问题上的倒行逆施。";
            System.out.println(BasicTokenizer.segment(text));
            // 测试分词速度，让大家对HanLP的性能有一个直观的认识
            long start = System.currentTimeMillis();
            int pressure = 100000;
            for (int i = 0; i < pressure; ++i)
            {
                BasicTokenizer.segment(text);
            }
            double costTime = (System.currentTimeMillis() - start) / (double) 1000;
            System.out.printf("BasicTokenizer分词速度：%.2f字每秒\n", text.length() * pressure / costTime);

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
