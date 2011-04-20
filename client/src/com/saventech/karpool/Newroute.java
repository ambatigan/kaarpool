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
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class Newroute extends Activity implements OnClickListener{
	
	private String value="";
	
	private String mode="driver";
	private Button newroute;
	Controller controller;
	private boolean checknewrouteflag;
	private int mHour;
	private int mMinute;
	static final int TIME_DIALOG_ID = 0;
	private Button driverjourneysettime;
	private EditText driverjourneyedittime;
	private EditText ed;
	private EditText ed1;
	private EditText seatid;
	private SharedPreferences mPreferences; 
	Session session;

	
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        Log.i("DriverJourneyDetails_New route", "New route tab in DriverJourneyDetails");
        session=new Session();
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
        driverjourneyedittime=(EditText)findViewById(R.id.driverjourneyedittime);
        driverjourneyedittime.setEnabled(false);
        ed = (EditText)findViewById(R.id.sourceid);
        ed1 = (EditText)findViewById(R.id.destinationid);
        seatid = (EditText)findViewById(R.id.seatid);
        ed.setEnabled(false);
        ed1.setEnabled(false);
        driverjourneysettime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
        
        newroute.setOnClickListener(this);     
        
      //----------time picker---------
        // get the current time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // display the current date
        updateDisplay();
        
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
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case TIME_DIALOG_ID:
	        return new TimePickerDialog(this.getParent(),mTimeSetListener, mHour, mMinute, false);
	    }
	    return null;
	}
	
	// updates the time we display in the TextView
	private void updateDisplay() {
		driverjourneyedittime.setText(
	        new StringBuilder()
	                .append(pad(mHour)).append(":")
	                .append(pad(mMinute)));
	}
	
	// the callback received when the user "sets" the time in the dialog
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
	    new TimePickerDialog.OnTimeSetListener() {
	        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	            mHour = hourOfDay;
	            mMinute = minute;
	            updateDisplay();
	        }
	    };
	    
	    
	    private static String pad(int c) {
	        if (c >= 10)
	            return String.valueOf(c);
	        else
	            return "0" + String.valueOf(c);
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
//    	Log.i("Newroute_changeDestination", "change button pressed to change Destination location");
//    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
//		final EditText input = new EditText(this);
//		alert.setView(input);
//		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//				value = input.getText().toString().trim();
//			}
//		});
//		alert.setNegativeButton("Cancel",
//				new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						dialog.cancel();
//					}
//				});
//		alert.show();
    }
	public void onClick(final View view)
	{
		// TODO Auto-generated method stub
		
		if(view==findViewById(R.id.drivernewrouteregsubmit))
		{
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
            //display dialog box
           /* //List items
            final CharSequence[] items = {"Current Location", "New Location", "Home", "Work"};
            //Prepare the list dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
            //Set its title
            builder.setTitle("Choose Location");
            //Set the list items along with checkbox and assign with the click listener
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                // Click listener
                public void onClick(DialogInterface dialog, int item) {
                    //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                    //If the Cheese item is chosen close the dialog box
                    if(items[item]=="New Location")
                    {
                    	changeDestination(view);
                    }
                }
            });
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int whichButton) {
    				//Toast.makeText(getApplicationContext(), value,Toast.LENGTH_SHORT).show();
    				
    		    	ed1.setText(value);
    			}
    		});
            AlertDialog alert = builder.create();
            //display dialog box
            alert.show();*/
        }
		
	}
}