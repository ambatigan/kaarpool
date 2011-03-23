package com.saventech.karpool;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends  MenuOptions implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button login = (Button)findViewById(R.id.login);
        Button register = (Button)findViewById(R.id.register);
        
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        
        
        
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.login:
			Intent login = new Intent(MainActivity.this, Login.class);
			startActivity(login);
			break;
		case R.id.register:
            Intent register = new Intent(MainActivity.this, Register.class);
			startActivity(register);
			break;
		default:
			
		}
		
	}
}
