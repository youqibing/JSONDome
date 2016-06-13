package com.example.dell.jsondemo.JSON;

/**
 * Created by dell on 2016/6/7.
 */

public class JSONData {

    private String image_url;
    private String image_comment;

    public String getUrl(){
        return image_url;
    }

    public void setUrl(String image_url){
        this.image_url=image_url;
    }

    public String getComment(){
        return  image_comment;
    }

    public void setComment(String image_comment){
        this.image_comment=image_comment;
    }

}
