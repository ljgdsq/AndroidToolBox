package com.example.androidtoolbox.misc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.fragment.FragmentA;
import com.example.androidtoolbox.fragment.FragmentB;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentTestActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.frame)
    FrameLayout frame;

    private boolean isFragmentA=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().add(R.id.frame,new FragmentA()).commit();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        if (isFragmentA){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new FragmentB()).commit();

        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new FragmentA()).commit();

        }
        isFragmentA=!isFragmentA;
    }
}
