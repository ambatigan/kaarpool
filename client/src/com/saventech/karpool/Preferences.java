package com.saventech.karpool;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Preferences extends Activity implements OnItemClickListener {
	
	String username1="";
	 private SharedPreferences mPreferences; 
	 Session session;
	 private String modevalue="";
	 
	
	//this is reference to listview object comes from xml file
	private ListView myListView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.preferences);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
        
        final TextView leftText = (TextView) findViewById(R.id.left_text);
        final TextView rightText = (TextView) findViewById(R.id.right_text);

        leftText.setText("kaarpool");
        
        
        
       
        
        session=new Session();
        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
        String username = session.getUsername(mPreferences);
	    String name[]= username.split("@");
	    rightText.setText(name[0]);
        if(!session.checkinfo(mPreferences))
    	{
    		Intent intent=new Intent(Preferences.this,Login.class);
    		startActivity(intent);
    	
    	}
    	modevalue=session.getMode(mPreferences);
       
        /*
         * findViewById gets the given id reference from xml 
         * cast it to ListView 
         * you can check R file which is generated to see listview reference there
         */
    
        myListView = (ListView) findViewById(R.id.myListView);
        
        String[] pref = getResources().getStringArray(R.array.pref_array);
        /*
         * set listview adapter create below
         * this means context
         * R.layout.rows which xml file to choose as a reference to draw rows of listview
         * dummyData data that listview show
         */
        myListView.setAdapter(new ArrayAdapter(this,R.layout.rows,R.id.text, pref));
       // myListView.setOnItemClickListener(this);
        myListView.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> a, View v, int position,	long id) {
        		
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
    	             RiderJourneyDetails.riderrideid=new ArrayList<String>();
    	             DriverJourneyDetails.driverrideid=new ArrayList<String>();
    	             JourneyDetails.ridelist1=new ArrayList<String>();
    	             RiderJourneyDetails.justriderequests=new ArrayList<String>();
    	             More.ridehistory=new ArrayList<String>();
    	             More.ridehistory1=new ArrayList<String>();
    	             JourneyDetails.dflag=0;
    	             JourneyDetails.rflag=0;
    	             JourneyDetails.DRIVER_NOTIFICATION=0;
    	             JourneyDetails.RIDER_NOTIFICATION=0;
    	             System.out.println("DATA REMOVED");
    	             RiderRoute.stopdeacon();
    	             Newroute.stopdeacon();
    	             finish();
    	             Intent intent = new Intent(getApplicationContext(), Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);             
    	             startActivity(intent); 
    	             removeDialog(0);
    	           break;
    			
    			}
    		}});
        
    }
    
    
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
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
}