package com.saventech.karpool;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderAcknowledgements.java
 * Date: Mar 25, 2011
 * It is responsible to display all request and responses messages
 */
public class RiderAcknowledgements extends Activity implements OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Rideracknowledgements_activity", "Now you are in Rideracknowledgements activity");
        setContentView(R.layout.rider_acknowledgements);
        Button bb =(Button)findViewById(R.id.OkButton);
        bb.setOnClickListener(this);
        Button bb1 = (Button)findViewById(R.id.notificationbutton);
        bb1.setOnClickListener(this);
    }
    
    private class OnReadyListener implements RatingDialog.ReadyListener {
        public void ready(String name) {
            Toast.makeText(RiderAcknowledgements.this, name, Toast.LENGTH_LONG).show();
        }
    }
    /*
     * onClick will be performed when ever a specified button is pressed
     */

	public void onClick(View v) {
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
		
	}
}
