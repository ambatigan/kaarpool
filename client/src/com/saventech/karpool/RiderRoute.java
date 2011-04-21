package com.saventech.karpool;

import java.util.ArrayList;
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

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderRoute.java
 * Date: Mar 25, 2011
 * Description: It is responsible to take ride data from the rider
 */
public class RiderRoute extends Activity implements OnClickListener{

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

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller=new Controller();                       //initializing controller object
        
        Log.i("Riderroute_Activity", "Now you are in riderroute activity");
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(RiderRoute.this,Login.class);
			startActivity(intent);
		
		}
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
    /*
     * this function will be performed when ever a button is pressed
     */
	public void onClick(final View view)
	{
		
		if(view==findViewById(R.id.ridergetridelist))
		{
			
			checkridelistflag=controller.Getridelist();
			if(checkridelistflag)
			{
				String res="";
				System.out.println("ridergetridelist: before if condition");
				res = controller.riderNewroute(session.getUsername(mPreferences), ed.getText().toString(), ed1.getText().toString(), ridereditsettime.getText().toString(), mode);
				System.out.println("Response from server : "+res);
				Log.i("RiderRoute_onClick", "Rider pressed on getride list button in RiderRoute");
				String response="";
				response = controller.riderGetRideList(session.getUsername(mPreferences), ed.getText().toString(), ed1.getText().toString(), ridereditsettime.getText().toString(), mode);
				System.out.println("Response from server : "+response+"------------------------------------------------------------------------");
				String str[]=response.toString().split("KPLL");
				ArrayList<String>ridelistdata=new ArrayList<String>();
				if(str.length>1)
			    {  
					System.out.println(str.length+"   00000000000000000000000000000000000");
					for(int k=0;k<str.length-1;k++)
				   {
						System.out.println(str[k]+"   mmm");
					    ridelistdata.add(str[k].toString());
				   }
					RiderJourneyDetails.ridelist=ridelistdata;
					JourneyDetails.ridelist1=ridelistdata;
					
					RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
		            ParentActivity.switchTab(1);
		            //finish();
				}
				else
				{

					RiderJourneyDetails.ridelist=ridelistdata;

					Toast.makeText(RiderRoute.this, "No Match found at this point of time", Toast.LENGTH_LONG).show();
					RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
		            ParentActivity.switchTab(1);
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
}
