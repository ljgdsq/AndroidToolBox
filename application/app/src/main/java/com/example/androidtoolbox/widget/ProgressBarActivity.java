package com.example.androidtoolbox.widget;

import android.graphics.drawable.AnimationDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.androidtoolbox.R;

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ImageView imageView=findViewById(R.id.imageView);
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }
}
