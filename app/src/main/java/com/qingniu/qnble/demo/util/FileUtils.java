package com.qingniu.qnble.demo.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.qingniu.qnble.utils.QNLogUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: hekang
 * @description:
 * @date: 2018/04/13 10:53
 */

public class FileUtils {

    /**
     * 传递的文件地址的文件存在且可读
     */
    public static boolean isCanReadFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.canRead()) {
            return true;
        }
        return false;
    }

    /**
     * 从assets目录中复制文件到指定地址
     *
     * @param context  Context 使用CopyFiles类的Activity
     * @param fileName String  原文件名字  如：aa.xx
     * @param newPath  String  复制后路径  如：xx:/bb/cc
     */
    public static void copyFileFormAssets(Context context, String fileName, String newPath) {
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(newPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
            }
            fos.flush();//刷新缓冲区
            is.close();
            fos.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            QNLogUtils.logAndWrite("读取asset文件到指定位置异常");
        }
    }


    public static String readAssertResource(Context context, String strAssertFileName) {
        AssetManager assetManager = context.getAssets();
        String strResponse = "";
        try {
            InputStream ims = assetManager.open(strAssertFileName);
            strResponse = getStringFromInputStream(ims);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResponse;
    }

    private static String getStringFromInputStream(InputStream a_is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(a_is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return sb.toString();
    }
}
