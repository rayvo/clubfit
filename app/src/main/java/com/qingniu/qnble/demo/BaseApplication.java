package com.qingniu.qnble.demo;

import android.app.Application;
import android.util.Log;

import com.kitnew.ble.utils.EncryptUtils;
import com.qingniu.qnble.demo.util.FileUtils;
import com.qingniu.qnble.utils.QNLogUtils;
import com.yolanda.health.qnblesdk.listen.QNResultCallback;
import com.yolanda.health.qnblesdk.out.QNBleApi;

/**
 * @author: hekang
 * @description:
 * @date: 2018/03/21 20:20
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String encryptPath = "file:///android_asset/testEncrypt.txt";
        QNBleApi mQNBleApi = QNBleApi.getInstance(this);
        mQNBleApi.initSdk("123456789", encryptPath, new QNResultCallback() {
            @Override
            public void onResult(int code, String msg) {
                Log.d("BaseApplication", "初始化文件" + msg);
            }
        });
    }
}