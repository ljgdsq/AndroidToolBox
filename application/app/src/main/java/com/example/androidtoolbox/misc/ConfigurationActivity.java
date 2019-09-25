package com.example.androidtoolbox.misc;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfigurationActivity extends AppCompatActivity {

    @BindView(R.id.button7)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.button7)
    void onBtnClick(View view)
    {
        Configuration configuration = getResources().getConfiguration();
        int orientation=getRequestedOrientation();
        if (orientation==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE |orientation==ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE)
        {
            ConfigurationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }else {
            ConfigurationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Toast.makeText(ConfigurationActivity.this,"screen change",Toast.LENGTH_SHORT).show();
        super.onConfigurationChanged(newConfig);
    }
}
