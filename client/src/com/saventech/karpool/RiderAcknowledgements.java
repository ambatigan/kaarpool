package com.saventech.karpool;

import android.app.Activity;
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
		
		RatingDialog myDialog = new RatingDialog(this.getParent(), "", new OnReadyListener());
	    myDialog.show();
		
	}
}
