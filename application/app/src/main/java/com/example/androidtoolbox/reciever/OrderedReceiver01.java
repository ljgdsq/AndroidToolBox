package com.example.androidtoolbox.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.androidtoolbox.utils.LogUtil;
import com.example.androidtoolbox.utils.ToastUtil;

public class OrderedReceiver01 extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtil.s("OrderedReceiver01");
        LogUtil.d("OrderedReceiver01");
    }
}
