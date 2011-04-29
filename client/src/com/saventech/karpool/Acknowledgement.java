/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Acknowledgement.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Acknowledgement extends ListActivity implements android.view.View.OnClickListener 
{
	//
	private SharedPreferences mPreferences; 
	Session session;
	
	//DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
	ArrayAdapter<String> adapter;
	int count=0;
	/**
	 * This screen show the riders list for driver and driver can get notifications(accept/reject)
	 * also in this screen.
	 */
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(Acknowledgement.this,Login.class);
			startActivity(intent);
		
		}
		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
        Log.i("DriverJourneyDetails_Acknowledgement","Acknowledgement tab in DriverJourneyDetails");
        session.storemode(mPreferences, "driver");
        //setContentView(R.layout.ridelist0);
        //driveracknowledgement=(ImageButton)findViewById(R.id.sendrequest);
        //driveracknowledgement.setVisibility(View.INVISIBLE);
        
        adapter=new ArrayAdapter<String>(this,
        	    android.R.layout.simple_list_item_1,
        	    DriverJourneyDetails.drivermeteormsg);
        setListAdapter(adapter);
        if(DriverJourneyDetails.drivermeteormsg.size()!=0)
        {
        	System.out.println("arraylist in acknowledgement: "+DriverJourneyDetails.drivermeteormsg.get(0).toString());
            /*for(int i=0; i<DriverJourneyDetails.drivermeteormsg.size();i++)
            {
            	String str[] = DriverJourneyDetails.drivermeteormsg.get(i).toString().split("::");
            }*/
        	
            adapter.notifyDataSetChanged();
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
    public void onClick(View V) 
	{
    	Log.i("DriverJourneyDetails_Acknowledgement", "image button clicked to see riders details");
		switch(V.getId())
		{
		case 0:
			final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
			final TextView input = new TextView(this);
			input.setText("\tName\t\t\t:Roshan\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n\tSeats\t\t\t:3");
			alert.setView(input);
			
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int whichButton) 
				{
					dialog.dismiss();
					return;
				}
			});
			alert.show();
			break;
		case 1:
			final AlertDialog.Builder alert1 = new AlertDialog.Builder(this.getParent());
			final TextView input1 = new TextView(this);
			input1.setText("\tName\t\t\t:Bhargav\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n\tSeats\t\t\t:3");
			alert1.setView(input1);
			
			alert1.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int whichButton) 
				{
					//String value = input.getText().toString().trim();
					return;
				}
			});
			alert1.show();
				break;
		case 2:
			final AlertDialog.Builder alert2 = new AlertDialog.Builder(this.getParent());
			final TextView input2 = new TextView(this);
			input2.setText("\tName\t\t\t:Lakshmikanth\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n\tSeats\t\t\t:3");
			alert2.setView(input2);
			
			alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int whichButton) 
				{
					return;
				}
			});
			alert2.show();
				break;
		case 3:
			final AlertDialog.Builder alert3 = new AlertDialog.Builder(this.getParent());
			final TextView input3 = new TextView(this);
			input3.setText("\tName\t\t\t:Praveen\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n\tSeats\t\t\t:3");
			alert3.setView(input3);
			
			alert3.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int whichButton) 
				{
					//String value = input.getText().toString().trim();
					return;
				}
			});
			alert3.show();
				break;
		}
		
		
	}
}
