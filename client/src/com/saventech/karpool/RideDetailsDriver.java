package com.saventech.karpool;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class RideDetailsDriver extends ActivityGroup implements OnItemClickListener{
	private SharedPreferences mPreferences; 
	Session session;
	private ListView myListView;
	Controller controller;
	ArrayList<HashMap<String,String>> ridedetails;
	SimpleAdapter adapter;
	private String totalride;
	public static RideDetailsDriver driverjourney;
	private ArrayList<View> history; 
	
	public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        this.history = new ArrayList<View>();  
	        driverjourney = this;  
	        refreshRideDetailsDriver();
	 }
	 public void refreshRideDetailsDriver()
	 {
		 View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.common, null);
		 setContentView(viewToLoad);
		 //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        session=new Session();
		    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		    
	        
		    controller=new Controller();
		    String data="";
		    System.out.println(session.getUsername(mPreferences)+"session.getUsername(mPreferences)");
	    	data = controller.userTotalRidedetails(session.getUsername(mPreferences));
		   
	    	String data1[]= data.split("::");
	    	System.out.println("data1: "+data1[0]+" "+data1[1]);
	    	String splitrecords[] = data1[1].split("@");
	    	for(int i=0; i<splitrecords.length; i++)
	    	{
	    		System.out.println("splitrecords: "+ splitrecords[i]);
	    	}
		    System.out.println("data1 length: "+data1.length+"\n splitrecords: "+splitrecords.length);
		    ridedetails = new ArrayList<HashMap<String,String>>();
		    
		    for(int i=1;i<splitrecords.length;i++)
		    {
		    	String temp[] = splitrecords[i].split(",");
		    	System.out.println("temp length: "+temp.length);
		    	System.out.println(" kddk"+temp[0]+"\t"+temp[1]+" to "+temp[2]+" with "+ temp[4]+" seats jid: "+temp[5]);
		    	//ridedetails.add(splitrecords[i].trim());
		    	String temp1 = temp[0]+"\t\t"+temp[1]+" to "+temp[2];
		    	populateList(temp1, temp[3], temp[4], temp[5]);
		    }
	    	if(data.length()==0)
	    	{
	    		data="No route is created/all routes are deleted by you";
	    	}
	    	//context = this.getParent();
		    myListView = (ListView)viewToLoad.findViewById(R.id.myListView);
		    adapter = new SimpleAdapter(this, ridedetails, R.layout.custom_row_view,
		    		new String[] {"route", "time"}, new int[] {R.id.text1,R.id.text2});
		    if(ridedetails.size()!=0)
	        {
		    	myListView.setAdapter(adapter);
	        	//System.out.println("arraylist in acknowledgement: "+DriverJourneyDetails.drivermeteormsg.get(0).toString());        	
	            ((BaseAdapter) adapter).notifyDataSetChanged();
	        }
		    myListView.setOnItemClickListener(new OnItemClickListener() {
	        	
				public void onItemClick(AdapterView<?> a, View v, int position,	long id) 
				{  
					System.out.println("position: "+position);
					HashMap<String, String> temp = new HashMap<String, String>();
					temp = ridedetails.get(position);
					String temp1 = temp.get("route");
					String temp2 = temp.get("time");
					String temp3 = temp.get("seats");
					String temp4 = temp.get("journeyid");
					String[] temp12 = temp1.split("\t\t");
					String[] temp121 = temp12[1].split(" to ");
					System.out.println("route name: "+temp12[0].trim());
					System.out.println("source and destination: "+temp121[0]+" "+temp121[1]);
					System.out.println("seats: "+temp3 +" and journeyid: "+temp4);
					setAlertbox("Edit Route","Cancel Route","Message", position, temp12[0].trim(), 
							temp121[0].trim(), temp121[1].trim(), temp2.trim(), temp3.trim(), temp4.trim());
				}
		    
		    });
	 }
	 public void onResume()
     {
    	super.onResume();
    	refreshRideDetailsDriver();
     }
	 private void populateList(String route, String time, String seats, String journeyid) {
	    	HashMap<String,String> temp = new HashMap<String,String>();
	    	temp.put("route",route);
	    	temp.put("time", time);
	    	temp.put("seats", seats);
	    	temp.put("journeyid", journeyid);
	    	ridedetails.add(temp);
	 }
	 
	 public void setAlertbox(final String msg1,final String msg2,final String title,final int position,final String route,
			 final String source, final String destination, final String time, final String seats, final String jid)
	    {
	    	AlertDialog.Builder adb=new AlertDialog.Builder(DriverJourneyDetails.context1);
	    	//AlertDialog adb = new AlertDialog(RideDetailsDriver.this.getParent());
	  	  	adb.setTitle(title);
	  	  	adb.setPositiveButton(msg1.toString().trim(), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					Toast.makeText(getApplicationContext(),msg1.toString().trim(),Toast.LENGTH_SHORT).show();
					//history.add(v);
					//Intent activity3Intent = new Intent(getApplicationContext(), CancelRoute.class);
					Intent previewMessage = new Intent(getParent(), CancelRoute.class);
		            TabGroupActivity parentActivity = (TabGroupActivity)getParent();
		            
		            previewMessage.putExtra("route", route);
		            previewMessage.putExtra("source", source);
		            previewMessage.putExtra("destination", destination);
		            previewMessage.putExtra("time", time);
		            previewMessage.putExtra("seats", seats);
		            previewMessage.putExtra("journeyid", jid);
		            parentActivity.startChildActivity("CancelRoute", previewMessage);
//					replaceContentView("activity3", activity3Intent);
					/*Window window = getLocalActivityManager().startActivity("rideactivity",
							 activity3Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));  

					// Replace the view of this ActivityGroup  
					replaceView(window.getDecorView());  */
				}
			});
			adb.setNegativeButton(msg2.toString().trim(), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							Toast.makeText(getApplicationContext(),msg2.toString().trim(),Toast.LENGTH_SHORT).show();
							deleteDriverroute("Yes","No","Are you sure you want to cancel the route", position);
							//removeMessage(position);
						}
					});
			AlertDialog alert = adb.create();
			
				alert.show();
	    }
	 public void replaceView(View v) 
	 {  
         // Adds the old one to history  
		 history.add(v);  
         // Changes this Groups View to the new View.  
		 //setContentView(v);  
	 }  

	 public void back() {  
		 if(history.size() > 0) {  
			 history.remove(history.size()-1);  
			 setContentView(history.get(history.size()-1));  
		 }else {  
			 finish();  
		 }  
	 }
	 public boolean onKeyDown(int keyCode, KeyEvent event) 
		{
			if(keyCode == KeyEvent.KEYCODE_BACK)
			{
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}

	 @Override  
	 public void onBackPressed() {  
		 RideDetailsDriver.driverjourney.back();  
		 return;  
	 }  
	 public void deleteDriverroute(final String msg1,final String msg2,final String title, final int position) {
		 AlertDialog.Builder adb=new AlertDialog.Builder(DriverJourneyDetails.context1);
	    	
	  	  	adb.setTitle(title);
	  	  	adb.setPositiveButton(msg1.toString().trim(), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					Toast.makeText(getApplicationContext(),msg1.toString().trim(),Toast.LENGTH_SHORT).show();
					removeMessage(position);	
				}
			});
			adb.setNegativeButton(msg2.toString().trim(), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							Toast.makeText(getApplicationContext(),msg2.toString().trim(),Toast.LENGTH_SHORT).show();
							dialog.cancel();
						}
					});
			adb.show();
			
		}
	public void removeMessage(int position)
    {
		HashMap<String, String> temp = new HashMap<String, String>();
		temp = ridedetails.get(position);
		String temp1 = temp.get("route");
		String temp2 = temp.get("time");
		String[] temp12 = temp1.split("\t\t");
		String[] temp121 = temp12[1].split(" to ");
		System.out.println("temp12: "+temp12[0].trim());
		System.out.println("temp121: "+temp121[0]+" "+temp121[1]);
		controller.Canceldriverroute1(session.getUsername(mPreferences), temp121[0].trim(), temp121[1].trim(), temp2);
    	ridedetails.remove(position);
		 ((BaseAdapter) adapter).notifyDataSetChanged();
		 
		// controller.sendMessage("ADDMESSAGE "+drivername+" "+channelname+"::"+res+"::"+rid.toString());
    }

	public void replaceContentView(String id, Intent newIntent) {
		View view = getLocalActivityManager().startActivity(id,newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)) .getDecorView(); 
		this.setContentView(view);
		}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}        
}