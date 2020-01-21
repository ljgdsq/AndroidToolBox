package com.example.androidtoolbox.jetpack;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.jetpack.livedata.MyViewModelWithLiveData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiveDataActivity extends AppCompatActivity {


    @BindView(R.id.textView7)
    TextView textView;

    private MyViewModelWithLiveData viewModelWithLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        ButterKnife.bind(this);

        viewModelWithLiveData = ViewModelProviders.of(this).get(MyViewModelWithLiveData.class);

        viewModelWithLiveData.getLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(integer.toString());
            }
        });



    }

    @OnClick({R.id.button10, R.id.button11})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button10:
                viewModelWithLiveData.increase();
                break;
            case R.id.button11:
                viewModelWithLiveData.decrease();
                break;
        }
    }
}
