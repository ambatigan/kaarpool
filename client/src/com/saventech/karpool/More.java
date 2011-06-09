package com.saventech.karpool;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class More extends Activity {
	ListView listview;
	private SharedPreferences mPreferences; 
	Session session; 
	String modevalue="";
	Controller controller;
	ListAdapter adapter;
	
	static ArrayList<String> ridehistory=new ArrayList<String>();
	static ArrayList<String> ridehistory1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
  
    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    setContentView(R.layout.more);
    Intent intent = getIntent();
    intent.getBooleanExtra("check", JourneyDetails.check);
    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
    
    final TextView leftText = (TextView) findViewById(R.id.left_text);
    final TextView rightText = (TextView) findViewById(R.id.right_text);

    leftText.setText("Kaarpool");
    leftText.setTypeface(null, Typeface.BOLD);
    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
    controller=new Controller();
    session=new Session();
    String username = session.getUsername(mPreferences);
    modevalue=session.getMode(mPreferences);
    String name[]= username.split("@");
    rightText.setText(name[0]);
    
    String[] more = getResources().getStringArray(R.array.more_array);

	listview = (ListView)findViewById(R.id.myListView);
	adapter=new MyAdapter(this,R.layout.rows,R.id.text,more);
	listview.setAdapter(adapter);
	listview.setOnItemClickListener(new OnItemClickListener() {
	    	
			public void onItemClick(AdapterView<?> a, View v, int position,	long id) {
		
				switch(position)
				{
				case 0:
					ridehistory1=new ArrayList<String>();
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
		            	//ridehistory.add("Source Destination Time");
		            	String historysplit[]=his.toString().trim().split("EVENT");
		            	 //System.out.println(historysplit.length+"MMMMMMMMMMMMMOOOOOOOOOORRRRRRRRRREEEEEEEEEEEEEEEEEEEEEE");
		            	for(int i=0;i<historysplit.length;i++)
		            	{
		            		String getdata[]=historysplit[i].split(":::");
		            		String adddata="";
		            		for(int j=0;j<getdata.length-1;j++)
		            		{
		            			adddata=adddata+getdata[j].toString().trim()+" ";
		            		}
		            		
		            		
		            		ridehistory.add(adddata.toString().trim());
		            		adddata=adddata+getdata[getdata.length-1].toString().trim()+" ";
		            		System.out.println(adddata+"oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
		            		ridehistory1.add(adddata.toString().trim());
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
				case 3:
					showInfo("About","Is under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under constructionIs under construction");
					break;
				case 4:
					showInfo("Contact Us","Is under construction");
					break;
				case 5:
					showInfo("Instructions","Is under construction");
					break;
				case 6:
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
	   	             Intent loginintent = new Intent(More.this, Login.class);             
	   	             startActivity(loginintent); 
	   	             removeDialog(0);
					break;
					
				}
    }});
	 }
    class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, int textViewResourceId,
				String[] objects) {
			super(context, resource, textViewResourceId, objects);
		}

		public boolean areAllItemsEnabled() {
            return true;
        }

        public boolean isEnabled(int position) {
        	if(JourneyDetails.check)
        	{
        		return true;
        	}
        	else if(position == 1)
        		return false;
        	else
        		return true;
        	
        }
    }
    public void showInfo(String a, String b)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(a)
			   .setMessage(b)
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
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

    