package com.vishen.gocorona;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getData extends AsyncTask<String,Void,Data> {
    String result="";
    String country="";
    String cases="";
    String deaths;
    String worldDeaths;
    String worldCases;
    Data d;
    @Override
    protected Data doInBackground(String... urls) {
        d=new Data();
        URL url;
        HttpURLConnection httpURLConnection=null;
        try {
            url=new URL(urls[0]);
            httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream in=httpURLConnection.getInputStream();
            InputStreamReader reader=new InputStreamReader(in);
            int data=reader.read();
            while(data!=-1){
                result+=(char)data;
                data=reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray=new JSONArray(result);
            JSONObject jsonObject=jsonArray.getJSONObject(jsonArray.length()-1);
            Log.i("JSON Data",jsonObject.toString());
            country=jsonObject.getString("Country");
            cases=jsonObject.getString("Cases");
            deaths=jsonObject.getString("Deaths");
            worldCases=jsonObject.getString("WorldCases");
            worldDeaths=jsonObject.getString("WorldDeaths");
            Log.i("Country and Cases",country+"   "+cases);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    protected void onPostExecute(Data s){
        s.setCountry(country);
        s.setCases(cases);
    }
}