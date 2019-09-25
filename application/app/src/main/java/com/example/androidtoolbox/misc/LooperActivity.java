package com.example.androidtoolbox.misc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.androidtoolbox.R;

public class LooperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);


        new Thread(){
            private Handler handler;
            @Override
            public void run() {
                handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what==0)
                        {
                            Log.d("SubThread", "handleMessage: ");
                        }
                    }
                };
                handler.sendEmptyMessageDelayed(0,1000);
            }

        }.start();
    }




}
