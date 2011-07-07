/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Acknowledgement.java
 * Date: Mar 25, 2011
 */

package com.saventech.karpool;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.DateFormat;
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
	private double fromLat;
	private double fromLon;
	private double toLat;
	private double toLon;
	private String timeToDestination;
	private int distanceToDestination;
	public double lat;
	public double lng;
	public String rideid;
	private SharedPreferences mPreferences; 
	Session session;
	private LocationManager locationManager;
	
	private ListView listview;
	
	ListAdapter adapter;

	int count=0;
	String popupmessage1="";
	String popupmessage2="";
	String popupmessage3="";
	Controller controller;
	private String gpsridername;
	private String gpsridetime;
	//private String globalrideidmessage="";
	/**
	 * This screen show the riders list for driver and driver can get notifications(accept/reject)
	 * also in this screen.
	 */
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        controller = new Controller();
        session=new Session();
        
        /*Calendar cal = Calendar.getInstance();
		long date = cal.getTimeInMillis();
		System.out.println("Date: "+date);*/
        
        Date d = new Date();
        CharSequence s  = DateFormat.format("yyyy/MM/dd h:mm a", d.getTime());
        String str = (String) s;
        System.out.println("Time: "+str);
        
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
        removeRideFixedConfirmations();
        listview.setOnItemClickListener(new OnItemClickListener() {
        	
			public void onItemClick(AdapterView<?> a, View v, int position,	long id) {

				String message=(String) listview.getItemAtPosition(position);
				System.out.println("driver acknowldgement message: "+ message);
				String message1="";
				if(DriverJourneyDetails.driverrideid.size()!=0)
				{
				    message1=DriverJourneyDetails.driverrideid.get(position).toString().trim();
				}
				System.out.println("message1 in Driver acknowledgement: "+message1);
				//Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
				String messagepopups[]=message.split("FROM");		
				String ridername=messagepopups[messagepopups.length-1].toString().trim();
				gpsridername = ridername;
				gpsridetime = parseTimeFromMessage(message1.toString().trim());
				if(messagepopups[0].toString().trim().equals(getString(R.string.r1)))
				{
					popupmessage1=getString(R.string.d1);
					popupmessage2=getString(R.string.d2);
					setAlertbox(popupmessage1,popupmessage2,ridername,getString(R.string.r1),message,message1);
				}
				else if(messagepopups[0].toString().trim().equals(getString(R.string.r3))||messagepopups[0].toString().trim().equals(getString(R.string.r2))||messagepopups[0].toString().trim().equals(getString(R.string.r4))||messagepopups[0].toString().trim().equals(getString(R.string.r5)))
				{
					System.out.println("i am in message popups: if condition ");
					popupmessage3="OK";
					setAlertbox1(popupmessage3,ridername,messagepopups[0].toString().trim(),message,message1);
					if(messagepopups[0].toString().trim().equals(getString(R.string.r2)))
					{
						JourneyDetails.check = checkTime_GPS(ridername, parseTimeFromMessage(message1).trim());
						rideid = getRideid(message1).trim();
						if(JourneyDetails.check)
						{
							System.out.println("below 30 mins");
							//storeCoordinates(rideid);
							locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
					    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
					    	        0, new GeoUpdateHandler());
						}
						else
							System.out.println("greater than 30 mins");
					}
					if(messagepopups[0].toString().trim().equals(getString(R.string.r4)))
					{
						System.out.println("completed pickup handshake and ready to send drop request");
						rideid = getRideid(message1).trim();
						gpsTrackingDrop(rideid);
					}
				}
				else if(messagepopups[0].toString().trim().equals(getString(R.string.r8)))
				{
					System.out.println("i am in message popups: if condition (Ride fixed with another users)");
					popupmessage3=getString(R.string.r8);
					setAlertbox2(popupmessage3,ridername,messagepopups[0].toString().trim(),message,message1);
					
				}
				
				else if(messagepopups[0].toString().trim().equals(getString(R.string.d5)))
				{
					popupmessage1=getString(R.string.d5);
					popupmessage2=getString(R.string.d2);
					setAlertbox(popupmessage1,popupmessage2,ridername,messagepopups[0].toString().trim(),message,message1);
				}/*
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
    /*public void storeCoordinates(String rideid)
    {
    	System.out.println("i am in storecoordinates");
    	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
    	        0, new GeoUpdateHandler());
    }*/
    public void gpsTrackingDrop(String id_ride)
    {
    	System.out.println("rideid:"+id_ride);
    	String ride_dest = controller.getrideDestination(id_ride);
    	System.out.println("ride destination: "+ride_dest);
    }
    public boolean checkTime_GPS(String ridername, String ridetime) 
    {
    	System.out.println("I am in checkTime_GPS");
        ridername = getEntireRiderName(ridername);
        //String drivername = session.getUsername(mPreferences);
        Calendar today=Calendar.getInstance();
		System.out.println(today.getTimeInMillis()+"kkkkkkkkkkkkkkkkkk"+today.getTime());
		String str = today.getTime().toString();
		System.out.println("Time in String format: "+str);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd h:mm a");
		Date d = null;
		try 
		{
			   d = formatter.parse(ridetime.toString().trim());//catch exception
			   
			  // System.out.println(d.getHours()+"  hhhhhhhhhhhhhh");
			   Calendar thatDay = Calendar.getInstance();
			   thatDay.setTime(d);
			   //System.out.println(thatDay.getTimeInMillis()+"  lllllllllllllllllllll"+d+" "+rideSeekingTime.toString().trim());
			   long dmil=thatDay.getTimeInMillis()-today.getTimeInMillis();	
			   System.out.println(dmil+" Minutes");
			   if( dmil/(60*1000)<=30)
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
        //String response = controller.getCurrentRideTime(drivername, ridername, cur_time);
        //System.out.println("Get ride time response: "+response);
		
	}
    
    /*
     *  first get the fixed ride message ride ids and channelnames then removes all messages except fixed ride message based on ride id and 
     *  channelname.
     */
    public void  removeRideFixedConfirmations()
    {
    	ArrayList<String>chname_rideid=new ArrayList<String>();
    	
    	for (Object data : DriverJourneyDetails.driverrideid) 
    	{
			 String messagecontains=(String)data.toString().trim();
			 String splitmessage[]=messagecontains.split("::");
			 String msgs[]=splitmessage[0].toString().trim().split("FROM");
			 System.out.println(msgs[0]+"     9999999999999999999999999999999999999999999999999999999999999999999999999999999999");
			 if(msgs[0].toString().trim().equals(getString(R.string.r8)))
			 {
				 System.out.println(msgs[1].toString().trim()+"::"+splitmessage[1].toString().trim()+"     000000000000000000000000000000000000");
				 chname_rideid.add(msgs[1].toString().trim()+"::"+splitmessage[1].toString().trim());
			 }
			 
	    }
    	for(int i=0;i<chname_rideid.size();i++)
    	{
    		String name_rideid=chname_rideid.get(i);
    		String name_rideids[]=name_rideid.toString().trim().split("::");
    		 for( Iterator< String > it2 = DriverJourneyDetails.driverrideid.iterator(); it2.hasNext() ; )
			 {
    			 String msg=it2.next();
    			 String checkmsg[]=msg.toString().trim().split("::");
    			 if(checkmsg[1].toString().trim().equals(name_rideids[1].toString().trim()))
    			 {
    				 
    				 String checkchanelname[]=checkmsg[0].toString().trim().split("FROM");
    				 if(checkchanelname[0].toString().trim().equals(getString(R.string.r8)))
    				 {
	    				 
    				 }
    				 else
    				 {
    					 if(checkchanelname[1].toString().trim().equals(name_rideids[0].toString().trim()))
	    				 {
	    					 int position=DriverJourneyDetails.driverrideid.indexOf(msg);
	    					 System.out.println(position+"=======000000=========="+DriverJourneyDetails.drivermeteormsg.size());
	    					 if((DriverJourneyDetails.drivermeteormsg.size()!=0) && (DriverJourneyDetails.driverrideid.size()>=position))
	    					 {
	    						 JourneyDetails.DRIVER_NOTIFICATION--;
	    						 DriverJourneyDetails.drivermeteormsg.remove(position);
	    						 ((BaseAdapter) adapter).notifyDataSetChanged();
	    						 
	    					 }
	    					 it2.remove();
	    				 }
    				 }
    			 }
			 }
    	}
    	
    }
    public void removeMessage(String message)
    {
    	if(DriverJourneyDetails.drivermeteormsg.size()!=0)
    	{
    		JourneyDetails.DRIVER_NOTIFICATION--;
    		DriverJourneyDetails.drivermeteormsg.remove(message);
   		    ((BaseAdapter) adapter).notifyDataSetChanged();
    	}
    	
    }
    public String getDrivername(String dname)
    {
    	String drivername[]=dname.toString().trim().split("-");
    	return drivername[0].toString().trim();
    }
    public void setAlertbox2(final String msg3, final String ridername,String displaymessage,final String message, final String message1)
    {
		        AlertDialog.Builder adb=new AlertDialog.Builder(Acknowledgement.this.getParent());
		    	
		    	adb.setTitle(displaymessage.toString().trim());
		    	adb.setMessage("Ridername  : "+getEntireRiderName(ridername)+"\nTime      : "+parseTimeFromMessage(message1));
		    	adb.setPositiveButton(msg3.toString().trim(), new DialogInterface.OnClickListener() {
		  			public void onClick(DialogInterface dialog, int whichButton) {
		  				//value = input.getText().toString().trim();
		  				removeMessage(message);
		  				System.out.println("hhhhhhhhhhhhhyyyyyyyyyyyyyyyyyyyyyyydddddddddddddddddddddddddddddd");
		  				sendResponseMessage(message,ridername,msg3.toString().trim(),message1.toString().trim());
		  				if(DriverJourneyDetails.driverrideid.size()!=0)
		  				{
		  					DriverJourneyDetails.driverrideid.remove(message1.toString().trim());
		  				}
		  				
		  				Toast.makeText(getApplicationContext(),msg3.toString().trim(),Toast.LENGTH_SHORT).show();
		
		  			}
		  		});
		    	adb.show();
    }
    public void setAlertbox1(final String msg3,String ridername,String displaymessage,final String message,final String message1)
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
  				if(DriverJourneyDetails.driverrideid.size()!=0)
  				{
  					DriverJourneyDetails.driverrideid.remove(message1.toString().trim());
  				}
  				
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
				 System.out.println(" *********************** "+messagecontains.toString().trim());
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
				//Toast.makeText(getApplicationContext(),msg1.toString().trim(),Toast.LENGTH_SHORT).show();
				removeMessage(message);
				sendResponseMessage(message,ridername,msg1.toString().trim(),message1.toString().trim());
			}
		});
		adb.setNegativeButton(msg2.toString().trim(), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						removeMessage(message);
						sendResponseMessage(message,ridername,msg2.toString().trim(),message1.toString().trim());
						//Toast.makeText(getApplicationContext(),msg2.toString().trim(),Toast.LENGTH_SHORT).show();
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
    		else if(res.toString().trim().equals(getString(R.string.r8)))
    			return "r8";
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
    public String getRideid(String msg)
    {
    	String split[]=msg.toString().trim().split("::");
    	return split[1].toString().trim();
    }
   
    public void sendResponseMessage(String message,String drivername,String respon,String message1)
    {
    	System.out.println("message: "+message);
    	String rideidmessage=getRideId(message1.toString().trim());
    	String rideid=parseRideIdfromMessage(rideidmessage.toString().trim());
    	String time=parseTimeFromMessage(message1.toString().trim());
    	System.out.println(rideidmessage+"  *************888 "+rideid);
    	String res=getResponseId(respon);
		 //String rid=getRid(message);
		 String channelname=parseChannelName(session.getUsername(mPreferences));
		 
		 String updateseatsmessage="";
		 if(res.toString().trim().equals("r8"))
		 {
			 if(DriverJourneyDetails.driverridernames.contains(channelname.toString().trim()+rideid.toString().trim()))
			 {
				 System.out.println("channel name: "+channelname+ "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
				 controller.UpdateSeats(rideid.toString().trim(), Integer.toString(+1));
				 
				 
				 DriverJourneyDetails.driverridernames.remove(channelname.toString().trim()+rideid.toString().trim());
			 }
			/* int checkvalue=0;
			 for( int namevalue=0;namevalue<DriverJourneyDetails.driverridernames.size();namevalue++)
			 {
				 if(DriverJourneyDetails.driverridernames.get(namevalue).toString().trim().equals(channelname.toString().trim()))
				 {
					 checkvalue=1;
					 System.out.println("channel name: "+channelname+ "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
					 controller.UpdateSeats(rideid.toString().trim(), Integer.toString(+1));
					 break;
				 }
			 }*/
			 
			 /*if((DriverJourneyDetails.driverridernames.size()!=0) )
			 {
				 DriverJourneyDetails.driverridernames.remove(channelname.toString().trim());
			 }*/
		 }
		 else
		 {
			 
		 
			 if(res.toString().trim().equals("d1"))
			 {
				 DriverJourneyDetails.driverridernames.add(channelname.toString().trim()+rideid.toString().trim());
				 updateseatsmessage=controller.checkToUpdateSeats(rideid.toString().trim());
				 
			 }
			 if(updateseatsmessage.toString().trim().equals("UPDATE")||res.toString().trim().equals("d5")||res.toString().trim().equals("d2"))
			 {
				 if(!(res.toString().trim().equals("d5")))
				 {
					 controller.UpdateSeats(rideid.toString().trim(), Integer.toString(-1));
				 }
				 ArrayList<String>even=new ArrayList<String>();
		         
				 System.out.println("Injecting events: "+" ADDMESSAGE "+drivername+" "+channelname+"::"+res+"::"+rideid+"::"+time+"EVENT");
				 even.add("ADDMESSAGE EVENT"+"r"+drivername+" EVENT"+"d"+channelname+"::"+res+"::"+rideid+"::"+time.toString().trim()+"EVENT TNEVE");
				 if(res.toString().trim().equals("d4"))
				 {
					 controller.UpdateSeats(rideid.toString().trim(), Integer.toString(+1));
				 }
				 if(DriverJourneyDetails.driverrideid.size()!=0)
				 {
					 DriverJourneyDetails.driverrideid.remove(message1.toString().trim());
				 }
				 String val=controller.injectAcknowledgeEvents(even);
				 if(val.trim().equals("successfully injected"))
				 {
					 System.out.println(val.toString().trim());
				 }
				 else
				 {
					 System.out.println("No message injected");
				 }
			 }
			 else
			 {
				 Toast.makeText(getApplicationContext(),"Seats are filled! no more accepatances please",Toast.LENGTH_SHORT).show();
			 }
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
	
	public class GeoUpdateHandler implements LocationListener {

		public void onLocationChanged(Location location) {
			lat = location.getLatitude();
			lng = location.getLongitude();
			
			controller.storeCoordinates(session.getUsername(mPreferences), rideid, lat, lng);
			System.out.println("Geo coordinates: latitude: "+lat+" longitude: "+lng);
			String rname1=gpsridername.toString().trim();
			String rname = getEntireRiderName(gpsridername);
			String coordinates = controller.getGPSCoordinates(rideid, rname);
			String[] result = coordinates.split("::");
			System.out.println(rname + "  coordinates : "+ coordinates);
			
			String add1 = lat+","+lng;
			String add2 = result[0].trim()+","+result[1].trim();
			int distance = Integer.parseInt(getDistancenTime(getUrl(add1,add2)));
			System.out.println("Distance: "+distance);
			if(distance <= 5000)
			{
				Toast.makeText(getApplicationContext(),"you are near to "+rname+" place! send pickup request ",Toast.LENGTH_SHORT).show();
				String makepayload="r"+rname1.toString().trim()+"::d5::"+rideid.toString().trim()+"::"+gpsridetime.toString().trim();
				System.out.println(makepayload+"       acknowledgement ridepickup request");
				parseMeteormsgdata(makepayload);
			    //Newroute obj=new Newroute();
			    //obj.parseMeteormsgdata(makepayload);
				//driverPickupNotification();
			}
		}
		
		

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
	}
	public void parseMeteormsgdata(String payload)
	{
		String str1[]=payload.toString().trim().split("::");
		System.out.println("ridername: "+str1[0]+"\nmessage: "+str1[1]+"\nrideid: "+str1[2]);
		String msg = getString(R.string.d5);
		System.out.println("ridername: "+str1[0]+"\nmessage: "+msg+"\nrideid: "+str1[2]);
		DriverJourneyDetails.drivermeteormsg.add(msg+" FROM "+str1[0].toString().trim().substring(1,str1[0].length()));
		DriverJourneyDetails.driverrideid.add(msg+" FROM "+str1[0].toString().trim().substring(1,str1[0].length())+"::"+str1[2].toString().trim()+"::"+str1[3].toString().trim());
		notificationAlarm(str1[0].toString().trim().substring(1,str1[0].length()), msg);
	}
	private void notificationAlarm(String name, String msg) {
		
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		JourneyDetails.DRIVER_NOTIFICATION++;
    	int icon = R.drawable.icon;
    	CharSequence text = msg;
    	CharSequence contentTitle = "Pickup Notification";
    	CharSequence contentText = JourneyDetails.DRIVER_NOTIFICATION+" unread(Kaarpool)" ;//msg+" from "+name;
    	long when = System.currentTimeMillis();

    	Intent intent = new Intent(Acknowledgement.this, JourneyDetails.class);
    	intent.putExtra("receiver", "drivernotification");
    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
    	Notification notification = new Notification(icon,text,when);
    	
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
	}
	public String getDistancenTime(String url) {
		String result = "";
		InputStream is = null;
    	try
    	{
    		HttpClient httpclient = new DefaultHttpClient();
    		HttpPost httppost = new HttpPost(url);
    		HttpResponse response = httpclient.execute(httppost);
    		HttpEntity entity = response.getEntity();
    		is = entity.getContent();
    	} 
    	catch(Exception e) 
    	{
    		Log.e("log_tag", "Error in http conection"+e.toString());
    	}
    	try 
    	{
    		BufferedReader reader = new BufferedReader(
    		new InputStreamReader(is,"iso-8859-1"),8);
    		StringBuilder sb = new StringBuilder();
    		String line = null;
    		while ( (line = reader.readLine() ) != null) {
    		sb.append(line + "\n"); }
    		is.close();
    		result=sb.toString();

    	}
    	catch(Exception e)
    	{
    		Log.e("log_tag", "Error converting result "+e.toString());
    	}
		try 
		{
			JSONObject rootObj = new JSONObject(result); //rootObj ist jetzt ein dict
			JSONArray routes = (JSONArray) rootObj.get("routes");
			if(routes.length()<1)
				return "ERROR no route there";
			JSONObject firstRoute = routes.getJSONObject(0);
			JSONArray legs = (JSONArray) firstRoute.get("legs");
			if(legs.length()<1)
				return "ERROR no legs there";
			JSONObject firstLeg = legs.getJSONObject(0);
			JSONObject durationObject = (JSONObject) firstLeg.get("duration");
			JSONObject distanceObject = (JSONObject) firstLeg.get("distance");
			JSONObject startaddObject = (JSONObject) firstLeg.get("start_location");
			JSONObject endaddObject = (JSONObject) firstLeg.get("end_location");
			String startadd = (String)firstLeg.get("start_address");
			String endadd = (String)firstLeg.get("end_address");

			timeToDestination = (String) durationObject.get("text");
			distanceToDestination = (Integer) distanceObject.get("value");
			fromLat = (Double) startaddObject.getDouble("lat");
			fromLon = (Double) startaddObject.getDouble("lng");
			
			toLat = (Double) endaddObject.getDouble("lat");
			toLon = (Double) endaddObject.getDouble("lng");
			
			System.out.println("start_Location: "+fromLat+","+fromLon+"\nend_location"+toLat+","+toLon);
			System.out.println("start_address: "+startadd+"\nend_address: "+endadd);
			
			System.out.println("Time: "+timeToDestination+" "+"and Distance in meters: "+distanceToDestination);
			return Integer.toString(distanceToDestination);
			
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
			return "Error while getting distance";
		}
		
	}
	public static String getUrl(String fromAdress, String toAdress) // connect to map web service
    {
	    StringBuffer urlString = new StringBuffer();
	    urlString.append("http://maps.google.com/maps/api/directions/json?origin=");
	    urlString.append(fromAdress.toString());
	    urlString.append("&destination=");
	    urlString.append(toAdress.toString());
	    urlString.append("&sensor=false");
	    System.out.println("url: "+urlString.toString());
	    return urlString.toString();
    }
}