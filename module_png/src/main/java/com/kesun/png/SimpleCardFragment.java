package com.kesun.png;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.kesun.comon.GsonUtil;
import com.kesun.comon.MyLogger;
import com.kesun.comon.bean.Common;
import com.kesun.png.bean.Png;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;

import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.service.impl.ImageMemoryCache;
import cn.trinea.android.common.service.impl.RemoveTypeLastUsedTimeFirst;

/**
 * Created by Administrator on 2018/2/26 0026.
 */

public class SimpleCardFragment extends Fragment {

    private String mTitle;
    private RecyclerView list;

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

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLogger.d("dddd");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        View v = inflater.inflate(R.layout.fragment_card, null);
//        TextView card_title_tv = (TextView) v.findViewById(R.id.textView);
//        card_title_tv.setText(mTitle);

        IMAGE_CACHE.initData(getContext(), TAG_CACHE);
        IMAGE_CACHE.setContext(getContext());
        IMAGE_CACHE.setCacheFolder(DEFAULT_CACHE_FOLDER);
        View v = inflater.inflate(R.layout.activity_demo_list, null);
        list = (RecyclerView) v.findViewById(R.id.mrecyclerview);
        list.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        list.setAdapter(adapter = new DemoAdapter(getContext(), IMAGE_CACHE));

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setmGetHtml();
    }

    public void setmGetHtml() {

        String url = "http://gank.io/api/data/福利/10/1";

        OkGo.<String>get(url)//
                .tag(this)//

                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {
                        Common common = GsonUtil.fromCommJson(response.body().toString());
                        if(!common.isError()){
                            ArrayList<Png> list = GsonUtil.fromJson(common.getResults(),new TypeToken<ArrayList<Png>>(){});
                            adapter.setList(list);
                        }else {
                            Toast.makeText(getContext(), "没有获取", Toast.LENGTH_SHORT).show();
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

    }

    public static AlphaAnimation getInAlphaAnimation(long durationMillis) {
        AlphaAnimation inAlphaAnimation = new AlphaAnimation(0, 1);
        inAlphaAnimation.setDuration(durationMillis);
        return inAlphaAnimation;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

        private static final int IMAGEVIEW_DEFAULT_HEIGHT = 400;
        /**
         * column number
         **/
        public static final int COLUMNS = 2;
        private ArrayList<Png> list = new ArrayList<>();
        private Context context;

        private ImageCache IMAGE_CACHE;

        public DemoAdapter(Context context, ImageCache imageCache) {

            this.context = context;
            this.IMAGE_CACHE = imageCache;
        }

        public void setList(ArrayList<Png> list) {
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
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
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
            IMAGE_CACHE.get(list.get(position).getUrl(), holder.imageView);

           /* OkGo.<Bitmap>get(list.get(position).getUrl())//
                    .tag(this)//
                    .execute(new BitmapCallback() {
                        @Override
                        public void onSuccess(Response<Bitmap> response) {
                            holder.imageView.setImageBitmap(response.body());
                        }
                    });*/

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ImageDetailsActivity.StartActivity((Activity) context, list, position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
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


}
