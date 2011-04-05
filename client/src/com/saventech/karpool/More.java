package com.saventech.karpool;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class More extends ListActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    
    
    String[] more = getResources().getStringArray(R.array.more_array);
    setListAdapter(new ArrayAdapter<String>(this,
            R.layout.more,R.id.more, more));
    getListView().setBackgroundResource(R.drawable.radialback);
    getListView().setTextFilterEnabled(true);
   }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	
    	switch(position)
		{
		case 0:
			Intent login = new Intent(More.this, RideHistory.class);
			startActivity(login);
			break;
		case 1:
            Intent trackroute = new Intent(More.this, TrackRoute.class);
			startActivity(trackroute);
			break;
		case 2:
            Intent register1 = new Intent(More.this, ForceAcknowledge.class);
			startActivity(register1);
			break;
			
		}
    		
    }
    
}
    