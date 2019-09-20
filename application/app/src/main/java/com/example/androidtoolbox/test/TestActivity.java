package com.example.androidtoolbox.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidtoolbox.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button button=findViewById(R.id.btn_right);

        button.setOnClickListener(v->{
            Toast.makeText(TestActivity.this,"click",Toast.LENGTH_LONG).show();
        });
    }
}
