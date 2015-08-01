package com.apppartner.androidprogrammertest;

import java.util.List;

import com.apppartner.androidprogrammertest.adapter.DataAdapter;
import com.apppartner.androidprogrammertest.data.DataClass;
import com.apppartner.androidprogrammertest.datafetch.DataFetch;
import com.apppartner.androidprogrammertest.datatransfer.DataTransfer;
import com.apppartner.androidprogrammertest.jsonparser.DataJSONParser;




import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends ActionBarActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		ListView ll= (ListView) findViewById(R.id.listView1);
		DataAdapter dataAdapter= new DataAdapter(this,R.layout.item_layout,DataTransfer.getList());
		ll.setAdapter(dataAdapter);
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
	}
	
	

