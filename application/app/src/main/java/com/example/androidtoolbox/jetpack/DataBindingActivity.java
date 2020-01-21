package com.example.androidtoolbox.jetpack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.databinding.ActivityDataBingindBinding;
import com.example.androidtoolbox.jetpack.livedata.MyViewModelWithLiveData;
import com.example.androidtoolbox.jetpack.livedata.MyViewModelWithSavedData;

public class DataBindingActivity extends AppCompatActivity {

    private MyViewModelWithLiveData viewModelWithLiveData;

    private ActivityDataBingindBinding binding;

    private MyViewModelWithSavedData myViewModelWithSavedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_data_bingind);
//        viewModelWithLiveData= ViewModelProviders.of(this).get(MyViewModelWithLiveData.class);
//        binding.setViewModel(viewModelWithLiveData);
        myViewModelWithSavedData=ViewModelProviders.of(this,new ViewModelProvider.Factory(){
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return null;
            }
        }).get(MyViewModelWithSavedData.class);
        binding.setViewModel(myViewModelWithSavedData);
        binding.setLifecycleOwner(this);
    }
}
