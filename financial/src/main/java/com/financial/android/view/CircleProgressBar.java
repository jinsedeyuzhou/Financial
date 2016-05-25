package com.financial.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.financial.android.R;


/**
 * Created by wyy on 2016/4/19.
 */
public class CircleProgressBar extends View {

    /**
     * 画笔对象的引用
     */
    private Paint outCirclePaint;
    /**
     * 圆环的颜色
     */
    private int roundColor;
    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;
    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;
    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;
    /**
     * 圆环的宽度
     */
    private float roundWidth;
    /**
     * 最大进度
     */
    private int max;
    /**
     * 当前进度
     */
    private int progress;
    /**
     * 当前进度
     */
    private int currentProgress=0;
    /**
     * 是否显示中间的进度
     */
    private boolean textIsDisplayable;
    /**
     * 进度的风格，实心或者空心
     */
    private int style;

    public static final int STROKE = 0;
    public static final int FILL = 1;
    private int center;
    private int radius;
    private RectF oval;
    private Paint percentPaint;
    private Paint innerCirclePaint;


    /**
     * 宽和高
     * @param context
     */
    private int mWidth;
    private int mHeight;

    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CirclePBar);
        roundColor = mTypedArray.getColor(R.styleable.CirclePBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.CirclePBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.CirclePBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.CirclePBar_textProgressSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.CirclePBar_roundWidth, 5);
        max = mTypedArray.getInteger(R.styleable.CirclePBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.CirclePBar_textIsDisplayable, false);
        style = mTypedArray.getInt(R.styleable.CirclePBar_style, 0);
        mTypedArray.recycle();


        /**
         * 最外层的大圆环
         */
        outCirclePaint = new Paint();
        outCirclePaint.setColor(roundColor);//设置圆环的颜色
        outCirclePaint.setStyle(Paint.Style.STROKE);//设置空心
        outCirclePaint.setStrokeWidth(roundWidth);//设置圆环的宽度
        outCirclePaint.setAntiAlias(true);   //消除锯齿

        /**
         * 画进度百分比
         */
        percentPaint = new Paint();
        percentPaint.setStrokeWidth(0);
        percentPaint.setAntiAlias(true);
        percentPaint.setColor(textColor);//设置字体颜色
        percentPaint.setTextSize(textSize);//设置字体大小
        percentPaint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体



        /**
         * 画圆弧，画圆环的进度
         */

        //设置进度是实心还是空心
        innerCirclePaint = new Paint();
        innerCirclePaint.setStrokeWidth(roundWidth);
        innerCirclePaint.setColor(roundProgressColor);//设置进度的颜色
        innerCirclePaint.setStyle(Paint.Style.STROKE);//设置空心
        innerCirclePaint.setAntiAlias(true);   //消除锯齿






    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        mWidth = getRealSize(widthMeasureSpec);
//        mHeight = getRealSize(heightMeasureSpec);
//        setMeasuredDimension(mWidth, mHeight);
//
//    }

    private int getRealSize(int measureSpec) {
            int result = 1;
            int mode = MeasureSpec.getMode(measureSpec);
            int size = MeasureSpec.getSize(measureSpec);

            if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
                //自己计算
//                result = (int) (radius * 2 + roundWidth);
                result=getWidth();
            } else {
                result = size;
            }
            return result;
    }


    private void initRect() {
        if (oval == null) {
            oval = new RectF();
            int viewSize = (int) (roundWidth);
            int left = (mWidth - viewSize) / 2;
            int top = (mHeight - viewSize) / 2;
            int right = left + viewSize;
            int bottom = top + viewSize;
            oval.set(left, top, right, bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        center = getWidth() / 2;
        radius = (int) (center - roundWidth / 2);
//        radius= (int) (mWidth/2-roundWidth/2);

        canvas.drawCircle(center,center, radius, outCirclePaint); //画出圆环
        int percent = (int) (((float) currentProgress / (float) max) * 100);
        float textWidth = percentPaint.measureText(percent + "%");//测试字体宽度，我们需要根据字体的宽度设置在圆环中间
        if (textIsDisplayable && currentProgress != 0 && style == STROKE) {
            canvas.drawText(currentProgress + "%", center - textWidth / 2, center + textSize / 2, percentPaint);//画出进度百分比
        }

        oval = new RectF(center - radius, center - radius, center + radius, center + radius);

        switch (style) {
            case STROKE: {
                innerCirclePaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, 0, 360 * currentProgress / max, false, innerCirclePaint);
                break;
            }
            case FILL: {
                innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0) {
                    canvas.drawArc(oval, 0, 360 * currentProgress / max, false, innerCirclePaint);//根据进度画圆弧
                }
                break;
            }
        }

//        if (currentProgress < progress) {
//            currentProgress += 1;
//            invalidate();
//        }

    }






    public synchronized int getMax()
    {
        return max;
    }
    /**
     * 设置进度的最大值
     */
    public synchronized void setMax(int max)
    {
        if (max<0)
        {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max=max;
    }

    /**
     * 获取进度，需要同步
     */
    public synchronized int getProgress()
    {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     * @param progress
     */
    public synchronized void setProgress(int progress)
    {
        if (progress<0)
        {
            throw  new IllegalArgumentException("progress not than 0");
        }
        if (progress>max)
        {
            progress=max;
        }
        if (progress<=max)
        {
//            this.progress=progress;
//            postInvalidate();

            setCurPercent(progress);
        }
    }





    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    private void setCurPercent(int percent) {

        progress = percent;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int sleepTime = 1;
                for (int i = 0; i <= progress; i++) {
                    if (i % 20 == 0) {
                        sleepTime += 2;
                    }
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentProgress = i;
                    CircleProgressBar.this.postInvalidate();
                }
            }

        }).start();
    }

}
