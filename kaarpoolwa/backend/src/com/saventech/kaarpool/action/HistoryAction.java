package com.saventech.kaarpool.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.saventech.kaarpool.dao.RequestDAO;
 
 
/**
 * DAO class for History
 * 
 * @author Hidayath
 */
public class HistoryAction extends ActionSupport implements ServletResponseAware {

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
	    
	    int page = 1;
	    int perPage = 20;
	    
	    try{
	    	page = Integer.parseInt(getPage());
	    }catch(NumberFormatException e){	    	
	    }
	    
	    try{
	    	perPage = Integer.parseInt(getPerpage());
	    }catch(NumberFormatException e){	    	
	    }
	    
	    output.append("<response request=\"history\">");
	    output.append("<history page=\"" + page + "\" per_page=\"" + perPage + "\">");
	    
	    if(dao.isValidUser(getUsername(), getPassword()))
	    	output.append(dao.getHistory(getUsername(), perPage, page));
	    
	    output.append("</history>");
	    output.append("</response>");
	    
	    response.getWriter().print(output.toString());
	    
		return null;
	}
	
	private String username;
	private String password;
	
	private String page;
	private String perpage;
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPerpage() {
		return perpage;
	}

	public void setPerpage(String perPage) {
		this.perpage = perPage;
	}

	 
	
}
