package com.saventech.karpool;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
//	 */
	public boolean Authenticate_login(String id, String pwd)
	{
		//Trim id and password
		loginid=id.toString().trim();
		loginpwd=pwd.toString().trim();
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("loginuserid", id.toString().trim()));
		postParameters.add(new BasicNameValuePair("loginuserpwd", pwd.toString().trim()));
	    String response;
		//checking login credentials
		try{

	    	    response = CustomHttpClient.executeHttpPost("http://198.162.18.22:8080/kaarpool/LoginServlet", postParameters);
	    	    String res=response.toString();
	    	    if(res.toString().trim().equals("YES"))
	    	    {
	    	    	Log.i("Login_Controller", "Login values are authenticated");
	    	    	return true;
	    	    }
	    	    else if(res.toString().trim().equals("NO"))
	    	    {   
	    	    	Log.i("Login_Controller", "Login values didnot match");
	    	    	return false;
	    	    }
	    	    else
	    	    {
	    	    	Log.i("Login_Controller", "User name doesnot exist");
	    	    	return false;
	    	    }
    	   }
		   catch(Exception e) 
    	   {
    		//e.printStackTrace();
    		return false;
	      }		
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
//		ArrayList<String> list=new ArrayList<String>();
//		list.add("rajesh");
//		list.add("mahesh");
//		list.add("somesh");
//		list.add("hareesh");
//		Log.i("Availableids_Controller", "Checking for available ides");
//		for(int i=0;i<list.size();i++)
//		{
//			//checking entered id with existed ids
//			if(list.get(i).toString().equals(id.toString()))
//			{
//				return true;
//			}
//		}
//		return false;
		System.out.println("Controller 999999999999999999999");
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("sysregid", id.toString().trim()));
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost("http://198.162.18.22:8080/kaarpool/AuthenticateIds", postParameters);
    	    String res=response.toString();
    	  System.out.println(res.length()+"dddddddddddddddddddddddddddd");
    	    if(res.toString().trim().equals("YES"))
    	    {
    	    	  Log.i("Controller", "Checked");
    	    	  System.out.println("Existedddddddddddddddddddddddddddddddd");
    	          return false;
    	    }
    	    else
    	    	return true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return true;
		

	}

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
	 * Takes new route details from driver and send to server to store in database
	 * 
	 */
	public String driverNewroute(String regid, String driversrc, String driverdest, String seats, String starttime, String mode)
	{
		String response1 = null;
		ArrayList<NameValuePair> newrouteparms = new ArrayList<NameValuePair>();
		newrouteparms.add(new BasicNameValuePair("regid", regid.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("driversrc", driversrc.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("driverdest", driverdest.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("seats", seats.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("starttime", starttime.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("mode", mode.toString().trim()));
		
    	try 
    	{
    	    response1 = CustomHttpClient.executeHttpPost("http://198.162.18.22:8080/kaarpool/DriverNewRoute", newrouteparms);
    	    String res=response1.toString();
    	    Log.i("Createnewroute_Controller", "New route has been created");
    	    return res;
    	}catch(Exception e) 
    	{
    		e.printStackTrace();
    		return null;
    	}
		
	}
	/*
	 * Cancel the current route
	 */
	public boolean Canceldriverroute()
	{
		Log.i("Canceldriver_Controller", "Current route has been canceled");
		return true;
	}
	public String Sysid_registration(String sysregid, String sysregpwd, String sysregdob, String sysregaddress, String sysregmobile,String sysreggender,String sysregimage)
	{
		
		
		//System.out.println("IMAGE  999999999999999999:"+sysregimage.length()+"      "+sysregimage);
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("sysregid", sysregid.toString().trim()));
		postParameters.add(new BasicNameValuePair("sysregpwd", sysregpwd.toString().trim()));
		postParameters.add(new BasicNameValuePair("sysregdob", sysregdob.toString().trim()));
		postParameters.add(new BasicNameValuePair("sysregaddress", sysregaddress.toString().trim()));
		postParameters.add(new BasicNameValuePair("sysregmobile", sysregmobile.toString().trim()));
		postParameters.add(new BasicNameValuePair("sysreggender", sysreggender.toString().trim()));
		postParameters.add(new BasicNameValuePair("sysregimage", sysregimage.toString().trim()));
		
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost("http://198.162.18.22:8080/kaarpool/SysRegistration", postParameters);
    	    String res=response.toString();
    	    Log.i("Controller",res+"  response from the server");
    	    return res;
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception");
    		return null;
	}
	}
	public String getUserPreferences(String username)
	{
		System.out.println(username+"username in controller");
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("user_pref",username));
		String response = null;
		try {
    	    response = CustomHttpClient.executeHttpPost("http://198.162.18.22:8080/kaarpool/ProfilePreferences", postParameters);
    	    String res=response.toString();
    	    Log.i("Controller",response+"  response from the server");
    	    return res;
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception");
    		return "";
	}
	}
	public String saveProfilePref(String pusername,String ppwd, String pmobile, String paddress, String pimage)
	{
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("pusername",pusername));
		postParameters.add(new BasicNameValuePair("ppwd",ppwd));
		postParameters.add(new BasicNameValuePair("pmobile",pmobile));
		postParameters.add(new BasicNameValuePair("paddress",paddress));
		postParameters.add(new BasicNameValuePair("pimage",pimage));
		String response = null;
		try {
    	    response = CustomHttpClient.executeHttpPost("http://198.162.18.22:8080/kaarpool/SaveProfilePref", postParameters);
    	    String res=response.toString();
    	    Log.i("Controller",res+"  response from the server");
    	    return res;
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception");
    		return "";
	}
	}
	
	
}