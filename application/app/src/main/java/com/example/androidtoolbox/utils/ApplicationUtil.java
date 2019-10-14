package com.example.androidtoolbox.utils;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.content.FileProvider;

import com.example.androidtoolbox.Application;

import java.io.File;

public class ApplicationUtil {
    public static void installApk(String filePath) {
        Intent intentInstall = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
// 由于没有在Activity环境下启动Activity,设置下面的标签
        intentInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
        Uri apkUri = FileProvider.getUriForFile(Application.getContext(), "com.example.androidtoolbox", file);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intentInstall.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intentInstall.setDataAndType(apkUri, "application/vnd.android.package-archive");
        Application.getContext().startActivity(intentInstall);
    }


    public static boolean canUseDownloadManager() {
        int state = Application.getContext().getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED
        ) {
            return false;
        }

        return true;
    }
}
