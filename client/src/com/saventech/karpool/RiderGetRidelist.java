package com.saventech.karpool;

import java.io.UnsupportedEncodingException;
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
import android.util.Base64;
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
	private ImageButton sendrequest;
	String checkboxesclicked="";
	String getcheckboxesclicked="";
	

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);	        

	        drawUI();
	}
	 
	 public void drawUI()
	 {
		 setContentView(R.layout.ridelist0);
		 controller=new Controller();

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
				if(session.ischeckboxesclicked(mPreferences))
				{
					getcheckboxesclicked=session.getCheckBoxesClicked(mPreferences);
				}
				else
				{
					 SharedPreferences.Editor editor=mPreferences.edit();
					 editor.putString("checkboxesclicked", checkboxesclicked);
					 editor.commit();
				}
				System.out.println(session.getUsername(mPreferences)+"-----"+session.getPassword(mPreferences)+ridelistdetails.size()+"  *****************");
				sendrequest=(ImageButton)findViewById(R.id.sendrequest);
				
				LinearLayout l = (LinearLayout) findViewById(R.id.mylayout1);
		        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        String toaststring="";
		        if(ridelistdetails.size()!=0)
		        {
		        	for(int k=0;k<ridelistdetails.size();k++)
		        	{
			        	String records[]=ridelistdetails.get(k).toString().split("KRL");
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
				            ImageButton img=(ImageButton)customView.findViewById(R.id.image);
				            if(k==0)
				            {
				            	route.setText("   Route:"+records[1].toString().trim()+" to "+records[2].toString().trim());
					            rate.setText("   Rate:");
					            String imagebyte = records[records.length-1];
					            byte[] decodedString = Base64.decode(imagebyte, Base64.DEFAULT);
					            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
					            img.setImageBitmap(decodedByte);
					            System.out.println(records.length+"             ************************************8");
					          // bm=retriveImagefromstring(records[records.length-1].toString());
				            }
				            else
				            {
				            	route.setText("   Route:"+records[0].toString().trim()+" to "+records[1].toString().trim());
					            rate.setText("   Rate:");
					            String imagebyte = records[records.length-1];
					            byte[] decodedString = Base64.decode(imagebyte, Base64.DEFAULT);
					            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
					            img.setImageBitmap(decodedByte);
					            System.out.println(records[records.length-1]+"             ************************************88");
					          // bm=retriveImagefromstring(records[records.length-1].toString());
				            }
				            
				           // System.out.println(imgbyte.length);
				            
				            //findViewById(R.id.image).setOnClickListener(this);
				           
				            img.setOnClickListener(this);
				            CheckBox check=(CheckBox)customView.findViewById(R.id.ridelistcheckbox);
				           // img.setImageBitmap(bm);

				            // Bitmap bmp=BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length
				           //img.setImageDrawable(getResources().getDrawable(R.drawable.image1));            
				            //setting ids to all widgets in the custome list view
				            route.setId(k);
				            rate.setId(k);
				            check.setId(k);
				            check.setOnClickListener(new View.OnClickListener() {
				            
				            	 public void onClick(View v) {
						            	//Toast.makeText(RiderGetRidelist.this, "Pressed checkbutton", Toast.LENGTH_LONG).show();
						            	checkbuttonid(v);
						               // showDialog(DATE_DIALOG_ID);
						            }
						        });
				            
				            img.setId(k+ridelistdetails.size());
				           
				            
				            
				            l.addView(customView); 
		        	}
		        }
		        sendrequest.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) 
		            {
		            	System.out.println("Pressed send request button");
		            	Toast.makeText(RiderGetRidelist.this, "Pressed send request button", Toast.LENGTH_LONG).show();
		            	//sendrequest(v);
		               // showDialog(DATE_DIALOG_ID);
		            }
		        });
		        
		 
	 }
	 
	 
	 public void sendrequest(View v)
	 {
		 checkboxesclicked=checkboxesclicked+getcheckboxesclicked;
		 session.saveCheckBoxesClicked(mPreferences, checkboxesclicked);
		 checkboxesclicked="";
		 
		 
	 }
	 public void checkbuttonid(View v)
	 {
		 if(ridelistdetails.size()!=0)
	        {
	        	for(int k=0;k<ridelistdetails.size();k++)
	        	{
	        		if(v.getId()==k)
	        		{
	        			String checkrecords[]=ridelistdetails.get(k).toString().trim().split("KRL");
	        			for(int j=0;j<checkrecords.length;j++)
						{
							if(k==0 && j==0)
							{
								checkboxesclicked=checkboxesclicked+checkrecords[3]+":";
								break;
							}
							else
							{
								checkboxesclicked=checkboxesclicked+checkrecords[2]+":";
								break;
							}
							
						}
	        			
	        		}
	        	}
	        	//Toast.makeText(RiderGetRidelist.this, "checkbox data is: "+checkboxesclicked, Toast.LENGTH_LONG).show();
	        	
	        }
		 
		 //session.saveCheckBoxesClicked(mPreferences, checkboxesclicked);
		 
	 }
	 
	 @Override
		public void onResume() {
			super.onResume();
			drawUI();
			
			
		}
	 
	 public static Bitmap retriveImagefromstring(String data)
	 {
		 Bitmap bm = null; 
		 try {
			byte[] b=data.getBytes(data);
			bm = BitmapFactory.decodeByteArray(b, 0, b.length); 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return bm;
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
	 	        		alert.setTitle("Information");
	 					final TextView input = new TextView(this);
	 					
	 		        	String records[]=ridelistdetails.get(k).toString().split("KRL");
	 					for(int j=0;j<records.length;j++)
	 					{
	 				        if(t==k)
	 				        {
	 					
		 						if(k==0)
		 						{
		 							input.setText("\tName\t\t\t:"+records[3]+"\n\tSource\t\t:"+records[1]+"\n\tDestination  :"+records[2]+"\n\tMobile\t\t:"+records[6]+"\n");
		 						}
		 						else
		 						{
		 							input.setText("\tName\t\t\t:"+records[2]+"\n\tSource\t\t:"+records[0]+"\n\tDestination  :"+records[1]+"\n\tMobile\t\t:"+records[5]+"\n");
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
	           
	    		
	    		
	    	}
	        
	    }
