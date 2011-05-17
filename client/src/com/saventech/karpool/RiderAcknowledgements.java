package com.saventech.karpool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import android.app.Activity;
import android.app.AlertDialog;
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
/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderAcknowledgements.java
 * Date: Mar 25, 2011
 * It is responsible to display all request and responses messages
 */
public class RiderAcknowledgements extends Activity implements OnClickListener {
	
	private SharedPreferences mPreferences; 
	Session session;
	Controller controller;
	private ListView listview;
	//ArrayAdapter<String> adapter;
	ListAdapter adapter;
	String popupmessage1="";
	String popupmessage2="";
	String popupmessage3="";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Rideracknowledgements_activity", "Now you are in Rideracknowledgements activity");
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(RiderAcknowledgements.this,Login.class);
			startActivity(intent);
		
		}
		controller=new Controller();
		 session.storemode(mPreferences, "rider");
		System.out.println(session.getUsername(mPreferences)+"---"+session.getPassword(mPreferences));
        setContentView(R.layout.rider_acknowledgements);
        System.out.println("ddddddddddddddddddddddddd");
        //Button bb =(Button)findViewById(R.id.OkButton);
        //bb.setOnClickListener(this);
        //Button bb1 = (Button)findViewById(R.id.notificationbutton);
        //bb1.setOnClickListener(this);
        listview=(ListView)findViewById(android.R.id.list);
        System.out.println("ddddddddddddddddddddddddd1");
        adapter=new ArrayAdapter<String>(this,R.layout.rows,R.id.text ,  RiderJourneyDetails.ridermeteormsg);
        System.out.println("ddddddddddddddddddddddddd2");
        listview.setAdapter(adapter);
       //lv1.setTextFilterEnabled(true);
        /*adapter=new ArrayAdapter<String>(this,
        	    android.R.layout.simple_list_item_1,
        	    RiderJourneyDetails.ridermeteormsg);*/
        /*setListAdapter(adapter);*/
        if(RiderJourneyDetails.ridermeteormsg.size()!=0)
        {
        	System.out.println("arraylist in acknowledgement: "+RiderJourneyDetails.ridermeteormsg.get(0).toString());        	
            ((BaseAdapter) adapter).notifyDataSetChanged();
        }
        listview.setOnItemClickListener(new OnItemClickListener() {
        	

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				
				String message=(String) listview.getItemAtPosition(position);
				String message1="";
				if(RiderJourneyDetails.riderrideid.size()!=0)
				{
					message1=RiderJourneyDetails.riderrideid.get(position).toString().trim();
				}
				//String message1=RiderJourneyDetails.riderrideid.get(position).toString().trim();
				//Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
				String messagepopups[]=message.split("FROM");
				String drivername=messagepopups[messagepopups.length-1].toString().trim();
				if(messagepopups[0].toString().trim().equals(getString(R.string.d1)))
				{
					popupmessage1=getString(R.string.r2);
					popupmessage2=getString(R.string.r3);
					setAlertbox(popupmessage1,popupmessage2,drivername,getString(R.string.d1),message,message1);
				}
				else if(messagepopups[0].toString().trim().equals(getString(R.string.d3))||messagepopups[0].toString().trim().equals(getString(R.string.d4))||messagepopups[0].toString().trim().equals(getString(R.string.d2)))
				{
					popupmessage3="OK";
					setAlertbox1(popupmessage3,drivername,messagepopups[0].toString().trim(),message,message1);
				}
				else if(messagepopups[0].toString().trim().equals(getString(R.string.d5)))
				{
					popupmessage1=getString(R.string.r4);
					popupmessage2=getString(R.string.r5);
					setAlertbox(popupmessage1,popupmessage2,drivername,messagepopups[0].toString().trim(),message,message1);
				}
				else if(messagepopups[0].toString().trim().equals(getString(R.string.d6)))
				{
					popupmessage3=getString(R.string.r6);
					setAlertbox1(popupmessage3,drivername,messagepopups[0].toString().trim(),message,message1);
				}
				else
				{
					Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
				}
				
	        	/*AlertDialog.Builder adb=new AlertDialog.Builder(RiderAcknowledgements.this.getParent());
	        	  adb.setTitle("Message");
	        	adb.setMessage("Selected Item is = "+listview.getItemAtPosition(position));
	        	//adb.setPositiveButton("Ok", null);
	        	adb.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog, int whichButton) {
	    				//value = input.getText().toString().trim();
	    				Toast.makeText(getApplicationContext(),"Accepted",Toast.LENGTH_SHORT).show();
	    				
	    		    	//ed.setText(value);
	    			}
	    		});
	    		adb.setNegativeButton("Reject",
	    				new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog, int whichButton) {
	    						Toast.makeText(getApplicationContext(),"Accepted",Toast.LENGTH_SHORT).show();
	    					}
	    				});
	        	adb.show();*/
				// TODO Auto-generated method stub
				
			}
        	});
    }
    public String getResponseId(String res)
    {
    	
    		if(res.toString().trim().equals(getString(R.string.r2)))
    			return "r2";
    		else if(res.toString().trim().equals(getString(R.string.r3)))
    			return "r3";
    		else if(res.toString().trim().equals(getString(R.string.r4)))
    			return "r4";
    		else if(res.toString().trim().equals(getString(R.string.r5)))
    			return "r5";
    		else if(res.toString().trim().equals(getString(R.string.r6)))
    			return "r6";
    		else if(res.toString().trim().equals(getString(R.string.r7)))
    			return "r7";
    		else
    			return "empty";
    					
    		
    	
    }
    public String getRid(String message)
    {
    	String splitoutput[]=message.toString().trim().split("::");
    	System.out.println("RID"+splitoutput[splitoutput.length-1]);
    	return splitoutput[splitoutput.length-1].toString().trim();
    }
    public String parseTimeFromMessage(String msg)
    {
    	String split[]=msg.toString().trim().split("::");
    	return split[2].toString().trim();
    }
    public boolean timeDiffers(String fixedusertime, String restofusertime)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd h:mm a");
		  Date d = null;
		  Date d1=null;
		 // System.out.println(fixedusertime+"=========="+restofusertime);
		  try {
			   d = formatter.parse(fixedusertime.toString().trim());//catch exception
			  // System.out.println(d.getHours()+"  hhhhhhhhhhhhhh");
			   Calendar fixed= Calendar.getInstance();
			   fixed.setTime(d);
			   d1=formatter.parse(restofusertime.toString().trim());
			   Calendar rest = Calendar.getInstance();
			   rest.setTime(d1);
			   //System.out.println(fixed.getTime()+"  "+rest.getTime()+" "+fixed.getTimeInMillis()+" "+rest.getTimeInMillis()+"====="+fixedusertime+" "+restofusertime);
			   //System.out.println(thatDay.getTimeInMillis()+"   lllllllllllllllllllll"+d+" "+rideSeekingTime.toString().trim());
			   long dmil=fixed.getTimeInMillis()-rest.getTimeInMillis();	
			   System.out.println(dmil+" Minutes");
			   if( dmil/(60*1000)>=-5 && dmil/(60*1000)<=5)
			   {
					return true;
			   }
		  } catch (ParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		   Log.i("RiderRoute_checkMobiletime", "Exception occure while validating time in rider rotue");
		   return false;
		  } 
		  
	    return false;
    }
    public void sendResponseMessage(String message,String drivername,String respon,String message1)
    {
    	 String res=getResponseId(respon);
    	 String val="";
    	 int restnumber=0;
		 String rid=getRid(message);
		 String rideidmessage=getRideId(message1.toString().trim());
	     String rideid=parseRideIdfromMessage(rideidmessage.toString());
		 String channelname=parseChannelName(session.getUsername(mPreferences));
	     String time=parseTimeFromMessage(message1.toString().trim());
	     System.out.println(rideidmessage+" **************888 "+rideid);
		 
		 
		 
		 ArrayList<String>even=new ArrayList<String>();
		 System.out.println("ADDMESSAGE EVENT"+"d"+drivername+" EVENT"+"r"+channelname+"::"+res+"::"+rideid.toString()+"::"+time.toString().trim()+"EVENT");
		 even.add("ADDMESSAGE EVENT"+"d"+drivername+" EVENT"+"r"+channelname+"::"+res+"::"+rideid.toString()+"::"+time.toString().trim()+"EVENT TNEVE");
		 if(res.toString().trim().equals("r2"))
		 {
			 
			 if(time.toString().trim().contains("AM"))
			 {
				 String confirmuserentiretime[]=time.toString().trim().split("AM");
				 String confirmusertime[]=confirmuserentiretime[0].toString().trim().split(" ");
				 for( Iterator< String > it1 = RiderJourneyDetails.riderrideid.iterator(); it1.hasNext() ; )
				 {
					 String data=it1.next();
					 String splitmessagesdata[]=data.toString().trim().split("::");
					 if(splitmessagesdata[2].toString().trim().contains("AM"))
					 {
						 String restofuesrsentiretime[]=splitmessagesdata[2].toString().trim().split("AM");
						 String restofuserstime[]=restofuesrsentiretime[0].toString().trim().split(" ");
						 if(confirmusertime[0].toString().trim().equals(restofuserstime[0].toString().trim()))
						 {
							 if(timeDiffers(time.toString().trim(),splitmessagesdata[2].toString().trim()))
							 {
								 String rideid1="";
								 System.out.println(splitmessagesdata[0]+"------------------------");
								 String getdrivernames[]=splitmessagesdata[0].toString().trim().split("FROM");
								 String dname=getdrivernames[1].toString().trim();
								 String rideidmessage1=getRideId(data.toString().trim());
							     rideid1=parseRideIdfromMessage(rideidmessage1.toString());
							     String time1=parseTimeFromMessage(data.toString().trim());
								 System.out.println(dname+"============================================="+getdrivernames[0].toString().trim());
								 if(getdrivernames[0].toString().trim().equals(getString(R.string.d2))||dname.toString().trim().equals(drivername.toString().trim()))
								 {
									 
								 }
								 else
								 {
									 
								     even.add("ADDMESSAGE EVENT"+"d"+dname+" EVENT"+"r"+channelname+"::"+"r8"+"::"+rideid1.toString()+"::"+time1.toString().trim()+"EVENT TNEVE");
									 System.out.println(data+"      CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCAM");
									 System.out.println("ADDMESSAGE EVENT"+"d"+dname+" EVENT"+"r"+channelname+"::"+"r8"+"::"+rideid1.toString()+"::"+time1.toString().trim()+"EVENT TNEVE");
									 if(RiderJourneyDetails.ridermeteormsg.size()!=0)
									 {
										 int position=RiderJourneyDetails.riderrideid.indexOf(data);
										 RiderJourneyDetails.ridermeteormsg.remove(position);
										// restnumber=1;
										 ((BaseAdapter) adapter).notifyDataSetChanged();
									 }
									 it1.remove();
									 System.out.println(" Rideracknowledgement data removed");
									
								 }
								 for( Iterator< String > it = RiderJourneyDetails.justriderequests.iterator(); it.hasNext() ; )
                                 {

							            String str = it.next();

							            if( str.contains(rideid1.toString().trim() ) )

							            {

							                it.remove();

							            }

							      }
								 
							 }
						 }
					 }
				 }
			 }
			 if(time.toString().trim().contains("PM"))
			 {
				 String confirmuserentiretime[]=time.toString().trim().split("PM");
				 String confirmusertime[]=confirmuserentiretime[0].toString().trim().split(" ");
				 for( Iterator< String > it1 = RiderJourneyDetails.riderrideid.iterator(); it1.hasNext() ; )
				 {
					 String data=it1.next();
					 String splitmessagesdata[]=data.toString().trim().split("::");
					 if(splitmessagesdata[2].toString().trim().contains("PM"))
					 {
						 String restofuesrsentiretime[]=splitmessagesdata[2].toString().trim().split("PM");
						 String restofuserstime[]=restofuesrsentiretime[0].toString().trim().split(" ");
						 if(confirmusertime[0].toString().trim().equals(restofuserstime[0].toString().trim()))
						 {
							 if(timeDiffers(time.toString().trim(),splitmessagesdata[2].toString().trim()))
							 {
								 String rideid1="";
								 String getdrivernames[]=splitmessagesdata[0].toString().trim().split("FROM");
								 String dname=getdrivernames[1].toString().trim();
								 String rideidmessage1=getRideId(data.toString().trim());
							     rideid1=parseRideIdfromMessage(rideidmessage1.toString());
							     String time1=parseTimeFromMessage(data.toString().trim());
								 System.out.println(dname+"  "+getString(R.string.d2)+"======="+getdrivernames[0].toString().trim()+"======="+drivername.toString().trim());
								 if(getdrivernames[0].toString().trim().equals(getString(R.string.d2))||dname.toString().trim().equals(drivername.toString().trim()))
								 {
									 
								 }
								 else
								 {
									 
								     even.add("ADDMESSAGE EVENT"+"d"+dname+" EVENT"+"r"+channelname+"::"+"r8"+"::"+rideid1.toString()+"::"+time1.toString().trim()+"EVENT TNEVE");
									 System.out.println(data+"      CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCPM");
									 System.out.println("ADDMESSAGE EVENT"+"d"+dname+" EVENT"+"r"+channelname+"::"+"r8"+"::"+rideid1.toString()+"::"+time1.toString().trim()+"EVENT TNEVE");
									 int position=RiderJourneyDetails.riderrideid.indexOf(data);
									 if(RiderJourneyDetails.ridermeteormsg.size()!=0)
									 {
										// RiderJourneyDetails.ridermeteormsg.remove(position);
										 System.out.println( RiderJourneyDetails.ridermeteormsg.remove(position)+"jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjss");
										 ((BaseAdapter) adapter).notifyDataSetChanged();
									 }
									 it1.remove();
									 System.out.println(" Rideracknowledgement data removed");
									 //RiderJourneyDetails.riderrideid.remove(data.toString().trim());
									 
									 /*for(Object data1: RiderJourneyDetails.justriderequests)
									 {
										 if(data1.toString().trim().contains(rideid1.toString().trim()))
										 {
										   RiderJourneyDetails.justriderequests.remove(data1.toString().trim());
										 }
									 }*/
									 
								 }
								 for( Iterator< String > it = RiderJourneyDetails.justriderequests.iterator(); it.hasNext() ; )
                                 {

							            String str = it.next();

							            if( str.contains(rideid1.toString().trim() ) )

							            {
							            	System.out.println(str+"   Rideracknowledgementssssssssssssss");

							                it.remove();

							            }

							        }
								 
							 }
						 }
					 }
				 }
			 }
		 }
		 if(res.toString().trim().equals("r2"))
		 {
			 System.out.println(RiderJourneyDetails.justriderequests.size()+"UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUuuddddddd");
			 if(RiderJourneyDetails.justriderequests.size()!=0)
			 {
				 for( Iterator< String > it2 = RiderJourneyDetails.justriderequests.iterator(); it2.hasNext() ; )
				 {
					String data1=it2.next();
					//System.out.println(data1+"UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"+rideid.toString().trim());
					 if(data1.toString().trim().contains(rideid.toString().trim()))
					 {
						// System.out.print(data1+"bbbbbbbbbbbbbbbbbbbbbbbbbb"+rideid.toString().trim());
					   it2.remove();
					 }
					 else
					 {
						 String dname=getDriverNameFromMessage(data1.toString().trim());
						// System.out.println(dname+"0000000000000000");
					     String rideid2=parseRideIdfromMessage(data1.toString().trim());
						 channelname=parseChannelName(session.getUsername(mPreferences));
					     time=getTimeFromMessage(data1.toString().trim());
					     even.add("ADDMESSAGE EVENT"+dname+" EVENT"+"r"+channelname+"::"+"r8"+"::"+rideid2.toString()+"::"+time.toString().trim()+"EVENT TNEVE");
					     it2.remove();
					   
					     //RiderJourneyDetails.justriderequests.remove(data1.toString().trim());
						 
					 }
				 } 
			 }
		 }
		 if(restnumber==0)
		 {
		   RiderJourneyDetails.riderrideid.remove(message1.toString().trim());
		 }
		 
		 val=controller.injectAcknowledgeEvents(even);
		 if(val.equals("successfully injected"))
		 {
			 System.out.println(val.toString().trim());
		 }
		 else
		 {
			 System.out.println("No message injected");	
			 
		 }
		 //System.out.println("ADDMESSAGE "+drivername+" "+channelname+"::"+res+"::"+rid.toString()+"EVENT");
    }
    
    public String parseRideIdfromMessage(String message)
    {
    	String split[]=message.split("::");
    	return split[1].toString().trim();
    }
    public String getRideId(String message)
    {
    	for (Object data : RiderJourneyDetails.riderrideid) 
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
    public void removeMessage(String message,String drivername,String respon)
    {
    	RiderJourneyDetails.ridermeteormsg.remove(message);
		 ((BaseAdapter) adapter).notifyDataSetChanged();
		 
		// controller.sendMessage("ADDMESSAGE "+drivername+" "+channelname+"::"+res+"::"+rid.toString());
    }
    public String parseChannelName(String chname)
	 {
		 String str1[]=chname.toString().trim().split("@");
		 String str2[]=str1[1].toString().trim().split(".com");
		 String channame=str1[0]+"-"+str2[0];
		 //System.out.println("Parsed channel name^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+channame);
		 return channame.toString().trim();
	 }
    public String getDriverNameFromMessage(String Message)
    {
    	String split[]=Message.toString().trim().split("::");
    	System.out.println(split[0]+"kddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddgg");
    	String split1[]=split[0].toString().trim().split(" ");
    	return split1[1].toString().trim();
    }
    public String getTimeFromMessage(String Message)
    {
    	String split[]=Message.toString().trim().split("::");
    	return split[3].toString().trim();
    }
    public String getDrivername(String dname)
    {
    	String drivername[]=dname.toString().trim().split("-");
    	return drivername[0].toString().trim();
    	
    }
    public String getEntireDrivername(String msg)
    {
    	
    	String drivername[]=msg.toString().trim().split("-");
    	return drivername[0].toString().trim()+"@"+drivername[1]+".com";
    	
    	
    }
    public void setAlertbox1(final String msg3,final String drivername,String displaymessage,final String message,final String message1)
    {
    	AlertDialog.Builder adb=new AlertDialog.Builder(RiderAcknowledgements.this.getParent());
    	
    	adb.setTitle(displaymessage.toString().trim());
    	adb.setMessage("Drivername  : "+getEntireDrivername(drivername)+"\nTime        : "+parseTimeFromMessage(message1));
    	//adb.setPositiveButton("Ok", null);
    	adb.setPositiveButton(msg3.toString().trim(), new DialogInterface.OnClickListener() {
  			public void onClick(DialogInterface dialog, int whichButton) {
  				//value = input.getText().toString().trim();
  				removeMessage(message,drivername,msg3.toString().trim());
  				if(msg3.toString().trim().equals(getString(R.string.r6)))
  				{
    		         sendResponseMessage(message,drivername,msg3.toString().trim(),message1.toString().trim());
    		
  				}
  				RiderJourneyDetails.riderrideid.remove(message1.toString().trim());
  				//sendResponseMessage(message,drivername,msg3.toString().trim());
  				Toast.makeText(getApplicationContext(),msg3.toString().trim(),Toast.LENGTH_SHORT).show();
  				
  		    	//ed.setText(value);
  			}
  		});
    	adb.show();
    }
    public void setAlertbox(final String msg1,final String msg2,final String drivername,String displaymessage,final String message,final String message1)
    {
    	AlertDialog.Builder adb=new AlertDialog.Builder(RiderAcknowledgements.this.getParent());
    	
    	adb.setTitle(displaymessage.toString().trim());
    	adb.setMessage("Drivername  : "+getEntireDrivername(drivername)+"\nTime        : "+parseTimeFromMessage(message1));
  	//adb.setPositiveButton("Ok", null);
  	adb.setPositiveButton(msg1.toString().trim(), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				//value = input.getText().toString().trim();
				Toast.makeText(getApplicationContext(),msg1.toString().trim(),Toast.LENGTH_SHORT).show();
				
				removeMessage(message,drivername,msg1.toString().trim());
				sendResponseMessage(message,drivername,msg1.toString().trim(),message1.toString().trim());
		    	//ed.setText(value);
			}
		});
		adb.setNegativeButton(msg2.toString().trim(),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						removeMessage(message,drivername,msg2.toString().trim());
						sendResponseMessage(message,drivername,msg2.toString().trim(),message1.toString().trim());
						Toast.makeText(getApplicationContext(),msg2.toString().trim(),Toast.LENGTH_SHORT).show();
					}
				});
  	adb.show();
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
		// TODO Auto-generated method stub
		
	}
    
    /*private class OnReadyListener implements RatingDialog.ReadyListener {
        public void ready(String name) {
            Toast.makeText(RiderAcknowledgements.this, name, Toast.LENGTH_LONG).show();
        }
    }*/
    /*
     * onClick will be performed when ever a specified button is pressed
     */

	/*public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v==findViewById(R.id.OkButton))
		{
			RatingDialog myDialog = new RatingDialog(this.getParent(), "", new OnReadyListener());
		    myDialog.show();
		}
		if(v==findViewById(R.id.notificationbutton))
		{
			NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	    	
	    	int icon = R.drawable.ic_tab_artists_white;
	    	CharSequence text = "pick up request";
	    	CharSequence contentTitle = "Ride pickup request from Roshan";
	    	CharSequence contentText = "Request has been accepted. Please confirm pickup";
	    	long when = System.currentTimeMillis();
	    	
	    	//RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
            //ParentActivity.switchTab(2);
	    	Intent intent = new Intent(this, JourneyDetails.class);
	    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
	    	Notification notification = new Notification(icon,text,when);
	    	
	    	//notification.defaults |= Notification.FLAG_AUTO_CANCEL;
	    	//notification.defaults |= Notification.DEFAULT_SOUND;
	    	notification.flags = Notification.DEFAULT_SOUND | Notification.FLAG_AUTO_CANCEL;
	    	
	    	long[] vibrate = {0,100,200,300};
	    	notification.vibrate = vibrate;
	    	
	    	notification.ledARGB = Color.RED;
	    	notification.ledOffMS = 300;
	    	notification.ledOnMS = 300;
	    	
	    	notification.defaults |= Notification.DEFAULT_LIGHTS;
	    	//notification.flags |= Notification.FLAG_SHOW_LIGHTS;
	    	
	    	notification.setLatestEventInfo(this, contentTitle, contentText, contentIntent);
	    	
	    	notificationManager.notify(1001, notification);
	    	RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
            ParentActivity.switchTab(2);
		}
		
	}*/
}