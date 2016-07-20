package com.example.dell.jsondemo.ImageDownload;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dell.jsondemo.Cache.MemoryCache;
import com.example.dell.jsondemo.Cache.NetCache;
import com.example.dell.jsondemo.Cache.SDcardCache;

/**
 * Created by dell on 2016/6/12.
 */

public class ImageLoader {

    private NetCache netCache;
    private MemoryCache memoryCache;
    private SDcardCache sDcardCache;

    public void displayImage(String url, ListView listView,ImageView imageView){
        memoryCache = new MemoryCache();
        sDcardCache = new SDcardCache();
        netCache = new NetCache(memoryCache,sDcardCache);


        //AsyncImageTask task = new AsyncImageTask(listView);
        //task.execute(url);

        //从内存中读取
        Bitmap bitmapFromMemory = memoryCache.readFromMemroy(url);
        if(bitmapFromMemory != null){           //如果内存中有的话就直接从内存中读取.
            imageView.setImageBitmap(bitmapFromMemory);

            return;
        }

        //从SD卡中读取
        Bitmap bitmapForSDCard = sDcardCache.readFromSDCard(url);
        if(bitmapForSDCard != null){
            imageView.setImageBitmap(bitmapForSDCard);

            memoryCache.writeToMemory(url,bitmapForSDCard);//读完之后把这个写到内存中

            return;
        }

        //启动网络下载
        netCache.readImageFromNet(listView,url);

    }


}
