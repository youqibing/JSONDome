package com.example.dell.jsondemo.Cache;

import android.graphics.Bitmap;
import android.util.LruCache;


/**
 * Created by dell on 2016/7/20.
 * 一级缓存，内存缓存
 */

public class MemoryCache {
    private LruCache<String,Bitmap> lruCache;

    public MemoryCache(){
        long maxMemory = Runtime.getRuntime().maxMemory();//获取最大运行内存,默认是16兆
        lruCache = new LruCache<String,Bitmap>((int)(maxMemory/8)){//一级缓存最大给八分之一运存
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //return super.sizeOf(key, value);
                int byteCount = value.getRowBytes()*value.getWidth();//获取图片字节数
                return byteCount;
            }
        };

    }

    /**
     *从内存中读取
     */
    public Bitmap readFromMemroy(String Url){
        return lruCache.get(Url);
    }

    /**
     * 写入内存
     */
    public void writeToMemory(String Url,Bitmap bitmap){
        lruCache.put(Url,bitmap);
    }


}
