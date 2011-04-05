package com.saventech.karpool;

/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Acknowledgement.java
 * Date: Apr 05, 2011
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Registerwithsysid extends  MenuOptions implements OnClickListener
{
	ImageButton checksystemavailability;
	private ImageButton mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;
	Controller controller=null;
	boolean checksysidflag=false;
	boolean checksyspwdflag=false;
	boolean checksysaddressflag=false;
	boolean checksysdobflag=false;
	boolean checksysgenderflag=false;
	boolean checksysmobileflag=false;
	EditText sysuserid;
	EditText sysuserpwd;
	EditText sysusermobile;
	EditText sysuseraddress;
	EditText sysuserdob;
	RadioButton sysusergendermale;
	RadioButton sysusergenderfemale;
	boolean validateflag;
	String validatestring;
	
	
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sysidscreen);
	        
	        controller=new Controller();
	        Log.i("Registration", "You are now in registration page");
	        sysuserid=(EditText)findViewById(R.id.sysuseridtxt);
	        sysuserpwd=(EditText)findViewById(R.id.regsysidpwd);
	        sysusermobile=(EditText)findViewById(R.id.regsysidmobile);
	        sysuseraddress=(EditText)findViewById(R.id.regsysidaddress);
	        sysuserdob=(EditText)findViewById(R.id.regsysiddob);
	        sysuserdob.setEnabled(false);
	        sysusergendermale=(RadioButton)findViewById(R.id.regsysidmale);
	        sysusergenderfemale=(RadioButton)findViewById(R.id.regsysidfemale);
	        checksystemavailability=(ImageButton)findViewById(R.id.checksysidavailability);
	        mPickDate = (ImageButton) findViewById(R.id.pickDate);

	        // add a click listener to the button
	        mPickDate.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                showDialog(DATE_DIALOG_ID);
	            }
	        });

	        // get the current date
	        final Calendar c = Calendar.getInstance();
	        mYear = c.get(Calendar.YEAR);
	        mMonth = c.get(Calendar.MONTH);
	        mDay = c.get(Calendar.DAY_OF_MONTH);
	        updateDisplay();

	        
	        checksystemavailability.setOnClickListener(this);
	        Button b = (Button)findViewById(R.id.sysregsubmit);
	        b.setOnClickListener(this);

	    }
        
	 private void updateDisplay() 
	 {
	        sysuserdob.setText(
	            new StringBuilder()
	                    // Month is 0 based so add 1
	                    .append(mMonth + 1).append("-")
	                    .append(mDay).append("-")
	                    .append(mYear).append(" "));
	    }
	 

	 private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() 
	 {

             

			public void onDateSet(DatePicker view, int year, 
                    int monthOfYear, int dayOfMonth) {
				 mYear = year;
                 mMonth = monthOfYear;
                 mDay = dayOfMonth;
                 updateDisplay();
				// TODO Auto-generated method stub
				
			}
         };
         
         
         @Override
         protected Dialog onCreateDialog(int id) {
             switch (id) {
             case DATE_DIALOG_ID:
                 return new DatePickerDialog(this,
                             mDateSetListener,
                             mYear, mMonth, mDay);
             }
             return null;
         }
	 
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.sysregsubmit:
			
			 //flags for validation of different edit texts
			 checksyspwdflag=false;
			 checksysaddressflag=false;
			 checksysdobflag=false;
			 checksysgenderflag=false;
			 checksysmobileflag=false;
			   
		     validatestring=new String();
		     
		     
		     // validating password 
		     
		     if(sysuserpwd.getText().toString().length()>=5)
		     {
		    	 checksyspwdflag=true;
		     }
		     else
		     {
		    	 validatestring="-> Type the correct password(length>=5)\n";
		     }
		     
		     // validating address
		     
		     if(sysuseraddress.getText().toString().length()<=200 && sysuseraddress.getText().length()>0)
		     {
		    	 checksysaddressflag=true;
		     }
		     else
		     {
		    	 validatestring=validatestring+"-> Address length should be one to two hundred\n";
		     }
		     
		     // validating userid's
		     
		     if(!checksysidflag)
		     {
		    	 validatestring=validatestring+"-> Pls select available users only \n";
		     }
		     
		     // validating gender buttons 
		     
		     if(sysusergendermale.isChecked()|| sysusergenderfemale.isChecked())
		     {
		    	 checksysgenderflag=true; 
		     }
		     else
		     {
		    	 validatestring=validatestring+"-> Pls select your gender\n";
		     }
		     // validating date of birth
		     
		     Pattern mobile = Pattern.compile("\\d{11}");
		     Matcher match = mobile.matcher(sysusermobile.getText().toString());
			 boolean matchfound= match.lookingAt();
			 if(matchfound)
			 {
				 if(sysusermobile.getText().toString().charAt(0)=='0' && (sysusermobile.getText().toString().length()==10 || sysusermobile.getText().toString().length()==11))
				 {
					 checksysmobileflag=true;
				 }
				 else
				 {
					 validatestring=validatestring+"-> Pls enter correct mobile number(start with zero) \n";
				 }
				 
			 }
			 else
			 {
				 validatestring=validatestring+"-> Pls enter correct mobile number (start with zero)\n";
			 }
		    
		     if(checksysaddressflag && checksyspwdflag &&  checksysidflag && checksysgenderflag && checksysmobileflag)
			     {
		    	 
		    	    
					 Intent intent=new Intent(Registerwithsysid.this,JourneyDetails.class);
			        startActivity(intent);
	             }
		     else
		     {
		    	 Toast.makeText(Registerwithsysid.this, validatestring, Toast.LENGTH_LONG).show();
		     }
			 break;
			 
		case R.id.checksysidavailability:
			     
			Log.i("Registerwithsysid_availableusers", "Checking for available users");
		     if(sysuserid.getText().toString().length()!=0)
		     {
				 checksysidflag=controller.Availablesysids(sysuserid.getText().toString());
				// System.out.println(checksysidflag);
				 if(checksysidflag)
				 {
					 TextView warn = (TextView)findViewById(R.id.availablesysid);
			    	 warn.setText("NO");
				 }
				 else
				 {
					 TextView warn = (TextView)findViewById(R.id.availablesysid);
			    	// Pattern p = Pattern.compile("^[a-z]+.+@[a-z]+.[a-z]{2,6}$");
					Pattern p=Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
				     Matcher m = p.matcher(sysuserid.getText().toString()+"@karpool.com");
					 boolean matchFound = m.lookingAt();
					 checksysidflag=matchFound;
					 if(matchFound)
					 {
						 warn.setText("YES");
						// Toast.makeText(Registerwithsysid.this, "Your id not following validations", Toast.LENGTH_LONG).show();
					 }
					 else
					 {
						 warn.setText("NO");
					 }
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
