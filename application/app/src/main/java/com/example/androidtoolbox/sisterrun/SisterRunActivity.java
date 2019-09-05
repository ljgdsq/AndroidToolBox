package com.example.androidtoolbox.sisterrun;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.androidtoolbox.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;


public class SisterRunActivity extends AppCompatActivity {
    private Bitmap[] sisterImgs = new Bitmap[8];
    private int count = 0;
    private SisterView sisterView;
    private Handler handler = new MyHandler(this);
    private int[] bitMapIds = new int[]
            {
                    R.drawable.s_1,
                    R.drawable.s_2,
                    R.drawable.s_3,
                    R.drawable.s_4,
                    R.drawable.s_5,
                    R.drawable.s_6,
                    R.drawable.s_7,
                    R.drawable.s_8
            };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sister_run);

        FrameLayout frameLayout = findViewById(R.id.fl_content);

        sisterView = new SisterView(this);
        frameLayout.addView(sisterView);

        for (int i = 0; i < 8; i++) {
            sisterImgs[i] = BitmapFactory.decodeResource(getResources(), bitMapIds[i]);
        }

        sisterView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sisterView.setX(event.getX());
                sisterView.setY(event.getY());
                sisterView.invalidate();
                return true;
            }
        });


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 0, 170);
    }

    public void Play() {
        sisterView.bitmap = sisterImgs[count++ % 8];
//        sisterView.invalidate();
    }

    private static class MyHandler extends Handler {

        private WeakReference<SisterRunActivity> activityWeakReference;

        public MyHandler(SisterRunActivity activity) {

            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                SisterRunActivity activity = activityWeakReference.get();
                if (activity != null) {
                    activity.Play();
                }
            }

        }
    }
}
