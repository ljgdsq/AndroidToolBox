package com.example.androidtoolbox.misc;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.reciever.SimpleReceiver;
import com.example.androidtoolbox.worker.SimpleWorker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkerTestActivity extends AppCompatActivity {

    @BindView(R.id.btn_work)
    Button btnWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_work)
    public void onViewClicked() {

        OneTimeWorkRequest request=new OneTimeWorkRequest.Builder(SimpleWorker.class).build();
        WorkManager.getInstance(getApplicationContext()).enqueue(request);
    }
}
