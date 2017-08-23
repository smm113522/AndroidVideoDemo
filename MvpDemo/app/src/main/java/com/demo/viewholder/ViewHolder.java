package com.demo.viewholder;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 16-7-18.
 */
public class ViewHolder extends RecyclerView.ViewHolder{

    private ViewDataBinding binding;

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }

    public ViewHolder(View itemView) {
        super(itemView);
    }
}