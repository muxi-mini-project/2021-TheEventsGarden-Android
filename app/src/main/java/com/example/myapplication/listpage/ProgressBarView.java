package com.example.myapplication.listpage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.myapplication.R;

/**
 * Created by Administrator on 2021/3/6.
 */

public class ProgressBarView extends View {

    private int sColor;
    private int eColor;
    private int mHeight;
    private Paint mPaint;
    private Paint coverPaint;
    //进度
    private float progress = 0;

    private int totalWidth;

    private ValueAnimator progressAnimator;


    private ProgressBarListener listener;

    public interface ProgressBarListener {
        void time(long currentPlayTime);

        void end(long currentPlayTime);
    }

    public void setListener(ProgressBarListener listener) {
        this.listener = listener;
    }

    public ProgressBarView(Context context) {
        this(context, null);
    }

    public ProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressBarView, 0, defStyleAttr);
        mHeight = typedArray.getDimensionPixelSize(R.styleable.ProgressBarView_height, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
        sColor = typedArray.getColor(R.styleable.ProgressBarView_sColor, 0xFF36EF89);
        eColor = typedArray.getColor(R.styleable.ProgressBarView_eColor, 0XFF5AC9BD);

        mPaint = new Paint();
        mPaint.setStrokeWidth(mHeight);
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        coverPaint = new Paint();
        coverPaint.setStrokeWidth(mHeight);
        coverPaint.setAntiAlias(true); // 消除锯齿
        coverPaint.setStrokeCap(Paint.Cap.ROUND);
        coverPaint.setShader(new LinearGradient(0, mHeight, getMeasuredWidth(), mHeight, new int[]{sColor, eColor}, null, Shader.TileMode.CLAMP));
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int width = getWidth();
//        int measuredWidth = getMeasuredWidth();
//        setMeasuredDimension(widthSize, mHeight);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        canvas.drawLine(mHeight + getPaddingLeft(), mHeight, getMeasuredWidth() - getPaddingRight() - mHeight, mHeight, mPaint);
        canvas.drawLine(mHeight + getPaddingLeft(), mHeight, progress, mHeight, coverPaint);
    }


    public void startAnimation(final int time) {
        post(new Runnable() {
            @Override
            public void run() {
                int measuredWidth = getMeasuredWidth();
                progressAnimator = ValueAnimator.ofFloat(0f, getMeasuredWidth() - getPaddingRight() - mHeight);
                progressAnimator.setDuration(time);
                progressAnimator.setTarget(progress);
                progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        progress = (float) animation.getAnimatedValue();
                        listener.time(animation.getCurrentPlayTime());
                        if (animation.getCurrentPlayTime() >= time){
                            listener.end(animation.getCurrentPlayTime());
                        }
                        invalidate();
                    }
                });
                progressAnimator.start();
            }
        });
    }

    public void stopValueAnimator(){
        if(progressAnimator != null){
            progressAnimator.cancel();
        }
    }

}
