package com.saventech.karpool;





import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class TravelPref extends Activity
{
	ListView lView;
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.customtravelpref);
    
    final String preferences[]={"Only Ladies","Only gents","Music","Smoke","With Children"};
    LinearLayout l = (LinearLayout) findViewById(R.id.mylayout1);
     LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    // System.out.println("ddddddddddddddddddddddddddddddddddddd");
     for (int i = 0; i < preferences.length; i++) 
     {
         View customView = linflater.inflate(R.layout.travelpref,
             null);
         TextView tv = (TextView) customView.findViewById(R.id.travelpreferences);
         CheckBox check=(CheckBox)customView.findViewById(R.id.travelprefcheckbox);
         tv.setId(i);
         check.setId(i);
         tv.setText(preferences[i].toString());
       //  System.out.println("ddddddddddddddddddddddddddddddddddddd");
         l.addView(customView);
        // System.out.println("ddddddddddddddddddddddddddddddddddddd");
     }
    		
    }

	
    
}
