package com.saventech.karpool;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RideHistory extends ListActivity {
	String value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    
    
    String[] pref = getResources().getStringArray(R.array.data_array);
    setListAdapter(new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, pref));
    getListView().setTextFilterEnabled(true);
   }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	
    	switch(position)
		{
		case 1:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
			       .setCancelable(false)
			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   //RideHistory.this.finish();
			           }
			       });
			       
			AlertDialog alert = builder.create();
			alert.show();
			break;
		case 2:
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
			       .setCancelable(false)
			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   //RideHistory.this.finish();
			           }
			       });
			       
			AlertDialog alert1 = builder1.create();
			alert1.show();
			break;
		case 3:
           AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
		       .setCancelable(false)
		       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	  // RideHistory.this.finish();
		           }
		       });
		       
			AlertDialog alert2 = builder2.create();
			alert2.show();
			break;
		case 4:
			
			AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
			builder3.setMessage("Source: IIIT\nDestination: Secretariate\nDriver:Nagesh\nRider:Rajesh\nAmount:5 points")
		       .setCancelable(false)
		       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	  // RideHistory.this.finish();
		           }
		       });
		       
			AlertDialog alert3 = builder3.create();
			alert3.show();
			break;
		}
    		
    }
    
}