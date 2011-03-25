/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: DriverJourneyDetails.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class DriverJourneyDetails extends TabActivity {
	
	TabHost tabHost;
	
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        Log.i("DriverJourneyDetails", "DriverJourneyDetails screen");
        setContentView(R.layout.journeydetails);
        
        Resources res = getResources(); // Resource object to get Drawables
	    tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Newroute.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("newroute").setIndicator("New route",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Cancelroute.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("cancelroute").setIndicator("Cancel route",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Acknowledgement.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("acknowledgement").setIndicator("Acknowledgements",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(0);
    }
    
    public void switchTab(int tab)
	{
    	Log.i("DriverJourneyDetails", "switching tabs to Newroute to Acknowledgements");
		tabHost.setCurrentTab(tab);
		tabHost.getTabWidget().getChildTabViewAt(2).setEnabled(true);
	}
}
