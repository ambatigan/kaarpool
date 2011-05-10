package com.saventech.karpool;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RideHistory extends Activity implements OnItemClickListener {
	String value="";
	private SharedPreferences mPreferences; 
	Session session;
	private ListView myListView;
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    setContentView(R.layout.ridehistory);
    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
    
    final TextView leftText = (TextView) findViewById(R.id.left_text);
    final TextView rightText = (TextView) findViewById(R.id.right_text);

    leftText.setText("kaarpool");
    
    session=new Session();
    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
    String username = session.getUsername(mPreferences);
    String name[]= username.split("@");
    rightText.setText(name[0]);
    
    myListView = (ListView) findViewById(R.id.myListView);
    
   // String[] rideHistory = getResources().getStringArray(R.array.data_array);
    
    myListView.setAdapter(new ArrayAdapter(this,R.layout.rows,R.id.text, More.ridehistory));
//    setListAdapter(new ArrayAdapter<String>(this,
//            R.layout.ridehistory,R.id.ride, rideHistory));
//    getListView().setBackgroundResource(R.drawable.radialback);
//   
//    getListView().setTextFilterEnabled(true);
    
    myListView.setOnItemClickListener(new OnItemClickListener() {
    	public void onItemClick(AdapterView<?> a, View v, int position,	long id) {
    		
			switch(position)
			{
//			case 1: 
//				AlertDialog.Builder builder = new AlertDialog.Builder(this);
//				builder.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
//				       .setCancelable(false)
//				       
//				       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//				           public void onClick(DialogInterface dialog, int id) {
//				        	   //RideHistory.this.finish();
//				           }
//				       });
//				       
//				AlertDialog alert = builder.create();
//				alert.show();
//				break;
//			case 2:
//				AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//				builder1.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
//				       .setCancelable(false)
//				       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//				           public void onClick(DialogInterface dialog, int id) {
//				        	   //RideHistory.this.finish();
//				           }
//				       });
//				       
//				AlertDialog alert1 = builder1.create();
//				alert1.show();
//				break;
//			case 3:
//	           AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
//				builder2.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
//			       .setCancelable(false)
//			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//			           public void onClick(DialogInterface dialog, int id) {
//			        	  // RideHistory.this.finish();
//			           }
//			       });
//			       
//				AlertDialog alert2 = builder2.create();
//				alert2.show();
//				break;
//			case 4:
//				
//				AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
//				builder3.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
//			       .setCancelable(false)
//			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//			           public void onClick(DialogInterface dialog, int id) {
//			        	  // RideHistory.this.finish();
//			           }
//			       });
//			       
//				AlertDialog alert3 = builder3.create();
//				alert3.show();
//				break;
			}}
    }	
	    );
    }
   
    
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			More.ridehistory=new ArrayList<String>();
			//return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}



    
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//    	super.onListItemClick(l, v, position, id);
//    	
//    	switch(position)
//		{
//		case 1: 
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
//			       .setCancelable(false)
//			       
//			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//			           public void onClick(DialogInterface dialog, int id) {
//			        	   //RideHistory.this.finish();
//			           }
//			       });
//			       
//			AlertDialog alert = builder.create();
//			alert.show();
//			break;
//		case 2:
//			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//			builder1.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
//			       .setCancelable(false)
//			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//			           public void onClick(DialogInterface dialog, int id) {
//			        	   //RideHistory.this.finish();
//			           }
//			       });
//			       
//			AlertDialog alert1 = builder1.create();
//			alert1.show();
//			break;
//		case 3:
//           AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
//			builder2.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
//		       .setCancelable(false)
//		       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//		           public void onClick(DialogInterface dialog, int id) {
//		        	  // RideHistory.this.finish();
//		           }
//		       });
//		       
//			AlertDialog alert2 = builder2.create();
//			alert2.show();
//			break;
//		case 4:
//			
//			AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
//			builder3.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
//		       .setCancelable(false)
//		       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//		           public void onClick(DialogInterface dialog, int id) {
//		        	  // RideHistory.this.finish();
//		           }
//		       });
//		       
//			AlertDialog alert3 = builder3.create();
//			alert3.show();
//			break;
//		}
//    		
//    }
//    
//}