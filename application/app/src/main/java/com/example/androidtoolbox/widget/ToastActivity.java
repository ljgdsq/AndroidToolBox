package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.ImageUtils;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);


    }

    private void defaultToast() {
        Toast.makeText(this, "Toast", Toast.LENGTH_LONG).show();
    }

    public void showDefaultToast(View view) {
        defaultToast();
    }


    private void toast01() {
        Toast toast = Toast.makeText(this, "Toast", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.START, 100, 100);
        TextView textView = toast.getView().findViewById(android.R.id.message);

        textView.setTextColor(Color.RED);
        toast.show();
    }


    public void showToast01(View view) {
        toast01();
    }

    public void showToast02(View view) {
        toast02();
    }


    private void toast02() {
        Toast toast = Toast.makeText(this, "Toast", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        linearLayout.setBackgroundColor(Color.BLUE);
        ImageView imageView = new ImageView(this);

        Bitmap scaledBitmap = ImageUtils.getScaledBitmap(getResources(), R.drawable.icon_edit, 64, 64);
        imageView.setImageBitmap(scaledBitmap);
        linearLayout.addView(imageView, 0);
        toast.show();
    }



    private void toast03()
    {
        Toast toast = new Toast(this);

        LayoutInflater layoutInflater=getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast, null);
        toast.setView(view);
        toast.show();
    }

    public void showToast03(View view) {
        toast03();
    }
}
