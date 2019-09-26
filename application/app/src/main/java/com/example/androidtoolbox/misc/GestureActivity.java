package com.example.androidtoolbox.misc;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.PermissionUtil;

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
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 1);//设置了requestcode 需要在OnActivityResult 中再次判断是否勾选了所需权限

// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

//        PermissionUtil.requestPermission(this,1, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean result = PermissionUtil.checkActivityResult(permissions, grantResults, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!result)
        {
            Toast.makeText(this,"request permission fail!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"request permission success!",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
