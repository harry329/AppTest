package com.apppartner.androidprogrammertest.jsonparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.apppartner.androidprogrammertest.data.DataClass;


public class DataJSONParser {
	
		public static List<DataClass> parseFeed(String content){
	        try{
	        	
	        JSONObject jb= new JSONObject(content);
	        
	        JSONArray ar= jb.getJSONArray("data");
	        List<DataClass> list = new ArrayList<DataClass>();
	        for(int i=0; i<ar.length(); i++){
	            DataClass mdata = new DataClass();
	            JSONObject obj = ar.getJSONObject(i);
	            mdata.setUserid(obj.getString("user_id"));
	            mdata.setUsername(obj.getString("username"));
	            mdata.setUrl(obj.getString("avatar_url"));
	            mdata.setMessage(obj.getString("message"));
            
	            list.add(mdata);
	        }

	        return list;
	        }   catch (JSONException e){
	            e.printStackTrace();
	            return null;
	        }
		}}
