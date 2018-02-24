package com.kesun.png.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.kesun.png.ImageDetailsActivity;
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
    /**
     * column number
     **/
    public static final int COLUMNS = 2;
    private ArrayList<String> list = new ArrayList<>();
    private Context context;

    private ImageCache IMAGE_CACHE;

    public DemoAdapter(Context context, ImageCache imageCache) {

        this.context = context;
        this.IMAGE_CACHE = imageCache;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_list_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        int viewId = 0x7F24FFF0;
        int verticalSpacing, horizontalSpacing;
        verticalSpacing = horizontalSpacing = context.getResources().getDimensionPixelSize(R.dimen.dp_4);
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        int imageWidth = (display.getWidth() - (COLUMNS + 1) * horizontalSpacing) / COLUMNS;
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER);
        holder.imageView.setBackgroundResource(R.drawable.image_border);

        // set imageView layout params
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.imageView.getLayoutParams();
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
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDetailsActivity.StartActivity((Activity) context, list, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static AlphaAnimation getInAlphaAnimation(long durationMillis) {
        AlphaAnimation inAlphaAnimation = new AlphaAnimation(0, 1);
        inAlphaAnimation.setDuration(durationMillis);
        return inAlphaAnimation;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressbar;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_list_image);
            progressbar = (ProgressBar) itemView.findViewById(R.id.progressbar);
        }
    }

}
