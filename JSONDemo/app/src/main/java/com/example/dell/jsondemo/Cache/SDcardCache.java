package com.example.dell.jsondemo.Cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.dell.jsondemo.MD5Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by dell on 2016/7/20.
 * 二级缓存，本地缓存
 */

public class SDcardCache {

    public static final String PATH = Environment.
            getExternalStorageDirectory().getAbsolutePath()+"/JSONDemoImage";

    public Bitmap readFromSDCard(String Url){
        String fileName = null;
        try {
            fileName = MD5Encoder.encode(Url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(PATH,fileName);

        if(file.exists()){
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                return bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void writeToSDCard(String Url,Bitmap bitmap){
        String filenName = null;
        try {
            filenName = MD5Encoder.encode(Url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(PATH,filenName);
        //我们首先得到他的符文剑
        File parentFile = file.getParentFile();
        //查看是否存在，如果不存在就创建
        if (!parentFile.exists()){
            parentFile.mkdirs(); //创建文件夹
        }

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));//压缩后保存到本地
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
