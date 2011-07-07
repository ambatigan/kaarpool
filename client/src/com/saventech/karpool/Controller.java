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
 * Description:It  is responsible for Connectivity and sending request to server and getting response and sending data  to requested services
 * 
 */
public class Controller 
{
	String loginid;
	String loginpwd;
	String regid;
	String regpwd;
	int checksysid=0;



	
	//String url="http://192.168.192.15:8080/kaarpool/";

	String url="http://122.183.102.229/kaarpool/";


	/*  Deafault  constructor for Controller
	 * 
	 */
	public Controller()
	{
		
	}
	/*Authenticate User id and password 
	 * 
	*/
	public String Authenticate_login(String id, String pwd)
	{
		//Trim id and password
		loginid=id.toString().trim();
		loginpwd=pwd.toString().trim();
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("loginuserid", id.toString().trim()));
		postParameters.add(new BasicNameValuePair("loginuserpwd", pwd.toString().trim()));
	    String response ="";
	    String res="";
	    
		//checking login  credentials
		try
		{
			System.out.println(url+"LoginServlet");
			res = CustomHttpClient.executeHttpPost(url+"LoginServlet", postParameters);   
    	}
		catch(Exception e) 
    	{
    	    e.printStackTrace();
    		Log.i("Login Auth","  got exception ");
    		
	    }
		   response=res.toString();
		   return response;
	}
	/*
	 * Checking openId Credentials and storing data in database
	 */
	public boolean Authenticate_register(String id, String pwd)
	{
		//Trim id and password
		loginid=id.toString().trim();
		loginpwd=pwd.toString().trim();
		
		//checking OpenId register  credentials
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
		System.out.println("Controller 999999999999999999999");
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("sysregid", id.toString().trim()));
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost(url+"AuthenticateIds", postParameters);
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
	/*Getting  ride list on the specified source and destination from the database 
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
	public String driverNewroute(String regid, String routename, String driversrc, String driverdest, String seats, String starttime, String mode)
	{
		String response1 = null;
		ArrayList<NameValuePair> newrouteparms = new ArrayList<NameValuePair>();
		newrouteparms.add(new BasicNameValuePair("regid", regid.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("routename", routename.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("driversrc", driversrc.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("driverdest", driverdest.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("seats", seats.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("starttime", starttime.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("mode", mode.toString().trim()));
		
    	try 
    	{
    	    response1 = CustomHttpClient.executeHttpPost(url+"DriverNewRoute", newrouteparms);
    	    String res=response1.toString();
    	    Log.i("Createnewroute_Controller", "New route has been created");
    	    return res;
    	}catch(Exception e) 
    	{
    		e.printStackTrace();
    		return null;
    	}
		
	}
	public String riderNewroute(String regid, String ridersrc, String riderdest,String starttime, String mode)
	{
		Log.i("Createridernewroute_Controller", "You are in controller riderNewroute method");
		String response1 = null;
		ArrayList<NameValuePair> newrouteparms = new ArrayList<NameValuePair>();
		newrouteparms.add(new BasicNameValuePair("regid", regid.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("ridersrc", ridersrc.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("riderdest", riderdest.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("starttime", starttime.toString().trim()));
		newrouteparms.add(new BasicNameValuePair("mode", mode.toString().trim()));
		
    	try 
    	{
    	    response1 = CustomHttpClient.executeHttpPost(url+"RiderNewRoute", newrouteparms);
    	    String res=response1.toString();
    	    Log.i("Createridernewroute_Controller", "Rider new route has been created");
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
	public String saveDriverroute(String jid,String route, String csource, String cdestination, String seats, String ctime)
	{
		ArrayList<NameValuePair> saveparams = new ArrayList<NameValuePair>();
		saveparams.add(new BasicNameValuePair("jid", jid.toString().trim()));
		saveparams.add(new BasicNameValuePair("route", route.toString().trim()));
		saveparams.add(new BasicNameValuePair("csource", csource.toString().trim()));
		saveparams.add(new BasicNameValuePair("cdestination", cdestination.toString().trim()));
		saveparams.add(new BasicNameValuePair("seats", seats.toString().trim()));
		saveparams.add(new BasicNameValuePair("ctime", ctime.toString().trim()));
		
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost(url+"DriverRidecancel", saveparams);
    	    String res=response.toString();
    	    System.out.println("saveDriverroute response: "+res);
    	    Log.i("saveDriverroute_Controller", "Current route has been saved");
    	    return res;
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception");
    		return null;
    	}
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

    	    response = CustomHttpClient.executeHttpPost(url+"SysRegistration", postParameters);

    	    String res=response.toString();
    	    Log.i("Controller",res+"  response from the server");
    	    return res;
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception in Sysid_registration");
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

    	    response = CustomHttpClient.executeHttpPost(url+"ProfilePreferences", postParameters);

    	    String res=response.toString();
    	    Log.i("Controller",response+"  response from the server");
    	    return res;
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception in getUserPreferences ");
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

    	    response = CustomHttpClient.executeHttpPost(url+"SaveProfilePref", postParameters);

    	    String res=response.toString();
    	    Log.i("Controller",res+"  response from the server");
    	    return res;
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception in saveProfilePref");
    		return "";
	}
	}
	public String saveTravelPref(String travelPref, String seats, String image,String username)
	{
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("travelPref",travelPref));
		postParameters.add(new BasicNameValuePair("seats",seats));
		postParameters.add(new BasicNameValuePair("image",image));
		postParameters.add(new BasicNameValuePair("username",username));
		String response = null;
		try {
    	    response = CustomHttpClient.executeHttpPost(url+"TravelPreferences", postParameters);
    	    String res=response.toString();
    	    Log.i("Controller",res+"  response from the server");
    	    return res;
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception in saveTravelPref");
    		return "";
	}
	}
    	public String saveTimeBasedPref(String weekdays,String tsource, String tdestination, String timing, String tlocation, String username)
    	{
    		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    		postParameters.add(new BasicNameValuePair("tsource",tsource));
    		postParameters.add(new BasicNameValuePair("tlocation",tlocation));
    		postParameters.add(new BasicNameValuePair("tdestination",tdestination));
    		postParameters.add(new BasicNameValuePair("timing",timing));
    		postParameters.add(new BasicNameValuePair("weekdays",weekdays));
    		postParameters.add(new BasicNameValuePair("username",username));
    		System.out.println(username+"usssssssssssssssssssssss");
    		String response = null;
    		try {
        	    response = CustomHttpClient.executeHttpPost(url+"TimeBased", postParameters);
        	    String res=response.toString();
        	    Log.i("Controller",res+"  response from the server");
        	    return res;
        	}catch(Exception e) {
        		e.printStackTrace();
        		Log.i("Controller","Got exception in saveTimeBasedPref");
        		return "";
    	}  
	}
    	public String getTimeBasedPref(String username)
    	{
    		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    		postParameters.add(new BasicNameValuePair("username",username));
    		System.out.println(username+"usssssssssssssssssssssss");
    		String response = null;
    		try {
        	    response = CustomHttpClient.executeHttpPost(url+"GetTimeBasedPref", postParameters);
        	    String res=response.toString();
        	    Log.i("Controller",res+"  response from the server");
        	    return res;
        	}catch(Exception e) {
        		e.printStackTrace();
        		Log.i("Controller","Got exception in saveTimeBasedPref");
        		return "";
    	}  
    	}
        	public String getTravelBasedPref(String username)
        	{
        		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        		postParameters.add(new BasicNameValuePair("username",username));
        		System.out.println(username+"usssssssssssssssssssssss");
        		String response = null;
        		try {
            	    response = CustomHttpClient.executeHttpPost(url+"GetTravelPref", postParameters);
            	    String res=response.toString();
            	    Log.i("Controller",res+"  response from the server");
            	    return res;
            	}catch(Exception e) {
            		e.printStackTrace();
            		Log.i("Controller","Got exception in saveTimeBasedPref");
            		return "";
        	}
	}

	public String riderGetRideList(String userid, String ridersource, String riderdestination, String riderstarttime, String ridermode)
	{
		String ridelistresponse = null;
		ArrayList<NameValuePair> accessroutedata=new ArrayList<NameValuePair>();
		accessroutedata.add(new BasicNameValuePair("riderid",userid.toString().trim()));
		accessroutedata.add(new BasicNameValuePair("ridersource",ridersource.toString().trim()));
		accessroutedata.add(new BasicNameValuePair("riderdestination",riderdestination.toString().trim()));
		accessroutedata.add(new BasicNameValuePair("riderstarttime",riderstarttime.toString().trim()));
		accessroutedata.add(new BasicNameValuePair("ridermode",ridermode.toString().trim()));
		try 
    	{
			Log.i("riderGetRideList_Controller", "Get ride list tab ");
			System.out.println(userid+" "+ridersource+" "+riderdestination+" "+riderstarttime+" "+ridermode+"--------------");
			ridelistresponse = CustomHttpClient.executeHttpPost(url+"GetRideList", accessroutedata);
    	    String res=ridelistresponse.toString();
			return res;
			
    	}
		catch(Exception e) 
    	{
    		e.printStackTrace();
    		return null;
    	}
		
	}
	public String getCancelroutedetails(String username)
	{
		String canceljourneylist = null;
		ArrayList<NameValuePair> cancelroutedata=new ArrayList<NameValuePair>();
		cancelroutedata.add(new BasicNameValuePair("username",username.toString().trim()));
		try 
    	{
			Log.i("getCancelroutedetails_Controller", "Get cancel route details ");
			canceljourneylist = CustomHttpClient.executeHttpPost(url+"CancelRide", cancelroutedata);
    	    String res=canceljourneylist.toString();
    	    System.out.println("Controller: "+res);
			return res;
			
    	}
		catch(Exception e) 
    	{
    		e.printStackTrace();
    		return null;
    	}
	}
	public String userTotalRidedetails(String username)
	{
		String canceljourneylist = null;
		ArrayList<NameValuePair> cancelroutedata=new ArrayList<NameValuePair>();
		cancelroutedata.add(new BasicNameValuePair("username",username.toString().trim()));
		try 
    	{
			Log.i("getCancelroutedetails_Controller", "Get cancel route details ");
			canceljourneylist = CustomHttpClient.executeHttpPost(url+"GetRidedetails", cancelroutedata);
    	    String res=canceljourneylist.toString();
    	    System.out.println("Controller: "+res);
			return res;
			
    	}
		catch(Exception e) 
    	{
    		e.printStackTrace();
    		return null;
    	}
	}
	public String checkDriverridedetails(String username, String src,
			String dest, String seats, String time) {
		// TODO Auto-generated method stub
		ArrayList<NameValuePair> checkdetails=new ArrayList<NameValuePair>();
		checkdetails.add(new BasicNameValuePair("username",username.toString().trim()));
		checkdetails.add(new BasicNameValuePair("src",src.toString().trim()));
		checkdetails.add(new BasicNameValuePair("dest",dest.toString().trim()));
		checkdetails.add(new BasicNameValuePair("seats",seats.toString().trim()));
		checkdetails.add(new BasicNameValuePair("time",time.toString().trim()));
		String checkRidedetails = "";
		try 
    	{
			Log.i("getCancelroutedetails_Controller", "Get cancel route details ");
			checkRidedetails = CustomHttpClient.executeHttpPost(url+"CheckRidedetails", checkdetails);
    	    String res=checkRidedetails.toString();
    	    System.out.println("CheckRidedetails: "+res);
			return res;
			
    	}
		catch(Exception e) 
    	{
    		e.printStackTrace();
    		return null;
    	}
	}
	public String checkRiderridedetails(String username, String src, String dest, String time) 
	{
		ArrayList<NameValuePair> checkdetails=new ArrayList<NameValuePair>();
		checkdetails.add(new BasicNameValuePair("username",username.toString().trim()));
		checkdetails.add(new BasicNameValuePair("src",src.toString().trim()));
		checkdetails.add(new BasicNameValuePair("dest",dest.toString().trim()));
		checkdetails.add(new BasicNameValuePair("time",time.toString().trim()));
		String checkRidedetails = "";
		try 
    	{
			Log.i("getCancelroutedetails_Controller", "Get cancel route  details");
			checkRidedetails = CustomHttpClient.executeHttpPost(url+"CheckRiderRidedetails", checkdetails);
    	    String res=checkRidedetails.toString();
    	    System.out.println("CheckRiderRidedetails:   "+res);
			return res;
			
			
    	}
		catch(Exception e) 
    	{
    		e.printStackTrace();
    		return null;
    	}		
	}
	public String sendRiderequest(String checkboxesclicked,String ridername,String data)
	{
		
		
		
		ArrayList<NameValuePair> sendrequest=new ArrayList<NameValuePair>();
		sendrequest.add(new BasicNameValuePair("checkboxesclicked",checkboxesclicked.toString().trim()));
		sendrequest.add(new BasicNameValuePair("senderridename",ridername.toString().trim()));
		sendrequest.add(new BasicNameValuePair("data",data.toString().trim()));
		String sendRequest="";
		try
		{
			sendRequest=CustomHttpClient.executeHttpPost(url+"SendRideRequest", sendrequest);
			String res=sendRequest.toString();
			return res;	
			
			
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	// injecting events code
	public String injectEvents(ArrayList<String> eventlist)
	{
		String eventString="";
		for( int i=0;i<eventlist.size();i++)
		{
			eventString=eventString+eventlist.get(i).toString().trim();
		}
		ArrayList<NameValuePair> events=new ArrayList<NameValuePair>();
		events.add(new BasicNameValuePair("InjectedEvents",eventString.toString().trim()));
		String injectEventResponse="";
		try
		{
			injectEventResponse=CustomHttpClient.executeHttpPost(url+"InjectEvents", events);
			String res=injectEventResponse.toString();
			 Log.i("InjectEvents_Controller", res.toString().trim());
			return res.toString();
		   				
			
		}
		catch(Exception e)
		{
			Log.i("InjectEvents_Controller", "Exception in controller while injecting events");
		}
		return "failed to send";
	}
	
	//Injecting communication events
	public String injectAcknowledgeEvents(ArrayList<String> eventacklist)
	{
		String eventString="";
		for( int i=0;i<eventacklist.size();i++)
		{
			eventString=eventString+eventacklist.get(i).toString().trim();
		}
		ArrayList<NameValuePair> events=new ArrayList<NameValuePair>();
		events.add(new BasicNameValuePair("InjectedAckEvents",eventString.toString().trim()));
		String injectAckEventResponse="";
		try
		{
			injectAckEventResponse=CustomHttpClient.executeHttpPost(url+"InjectAcknowledgementEvents", events);
			String res=injectAckEventResponse.toString();
			 Log.i("InjectEvents_Controller", res.toString().trim());
			return res.toString();
		
		}
		catch(Exception e)
		{
			Log.i("InjectAckEvents_Controller", "Exception in controller while injecting Acknowledgement events");
		}
		return "failed to send";
		
	}
	
	public String rideHistory(String name,String role)
	{
		ArrayList<NameValuePair> history=new ArrayList<NameValuePair>();
		history.add(new BasicNameValuePair("name",name.toString().trim()));
		history.add(new BasicNameValuePair("role",role.toString().trim()));
		String rideHistoryResponse="";
		try
		{
			 rideHistoryResponse=CustomHttpClient.executeHttpPost(url+"RideHistory", history);
			String res=rideHistoryResponse.toString();
		    Log.i("Getting_RideHistory", res.toString().trim());
			return res.toString();
		
		}
		catch(Exception e)
		{
			Log.i("GetRideHistory_Controller", "Exception in controller while getting ride history");
		}
		return "failed to get Ride History";
		
		
	}
	public String modeChange(String name, int mode)
	{
		ArrayList<NameValuePair> changemode=new ArrayList<NameValuePair>();
		changemode.add(new BasicNameValuePair("username",name.toString().trim()));
		changemode.add(new BasicNameValuePair("mode",Integer.toString(mode)));
		String changeModeValue="";
		try
		{
			changeModeValue=CustomHttpClient.executeHttpPost(url+"ChangeMode", changemode);
			String res=changeModeValue.toString();
		    Log.i("Getting_RideHistory", res.toString().trim());
			return res.toString();
		
		}
		catch(Exception e)
		{
			Log.i("ChangeMode_Controller", "Exception in while changing the mode");
		}
		
		return "Failed to change the mode";
	}
	public String storeCoordinates(String username, String rideid, double lat,
			double lng) {
		// TODO Auto-generated method stub
		ArrayList<NameValuePair> storecoordinates=new ArrayList<NameValuePair>();
		storecoordinates.add(new BasicNameValuePair("rideid",rideid.toString().trim()));
		storecoordinates.add(new BasicNameValuePair("lat",Double.toString(lat)));
		storecoordinates.add(new BasicNameValuePair("lng",Double.toString(lng)));
		storecoordinates.add(new BasicNameValuePair("username", username.toString()));
		
		String changeModeValue="";
		try
		{
			changeModeValue=CustomHttpClient.executeHttpPost(url+"StoreCoordinates", storecoordinates);
			String res=changeModeValue.toString();
		    Log.i("Getting_Coordinates", res.toString().trim());
			return res.toString();
		
		}
		catch(Exception e)
		{
			Log.i("storingCoordinates_Controller", "Exception in while storing the coordinates");
		}
		
		return "Failed to store the coordinates";
		
		
	}
	
	public String trackrouteDrivername(String username, String rideid) {
		// TODO Auto-generated method stub
		System.out.println("trackrouteDrivername");
		ArrayList<NameValuePair> trackdetails=new ArrayList<NameValuePair>();
		trackdetails.add(new BasicNameValuePair("rideid",rideid.toString().trim()));
		trackdetails.add(new BasicNameValuePair("username", username.toString()));
		
		String drivername="";
		try
		{
			drivername=CustomHttpClient.executeHttpPost(url+"TrackDrivername", trackdetails);
			String res=drivername.toString();
		    Log.i("Getting_Drivername for tracking route: ", res.toString().trim());
			return res.toString();
		
		}
		catch(Exception e)
		{
			Log.i("trackrouteDrivername_Controller", "Exception in getting driver name");
		}
		
		return "Failed to get driver name";	
	}
	public String gpsCheckTime(String username, String date, String time) {
		System.out.println("gpeCheckTime");
		ArrayList<NameValuePair> userdetails=new ArrayList<NameValuePair>();
		userdetails.add(new BasicNameValuePair("username",username.toString().trim()));
		userdetails.add(new BasicNameValuePair("date", date.toString().trim()));
		userdetails.add(new BasicNameValuePair("time", time.toString().trim()));
		
		String gpscheck="";
		try
		{
			gpscheck=CustomHttpClient.executeHttpPost(url+"GPSCheckTime", userdetails);
			String res=gpscheck.toString();
		    Log.i("Getting GPS timings to enable GPS: ", res.toString().trim());
			return res.toString();
		
		}
		catch(Exception e)
		{
			Log.i("gpsCheckTime_Controller", "Exception in getting gps timings");
		}
		
		return "Failed to get timings";	
	}
	public void Canceldriverroute1(String username, String source, String destination, String time) 
	{
		System.out.println("canceldriverroute1");
		ArrayList<NameValuePair> cancelparams = new ArrayList<NameValuePair>();
		cancelparams.add(new BasicNameValuePair("username", username.toString().trim()));
		cancelparams.add(new BasicNameValuePair("source", source.toString().trim()));
		cancelparams.add(new BasicNameValuePair("destination", destination.toString().trim()));
		cancelparams.add(new BasicNameValuePair("time", time.toString().trim()));
		
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost(url+"RideCancel", cancelparams);
    	    String res=response.toString();
    	    System.out.println("res from server in cancel route: "+res);
    	    Log.i("Canceldriver_Controller", "Current route has been canceled");
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller","Got exception");
    	}
	}
	public void UpdateSeats(String rideid, String seats)
	{
		System.out.println("Update Seats Number");
		ArrayList<NameValuePair> updateseats = new ArrayList<NameValuePair>();
		updateseats.add(new BasicNameValuePair("rideid", rideid.toString().trim()));
		updateseats.add(new BasicNameValuePair("seats", seats.toString().trim()));
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost(url+"UpdateSeats", updateseats);
    	    String res=response.toString();
    	    System.out.println("res from server in update seats: "+res);
    	    Log.i("Updateseats_Controller", "Seats has been updated");
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller_update seats","Got exception");
    	}
	}
	public String checkToUpdateSeats(String rideid)
	{
		System.out.println("Update Seats Number");
		ArrayList<NameValuePair> updateseats = new ArrayList<NameValuePair>();
		updateseats.add(new BasicNameValuePair("rideid", rideid.toString().trim()));
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost(url+"CheckUpdateSeats", updateseats);
    	    String res=response.toString();
    	    System.out.println("res from server in check to update seats: "+res);
    	    Log.i("checkToUpdateSeats_Controller", "check seats to update");
    	    return res.toString().trim();
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller_checkToUpdateSeats","Got exception");
    		return "Exception in checktoupdateseats_controller";
    	}
	}
	public String getGPSCoordinates(String rideid, String ridername) {
		System.out.println("Getting GPS Coordinates");
		ArrayList<NameValuePair> GPS = new ArrayList<NameValuePair>();
		GPS.add(new BasicNameValuePair("rideid", rideid.toString().trim()));
		GPS.add(new BasicNameValuePair("ridername", ridername.toString().trim()));
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost(url+"GPSDistance", GPS);
    	    String res=response.toString();
    	    System.out.println("res from server for getting gps coordinates: "+res);
    	    Log.i("getGPSCoordinates_Controller", "getGPSCoordinates");
    	    return res.toString().trim();
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller_getGPSCoordinates","Got exception");
    		return "Exception in getGPSCoordinates_controller";
    	}
	}
	public String getrideDestination(String rideid)
	{
		System.out.println("getrideDestination");
		ArrayList<NameValuePair> dest = new ArrayList<NameValuePair>();
		dest.add(new BasicNameValuePair("rideid", rideid.toString().trim()));
		String response = null;
    	try {
    	    response = CustomHttpClient.executeHttpPost(url+"RideDestination", dest);
    	    String res=response.toString();
    	    System.out.println("res from server for getride destination: "+res);
    	    Log.i("getrideDestination_Controller", "getrideDestination");
    	    return res.toString().trim();
    	}catch(Exception e) {
    		e.printStackTrace();
    		Log.i("Controller_getrideDestination","Got exception");
    		return "Exception in getrideDestination_controller";
    	}
	}
}