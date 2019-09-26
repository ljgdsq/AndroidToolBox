package com.example.androidtoolbox.misc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransactionBActivity extends AppCompatActivity {

    @BindView(R.id.button8)
    Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_b);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button8)
    public void onViewClicked() {
        startActivity(new Intent(getApplicationContext(),TransactionAActivity.class));
    }
}
