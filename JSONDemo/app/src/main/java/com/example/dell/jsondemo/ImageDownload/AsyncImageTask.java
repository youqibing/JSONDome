package com.example.dell.jsondemo.ImageDownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by dell on 2016/6/12.
 * 利用AsyncTask线程池管理卸载任务,注意不要往文件夹里边存图片，直接在输入流里边截取图片显示就可以了，（这部分已经注释掉了）
 * 如果要往文家夹里边写图片的话，因为我们用系统时间命名，每张图片的名字都不一样，系统就会认为这是不同的图片，导致文件夹里边
 * 的图片越来越多，手机会炸掉了，这个暂时没有找到可行的解决方法。
 */

public class AsyncImageTask extends AsyncTask<String,Integer,Bitmap> {

    private ImageView imageView;
    //private static final String savePath = Environment.getExternalStorageDirectory()+"/JSONDome";
    //private String saveFileName =System.currentTimeMillis()+".jpg";

    public AsyncImageTask(ImageView imageView){
        this.imageView=imageView;
    }

    //后台运行子线程
    @Override
    protected Bitmap doInBackground(String... params) {
        String imageUrl = params[0];
        //Log.e("test",imageUrl);

        URL url =null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream  =null;
        Bitmap bitmap = null;

        try {
            url = new URL(imageUrl);
            conn = (HttpURLConnection)url.openConnection();

            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect();

            //File file =new File(savePath,saveFileName);
            /*
            if(!file.exists()){
                file.mkdirs();
            }
            */
            inputStream = conn.getInputStream();
            //fileOutputStream = new FileOutputStream(file);
            bitmap = BitmapFactory.decodeStream(inputStream);


            //byte[] buffer = new byte[1024];
            //int length = -1;
            /*
            while((length = inputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer,0,length);
            }
            */

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if(conn!=null){
                conn.disconnect();
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    //在UI线程中进行的操作
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.e("test",bitmap+"");

        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
            //Log.e("test",bitmap+"");
        }
    }
}
