package com.saventech.karpool;

import android.content.SharedPreferences;

public class Session 
{
	private String username;
	private String password;
	
	//private SharedPreferences mPreferences; 
	 public boolean checkinfo(SharedPreferences mPreferences)
	    {
		 //mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
	    	boolean username_set = mPreferences.contains("UserName");
	        boolean password_set = mPreferences.contains("PassWord");
	        if ( username_set || password_set ) {
	              return true;
	        } 
	    	return false;
	    }
	 public String getUsername(SharedPreferences mPreferences)
	 {
		 
		 username= mPreferences.getString("UserName","uid");
		
		// System.out.println(username);
		 return username;
	 }
	 public String getPassword(SharedPreferences mPreferences)
	 {
		 
		 password= mPreferences.getString("PassWord","dd");
		
		// System.out.println(username);
		 return password;
	 }
	 public void removeSession(SharedPreferences mPreferences)
	 {
		 SharedPreferences.Editor editor=mPreferences.edit();
         editor.remove("UserName");
         editor.remove("PassWord");
         editor.remove("ridersource");
         editor.remove("riderdestination");
         editor.remove("ridersettime");
         editor.remove("driversource");
         editor.remove("driverdestination");
         editor.remove("driversettime");
         editor.remove("driverseats");
         editor.remove("checkboxesclicked");
         editor.commit();
	 }
	 public  boolean checkRideDetails(SharedPreferences mPreferences)
	 {
		 boolean rider_source = mPreferences.contains("ridersource");
	        boolean rider_destination = mPreferences.contains("riderdestination");
	        boolean rider_settime = mPreferences.contains("ridersettime");
	        System.out.println(rider_source+" "+rider_destination+" "+rider_settime);
	        if ( rider_source || rider_destination ||rider_settime) {
	              return true;
	        } 
	    	return false;
	 }
	 public void saveRideDetails(SharedPreferences mPreferences,String source,String destination, String time)
	 {
		 SharedPreferences.Editor editor=mPreferences.edit();
         editor.putString("ridersource", source.toString().trim());
         editor.putString("riderdestination", destination.toString().trim());
         editor.putString("ridersettime", time.toString().trim());
         editor.commit();
	 }
	 public void saveDriverDetails(SharedPreferences mPreferences,String source,String destination, String time, String seats)
	 {
		 SharedPreferences.Editor editor=mPreferences.edit();
         editor.putString("driversource", source.toString().trim());
         editor.putString("driverdestination", destination.toString().trim());
         editor.putString("driversettime", time.toString().trim());
         editor.putString("driverseats", seats.toString().trim());
         editor.commit();
		 
	 }
	 public  boolean checkNewRouteDetails(SharedPreferences mPreferences)
	 {
		 boolean driver_source = mPreferences.contains("driversource");
	        boolean driver_destination = mPreferences.contains("driverdestination");
	        boolean driver_settime = mPreferences.contains("driversettime");
	        boolean driver_seats = mPreferences.contains("driverseats");
	        System.out.println(driver_source+" "+driver_destination+" "+driver_settime);
	        if ( driver_source || driver_destination ||driver_settime || driver_seats) 
	        {
	              return true;
	        } 
	    	return false;
	 }
	 public boolean ischeckboxesclicked(SharedPreferences mPreferences)
	 {
		 boolean checkboxes_clicked=mPreferences.contains("checkboxesclicked");
		 if(checkboxes_clicked)
		 {
			 return true;
		 }
		 return false;
	 }
	 public String getCheckBoxesClicked(SharedPreferences mPreferences)
	 {
		 return mPreferences.getString("checkboxesclicked", "cc");
	 }
	 public void saveCheckBoxesClicked(SharedPreferences mPreferences,String checkboxes)
	 {
		 SharedPreferences.Editor editor=mPreferences.edit();
		 String previouscheckboxes=mPreferences.getString("checkboxesclicked","cd");
         editor.putString("checkboxesclicked", checkboxes.toString().trim());
         System.out.println(mPreferences.getString("checkboxesclicked","cd")+" ############################");
         editor.commit();
         
	 }

}
