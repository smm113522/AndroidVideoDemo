package com.kesun.png;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kesun.png.adapter.DemoAdapter;
import com.kesun.png.utils.SpacesItemDecoration;

import java.io.File;
import java.util.ArrayList;

import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.service.impl.ImageMemoryCache;
import cn.trinea.android.common.service.impl.RemoveTypeLastUsedTimeFirst;

/**
 * Created by Administrator on 2018/2/22 0022.
 */

public class DemoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private DemoAdapter adapter;

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
        setContentView(R.layout.activity_demo_list);

        IMAGE_CACHE.initData(getApplicationContext(), TAG_CACHE);
        IMAGE_CACHE.setContext(getApplicationContext());
        IMAGE_CACHE.setCacheFolder(DEFAULT_CACHE_FOLDER);

        initView();
        initImageUrlList();
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.mrecyclerview);
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(adapter = new DemoAdapter(this,IMAGE_CACHE));
    }

    private ArrayList<String> imageUrlList;

    private void initImageUrlList() {
        imageUrlList = new ArrayList<String>();
        imageUrlList.add("http://farm8.staticflickr.com/7409/9148527822_36fa37d7ca_z.jpg");
        imageUrlList.add("http://farm4.staticflickr.com/3755/9148527824_6c156185ea.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7318/9148527808_e804baef0b.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7318/9146300275_5fe995d123.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7288/9146300469_bd3420c75b_z.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7351/9148527976_8a4e75ae87.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2888/9148527996_f05118d7de_o.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2863/9148527892_31f9377351_o.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7310/9148528008_8e8f51997a.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2849/9148528108_dfcda19507.jpg");
        imageUrlList.add("http://farm4.staticflickr.com/3739/9148528022_e9bf03058f.jpg");

        adapter.setList(imageUrlList);
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
                    imageParams.height = imageParams.width * loadedImage.getHeight() / loadedImage.getWidth();
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
