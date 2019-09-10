package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DrawableUtils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.example.androidtoolbox.R;

import java.util.Timer;
import java.util.TimerTask;

public class SeekBarActivity extends AppCompatActivity {

    private ImageView imageView;
    private int level=0;
    @SuppressLint("HandlerLeak")
    private  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (level<10000)
            {
                level+=1000/60;
                imageView.getDrawable().setLevel(level);

                handler.sendEmptyMessageDelayed(0,1000/60);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        Drawable drawable = getDrawable(R.drawable.icon_edit);

        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_edit), 128, 128, true);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setThumb(new BitmapDrawable(getResources(), bitmap));


        imageView = findViewById(R.id.progressBar);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 1000);
    }
}
