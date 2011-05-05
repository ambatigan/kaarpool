/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: JourneyDetails.java
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;

public class JourneyDetails extends TabActivity {
	
	private SharedPreferences mPreferences; 
	Session session;
	String Regusername="";
	String Regpwd="";
	long transactionID = -1;
	TabHost tabHost;
	private String flag;

	public static int dflag=0;
	public static int rflag=0;

	public static ArrayList<String> ridelist1 = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
		
		Log.i("JourneyDetails","You are now in Journeydetails table");
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.journeydetails);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
        
        final TextView leftText = (TextView) findViewById(R.id.left_text);
        final TextView rightText = (TextView) findViewById(R.id.right_text);

        leftText.setText("kaarpool");
        
        //tv.setText("kaarpool");
	    Intent intent1 = getIntent();
	    flag = intent1.getStringExtra("receiver");
	    System.out.println("Journey details $$$$$$"+flag);
	    setContentView(R.layout.journeydetails);
	    Intent newintent=  getIntent();

	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
	    session=new Session();

	    
	    try
	    {
	    	Regusername=newintent.getStringExtra("RegisterUsername");
	   	  
	 	    Regpwd=newintent.getStringExtra("RegisterPassword");
	 	    
	 	    
	 	    if(Regusername.toString().equals("loginid") && Regpwd.toString().equals("loginpwd"))
	 	    {
	 	    	
	 		    Log.i("JourneyDetails", "Session checking");
	 			if(!session.checkinfo(mPreferences))
	 			{
	 				 Log.i("JourneyDetails", "No session");
	 				Intent intent=new Intent(JourneyDetails.this,Login.class);
	 				startActivity(intent);
	 			
	 			}
	 	    	
	 	    }
	 	    else
	 	    {
	 	    	Log.i("JourneyDetails", "Stroing user details");
	 	    	System.out.println("User name and password......"+Regusername+" \t"+Regpwd);
	 	    	SharedPreferences.Editor editor=mPreferences.edit();
	 	    	
	             editor.putString("UserName", Regusername);
	             
	             editor.putString("PassWord", Regpwd);
	            
	             editor.commit();  
	             System.out.println();
	             

	 		    
	 	    }
	 	   
	    }
	    catch(Exception e)
	    {
	    	
 		    Log.i("JourneyDetails", "Session checkingNotifications");
 			if(!session.checkinfo(mPreferences))
 			{
 				 Log.i("JourneyDetails", "No session");
 				Intent intent=new Intent(JourneyDetails.this,Login.class);
 				startActivity(intent);
 			
 			}
	    }
	    String username = session.getUsername(mPreferences);
	    String name[]= username.split("@");
	    rightText.setText(name[0]);
	    
	    Resources res = getResources(); // Resource object to get Drawables
	    tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent, intent2;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, DriverJourneyDetails.class);
	    
	    if(flag.trim().equals("drivernotification"))
	    	intent.putExtra("check", "notifications");
	    else if(flag.trim().equals("preferencesdrivernotification"))
	    	intent.putExtra("check", "drivernotify");
	    /*else if(flag.trim().equals("preferencesridernotification"))
	    	intent.putExtra("check", "ridernotify");*/
	    else
	    	intent.putExtra("check", "error");
	    
	    
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Driver").setIndicator("DRIVER",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent);
	    tabHost.addTab(spec);
	    tabHost.setBackgroundResource(R.drawable.radialback);//setBackgroundDrawable(R.drawable.radialback);
	    // Do the same for the other tabs
	    intent2 = new Intent().setClass(this, RiderJourneyDetails.class);
	    
	    if(flag.trim().equals("ridernotification"))
	    {
	    	System.out.println("journey details: ridernotificationnnnnnnnnnnnnnnn");
	    	intent2.putExtra("check", "notifications");
	    }
	    else if(flag.trim().equals("preferencesridernotification"))
	    	intent2.putExtra("check", "ridernotify");
	    /*else if(flag.trim().equals("preferencesdrivernotification"))
	    	intent.putExtra("check", "drivernotify");*/
	    else
	    	intent2.putExtra("check", "error");
	   
	    
	    spec = tabHost.newTabSpec("rider").setIndicator("RIDER",res.getDrawable(R.drawable.ic_tab_newroute)).setContent(intent2);
	    tabHost.addTab(spec);
	    
	    
	    if(flag.trim().equals("drivernotification")||flag.trim().equals("preferencesdrivernotification"))
	    {
	    	System.out.println("i am in notification");
	    	tabHost.setCurrentTab(0);	    	
	    }
	    if(flag.trim().equals("ridernotification")||flag.trim().equals("preferencesridernotification"))
	    {
	    	System.out.println("i am in notification");
	    	tabHost.setCurrentTab(1);	    	
	    }
	    if(flag.trim().equals("notify"))
	    {
	    	System.out.println("i am in notify");
	    	tabHost.setCurrentTab(1);
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
	
	/**
	 * Creating menu options for both rider/driver after login into application
	 */
	
	private static final int ABOUT = Menu.FIRST ;
	private static final int CONTACT_US = Menu.FIRST+1 ;
	private static final int INSTRUCTIONS = Menu.FIRST+2 ;
	private static final int PREFERENCES = Menu.FIRST+3 ;
	private static final int MORE = Menu.FIRST+4;
	private static final int SIGNOUT = Menu.FIRST+5;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		menu.add(0, ABOUT, 0, "ABOUT");	
		menu.add(0, CONTACT_US, 0, "CONTACT_US");	
		menu.add(0, INSTRUCTIONS, 0, "INSTRUCTIONS");
		menu.add(0, PREFERENCES, 0, "PREFERENCES");	
		menu.add(0, MORE, 0, "MORE");	
		menu.add(0, SIGNOUT, 0, "SIGN OUT");	
		return true;
	}
	
	/**
	 *  For the selected items in the option menu of karpool application
	 */
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		Log.i("JourneyDetails_onOptionsItemSelected","Menu button selected");
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
		case SIGNOUT:
			signOut();
			return true;
		}
		return false;
	}
	public void more()
	{
		Intent in = new Intent(this, More.class);
		Log.i("JourneyDetails_more", "More menu option has been selected");
		startActivity(in);
		
	}
	
	public void preferences()
	{
		Log.i("JourneyDetails_preferences","preferences menu option has been selected");
		Intent in = new Intent(this, Preferences.class);
		startActivity(in);
	}
	public long getTransactionID(){
		return transactionID;
	}
	public void setTransactionID(long l){
		transactionID = l;
	}
	public void switchTabSpecial(int tab){
		tabHost.setCurrentTab(tab);
	}
	public void signOut()
	{
		
		 session.removeSession(mPreferences);
         DriverJourneyDetails.drivermeteormsg = new ArrayList<String>();
         RiderJourneyDetails.ridermeteormsg = new ArrayList<String>();
         RiderJourneyDetails.ridelist=new ArrayList<String>();
         JourneyDetails.ridelist1=new ArrayList<String>();
         JourneyDetails.dflag=0;
         JourneyDetails.rflag=0;
         System.out.println("DATA REMOVED");
         RiderGetRidelist.stopdeacon();
         Newroute.stopdeacon();
         finish();
         Intent intent = new Intent(getApplicationContext(), Login.class);             
         startActivity(intent); 
         removeDialog(0);
	}
	
}
