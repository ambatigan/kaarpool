package com.saventech.karpool;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Rideracknowledgements extends Activity implements OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rider_acknowledgements);
        Button bb =(Button)findViewById(R.id.OkButton);
        bb.setOnClickListener(this);
    }
    
    private class OnReadyListener implements RatingDialog.ReadyListener {
        public void ready(String name) {
            Toast.makeText(Rideracknowledgements.this, name, Toast.LENGTH_LONG).show();
        }
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		RatingDialog myDialog = new RatingDialog(this.getParent(), "", new OnReadyListener());
	    myDialog.show();
		
	}
}
