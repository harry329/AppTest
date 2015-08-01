package com.apppartner.androidprogrammertest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {
	EditText username;
	EditText password;
	HttpResponse response;
	String code,message;
	long elapsedTime;
	AlertDialog alert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username=(EditText) findViewById(R.id.editText3);
		password=(EditText) findViewById(R.id.editText4);
		  ActionBar actionBar = getSupportActionBar();
		    actionBar.setHomeButtonEnabled(true);
		    actionBar.setDisplayHomeAsUpEnabled(true);
		
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; goto parent activity.
	            this.finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void onLogin(View v){
		LoginAsync logasyn= new LoginAsync();
		logasyn.execute();
	}
	public void alertShow(){
		alert=new AlertDialog.Builder(this)
	    .setTitle("Response")
	    .setMessage("The code is "+ this.code+ ", " +"the message is " + this.message+ ",  & " + "the time taken "
	    		+ this.elapsedTime+" ms")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            Intent in= new Intent(getBaseContext(),MainActivity.class);
	            startActivity(in);
	        }
	     }).show();
	}

	class LoginAsync extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... params) {
		     HttpClient client = new DefaultHttpClient();
             HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
             
             JSONObject json = new JSONObject();

             try {
            	 URL url = new URL("http://dev.apppartner.com/AppPartnerProgrammerTest/scripts/login.php");
                 HttpPost post = new HttpPost(url.toURI());
                 json.put("username", username.getText().toString());
                 json.put("password", password.getText().toString());
                 StringEntity se = new StringEntity(json.toString());  
                 se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                 post.setEntity(se);
                 long startTime = System.currentTimeMillis();
                 response = client.execute(post);

                 /*Checking response */
                 if(response!=null){
                     InputStream in = response.getEntity().getContent(); //Get the data in the entity
                     elapsedTime = System.currentTimeMillis() - startTime;
                     BufferedReader bf= new BufferedReader(new InputStreamReader(in));
                     StringBuilder sb= new StringBuilder();
                     String st;
                     while((st=bf.readLine())!=null){
                    	 sb.append(st);
                     }
                     
                     JSONObject jsonObject= new JSONObject(sb.toString());
                     code=jsonObject.getString("code");
                     message=jsonObject.getString("message");
                 }

             } catch(Exception e) {
                 e.printStackTrace();
                 
             }
			return null;
		}
		protected void onPostExecute(Void unused){
			alertShow();
		}
		
	}
	
}
