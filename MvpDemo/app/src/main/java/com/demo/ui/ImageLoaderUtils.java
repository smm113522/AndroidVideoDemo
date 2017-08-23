package com.demo.ui;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.MyApplication;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class ImageLoaderUtils {

    private volatile static ImageLoaderUtils instance;

    public static ImageLoaderUtils getInstance() {
        if (instance == null) {
            synchronized (ImageLoaderUtils.class) {
                if (instance == null) {
                    instance = new ImageLoaderUtils();
                }
            }
        }
        return instance;
    }

    protected ImageLoaderUtils() {
    }


    public void displayImage(String path, ImageView imageView) {
        Glide.with(MyApplication.getInstance()).load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(Integer.parseInt(path))
                .skipMemoryCache(true)
                .into(imageView);
    }
}
