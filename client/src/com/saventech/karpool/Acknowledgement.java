/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Acknowledgement.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Acknowledgement extends Activity implements OnClickListener 
{
	private SharedPreferences mPreferences; 
	Session session;
	
	private ListView listview;
	
	ListAdapter adapter;

	int count=0;
	String popupmessage1="";
	String popupmessage2="";
	String popupmessage3="";
	Controller controller;
	private String globalrideidmessage="";
	/**
	 * This screen show the riders list for driver and driver can get notifications(accept/reject)
	 * also in this screen.
	 */
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        controller = new Controller();
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(Acknowledgement.this,Login.class);
			startActivity(intent);
		
		}
		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
        Log.i("DriverJourneyDetails_Acknowledgement","Acknowledgement tab in DriverJourneyDetails");
        session.storemode(mPreferences, "driver");
        ProgressDialog dialog = new ProgressDialog(this.getParent());
        dialog.setMessage("Authentication user details...");
        dialog.dismiss();
        setContentView(R.layout.driver_acknowledgements);
        dialog.dismiss();
        System.out.println("ddddddddddddddddddddddddd");

        listview=(ListView)findViewById(android.R.id.list);
        System.out.println("ddddddddddddddddddddddddd1");
        adapter=new ArrayAdapter<String>(this,R.layout.rows,R.id.text ,  DriverJourneyDetails.drivermeteormsg);
        System.out.println("ddddddddddddddddddddddddd2");
        
        if(DriverJourneyDetails.drivermeteormsg.size()!=0)
        {
        	listview.setAdapter(adapter);
        	System.out.println("arraylist in acknowledgement: "+DriverJourneyDetails.drivermeteormsg.get(0).toString());        	
            ((BaseAdapter) adapter).notifyDataSetChanged();
        }
        listview.setOnItemClickListener(new OnItemClickListener() {
        	
			public void onItemClick(AdapterView<?> a, View v, int position,	long id) {

				String message=(String) listview.getItemAtPosition(position);
				String message1=DriverJourneyDetails.driverrideid.get(position).toString().trim();
				//Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
				String messagepopups[]=message.split("FROM");
				String ridername=messagepopups[messagepopups.length-1].toString().trim();
				if(messagepopups[0].toString().trim().equals(getString(R.string.r1)))
				{
					popupmessage1=getString(R.string.d1);
					popupmessage2=getString(R.string.d2);
					setAlertbox(popupmessage1,popupmessage2,ridername,getString(R.string.r1),message,message1);
				}
				else if(messagepopups[0].toString().trim().equals(getString(R.string.r3))||messagepopups[0].toString().trim().equals(getString(R.string.r2))||messagepopups[0].toString().trim().equals(getString(R.string.r8)))
				{
					popupmessage3="OK";
					setAlertbox1(popupmessage3,ridername,messagepopups[0].toString().trim(),message,message1);
				}
				
				/*else if(messagepopups[0].toString().trim().equals(getString(R.string.d5)))
				{
					popupmessage1=getString(R.string.r4);
					popupmessage2=getString(R.string.r5);
					setAlertbox(popupmessage1,popupmessage2,ridername,messagepopups[0].toString().trim(),message);
				}
				else if(messagepopups[0].toString().trim().equals(getString(R.string.d6)))
				{
					popupmessage3=getString(R.string.r6);
					setAlertbox1(popupmessage3,ridername,messagepopups[0].toString().trim(),message);
				}*/
				else
				{
					Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
				}				
			}
        	});
        
    }
    public void removeMessage(String message)
    {
    	DriverJourneyDetails.drivermeteormsg.remove(message);
		 ((BaseAdapter) adapter).notifyDataSetChanged();
    }
    public String getDrivername(String dname)
    {
    	String drivername[]=dname.toString().trim().split("-");
    	return drivername[0].toString().trim();
    }
    public void setAlertbox1(final String msg3,String ridername,String displaymessage,final String message,String message1)
    {
    	/*if(displaymessage.toString().trim().equals(getString(R.string.r8)))
    	{
	    	for (Object data : DriverJourneyDetails.driverrideid) 
	    	{
	    	   if(parseTimeFromMessage(data.toString().trim()).toString().trim().equals(parseTimeFromMessage(message1.toString().trim())))
	    	   {
	    		   int position=DriverJourneyDetails.driverrideid.indexOf(data);
	    		   System.out.println(DriverJourneyDetails.drivermeteormsg.remove(position)+"  REMOVED MESSAGE IS");
	    		  ((BaseAdapter) adapter).notifyDataSetChanged();
	    		  System.out.println(DriverJourneyDetails.driverrideid.remove(data)+" REMOVED MESSAGE1 IS");
	    	   }
	    	}
    	}*/
    	AlertDialog.Builder adb=new AlertDialog.Builder(Acknowledgement.this.getParent());
    	
    	adb.setTitle(displaymessage.toString().trim());
    	adb.setMessage("Ridername  : "+getEntireRiderName(ridername)+"\nTime      : "+parseTimeFromMessage(message1));
    	adb.setPositiveButton(msg3.toString().trim(), new DialogInterface.OnClickListener() {
  			public void onClick(DialogInterface dialog, int whichButton) {
  				//value = input.getText().toString().trim();
  				removeMessage(message);
  				DriverJourneyDetails.driverrideid.remove(getRideId(message));
  				Toast.makeText(getApplicationContext(),msg3.toString().trim(),Toast.LENGTH_SHORT).show();

  			}
  		});
    	adb.show();
    }
    //not necessary
    public String getRideId(String message)
    {
    	for (Object data : DriverJourneyDetails.driverrideid) 
    	{
			 String messagecontains=(String)data.toString().trim();
			 System.out.println(message.toString().trim()+" ******************* "+data.toString().trim());
			 if(messagecontains.toString().trim().equals(message.toString().trim()))
			 {
				 System.out.println("*********************** "+messagecontains.toString().trim());
				 return messagecontains.toString().trim();
			 }
	    }
    	return "No String Found";
    }
    public void setAlertbox(final String msg1,final String msg2,final String ridername,String displaymessage,final String message,final String message1)
    {
    	AlertDialog.Builder adb=new AlertDialog.Builder(Acknowledgement.this.getParent());
    	
    	adb.setTitle(displaymessage.toString().trim());
    	adb.setMessage("Ridername  : "+getEntireRiderName(ridername)+"\nTime      : "+parseTimeFromMessage(message1));
  	  	adb.setPositiveButton(msg1.toString().trim(), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				//value = input.getText().toString().trim();
				Toast.makeText(getApplicationContext(),msg1.toString().trim(),Toast.LENGTH_SHORT).show();
				removeMessage(message);
				sendResponseMessage(message,ridername,msg1.toString().trim(),message1.toString().trim());
			}
		});
		adb.setNegativeButton(msg2.toString().trim(), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						removeMessage(message);
						sendResponseMessage(message,ridername,msg2.toString().trim(),message1.toString().trim());
						Toast.makeText(getApplicationContext(),msg2.toString().trim(),Toast.LENGTH_SHORT).show();
					}
				});
		adb.show();
    }
    public String getResponseId(String res)
    {
    	
    		if(res.toString().trim().equals(getString(R.string.d1)))
    			return "d1";
    		else if(res.toString().trim().equals(getString(R.string.d2)))
    			return "d2";
    		else if(res.toString().trim().equals(getString(R.string.d3)))
    			return "d3";
    		else if(res.toString().trim().equals(getString(R.string.d4)))
    			return "d4";
    		else if(res.toString().trim().equals(getString(R.string.d5)))
    			return "d5";
    		else if(res.toString().trim().equals(getString(R.string.d6)))
    			return "d6";
    		else
    			return "empty";
    					
    		
    	
    }
    public String getRid(String message)
    {
    	String splitoutput[]=message.toString().trim().split("::");
    	System.out.println("RID"+splitoutput[splitoutput.length-1]);
    	return splitoutput[splitoutput.length-1].toString().trim();
    }
    public String parseRideIdfromMessage(String message)
    {
    	String split[]=message.split("::");
    	return split[1].toString().trim();
    }
    public String parseTimeFromMessage(String msg)
    {
    	String split[]=msg.toString().trim().split("::");
    	return split[2].toString().trim();
    }
    public void sendResponseMessage(String message,String drivername,String respon,String message1)
    {
    	System.out.println("message: "+message);
    	String rideidmessage=getRideId(message1.toString().trim());
    	String rideid=parseRideIdfromMessage(rideidmessage.toString().trim());
    	String time=parseTimeFromMessage(message1.toString().trim());
    	System.out.println(rideidmessage+" *************888 "+rideid);
    	String res=getResponseId(respon);
		 //String rid=getRid(message);
		 String channelname=parseChannelName(session.getUsername(mPreferences));
		 ArrayList<String>even=new ArrayList<String>();
   
		 System.out.println("Injecting events: "+" ADDMESSAGE "+drivername+" "+channelname+"::"+res+"::"+rideid+"::"+time+"EVENT");
		 even.add("ADDMESSAGE EVENT"+"r"+drivername+" EVENT"+"d"+channelname+"::"+res+"::"+rideid+"::"+time.toString().trim()+"EVENT TNEVE");
		 DriverJourneyDetails.driverrideid.remove(message1.toString().trim());
		 String val=controller.injectAcknowledgeEvents(even);
		 if(val.trim().equals("successfully injected"))
		 {
			 System.out.println(val.toString().trim());
		 }
		 else
		 {
			 System.out.println("No message injected");
		 }
		 //System.out.println("ADDMESSAGE "+drivername+" "+channelname+"::"+res+"::"+rid.toString()+"EVENT");
    }
    public String getEntireRiderName(String name)
    {
   
    	String drivername[]=name.toString().trim().split("-");
    	return drivername[0].toString().trim()+"@"+drivername[1]+".com";
    	
    	
    }
    public String parseChannelName(String chname)
	{
		 String str1[]=chname.toString().trim().split("@");
		 String str2[]=str1[1].toString().trim().split(".com");
		 String channame=str1[0]+"-"+str2[0];
		 //System.out.println("Parsed channel name^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+channame);
		 return channame.toString().trim();
	 }
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	public void onClick(View v) {		
	}
}
