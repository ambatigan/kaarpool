package com.saventech.karpool;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderGetRidelist.java
 * Date: Mar 25, 2011
 * Description: It is responsible to display the ridelist to the rider based on the specified values
 */
public class RiderGetRidelist extends Activity implements android.view.View.OnClickListener {
	private SharedPreferences mPreferences; 
	Session session;
	Controller controller;
	ArrayList<String> ridelistdetails=new ArrayList<String>();
	
	

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);	        
	        drawUI();
	        /*controller=new Controller();
	        Log.i("Ridergetridelist_Activity","Entered in Ridergetridelist activity");
	        session=new Session();
	        ridelistdetails=RiderJourneyDetails.ridelist;
		    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
			if(!session.checkinfo(mPreferences))
			{
				Intent intent=new Intent(RiderGetRidelist.this,Login.class);
				startActivity(intent);
			
			}
			System.out.println(session.getUsername(mPreferences)+"-----"+session.getPassword(mPreferences)+ridelistdetails.size()+"  *****************");
	        LinearLayout l = (LinearLayout) findViewById(R.id.mylayout1);
	        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        String toaststring="";
	        if(ridelistdetails.size()!=0 && JourneyDetails.ridelist1.size()!=0)
	        {
	        	for(int k=0;k<ridelistdetails.size();k++)
	        	{
		        	String records[]=ridelistdetails.get(k).toString().split("KPL");
					for(int j=0;j<records.length;j++)
					{
						if(k==0 && j==0)
						{
							
						}
						else
						{
						  toaststring =toaststring+records[j]+" ";
						}
					}
					toaststring=toaststring+"\n";
					View customView = linflater.inflate(R.layout.ridelist, null);
			            TextView route = (TextView) customView.findViewById(R.id.route);
			            TextView rate = (TextView) customView.findViewById(R.id.rate);
			            if(k==0)
			            {
			            	route.setText("   Route:"+records[1].toString().trim()+" to "+records[2].toString().trim());
				            rate.setText("   Rate:");
			            }
			            else
			            {
			            	route.setText("   Route:"+records[0].toString().trim()+" to "+records[1].toString().trim());
				            rate.setText("   Rate:");
			            }
			            
			           // System.out.println(imgbyte.length);
			            
			            //findViewById(R.id.image).setOnClickListener(this);
			            ImageButton img=(ImageButton)customView.findViewById(R.id.image);
			            img.setOnClickListener(this);
			            CheckBox check=(CheckBox)customView.findViewById(R.id.ridelistcheckbox);
			            // Bitmap bmp=BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length
			            img.setImageDrawable(getResources().getDrawable(R.drawable.image1));            
			            //setting ids to all widgets in the custome list view
			            route.setId(k);
			            rate.setId(k);
			            check.setId(k);
			            img.setId(k+ridelistdetails.size());
			           
			            
			            
			            l.addView(customView);   //adding customview to list
	        	}
	        }
	        
	        Toast.makeText(RiderGetRidelist.this, toaststring, Toast.LENGTH_LONG).show();*/
	        //Loop to display a custom list view
	        
//	        for (int i = 0; i < 4; i++) 
//	        {
//	            View customView = linflater.inflate(R.layout.ridelist,
//	                null);
//	            TextView route = (TextView) customView.findViewById(R.id.route);
//	            TextView rate = (TextView) customView.findViewById(R.id.rate);
//	            route.setText("   Route: IIIT to Secrateriate");
//	            rate.setText("   Rate:");
//	           // System.out.println(imgbyte.length);
//	            
//	            //findViewById(R.id.image).setOnClickListener(this);
//	            ImageButton img=(ImageButton)customView.findViewById(R.id.image);
//	            img.setOnClickListener(this);
//	            CheckBox check=(CheckBox)customView.findViewById(R.id.ridelistcheckbox);
//	           // Bitmap bmp=BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
//	           
//
//	            //setting different images based on the value
//	            switch(i)
//	            {
//	            case 0:
//	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image1));
//	            	break;
//	            case 1:
//	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image2));
//	            	break;
//	            case 2:
//	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image3));
//	            	break;
//	            case 3:
//	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image4));
//	            	break;
//	            }
//	            
//	            //setting ids to all widgets in the custome list view
//	            route.setId(i);
//	            rate.setId(i);
//	            check.setId(i);
//	            img.setId(i);
//	           
//	            
//	            
//	            l.addView(customView);   //adding customview to list
//	        }
	        }
	 
	 
	 
	 @Override
	 public void onStart()
	 {
	     super.onStart();
	     System.out.println("=======================================================================================");
	     drawUI();
	 }
	 
	 public void drawUI()
	 {
		 setContentView(R.layout.ridelist0);
		 controller=new Controller();
	        Log.i("Ridergetridelist_Activity","Entered in Ridergetridelist activity");
	        session=new Session();
	        ridelistdetails=RiderJourneyDetails.ridelist;
		    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
			if(!session.checkinfo(mPreferences))
			{
				Intent intent=new Intent(RiderGetRidelist.this,Login.class);
				startActivity(intent);
			
			}
			System.out.println(session.getUsername(mPreferences)+"-----"+session.getPassword(mPreferences)+ridelistdetails.size()+"  *****************");
	        LinearLayout l = (LinearLayout) findViewById(R.id.mylayout1);
	        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        String toaststring="";
	        if(ridelistdetails.size()!=0 && JourneyDetails.ridelist1.size()!=0)
	        {
	        	for(int k=0;k<ridelistdetails.size();k++)
	        	{
		        	String records[]=ridelistdetails.get(k).toString().split("KPL");
					for(int j=0;j<records.length;j++)
					{
						if(k==0 && j==0)
						{
							
						}
						else
						{
						  toaststring =toaststring+records[j]+" ";
						}
					}
					toaststring=toaststring+"\n";
					View customView = linflater.inflate(R.layout.ridelist, null);
			            TextView route = (TextView) customView.findViewById(R.id.route);
			            TextView rate = (TextView) customView.findViewById(R.id.rate);
			            if(k==0)
			            {
			            	route.setText("   Route:"+records[1].toString().trim()+" to "+records[2].toString().trim());
				            rate.setText("   Rate:");
			            }
			            else
			            {
			            	route.setText("   Route:"+records[0].toString().trim()+" to "+records[1].toString().trim());
				            rate.setText("   Rate:");
			            }
			            
			           // System.out.println(imgbyte.length);
			            
			            //findViewById(R.id.image).setOnClickListener(this);
			            ImageButton img=(ImageButton)customView.findViewById(R.id.image);
			            img.setOnClickListener(this);
			            CheckBox check=(CheckBox)customView.findViewById(R.id.ridelistcheckbox);
			            // Bitmap bmp=BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length
			            img.setImageDrawable(getResources().getDrawable(R.drawable.image1));            
			            //setting ids to all widgets in the custome list view
			            route.setId(k);
			            rate.setId(k);
			            check.setId(k);
			            img.setId(k+ridelistdetails.size());
			           
			            
			            
			            l.addView(customView);   //adding customview to list
	        	}
	        }
	        
	        Toast.makeText(RiderGetRidelist.this, toaststring, Toast.LENGTH_LONG).show();
	             // Do stuff in here such as querying for contacts
	     }
	 public boolean onKeyDown(int keyCode, KeyEvent event) 
		{
			if(keyCode == KeyEvent.KEYCODE_BACK)
			{
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
	 
	        public void onClick(View V)      //onClick will be performed when button is pressed
	    	{
	        	int t=V.getId()-ridelistdetails.size();
	        	 Log.i("Pressed_0", "Driver image has been pressed");
			    
	        	 if(ridelistdetails.size()!=0)
	 	        {
	 	        	for(int k=0;k<ridelistdetails.size();k++)
	 	        	{
	 	        		final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
	 					final TextView input = new TextView(this);
	 		        	String records[]=ridelistdetails.get(k).toString().split("KPL");
	 					for(int j=0;j<records.length;j++)
	 					{
	 				        if(t==k)
	 				        {
	 					
		 						if(k==0)
		 						{
		 							input.setText("\tName\t\t\t:"+records[3]+"\n\tSource\t\t:"+records[1]+"\n\tDestination  :"+records[2]+"\n");
		 						}
		 						else
		 						{
		 							input.setText("\tName\t\t\t:"+records[2]+"\n\tSource\t\t:"+records[0]+"\n\tDestination  :"+records[1]+"\n");
		 						}
	 				        }
	 					
	 					}
	 					if(t==k)
	 					{
		 					alert.setView(input);
							
							alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									
									return;
								}
							});
							
								alert.show();
	 					}
	 	        	}
	 	        }
	            //displaying various alert messages based on the pressed image
//	    		switch(V.getId())
//	    		{
//	    		  case t:
			   
					
					
					
//	    				break;
//	    		case 1:
//	    			 Log.i("Pressed_1", "Driver image has been pressed");
//	    			final AlertDialog.Builder alert1 = new AlertDialog.Builder(this.getParent());
//	    			final TextView input1 = new TextView(this);
//	    			input1.setText("\tName\t\t\t:Nagesh\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n");
//	    			alert1.setView(input1);
//	    			
//	    			alert1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//	    				public void onClick(DialogInterface dialog, int whichButton) {
//	    					
//	    					return;
//	    				}
//	    			});
//	    			alert1.show();
//	    				break;
//	    		case 2:
//	    			 Log.i("Pressed_2", "Driver image has been pressed");
//	    			final AlertDialog.Builder alert2 = new AlertDialog.Builder(this.getParent());
//	    			final TextView input2 = new TextView(this);
//	    			input2.setText("\tName\t\t\t:Mahesh\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n");
//	    			alert2.setView(input2);
//	    			
//	    			alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//	    				public void onClick(DialogInterface dialog, int whichButton) {
//	    					
//	    					return;
//	    				}
//	    			});
//	    			alert2.show();
//	    				break;
//	    		case 3:
//	    			 Log.i("Pressed_3", "Driver image has been pressed");
//	    			final AlertDialog.Builder alert3 = new AlertDialog.Builder(this.getParent());
//	    			final TextView input3 = new TextView(this);
//	    			input3.setText("\tName\t\t\t:Somesh\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n");
//	    			alert3.setView(input3);
//	    			
//	    			alert3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//	    				public void onClick(DialogInterface dialog, int whichButton) {
//	    					
//	    					return;
//	    				}
//	    			});
//	    			alert3.show();
//	    				break;
//	    		}
	    		
	    		
	    	}
	        
	    }
