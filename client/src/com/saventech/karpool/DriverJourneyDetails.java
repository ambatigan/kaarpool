/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: DriverJourneyDetails.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TabHost;

public class DriverJourneyDetails extends TabActivity {
	
	TabHost tabHost;
	private SharedPreferences mPreferences; 
	Session session;
	TabActivity tabact;
	private String flag1;
	
	static ArrayList<String> drivermeteormsg = new ArrayList<String>();
	static ArrayList<String> driverrideid=new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        Log.i("DriverJourneyDetails", "DriverJourneyDetails screen");
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(DriverJourneyDetails.this,Login.class);
			startActivity(intent);
		
		}
		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
		Intent intent1 = getIntent();
		flag1 = intent1.getStringExtra("check");
		System.out.println("DriverJourneyDetails ======"+flag1);
        setContentView(R.layout.journeydetails);
        
        Resources res = getResources(); // Resource object to get Drawables
	    tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent, intent11, intent2;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Newroute.class);
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("newroute").setIndicator("New route",res.getDrawable(R.drawable.tabmapsicon)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent11 = new Intent().setClass(this, CancelRoute.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("cancelroute").setIndicator("Cancel route",res.getDrawable(R.drawable.tabcancel)).setContent(intent11);
	    tabHost.addTab(spec);
	    
	    intent2 = new Intent().setClass(this, Acknowledgement.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("acknowledgement").setIndicator("Acknowledgements",res.getDrawable(R.drawable.tabackicon)).setContent(intent2);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(0);
	    
	    if(flag1.trim().equals("notifications"))
	    {
	    	tabHost.setCurrentTab(2);
	    }
	    if(flag1.trim().equals("notify")||flag1.trim().equals("drivernotify"))
	    {
	    	tabHost.setCurrentTab(0);
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
    
    public void switchTab(int tab)
	{
    	Log.i("DriverJourneyDetails", "switching tabs to Newroute to Acknowledgements");
		tabHost.setCurrentTab(tab);
		tabHost.getTabWidget().getChildTabViewAt(2).setEnabled(true);
	}
}
