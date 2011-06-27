package com.saventech.kaarpool.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 
 * @author Chethan Reddy
 */
public class DBUtils {

	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(DBUtils.class);
	
	
	
	/**
	 * Datasource
	 */
	private static DataSource datasource = null;
	
	/**
	 * Get connection
	 * 
	 * @return {@link java.sql.Connection}
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return initializeDataSource();
	}

	/**
	 * Close the connection
	 * 
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		
		try {
			if(connection != null)
				connection.close();
		}
		catch(SQLException e) {
			log.error("Connection Close SQL Error Code : " + e.getErrorCode());
		}
	}
	
	/**
	 * Initialize the Datasource
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	private static Connection initializeDataSource() throws SQLException {

		if(datasource == null) {
			try {
				InitialContext initContext = new InitialContext();
				datasource = (DataSource) initContext.lookup(Constants.JNDI_NAME);
				log.info("Kaarpool DataSource " + datasource + " is initialized");
				return datasource.getConnection();
			} 
			catch (NamingException e) {
				log.fatal("Kaarpool DataSource Naming Exception : " + e.toString());
				throw new SQLException("No connection to the database");
			}			
		}
		
		return datasource.getConnection();
	}
}
