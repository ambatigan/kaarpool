package com.saventech.karpool;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;


public class ForceAcknowledge extends Activity {
	private SharedPreferences mPreferences; 
	 Session session;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.forceacknowledge);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
        
        final TextView leftText = (TextView) findViewById(R.id.left_text);
        final TextView rightText = (TextView) findViewById(R.id.right_text);

        leftText.setText("Kaarpool");
        leftText.setTypeface(null, Typeface.BOLD);
        
        session=new Session();
        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
        String username = session.getUsername(mPreferences);
        String name[]= username.split("@");
        rightText.setText(name[0]);
        
    }

}
