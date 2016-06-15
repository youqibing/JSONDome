package com.example.dell.jsondemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
    private ListView listView;

    public PictureAdapter(Context context,ArrayList<HashMap<String,String>> pictureList){
        this.context = context;

        pictureData = pictureList;
        imageLoader = new ImageLoader();
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /**此适配器中代表的数据集中的条目总数*/
    @Override
    public int getCount() {
        return pictureData.size();
    }

    /**获取数据集中与指定索引对应的数据项*/
    @Override
    public Object getItem(int position) {
        return position;
    }

    /**取在列表中与指定索引对应的ID*/
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView imageUrlTx;
        ImageView imageShow;

        if(listView==null){
            listView =(ListView)parent;
        }

        if(convertView==null){      //利用convertView回收视图，提高效率。
            convertView = inflater.inflate(R.layout.item_layout,null);
        }

        imageUrlTx = (TextView)convertView.findViewById(R.id.imageUrl);
        imageShow = (ImageView)convertView.findViewById(R.id.imageView);
        imageShow.setImageResource(R.mipmap.ic_launcher);

        HashMap<String,String> imageData = new HashMap<String,String>();
        imageData =pictureData.get(position);

        //设置ListView的相关值
        imageUrlTx.setText(imageData.get("image_comment"));
        imageLoader.displayImage(imageData.get("image_url"),listView);    //给ImageView加载相应的图片

        imageShow.setTag(imageData.get("image_url"));

        return convertView;
    }

}