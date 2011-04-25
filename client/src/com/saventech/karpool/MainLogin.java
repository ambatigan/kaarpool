package com.saventech.karpool;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainLogin extends  MenuOptions implements OnClickListener{
    /** Called when the activity is first created. */
	private ImageButton kaarpoolbutton;
	private ImageButton facebookbutton;
	private ImageButton gmailbutton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.loginwithdifferentids);
        kaarpoolbutton=(ImageButton)findViewById(R.id.mainloginsysgen);
        facebookbutton=(ImageButton)findViewById(R.id.facebook);
        gmailbutton=(ImageButton)findViewById(R.id.gmail);
        kaarpoolbutton.setOnClickListener(this);
        facebookbutton.setOnClickListener(this);
        gmailbutton.setOnClickListener(this);
        
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent intent=new Intent(MainLogin.this,MainActivity.class);
			startActivity(intent);
			//return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.mainloginsysgen:
			Intent intent=new Intent(MainLogin.this,Login.class);
			startActivity(intent);
			break;
		case R.id.facebook:
			Toast.makeText(MainLogin.this, "Pressed faceboook button", Toast.LENGTH_LONG).show();
			break;
		case R.id.gmail:
			Toast.makeText(MainLogin.this, "Pressed gmail button", Toast.LENGTH_LONG).show();
			break;
		}
		
	}
}