/**
 * 
 */
package com.saventech.kaarpool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.saventech.kaarpool.dao.RegisterDAO;
import com.saventech.kaarpool.exception.UserAlreadyExistsException;

/**
 * @author sfhidayath
 *
 */
public class RegisterAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private static final Logger logger = Logger.getLogger(RegisterAction.class);
	
	

	/**
	 * 
	 */
	public RegisterAction() {
	}
	
	@Override
	public String execute() throws Exception {
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    //logger.debug("Query String:" + request.getQueryString());
	    
	    RegisterDAO dao = new RegisterDAO();
	    
	    StringBuffer output = new StringBuffer();
	    
	    output.append("<response request=\"register\">");
	    
	    boolean status = false;
	    
	    try {
	    	status = dao.createUser(getUsername(), getPassword(), getDob(), getMobile(), getAddress(), getGender());
	    	
	    	if(status)
	    		output.append("<status>ok</status>");
	    	else
	    		output.append("<status>fail</status>");
	    }catch(UserAlreadyExistsException e){
	    	output.append("<status>fail</status>");
	    	output.append("<message>" + e.getMessage() + "</message>");
	    }

	    output.append("</response>"); 
	    
	    response.getWriter().print(output.toString());
	    
		return null;
	}
	
	
	private String username;
	private String password;
	private String dob;
	private String mobile;
	private String address;
	private String gender; 
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

}
