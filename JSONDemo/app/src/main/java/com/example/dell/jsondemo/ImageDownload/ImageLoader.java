package com.example.dell.jsondemo.ImageDownload;

import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by dell on 2016/6/12.
 */

public class ImageLoader {

    public void displayImage(String url, ListView listView){

        //imageView.setTag(url);
        //imageView = (ImageView)listView.findViewWithTag(url);

        AsyncImageTask task = new AsyncImageTask(listView);

        task.execute(url);

    }


}
