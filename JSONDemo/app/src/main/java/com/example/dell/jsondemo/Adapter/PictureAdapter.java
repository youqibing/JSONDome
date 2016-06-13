package com.example.dell.jsondemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.jsondemo.ImageDownload.ImageLoader;
import com.example.dell.jsondemo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dell on 2016/6/12.
 * 每个条目的适配
 */

public class PictureAdapter extends BaseAdapter{

    private ArrayList<HashMap<String,String>> pictureData;
    private static LayoutInflater inflater = null;
    private Context context;
    private ImageLoader imageLoader;     //下载图片的类

    public PictureAdapter(Context context,ArrayList<HashMap<String,String>> pictureList){
        this.context = context;

        pictureData = pictureList;
        imageLoader = new ImageLoader();
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return pictureData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.item_layout,null);


        TextView imageUrlTx = (TextView)view.findViewById(R.id.imageUrl);
        ImageView imageShow = (ImageView)view.findViewById(R.id.imageView);

        HashMap<String,String> imageData = new HashMap<String,String>();
        imageData =pictureData.get(position);

        //设置ListView的相关值
        imageUrlTx.setText(imageData.get("image_comment"));
        imageLoader.displayImage(imageData.get("image_url"),imageShow);    //给ImageView加载相应的图片

        return view;
    }
}










