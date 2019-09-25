package com.example.androidtoolbox.misc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LooperActivity extends AppCompatActivity {

    @BindView(R.id.button6)
    Button button;
    private CalThread calThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);
        ButterKnife.bind(this);


//        new Thread(){
//            private Handler handler=new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//
//                    Log.d("SubThread", "handleMessage: ");
//                }
//            };
//            @Override
//            public void run() {
//                Log.d("SubThread", "run: ");
//
//                handler.sendEmptyMessageDelayed(0,10);
//            }
//
//        }.start();

        calThread = new CalThread();
        calThread.start();


    }

    public void sendMsg(View view) {
        calThread.mHandler.sendEmptyMessage(0x123);

    }


    class CalThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();


            mHandler = new Handler() {
                // 定义处理消息的方法
                @Override
                public void handleMessage(Message msg) {

                    Log.d("SubThread", "handleMessage: ");
                    // 使用Toast显示统计出来的所有质数
                    Toast.makeText(LooperActivity.this, "123"
                            , Toast.LENGTH_LONG).show();
                    button.setText("SubThread");

                }
            };

//            Looper looper = mHandler.getLooper();

            mHandler.sendEmptyMessage(0x123);
            Looper.loop();
        }
    }

}
