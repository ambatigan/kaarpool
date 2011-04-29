package com.saventech.karpool;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Preferences extends ListActivity {
	
	String username1="";
	 private SharedPreferences mPreferences; 
	 Session session;
	 private String modevalue="";
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    session=new Session();
    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
	if(!session.checkinfo(mPreferences))
	{
		Intent intent=new Intent(Preferences.this,Login.class);
		startActivity(intent);
	
	}
	modevalue=session.getMode(mPreferences);
    String[] pref = getResources().getStringArray(R.array.pref_array);
    setListAdapter(new ArrayAdapter<String>(this,
            R.layout.preferences, R.id.prefer, pref));
    getListView().setBackgroundResource(R.drawable.radialback);
    getListView().setTextFilterEnabled(true);
   // getListView().set.setTextFilterEnabled(true);
   }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent intent=new Intent(Preferences.this,JourneyDetails.class);
			System.out.println("Preferences ==="+modevalue);
			if(modevalue.toString().trim().equals("driver"))
            {
           	 intent.putExtra("receiver", "preferences"+modevalue.toString().trim()+"notification");
           	 
            }
            if(modevalue.toString().trim().equals("rider"))
            {
           	 intent.putExtra("receiver", "preferences"+modevalue.toString().trim()+"notification");
           	 
            }
			startActivity(intent);
			//return true;
		}
		return super.onKeyDown(keyCode, event);
	}
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	
    	switch(position)
		{
		case 0:
			Intent profilepref = new Intent(Preferences.this, ProfilePref.class);
			startActivity(profilepref);
			break;
		case 1:
            Intent register = new Intent(Preferences.this, TravelPref.class);
			startActivity(register);
			break;
		case 2:
            Intent register1 = new Intent(Preferences.this, Timebasedefault.class);
			startActivity(register1);
			break;
		case 3:
           
             session.removeSession(mPreferences);
             DriverJourneyDetails.drivermeteormsg = new ArrayList<String>();
             RiderJourneyDetails.ridermeteormsg = new ArrayList<String>();
             RiderJourneyDetails.ridelist=new ArrayList<String>();
             JourneyDetails.ridelist1=new ArrayList<String>();
             System.out.println("DATA REMOVED");
             RiderGetRidelist.stopdeacon();
             Newroute.stopdeacon();
             finish();
             Intent intent = new Intent(getApplicationContext(), Login.class);             
             startActivity(intent); 
             removeDialog(0);
           break;
		
		}
    		
    }
    
}
    
   