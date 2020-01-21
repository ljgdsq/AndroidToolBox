package com.example.androidtoolbox.misc;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LruCache;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BitmapActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        ButterKnife.bind(this);

        ActivityManager.MemoryInfo memoryInfo=new ActivityManager.MemoryInfo();
        ActivityManager systemService = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        systemService.getMemoryInfo(memoryInfo);
        int memoryClass = systemService.getMemoryClass();
        Log.d("memoryClass", "memoryClass: "+memoryClass);

        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        Log.d("memoryClass", "totalMemory: "+totalMemory/1024/1024);
        Log.d("memoryClass", "maxMemory: "+maxMemory/1024/1024);

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        captureScreen();
    }

    private void captureScreen() {
        new Thread(() -> {
            View decorView = getWindow().getDecorView();
            Bitmap bitmap = Bitmap.createBitmap(decorView.getWidth(), decorView.getHeight(), Bitmap.Config.ARGB_8888);
            decorView.draw(new Canvas(bitmap));
            String path = getFilesDir().getAbsolutePath();
            try {
                OutputStream ops = new FileOutputStream(new File(path + File.separatorChar + "capture.png"));
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, ops);
                ops.flush();
                ops.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
