package com.saventech.karpool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Register extends  MenuOptions  implements android.view.View.OnClickListener {
    /** Called when the activity is first created. */
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newscreen);
        
        findViewById(R.id.facebook).setOnClickListener(this);
        findViewById(R.id.gmail).setOnClickListener(this);
        findViewById(R.id.twitter).setOnClickListener(this);
        findViewById(R.id.sysgen).setOnClickListener(this);
        Log.i("Registration", "You are now in registration page");
    }
	
	public void onClick(View V) 
	{
		switch(V.getId())
		{
		case R.id.facebook:
			Intent facebook = new Intent(Register.this, Registerwithopenid.class);
			startActivity(facebook);
			break;
		case R.id.gmail:
			Intent gmail = new Intent(Register.this, Registerwithopenid.class);
			startActivity(gmail);
			break;
		case R.id.twitter:
			Intent twitter = new Intent(Register.this, Registerwithopenid.class);
			startActivity(twitter);
			break;
		case R.id.sysgen:
			Intent sysgen = new Intent(Register.this, Registerwithsysid.class);
			startActivity(sysgen);
			break;
		}
		
		
	}
}