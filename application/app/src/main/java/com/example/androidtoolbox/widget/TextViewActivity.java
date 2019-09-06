package com.example.androidtoolbox.widget;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androidtoolbox.R;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);


        TextView textView=findViewById(R.id.tv_drawable);

        Drawable[] compoundDrawables = textView.getCompoundDrawables();

        if (compoundDrawables[0]!=null){
            compoundDrawables[0].setBounds(0,0,150,150);
        }
    }
}
