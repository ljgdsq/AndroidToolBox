package com.example.androidtoolbox.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;


public class MyImageButton extends androidx.appcompat.widget.AppCompatImageButton {

    private static final int FLUSH_TIME=15;
    private Paint bottomPaint;
    private Paint colorPaint;
    private long downTime = 0;
    private int viewWidth, viewHeight;
    private int maxRadio;
    private int shaderRadio;
    private int pointX, pointY;
    private int eventX, eventY;
    private boolean isPushButton;
    private static int tapTime;
    private static int DIFFUSE_GAP = 10;
    public MyImageButton(Context context) {
        super(context);
        initPaint();
        tapTime= ViewConfiguration.getLongPressTimeout();
    }


    public MyImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        tapTime= ViewConfiguration.getLongPressTimeout();
    }

    private void initPaint() {
        colorPaint = new Paint();
        bottomPaint = new Paint();

        colorPaint.setColor(Color.GREEN);
        bottomPaint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (downTime == 0)
                    downTime = SystemClock.elapsedRealtime();
                eventX = (int) event.getX();
                eventY = (int) event.getY();
                countMaxRadio();
                isPushButton=true;
                postInvalidateDelayed(FLUSH_TIME);
                break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (SystemClock.elapsedRealtime()-downTime<tapTime){
                        DIFFUSE_GAP=30;
                        postInvalidate();
                    }else {
                        clearData();
                    }

                    break;

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(!isPushButton) return; //如果按钮没有被按下则返回
        //绘制按下后的整个背景
        canvas.drawRect(pointX, pointY, pointX + viewWidth, pointY + viewHeight, bottomPaint);
        canvas.save();
        //绘制扩散圆形背景
        canvas.clipRect(pointX, pointY, pointX + viewWidth, pointY + viewHeight);
        canvas.drawCircle(eventX, eventY, shaderRadio, colorPaint);
        canvas.restore();
        //直到半径等于最大半径
        if(shaderRadio < maxRadio){
            postInvalidateDelayed(FLUSH_TIME,
                    pointX, pointY, pointX + viewWidth, pointY + viewHeight);
            shaderRadio += DIFFUSE_GAP;
        }else{
            clearData();
        }
    }


    private void countMaxRadio() {
        if (viewWidth > viewHeight) {
            if (eventX<viewWidth/2){
                maxRadio=viewWidth-eventX;
            }else {
                maxRadio=eventX;
            }
        } else {
            if (eventY<viewHeight/2){
                maxRadio=viewHeight-eventY;
            }else {
                maxRadio=eventY;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void clearData()
    {
        downTime = 0;
        DIFFUSE_GAP = 10;
        isPushButton = false;
        shaderRadio = 0;
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.viewWidth = w;
        this.viewHeight = h;
    }
}
