package com.example.androidtoolbox.jetpack.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
public class MyViewModelWithSavedData extends ViewModel {
    private static final String KEY="saved_num";

    private SavedStateHandle savedStateHandle;

    public MyViewModelWithSavedData(SavedStateHandle savedStateHandle){
        this.savedStateHandle = savedStateHandle;
    }
    public MutableLiveData<Integer> getLiveData(){
        if (!savedStateHandle.contains(KEY))
        {
            savedStateHandle.set(KEY,0);
        }
        return savedStateHandle.getLiveData(KEY);
    }

    public void add()
    {
        getLiveData().setValue(getLiveData().getValue()+1);
    }
}
