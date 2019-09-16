package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtoolbox.R;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);



    }

    private void defaultToast(){
        Toast.makeText(this,"Toast",Toast.LENGTH_LONG).show();
    }

    public void showDefaultToast(View view) {
        defaultToast();
    }


    private void toast01()
    {
        Toast toast=Toast.makeText(this,"Toast",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.START,100,100);
        TextView textView=toast.getView().findViewById(android.R.id.message);
        textView.setTextColor(Color.RED);
        toast.show();
    }


    public void showToast01(View view) {
        toast01();
    }
}
