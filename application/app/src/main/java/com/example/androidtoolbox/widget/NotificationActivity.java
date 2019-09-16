package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.androidtoolbox.R;

public class NotificationActivity extends AppCompatActivity {

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifaction);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            this.requestPermissions(new String[]{Manifest.permission.VIBRATE},1);
//        }

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"NotificationActivity");

        PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentTitle("Title");
        builder.setContentText("this is a notification!");
        builder.setTicker("Ticker!");
//        builder.setContentIntent(pendingIntent);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setOngoing(true);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setWhen(System.currentTimeMillis())
//       	.setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.drawable.woman_icon);

        builder.setVibrate(new long[]{500,1000,600,1000,700,1000});


        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
        {
            NotificationChannel cn=new NotificationChannel("NotificationActivity","ChannelName",NotificationManager.IMPORTANCE_DEFAULT);
            cn.enableVibration(true);
            cn.setVibrationPattern(new long[]{500,400,600});
            cn.enableLights(true);
            notificationManager.createNotificationChannel(cn);
        }

        Notification notification = builder.build();
//        notification.flags=Notification.DEFAULT_VIBRATE;
        notificationManager.notify(1,notification);
    }

    public void cancelNotification(View view) {
        notificationManager.cancel(1);
    }
}
