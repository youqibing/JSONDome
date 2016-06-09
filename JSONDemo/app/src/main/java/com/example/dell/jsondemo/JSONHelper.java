package com.example.dell.jsondemo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/6/7.
 */

public class JSONHelper {

    private InputStream inputStream;
    private JSONData data;
    private List<JSONData> datas;

    public JSONHelper(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<JSONData> getData(){
        datas = new ArrayList<JSONData>();

        try{
            String json_str = getJsonString(inputStream);    //从输入流中获取JSON字符串的函数
            JSONArray array = new JSONArray(json_str);      //因为我们要提取的json数据最外层为[]，看到[]就用JSONArray

            for(int i=0;i<array.length();i++){              //这个循环是扒"information","image"这两个外面的中括号那一层的

                JSONObject object = array.getJSONObject(i);
                JSONArray imageArray = object.getJSONArray("image");

                for(int j=0; j<imageArray.length();j++){                    //这个循环是扒image这一层的数据
                    JSONObject imageObject = imageArray.getJSONObject(j);

                    data = new JSONData();
                    data.setUrl(imageObject.getString("image_url"));           //提取目标信息
                    data.setComment(imageObject.getString("image_comment"));

                    datas.add(data);                                          //添加入List
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return datas;
    }

    private String getJsonString(InputStream inputStream) throws Exception{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GB2312"));
        StringBuilder stringBuider= new StringBuilder();
        String ReaderLine = "";

        while((ReaderLine = bufferedReader.readLine())!= null){

            stringBuider.append(ReaderLine);
        }
        bufferedReader.close();

        return stringBuider.toString();
    }


}
