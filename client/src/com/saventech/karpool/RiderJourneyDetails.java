package com.saventech.karpool;

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderJourneyDetails.java
 * Date: Mar 25, 2011
 * It is responsible to provide tab activity to rider tab
 */
public class RiderJourneyDetails extends TabActivity implements android.view.View.OnClickListener {
	TabHost tabHost;
	private SharedPreferences mPreferences; 
	Session session;
	private String flag1;
	public static ArrayList<String> ridelist = new ArrayList<String>();
	static ArrayList<String> ridermeteormsg = new ArrayList<String>();
	static ArrayList<String> riderrideid= new ArrayList<String>();
	static ArrayList<String> justriderequests=new ArrayList<String>();
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent1 = getIntent();
		flag1 = intent1.getStringExtra("check");
		System.out.println("Riderjourneydetails  ==="+flag1);
        setContentView(R.layout.journeydetails);
        
       
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(RiderJourneyDetails.this,Login.class);
			startActivity(intent);
		
		}
		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
        Log.i("RiderJourneyDetails_Activity", "Now you are in riderjourneydetails activity");
        
        Resources res = getResources(); // Resource object to get Drawables
	    tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, RiderRoute.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("route").setIndicator("Route",res.getDrawable(R.drawable.tabmapsicon)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, RiderGetRidelist.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("ridelist").setIndicator("Ridelist",res.getDrawable(R.drawable.tabbulletlisticon)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, RiderAcknowledgements.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("acknowledgement").setIndicator("Acknowledgements",res.getDrawable(R.drawable.tabackicon)).setContent(intent);
	    tabHost.addTab(spec);
    	tabHost.setCurrentTab(0);

	    //tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(false);
	    
	    if(flag1.trim().equals("notifications"))
	    {
	    	tabHost.setCurrentTab(2);
	    }
	    if(flag1.trim().equals("notify")||flag1.trim().equals("ridernotify"))
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
	

	public void onClick(View v) {
		
	}
	public void switchTab(int tab)
	{
		
		Log.i("RiderJourneyDetails_switchTab", "enable ridelist tab ");
	
		tabHost.setCurrentTab(tab);
		//tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(true);
	}
}
