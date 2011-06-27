package com.saventech.kaarpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.saventech.kaarpool.util.CommonUtils;
import com.saventech.kaarpool.util.DBUtils;

/**
 * Data Access Object for retrieving services
 * 
 * @author Chethan Reddy
 * @author sfhidayath
 *
 */
public class RequestDAO {

	private static final Logger logger = Logger.getLogger(RequestDAO.class);
	
	
	 
	
	
	
	/**
	 * 
	 * 
	 * @return String representation of XML response showing region name with their codes
	 */
	public String getHistoryAtLogin(String username, String password) {
		
		StringBuffer buffer = new StringBuffer();
		
		 
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT kid FROM kaarpoolnetwork_details  WHERE loginid=? AND loginpwd=?";
		
		try {
			con = DBUtils.getConnection();
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				buffer.append("<status>ok</status>");
				buffer.append("<history ")
						.append(createAttribute("page", 1, true))
						.append(createAttribute("per_page", 20, true))
						.append(">")
						.append(getHistory(username, 20, 1))
						.append("</history>");
			}
			else {
				buffer.append("<status>fail</status>");
			}
			
			rs.close();
			rs = null;
			
			stmt.close();
			stmt = null;
			
			con.close();
			con = null;
		} 
		catch (SQLException e) {
			logger.error("Login time : " + e.getMessage());
		}
		finally {
			if(con != null) {
				try { con.close(); } catch (SQLException e) {}
			}
		}

		return buffer.toString();
	}

	public String getHistory(String ridername, int per_page, int page) {
		
		StringBuffer buffer = new StringBuffer();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		/*String sql = "SELECT jd.jid, jd.jsource, jd.jdestination, pd.username, jd.locid, jd.stime, jd.etime " 
						+ "FROM journey_details jd, ride r, msg_updates mu, user_details ud, personal_details pd "
						+ "WHERE "
						+ "jd.userid = ud.uid AND "
						+ "ud.prdid = pd.pid AND "
						+ "jd.jid = r.jdid AND "
						+ "r.rid = mu.rdid AND "  
						+ "mu.ridername=? LIMIT ?, ?";*/
		
		String sql = "SELECT jd.jid, jd.jsource, jd.jdestination, pd.username, jd.locid, jd.stime, jd.etime, mu.drivername, mu.ridername "
						+ "FROM journey_details jd, ride r, msg_updates mu, user_details ud, personal_details pd "
						+ "WHERE "
						+ "jd.userid = ud.uid AND "
						+ "ud.prdid = pd.pid AND "
						+ "jd.jid = r.jdid AND "
						+ "r.rid = mu.rdid AND "
						+ "mu.messageid = 'r6' AND "
						+ "mu.status='stop' AND "
						+ "mu.ridername=? LIMIT ?, ?";
		
		try {
			con = DBUtils.getConnection();
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, ridername);
			stmt.setInt(2, ((page - 1 ) *  per_page));
			stmt.setInt(3, per_page);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				 buffer.append("<row ");
				 buffer.append(createAttribute("id", rs.getString(1), true));
				 buffer.append(createAttribute("source", rs.getString(2), true));
				 buffer.append(createAttribute("destination", rs.getString(3), true));
				 buffer.append(createAttribute("user", rs.getString(4), true));
				 buffer.append(createAttribute("location", rs.getString(5), true));
				 buffer.append(createAttribute("start_time", rs.getString(6), true));
				 buffer.append(createAttribute("end_time", rs.getString(7), true));
				 buffer.append(createAttribute("driver", rs.getString(8), true));
				 buffer.append(createAttribute("rider", rs.getString(9), true));
				 
				 buffer.append(" />");
			}
			
			rs.close();
			rs = null;
			
			stmt.close();
			stmt = null;
			
			con.close();
			con = null;
		} 
		catch (SQLException e) {
			logger.error("While getting history : " + e.getMessage());
		}
		finally {
			if(con != null) {
				try { con.close(); } catch (SQLException e) {}
			}
		}

		return buffer.toString();
	} 
	
	public boolean isValidUser(String username, String password) {
		boolean result = false;
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT kid FROM kaarpoolnetwork_details  WHERE loginid=? AND loginpwd=?";
		
		try {
			con = DBUtils.getConnection();
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			
			rs.close();
			rs = null;
			
			stmt.close();
			stmt = null;
			
			con.close();
			con = null;
		} 
		catch (SQLException e) {
			logger.error("Login time : " + e.getMessage());
		}
		finally {
			if(con != null) {
				try { con.close(); } catch (SQLException e) {}
			}
		}

		return result;
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
	
	public String createNode(String name, Object value, boolean flag) {
		
		if(value != null)
			return "<" + name + ">" + value + "</" + name + ">";
		if(flag)
			return "<" + name + ">" + "</" + name + ">";
		return "";
	}
	
	 
}
