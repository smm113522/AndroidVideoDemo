package com.demo.ui;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class Utils {

    @BindingConversion
    public static String convertDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
//    @BindingAdapter({"bind:image"})
//    public static void imageLoader(ImageView imageView, String url) {
//        ImageLoaderUtils.getInstance().displayImage(url, imageView);
//    }
    @BindingAdapter({"image"})
    public static void imageLoader(ImageView imageView, String url) {
        ImageLoaderUtils.getInstance().displayImage(url, imageView);
    }

//    @BindingAdapter("bind:studentAvatar")
//    public static void showImageByUrl(final ImageView imageView,int resId){
//        imageView.setImageResource(resId);
//    }

    @BindingAdapter("studentAvatar")
    public static void showImageByUrl(ImageView imageView,int resId){
        imageView.setImageResource(resId);
    }

}
