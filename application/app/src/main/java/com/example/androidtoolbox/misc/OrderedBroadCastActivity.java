package com.example.androidtoolbox.misc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.impl.utils.ForceStopRunnable;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.reciever.OrderedReceiver01;
import com.example.androidtoolbox.reciever.OrderedReceiver02;

public class OrderedBroadCastActivity extends AppCompatActivity {

    private OrderedReceiver01 receiver01;
    private OrderedReceiver02 receiver02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_broad_cast);


        receiver01=new OrderedReceiver01();
        receiver02=new OrderedReceiver02();
        IntentFilter filter01=new IntentFilter("com.tets.order");
        filter01.setPriority(100);

        IntentFilter filter02=new IntentFilter("com.tets.order");
        filter02.setPriority(200);

        registerReceiver(receiver01,filter01);
        registerReceiver(receiver02,filter02);

        sendOrderedBroadcast(new Intent("com.tets.order"),null);
    }


}
