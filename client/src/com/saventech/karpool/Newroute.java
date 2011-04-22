/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Newroute.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;

import java.util.Calendar;

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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.saventech.karpool.DateTimePicker;

public class Newroute extends Activity implements OnClickListener{
	
	private String value="";
	private String datetime="";
	private String mode="driver";
	private Button newroute;
	Controller controller;
	private boolean checknewrouteflag;
	private Button driverjourneysettime;
	private EditText driverjourneyedittime;
	private EditText ed;
	private EditText ed1;
	private EditText seatid;
	private SharedPreferences mPreferences; 
	Session session;
	Validations validate;

	
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        Log.i("DriverJourneyDetails_New route", "New route tab in DriverJourneyDetails");
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
        ed = (EditText)findViewById(R.id.sourceid);
        ed1 = (EditText)findViewById(R.id.destinationid);
        seatid = (EditText)findViewById(R.id.seatid);
        ed.setEnabled(false);
        ed1.setEnabled(false);        
        newroute.setOnClickListener(this);    
        if(session.checkNewRouteDetails(mPreferences))
		{
        	ed.setText(mPreferences.getString("driversource","rs"));
        	ed1.setText(mPreferences.getString("driverdestination","rd"));
        	driverjourneyedittime.setText(mPreferences.getString("driversettime","rt"));
        	seatid.setText(mPreferences.getString("driverseats","rt"));
        	
			
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
	public void onClick(final View view)
	{
		// TODO Auto-generated method stub
		
		if(view==findViewById(R.id.drivernewrouteregsubmit))
		{
			boolean driverregsubmitflag=validate.driverNewRouteDetails(ed.getText().toString(), ed1.getText().toString(), seatid.getText().toString(), driverjourneyedittime.getText().toString());
			if(driverregsubmitflag)
			{
				if(!session.checkNewRouteDetails(mPreferences))
				{
					session.saveDriverDetails(mPreferences, ed.getText().toString().trim(), ed1.getText().toString().trim(),driverjourneyedittime.getText().toString().trim(), seatid.getText().toString().trim());
				}
				Log.i("Newroute_onClick", "Getridelist button pressed for riderslist for driver");
				String response="";
				response = controller.driverNewroute(session.getUsername(mPreferences), ed.getText().toString(), ed1.getText().toString(), seatid.getText().toString(), driverjourneyedittime.getText().toString(), mode);
				System.out.println("Response from server : "+response);
				checknewrouteflag=controller.Getridelist();
				if(checknewrouteflag)
				{
					//Toast.makeText(this, "New route is created", Toast.LENGTH_LONG).show();
					DriverJourneyDetails ParentActivity = (DriverJourneyDetails) this.getParent();
		            ParentActivity.switchTab(2);
				}
			}
			else
			{
				Toast.makeText(Newroute.this, "Please make sure all fields are filled", Toast.LENGTH_LONG).show();
			}
			
			
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
}