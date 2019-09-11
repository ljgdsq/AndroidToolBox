package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.androidtoolbox.R;

public class ScrollViewActivity extends AppCompatActivity {

    private TextView textView;
    private ScrollView scrollView;
    private Button toTopButton;
    private Button toBottomButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        initViews();
    }



    private void initViews(){

        toTopButton=findViewById(R.id.buttonToTop);
        toBottomButton=findViewById(R.id.buttonToBottom);

        textView=findViewById(R.id.tv_content);
        scrollView=findViewById(R.id.scrollview);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 100; i++) {
            sb.append("呵呵 * " + i + "\n");
        }
        textView.setText(sb.toString());

        toTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });


        toBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                scrollToBottom(scrollView,textView);
            }
        });
    }


    private void scrollToBottom(ScrollView scrollView,View inner){
        int scrollDistance = inner.getHeight() - scrollView.getMeasuredHeight();
        scrollView.scrollTo(0,scrollDistance);
    }
}
