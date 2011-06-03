package com.saventech.karpool;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Timebasedefault extends Activity implements OnClickListener{
	
	EditText source ;
	EditText destination;
	EditText timings ;
	private int mhour;
	private int mminute;

	EditText weekdays;
	String location="";
	Controller controller ;
	Session session;
	String username="";
	static final int TIME_DIALOG_ID = 1;
	private SharedPreferences mPreferences;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.timebasedefault);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
        
        final TextView leftText = (TextView) findViewById(R.id.left_text);
        final TextView rightText = (TextView) findViewById(R.id.right_text);

        leftText.setText("kaarpool");
        
        session = new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
	    
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(Timebasedefault.this,Login.class);
			startActivity(intent);
		}
		
        username = session.getUsername(mPreferences);
        String name[]= username.split("@");
	    rightText.setText(name[0]);
        
        System.out.println(username+"username");
        controller = new Controller();
        String totalString = controller.getTimeBasedPref(username);
//        
       
        
        
        findViewById(R.id.timeback).setOnClickListener(this);
        findViewById(R.id.timesave).setOnClickListener(this);
        findViewById(R.id.timebaseddefaultchangetime).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(TIME_DIALOG_ID);
            }

        });

        Spinner spinner = (Spinner) findViewById(R.id.timebasedspinner);
        // spinner.setText(fields[4]);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                 this, R.array.time_based, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter);
        source=(EditText)findViewById(R.id.timebasedsource);
        destination=(EditText)findViewById(R.id.timebasedestination);
        timings=(EditText)findViewById(R.id.timebasedtiming);
        timings.setEnabled(false);
        weekdays=(EditText)findViewById(R.id.weekdays);
        System.out.println();
        System.out.println(totalString+"totalStringtotalStringtotalStringtotalStringtotalString");
        
        final Calendar c = Calendar.getInstance();
        mhour = c.get(Calendar.HOUR_OF_DAY);
        mminute = c.get(Calendar.MINUTE);
        updatetime();

        String[] fields = totalString.split("::");
        if(!totalString.equals("") &&fields.length>1)
        {
        	if(!fields[0].equals("null"))
        	{
        	source.setText(fields[0]);
        	}
        	if(!fields[1].equals("null"))
        	{
        		destination.setText(fields[1]);
        	}
        	if(!fields[2].equals("null"))
        	{
        		timings.setText(fields[2]);
        	}
        	if(!fields[4].equals("null"))
        	{
        		weekdays.setText(fields[4]);
        	}
        	int i = adapter.getPosition(fields[3]);
        	System.out.println(i+"selectPos");
        	spinner.setSelection(i);
        	}
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
}

    public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) 
		{
			//Toast.makeText(parent.getContext(), "The planet is " +
	        //parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
			location = parent.getItemAtPosition(pos).toString();
			System.out.println(location+"location");
			
		}

		public void onNothingSelected(AdapterView<?> arg0) 
		{
			// TODO Auto-generated method stub
			
		}
	}
    
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case TIME_DIALOG_ID:
        	//return new TimePickerDialog(this,mTimeSetListener, 0, 0, false);
            return new TimePickerDialog(this,mTimeSetListener, mhour, mminute, false);

        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				mhour = hourOfDay;
                mminute = minute;
                updatetime();
				
			}


        };

    public void updatetime()
    {
    	if(mhour>=12)
    	{
    		mhour=mhour-12;
    		timings.setText(
                    new StringBuilder()
                            .append(pad(mhour)).append(":")
                            .append(pad(mminute)).append(" PM")); 
    	}
    	else
    	{
    		timings.setText(
                    new StringBuilder()
                            .append(pad(mhour)).append(":")
                            .append(pad(mminute)).append(" AM")); 
    	}
    	
    }

    private static String pad(int c) {
               /* if (c >= 10)
                {
                	if(c>12)
                	{
                		c=c-12;
                	}*/
                	
                	return String.valueOf(c);
               // }
                /*else
                {
                    return "0" + String.valueOf(c);
                }*/
    }



    public void onClick(View V) 
	{
		switch(V.getId())
		{
		case R.id.timeback:
			Intent twitter = new Intent(Timebasedefault.this, Preferences.class);
			startActivity(twitter);
			break;
		case R.id.timesave:
			 System.out.println(username+"username in savre");
			 if(weekdays.getText().length()!=0 && source.getText().toString().length()!=0 && destination.getText().toString().length() !=0 && timings.getText().toString().length() !=0)
			 {
				 if((!source.getText().toString().equals(destination.getText().toString())))
				{
					String res =controller.saveTimeBasedPref(weekdays.getText().toString(),source.getText().toString(), destination.getText().toString(), timings.getText().toString(),location,username);
					System.out.println(res+"response   kkkkkkkkkkkkk");
					Intent timebased = new Intent(Timebasedefault.this, Preferences.class);
					startActivity(timebased);
				}
				 else
				 {
					 Toast.makeText(Timebasedefault.this,"Please check source and destination", Toast.LENGTH_LONG).show();
			    	 Log.i("Timebasedefault", "Please check source and destination"); 
				 }
			 }
			 else
			 {
				Toast.makeText(Timebasedefault.this,"Please enter all fields", Toast.LENGTH_LONG).show();
	    	    Log.i("Timebasedefault", "Please enter all fields");
			 }
			break;
		}
		
		
	}
}