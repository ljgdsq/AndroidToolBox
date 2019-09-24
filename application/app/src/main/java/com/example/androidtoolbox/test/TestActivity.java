package com.example.androidtoolbox.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.include_rl)
    RelativeLayout includeRl;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_right)
    public void onViewClicked() {
        Toast.makeText(TestActivity.this, "click", Toast.LENGTH_LONG).show();

    }
}
