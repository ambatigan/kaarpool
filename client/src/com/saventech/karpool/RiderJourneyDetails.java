package com.saventech.karpool;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

public class RiderJourneyDetails extends TabActivity implements android.view.View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journeydetails);
        Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Riderroute.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("route").setIndicator("Route",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Ridergetridelist.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("ridelist").setIndicator("Ridelist",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Rideracknowledgements.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("acknowledgement").setIndicator("Acknowledgements",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(0);
//        Button bt=(Button)findViewById(R.id.regsubmitend);
//        //System.out.println("dddddddddddddddddddddddddddddddddd");
//        bt.setOnClickListener(this);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		//System.out.println("commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
//		switch(v.getId())
//		{
//		case R.id.regsubmitend:
//			Intent intent;  // Reusable Intent for each tab
//
//		    // Create an Intent to launch an Activity for the tab (to be reused)
//		    intent = new Intent().setClass(this, Ridergetridelist.class);
//		    startActivity(intent);
//		}
		
		
	}
}
