package com.example.androidtoolbox.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.androidtoolbox.Address;
import com.example.androidtoolbox.IAddress;

import java.util.HashMap;
import java.util.Map;

public class AddressAidlService extends Service {
    private AddressBinder addressBinder;

    private Map<Integer,Address> addressMap;

    public AddressAidlService() {
        addressBinder=new AddressBinder();
        addressMap=new HashMap<>();

        for (int i=1;i<5;i++)
        {
            addressMap.put(i,new Address(i,"address"+i));

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return addressBinder;
    }


//
    private class AddressBinder extends IAddress.Stub{

    @Override
    public void queryAddress(int i, Address add) throws RemoteException {
        add.setName(addressMap.get(i).getName());
    }

    @Override
    public Address queryById(int id) throws RemoteException {
        return addressMap.get(id);
    }
}


}
