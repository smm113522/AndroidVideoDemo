package com.seedling.helloc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.view.View;
import android.widget.Toast;

import com.ftd.livepermissions.LivePermissions;
import com.ftd.livepermissions.PermissionResult;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.seedling.helloc.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("mp3lame");
    }

    private final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int REQUEST_CODE_PERMISSIONS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        mainBinding.tvText.setText(hello());

        PermissionUtils.checkMorePermissions(this, PERMISSIONS, new PermissionUtils.PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {

            }

            @Override
            public void onUserHasAlreadyTurnedDown(String... permission) {

            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                PermissionUtils.requestMorePermissions(MainActivity.this, PERMISSIONS, REQUEST_CODE_PERMISSIONS);
            }
        });
        // 源文件路径
        String sourceFilePath = "";
        // 目标文件路径
        String destinationFilePath = "";
        // 转换音频文件为mp3文件
//        raw2mp3(sourceFilePath,destinationFilePath);

        beginRun();
        String ALARMS_EXTERNAL_STORAGE_FOLDER = "Alarms";

        final File externalStorage = Environment.getExternalStorageDirectory();
        final File alarmsFolder = new File(externalStorage, ALARMS_EXTERNAL_STORAGE_FOLDER);
        mainBinding.btSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialFilePicker()
                        .withActivity(MainActivity.this)
                        .withCloseMenu(true)
                        .withPath(alarmsFolder.getAbsolutePath())
                        .withRootPath(externalStorage.getAbsolutePath())
                        .withHiddenFiles(true)
                        .withFilter(Pattern.compile(".*\\.(wav|mp3|m4a|amr|jpg|jpeg|pptx|ppt|docx|doc|txt|xlsx|xls)$"))
                        .withFilterDirectories(false)
                        .withTitle("Sample title")
                        .withRequestCode(FILE_PICKER_REQUEST_CODE)
                        .start();

            }
        });

    }
    int FILE_PICKER_REQUEST_CODE = 1;
    private int numChannels = 1;
    private int sampleRate = 16000;
    private int bitRate = 96;

    public boolean raw2mp3(String source, String destination) {
        initEncoder(this.numChannels, this.sampleRate, this.bitRate, 1, 2);
        int result = this.encodeFile(source, destination);
        System.out.println("ddddddddddddddddddddd" + result);
        this.destroyEncoder();
        return result == 0;
    }

//    public native String hello();

    private native void initEncoder(int numChannels, int sampleRate, int bitRate, int mode, int quality);

    private native void destroyEncoder();

    private native int encodeFile(String sourcePath, String targetPath);


    /**
     * 注意：为方便演示
     */
    private void beginRun() {
        System.out.println("dddddddddddddddddddddddddddddddddddddddddddddddddd");
        String sourceFilePath = "testcase.wav";
        String destinationFilePath = "testcase.mp3";
        String demo3apkPath = sourceFilePath;

        // 文件是否已经存在？直接删除重来
        String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator + sourceFilePath;
        File pluginFile = new File(pluginFilePath);
        if (!pluginFile.exists()){
            pluginFile.mkdirs();
            pluginFile.mkdir();
        }
        String file1 = pluginFile.getAbsolutePath();

        String pluginFilePath1 = getFilesDir().getAbsolutePath() + File.separator + destinationFilePath;
//        String pluginFilePath1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + destinationFilePath;
        File pluginFile1 = new File(pluginFilePath1);
        if (!pluginFile1.exists()){
            pluginFile1.mkdirs();
            pluginFile1.mkdir();
        }
        String file2 = pluginFile1.getAbsolutePath();
        // 开始复制
        copyAssetsFileToAppFiles(demo3apkPath, sourceFilePath);
        System.out.println("dddddddddddddddddddddddddddddddd" + file1);
        boolean isOk = raw2mp3(file1, file2);
        System.out.println("dddddddddddddddddddddddddddddddd" + file2);
        System.out.println("dddddddddddddddddddddddddddddddd========" + isOk);
    }

    /**
     * 从assets目录中复制某文件内容
     *
     * @param assetFileName assets目录下的Apk源文件路径
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = this.getAssets().open(assetFileName);
            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            String ALARMS_EXTERNAL_STORAGE_FOLDER = "Alarms/" + new File(filePath).getName() + ".mp3";

            final File externalStorage = Environment.getExternalStorageDirectory();
            final File alarmsFolder = new File(externalStorage, ALARMS_EXTERNAL_STORAGE_FOLDER);
            System.out.println("dddddddddddddddddddddddddddddddd11111111111111111111====" + filePath);
            boolean isOk = raw2mp3(filePath, alarmsFolder.getAbsolutePath());
            System.out.println("dddddddddddddddddddddddddddddddd111111111111111" + alarmsFolder.getAbsolutePath());
            System.out.println("dddddddddddddddddddddddddddddddd11111111111111111========" + isOk);
        }
    }


}
