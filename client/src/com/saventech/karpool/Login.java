package com.saventech.karpool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Login.java
 * Date: Mar 25, 2011
 * Description: It is responsible to login to the application
 */
public class Login extends  MenuOptions implements OnClickListener{
    /** Called when the activity is first created. */
	private EditText userid;
	private EditText pwd;
	String username = null;
	Session session;
	String pword = null;
	private SharedPreferences mPreferences; 
	Controller controller=null;                     //declaring controller object. Responsable to take data from data base and provid to this activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.login);
        
        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
        SharedPreferences.Editor editor=mPreferences.edit();
        editor.remove("UserName");
        editor.remove("PassWord");
        editor.commit();
        System.out.println("DATA REMOVED");
        Log.i("Login_Activity", "Now you are in login activity");
        session=new Session();
        controller=new Controller();                //initializing controller object
        userid = (EditText)findViewById(R.id.useridlogintxt);
        pwd = (EditText)findViewById(R.id.pwdlogintxt);
        findViewById(R.id.loginbutton).setOnClickListener(this);
}
    
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent intent=new Intent(Login.this,MainActivity.class);
			startActivity(intent);
			//return true;
		}
		return super.onKeyDown(keyCode, event);
	}
/*
 * onClick will be called when ever login button is clicked
 * it trim the userid and password and send to Controller
 * it redirects to the specified activity based on controller response.
 */
	public void onClick(View v)
	{
		username = userid.getText().toString().trim();
        pword = pwd.getText().toString().trim();
		switch(v.getId())
		{
		case R.id.loginbutton:
			boolean flag=controller.Authenticate_login(username, pword);     //authenticate userid and password
			if(flag)
		     {
				
				mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
				if(!session.checkinfo(mPreferences))
				{
					Toast.makeText(Login.this,"Successfully Logged in", Toast.LENGTH_LONG).show();
					SharedPreferences.Editor editor=mPreferences.edit();
	                editor.putString("UserName", username);
	                editor.putString("PassWord", pword);
	                editor.commit();
	                Log.i("Login_Session", "User credentials are ok");
	               // System.out.println(session.getUsername(mPreferences)+"  "+session.getPassword(mPreferences)+"  ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
				}
				Log.i("Login_onClick", "It is redirecting to Journeydetails");
				Intent intent=new Intent(Login.this,JourneyDetails.class);
				startActivity(intent);
		     }
			 else
		     {
				 Log.i("Login_onClick", "Wrong id and password is typed");
		    	 TextView warn = (TextView)findViewById(R.id.loginwarn);
		    	 warn.setText("Please enter valid\nuser name and password");
		    	 
		    	
		     }
			break;
			
		}
		
	}
}