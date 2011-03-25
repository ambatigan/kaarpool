package com.saventech.karpool;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Preferences extends ListActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    
    
    String[] pref = getResources().getStringArray(R.array.pref_array);
    setListAdapter(new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, pref));
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
			//System.out.println("checkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
			break;
		case 4:
			finish();
			break;
			
		}
    		
    }
    
}
    
   