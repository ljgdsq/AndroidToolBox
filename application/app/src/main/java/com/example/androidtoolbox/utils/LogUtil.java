package com.example.androidtoolbox.utils;

import android.util.Log;

public class LogUtil {
    private LogUtil(){}

    public static void v(String msg)
    {
        Log.v(Thread.currentThread().getStackTrace()[3].getClassName(), msg);
    }
    public static void i(String msg)
    {
        Log.i(Thread.currentThread().getStackTrace()[3].getClassName(), msg);
    }
    public static void d(String msg){
        Log.d(Thread.currentThread().getStackTrace()[3].getClassName(), msg);
    }
    public static void w(String msg)
    {
        Log.w(Thread.currentThread().getStackTrace()[3].getClassName(), msg);
    }
    public static void e(String msg)
    {
        Log.e(Thread.currentThread().getStackTrace()[3].getClassName(), msg);
    }

}
