/**
 * Project: KaarpoolServer
 * Package: com.saventech.kaarpool.dbInterface
 * File: DBInterface.java
 */

package com.saventech.kaarpool;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	ResultSet rs = null;

	/** The statement. */
	Statement statement = null;
	Statement stmt=null;
	
	public BigDecimal journeyid=null;
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
			statement = connection.createStatement();
			stmt=connection.createStatement();
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
			/*statement = connection.createStatement();
			stmt=connection.createStatement();*/
			statement.executeUpdate("insert into info values("+"\""+uname+"\""+","+"\""+pwd+"\""+")");
		
		}
			catch (final SQLException ex)
			{
				log.info("Unable to store the user info. SQLException"+ex.getStackTrace());
			}
	}
	public boolean registration(String uname, String mobile,String address ,String gender, String dob, String image )
	{
	   try
		{
			statement = connection.createStatement();
			// result of executing SQL query
			System.out.println(resourceBundle.getString("regQuery")+uname+"\""+","+"\""+mobile+"\""+","+"\""+address+"\""+","+"\""+gender+"\""+","+"\""+dob+"\""+","+"\""+image+"\""+")");
			statement.executeUpdate(resourceBundle.getString("regQuery")+uname+"\""+","+"\""+mobile+"\""+","+"\""+address+"\""+","+"\""+gender+"\""+","+"\""+dob+"\""+","+"\""+image+"\""+")");
			log.info("Inserted personal_details successfully");
			return true;
			
		}
		catch (final SQLException ex)
		{
			log.fatal("Unable to insert the user's personal info. SQLException"+ex.getStackTrace());
			ex.printStackTrace();
			
		}
		return false;
	}
	public boolean karpooldetails(String uname, String pwd, String session)
	{
	   try
		{
			statement = connection.createStatement();
			// result of executing SQL query
			statement.executeUpdate(resourceBundle.getString("insert_karpoolDetails") +uname+"\""+","+"\""+pwd+"\""+","+"\""+session+"\""+")");
			log.info("Updated kaarpoolnetwork_details");
			return true;
		}
		catch (final SQLException ex)
		{
			log.fatal("Unable to retrieve password. SQLException"+ex.getStackTrace());
			ex.printStackTrace();
			
		}
		return false;
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
	public boolean update_userdetails(String username)
	{
		
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
			return true;
			
			//statement.executeUpdate("update  user_details set preid= "+"(select max(prefid) from preferences) where user_details.prdid= "+resultSet2.getBigDecimal(1));
			//log.info("update  store_travelPref"+r);
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to update userdetails . SQLException");
		}

		return false;
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
				System.out.println(resourceBundle.getString("get_trid_ride")+username+"\"");
				resultSet1 = statement.executeQuery(resourceBundle.getString("get_trid_ride")+username+"\"");
				resultSet1.next();
				System.out.println(resourceBundle.getString("update_ride")+newpref[0]+"\""+","+"gents="+"\""+newpref[1]+"\""+","+"music="+"\""+newpref[2]+"\""+","+"smoke="+"\""+newpref[3]+"\""+","+"children="+"\""+newpref[4]+"\""+","+"handicap="+"\""+newpref[5]+"\""+","+"seatavail="+"\""+seats+"\""+","+"carimg="+"\""+image+"\""+resourceBundle.getString("update_ride1")+resultSet1.getBigDecimal(1));
				int res=statement.executeUpdate(resourceBundle.getString("update_ride")+newpref[0]+"\""+","+"gents="+"\""+newpref[1]+"\""+","+"music="+"\""+newpref[2]+"\""+","+"smoke="+"\""+newpref[3]+"\""+","+"children="+"\""+newpref[4]+"\""+","+"handicap="+"\""+newpref[5]+"\""+","+"seatavail="+"\""+seats+"\""+","+"carimg="+"\""+image+"\""+resourceBundle.getString("update_ride1")+resultSet1.getBigDecimal(1));
				System.out.println(res+" in in");
			}
			else
			{
				System.out.println(resourceBundle.getString("insert_ride")+newpref[0]+"\""+","+"\""+newpref[1]+"\""+","+"\""+newpref[2]+"\""+","+"\""+newpref[3]+"\""+","+"\""+newpref[4]+"\""+","+"\""+newpref[5]+"\""+","+"\""+seats+"\""+","+"\""+image+"\""+") ");
				int r = statement.executeUpdate(resourceBundle.getString("insert_ride")+newpref[0]+"\""+","+"\""+newpref[1]+"\""+","+"\""+newpref[2]+"\""+","+"\""+newpref[3]+"\""+","+"\""+newpref[4]+"\""+","+"\""+newpref[5]+"\""+","+"\""+seats+"\""+","+"\""+image+"\""+") ");
				System.out.println("select prefid from user_details,personal_details,preferences where user_details.preid=preferences.prefid and user_details.prdid=personal_details.pid and personal_details.username="+"\""+username+"\"");
				resultSet = statement.executeQuery("select prefid from user_details,personal_details,preferences where user_details.preid=preferences.prefid and user_details.prdid=personal_details.pid and personal_details.username="+"\""+username+"\"");
				resultSet.next();
				//System.out.println(resultSet.getBigDecimal(1));
				if(resultSet.getRow()>0)
				{
					System.out.println("in             ddddddd");
					//resultSet.close();
					//resultSet1 = statement.executeQuery("select max(trid) from ridepreferences");
					//resultSet1.next();
					System.out.println("update preferences set travelid="+"(select max(trid) from ridepreferences) "+"where preferences.prefid="+resultSet.getBigDecimal(1));
					statement.executeUpdate("update preferences set travelid="+"(select max(trid) from ridepreferences) "+"where preferences.prefid="+resultSet.getBigDecimal(1));
					//resultSet1.close();
				}
				else
				{
				//System.out.println("insert into preferences(travelid) values(select max(trid) from ridepreferences)");
				resultSet = statement.executeQuery("select max(trid) from ridepreferences");
				resultSet.next();
				System.out.println("insert into preferences(travelid) values("+resultSet.getBigDecimal(1)+")");
				statement.executeUpdate("insert into preferences(travelid) values("+resultSet.getBigDecimal(1)+")");
				
				}
				System.out.println(resourceBundle.getString("getpid")+username+"\"");
				resultSet1 = statement.executeQuery(resourceBundle.getString("getpid")+username+"\"");
				resultSet1.next();
				System.out.println(resourceBundle.getString("update_userdetails1")+resultSet1.getBigDecimal(1));
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
			{		//resultSet.close();		
				System.out.println(resourceBundle.getString("get_tbid_timebased")+username+"\""+"roshanffffffffffffffffff");
				resultSet1 = statement.executeQuery(resourceBundle.getString("get_tbid_timebased")+username+"\"");
				resultSet1.next();
				System.out.println(resourceBundle.getString("update_timebased")+"\""+weekdays+"\""+","+"source="+"\""+tsource+"\""+","+"destination="+"\""+tdestination+"\""+","+"startime="+"\""+timing+"\""+","+"location="+"\""+tlocation+"\""+resourceBundle.getString("update_timebased1")+resultSet1.getBigDecimal(1));
				statement.executeUpdate(resourceBundle.getString("update_timebased")+"\""+weekdays+"\""+","+"source="+"\""+tsource+"\""+","+"destination="+"\""+tdestination+"\""+","+"startime="+"\""+timing+"\""+","+"location="+"\""+tlocation+"\""+resourceBundle.getString("update_timebased1")+resultSet1.getBigDecimal(1));
				//resultSet.close();	
			}
			else
			{
				
				
				System.out.println(resourceBundle.getString("insert_timebased")+"\""+weekdays+"\""+","+"\""+tsource+"\""+","+"\""+tdestination+"\""+","+"\""+timing+"\""+","+"\""+tlocation+"\""+") ");
				statement.executeUpdate(resourceBundle.getString("insert_timebased")+"\""+weekdays+"\""+","+"\""+tsource+"\""+","+"\""+tdestination+"\""+","+"\""+timing+"\""+","+"\""+tlocation+"\""+") ");
				
				System.out.println("select prefid from user_details,personal_details,preferences where user_details.preid=preferences.prefid and user_details.prdid=personal_details.pid and personal_details.username="+"\""+username+"\"");
				
				
				
				rs = statement.executeQuery("select prefid from user_details,personal_details,preferences where user_details.preid=preferences.prefid and user_details.prdid=personal_details.pid and personal_details.username="+"\""+username+"\"");
				System.out.println(rs.getRow()+"resultSet.getRow() in store tarave");
				int i=0;
				while(rs.next())
				{
					i++;
				}
				System.out.println(i+"number of rows");
				if(i>0)
				{
					//resultSet.close();
//					resultSet = statement.executeQuery(resourceBundle.getString("maxtimebasedrecord"));
//					resultSet.next();
					System.out.println(resourceBundle.getString("update_pref1")+username+"\"");
					resultSet1 = statement.executeQuery(resourceBundle.getString("update_pref1")+username+"\"");
					resultSet1.next();
					//System.out.println(resourceBundle.getString("update_pref")+"(select max(tbid) from timebased_defaultloc)"+" where preferences.prefid="+resultSet1.getBigDecimal(1));
					System.out.println(resourceBundle.getString("update_pref")+"(select max(tbid) from timebased_defaultloc)"+" where preferences.prefid="+resultSet1.getBigDecimal(1));
					statement.executeUpdate(resourceBundle.getString("update_pref")+"(select max(tbid) from timebased_defaultloc)"+" where preferences.prefid="+resultSet1.getBigDecimal(1));
					//System.out.println(resourceBundle.getString("getpid")+username+"\"" );
//					resultSet1 = statement.executeQuery("select max(trid) from ridepreferences");
//					resultSet1.next();
//					statement.executeUpdate("update preferences set travelid="+resultSet1.getBigDecimal(1)+"where preferences.prefid="+resultSet.getBigDecimal(1));
//					resultSet1.close();
					System.out.println("greater than 0");
				}
				else
				{
					//resultSet1 = statement.executeQuery(resourceBundle.getString("update_pref1")+username+"\"");
					//resultSet1.next();
					resultSet = statement.executeQuery(resourceBundle.getString("maxtimebasedrecord"));
					resultSet.next();
					System.out.println("insert into preferences (tbdid) values ("+resultSet.getBigDecimal(1)+")");//where preferences.prefid="+resultSet1.getBigDecimal(1));
					statement.executeUpdate("insert into preferences (tbdid) values ("+resultSet.getBigDecimal(1)+")");// where preferences.prefid="+resultSet1.getBigDecimal(1));
				}
				
					System.out.println(resourceBundle.getString("getpid")+username+"\"" );
				resultSet2 = statement.executeQuery(resourceBundle.getString("getpid")+username+"\"" );
				resultSet2.next();
				
				System.out.println(resourceBundle.getString("update_userdetails1")+resultSet2.getBigDecimal(1));
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
	
	public void journeydetails(String uname, String routename, String src, String dest, String stime, String usermode, String seats)
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
			System.out.println(resourceBundle.getString("insertride")+resultSet.getBigDecimal(1)+","+"\""+seats+"\""+","+"\""+routename+"\""+")");
			statement.executeUpdate(resourceBundle.getString("insertride")+resultSet.getBigDecimal(1)+","+"\""+seats+"\""+","+"\""+routename+"\""+")");
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
	
	/*
	 * this method is used ot get the list of ride based on specified source, destinationa and time
	 */
	public ArrayList<String>  getRidelist(String rsource, String rdestination, String rstime,String rid)
	{
		
		int count=0;
		
		try
		{
			ArrayList<String>list=new ArrayList<String>();
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("ridelist")+rsource+"\""+" and jdestination="+"\""+rdestination+"\""+" and stime=\""+rstime+"\") and user_details.prdid=personal_details.pid and user_details.uid = journey_details.userid and journey_details.jid = ride.jdid and personal_details.username != \""+rid.toString()+"\" and user_details.modeid = 1 and ride.seats >0");
			resultSet=statement.executeQuery(resourceBundle.getString("ridelist")+rsource+"\""+" and jdestination="+"\""+rdestination+"\") and user_details.prdid=personal_details.pid and user_details.uid = journey_details.userid and journey_details.jid = ride.jdid and personal_details.username != \""+rid.toString()+"\" and user_details.modeid = 1 and ride.seats >0");
			
			while(resultSet.next())
			{
				System.out.println(rid.toString()+"-----------------"+resultSet.getString("username"));
				System.out.println(resultSet.getString("jsource").toString().trim()+"------"+rsource+" "+resultSet.getString("jdestination").toString().trim()+"----"+rdestination+" "+resultSet.getString("stime").toString().trim()+"----"+rstime);
				if(rid.toString().equals(resultSet.getString("username").toString().trim()))
				{
					
				}
				else
				{
					System.out.println(rid.toString()+"-----------------"+resultSet.getString("username"));
					
					if(resultSet.getString("jsource").toString().trim().equals(rsource) && resultSet.getString("jdestination").toString().trim().equals(rdestination))
					{
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm a");
						String ridertime[];
						String drivertime[];
						String ddate="";
						String rdate="";
						java.util.Date d;
						java.util.Date d1;
						int amvalue=0;
					    int pmvalue=0;
						if(rstime.toString().trim().contains("AM"))
						{
							ridertime=rstime.toString().trim().split("AM");
							String timesplit[]=ridertime[0].toString().trim().split(" ");
							rdate=timesplit[0].toString().trim();
							if(resultSet.getString("stime").toString().trim().contains("AM"))
							{
								String getridelisttime[]=resultSet.getString("stime").toString().trim().split("AM");
								String getridelistdatesplit[]=getridelisttime[0].toString().trim().split(" ");
								String getridelistdate=getridelistdatesplit[0].toString().trim();
								System.out.println(getridelistdate.toString().trim()+" ============================AM");
								if(rdate.toString().trim().equals(getridelistdate.toString().trim()))
								{
									try {
										d=dateFormat.parse(resultSet.getString("stime"));
										d1=dateFormat.parse(rstime.toString());
										
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										log.fatal("Date Format Exception"+e.getStackTrace());
										return null;
									}
									long dmil=d.getTime()-d1.getTime();	
									System.out.println(dmil+" Minutes");
									if(dmil/(60*1000)>=0 && dmil/(60*1000)<=5)
									{
										amvalue=1;
									}
								}
								
							}
							
							
							
						}
						if(rstime.toString().trim().contains("PM"))
						{
							ridertime=rstime.toString().trim().split("PM");
							String timesplit[]=ridertime[0].toString().trim().split(" ");
							rdate=timesplit[0].toString().trim();
							if(resultSet.getString("stime").toString().trim().contains("PM"))
							{
								String getridelisttime[]=resultSet.getString("stime").toString().trim().split("PM");
								String getridelistdatesplit[]=getridelisttime[0].toString().trim().split(" ");
								String getridelistdate=getridelistdatesplit[0].toString().trim();
								System.out.println(getridelistdate.toString().trim()+" ============================PM");
								if(rdate.toString().trim().equals(getridelistdate.toString().trim()))
								{
									try {
										d=dateFormat.parse(resultSet.getString("stime"));
										d1=dateFormat.parse(rstime.toString());
										
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										log.fatal("Date Format Exception"+e.getStackTrace());
										return null;
									}
									long dmil=d.getTime()-d1.getTime();	
									System.out.println(dmil+" Minutes");
									if(dmil/(60*1000)>=0 && dmil/(60*1000)<=5)
									{
										pmvalue=1;
									}
								}
								
							}
							
							
						}
						
						
						
						
						
						if(amvalue!=0||pmvalue!=0)
						{
							//System.out.println(dmil+" Minutes");
								if(rid.toString().trim().toLowerCase().equals(resultSet.getString("username").toString().trim()))
								{
									System.out.println("ride created with same username");
								}
								else
								{
									count++;
									String str="";
									System.out.println(rid.toString()+"-----------------"+resultSet.getString("username"));
									str=str+resultSet.getString("jsource")+"KRL";
									str=str+resultSet.getString("jdestination")+"KRL";
									str=str+resultSet.getString("username")+"KRL";
									str=str+resultSet.getString("stime")+"KRL";
									//str=str+resultSet.getString("address")+"KRL";
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
	
	public String getCancelroutedetails(String username)
	{
		
		String data="nodata";
		try
		{
			System.out.println("i am in get cancelroutedetails");
			statement = connection.createStatement();
			rs = statement.executeQuery(resourceBundle.getString("getDrivername")+username+"\"");
			rs.next();
			System.out.println(resourceBundle.getString("ridecancellation")+rs.getBigDecimal(1));
			String str = resourceBundle.getString("ridecancellation")+rs.getBigDecimal(1);
			//resultSet=statement.executeQuery(resourceBundle.getString("ridecancellation")+username+"\""+" order by stime DESC LIMIT 1");
			resultSet = statement.executeQuery(str);
			resultSet.next();
			int s = resultSet.getRow();
			System.out.println("getrow:"+s);
			if(s>0)
			{
				System.out.println("after query execution");
				
				data = resultSet.getString("jsource")+","+resultSet.getString("jdestination")+","+resultSet.getString("stime");
				System.out.println("cancelride: "+data);
				return data;
			} 
			else
			{
				System.out.println("no data for user: "+username);
				return "nodata";
			}
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
			return null;
		}
		
		
	}
	public boolean confirmRideDetails(String username, String csource, String cdestination, String ctime)
	{
		try {
			//boolean bool = false;
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet = statement.executeQuery(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet.next();
			System.out.println("bbbbbbbbbbbbbb");
			System.out.println(resourceBundle.getString("totalridedetails1")+resultSet.getBigDecimal(1)+")");
			rs = statement.executeQuery(resourceBundle.getString("totalridedetails1")+resultSet.getBigDecimal(1)+")");
			
			while(rs.next())
			{
				//System.out.println(csource+" "+rs.getString("jsource")+" "+cdestination+" "+rs.getString("jdestination")+" "+ctime+" "+rs.getString("stime"));
				if(csource.trim().equals(rs.getString(2).trim()) && cdestination.trim().equals(rs.getString(3).trim()) && ctime.trim().equals(rs.getString(4).trim()))
				{
					System.out.println("details are matched");
					journeyid = rs.getBigDecimal(1);
					System.out.println("journeyid in confirmridedetails: "+journeyid);
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public String driverRideCacellation(String username, String csource, String cdestination, String ctime)
	{
		System.out.println("DriverRideCancellation");
		try
		{
			//System.out.println("i am in get cancelroutedetails");
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("driverridecancel")+username+"\""+" order by jid DESC LIMIT 1");
			String str = resourceBundle.getString("driverridecancel")+username+"\""+" order by jid DESC LIMIT 1";
			System.out.println("aaaaaaaaaaaaaaaa");
			boolean check = confirmRideDetails(username, csource, cdestination, ctime);
			System.out.println("confirridedetails: "+check);
			if(check)
			{
				resultSet = statement.executeQuery(str);
				resultSet.next();
				int s = resultSet.getRow();
				System.out.println("getrow:"+s);
				if(s>0)
				{
					System.out.println("delete from ride where jdid = "+journeyid);
					statement.executeUpdate("delete from ride where jdid = "+journeyid);
					System.out.println("after query execution");
					rs = statement.executeQuery(str);
					rs.next();
					int s1 = rs.getRow();
					System.out.println("getrow1:"+s);
					if(s1>0)
					{
						System.out.println("delete from journey_details where jid = "+journeyid);
						statement.executeUpdate("delete from journey_details where jid = "+journeyid);
						return "driverridecancel completed";
					}
					else
					{
						System.out.println("no data for user: "+username);
						return "nodatafound";
					}
					
					
				} 
				else
				{
					System.out.println("no data for user: "+username);
					return "nodatafound";
				}				
			}
			else
				return "nodatafound";
			
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
			return null;
		}
	}
	
	/*public String getTotalRidedetails(String username)
	{
		
		String str ="";
		String st ="";
		
		try
		{
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet=statement.executeQuery(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet.next();
			System.out.println(resourceBundle.getString("totalridedetails")+resultSet.getBigDecimal(1)+")");

			//rs = statement.executeQuery(resourceBundle.getString("totalridedetails")+resultSet.getBigDecimal(1)+")");
			System.out.println(resourceBundle.getString("driverRidedetails")+username+"\"");
			rs = statement.executeQuery(resourceBundle.getString("driverRidedetails")+username+"\"");

			while(rs.next())
			{
				str += "@"+"Route: "+rs.getString("routename")+"\n"+"source: "+rs.getString("jsource")+"\n"+"dest: "+rs.getString("jdestination")+"\n";
				/**
				 * For displaying the route details to the user, using "," as a delimiter
				 * between the fields
				 *
				st += "@"+rs.getString("routename")+","+rs.getString("jsource")+ ","+rs.getString("jdestination")+","+rs.getString("stime")+","+rs.getInt("seats")+","+rs.getBigDecimal("jdid");
				System.out.println(str+"   getTotalRidedetails  "+st);
			}
			/**
			 * returning the two strings(First string is the concatenation of the label with the value and the second is just the values 
			 * that are needed for displaying the user route details
			 * 
			 * The two strings starts with "@" symbol bcoz the strings starts with the space
			 *
			return str+"::"+st;
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
		}
		return null;
	}*/
	
	public String getTotalRidedetails(String username)
	{
		
		String str ="";
		String st ="";
		
		try
		{
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet=statement.executeQuery(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet.next();
			System.out.println(resourceBundle.getString("totalridedetails")+resultSet.getBigDecimal(1)+")");

			//rs = statement.executeQuery(resourceBundle.getString("totalridedetails")+resultSet.getBigDecimal(1)+")");
			System.out.println(resourceBundle.getString("driverRidedetails")+username+"\"");
			rs = statement.executeQuery(resourceBundle.getString("driverRidedetails")+username+"\"");

			while(rs.next())
			{
				str += "@"+"Route: "+rs.getString("routename")+"\n"+"source: "+rs.getString("jsource")+"\n"+"dest: "+rs.getString("jdestination")+"\n";
				/**
				 * For displaying the route details to the user, using "," as a delimiter
				 * between the fields
				 */
				st += "@"+rs.getString("routename")+","+rs.getString("jsource")+ ","+rs.getString("jdestination")+","+rs.getString("stime")+","+rs.getInt("seats")+","+rs.getBigDecimal("jdid");
				//System.out.println(st+ "           bagiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
			}
			/**
			 * returning the two strings(First string is the concatenation of the label with the value and the second is just the values 
			 * that are needed for displaying the user route details
			 * 
			 * The two strings starts with "@" symbol bcoz the strings starts with the space
			 */
			return str+"::"+st;
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
		}
		return null;
	}


	public String checkDriverjourneydetails(String username, String src,
			String dest, String seats, String time) {
		// TODO Auto-generated method stub
		String str ="true";
		
		try
		{
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet=statement.executeQuery(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet.next();
			System.out.println("checkDriverdetails: "+resourceBundle.getString("checkDriverdetails")+resultSet.getBigDecimal(1)+")");
			rs = statement.executeQuery(resourceBundle.getString("checkDriverdetails")+resultSet.getBigDecimal(1));
			while(rs.next())
			{
				//str += "source: "+rs.getString("jsource")+"\n"+"dest: "+rs.getString("jdestination")+"\n"+"start time: "+rs.getString("stime")+"\n";
				//System.out.println(str);
				if(rs.getString("jsource").trim().equals(src) && rs.getString("jdestination").trim().equals(dest) && rs.getString("stime").trim().equals(time))
				{
					System.out.println("if condition: ");
					return str;
				}
			}
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
			
		}
		return "false";
		
	}

	public String checkRiderjourneydetails(String username, String src,	String dest, String time) 
	{
		String str ="true";
		try
		{
			statement = connection.createStatement();
			System.out.println(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet=statement.executeQuery(resourceBundle.getString("uidforridedetails")+username+"\"");
			resultSet.next();
			System.out.println(resourceBundle.getString("checkDriverdetails")+resultSet.getBigDecimal(1)+")");
			rs = statement.executeQuery(resourceBundle.getString("checkDriverdetails")+resultSet.getBigDecimal(1));
			while(rs.next())
			{
				if(rs.getString("jsource").trim().equals(src) && rs.getString("jdestination").trim().equals(dest) && rs.getString("stime").trim().equals(time))
				{
					System.out.println("if condition in check rider ride details: ");
					return str;
				}
			}
			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
			
		}
		return "false";
	}
	
	
	/*
	 * updating the msg_updates table when request was sent
	 */
	
	public String sendrequest(String desiredsendrequests,String ridername,String data)
	{
		int insertedvalues=0;
		int updatedvalues=0;
		String newdrivername="";
		System.out.println(desiredsendrequests+"DBINTERFACEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		try
		{
			String sendrequests[]=desiredsendrequests.split("::");
			System.out.println(desiredsendrequests+"DBINTERFACEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEFFFFFFFFFFFF");
			for(int i=0;i<sendrequests.length;i++)
			{
				String temp[]=sendrequests[i].split("CHECKBOX");
				System.out.println(resourceBundle.getString("sendrequest")+temp[2]+"\" and personal_details.pid=user_details.prdid and user_details.modeid=1 and journey_details.jsource=\""+temp[0]+"\" and journey_details.jdestination=\""+temp[1]+"\" and journey_details.stime=\""+temp[3]+"\" and user_details.uid=journey_details.userid and journey_details.jid=ride.jdid");
				resultSet=statement.executeQuery(resourceBundle.getString("sendrequest")+temp[2]+"\" and personal_details.pid=user_details.prdid and user_details.modeid=1 and journey_details.jsource=\""+temp[0]+"\" and journey_details.jdestination=\""+temp[1]+"\" and journey_details.stime=\""+temp[3]+"\" and user_details.uid=journey_details.userid and journey_details.jid=ride.jdid");
				if(resultSet.next())
				{
					String rid=resultSet.getString("rid");
					System.out.println(resourceBundle.getString("select_msgs")+rid+"\" and ridername=\""+ridername.toString().trim()+"\"");
					resultSet=statement.executeQuery(resourceBundle.getString("select_msgs")+rid+"\" and ridername=\""+ridername.toString().trim()+"\"");
					System.out.println("ddddddddddddddddddddddddddddddddddddd"+resultSet.getRow());
					if(resultSet.next())
					{
						updatedvalues++;
						if(data.toString().trim().equals("meteor"))         //checking for sending request to meteor
						{
						
						}
						else                          //update database
						{
							System.out.println(resourceBundle.getString("update_msgs")+"r1\""+" where rid=\""+rid+"\" and ridername=\""+ridername.toString().trim()+"\"");
							statement.executeUpdate(resourceBundle.getString("update_msgs")+"r1\""+" where rdid=\""+rid+"\" and ridername=\""+ridername.toString().trim()+"\"");
						}
					}
					else
					{
						insertedvalues++;
						System.out.println(resourceBundle.getString("get_source")+rid.toString().trim()+"\"");
						resultSet=statement.executeQuery(resourceBundle.getString("get_source")+rid.toString().trim()+"\"");
						if(resultSet.next())
						{
							System.out.println("stime is "+resultSet.getString("stime"));
							newdrivername=newdrivername+temp[2].toString().trim()+"-"+rid.toString().trim()+"-"+resultSet.getString("stime").toString().trim()+"###";
							System.out.println(newdrivername+"   lllllllllllllllllll");
						}
						if(data.toString().trim().equals("meteor"))          //checking to send request to meteor
						{
						
						}
						else                           //update database
						{
							System.out.println(resourceBundle.getString("insert_msgs")+"\""+rid+"\",\""+ridername.toString().trim()+"\",\""+temp[2]+"\",\"r1\",\"running\")");
							statement.executeUpdate(resourceBundle.getString("insert_msgs")+"\""+rid+"\",\""+ridername.toString().trim()+"\",\""+temp[2]+"\",\"r1\",\"running\")");
						}
					}
					//System.out.println(rid+"      RIDSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
				}
				else
				{
					System.out.println("Please select a ride");
				}
			}
			if(insertedvalues>0)
			{
				return "Your request has been sent###"+newdrivername;
			}
			else
			{
				if(updatedvalues>0)
				{
					return "Already you sent requests to this users"; 
				}
				else
				{
				   return "Please select a ride to send request:";
				}
			}
			//return "OK";
			
		}
		catch(Exception e)
		{
			log.fatal("SQLException"+e.getStackTrace()+"  888888888888");
			return "Sql exception";
		}
	}
	
	
	
	public String injectingEvents(String events) throws IOException
	{
		String MeteorIP=resourceBundle.getString("meteorip").toString().trim();
		String MeteorPort=resourceBundle.getString("meteorport").toString().trim();
		InjecttoMeteor injectMeteor=new InjecttoMeteor();
		String response=injectMeteor.injecttoMeteor(events, MeteorIP, MeteorPort);
		return response.toString().trim();
	}
	//updating database for acknowledgement screens
	
	public String updateAcknowledgementEvents(String chanelname1, String message)
	{
		
		String messagesplit[]=message.toString().trim().split("::");
			try
			{
				System.out.println(messagesplit.length+"dddddddddddddddddddddddddddddddddddddddddddddd");
				if(messagesplit.length>=3)
				{
				    String dname="";
				    String rname="";
					if(chanelname1.toString().trim().charAt(0)=='d')
					{
						System.out.println(messagesplit.length+"dddddddddddddddddddddddddddddddddddddddddddddd");
						dname=parseChannelname(chanelname1.toString().trim());;
						rname=parseChannelname(messagesplit[0].toString().trim());
						
						
						if(messagesplit[1].toString().trim().equals("r3")||messagesplit[1].toString().trim().equals("r5")||messagesplit[1].toString().trim().equals("r6"))
						{
							System.out.println(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\", status=\"stop\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and rdid=\""+messagesplit[2].toString().trim()+"\"");
							statement.executeUpdate(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\", status=\"stop\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and rdid=\""+messagesplit[2].toString().trim()+"\"");
						}
						else
						{
							System.out.println(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and status !=\"stop\" and rdid=\""+messagesplit[2].toString().trim()+"\"");
							statement.executeUpdate(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and status !=\"stop\" and rdid=\""+messagesplit[2].toString().trim()+"\"");
						}
						if(messagesplit[1].toString().trim().equals("r4"))
						{
							System.out.println(resourceBundle.getString("select_userid")+rname+"\"");
							resultSet=statement.executeQuery(resourceBundle.getString("select_userid")+rname+"\"");
							//System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd8");
							if(resultSet.next())
							{
								String uid=resultSet.getBigDecimal("uid").toString().trim();
								System.out.println(resourceBundle.getString("select_usermode")+messagesplit[2].toString().trim()+" and usrid="+resultSet.getBigDecimal("uid"));
								resultSet1=statement.executeQuery(resourceBundle.getString("select_usermode")+messagesplit[2].toString().trim()+" and usrid="+resultSet.getBigDecimal("uid"));
								System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
								System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
								if(resultSet1.next())
								{
									//System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjooo");
								}
								else
								{
									//System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkk"+uid);
									System.out.println(resourceBundle.getString("insertridemembers1")+messagesplit[2].toString().trim()+","+uid+",\"rider\")");
									statement.executeUpdate(resourceBundle.getString("insertridemembers1")+messagesplit[2].toString().trim()+","+uid+",\"rider\")");
								}
							}
							
						}
						//statement.executeUpdate(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and status !=\"stop\"");
						//statement.executeUpdate(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\" where drivername=\""+dname+"\" and ridername=\""+rname+"\"");
					}
					else if(chanelname1.toString().trim().charAt(0)=='r')
					{
						System.out.println(messagesplit.length+"rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
						rname=parseChannelname(chanelname1.toString().trim());;
						dname=parseChannelname(messagesplit[0].toString().trim());
						System.out.println(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and rdid=\""+messagesplit[2].toString().trim()+"\"");
						
						if(messagesplit[1].toString().trim().equals("d2")||messagesplit[1].toString().trim().equals("d4"))
						{
							
							statement.executeUpdate(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\", status=\"stop\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and rdid=\""+messagesplit[2].toString().trim()+"\"");
						}
						else
						{
							System.out.println(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and status !=\"stop\" and rdid=\""+messagesplit[2].toString().trim()+"\"");
							statement.executeUpdate(resourceBundle.getString("update_msgs")+messagesplit[1].toString().trim()+"\" where drivername=\""+dname+"\" and ridername=\""+rname+"\" and status !=\"stop\" and rdid=\""+messagesplit[2].toString().trim()+"\"");
						}
						
					}
				}
				//resultSet.close();
				return "Successfully updated";
			}
			catch(Exception e)
			{
				
				log.fatal("ControllerSQLException while updating acknowledgement events"+e.getStackTrace());
				return "Exception occred while updating database";
			}
		
		
	}
	public String parseChannelname(String str)
	{
		String chanel=str.substring(1, str.length());
		String channel[]=chanel.split("-");
		return channel[0].toString().trim()+"@"+channel[1].toString().trim()+".com";
	}
	public String rideHistory(String name,String role)
	{
		String history="";
		String usernames="";
		try{
			if(role.toString().trim().equals("rider"))
			{
				System.out.println(resourceBundle.getString("ride_history")+" ridername=\""+name+"\"))");
				resultSet=statement.executeQuery(resourceBundle.getString("ride_history")+" ridername=\""+name+"\"))");
				while(resultSet.next())
				{
					System.out.println(resourceBundle.getString("select_usernames")+resultSet.getString("userid")+"\""+";;;;;;;;;;;;;;;;;;;;;;;;;;");
					resultSet1=stmt.executeQuery(resourceBundle.getString("select_usernames")+resultSet.getString("userid")+"\"");
					System.out.println("lllllllllllllllllllllllllllllllll");
					if(resultSet1.next())
					{
						System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");
						usernames=resultSet1.getString("username");
					}
					history=history+resultSet.getString("jsource")+":::";
					history=history+resultSet.getString("jdestination")+":::";
					history=history+resultSet.getString("stime")+":::";
					history=history+usernames+"EVENT";
				}
			}
			if(role.toString().trim().equals("driver"))
			{
				System.out.println(resourceBundle.getString("ride_history")+" drivername=\""+name+"\"))");
				resultSet=statement.executeQuery(resourceBundle.getString("ride_history")+" drivername=\""+name+"\"))");
				while(resultSet.next())
				{
					resultSet1=stmt.executeQuery(resourceBundle.getString("select_usernames")+resultSet.getString("userid")+"\")");
					if(resultSet1.next())
					{
						usernames=resultSet1.getString("username");
					}
					history=history+resultSet.getString("jsource")+":::";
					history=history+resultSet.getString("jdestination")+":::";
					history=history+resultSet.getString("stime")+":::";
					history=history+usernames+"EVENT";
				}
				
			}
			return history;
			
		}
		catch(Exception e)
		{
			
			log.fatal("SQL exception while getting ride history"+e.getStackTrace());
			return "Exception in history";
		}
		
		
	}
	public String changeMode(String name, String mode)
	{
		try
		{
			System.out.println(resourceBundle.getString("update_mode")+Integer.parseInt(mode)+" where user_details.prdid=personal_details.pid and personal_details.username =\""+name+"\"");
			statement.executeUpdate(resourceBundle.getString("update_mode")+Integer.parseInt(mode)+" where user_details.prdid=personal_details.pid and personal_details.username =\""+name+"\"");
			return "Success";
		}
		catch(Exception e)
		{
			log.fatal("SQL exception while changing the mode"+e.getStackTrace());
			return "Exception in changing mode";
		}
	}
	public String updateSeats(String rideid, int seats)
	{
		try
		{
			System.out.println(resourceBundle.getString("update_setas_ride")+"+"+seats+" where rid="+rideid.toString().trim());
			if(seats>0)
			{
				System.out.println(resourceBundle.getString("update_setas_ride")+"+"+seats+" where rid="+rideid.toString().trim());
				statement.executeUpdate(resourceBundle.getString("update_setas_ride")+"+"+seats+" where rid="+rideid.toString().trim());
			}
			else
			{
				System.out.println(Integer.toString(+1)+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
				System.out.println(resourceBundle.getString("update_setas_ride")+seats+" where rid="+rideid.toString().trim());
				statement.executeUpdate(resourceBundle.getString("update_setas_ride")+seats+" where rid="+rideid.toString().trim());
			}
			//System.out.println("9999999999999999999999999999999999999999999999999999999999999999999999");
			return "success";
		}
		catch(Exception e)
		{
			log.fatal("SQL exception while updating seats"+e.getStackTrace());
			return "Exception in updating seats in updateseats";
		}
	}
	public String checkUpdateSeats(String rideid)
	{
		int numberofseats=0;
		try
		{
			System.out.println(resourceBundle.getString("check_seatstoupdate")+rideid.toString().trim()+"checkupdateseatssssssssssssssssssssssssssssssssss");
			resultSet=statement.executeQuery(resourceBundle.getString("check_seatstoupdate")+rideid.toString().trim());
			if(resultSet.next())
			{
				numberofseats=resultSet.getInt("seats");
			}
			if(numberofseats>0)
			{
				return "UPDATE";
			}
			else
			{
				return "CANNOT UPDATE";
			}
		}
		catch(Exception e)
		{
			log.fatal("SQL exception while updating seats"+e.getStackTrace());
			return "Exception in updating seats in checkupdateseats";
		}
	}

	public String getCurrentRidetime(String drivername, String ridername,
			String cur_time) {
		// TODO Auto-generated method stub
		return null;
	}

	public String storeCoordinates(String rideid, String lat, String lng, String username) {
		// TODO Auto-generated method stub
		try
		{
			resultSet = statement.executeQuery(resourceBundle.getString("checkgeo")+rideid+" and "+" username = "+"\""+username+"\"");
			if(resultSet.next())
			{
				//System.out.println("result set is null");
				System.out.println("update current_location set latitude= "+Double.parseDouble(lat)+","+"longitude= "+Double.parseDouble(lng)+" where ridid= "+rideid+" and username="+"\""+username+"\"");
				statement.executeUpdate("update current_location set latitude= "+Double.parseDouble(lat)+","+"longitude= "+Double.parseDouble(lng)+" where ridid= "+rideid+" and username="+"\""+username+"\"");
			}
			else
			{
				System.out.println(resourceBundle.getString("coordinates")+rideid+","+Double.parseDouble(lat)+","+Double.parseDouble(lng)+","+"\""+username+"\""+")");
				statement.executeUpdate(resourceBundle.getString("coordinates")+rideid+","+Double.parseDouble(lat)+","+Double.parseDouble(lng)+","+"\""+username+"\""+")");
			}
			
			return "Success";
		}
		catch(Exception e)
		{
			log.fatal("SQL exception while changing the mode"+e.getStackTrace());
			return "Exception in storing geo coordinates";
		}
	}

	public String traceUser(String username, String rideid) {
		// TODO Auto-generated method stub
		try
		{
			System.out.println(resourceBundle.getString("trackuser")+rideid.trim()+" and ridername="+"\""+username+"\"");
			resultSet=statement.executeQuery(resourceBundle.getString("trackuser")+rideid.trim()+" and ridername="+"\""+username+"\"");
			if(resultSet.next())
			{
				String drivername = resultSet.getString("drivername").toString().trim();
				System.out.println(resourceBundle.getString("usercoordinates")+rideid.trim()+" and username="+"\""+drivername+"\"");
				System.out.println("xxxxxxxxxxxxx");
				resultSet1=stmt.executeQuery(resourceBundle.getString("usercoordinates")+rideid.trim()+" and username="+"\""+drivername+"\"");
				if(resultSet1.next())
				{
					String geocoord = resultSet1.getDouble(1)+"::"+resultSet1.getDouble(2);
					System.out.println("geo coordinates: "+geocoord);
					return geocoord;
				}
			}
			return null;
		}
		catch(Exception e)
		{
			return "Exception in storing geo coordinates";
		}
	}

	public String gpsTime(String username, String date, String time) {
		try
		{
			System.out.println(resourceBundle.getString("gpstime")+username+"\""+") and stime RLIKE "+"\"^"+date+"\"");
			resultSet=statement.executeQuery(resourceBundle.getString("gpstime")+username+"\""+") and stime RLIKE "+"\"^"+date+"\"");
			while(resultSet.next())
			{
				String gpstime = resultSet.getString("stime").toString().trim();
				System.out.println("Timings are: "+gpstime);
				if(checkTime_GPS(gpstime))
				{
					return "true";
				}
				
			}
			return "false";
		}
		catch(Exception e)
		{
			return "Exception in getting gps time";
		}
	}
	public boolean checkTime_GPS(String ridetime) 
    {
		// TODO Auto-generated method stub
    	System.out.println("I am in checkTime_GPS");
        //String ridername = session.getUsername(mPreferences);
        Calendar today=Calendar.getInstance();
		System.out.println(today.getTimeInMillis()+"kkkkkkkkkkkkkkkkkk"+today.getTime());
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
		   return false;
		}
		return false;
        //String response = controller.getCurrentRideTime(drivername, ridername, cur_time);
        //System.out.println("Get ride time response: "+response);
		
	}

	public String saveDriverroute(String jid, String route, String source,
			String destination, String seats, String time) {
		try
		{
		   
			statement = connection.createStatement();
			System.out.println("update journey_details set jsource="+"\""+source+"\""+", jdestination = "+"\""+destination+"\""+", stime = "+"\""+time+"\""+" where jid = "+jid.trim());
			statement.executeUpdate("update journey_details set jsource="+"\""+source+"\""+", jdestination = "+"\""+destination+"\""+", stime = "+"\""+time+"\""+" where jid = "+jid.trim());
			
			log.info("updated route details and Stored driver journeydetails");
			
			//Storing driver ride details by getting jid from journey details
			resultSet = null;
			System.out.println("update ride set seats="+Integer.parseInt(seats)+", routename = "+"\""+route+"\""+" where jid = "+jid.trim());
			statement.executeUpdate("update ride set seats="+Integer.parseInt(seats)+", routename = "+"\""+route+"\""+" where jdid = "+jid.trim());
			log.info("updated driver ride details");
			return "successfully updated";
			//updating user_details by inserting user mode id 			
		}
		catch (final SQLException ex)
		{
			log.fatal("SQLException"+ex.getStackTrace());
			ex.printStackTrace();
			return "updating exception";
		}
		
	}

	public String GPSCoordiantes_Distance(String rideid, String ridername) {
		try
		{
			System.out.println(resourceBundle.getString("usercoordinates")+rideid.trim()+" and username="+"\""+ridername+"\"");
			System.out.println("yyyyyyyyyyyyyy");
			resultSet1=stmt.executeQuery(resourceBundle.getString("usercoordinates")+rideid.trim()+" and username="+"\""+ridername+"\"");
			if(resultSet1.next())
			{
				System.out.println("if condition after yyyyyyyyyyy");
				String geocoord = resultSet1.getString(1)+"::"+resultSet1.getString(2);
				System.out.println("geo coordinates: "+geocoord);
				return geocoord;
			}
			return null;
		}
		catch(Exception e)
		{
			return "Exception in getting geo coordinates";
		}
	}

}
