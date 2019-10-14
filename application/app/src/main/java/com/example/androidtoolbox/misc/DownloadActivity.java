package com.example.androidtoolbox.misc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.LogUtil;

import java.io.File;

public class DownloadActivity extends AppCompatActivity {

    public static final String FILE_NAME="a.apk";

    private  DownLoadReceiver downLoadReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        String endpoint="";

        try {
//            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
//            applicationInfo.metaData.getString("down_url");
//
//            String path = getIntent().getStringExtra("path");
//            String url=endpoint+path;
//            http://localhost:8080/down/a.txt
            String url="http://3g.163.com/links/4636";
            String urlA = "http://gdown.baidu.com/data/wisegame/dc429998555b7d4d/QQ_398.apk";
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
            request.setDestinationInExternalFilesDir(getApplicationContext(),Environment.DIRECTORY_DOWNLOADS,FILE_NAME);
            request.setMimeType("application/vnd.android.package-archive");
            request.setTitle(getString(R.string.app_name));
            request.setDescription("Download Test!");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                request.setRequiresDeviceIdle(false);
                request.setRequiresCharging(false);
            }
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE| DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
           long id= downloadManager.enqueue(request);
            LogUtil.d("id:"+id);
//            this.finish();
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        downLoadReceiver=new DownLoadReceiver();
        IntentFilter intentFilter=new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downLoadReceiver,intentFilter);
//        sendBroadcast(new Intent("com.down"));

    }


    public static class DownLoadReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            Uri downloadFileUri=downloadManager.getUriForDownloadedFile(downloadId);
            Cursor cursor = downloadManager.query(query);

            if (cursor.moveToFirst()){
                int status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
//                String filename = cursor.getString(cursor.getColumnIndexOrThrow(context.getContentResolver().openFileDescriptor()));

                if (status==DownloadManager.STATUS_SUCCESSFUL&&downloadFileUri.toString().indexOf(FILE_NAME)!=0){
                    Intent intent1 = new Intent();
                    intent1.setAction(Intent.ACTION_VIEW);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent1.setDataAndType(Uri.fromFile(""))

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                        String absolutePath = externalFilesDir.getAbsolutePath();
                        File downFile=new File(absolutePath,FILE_NAME);
                        String path=downFile.getAbsolutePath();
                        Uri uri = FileProvider.getUriForFile(context,"com.example.androidtoolbox",new File(absolutePath,FILE_NAME));
                        intent1.setDataAndType(uri, "application/vnd.android.package-archive");
                    } else {
                        intent1.setDataAndType(Uri.parse(cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI))),"application/vnd.android.package-archive");
                    }

                    context.startActivity(intent1);
                }
            }
        }
    }


    protected void installApk(String filePath){
        Intent intentInstall = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
// 由于没有在Activity环境下启动Activity,设置下面的标签
        intentInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
        Uri apkUri = FileProvider.getUriForFile(getApplicationContext(), "com.example.androidtoolbox", file);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intentInstall.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intentInstall.setDataAndType(apkUri, "application/vnd.android.package-archive");
        getApplicationContext().startActivity(intentInstall);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downLoadReceiver);
    }
}
