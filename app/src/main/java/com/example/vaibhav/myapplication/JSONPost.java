package com.example.vaibhav.myapplication;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tamoghna on 27-06-2015.
 */
public class JSONPost {
    StringBuilder builder = new StringBuilder();
    JSONObject json;
    HttpClient httpClient = new DefaultHttpClient();
    HttpContext httpContext = new BasicHttpContext();
    public static HttpPost httpPost = new HttpPost();
    static JSONArray jobj = null;
    public JSONArray postJSON(JSONObject array, String url, HashMap<String,String> header) {

        Log.e("JSON", array.toString());
        builder = new StringBuilder();
        json = array;
        httpClient = new DefaultHttpClient();
        httpContext = new BasicHttpContext();
        httpPost = new HttpPost(url); // this is the request url is the url req
        try {
            StringEntity se = new StringEntity(json.toString(), "UTF-8");
            Log.e("stringentity", se.toString());
            Log.e("httppostmethod", httpPost.getMethod());
            httpPost.setEntity(se); // Hence the se[0] instead of se ok sir (Y)
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            for(int i = 0; i<header.size();i++){
                httpPost.addHeader((String)(header.keySet().toArray()[i]), header.get(header.keySet().toArray()[i]));
            }
            for(int i = 0;i<httpPost.getAllHeaders().length;i++){
                Log.e(httpPost.getAllHeaders()[i].getName(), httpPost.getAllHeaders()[i].getValue());
            }
            HttpResponse response = httpClient.execute(httpPost); //execute your request and parse response
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            if(builder.charAt(0) == '{'){
                builder.insert(0,'[');
                builder.append("]");
            }
            jobj = new JSONArray( builder.toString()); //if response in JSON format
            Log.e("bangbang", builder.toString());
            return jobj;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Shit", e.toString());
        }
        return null;
    }
    public ArrayList<InputStream> postJSONimage(ArrayList<String> imagepath, String imageurl) {

        httpClient = new DefaultHttpClient();
        httpContext = new BasicHttpContext();
        httpPost = new HttpPost(imageurl); // this is the request url is the url req

        try {
            ArrayList<InputStream> is = new ArrayList<>();
            for (int i = 0; i < imagepath.size(); i++){
                File image = new File(imagepath.get(i));
                FileBody body = new FileBody(image);
                MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
                multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                multipartEntityBuilder.addPart("PhotoMessage", body);
                httpPost.setEntity(multipartEntityBuilder.build());
                HttpResponse response = httpClient.execute(httpPost); //execute your request and parse response
                HttpEntity entity = response.getEntity();
                is.add(entity.getContent());
            }
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Shit", e.toString());
        }
        return null;
    }
    public JSONArray postJSON(JSONObject array, String url) {

        Log.e("JSON", array.toString());
        builder = new StringBuilder();
        json = array;
        httpClient = new DefaultHttpClient();
        httpContext = new BasicHttpContext();
        httpPost = new HttpPost(url); // this is the request url is the url req
        try {

            StringEntity se = new StringEntity(json.toString(), "UTF-8");
            Log.e("stringentity", se.toString());
            Log.e("httppostmethod", httpPost.getMethod());
            httpPost.setEntity(se); // Hence the se[0] instead of se ok sir (Y)
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            HttpResponse response = httpClient.execute(httpPost); //execute your request and parse response
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            if(builder.charAt(0) == '{'){
                builder.insert(0,'[');
                builder.append("]");
            }
            jobj = new JSONArray( builder.toString()); //if response in JSON format
            Log.e("bangbang", builder.toString());
            return jobj;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Shit", e.toString());
        }
        return null;
    }
    public void clear(){
        httpPost = null;
    }
}
