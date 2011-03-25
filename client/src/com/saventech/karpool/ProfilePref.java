package com.saventech.karpool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfilePref extends  CommonMenuOption implements OnClickListener {
    /** Called when the activity is first created. */
    String value="";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepref);
        
        Button bb =(Button)findViewById(R.id.change);
        bb.setOnClickListener(this);
        Button back =(Button)findViewById(R.id.backpref);
        back.setOnClickListener(this);
        Button save =(Button)findViewById(R.id.savepropref);
        save.setOnClickListener(this);
    }

    @SuppressWarnings("unused")
	private class OnReadyListener implements CustomizeDialog.ReadyListener {
        public void ready(String name) {
        	EditText ed = (EditText)findViewById(R.id.pwd);
        	ed.setText(name);
            Toast.makeText(ProfilePref.this, name, Toast.LENGTH_LONG).show();
        }
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	    
	    switch(v.getId())
		{
		case R.id.change:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			final EditText input = new EditText(this);
			builder.setView(input);
			builder.setMessage("Enter password")
			       .setCancelable(false)
			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   value = input.getText().toString().trim();
			        	   EditText ed = (EditText)findViewById(R.id.pwd);
					    	ed.setText(value);
			           }
			       });
			       
			AlertDialog alert = builder.create();
			alert.show();
			break;
		case R.id.backpref:
            Intent backpref = new Intent(ProfilePref.this, Preferences.class);
			startActivity(backpref);
			break;
		case R.id.savepropref:
            Intent savepropref = new Intent(ProfilePref.this, Preferences.class);
			startActivity(savepropref);
			break;
	}
}
}