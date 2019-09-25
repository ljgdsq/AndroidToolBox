package com.example.androidtoolbox.events;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.view.MyButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {

    private static final String TAG = EventActivity.class.getSimpleName();
    @BindView(R.id.button)
    MyButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

//        button.setOnClickListener(v -> {
//            Log.d(TAG, "button click!: ");
//        });
//
        button.setOnTouchListener((v, event) -> {
            Log.d(TAG, "setOnTouchListener: ");
            return super.onTouchEvent(event);
        });

        Looper.prepare();
        Looper.loop();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, " onTouchEvent");
        return false;
    }


}
