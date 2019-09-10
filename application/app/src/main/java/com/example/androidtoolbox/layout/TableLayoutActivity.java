package com.example.androidtoolbox.layout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidtoolbox.R;

public class TableLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        Toast.makeText(this,"this demo may not prefer correct effect!",Toast.LENGTH_LONG).show();
    }
}
