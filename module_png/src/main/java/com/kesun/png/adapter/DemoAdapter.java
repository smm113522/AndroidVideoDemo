package com.kesun.png.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kesun.png.R;

import java.io.File;
import java.util.ArrayList;

import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.service.impl.ImageMemoryCache;
import cn.trinea.android.common.service.impl.RemoveTypeLastUsedTimeFirst;

/**
 * Created by Administrator on 2018/2/22 0022.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    private static final int IMAGEVIEW_DEFAULT_HEIGHT = 400;
    /** column number **/
    public static final int    COLUMNS                  = 2;
    private ArrayList<String> list = new ArrayList<>();
    private Context context;

    public static final String TAG_CACHE                = "image_cache";
    /** cache folder path which be used when saving images **/
    public static final String DEFAULT_CACHE_FOLDER     = new StringBuilder()
            .append(Environment.getExternalStorageDirectory()
                    .getAbsolutePath()).append(File.separator)
            .append("Trinea").append(File.separator)
            .append("AndroidDemo").append(File.separator)
            .append("ImageCache").toString();

    public DemoAdapter(Context context) {

        this.context = context;
        IMAGE_CACHE.initData(context, TAG_CACHE);
        IMAGE_CACHE.setContext(context);
        IMAGE_CACHE.setCacheFolder(DEFAULT_CACHE_FOLDER);
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_list_item,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewId = 0x7F24FFF0;
        int verticalSpacing, horizontalSpacing;
        verticalSpacing = horizontalSpacing = context.getResources().getDimensionPixelSize(R.dimen.dp_4);
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        int imageWidth = (display.getWidth() - (COLUMNS + 1) * horizontalSpacing) / COLUMNS;
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER);
        holder.imageView.setBackgroundResource(R.drawable.image_border);

        // set imageView layout params
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)holder.imageView.getLayoutParams();
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
        layoutParams.height = IMAGEVIEW_DEFAULT_HEIGHT;
        // get image
        IMAGE_CACHE.get(list.get(position), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_list_image);
        }
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
}
