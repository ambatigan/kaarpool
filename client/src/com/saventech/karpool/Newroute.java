/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Newroute.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;

public class Newroute extends Activity implements OnClickListener{
	
	private String value="";
	private Button newroute;
	Controller controller;
	private boolean checknewrouteflag;
	
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        Log.i("DriverJourneyDetails_New route", "New route tab in DriverJourneyDetails");
        setContentView(R.layout.drivernewroute);
        controller=new Controller();
        Button change1 = (Button) findViewById(R.id.change1);
        change1.setOnClickListener(this);
        /** We need to set up a click listener on the change2 button */
        Button change2 = (Button) findViewById(R.id.change2);
        change2.setOnClickListener(this);
        newroute=(Button)findViewById(R.id.drivernewrouteregsubmit);
        newroute.setOnClickListener(this);        
        
    }
    /**
     *  The following methods are used to change source and destination of 
     *  particular ride
     */
    public void changeSource(View view)
    {
    	Log.i("Newroute_changeSource", "change button pressed to change source location");
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value = input.getText().toString().trim();
				//Toast.makeText(getApplicationContext(), value,Toast.LENGTH_SHORT).show();
				EditText ed = (EditText)findViewById(R.id.sourceid);
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
    	Log.i("Newroute_changeDestination", "change button pressed to change Destination location");
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
	public void onClick(final View view)
	{
		// TODO Auto-generated method stub
		if(view==findViewById(R.id.drivernewrouteregsubmit))
		{
			Log.i("Newroute_onClick", "Getridelist button pressed for riderslist for driver");
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
        if (view == findViewById(R.id.change2)) {
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
    				EditText ed = (EditText)findViewById(R.id.destinationid);
    		    	ed.setText(value);
    			}
    		});
            AlertDialog alert = builder.create();
            //display dialog box
            alert.show();
        }
		
	}
}