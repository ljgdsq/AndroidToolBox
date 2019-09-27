package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidtoolbox.aidl.IPerson;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;
    private Button button;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                String s = ((IPerson) service).queryName(1);

                IPerson iPerson = IPerson.Stub.asInterface(service);
//                String s = iPerson.queryName(1);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver=new SimpleReceiver();
        registerReceiver(receiver,new IntentFilter("com.example.broadcastreceiver.SimpleReceiver"));

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent("com.example.androidtoolbox.services.PersonAidlService");
                intent.setPackage("com.example.androidtoolbox");
                boolean res=bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
                if (res)
                {
                    Toast.makeText(getApplicationContext(),"bind success!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"bind fail!",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private static class SimpleReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"onReceive",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unbindService(serviceConnection);
    }
}
