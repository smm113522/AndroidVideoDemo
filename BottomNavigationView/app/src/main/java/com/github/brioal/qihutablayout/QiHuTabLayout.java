package com.github.brioal.qihutablayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.brioal.qihutablayout.interfaces.OnTabSelectedListener;

/**
 * 作者：Alex
 * 时间：2016年08月20日    15:35
 * 博客：http://www.jianshu.com/users/c3c4ea133871/subscriptions
 */
public class QiHuTabLayout extends LinearLayout implements View.OnClickListener {
    private int currentIndex = 0;

    private OnTabSelectedListener onTabSelectedListener;

    public QiHuTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setQiHuTab();
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        ((QiHuTab) getChildAt(this.currentIndex)).startAnimation();
    }

    /**
     * 设置消息数量
     */
    public void setNoticeCount(String noticeCount, int index) {
        int count = getChildCount();
        if ((count <= 0) && (index >= count)) {
            return;
        }
        View childAt = getChildAt(index);
        if (childAt == null) {
            return;
        }
        ((QiHuTab) childAt).setNoticeCount(noticeCount);
    }

    /**
     * 清除制定位置消息
     */
    public void clearNotice(int index) {
        ((QiHuTab) getChildAt(index)).setNoticeCount("");
    }

    /**
     * 设置事件监听器
     */
    public void setSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

    private void setQiHuTab() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof QiHuTab)) {
                return;
            }
            QiHuTab qiHuTab = (QiHuTab) childAt;
            qiHuTab.setId(i);
            qiHuTab.setOnClickListener(this);
            qiHuTab.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (onTabSelectedListener != null && currentIndex != id) {
            onTabSelectedListener.onSelected(QiHuTabLayout.this, id);
            currentIndex = id;
        }
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            if (getChildAt(i).getId() != id) {
                ((QiHuTab) getChildAt(i)).reset();
            }
        }
    }

}
