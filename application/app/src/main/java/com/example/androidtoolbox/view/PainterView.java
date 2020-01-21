package com.example.androidtoolbox.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.androidtoolbox.Application;
import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.DisplayUtils;
import com.example.androidtoolbox.utils.ImageUtils;

public class PainterView extends View {
    private Paint mPaint;
    public PainterView(Context context) {
        super(context);
        init();
    }

    public PainterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PainterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);

        canvas.drawColor(Color.YELLOW);
        canvas.drawCircle(200,200,100,mPaint);

        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.icon_edit),200,200,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRoundRect(new RectF(250,200,450,400),16,16,mPaint);

        canvas.drawText("hello狗子!",0,10,mPaint);

        canvas.save();
        canvas.translate(-200,-200);
        canvas.drawCircle(200,200,100,mPaint);
        canvas.restore();
    }

    private void init()
    {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(DisplayUtils.dp2px(Application.getContext(),28));
        mPaint.setStrokeWidth(4);

    }
}
