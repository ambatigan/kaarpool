package com.saventech.kaarpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.log4j.Logger;

import com.saventech.kaarpool.exception.UserAlreadyExistsException;
import com.saventech.kaarpool.util.DBUtils;

 

/**
 * 
 * Data Access Object for History
 * 
 * @author sfhidayath
 */
public class RegisterDAO {
	
	private static final Logger logger = Logger.getLogger(RegisterDAO.class);

	
	public boolean createUser(String username, 
			 					String password, 
			 					String dob, 
			 					String mobile, 
			 					String address, 
			 					String gender) throws UserAlreadyExistsException{
		 boolean result = false;
		 
		 Connection con = null;
		 PreparedStatement stmt = null, stmt2 = null;
		 
		 String sql = "INSERT INTO personal_details (username, dob, mobile, address, gender) VALUES (?, ?, ?, ?, ?)";
		 String sql2 = "INSERT INTO kaarpoolnetwork_details (loginid, loginpwd) VALUES (?, ?)";
		
		 try {
			 con = DBUtils.getConnection();
			 stmt = con.prepareStatement(sql);
			 stmt2 = con.prepareStatement(sql2);
			
			 stmt.setString(1, username);
			 stmt.setString(2, dob);
			 stmt.setString(3, mobile);
			 stmt.setString(4, address);
			 stmt.setString(5, gender);
			 
			 int count = stmt.executeUpdate();
			 
			 stmt2.setString(1, username);
			 stmt2.setString(2, password);
			 
			 int count2 = stmt2.executeUpdate();
			 
			 if(count > 0 && count2 > 0) {
				 result = true;
				 con.commit();
			 }
			 else {
				 con.rollback();
			 }
			 
			 stmt.close();
			 stmt = null;
			
			 con.close();
			 con = null;
		} 
		catch(SQLIntegrityConstraintViolationException e){
			throw new UserAlreadyExistsException("User ' " + username + "' already exists");
		}
		catch (Exception e) {
			logger.error("While registering user : class:" + e.getClass() + " details:" + e.getMessage());
		}
		finally {
			if(con != null) {
				try { con.close(); } catch (SQLException e) {}
			}
		}
		return result;
	}
	
	

	
	/**
	 * Get table column and value for the set clause of UPDATE statement
	 * 
	 * @param columnName Table column name
	 * @param columnValue Value for the column, int as a string
	 * @return A String that will be used in the set clause of the UPDATE statement 
	 */
	private String setClauseOfUpdate(String columnName, String columnValue){
		return ((columnValue != null && !columnValue.trim().equals(""))?(columnName + "=" + columnValue + ", "):"");
	}
	
	/**
	 * Creates a string of an attribute-value pair of an XML node
	 * 
	 * @param name Name of the attribute
	 * @param value Value for the attribute
	 * @param flag If value is null and flag is <code>true</code>, then an empty value is returned,
	 * 			else if flag is <code>false</code>, then the attribute value pair is skipped
	 * 			
	 * @return String of an attribute-value pair of an XML node
	 */
	public String createAttribute(String name, Object value, boolean flag) {
		if(name != null && name != ""){
			if(value != null)
				return name + "=\"" + value + "\" ";
			if(flag)
				return name + "=\"\" ";
		}
		return "";
	}
}
