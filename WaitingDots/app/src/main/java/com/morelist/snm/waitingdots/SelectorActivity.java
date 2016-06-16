package com.morelist.snm.waitingdots;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * 选择图片
 */
public class SelectorActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE = 2;

    private TextView mResultText;
    private RadioGroup mChoiceMode, mShowCamera;
    private EditText mRequestNum;

    private ArrayList<String> mSelectPath;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        mResultText = (TextView) findViewById(R.id.result);
        mChoiceMode = (RadioGroup) findViewById(R.id.choice_mode);
        mShowCamera = (RadioGroup) findViewById(R.id.show_camera);
        mRequestNum = (EditText) findViewById(R.id.request_num);

        mChoiceMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.multi) {
                    mRequestNum.setEnabled(true);
                } else {
                    mRequestNum.setEnabled(false);
                    mRequestNum.setText("");
                }
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

                if (mChoiceMode.getCheckedRadioButtonId() == R.id.single) {
                    selectedMode = MultiImageSelectorActivity.MODE_SINGLE;
                } else {
                    selectedMode = MultiImageSelectorActivity.MODE_MULTI;
                }

                boolean showCamera = mShowCamera.getCheckedRadioButtonId() == R.id.show;

                int maxNum = 9;
                if (!TextUtils.isEmpty(mRequestNum.getText())) {
                    maxNum = Integer.valueOf(mRequestNum.getText().toString());
                }

                Intent intent = new Intent(SelectorActivity.this, MultiImageSelectorActivity.class);
                // 是否显示拍摄图片
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);
                // 最大可选择图片数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
                // 选择模式
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);
                // 默认选择
                if (mSelectPath != null && mSelectPath.size() > 0) {
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);
                }
                startActivityForResult(intent, REQUEST_IMAGE);

            }
        });

/*        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GestureImageActivity.class);
                startActivity(intent);
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for (String p : mSelectPath) {
                    sb.append(p);
                    sb.append("\n");
                }
                mResultText.setText(sb.toString());
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.morelist.snm.waitingdots/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.morelist.snm.waitingdots/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
