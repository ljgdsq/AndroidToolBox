package com.example.androidtoolbox.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


public class ImageUtils {
    public static Drawable bitmap2Drawable(Resources resources, Bitmap bitmap){
        return new BitmapDrawable(resources,bitmap);
    }

    public static Bitmap getScaledBitmap(Resources resources,int id,int width,int height){
        Bitmap bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,id),width,height,true);
        return bitmap;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable)
    {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
