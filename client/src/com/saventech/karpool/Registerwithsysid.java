package com.saventech.karpool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Registerwithsysid extends  MenuOptions implements OnClickListener
{
	ImageButton checksystemavailability;
	Controller controller=null;
	boolean checksysidflag;
	EditText sysuserid;
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sysidscreen);
	        
	        controller=new Controller();
	        Log.i("Registration", "You are now in registration page");
	        sysuserid=(EditText)findViewById(R.id.sysuseridtxt);
	        checksystemavailability=(ImageButton)findViewById(R.id.checksysidavailability);
	        
	        checksystemavailability.setOnClickListener(this);
	        Button b = (Button)findViewById(R.id.sysregsubmit);
	        b.setOnClickListener(this);

	    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.sysregsubmit:
			     
				 Intent intent=new Intent(Registerwithsysid.this,JourneyDetails.class);
				 startActivity(intent);
				 break;
		case R.id.checksysidavailability:
			     System.out.println("nageshddddddddddddddd");
			     if(sysuserid.getText().toString().length()!=0)
			     {
					 checksysidflag=controller.Availablesysids(sysuserid.getText().toString());
					 System.out.println(checksysidflag);
					 if(checksysidflag)
					 {
						 TextView warn = (TextView)findViewById(R.id.availablesysid);
				    	 warn.setText("NO");
					 }
					 else
					 {
						 TextView warn = (TextView)findViewById(R.id.availablesysid);
				    	 warn.setText("YES");
					 }
			     }
			     else
			     {
			    	 TextView warn = (TextView)findViewById(R.id.availablesysid);
			    	 warn.setText(" "); 
			     }
			     break;
		
		}
		     
	}
}
