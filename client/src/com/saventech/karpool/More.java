package com.saventech.karpool;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class More extends Activity {
	ListView listview;
	private SharedPreferences mPreferences; 
	Session session; 
	String modevalue="";
	Controller controller;
	static ArrayList<String> ridehistory=new ArrayList<String>();
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
    controller=new Controller();
    session=new Session();
    String username = session.getUsername(mPreferences);
    modevalue=session.getMode(mPreferences);
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
					String his="";
					Intent intent = new Intent(More.this, RideHistory.class);
					if(modevalue.toString().trim().equals("driver"))
		            {
						//System.out.println(modevalue+" MMMMMMMMMMMMMMMMMMOOOOOOOOOORRRRRRRRRREEEEEEEEEEEEEEEEEEEd");
		           	   	his=controller.rideHistory(session.getUsername(mPreferences).toString().trim(), modevalue.toString().trim()) ;          	 
		            }
		            if(modevalue.toString().trim().equals("rider"))
		            {
		            	//System.out.println(modevalue+" MMMMMMMMMMMMMMMMMMOOOOOOOOOORRRRRRRRRREEEEEEEEEEEEEEEEEEEr");
		            	his=controller.rideHistory(session.getUsername(mPreferences).toString().trim(), modevalue.toString().trim()) ;   
		            }
		            if(his.contains(":::"))
		            {
		            	ridehistory.add("Source Destination Time");
		            	String historysplit[]=his.toString().trim().split("EVENT");
		            	//System.out.println(historysplit.length+"MMMMMMMMMMMMMOOOOOOOOOORRRRRRRRRREEEEEEEEEEEEEEEEEEEEEE");
		            	for(int i=0;i<historysplit.length;i++)
		            	{
		            		String getdata[]=historysplit[i].split(":::");
		            		String adddata="";
		            		for(int j=0;j<getdata.length;j++)
		            		{
		            			adddata=adddata+getdata[j].toString().trim()+" ";
		            		}
		            		ridehistory.add(adddata.toString().trim());
		            	}
		            }
					
					startActivity(intent);
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

    