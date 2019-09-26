package com.example.androidtoolbox.misc;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.services.ForegroundService;
import com.example.androidtoolbox.services.MyIntentService;
import com.example.androidtoolbox.services.MyService01;
import com.example.androidtoolbox.utils.PermissionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicesDemoActivity extends AppCompatActivity {

    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.bind)
    Button bind;
    @BindView(R.id.unbind)
    Button unbind;
    @BindView(R.id.start02)
    Button start02;
    @BindView(R.id.stop02)
    Button stop02;
    @BindView(R.id.bind02)
    Button bind02;
    @BindView(R.id.unbind02)
    Button unbind02;
    @BindView(R.id.foregroundService)
    Button foregroundService;
    private Intent intentForService;

    private Intent intentForIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_demo);
        ButterKnife.bind(this);
        intentForService = new Intent("com.example.androidtoolbox.services.MyService01");
        intentForService.setPackage(getPackageName());


        intentForIntentService = new Intent(getApplicationContext(), MyIntentService.class);
        intentForIntentService.putExtra("action", 1);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService01.MyBinder binder = (MyService01.MyBinder) service;
            int count = binder.getCount();
            Log.d("ServiceConnection", "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("ServiceConnection", "onServiceDisconnected: ");
        }
    };

    @OnClick({R.id.start, R.id.stop, R.id.bind, R.id.unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:

                startService(intentForService);
                break;
            case R.id.stop:
                stopService(intentForService);
                break;
            case R.id.bind:
                bindService(intentForService, serviceConnection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(serviceConnection);
                break;
        }
    }


    private ServiceConnection intentServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @OnClick({R.id.start02, R.id.stop02, R.id.bind02, R.id.unbind02})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.start02:
                startService(intentForIntentService);
                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
                intent.putExtra("action", 2);
                startService(intent);
                break;
            case R.id.stop02:
                stopService(intentForIntentService);
                break;
            case R.id.bind02:
                bindService(intentForIntentService, intentServiceConn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind02:
                unbindService(intentServiceConn);
                break;
        }
    }


    @OnClick(R.id.foregroundService)
    public void onViewClicked3() {
        PermissionUtil.requestPermission(ServicesDemoActivity.this, 1, Manifest.permission.FOREGROUND_SERVICE);
        if (PermissionUtil.hasPermission(getApplicationContext(), Manifest.permission.FOREGROUND_SERVICE)) {
            Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
            startService(intent);
        }
    }
}
