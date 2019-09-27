package com.example.androidtoolbox.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.example.androidtoolbox.aidl.IPerson;

import java.util.HashMap;
import java.util.List;

public class PersonAidlService extends Service {
    private PersonBinder personBinder=new PersonBinder();
    private HashMap<Integer,String> namesMap=new HashMap<>();
    @Override
    public IBinder onBind(Intent intent) {
        return personBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        namesMap.put(1,"tom");
        namesMap.put(2,"jack");
        namesMap.put(3,"bill");
        namesMap.put(4,"rose");
        Toast.makeText(getApplicationContext(),"service runing!",Toast.LENGTH_SHORT).show();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    private class PersonBinder extends IPerson.Stub{
        @Override
        public String queryName(int id) throws RemoteException {
            return namesMap.get(id);
        }
    }
}
