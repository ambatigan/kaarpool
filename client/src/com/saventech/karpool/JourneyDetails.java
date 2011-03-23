package com.saventech.karpool;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class JourneyDetails extends TabActivity {
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.journeydetails);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, DriverJourneyDetails.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Driver").setIndicator("DRIVER",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, RiderJourneyDetails.class);
	    spec = tabHost.newTabSpec("rider").setIndicator("RIDER",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(1);
	}
	private static final int ABOUT = Menu.FIRST ;
	private static final int CONTACT_US = Menu.FIRST+1 ;
	private static final int INSTRUCTIONS = Menu.FIRST+2 ;
	private static final int PREFERENCES = Menu.FIRST+3 ;
	private static final int MORE = Menu.FIRST+4;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		menu.add(0, ABOUT, 0, "ABOUT");	
		menu.add(0, CONTACT_US, 0, "CONTACT_US");	
		menu.add(0, INSTRUCTIONS, 0, "INSTRUCTIONS");
		menu.add(0, PREFERENCES, 0, "PREFERENCES");	
		menu.add(0, MORE, 0, "MORE");	
		return true;
	}
	
	/**
	 *  For the selected items in the option menu of quizActivity
	 */
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
		case ABOUT:
			return true;
		case CONTACT_US:
			return true;
		case INSTRUCTIONS:
			return true;
		case PREFERENCES:
			preferences();
			return true;
		case MORE:
			more();
			return true;
		}
		return false;
	}
	public void more()
	{
		Intent in = new Intent(this, More.class);
		startActivity(in);
	}
	
	public void preferences()
	{
		Intent in = new Intent(this, Preferences.class);
		startActivity(in);
	}
	

}
