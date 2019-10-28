package com.example.androidtoolbox.misc;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.LogUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidPathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_path);
        ButterKnife.bind(this);
      HashMap<String,String> paths=new HashMap<>();
        paths.put("getFilesDir",getFilesDir().getAbsolutePath());

        paths.put("getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)",getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        paths.put("getCacheDir",getCacheDir().getAbsolutePath());
        paths.put("getExternalCacheDir",getExternalCacheDir().getAbsolutePath());
        paths.put("getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)",getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());

        paths.put("Environment.getRootDirectory()",Environment.getRootDirectory().getAbsolutePath());
        paths.put("Environment.getDataDirectory()",Environment.getDataDirectory().getAbsolutePath());
        paths.put("Environment.getExternalStorageState",Environment.getExternalStorageState());
        paths.put("Environment.getDownloadCacheDirectory()",Environment.getDownloadCacheDirectory().getAbsolutePath());

        for (String key:paths.keySet()){
            LogUtil.w(key+" : "+paths.get(key));
        }
    }
}
