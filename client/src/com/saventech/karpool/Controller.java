package com.saventech.karpool;

import java.util.ArrayList;

import android.util.Log;

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Controller.java
 * Date: Mar 25, 2011
 * Description:It is responsible for Connectivity and sending request to server and getting response and sending data  to requested services
 * 
 */
public class Controller 
{
	String loginid;
	String loginpwd;
	String regid;
	String regpwd;
	int checksysid=0;
	
	/* Deafault constructor for Controller
	 * 
	 */
	public Controller()
	{
		
	}
	/*Authenticate User id and password 
	 * 
	 */
	public boolean Authenticate_login(String id, String pwd)
	{
		//Trim id and password
		loginid=id.toString().trim();
		loginpwd=pwd.toString().trim();
	    
		//checking login credentials
		if(loginid.equals("user@gmail.com")&&loginpwd.equals("user"))
		{
			Log.i("Login_Controller", "Login values are authenticated");
			return true;
		}
		return false;
		
	}
	/*
	 * Checking openId Credentials and storing data in database
	 */
	public boolean Authenticate_register(String id, String pwd)
	{
		//Trim id and password
		loginid=id.toString().trim();
		loginpwd=pwd.toString().trim();
		
		//checking OpenId register credentials
		if(loginid.equals("user@gmail.com")&&loginpwd.equals("user"))
		{
			Log.i("OpenId_Controller", "OpenId values are verified");
			return true;
		}
		return false;
		
	}
	/*Checking user enterd id with existed ids
	 * 
	 */
	public boolean Availablesysids(String id)
	{
		
		//List to store available id's from database
		ArrayList<String> list=new ArrayList<String>();
		list.add("rajesh");
		list.add("mahesh");
		list.add("somesh");
		list.add("hareesh");
		Log.i("Availableids_Controller", "Checking for available ides");
		for(int i=0;i<list.size();i++)
		{
			//checking entered id with existed ids
			if(list.get(i).toString().equals(id.toString()))
			{
				return true;
			}
		}
		return false;
	}
	/*
	 * Validating user register data and storing data in database
	 */
	public boolean Validatesysidregister()
	{
		Log.i("Validatesysidregister_Controller", "Validating system id registration");
		return false;
	}
	/*Getting ride list on the specified source and destination from the database 
	 * sending to reqeusted activity
	 */
	public boolean Getridelist()
	{
		Log.i("Getridelist_Controller", "Getting ride list from the database on the specified source and destination");
		return true;
	}
	/*Creating new route by taking available credentials from the driver
	 * 
	 */
	public boolean Createnewroute()
	{
		Log.i("Createnewroute_Controller", "New route has been created");
		return true;
	}
	/*
	 * Cancel the current route
	 */
	public boolean Canceldriverroute()
	{
		Log.i("Canceldriver_Controller", "Current route has been canceled");
		return true;
	}
	
}
