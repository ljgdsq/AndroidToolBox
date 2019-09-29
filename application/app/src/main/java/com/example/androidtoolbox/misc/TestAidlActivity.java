package com.example.androidtoolbox.misc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.Address;
import com.example.androidtoolbox.IAddress;
import com.example.androidtoolbox.R;
import com.example.androidtoolbox.services.AddressAidlService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestAidlActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;


    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Address address=new Address(2,"hehe");
            try {
                ((IAddress)service).queryAddress(1,address);
                Toast.makeText(getApplicationContext(),address.getName(),Toast.LENGTH_SHORT).show();
                Address address1 = ((IAddress) service).queryById(3);
                Toast.makeText(getApplicationContext(),address1.getName(),Toast.LENGTH_SHORT).show();

            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_aidl);
        ButterKnife.bind(this);

        intent=new Intent(getApplicationContext(), AddressAidlService.class);
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:

                boolean res=bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
                if (res)
                {
                    Toast.makeText(getApplicationContext(),"bind success",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(),"bind fail",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button2:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
