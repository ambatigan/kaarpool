package com.saventech.karpool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Ridergetridelist extends Activity implements android.view.View.OnClickListener {

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.ridelist0);
	        LinearLayout l = (LinearLayout) findViewById(R.id.mylayout1);
	        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        for (int i = 0; i < 4; i++) 
	        {
	            View customView = linflater.inflate(R.layout.ridelist,
	                null);
	            TextView route = (TextView) customView.findViewById(R.id.route);
	            TextView rate = (TextView) customView.findViewById(R.id.rate);
	            route.setText("   Route: IIIT to Secrateriate");
	            rate.setText("   Rate:");
	            
	            //findViewById(R.id.image).setOnClickListener(this);
	            ImageButton img=(ImageButton)customView.findViewById(R.id.image);
	            img.setOnClickListener(this);
	            CheckBox check=(CheckBox)customView.findViewById(R.id.ridelistcheckbox);
	            switch(i)
	            {
	            case 0:
	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image1));
	            	break;
	            case 1:
	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image2));
	            	break;
	            case 2:
	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image3));
	            	break;
	            case 3:
	            	img.setImageDrawable(getResources().getDrawable(R.drawable.image4));
	            	break;
	            }
	            route.setId(i);
	            rate.setId(i);
	            check.setId(i);
	            img.setId(i);
	           
	            
	            
	            l.addView(customView);
	        }
	        }
	        public void onClick(View V) 
	    	{
	        	//System.out.println("naggessssssssssssssssssssssss");
	    		switch(V.getId())
	    		{
	    		case 0:
	    			     //Toast.makeText(this, "Pressed first icon", Toast.LENGTH_LONG).show();
	    			     
	    			     final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
	    					final TextView input = new TextView(this);
	    					input.setText("\tName\t\t\t:Rajesh\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n");
	    					alert.setView(input);
	    					
	    					alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    						public void onClick(DialogInterface dialog, int whichButton) {
	    							//String value = input.getText().toString().trim();
	    							return;
	    						}
	    					});
	    					
	    						alert.show();
	    				        
	    				   
	    					
	    				break;
	    		case 1:
	    			final AlertDialog.Builder alert1 = new AlertDialog.Builder(this.getParent());
	    			final TextView input1 = new TextView(this);
	    			input1.setText("\tName\t\t\t:Nagesh\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n");
	    			alert1.setView(input1);
	    			
	    			alert1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int whichButton) {
	    					//String value = input.getText().toString().trim();
	    					return;
	    				}
	    			});
	    			alert1.show();
	    				break;
	    		case 2:
	    			final AlertDialog.Builder alert2 = new AlertDialog.Builder(this.getParent());
	    			final TextView input2 = new TextView(this);
	    			input2.setText("\tName\t\t\t:Mahesh\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n");
	    			alert2.setView(input2);
	    			
	    			alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int whichButton) {
	    					//String value = input.getText().toString().trim();
	    					return;
	    				}
	    			});
	    			alert2.show();
	    				break;
	    		case 3:
	    			final AlertDialog.Builder alert3 = new AlertDialog.Builder(this.getParent());
	    			final TextView input3 = new TextView(this);
	    			input3.setText("\tName\t\t\t:Somesh\n\tSource\t\t:IIIT\n\tDestination  :Secrateriate\n");
	    			alert3.setView(input3);
	    			
	    			alert3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int whichButton) {
	    					//String value = input.getText().toString().trim();
	    					return;
	    				}
	    			});
	    			alert3.show();
	    				break;
	    		}
	    		
	    		
	    	}
	    }