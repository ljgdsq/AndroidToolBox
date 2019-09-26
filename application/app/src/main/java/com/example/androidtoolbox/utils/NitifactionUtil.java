package com.example.androidtoolbox.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NitifactionUtil {
    public static Notification sendSimpleNotifaction(Context context, String title, String content, int icon, String ticker)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"NotificationActivity");
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setTicker(ticker);
        builder.setOngoing(true);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setWhen(System.currentTimeMillis())
                .setSmallIcon(icon);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
        {
            NotificationChannel cn=new NotificationChannel("NotificationActivity","ChannelName",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(cn);
        }

        Notification notification = builder.build();
        notificationManager.notify(1,notification);
        return notification;
    }
}
