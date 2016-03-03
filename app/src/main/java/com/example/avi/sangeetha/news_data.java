package com.example.avi.sangeetha;

import android.view.animation.Animation;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;

/**
 * Created by Avi on 3/1/2016.
 */
public class news_data {
    private final String date;
    public String title,author,category,link,description;

    public news_data(String title, String author, String category, String link, String description, String date) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.link = link;
        this.description = description;
        this.date = date;
    }

    public String getInfo() {
        return title+"\n"+author+"\n"+category+"\n"+link+"\n"+description+"\n"+date+"\n"+"\n"+"\n";
    }
}
