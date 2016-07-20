package com.example.dell.jsondemo.ImageDownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dell.jsondemo.Cache.MemoryCache;
import com.example.dell.jsondemo.Cache.SDcardCache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by dell on 2016/6/12.
 * 利用AsyncTask线程池管理下载任务
 */

public class AsyncImageTask extends AsyncTask<String,Integer,Bitmap> {

    private ImageView imageView;
    private ListView listView;

    private MemoryCache memoryCache;
    private SDcardCache sDcardCache;

    String imageUrl;
    //private static final String savePath = Environment.getExternalStorageDirectory()+"/JSONDome";
    //private String saveFileName =System.currentTimeMillis()+".jpg";
    public AsyncImageTask(ListView listView,MemoryCache memoryCache,SDcardCache sDcardCache){

        this.memoryCache = memoryCache;
        this.sDcardCache = sDcardCache;
        this.listView =listView;

    }

    //后台运行子线程
    @Override
    protected Bitmap doInBackground(String... params) {
        imageUrl = params[0];
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

        imageView =(ImageView)listView.findViewWithTag(imageUrl);
        if(bitmap!=null&&imageView!=null){
            imageView.setImageBitmap(bitmap);
            //网络下好图片之后先写入SD卡
            sDcardCache.writeToSDCard(imageUrl,bitmap);
            //然后再写入内存
            memoryCache.writeToMemory(imageUrl,bitmap);
        }
    }
}
