package com.saventech.karpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Timebasedefault extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timebasedefault);
        findViewById(R.id.timepref).setOnClickListener(this);
        findViewById(R.id.timesave).setOnClickListener(this);
}

    public void onClick(View V) 
	{
		switch(V.getId())
		{
		case R.id.timepref:
			Intent twitter = new Intent(Timebasedefault.this, Registerwithopenid.class);
			startActivity(twitter);
			break;
		case R.id.timesave:
			Intent sysgen = new Intent(Timebasedefault.this, Registerwithsysid.class);
			startActivity(sysgen);
			break;
		}
		
		
	}
}