package com.saventech.karpool;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Preferences extends ListActivity implements OnClickListener  {
	
	String username1="";
	 private SharedPreferences mPreferences; 
	 Session session;
	 private String modevalue="";
	 ListView listview;
	 ListAdapter adapter;
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
	setContentView(R.layout.preferences);
	listview = (ListView)findViewById(android.R.id.list);
	modevalue=session.getMode(mPreferences);
    String[] pref = getResources().getStringArray(R.array.pref_array);
    
    adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,  pref);
    //setListAdapter(new ArrayAdapter<String>(this,
    	//	android.R.layout.simple_list_item_1,  pref));

//    getListView().setTextFilterEnabled(true);
   // getListView().set.setTextFilterEnabled(true);
    System.out.println("Preferencesssssssssssssssssssssssssssssss");
    listview.setAdapter(adapter);
    System.out.println("Preferencesssssssssssssssssssssssssssssss11");
   // listview.setOnItemClickListener(this);
 //getListView().setBackgroundResource(R.drawable.radialback);
    listview.setOnItemClickListener(new OnItemClickListener() {
    	
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
		}});
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

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
    
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//    	super.onListItemClick(l, v, position, id);
//    	
//    	switch(position)
//		{
//		case 0:
//			Intent profilepref = new Intent(Preferences.this, ProfilePref.class);
//			startActivity(profilepref);
//			break;
//		case 1:
//            Intent register = new Intent(Preferences.this, TravelPref.class);
//			startActivity(register);
//			break;
//		case 2:
//            Intent register1 = new Intent(Preferences.this, Timebasedefault.class);
//			startActivity(register1);
//			break;
//		case 3:
//           
//             session.removeSession(mPreferences);
//             DriverJourneyDetails.drivermeteormsg = new ArrayList<String>();
//             RiderJourneyDetails.ridermeteormsg = new ArrayList<String>();
//             RiderJourneyDetails.ridelist=new ArrayList<String>();
//             JourneyDetails.ridelist1=new ArrayList<String>();
//             System.out.println("DATA REMOVED");
//             RiderGetRidelist.stopdeacon();
//             Newroute.stopdeacon();
//             finish();
//             Intent intent = new Intent(getApplicationContext(), Login.class);             
//             startActivity(intent); 
//             removeDialog(0);
//           break;
//		
//		}
//    		
//    }
//
//	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		// TODO Auto-generated method stub
//		
//	}
    
}
    
   