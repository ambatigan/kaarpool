package com.saventech.karpool;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Registerwithopenid extends MenuOptions implements OnClickListener
{
	String username = null;
	private EditText userid;
	private EditText pwd;
	String pword = null;
	Controller controller=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerwithopenid);
        controller=new Controller();
         userid = (EditText)findViewById(R.id.useridtxt);
         pwd = (EditText)findViewById(R.id.pwdtxt);
        
        
        
        Button register = (Button)findViewById(R.id.registerwithopenid);
//        ImageButton fbook = (ImageButton)findViewById(R.id.facebook);
//        ImageButton gmail = (ImageButton)findViewById(R.id.gmail);
//        ImageButton twitter = (ImageButton)findViewById(R.id.twitter);
//        
//        fbook.setOnClickListener(this);
//        gmail.setOnClickListener(this);
//        twitter.setOnClickListener(this);
        register.setOnClickListener(this);
        
       
        
    }

	public void onClick(View v) 
	{
				// TODO Auto-generated method stub
				
				username = userid.getText().toString().trim();
		        pword = pwd.getText().toString().trim();
					  //Toast.makeText(this, "Pressed first icon", Toast.LENGTH_LONG).show();
		        switch(v.getId())
		        {
		        		case R.id.registerwithopenid:
					        boolean flag=controller.Authenticate_register(username, pword);
							if(flag)
						     {
									Intent login = new Intent(Registerwithopenid.this, JourneyDetails.class);
									startActivity(login);	
							 }
						     else
						     {
						    	 TextView warn = (TextView)findViewById(R.id.warn);
						    	 warn.setText("Please enter valid\nuser name and password");
						    	// Toast.makeText(this, "Pressed first icon", Toast.LENGTH_LONG).show();
						     }
							break;
		        }	
		
	}

}
