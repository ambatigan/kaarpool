package com.saventech.karpool;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Cancelroute extends Activity implements OnClickListener {
	private EditText source;
	@SuppressWarnings("unused")
	private EditText destination;
	@SuppressWarnings("unused")
	private EditText starttime;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cancelroute);
        source=(EditText)findViewById(R.id.drivercancelroutesource);
        destination=(EditText)findViewById(R.id.drivercancelroutedestination);
        starttime=(EditText)findViewById(R.id.drivercancelroutestarttime);
        
        findViewById(R.id.drivercancelroute).setOnClickListener(this);
        
    }

	public void onClick(View v) {
		
		switch(v.getId())
		{
		case R.id.drivercancelroute:
			Toast.makeText(this, "Route has been canceled", Toast.LENGTH_LONG).show();
			source.clearFocus();
		}
		
	}
    
}