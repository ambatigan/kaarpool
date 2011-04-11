

package com.saventech.kaarpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
 
import org.apache.log4j.Logger;

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
			int noOfRows = statement.executeUpdate("insert into info values("+"\""+uname+"\""+","+"\""+pwd+"\""+")");
		
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
			int insert = statement.executeUpdate("insert into personal_details (username,mobile,address,gender,dob,image) values("+"\""+uname+"\""+","+"\""+mobile+"\""+","+"\""+address+"\""+","+"\""+gender+"\""+","+"\""+dob+"\""+","+"\""+image+"\""+")");
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
			//System.out.println("insert into kaarpoolnetwork_details values("+"\""+uname+"\""+","+"\""+pwd+"\""+","+"\""+session+"\""+")");
			int s = statement.executeUpdate("insert into kaarpoolnetwork_details(loginid,loginpwd,ksession)values("+"\""+uname+"\""+","+"\""+pwd+"\""+","+"\""+session+"\""+")");
			System.out.println("Updated kaarpoolnetwork_details");
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
				resultSet = statement.executeQuery("select loginpwd from kaarpoolnetwork_details where loginid =" +"\""+ uname + "\"");
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
				System.out.println("checkingdddddddddddddddddddddddddddddddddddddddd");
				System.out.println("select loginpwd from kaarpoolnetwork_details where loginid =" +"\""+ registerid + "\"");
				resultSet = statement.executeQuery("select loginpwd from kaarpoolnetwork_details where loginid =" +"\""+ registerid + "\"");
				// move to the first (and only record)
				if(resultSet.next())
				{
					System.out.println("one row affected....................");
					return true;
				}
				
			}
			catch(Exception e)
			{
				return false;
			}
		}
		return false;
	}
}
