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
				password = resultSet.getString("loginpwd");
				
				log.info("Fetched password");
			}
			catch (final SQLException ex)
			{
				log.fatal("Unable to retrieve password. SQLException");
				
			}
	
			// retrieved password (or) null if there is an error
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
			
			update_userdetails = statement.executeUpdate(resourceBundle.getString("update_userdetails")+ resultSet.getBigDecimal(2)+","+resultSet.getBigDecimal(1)+")");
			log.info("retrieved userids");
			log.info("update_userdetails");
		}
		catch (final SQLException ex)
		{
			ex.printStackTrace();
			log.fatal("Unable to update userdetails . SQLException");
		}

		// retrieves result as "1" if there's an update and "0" if not
		return update_userdetails;
	}
	public void journeydetails(String uname, String src, String dest, String stime, String usermode, String seats)
	{
	   try
		{
		   
			statement = connection.createStatement();
			//inserting values to driver journey details and getting uid from user details using username
			resultSet = statement.executeQuery(resourceBundle.getString("getuid")+uname+"\"");
			resultSet.next();
			statement.executeUpdate(resourceBundle.getString("driverjourneydetails")+src+"\""+","+"\""+dest+"\""+","+resultSet.getBigDecimal(1)+","+"\""+stime+"\""+")");
			
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
	//updating user details after inserting journey details
	public void updateUserdetails(String username, String usermode)
	{
		try 
		{
			statement = connection.createStatement();
			System.out.println("test1");
			resultSet = statement.executeQuery(resourceBundle.getString("getuid")+username+"\"");
			System.out.println("test2");
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
					count++;
					String str="";
					
					str=str+resultSet.getString("jsource")+"KPL";
					str=str+resultSet.getString("jdestination")+"KPL";
					str=str+resultSet.getString("username")+"KPL";
					str=str+resultSet.getString("address")+"KPL";
					str=str+resultSet.getString("gender")+"KPL";
					str=str+resultSet.getString("mobile")+"\n";
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
//					str=str+resultSet.getString("image")+"KPL";
					
					  list.add(str);
					  
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
