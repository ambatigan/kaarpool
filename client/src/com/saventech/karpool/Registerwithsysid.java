package com.saventech.karpool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Registerwithsysid extends  MenuOptions implements OnClickListener
{
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sysidscreen);
	        
	        
	        Log.i("Registration", "You are now in registration page");
	        
	       Button b = (Button)findViewById(R.id.sysregsubmit);
	       b.setOnClickListener(this);

	    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.sysregsubmit:
			
				 Intent intent=new Intent(Registerwithsysid.this,JourneyDetails.class);
				 startActivity(intent);
		}
		     
	}
}
