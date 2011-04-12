package com.saventech.karpool;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
	    spec = tabHost.newTabSpec("route").setIndicator("Route",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, RiderGetRidelist.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("ridelist").setIndicator("Ridelist",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, RiderAcknowledgements.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("acknowledgement").setIndicator("Acknowledgements",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(0);
	    tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(false);
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
		Log.i("RiderJourneyDetails_switchTab", "enable ridelist tab");
		tabHost.setCurrentTab(tab);
		tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(true);
	}
}
