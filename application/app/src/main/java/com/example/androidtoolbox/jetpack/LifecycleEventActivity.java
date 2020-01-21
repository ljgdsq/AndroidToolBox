package com.example.androidtoolbox.jetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidtoolbox.R;

public class LifecycleEventActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_event);
    }
}
