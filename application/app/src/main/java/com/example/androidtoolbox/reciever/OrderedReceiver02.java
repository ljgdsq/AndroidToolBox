package com.example.androidtoolbox.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.androidtoolbox.utils.ToastUtil;

public class OrderedReceiver02 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtil.l("OrderedReceiver02");
    }
}
