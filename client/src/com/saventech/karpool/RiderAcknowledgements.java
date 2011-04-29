package com.saventech.karpool;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderAcknowledgements.java
 * Date: Mar 25, 2011
 * It is responsible to display all request and responses messages
 */
public class RiderAcknowledgements extends ListActivity implements OnClickListener {
	
	private SharedPreferences mPreferences; 
	Session session;
	ArrayAdapter<String> adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Rideracknowledgements_activity", "Now you are in Rideracknowledgements activity");
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(RiderAcknowledgements.this,Login.class);
			startActivity(intent);
		
		}
		 session.storemode(mPreferences, "rider");
		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
        //setContentView(R.layout.rider_acknowledgements);
        //Button bb =(Button)findViewById(R.id.OkButton);
        //bb.setOnClickListener(this);
        //Button bb1 = (Button)findViewById(R.id.notificationbutton);
        //bb1.setOnClickListener(this);
        
        adapter=new ArrayAdapter<String>(this,
        	    android.R.layout.simple_list_item_1,
        	    RiderJourneyDetails.ridermeteormsg);
        setListAdapter(adapter);
        if(RiderJourneyDetails.ridermeteormsg.size()!=0)
        {
        	System.out.println("arraylist in acknowledgement: "+RiderJourneyDetails.ridermeteormsg.get(0).toString());        	
            adapter.notifyDataSetChanged();
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
    
    /*private class OnReadyListener implements RatingDialog.ReadyListener {
        public void ready(String name) {
            Toast.makeText(RiderAcknowledgements.this, name, Toast.LENGTH_LONG).show();
        }
    }*/
    /*
     * onClick will be performed when ever a specified button is pressed
     */

	/*public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v==findViewById(R.id.OkButton))
		{
			RatingDialog myDialog = new RatingDialog(this.getParent(), "", new OnReadyListener());
		    myDialog.show();
		}
		if(v==findViewById(R.id.notificationbutton))
		{
			NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	    	
	    	int icon = R.drawable.ic_tab_artists_white;
	    	CharSequence text = "pick up request";
	    	CharSequence contentTitle = "Ride pickup request from Roshan";
	    	CharSequence contentText = "Request has been accepted. Please confirm pickup";
	    	long when = System.currentTimeMillis();
	    	
	    	//RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
            //ParentActivity.switchTab(2);
	    	Intent intent = new Intent(this, JourneyDetails.class);
	    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
	    	Notification notification = new Notification(icon,text,when);
	    	
	    	//notification.defaults |= Notification.FLAG_AUTO_CANCEL;
	    	//notification.defaults |= Notification.DEFAULT_SOUND;
	    	notification.flags = Notification.DEFAULT_SOUND | Notification.FLAG_AUTO_CANCEL;
	    	
	    	long[] vibrate = {0,100,200,300};
	    	notification.vibrate = vibrate;
	    	
	    	notification.ledARGB = Color.RED;
	    	notification.ledOffMS = 300;
	    	notification.ledOnMS = 300;
	    	
	    	notification.defaults |= Notification.DEFAULT_LIGHTS;
	    	//notification.flags |= Notification.FLAG_SHOW_LIGHTS;
	    	
	    	notification.setLatestEventInfo(this, contentTitle, contentText, contentIntent);
	    	
	    	notificationManager.notify(1001, notification);
	    	RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
            ParentActivity.switchTab(2);
		}
		
	}*/
}
