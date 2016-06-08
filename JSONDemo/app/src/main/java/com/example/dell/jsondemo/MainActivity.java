package com.example.dell.jsondemo;

import android.app.Activity;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private ListView JSONList;
    private SimpleAdapter adapter;
    private Button creatFilebtn;
  //  private Button analyDatabtn;

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONList = (ListView)findViewById(R.id.list_item);
        creatFilebtn = (Button)findViewById(R.id.button);
        //analyDatabtn = (Button)findViewById(R.id.button2);

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
                        Toast.makeText(MainActivity.this,b+"",Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(MainActivity.this,PATH,Toast.LENGTH_LONG).show();
                }
            });

           // analyDatabtn.setOnClickListener(new View.OnClickListener() {

                FileInputStream inputStream = new FileInputStream(file+"/json.json");
                JSONHelper jsonHelper = new JSONHelper(inputStream);
                List<JSONData> datas = jsonHelper.getData();
                List<Map<String,String>> list = new ArrayList<Map<String,String>>();

            //    @Override
            //    public void onClick(View v) {

                    Map<String,String> map;
                    for(JSONData json : datas){
                        map = new HashMap<String, String>();

                        map.put("image_url",json.getUrl());
                        map.put("image_comment",json.getComment());

                        Toast.makeText(MainActivity.this,json.getUrl(),Toast.LENGTH_SHORT).show();
                        list.add(map);
                    }

                    String[] from ={"image_url","image_comment"};
                    int[] to ={R.id.textView1,R.id.textView2};

                    adapter = new SimpleAdapter(MainActivity.this,list,R.layout.item_layout,from,to);
            //    }
            //});


        }catch(Exception e){
            e.printStackTrace();
        }

        JSONList.setAdapter(adapter);

    }
}
