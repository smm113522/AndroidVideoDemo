package com.kincai.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kincai.recyclerviewdemo.R;
import com.kincai.recyclerviewdemo.bean.DataInfo;

import java.util.IdentityHashMap;
import java.util.List;




public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {


    private Context context;
    private List<DataInfo> data;
    private boolean vertical;
    private boolean isHeader;
    View header;
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onClick(View itemView, int position);
        void onLongClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ListAdapter(Context context, List<DataInfo> data, boolean vertical) {
        header = View.inflate(context, R.layout.header_layout, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        header.setLayoutParams(layoutParams);
        this.context = context;
        this.data = data;
        this.vertical = vertical;
    }

    //当viewholder创建的时候回调
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if(vertical && viewType == 0) {
            return new ListViewHolder(header);
        }

        //垂直于水平使用不同的item布局
        if (vertical) {
            view = View.inflate(context, R.layout.item_list, null);
        } else {
            view = View.inflate(context, R.layout.item_grid, null);
        }


        return new ListViewHolder(view);
    }

    //当viewholder和数据绑定时回调
    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        DataInfo dataInfo = data.get(position);
        if (isHeader(position)) {
            return;
        }


            holder.descTv.setText(dataInfo.getText());
            holder.iv.setImageResource(dataInfo.getIcon());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClick(holder.itemView, position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (listener != null) {
                    listener.onLongClick(holder.itemView, position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView descTv;
        private ImageView iv;

        public ListViewHolder(View itemView) {
            super(itemView);

                descTv = (TextView) itemView.findViewById(R.id.item_list_tv_name);
                iv = (ImageView) itemView.findViewById(R.id.item_list_iv_icon);

        }
    }

    public boolean isHeader(int position) {
        return position == 0;
    }
    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }
}
