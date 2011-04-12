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
         editor.commit();
	 }

}
