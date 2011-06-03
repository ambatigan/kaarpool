/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Newroute.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.deacon.Deacon;
import org.deacon.DeaconError;
import org.deacon.DeaconObserver;
import org.deacon.DeaconResponse;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.saventech.karpool.DateTimePicker;

public class Newroute extends Activity implements OnClickListener, DeaconObserver{
	
	private String value="";
	private int DRIVER_MODE=1;
	private String datetime="";
	private String mode="driver";
	private Button newroute;
	Controller controller;
	private boolean checknewrouteflag;
	private Button driverjourneysettime;
	private EditText driverjourneyedittime;
	private EditText rn;
	private EditText ed;
	private EditText ed1;
	private EditText seatid;
	private SharedPreferences mPreferences; 
	Session session;
	Validations validate;
	private String ip = "";
	private int port;
	private static String driverusername="";
	
	private static Deacon deacon;
	PrintWriter outToServer;
	static String channelname;
	public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        Log.i("DriverJourneyDetails_New route", "New route tab in DriverJourneyDetails");
       // drawUI();
               
        //Meteor connectivity
        
        
    }
    
    
    public void drawUI()
    {
    	 session=new Session();
         validate=new Validations();
 	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
 		if(!session.checkinfo(mPreferences))
 		{
 			Intent intent=new Intent(Newroute.this,Login.class);
 			startActivity(intent);
 		
 		}
 		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
         setContentView(R.layout.drivernewroute);
         controller=new Controller();
         session.storemode(mPreferences, "driver");
         Button change1 = (Button) findViewById(R.id.change1);
         change1.setOnClickListener(this);
         
         /** We need to set up a click listener on the change2 button */
         Button change2 = (Button) findViewById(R.id.change2);
         change2.setOnClickListener(this);
         
         newroute=(Button)findViewById(R.id.drivernewrouteregsubmit);
         driverjourneysettime=(Button)findViewById(R.id.driverjourneysettime);
         driverjourneysettime.setOnClickListener(this);
         driverjourneyedittime=(EditText)findViewById(R.id.driverjourneyedittime);
         driverjourneyedittime.setEnabled(false);
         rn =(EditText)findViewById(R.id.routenameid);
         ed = (EditText)findViewById(R.id.sourceid);
         ed1 = (EditText)findViewById(R.id.destinationid);
         seatid = (EditText)findViewById(R.id.seatid);
         ed.setEnabled(false);
         ed1.setEnabled(false);        
         newroute.setOnClickListener(this);
         
         
         try 
         {
         	ip = getString(R.string.MeteorIP);
         	port=Integer.parseInt(getString(R.string.SubscriberPort));
         	driverusername=parseChannelName(session.getUsername(mPreferences));
         	System.out.println("Driver   "+JourneyDetails.dflag+"   dflag value"+JourneyDetails.rflag+"  rflag");
         	
         	if(JourneyDetails.rflag!=0)
        	{
        		System.out.println("Rider channel  Closeddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        		//deacon.leaveChannel(parseChannelName(session.getUsername(mPreferences)) );
        		RiderRoute.stopdeacon();
        		JourneyDetails.rflag=0;
        	}
         	if(JourneyDetails.dflag==0)
 			{
         		String changemoderesponse=controller.modeChange(session.getUsername(mPreferences).toString().trim(), DRIVER_MODE);
				    if(changemoderesponse.toString().trim().equals("Success"))
				    {
				    	System.out.println("Newroute: Mode has been changed");
				    }
 			   try {
 					this.deacon = new Deacon(ip.toString().trim(),port, this);
 					System.out.println("Driver decons is created");
	 				if(!deacon.isRunning())
	 				{
	 					    
	 		        		deacon.catchUpTimeOut(60);
	 		            	deacon.register(this);
	 		            	System.out.println("Driver channel is running");
	 						//deacon.leaveChannel(parseChannelName(session.getUsername(mPreferences)));
	 						deacon.joinChannel("d"+parseChannelName(session.getUsername(mPreferences)), 0);
	 						deacon.start();
	 						
	 					
	 				}
	 				JourneyDetails.dflag=1;
	 				} catch (Exception e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
 			}
         } 
         catch (Exception e) 
         {
         	System.out.println("Problem while creating Deacon");
         	e.printStackTrace();
         }
    }
    @Override
	public void onResume() {
		super.onResume();
		drawUI();
		
		
	}
    
    public boolean checkMobiletime(String rideSeekingTime)
    {
    	
    	Calendar today=Calendar.getInstance();
		System.out.println(today.getTimeInMillis()+"kkkkkkkkkkkkkkkkkk"+today.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd h:mm a");
		  Date d = null;
		  try {
			   d = formatter.parse(rideSeekingTime.toString().trim());//catch exception
			  // System.out.println(d.getHours()+"  hhhhhhhhhhhhhh");
			   Calendar thatDay = Calendar.getInstance();
			   thatDay.setTime(d);
			   //System.out.println(thatDay.getTimeInMillis()+"  lllllllllllllllllllll"+d+" "+rideSeekingTime.toString().trim());
			   long dmil=thatDay.getTimeInMillis()-today.getTimeInMillis();	
			   System.out.println(dmil+" Minutes");
			   if( dmil/(60*1000)<=15)
			   {
					return true;
			   }
		  } catch (ParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		   Log.i("RiderRoute_checkMobiletime", "Exception occure while validating time in rider rotue");
		   return false;
		  } 
		  
	    return false;
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
    /**
     *  The following methods are used to change source and destination of 
     *  particular ride
     */
    public void changeSource(View view)
    {
    	Log.i("Newroute_changeSource", "change button pressed to change source location");
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
    	alert.setTitle("Source");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value = input.getText().toString().trim();
				//Toast.makeText(getApplicationContext(), value,Toast.LENGTH_SHORT).show();
				
		    	ed.setText(value);
			}
		});
		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		alert.show();
    }
    
    public void changeDestination(View view)
    {
    	
    	Log.i("Newroute_changeSource", "change button pressed to change destination location");
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
    	alert.setTitle("Destination");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value = input.getText().toString().trim();
				//Toast.makeText(getApplicationContext(), value,Toast.LENGTH_SHORT).show();
				
		    	ed1.setText(value);
			}
		});
		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		alert.show();
    }
    private void showDateTimeDialog() {
		// Create the dialog
		final Dialog mDateTimeDialog = new Dialog(this.getParent());
		// Inflate the root layout
		final RelativeLayout mDateTimeDialogView = (RelativeLayout) getLayoutInflater().inflate(R.layout.date_time_dialog, null);
		// Grab widget instance
		final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);
		// Check is system is set to use 24h time (this doesn't seem to work as expected though)
		final String timeS = android.provider.Settings.System.getString(getContentResolver(), android.provider.Settings.System.TIME_12_24);
		final boolean is24h = !(timeS == null || timeS.equals("12"));
		// Update demo TextViews when the "OK" button is clicked 
		((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				datetime = mDateTimePicker.get(Calendar.YEAR) + "/" + (mDateTimePicker.get(Calendar.MONTH)+1) + "/"
				+ mDateTimePicker.get(Calendar.DAY_OF_MONTH);
				if (mDateTimePicker.is24HourView()) {
					datetime += " "+mDateTimePicker.get(Calendar.HOUR_OF_DAY) + ":" + mDateTimePicker.get(Calendar.MINUTE);
					
				} else {
					datetime += " "+mDateTimePicker.get(Calendar.HOUR) + ":" + mDateTimePicker.get(Calendar.MINUTE) + " "
					+ (mDateTimePicker.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");
				}
				((EditText)findViewById(R.id.driverjourneyedittime)).setText(datetime);
				mDateTimeDialog.dismiss();
			}
		});

		// Cancel the dialog when the "Cancel" button is clicked
		((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mDateTimeDialog.cancel();
			}
		});

		// Reset Date and Time pickers when the "Reset" button is clicked
		((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mDateTimePicker.reset();
			}
		});
		// Setup TimePicker
		mDateTimePicker.setIs24HourView(is24h);
		// No title on the dialog window
		mDateTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Set the dialog content view
		mDateTimeDialog.setContentView(mDateTimeDialogView);
		// Display the dialog
		mDateTimeDialog.show();
	}
    
    public String parseChannelName(String chname)
	 {
		 String str1[]=chname.toString().trim().split("@");
		 String str2[]=str1[1].toString().trim().split(".com");
		 String channame=str1[0]+"-"+str2[0];
		 return channame.toString().trim();
	 }
	@SuppressWarnings("static-access")
	public void onClick(final View view)
	{		
		if(view==findViewById(R.id.drivernewrouteregsubmit))
		{
			if(ed.getText().toString().trim().length()==0 || ed1.getText().toString().trim().length()==0 || driverjourneyedittime.getText().toString().trim().length()==0 )
			{
				Toast.makeText(Newroute.this, "Please make sure all details are filled", Toast.LENGTH_LONG ).show();
			}
			else if(ed.getText().toString().trim().equals(ed1.getText().toString().trim()))
			{
				Toast.makeText(Newroute.this, "Source and Destination should be varied", Toast.LENGTH_LONG).show();
			}
			else if(checkMobiletime(driverjourneyedittime.getText().toString().trim()))
			{
				Toast.makeText(Newroute.this, "Invalid time", Toast.LENGTH_LONG).show();
			}
			else
			{
				ProgressDialog dialog = new ProgressDialog(this.getParent());
	            dialog.setMessage("Authentication user details...");
	            dialog.setIndeterminate(true);
	            dialog.setCancelable(true);
	            dialog.show();
				boolean driverregsubmitflag=validate.driverNewRouteDetails(ed.getText().toString(), ed1.getText().toString(), seatid.getText().toString(), driverjourneyedittime.getText().toString());
				if(driverregsubmitflag)
				{
					String check = controller.checkDriverridedetails(session.getUsername(mPreferences),ed.getText().toString(), ed1.getText().toString(), seatid.getText().toString(), driverjourneyedittime.getText().toString());
					System.out.println("response for checkDriverridedetails: "+check);
					if(check.trim().equals("true"))
					{
						Toast.makeText(this, "You've already created ride with this details", Toast.LENGTH_LONG).show();
						ed.setText("");
						ed1.setText("");
						driverjourneyedittime.setText("");
						seatid.setText("");
						dialog.dismiss();
					}
					else
					{
						Log.i("Newroute_onClick", "Getridelist button pressed for riderslist for driver");
						String response="";
						String str = rn.getText().toString();
						if(!str.equals(""))
							response = controller.driverNewroute(session.getUsername(mPreferences), rn.getText().toString(), ed.getText().toString(), ed1.getText().toString(), seatid.getText().toString(), driverjourneyedittime.getText().toString(), mode);
						else
							response = controller.driverNewroute(session.getUsername(mPreferences), "Route", ed.getText().toString(), ed1.getText().toString(), seatid.getText().toString(), driverjourneyedittime.getText().toString(), mode);
						System.out.println("Response from server : "+response);
						checknewrouteflag=controller.Getridelist();
						if(checknewrouteflag)
						{
							Toast.makeText(this, "New route is created", Toast.LENGTH_LONG).show();
							DriverJourneyDetails ParentActivity = (DriverJourneyDetails) this.getParent();
				            ParentActivity.switchTab(2);
				            dialog.dismiss();
						}
						System.out.println("vvvvvvvvusername: "+ session.getUsername(mPreferences));
						//channelname = parseChannelName(session.getUsername(mPreferences));
	
						
	
					}
					
				}
				else
				{
					Toast.makeText(Newroute.this, "Please make sure all fields are filled", Toast.LENGTH_LONG).show();
					dialog.dismiss();
				}
			}
			//outToServer.println("ADDMESSAGE "+session.getUsername(mPreferences)+" "+str1);
		}
		
		if (view == findViewById(R.id.change1)) 
		{
            //List items
            final CharSequence[] items = {"Current Location", "New Location", "Home", "Work"};
            //Prepare the list dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
            //Set its title
            builder.setTitle("Choose Location");
            //Set the list items and assign with the click listener
            builder.setItems(items, new DialogInterface.OnClickListener() {
                // Click listener
                public void onClick(DialogInterface dialog, int item) {
                    //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                    if(items[item]=="New Location")
                    {
                    	changeSource(view);                 	                    	
                    }
                }
            });
            AlertDialog alert = builder.create();
            //display dialog box
            alert.show();
        }
        /** check whether the change2 button has been clicked */
        if (view == findViewById(R.id.change2)) 
        {
        	
        	 //List items
            final CharSequence[] items = {"Current Location", "New Location", "Home", "Work"};
            //Prepare the list dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
            //Set its title
            builder.setTitle("Choose Location");
            //Set the list items and assign with the click listener
            builder.setItems(items, new DialogInterface.OnClickListener() {
                // Click listener
                public void onClick(DialogInterface dialog, int item) {
                    //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                    if(items[item]=="New Location")
                    {
                    	changeDestination(view);                 	                    	
                    }
                }
            });
            AlertDialog alert = builder.create();
            //display dialog box
            alert.show();
        }
        if (view == findViewById(R.id.driverjourneysettime))
        {
        	System.out.println("if condition in driverjourneysettime");
        	showDateTimeDialog();
        }
		
	}

	public void onError(DeaconError arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onPush(DeaconResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Driver payload from meteor: "+ response.getPayload());
		String payload;
		System.out.println("payload from meteor: "+ response.getPayload());
		payload = response.getPayload().trim();
		String str1[]=payload.toString().trim().split("::");
		System.out.println("ridername: "+str1[0]+"\nmessage: "+str1[1]+"\nrideid: "+str1[2]);
		String msg = msgParse(str1[1]);
		System.out.println("ridername: "+str1[0]+"\nmessage: "+msg+"\nrideid: "+str1[2]);
		DriverJourneyDetails.drivermeteormsg.add(msg+" FROM "+str1[0].toString().trim().substring(1,str1[0].length()));
		DriverJourneyDetails.driverrideid.add(msg+" FROM "+str1[0].toString().trim().substring(1,str1[0].length())+"::"+str1[2].toString().trim()+"::"+str1[3].toString().trim());
		notificationAlarm(str1[0].toString().trim().substring(1,str1[0].length()), msg);
		//notificationAlarm();
	}
	private void notificationAlarm(String name, String msg) {
		
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		JourneyDetails.DRIVER_NOTIFICATION++;
    	int icon = R.drawable.icon;
    	CharSequence text = msg;
    	CharSequence contentTitle = "  Kaarpool notification";
    	CharSequence contentText = JourneyDetails.DRIVER_NOTIFICATION+" unread(Kaarpool)" ;//msg+" from "+name;
    	long when = System.currentTimeMillis();

    	Intent intent = new Intent(Newroute.this, JourneyDetails.class);
    	intent.putExtra("receiver", "drivernotification");
    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
    	Notification notification = new Notification(icon,text,when);
    	
    	notification.flags = Notification.DEFAULT_SOUND | Notification.FLAG_AUTO_CANCEL;
    	
    	long[] vibrate = {0,100,200,300};
    	notification.vibrate = vibrate;
    	
    	notification.ledARGB = Color.RED;
    	notification.ledOffMS = 300;
    	notification.ledOnMS = 300;
    	
    	notification.defaults |= Notification.DEFAULT_LIGHTS;
    	//notification.flags |= Notification.FLAG_SHOW_LIGHTS;
    	
    	notification.setLatestEventInfo(this, contentTitle, contentText, contentIntent);
    	
    	notificationManager.notify(1001, notification);
	}

	public static void stopdeacon()
    {
    	try
    	{
    		System.out.println(driverusername+ "    Rider name  ");
    		//deacon.leaveChannel(channelname);
    		deacon.leaveChannel("d"+driverusername);
    		deacon.stop();
    		Log.i("RiderGetRidelist_StopDeacon", "Deacon stopped");
    	}
    	catch(Exception e)
    	{
    		Log.i("Exception_Stoping deacon", "Exception occured while stopping deacon");
    	}
    	
    }
	public void onReconnect() {
		// TODO Auto-generated method stub
		
	}
	
	public String msgParse(String msg)
	{
		if(msg.trim().equals("r1"))
			return getString(R.string.r1);
		if(msg.trim().equals("r2"))
			return getString(R.string.r2);
		if(msg.trim().equals("r3"))
			return getString(R.string.r3);
		if(msg.trim().equals("r4"))
			return getString(R.string.r4);
		if(msg.trim().equals("r5"))
			return getString(R.string.r5);
		if(msg.trim().equals("r6"))
			return getString(R.string.r6);
		if(msg.trim().equals("r7"))
			return getString(R.string.r7);
		if(msg.trim().equals("r8"))
			return getString(R.string.r8);
		return msg;
		
	}
}