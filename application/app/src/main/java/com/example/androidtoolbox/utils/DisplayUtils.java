package com.example.androidtoolbox.utils;

import android.content.Context;

public class DisplayUtils {
    public static int dp2px(Context context, int dp){
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,context.getResources().getDisplayMetrics());
        return (int) (context.getResources().getDisplayMetrics().density*dp+0.5f);
    }

    public static int px2dp(Context context,int px)
    {
        return (int)(px/context.getResources().getDisplayMetrics().density+0.5f);
    }
}
