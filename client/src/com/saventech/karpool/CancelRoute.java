/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Cancelroute.java
 * Date: Mar 25, 2011
 * It is responsible to cancel current ride for driver
 */

package com.saventech.karpool;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CancelRoute extends Activity implements OnClickListener 
{
	
	private EditText source;
	private EditText destination;
	private EditText starttime;
	private Button cancelroutebutton;
	private boolean cancelrouteflag;
	Controller controller;
	private SharedPreferences mPreferences; 
	Session session;
	
    public void onCreate(Bundle savedInstanceState) 
    {
    	
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.cancelroute);
        source=(EditText)findViewById(R.id.drivercancelroutesource);
        destination=(EditText)findViewById(R.id.drivercancelroutedestination);
        starttime=(EditText)findViewById(R.id.drivercancelroutestarttime);
        source.setEnabled(false);
        destination.setEnabled(false);
        starttime.setEnabled(false);
        
        cancelroutebutton=(Button)findViewById(R.id.drivercancelroute);
        cancelroutebutton.setOnClickListener(this);
        
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onClick(View view) 
	{
		Log.i("Cancelroute_onClick", "Cancelroute button pressed");
		switch(view.getId())
		{
			case R.id.drivercancelroute:
				cancelrouteflag=controller.Canceldriverroute();
				if(cancelrouteflag)
				{
					Toast.makeText(this, "Route has been canceled", Toast.LENGTH_LONG).show();
					source.clearFocus();
				}
				break;
			default:
				break;
			
		}
		
	}
    
}