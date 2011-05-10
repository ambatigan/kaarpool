package com.saventech.karpool;

import java.util.ArrayList;
import java.util.Calendar;

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

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderRoute.java
 * Date: Mar 25, 2011
 * Description: It is responsible to take ride data from the rider
 */
public class RiderRoute extends Activity implements OnClickListener,DeaconObserver{

	private String value="";
	private String datetime="";
	private Button newroute;
	private String mode="rider";
	boolean getRideListflag=true;
	Controller controller;             //declaring controller object. Responsable to take data from data base and provid to this activity 
	private boolean checkridelistflag;
	private Button ridersettime;
	private EditText ridereditsettime;
	private EditText ed;
	private EditText ed1;
	private SharedPreferences mPreferences; 
	Session session;
	Validations riderroutevalidate;
	
	// deacon data
	private String ip = "";
	private int port;
	private static String riderusername="";
	private static  Deacon deacon;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //drawUI();
        
       
        
    }
	public void drawUI()
	{
        controller=new Controller();                       //initializing controller object
        
        Log.i("Riderroute_Activity", "Now you are in riderroute activity");
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(RiderRoute.this,Login.class);
			startActivity(intent);
		
		}
		 session.storemode(mPreferences, "rider");
		riderroutevalidate=new Validations();
		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
        setContentView(R.layout.riderjourney);
        Button change1 = (Button) findViewById(R.id.riderjourneychangesource);
        change1.setOnClickListener(this);
        /** We need to set up a click listener on the change2 button */
        Button change2 = (Button) findViewById(R.id.riderjourneychangedestination);
        ridersettime=(Button)findViewById(R.id.riderjourneysettime);
        ridersettime.setOnClickListener(this);
        ridereditsettime=(EditText)findViewById(R.id.riderjourneyedittime);
        ed1 = (EditText)findViewById(R.id.riderjourneydestination);
        ed = (EditText)findViewById(R.id.riderjourneysource);
        
        ed1.setEnabled(false);
        ed.setEnabled(false);
        ridereditsettime.setEnabled(false);

        change2.setOnClickListener(this);
        newroute=(Button)findViewById(R.id.ridergetridelist);
        newroute.setOnClickListener(this);
        
        
        try 
        {
		   // System.out.println("context"+context);
        	riderusername=parseChannelName(session.getUsername(mPreferences));
        	ip = getString(R.string.MeteorIP);
        	port=Integer.parseInt(getString(R.string.SubscriberPort));
        	System.out.println("Rider "+JourneyDetails.rflag+"   rflag value"+JourneyDetails.dflag+"  dflag value");
        	if(JourneyDetails.dflag!=0)
        	{
        		Newroute.stopdeacon();
        		//deacon.leaveChannel(parseChann elName(session.getUsername(mPreferences)));
        		JourneyDetails.dflag=0;
        		System.out.println("Driver channel leaveddddddddddddddd");
        	}
        	if(JourneyDetails.rflag==0)
   		    {
        		System.out.println("Rider Routeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
   			 try {
   				 this.deacon = new Deacon(ip.toString().trim(),port, this);
   				 if(!deacon.isRunning())
   					{
   			        		deacon.catchUpTimeOut(60);
   			            	deacon.register(this);
   			            	
   							//deacon.leaveChannel(parseChannelName(session.getUsername(mPreferences)));
   							deacon.joinChannel("r"+parseChannelName(session.getUsername(mPreferences)), 0);
   							deacon.start();
   						
   					}
   				 JourneyDetails.rflag=1;
   				 } catch (Exception e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
   		 }
        	//System.out.println(ip+"ip add of meteorAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        	
        	Log.i("RiderChannel",parseChannelName(mPreferences.getString("UserName","un").toString().trim()) );
        	
 			
        } 
        catch (Exception e) 
        {
        	System.out.println("Problem while creating Deacon");
        	e.printStackTrace();
        }
       /* System.out.println(session.checkRideDetails(mPreferences)+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^898989");
        if(session.checkRideDetails(mPreferences))
		{
        	ed.setText(mPreferences.getString("ridersource","rs"));
        	ed1.setText(mPreferences.getString("riderdestination","rd"));
        	ridereditsettime.setText(mPreferences.getString("ridersettime","rt"));
			
		}*/
		
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		drawUI();
		
		
	}
	
	public String parseChannelName(String chname)
	 {
		 String str1[]=chname.toString().trim().split("@");
		 String str2[]=str1[1].toString().trim().split(".com");
		 String channame=str1[0]+"-"+str2[0];
		 //System.out.println("Parsed channel name^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+channame);
		 return channame.toString().trim();
	 }
	 
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  // Save UI state changes to the savedInstanceState.
	  // This bundle will be passed to onCreate if the process is
	  // killed and restarted.
		
	  // etc.
	  super.onSaveInstanceState(savedInstanceState);
	}
	


	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	/*
	 *  this function gives a way to change the default source
	 */
    public void changeSource(View view)
    {
    	Log.i("Riderroute_changesource", "Changing the source of a ride");
    	
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
    	alert.setTitle("Source");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value = input.getText().toString().trim();
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
    
    /*
	 *  this function gives a way to change the default destination
	 */
    public void changeDestination(View view)
    {
    	
Log.i("Riderroute_changesource", "Changing the Destination of a ride");
    	
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
    	alert.setTitle("Destination");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value = input.getText().toString().trim();
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
				((EditText)findViewById(R.id.riderjourneyedittime)).setText(datetime);
				mDateTimeDialog.dismiss();
			}
		});

		// Cancel the dialog when the "Cancel" button is clicked
		((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDateTimeDialog.cancel();
			}
		});

		// Reset Date and Time pickers when the "Reset" button is clicked
		((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
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
    
    
    public void removePreviousSharedPreferences()
    {
    	if(mPreferences.contains("checkboxesclicked"))
		{
			 System.out.println("RiderRoute"+session.getCheckBoxesClicked(mPreferences));
			 SharedPreferences.Editor editor=mPreferences.edit();
			 editor.putString("checkboxesclicked","");
			 editor.commit();
			 System.out.println("RiderRoute"+session.getCheckBoxesClicked(mPreferences));
		}
    }
    /*
     * this function will be performed when ever a button is pressed
     */
	public void onClick(final View view)
	{
		
		if(view==findViewById(R.id.ridergetridelist))
		{

			if(ed.getText().toString().trim().equals(ed1.getText().toString().trim()))
			{
				Toast.makeText(RiderRoute.this, "Source and Destination should be varied", Toast.LENGTH_LONG).show();
			}
			else
			{
				
			
				ProgressDialog progressdialog = new ProgressDialog(this.getParent());
	            progressdialog.setMessage("Authentication user details...");
	            progressdialog.setIndeterminate(true);
	            progressdialog.setCancelable(true);
	            progressdialog.show();
				removePreviousSharedPreferences();
				boolean validateridelistflag=riderroutevalidate.rideGetRidelist(ed.getText().toString().trim(), ed1.getText().toString().trim(), ridereditsettime.getText().toString().trim());
				if(validateridelistflag)
				{
					String check = controller.checkRiderridedetails(session.getUsername(mPreferences),ed.getText().toString(), ed1.getText().toString(), ridereditsettime.getText().toString());
					System.out.println("response for checkDriverridedetails: "+check);
					String response="";
					if(!check.trim().equals("true"))
					{
						String res="";
						System.out.println("ridergetridelist: before if condition");
						res = controller.riderNewroute(session.getUsername(mPreferences), ed.getText().toString(), ed1.getText().toString(), ridereditsettime.getText().toString(), mode);
						System.out.println("Response from server : "+res);
						Log.i("RiderRoute_onClick", "Rider pressed on getride list button in RiderRoute");
					}
					
						checkridelistflag=controller.Getridelist();
						if(checkridelistflag)
						{
							
							
							response = controller.riderGetRideList(session.getUsername(mPreferences), ed.getText().toString(), ed1.getText().toString(), ridereditsettime.getText().toString(), mode);
							System.out.println("Response from server : "+response+"------------------------------------------------------------------------");
							String str[]=response.toString().split("KPLL");
							ArrayList<String>ridelistdata=new ArrayList<String>();
							if(str.length>1)
						    {  
								
								for(int k=0;k<str.length-1;k++)
							   {
									System.out.println(str[k]+"   mmm");
								    ridelistdata.add(str[k].toString());
							   }
								RiderJourneyDetails.ridelist=ridelistdata;
								JourneyDetails.ridelist1=ridelistdata;
	
								RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
					            ParentActivity.switchTab(1);
					            progressdialog.dismiss();
							}
							else
							{
								/*if(!session.checkRideDetails(mPreferences))
								{
									session.saveRideDetails(mPreferences, ed.getText().toString().trim(), ed1.getText().toString().trim(), ridereditsettime.getText().toString().trim());
								}*/
								//removePreviousSharedPreferences();
								RiderJourneyDetails.ridelist=ridelistdata;
								JourneyDetails.ridelist1=ridelistdata;
								Toast.makeText(RiderRoute.this, "No Match found at this point of time", Toast.LENGTH_LONG).show();
								RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
					            ParentActivity.switchTab(1);
					            progressdialog.dismiss();
							}
							
							
	
							RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
				            ParentActivity.switchTab(1);
				            //finish();
						}
						else
						{
	                      
							RiderJourneyDetails.ridelist=null;
							System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
							
							Toast.makeText(RiderRoute.this, "No Match found at this point of time", Toast.LENGTH_LONG).show();
							RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
				            ParentActivity.switchTab(1);
				            progressdialog.dismiss();
						}
					
					
					
				}
				else
				{
					Toast.makeText(RiderRoute.this, "Please make sure all details are filled", Toast.LENGTH_LONG ).show();
					progressdialog.dismiss();
				}
			}
			
			
		}
		
		if (view == findViewById(R.id.riderjourneychangesource)) 
		{
			
			Log.i("Riderroute_changesourcepopup", "Pop will be displayed to change the source");
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
        if (view == findViewById(R.id.riderjourneychangedestination)) {
        	
        	Log.i("Riderroute_changesourcepopup", "Pop will be displayed to change the source");
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
        if(view == findViewById(R.id.riderjourneysettime))
        {
        	System.out.println("if condition in riderjourneysettime");
        	showDateTimeDialog();
        }
    }

	public void onError(DeaconError arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onPush(DeaconResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Rider payload from meteor: "+ response.getPayload());
		String payload;
		System.out.println("payload from meteor: "+ response.getPayload());
		payload = response.getPayload().trim();
		String str1[]=payload.toString().trim().split("::");
		System.out.println("ridername: "+str1[0]+"\nmessage: "+str1[1]+"\nrideid: "+str1[2]);
		String msg = msgParse(str1[1]);
		RiderJourneyDetails.ridermeteormsg.add(msg+" FROM "+str1[0].toString().trim().substring(1,str1[0].length()));
		RiderJourneyDetails.riderrideid.add(msg+" FROM "+str1[0].toString().trim().substring(1,str1[0].length())+"::"+str1[2].toString().trim());
		notificationAlarm(str1[0].toString().trim().substring(1,str1[0].length()), msg);
		
	}
	private void notificationAlarm(String name, String msg) {
		// TODO Auto-generated method stub
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    	
    	int icon = R.drawable.ic_tab_artists_white;
    	CharSequence text = msg;
    	CharSequence contentTitle = "  Kaarpool notification";
    	CharSequence contentText = msg+" from "+name;
    	long when = System.currentTimeMillis();
    	
    	//RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
        //ParentActivity.switchTab(2);
    	Intent intent = new Intent(RiderRoute.this, JourneyDetails.class);
    	intent.putExtra("receiver", "ridernotification");
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

	public void onReconnect() {
		// TODO Auto-generated method stub
		
	}
	public String msgParse(String msg)
	{
		if(msg.trim().equals("d1"))
			return getString(R.string.d1);
		if(msg.trim().equals("d2"))
			return getString(R.string.d2);
		if(msg.trim().equals("d3"))
			return getString(R.string.d3);
		if(msg.trim().equals("d4"))
			return getString(R.string.d4);
		if(msg.trim().equals("d5"))
			return getString(R.string.d5);
		if(msg.trim().equals("d6"))
			return getString(R.string.d6);
		return msg;
		
	}
	 public static void stopdeacon()
     {
     	
     	try
     	{
     		System.out.println(riderusername+ "    Rider name  ");
     		deacon.leaveChannel("r"+riderusername);
     		deacon.stop();
     		Log.i("RiderGetRidelist_StopDeacon", "Deacon stopped");
     	}
     	catch(Exception e)
     	{
     		Log.i("Exception_Stoping deacon", "Exception occured while stopping deacon");
     	}
     	
     }
	 
	 /*public String makeMessage(String chname1,String chname2, String messageval,String ridid)
	 {
		 String deaconmessage="";
		 if(chname1.toString().trim().length()!=0 && chname2.toString().trim().length()!=0 )
		 {
		     deaconmessage="ADDMESSAGE "+chname1+" "+chname2.toString().trim()+"::"+messageval+"::"+ridid+"EVENT";
		 }
		 return deaconmessage;
	 }*/
}
