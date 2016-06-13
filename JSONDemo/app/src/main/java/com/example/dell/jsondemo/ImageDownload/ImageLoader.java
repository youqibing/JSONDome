package com.example.dell.jsondemo.ImageDownload;

import android.widget.ImageView;

/**
 * Created by dell on 2016/6/12.
 */

public class ImageLoader {

    public void displayImage(String url,ImageView imageView){

        AsyncImageTask task = new AsyncImageTask(imageView);

        task.execute(url);

    }


}
