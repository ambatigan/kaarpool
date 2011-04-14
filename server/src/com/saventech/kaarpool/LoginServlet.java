package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	/** The log. */
	Logger log = Logger.getLogger(LoginServlet.class);
  //  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String userid,userpwd;
        userid=request.getParameter("loginuserid");
        userpwd=request.getParameter("loginuserpwd");
     
        DBInterface connect = DBInterface.getInstance();
  	   if(connect.isConnectionOpen())
  	   {
  		     String pwd = connect.getPwd(userid);
  		     try
  		     {
	  		     if(!pwd.equals(null))
	  		     {
	  		        
		  		     if(userid.equalsIgnoreCase(userid) && userpwd.equals(pwd))
		  			 {
		  				out.print("YES");
		  				log.info("User is successfully logged in");
		  		     }
		  		     else
		  		     {
		  		        out.print("NO");
		  		        log.info("User credentials are not correct");
		  		     }
	
	  		     }
	  		     else
	  		     {
	  		    	 out.println("NOT Exist");
	  		    	 log.info("User with "+userid+" doesnot exist");
	  		     }
  		     }
  		     catch(Exception e)
  		     {
  		    	log.info("User with "+userid+" doesnot exist. Please register");
  		     }
  	     }
  		     
  	  }	
}
