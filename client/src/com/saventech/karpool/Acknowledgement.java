/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Acknowledgement.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Acknowledgement extends Activity implements android.view.View.OnClickListener 
{
	//
	private SharedPreferences mPreferences; 
	Session session;
	private ImageButton driveracknowledgement;
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
        setContentView(R.layout.ridelist0);
        driveracknowledgement=(ImageButton)findViewById(R.id.sendrequest);
        driveracknowledgement.setVisibility(View.INVISIBLE);
        //LinearLayout l = (LinearLayout) findViewById(R.id.mylayout1);
        //LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
        /*for (int i = 0; i < 4; i++) 
        {
            View customView = linflater.inflate(R.layout.ridelist,null);
            TextView route = (TextView) customView.findViewById(R.id.route);
            TextView rate = (TextView) customView.findViewById(R.id.rate);
            route.setText("   Route: IIIT to Secrateriate");
            rate.setText("   Rate:");
            ImageButton img=(ImageButton)customView.findViewById(R.id.image);
            img.setOnClickListener(this);
            CheckBox check=(CheckBox)customView.findViewById(R.id.ridelistcheckbox);
            switch(i)
            {
	            case 0:
	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image1));
	            	break;
	            case 1:
	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image2));
	            	break;
	            case 2:
	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image3));
	            	break;
	            case 3:
	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image4));
	            	break;
            }
            route.setId(i);
            rate.setId(i);
            check.setId(i);
            img.setId(i);
            l.addView(customView);
        }*/
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
