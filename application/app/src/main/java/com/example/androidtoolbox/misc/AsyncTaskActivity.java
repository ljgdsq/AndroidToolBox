package com.example.androidtoolbox.misc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncTaskActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ButterKnife.bind(this);

        new DownloadTask().execute("www.baidu.com");

    }



    private class DownloadTask extends AsyncTask<String,Float,Float>{
        public static final String TAG="DownloadTask";
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
        }

        @Override
        protected void onPostExecute(Float aFloat) {
            Log.d(TAG, "onPostExecute: ");

            Toast.makeText(AsyncTaskActivity.this,"Complete!",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            Log.d(TAG, "onProgressUpdate: "+values[0]);
            progressBar.setProgress((int)(values[0]*100));
        }

        @Override
        protected void onCancelled(Float aFloat) {
            Log.d(TAG, "onCancelled: ");
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled: ");
        }

        @Override
        protected Float doInBackground(String... strings) {
            for (int i=1;i<=100;i++)
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i/100.0f);

            }
            return null;
        }
    }
}
