package com.example.androidtoolbox.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopupWindowActivity extends AppCompatActivity {

    private static final String TAG = "PopupWindowActivity";
    @BindView(R.id.button1)
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        ButterKnife.bind(this);


    }


    private void createPopupWindow(View parent) {
        View view = LayoutInflater.from(this).inflate(R.layout.popup, null);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.anim.popup_in);
//        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor((v, event) ->{
            Log.d(TAG, "createPopupWindow: "+v);
            return  false;
        });
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.CYAN));

        popupWindow.showAsDropDown(parent,100,0);


    }

    @OnClick(R.id.button1)
    public void onViewClicked(View view) {
        createPopupWindow(view);
    }
}
