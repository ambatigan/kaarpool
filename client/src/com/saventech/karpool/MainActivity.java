package com.saventech.karpool;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: MainActivity.java
 * Date: Mar 25, 2011
 * Description: It is provides register and login options to user
 */
public class MainActivity extends  MenuOptions implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.i("MainActivity_Activity", "You are in MainActivity");
        Button login = (Button)findViewById(R.id.login);
        Button register = (Button)findViewById(R.id.register);
        
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        
        
        
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			//finish();

			Intent setIntent = new Intent(Intent.ACTION_MAIN);
		    setIntent.addCategory(Intent.CATEGORY_HOME);
		    //setIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		   // setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    startActivity(setIntent); 
			//android.os.Process.killProcess(android.os.Process.myPid());
		    //finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
   /*
    * onClick will be called when ever user pressed the either login or register button
    */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.login:
			Intent login = new Intent(MainActivity.this, MainLogin.class);
			startActivity(login);
			break;
		case R.id.register:
            Intent register = new Intent(MainActivity.this, Registerwithsysid.class);
			startActivity(register);
			break;
		default:
			
		}
		
	}
}
