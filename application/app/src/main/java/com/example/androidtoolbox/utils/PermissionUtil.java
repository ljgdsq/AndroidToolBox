package com.example.androidtoolbox.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {
//    public static void requestPermission(Context context,String permission) {
//        int permission1 = context.checkSelfPermission(permission);
//
//    }


    public static boolean hasPermission(Context context,String...permissions)
    {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M)
        {
            return true;
        }
        for (String permission:permissions)
        {
            int res = ContextCompat.checkSelfPermission(context, permission);
            if (res!=PackageManager.PERMISSION_GRANTED)
            {
                return false;
            }
        }
        return true;
    }

    public static void requestPermission(Activity activity, int reqCode, String...permission)
    {
        ActivityCompat.requestPermissions(activity,permission,reqCode);

    }

    public static boolean checkActivityResult(String[] permissions,int[] grantResults,String...yourPermissions){
        for (int i=0;i<permissions.length;i++)
        {
            for (String permission:yourPermissions)
            {
                if (permissions[i].equals(permission) &grantResults[i]==PackageManager.PERMISSION_DENIED)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
