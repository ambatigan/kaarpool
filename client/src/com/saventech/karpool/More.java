package com.saventech.karpool;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class More extends ListActivity {
	ListView listview;
	 ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    
    
    String[] more = getResources().getStringArray(R.array.more_array);
//    setListAdapter(new ArrayAdapter<String>(this,
//            R.layout.more,R.id.more, more));
//    getListView().setBackgroundResource(R.drawable.radialback);
//    getListView().setTextFilterEnabled(true);
    setContentView(R.layout.more);
	listview = (ListView)findViewById(android.R.id.list);
	 adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,  more);
	 listview.setAdapter(adapter);

	 
	 listview.setOnItemClickListener(new OnItemClickListener() {
	    	
			public void onItemClick(AdapterView<?> a, View v, int position,	long id) {
		
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
    }});
	 }
    }
    
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//    	super.onListItemClick(l, v, position, id);
//    	
//    	switch(position)
//		{
//		case 0:
//			Intent login = new Intent(More.this, RideHistory.class);
//			startActivity(login);
//			break;
//		case 1:
//            Intent trackroute = new Intent(More.this, TrackRoute.class);
//			startActivity(trackroute);
//			break;
//		case 2:
//            Intent register1 = new Intent(More.this, ForceAcknowledge.class);
//			startActivity(register1);
//			break;
//			
//		}
//    		
//    }

    