package com.saventech.karpool;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Preferences extends ListActivity {
	
	String username1="";
	 private SharedPreferences mPreferences; 
	 Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    session=new Session();

    String[] pref = getResources().getStringArray(R.array.pref_array);
    setListAdapter(new ArrayAdapter<String>(this,
            R.layout.preferences, R.id.prefer, pref));
    getListView().setBackgroundResource(R.drawable.radialback);
    getListView().setTextFilterEnabled(true);
   // getListView().set.setTextFilterEnabled(true);
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
          
			 mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
             session.removeSession(mPreferences);
             System.out.println("DATA REMOVED");
             finish();
             Intent intent = new Intent(getApplicationContext(), Login.class);
             startActivity(intent); 
             removeDialog(0);
           break;
		case 4:
			finish();
			break;
			
		}
    		
    }
    
}
    
   