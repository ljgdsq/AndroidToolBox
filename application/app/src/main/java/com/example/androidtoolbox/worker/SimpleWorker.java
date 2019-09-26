package com.example.androidtoolbox.worker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.androidtoolbox.reciever.SimpleReceiver;


public class SimpleWorker extends Worker {
    public static final String TAG="SimpleWorker";
    private int count;
    public SimpleWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Data inputData = workerParams.getInputData();
        count=inputData.getInt("count",100);
    }

    @NonNull
    @Override
    public Result doWork() {
        int i=0;
        while (i<count)
        {
            i++;
            Log.d(TAG, "doWork: "+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i%10==0)
            {
                Intent intent=new Intent("com.example.broadcastreceiver.SimpleReceiver");
                getApplicationContext().sendBroadcast(intent);
            }
        }



        return Result.success();
    }
}
