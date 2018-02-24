package com.kesun.png;

import android.graphics.Bitmap;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kesun.png.adapter.DemoAdapter;
import com.kesun.png.utils.GetImagePathUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.entity.HttpResponse;
import cn.trinea.android.common.service.HttpCache;
import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.service.impl.ImageMemoryCache;
import cn.trinea.android.common.service.impl.RemoveTypeLastUsedTimeFirst;
import cn.trinea.android.common.util.CacheManager;

/**
 * Created by Administrator on 2018/2/23 0023.
 */

public class AllDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mQueryEdit;
    private Button mJiexiBt;
    private RecyclerView mMrecyclerview;

    private DemoAdapter adapter;

    private HttpCache httpCache;
    public static final String TAG_CACHE = "image_cache";
    /**
     * cache folder path which be used when saving images
     **/
    public static final String DEFAULT_CACHE_FOLDER = new StringBuilder()
            .append(Environment.getExternalStorageDirectory()
                    .getAbsolutePath()).append(File.separator)
            .append("Trinea").append(File.separator)
            .append("AndroidDemo").append(File.separator)
            .append("ImageCache").toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        httpCache = CacheManager.getHttpCache(getApplicationContext());

        initView();

        IMAGE_CACHE.initData(getApplicationContext(), TAG_CACHE);
        IMAGE_CACHE.setContext(getApplicationContext());
        IMAGE_CACHE.setCacheFolder(DEFAULT_CACHE_FOLDER);

    }

    private void initView() {
        mQueryEdit = (EditText) findViewById(R.id.edit_query);
        mJiexiBt = (Button) findViewById(R.id.bt_jiexi);
        mJiexiBt.setOnClickListener(this);
        mMrecyclerview = (RecyclerView) findViewById(R.id.mrecyclerview);

        mMrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mMrecyclerview.setAdapter(adapter = new DemoAdapter(this, IMAGE_CACHE));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_jiexi) {// TODO 18/02/23
            setmGetHtml();
        }
    }

    String url;

    public void setmGetHtml() {
        String eturl = mQueryEdit.getText().toString().trim();
        if (TextUtils.isEmpty(eturl)) {
            url = "http://p.codekk.com/detail/Android/zeleven/mua";
        } else {
            url = eturl;
        }

        httpCache.httpGet(url, new HttpCache.HttpCacheListener() {

            protected void onPreGet() {

            }

            protected void onPostGet(HttpResponse httpResponse, boolean isInCache) {
                if (httpResponse != null) {
                    StringBuilder sb = new StringBuilder(256);
                    sb.append("is in cache: ").append(isInCache).append("\r\n");
                    if (isInCache) {
                        sb.append("expires: ").append(new Date(httpResponse.getExpiredTime()).toGMTString())
                                .append("\r\n");
                    }

                    Log.d("smm", sb.toString());
                    Log.d("smm", httpResponse.getResponseBody());
                    String content = httpResponse.getResponseBody();

                    ArrayList list = GetImagePathUtil.getImgList(content);
                    Log.d("smm", list.toString());
                    adapter.setList(list);
                } else {
                    Toast.makeText(getApplicationContext(), "没有获取", Toast.LENGTH_SHORT).show();
                    Log.d("smm", "null");
                }

            }
        });
    }


    public static final ImageCache IMAGE_CACHE = new ImageCache(128, 512);

    static {
        /** init icon cache **/
        ImageMemoryCache.OnImageCallbackListener imageCallBack = new ImageMemoryCache.OnImageCallbackListener() {

            /**
             * callback function after get image successfully, run on ui thread
             *
             * @param imageUrl    imageUrl
             * @param loadedImage bitmap
             * @param view        view need the image
             * @param isInCache   whether already in cache or got realtime
             */
            @Override
            public void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
                if (view != null && loadedImage != null) {
                    ImageView imageView = (ImageView) view;
                    imageView.setImageBitmap(loadedImage);
                    // first time show with animation
                    if (!isInCache) {
                        imageView.startAnimation(getInAlphaAnimation(2000));
                    }

                    // auto set height accroding to rate between height and weight
                    RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                    imageParams.height = loadedImage.getHeight();
//                    imageParams.height = imageParams.width * loadedImage.getHeight() / loadedImage.getWidth();
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }

            /**
             * callback function before get image, run on ui thread
             *
             * @param imageUrl imageUrl
             * @param view     view need the image
             */
            @Override
            public void onPreGet(String imageUrl, View view) {
                // Log.e(TAG_CACHE, "pre get image");
            }

            /**
             * callback function after get image failed, run on ui thread
             *
             * @param imageUrl     imageUrl
             * @param loadedImage  bitmap
             * @param view         view need the image
             * @param failedReason failed reason for get image
             */
            @Override
            public void onGetFailed(String imageUrl, Bitmap loadedImage, View view, FailedReason failedReason) {
//                Log.e(TAG_CACHE,
//                        new StringBuilder(128).append("get image ").append(imageUrl).append(" error, failed type is: ")
//                                .append(failedReason.getFailedType()).append(", failed reason is: ")
//                                .append(failedReason.getCause().getMessage()).toString());
            }

            @Override
            public void onGetNotInCache(String imageUrl, View view) {
                if (view != null && view instanceof ImageView) {
                    ((ImageView) view).setImageResource(R.drawable.trinea);
                }
            }
        };
        IMAGE_CACHE.setOnImageCallbackListener(imageCallBack);
        IMAGE_CACHE.setCacheFullRemoveType(new RemoveTypeLastUsedTimeFirst<Bitmap>());

        IMAGE_CACHE.setHttpReadTimeOut(10000);
        IMAGE_CACHE.setOpenWaitingQueue(true);
        IMAGE_CACHE.setValidTime(-1);
        /**
         * close connection, default is connect keep-alive to reuse connection. if image is from different server, you
         * can set this
         */
        // IMAGE_CACHE.setRequestProperty("Connection", "false");
    }

    public static AlphaAnimation getInAlphaAnimation(long durationMillis) {
        AlphaAnimation inAlphaAnimation = new AlphaAnimation(0, 1);
        inAlphaAnimation.setDuration(durationMillis);
        return inAlphaAnimation;
    }

    @Override
    protected void onDestroy() {
        IMAGE_CACHE.saveDataToDb(this, TAG_CACHE);
        super.onDestroy();
    }
}
