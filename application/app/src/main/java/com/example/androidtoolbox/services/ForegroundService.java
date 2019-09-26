package com.example.androidtoolbox.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.androidtoolbox.R;

public class ForegroundService extends Service {
    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"ServicesDemoActivity");
        builder.setContentTitle("title");
        builder.setContentText("this is content");
        builder.setSmallIcon(R.drawable.icon_edit);


        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("ServicesDemoActivity","ServicesDemoActivity",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        startForeground(1,builder.build());
    }
}
