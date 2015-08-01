package com.apppartner.androidprogrammertest.datafetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.apppartner.androidprogrammertest.R;

import android.content.Context;



public class DataFetch {
	Context context;
	public DataFetch(Context context){
		this.context= context;
	}
	
	public String dataFetch(){
		StringBuilder sb= new StringBuilder();
		BufferedReader bf;
		String line;		
		bf= new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.chat_data)));
		try {
//			while(bf.read()!= -1){
//				sb.append(bf.readLine());
//			}
//			
			 while ((line = bf.readLine()) != null){
	                sb.append(line);
	            }

			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
            if (bf == null) {
            } else {
                try{
                    bf.close();
                }catch (Exception e){
                    e.printStackTrace();

                }}}
	}}
