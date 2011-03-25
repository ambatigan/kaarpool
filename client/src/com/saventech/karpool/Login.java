package com.saventech.karpool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends  MenuOptions implements OnClickListener{
    /** Called when the activity is first created. */
	private EditText userid;
	private EditText pwd;
	String username = null;
	String pword = null;
	Controller controller=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.login);

        controller=new Controller();
        userid = (EditText)findViewById(R.id.useridlogintxt);
        pwd = (EditText)findViewById(R.id.pwdlogintxt);
        findViewById(R.id.loginbutton).setOnClickListener(this);
}

	public void onClick(View v)
	{
		username = userid.getText().toString().trim();
        pword = pwd.getText().toString().trim();
		switch(v.getId())
		{
		case R.id.loginbutton:
			boolean flag=controller.Authenticate_login(username, pword);
			if(flag)
		     {
				 Intent intent=new Intent(Login.this,JourneyDetails.class);
				 startActivity(intent);
		     }
			 else
		     {
		    	 TextView warn = (TextView)findViewById(R.id.loginwarn);
		    	 warn.setText("Please enter valid\nuser name and password");
		    	// Toast.makeText(this, "Pressed first icon", Toast.LENGTH_LONG).show();
		     }
			break;
			
		}
		
	}
}