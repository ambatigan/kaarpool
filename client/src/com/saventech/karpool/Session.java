package com.saventech.karpool;

import java.util.ArrayList;

import android.content.SharedPreferences;

public class Session 
{
	private String username;
	private String password;
	private String Previouscheckbox;
	
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
        /* editor.remove("ridersource");
         editor.remove("riderdestination");
         editor.remove("ridersettime");*/
         /*editor.remove("driversource");
         editor.remove("driverdestination");
         editor.remove("driversettime");
         editor.remove("driverseats");*/
         editor.remove("checkboxesclicked");
         editor.commit();
	 }
	 /*public  boolean checkRideDetails(SharedPreferences mPreferences)
	 {
		 boolean rider_source = mPreferences.contains("ridersource");
	        boolean rider_destination = mPreferences.contains("riderdestination");
	        boolean rider_settime = mPreferences.contains("ridersettime");
	        System.out.println(rider_source+" "+rider_destination+" "+rider_settime);
	        if ( rider_source || rider_destination ||rider_settime) {
	              return true;
	        } 
	    	return false;
	 }*/
	 /*public void saveRideDetails(SharedPreferences mPreferences,String source,String destination, String time)
	 {
		 SharedPreferences.Editor editor=mPreferences.edit();
         editor.putString("ridersource", source.toString().trim());
         editor.putString("riderdestination", destination.toString().trim());
         editor.putString("ridersettime", time.toString().trim());
         editor.commit();
	 }*/
	/* public void saveDriverDetails(SharedPreferences mPreferences,String source,String destination, String time, String seats)
	 {
		 SharedPreferences.Editor editor=mPreferences.edit();
         editor.putString("driversource", source.toString().trim());
         editor.putString("driverdestination", destination.toString().trim());
         editor.putString("driversettime", time.toString().trim());
         editor.putString("driverseats", seats.toString().trim());
         editor.commit();
		 
	 }*/
	 /*public  boolean checkNewRouteDetails(SharedPreferences mPreferences)
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
	 }*/
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
		 Previouscheckbox=mPreferences.getString("checkboxesclicked", "cc");
		 System.out.println("getcheckboxesclicked--------"+Previouscheckbox);
		 return Previouscheckbox.toString().trim();
	 }
	 public void saveCheckBoxesClicked(SharedPreferences mPreferences,String checkboxes)
	 {
		 String checkdata="";
		// System.out.println(checkboxes+"SEEEEEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSIIIIIIIIIIONNNNNNNNNNNNNN");
		 SharedPreferences.Editor editor=mPreferences.edit();
		 /*String array[]=checkboxes.split("::");
		 ArrayList<String>checked=new ArrayList<String>();
		 ArrayList<String>unchecked=new ArrayList<String>();
		 for( int j=0;j<array.length;j++)
		 {
			 String temp[]=array[j].split("CHECKBOX");
			 if(temp[temp.length-1].equals("checked"))
			 {
				 String tempstr="";
				 for(int k=0;k<temp.length-1;k++)
				 {
					 tempstr=tempstr+temp[k]+"CHECKBOX";
				 }
				 //tempstr=tempstr+"checked";
				 checked.add(tempstr);
			 }
			 else if(temp[temp.length-1].equals("unchecked"))
			 {
				 String tempstr="";
				 for(int k=0;k<temp.length-1;k++)
				 {
					 tempstr=tempstr+temp[k]+"CHECKBOX";
				 }
				 unchecked.add(tempstr);
			 }
			 
		 }
		 if(checked.size()==0 && unchecked.size()==0)
		 {
			 for( int j=0;j<array.length;j++)
			 {
			   checkdata=checkdata+array[j]+"::";
			 }
		 }
		 else
		 {
			 for (Object data : unchecked) 
			 {
		            checked.remove(data);
		    }
			 for (Object data : checked) {
		           checkdata=checkdata+data+"::";
		    }
		 }*/
		 System.out.println(checkboxes+"SEEEEEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSIIIIIIIIIIONNNNNNNNNNNNNN");
         editor.putString("checkboxesclicked", checkboxes);
         editor.commit();
         System.out.println(mPreferences.getString("checkboxesclicked","cd")+" ############################");
        
         /*checked=null;
         unchecked=null;*/
        // return checkdata;
         
	 }

}
