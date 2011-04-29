/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Cancelroute.java
 * Date: Mar 25, 2011
 * It is responsible to cancel current ride for driver
 */

package com.saventech.karpool;

import java.util.Calendar;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class CancelRoute extends Activity implements OnClickListener 
{
	private String value="";
	private String datetime="";
	private String cancelinfo=null;
	private EditText source;
	private EditText destination;
	private EditText starttime;
	private Button change1;
	private Button change2;
	private Button routetime;
	private Button cancelroutebutton;
	Controller controller;
	private SharedPreferences mPreferences; 
	Session session;
	
    public void onCreate(Bundle savedInstanceState) 
    {
    	
        super.onCreate(savedInstanceState);
        reloadCancelRoute();
        
    }
    public void reloadCancelRoute()
    {
    	session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(CancelRoute.this,Login.class);
			startActivity(intent);
		
		}
		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
        Log.i("DriverJourneyDetails_Cancel route", "Cancel route tab in DriverJourneyDetails");
        controller=new Controller();
        session.storemode(mPreferences, "driver");
        cancelinfo = controller.getCancelroutedetails(session.getUsername(mPreferences));
        System.out.println("cancel information: "+cancelinfo);
        
        setContentView(R.layout.cancelroute);
        source=(EditText)findViewById(R.id.drivercancelroutesource);
        change1 = (Button)findViewById(R.id.drivercancelroutechange1);
        change1.setOnClickListener(this);
        destination=(EditText)findViewById(R.id.drivercancelroutedestination);
        change2 = (Button)findViewById(R.id.drivercancelroutechange2);
        change2.setOnClickListener(this);
        starttime=(EditText)findViewById(R.id.drivercancelroutestarttime);
        routetime = (Button)findViewById(R.id.drivercancelroutetime);
        routetime.setOnClickListener(this);
        source.setEnabled(false);
        destination.setEnabled(false);
        starttime.setEnabled(false);        
        cancelroutebutton=(Button)findViewById(R.id.drivercancelroute);
        cancelroutebutton.setOnClickListener(this);
        if((cancelinfo.trim()).equals("nodata"))
        {
        	Log.i("Cancel info", "No route is created by with this name");
        	Toast.makeText(this, "You didn't create any ride till now", Toast.LENGTH_LONG).show();
        }
        else if((cancelinfo.toString().trim().equals(",,")))
        {
        	
        }
        else
        {
        	System.out.println("cancelinfo in else statement");
        	StringTokenizer tokenizer = new StringTokenizer(cancelinfo, ",");
        	source.setText(tokenizer.nextToken());
        	destination.setText(tokenizer.nextToken());
        	starttime.setText(tokenizer.nextToken());
        	cancelinfo=null;
        }
    }
    public void onResume()
    {
    	super.onResume();
    	reloadCancelRoute();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
    
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
		    	source.setText(value);
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
    	
    	Log.i("Riderroute_changesource", "Changing the source of a ride");
    	
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
    	alert.setTitle("Destination");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value = input.getText().toString().trim();
				destination.setText(value);
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
    
    public void showDateTimeDialog() {
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
				((EditText)findViewById(R.id.drivercancelroutestarttime)).setText(datetime);
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
    
    public void getUserRidedetails()
    {
    	String data="";
    	data = controller.userTotalRidedetails(session.getUsername(mPreferences));
    	System.out.println("ride details: "+data);
    	if(data.length()==0)
    	{
    		data="No route is created/all routes are deleted by you";
    	}
    	AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
		builder.setTitle("Ride Details")
			   .setMessage(data)
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
    	
    }

	public void onClick(final View view) 
	{
		
		if (view == findViewById(R.id.drivercancelroute))
		{
			if(source.getText().toString()==null || destination.getText().toString()==null || starttime.getText().toString()==null)
			{
				Toast.makeText(this, "please enter valid cancel details", Toast.LENGTH_LONG).show();
			}
			else
			{
				Log.i("Cancelroute_onClick", "Cancelroute button pressed");
				System.out.println(session.getUsername(mPreferences)+" "+source.getText().toString()+" "+destination.getText().toString()+" "+starttime.getText().toString());
				String response =controller.Canceldriverroute(session.getUsername(mPreferences), source.getText().toString(), destination.getText().toString(), starttime.getText().toString());
				System.out.println("1Response from server: "+response);
				source.setText("");
	        	destination.setText("");
	        	starttime.setText("");
				//Toast.makeText(this, "Route has been canceled", Toast.LENGTH_LONG).show();
	        	if((response.trim()).compareTo("nodatafound")==0)
	        	{
	        		Toast.makeText(this, "please enter correct ride details", Toast.LENGTH_LONG).show();
	        		AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
	        		builder.setMessage("Please enter correct ride details....")
	        		       .setCancelable(false)
	        		       .setPositiveButton("Show Ride Details", new DialogInterface.OnClickListener() {
	        		           public void onClick(DialogInterface dialog, int id) {
	        		        	   getUserRidedetails();
	        		           }
	        		       })
	        		       .setNegativeButton("OK", new DialogInterface.OnClickListener() {
	        		           public void onClick(DialogInterface dialog, int id) {
	        		        	   dialog.cancel();
	        		           }
	        		       });
	        		AlertDialog alert = builder.create();
	        		alert.show();
	        	}
			}
			
		}
		if (view == findViewById(R.id.drivercancelroutechange1)) 
		{
			
			Log.i("driver cancel route source", "Pop will be displayed to change the source");
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
        if (view == findViewById(R.id.drivercancelroutechange2)) {
        	
        	Log.i("driver cancel route source", "Pop will be displayed to change the source");
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
        if(view == findViewById(R.id.drivercancelroutetime))
        {
        	System.out.println("if condition in driver cancel route");
        	showDateTimeDialog();
        }
		
	}
    
}