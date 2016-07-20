package com.example.dell.jsondemo.Cache;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dell.jsondemo.ImageDownload.AsyncImageTask;

import java.net.URL;

/**
 * Created by dell on 2016/7/20.
 */

public class NetCache {
    private ImageView imageView;

    private MemoryCache memoryCache;
    private SDcardCache sDcardCache;

    public NetCache(MemoryCache memoryCache, SDcardCache sDcardCache){
        this.memoryCache = memoryCache;
        this.sDcardCache = sDcardCache;
    }

    public void readImageFromNet(ListView listView,String Url){
        new AsyncImageTask(listView,memoryCache,sDcardCache).execute(Url);//启动网络下载同时将listView对象传入用于确保不乱序
    }

}
