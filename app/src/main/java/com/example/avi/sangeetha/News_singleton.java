package com.example.avi.sangeetha;

import java.util.ArrayList;

/**
 * Created by Avi on 2/9/2016.
 */
public class News_singleton {

    public ArrayList<news_data> data;
    private static News_singleton instance = null;

    public News_singleton()
    {
        data = new ArrayList<>();
    }

    public static News_singleton getInstance(){
        if(instance==null){
            instance = new News_singleton();
        }
        return instance;
    }

    public void AddData(news_data symposium_data)
    {
        data.add(symposium_data);
    }
}