package com.smm.andfixdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

import static com.smm.andfixdemo.BuildConfig.*;

public class MainActivity extends AppCompatActivity {

    private TextView tvtishi;
    private Button btandfix;
    private Button bttomast;

    private static final String APATCH_PATH = "/fix.apatch"; // 补丁文件名
    public String downloadUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bttomast = (Button) findViewById(R.id.bt_tomast);
        this.btandfix = (Button) findViewById(R.id.bt_andfix);
        this.tvtishi = (TextView) findViewById(R.id.tv_tishi);

        btandfix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upApp();
//                downloadFile(downloadUrl,null);
            }
        });

        bttomast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast1();
            }
        });
    }

    private void showToast() {
        Toast.makeText(this, "打补丁之前", Toast.LENGTH_LONG).show();
    }

    private void showToast1() {
        Toast.makeText(this, "打补丁之后", Toast.LENGTH_LONG).show();
        tvtishi.setText("我们都是好孩子");
    }

    private void update() {
        String patchFileStr = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;

        File file = new File(patchFileStr);

        if (file.exists()) {
//            Toast.makeText(getApplicationContext(),"文件夹存在",Toast.LENGTH_SHORT).show();
        }

        if (file.isDirectory()) {//判断文件的存在性
            Toast.makeText(getApplicationContext(), "存在", Toast.LENGTH_SHORT).show();
        }

        try {
            Log.d("smm", patchFileStr);
            AndFixApplication.mPatchManager.addPatch(patchFileStr);
        } catch (IOException e) {
            Log.d("smm1", e.toString());
            e.printStackTrace();
        }
    }

    int lastProgress = 0;

    private String downloadAPKPath = FileHelper.getDownloadApkCachePath();

    DownloadSuccessListener successListener;

    public void downloadFile(String url, FileCallback callback) {
        if (callback == null) {
            lastProgress = 0;
            final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(getString(R.string.app_name));
            builder.setTicker("下载中...");
            OkGo.get(url).execute(new FileCallback(downloadAPKPath, getString(R.string.app_name) + ".apatch") {
                @Override
                public void onBefore(BaseRequest request) {
                    super.onBefore(request);
                    builder.setContentText(String.format(getString(R.string.versionchecklib_download_progress), 0));
                    Notification notification = builder.build();
                    notification.vibrate = new long[]{500, 500};
                    notification.defaults = Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;
                    manager.notify(0, notification);
                }

                @Override
                public void onSuccess(File file, Call call, Response response) {
                    if (successListener != null)
                        successListener.onDownloadSuccess(file);

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    Uri uri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        uri = VersionFileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".versionProvider", file);
                        Log.e("versionLib", getApplicationContext().getPackageName() + "");
                        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    } else {
                        uri = Uri.fromFile(file);
                    }
                    //设置intent的类型
                    i.setDataAndType(uri,
                            "application/vnd.android.package-archive");
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, 0);
                    builder.setContentIntent(pendingIntent);
                    builder.setContentText(getString(R.string.versionchecklib_download_finish));
                    builder.setProgress(100, 100, false);
                    manager.notify(0, builder.build());

                    finish();

                }

                @Override
                public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
                    Log.e("VersionDilaogActivity", progress + "");
                    int currentProgress = (int) (progress * 100);

                    if (currentProgress - lastProgress >= 5) {
                        lastProgress = currentProgress;
                        builder.setContentText(String.format(getString(R.string.versionchecklib_download_progress), lastProgress));
                        builder.setProgress(100, lastProgress, false);
                        manager.notify(0, builder.build());
                    }

                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    super.onError(call, response, e);
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                    builder.setContentIntent(pendingIntent);
                    builder.setContentText(getString(R.string.versionchecklib_download_fail));
                    builder.setProgress(100, 0, false);
                    manager.notify(0, builder.build());
                }
            });
        } else {
            OkGo.get(url).execute(callback);
        }

    }

    public interface DownloadSuccessListener {
        void onDownloadSuccess(File file);
    }

    public void setSuccessListener(DownloadSuccessListener successListener) {
        this.successListener = successListener;
    }

    String url = "http://shimengmeng.win/data/upload.json";

    public void upApp() {
        OkGo.post(url).execute(new AbsCallback<String>() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Version version = new Version();
                String plugInGackage = "";
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int versionCode = jsonObject.getInt("versionCode");
                    String downloadAddress = jsonObject.getString("downloadAddress");
                    plugInGackage = jsonObject.getString("plugInGackage");
                    String versionName = jsonObject.getString("versionName");
                    version.setDownloadAddress(downloadAddress);
                    version.setPlugInGackage(plugInGackage);
                    version.setVersionName(versionName);
                    version.setVersionCode(versionCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int loadVersion = VERSION_CODE;
                if (!plugInGackage.equals("")) {
                    downloadFile(version.getPlugInGackage(), null);
                } else {
                    if (loadVersion < version.getVersionCode()) {
                        downloadFile(version.getDownloadAddress(), null);
                    }
                }
            }

            @Override
            public String convertSuccess(Response response) throws Exception {
                return null;
            }
        });
    }

}
