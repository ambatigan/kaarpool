package com.saventech.karpool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
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
	TextView warn;
	Controller controller=null;
	private String systemid="@kaarpool.com";

	private SharedPreferences mPreferences; 

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.login);
        
        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
        @SuppressWarnings("unused")
		SharedPreferences.Editor editor=mPreferences.edit();
       
        System.out.println("DATA REMOVED");
        Log.i("Login_Activity", "Now you are in login activity");
        session=new Session();
        session.removeSession(mPreferences);
        controller=new Controller();                //initializing controller object
        userid = (EditText)findViewById(R.id.useridlogintxt);
        userid.setOnFocusChangeListener(new OnFocusChangeListener() 
        {

	        	public void onFocusChange(View v, boolean hasFocus) 
	        	{
	        		focus(v);
	        	    
	        	}
         });
        pwd = (EditText)findViewById(R.id.pwdlogintxt);
        findViewById(R.id.loginbutton).setOnClickListener(this);
        warn = (TextView)findViewById(R.id.loginwarn);
    }
    public void focus(View v)
    {
    	Log.i("Registerwithsysid_availableusers", "Checking for available users");
	     if(userid.getText().toString().length()!=0)
	     {
	    	 if(userid.getText().toString().contains(systemid))
	    		 userid.setText(userid.getText().toString());
	    	 else
	    		 userid.setText(userid.getText().toString()+systemid);
	     }
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent intent=new Intent(Login.this,MainLogin.class);
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
			if(username.length() != 0 && pword.length() != 0)
			{
				ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage("Authentication user details...");
                dialog.setIndeterminate(true);
                dialog.setCancelable(true);
                dialog.show();
				String authenticate =controller.Authenticate_login(username, pword);     //authenticate userid and password
		
				System.out.println(authenticate+"authenticateauthenticate ");
				if(authenticate.toString().trim().equals("YES"))
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
					}
					Log.i("Login_onClick", "It is redirecting to Journeydetails");
					Intent intent=new Intent(Login.this,JourneyDetails.class);
					intent.putExtra("RegisterUsername", "loginid");
					intent.putExtra("RegisterPassword", "loginpwd");
					intent.putExtra("receiver", "notify");
					startActivity(intent);
	    	    	Log.i("Login_Controller", "Login values are authenticated");
    	    	
    	    }
			else if(authenticate.toString().trim().equals("NO"))
    	    {
				System.out.println(authenticate.toString());
    	    	Toast.makeText(Login.this,"User with this user name doesnot exist", Toast.LENGTH_LONG).show();
    	    	Log.i("Login_Controller", "User with this user name doesnot exist");
    	    	dialog.dismiss();
    	    	
    	    }
    	    else if(authenticate.toString().trim().equals("NOT_Exist"))
    	    {
    	    	System.out.println(authenticate.toString());
    	    	Toast.makeText(Login.this,"User with this user name doesnot exist", Toast.LENGTH_LONG).show();
    	    	Log.i("Login_Controller", "User with this user name doesnot exist");
    	    	dialog.dismiss();
    	    	
    	    }
    	    else if(authenticate.toString().trim().equals("Please_check_the_connection"))
    	    {
    	    	System.out.println(authenticate.toString());
    	    	Toast.makeText(Login.this,"Please check the server connection", Toast.LENGTH_LONG).show();
    	    	Log.i("Login_Controller", "No connection to the server");
    	    	dialog.dismiss();
    	    }
			//dialog.dismiss();
			
		}
			else
			{
				Toast.makeText(Login.this,"Please enter username/password", Toast.LENGTH_LONG).show();
		    	Log.i("Login_Controller", "No connection to the server");
			}
			 
		}
		
		
	}
	
}