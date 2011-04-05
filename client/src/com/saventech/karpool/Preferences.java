package com.saventech.karpool;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Preferences extends ListActivity {
	TextView tt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    
    //setContentView(R.layout.preferences);
    String[] pref = getResources().getStringArray(R.array.pref_array);
    setListAdapter(new ArrayAdapter<String>(this,
            R.layout.preferences, R.id.prefer, pref));
   // tt =(TextView)findViewById(R.id.prefer);
    getListView().setBackgroundResource(R.drawable.radialback);
    getListView().setTextFilterEnabled(true);
   }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	
    	switch(position)
		{
		case 0:
			Intent login = new Intent(Preferences.this, ProfilePref.class);
			startActivity(login);
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
           int pid=android.os.Process.myPid();
           android.os.Process.killProcess(pid);
           break;
		case 4:
			finish();
			break;
			
		}
    		
    }
    
}
    
   