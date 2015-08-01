package com.apppartner.androidprogrammertest.adapter;

import java.util.List;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.data.DataClass;

import android.app.Activity;
import android.content.Context;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<DataClass> {
	private Context context;
	private List<DataClass> datalist;
	
	
	public DataAdapter(Context context, int resource,List<DataClass> object) {
		super(context, resource,object);
		this.context= context;
		this.datalist= object;
	}
	public Bitmap getRoundedBitmap(Bitmap bitmap,int pixels){
		  Bitmap result = null;
		    try {
		        result = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
		        Canvas canvas = new Canvas(result);

		        int color = 0xff424242;
		        Paint paint = new Paint();
		        Rect rect = new Rect(0, 0, 200, 200);

		        paint.setAntiAlias(true);
		        canvas.drawARGB(0, 0, 0, 0);
		        paint.setColor(color);
		        canvas.drawCircle(60, 60, 50, paint);
		        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		        canvas.drawBitmap(bitmap, rect, rect, paint);

		    } catch (NullPointerException e) {
		    } catch (OutOfMemoryError o) {
		    }
		    return result;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row=convertView;
        ViewHolder holder= null;
        if(row==null) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.item_layout,parent,false);
        holder= new ViewHolder(row);
        row.setTag(holder);
       }else{
           holder= (ViewHolder) row.getTag();
       }
        DataClass mdata=datalist.get(position);
        holder.tv1.setText( mdata.getUsername());
        holder.tv2.setText( mdata.getMessage());
        Typeface tf= Typeface.createFromAsset(context.getAssets(), "fonts/Jelloween - Machinato.ttf");
        holder.tv1.setTypeface(tf);
        Typeface tf1= Typeface.createFromAsset(context.getAssets(), "fonts/Jelloween - Machinato Light.ttf");
        holder.tv2.setTypeface(tf1);
        holder.imv.setImageBitmap(getRoundedBitmap(mdata.getImage(),100));
        return row;
       }
	
	 class ViewHolder{
	        TextView tv1;
	        TextView tv2;
	        ImageView imv;
	        ViewHolder(View v){
	        	tv1= (TextView)  v.findViewById(R.id.textView1);
	        	tv2= (TextView)  v.findViewById(R.id.textView2);
	        	imv=(ImageView)  v.findViewById(R.id.imageView1);
	        
	        }
	    }
}
