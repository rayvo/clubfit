package com.qingniu.qnble.demo.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * author: yolanda-XY
 * date: 2018/4/2
 * package_name: com.qingniu.qnble.demo.util
 * description: ${动态申请权限}
 */

public class AndroidPermissionCenter {

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"};

    public static int REQUEST_EXTERNAL_STORAGE = 201;

    //API>=23时才需要判断权限，扫描时再判断权限
    public static void verifyPermissions(Activity activity) {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}
                    , REQUEST_EXTERNAL_STORAGE);
        }
    }

}
