package com.saventech.karpool;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Timebasedefault extends Activity implements OnClickListener{
	
	EditText source ;
	EditText destination;
	EditText timings ;
	EditText weekdays;
	String location="";
	Controller controller ;
	Session session;
	String username="";
	private SharedPreferences mPreferences;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
	    
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(Timebasedefault.this,Login.class);
			startActivity(intent);
		}
        username = session.getUsername(mPreferences);
        System.out.println(username+"username");
        controller = new Controller();
        String totalString = controller.getTimeBasedPref(username);
//        
        setContentView(R.layout.timebasedefault);
        findViewById(R.id.timeback).setOnClickListener(this);
        findViewById(R.id.timesave).setOnClickListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.timebasedspinner);
        // spinner.setText(fields[4]);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                 this, R.array.time_based, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter);
        source=(EditText)findViewById(R.id.timebasedsource);
        destination=(EditText)findViewById(R.id.timebasedestination);
        timings=(EditText)findViewById(R.id.timebasedtiming);
        weekdays=(EditText)findViewById(R.id.weekdays);
        System.out.println();
        System.out.println(totalString+"totalStringtotalStringtotalStringtotalStringtotalString");
        
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
			String res =controller.saveTimeBasedPref(weekdays.getText().toString(),source.getText().toString(), destination.getText().toString(), timings.getText().toString(),location,username);
			System.out.println(res+"response   kkkkkkkkkkkkk");
			Intent timebased = new Intent(Timebasedefault.this, Preferences.class);
			startActivity(timebased);
			break;
		}
		
		
	}
}