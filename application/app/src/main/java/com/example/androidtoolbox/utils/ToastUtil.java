package com.example.androidtoolbox.utils;

import android.widget.Toast;

import com.example.androidtoolbox.Application;

public class ToastUtil {
    private ToastUtil() {
    }

    public static void s(String msg) {
        Toast.makeText(Application.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void l(String msg){
        Toast.makeText(Application.getContext(), msg, Toast.LENGTH_LONG).show();

    }
}
