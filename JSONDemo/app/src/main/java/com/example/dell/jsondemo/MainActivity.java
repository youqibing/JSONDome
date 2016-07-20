package com.example.dell.jsondemo;

import android.app.Activity;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.dell.jsondemo.Adapter.PictureAdapter;
import com.example.dell.jsondemo.JSON.JSONData;
import com.example.dell.jsondemo.JSON.JSONHelper;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private ListView JSONList;
    private Button creatFilebtn;
    private PictureAdapter adapter;
    //private SimpleAdapter adapter;

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creatFilebtn = (Button)findViewById(R.id.button);

        JSONList = (ListView)findViewById(R.id.list_item);
        ArrayList<HashMap<String,String>> pictureList = new ArrayList<HashMap<String,String>>();

        try{
            final String PATH = Environment.getExternalStorageDirectory().getPath()+"/json.data";
            //final String PATH = getExternalFilesDir("json.data").getPath();
            //String PATH = getFilesDir().getPath()+"/json.data";
            file = new File(PATH);

            creatFilebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!file.exists()){
                        file.mkdirs();

                        boolean b = file.exists();
                        Log.e("test",b+"");
                        //Toast.makeText(MainActivity.this,b+"",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            FileInputStream inputStream = new FileInputStream(file+"/json.txt");
            JSONHelper jsonHelper = new JSONHelper(inputStream);
            List<JSONData> datas = jsonHelper.getData();

            HashMap<String,String> map;
            for(JSONData json : datas){
                map = new HashMap<String, String>();

                map.put("image_url",json.getUrl());
                map.put("image_comment",json.getComment());

                pictureList.add(map);
            }

            adapter =new PictureAdapter(this,pictureList);

            //String[] from ={"image_url","image_comment"};
            //int[] to ={R.id.textView1,R.id.textView2};

            //adapter = new SimpleAdapter(MainActivity.this,list,R.layout.item_layout,from,to);


        }catch(Exception e){
            e.printStackTrace();
        }

        JSONList.setAdapter(adapter);

    }
}
