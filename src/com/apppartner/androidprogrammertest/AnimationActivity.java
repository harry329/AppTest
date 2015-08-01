package com.apppartner.androidprogrammertest;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AnimationActivity extends ActionBarActivity {
	ImageView button;
	ImageView icon;
	Handler handler;
	private static final String IMAGEVIEW_TAG = "Android Logo";
	String msg;
	RelativeLayout rel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		handler= new Handler();
		setContentView(R.layout.activity_animation);
		button= (ImageView) findViewById(R.id.imageView1);
		icon= (ImageView) findViewById(R.id.imageView2);
		icon.setTag(IMAGEVIEW_TAG);
		rel= (RelativeLayout) findViewById(R.id.relative);
		icon.setOnLongClickListener(new View.OnLongClickListener(){

			@Override
			public boolean onLongClick(View v) {
				ClipData.Item item= new ClipData.Item((CharSequence) v.getTag());
				String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
	            ClipData dragData = new ClipData(v.getTag().toString(), 
	            mimeTypes, item);
	            DragShadowBuilder myShadow = new DragShadowBuilder(icon);
	            icon.startDrag(dragData, myShadow, icon, 0);
	            icon.setVisibility(View.INVISIBLE);
				return true;
			}
			
		});
		
		rel.setOnDragListener(new OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
				switch(event.getAction())                   
		         {
		            case DragEvent.ACTION_DRAG_STARTED:

		               break;
		            case DragEvent.ACTION_DRAG_ENTERED:
       
		               break;
		            case DragEvent.ACTION_DRAG_EXITED :

		               break;
		            case DragEvent.ACTION_DRAG_LOCATION  :
		               break;
		            
		            case DragEvent.ACTION_DRAG_ENDED   :
		               Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
		               
		               break;
		            case DragEvent.ACTION_DROP:
		            	View view= (View) event.getLocalState();
		            	((ViewGroup) v).removeView(view);
		            	int xCoord = (int) event.getX();
		                int yCoord = (int) event.getY();
		                int windowWidth = getWindowManager().getDefaultDisplay().getWidth();
		                int windowHeight = getWindowManager().getDefaultDisplay().getHeight();
		                    if (xCoord > windowWidth) {
		                        xCoord = windowWidth/2;
		                    }
		                    if (yCoord > windowHeight) {
		                        yCoord = windowHeight/2;
		                    }
		            	RelativeLayout containView= (RelativeLayout) v;
		            	view.setX((float)xCoord/2);
		            	view.setY((float)yCoord/2);
		            	containView.addView(view);
		            	view.setVisibility(View.VISIBLE);
		               Log.d(msg, "ACTION_DROP event");
		              
		               break;
		            default: break;
		            }
		            return true;
			}
		});

		
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
	@SuppressLint("NewApi") public void onFade(View v){
		icon.setImageAlpha(0);
		handler.postDelayed(new Runnable() {
		    @SuppressLint("NewApi") @Override
		    public void run() {
		     icon.setImageAlpha(255);   
		    }
		}, 1000);}
	
	 
	
}
