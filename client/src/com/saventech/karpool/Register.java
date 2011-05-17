package com.saventech.karpool;






import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;


public class Register extends  MenuOptions  implements android.view.View.OnClickListener {
    /** Called when the activity is first created. */
	
	
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        setContentView(R.layout.newscreen);
       
        findViewById(R.id.sysgen).setOnClickListener(this);
        Log.i("Registration", "You are now in registration page");
    }
	
   
	
	public void onClick(View V) 
	{
		switch(V.getId())
		{
		case R.id.sysgen:
			Intent sysgen = new Intent(Register.this, Registerwithsysid.class);
			startActivity(sysgen);
			break;
		}
		
		
	}
	 public boolean onKeyDown(int keyCode, KeyEvent event) 
		{
			if(keyCode == KeyEvent.KEYCODE_BACK)
			{
				Intent intent=new Intent(Register.this,MainActivity.class);
				startActivity(intent);
				//return true;
			}
			return super.onKeyDown(keyCode, event);
		}
	 
	
	 
}