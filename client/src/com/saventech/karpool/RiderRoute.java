package com.saventech.karpool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller=new Controller();                       //initializing controller object
        
        Log.i("Riderroute_Activity", "Now you are in riderroute activity");
        
        setContentView(R.layout.riderjourney);
        Button change1 = (Button) findViewById(R.id.change1);
        change1.setOnClickListener(this);
        /** We need to set up a click listener on the change2 button */
        Button change2 = (Button) findViewById(R.id.change2);
        change2.setOnClickListener(this);
        newroute=(Button)findViewById(R.id.ridergetridelist);
        newroute.setOnClickListener(this);
        
        
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
		
		if (view == findViewById(R.id.change1)) 
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
        if (view == findViewById(R.id.change2)) {
        	
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
