package com.example.androidtoolbox.sisterrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.androidtoolbox.R;

public class SisterView extends View {

    private Paint paint;

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    private float x;
    private float y;
    public Bitmap bitmap;
    public SisterView(Context context) {
        super(context);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s_jump);
        paint = new Paint();
        paint.setColor(Color.RED);
        x=bitmap.getWidth()/2;
        y=bitmap.getHeight()/2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,x-bitmap.getWidth()/2,y-bitmap.getHeight()/2, paint);
        canvas.drawCircle(x-bitmap.getWidth()/2,y-bitmap.getHeight()/2,8, paint);
        canvas.drawCircle(x,y,4, paint);

    }
}
