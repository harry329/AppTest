package com.apppartner.androidprogrammertest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.apppartner.androidprogrammertest.data.DataClass;
import com.apppartner.androidprogrammertest.datafetch.DataFetch;
import com.apppartner.androidprogrammertest.datatransfer.DataTransfer;
import com.apppartner.androidprogrammertest.jsonparser.DataJSONParser;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	TextView tv;
	private List<DataClass> datalist;
	String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView)findViewById(R.id.textView1);
        Typeface tf= Typeface.createFromAsset(this.getAssets(), "fonts/Jelloween - Machinato Bold.ttf");
        tv.setTypeface(tf);
        MyTask myTask= new MyTask();
		myTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onChat(View v){
    	Intent in= new Intent(this,ChatActivity.class);
    	startActivity(in);
    	
    	
    }
    public void onLogin(View v){
    	Intent in2= new Intent(this,LoginActivity.class);
    	startActivity(in2);
    	
    	
    }
    public void onAnimation(View v){
    	Intent in3= new Intent(this,AnimationActivity.class);
    	startActivity(in3);
    	
    	
    }
    class MyTask extends AsyncTask<Void,Void,List<DataClass>>{

		@Override
		protected List<DataClass> doInBackground(Void... params) {
			// TODO Auto-generated method stub
		//	 df= new DataFetch(getBaseContext());
			DataFetch df= new DataFetch(getApplicationContext());
			 ip = df.dataFetch();
			datalist= DataJSONParser.parseFeed(ip);
			DataTransfer tf= new DataTransfer(datalist);
            InputStream is;
            for(int i= 0; i<datalist.size();i++){
			try {
				is = (InputStream) new URL(datalist.get(i).getUrl()).openStream();
				Bitmap bitmap = BitmapFactory.decodeStream(is);
				datalist.get(i).setImage(bitmap);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}    return datalist;}
		protected void onPostResult(List<DataClass> result){
			if(result != null){
			datalist=result;}
			else{
				
				datalist.add(new DataClass());
			}
		}
    }
}
