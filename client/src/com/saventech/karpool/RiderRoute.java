package com.saventech.karpool;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderRoute.java
 * Date: Mar 25, 2011
 * Description: It is responsible to take ride data from the rider
 */
public class RiderRoute extends Activity implements OnClickListener{

	private String value="";
	private Button newroute;
	Controller controller;             //declaring controller object. Responsable to take data from data base and provid to this activity 
	private boolean checkridelistflag;  
	private Button ridersettime;
	private int mHour;
	private int mMinute;
	private EditText ridereditsettime;

	static final int TIME_DIALOG_ID = 0;
	private EditText ed;
	private EditText ed1;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller=new Controller();                       //initializing controller object
        
        Log.i("Riderroute_Activity", "Now you are in riderroute activity");
        
        setContentView(R.layout.riderjourney);
        Button change1 = (Button) findViewById(R.id.riderjourneychangesource);
        change1.setOnClickListener(this);
        /** We need to set up a click listener on the change2 button */
        Button change2 = (Button) findViewById(R.id.riderjourneychangedestination);
        ridersettime=(Button)findViewById(R.id.riderjourneysettime);
        ridereditsettime=(EditText)findViewById(R.id.riderjourneyedittime);
        ed1 = (EditText)findViewById(R.id.riderjourneydestination);
        ed = (EditText)findViewById(R.id.riderjourneysource);
        ed1.setEnabled(false);
        ed.setEnabled(false);
        ridereditsettime.setEnabled(false);
        ridersettime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        change2.setOnClickListener(this);
        newroute=(Button)findViewById(R.id.ridergetridelist);
        newroute.setOnClickListener(this);
        
        //----------time picker---------
        // get the current time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // display the current date
        updateDisplay();

        
        
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
		ridereditsettime.setText(
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
	/*
	 *  this function gives a way to change the default source
	 */
    public void changeSource(View view)
    {
    	Log.i("Riderroute_changesource", "Changing the source of a ride");
    	
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
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
    	
    	Log.i("Riderroute_changedestinaton", "Changing the destination of a ride");
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value = input.getText().toString().trim();
				
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
     * this function will be performed when ever a button is pressed
     */
	public void onClick(final View view)
	{
		// TODO Auto-generated method stub
		
		if(view==findViewById(R.id.ridergetridelist))
		{
			checkridelistflag=controller.Getridelist();
			if(checkridelistflag)
			{
				RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
	            ParentActivity.switchTab(1);
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
        	
        	Log.i("Riderroute_changesourcepopup", "Pop will be displayed to change the destinaton");
            //List items
            final CharSequence[] items = {"Current Location", "New Location", "Home", "Work"};
            //Prepare the list dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
            //Set its title
            builder.setTitle("Choose Location");
            //Set the list items along with checkbox and assign with the click listener
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                // Click listener
                public void onClick(DialogInterface dialog, int item) {
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
            alert.show();
        }
    }
}
