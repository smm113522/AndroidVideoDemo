package com.github.brioal.qihutablayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.demo.smm.bottomnavigationview.R;


/**
 * 作者：Alex
 * 时间：2016年08月20日    15:35
 * 博客：http://www.jianshu.com/users/c3c4ea133871/subscriptions
 */
public class QiHuTab extends View {
    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    /**
     * 按钮高度
     */
    private int width;
    /**
     * 按钮高度
     */
    private int height;
    /**
     * Icon高度
     */
    private int iconHeight;
    /**
     * 选中颜色
     */
    private int selectedTextColor;
    /**
     * 文字大小
     */
    private float normalTextSize;
    /**
     * 文字大小
     */
    private float selectedTextSize;
    /**
     * 文字选中颜色
     */
    private int normalTextColor;
    /**
     * 外圆半径
     */
    private int maxOutCircleRadius;
    /**
     * 外圆颜色
     */
    private int outCircleColor;
    /**
     * 内圆半径
     */
    private int maxInCircleRadius;
    /**
     * 内圆颜色
     */
    private int innerCircleColor;

    /**
     * 文字
     */
    private String normalText;
    /**
     * 文字
     */
    private String selectedText;
    private Paint paint;
    private Drawable normalDrawable;
    private Drawable selectedDrawable;
    private int normalDrawableResId;
    private int selectedDrawableResId;

    private int outRadius;
    private int innerRadius;
    private long duration;
    private int type;
    private String noticeCount;
    private int noticeColor;

    private boolean isSelected;
    private boolean isFirstOnDraw;

    public QiHuTab(Context context) {
        this(context, null);
    }

    public QiHuTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        isFirstOnDraw = true;
        outRadius = 0;
        innerRadius = 0;
        Context context = getContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QiHuTab);
        normalTextSize = typedArray.getDimension(R.styleable.QiHuTab_normalTextSize, sp2px(12));
        selectedTextSize = typedArray.getDimension(R.styleable.QiHuTab_selectedTextSize, sp2px(14));
        outCircleColor = typedArray.getColor(R.styleable.QiHuTab_outCircleColor, Color.parseColor("#B8ECE8"));
        innerCircleColor = typedArray.getColor(R.styleable.QiHuTab_innerCircleColor, Color.parseColor("#1EC2B6"));
        normalTextColor = typedArray.getColor(R.styleable.QiHuTab_normalTextColor, Color.parseColor("#666666"));
        selectedTextColor = typedArray.getColor(R.styleable.QiHuTab_selectedTextColor, Color.parseColor("#FFFFFF"));
        normalText = typedArray.getString(R.styleable.QiHuTab_normalText);
        selectedText = typedArray.getString(R.styleable.QiHuTab_selectedText);
        type = typedArray.getInteger(R.styleable.QiHuTab_type, 0);
        normalDrawableResId = typedArray.getResourceId(R.styleable.QiHuTab_normalDrawableResId, -1);
        selectedDrawableResId = typedArray.getResourceId(R.styleable.QiHuTab_selectedDrawableResId, -1);
        noticeCount = typedArray.getString(R.styleable.QiHuTab_noticeCount);
        noticeColor = typedArray.getColor(R.styleable.QiHuTab_noticeColor, Color.parseColor("#FF0000"));
        if (normalDrawableResId != -1) {
            normalDrawable = ContextCompat.getDrawable(context, normalDrawableResId);
        }
        if (selectedDrawableResId != -1) {
            selectedDrawable = ContextCompat.getDrawable(context, selectedDrawableResId);
        }
        duration = typedArray.getInteger(R.styleable.QiHuTab_animDuration, 120);
        isSelected = typedArray.getBoolean(R.styleable.QiHuTab_isSelected, false);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        if (isSelected && isFirstOnDraw) {
            isFirstOnDraw = false;
            outRadius = (int) (maxOutCircleRadius * 1.0F);
            innerRadius = (int) (maxInCircleRadius * 1.0F);
        }
        if (outRadius != 0 || innerRadius != 0) {
            canvas.save();
            canvas.translate(getWidth() / 2, getHeight() / 2);
            paint.setColor(outCircleColor);
            /*绘制外圆*/
            canvas.drawCircle(0, 0, outRadius, paint);
            paint.setColor(innerCircleColor);
            /*绘制内圆*/
            canvas.drawCircle(0, 0, innerRadius, paint);
            canvas.restore();
        }
        if (type != CENTER) {
            paint.setColor(innerCircleColor);
            if (type == LEFT) {
                canvas.drawRect(0, 0, outRadius, getHeight(), paint);
            } else {
                canvas.drawRect(outRadius, 0, outRadius * 2, getHeight(), paint);
            }
        }

        /*绘制Icon*/
        canvas.save();
        canvas.translate(getWidth() / 2, iconHeight / 2);
        int r = iconHeight / 2;
        if (isSelected) {
            selectedDrawable.setBounds(-r, -r, r, r);
            //normalDrawable.setColorFilter(normalTextColor, PorterDuff.Mode.SRC_IN);
            selectedDrawable.draw(canvas);
        } else {
            normalDrawable.setBounds(-r, -r, r, r);
            //normalDrawable.setColorFilter(normalTextColor, PorterDuff.Mode.SRC_IN);
            normalDrawable.draw(canvas);
        }
        canvas.restore();
        canvas.save();
        /*绘制未读消息*/
        if (!TextUtils.isEmpty(noticeCount)) {
            noticeCount = (noticeCount.length() > 3) ? noticeCount.substring(0, 3) : noticeCount;
            canvas.translate(getWidth() / 2 + r, iconHeight / 2 - 5);
            paint.setColor(noticeColor);
            float noticeRadius = r * 2 / 3;
            canvas.drawCircle(0, 0, noticeRadius, paint);

            int textLength = noticeCount.length();
            paint.setTextSize((textLength == 1) ? noticeRadius : noticeRadius / textLength * 2);
            Rect bound = new Rect();
            paint.getTextBounds(noticeCount, 0, noticeCount.length(), bound);
            paint.setColor(Color.WHITE);
            canvas.drawText(noticeCount, -(bound.right - bound.left) / 2, -(bound.bottom + bound.top) / 2, paint);
        }
        canvas.restore();

        /*绘制文字*/
        if (isSelected) {
            paint.setColor(selectedTextColor);
            paint.setTextSize(selectedTextSize);
        } else {
            paint.setColor(normalTextColor);
            paint.setTextSize(normalTextSize);
        }
        canvas.save();
        canvas.translate(getWidth() / 2, iconHeight + (getHeight() - iconHeight) / 2);
        Rect bound = new Rect();
        String text = "";
        if ((normalText != null) && (!isSelected)) {
            text = normalText;
        } else if ((selectedText != null) && (isSelected)) {
            text = selectedText;
        }
        paint.getTextBounds(text, 0, text.length(), bound);
        canvas.drawText(text, -(bound.right - bound.left) / 2, -(bound.bottom + bound.top) / 2, paint);
        canvas.restore();
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * 设置未选中颜色
     */
    public void setNormalTextColor(int normalTextColor) {
        this.normalTextColor = normalTextColor;
    }

    /**
     * 设置选中颜色
     */
    public void setSelectedTextColor(int setectedTextColor) {
        this.selectedTextColor = setectedTextColor;
    }

    /**
     * 设置外圆颜色
     */
    public void setExCircleColor(int exCircleColor) {
        outCircleColor = exCircleColor;
    }

    /**
     * 设置内圆颜色
     */
    public void setInnerCircleColor(int innerCircleColor) {
        this.innerCircleColor = innerCircleColor;
    }

    /**
     * 设置文字大小
     */
    public void setNormalTextSize(int normalTextSize) {
        this.normalTextSize = dp2Px(normalTextSize);
    }

    /**
     * 设置未读消息
     */
    public void setNoticeCount(String count) {
        this.noticeCount = count;
        invalidate();
    }

    /**
     * 设置动画时长
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * 设置Icon
     */
    public void setNormalDrawable(Drawable drawable) {
        this.normalDrawable = drawable;
    }

    /**
     * 设置文字
     */
    public void setNormalText(String normalText) {
        this.normalText = normalText;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = measureWidth(widthMeasureSpec);
        height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            /*精确值模式，指定具体数值*/
            result = specSize;
        } else {
            /*先设置一个默认大小*/
            result = (int) dp2Px(48);
            /*
            *最大值模式，
            * layout_width 或 layout_height 为 wrap_content 时，
            * 控件大小随控件的内容变化而变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。
            * */
            if (specMode == MeasureSpec.AT_MOST) {
                /*取出我们指定的大小和 specSize 中最小的一个来作为最后的测量值*/
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            /*精确值模式，指定具体数值*/
            result = specSize;
        } else {
            /*先设置一个默认大小*/
            result = (int) dp2Px(48);
            /*
            *最大值模式，
            * layout_width 或 layout_height 为 wrap_content 时，
            * 控件大小随控件的内容变化而变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。
            * */
            if (specMode == MeasureSpec.AT_MOST) {
                /*取出我们指定的大小和 specSize 中最小的一个来作为最后的测量值*/
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!isSelected) {
                    startAnimation();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    //开始动画
    public void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                outRadius = (int) (maxOutCircleRadius * progress);
                innerRadius = (int) (maxInCircleRadius * progress);
                invalidate();
            }
        });
        animator.start();
        isSelected = true;
    }

    //恢复
    public void reset() {
        outRadius = 0;
        innerRadius = 0;
        invalidate();
        isSelected = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        iconHeight = height * 3 / 5;
        maxOutCircleRadius = w / 2;
        maxInCircleRadius = (int) (maxOutCircleRadius - dp2Px(10));
    }


    /**
     * 数据转换: dp---->px
     */
    private float dp2Px(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }

    /**
     * sp转px
     */
    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getContext().getResources().getDisplayMetrics());
    }
}
