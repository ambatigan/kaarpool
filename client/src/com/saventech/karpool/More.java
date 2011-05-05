package com.saventech.karpool;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class More extends Activity {
	ListView listview;
	private SharedPreferences mPreferences; 
	Session session; 
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    
    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    setContentView(R.layout.more);
    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
    
    final TextView leftText = (TextView) findViewById(R.id.left_text);
    final TextView rightText = (TextView) findViewById(R.id.right_text);

    leftText.setText("kaarpool");
    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
    session=new Session();
    String username = session.getUsername(mPreferences);
    String name[]= username.split("@");
    rightText.setText(name[0]);
    
    String[] more = getResources().getStringArray(R.array.more_array);

    
	listview = (ListView)findViewById(R.id.myListView);
	listview.setAdapter(new ArrayAdapter(this,R.layout.rows,R.id.text, more));
	
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

    