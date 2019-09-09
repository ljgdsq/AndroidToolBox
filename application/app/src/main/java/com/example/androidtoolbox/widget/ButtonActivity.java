package com.example.androidtoolbox.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidtoolbox.R;

public class ButtonActivity extends AppCompatActivity {

    private Button buttonShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        buttonShow=findViewById(R.id.btn_show);
    }

    public void enableButton(View view) {
        buttonShow.setEnabled(true);
    }

    public void disableButton(View view) {
        buttonShow.setEnabled(false);
    }

    public void shapeButtonClick(View view) {
        Toast.makeText(this,"click!",Toast.LENGTH_SHORT).show();
    }
}
