package com.kesun.png.adapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.kesun.png.ImageDetailsActivity;
import com.kesun.png.R;

import java.util.ArrayList;

import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.util.DownloadManagerPro;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by Administrator on 2018/2/23 0023.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> list;
    private ImageCache imageCache;
    private DownloadManager downloadManager ;
    public ViewPagerAdapter(Context applicationContext, ArrayList<String> images, ImageCache imageCache) {
        this.context = applicationContext;
        this.list = images;
        this.imageCache = imageCache;
        downloadManager = (DownloadManager)context.getSystemService(DOWNLOAD_SERVICE);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private static final int IMAGEVIEW_DEFAULT_HEIGHT = 400;
    /**
     * column number
     **/
    public static final int COLUMNS = 1;

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(context, R.layout.view_pager_item, null);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.photoview);
        TextView indicator = (TextView) view.findViewById(R.id.indicator);
        //用Glide加载图片
//            Glide.with(ImageDetailsActivity.this).load(images.get(position)).into(photoView);

       /* int viewId = 0x7F24FFF0;
        int verticalSpacing, horizontalSpacing;
        verticalSpacing = horizontalSpacing = context.getResources().getDimensionPixelSize(R.dimen.dp_4);
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        int imageWidth = (display.getWidth() - (COLUMNS + 1) * horizontalSpacing) / COLUMNS;
        photoView.setScaleType(ImageView.ScaleType.FIT_XY);
        photoView.setBackgroundResource(R.drawable.image_border);

        // set imageView layout params
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) photoView.getLayoutParams();
        layoutParams.width = imageWidth;
        layoutParams.topMargin = verticalSpacing;
        layoutParams.rightMargin = horizontalSpacing;
        int column = position % COLUMNS;
        int row = position / COLUMNS;
        if (row > 0) {
            layoutParams.addRule(RelativeLayout.BELOW, viewId - COLUMNS);
        }
        if (column > 0) {
            layoutParams.addRule(RelativeLayout.RIGHT_OF, viewId - 1);
        }
        layoutParams.height = IMAGEVIEW_DEFAULT_HEIGHT;*/
        photoView.setScaleType(ImageView.ScaleType.FIT_XY);
        // get image

        imageCache.get(list.get(position), photoView);
        CharSequence text = context.getString(R.string.viewpager_indicator, position + 1, list.size());
        //设置indicator
        indicator.setText(text);

        Button bt_down = (Button) view.findViewById(R.id.bt_down);
        bt_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        container.addView(view, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}