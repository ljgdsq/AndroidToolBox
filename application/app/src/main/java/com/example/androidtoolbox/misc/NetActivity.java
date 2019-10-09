package com.example.androidtoolbox.misc;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);

        new Thread(){
            @Override
            public void run() {
                testUrlConnection();

            }
        }.start();


    }


    private void testUrlConnection() {
        try {
            URL url = new URL("https://www.baidu.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(1000 * 10);
            urlConnection.setReadTimeout(1000 * 60);
            InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[512];
            int len = -1;
            while ((len = inputStream.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }

            byte[] bytes = bos.toByteArray();
            bos.close();

            String str = new String(bytes, StandardCharsets.UTF_8.name());
            LogUtil.d("-------------------------");
            LogUtil.d(str);
            LogUtil.d("-------------------------");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(str);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
