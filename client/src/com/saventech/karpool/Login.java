package com.saventech.karpool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
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
	String pword = null;
	Controller controller=null;                     //declaring controller object. Responsable to take data from data base and provid to this activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.login);
        Log.i("Login_Activity", "Now you are in login activity");

        controller=new Controller();                //initializing controller object
        userid = (EditText)findViewById(R.id.useridlogintxt);
        pwd = (EditText)findViewById(R.id.pwdlogintxt);
        findViewById(R.id.loginbutton).setOnClickListener(this);
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
			boolean flag=controller.Authenticate_login(username, pword);     //authenticate userid and passord
			if(flag)
		     {
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