package com.example.avi.sangeetha;

import android.app.Activity;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.avi.sangeetha.dfapi.ApiException;
import com.example.avi.sangeetha.dfapi.BaseAsyncRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Avi on 3/1/2016.
 */
public class Loads_news extends BaseAsyncRequest {

    private final Activity activity;
    private String city;

    public Loads_news(Activity activity, String city)
    {
        this.city = city;
        this.activity = activity;
    }

    @Override
    protected void doSetup() throws ApiException, JSONException {
        super.doSetup();
        serviceName = city;
        endPoint = "?service=rss";
        verb = "GET";
    }

    @Override
    protected void processResponse(String response) throws ApiException, JSONException {
        super.processResponse(response);
        Log.e("data", response);
        AddData(response);
        Thread uithread  = new Thread(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)activity).mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        uithread.start();
    }

    private void AddData(String response) {
        DocumentBuilder db = null;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(response));
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("item");
            News_singleton singleton = News_singleton.getInstance();
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                String title= getCharacterDataFromElement((Element) element.getElementsByTagName("title").item(0));
                String author= getCharacterDataFromElement((Element) element.getElementsByTagName("author").item(0));
                String category= getCharacterDataFromElement((Element) element.getElementsByTagName("category").item(0));
                String link= getCharacterDataFromElement((Element) element.getElementsByTagName("link").item(0));
                String description= getCharacterDataFromElement((Element) element.getElementsByTagName("description").item(0));
                String date= getCharacterDataFromElement((Element) element.getElementsByTagName("pubDate").item(0));
                singleton.AddData(new news_data(title,author,category,link,description,date));
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String getCharacterDataFromElement(Element e) {
        if(e.getFirstChild()==null)
            return "";
        return e.getFirstChild().getTextContent();

    }
}

