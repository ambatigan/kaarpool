/**
 * Project: KaarpoolServer
 * Package: com.saventech.kaarpool.dbInterface
 * File: DBInterface.java
 */

package com.saventech.kaarpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
 
import org.apache.log4j.Logger;
/**
 * The Class DBInterface.
 */
 
public class DBInterface
{
	/** static instance; will be the only reference to a DBInterface object. */
	 static DBInterface instance = null;

	ResourceBundle resourceBundle;

	/** The connection. */
	private Connection connection = null;

	/** The result set. */
	ResultSet resultSet = null;
	
	/** The result set. */
	ResultSet resultSet1 = null;
	
	/** The result set. */
	ResultSet resultSet2 = null;

	/** The statement. */
	Statement statement = null;

	/** The log. */
	Logger log = Logger.getLogger(DBInterface.class);
	
	/** Variable to check the connection to mysql database */
	 boolean isConnectionOpen = false;

	public DBInterface()
	{
		// open the connection when the DBInterface is create - destroy it only
		// when the object is destroyed
		isConnectionOpen = openConnection();
	}

	/**
	 * Returns the only instance of the QuizAppClient. If the instance does not
	 * exist, then a new instance is created and returned.
	 * 
	 * @return instance of the QuizAppClient
	 */
	public static synchronized DBInterface getInstance()
	{
		// Check if an instance exists, if it does not, then create a new one
		if (instance == null)
		{
			instance = new DBInterface();
		}

		// return the ConfigManager instance
		return instance;
	}

	/**
	 * Open connection to the MySQL database containing the questions.
	 * 
	 * @return true, if successful
	 */
	private boolean openConnection()
	{
		String url; // URL of the DB server
		String dbName; // MySQL database name
		String driverName; // driver to use
		String username; // DB username
		String password; // DB password

		// fetch settings from the properties file
		resourceBundle = ResourceBundle.getBundle("server");
		url = resourceBundle.getString("url");
		dbName = resourceBundle.getString("dbname");
		driverName = resourceBundle.getString("classname");
		username = resourceBundle.getString("username");
		password = resourceBundle.getString("password");
        System.out.println(username+"username");
		// open connection to the database
		try
		{
			// MySQL driver
			Class.forName(driverName).newInstance();
			// connection to the server
			connection = DriverManager.getConnection(url + dbName, username, password);
			System.out.println("Connection to MySQL db is established");
			log.info("Connection to MySQL db is established");

			return true;
		}
		catch (final SQLException s)
		{
			log.error("Exception: " + s.getMessage());
			return false;
		}
		catch (final InstantiationException e)
		{
			log.error("Exception: " + e.getMessage());
			return false;
		}
		catch (final IllegalAccessException e)
		{
			log.error("Exception: " + e.getMessage());
			return false;
		}
		catch (final ClassNotFoundException e)
		{
			log.error("Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean isConnectionOpen()
	{
		return isConnectionOpen;
	}
	
	public void info(String uname, String pwd)
	{
	   try
		{
			statement = connection.createStatement();
			statement.executeUpdate("insert into info values("+"\""+uname+"\""+","+"\""+pwd+"\""+")");
		
		}
			catch (final SQLException ex)
			{
				log.info("Unable to store the user info. SQLException"+ex.getStackTrace());
			}
	}
	public void registration(String uname, String mobile,String address ,String gender, String dob, String image )
	{
	   try
		{
			statement = connection.createStatement();
			// result of executing SQL query
			System.out.println(resourceBundle.getString("regQuery")+uname+"\""+","+"\""+mobile+"\""+","+"\""+address+"\""+","+"\""+gender+"\""+","+"\""+dob+"\""+","+"\""+image+"\""+")");
			statement.executeUpdate(resourceBundle.getString("regQuery")+uname+"\""+","+"\""+mobile+"\""+","+"\""+address+"\""+","+"\""+gender+"\""+","+"\""+dob+"\""+","+"\""+image+"\""+")");
			log.info("Inserted personal_details successfully");
		}
			catch (final SQLException ex)
			{
				log.fatal("Unable to insert the user's personal info. SQLException"+ex.getStackTrace());
				ex.printStackTrace();
			}
	}
	public void karpooldetails(String uname, String pwd, String session)
	{
	   try
		{
			statement = connection.createStatement();
			// result of executing SQL query
			statement.executeUpdate(resourceBundle.getString("insert_karpoolDetails") +uname+"\""+","+"\""+pwd+"\""+","+"\""+session+"\""+")");
			log.info("Updated kaarpoolnetwork_details");
		}
			catch (final SQLException ex)
			{
				log.fatal("Unable to retrieve password. SQLException"+ex.getStackTrace());
				ex.printStackTrace();
			}
	}
	
	public String getPwd(String uname)
	{
		String password = null; // holds the password fetched from the server
		
		if(isConnectionOpen == true)
		{
			// fetch the password from the server
			try
			{
				// DB statement
				statement = connection.createStatement();
				// result of executing SQL query
				System.out.println(resourceBundle.getString("getPwdQuery")+uname + "\"");
				resultSet = statement.executeQuery(resourceBundle.getString("getPwdQuery")+uname+ "\"");
				// move to the first (and only record)
				resultSet.next();
				// retrieve the password
				if(resultSet.getRow()>0)
				{
				password = resultSet.getString("loginpwd");
				}
				
				log.info("Fetched password");
			}
			catch (final SQLException ex)
			{
				ex.printStackTrace();
				log.fatal("Unable to retrieve password. SQLException");
				
			}
			return password;
		}
		else
		{
			log.info("Please check your server user details");
			return "0";
		}
	}
	public boolean Authenticate_registerids(String registerid)
	{
		System.out.println("checkingdddddddddddddddddddddddddddddddddddddddd");
		if(isConnectionOpen == true)
		{
			// fetch the password from the server
			try
			{
				// DB statement
				statement = connection.createStatement();
				// result of executing SQL query
				System.out.println(resourceBundle.getString("auth_registerIds") + registerid + "\"");
				resultSet = statement.executeQuery( resourceBundle.getString("auth_registerIds") + registerid + "\"");
				// move to the first (and only record)
				if(resultSet.next())
				{
					System.out.println("one row affected....................");
					return true;
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				log.info("unable to Authenticate_registerids");
				return false;
			}
		}
		return false;
	}

	public String getUserProfilePreferences(String username)
	{
		
		@SuppressWarnings("unused")
		String uname, image, address, image1 , mobile, pwd= null;
	    String result ="";
		if(isConnectionOpen == true)
		{
			// fetch the password from the server
			try
			{
				pwd = instance.getPwd(username);
			
				// DB statement
				statement = connection.createStatement();
				// result of executing SQL query
				System.out.println(resourceBundle.getString("personal_details")+username+ "\"");
				@SuppressWarnings("unused")
				DBInterface instantce = DBInterface.getInstance();
				resultSet = statement.executeQuery(resourceBundle.getString("personal_details")+username+ "\"");
				
				while(resultSet.next())
				{			
					result = resultSet.getString("username")+":"+pwd+":"+resultSet.getString("mobile")+":"+resultSet.getString("address")+":"+resultSet.getString("image");
					return result;
				}
				
			}
			catch(Exception e)
			{
				log.info("unable to getUserProfilePreferences");
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Update the user's version when the user gets completes trail version
	 * 
	 * @param username
	 * @return
	 */
	public int updateUserPwd(String uname,String pwd)
	{
		int updateUserPwd = 0;
		try
		{
			// DB statement
			statement = connection.createStatement();
			// result of executing SQL query whether updated or not
			System.out.println(resourceBundle.getString("personal_details")+pwd+"\""+" where loginid="+"\""+uname+"\"");
			updateUserPwd = statement.executeUpdate(resourceBundle.getString("personal_details")+pwd+"\""+" where loginid="+"\""+uname+"\"");
			
			log.info("updated UserPwd");
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to update UserPwd . SQLException");
		}

		// retrieves result as "1" if there's an update and "0" if not
		return updateUserPwd;
	}
	public int saveUserPref(String uname,String pwd, String mobile, String address, String image)
	{
		int updateUserPwd = 0;
		@SuppressWarnings("unused")
		int updateUserPwd1 = 0;
		try
		{
			// DB statement
			statement = connection.createStatement();
			// result of executing SQL query whether updated or not
			System.out.println(resourceBundle.getString("saveUserPref")+mobile+"\""+", address="+"\""+address+"\""+", image="+"\""+image+"\""+" where username="+"\""+uname+"\"");
			updateUserPwd = statement.executeUpdate(resourceBundle.getString("saveUserPref")+mobile+"\""+", address="+"\""+address+"\""+", image="+"\""+image+"\""+" where username="+"\""+uname+"\"");
			log.info("updated User preferences");
			System.out.println(resourceBundle.getString("saveUserPwd")+pwd+"\""+" where loginid="+"\""+uname+"\"");
			updateUserPwd1 = statement.executeUpdate(resourceBundle.getString("saveUserPwd")+pwd+"\""+" where loginid="+"\""+uname+"\"");
			log.info("updated UserPwd");
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to update UserPwd . SQLException");
		}

		// retrieves result as "1" if there's an update and "0" if not
		return updateUserPwd;
	}
	public int insert_NetworkDetails(String kid)
	{
		int insert_intoND = 0;
		try
		{
			// DB statement
			statement = connection.createStatement();
			// result of executing SQL query whether updated or not
			System.out.println(resourceBundle.getString("insert_networkdetails")+ kid+")");
			insert_intoND = statement.executeUpdate(resourceBundle.getString("insert_networkdetails")+ kid+")");
			log.info("inserted into networkdetails");
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to insert into NetworkDetails . SQLException");
		}

		// retrieves result as "1" if there's an update and "0" if not
		return insert_intoND;
	}
	public String getKid(String username)
	{
		String kid = "";
		try
		{
			// DB statement
			statement = connection.createStatement();
			// result of executing SQL query whether updated or not
			System.out.println(resourceBundle.getString("getKid")+username+"\"");
			resultSet = statement.executeQuery(resourceBundle.getString("getKid")+username+"\"");
			resultSet.next();
			kid = resultSet.getString(1);
			System.out.println(kid+"kidvcvvvvvvvvvvvvvvvvvvvvvvvv");
			
			log.info("fetched kid from kaarpoolnetwork_details");
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to update UserPwd . SQLException");
		}

		// retrieves result as "1" if there's an update and "0" if not
		return kid;
	}
	public int update_userdetails(String username)
	{
		int update_userdetails=0;
		try
		{
			// DB statement
			statement = connection.createStatement();
			// result of executing SQL query whether updated or not
			System.out.println(resourceBundle.getString("getids")+username+"\"");
			resultSet = statement.executeQuery(resourceBundle.getString("getids")+username+"\"");
			resultSet.next();
			//System.out.println(resourceBundle.getString("update_userdetails")+ resultSet.getBigDecimal(2)+","+resultSet.getBigDecimal(1)+")");
			statement.executeUpdate(resourceBundle.getString("update_userdetails")+ resultSet.getBigDecimal(2)+","+resultSet.getBigDecimal(1)+")");
			log.info("retrieved userids");
			log.info("update_userdetails");
			
			//statement.executeUpdate("update  user_details set preid= "+"(select max(prefid) from preferences) where user_details.prdid= "+resultSet2.getBigDecimal(1));
			//log.info("update  store_travelPref"+r);
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to update userdetails . SQLException");
		}

		return update_userdetails;
	}
	public int store_travelPref(String travelPref, String seats, String image, String username)
	{
		int update_userdetails=0;
		String pref[] = travelPref.split(":");
		String newpref[] = travelPref.split(":");
		for(int i=0;i<pref.length;i++)
		{
			if(!pref[i].equals("null"))
			{
				System.out.println(pref[i]);
				newpref[i]="Y";
			}
			else
			{
				newpref[i]="N";
			}
			
		}
		
		for(int i=0;i<newpref.length;i++)
		{
			System.out.println(newpref[i]+"newpref[i]");
		}
		try
		{
			// DB statement
			statement = connection.createStatement();
			// result of executing SQL query whether updated or not
			System.out.println(resourceBundle.getString("getride")+username+"\"");
			resultSet = statement.executeQuery(resourceBundle.getString("getride")+username+"\"");
			//System.out.println("insert into ridepreferences (ladies,gents,handicap,music,smoke,seatavail,carimage) values("+"\""+newpref[0]+"\""+","+"\""+newpref[1]+"\""+","+"\""+newpref[2]+"\""+","+"\""+newpref[3]+"\""+","+"\""+newpref[4]+"\""+","+"\""+newpref[5]+"\""+","+"\""+seats+"\""+","+"\""+image+"\""+") ");
			resultSet.next();
			System.out.println(resultSet.getRow());
			//resultSet = statement.executeQuery(resourceBundle.getString("getids")+username+"\"");
			//System.out.println(resultSet.getFetchSize()+"resultSet.getFetchSize()resultSet.getFetchSize()");
			//resultSet1 = statement.executeQuery(resourceBundle.getString("get_trid_ride")+username+"\"");
			//resultSet1.next();
			//System.out.println(resourceBundle.getString("update_ride")+newpref[0]+"\""+","+"gents="+"\""+newpref[1]+"\""+","+"music="+"\""+newpref[2]+"\""+","+"smoke="+"\""+newpref[3]+"\""+","+"children="+"\""+newpref[4]+"\""+","+"handicap="+"\""+newpref[5]+"\""+","+"seatavail="+"\""+seats+"\""+","+"carimage="+"\""+image+"\""+resourceBundle.getString("update_ride1")+resultSet1.getBigDecimal(1));
			//resultSet.next();
			int s = resultSet.getRow();
			System.out.println(s+"Size of result set");
			//System.out.println(resultSet.getHoldability()+"resultSet.getHoldability();");
			if(s>0)
			{				
				resultSet1 = statement.executeQuery(resourceBundle.getString("get_trid_ride")+username+"\"");
				resultSet1.next();
				int res=statement.executeUpdate(resourceBundle.getString("update_ride")+newpref[0]+"\""+","+"gents="+"\""+newpref[1]+"\""+","+"music="+"\""+newpref[2]+"\""+","+"smoke="+"\""+newpref[3]+"\""+","+"children="+"\""+newpref[4]+"\""+","+"handicap="+"\""+newpref[5]+"\""+","+"seatavail="+"\""+seats+"\""+","+"carimg="+"\""+image+"\""+resourceBundle.getString("update_ride1")+resultSet1.getBigDecimal(1));
				System.out.println(res+" in in");
			}
			else
			{
				System.out.println(resourceBundle.getString("insertride")+newpref[0]+"\""+","+"\""+newpref[1]+"\""+","+"\""+newpref[2]+"\""+","+"\""+newpref[3]+"\""+","+"\""+newpref[4]+"\""+","+"\""+newpref[5]+"\""+","+"\""+seats+"\""+","+"\""+image+"\""+") ");
				int r = statement.executeUpdate(resourceBundle.getString("insertride")+newpref[0]+"\""+","+"\""+newpref[1]+"\""+","+"\""+newpref[2]+"\""+","+"\""+newpref[3]+"\""+","+"\""+newpref[4]+"\""+","+"\""+newpref[5]+"\""+","+"\""+seats+"\""+","+"\""+image+"\""+") ");
				System.out.println("insert into preferences(travelid) values(select max(trid) from ridepreferences)");
				resultSet = statement.executeQuery("select max(trid) from ridepreferences");
				resultSet.next();
				statement.executeUpdate("insert into preferences(travelid) values("+resultSet.getBigDecimal(1)+")");
				resultSet1 = statement.executeQuery(resourceBundle.getString("getpid")+username+"\"");
				resultSet1.next();
				statement.executeUpdate(resourceBundle.getString("update_userdetails1")+resultSet1.getBigDecimal(1));
				log.info("update  store_travelPref"+r);
			}			
			
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to update userdetails . SQLException");
		}

		return update_userdetails;
	}
	
	public String get_travelbasedPref(String username)
	{
		String result=null;
		try
		{
			log.info(username+" in time based preferences");
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("getride")+username+"\"");
			resultSet = statement.executeQuery(resourceBundle.getString("getride")+username+"\"");
			resultSet.next();
			System.out.println(resultSet.getRow());
			if(resultSet.getRow()>0)
			{
			result =resultSet.getString("ladies")+"::"+resultSet.getString("gents")+"::"+resultSet.getString("music")+"::"+ resultSet.getString("smoke")+"::"+  resultSet.getString("children")+"::"+resultSet.getString("handicap")+"::"+ resultSet.getString("seatavail")+"::"+ resultSet.getString("carimg");
			System.out.println(resultSet.getFetchSize()+"dssssssssssssssssssssssss");
			}
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to get timebasedPref  . SQLException");
			return result;
		}

		return result;
	}
	

	public String store_timebasedPref(String weekdays,String tsource, String tdestination, String timing, String tlocation, String username)
	{
		String result="";
		try
		{
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("getTimeBased")+username+"\"");
			resultSet = statement.executeQuery(resourceBundle.getString("getTimeBased")+username+"\"");
			resultSet.next();
			if(resultSet.getRow()>0)
			{		resultSet.close();		
			System.out.println(resourceBundle.getString("get_tbid_timebased")+username+"\"");
				resultSet = statement.executeQuery(resourceBundle.getString("get_tbid_timebased")+username+"\"");
				resultSet.next();
				System.out.println(resourceBundle.getString("update_timebased")+"\""+weekdays+"\""+","+"source="+"\""+tsource+"\""+","+"destination="+"\""+tdestination+"\""+","+"startime="+"\""+timing+"\""+","+"location="+"\""+tlocation+"\""+resourceBundle.getString("update_timebased1")+resultSet.getBigDecimal(1));
				statement.executeUpdate(resourceBundle.getString("update_timebased")+"\""+weekdays+"\""+","+"source="+"\""+tsource+"\""+","+"destination="+"\""+tdestination+"\""+","+"startime="+"\""+timing+"\""+","+"location="+"\""+tlocation+"\""+resourceBundle.getString("update_timebased1")+resultSet.getBigDecimal(1));
				resultSet.close();	
			}
			else
			{
				System.out.println(resourceBundle.getString("insert_timebased")+"\""+weekdays+"\""+","+"\""+tsource+"\""+","+"\""+tdestination+"\""+","+"\""+timing+"\""+","+"\""+tlocation+"\""+") ");
				statement.executeUpdate(resourceBundle.getString("insert_timebased")+"\""+weekdays+"\""+","+"\""+tsource+"\""+","+"\""+tdestination+"\""+","+"\""+timing+"\""+","+"\""+tlocation+"\""+") ");
				resultSet = statement.executeQuery(resourceBundle.getString("maxtimebasedrecord"));
				resultSet.next();
				//System.out.println(resourceBundle.getString("insert_pref")+resultSet.getBigDecimal(1)+")");
				statement.executeUpdate(resourceBundle.getString("insert_pref")+resultSet.getBigDecimal(1)+")");
				//System.out.println(resourceBundle.getString("getpid")+username+"\"" );
				resultSet.close();	
				resultSet2 = statement.executeQuery(resourceBundle.getString("getpid")+username+"\"" );
				resultSet2.next();
				
				System.out.println(resultSet2.getBigDecimal(1));
				statement.executeUpdate(resourceBundle.getString("update_userdetails1")+resultSet2.getBigDecimal(1));
				log.info("update  store_travelPref");
					
			}
			
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to store in timebasedPref  . SQLException");
		}

		return result;
	}


	public String get_timebasedPref(String username)
	{
		String result=null;
		try
		{
			log.info(username+" in time based preferences");
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("getTimeBased")+username+"\"");
			resultSet = statement.executeQuery(resourceBundle.getString("getTimeBased")+username+"\"");
			resultSet.next();
			if(resultSet.getRow()>0)
			{
				result =resultSet.getString("source")+"::"+resultSet.getString("destination")+"::"+resultSet.getString("startime")+"::"+resultSet.getString("location")+"::"+ resultSet.getString("days");
				System.out.println(result+"roshan");
			}
			
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to get timebasedPref as there are no values in the db");
			
		}

		return result;
	}
	
	public void journeydetails(String uname, String src, String dest, String stime, String usermode, String seats)
	{
	   try
		{
		   
			statement = connection.createStatement();
			//inserting values to driver journey details and getting uid from user details using username
			resultSet = statement.executeQuery(resourceBundle.getString("getuid")+uname+"\"");
			resultSet.next();
			statement.executeUpdate(resourceBundle.getString("journeydetails")+src+"\""+","+"\""+dest+"\""+","+resultSet.getBigDecimal(1)+","+"\""+stime+"\""+")");
			
			log.info("updated user details and Stored driver journeydetails");
			
			//Storing driver ride details by getting jid from journey details
			resultSet = null;
			resultSet = statement.executeQuery(resourceBundle.getString("getjid"));
			resultSet.next();
			statement.executeUpdate(resourceBundle.getString("insertride")+resultSet.getBigDecimal(1)+","+"\""+seats+"\""+")");
			log.info("stored driver ride details");

			//updating user_details by inserting user mode id 			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
		}
	}
	public void riderJourneydetails(String uname, String src, String dest, String stime, String usermode)
	{
	   try
		{
		   
			statement = connection.createStatement();
			//inserting values to driver journey details and getting uid from user details using username
			resultSet = statement.executeQuery(resourceBundle.getString("getuid")+uname+"\"");
			resultSet.next();
			statement.executeUpdate(resourceBundle.getString("journeydetails")+src+"\""+","+"\""+dest+"\""+","+resultSet.getBigDecimal(1)+","+"\""+stime+"\""+")");
			
			log.info("updated user details and Stored rider journeydetails");			
			//updating user_details by inserting user mode id 			

		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
		}
	}
	//updating user details after inserting journey details
	public void updateUserdetails(String username, String usermode)
	{
		try 
		{
			statement = connection.createStatement();
			//System.out.println("test1");
			resultSet = statement.executeQuery(resourceBundle.getString("getuid")+username+"\"");
			//System.out.println("test2");
			resultSet.next();
			statement.executeUpdate("update user_details set modeid="+"("+resourceBundle.getString("getusermode")+usermode+"\""+")"+" where uid="+resultSet.getBigDecimal(1));
			System.out.println("test3");
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
		}
		
		
	}
	public void insterRidemembers(String username, String usermode)
	{
		try 
		{
			statement = connection.createStatement();
			//System.out.println("test1");
			resultSet = statement.executeQuery(resourceBundle.getString("getuid")+username+"\"");
			//System.out.println("test2");
			resultSet.next();
			statement.executeUpdate(resourceBundle.getString("insertridemembers")+"select max(rid) from ride), "+resultSet.getBigDecimal(1)+" , "+"\""+usermode+"\")");
			
			System.out.println("test4");
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
		}		
	}
	public ArrayList<String>  getRidelist(String rsource, String rdestination, String rstime,String rid)
	{
		
		int count=0;
		
		try
		{
			ArrayList<String>list=new ArrayList<String>();
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("ridelist")+rsource+"\""+" and jdestination="+"\""+rdestination+"\""+" and stime=\""+rstime+"\") and user_details.prdid=personal_details.pid");
			resultSet=statement.executeQuery(resourceBundle.getString("ridelist")+rsource+"\""+" and jdestination="+"\""+rdestination+"\""+" and stime=\""+rstime+"\") and user_details.prdid=personal_details.pid and journey_details.userid=user_details.uid");
			
			while(resultSet.next())
			{
				System.out.println(rid.toString()+"-----------------"+resultSet.getString("username"));
				if(rid.toString().equals(resultSet.getString("username")))
				{
					
				}
				else
				{
					
					if(resultSet.getString("jsource").toString().trim().equals(rsource) && resultSet.getString("jdestination").toString().trim().equals(rdestination) && resultSet.getString("stime").toString().trim().equals(rstime) )
					{
						count++;
						String str="";
						
						str=str+resultSet.getString("jsource")+"KRL";
						str=str+resultSet.getString("jdestination")+"KRL";
						str=str+resultSet.getString("username")+"KRL";
						str=str+resultSet.getString("address")+"KRL";
						str=str+resultSet.getString("gender")+"KRL";
						str=str+resultSet.getString("mobile")+"KRL";
	//					str=str+resultSet.getString("jid")+"KPL";
	//					str=str+resultSet.getString("userid")+"KPL";
	//					str=str+resultSet.getString("locid")+"KPL";
	//					str=str+resultSet.getString("prdid")+"KPL";
	//					str=str+resultSet.getString("accid")+"KPL";
	//					str=str+resultSet.getString("netid")+"KPL";
	//					str=str+resultSet.getString("modeid")+"KPL";
	//					str=str+resultSet.getString("preid")+"KPL";
	//					str=str+resultSet.getString("pid")+"KPL";
	//					str=str+resultSet.getString("dob")+"KPL";
	//					str=str+resultSet.getString("mobile")+"KPL";
					    str=str+resultSet.getString("image")+"KPLL";
						
						System.out.println(resultSet.getString("image"));
						  list.add(str);
					}
					  
				}
				
			}
			return list;
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			//ex.printStackTrace();
		}
		return null;
		
	}
}
