package com.saventech.kaarpool.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.saventech.kaarpool.dao.RequestDAO;

/**
 *
 * @author Chethan Reddy
 * @author sfhidayath
 */
public class LoginAction extends ActionSupport implements ServletResponseAware {

	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public String execute() throws Exception {

		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    
	    RequestDAO dao = new RequestDAO();
	    StringBuffer output = new StringBuffer();
	    
	    output.append("<response request=\"login\">");
	    
	    output.append(dao.getHistoryAtLogin(getUsername(), getPassword()));
	    
	    		
	    
	    output.append("</response>");
	    response.getWriter().print(output.toString());
	    
		return null;
	}
	
	 
	private String username;
	private String password;

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
	
	
	 
}
