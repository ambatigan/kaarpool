package com.saventech.karpool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/** Class Must extends with Dialog */
/** Implement onClickListener to dismiss dialog when OK Button is pressed */
public class CustomizeDialog extends Dialog implements OnClickListener {

	  public interface ReadyListener {
	        public void ready(String name);
	    }

	    private ReadyListener readyListener;
	    EditText etName;
	 
	    public CustomizeDialog(Context context, String name, ReadyListener readyListener) {
	        super(context);
	        this.readyListener = readyListener;
	    }

	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mycustomdialog);
	        setTitle("Enter Password ");
	        Button buttonOK = (Button) findViewById(R.id.Button01);
	        buttonOK.setOnClickListener(new OKListener());
	        etName = (EditText) findViewById(R.id.EditText01);
	    }

	    private class OKListener implements android.view.View.OnClickListener {
	        public void onClick(View v) {
	            readyListener.ready(String.valueOf(etName.getText()));
	            
	            CustomizeDialog.this.dismiss();
	        }
	    }

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}
}
