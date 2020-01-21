package com.example.androidtoolbox.jetpack.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModelWithLiveData extends ViewModel {
    private MutableLiveData<Integer> liveData;


    public MyViewModelWithLiveData() {
        liveData=new MutableLiveData<>();
        liveData.setValue(0);
    }


    public MutableLiveData<Integer> getLiveData() {
        return liveData;
    }

    public void increase()
    {
        liveData.setValue(liveData.getValue()+1);
    }

    public void decrease()
    {
        liveData.setValue(liveData.getValue()-1);
    }
}
