package com.example.androidtoolbox.misc;

import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GestureActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.gestureOverlayView)
    GestureOverlayView gestureOverlayView;

    private String path="mnt/sdcard/gesture";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        ButterKnife.bind(this);

        gestureOverlayView.setGestureColor(Color.GREEN);
        gestureOverlayView.setGestureStrokeWidth(4);
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                View dialogView=getLayoutInflater().inflate(R.layout.dialog_save_gesture,null);
                ImageView img_show =dialogView.findViewById(R.id.img_show);
                EditText edit_name =  dialogView.findViewById(R.id.edit_name);
                Bitmap bitmap = gesture.toBitmap(128, 128, 10, 0xffff0000);
                img_show.setImageBitmap(bitmap);

                new AlertDialog.Builder(GestureActivity.this).setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GestureLibrary library = GestureLibraries.fromFile(path);
                        library.addGesture(edit_name.getText().toString(),gesture);

                        boolean res=library.save();
                        if (res)
                        {
                            Toast.makeText(GestureActivity.this,"success!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(GestureActivity.this,"fail!",Toast.LENGTH_SHORT).show();

                        }
                    }
                }).setNegativeButton("cancle",null).setView(dialogView).show();


            }
        });
    }


    @OnClick(R.id.button)
    void onBtnClick(){
        GestureLibrary gestureLibrary = GestureLibraries.fromFile(path);
        if (gestureLibrary.load())
        {
            Toast.makeText(GestureActivity.this,"success!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(GestureActivity.this,"fail!",Toast.LENGTH_SHORT).show();

        }
    }
}
